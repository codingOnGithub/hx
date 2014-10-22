package com.hotent.bpmx.api.cmd;

import java.util.List;

import com.hotent.bpmx.api.model.identity.BpmIdentity;

/**
 * 
 * 描述：任务转办命令
 * 构建组：x5-bpmx-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-11-8-下午3:21:27
 * 版权：广州宏天软件有限公司版权所有
 */
public interface TaskTurnCmd extends AbstractTaskActionCmd{
	/**
	 * 转办原因
	 * @return 
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getReason();
	
	/**
	 * 任务转发给其他用户或组标识
	 * @return 
	 * List<BpmIdentity>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<BpmIdentity> getTurnToIdentites();
}
