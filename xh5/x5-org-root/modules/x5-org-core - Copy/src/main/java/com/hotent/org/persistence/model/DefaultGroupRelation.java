package com.hotent.org.persistence.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import com.hotent.base.core.model.AbstractModel;
import com.hotent.org.api.model.GroupRelation;

/**
 * 对象功能:用户组关系表 entity对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zyp
 * 创建时间:2013-12-20 09:41:01
 */
public class DefaultGroupRelation extends AbstractModel<String> implements GroupRelation{
	protected String  relId; /*关系ID*/
	protected String  groupId; /*用户组ID*/
	protected String  relGroupId; /*用户组ID*/
	protected String  relType; /*可放置多种用户组关系类型*/
	protected String  memo; /*关系备注*/
	protected java.util.Date  createTime; /*创建时间*/
	@Override
	public void setId(String relId) {
		this.relId = relId;
	}
	@Override
	public String getId() {
		return relId;
	}	
	public void setRelId(String relId) 
	{
		this.relId = relId;
	}
	/**
	 * 返回 关系ID
	 * @return
	 */
	public String getRelId() 
	{
		return this.relId;
	}
	public void setGroupId(String groupId) 
	{
		this.groupId = groupId;
	}
	/**
	 * 返回 用户组ID
	 * @return
	 */
	public String getGroupId() 
	{
		return this.groupId;
	}
	public void setRelGroupId(String relGroupId) 
	{
		this.relGroupId = relGroupId;
	}
	/**
	 * 返回 用户组ID
	 * @return
	 */
	public String getRelGroupId() 
	{
		return this.relGroupId;
	}
	public void setRelType(String relType) 
	{
		this.relType = relType;
	}
	/**
	 * 返回 可放置多种用户组关系类型
	 * @return
	 */
	public String getRelType() 
	{
		return this.relType;
	}
	public void setMemo(String memo) 
	{
		this.memo = memo;
	}
	/**
	 * 返回 关系备注
	 * @return
	 */
	public String getMemo() 
	{
		return this.memo;
	}
	public void setCreateTime(java.util.Date createTime) 
	{
		this.createTime = createTime;
	}
	/**
	 * 返回 创建时间
	 * @return
	 */
	public java.util.Date getCreateTime() 
	{
		return this.createTime;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("relId", this.relId) 
		.append("groupId", this.groupId) 
		.append("relGroupId", this.relGroupId) 
		.append("relType", this.relType) 
		.append("memo", this.memo) 
		.append("createTime", this.createTime) 
		.toString();
	}
}