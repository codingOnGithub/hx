package com.hotent.bpmx.persistence.model;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.base.core.model.AbstractModel;

/**
 * 对象功能:bpm_msg_rule entity对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zyp
 * 创建时间:2013-12-19 10:39:52
 */
public class BpmMsgRule extends AbstractModel<String> implements Cloneable{
	protected String  id; /*消息节点配置ID*/
	protected String  configId; /*节点配置ID*/
	protected String  procDefId; /*流程定义ID*/
	protected String  bizType; /*消息业务类型。def=流程；node=节点（公共）；msg=消息节点*/
	protected String  msgType; /*消息类型。mail 邮件；sms 短信； inner 站内消息。*/
	protected String  subjectRule; /*消息主题规则。对于短信类型不需要标题。*/
	protected String  template; /*消息模板。对于短信类型注意在界面限制消息长度。*/
	public void setId(String id) 
	{
		this.id = id;
	}
	/**
	 * 返回 消息节点配置ID
	 * @return
	 */
	public String getId() 
	{
		return this.id;
	}
	public void setConfigId(String configId) 
	{
		this.configId = configId;
	}
	/**
	 * 返回 节点配置ID
	 * @return
	 */
	public String getConfigId() 
	{
		return this.configId;
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
	public void setBizType(String bizType) 
	{
		this.bizType = bizType;
	}
	/**
	 * 返回 消息业务类型。def=流程；node=节点（公共）；msg=消息节点
	 * @return
	 */
	public String getBizType() 
	{
		return this.bizType;
	}
	public void setMsgType(String msgType) 
	{
		this.msgType = msgType;
	}
	/**
	 * 返回 消息类型。mail 邮件；sms 短信； inner 站内消息。
	 * @return
	 */
	public String getMsgType() 
	{
		return this.msgType;
	}
	public void setSubjectRule(String subjectRule) 
	{
		this.subjectRule = subjectRule;
	}
	/**
	 * 返回 消息主题规则。对于短信类型不需要标题。
	 * @return
	 */
	public String getSubjectRule() 
	{
		return this.subjectRule;
	}
	public void setTemplate(String template) 
	{
		this.template = template;
	}
	/**
	 * 返回 消息模板。对于短信类型注意在界面限制消息长度。
	 * @return
	 */
	public String getTemplate() 
	{
		return this.template;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("configId", this.configId) 
		.append("procDefId", this.procDefId) 
		.append("bizType", this.bizType) 
		.append("msgType", this.msgType) 
		.append("subjectRule", this.subjectRule) 
		.append("template", this.template) 
		.toString();
	}
	
	public Object clone(){
		BpmMsgRule bpmMsgRule = null;
		try{
			bpmMsgRule = (BpmMsgRule)super.clone();
		}catch(Exception e){
			
		}
		return bpmMsgRule;
	}
}