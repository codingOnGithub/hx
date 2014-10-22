package com.hotent.org.persistence.model;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.base.core.model.AbstractModel;
import com.hotent.org.api.model.GroupRelation;

/**
 * 对象功能:XOG_GROUP_REL entity对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zyp
 * 创建时间:2014-02-18 20:00:06
 */
public class DefaultGroupRelation extends AbstractModel<String> implements GroupRelation{
	protected String  id; /*用户组关系ID*/
	protected String  relTypeId; /*关系类型ID*/
	protected String  groupId; /*当前用户组ID*/
	protected String  relGroupId; /*关联用户组ID*/
	protected String  currentDimId; /*冗余 - 当前用户组所属维度ID*/
	protected String  relDimId; /*冗余 - 关联用户组所属维度ID*/
	protected char  isCombination; /*是否由组合维度创建*/
	protected Status  status; /*状态。actived 已激活（正常）；locked 锁定（禁用）；archived=归档（历史关系）*/
	protected java.util.Date  startTime; /*有效时间起始*/
	protected java.util.Date  endTime; /*有效时间起始*/
	protected String  createBy; /*创建人ID*/
	protected java.util.Date  createTime; /*创建时间*/
	protected String  updateBy; /*更新人ID*/
	protected java.util.Date  updateTime; /*更新时间*/
	public void setId(String id) 
	{
		this.id = id;
	}
	/**
	 * 返回 用户组关系ID
	 * @return
	 */
	public String getId() 
	{
		return this.id;
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
	public void setGroupId(String groupId) 
	{
		this.groupId = groupId;
	}
	/**
	 * 返回 当前用户组ID
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
	 * 返回 关联用户组ID
	 * @return
	 */
	public String getRelGroupId() 
	{
		return this.relGroupId;
	}
	public void setCurrentDimId(String currentDimId) 
	{
		this.currentDimId = currentDimId;
	}
	/**
	 * 返回 冗余 - 当前用户组所属维度ID
	 * @return
	 */
	public String getCurrentDimId() 
	{
		return this.currentDimId;
	}
	public void setRelDimId(String relDimId) 
	{
		this.relDimId = relDimId;
	}
	/**
	 * 返回 冗余 - 关联用户组所属维度ID
	 * @return
	 */
	public String getRelDimId() 
	{
		return this.relDimId;
	}
	public void setIsCombination(char isCombination) 
	{
		this.isCombination = isCombination;
	}
	/**
	 * 返回 是否由组合维度创建
	 * @return
	 */
	public char getIsCombination() 
	{
		return this.isCombination;
	}
	public void setStatus(Status status) 
	{
		this.status = status;
	}
	/**
	 * 返回 状态。actived 已激活（正常）；locked 锁定（禁用）；archived=归档（历史关系）
	 * @return
	 */
	public Status getStatus() 
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
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("relTypeId", this.relTypeId) 
		.append("groupId", this.groupId) 
		.append("relGroupId", this.relGroupId) 
		.append("currentDimId", this.currentDimId) 
		.append("relDimId", this.relDimId) 
		.append("isCombination", this.isCombination) 
		.append("status", this.status) 
		.append("startTime", this.startTime) 
		.append("endTime", this.endTime) 
		.append("createBy", this.createBy) 
		.append("createTime", this.createTime) 
		.append("updateBy", this.updateBy) 
		.append("updateTime", this.updateTime) 
		.toString();
	}
}