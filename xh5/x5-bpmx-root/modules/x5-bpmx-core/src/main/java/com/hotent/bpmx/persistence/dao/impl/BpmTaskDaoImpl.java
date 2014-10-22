package com.hotent.bpmx.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.bpmx.persistence.dao.BpmTaskDao;
import com.hotent.bpmx.persistence.model.DefaultBpmTask;

@Repository
public class BpmTaskDaoImpl extends MyBatisDaoImpl<String, DefaultBpmTask> implements BpmTaskDao{

    @Override
    public String getNamespace() {
        return DefaultBpmTask.class.getName();
    }
	
}

