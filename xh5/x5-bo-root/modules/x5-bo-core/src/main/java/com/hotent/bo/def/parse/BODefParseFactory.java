package com.hotent.bo.def.parse;

import com.hotent.bo.persistence.model.BODef;

public interface BODefParseFactory {
	
	/**
	 * 解析xml为BODef
	 * <pre>
	 * 示例：
	 * String xml="XML的字串内容";
	 * BODef bodef = bODefParseFactory.parse(xml);   //BODefParseFactory类
	 * 返回:
	 * BODef对象
	 * </pre>
	 * @param xml
	 * @return BODef
	 * BODef
	 * @exception 
	 * @since  1.0.0
	 */
	BODef parse(String xml);
	
	/**
	 * 将BODef反解析为BODefXML
	 * <pre>
	 * 示例：
	 *  BODef bODef = boDefManager.loadBODef("业务对象ID");
	 *	String xml = bODefParseFactory.getBODefXML(bODef);
	 * 返回:
	 * BODef对象
	 * </pre>
	 * @param boDef
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	String getBODefXML(BODef boDef);
}
