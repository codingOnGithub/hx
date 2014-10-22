package com.hotent.bpmx.api.cmd;

import java.util.List;

import com.hotent.bpmx.api.model.process.nodedef.BpmNodeIdentity;

/**
 * 任务处理命令接口
 * @author csx
 *
 */
public interface TaskFinishCmd extends AbstractTaskActionCmd {
	
	/**
	 * 获取审批的状态
	 * @return
	 */
	public String getCheckStatus();
	/**
	 * 获取审批的意见
	 * @return
	 */
	public String getApprovalOpinion();
	
	/**
	 * 
	 * 取得当前节点的后续节点列表的用户标识列表
	 * @return 
	 * Map<String,List<BpmIdentity>>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<BpmNodeIdentity> getNextNodeIdentities();

	
}
