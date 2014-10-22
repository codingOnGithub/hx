package com.hotent.bpmx.activiti.ext.listener;

import org.activiti.engine.delegate.DelegateExecution;

import com.hotent.bpmx.api.constant.EventType;

/**
 * 外部子流程结束事件监听器。
 * <pre>
 * 	1.用于修改子流程节点的状态为正在结束。
 *  2.在子流程调用任务结束后，将人员变量删除。
 * </pre>
 * @author ray
 *
 */
public class CallSubProcessEndListener extends AbstractExecutionListener {

	@Override
	public EventType getEventType() {
		// TODO Auto-generated method stub
		return null;
	}

}
