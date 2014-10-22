package com.hotent.org.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import java.util.List;

import com.hotent.org.api.model.Attribute;
import com.hotent.org.api.model.Group;
import com.hotent.org.api.model.GroupRelation;
import com.hotent.org.api.model.RelationType;
import com.hotent.org.api.model.RelationType.Status;
import com.hotent.org.api.model.UserGroupRelation;
import com.hotent.org.api.model.UserRelation;
import com.hotent.org.persistence.dao.GroupAuthorizationDao;
import com.hotent.org.persistence.dao.GroupRelationDao;
import com.hotent.org.persistence.dao.UserGroupRelationDao;
import com.hotent.org.persistence.dao.UserRelationDao;
import com.hotent.org.persistence.model.DefaultAttributeValue;
import com.hotent.org.persistence.model.DefaultDimension;
import com.hotent.org.persistence.model.DefaultGroupAuthorization;
import com.hotent.org.persistence.model.DefaultUserGroupRelation;
import com.hotent.base.api.Page;
import com.hotent.base.db.api.Dao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.org.persistence.dao.RelationTypeDao;
import com.hotent.org.persistence.model.DefaultRelationType;
import com.hotent.org.persistence.manager.RelationTypeManager;
import com.hotent.org.persistence.query.AttributeValueQuery;
import com.hotent.org.persistence.query.GroupRelationQuery;
import com.hotent.org.persistence.query.GroupRelationQuery.DeleteQuery;
import com.hotent.org.persistence.query.GroupRelationQuery.UpdateQuery;
import com.hotent.org.persistence.query.RelationTypeQuery;
import com.hotent.org.persistence.query.RelationTypeQuery.FindQuery;
import com.hotent.org.persistence.query.UserGroupRelationQuery;
import com.hotent.org.persistence.query.UserRelationQuery;

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
			RelationTypeQuery.UpdateQuery uQuery = new RelationTypeQuery.UpdateQuery();
			uQuery.createCriteria().andIdEqualTo(entityId);
			uQuery.getUpdateClause().setStatus(Status.deleted);
			relationTypeDao.updateByUpdateQuery(uQuery);
		} else {
			super.remove(entityId);
		}
	}

	private void ensureDataIntegrity(String relationTypeId, boolean logical) {
		
		if (logical) {
			// /User Group Relation
			{
				UserGroupRelationQuery.UpdateQuery userGroupRelationUQuery = new UserGroupRelationQuery.UpdateQuery();
				userGroupRelationUQuery.createCriteria().andRelTypeIdEqualTo(relationTypeId);
				userGroupRelationUQuery.getUpdateClause().setStatus(UserGroupRelation.Status.archived);
				userGroupRelationDao.updateByUpdateQuery(userGroupRelationUQuery);
			}

			// User Relation
			{
				UserRelationQuery.UpdateQuery userRelationUQuery = new UserRelationQuery.UpdateQuery();
				userRelationUQuery.createCriteria().andRelTypeIdEqualTo(relationTypeId);
				userRelationUQuery.getUpdateClause().setStatus(com.hotent.org.api.model.UserRelation.Status.archived);
				userRelationDao.updateByUpdateQuery(userRelationUQuery);
			}
			
			// Group Relation
			{
				GroupRelationQuery.UpdateQuery groupRelationUQuery = new GroupRelationQuery.UpdateQuery();
				groupRelationUQuery.createCriteria().andRelTypeIdEqualTo(relationTypeId);
				groupRelationUQuery.or().andRelTypeIdEqualTo(relationTypeId);
				groupRelationUQuery.getUpdateClause().setStatus(GroupRelation.Status.archived);
				groupRelationDao.updateByUpdateQuery(groupRelationUQuery);
			}

		} else {
			
			// /User Group Relation
			{
				UserGroupRelationQuery.DeleteQuery userGroupRelationDeleteQuery = new UserGroupRelationQuery.DeleteQuery();
				userGroupRelationDeleteQuery.createCriteria().andRelTypeIdEqualTo(relationTypeId);
				userGroupRelationDao.deleteByDeleteQuery(userGroupRelationDeleteQuery);
			}
			// User Relation
			{
				UserRelationQuery.DeleteQuery userRelationDeleteQuery = new UserRelationQuery.DeleteQuery();
				userRelationDeleteQuery.createCriteria().andRelTypeIdEqualTo(relationTypeId);
				userRelationDao.deleteByDeleteQuery(userRelationDeleteQuery);
			}
						
			// Group Relation
			{
				GroupRelationQuery.DeleteQuery groupRelationDeleteQuery = new GroupRelationQuery.DeleteQuery();
				groupRelationDeleteQuery.createCriteria().andRelTypeIdEqualTo(relationTypeId);
				groupRelationDao.deleteByDeleteQuery(groupRelationDeleteQuery);
			}

		}

	}
	@Override
	public DefaultRelationType queryByUserRelation(String userRelationId) {
		RelationTypeQuery.FindQuery query = new RelationTypeQuery.FindQuery();
		query.createUserRelationCriteria().andRelIdEqualTo(userRelationId);
		List<DefaultRelationType> relationTypes = relationTypeDao.queryByCriteria(query);
		if(relationTypes.isEmpty()){
			return null;
		}
		return relationTypes.get(0);
	}
	@Override
	public DefaultRelationType queryByGroupRelation(String groupRelationId) {
		RelationTypeQuery.FindQuery query = new RelationTypeQuery.FindQuery();
		query.createGroupRelationCriteria().andIdEqualTo(groupRelationId);
		List<DefaultRelationType> relationTypes = relationTypeDao.queryByCriteria(query);
		if(relationTypes.isEmpty()){
			return null;
		}
		return relationTypes.get(0);
	}
	@Override
	public DefaultRelationType queryByUserGroupRelation(String userGroupRelationId) {
		RelationTypeQuery.FindQuery query = new RelationTypeQuery.FindQuery();
		query.createUserGroupCriteria().andRelIdEqualTo(userGroupRelationId);
		List<DefaultRelationType> relationTypes = relationTypeDao.queryByCriteria(query);
		if(relationTypes.isEmpty()){
			return null;
		}
		return relationTypes.get(0);
	}
	@Override
	public List<DefaultRelationType> queryByCriteria(FindQuery query) {
		return relationTypeDao.queryByCriteria(query);
	}
	@Override
	public List<DefaultRelationType> queryByCriteria(FindQuery query, Page page) {
		return relationTypeDao.queryByCriteria(query, page);
	}
	@Override
	public long queryCountByCriteria(FindQuery query) {
		return relationTypeDao.queryCountByCriteria(query);
	}
	@Override
	public void updateByUpdateQuery(com.hotent.org.persistence.query.RelationTypeQuery.UpdateQuery uQuery) {
		relationTypeDao.updateByUpdateQuery(uQuery);
		
	}
	@Override
	public void deleteByDeleteQuery(com.hotent.org.persistence.query.RelationTypeQuery.DeleteQuery dQuery) {
		relationTypeDao.deleteByDeleteQuery(dQuery);
		
	}
	
}
