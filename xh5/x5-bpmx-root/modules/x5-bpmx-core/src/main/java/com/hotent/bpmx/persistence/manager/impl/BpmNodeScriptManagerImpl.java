package com.hotent.bpmx.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.db.api.Dao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.bpmx.persistence.dao.BpmNodeScriptDao;
import com.hotent.bpmx.persistence.model.BpmNodeScript;
import com.hotent.bpmx.persistence.manager.BpmNodeScriptManager;

@Service("bpmNodeScriptManager")
public class BpmNodeScriptManagerImpl extends AbstractManagerImpl<String, BpmNodeScript> implements BpmNodeScriptManager{
	@Resource
	BpmNodeScriptDao bpmNodeScriptDao;
	@Override
	protected Dao<String, BpmNodeScript> getDao() {
		return bpmNodeScriptDao;
	}
	/* (non-Javadoc)
	 * @see com.hotent.bpmx.persistence.manager.BpmNodeScriptManager#cloneMsgRules(java.lang.String, java.lang.String)
	 */
	@Override
	public void cloneNodeScripts(String originConfigId, String destConfigId) {
		List<BpmNodeScript> bpmNodeScripts = bpmNodeScriptDao.queryByConfigId(originConfigId);
		for(BpmNodeScript bpmNodeScript:bpmNodeScripts){
			BpmNodeScript newBpmNodeScript = (BpmNodeScript)bpmNodeScript.clone();
			newBpmNodeScript.setConfigId(destConfigId);
			bpmNodeScriptDao.create(newBpmNodeScript);
		}
	}
	@Override
	public void removeByConfigId(String configId) {
		bpmNodeScriptDao.removeByConfigId(configId);
	}
	
}
