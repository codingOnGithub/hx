package com.hotent.bpmx.persistence.model;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.base.core.model.AbstractModel;
import com.hotent.bpmx.api.model.process.inst.BpmProcessInstance;

/**
 * 对象功能:BPM_PROCESS_INST  【流程实例】entity对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2013-12-21 14:55:09
 */
public class DefaultBpmProcessInstance extends AbstractModel<String> implements BpmProcessInstance{
	/*流程实例ID*/
	protected String  id; 
	/*BPMN流程实例ID*/
	protected String bpmnInstId;
	/*流程实例标题*/
	protected String  subject; 
	/*流程定义ID*/
	protected String  procDefId; 
	/*BPMN流程定义ID*/
	protected String  bpmnDefId; 
	/*流程定义Key*/
	protected String  procDefKey; 
	/*流程名称*/
	protected String  procDefName; 
	/*关联数据业务主键*/
	protected String  bizKey;
	/*关联业务实体JSON值*/
	protected String bizData;
	/*绑定的表单主键*/
	protected String  formKey; 
	/*实例状态*/
	protected String  status; 
	/*实例结束时间*/
	protected java.util.Date  endTime; 
	/*持续时间(ms)*/
	protected Long  duration; 
	/*所属分类ID*/
	protected String  typeId; 
	/*流程结束后的最终审批结果，agree=同意；refuse=拒绝*/
	protected String  resultType; 
	
	public void setId(String id) 
	{
		this.id = id;
	}
	/**
	 * 返回 流程实例ID
	 * @return
	 */
	public String getId() 
	{
		return this.id;
	}
	
	public void setSubject(String subject) 
	{
		this.subject = subject;
	}
	/**
	 * 返回 流程实例标题
	 * @return
	 */
	public String getSubject() 
	{
		return this.subject;
	}
	public void setProcDefId(String procDefId) 
	{
		this.procDefId = procDefId;
	}
	/**
	 * 返回 流程定义ID
	 * @return
	 */
	public String getProcDefId() 
	{
		return this.procDefId;
	}
	public void setBpmnDefId(String bpmnDefId) 
	{
		this.bpmnDefId = bpmnDefId;
	}
	/**
	 * 返回 BPMN流程定义ID
	 * @return
	 */
	public String getBpmnDefId() 
	{
		return this.bpmnDefId;
	}
	public void setProcDefKey(String procDefKey) 
	{
		this.procDefKey = procDefKey;
	}
	/**
	 * 返回 流程定义Key
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
	 * 返回 流程名称
	 * @return
	 */
	public String getProcDefName() 
	{
		return this.procDefName;
	}
	public void setBizKey(String bizKey) 
	{
		this.bizKey = bizKey;
	}
	/**
	 * 返回 关联数据业务主键
	 * @return
	 */
	public String getBizKey() 
	{
		return this.bizKey;
	}
	public void setFormKey(String formKey) 
	{
		this.formKey = formKey;
	}
	/**
	 * 返回 绑定的表单主键
	 * @return
	 */
	public String getFormKey() 
	{
		return this.formKey;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}
	/**
	 * 返回 实例状态
	 * @return
	 */
	public String getStatus() 
	{
		return this.status;
	}
	public void setEndTime(java.util.Date endTime) 
	{
		this.endTime = endTime;
	}
	/**
	 * 返回 实例结束时间
	 * @return
	 */
	public java.util.Date getEndTime() 
	{
		return this.endTime;
	}
	public void setDuration(Long duration) 
	{
		this.duration = duration;
	}
	/**
	 * 返回 持续时间(ms)
	 * @return
	 */
	public Long getDuration() 
	{
		return this.duration;
	}
	public void setTypeId(String typeId) 
	{
		this.typeId = typeId;
	}
	/**
	 * 返回 所属分类ID
	 * @return
	 */
	public String getTypeId() 
	{
		return this.typeId;
	}
	public void setResultType(String resultType) 
	{
		this.resultType = resultType;
	}
	/**
	 * 返回 流程结束后的最终审批结果，agree=同意；refuse=拒绝
	 * @return
	 */
	public String getResultType() 
	{
		return this.resultType;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("bpmnInstId",this.bpmnInstId)
		.append("subject", this.subject) 
		.append("procDefId", this.procDefId) 
		.append("bpmnDefId", this.bpmnDefId) 
		.append("procDefKey", this.procDefKey) 
		.append("procDefName", this.procDefName) 
		.append("bizKey", this.bizKey)
		.append("bizData",this.bizData)
		.append("formKey", this.formKey) 
		.append("status", this.status) 
		.append("endTime", this.endTime) 
		.append("duration", this.duration) 
		.append("typeId", this.typeId) 
		.append("resultType", this.resultType) 
		.append("createBy", this.createBy) 
		.append("createTime", this.createTime) 
		.append("createOrgId", this.createOrgId) 
		.append("updateBy", this.updateBy) 
		.append("updateTime", this.updateTime) 
		.toString();
	}
	
	@Override
	public String getProcInstId() {
		return this.id;
	}
	public String getBpmnInstId() {
		return bpmnInstId;
	}
	public void setBpmnInstId(String bpmnInstId) {
		this.bpmnInstId = bpmnInstId;
	}
	public String getBizData() {
		return bizData;
	}
	public void setBizData(String bizData) {
		this.bizData = bizData;
	}
	
	
}