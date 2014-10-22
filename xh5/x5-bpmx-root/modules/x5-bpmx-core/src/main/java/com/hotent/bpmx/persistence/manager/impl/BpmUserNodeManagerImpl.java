package com.hotent.bpmx.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.db.api.Dao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.bpmx.persistence.dao.BpmUserNodeDao;
import com.hotent.bpmx.persistence.model.DefaultBpmUserNode;
import com.hotent.bpmx.persistence.manager.BpmUserNodeManager;

@Service("bpmUserNodeManager")
public class BpmUserNodeManagerImpl extends AbstractManagerImpl<String, DefaultBpmUserNode> implements BpmUserNodeManager{
	@Resource
	BpmUserNodeDao bpmUserNodeDao;
	@Override
	protected Dao<String, DefaultBpmUserNode> getDao() {
		return bpmUserNodeDao;
	}
	
}
