package com.hotent.variable.api.service;

import com.hotent.variable.api.model.AbstractVariableDef;

/**
 * 
 * 描述：变量定义服务接口
 * 构建组：x5-variable-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-11-15-下午2:35:16
 * 版权：广州宏天软件有限公司版权所有
 */
public interface VariableDefService<T extends AbstractVariableDef> {
	/**
	 * 通过变量定义ID获取变量定义实体
	 * @param varDefId
	 * @return 
	 * T
	 * @exception 
	 * @since  1.0.0
	 */
	public T getById(String varDefId);
	/**
	 * 通过变量名称及版本获取流程定义实体
	 * @param name
	 * @param version
	 * @return 
	 * T
	 * @exception 
	 * @since  1.0.0
	 */
	public T getByNameVersion(String name,Integer version);
	/**
	 * 创建流程变量实体
	 * @param varDef 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void create(T varDef);
	/**
	 * 更新变量实体
	 * @param varDef 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void update(T varDef);
	/**
	 * 按变量定义ID删除变量定义实体
	 * @param varDefId 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void deleteById(String varDefId);
	/**
	 * 按变量名称删除变量定义实体
	 * @param varName 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void deleteByName(String varName);
	/**
	 * 按变量名及版本删除变量定义实体
	 * @param varName
	 * @param version 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void deleteByNameVersion(String varName,String version);
}
