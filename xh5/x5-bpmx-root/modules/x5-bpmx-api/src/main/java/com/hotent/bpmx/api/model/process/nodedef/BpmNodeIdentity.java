package com.hotent.bpmx.api.model.process.nodedef;

import java.util.List;

import com.hotent.bpmx.api.model.identity.BpmIdentity;

/**
 * 
 * 描述：任务节点与用户标识映射类
 * 构建组：x5-bpmx-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-11-15-上午10:44:27
 * 版权：广州宏天软件有限公司版权所有
 */
public interface BpmNodeIdentity {
	/**
	 * 节点ID
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getNodeId();
	/**
	 * 节点标识列表
	 * @return 
	 * List<BpmIdentity>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<BpmIdentity> getBpmIdentities();
}
