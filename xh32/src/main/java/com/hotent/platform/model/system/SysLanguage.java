package com.hotent.platform.model.system;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
/**
 * 对象功能:系统支持的语言 Model对象
 * 开发公司:SF
 * 开发人员:xxx
 * 创建时间:2013-12-26 18:29:05
 */
public class SysLanguage extends BaseModel
{
	/**
	 * 简体中文
	 */
	public static String SIMPLIFIED_CHINESE = "zh_CN";
	/**
	 * 繁体中文
	 */
	public static String TRADITIONAL_CHINESE = "zh_TW";
	/**
	 * 英文
	 */
	public static String ENGLISH = "en_US";
	/**
	 * cookie当前语言
	 */
	public static String CURRENT_LANGUAGE = "CURRENT_LANGUAGE";
	/**
	 * 系统默认语言
	 */
	public static Short DEFAULT_LANGUAGE = 1;
	/**
	 * 系统禁用语言
	 */
	public static Short DISABLE_LANGUAGE = -1;
	/**
	 * 系统其他语言
	 */
	public static Short COMMON_LANGUAGE = 0;
	// 主键
	protected Long  id;
	// 语言
	protected String  language;
	// 是否默认1，默认 0，普通 -1，禁用
	protected Short  isdefault=1;
	// 备注
	protected String  memo;
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
	public void setLanguage(String language) 
	{
		this.language = language;
	}
	/**
	 * 返回 语言
	 * @return
	 */
	public String getLanguage() 
	{
		return this.language;
	}
	public void setIsdefault(Short isdefault) 
	{
		this.isdefault = isdefault;
	}
	/**
	 * 返回 是否默认
	 * @return
	 */
	public Short getIsdefault() 
	{
		return this.isdefault;
	}
	public void setMemo(String memo) 
	{
		this.memo = memo;
	}
	/**
	 * 返回 备注
	 * @return
	 */
	public String getMemo() 
	{
		return this.memo;
	}


   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof SysLanguage)) 
		{
			return false;
		}
		SysLanguage rhs = (SysLanguage) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.language, rhs.language)
		.append(this.isdefault, rhs.isdefault)
		.append(this.memo, rhs.memo)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.language) 
		.append(this.isdefault) 
		.append(this.memo) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("language", this.language) 
		.append("isdefault", this.isdefault) 
		.append("memo", this.memo) 
		.toString();
	}
   
  

}