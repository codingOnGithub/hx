package com.hotent.bpmx.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.hotent.base.api.query.FieldRelation;
import com.hotent.base.core.constants.Bool;
import com.hotent.base.db.api.Dao;
import com.hotent.base.db.api.IdGenerator;
import com.hotent.base.db.model.DefaultFieldLogic;
import com.hotent.base.db.model.DefaultQueryFilter;
import com.hotent.base.db.mybatis.domain.DefaultPage;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.bpmx.api.model.process.nodedef.BpmNodeConfig;
import com.hotent.bpmx.persistence.dao.BpmDefConfigDao;
import com.hotent.bpmx.persistence.dao.BpmDefDataDao;
import com.hotent.bpmx.persistence.dao.BpmDefinitionDao;
import com.hotent.bpmx.persistence.manager.BpmDefinitionManager;
import com.hotent.bpmx.persistence.manager.BpmMsgRuleManager;
import com.hotent.bpmx.persistence.manager.BpmNodeConfigManager;
import com.hotent.bpmx.persistence.manager.BpmNodeScriptManager;
import com.hotent.bpmx.persistence.manager.BpmUserNodeManager;
import com.hotent.bpmx.persistence.manager.BpmWsNodeManager;
import com.hotent.bpmx.persistence.model.BpmDefConfig;
import com.hotent.bpmx.persistence.model.BpmDefData;
import com.hotent.bpmx.persistence.model.BpmWsNode;
import com.hotent.bpmx.persistence.model.DefaultBpmDefinition;
import com.hotent.bpmx.persistence.model.DefaultBpmNodeConfig;
import com.hotent.bpmx.persistence.model.DefaultBpmUserNode;
import com.hotent.bpmx.persistence.model.query.BpmDefFieldSorts;
import com.hotent.bpmx.persistence.model.query.BpmDefQueryFields;

@Service("defaultBpmDefinitionManager")
public class BpmDefinitionManagerImpl extends AbstractManagerImpl<String, DefaultBpmDefinition> implements BpmDefinitionManager{
	@Resource
	BpmDefinitionDao bpmDefinitionDao;
	
	@Resource
	BpmDefConfigDao bpmDefConfigDao;
	
	@Resource
	BpmDefDataDao bpmDefDataDao;
	
	@Resource
	BpmNodeConfigManager bpmNodeConfigManager;
	
	@Resource
	BpmUserNodeManager bpmUserNodeManager;
	
	@Resource
	BpmMsgRuleManager bpmMsgRuleManager;
	
	@Resource
	BpmNodeScriptManager bpmNodeScriptManager;

	@Resource
	BpmWsNodeManager bpmWsNodeManager;
	
	@Resource
	IdGenerator idGenerator;
	
	@Override
	protected Dao<String, DefaultBpmDefinition> getDao() {
		return bpmDefinitionDao;
	}
	
	@Override
	public DefaultBpmDefinition getWithConfig(String entityId) {
		DefaultBpmDefinition defaultBpmDefinition = bpmDefinitionDao.get(entityId);
		BpmDefConfig bpmDefConfig = bpmDefConfigDao.get(entityId);
		defaultBpmDefinition.setBpmDefConfig(bpmDefConfig);
		return defaultBpmDefinition;
	}

	@Override
	public DefaultBpmDefinition getWithCascade(String entityId) {
		DefaultBpmDefinition defaultBpmDefinition = getWithConfig(entityId);
		BpmDefData bpmDefData = bpmDefDataDao.get(entityId);
		defaultBpmDefinition.setBpmDefData(bpmDefData);
		return defaultBpmDefinition;
	}

	@Override
	public void create(DefaultBpmDefinition entity) {
		super.create(entity);
		if(StringUtils.isEmpty(entity.getBpmDefConfig().getId())){
			entity.getBpmDefConfig().setId(entity.getDefId());			
		}
		bpmDefConfigDao.create(entity.getBpmDefConfig());
		if(StringUtils.isEmpty(entity.getBpmDefData().getId())){
			entity.getBpmDefData().setId(entity.getDefId());			
		}
		bpmDefDataDao.create(entity.getBpmDefData());
	}
	@Override
	public void update(DefaultBpmDefinition entity) {		
		super.update(entity);
		if(StringUtils.isNotEmpty(entity.getBpmDefConfig().getId())){
			bpmDefConfigDao.update(entity.getBpmDefConfig());
		}
		if(StringUtils.isNotEmpty(entity.getBpmDefData().getId())){
			bpmDefDataDao.update(entity.getBpmDefData());
		}
	}
	@Override
	public void remove(String entityId) {		
		bpmDefConfigDao.remove(entityId);
		bpmDefDataDao.remove(entityId);	
		
		super.remove(entityId);
	}

