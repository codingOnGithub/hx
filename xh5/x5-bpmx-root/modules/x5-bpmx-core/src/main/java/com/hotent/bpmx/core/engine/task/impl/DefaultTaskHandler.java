package com.hotent.bpmx.core.engine.task.impl;

import com.hotent.bpmx.api.model.delegate.BpmDelegateTask;
import com.hotent.bpmx.api.model.process.task.BpmTask;
import com.hotent.bpmx.core.util.BpmUtil;
import com.hotent.bpmx.natapi.task.TaskHandler;
import com.hotent.bpmx.persistence.manager.BpmTaskManager;
import com.hotent.bpmx.persistence.model.DefaultBpmTask;

/**
 * 任务处理Handler。
 * <pre> 
 * 描述：任务处理Handler。
 * 构建组：x5-bpmx-core
 * 作者：ray
 * 邮箱:zhangyg@jee-soft.cn
 * 日期:2014-2-13-下午6:03:02
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class DefaultTaskHandler implements TaskHandler {
	
	private BpmTaskManager bpmTaskManager;
	
	public void setBpmTaskManager(BpmTaskManager bpmTaskManager){
		this.bpmTaskManager=bpmTaskManager;
	}

	@Override
	public void create(BpmDelegateTask delegateTask) {
		BpmTask task=BpmUtil.convertTask(delegateTask);
		
		this.bpmTaskManager.create((DefaultBpmTask) task);
	}

	@Override
	public void save(BpmDelegateTask task) {
		

	}

	
}
