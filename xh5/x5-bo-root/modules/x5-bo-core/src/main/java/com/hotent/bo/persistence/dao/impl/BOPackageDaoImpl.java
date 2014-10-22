package com.hotent.bo.persistence.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.bo.persistence.dao.BOPackageDao;
import com.hotent.bo.persistence.model.BOPackage;

@Repository
public class BOPackageDaoImpl extends MyBatisDaoImpl<String, BOPackage> implements BOPackageDao{

    @Override
    public String getNamespace() {
        return BOPackage.class.getName();
    }
	
    @Override
    public BOPackage getBOPackageByMap(Map params) {
		return getUnique("getBOPackageByMap", params);
	}
    
    
}
