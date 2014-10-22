package com.hotent.bpmx.activiti.ext.factory;

import org.activiti.engine.delegate.DelegateExecution;

import com.hotent.bpmx.activiti.ext.model.BpmDelegateExecutionImpl;
import com.hotent.bpmx.api.model.delegate.BpmDelegateExecution;

public class BpmDelegateExecutionFactory {
	public static BpmDelegateExecution getBpmDelegateExecution(DelegateExecution delegateExecution) {
		BpmDelegateExecutionImpl bpmDelegateExecutionImpl = new BpmDelegateExecutionImpl();
		bpmDelegateExecutionImpl.setDelegateExecution(delegateExecution);
		return (BpmDelegateExecution)bpmDelegateExecutionImpl;
	}
}
