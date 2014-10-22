package com.hotent.bpmx.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.db.api.Dao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.bpmx.persistence.dao.BpmMsgRuleDao;
import com.hotent.bpmx.persistence.model.BpmMsgRule;
import com.hotent.bpmx.persistence.manager.BpmMsgRuleManager;

@Service("bpmMsgRuleManager")
public class BpmMsgRuleManagerImpl extends AbstractManagerImpl<String, BpmMsgRule> implements BpmMsgRuleManager{
	@Resource
	BpmMsgRuleDao bpmMsgRuleDao;
	@Override
	protected Dao<String, BpmMsgRule> getDao() {
		return bpmMsgRuleDao;
	}
	/* (non-Javadoc)
	 * @see com.hotent.bpmx.persistence.manager.BpmMsgRuleManager#queryByProcDefId(java.lang.String)
	 */
	@Override
	public List<BpmMsgRule> queryForDef(String procDefId) {
		return bpmMsgRuleDao.queryForDef(procDefId);
	}
	/* (non-Javadoc)
	 * @see com.hotent.bpmx.persistence.manager.BpmMsgRuleManager#queryByConfigId(java.lang.String)
	 */
	@Override
	public List<BpmMsgRule> queryForNode(String configId) {
		return bpmMsgRuleDao.queryForNode(configId);
	}
	/* (non-Javadoc)
	 * @see com.hotent.bpmx.persistence.manager.BpmMsgRuleManager#queryForMsgNode(java.lang.String)
	 */
	@Override
	public List<BpmMsgRule> queryForMsgNode(String configId) {
		return bpmMsgRuleDao.queryForMsgNode(configId);
	}
	/* (non-Javadoc)
	 * @see com.hotent.bpmx.persistence.manager.BpmMsgRuleManager#cloneMsgRulesForDef(java.lang.String, java.lang.String)
	 */
	@Override
	public void cloneMsgRulesForDef(String originProcDefId, String destProcDefId) {
		List<BpmMsgRule> originBpmMsgRules = queryForDef(originProcDefId);		
		for(BpmMsgRule bpmMsgRule:originBpmMsgRules){
			BpmMsgRule newBpmMsgRule = (BpmMsgRule)bpmMsgRule.clone();
			newBpmMsgRule.setProcDefId(destProcDefId);
			create(newBpmMsgRule);
		}		
	}	
	/* (non-Javadoc)
	 * @see com.hotent.bpmx.persistence.manager.BpmMsgRuleManager#cloneMsgRules(java.lang.String, java.lang.String)
	 */
	@Override
	public void cloneMsgRulesForNode(String originConfigId, String destConfigId) {		
		List<BpmMsgRule> originBpmMsgRules = queryForNode(originConfigId);		
		for(BpmMsgRule bpmMsgRule:originBpmMsgRules){
			BpmMsgRule newBpmMsgRule = (BpmMsgRule)bpmMsgRule.clone();
			newBpmMsgRule.setConfigId(destConfigId);
			create(newBpmMsgRule);
		}
	}
	/* (non-Javadoc)
	 * @see com.hotent.bpmx.persistence.manager.BpmMsgRuleManager#cloneMsgRulesForMsgNode(java.lang.String, java.lang.String)
	 */
	@Override
	public void cloneMsgRulesForMsgNode(String originConfigId,
			String destConfigId) {
		List<BpmMsgRule> originBpmMsgRules = queryForMsgNode(originConfigId);		
		for(BpmMsgRule bpmMsgRule:originBpmMsgRules){
			BpmMsgRule newBpmMsgRule = (BpmMsgRule)bpmMsgRule.clone();
			newBpmMsgRule.setConfigId(destConfigId);
			create(newBpmMsgRule);
		}
	}
	@Override
	public void removeByConfigId(String configId) {
		bpmMsgRuleDao.removeByConfigId(configId);
	}

	
}
