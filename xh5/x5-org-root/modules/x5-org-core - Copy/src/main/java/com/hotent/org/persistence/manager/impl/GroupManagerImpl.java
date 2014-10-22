package com.hotent.org.persistence.manager.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.base.api.Page;
import com.hotent.base.db.api.Dao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.org.api.OrgException;
import com.hotent.org.api.model.Attribute;
import com.hotent.org.api.model.AttributeValue;
import com.hotent.org.api.model.Dimension;
import com.hotent.org.api.model.Group;
import com.hotent.org.api.model.GroupRelation;
import com.hotent.org.api.model.RelationType;
import com.hotent.org.api.model.User;
import com.hotent.org.api.model.UserGroupRelation;
import com.hotent.org.api.model.UserRelation;
import com.hotent.org.persistence.dao.AttributeDao;
import com.hotent.org.persistence.dao.AttributeValueDao;
import com.hotent.org.persistence.dao.GroupDao;
import com.hotent.org.persistence.dao.GroupRelationDao;
import com.hotent.org.persistence.dao.UserGroupRelationDao;
import com.hotent.org.persistence.manager.AttributeManager;
import com.hotent.org.persistence.manager.DimensionManager;
import com.hotent.org.persistence.manager.GroupManager;
import com.hotent.org.persistence.manager.RelationTypeManager;
import com.hotent.org.persistence.model.DefaultAttributeValue;
import com.hotent.org.persistence.model.DefaultGroup;
import com.hotent.org.persistence.model.DefaultGroupRelation;
import com.hotent.org.persistence.model.DefaultRelationType;
import com.hotent.org.persistence.model.DefaultUser;
import com.hotent.org.persistence.model.DefaultUserGroupRelation;
import com.hotent.org.persistence.query.AttributeUpdateQuery;
import com.hotent.org.persistence.query.AttributeValueQuery;
import com.hotent.org.persistence.query.GroupDeleteQuery;
import com.hotent.org.persistence.query.GroupQuery;
import com.hotent.org.persistence.query.GroupRelationDeleteQuery;
import com.hotent.org.persistence.query.GroupRelationQuery;
import com.hotent.org.persistence.query.GroupRelationUpdateQuery;
import com.hotent.org.persistence.query.GroupUpdateQuery;
import com.hotent.org.persistence.query.UserGroupRelationDeleteQuery;
import com.hotent.org.persistence.query.UserGroupRelationQuery;
import com.hotent.org.persistence.query.UserGroupRelationUpdateQuery;
import com.hotent.org.persistence.query.UserQuery;

@Service("groupManager")
public class GroupManagerImpl extends AbstractManagerImpl<String, DefaultGroup> implements GroupManager<String, DefaultGroup> {
	@Resource(name = "groupDaoImpl")
	GroupDao groupDao;

	@Resource
	UserGroupRelationDao userGroupRelationDao;

	@Resource
	GroupRelationDao groupRelationDao;

	@Resource
	AttributeValueDao attributeValueDao;

	@Resource
	AttributeDao attributeDao;

	@Resource
	AttributeManager attributeManager;

	@Resource
	DimensionManager dimensionManager;

	@Resource
	RelationTypeManager relationTypeManager;

	@Override
	protected Dao getDao() {
		return groupDao;
	}

	@Override
	public DefaultGroup getByKey(String groupKey) {
		GroupQuery query = new GroupQuery();
		query.createCriteria().andKeyEqualTo(groupKey);
		List<DefaultGroup> groups = groupDao.queryByCriteria(query);
		if (groups.size() == 0) {
			return null;
		} else {
			return groups.get(0);
		}
	}

	@Override
	public void deleteByGroupKey(String groupKey) {
		GroupDeleteQuery dQuery = new GroupDeleteQuery();
		dQuery.createCriteria().andKeyEqualTo(groupKey);
		groupDao.deleteByDeleteQuery(dQuery);
	}

	@Override
	public List<DefaultGroup> queryByDimId(String dimId) {
		GroupQuery query = new GroupQuery();
		Dimension dimension = dimensionManager.get(dimId);
		if (dimension == null) {
			return new ArrayList<DefaultGroup>();
		}
		query.createCriteria().andDimKeyEqualTo(dimension.getDimKey());
		return groupDao.queryByCriteria(query);
	}

	@Override
	public List<DefaultGroup> queryByUserId(String userId, String status) {
		GroupQuery query = new GroupQuery();
		query.createUserGroupCriteria().andUserIdEqualTo(userId).andStatusEqualTo(status);
		return groupDao.queryByCriteria(query);
	}

	@Override
	public List<DefaultGroup> queryByUserId(String userId, String status, Page page) {
		GroupQuery query = new GroupQuery();
		query.createUserGroupCriteria().andUserIdEqualTo(userId).andStatusEqualTo(status);
		return groupDao.queryByCriteria(query, page);
	}

