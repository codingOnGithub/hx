package com.hotent.org.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.base.api.Page;
import com.hotent.base.db.api.Dao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.org.api.model.Attribute;
import com.hotent.org.api.model.UserGroupRelation;
import com.hotent.org.api.model.UserRelation;
import com.hotent.org.persistence.dao.AttributeDao;
import com.hotent.org.persistence.dao.AttributeValueDao;
import com.hotent.org.persistence.model.DefaultAttribute;
import com.hotent.org.persistence.model.DefaultAttributeValue;
import com.hotent.org.persistence.model.DefaultGroup;
import com.hotent.org.persistence.manager.AttributeManager;
import com.hotent.org.persistence.query.AttributeQuery;
import com.hotent.org.persistence.query.AttributeUpdateQuery;
import com.hotent.org.persistence.query.AttributeValueDeleteQuery;
import com.hotent.org.persistence.query.AttributeValueQuery;
import com.hotent.org.persistence.query.GroupQuery;
import com.hotent.org.persistence.query.UserGroupRelationDeleteQuery;
import com.hotent.org.persistence.query.UserGroupRelationUpdateQuery;
import com.hotent.org.persistence.query.UserRelationDeleteQuery;
import com.hotent.org.persistence.query.UserRelationUpdateQuery;

@Service("attributeManager")
public class AttributeManagerImpl extends AbstractManagerImpl<String, DefaultAttribute> implements AttributeManager {
	@Resource
	AttributeDao attributeDao;
	
	@Resource
	AttributeValueDao attributeValueDao;
	
	@Override
	protected Dao<String, DefaultAttribute> getDao() {
		return attributeDao;
	}

	@Override
	public DefaultAttribute getByAttrKey(String attrKey) {
		return attributeDao.getByAttrKey(attrKey);
	}

	@Override
	public List<DefaultAttribute> queryByBelongType(String belongType) {
		return attributeDao.queryByBelongType(belongType);
	}

	@Override
	public List<DefaultAttribute> queryByCriteria(AttributeQuery query) {
		return attributeDao.queryByCriteria(query);
	}

	@Override
	public List<DefaultAttribute> queryByCriteria(AttributeQuery query, Page page) {
		return attributeDao.queryByCriteria(query, page);
	}

	@Override
	public void remove(String entityId) {
		super.remove(entityId);
	}

	public void remove(String entityId, boolean logical) {
		ensureDataIntegrity(entityId, logical);
		if (logical) {
			AttributeUpdateQuery uQuery = new AttributeUpdateQuery();
			uQuery.createCriteria().andAttrIdEqualTo(entityId);
			uQuery.getUpdateClause().setStatus(Attribute.STATUS_DELETED);
			attributeDao.updateByUpdateQuery(uQuery);;
		} else {
			super.remove(entityId);
		}
	}
	
	private void ensureDataIntegrity(String attrId, boolean logical) {
		if (logical) {
			
		} else {
			AttributeValueDeleteQuery attributeValueQuery = new AttributeValueDeleteQuery();
			attributeValueQuery.createCriteria().andAttrIdEqualTo(attrId);
			attributeValueDao.deleteByDeleteQuery(attributeValueQuery);
		}

	}
}
