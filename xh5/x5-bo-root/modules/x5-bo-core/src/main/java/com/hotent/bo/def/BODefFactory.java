package com.hotent.bo.def;

import com.hotent.bo.persistence.model.BODef;

public interface BODefFactory {
	/**
	 * 将BODefXML文件解析为BODef
	 * @param xml
	 * @return 
	 * BODef
	 * @exception 
	 * @since  1.0.0
	 */
	BODef parse(String xml);
	/**
	 * 将BODef反解析为BODefXML
	 * @param boDef
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	String getBODefXML(BODef boDef);
}