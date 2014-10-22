package com.hotent.bpmx.api.model.process.task;
import java.util.Date;



/**
 * 对象功能:流程任务 entity对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zyp
 * 创建时间:2014-02-12 18:04:14
 */
public interface BpmTask  {

	
	/**
	 * 返回 任务ID
	 * @return
	 */
	public String getId() ;
	
	/**
	 * 返回 任务名称
	 * @return
	 */
	public String getName() ;
	
	/**
	 * 返回 待办事项标题
	 * @return
	 */
	public String getSubject() ;
	
	/**
	 * 返回 关联的任务ID
	 * @return
	 */
	public String getTaskId() ;
	
	/**
	 * 返回 关联 - 节点执行ID
	 * @return
	 */
	public String getExecId() ;
	
	/**
	 * 返回 关联 - 任务节点ID
	 * @return
	 */
	public String getNodeId() ;
	
	/**
	 * 返回 关联 - 流程实例ID
	 * @return
	 */
	public String getProcInstId() ;
	
	/**
	 * 返回 关联 - 流程定义ID
	 * @return
	 */
	public String getProcDefId() ;
	
	/**
	 * 返回 关联 - 流程业务主键
	 * @return
	 */
	public String getProcDefKey() ;
	
	/**
	 * 返回 关联 - 流程名称
	 * @return
	 */
	public String getProcDefName() ;
	
	/**
	 * 返回 任务所属人ID
	 * @return
	 */
	public String getOwnerId() ;
	
	/**
	 * 返回 任务执行人ID
	 * @return
	 */
	public String getAssigneeId() ;
	
	/**
	 * 返回 任务状态
	 * @return
	 */
	public String getStatus() ;
	
	/**
	 * 返回 任务优先级
	 * @return
	 */
	public Long getPriority() ;
	
	/**
	 * 返回 任务创建时间
	 * @return
	 */
	public java.util.Date getCreateTime() ;
	
	/**
	 * 返回 任务到期时间
	 * @return
	 */
	public java.util.Date getDueTime() ;
	
	/**
	 * 返回 是否挂起(0正常,1挂起)
	 * @return
	 */
	public Short getSuspendState() ;
	
	/**
	 * 
	 * 增加任务的父ID
	 * @return  String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getParentId();
	
}