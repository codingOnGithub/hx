package com.hotent.bpmx.persistence.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.bpmx.persistence.dao.BpmNodeConfigDao;
import com.hotent.bpmx.persistence.model.DefaultBpmNodeConfig;

@Repository
public class BpmNodeConfigDaoImpl extends MyBatisDaoImpl<String, DefaultBpmNodeConfig> implements BpmNodeConfigDao{

    @Override
    public String getNamespace() {
        return DefaultBpmNodeConfig.class.getName();
    }

	/* (non-Javadoc)
	 * @see com.hotent.bpmx.persistence.dao.DefaultBpmNodeConfigDao#queryBpmNodeConfigs(java.lang.String)
	 */
	@Override
	public List<DefaultBpmNodeConfig> queryBpmNodeConfigs(String procDefId) {
		return getByKey("queryBpmNodeConfigs", buildMap("procDefId", procDefId));
	}

	@Override
	public DefaultBpmNodeConfig getByDefIdAndNodeId(String procDefId,
			String nodeId) {
		return getUnique("getByDefIdAndNodeId", buildMapBuilder().addParam("procDefId",procDefId).addParam("nodeId",nodeId).getParams());
	}
	
}
