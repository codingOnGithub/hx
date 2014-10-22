package com.hotent.bpmx.persistence.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.bpmx.persistence.dao.BpmNodeScriptDao;
import com.hotent.bpmx.persistence.model.BpmNodeScript;

@Repository
public class BpmNodeScriptDaoImpl extends MyBatisDaoImpl<String, BpmNodeScript> implements BpmNodeScriptDao{

    @Override
    public String getNamespace() {
        return BpmNodeScript.class.getName();
    }

	/* (non-Javadoc)
	 * @see com.hotent.bpmx.persistence.dao.BpmNodeScriptDao#queryByConfigId(java.lang.String)
	 */
	@Override
	public List<BpmNodeScript> queryByConfigId(String configId) {
		return this.getByKey("queryByConfigId", buildMap("configId", configId));
	}
	
	@Override
	public void removeByConfigId(String configId) {
		sqlSessionTemplate.delete(getNamespace() + ".removeByConfigId", configId);
	}
}
