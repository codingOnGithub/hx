package com.hotent.bo.persistence.manager.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.db.api.Dao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.bo.persistence.dao.BOPackageDao;
import com.hotent.bo.persistence.model.BOPackage;
import com.hotent.bo.persistence.manager.BOPackageManager;

@Service("bOPackageManager")
public class BOPackageManagerImpl extends AbstractManagerImpl<String, BOPackage> implements BOPackageManager{
	@Resource
	BOPackageDao bOPackageDao;
	
	@Override
	protected Dao<String, BOPackage> getDao() {
		return bOPackageDao;
	}
	
	@Override
	public BOPackage getBOPackageByMap(Map map) {
		return bOPackageDao.getBOPackageByMap(map);
	}
}
