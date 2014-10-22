package com.hotent.org.persistence.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.base.core.model.AbstractModel;
import com.hotent.org.api.model.RelationType;

/**
 * 对象功能:用户及组关系类型定义 entity对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zyp
 * 创建时间:2014-02-10 16:03:54
 */
public class DefaultRelationType extends AbstractModel<String> implements RelationType{
	protected String  id; /*关系类型ID*/
	protected String  name; /*关系名*/
	protected String  key; /*关系业务主键*/
	protected String  type; /*关系归属。用户关系=user；用户组关系=group；用户与组关系=usergroup*/
	protected String  constType; /*关系约束类型。1对1=one2one；1对多=one2many；多对1=many2one；多对多=many2many*/
	protected String  currentName; /*关系当前方名称*/
	protected String  relName; /*关系关联方名称*/
	protected String  currentDimId; /*当前方维度ID（仅对用户组关系）*/
	protected String  relDimId; /*关联方维度ID（用户关系忽略此值）*/
	protected String  status; /*关系状态。actived=正常；forbidden=禁用*/
	protected String  memo; /*关系备注*/
	protected String  createBy; /*创建人ID*/
	protected java.util.Date  createTime; /*创建时间*/
	protected String  createSaasId; /*公共 - 创建者所属SAAS ID*/
	protected String  updateBy; /*更新人ID*/
	protected java.util.Date  updateTime; /*更新时间*/
	protected char  isBidirectonal = IS_BIDIRECTONAL_Y; /*是否是双向*/
	protected List<DefaultGroupAuthorization> groupAuthorizationList=new ArrayList<DefaultGroupAuthorization>(); /*分级管理具体授权列表*/
	protected List<DefaultUserGroupRelation> userGroupList=new ArrayList<DefaultUserGroupRelation>(); /*@名称：XOG_USER_GROUP【用户和组关系表】列表*/
	public void setId(String id) 
	{
		this.id = id;
	}
	/**
	 * 返回 关系类型ID
	 * @return
	 */
	public String getId() 
	{
		return this.id;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	/**
	 * 返回 关系名
	 * @return
	 */
	public String getName() 
	{
		return this.name;
	}
	public void setKey(String key) 
	{
		this.key = key;
	}
	/**
	 * 返回 关系业务主键
	 * @return
	 */
	public String getKey() 
	{
		return this.key;
	}
	public void setType(String type) 
	{
		this.type = type;
	}
	/**
	 * 返回 关系归属。用户关系=user；用户组关系=group；用户与组关系=usergroup
	 * @return
	 */
	public String getType() 
	{
		return this.type;
	}
	public void setConstType(String constType) 
	{
		this.constType = constType;
	}
	/**
	 * 返回 关系约束类型。1对1=one2one；1对多=one2many；多对1=many2one；多对多=many2many
	 * @return
	 */
	public String getConstType() 
	{
		return this.constType;
	}
	public void setCurrentName(String currentName) 
	{
		this.currentName = currentName;
	}
	/**
	 * 返回 关系当前方名称
	 * @return
	 */
	public String getCurrentName() 
	{
		return this.currentName;
	}
	public void setRelName(String relName) 
	{
		this.relName = relName;
	}
	/**
	 * 返回 关系关联方名称
	 * @return
	 */
	public String getRelName() 
	{
		return this.relName;
	}
	public void setCurrentDimId(String currentDimId) 
	{
		this.currentDimId = currentDimId;
	}
	/**
	 * 返回 当前方维度ID（仅对用户组关系）
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
	 * 返回 关联方维度ID（用户关系忽略此值）
	 * @return
	 */
	public String getRelDimId() 
	{
		return this.relDimId;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}
	/**
	 * 返回 关系状态。actived=正常；forbidden=禁用
	 * @return
	 */
	public String getStatus() 
	{
		return this.status;
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
	public void setCreateSaasId(String createSaasId) 
	{
		this.createSaasId = createSaasId;
	}
	/**
	 * 返回 公共 - 创建者所属SAAS ID
	 * @return
	 */
	public String getCreateSaasId() 
	{
		return this.createSaasId;
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
	public void setIsBidirectonal(char isBidirectonal) 
	{
		this.isBidirectonal = isBidirectonal;
	}
	/**
	 * 返回 是否是双向
	 * @return
	 */
	public char getIsBidirectonal() 
	{
		return this.isBidirectonal;
	}
	public void setGroupAuthorizationList(List<DefaultGroupAuthorization> groupAuthorizationList) 
	{
		this.groupAuthorizationList = groupAuthorizationList;
	}
	/**
	 * 返回 分级管理具体授权列表
	 * @return
	 */
	public List<DefaultGroupAuthorization> getGroupAuthorizationList() 
	{
		return this.groupAuthorizationList;
	}
	public void setDefaultUserGroupRelationList(List<DefaultUserGroupRelation> userGroupList) 
	{
		this.userGroupList = userGroupList;
	}
	/**
	 * 返回 @名称：XOG_USER_GROUP【用户和组关系表】列表
	 * @return
	 */
	public List<DefaultUserGroupRelation> getDefaultUserGroupRelationList() 
	{
		return this.userGroupList;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("name", this.name) 
		.append("key", this.key) 
		.append("type", this.type) 
		.append("constType", this.constType) 
		.append("currentName", this.currentName) 
		.append("relName", this.relName) 
		.append("currentDimId", this.currentDimId) 
		.append("relDimId", this.relDimId) 
		.append("status", this.status) 
		.append("memo", this.memo) 
		.append("createBy", this.createBy) 
		.append("createTime", this.createTime) 
		.append("createSaasId", this.createSaasId) 
		.append("updateBy", this.updateBy) 
		.append("updateTime", this.updateTime) 
		.append("isBidirectonal", this.isBidirectonal) 
		.toString();
	}
}