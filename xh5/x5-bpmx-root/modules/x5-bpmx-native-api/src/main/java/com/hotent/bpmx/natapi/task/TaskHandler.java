package com.hotent.bpmx.natapi.task;

import com.hotent.bpmx.api.model.delegate.BpmDelegateTask;

public interface TaskHandler {
	
	/**
	 * 创建任务接口。
	 * 给bpmx-core项目来实现此接口。
	 * @param task 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	void create(BpmDelegateTask task);
	
	
	/**
	 * 保存任务。
	 * 预留给bpmx-core项目来实现此接口。
	 * @param bpmTask 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	void save(BpmDelegateTask bpmTask);
	
	
	
	
}