package com.hotent.bpmx.api.cmd;
import java.util.Map;

/**
 * 
 * 描述：流程实例对象命令对象
 * 构建组：x5-bpmx-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-11-15-上午10:59:01
 * 版权：广州宏天软件有限公司版权所有
 */
public interface ProcessInstCmd {
	/**
	 * 取得BPMN流程定义ID
	 * getBpmnDefId(),getProcDefId(),getFlowKey()三者只需要设置一值
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	String getBpmnDefId(); 
	/**
	 * 取得X5流程定义ID
	 * getBpmnDefId(),getProcDefId(),getFlowKey()三者只需要设置一值
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	String getProcDefId();
	/**
	 * 获取流程定义标识键
	 * getBpmnDefId(),getProcDefId(),getFlowKey()三者只需要设置一值
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	String getFlowKey();
	
	/**
	 * 变量
	 * @return 
	 * Map<String,Object>
	 * @exception 
	 * @since  1.0.0
	 */
	Map<String,Object> getVariables();
	/**
	 * 获取发起人ID
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	String getStartUserId();
	/**
	 * 获取实例标题
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	String getSubject();
	/**
	 * 获取业务主键
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	String getBusinessKey();
	
	/**
	 * 获取业务对象的JSON值
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	String getBusinessData();
	
	
}
