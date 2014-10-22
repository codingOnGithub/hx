/**
 * 描述：TODO
 * 包名：com.hotent.bpmx.api.plugin.org.factory
 * 文件名：OrgPluginFactory.java
 * 作者：win-mailto:chensx@jee-soft.cn
 * 日期2014-2-24-上午9:03:10
 *  2014广州宏天软件有限公司版权所有
 * 
 */
package com.hotent.bpmx.api.plugin.org.factory;

import java.util.List;

import com.hotent.bpmx.api.plugin.org.def.OrgPluginDef;
import com.hotent.bpmx.api.plugin.org.runtime.OrgPlugin;

/**
 * <pre> 
 * 描述：TODO
 * 构建组：x5-bpmx-api
 * 作者：Winston Yan
 * 邮箱：yancm@jee-soft.cn
 * 日期：2014-2-24-上午9:03:10
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public interface OrgPluginFactory {
	/**
	 * 根据插件定义获得插件运行时
	 * @param orgPluginDef
	 * @return 
	 * OrgPlugin
	 * @exception 
	 * @since  1.0.0
	 */
	public OrgPlugin buildOrgPlugin(OrgPluginDef orgPluginDef);
	
	/**
	 * 
	 * 根据插件定义集合获得插件运行时集合
	 * @param orgPluginDefs
	 * @return 
	 * OrgPlugin
	 * @exception 
	 * @since  1.0.0
	 */
	public List<OrgPlugin> buildOrgPlugins(List<OrgPluginDef> orgPluginDefs);
}
