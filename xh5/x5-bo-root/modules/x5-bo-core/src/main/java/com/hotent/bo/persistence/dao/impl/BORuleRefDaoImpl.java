package com.hotent.bo.persistence.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.bo.persistence.dao.BORuleRefDao;
import com.hotent.bo.persistence.model.BORuleRef;

@Repository
public class BORuleRefDaoImpl extends MyBatisDaoImpl<String, BORuleRef> implements BORuleRefDao{

    @Override
    public String getNamespace() {
        return BORuleRef.class.getName();
    }

	@Override
	public void removeRulesRefs(Map params)
	{
		deleteByKey("removeRulesRefs", params);		
	}
	
}
