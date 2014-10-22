package com.hotent.org.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import java.util.List;

import com.hotent.org.api.model.Attribute;
import com.hotent.org.api.model.Group;
import com.hotent.org.api.model.GroupRelation;
import com.hotent.org.api.model.RelationType;
import com.hotent.org.api.model.UserGroupRelation;
import com.hotent.org.api.model.UserRelation;
import com.hotent.org.persistence.dao.GroupAuthorizationDao;
import com.hotent.org.persistence.dao.GroupRelationDao;
import com.hotent.org.persistence.dao.UserGroupRelationDao;
import com.hotent.org.persistence.dao.UserRelationDao;
import com.hotent.org.persistence.model.DefaultAttributeValue;
import com.hotent.org.persistence.model.DefaultGroupAuthorization;
import com.hotent.org.persistence.model.DefaultUserGroupRelation;
import com.hotent.base.db.api.Dao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.org.persistence.dao.RelationTypeDao;
import com.hotent.org.persistence.model.DefaultRelationType;
import com.hotent.org.persistence.manager.RelationTypeManager;
import com.hotent.org.persistence.query.AttributeUpdateQuery;
import com.hotent.org.persistence.query.AttributeValueQuery;
import com.hotent.org.persistence.query.GroupRelationDeleteQuery;
import com.hotent.org.persistence.query.GroupRelationUpdateQuery;
import com.hotent.org.persistence.query.GroupUpdateQuery;
import com.hotent.org.persistence.query.RelationTypeQuery;
import com.hotent.org.persistence.query.RelationTypeUpdateQuery;
import com.hotent.org.persistence.query.UserGroupRelationDeleteQuery;
import com.hotent.org.persistence.query.UserGroupRelationUpdateQuery;
import com.hotent.org.persistence.query.UserRelationDeleteQuery;
import com.hotent.org.persistence.query.UserRelationUpdateQuery;

@Service("relationTypeManager")
public class RelationTypeManagerImpl extends AbstractManagerImpl<String, DefaultRelationType> implements RelationTypeManager{
	@Resource
	RelationTypeDao relationTypeDao;
	@Resource
	GroupAuthorizationDao groupAuthorizationDao;
	@Resource
	UserGroupRelationDao userGroupRelationDao;
	
	@Resource
	UserRelationDao userRelationDao;

	@Resource
	GroupRelationDao groupRelationDao;
	
	@Override
	protected Dao<String, DefaultRelationType> getDao() {
		return relationTypeDao;
	}
	/**
	 * 创建实体包含子表实体
	 */
	public void create(DefaultRelationType relationType){
    	super.create(relationType);
    	groupAuthorizationDao.delByMainId(relationType.getId());
    	List<DefaultGroupAuthorization> groupAuthorizationList=relationType.getGroupAuthorizationList();
    	for(DefaultGroupAuthorization groupAuthorization:groupAuthorizationList){
    		groupAuthorizationDao.create(groupAuthorization);
    	}
    	userGroupRelationDao.delByMainId(relationType.getId());
    	List<DefaultUserGroupRelation> userGroupList=relationType.getDefaultUserGroupRelationList();
    	for(DefaultUserGroupRelation userGroup:userGroupList){
    		userGroupRelationDao.create(userGroup);
    	}
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
			RelationTypeUpdateQuery uQuery = new RelationTypeUpdateQuery();
			uQuery.createCriteria().andIdEqualTo(entityId);
			uQuery.getUpdateClause().setStatus(RelationType.STATUS_DELETED);
			relationTypeDao.updateByUpdateQuery(uQuery);
		} else {
			super.remove(entityId);
		}
	}

	private void ensureDataIntegrity(String relationTypeId, boolean logical) {
		
		if (logical) {
			// /User Group Relation
			{
				UserGroupRelationUpdateQuery userGroupRelationUQuery = new UserGroupRelationUpdateQuery();
				userGroupRelationUQuery.createCriteria().andRelTypeIdEqualTo(relationTypeId);
				userGroupRelationUQuery.getUpdateClause().setStatus(UserGroupRelation.STATUS_DELETED);
				userGroupRelationDao.updateByUpdateQuery(userGroupRelationUQuery);
			}

			// User Relation
			{
				UserRelationUpdateQuery userRelationUQuery = new UserRelationUpdateQuery();
				userRelationUQuery.createCriteria().andRelTypeIdEqualTo(relationTypeId);
				userRelationUQuery.getUpdateClause().setStatus(UserRelation.STATUS_DELETED);
				userRelationDao.updateByUpdateQuery(userRelationUQuery);
			}
			
			// Group Relation
			{
				GroupRelationUpdateQuery groupRelationUQuery = new GroupRelationUpdateQuery();
				groupRelationUQuery.createCriteria().andRelTypeIdEqualTo(relationTypeId);
				groupRelationUQuery.or().andRelTypeIdEqualTo(relationTypeId);
				groupRelationUQuery.getUpdateClause().setStatus(GroupRelation.STATUS_DELETED);
				groupRelationDao.updateByUpdateQuery(groupRelationUQuery);
			}

		} else {
			
			// /User Group Relation
			{
				UserGroupRelationDeleteQuery userGroupRelationDeleteQuery = new UserGroupRelationDeleteQuery();
				userGroupRelationDeleteQuery.createCriteria().andRelTypeIdEqualTo(relationTypeId);
				userGroupRelationDao.deleteByDeleteQuery(userGroupRelationDeleteQuery);
			}
			// User Relation
			{
				UserRelationDeleteQuery userRelationDeleteQuery = new UserRelationDeleteQuery();
				userRelationDeleteQuery.createCriteria().andRelTypeIdEqualTo(relationTypeId);
				userRelationDao.deleteByDeleteQuery(userRelationDeleteQuery);
			}
						
			// Group Relation
			{
				GroupRelationDeleteQuery groupRelationDeleteQuery = new GroupRelationDeleteQuery();
				groupRelationDeleteQuery.createCriteria().andRelTypeIdEqualTo(relationTypeId);
				groupRelationDao.deleteByDeleteQuery(groupRelationDeleteQuery);
			}

		}

	}
	@Override
	public DefaultRelationType queryByUserRelation(String userRelationId) {
		RelationTypeQuery query = new RelationTypeQuery();
		query.createUserRelationCriteria().andRelIdEqualTo(userRelationId);
		List<DefaultRelationType> relationTypes = relationTypeDao.queryByCriteria(query);
		if(relationTypes.isEmpty()){
			return null;
		}
		return relationTypes.get(0);
	}
	@Override
	public DefaultRelationType queryByGroupRelation(String groupRelationId) {
		RelationTypeQuery query = new RelationTypeQuery();
		query.createGroupRelationCriteria().andIdEqualTo(groupRelationId);
		List<DefaultRelationType> relationTypes = relationTypeDao.queryByCriteria(query);
		if(relationTypes.isEmpty()){
			return null;
		}
		return relationTypes.get(0);
	}
	@Override
	public DefaultRelationType queryByUserGroupRelation(String userGroupRelationId) {
		RelationTypeQuery query = new RelationTypeQuery();
		query.createUserGroupCriteria().andRelIdEqualTo(userGroupRelationId);
		List<DefaultRelationType> relationTypes = relationTypeDao.queryByCriteria(query);
		if(relationTypes.isEmpty()){
			return null;
		}
		return relationTypes.get(0);
	}
	
}
