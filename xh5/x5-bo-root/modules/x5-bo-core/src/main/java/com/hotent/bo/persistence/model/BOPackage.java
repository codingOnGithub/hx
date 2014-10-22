package com.hotent.bo.persistence.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import com.hotent.base.core.model.AbstractModel;

/**
 * 对象功能:业务实体包 entity对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:何一帆
 * 创建时间:2014-01-21 10:39:34
 */
public class BOPackage extends AbstractModel<String>{
	
	public final static class BOPACKAGE_STATUS{
		/**
		 * 末激活
		 */
		public final static String INACTIVE = "inactive";
		/**
		 * 已激活
		 */
		public final static String ACTIVED = "actived";
		/**
		 * 禁止
		 */
		public final static String FORBIDDEN = "forbidden";

	}
	
	protected String  packageId; /*包ID*/
	protected String  name; /*包名*/
	protected String  path; /*包路径*/
	protected Long  depth;			/*层次*/
	protected String  desc; /*包描述*/
	protected String  parentId; /*上级包ID*/
	protected String  status; /*状态。inactive=未激活；actived=激活；forbidden=禁用*/
	protected String  createBy; /*创建人ID*/
	protected java.util.Date  createTime; /*创建时间*/
	@Override
	public void setId(String packageId) {
		setPackageId(packageId);
	}
	@Override
	public String getId() {
		return getPackageId();
	}	
	public void setPackageId(String packageId) 
	{
		this.packageId = packageId;
	}
	/**
	 * 返回 包ID
	 * @return
	 */
	public String getPackageId() 
	{
		return this.packageId;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	/**
	 * 返回 包名
	 * @return
	 */
	public String getName() 
	{
		return this.name;
	}
	public void setPath(String path) 
	{
		this.path = path;
	}
	/**
	 * 返回 包路径
	 * @return
	 */
	public String getPath() 
	{
		return this.path;
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
	public void setDesc(String desc) 
	{
		this.desc = desc;
	}
	/**
	 * 返回 包描述
	 * @return
	 */
	public String getDesc() 
	{
		return this.desc;
	}
	public void setParentId(String parentId) 
	{
		this.parentId = parentId;
	}
	/**
	 * 返回 上级包ID
	 * @return
	 */
	public String getParentId() 
	{
		return this.parentId;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}
	/**
	 * 返回 状态。inactive=未激活；actived=激活；forbidden=禁用
	 * @return
	 */
	public String getStatus() 
	{
		return this.status;
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
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("packageId", this.packageId) 
		.append("name", this.name) 
		.append("path", this.path) 
		.append("depth", this.depth) 
		.append("desc", this.desc) 
		.append("parentId", this.parentId) 
		.append("status", this.status) 
		.append("createBy", this.createBy) 
		.append("createTime", this.createTime) 
		.toString();
	}
}