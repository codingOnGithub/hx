package com.hotent.demo.model.ztree;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:SYS_ZTREE Model对象
 */
public class SysZtree extends BaseModel
{
	//主键
	protected Long typeid;
	/**
	 *分类名称
	 */
	protected String  typename;
	/**
	 *分类别名
	 */
	protected String  alias;
	/**
	 *用于tree字段的ID字段 如orgId
	 */
	protected String  idkey;
	/**
	 *用于tree字段的Name字段 如orgName
	 */
	protected String  namekey;
	/**
	 *用于tree字段的parentID字段 如orgParentId
	 */
	protected String  pidkey;
	/**
	 *默认加载数据的url
	 */
	protected String  urls;
	/**
	 *用做根节点的ID
	 */
	protected String  rootpid;
	/**
	 *  用于根节点的名称
	 */
	protected String  roottitle;
	/**
	 * 默认展开的层数
	 */
	protected Long  expandslevel;
	/**
	 *用于查询的params的返回值字段集合。如果为空，则表示返回查询的所有字段
	 */
	protected String  dataparams;
	/**
	 *默认的js代码 包括onclick、onRightClick、等
	 */
	protected String  callbacks;
	/**
	 *对应于ztree的异步设置,方便扩展
	 */
	protected String  asyncs;
	/**
	 *对应于ztree的check设置  ,方便扩展
	 */
	protected String  checks;
	/**
	 *对应于ztree的data设置,方便扩展
	 */
	protected String  datas;
	/**
	 *对应于ztree的edit设置,方便扩展
	 */
	protected String  edits;
	/**
	 *对应于ztree的view设置,方便扩展
	 */
	protected String  views;
	/**
	 *对应的表名
	 */
	protected String  tablename;
	/**
	 *是表或者视图 1=表 0=视图
	 */
	protected Long  istable;
	/**
	 *数据源别名
	 */
	protected String  dsname;
	
	public Long getTypeid() {
		return typeid;
	}
	public void setTypeid(Long typeid) {
		this.typeid = typeid;
	}
	
