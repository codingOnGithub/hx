package com.hotent.bpmx.plugin.process.cmd;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.bpmx.api.constant.EventType;
import com.hotent.bpmx.api.context.BpmDefContextFactory;
import com.hotent.bpmx.api.model.delegate.BpmDelegateExecution;
import com.hotent.bpmx.api.model.process.def.BpmProcessDef;
import com.hotent.bpmx.api.model.process.nodedef.BpmNodeDef;
import com.hotent.bpmx.api.plugin.process.cmd.ExecutionCommand;
import com.hotent.bpmx.api.plugin.process.factory.BpmPluginFactory;
import com.hotent.bpmx.api.plugin.process.factory.BpmPluginSessionFactory;
import com.hotent.bpmx.api.plugin.process.runtime.BpmExecutionPlugin;
import com.hotent.bpmx.api.plugin.process.session.BpmExecutionPluginSession;

@Service
public class PluginExecutionCommand implements ExecutionCommand{

	@Resource
	BpmDefContextFactory bpmDefContext;
	@Resource
	BpmPluginFactory bpmPluginFactory;
	@Resource
	BpmPluginSessionFactory bpmPluginSessionFactory;
	
	@Override
	public void execute(EventType eventType, BpmDelegateExecution execution) {
		//改造执行插件的会话数据
		BpmExecutionPluginSession bpmExecutionPluginSession = bpmPluginSessionFactory.buildBpmExecutionPluginSession(execution);
		
		//处理流程的执行类插件
		BpmProcessDef bpmProcessDef = bpmDefContext.getBpmProcessDef(execution.getProcessDefinitionId());
		List<BpmExecutionPlugin> executionPlugins = bpmPluginFactory.buildExecutionPlugins(bpmProcessDef.getBpmExecutionPluginDefs(), eventType);
		for(BpmExecutionPlugin plugin:executionPlugins){
			plugin.execute(bpmExecutionPluginSession);
		}
		
		//处理流程节点的执行类插件
		BpmNodeDef bpmNodeDef=bpmDefContext.getBpmNodeDef(execution.getProcessDefinitionId(), execution.getCurrentActivityId());		
		executionPlugins= bpmPluginFactory.buildExecutionPlugins(bpmNodeDef.getBpmExecutionPluginDefs(), eventType);		 
		for(BpmExecutionPlugin plugin:executionPlugins){
			plugin.execute(bpmExecutionPluginSession);
		}
	}
	
}
