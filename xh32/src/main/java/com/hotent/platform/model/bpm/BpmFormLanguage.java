package com.hotent.platform.model.bpm;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.hotent.core.model.BaseModel;
/**
 * 对象功能:表单国际化资源 Model对象
 * 开发公司:宏天
 * 开发人员:xxx
 * 创建时间:2013-12-26 09:54:54
 */
public class BpmFormLanguage extends BaseModel
{
	/**
	 * 表单类型
	 */
	public final static Integer BPM_FORM_TYPE = 1;
	/**
	 * 流程节点类型
	 */
	public final static Integer BPM_DEFINITION_TYPE = 2;
	/**
	 * 流程按钮类型
	 */
	public final static Integer BPM_BUTTON_TYPE = 3;
	/**
	 * 分类标志
	 */
	public final static Integer SYS_TYPE_KEY = 4;
	/**
	 * 系统分类
	 */
	public final static Integer SYS_GLOBAL_TYPE = 5;
	
	// 主键
	protected Long  id;
	// 表单ID或者表格ID,流程actID或流程按钮ID
	protected String  formid;
	// 资源KEY
	protected String  reskey;
	// 资源值
	protected String  resvalue;
	// 分类ID(1,表单字段 2,流程节点 3,流程按钮)
	protected Integer typeid;
	// 语言类型
	protected String  lantype;
	// 是否新增记录
	protected boolean isAdd = false;
	public boolean isAdd() {
		return isAdd;
	}
	public void setAdd(boolean isAdd) {
		this.isAdd = isAdd;
	}
	public void setId(Long id) 
	{
		this.id = id;
	}
	/**
	 * 返回 主键
	 * @return
	 */
	public Long getId() 
	{
		return this.id;
	}
	public void setFormid(String formid) 
	{
		this.formid = formid;
	}
	/**
	 * 返回 表单ID或者表格ID
	 * @return
	 */
	public String getFormid() 
	{
		return this.formid;
	}
	public void setReskey(String reskey) 
	{
		this.reskey = reskey;
	}
	/**
	 * 返回 资源KEY
	 * @return
	 */
	public String getReskey() 
	{
		return this.reskey;
	}
	public void setResvalue(String resvalue) 
	{
		this.resvalue = resvalue;
	}
	/**
	 * 返回 资源值
	 * @return
	 */
	public String getResvalue() 
	{
		return this.resvalue;
	}
	public void setLantype(String lantype) 
	{
		this.lantype = lantype;
	}
	/**
	 * 返回 语言类型
	 * @return
	 */
	public String getLantype() 
	{
		return this.lantype;
	}
	

   	public Integer getTypeid() {
		return typeid;
	}
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof BpmFormLanguage)) 
		{
			return false;
		}
		BpmFormLanguage rhs = (BpmFormLanguage) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.formid, rhs.formid)
		.append(this.reskey, rhs.reskey)
		.append(this.resvalue, rhs.resvalue)
		.append(this.lantype, rhs.lantype)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.formid) 
		.append(this.reskey) 
		.append(this.resvalue) 
		.append(this.lantype) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("formid", this.formid) 
		.append("reskey", this.reskey) 
		.append("resvalue", this.resvalue) 
		.append("lantype", this.lantype) 
		.toString();
	}
   
  

}