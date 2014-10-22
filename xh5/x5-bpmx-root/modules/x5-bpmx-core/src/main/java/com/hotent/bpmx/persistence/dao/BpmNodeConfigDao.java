package com.hotent.bpmx.persistence.dao;
import java.util.List;

import com.hotent.base.db.api.Dao;
import com.hotent.bpmx.persistence.model.DefaultBpmNodeConfig;


public interface BpmNodeConfigDao extends Dao<String, DefaultBpmNodeConfig> {
	public List<DefaultBpmNodeConfig> queryBpmNodeConfigs(String procDefId);
	
	public DefaultBpmNodeConfig getByDefIdAndNodeId(String procDefId,String nodeId);
}
