package com.hotent.bpmx.api.service;

import java.util.Map;

/**
 * 
 * <pre> 
 * 描述：流程变量实例服务接口
 * 构建组：x5-bpmx-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-12-16-下午5:00:13
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public interface BpmVariableService {
	/**
	 * 通过流程实例ID获取流程变量列表
	 * @param processInstanceId
	 * @return 
	 * Map<String,Object>
	 * @exception 
	 * @since  1.0.0
	 */
	public Map<String,Object> getVarsByProcessInstanceId(String processInstanceId);
	/**
	 * 通过流程任务ID获取流程变量列表
	 * @param taskId 
	 * @return 
	 * Map<String,Object>
	 * @exception 
	 * @since  1.0.0
	 */
	public Map<String,Object> getVarsByTaskId(String taskId);
	/**
	 * 通过流程实例ID及变量名获取变量值
	 * @param processInstanceId 流程实例ID
	 * @param varName 变量名称
	 * @return 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
	public Object getVarByProcessInstanceIdVarName(String processInstanceId,String varName);
	
	/**
	 * 删除某一流程实例中的某变量
	 * @param processInstanceId 流程实例ID 
	 * @param varName 变量名称
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void removeVarByProcessInstanceIdVarName(String processInstanceId,String varName);
	/**
	 * 设置任务或活动执行范围内的变量
	 * @param executionId
	 * @param varName
	 * @param varValue 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void setExecutionVar(String executionId,String varName,Object varValue);
	/**
	 * 设置流程实例范围内的变量值
	 * @param processInstanceId
	 * @param varName
	 * @param varValue 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void setProcesInstanceVar(String processInstanceId,String varName,Object varValue);
	
}
