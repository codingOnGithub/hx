package com.hotent.org.persistence.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.base.core.model.AbstractModel;
import com.hotent.org.api.model.Group;
import com.hotent.org.api.model.GroupRelation;

/**
 * 对象功能:@名称：XOG_GROUP【系统用户组】 entity对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:王龙升
 * 创建时间:2014-02-10 16:12:17
 */
public class DefaultGroup extends AbstractModel<String> implements Group{
	protected String  groupId; /*用户组ID*/
	protected String  name; /*用户组名称*/
	protected String  key; /*用户组业务主键*/
	protected String  dimId; /*所属维度业务主键*/
	protected String  rankTypeKey; /*所属用户组等级分类*/
	protected Status  status; /*状态。0 未激活；1 已激活；2 锁定。*/
	protected String  desc; /*描述*/
	protected Long  sn;			/*排序号*/
	protected String  parentId; /*父用户组ID*/
	protected Long  depth;			/*层次*/
	protected String  path; /*路径*/
	protected String  createBy; /*创建人ID*/
	protected java.util.Date  createTime; /*创建时间*/
	protected String  createSaasId; /*公共 - 创建者所属SAAS ID*/
	protected String  updateBy; /*更新人ID*/
	protected java.util.Date  updateTime; /*更新时间*/
	protected String  form; /*来源*/
	protected List<GroupRelation> groupRelationList=new ArrayList<GroupRelation>(); /*XOG_GROUP_REL列表*/
	@Override
	public void setId(String groupId) {
		setGroupId(groupId);
	}
	@Override
	public String getId() {
		return getGroupId();
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
	public void setName(String name) 
	{
		this.name = name;
	}
	/**
	 * 返回 用户组名称
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
	 * 返回 用户组业务主键
	 * @return
	 */
	public String getKey() 
	{
		return this.key;
	}
	public void setDimId(String dimId) 
	{
		this.dimId = dimId;
	}
	/**
	 * 返回 所属维度业务主键
	 * @return
	 */
	public String getDimId() 
	{
		return this.dimId;
	}
	public void setRankTypeKey(String rankTypeKey) 
	{
		this.rankTypeKey = rankTypeKey;
	}
	/**
	 * 返回 所属用户组等级分类
	 * @return
	 */
	public String getRankTypeKey() 
	{
		return this.rankTypeKey;
	}
	public void setStatus(Status status) 
	{
		this.status = status;
	}
	/**
	 * 返回 状态。0 未激活；1 已激活；2 锁定。
	 * @return
	 */
	public Status getStatus() 
	{
		return this.status;
	}
	public void setDesc(String desc) 
	{
		this.desc = desc;
	}
	/**
	 * 返回 描述
	 * @return
	 */
	public String getDesc() 
	{
		return this.desc;
	}
	public void setSn(Long sn) 
	{
		this.sn = sn;
	}
	/**
	 * 返回 排序号
	 * @return
	 */
	public Long getSn() 
	{
		return this.sn;
	}
	public void setParentId(String parentId) 
	{
		this.parentId = parentId;
	}
	/**
	 * 返回 父用户组ID
	 * @return
	 */
	public String getParentId() 
	{
		return this.parentId;
	}
	public void setDepth(Long depth) 
	{
		this.depth = depth;
	}
	/**
	 * 返回 层次
	 * @return
	 */
	public Long getDepth() 
	{
		return this.depth;
	}
	public void setPath(String path) 
	{
		this.path = path;
	}
	/**
	 * 返回 路径
	 * @return
	 */
	public String getPath() 
	{
		return this.path;
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
	public void setForm(String form) 
	{
		this.form = form;
	}
	/**
	 * 返回 来源
	 * @return
	 */
	public String getForm() 
	{
		return this.form;
	}
	public void setGroupRelationList(List<GroupRelation> groupRelationList) 
	{
		this.groupRelationList = groupRelationList;
	}
	/**
	 * 返回 XOG_GROUP_REL列表
	 * @return
	 */
	public List<GroupRelation> getGroupRelationList() 
	{
		return this.groupRelationList;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("groupId", this.groupId) 
		.append("name", this.name) 
		.append("key", this.key) 
		.append("dimKey", this.dimId) 
		.append("rankTypeKey", this.rankTypeKey) 
		.append("status", this.status) 
		.append("desc", this.desc) 
		.append("sn", this.sn) 
		.append("parentId", this.parentId) 
		.append("depth", this.depth) 
		.append("path", this.path) 
		.append("createBy", this.createBy) 
		.append("createTime", this.createTime) 
		.append("createSaasId", this.createSaasId) 
		.append("updateBy", this.updateBy) 
		.append("updateTime", this.updateTime) 
		.append("form", this.form) 
		.toString();
	}
	@Override
	public String getIdentityType() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isDeleted() {
		// TODO Auto-generated method stub
		return false;
	}
	public void setDeleted(boolean b) {
		// TODO Auto-generated method stub
		
	}
	public void setType(String type) {
		// TODO Auto-generated method stub
		
	}
}