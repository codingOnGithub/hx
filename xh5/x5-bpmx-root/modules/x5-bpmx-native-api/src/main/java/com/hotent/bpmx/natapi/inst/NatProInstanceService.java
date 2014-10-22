package com.hotent.bpmx.natapi.inst;

import java.util.Map;

/**
 * 
 * <pre> 
 * 描述：流程实例服务
 * 构建组：x5-bpmx-native-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-12-20-下午8:34:36
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public interface NatProInstanceService {
	/**
	 * 启动流程实例并返回流程实例ID
	 * @param processDefinitionId 流程定义ID
	 * @param businessKey 流程业务主键ID
	 * @param variables 变量
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String startProcessInstance(String processDefinitionId,String businessKey,Map<String,Object> variables);
}
