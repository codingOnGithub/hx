package com.hotent.bpmx.api.plugin.process.runtime;

import com.hotent.bpmx.api.model.delegate.BpmDelegateTask;
import com.hotent.bpmx.api.plugin.process.session.BpmTaskPluginSession;

/**
 * <pre> 
 * 描述：任务类插件运行时
 * 构建组：x5-bpmx-native-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-12-17-下午6:39:54
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public interface BpmTaskPlugin {
	/**
	 * 
	 * @param delegateTask 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void execute(BpmTaskPluginSession bpmTaskPluginSession);
}