	public void setTypename(String typename) 
	{
		this.typename = typename;
	}
	/**
	 * 返回 分类名称
	 * @return
	 */
	public String getTypename() 
	{
		return this.typename;
	}
	public void setAlias(String alias) 
	{
		this.alias = alias;
	}
	/**
	 * 返回 分类别名
	 * @return
	 */
	public String getAlias() 
	{
		return this.alias;
	}
	public void setIdkey(String idkey) 
	{
		this.idkey = idkey;
	}
	/**
	 * 返回 用于tree字段的ID字段 如orgId
	 * @return
	 */
	public String getIdkey() 
	{
		return this.idkey;
	}
	public void setNamekey(String namekey) 
	{
		this.namekey = namekey;
	}
	/**
	 * 返回 用于tree字段的Name字段 如orgName
	 * @return
	 */
	public String getNamekey() 
	{
		return this.namekey;
	}
	public void setPidkey(String pidkey) 
	{
		this.pidkey = pidkey;
	}
	/**
	 * 返回 用于tree字段的parentID字段 如orgParentId
	 * @return
	 */
	public String getPidkey() 
	{
		return this.pidkey;
	}
	public void setUrls(String urls) 
	{
		this.urls = urls;
	}
	/**
	 * 返回 默认加载数据的url
	 * @return
	 */
	public String getUrls() 
	{
		return this.urls;
	}
	public void setRootpid(String rootpid) 
	{
		this.rootpid = rootpid;
	}
	/**
	 * 返回 用做根节点的ID
	 * @return
	 */
	public String getRootpid() 
	{
		return this.rootpid;
	}
	public void setRoottitle(String roottitle) 
	{
		this.roottitle = roottitle;
	}
	/**
	 * 返回   用于根节点的名称
	 * @return
	 */
	public String getRoottitle() 
	{
		return this.roottitle;
	}
	public void setExpandslevel(Long expandslevel) 
	{
		this.expandslevel = expandslevel;
	}
	/**
	 * 返回  默认展开的层数
	 * @return
	 */
	public Long getExpandslevel() 
	{
		return this.expandslevel;
	}
	public void setDataparams(String dataparams) 
	{
		this.dataparams = dataparams;
	}
	/**
	 * 返回 用于查询的params的返回值字段集合。如果为空，则表示返回查询的所有字段
	 * @return
	 */
	public String getDataparams() 
	{
		return this.dataparams;
	}
	public void setCallbacks(String callbacks) 
	{
		this.callbacks = callbacks;
	}
	/**
	 * 返回 默认的js代码 包括onclick、onRightClick、等
	 * @return
	 */
	public String getCallbacks() 
	{
		return this.callbacks;
	}
	public void setAsyncs(String asyncs) 
	{
		this.asyncs = asyncs;
	}
	/**
	 * 返回 对应于ztree的异步设置,方便扩展
	 * @return
	 */
	public String getAsyncs() 
	{
		return this.asyncs;
	}
	public void setChecks(String checks) 
	{
		this.checks = checks;
	}
	/**
	 * 返回 对应于ztree的check设置  ,方便扩展
	 * @return
	 */
	public String getChecks() 
	{
		return this.checks;
	}
	public void setDatas(String datas) 
	{
		this.datas = datas;
	}
	/**
	 * 返回 对应于ztree的data设置,方便扩展
	 * @return
	 */
	public String getDatas() 
	{
		return this.datas;
	}
	public void setEdits(String edits) 
	{
		this.edits = edits;
	}
	/**
	 * 返回 对应于ztree的edit设置,方便扩展
	 * @return
	 */
	public String getEdits() 
	{
		return this.edits;
	}
	public void setViews(String views) 
	{
		this.views = views;
	}
	/**
	 * 返回 对应于ztree的view设置,方便扩展
	 * @return
	 */
	public String getViews() 
	{
		return this.views;
	}
	public void setTablename(String tablename) 
	{
		this.tablename = tablename;
	}
	/**
	 * 返回 对应的表名
	 * @return
	 */
	public String getTablename() 
	{
		return this.tablename;
	}
	public void setIstable(Long istable) 
	{
		this.istable = istable;
	}
	/**
	 * 返回 是表或者视图 1=表 0=视图
	 * @return
	 */
	public Long getIstable() 
	{
		return this.istable;
	}
	public void setDsname(String dsname) 
	{
		this.dsname = dsname;
	}
	/**
	 * 返回 数据源别名
	 * @return
	 */
	public String getDsname() 
	{
		return this.dsname;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof SysZtree)) 
		{
			return false;
		}
		SysZtree rhs = (SysZtree) object;
		return new EqualsBuilder()
		.append(this.typeid,rhs.typeid)
		.append(this.typename, rhs.typename)
		.append(this.alias, rhs.alias)
		.append(this.idkey, rhs.idkey)
		.append(this.namekey, rhs.namekey)
		.append(this.pidkey, rhs.pidkey)
		.append(this.urls, rhs.urls)
		.append(this.rootpid, rhs.rootpid)
		.append(this.roottitle, rhs.roottitle)
		.append(this.expandslevel, rhs.expandslevel)
		.append(this.dataparams, rhs.dataparams)
		.append(this.callbacks, rhs.callbacks)
		.append(this.asyncs, rhs.asyncs)
		.append(this.checks, rhs.checks)
		.append(this.datas, rhs.datas)
		.append(this.edits, rhs.edits)
		.append(this.views, rhs.views)
		.append(this.tablename, rhs.tablename)
		.append(this.istable, rhs.istable)
		.append(this.dsname, rhs.dsname)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.typeid)
		.append(this.typename) 
		.append(this.alias) 
		.append(this.idkey) 
		.append(this.namekey) 
		.append(this.pidkey) 
		.append(this.urls) 
		.append(this.rootpid) 
		.append(this.roottitle) 
		.append(this.expandslevel) 
		.append(this.dataparams) 
		.append(this.callbacks) 
		.append(this.asyncs) 
		.append(this.checks) 
		.append(this.datas) 
		.append(this.edits) 
		.append(this.views) 
		.append(this.tablename) 
		.append(this.istable) 
		.append(this.dsname) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("typeid",this.typeid)
		.append("typename", this.typename) 
		.append("alias", this.alias) 
		.append("idkey", this.idkey) 
		.append("namekey", this.namekey) 
		.append("pidkey", this.pidkey) 
		.append("urls", this.urls) 
		.append("rootpid", this.rootpid) 
		.append("roottitle", this.roottitle) 
		.append("expandslevel", this.expandslevel) 
		.append("dataparams", this.dataparams) 
		.append("callbacks", this.callbacks) 
		.append("asyncs", this.asyncs) 
		.append("checks", this.checks) 
		.append("datas", this.datas) 
		.append("edits", this.edits) 
		.append("views", this.views) 
		.append("tablename", this.tablename) 
		.append("istable", this.istable) 
		.append("dsname", this.dsname) 
		.toString();
	}

}
