package com.hotent.org.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.base.db.api.Dao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.org.persistence.dao.GroupRelationDao;
import com.hotent.org.persistence.manager.GroupRelationManager;
import com.hotent.org.persistence.model.DefaultGroupRelation;

@Service("groupRelationManager")
public class GroupRelationManagerImpl extends AbstractManagerImpl<String, DefaultGroupRelation> implements GroupRelationManager{
	@Resource
	GroupRelationDao groupRelationDao;
	@Override
	protected Dao<String, DefaultGroupRelation> getDao() {
		return groupRelationDao;
	}
}
