package com.hotent.bpmx.activiti.ext.listener;

import com.hotent.bpmx.api.constant.EventType;


/**
 * 任务创建的监听器。
 * <pre>
 * 主要处理人员的分配。
 * </pre>
 * @author ray
 *
 */
public class TaskCreateListener extends AbstractTaskListener {

	@Override
	public EventType getEventType() {
		return EventType.TASK_CREATE_EVENT;
	}

}
