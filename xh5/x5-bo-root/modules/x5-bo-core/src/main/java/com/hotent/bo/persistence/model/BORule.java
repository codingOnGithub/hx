package com.hotent.bo.persistence.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import com.hotent.base.core.model.AbstractModel;

/**
 * 对象功能:xbo_rule entity对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:何一帆
 * 创建时间:2014-01-21 11:00:05
 */
public class BORule extends AbstractModel<String>{
	
	public final static class BORULE_BELONG_TYPE{
		/**
		 * 对象定义类型
		 */
		public final static String DEF = "def";
		/**
		 * 只属于某个属性类型
		 */
		public final static String ATTR = "attr";


	}
	
	public final static class BORULE_TYPE{
		/**
		 * 正则表达式
		 */
		public final static String FORMAT = "format";
		/**
		 * 排除
		 */
		public final static String EXCLUSION = "exclusion";
		/**
		 * 包含
		 */
		public final static String INCLUSION = "inclusion";
		/**
		 * 依赖
		 */
		public final static String REFERENCE = "reference";
	}
	
	protected String  id; /*规则ID*/
	protected String  defId; /*对象定义ID*/
	protected String  attrId; /*属性定义ID*/
	protected String  belongType; /*从属类型。def=对象定义；attr=只属于某个属性*/
	protected String  name; /*规则名称*/
	protected String  ruleType; /*规则类型*/
	protected String  content; /*规则内容*/
	protected String  tipInfo; /*提示信息*/
	public void setId(String id) 
	{
		this.id = id;
	}
	/**
	 * 返回 规则ID
	 * @return
	 */
	public String getId() 
	{
		return this.id;
	}
	public void setDefId(String defId) 
	{
		this.defId = defId;
	}
	/**
	 * 返回 对象定义ID
	 * @return
	 */
	public String getDefId() 
	{
		return this.defId;
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
	public void setBelongType(String belongType) 
	{
		this.belongType = belongType;
	}
	/**
	 * 返回 从属类型。def=对象定义；attr=只属于某个属性
	 * @return
	 */
	public String getBelongType() 
	{
		return this.belongType;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	/**
	 * 返回 规则名称
	 * @return
	 */
	public String getName() 
	{
		return this.name;
	}
	public void setRuleType(String ruleType) 
	{
		this.ruleType = ruleType;
	}
	/**
	 * 返回 规则类型
	 * @return
	 */
	public String getRuleType() 
	{
		return this.ruleType;
	}
	public void setContent(String content) 
	{
		this.content = content;
	}
	/**
	 * 返回 规则内容
	 * @return
	 */
	public String getContent() 
	{
		return this.content;
	}
	public void setTipInfo(String tipInfo) 
	{
		this.tipInfo = tipInfo;
	}
	/**
	 * 返回 提示信息
	 * @return
	 */
	public String getTipInfo() 
	{
		return this.tipInfo;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("defId", this.defId) 
		.append("attrId", this.attrId) 
		.append("belongType", this.belongType) 
		.append("name", this.name) 
		.append("ruleType", this.ruleType) 
		.append("content", this.content) 
		.append("tipInfo", this.tipInfo) 
		.toString();
	}
}