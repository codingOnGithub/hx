package com.hotent.bpmx.api.plugin.process.parse;

import com.hotent.bpmx.api.plugin.process.def.BpmPluginDef;

public interface BpmPluginParser {
	/**
	 * 根据XML及XSD解析实现类实例
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public BpmPluginDef parse(String xml);
}
