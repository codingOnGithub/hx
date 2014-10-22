package com.hotent.bpmx.api.model.form;

import com.hotent.base.api.BaseModel;

/**
 * 
 * 描述：流程运行实例中的表单实例
 * 构建组：x5-bpmx-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-11-7-下午4:48:01
 * 版权：广州宏天软件有限公司版权所有
 */
public interface BpmFormInstance extends BaseModel{
	/**
	 * 
	 * 运行ID
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getId();
	/**
	 * 表单ID
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getFormId();
	/**
	 * 表单Key
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getFormKey();
	/**
	 * 表单的HTML
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getFormHtml();
	/**
	 * 返回当前表单的运行数据,如Json或XML格式的数据
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getFormData();	
	/**
	 * 表单的数据源别名
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getDsKey();
	/**
	 * 关联数据业务主键
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getBizKey();	
	/**
	 * 流程实例ID
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getProcInstId();
	/**
	 * 流程实例标题
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getProcInstSubject();	
	/**
	 * 流程定义ID
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getProcDefId();	
	/**
	 * 表单的版本
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getVersion();
	
}
