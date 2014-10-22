package com.hotent.bpmx.api.cmd;

/**
 * 
 * 描述：任务追回命令接口
 * 构建组：x5-bpmx-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-11-11-下午6:33:56
 * 版权：广州宏天软件有限公司版权所有
 */
public interface TaskRecoverCmd {
	/**
	 * 返回流程实例Id
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getInstanceId();
	/**
	 * 返回追回至的某节点的ID
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getNodeId();
}
