package com.hotent.bpmx.api.plugin.process.cmd;

import com.hotent.bpmx.api.constant.EventType;
import com.hotent.bpmx.api.model.delegate.BpmDelegateExecution;

public interface ExecutionCommand {
	public void execute(EventType eventType, BpmDelegateExecution execution);
}
