package com.hotent.bpmx.persistence.manager;

import java.util.List;

import com.hotent.base.manager.api.Manager;
import com.hotent.bpmx.persistence.model.DefaultBpmNodeConfig;

public interface BpmNodeConfigManager extends Manager<String, DefaultBpmNodeConfig>{

	/**
	 * 根据流程定义ID查询该流程所有节点配置集合
	 * 集合内的DefaultBpmNodeConfig不包含BpmNodeConfigData数据，也不包含子表的数据。
	 * @param procDefId
	 * @return 
	 * List<DefaultBpmNodeConfig>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<DefaultBpmNodeConfig> queryBpmNodeConfigs(String procDefId);
	
	/**
	 * 创建新的节点配置集合
	 * @param bpmnXml
	 * @param defId 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void createNodeConfigs(String bpmnXml,String defId);
	
	/**
	 * 根据传入的bpmnXml，调整该流程已存在的节点配置
	 * @param bpmnXml
	 * @param defId 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void modifyNodeConfigs(String bpmnXml,String defId);
	
	public DefaultBpmNodeConfig cloneNodeConfig(DefaultBpmNodeConfig oldBpmNodeConfig,String defId);
	
	public DefaultBpmNodeConfig getByDefIdAndNodeId(String procDefId,String nodeId);
}
