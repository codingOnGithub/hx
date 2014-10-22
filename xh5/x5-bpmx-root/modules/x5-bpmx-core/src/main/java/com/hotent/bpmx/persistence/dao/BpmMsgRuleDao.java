package com.hotent.bpmx.persistence.dao;
import java.util.List;

import com.hotent.base.db.api.Dao;
import com.hotent.bpmx.persistence.model.BpmMsgRule;

public interface BpmMsgRuleDao extends Dao<String, BpmMsgRule> {
	public List<BpmMsgRule> queryForDef(String procDefId);
	
	public List<BpmMsgRule> queryForNode(String configId);
	
	public List<BpmMsgRule> queryForMsgNode(String configId);
	
	public void removeByConfigId(String configId);
}
