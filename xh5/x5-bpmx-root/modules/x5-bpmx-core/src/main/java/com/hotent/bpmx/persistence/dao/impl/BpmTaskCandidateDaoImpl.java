package com.hotent.bpmx.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.bpmx.persistence.dao.BpmTaskCandidateDao;
import com.hotent.bpmx.persistence.model.DefaultBpmTaskCandidate;

@Repository
public class BpmTaskCandidateDaoImpl extends MyBatisDaoImpl<String,DefaultBpmTaskCandidate> implements BpmTaskCandidateDao{

    @Override
    public String getNamespace() {
        return DefaultBpmTaskCandidate.class.getName();
    }
	
}

