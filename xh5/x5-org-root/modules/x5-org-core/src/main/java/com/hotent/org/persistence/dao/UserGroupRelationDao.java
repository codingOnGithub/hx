package com.hotent.org.persistence.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hotent.base.api.Page;
import com.hotent.base.db.api.Dao;
import com.hotent.org.api.model.UserGroupRelation;
import com.hotent.org.persistence.model.DefaultUser;
import com.hotent.org.persistence.model.DefaultUserGroupRelation;
import com.hotent.org.persistence.query.UserGroupRelationQuery;
import com.hotent.org.persistence.query.UserGroupRelationQuery.FindQuery;
import com.hotent.org.persistence.query.UserQuery;


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
	
	List<DefaultUserGroupRelation> queryByCriteria(UserGroupRelationQuery.FindQuery query);
	
	List<DefaultUserGroupRelation> queryByCriteria(UserGroupRelationQuery.FindQuery query, Page page);
	
	long queryCountByCriteria(UserGroupRelationQuery.FindQuery query);
	
	void updateByUpdateQuery(UserGroupRelationQuery.UpdateQuery uQuery);
	
	void deleteByDeleteQuery(UserGroupRelationQuery.DeleteQuery dQuery);
	
}
