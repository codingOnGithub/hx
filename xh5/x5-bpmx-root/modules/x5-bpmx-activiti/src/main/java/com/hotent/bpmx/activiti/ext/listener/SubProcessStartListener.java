package com.hotent.bpmx.activiti.ext.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

import com.hotent.bpmx.api.constant.EventType;

/**
 * 内嵌子流程进入的监听器。
 * @author Administrator
 *
 */
public class SubProcessStartListener extends AbstractExecutionListener {

	@Override
	public EventType getEventType() {
		// TODO Auto-generated method stub
		return null;
	}


}
