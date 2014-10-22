package com.hotent.bpmx.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.base.db.api.Dao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.bpmx.persistence.dao.BpmTaskCandidateDao;
import com.hotent.bpmx.persistence.manager.BpmTaskCandidateManager;
import com.hotent.bpmx.persistence.model.DefaultBpmTaskCandidate;

@Service("bpmTaskCandidateManager")
public class BpmTaskCandidateManagerImpl extends AbstractManagerImpl<String, DefaultBpmTaskCandidate> implements BpmTaskCandidateManager{
	@Resource
	BpmTaskCandidateDao bpmTaskCandidateDao;
	@Override
	protected Dao<String, DefaultBpmTaskCandidate> getDao() {
		return bpmTaskCandidateDao;
	}
}
