package com.hotent.org.persistence.manager;

import com.hotent.base.manager.api.Manager;
import com.hotent.org.persistence.model.DefaultRelationType;

public interface RelationTypeManager extends Manager<String, DefaultRelationType> {
	/**
	 * 根据用户关系，获取关系类型
	 * 
	 * @param userRelationId
	 * @return
	 */
	public DefaultRelationType queryByUserRelation(String userRelationId);

	/**
	 * 根据用户组关系，获取关系类型
	 * 
	 * @param groupRelationId
	 * @return
	 */
	public DefaultRelationType queryByGroupRelation(String groupRelationId);

	/**
	 * 根据用户和组关系，获取关系类型
	 * 
	 * @param userGroupRelationId
	 * @return
	 */
	public DefaultRelationType queryByUserGroupRelation(String userGroupRelationId);
}
