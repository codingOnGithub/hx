package com.hotent.bo.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.db.api.Dao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.bo.persistence.dao.BORuleDao;
import com.hotent.bo.persistence.model.BORule;
import com.hotent.bo.persistence.manager.BORuleManager;

@Service("bORuleManager")
public class BORuleManagerImpl extends AbstractManagerImpl<String, BORule> implements BORuleManager{
	@Resource
	BORuleDao bORuleDao;
	@Override
	protected Dao<String, BORule> getDao() {
		return bORuleDao;
	}
	@Override
	public List<BORule> getByAttributeId(String attributeId) {
		return bORuleDao.getByAttributeId(attributeId);
	}
}
