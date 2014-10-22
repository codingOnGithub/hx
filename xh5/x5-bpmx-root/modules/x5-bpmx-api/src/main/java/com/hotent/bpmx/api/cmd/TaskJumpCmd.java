package com.hotent.bpmx.api.cmd;

import java.util.List;

import com.hotent.bpmx.api.model.identity.BpmIdentity;

/**
 * 实现任务跳转的命令参数接口
 * @author csx
 *
 */
public interface TaskJumpCmd extends AbstractTaskActionCmd{
	
	/**
	 * 获取目标节点的ID
	 * @return
	 */
	public String getDestNodeId();
	
	/**
	 * 获取跳转的人员或组标识列表
	 * @return 
	 * List<BpmIdentity>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<BpmIdentity> getJumpIdentities();
	
}
