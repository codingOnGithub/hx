package com.hotent.base.core.soap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.NodeList;

import com.hotent.base.core.engine.script.GroovyScriptEngine;
import com.hotent.base.core.soap.type.SoapType;
import com.hotent.base.core.soap.type.SoapTypes;
import com.hotent.base.core.util.BeanUtils;
import com.hotent.base.core.util.string.StringEscaper;

/**
 * webservice入参处理器
 * 
 * @author heyifan
 * 
 */
public class RequestBuilder {

	private static Logger logger = LoggerFactory
			.getLogger(RequestBuilder.class);

	/**
	 * 构建入参
	 * 
	 * @param inputs
	 *            输入JSONArray
	 * @param inputParams
	 * @param namespace
	 * @param method
	 * @param variables
	 * @param soapaction
	 * @return
	 * @throws Exception
	 *             SOAPMessage
	 * @since 1.0.0
	 */
	public static SOAPMessage build(JSONArray inputs, JSONArray inputParams,
			String namespace, String method, Map<String, Object> variables,
			String soapaction) throws Exception {
		if (StringUtils.isNotEmpty(soapaction))
			soapaction = namespace + method;
		return buildRequest(
				createRequest(inputs, inputParams, namespace, method,
						variables, soapaction), soapaction);
	}

	private static SOAPMessage buildRequest(SOAPElement element,
			String soapaction) throws SOAPException {
		// 创建消息工厂
		MessageFactory messageFactory = MessageFactory.newInstance();
		// 根据消息工厂创建SoapMessage
		SOAPMessage message = messageFactory.createMessage();

		if (StringUtils.isNotEmpty(soapaction)) {
			MimeHeaders mHers = message.getMimeHeaders();
			mHers.setHeader("SOAPAction", soapaction);
		}

		// 创建soap消息主体
		SOAPPart soapPart = message.getSOAPPart();// 创建soap部分
		SOAPEnvelope envelope = soapPart.getEnvelope();
		// 可以通过SoapEnvelope有效的获取相应的Body和Header等信息
		SOAPBody body = envelope.getBody();

		body.addChildElement(element);
		// Save the message
		message.saveChanges();
		return message;
	}

	private static void buildSoapElementValue(SOAPElement soapElement,
			JSONObject jobject, Map<String, Object> variables) throws Exception {
		if (jobject == null)
			return;
		// 设值
		String binding = jobject.getString("bindingVal");// 绑定参数
		String soapType = jobject.getString("soapType");// soap类型
		String javaType = jobject.getString("javaType");// java类型
		Integer bindingType = jobject.getInt("bindingType");// 绑定类型
		binding = StringEscaper.unescapeJson(binding);

		String listObj = "";
		String elementStr = "";
		Pattern regex = Pattern.compile("^.*\\.(\\w+)\\[i\\](\\.\\w+)?$");
		Matcher regexMatcher = regex.matcher(binding);
		if (regexMatcher.find()) {
			listObj = regexMatcher.group(1);
			elementStr = regexMatcher.group(2);
		}
		try {
			Object obj = null;
			switch (bindingType) {
			// 固定值
			case 1:
				soapElement.setTextContent(binding);
				break;
			// 来自流程变量
			case 2:
				if (StringUtils.isNotEmpty(binding)) {
					obj = PropertyUtils.getProperty(variables, binding);
				}
				break;
			// 脚本获取
			case 3:
				GroovyScriptEngine scriptEngine = new GroovyScriptEngine();
				String scriptContent = binding;
				obj = scriptEngine.executeObject(scriptContent, variables);
				break;
			}
			// 有值时才设置
			if (obj != null) {
				SoapType converter = null;
				Class<?> klass = null;
				// webservice入参
				if (soapType != null) {
					converter = SoapTypes.getTypeBySoap(soapType);
				}
				// 流程变量的类型
				else if (javaType != null) {
					klass = Class.forName(javaType);
					converter = SoapTypes.getTypeByBean(klass);
				}
				// 该值不为空 说明 该webservice入参为 List类型
				if (StringUtils.isNotEmpty(listObj)) {
					if (obj instanceof List) {
						List<?> list = (List<?>) obj;
						String elementName = soapElement.getLocalName();
						SOAPElement parentElement = soapElement;
						// 判断绑定设置是 针对 List对象 还是 List对象下的一个属性
						if (StringUtils.isNotEmpty(elementStr))
							parentElement = soapElement.getParentElement();
						// 如果列表的个数为0，则从soapElement结构中 移除该节点
						if (list.size() == 0) {
							parentElement.detachNode();
							return;
						}

						SOAPElement grandpaElement = parentElement
								.getParentElement();
						listObj = parentElement.getTagName();
						NodeList fieldNodeList = grandpaElement
								.getElementsByTagName(listObj);
						if (fieldNodeList == null)
							return;
						int nodeCount = fieldNodeList.getLength();
						int listSize = list.size();
						int diffCount = listSize - nodeCount;
						// 根据List类型的个数 补齐 缺少的 xml节点
						for (int i = 0; i < diffCount; i++) {
							SOAPElement cloneElement = (SOAPElement) parentElement
									.cloneNode(true);
							grandpaElement.addChildElement(cloneElement);
						}
						fieldNodeList = grandpaElement
								.getElementsByTagName(listObj);
						for (int i = 0; i < listSize; i++) {
							Object item = list.get(i);
							SOAPElement listElement = (SOAPElement) fieldNodeList
									.item(i);
							SOAPElement itemElement = listElement;
							if (StringUtils.isNotEmpty(elementStr))
								itemElement = (SOAPElement) listElement
										.getElementsByTagName(elementName)
										.item(0);
							if (item == null) {
								itemElement.detachNode();
								continue;
							}
							if (converter != null) {
								converter.setValue(itemElement, item, klass);
							} else {
								itemElement.setTextContent(item.toString());
							}
						}
					}
				} else {
					if (converter != null) {
						converter.setValue(soapElement, obj, klass);
					} else {
						soapElement.setTextContent(obj.toString());
					}
				}
			}
			String textContext = soapElement.getTextContent();
			boolean hasChild = soapElement.hasChildNodes();
			// 如果节点既没有子节点 也没有值 则移除该节点（否则调用时 会报错）
			if (StringUtils.isEmpty(textContext) && !hasChild) {
				soapElement.detachNode();
			}
		} catch (Exception e) {
			logger.error("动态设值出错.", e);
			throw e;
		}
	}

