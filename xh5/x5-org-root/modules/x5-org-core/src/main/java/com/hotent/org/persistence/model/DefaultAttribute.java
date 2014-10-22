package com.hotent.org.persistence.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import com.hotent.base.core.model.AbstractModel;
import com.hotent.org.api.model.Attribute;

/**
 * 对象功能:用户组织属性扩展表 entity对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zyp
 * 创建时间:2013-12-27 10:11:07
 */
public class DefaultAttribute extends AbstractModel<String> implements Attribute{
	protected String  attrId; /*属性ID*/
	protected String  name; /*属性名称*/
	protected String  attrKey; /*属性业务主键*/
	protected BelongType  belongType; /*属性归属类型：group：组属性；user：用户属性*/
	protected DataType  dataType; /*属性数据类型*/
	protected String  desc; /*属性描述*/
	protected String  createOrgId; /*创建者所属组织ID*/
	protected Status  status; 
	@Override
	public void setId(String attrId) {
		this.attrId = attrId;
	}
	@Override
	public String getId() {
		return attrId;
	}	
	public void setAttrId(String attrId) 
	{
		this.attrId = attrId;
	}
	/**
	 * 返回 属性ID
	 * @return
	 */
	public String getAttrId() 
	{
		return this.attrId;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	/**
	 * 返回 属性名称
	 * @return
	 */
	public String getName() 
	{
		return this.name;
	}
	public void setAttrKey(String attrKey) 
	{
		this.attrKey = attrKey;
	}
	/**
	 * 返回 属性业务主键
	 * @return
	 */
	public String getAttrKey() 
	{
		return this.attrKey;
	}
	public void setBelongType(BelongType belongType) 
	{
		this.belongType = belongType;
	}
	/**
	 * 返回 属性归属类型：group：组属性；user：用户属性
	 * @return
	 */
	public BelongType getBelongType() 
	{
		return this.belongType;
	}
	public void setDataType(DataType dataType) 
	{
		this.dataType = dataType;
	}
	/**
	 * 返回 属性数据类型
	 * @return
	 */
	public DataType getDataType() 
	{
		return this.dataType;
	}
	public void setDesc(String desc) 
	{
		this.desc = desc;
	}
	/**
	 * 返回 属性描述
	 * @return
	 */
	public String getDesc() 
	{
		return this.desc;
	}
	public void setCreateOrgId(String createOrgId) 
	{
		this.createOrgId = createOrgId;
	}
	/**
	 * 返回 创建者所属组织ID
	 * @return
	 */
	public String getCreateOrgId() 
	{
		return this.createOrgId;
	}
	
	
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("attrId", this.attrId) 
		.append("name", this.name) 
		.append("attrKey", this.attrKey) 
		.append("belongType", this.belongType) 
		.append("dataType", this.dataType) 
		.append("desc", this.desc) 
		.append("createOrgId", this.createOrgId) 
		.append("status", this.status) 
		.toString();
	}
}