	@Override
	public void removeCascade(String entityId) {
		//TODO 删除流程历史
		//TODO 删除流程实例
		
		//调用bpmNodeConfigManager的接口，删除这个流程的所有nodeConfig
		bpmNodeConfigManager.remove(entityId);

		//TODO 删除流程定义授权
		//TODO 删除任务催办
		//TODO 删除流程人员计算规则
		//TODO 删除流程变量
		//TODO 删除流程表单设置（关联表）
		
		remove(entityId);
	}

	@Override
	public void updateMainDefId(String mainDefId, String defKey) {
		bpmDefinitionDao.updateMainDefId(mainDefId, defKey);
	}

	/* (non-Javadoc)
	 * @see com.hotent.bpmx.persistence.manager.BpmDefinitionManager#getByDefKey(java.lang.String)
	 */
	@Override
	public DefaultBpmDefinition getMainByDefKey(String defKey) {
		return bpmDefinitionDao.getMainByDefKey(defKey);
	}

	/* (non-Javadoc)
	 * @see com.hotent.bpmx.persistence.manager.BpmDefinitionManager#clone(com.hotent.bpmx.persistence.model.DefaultBpmDefinition)
	 */
	@Override
	public DefaultBpmDefinition cloneToMain(DefaultBpmDefinition bpmDefinition) {
		//克隆流程定义并新建
		DefaultBpmDefinition newBpmDefinition = (DefaultBpmDefinition)bpmDefinition.clone();
		newBpmDefinition.setDefId(idGenerator.getSuid());
		//将新的流程定义设置为主版本
		newBpmDefinition.setIsMain(Bool.TRUE.value());
		//设置版本号
		Integer maxVersion = getMaxVersion(bpmDefinition.getDefKey());
		newBpmDefinition.setVersion(maxVersion+1);
		newBpmDefinition.setMainDefId(newBpmDefinition.getDefId());
		bpmDefinitionDao.create(newBpmDefinition);
		
		String newDefId = newBpmDefinition.getDefId();
		
		//获取旧的流程定义关联的节点配置
		List<DefaultBpmNodeConfig> nodeConfigs = bpmNodeConfigManager.queryBpmNodeConfigs(bpmDefinition.getDefId());
		for(DefaultBpmNodeConfig nodeConfig:nodeConfigs){
			if(nodeConfig.isSettingSub()){
				if(nodeConfig.getNodeType().equals(BpmNodeConfig.NODE_TYPE.MSG)){					
					//克隆一个新的节点配置
					DefaultBpmNodeConfig newBpmNodeConfig = bpmNodeConfigManager.cloneNodeConfig(nodeConfig,newDefId);
					
					//克隆消息规则
					bpmMsgRuleManager.cloneMsgRulesForMsgNode(nodeConfig.getConfigId(), newBpmNodeConfig.getConfigId());
					
					continue;
				}else if(nodeConfig.getNodeType().equals(BpmNodeConfig.NODE_TYPE.SCRIPT)){
					//克隆一个新的节点配置					
					DefaultBpmNodeConfig newBpmNodeConfig = bpmNodeConfigManager.cloneNodeConfig(nodeConfig,newDefId);
					
					//克隆脚本节点
					bpmNodeScriptManager.cloneNodeScripts(nodeConfig.getConfigId(), newBpmNodeConfig.getConfigId());
					
					//克隆消息
					bpmMsgRuleManager.cloneMsgRulesForNode(nodeConfig.getConfigId(), newBpmNodeConfig.getConfigId());
					
					continue;
				}else if(nodeConfig.getNodeType().equals(BpmNodeConfig.NODE_TYPE.USER)){
					//查询用户节点
					DefaultBpmUserNode bpmUserNode = bpmUserNodeManager.get(nodeConfig.getConfigId());
					
					//如果不为空，则克隆用户节点
					if(bpmUserNode!=null){
						DefaultBpmUserNode newBpmUserNode = (DefaultBpmUserNode)bpmUserNode.clone();
						newBpmUserNode.setId(idGenerator.getSuid());
						newBpmUserNode.setProcDefId(newBpmDefinition.getDefId());	//关联新的流程定义
						bpmUserNodeManager.create(newBpmUserNode);
						
						//克隆消息
						bpmMsgRuleManager.cloneMsgRulesForNode(nodeConfig.getConfigId(), newBpmUserNode.getConfigId());
						
						continue;
					}
				}else if(nodeConfig.getNodeType().equals(BpmNodeConfig.NODE_TYPE.WEB_SERVICE)){
					BpmWsNode bpmWsNode = bpmWsNodeManager.get(nodeConfig.getConfigId());
					if(bpmWsNode!=null){
						BpmWsNode newBpmWsNode = (BpmWsNode)bpmWsNode.clone();
						bpmWsNodeManager.create(newBpmWsNode);			
						
						//克隆消息
						bpmMsgRuleManager.cloneMsgRulesForNode(nodeConfig.getConfigId(), newBpmWsNode.getConfigId());
						
						continue;
					}
				}
			}
			
			//如果上面没有做保存，则作为未配置节点处理
			nodeConfig.setIsSettingSub(Bool.FALSE.value());
			DefaultBpmNodeConfig newBpmNodeConfig = bpmNodeConfigManager.cloneNodeConfig(nodeConfig,newDefId);
			
			//克隆消息
			bpmMsgRuleManager.cloneMsgRulesForNode(nodeConfig.getConfigId(), newBpmNodeConfig.getConfigId());
		}
		
		//克隆流程级别的消息规则
		bpmMsgRuleManager.cloneMsgRulesForDef(bpmDefinition.getDefId(), newBpmDefinition.getDefId());
		
		//更新其它旧版本的主版本流程ID和主版本标识		
		updateMainDefId(newBpmDefinition.getDefId(),newBpmDefinition.getDefKey());				
				
		return newBpmDefinition;
	}

