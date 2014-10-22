package com.hotent.bpmx.persistence.model;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.base.core.model.AbstractModel;
import com.hotent.bpmx.api.model.process.task.BpmTaskCandidate;

/**
 * 对象功能:任务候选人 entity对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zyp
 * 创建时间:2014-02-12 18:04:14
 */
public class DefaultBpmTaskCandidate extends AbstractModel<String> implements BpmTaskCandidate,Cloneable{
	protected String  id; /*主键*/
	protected String  taskId; /*任务ID*/
	protected String  type; /*候选人类型*/
	protected String  executor; /*执行人ID*/
	protected String  actInstId; /*流程实例ID*/
	public void setId(String id) 
	{
		this.id = id;
	}
	/**
	 * 返回 主键
	 * @return
	 */
	public String getId() 
	{
		return this.id;
	}
	public void setTaskId(String taskId) 
	{
		this.taskId = taskId;
	}
	/**
	 * 返回 任务ID
	 * @return
	 */
	public String getTaskId() 
	{
		return this.taskId;
	}
	public void setType(String type) 
	{
		this.type = type;
	}
	/**
	 * 返回 候选人类型
	 * @return
	 */
	public String getType() 
	{
		return this.type;
	}
	public void setExecutor(String executor) 
	{
		this.executor = executor;
	}
	/**
	 * 返回 执行人ID
	 * @return
	 */
	public String getExecutor() 
	{
		return this.executor;
	}
	public void setActInstId(String actInstId) 
	{
		this.actInstId = actInstId;
	}
	/**
	 * 返回 流程实例ID
	 * @return
	 */
	public String getActInstId() 
	{
		return this.actInstId;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("taskId", this.taskId) 
		.append("type", this.type) 
		.append("executor", this.executor) 
		.append("actInstId", this.actInstId) 
		.toString();
	}
}