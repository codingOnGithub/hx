package com.hotent.bpmx.api.plugin.org.context;

import java.util.Map;

import com.hotent.bpmx.api.plugin.org.handler.BpmIdentityLinkHandler;
/**
 * 
 * <pre> 
 * 描述：流程节点与人员关联上下文
 * 构建组：x5-bpmx-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-2-23-下午2:46:07
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public interface BpmIdentityLinkContext {
	/**
	 * 
	 * @return 
	 * Map<String,BpmIdentityLinkHandler>
	 * @exception 
	 * @since  1.0.0
	 */
	public Map<String,BpmIdentityLinkHandler> getBpmIdentityHandler();
}
