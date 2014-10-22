package com.hotent.bpmx.api.cmd;

import java.util.List;

import com.hotent.bpmx.api.model.identity.BpmIdentity;

/**
 * 任务分发
 * @author csx
 *
 */
public interface TaskSplitCmd extends AbstractTaskActionCmd{
	/**
	 * 获取任务分发的标识类型
	 * @return 
	 * BpmUserGroup[]
	 * @exception 
	 * @since  1.0.0
	 */
	public List<BpmIdentity> getCopyIdentities();
	
}
