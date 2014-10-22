package com.hotent.bpmx.persistence.manager;

import com.hotent.base.manager.api.Manager;
import com.hotent.bpmx.api.cmd.ProcessInstCmd;
import com.hotent.bpmx.api.model.process.def.BpmDefinition;
import com.hotent.bpmx.persistence.model.DefaultBpmProcessInstance;


public interface BpmProcessInstanceManager extends Manager<String, DefaultBpmProcessInstance>{
	/**
	 * 构建流程定义标题
	 * @param bpmDefinition
	 * @param processInstCmd
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getSubject(BpmDefinition bpmDefinition, ProcessInstCmd processInstCmd);
	
}
