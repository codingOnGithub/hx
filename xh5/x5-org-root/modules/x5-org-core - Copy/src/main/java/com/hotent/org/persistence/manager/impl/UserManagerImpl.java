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
import com.hotent.org.api.model.RelationType;
import com.hotent.org.api.model.User;
import com.hotent.org.api.model.UserGroupRelation;
import com.hotent.org.api.model.UserRelation;
import com.hotent.org.persistence.dao.AttributeDao;
import com.hotent.org.persistence.dao.AttributeValueDao;
import com.hotent.org.persistence.dao.RelationTypeDao;
import com.hotent.org.persistence.dao.UserDao;
import com.hotent.org.persistence.dao.UserGroupRelationDao;
import com.hotent.org.persistence.dao.UserRelationDao;
import com.hotent.org.persistence.manager.AttributeManager;
import com.hotent.org.persistence.manager.AttributeValueManager;
import com.hotent.org.persistence.manager.RelationTypeManager;
import com.hotent.org.persistence.manager.UserManager;
import com.hotent.org.persistence.model.DefaultAttributeValue;
import com.hotent.org.persistence.model.DefaultRelationType;
import com.hotent.org.persistence.model.DefaultUser;
import com.hotent.org.persistence.query.AttributeDeleteQuery;
import com.hotent.org.persistence.query.AttributeUpdateQuery;
import com.hotent.org.persistence.query.AttributeValueQuery;
import com.hotent.org.persistence.query.RelationTypeQuery;
import com.hotent.org.persistence.query.UserGroupRelationDeleteQuery;
import com.hotent.org.persistence.query.UserGroupRelationUpdateQuery;
import com.hotent.org.persistence.query.UserQuery;
import com.hotent.org.persistence.query.UserRelationDeleteQuery;
import com.hotent.org.persistence.query.UserRelationUpdateQuery;
import com.hotent.org.persistence.query.UserUpdateQuery;

@Service("userManager")
public class UserManagerImpl extends AbstractManagerImpl<String, DefaultUser> implements UserManager {
	@Resource
	UserDao userDao;

	@Resource
	UserGroupRelationDao userGroupRelationDao;

	@Resource
	AttributeValueDao attributeValueDao;

	@Resource
	UserRelationDao userRelationDao;

	@Resource
	AttributeManager attributeManager;
	
	@Resource
	AttributeDao attributeDao;

	
	@Resource
	RelationTypeManager relationTypeManager;
	
	
	@Override
	protected Dao<String, DefaultUser> getDao() {
		return userDao;
	}

