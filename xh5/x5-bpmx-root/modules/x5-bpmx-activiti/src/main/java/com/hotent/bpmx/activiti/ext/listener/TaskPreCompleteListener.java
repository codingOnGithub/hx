package com.hotent.bpmx.activiti.ext.listener;

import com.hotent.bpmx.api.constant.EventType;

public class TaskPreCompleteListener extends AbstractTaskListener {

	@Override
	public EventType getEventType() {
		return EventType.TASK_PRE_COMPLETE;
	}

}
