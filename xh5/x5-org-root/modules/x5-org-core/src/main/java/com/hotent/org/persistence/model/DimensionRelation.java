package com.hotent.org.persistence.model;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.base.core.model.AbstractModel;

/**
 * 对象功能:XOG_DIM_REL entity对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zyp
 * 创建时间:2014-02-10 16:12:17
 */
public class DimensionRelation extends AbstractModel<String>{
	protected String  id; /*维度业务关系ID*/
	protected String  dimId; /*当前维度ID*/
	protected String  relDimId; /*关联维度ID*/
	protected String  relType; /*关系类型。combination=组合维度*/
	public void setId(String id) 
	{
		this.id = id;
	}
	/**
	 * 返回 维度业务关系ID
	 * @return
	 */
	public String getId() 
	{
		return this.id;
	}
	public void setDimId(String dimId) 
	{
		this.dimId = dimId;
	}
	/**
	 * 返回 当前维度ID
	 * @return
	 */
	public String getDimId() 
	{
		return this.dimId;
	}
	public void setRelDimId(String relDimId) 
	{
		this.relDimId = relDimId;
	}
	/**
	 * 返回 关联维度ID
	 * @return
	 */
	public String getRelDimId() 
	{
		return this.relDimId;
	}
	public void setRelType(String relType) 
	{
		this.relType = relType;
	}
	/**
	 * 返回 关系类型。combination=组合维度
	 * @return
	 */
	public String getRelType() 
	{
		return this.relType;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("dimId", this.dimId) 
		.append("relDimId", this.relDimId) 
		.append("relType", this.relType) 
		.toString();
	}
}