package com.hotent.bpmx.api.model.process.def;

import java.util.List;

import com.hotent.bpmx.api.model.process.nodedef.BpmNodeDef;
import com.hotent.bpmx.api.plugin.process.def.BpmExecutionPluginDef;


/**
 * <pre>
 * 描述：BPMN流程定义接口
 * 构建组：x5-bpmx-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-2-13-下午5:39:52
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public interface BpmProcessDef {
	/**
	 * 流程名称
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getName(); 
	/**
	 * 流程定义ID
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getProcessDefinitionId() ;
	/**
	 * 流程节点定义
	 * @return 
	 * List<BpmNodeDef>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<BpmNodeDef> getBpmnNodeDefs();
	/**
	 * 流程定义插件列表
	 * @return 
	 * List<BpmProcessPlugin>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<BpmExecutionPluginDef> getBpmExecutionPluginDefs();

}
