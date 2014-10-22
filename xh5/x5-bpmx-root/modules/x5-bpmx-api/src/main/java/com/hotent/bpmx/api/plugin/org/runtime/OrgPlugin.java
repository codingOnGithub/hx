/**
 * 描述：TODO
 * 包名：com.hotent.bpmx.api.plugin.org
 * 文件名：OrgPlugin.java
 * 作者：win-mailto:chensx@jee-soft.cn
 * 日期2014-2-23-下午10:07:57
 *  2014广州宏天软件有限公司版权所有
 * 
 */
package com.hotent.bpmx.api.plugin.org.runtime;

import java.util.List;

import com.hotent.bpmx.api.context.model.BpmProcessInstanceContext;
import com.hotent.bpmx.api.model.identity.BpmIdentity;

/**
 * <pre> 
 * 描述：人员查询插件运行时
 * 构建组：x5-bpmx-api
 * 作者：Winston Yan
 * 邮箱：yancm@jee-soft.cn
 * 日期：2014-2-23-下午10:07:57
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public interface OrgPlugin {
	/**
	 * 根据传入的会话数据查询人员集合
	 * @param orgPluginSession
	 * @return 
	 * List<BpmIdentity>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<BpmIdentity> queryBpmIdentitys(BpmProcessInstanceContext bpmProcessInstanceContext);
}
