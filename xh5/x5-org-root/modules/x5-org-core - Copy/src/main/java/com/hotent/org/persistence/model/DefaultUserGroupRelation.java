package com.hotent.org.persistence.model;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.base.core.model.AbstractModel;

/**
 * 对象功能:@名称：XOG_USER_GROUP【用户和组关系表】 entity对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zyp
 * 创建时间:2014-02-10 16:12:17
 */
public class DefaultUserGroupRelation extends AbstractModel<String>{
	protected String  relId; /*主键*/
	protected String  relTypeId; /*关系类型ID*/
	protected String  userId; /*用户ID*/
	protected String  groupId; /*用户组ID*/
	protected String  dimId; /*维度ID*/
	protected String  status; /*状态。actived=正常；forbidden=禁用；archives=归档（历史关系）*/
	protected java.util.Date  startTime; /*有效时间起始*/
	protected java.util.Date  endTime; /*有效时间起始*/
	protected String  createBy; /*创建人ID*/
	protected java.util.Date  createTime; /*创建时间*/
	protected String  updateBy; /*更新人ID*/
	protected java.util.Date  updateTime; /*更新时间*/
	protected String  from; /*来源*/
	@Override
	public void setId(String relId) {
		setRelId(relId);
	}
	@Override
	public String getId() {
		return getRelId();
	}	
	public void setRelId(String relId) 
	{
		this.relId = relId;
	}
	/**
	 * 返回 主键
	 * @return
	 */
	public String getRelId() 
	{
		return this.relId;
	}
	public void setRelTypeId(String relTypeId) 
	{
		this.relTypeId = relTypeId;
	}
	/**
	 * 返回 关系类型ID
	 * @return
	 */
	public String getRelTypeId() 
	{
		return this.relTypeId;
	}
	public void setUserId(String userId) 
	{
		this.userId = userId;
	}
	/**
	 * 返回 用户ID
	 * @return
	 */
	public String getUserId() 
	{
		return this.userId;
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
	public void setDimId(String dimId) 
	{
		this.dimId = dimId;
	}
	/**
	 * 返回 维度ID
	 * @return
	 */
	public String getDimId() 
	{
		return this.dimId;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}
	/**
	 * 返回 状态。actived=正常；forbidden=禁用；archives=归档（历史关系）
	 * @return
	 */
	public String getStatus() 
	{
		return this.status;
	}
	public void setStartTime(java.util.Date startTime) 
	{
		this.startTime = startTime;
	}
	/**
	 * 返回 有效时间起始
	 * @return
	 */
	public java.util.Date getStartTime() 
	{
		return this.startTime;
	}
	public void setEndTime(java.util.Date endTime) 
	{
		this.endTime = endTime;
	}
	/**
	 * 返回 有效时间起始
	 * @return
	 */
	public java.util.Date getEndTime() 
	{
		return this.endTime;
	}
	public void setCreateBy(String createBy) 
	{
		this.createBy = createBy;
	}
	/**
	 * 返回 创建人ID
	 * @return
	 */
	public String getCreateBy() 
	{
		return this.createBy;
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
	public void setUpdateBy(String updateBy) 
	{
		this.updateBy = updateBy;
	}
	/**
	 * 返回 更新人ID
	 * @return
	 */
	public String getUpdateBy() 
	{
		return this.updateBy;
	}
	public void setUpdateTime(java.util.Date updateTime) 
	{
		this.updateTime = updateTime;
	}
	/**
	 * 返回 更新时间
	 * @return
	 */
	public java.util.Date getUpdateTime() 
	{
		return this.updateTime;
	}
	public void setFrom(String from) 
	{
		this.from = from;
	}
	/**
	 * 返回 来源
	 * @return
	 */
	public String getFrom() 
	{
		return this.from;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("relId", this.relId) 
		.append("relTypeId", this.relTypeId) 
		.append("userId", this.userId) 
		.append("groupId", this.groupId) 
		.append("dimId", this.dimId) 
		.append("status", this.status) 
		.append("startTime", this.startTime) 
		.append("endTime", this.endTime) 
		.append("createBy", this.createBy) 
		.append("createTime", this.createTime) 
		.append("updateBy", this.updateBy) 
		.append("updateTime", this.updateTime) 
		.append("from", this.from) 
		.toString();
	}
}