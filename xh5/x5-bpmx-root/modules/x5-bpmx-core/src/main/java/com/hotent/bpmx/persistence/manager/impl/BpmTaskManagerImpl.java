package com.hotent.bpmx.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.base.db.api.Dao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.bpmx.persistence.dao.BpmTaskDao;
import com.hotent.bpmx.persistence.manager.BpmTaskManager;
import com.hotent.bpmx.persistence.model.DefaultBpmTask;

@Service("bpmTaskManager")
public class BpmTaskManagerImpl extends AbstractManagerImpl<String, DefaultBpmTask> implements BpmTaskManager{
	@Resource
	BpmTaskDao bpmTaskDao;
	@Override
	protected Dao<String, DefaultBpmTask> getDao() {
		return bpmTaskDao;
	}
}
