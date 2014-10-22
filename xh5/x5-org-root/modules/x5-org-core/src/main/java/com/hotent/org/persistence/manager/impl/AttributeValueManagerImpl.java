package com.hotent.org.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.base.api.Page;
import com.hotent.base.db.api.Dao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.org.persistence.dao.AttributeValueDao;
import com.hotent.org.persistence.model.DefaultAttribute;
import com.hotent.org.persistence.model.DefaultAttributeValue;
import com.hotent.org.persistence.manager.AttributeValueManager;
import com.hotent.org.persistence.query.AttributeQuery;
import com.hotent.org.persistence.query.AttributeValueQuery;
import com.hotent.org.persistence.query.AttributeValueQuery.DeleteQuery;
import com.hotent.org.persistence.query.AttributeValueQuery.FindQuery;
import com.hotent.org.persistence.query.AttributeValueQuery.UpdateQuery;

@Service("attributeValueManager")
public class AttributeValueManagerImpl extends AbstractManagerImpl<String, DefaultAttributeValue> implements AttributeValueManager{
	@Resource
	AttributeValueDao attributeValueDao;
	@Override
	protected Dao<String, DefaultAttributeValue> getDao() {
		return attributeValueDao;
	}
	
	@Override
	public List<DefaultAttributeValue> queryByCriteria(AttributeValueQuery.FindQuery query) {
		return attributeValueDao.queryByCriteria(query);
	}
	
	@Override
	public List<DefaultAttributeValue> queryByCriteria(AttributeValueQuery.FindQuery query, Page page) {
		return attributeValueDao.queryByCriteria(query, page);
	}

	@Override
	public long queryCountByCriteria(FindQuery query) {
		return attributeValueDao.queryCountByCriteria(query);
	}

	@Override
	public void updateByUpdateQuery(UpdateQuery uQuery) {
		 attributeValueDao.updateByUpdateQuery(uQuery);
	}

	@Override
	public void deleteByDeleteQuery(DeleteQuery dQuery) {
		attributeValueDao.deleteByDeleteQuery(dQuery);
	}
}
