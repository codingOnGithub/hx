package com.hotent.org.persistence.model;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.base.core.model.AbstractModel;

/**
 * 对象功能:分级管理具体授权 entity对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zyp
 * 创建时间:2014-02-10 16:03:54
 */
public class DefaultGroupAuthorization extends AbstractModel<String>{
	protected String  id; /*主键*/
	protected String  relTypeId; /*关系类型ID*/
	protected String  managerId; /*分级管理员用户ID*/
	protected String  groupId; /*被授权的用户组ID*/
	protected String  dimId; /*用户组所属维度ID*/
	protected String  showType; /*tree=树型数据。flat=平铺数据*/
	protected String  groupPerms; /*针对子用户组的添加、更新和删除。*/
	protected String  userPerms; /*针对用户与组关系的添加、更新和删除。*/
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
	public void setManagerId(String managerId) 
	{
		this.managerId = managerId;
	}
	/**
	 * 返回 分级管理员用户ID
	 * @return
	 */
	public String getManagerId() 
	{
		return this.managerId;
	}
	public void setGroupId(String groupId) 
	{
		this.groupId = groupId;
	}
	/**
	 * 返回 被授权的用户组ID
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
	 * 返回 用户组所属维度ID
	 * @return
	 */
	public String getDimId() 
	{
		return this.dimId;
	}
	public void setShowType(String showType) 
	{
		this.showType = showType;
	}
	/**
	 * 返回 tree=树型数据。flat=平铺数据
	 * @return
	 */
	public String getShowType() 
	{
		return this.showType;
	}
	public void setGroupPerms(String groupPerms) 
	{
		this.groupPerms = groupPerms;
	}
	/**
	 * 返回 针对子用户组的添加、更新和删除。
	 * @return
	 */
	public String getGroupPerms() 
	{
		return this.groupPerms;
	}
	public void setUserPerms(String userPerms) 
	{
		this.userPerms = userPerms;
	}
	/**
	 * 返回 针对用户与组关系的添加、更新和删除。
	 * @return
	 */
	public String getUserPerms() 
	{
		return this.userPerms;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("relTypeId", this.relTypeId) 
		.append("managerId", this.managerId) 
		.append("groupId", this.groupId) 
		.append("dimId", this.dimId) 
		.append("showType", this.showType) 
		.append("groupPerms", this.groupPerms) 
		.append("userPerms", this.userPerms) 
		.toString();
	}
}