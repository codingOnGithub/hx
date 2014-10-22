package com.hotent.bpmx.persistence.manager;

import com.hotent.base.manager.api.Manager;
import com.hotent.bpmx.persistence.model.BpmNodeScript;

public interface BpmNodeScriptManager extends Manager<String, BpmNodeScript>{
	public void cloneNodeScripts(String originConfigId,String destConfigId);
	
	public void removeByConfigId(String configId);
}