	@Override
	public List<DefaultGroup> queryByGroupRelation(String groupKey, String relType) {
		DefaultRelationType relationType = relationTypeManager.get(relType);
		if (relationType == null) {
			return new ArrayList<DefaultGroup>();
		}
		// /是双向关系
		if (relationType.getIsBidirectonal() == RelationType.IS_BIDIRECTONAL_Y) {
			GroupQuery query = new GroupQuery();
			query.createGroupRelationCriteria().andGroupIdEqualTo(groupKey).andRelTypeIdEqualTo(relType);
			query.orGroupRelationCriteria().andRelGroupIdEqualTo(groupKey).andRelTypeIdEqualTo(relType);
			return queryByCriteria(query);
		} else {
			GroupQuery query = new GroupQuery();
			query.createGroupRelationCriteria().andGroupIdEqualTo(groupKey).andRelTypeIdEqualTo(relType);
			return queryByCriteria(query);
		}

	}

	@Override
	public List<DefaultGroup> queryByGroupRelationRel(String groupKey, String relType) {
		DefaultRelationType relationType = relationTypeManager.get(relType);
		if (relationType == null) {
			return new ArrayList<DefaultGroup>();
		}
		// /是双向关系
		if (relationType.getIsBidirectonal() == RelationType.IS_BIDIRECTONAL_Y) {
			GroupQuery query = new GroupQuery();
			query.createGroupRelationCriteria().andGroupIdEqualTo(groupKey).andRelTypeIdEqualTo(relType);
			query.orGroupRelationCriteria().andRelGroupIdEqualTo(groupKey).andRelTypeIdEqualTo(relType);
			return queryByCriteria(query);
		} else {
			GroupQuery query = new GroupQuery();
			query.createGroupRelationCriteria().andRelGroupIdEqualTo(groupKey).andRelTypeIdEqualTo(relType);
			return queryByCriteria(query);
		}

	}

	@Override
	public List<DefaultGroup> queryByUserGroupRelation(String userId, String relType) {
		GroupQuery query = new GroupQuery();
		query.createUserGroupCriteria().andUserIdEqualTo(userId).andRelTypeIdEqualTo(relType);
		return groupDao.queryByCriteria(query);
	}

	@Override
	public List<DefaultGroup> queryGroupByAttributeValue(String key, Object value) {
		GroupQuery query = new GroupQuery();
		AttributeValueQuery.Criteria attributeValueCriteria = query.createAttributeValueCriteria().andAttrKeyEqualTo(key);
		if (Date.class.isAssignableFrom(value.getClass())) {
			attributeValueCriteria.andDateValEqualTo((Date) value);
		} else if (Long.class.isAssignableFrom(value.getClass())) {
			attributeValueCriteria.andLongValEqualTo((Long) value);
		} else if (Double.class.isAssignableFrom(value.getClass())) {
			attributeValueCriteria.andDecValEqualTo((Double) value);
		} else if (String.class.isAssignableFrom(value.getClass())) {
			attributeValueCriteria.andTextValEqualTo((String) value);
		} else {
			// TODO
			return new ArrayList<DefaultGroup>();
		}
		return queryByCriteria(query);
	}

	@Override
	public List<DefaultGroup> queryByCriteria(GroupQuery query) {
		return groupDao.queryByCriteria(query);
	}

