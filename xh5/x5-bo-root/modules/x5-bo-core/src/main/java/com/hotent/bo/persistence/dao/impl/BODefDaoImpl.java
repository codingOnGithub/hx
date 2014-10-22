package com.hotent.bo.persistence.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.bo.persistence.dao.BODefDao;
import com.hotent.bo.persistence.model.BODef;

@Repository
public class BODefDaoImpl extends MyBatisDaoImpl<String, BODef> implements BODefDao{

    @Override
    public String getNamespace() {
        return BODef.class.getName();
    }

	@Override
	public void updateBoDefsIsMain(Map params){
		updateByKey("updateBoDefsIsMain", params);
	}

	@Override
	public BODef getMaxVersionBODef(Map params)
	{
		return this.getUnique("getMaxVersionBODef", params);
	}
	
}
