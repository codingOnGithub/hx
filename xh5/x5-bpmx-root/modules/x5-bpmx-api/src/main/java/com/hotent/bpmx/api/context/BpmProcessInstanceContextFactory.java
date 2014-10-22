/**
 * 描述：TODO
 * 包名：com.hotent.bpmx.api.context
 * 文件名：BpmTaskContextFactory.java
 * 作者：win-mailto:chensx@jee-soft.cn
 * 日期2014-2-24-上午10:04:12
 *  2014广州宏天软件有限公司版权所有
 * 
 */
package com.hotent.bpmx.api.context;

import com.hotent.bpmx.api.context.model.BpmProcessInstanceContext;

/**
 * <pre> 
 * 描述：流程任务实例上下文数据获取工厂（即关键性数据）
 * 构建组：x5-bpmx-api
 * 作者：Winston Yan
 * 邮箱：yancm@jee-soft.cn
 * 日期：2014-2-24-上午10:04:12
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public interface BpmProcessInstanceContextFactory {
	/**
	 * 获得流程实例上下文
	 * @param instanceId
	 * @return 
	 * BpmProcessInstanceContext
	 * @exception 
	 * @since  1.0.0
	 */
	public BpmProcessInstanceContext getBpmProcessInstance(String instanceId); 
}
