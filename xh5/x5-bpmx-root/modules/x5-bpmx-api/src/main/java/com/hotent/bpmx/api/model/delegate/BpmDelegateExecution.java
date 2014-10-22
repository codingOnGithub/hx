package com.hotent.bpmx.api.model.delegate;


/**
 * activiti 的DelegateExecution类的代理类。
 * <pre> 
 * 描述：activiti 的DelegateExecution类的代理类。
 * 构建组：x5-bpmx-native-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-12-17-下午6:37:29
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public interface BpmDelegateExecution extends BpmVariable {

	String getId();

	String getProcessInstanceId();

	String getEventName();

	String getProcessBusinessKey();

	String getProcessDefinitionId();

	String getParentId();

	String getCurrentActivityId();

	String getCurrentActivityName();

	//String updateProcessBusinessKey(String bzKey);

}
