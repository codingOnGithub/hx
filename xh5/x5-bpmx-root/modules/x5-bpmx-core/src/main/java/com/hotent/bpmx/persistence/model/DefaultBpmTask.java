package com.hotent.bpmx.persistence.model;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.base.core.model.AbstractModel;
import com.hotent.bpmx.api.model.process.task.BpmTask;

/**
 * 对象功能:流程任务 entity对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zyp
 * 创建时间:2014-02-12 18:04:14
 */
public class DefaultBpmTask extends AbstractModel<String> implements BpmTask,Cloneable{
	protected String  id; /*任务ID*/
	protected String  name; /*任务名称*/
	protected String  subject; /*待办事项标题*/
	protected String  taskId; /*关联的任务ID*/
	protected String  execId; /*关联 - 节点执行ID*/
	protected String  nodeId; /*关联 - 任务节点ID*/
	protected String  procInstId; /*关联 - 流程实例ID*/
	protected String  procDefId; /*关联 - 流程定义ID*/
	protected String  procDefKey; /*关联 - 流程业务主键*/
	protected String  procDefName; /*关联 - 流程名称*/
	protected String  ownerId; /*任务所属人ID*/
	protected String  assigneeId; /*任务执行人ID*/
	protected String  status; /*任务状态*/
	protected Long  priority;			/*任务优先级*/
	protected java.util.Date  createTime; /*任务创建时间*/
	protected java.util.Date  dueTime; /*任务到期时间*/
	protected Short  suspendState; /*是否挂起(0正常,1挂起)*/
	protected String parentId="";
	
	public void setId(String id) 
	{
		this.id = id;
	}
	/**
	 * 返回 任务ID
	 * @return
	 */
	public String getId() 
	{
		return this.id;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	/**
	 * 返回 任务名称
	 * @return
	 */
	public String getName() 
	{
		return this.name;
	}
	public void setSubject(String subject) 
	{
		this.subject = subject;
	}
	/**
	 * 返回 待办事项标题
	 * @return
	 */
	public String getSubject() 
	{
		return this.subject;
	}
	public void setTaskId(String taskId) 
	{
		this.taskId = taskId;
	}
	/**
	 * 返回 关联的任务ID
	 * @return
	 */
	public String getTaskId() 
	{
		return this.taskId;
	}
	public void setExecId(String execId) 
	{
		this.execId = execId;
	}
	/**
	 * 返回 关联 - 节点执行ID
	 * @return
	 */
	public String getExecId() 
	{
		return this.execId;
	}
	public void setNodeId(String nodeId) 
	{
		this.nodeId = nodeId;
	}
	/**
	 * 返回 关联 - 任务节点ID
	 * @return
	 */
	public String getNodeId() 
	{
		return this.nodeId;
	}
	public void setProcInstId(String procInstId) 
	{
		this.procInstId = procInstId;
	}
	/**
	 * 返回 关联 - 流程实例ID
	 * @return
	 */
	public String getProcInstId() 
	{
		return this.procInstId;
	}
	public void setProcDefId(String procDefId) 
	{
		this.procDefId = procDefId;
	}
	/**
	 * 返回 关联 - 流程定义ID
	 * @return
	 */
	public String getProcDefId() 
	{
		return this.procDefId;
	}
	public void setProcDefKey(String procDefKey) 
	{
		this.procDefKey = procDefKey;
	}
	/**
	 * 返回 关联 - 流程业务主键
	 * @return
	 */
	public String getProcDefKey() 
	{
		return this.procDefKey;
	}
	public void setProcDefName(String procDefName) 
	{
		this.procDefName = procDefName;
	}
	/**
	 * 返回 关联 - 流程名称
	 * @return
	 */
	public String getProcDefName() 
	{
		return this.procDefName;
	}
	public void setOwnerId(String ownerId) 
	{
		this.ownerId = ownerId;
	}
	/**
	 * 返回 任务所属人ID
	 * @return
	 */
	public String getOwnerId() 
	{
		return this.ownerId;
	}
	public void setAssigneeId(String assigneeId) 
	{
		this.assigneeId = assigneeId;
	}
	/**
	 * 返回 任务执行人ID
	 * @return
	 */
	public String getAssigneeId() 
	{
		return this.assigneeId;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}
	/**
	 * 返回 任务状态
	 * @return
	 */
	public String getStatus() 
	{
		return this.status;
	}
	public void setPriority(Long priority) 
	{
		this.priority = priority;
	}
	/**
	 * 返回 任务优先级
	 * @return
	 */
	public Long getPriority() 
	{
		return this.priority;
	}
	public void setCreateTime(java.util.Date createTime) 
	{
		this.createTime = createTime;
	}
	/**
	 * 返回 任务创建时间
	 * @return
	 */
	public java.util.Date getCreateTime() 
	{
		return this.createTime;
	}
	public void setDueTime(java.util.Date dueTime) 
	{
		this.dueTime = dueTime;
	}
	/**
	 * 返回 任务到期时间
	 * @return
	 */
	public java.util.Date getDueTime() 
	{
		return this.dueTime;
	}
	public void setSuspendState(Short suspendState) 
	{
		this.suspendState = suspendState;
	}
	/**
	 * 返回 是否挂起(0正常,1挂起)
	 * @return
	 */
	@Override
	public Short getSuspendState() 
	{
		return this.suspendState;
	}
	
	/**
	 * 返回父ID
	 */
	@Override
	public String getParentId() {
		return this.parentId;
	}
	
	public void setParentId(String parentId){
		this.parentId=parentId;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("name", this.name) 
		.append("subject", this.subject) 
		.append("taskId", this.taskId) 
		.append("execId", this.execId) 
		.append("nodeId", this.nodeId) 
		.append("procInstId", this.procInstId) 
		.append("procDefId", this.procDefId) 
		.append("procDefKey", this.procDefKey) 
		.append("procDefName", this.procDefName) 
		.append("ownerId", this.ownerId) 
		.append("assigneeId", this.assigneeId) 
		.append("status", this.status) 
		.append("priority", this.priority) 
		.append("createTime", this.createTime) 
		.append("dueTime", this.dueTime) 
		.append("suspendState", this.suspendState) 
		.toString();
	}
	
	
	
}