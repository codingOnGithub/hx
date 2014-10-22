package com.hotent.bpmx.api.model.process.nodedef;

public interface BpmUserNode extends BpmNodeConfig {

	public static final class BIZ_TYPE{
		/**
		 * 普通任务节点
		 */
		public static final String NORMAL = "normal";
		/**
		 * 分发任务节点
		 */
		public static final String FORK = "fork";
		/**
		 * 汇集任务节点
		 */
		public static final String JOIN = "join";
	}	
	
	
	public String getBizType();
	/**
	 * 是否允许回退
	 * @return
	 */
	public boolean isAllowBack();
	/**
	 * 是否允许回退至发起人
	 * @return
	 */
	public boolean isAllowBackToStart();
	/**
	 * 是否隐藏意见输入框
	 * @return
	 */
	public boolean isHideOpinionField();
	/**
	 * 是否隐藏执行路径
	 * @return
	 */
	public boolean isHideExecPath();
}
