package com.hotent.bpmx.api.model.form;

import com.hotent.base.api.BaseModel;
import com.hotent.base.api.TreeType;

/**
 * 	
 * 描述：流程任务表单
 * 构建组：x5-bpmx-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-11-7-下午2:42:49
 * 版权：广州宏天软件有限公司版权所有
 */
public interface BpmForm extends BaseModel{
	/**
	 * 表单ID
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getFormId();
	/**
	 * 表单名称
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getName();
	/**
	 * 表单业务主键
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getFormKey();
	/**
	 * 表单的描述
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getDesc();
	/**
	 * 表单的设计HTML
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getFormHtml();
	/**
	 * 绑定的数据源业务主键
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getDsKey();	
	/**
	 * 表单的状态
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getStatus();
	/**
	 * 表单的类型，如URL表单、在线HTML表单、Lotus表单
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getFormType();
	/**
	 * 表单的分类管理
	 * @return TreeType
	 * @exception 
	 * @since  1.0.0
	 */
	public TreeType getType();
	/**
	 * 表单的版本
	 * @return Integer
	 * @exception 
	 * @since  1.0.0
	 */
	public Integer getVersion();	
}
