package com.hotent.org.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.hotent.base.api.Page;
import com.hotent.base.core.util.string.StringUtil;
import com.hotent.base.db.api.Dao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.org.api.OrgException;
import com.hotent.org.api.model.Attribute;
import com.hotent.org.api.model.UserGroupRelation;
import com.hotent.org.api.model.UserRelation;
import com.hotent.org.persistence.dao.AttributeDao;
import com.hotent.org.persistence.dao.AttributeValueDao;
import com.hotent.org.persistence.model.DefaultAttribute;
import com.hotent.org.persistence.model.DefaultAttributeValue;
import com.hotent.org.persistence.model.DefaultGroup;
import com.hotent.org.persistence.manager.AttributeManager;
import com.hotent.org.persistence.manager.GroupManager;
import com.hotent.org.persistence.manager.UserManager;
import com.hotent.org.persistence.query.AttributeQuery;
import com.hotent.org.persistence.query.AttributeQuery.DeleteQuery;
import com.hotent.org.persistence.query.AttributeQuery.FindQuery;
import com.hotent.org.persistence.query.AttributeQuery.UpdateQuery;
import com.hotent.org.persistence.query.AttributeValueQuery;
import com.hotent.org.persistence.query.GroupQuery;
import com.hotent.org.persistence.query.UserQuery;

@Service("attributeManager")
public class AttributeManagerImpl extends AbstractManagerImpl<String, DefaultAttribute> implements AttributeManager {
	@Resource
	AttributeDao attributeDao;

	@Resource
	AttributeValueDao attributeValueDao;
	@Resource
	GroupManager groupManager;
	@Resource
	UserManager userManager;

	@Override
	protected Dao<String, DefaultAttribute> getDao() {
		return attributeDao;
	}

	@Override
	public DefaultAttribute getByAttrKey(String attrKey) {
		return attributeDao.getByAttrKey(attrKey);
	}

	@Override
	public List<DefaultAttribute> queryByCriteria(AttributeQuery.FindQuery query) {
		return attributeDao.queryByCriteria(query);
	}

	@Override
	public List<DefaultAttribute> queryByCriteria(AttributeQuery.FindQuery query, Page page) {
		return attributeDao.queryByCriteria(query, page);
	}

	@Override
	public void remove(String entityId) {
		this.remove(entityId,true);
	}

	public void remove(String entityId, boolean logical) {
		ensureDataIntegrity(entityId, logical);
		if (logical) {
			AttributeQuery.UpdateQuery uQuery = new AttributeQuery.UpdateQuery();
			uQuery.createCriteria().andAttrIdEqualTo(entityId);
			uQuery.getUpdateClause().setStatus(Attribute.Status.deleted);
			attributeDao.updateByUpdateQuery(uQuery);
		} else {
			super.remove(entityId);
			AttributeQuery.DeleteQuery dQuery = new com.hotent.org.persistence.query.AttributeQuery.DeleteQuery();
			dQuery.createCriteria().andAttrIdEqualTo(entityId);
			this.deleteByDeleteQuery(dQuery);
		}
	}

	private void ensureDataIntegrity(String attrId, boolean logical) {
		if (logical) {

		} else {
			AttributeValueQuery.DeleteQuery attributeValueQuery = new AttributeValueQuery.DeleteQuery();
			attributeValueQuery.createCriteria().andAttrIdEqualTo(attrId);
			attributeValueDao.deleteByDeleteQuery(attributeValueQuery);
		}

	}

	@Override
	public long queryCountByCriteria(FindQuery query) {
		return attributeDao.queryCountByCriteria(query);
	}

	@Override
	public void updateByUpdateQuery(UpdateQuery uQuery) {
		attributeDao.updateByUpdateQuery(uQuery);

	}

	@Override
	public void deleteByDeleteQuery(DeleteQuery dQuery) {
		attributeDao.deleteByDeleteQuery(dQuery);
	}

	private void ensureAttributeValueDataIntegrity(DefaultAttributeValue attributeValue) {
		DefaultAttribute attribute = this.get(attributeValue.getAttrId());
		if (attribute == null) {
			throw new OrgException("Attribute-Value must specify a valid Attribute.");
		}

		switch (attribute.getBelongType()) {
		case group:
			boolean isGroupValid = true;
			if (StringUtils.isEmpty(attributeValue.getGroupId())) {
				isGroupValid = false;
			} else {
				GroupQuery.FindQuery groupFindQuery = new GroupQuery.FindQuery();
				groupFindQuery.createCriteria().andGroupIdEqualTo(attributeValue.getGroupId());
				long cnt = groupManager.queryCountByCriteria(groupFindQuery);
				if (cnt == 0) {
					isGroupValid = false;
				}
			}
			if (!isGroupValid) {
				throw new OrgException("Group-Attribute-Value must specify a valid group.");
			}
			break;
		case user:
			boolean isUserValid = true;
			if (StringUtils.isEmpty(attributeValue.getUserId())) {
				isUserValid = false;
			} else {
				UserQuery.FindQuery userFindQuery = new UserQuery.FindQuery();
				userFindQuery.createCriteria().andUserIdEqualTo(attributeValue.getGroupId());
				long cnt = userManager.queryCountByCriteria(userFindQuery);
				if (cnt == 0) {
					isUserValid = false;
				}
			}
			if (!isUserValid) {
				throw new OrgException("User-Attribute-Value must specify a valid user.");
			}
			break;
		}
	}

	@Override
	public void createAttributeValue(DefaultAttributeValue attributeValue) {
		ensureAttributeValueDataIntegrity(attributeValue);
		attributeValueDao.create(attributeValue);
	}
	
}