	@Override
	public List<DefaultGroup> queryByCriteria(GroupQuery query, Page page) {
		return groupDao.queryByCriteria(query, page);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hotent.base.manager.impl.AbstractManagerImpl#remove(java.io.Serializable
	 * )
	 */
	public void remove(String entityId) {
		this.remove(entityId, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hotent.base.manager.impl.AbstractManagerImpl#remove(java.io.Serializable
	 * )
	 */
	public void remove(String entityId, boolean logical) {
		ensureDataIntegrity(entityId, logical);
		if (logical) {
			GroupUpdateQuery uQuery = new GroupUpdateQuery();
			uQuery.createCriteria().andGroupIdEqualTo(entityId);
			uQuery.getUpdateClause().setStatus(Group.STATUS_DELETED);
			groupDao.updateByUpdateQuery(uQuery);
		} else {
			super.remove(entityId);
		}
	}

	private void ensureDataIntegrity(String groupId, boolean logical) {

		if (logical) {
			// /User Group Relation
			{
				UserGroupRelationUpdateQuery userGroupRelationUQuery = new UserGroupRelationUpdateQuery();
				userGroupRelationUQuery.createCriteria().andGroupIdEqualTo(groupId);
				userGroupRelationUQuery.getUpdateClause().setStatus(UserGroupRelation.STATUS_DELETED);
				userGroupRelationDao.updateByUpdateQuery(userGroupRelationUQuery);
			}

			// User Relation
			{
				GroupRelationUpdateQuery groupRelationUQuery = new GroupRelationUpdateQuery();
				groupRelationUQuery.createCriteria().andGroupIdEqualTo(groupId);
				groupRelationUQuery.or().andRelGroupIdEqualTo(groupId);
				groupRelationUQuery.getUpdateClause().setStatus(GroupRelation.STATUS_DELETED);
				groupRelationDao.updateByUpdateQuery(groupRelationUQuery);
			}

			// Attribute Attribute
			{
				AttributeValueQuery attributeValueQuery = new AttributeValueQuery();
				attributeValueQuery.createCriteria().andGroupIdEqualTo(groupId);
				List<DefaultAttributeValue> attributeValues = attributeValueDao.queryByCriteria(attributeValueQuery);
				for (DefaultAttributeValue attributeValue : attributeValues) {
					AttributeUpdateQuery attributeUQuery = new AttributeUpdateQuery();
					attributeUQuery.createCriteria().andAttrIdEqualTo(attributeValue.getAttrId());
					attributeUQuery.getUpdateClause().setStatus(Attribute.STATUS_DELETED);
					attributeDao.updateByUpdateQuery(attributeUQuery);
				}
			}
		} else {

			// /User Group Relation
			{
				UserGroupRelationDeleteQuery userGroupRelationDeleteQuery = new UserGroupRelationDeleteQuery();
				userGroupRelationDeleteQuery.createCriteria().andGroupIdEqualTo(groupId);
				userGroupRelationDao.deleteByDeleteQuery(userGroupRelationDeleteQuery);
			}

			// Group Relation
			{
				GroupRelationDeleteQuery groupRelationDeleteQuery = new GroupRelationDeleteQuery();
				groupRelationDeleteQuery.createCriteria().andGroupIdEqualTo(groupId);
				groupRelationDeleteQuery.or().andRelGroupIdEqualTo(groupId);
				groupRelationDao.deleteByDeleteQuery(groupRelationDeleteQuery);
			}

			// Attribute Attribute
			{
				AttributeValueQuery attributeValueQuery = new AttributeValueQuery();
				attributeValueQuery.createCriteria().andGroupIdEqualTo(groupId);
				List<DefaultAttributeValue> attributeValues = attributeValueDao.queryByCriteria(attributeValueQuery);
				for (DefaultAttributeValue attributeValue : attributeValues) {
					attributeManager.remove(attributeValue.getAttrId());
				}
			}
		}

	}

	@Override
	public void ceateUserGroupRelation(DefaultUserGroupRelation userGroupRelation) {

		UserGroupRelationQuery userGroupRelationQuery = new UserGroupRelationQuery();
		userGroupRelationQuery.createCriteria().andUserIdEqualTo(userGroupRelation.getUserId()).andGroupIdEqualTo(userGroupRelation.getGroupId())
				.andRelTypeIdEqualTo(userGroupRelation.getRelTypeId());
		long cnt = userGroupRelationDao.queryCountByCriteria(userGroupRelationQuery);
		if (cnt > 0) {
			throw new OrgException("用户组织关系已存在!");
		}
		userGroupRelationDao.create(userGroupRelation);
	}

	@Override
	public void ceateGroupRelation(DefaultGroupRelation groupRelation) {
		DefaultRelationType relationType = relationTypeManager.get(groupRelation.getRelType());
		if (relationType.getIsBidirectonal() == RelationType.IS_BIDIRECTONAL_Y) {
			GroupRelationQuery groupRelationQuery = new GroupRelationQuery();
			groupRelationQuery.createCriteria().andGroupIdEqualTo(groupRelation.getGroupId()).andRelTypeIdEqualTo(groupRelation.getRelType());
			groupRelationQuery.or().andRelGroupIdEqualTo(groupRelation.getGroupId()).andRelTypeIdEqualTo(groupRelation.getRelType());
			long cnt = groupRelationDao.queryCountByCriteria(groupRelationQuery);
			if (cnt > 0) {
				throw new OrgException("组织、组织关系已存在!");
			}
		} else {
			GroupRelationQuery groupRelationQuery = new GroupRelationQuery();
			groupRelationQuery.createCriteria().andGroupIdEqualTo(groupRelation.getGroupId()).andRelGroupIdEqualTo(groupRelation.getRelGroupId()).andRelTypeIdEqualTo(groupRelation.getRelType());
			long cnt = groupRelationDao.queryCountByCriteria(groupRelationQuery);
			if (cnt > 0) {
				throw new OrgException("组织、组织关系已存在!");
			}
		}
		groupRelationDao.create(groupRelation);
	}

	@Override
	public long queryCountByCriteria(GroupQuery query) {
		return groupDao.queryCountByCriteria(query);
	}

	@Override
	public void updateByUpdateQuery(GroupUpdateQuery uQuery) {
		groupDao.updateByUpdateQuery(uQuery);
	}

	@Override
	public void deleteByDeleteQuery(GroupDeleteQuery dQuery) {
		groupDao.deleteByDeleteQuery(dQuery);
	}
}
