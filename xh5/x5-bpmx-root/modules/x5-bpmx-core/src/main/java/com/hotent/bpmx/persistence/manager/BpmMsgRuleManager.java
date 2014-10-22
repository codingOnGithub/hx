package com.hotent.bpmx.persistence.manager;

import java.util.List;

import com.hotent.base.manager.api.Manager;
import com.hotent.bpmx.persistence.model.BpmMsgRule;

public interface BpmMsgRuleManager extends Manager<String, BpmMsgRule>{
	public List<BpmMsgRule> queryForDef(String procDefId);
	
	public List<BpmMsgRule> queryForNode(String configId);
	
	public List<BpmMsgRule> queryForMsgNode(String configId);	
	
	public void cloneMsgRulesForDef(String originProcDefId,String destProcDefId);
	
	public void cloneMsgRulesForNode(String originConfigId,String destConfigId);
	
	public void cloneMsgRulesForMsgNode(String originConfigId,String destConfigId);
	
	public void removeByConfigId(String configId);
}
