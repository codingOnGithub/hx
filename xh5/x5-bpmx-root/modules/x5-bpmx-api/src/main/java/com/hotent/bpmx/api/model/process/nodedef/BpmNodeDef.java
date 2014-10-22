package com.hotent.bpmx.api.model.process.nodedef;

import java.util.List;
import java.util.Map;

import com.hotent.bpmx.api.plugin.process.def.BpmExecutionPluginDef;
import com.hotent.bpmx.api.plugin.process.def.BpmTaskPluginDef;

/**
 * 描述：流程任务节点
 * 构建组：x5-bpmx-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-11-7-下午2:57:41
 * 版权：广州宏天软件有限公司版权所有
 */
public interface BpmNodeDef {
	/**
	 * 取得节点的ID
	 * @return
	 */
	public String getNodeId();	
	/**
	 * 取得节点的名称
	 * @return
	 */
	public String getName();
	/** 
	 * 取得节点的类型
	 * @return
	 */
	public String getType();
	/**
	 * 流程定义ID
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getProcDefId();		
	/**
	 * 取得当前节点的所有入口节点集合
	 * @return
	 */
	public List<BpmNodeDef> getIncomeNodes();
	/**
	 * 取得当前节点的所有出口节点集合
	 * @return
	 */
	public List<BpmNodeDef> getOutcomeNodes();
	/**
	 * 取得节点的事件插件
	 * @return 
	 * List<BpmNodePlugin>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<BpmExecutionPluginDef> getBpmExecutionPluginDefs();
	
	/**
	 * 取得节点的事件插件
	 * @return 
	 * List<BpmNodePlugin>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<BpmTaskPluginDef> getBpmTaskPluginDefs();	
	/**
	 * 获得子流程的所有节点，List形式
	 * @return 
	 * List<BpmNodeDef>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<BpmNodeDef> getSubNodes();
	/**
	 * 获得子流程的所有节点，Map形式。
	 * @return 
	 * Map<String,BpmNodeDef> key：nodeId，value：BpmNodeDef
	 * @exception 
	 * @since  1.0.0
	 */
	public Map<String,BpmNodeDef> getSubNodeMap();
	/**
	 * 获得属性值集合
	 * @return 
	 * List<String>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<String> getAttributeList();
}
