package com.hotent.platform.model.system;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:自定义别名脚本表 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2013-12-19 11:26:03
 */
public class AliasScript extends BaseModel
{
	// ID
	protected Long  id;
	// 脚本的别名
	protected String  aliasName;
	// 脚本的描叙
	protected String  aliasDesc;
	// 调用类的路径
	protected String  className;
	// 调用类的对象名
	protected String  classInsName;
	// 调用的方法名
	protected String  methodName;
	// 调用的方法的描叙
	protected String  methodDesc;
	// 方法返回类型
	protected String  returnType;
	// 方法相关设置
	protected String  argument;
	// 是否禁用
	protected Long  enable;
	public void setId(Long id) 
	{
		this.id = id;
	}
	/**
	 * 返回 ID
	 * @return
	 */
	public Long getId() 
	{
		return this.id;
	}
	public void setAliasName(String aliasName) 
	{
		this.aliasName = aliasName;
	}
	/**
	 * 返回 脚本的别名
	 * @return
	 */
	public String getAliasName() 
	{
		return this.aliasName;
	}
	public void setAliasDesc(String aliasDesc) 
	{
		this.aliasDesc = aliasDesc;
	}
	/**
	 * 返回 脚本的描叙
	 * @return
	 */
	public String getAliasDesc() 
	{
		return this.aliasDesc;
	}
	public void setClassName(String className) 
	{
		this.className = className;
	}
	/**
	 * 返回 调用类的路径
	 * @return
	 */
	public String getClassName() 
	{
		return this.className;
	}
	public void setClassInsName(String classInsName) 
	{
		this.classInsName = classInsName;
	}
	/**
	 * 返回 调用类的对象名
	 * @return
	 */
	public String getClassInsName() 
	{
		return this.classInsName;
	}
	public void setMethodName(String methodName) 
	{
		this.methodName = methodName;
	}
	/**
	 * 返回 调用的方法名
	 * @return
	 */
	public String getMethodName() 
	{
		return this.methodName;
	}
	public void setMethodDesc(String methodDesc) 
	{
		this.methodDesc = methodDesc;
	}
	/**
	 * 返回 调用的方法的描叙
	 * @return
	 */
	public String getMethodDesc() 
	{
		return this.methodDesc;
	}
	public void setReturnType(String returnType) 
	{
		this.returnType = returnType;
	}
	/**
	 * 返回 方法返回类型
	 * @return
	 */
	public String getReturnType() 
	{
		return this.returnType;
	}
	public void setArgument(String argument) 
	{
		this.argument = argument;
	}
	/**
	 * 返回 方法相关设置
	 * @return
	 */
	public String getArgument() 
	{
		return this.argument;
	}
	public void setEnable(Long enable) 
	{
		this.enable = enable;
	}
	/**
	 * 返回 是否禁用
	 * @return
	 */
	public Long getEnable() 
	{
		return this.enable;
	}

   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof AliasScript)) 
		{
			return false;
		}
		AliasScript rhs = (AliasScript) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.aliasName, rhs.aliasName)
		.append(this.aliasDesc, rhs.aliasDesc)
		.append(this.className, rhs.className)
		.append(this.classInsName, rhs.classInsName)
		.append(this.methodName, rhs.methodName)
		.append(this.methodDesc, rhs.methodDesc)
		.append(this.returnType, rhs.returnType)
		.append(this.argument, rhs.argument)
		.append(this.enable, rhs.enable)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.aliasName) 
		.append(this.aliasDesc) 
		.append(this.className) 
		.append(this.classInsName) 
		.append(this.methodName) 
		.append(this.methodDesc) 
		.append(this.returnType) 
		.append(this.argument) 
		.append(this.enable) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("aliasName", this.aliasName) 
		.append("aliasDesc", this.aliasDesc) 
		.append("className", this.className) 
		.append("classInsName", this.classInsName) 
		.append("methodName", this.methodName) 
		.append("methodDesc", this.methodDesc) 
		.append("returnType", this.returnType) 
		.append("argument", this.argument) 
		.append("enable", this.enable) 
		.toString();
	}
   
  

}