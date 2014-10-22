package com.hotent.bpmx.api.cmd;

import com.hotent.bpmx.api.model.identity.BpmIdentity;

/**
 * 任务抄送数据接口
 * @author csx
 *
 */
public interface TaskCopyCmd extends AbstractTaskActionCmd{
	
	/**
	 * 抄送的用户组类型
	 * @return 
	 * BpmUserGroup[]
	 * @exception 
	 * @since  1.0.0
	 */
	public BpmIdentity[] getCopyIdentities();
	
	
}
