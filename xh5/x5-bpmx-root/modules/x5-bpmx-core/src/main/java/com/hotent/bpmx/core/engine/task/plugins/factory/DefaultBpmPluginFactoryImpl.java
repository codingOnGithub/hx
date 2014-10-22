package com.hotent.bpmx.core.engine.task.plugins.factory;

import java.util.List;

import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;

import com.hotent.bpmx.api.constant.EventType;
import com.hotent.bpmx.api.plugin.process.def.BpmExecutionPluginDef;
import com.hotent.bpmx.api.plugin.process.def.BpmTaskPluginDef;
import com.hotent.bpmx.api.plugin.process.factory.BpmPluginFactory;
import com.hotent.bpmx.api.plugin.process.runtime.BpmExecutionPlugin;
import com.hotent.bpmx.api.plugin.process.runtime.BpmTaskPlugin;

/**
 * @author Frederik Heremans
 */
public class DefaultBpmPluginFactoryImpl implements BpmPluginFactory {


	
	@Override
	public List<BpmExecutionPlugin> buildExecutionPlugins(List<BpmExecutionPluginDef> pluginDefs, EventType eventType) {
		
		return null;
	}

	@Override
	public List<BpmTaskPlugin> buildTaskPlugins(List<BpmTaskPluginDef> pluginDefs, EventType eventType) {
		// TODO Auto-generated method stub
		return null;
	}
}
