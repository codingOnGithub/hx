package com.hotent.org.persistence.model;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.base.core.model.AbstractModel;

/**
 * 对象功能:用户组等级分类 entity对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zyp
 * 创建时间:2014-02-10 16:03:54
 */
public class DefaultRankType extends AbstractModel<String>{
	protected String  id; /*主键*/
	protected String  dimId; /*维度ID*/
	protected String  name; /*名称*/
	protected String  key; /*分类业务键*/
	protected Long  level;			/*级别数值*/
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
	public void setName(String name) 
	{
		this.name = name;
	}
	/**
	 * 返回 名称
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
	 * 返回 分类业务键
	 * @return
	 */
	public String getKey() 
	{
		return this.key;
	}
	public void setLevel(Long level) 
	{
		this.level = level;
	}
	/**
	 * 返回 级别数值
	 * @return
	 */
	public Long getLevel() 
	{
		return this.level;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("dimId", this.dimId) 
		.append("name", this.name) 
		.append("key", this.key) 
		.append("level", this.level) 
		.toString();
	}
}