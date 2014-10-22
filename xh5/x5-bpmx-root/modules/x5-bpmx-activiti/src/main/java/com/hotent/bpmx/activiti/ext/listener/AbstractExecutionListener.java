package com.hotent.bpmx.activiti.ext.listener;

import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

import com.hotent.bpmx.activiti.ext.factory.BpmDelegateExecutionFactory;
import com.hotent.bpmx.api.constant.EventType;
import com.hotent.bpmx.api.plugin.process.cmd.ExecutionCommand;
/**
 * <pre> 
 * 描述：抽象事件监听处理器
 * 构建组：x5-bpmx-activiti
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-2-14-上午10:37:10
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public abstract class AbstractExecutionListener implements ExecutionListener{
	
	private static final long serialVersionUID = 5610767582073352010L;

	private List<ExecutionCommand> executionCommands;
	
	public abstract EventType getEventType();
	
	@Override
	public void notify(DelegateExecution delegateExecution) throws Exception {
		
		if(executionCommands!=null){
			for(ExecutionCommand cmd:executionCommands){
				cmd.execute(getEventType(), BpmDelegateExecutionFactory.getBpmDelegateExecution(delegateExecution));
			}
		}
		
	}

	public List<ExecutionCommand> getExecutionCommands() {
		return executionCommands;
	}

	public void setExecutionCommands(List<ExecutionCommand> executionCommands) {
		this.executionCommands = executionCommands;
	}
	
}
