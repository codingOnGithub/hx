package com.hotent.org.persistence.manager.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.base.api.Page;
import com.hotent.base.core.util.BeanUtils;
import com.hotent.base.db.api.Dao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.org.api.OrgException;
import com.hotent.org.api.model.Attribute;
import com.hotent.org.api.model.RelationType;
import com.hotent.org.api.model.User;
import com.hotent.org.api.model.User.Status;
import com.hotent.org.api.model.UserGroupRelation;
import com.hotent.org.api.model.UserRelation;
import com.hotent.org.api.model.RelationType.ConstType;
import com.hotent.org.api.model.RelationType.IsBidirection;
import com.hotent.org.api.model.RelationType.Type;
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
import com.hotent.org.persistence.model.DefaultGroupRelation;
import com.hotent.org.persistence.model.DefaultRelationType;
import com.hotent.org.persistence.model.DefaultUser;
import com.hotent.org.persistence.model.DefaultUserGroupRelation;
import com.hotent.org.persistence.model.DefaultUserRelation;
import com.hotent.org.persistence.query.AttributeQuery;
import com.hotent.org.persistence.query.AttributeValueQuery;
import com.hotent.org.persistence.query.GroupRelationQuery;
import com.hotent.org.persistence.query.AttributeValueQuery.FindQuery;
import com.hotent.org.persistence.query.RelationTypeQuery;
import com.hotent.org.persistence.query.UserGroupRelationQuery;
import com.hotent.org.persistence.query.UserGroupRelationQuery.UpdateQuery;
import com.hotent.org.persistence.query.UserQuery;
import com.hotent.org.persistence.query.UserQuery.DeleteQuery;
import com.hotent.org.persistence.query.UserRelationQuery;

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
		UserQuery.FindQuery query = new UserQuery.FindQuery();
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
		ensureDataIntegrityAtRemove(entityId, logical);
		if (logical) {
			UserQuery.UpdateQuery uQuery = new UserQuery.UpdateQuery();
			uQuery.createCriteria().andUserIdEqualTo(entityId);
			uQuery.getUpdateClause().setStatus(User.Status.deleted);
			userDao.updateByUpdateQuery(uQuery);
		} else {
			super.remove(entityId);
		}
	}

	private void ensureDataIntegrityAtRemove(String userId, boolean logical) {

		if (logical) {
			// /User Group Relation
			{
				UserGroupRelationQuery.UpdateQuery userGroupRelationUQuery = new UserGroupRelationQuery.UpdateQuery();
				userGroupRelationUQuery.createCriteria().andUserIdEqualTo(userId);
				userGroupRelationUQuery.getUpdateClause().setStatus(UserGroupRelation.Status.archived);
				userGroupRelationDao.updateByUpdateQuery(userGroupRelationUQuery);
			}

			// User Relation
			{
				UserRelationQuery.UpdateQuery userRelationUQuery = new UserRelationQuery.UpdateQuery();
				userRelationUQuery.createCriteria().andUserIdEqualTo(userId);
				userRelationUQuery.or().andRelUserIdEqualTo(userId);
				userRelationUQuery.getUpdateClause().setStatus(UserRelation.Status.archived);
				userRelationDao.updateByUpdateQuery(userRelationUQuery);
			}

			// Attribute Attribute
			{
				AttributeValueQuery.FindQuery attributeValueQuery = new AttributeValueQuery.FindQuery();
				attributeValueQuery.createCriteria().andUserIdEqualTo(userId);
				List<DefaultAttributeValue> attributeValues = attributeValueDao.queryByCriteria(attributeValueQuery);
				for (DefaultAttributeValue attributeValue : attributeValues) {
					AttributeQuery.UpdateQuery attributeUQuery = new AttributeQuery.UpdateQuery();
					attributeUQuery.createCriteria().andAttrIdEqualTo(attributeValue.getAttrId());
					attributeUQuery.getUpdateClause().setStatus(Attribute.Status.deleted);
					attributeDao.updateByUpdateQuery(attributeUQuery);
				}
			}
		} else {
			
			// /User Group Relation
			{
				UserGroupRelationQuery.DeleteQuery userGroupRelationDeleteQuery = new UserGroupRelationQuery.DeleteQuery();
				userGroupRelationDeleteQuery.createCriteria().andUserIdEqualTo(userId);
				userGroupRelationDao.deleteByDeleteQuery(userGroupRelationDeleteQuery);
			}
			
			// User Relation
			{
				UserRelationQuery.DeleteQuery userRelationDeleteQuery = new UserRelationQuery.DeleteQuery();
				userRelationDeleteQuery.createCriteria().andUserIdEqualTo(userId);
				userRelationDeleteQuery.or().andRelUserIdEqualTo(userId);
				userRelationDao.deleteByDeleteQuery(userRelationDeleteQuery);
			}

			// Attribute Attribute
			{
				AttributeValueQuery.FindQuery attributeValueQuery = new AttributeValueQuery.FindQuery();
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
		UserQuery.FindQuery query = new UserQuery.FindQuery();
		query.createCriteria().andAccountEqualTo(account);
		List<DefaultUser> users = queryByCriteria(query);
		if (users.size() == 0) {
			return null;
		} else {
			return users.get(0);
		}
	}

	@Override
	public List<DefaultUser> queryByUserRelation(String userId, String relType) {
		return this.queryByUserRelation(userId, relType, null);
	}
	
	

	@Override
	public List<DefaultUser> queryByUserRelation(String userKey, String relType, List<Status> statuses) {
		DefaultRelationType relationType = relationTypeManager.get(relType);
		if(relationType==null){
			return new ArrayList<DefaultUser>();
		}
		UserQuery.FindQuery query = new UserQuery.FindQuery();
		///是双向关系
		if(relationType.getIsBidirectonal()==IsBidirection.Y){
			query.createUserRelationCriteria().andUserIdEqualTo(userKey).andRelTypeIdEqualTo(relType);
			query.orUserRelationCriteria().andRelUserIdEqualTo(userKey).andRelTypeIdEqualTo(relType);
		}else{
			query.createUserRelationCriteria().andUserIdEqualTo(userKey).andRelTypeIdEqualTo(relType);
		}
		
		if(BeanUtils.isNotEmpty(statuses)){
			query.createCriteria().andStatusIn(statuses);
		}
		return queryByCriteria(query);
	}

	
	@Override
	public List<DefaultUser> queryByUserRelationRel(String relUserId, String relType) {
		return this.queryByUserRelationRel(relUserId, relType, null);
	}
	
	@Override
	public List<DefaultUser> queryByUserRelationRel(String relUserId, String relType, List<Status> statuses) {
		DefaultRelationType relationType = relationTypeManager.get(relType);
		if(relationType==null){
			return new ArrayList<DefaultUser>();
		}
		UserQuery.FindQuery query = new UserQuery.FindQuery();
		///是双向关系
		if(relationType.getIsBidirectonal()==IsBidirection.Y){
			query.createUserRelationCriteria().andUserIdEqualTo(relUserId).andRelTypeIdEqualTo(relType);
			query.orUserRelationCriteria().andRelUserIdEqualTo(relUserId).andRelTypeIdEqualTo(relType);
		}else{
			query.createUserRelationCriteria().andRelUserIdEqualTo(relUserId).andRelTypeIdEqualTo(relType);
		}
		if(BeanUtils.isNotEmpty(statuses)){
			query.createCriteria().andStatusIn(statuses);
		}
		return queryByCriteria(query);
	}

	@Override
	public List<DefaultUser> queryByUserGroupRelation(String groupKey, String relType) {
		return this.queryByUserGroupRelation(groupKey, relType, null);
	}

	
	@Override
	public List<DefaultUser> queryByUserGroupRelation(String groupKey, String relType, List<Status> statuses) {
		UserQuery.FindQuery query = new UserQuery.FindQuery();
		query.createUserGroupCriteria().andGroupIdEqualTo(groupKey).andRelTypeIdEqualTo(relType);
		if(BeanUtils.isNotEmpty(statuses)){
			query.createCriteria().andStatusIn(statuses);
		}
		return queryByCriteria(query);
	}

	
	
	
	@Override
	public List<DefaultUser> queryByAttributeValue(String key, Object value) {
		return this.queryByAttributeValue(key, value, null);
	}

	

	@Override
	public List<DefaultUser> queryByAttributeValue(String key, Object value, List<Status> statuses) {
		UserQuery.FindQuery query = new UserQuery.FindQuery();

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
		
		if(BeanUtils.isNotEmpty(statuses)){
			query.createCriteria().andStatusIn(statuses);
		}
		
		return queryByCriteria(query);
	}

	@Override
	public List<DefaultUser> queryByCriteria(UserQuery.FindQuery query) {
		return userDao.queryByCriteria(query);
	}

	@Override
	public List<DefaultUser> queryByCriteria(UserQuery.FindQuery query, Page page) {
		return userDao.queryByCriteria(query, page);
	}

	@Override
	public long queryCountByCriteria(UserQuery.FindQuery query) {
		return userDao.queryCountByCriteria(query);
	}

	@Override
	public void updateByUpdateQuery(UserQuery.UpdateQuery uQuery) {
		userDao.updateByUpdateQuery(uQuery);
		
	}

	@Override
	public void deleteByDeleteQuery(DeleteQuery dQuery) {
		userDao.deleteByDeleteQuery(dQuery);
	}
	
	
	// ////
	private void ensureDataIntegriyAtCreateUserRelation(DefaultUserRelation userRelation) {
		DefaultRelationType relationType = relationTypeManager.get(userRelation.getRelTypeId());
		if (relationType == null||relationType.getStatus()==RelationType.Status.deleted) {
			throw new OrgException("User-Group relation must relate a valid realtion type.");
		}
		
		if(relationType.getType()!=Type.user){
			throw new OrgException("User-User relation must relate a valid realtion type with type of 'user'.");
		}
	}
	
	@Override
	public void createUserRelation(DefaultUserRelation relation) {
		ensureDataIntegriyAtCreateUserRelation(relation);
		DefaultRelationType relationType = relationTypeManager.get(relation.getRelTypeId());
		if (relationType.getIsBidirectonal() == IsBidirection.Y) {
			UserRelationQuery.FindQuery relationQuery = new UserRelationQuery.FindQuery();
			relationQuery.createCriteria().andUserIdEqualTo(relation.getUserId()).andRelUserIdEqualTo(relation.getRelUserId()).andRelTypeIdEqualTo(relation.getRelTypeId());
			relationQuery.or().andUserIdEqualTo(relation.getRelUserId()).andRelUserIdEqualTo(relation.getUserId()).andRelTypeIdEqualTo(relation.getRelTypeId());
			long cnt = userRelationDao.queryCountByCriteria(relationQuery);
			if (cnt > 0) {
				throw new OrgException("用户-用户关联已存在!");
			}
		} else {
			UserRelationQuery.FindQuery relationQuery = new UserRelationQuery.FindQuery();
			relationQuery.createCriteria().andUserIdEqualTo(relation.getUserId()).andRelUserIdEqualTo(relation.getRelUserId()).andRelTypeIdEqualTo(relation.getRelTypeId());
			long cnt = userRelationDao.queryCountByCriteria(relationQuery);
			if (cnt > 0) {
				throw new OrgException("用户-用户关联已存在!");
			}
		}
		userRelationDao.create(relation);
	}


}
