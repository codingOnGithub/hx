package com.hotent.org.persistence.manager;

import java.io.Serializable;
import java.util.List;

import com.hotent.base.api.Page;
import com.hotent.base.manager.api.Manager;
import com.hotent.org.api.model.Group.Status;
import com.hotent.org.api.model.UserGroupRelation;
import com.hotent.org.persistence.model.DefaultGroup;
import com.hotent.org.persistence.model.DefaultGroupRelation;
import com.hotent.org.persistence.model.DefaultUserGroupRelation;
import com.hotent.org.persistence.query.GroupQuery;

public interface GroupManager<PK extends Serializable, T extends DefaultGroup> extends Manager<PK,T>{
	/**
	 * 根据用户组Key获取用户组信息
	 * @param groupKey
	 * @return
	 */
	public DefaultGroup getByKey(String groupKey);
	
	/**
	 * 根据用户组key 删除用户组信息
	 * @param groupKey
	 */
	public void deleteByGroupKey(String groupKey);
	
	/**
	 * 根据维度ID 获取该维度下的所有用户组列表
	 * @param dimId
	 * @return
	 */
	public List<DefaultGroup> queryByDimId(String dimId);
	
	/**
	 * 根据用户id 是否可用 
	 * 获取某一用所在的可用用户组列表
	 * @param userId
	 * @param isEnabled
	 * @return
	 */
	public List<DefaultGroup> queryByUserId(String userId,UserGroupRelation.Status status);
	
	/**
	 * 获取某一用所在的可用用户组列表(分页)
	 * @param userId
	 * @param isEnabled
	 * @param page
	 * @return
	 */
	public List<DefaultGroup> queryByUserId(String userId, UserGroupRelation.Status status, Page page);
	
	/**
	 * 获取与某一用户组具有某种关系的用户组列表
	 * @param groupKey
	 * @param relType
	 * @return
	 */
	public List<DefaultGroup> queryByGroupRelation(String groupKey, String relType);
	
	/**
	 * 获取与某一用户组具有某种关系的用户组列表
	 * @param groupKey
	 * @param relType
	 * @return
	 */
	public List<DefaultGroup> queryByGroupRelation(String groupKey, String relType,List<Status> statuses);
	
	
	/**
	 * 获取与某一用户组具有某种关系的用户组列表
	 * @param groupId
	 * @param relType
	 * @return
	 */
	public List<DefaultGroup> queryByGroupRelationRel(String groupId, String relType);
	
	
	
	/**
	 * 获取与用户存在某种关系的用户组列表
	 * @param userId
	 * @param relType
	 * @return
	 */
	public List<DefaultGroup> queryByUserGroupRelation(String userId, String relType);
	
	/**
	 * 获取具有某种属性的属性值得用户组列表
	 * @param key
	 * @param value
	 * @return
	 */
	List<DefaultGroup>  queryGroupByAttributeValue(String key, Object value);

	
	/**
	 * 创建用户与组之间的关系
	 * @param userGroupRelation
	 */
	void createUserGroupRelation(DefaultUserGroupRelation userGroupRelation);
	
	
	/**
	 * 创建组与组之间的关系
	 * @param userGroupRelation
	 */
	void createGroupRelation(DefaultGroupRelation groupRelation);
	

	/**
	 * 删除用户组信息
	 * @param entityId
	 * @param logical
	 */
	void remove(String entityId, boolean logical) ;
	
	List<DefaultGroup> queryByCriteria(GroupQuery.FindQuery query);
	
	List<DefaultGroup> queryByCriteria(GroupQuery.FindQuery query, Page page);
	
	long queryCountByCriteria(GroupQuery.FindQuery query);

	void updateByUpdateQuery(GroupQuery.UpdateQuery uQuery);

	void deleteByDeleteQuery(GroupQuery.DeleteQuery dQuery);

	List<DefaultGroup> queryByGroupRelationRel(String groupKey, String relType, List<Status> statuses);

	List<DefaultGroup> queryByUserGroupRelation(String userId, String relType, List<Status> statuses);

	List<DefaultGroup> queryGroupByAttributeValue(String key, Object value, List<Status> statuses);
	
}
