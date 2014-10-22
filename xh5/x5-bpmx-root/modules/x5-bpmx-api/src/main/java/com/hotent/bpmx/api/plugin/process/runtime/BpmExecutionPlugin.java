package com.hotent.bpmx.api.plugin.process.runtime;

import com.hotent.bpmx.api.model.delegate.BpmDelegateExecution;
import com.hotent.bpmx.api.plugin.process.session.BpmExecutionPluginSession;

/**
 * 
 * <pre> 
 * 描述：执行类插件运行时
 * 构建组：x5-bpmx-native-api
 * 作者：ray
 * 邮箱:zhangyg@jee-soft.cn
 * 日期:2013-12-18-下午3:13:55
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public interface BpmExecutionPlugin {
	
	public void execute(BpmExecutionPluginSession bpmExecutionPluginSession);
}
