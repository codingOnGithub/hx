package com.hotent.bpmx.plugin.process.cmd;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.bpmx.api.constant.EventType;
import com.hotent.bpmx.api.context.BpmDefContextFactory;
import com.hotent.bpmx.api.model.delegate.BpmDelegateTask;
import com.hotent.bpmx.api.model.process.nodedef.BpmNodeDef;
import com.hotent.bpmx.api.plugin.process.cmd.TaskCommand;
import com.hotent.bpmx.api.plugin.process.factory.BpmPluginFactory;
import com.hotent.bpmx.api.plugin.process.factory.BpmPluginSessionFactory;
import com.hotent.bpmx.api.plugin.process.runtime.BpmTaskPlugin;
import com.hotent.bpmx.api.plugin.process.session.BpmTaskPluginSession;


@Service
public class PluginTaskCommand implements TaskCommand{

	@Resource
	BpmDefContextFactory bpmDefContext;
	
	@Resource
	BpmPluginFactory bpmPluginFactory;
	
	@Resource
	BpmPluginSessionFactory bpmPluginSessionFactory;

	@Override
	public void execute(EventType eventType, BpmDelegateTask bpmDelegateTask) {
		//改造执行插件的会话数据
		BpmTaskPluginSession bpmTaskPluginSession = bpmPluginSessionFactory.buildBpmTaskPluginSession(bpmDelegateTask);
		
		//执行节点的任务类插件
		BpmNodeDef bpmNodeDef= bpmDefContext.getBpmNodeDef(bpmDelegateTask.getProcessDefinitionId(), bpmDelegateTask.getTaskDefinitionKey());					
		List<BpmTaskPlugin> bpmTaskPlugins = bpmPluginFactory.buildTaskPlugins(bpmNodeDef.getBpmTaskPluginDefs(),eventType);
		for(BpmTaskPlugin bpmTaskPlugin:bpmTaskPlugins){
			bpmTaskPlugin.execute(bpmTaskPluginSession);
		}
	}
	
	
	
}
