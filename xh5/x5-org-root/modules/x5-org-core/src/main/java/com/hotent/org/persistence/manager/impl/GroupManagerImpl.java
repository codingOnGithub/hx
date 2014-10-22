package com.hotent.org.persistence.manager.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.hotent.base.api.Page;
import com.hotent.base.core.util.BeanUtils;
import com.hotent.base.db.api.Dao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.org.api.OrgException;
import com.hotent.org.api.model.Attribute;
import com.hotent.org.api.model.Attribute.DataType;
import com.hotent.org.api.model.AttributeValue;
import com.hotent.org.api.model.Dimension;
import com.hotent.org.api.model.Group;
import com.hotent.org.api.model.GroupRelation;
import com.hotent.org.api.model.RelationType;
import com.hotent.org.api.model.User;
import com.hotent.org.api.model.UserGroupRelation;
import com.hotent.org.api.model.UserGroupRelation.Status;
import com.hotent.org.api.model.UserRelation;
import com.hotent.org.api.model.RelationType.IsBidirection;
import com.hotent.org.api.model.RelationType.Type;
import com.hotent.org.persistence.dao.AttributeDao;
import com.hotent.org.persistence.dao.AttributeValueDao;
import com.hotent.org.persistence.dao.GroupDao;
import com.hotent.org.persistence.dao.GroupRelationDao;
import com.hotent.org.persistence.dao.UserGroupRelationDao;
import com.hotent.org.persistence.manager.AttributeManager;
import com.hotent.org.persistence.manager.DimensionManager;
import com.hotent.org.persistence.manager.GroupManager;
import com.hotent.org.persistence.manager.RelationTypeManager;
import com.hotent.org.persistence.model.DefaultAttribute;
import com.hotent.org.persistence.model.DefaultAttributeValue;
import com.hotent.org.persistence.model.DefaultDimension;
import com.hotent.org.persistence.model.DefaultGroup;
import com.hotent.org.persistence.model.DefaultGroupRelation;
import com.hotent.org.persistence.model.DefaultRelationType;
import com.hotent.org.persistence.model.DefaultUserGroupRelation;
import com.hotent.org.persistence.query.AttributeQuery;
import com.hotent.org.persistence.query.AttributeValueQuery;
import com.hotent.org.persistence.query.DimensionQuery;
import com.hotent.org.persistence.query.DimensionQuery.FindQuery;
import com.hotent.org.persistence.query.GroupQuery;
import com.hotent.org.persistence.query.GroupRelationQuery;
import com.hotent.org.persistence.query.UserGroupRelationQuery;

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
		GroupQuery.FindQuery query = new GroupQuery.FindQuery();
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
		GroupQuery.DeleteQuery dQuery = new GroupQuery.DeleteQuery();
		dQuery.createCriteria().andKeyEqualTo(groupKey);
		groupDao.deleteByDeleteQuery(dQuery);
	}

	@Override
	public List<DefaultGroup> queryByDimId(String dimId) {
		GroupQuery.FindQuery query = new GroupQuery.FindQuery();
		Dimension dimension = dimensionManager.get(dimId);
		if (dimension == null) {
			return new ArrayList<DefaultGroup>();
		}
		query.createCriteria().andDimIdEqualTo(dimension.getDimKey());
		return groupDao.queryByCriteria(query);
	}

	@Override
	public List<DefaultGroup> queryByUserId(String userId, Status status) {
		GroupQuery.FindQuery query = new GroupQuery.FindQuery();
		query.createUserGroupCriteria().andUserIdEqualTo(userId).andStatusEqualTo(status);
		return groupDao.queryByCriteria(query);
	}

	@Override
	public List<DefaultGroup> queryByUserId(String userId, Status status, Page page) {
		GroupQuery.FindQuery query = new GroupQuery.FindQuery();
		query.createUserGroupCriteria().andUserIdEqualTo(userId).andStatusEqualTo(status);
		return groupDao.queryByCriteria(query, page);
	}

	@Override
	public List<DefaultGroup> queryByGroupRelation(String groupKey, String relType) {
		return this.queryByGroupRelation(groupKey, relType, null);
	}

	@Override
	public List<DefaultGroup> queryByGroupRelation(String groupId, String relType, List<com.hotent.org.api.model.Group.Status> statuses) {
		DefaultRelationType relationType = relationTypeManager.get(relType);
		if (relationType == null) {
			return new ArrayList<DefaultGroup>();
		}
		// /是双向关系
		GroupQuery.FindQuery query = new GroupQuery.FindQuery();
		if (relationType.getIsBidirectonal() == IsBidirection.Y) {
			query.createGroupRelationCriteria().andGroupIdEqualTo(groupId).andRelTypeIdEqualTo(relType);
			query.orGroupRelationCriteria().andRelGroupIdEqualTo(groupId).andRelTypeIdEqualTo(relType);

		} else {
			query.createGroupRelationCriteria().andGroupIdEqualTo(groupId).andRelTypeIdEqualTo(relType);
		}
		if (BeanUtils.isNotEmpty(statuses)) {
			query.createCriteria().andStatusIn(statuses);
		}
		return queryByCriteria(query);
	}

	@Override
	public List<DefaultGroup> queryByGroupRelationRel(String groupId, String relType) {
		return this.queryByGroupRelationRel(groupId, relType, null);
	}

	@Override
	public List<DefaultGroup> queryByGroupRelationRel(String groupId, String relType, List<com.hotent.org.api.model.Group.Status> statuses) {
		DefaultRelationType relationType = relationTypeManager.get(relType);
		if (relationType == null) {
			return new ArrayList<DefaultGroup>();
		}
		GroupQuery.FindQuery query = new GroupQuery.FindQuery();
		// /是双向关系
		if (relationType.getIsBidirectonal() == IsBidirection.Y) {
			query.createGroupRelationCriteria().andGroupIdEqualTo(groupId).andRelTypeIdEqualTo(relType);
			query.orGroupRelationCriteria().andRelGroupIdEqualTo(groupId).andRelTypeIdEqualTo(relType);
		} else {
			query.createGroupRelationCriteria().andRelGroupIdEqualTo(groupId).andRelTypeIdEqualTo(relType);
		}
		if (BeanUtils.isNotEmpty(statuses)) {
			query.createCriteria().andStatusIn(statuses);
		}
		return queryByCriteria(query);

	}

	@Override
	public List<DefaultGroup> queryByUserGroupRelation(String userId, String relType) {
		return this.queryByUserGroupRelation(userId, relType, null);
	}

	@Override
	public List<DefaultGroup> queryByUserGroupRelation(String userId, String relType, List<com.hotent.org.api.model.Group.Status> statuses) {

		GroupQuery.FindQuery query = new GroupQuery.FindQuery();
		query.createUserGroupCriteria().andUserIdEqualTo(userId).andRelTypeIdEqualTo(relType);
		if (BeanUtils.isNotEmpty(statuses)) {
			query.createCriteria().andStatusIn(statuses);
		}
		return groupDao.queryByCriteria(query);
	}

	@Override
	public List<DefaultGroup> queryGroupByAttributeValue(String key, Object value) {
		return this.queryGroupByAttributeValue(key, value, null);
	}

	@Override
	public List<DefaultGroup> queryGroupByAttributeValue(String key, Object value, List<com.hotent.org.api.model.Group.Status> statuses) {

		DefaultAttribute attribute = attributeManager.getByAttrKey(key);
		if (attribute == null) {
			return new ArrayList<DefaultGroup>();
		}

		GroupQuery.FindQuery query = new GroupQuery.FindQuery();
		AttributeValueQuery.Criteria attributeValueCriteria = query.createAttributeValueCriteria().andAttrKeyEqualTo(key);
		if (attribute.getDataType() == DataType.DATE) {
			if (Date.class.isAssignableFrom(value.getClass())) {
				attributeValueCriteria.andDateValEqualTo((Date) value);
			} else {
				return new ArrayList<DefaultGroup>();
			}
		} else if (attribute.getDataType() == DataType.LONG) {
			if (Long.class.isAssignableFrom(value.getClass())) {
				attributeValueCriteria.andLongValEqualTo((Long) value);
			} else {
				return new ArrayList<DefaultGroup>();
			}
		} else if (attribute.getDataType() == DataType.DOUBLE) {
			if (Double.class.isAssignableFrom(value.getClass())) {
				attributeValueCriteria.andDecValEqualTo((Double) value);
			} else {
				return new ArrayList<DefaultGroup>();
			}
		} else if (attribute.getDataType() == DataType.TEXT) {
			if (String.class.isAssignableFrom(value.getClass())) {
				attributeValueCriteria.andTextValEqualTo((String) value);
			} else {
				return new ArrayList<DefaultGroup>();
			}
		} else {
			return new ArrayList<DefaultGroup>();
		}

		if (BeanUtils.isNotEmpty(statuses)) {
			query.createCriteria().andStatusIn(statuses);
		}

		return queryByCriteria(query);
	}

	@Override
	public List<DefaultGroup> queryByCriteria(GroupQuery.FindQuery query) {
		return groupDao.queryByCriteria(query);
	}

	@Override
	public List<DefaultGroup> queryByCriteria(GroupQuery.FindQuery query, Page page) {
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
		ensureDataIntegrityAtRemove(entityId, logical);
		if (logical) {
			GroupQuery.UpdateQuery uQuery = new GroupQuery.UpdateQuery();
			uQuery.createCriteria().andGroupIdEqualTo(entityId);
			uQuery.getUpdateClause().setStatus(Group.Status.deleted);
			groupDao.updateByUpdateQuery(uQuery);
		} else {
			super.remove(entityId);
		}
	}

	private void ensureDataIntegrityAtRemove(String groupId, boolean logical) {
		// delete children
		{
			GroupQuery.FindQuery query = new com.hotent.org.persistence.query.GroupQuery.FindQuery();
			query.createCriteria().andParentIdEqualTo(groupId).andStatusNotEqualTo(Group.Status.deleted);
			List<DefaultGroup> children = this.queryByCriteria(query);
			for (DefaultGroup g : children) {
				this.remove(g.getGroupId(), logical);
			}
		}

		if (logical) {
			// /User Group Relation
			{
				UserGroupRelationQuery.UpdateQuery userGroupRelationUQuery = new UserGroupRelationQuery.UpdateQuery();
				userGroupRelationUQuery.createCriteria().andGroupIdEqualTo(groupId);
				userGroupRelationUQuery.getUpdateClause().setStatus(UserGroupRelation.Status.archived);
				userGroupRelationDao.updateByUpdateQuery(userGroupRelationUQuery);
			}

			// User Relation
			{
				GroupRelationQuery.UpdateQuery groupRelationUQuery = new GroupRelationQuery.UpdateQuery();
				groupRelationUQuery.createCriteria().andGroupIdEqualTo(groupId);
				groupRelationUQuery.or().andRelGroupIdEqualTo(groupId);
				groupRelationUQuery.getUpdateClause().setStatus(GroupRelation.Status.archived);
				groupRelationDao.updateByUpdateQuery(groupRelationUQuery);
			}

			// Attribute Attribute
			// {
			// AttributeValueQuery.UpdateQuery uQuery = new
			// AttributeValueQuery.UpdateQuery();
			// uQuery.createCriteria().andGroupIdEqualTo(groupId);
			// attributeValueDao.updateByUpdateQuery(uQuery);
			// }
		} else {

			// /User Group Relation
			{
				UserGroupRelationQuery.DeleteQuery userGroupRelationDeleteQuery = new UserGroupRelationQuery.DeleteQuery();
				userGroupRelationDeleteQuery.createCriteria().andGroupIdEqualTo(groupId);
				userGroupRelationDao.deleteByDeleteQuery(userGroupRelationDeleteQuery);
			}

			// Group Relation
			{
				GroupRelationQuery.DeleteQuery groupRelationDeleteQuery = new GroupRelationQuery.DeleteQuery();
				groupRelationDeleteQuery.createCriteria().andGroupIdEqualTo(groupId);
				groupRelationDeleteQuery.or().andRelGroupIdEqualTo(groupId);
				groupRelationDao.deleteByDeleteQuery(groupRelationDeleteQuery);
			}

			// Attribute Attribute
			{
				AttributeValueQuery.DeleteQuery dQuery = new AttributeValueQuery.DeleteQuery();
				dQuery.createCriteria().andGroupIdEqualTo(groupId);
				attributeValueDao.deleteByDeleteQuery(dQuery);
			}
		}

	}

	// ////
	private void ensureDataIntegrityAtCreateUserGroupRelation(DefaultUserGroupRelation userGroupRelation) {
		DefaultRelationType relationType = relationTypeManager.get(userGroupRelation.getRelTypeId());
		if (relationType == null || relationType.getStatus() == RelationType.Status.deleted) {
			throw new OrgException("User-Group relation must relate a valid realtion type.");
		}

		if (relationType.getType() != Type.usergroup) {
			throw new OrgException("User-Group relation must relate a valid realtion type with type of 'usergroup'.");
		}

		if (!relationType.getRelDimId().equals(userGroupRelation.getDimId())) {
			throw new OrgException("In User-Group relation,group's dimension must same as the dimension of realtion-type which specified.");
		}

		DefaultGroup group = this.get(userGroupRelation.getGroupId());
		if (group == null) {
			throw new OrgException("用户-组 关联,关联组必须是有效的用户组。");
		}
		if (!group.getDimId().equals(relationType.getRelDimId())) {
			throw new OrgException("用户-组 关联,关联维度必须与关联组的维度一致。");
		}
	}

	private void ensureDataIntegrityAtCreateGroupRelation(DefaultGroupRelation groupRelation) {
		DefaultRelationType relationType = relationTypeManager.get(groupRelation.getRelTypeId());
		if (relationType == null || relationType.getStatus() == RelationType.Status.deleted) {
			throw new OrgException("Group-Group relation must relate a valid realtion type.");
		}

		if (relationType.getType() != Type.group) {
			throw new OrgException("Group-Group relation must relate a valid realtion type with type of 'group'.");
		}

		if (!relationType.getCurrentDimId().equals(groupRelation.getCurrentDimId()) || !relationType.getRelDimId().equals(groupRelation.getRelDimId())) {
			throw new OrgException("In Group-Group relation,group's dimension must same as the dimension of realtion-type which specified.");
		}

		DefaultGroup group = this.get(groupRelation.getGroupId());
		if (group == null) {
			throw new OrgException("用户组-用户组 关联，当前组必须是有效的用户组。");
		}
		if (!group.getDimId().equals(relationType.getCurrentDimId())) {
			throw new OrgException("用户组-用户组 关联，当前维度必须与当前组的维度一致。");
		}

		DefaultGroup relGroup = this.get(groupRelation.getRelGroupId());
		if (relGroup == null) {
			throw new OrgException("用户组-用户组 关联，关联组必须是有效的用户组。");
		}
		if (!relGroup.getDimId().equals(relationType.getRelDimId())) {
			throw new OrgException("用户组-用户组 关联，关联维度必须与关联组的维度一致。");
		}
	}

	@Override
	public void createUserGroupRelation(DefaultUserGroupRelation userGroupRelation) {
		ensureDataIntegrityAtCreateUserGroupRelation(userGroupRelation);

		UserGroupRelationQuery.FindQuery userGroupRelationQuery = new UserGroupRelationQuery.FindQuery();
		userGroupRelationQuery.createCriteria().andUserIdEqualTo(userGroupRelation.getUserId()).andGroupIdEqualTo(userGroupRelation.getGroupId())
				.andRelTypeIdEqualTo(userGroupRelation.getRelTypeId());
		long cnt = userGroupRelationDao.queryCountByCriteria(userGroupRelationQuery);
		if (cnt > 0) {
			throw new OrgException("用户组织关系已存在!");
		}
		userGroupRelationDao.create(userGroupRelation);
	}

	@Override
	public void createGroupRelation(DefaultGroupRelation groupRelation) {
		ensureDataIntegrityAtCreateGroupRelation(groupRelation);

		DefaultRelationType relationType = relationTypeManager.get(groupRelation.getRelTypeId());
		if (relationType.getIsBidirectonal() == IsBidirection.Y) {
			GroupRelationQuery.FindQuery groupRelationQuery = new GroupRelationQuery.FindQuery();
			groupRelationQuery.createCriteria().andGroupIdEqualTo(groupRelation.getGroupId()).andRelGroupIdEqualTo(groupRelation.getRelGroupId())
					.andRelTypeIdEqualTo(groupRelation.getRelTypeId());
			groupRelationQuery.or().andGroupIdEqualTo(groupRelation.getRelGroupId()).andRelGroupIdEqualTo(groupRelation.getGroupId())
					.andRelTypeIdEqualTo(groupRelation.getRelTypeId());
			long cnt = groupRelationDao.queryCountByCriteria(groupRelationQuery);
			if (cnt > 0) {
				throw new OrgException("组织、组织关系已存在!");
			}
		} else {
			GroupRelationQuery.FindQuery groupRelationQuery = new GroupRelationQuery.FindQuery();
			groupRelationQuery.createCriteria().andGroupIdEqualTo(groupRelation.getGroupId()).andRelGroupIdEqualTo(groupRelation.getRelGroupId())
					.andRelTypeIdEqualTo(groupRelation.getRelTypeId());
			long cnt = groupRelationDao.queryCountByCriteria(groupRelationQuery);
			if (cnt > 0) {
				throw new OrgException("组织、组织关系已存在!");
			}
		}
		groupRelationDao.create(groupRelation);
	}

	@Override
	public long queryCountByCriteria(GroupQuery.FindQuery query) {
		return groupDao.queryCountByCriteria(query);
	}

	@Override
	public void updateByUpdateQuery(GroupQuery.UpdateQuery uQuery) {
		groupDao.updateByUpdateQuery(uQuery);
	}

	@Override
	public void deleteByDeleteQuery(GroupQuery.DeleteQuery dQuery) {
		groupDao.deleteByDeleteQuery(dQuery);
	}

	/**
	 * 1、保证用户组指定的维度是用效的。 2、保证用户组指定的父用户组是有效的。
	 * 
	 * @param entity
	 */
	private void ensureDataIntegrityAtCreate(DefaultGroup entity) {
		// //////////////
		DimensionQuery.FindQuery dimFindQuery = new DimensionQuery.FindQuery();
		dimFindQuery.createCriteria().andDimIdEqualTo(entity.getDimId()).andStatusNotEqualTo(Dimension.Status.deleted);
		long cnt = dimensionManager.queryCountByCriteria(dimFindQuery);
		if (cnt == 0) {
			throw new OrgException("Dimension specitied by the gorup being created is not valid.");
		}

		// /////parent group
		String path = "";
		if (StringUtils.isNotEmpty(entity.getParentId())) {
			DefaultGroup parent = this.get(entity.getParentId());
			if (parent == null || parent.getStatus() == Group.Status.deleted) {
				throw new OrgException("Parent group specitied by the gorup being created is not valid.");
			}
			path = entity.getId();
		}
		path = path + entity.getId() + ".";
		entity.setPath(path);
	}

	@Override
	public void create(DefaultGroup entity) {
		ensureDataIntegrityAtCreate(entity);
		super.create(entity);
	}

}
