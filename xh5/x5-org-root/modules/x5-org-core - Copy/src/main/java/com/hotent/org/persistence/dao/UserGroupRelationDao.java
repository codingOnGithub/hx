package com.hotent.org.persistence.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hotent.base.api.Page;
import com.hotent.base.db.api.Dao;
import com.hotent.org.api.model.UserGroupRelation;
import com.hotent.org.persistence.model.DefaultUser;
import com.hotent.org.persistence.model.DefaultUserGroupRelation;
import com.hotent.org.persistence.query.UserGroupRelationDeleteQuery;
import com.hotent.org.persistence.query.UserGroupRelationQuery;
import com.hotent.org.persistence.query.UserGroupRelationUpdateQuery;
import com.hotent.org.persistence.query.UserQuery;
import com.hotent.org.persistence.query.UserUpdateQuery;


public interface UserGroupRelationDao extends Dao<String, DefaultUserGroupRelation> {
	/**
	 * 根据外键获取子表明细列表
	 * @param relTypeId
	 * @return
	 */
	public List<DefaultUserGroupRelation> getByMainId(String relTypeId);
	
	/**
	 * 根据外键删除子表记录
	 * @param relTypeId
	 * @return
	 */
	public void delByMainId(String relTypeId);
	
	List<DefaultUserGroupRelation> queryByCriteria(UserGroupRelationQuery query);
	
	List<DefaultUserGroupRelation> queryByCriteria(UserGroupRelationQuery query, Page page);
	
	long queryCountByCriteria(UserGroupRelationQuery query);
	
	void updateByUpdateQuery(UserGroupRelationUpdateQuery uQuery);
	
	void deleteByDeleteQuery(UserGroupRelationDeleteQuery dQuery);
	
}
