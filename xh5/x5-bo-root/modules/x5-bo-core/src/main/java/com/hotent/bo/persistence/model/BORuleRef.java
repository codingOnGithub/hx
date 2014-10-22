package com.hotent.bo.persistence.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import com.hotent.base.core.model.AbstractModel;

/**
 * 对象功能:xbo_rule_ref entity对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:何一帆
 * 创建时间:2014-02-08 15:24:21
 */
public class BORuleRef extends AbstractModel<String>{
	protected String  id; /*主键ID*/
	protected String  ruleId; /*规则ID*/
	protected String  attrId; /*属性定义ID*/
	public void setId(String id) 
	{
		this.id = id;
	}
	/**
	 * 返回 主键ID
	 * @return
	 */
	public String getId() 
	{
		return this.id;
	}
	public void setRuleId(String ruleId) 
	{
		this.ruleId = ruleId;
	}
	/**
	 * 返回 规则ID
	 * @return
	 */
	public String getRuleId() 
	{
		return this.ruleId;
	}
	public void setAttrId(String attrId) 
	{
		this.attrId = attrId;
	}
	/**
	 * 返回 属性定义ID
	 * @return
	 */
	public String getAttrId() 
	{
		return this.attrId;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("ruleId", this.ruleId) 
		.append("attrId", this.attrId) 
		.toString();
	}
}