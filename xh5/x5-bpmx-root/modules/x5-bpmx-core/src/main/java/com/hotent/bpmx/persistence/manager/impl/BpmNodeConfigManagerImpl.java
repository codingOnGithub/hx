package com.hotent.bpmx.persistence.manager.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.base.db.api.Dao;
import com.hotent.base.db.api.IdGenerator;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.bpmx.core.defxml.DefXmlUtil;
import com.hotent.bpmx.core.defxml.entity.Task;
import com.hotent.bpmx.persistence.dao.BpmNodeConfigDao;
import com.hotent.bpmx.persistence.manager.BpmMsgRuleManager;
import com.hotent.bpmx.persistence.manager.BpmNodeConfigManager;
import com.hotent.bpmx.persistence.manager.BpmNodeScriptManager;
import com.hotent.bpmx.persistence.manager.BpmUserNodeManager;
import com.hotent.bpmx.persistence.manager.BpmWsNodeManager;
import com.hotent.bpmx.persistence.model.DefaultBpmNodeConfig;
import com.hotent.bpmx.persistence.util.BpmNodeConfigUtil;

@Service("defaultBpmNodeConfigManager")
public class BpmNodeConfigManagerImpl extends AbstractManagerImpl<String, DefaultBpmNodeConfig> implements BpmNodeConfigManager{
	@Resource
	BpmNodeConfigDao defaultBpmNodeConfigDao;
	
	@Resource
	BpmMsgRuleManager bpmMsgRuleManager;

	@Resource
	BpmUserNodeManager bpmUserNodeManager;

	@Resource
	BpmNodeScriptManager bpmNodeScriptManager;
	
	@Resource
	BpmWsNodeManager bpmWsNodeManager;
	
	@Resource
	IdGenerator idGenerator;
	
	@Override
	protected Dao<String, DefaultBpmNodeConfig> getDao() {
		return defaultBpmNodeConfigDao;
	}
	
	@Override
	public void create(DefaultBpmNodeConfig entity) {		
		super.create(entity);
	}

	@Override
	public void remove(String entityId) {
		//删除消息
		bpmMsgRuleManager.removeByConfigId(entityId);		
		
		//删除用户节点
		bpmUserNodeManager.remove(entityId);
		
		//删除脚本节点
		bpmNodeScriptManager.removeByConfigId(entityId);
		
		//将BPM_WS_NODE和BPM_WS_NODE_PARAMS两表级联删除
		bpmWsNodeManager.remove(entityId);	
		
		super.remove(entityId);
	}

	@Override
	public void removeByIds(String... ids) {
		for(String id:ids){
			remove(id);
		}		
	}

	/* (non-Javadoc)
	 * @see com.hotent.bpmx.persistence.manager.DefaultBpmNodeConfigManager#queryBpmNodeConfigs(java.lang.String)
	 */
	@Override
	public List<DefaultBpmNodeConfig> queryBpmNodeConfigs(String procDefId) {
		return defaultBpmNodeConfigDao.queryBpmNodeConfigs(procDefId);
	}

	/* (non-Javadoc)
	 * @see com.hotent.bpmx.persistence.manager.BpmNodeConfigManager#createNodeConfigs(java.lang.String, java.lang.String)
	 */
	@Override
	public void createNodeConfigs(String bpmnXml, String defId) {
		// 读取BpmnXml内容，获得Tasks集合。
		List<Task> tasks = DefXmlUtil.getTasks(bpmnXml);

		// 遍历该tasks，生成BpmNodeConfig
		for (int i = 0; i < tasks.size(); i++) {
			Integer sn = i + 1;
			DefaultBpmNodeConfig bpmNodeConfig = BpmNodeConfigUtil
					.buildDefaultBpmNodeConfig(tasks.get(i), defId, sn);
			bpmNodeConfig.setConfigId(idGenerator.getSuid());
			create(bpmNodeConfig);
		}
	}

	/* (non-Javadoc)
	 * @see com.hotent.bpmx.persistence.manager.BpmNodeConfigManager#modifyNodeConfigs(java.lang.String, java.lang.String)
	 */
	@Override
	public void modifyNodeConfigs(String bpmnXml, String defId) {
		//读取流程节点配置集合
		List<DefaultBpmNodeConfig> bpmNodeConfigsInDB = queryBpmNodeConfigs(defId);
		
		// 读取BpmnXml内容，获得Tasks集合。
		List<Task> tasks = DefXmlUtil.getTasks(bpmnXml);
		
		//在数据库中的依然有效的节点ID集合（删除在新流程设计中不存在的旧节点配置使用）
		List<String> nodeIdsAvailable = new ArrayList<String>();
		
		// 遍历该tasks，更新存在的BpmNodeConfig
		for (int i = 0; i < tasks.size(); i++) {
			Integer sn = i + 1;
			Task task = tasks.get(i);
			String nodeId = task.getId();
			
			//标识：当前Task是否是新的
			boolean isNewTask = true;
			
			//更新存在的节点配置
			for(DefaultBpmNodeConfig bpmNodeConfig:bpmNodeConfigsInDB){
				if(bpmNodeConfig.getNodeId().equals(nodeId)){
					String nodeName = task.getName();
					bpmNodeConfig.setNodeName(nodeName);
					bpmNodeConfig.setSn(sn);
					update(bpmNodeConfig);
					nodeIdsAvailable.add(nodeId);
					isNewTask = false;
					break;
				}
			}
			
			//新增节点配置
			if(isNewTask){
				DefaultBpmNodeConfig newBpmNodeConfig = BpmNodeConfigUtil
						.buildDefaultBpmNodeConfig(task, defId, sn);
				newBpmNodeConfig.setConfigId(idGenerator.getSuid());
				create(newBpmNodeConfig);
			}
		}		
		
		//删除节点配置（在新流程设计中不存在）
		for(DefaultBpmNodeConfig bpmNodeConfig:bpmNodeConfigsInDB){
			if(nodeIdsAvailable.contains(bpmNodeConfig.getNodeId())){
				continue;				
			}else{
				//该节点在新的流程设计中不存在，删除。
				remove(bpmNodeConfig.getConfigId());
			}
		}		
	}
	
	public DefaultBpmNodeConfig cloneNodeConfig(DefaultBpmNodeConfig oldBpmNodeConfig,String defId){
		DefaultBpmNodeConfig newBpmNodeConfig = (DefaultBpmNodeConfig)oldBpmNodeConfig.clone();
		newBpmNodeConfig.setConfigId(idGenerator.getSuid());
		newBpmNodeConfig.setProcDefId(defId); //关联新的流程定义
		create(newBpmNodeConfig);			
		return newBpmNodeConfig;
	}

	@Override
	public DefaultBpmNodeConfig getByDefIdAndNodeId(String procDefId,
			String nodeId) {
		return defaultBpmNodeConfigDao.getByDefIdAndNodeId(procDefId, nodeId);	
	}
	
	
}