	/* (non-Javadoc)
	 * @see com.hotent.bpmx.persistence.manager.BpmDefinitionManager#queryByDefKey(java.lang.String)
	 */
	@Override
	public List<DefaultBpmDefinition> queryByDefKey(String defKey) {
		return bpmDefinitionDao.queryByDefKey(defKey);
	}

	/* (non-Javadoc)
	 * @see com.hotent.bpmx.persistence.manager.BpmDefinitionManager#queryHistorys(java.lang.String)
	 */
	@Override
	public List<DefaultBpmDefinition> queryHistorys(String defKey) {
		return bpmDefinitionDao.queryHistorys(defKey);
	}

	/* (non-Javadoc)
	 * @see com.hotent.bpmx.persistence.manager.BpmDefinitionManager#getMaxVersion(java.lang.String)
	 */
	@Override
	public DefaultBpmDefinition getMaxVersionDef(String defKey) {
		return bpmDefinitionDao.getMainByDefKey(defKey);
	}
	
	@Override
	public Integer getMaxVersion(String defKey) {
		DefaultBpmDefinition def = getMaxVersionDef(defKey);
		if(def!=null){
			return def.getVersion();
		}
		return 0;
	}

	public List<DefaultBpmDefinition> query(BpmDefQueryFields bpmDefQueryFields,FieldRelation fieldRelation,DefaultPage page){
		DefaultQueryFilter queryFilter=new DefaultQueryFilter();
		//查询条件
		DefaultFieldLogic fieldLogic = new DefaultFieldLogic(fieldRelation);
		fieldLogic.setWhereClauses(bpmDefQueryFields.getQueryFields());
		//排序和分页
		queryFilter.setFieldLogic(fieldLogic);
		queryFilter.setPage(page);
		return this.query(queryFilter);
	}

	/* (non-Javadoc)
	 * @see com.hotent.bpmx.persistence.manager.BpmDefinitionManager#query(com.hotent.bpmx.persistence.model.query.BpmDefQueryFields, com.hotent.base.api.query.FieldRelation, com.hotent.bpmx.persistence.model.query.BpmDefFieldSorts)
	 */
	@Override
	public List<DefaultBpmDefinition> query(
			BpmDefQueryFields bpmDefQueryFields, FieldRelation fieldRelation,
			BpmDefFieldSorts bpmDefFieldSorts) {
		DefaultQueryFilter queryFilter=new DefaultQueryFilter();
		//查询条件
		DefaultFieldLogic fieldLogic = new DefaultFieldLogic(fieldRelation);
		fieldLogic.setWhereClauses(bpmDefQueryFields.getQueryFields());
		//设置
		queryFilter.setFieldLogic(fieldLogic);
		queryFilter.setFieldSortList(bpmDefFieldSorts.getFieldSorts());
		queryFilter.setPage(null);		
		return query(queryFilter);
	}
	
	public DefaultBpmDefinition getByBpmnDefId(String bpmnDefId){
		return bpmDefinitionDao.getByBpmnDefId(bpmnDefId);
	}
	
}
