package com.hotent.bpmx.activiti.ext.listener;

import org.activiti.engine.delegate.DelegateExecution;

import com.hotent.bpmx.api.constant.EventType;

public class EndEventListener extends AbstractExecutionListener {

	@Override
	public EventType getEventType() {
		return EventType.END_EVENT;
	}

}
