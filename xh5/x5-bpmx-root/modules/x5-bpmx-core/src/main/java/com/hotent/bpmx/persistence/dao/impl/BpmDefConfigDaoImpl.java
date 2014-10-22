package com.hotent.bpmx.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.bpmx.persistence.dao.BpmDefConfigDao;
import com.hotent.bpmx.persistence.model.BpmDefConfig;

@Repository
public class BpmDefConfigDaoImpl extends MyBatisDaoImpl<String, BpmDefConfig> implements BpmDefConfigDao{

    @Override
    public String getNamespace() {
        return BpmDefConfig.class.getName();
    }
	
}
