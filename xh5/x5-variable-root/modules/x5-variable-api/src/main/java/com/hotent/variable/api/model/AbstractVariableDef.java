package com.hotent.variable.api.model;

import com.hotent.base.api.BaseModel;
/**
 * 描述:变量定义抽象类
 * 构建组：x5-variable-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-11-15-下午2:30:27
 * 版权：广州宏天软件有限公司版权所有
 */
public interface AbstractVariableDef extends BaseModel{
	public static final String STATUS_INIT="init";
	public static final String STATUS_TEST="test";
	public static final String STATUS_ENABLED="enabled";
	public static final String STATUS_DISABLED="disabled";
	
	/**
	 * ID
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getId();
	/**
	 * 变量名
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getName();
	/**
	 * 变量类型
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getType();
	/**
	 * 变量描述
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getDescription();
	/**
	 * 变量版本
	 * @return 
	 * Integer
	 * @exception 
	 * @since  1.0.0
	 */
	public String getVersion();
	/**
	 * 变量状态
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getStatus();
}
