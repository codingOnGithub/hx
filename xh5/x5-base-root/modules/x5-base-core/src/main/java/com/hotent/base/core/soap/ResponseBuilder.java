package com.hotent.base.core.soap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.hotent.base.core.engine.script.GroovyScriptEngine;
import com.hotent.base.core.soap.type.SoapType;
import com.hotent.base.core.soap.type.SoapTypes;
import com.hotent.base.core.util.string.StringEscaper;

/**
 * webservice出参处理器
 * 
 * @author heyifan
 * 
 */
public class ResponseBuilder {

	private static GroovyScriptEngine engine = new GroovyScriptEngine();

	/**
	 * 
	 * @param variables
	 * @param document
	 * @param message
	 * @throws SOAPException
	 * @throws InvokeException
	 */
	public static void build(Map<String, Object> variables, JSONArray jarray,
			SOAPMessage message) throws Exception {
		// 校验是否失败
		SoapUtil.checkFault(message);
		// 获取返回对象
		NodeList nodeList = message.getSOAPBody().getChildNodes();

		if (nodeList == null || nodeList.getLength() < 1) // 无返回,什么都不用处理
			return;

		// 准备好返回数据
		SOAPElement[] elements = new SOAPElement[nodeList.getLength()];
		for (int i = 0; i < elements.length; i++) {
			elements[i] = (SOAPElement) nodeList.item(i);
		}

		for (Object obj : jarray) {
			JSONObject jobject = (JSONObject) obj;
			build(variables, elements, jobject);
		}
	}

	@SuppressWarnings("unchecked")
	private static SOAPElement getElementByPath(SOAPElement[] elements,
			String fullpath) {
		if (StringUtils.isEmpty(fullpath))
			return elements[0];
		String[] names = fullpath.split("\\.");
		int size = names.length;

		SOAPElement root = null;
		Node node = elements[0].getFirstChild();
		if (node != null)
			root = (SOAPElement) node;
		if (root == null)
			return null;

		for (int i = 1; i < size; i++) {
			String name = names[i];
			root = getElement(root.getChildElements(), name);
		}
		return root;
	}

	private static SOAPElement getElement(Iterator<SOAPElement> it, String name) {
		while (it.hasNext()) {
			SOAPElement element = it.next();
			String tagName = element.getTagName();
			if (tagName.equals(name)) {
				return element;
			}
		}
		return null;
	}

	/**
	 * 构建
	 * 
	 * @param variables
	 * @param roots
	 * @param jobject
	 * @throws Exception
	 *             void
	 * @since 1.0.0
	 */
	private static void build(Map<String, Object> variables,
			SOAPElement[] roots, JSONObject jobject) throws Exception {
		if (jobject == null)
			return;
		// 设值
		String binding = jobject.getString("bindingVal");// 绑定参数
		String soapType = jobject.getString("soapType");// soap类型
		String beanType = jobject.getString("javaType");// java类型
		Integer bindingType = jobject.getInt("bindingType");// 绑定类型
		String fullpath = "";
		if (jobject.containsKey("fullpath")) {
			fullpath = jobject.getString("fullpath");
		}
		SOAPElement elements = getElementByPath(roots, fullpath);
		binding = StringEscaper.unescapeJson(binding);

		if (StringUtils.isEmpty(binding))
			return;
		// 返回值对象
		Object obj = null;
		SoapType converter;
		// webservice返回值类型
		if (StringUtils.isNotEmpty(soapType)) {
			try {
				Class<?> kclass;
				if (soapType.matches("List\\{\\w*\\}")) {
					kclass = List.class;
				} else {
					kclass = Class.forName(soapType);
				}
				converter = SoapTypes.getTypeBySoap(soapType);
				obj = converter.convertToBean(kclass, elements);
			} catch (Exception ex) {
				converter = SoapTypes.getTypeBySoap("string");
				obj = converter.convertToBean(elements);
			}
		}
		// 流程变量中
		else if (StringUtils.isNotEmpty(beanType)
				&& bindingType.intValue() == 2) {
			Class<?> klass = Class.forName(beanType);
			converter = SoapTypes.getTypeByBean(klass);
			obj = converter.convertToBean(klass, elements);
		}
		// 2个类型都没有时直接赋值
		else {
			obj = elements.getTextContent();
		}

		switch (bindingType) {
		// webservice返回值回填到流程变量中
		case 2:
			if (obj != null) {
				if (!(obj instanceof List)) {
					if (binding.indexOf("[i]") > -1) {
						List<Object> list = new ArrayList<Object>();
						list.add(obj);
						obj = list;
					}
				}
				PropertyUtils.setProperty(variables, binding, obj);
			}
			break;
		// 执行脚本
		case 3:
			// 将返回值放到map中用作执行脚本
			variables.put("returnObj", obj);
			engine.executeObject(binding, variables);
			// 执行完脚本以后移除返回值
			variables.remove("returnObj");
			break;
		}
	}
}
