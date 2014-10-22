package com.hotent.bpmx.api.plugin.process.factory;

import java.util.List;

import com.hotent.bpmx.api.constant.EventType;
import com.hotent.bpmx.api.plugin.process.def.BpmExecutionPluginDef;
import com.hotent.bpmx.api.plugin.process.def.BpmTaskPluginDef;
import com.hotent.bpmx.api.plugin.process.runtime.BpmExecutionPlugin;
import com.hotent.bpmx.api.plugin.process.runtime.BpmTaskPlugin;
/**
 * 
 * <pre> 
 * 描述：插件工厂
 * 构建组：x5-bpmx-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-2-19-下午5:15:44
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public interface BpmPluginFactory {
	/**
	 * 返回流程的插件实例
	 * @param pluginDefs
	 * @param eventType
	 * @return 
	 * List<AbstractBpmPlugin>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<BpmExecutionPlugin> buildExecutionPlugins(List<BpmExecutionPluginDef> pluginDefs,EventType eventType);
	
	/**
	 * 获取节点插件实例
	 * @param pluginDefs
	 * @param eventType
	 * @return 
	 * List<BpmPlugin>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<BpmTaskPlugin> buildTaskPlugins(List<BpmTaskPluginDef> pluginDefs,EventType eventType);	

}
