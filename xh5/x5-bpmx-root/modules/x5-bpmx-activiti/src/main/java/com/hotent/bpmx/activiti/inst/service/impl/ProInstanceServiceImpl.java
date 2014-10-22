package com.hotent.bpmx.activiti.inst.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;

import com.hotent.bpmx.natapi.inst.NatProInstanceService;

@Service
public class ProInstanceServiceImpl implements NatProInstanceService{
	@Resource
	RuntimeService runtimeService;
	@Override
	public String startProcessInstance(String processDefinitionId, String businessKey, Map<String, Object> variables) {
		ProcessInstance instance = runtimeService.startProcessInstanceById(processDefinitionId, variables);
		return instance.getId();
	}
}
