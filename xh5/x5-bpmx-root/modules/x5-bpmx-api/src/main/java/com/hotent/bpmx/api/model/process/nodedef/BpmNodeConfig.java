package com.hotent.bpmx.api.model.process.nodedef;

/**
 * 
 * 描述：流程定义节点的配置
 * 构建组：x5-bpmx-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-11-14-下午5:44:52
 * 版权：广州宏天软件有限公司版权所有
 */
public interface BpmNodeConfig {
	public static final class NODE_TYPE{
		public static final String USER = "user";
		public static final String SCRIPT = "script";
		public static final String MSG = "msg";
		public static final String WEB_SERVICE = "ws";
	}
	/**
	 * 获取节点配置ID
	 * @return
	 */
	public String getConfigId();
	/**
	 * 节点ID
	 * @return
	 */
	public String getNodeId();
	/**
	 * 节点的名称（快照）
	 * @return
	 */
	public String getNodeName();	
	/**
	 * 获取流程定义ID
	 * @return
	 */
	public String getProcDefId();
	/**
	 * 获取节点的排序
	 */
	public Integer getSn();	
	/**
	 * 返回节点的类型
	 * 如 BpmNodeConfig.NODE_TYPE_NORMAL 
	 * 或 BpmNodeConfig.NODE_TYPE_FORK 或 BpmNodeConfig.NODE_TYPE_JOIN
	 * @return
	 */
	public String getNodeType();

}
