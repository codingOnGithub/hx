package com.hotent.bpmx.activiti.ext.listener;

import org.activiti.engine.delegate.DelegateTask;

import com.hotent.bpmx.api.constant.EventType;

public class TaskSignCreateListener extends AbstractTaskListener {

	@Override
	public EventType getEventType() {
		return EventType.TASK_SIGN_CREATE_EVENT;
	}


}
