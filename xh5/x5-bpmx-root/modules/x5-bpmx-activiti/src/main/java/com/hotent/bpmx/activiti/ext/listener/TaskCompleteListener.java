package com.hotent.bpmx.activiti.ext.listener;

import com.hotent.bpmx.api.constant.EventType;


/**
 * 任务完成监听器。
 * @author ray
 *
 */
public class TaskCompleteListener extends AbstractTaskListener {

	@Override
	public EventType getEventType() {
		return EventType.TASK_COMPLETE_EVENT;
	}

}
