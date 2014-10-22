package com.hotent.bpmx.activiti.ext.listener;

import java.util.List;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import com.hotent.bpmx.activiti.ext.factory.BpmDelegateTaskFactory;
import com.hotent.bpmx.api.constant.EventType;
import com.hotent.bpmx.api.plugin.process.cmd.TaskCommand;

/**
 * <pre> 
 * 描述：任务监听器
 * 构建组：x5-bpmx-activiti
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-2-14-上午10:42:09
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public abstract class AbstractTaskListener implements TaskListener{
	
	private static final long serialVersionUID = -296298349312307694L;
	
	private List<TaskCommand> taskCommands;

	public abstract EventType getEventType();
	
	@Override
	public void notify(DelegateTask delegateTask) {
		for(TaskCommand cmd:taskCommands){
			cmd.execute(getEventType(), BpmDelegateTaskFactory.getBpmDelegateTask(delegateTask));
		}		
	}

	public List<TaskCommand> getTaskCommands() {
		return taskCommands;
	}

	public void setTaskCommands(List<TaskCommand> taskCommands) {
		this.taskCommands = taskCommands;
	}
	
}
