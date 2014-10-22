package com.hotent.bpmx.activiti.ext.factory;

import org.activiti.engine.delegate.DelegateTask;

import com.hotent.bpmx.activiti.ext.model.BpmDelegateTaskImpl;
import com.hotent.bpmx.api.model.delegate.BpmDelegateTask;

public class BpmDelegateTaskFactory {
	public static BpmDelegateTask getBpmDelegateTask(DelegateTask delegateTask) {
		BpmDelegateTaskImpl bpmDelegateTaskImpl = new BpmDelegateTaskImpl();
		bpmDelegateTaskImpl.setDelegateTask(delegateTask);
		return (BpmDelegateTask)bpmDelegateTaskImpl;
	}
}
