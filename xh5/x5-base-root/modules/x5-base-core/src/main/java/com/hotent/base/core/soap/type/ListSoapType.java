/**
 * 
 */
package com.hotent.base.core.soap.type;

import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPElement;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * 列表类型转换器
 * 
 * @author wwz
 */
public class ListSoapType extends BaseSoapType {
	private static Log logger = LogFactory.getLog(BaseSoapType.class);

	@Override
	public Class<?>[] getBeanTypes() {
		return new Class[] { List.class };
	}

	@Override
	public String[] getSoapTypes() {
		return new String[] { "List" };
	}

	private Class<?> currentClass;

	@SuppressWarnings("rawtypes")
	@Override
	void setCurrentValue(SOAPElement element, Object obj, Class<?> klass) {
		String tagName = element.getTagName();
		try {
			if (obj == null)
				return;
			List list = (List) obj;

			String elementName = element.getLocalName();
			SOAPElement parentElement = element.getParentElement();
			NodeList fieldNodeList = parentElement
					.getElementsByTagName(elementName);
			if (fieldNodeList == null)
				return;
			int nodeCount = fieldNodeList.getLength();
			if (nodeCount == list.size()) {
				for (int i = 0; i < nodeCount; i++) {
					Object item = list.get(i);
					currentClass = item.getClass();
					SOAPElement itemElement = (SOAPElement) fieldNodeList
							.item(i);
					SoapTypes.getTypeByBean(currentClass).setValue(itemElement,
							item, currentClass);
				}
			} else {
				Node tempElement = element.cloneNode(true);
				element.detachNode();
				for (Object item : list) {
					currentClass = item.getClass();
					SOAPElement itemElement = (SOAPElement) tempElement
							.cloneNode(true);
					parentElement.addChildElement(itemElement);
					SoapTypes.getTypeByBean(currentClass).setValue(itemElement,
							item, currentClass);
				}
			}
		} catch (Exception ex) {
			// 设置失败,跳过.
			logger.warn("字段[" + tagName + "]设置失败.", ex);
		}
	}

	@Override
	Object convertCurrent(Class<?> klass, SOAPElement element) {
		String tagName = element.getTagName();
		try {
			SOAPElement parentElement = element.getParentElement();
			NodeList nodeList = parentElement.getElementsByTagName(tagName);
			int size = nodeList.getLength();
			List<Object> list = new ArrayList<Object>();
			for (int i = 0; i < size; i++) {
				SOAPElement node = (SOAPElement) nodeList.item(i);
				String text = node.getTextContent();
				if (StringUtils.isEmpty(text)) {
					SoapType convert = SoapTypes.getTypeByBean(null);
					Class<?> c = Object.class;
					try {
						c = Class.forName(node.getTagName());
					} catch (Exception e) {
					}
					Object obj = convert.convertToBean(c, element);
					list.add(obj);
				} else {
					list.add(text);
				}
			}
			return list;
		} catch (Exception ex) {
			// 设置失败,跳过.
			logger.warn("字段[" + tagName + "]设置失败.", ex);
			return null;
		}
	}
}
