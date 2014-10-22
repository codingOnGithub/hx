package com.hotent.bpmx.activiti.ext.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

import com.hotent.bpmx.api.constant.EventType;

/**
 *子流程退出时执行的监听。 
 * @author ray
 *
 */
public class SubProcessEndListener extends AbstractExecutionListener {

	@Override
	public EventType getEventType() {
		// TODO Auto-generated method stub
		return null;
	}


}