	/**
	 * 根据模板创建{@link SOAPElement}对象.<br>
	 * 
	 * @param document
	 * @param variables
	 * @return
	 * @throws Exception
	 */
	private static SOAPElement createRequest(JSONArray inputs,
			JSONArray inputParams, String namespace, String method,
			Map<String, Object> variables, String soapaction) throws Exception {
		String prefix = "api";
		if (StringUtils.isNotEmpty(soapaction))
			prefix = "";
		SOAPFactory factory = SOAPFactory.newInstance();
		SOAPElement bodyElement = factory.createElement(method, prefix,namespace);

		if (BeanUtils.isNotEmpty(inputParams)) {
			Map<JSONObject, SOAPElement> map = new HashMap<JSONObject, SOAPElement>();
			for (Object obj : inputParams) {
				JSONObject jobject = (JSONObject) obj;
				if (jobject == null)
					continue;
				String rootName = jobject.getString("name");
				
				SOAPElement rootElement = bodyElement.addChildElement(rootName);
			
				// 递归构建soapElement
				setRequestStruct(jobject, rootElement, 1);
				// 绑定值
				setBindingValue(inputs, rootElement, 1, rootName, variables,
						map);
			}

			for (Iterator<JSONObject> it = map.keySet().iterator(); it
					.hasNext();) {
				JSONObject bindingJobject = it.next();
				SOAPElement soapElement = map.get(bindingJobject);
				buildSoapElementValue(soapElement, bindingJobject, variables);
			}
		} else {
			for (Object obj : inputs) {
				JSONObject jobject = (JSONObject) obj;
				if (jobject == null)
					continue;
				String paramName = jobject.getString("name");
				SOAPElement element = bodyElement.addChildElement(paramName);
				// 递归绑定
				buildSoapElementValue(element, jobject, variables);
			}
		}
		return bodyElement;
	}

	/**
	 * 构建输入参数的结构
	 * 
	 * @param jobject
	 * @param soapElement
	 * @param level
	 *            结构中的层级
	 * @param prefix
	 * @throws SOAPException
	 */
	private static void setRequestStruct(JSONObject jobject,
			SOAPElement soapElement, int level) throws SOAPException {
		String paramName = jobject.getString("name");
		String type = jobject.getString("type");
		
		// 根节点
		SOAPElement element = (level == 1) ? soapElement : soapElement.addChildElement(paramName);
		// 复合类型需要添加到结构中
		if (!"bean".equals(type))
			return;
		if (jobject.containsKey("children")) {
			JSONArray children = jobject.getJSONArray("children");
			level++;
			for (Object obj : children) {
				JSONObject childObject = (JSONObject) obj;
				if (childObject == null)
					continue;
				setRequestStruct(childObject, element, level);
			}
		}

	}

	/**
	 * 设置输入参数的值绑定
	 * 
	 * @param jarry
	 *            值绑定设置json
	 * @param soapElment
	 * @param level
	 *            soapElement级别
	 * @param rootName
	 *            根节点名称
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private static void setBindingValue(JSONArray inputs,
			SOAPElement soapElment, int level, String rootName,
			Map<String, Object> variables, Map<JSONObject, SOAPElement> map)
			throws Exception {
		String nodeName = soapElment.getNodeName();
		JSONObject bindingJobject = getBindingJObject(inputs, level, rootName,
				nodeName);
		Iterator<SOAPElement> it = soapElment.getChildElements();
		level++;
		if (bindingJobject == null) {
			if (!it.hasNext()) {
				soapElment.detachNode();
			} else {
				while (it.hasNext()) {
					SOAPElement child = it.next();
					setBindingValue(inputs, child, level, rootName + "."
							+ child.getNodeName(), variables, map);
				}
			}
		} else {
			map.put(bindingJobject, soapElment);
		}
	}

	/**
	 * 获取绑定设置对象
	 * 
	 * @param inputs
	 * @param level
	 * @param rootName
	 * @param nodeName
	 * @return
	 */
	private static JSONObject getBindingJObject(JSONArray inputs, int level,
			String rootName, String nodeName) {
		JSONObject reJobject = null;
		for (Object obj : inputs) {
			JSONObject jobject = (JSONObject) obj;
			if (jobject == null)
				continue;
			String paramName = jobject.getString("name");
			if (paramName.equals(nodeName)) {
				String fullpath = "";
				if (jobject.containsKey("fullpath")) {
					fullpath = jobject.getString("fullpath");
				}
				if (StringUtils.isNotEmpty(fullpath)) {
					// 全路径匹配
					if (fullpath.equals(rootName)) {
						reJobject = jobject;
					}
				} else {
					List<String> pathAry = Arrays.asList(jobject.getString(
							"bindingVal").split("\\."));
					// 未获取到 绑定设置中的全路径时，通过 层级是否相等来判断
					if (level == pathAry.size()) {
						reJobject = jobject;
					}
				}
			}
		}
		return reJobject;
	}
}
