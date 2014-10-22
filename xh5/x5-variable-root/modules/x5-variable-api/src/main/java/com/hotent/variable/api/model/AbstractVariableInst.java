package com.hotent.variable.api.model;

import com.hotent.base.api.BaseModel;

/**
 * <pre> 
 * 描述：变量实例实体接口
 * 构建组：x5-variable-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-11-18-上午11:13:10
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public interface AbstractVariableInst extends BaseModel{
	/**
	 * ID
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getId();
	/**
	 * 变量定义ID
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getVarDefId();
	/**
	 * 变量定义的版本号
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getVarVersion();
	/**
	 * 变量类型
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getVarType();
	/**
	 * 变量名称
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getVarName();
	
	/**
	 * 变量值
	 * @return 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
	public Object getValue();

}
