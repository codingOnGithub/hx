package com.hotent.bpmx.plugin.process.def;

import com.hotent.bpmx.api.plugin.process.def.BpmPluginDef;

/**
 * 
 * <pre> 
 * 描述：抽象的插件定义基类
 * 构建组：x5-bpmx-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-2-22-下午1:53:57
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public abstract class AbstractBpmPluginDef implements BpmPluginDef{
	/**
	 * 插件名称
	 */
	protected String pluginName;
	/**
	 * 命名空间
	 */
	protected String namespace;
	/**
	 * 配置属性JSON串
	 */
	protected String jsonConfig;
	/**
	 * 流程定义ID
	 */
	protected String processDefinitionId;
	
	
	public String getPluginName() {
		return pluginName;
	}
	public void setPluginName(String pluginName) {
		this.pluginName = pluginName;
	}
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	public String getJsonConfig() {
		return jsonConfig;
	}
	public void setJsonConfig(String jsonConfig) {
		this.jsonConfig = jsonConfig;
	}
	public String getProcessDefinitionId() {
		return processDefinitionId;
	}
	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}
	
	
}
