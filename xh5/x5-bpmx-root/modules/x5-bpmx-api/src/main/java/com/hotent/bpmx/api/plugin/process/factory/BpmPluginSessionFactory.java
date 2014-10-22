/**
 * 描述：TODO
 * 包名：com.hotent.bpmx.api.plugin.process.factory
 * 文件名：BpmPluginSessionFactory.java
 * 作者：win-mailto:chensx@jee-soft.cn
 * 日期2014-2-23-下午8:55:51
 *  2014广州宏天软件有限公司版权所有
 * 
 */
package com.hotent.bpmx.api.plugin.process.factory;

import com.hotent.bpmx.api.model.delegate.BpmDelegateExecution;
import com.hotent.bpmx.api.model.delegate.BpmDelegateTask;
import com.hotent.bpmx.api.plugin.process.session.BpmExecutionPluginSession;
import com.hotent.bpmx.api.plugin.process.session.BpmTaskPluginSession;

/**
 * <pre> 
 * 描述：构造插件执行所需的会话数据的工厂
 * 构建组：x5-bpmx-api
 * 作者：Winston Yan
 * 邮箱：yancm@jee-soft.cn
 * 日期：2014-2-23-下午8:55:51
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public interface BpmPluginSessionFactory {
	/**
	 * 构造任务插件服务
	 * @param bpmDelegateTask
	 * @return 
	 * BpmTaskPluginSession
	 * @exception 
	 * @since  1.0.0
	 */
	public BpmTaskPluginSession buildBpmTaskPluginSession(BpmDelegateTask bpmDelegateTask);
	/**
	 * 构造执行插件服务
	 * @param bpmDelegateExecution
	 * @return 
	 * BpmExecutionPluginSession
	 * @exception 
	 * @since  1.0.0
	 */
	public BpmExecutionPluginSession buildBpmExecutionPluginSession(BpmDelegateExecution bpmDelegateExecution);
}
