package com.hotent.bpmx.api.plugin.org.handler;

import java.util.List;

import com.hotent.bpmx.api.model.identity.BpmIdentity;
import com.hotent.bpmx.api.plugin.org.def.BpmIdentityLinkDef;

/**
 * 
 * <pre> 
 * 描述：节点人员计算处理器
 * 构建组：x5-bpmx-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-2-23-下午2:31:50
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public interface BpmIdentityLinkHandler {
	/**
	 * 返回节点与人员关联定义
	 * @param linkDef
	 * @return 
	 * List<BpmIdentity>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<BpmIdentity> getBpmIdentites(BpmIdentityLinkDef linkDef);
}