	/**
	 * 创建系统用户
	 * 
	 * @exception OrgException
	 *                如果指定账号的用户在系统中已经存在
	 * 
	 * @see com.hotent.base.manager.impl.AbstractManagerImpl#create(java.io.Serializable)
	 */
	@Override
	public void create(DefaultUser entity) {
		UserQuery query = new UserQuery();
		query.createCriteria().andAccountEqualTo(entity.getAccount());
		Long cnt = userDao.queryCountByCriteria(query);
		if (cnt > 0) {
			throw new OrgException("User specified by the account already exists");
		}
		super.create(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hotent.base.manager.impl.AbstractManagerImpl#remove(java.io.Serializable
	 * )
	 */
	@Override
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
			UserUpdateQuery uQuery = new UserUpdateQuery();
			uQuery.createCriteria().andUserIdEqualTo(entityId);
			uQuery.getUpdateClause().setStatus(User.STATUS_DELETED);
			userDao.updateByUpdateQuery(uQuery);
		} else {
			super.remove(entityId);
		}
	}

	private void ensureDataIntegrity(String userId, boolean logical) {

		if (logical) {
			// /User Group Relation
			{
				UserGroupRelationUpdateQuery userGroupRelationUQuery = new UserGroupRelationUpdateQuery();
				userGroupRelationUQuery.createCriteria().andUserIdEqualTo(userId);
				userGroupRelationUQuery.getUpdateClause().setStatus(UserGroupRelation.STATUS_DELETED);
				userGroupRelationDao.updateByUpdateQuery(userGroupRelationUQuery);
			}

			// User Relation
			{
				UserRelationUpdateQuery userRelationUQuery = new UserRelationUpdateQuery();
				userRelationUQuery.createCriteria().andUserIdEqualTo(userId);
				userRelationUQuery.or().andRelUserIdEqualTo(userId);
				userRelationUQuery.getUpdateClause().setStatus(UserRelation.STATUS_DELETED);
				userRelationDao.updateByUpdateQuery(userRelationUQuery);
			}

			// Attribute Attribute
			{
				AttributeValueQuery attributeValueQuery = new AttributeValueQuery();
				attributeValueQuery.createCriteria().andUserIdEqualTo(userId);
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
				userGroupRelationDeleteQuery.createCriteria().andUserIdEqualTo(userId);
				userGroupRelationDao.deleteByDeleteQuery(userGroupRelationDeleteQuery);
			}
			
			// User Relation
			{
				UserRelationDeleteQuery userRelationDeleteQuery = new UserRelationDeleteQuery();
				userRelationDeleteQuery.createCriteria().andUserIdEqualTo(userId);
				userRelationDeleteQuery.or().andRelUserIdEqualTo(userId);
				userRelationDao.deleteByDeleteQuery(userRelationDeleteQuery);
			}

			// Attribute Attribute
			{
				AttributeValueQuery attributeValueQuery = new AttributeValueQuery();
				attributeValueQuery.createCriteria().andUserIdEqualTo(userId);
				List<DefaultAttributeValue> attributeValues = attributeValueDao.queryByCriteria(attributeValueQuery);
				for (DefaultAttributeValue attributeValue : attributeValues) {
					attributeManager.remove(attributeValue.getAttrId());
				}
			}
		}

	}

	@Override
	public DefaultUser getByAccount(String account) {
		UserQuery query = new UserQuery();
		query.createCriteria().andAccountEqualTo(account);
		List<DefaultUser> users = queryByCriteria(query);
		if (users.size() == 0) {
			return null;
		} else {
			return users.get(0);
		}
	}

	@Override
	public List<DefaultUser> queryByUserRelation(String userKey, String relType) {
		DefaultRelationType relationType = relationTypeManager.get(relType);
		if(relationType==null){
			return new ArrayList<DefaultUser>();
		}
		///是双向关系
		if(relationType.getIsBidirectonal()==RelationType.IS_BIDIRECTONAL_Y){
			UserQuery query = new UserQuery();
			query.createUserRelationCriteria().andUserIdEqualTo(userKey).andRelTypeIdEqualTo(relType);
			query.orUserRelationCriteria().andRelUserIdEqualTo(userKey).andRelTypeIdEqualTo(relType);
			return queryByCriteria(query);
		}else{
			UserQuery query = new UserQuery();
			query.createUserRelationCriteria().andUserIdEqualTo(userKey).andRelTypeIdEqualTo(relType);
			return queryByCriteria(query);
		}
	}
	
	@Override
	public List<DefaultUser> queryByUserRelationRel(String relUserId, String relType) {
		DefaultRelationType relationType = relationTypeManager.get(relType);
		if(relationType==null){
			return new ArrayList<DefaultUser>();
		}
		///是双向关系
		if(relationType.getIsBidirectonal()==RelationType.IS_BIDIRECTONAL_Y){
			UserQuery query = new UserQuery();
			query.createUserRelationCriteria().andUserIdEqualTo(relUserId).andRelTypeIdEqualTo(relType);
			query.orUserRelationCriteria().andRelUserIdEqualTo(relUserId).andRelTypeIdEqualTo(relType);
			return queryByCriteria(query);
		}else{
			UserQuery query = new UserQuery();
			query.createUserRelationCriteria().andRelUserIdEqualTo(relUserId).andRelTypeIdEqualTo(relType);
			return queryByCriteria(query);
		}
	}

	@Override
	public List<DefaultUser> queryByUserGroupRelation(String groupKey, String relType) {
		UserQuery query = new UserQuery();
		query.createUserGroupCriteria().andGroupIdEqualTo(groupKey).andRelTypeIdEqualTo(relType);
		return queryByCriteria(query);
	}

	@Override
	public List<DefaultUser> queryUserByAttributeValue(String key, Object value) {
		UserQuery query = new UserQuery();

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
			return new ArrayList<DefaultUser>();
		}
		return queryByCriteria(query);
	}

	@Override
	public List<DefaultUser> queryByCriteria(UserQuery query) {
		return userDao.queryByCriteria(query);
	}

	@Override
	public List<DefaultUser> queryByCriteria(UserQuery query, Page page) {
		return userDao.queryByCriteria(query, page);
	}
}
