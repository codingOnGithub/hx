package com.hotent.bpmx.persistence.dao;
import java.util.List;

import com.hotent.base.db.api.Dao;
import com.hotent.bpmx.persistence.model.BpmNodeScript;


public interface BpmNodeScriptDao extends Dao<String, BpmNodeScript> {
	public List<BpmNodeScript> queryByConfigId(String configId);
	
	public void removeByConfigId(String configId);
}
