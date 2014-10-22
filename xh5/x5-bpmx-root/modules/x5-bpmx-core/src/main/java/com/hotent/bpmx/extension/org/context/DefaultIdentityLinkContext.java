package com.hotent.bpmx.extension.org.context;

import java.util.HashMap;
import java.util.Map;

import com.hotent.bpmx.api.plugin.org.context.BpmIdentityLinkContext;
import com.hotent.bpmx.api.plugin.org.handler.BpmIdentityLinkHandler;
/**
 * 
 * <pre> 
 * 描述：人员运算规则
 * 构建组：x5-bpmx-core
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-2-23-下午6:59:19
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class DefaultIdentityLinkContext implements BpmIdentityLinkContext{

	private Map<String,BpmIdentityLinkHandler> bpmIdentityHandlers=new HashMap<String, BpmIdentityLinkHandler>();
	
	@Override
	public Map<String, BpmIdentityLinkHandler> getBpmIdentityHandler() {
		return bpmIdentityHandlers; 
	}

	public void setBpmIdentityHandlers(Map<String, BpmIdentityLinkHandler> identityHandlers) {
		this.bpmIdentityHandlers = identityHandlers;
	}

}
