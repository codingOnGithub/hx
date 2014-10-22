package com.hotent.bpmx.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.bpmx.persistence.dao.BpmProcessInstanceDao;
import com.hotent.bpmx.persistence.model.DefaultBpmProcessInstance;

@Repository
public class BpmProcessInstanceDaoImpl extends MyBatisDaoImpl<String, DefaultBpmProcessInstance> implements BpmProcessInstanceDao{

    @Override
    public String getNamespace() {
        return DefaultBpmProcessInstance.class.getName();
    }
	
}
