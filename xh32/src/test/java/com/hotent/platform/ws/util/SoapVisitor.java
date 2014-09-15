package com.hotent.platform.ws.util;

import org.dom4j.Element;
import org.dom4j.VisitorSupport;

/**
 * 解析soap格式的xml
 * 
 * @author zxh
 * 
 */
public class SoapVisitor extends VisitorSupport {
	/**
	 * 选项
	 */
	String op = "";
	/**
	 * 返回值
	 */
	String val = null;

	@Override
	public void visit(Element node) {
		if (op.equals(node.getName())) {
			this.val = node.getText();//
		}
	}
}
