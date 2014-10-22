package com.hotent.bpmx.plugin.process.factory;

import java.util.List;

import com.hotent.bpmx.api.constant.EventType;
import com.hotent.bpmx.api.plugin.process.def.BpmExecutionPluginDef;
import com.hotent.bpmx.api.plugin.process.def.BpmTaskPluginDef;
import com.hotent.bpmx.api.plugin.process.factory.BpmPluginFactory;
import com.hotent.bpmx.api.plugin.process.runtime.BpmExecutionPlugin;
import com.hotent.bpmx.api.plugin.process.runtime.BpmTaskPlugin;


public class DefaultBpmPluginFactory implements BpmPluginFactory{

	/* (non-Javadoc)
	 * @see com.hotent.bpmx.api.plugin.process.factory.BpmPluginFactory#getExecutionPlugins(java.util.List, com.hotent.bpmx.api.constant.EventType)
	 */
	@Override
	public List<BpmExecutionPlugin> buildExecutionPlugins(
			List<BpmExecutionPluginDef> pluginDefs, EventType eventType) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hotent.bpmx.api.plugin.process.factory.BpmPluginFactory#getTaskPlugins(java.util.List, com.hotent.bpmx.api.constant.EventType)
	 */
	@Override
	public List<BpmTaskPlugin> buildTaskPlugins(
			List<BpmTaskPluginDef> pluginDefs, EventType eventType) {
		// TODO Auto-generated method stub
		return null;
	}



	
}
