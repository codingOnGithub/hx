package com.hotent.org.persistence.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hotent.base.api.Page;
import com.hotent.base.db.api.Dao;
import com.hotent.org.persistence.model.DefaultUserRelation;
import com.hotent.org.persistence.model.DefaultUserRelation;
import com.hotent.org.persistence.query.UserRelationDeleteQuery;
import com.hotent.org.persistence.query.UserRelationQuery;
import com.hotent.org.persistence.query.UserRelationUpdateQuery;


public interface UserRelationDao extends Dao<String, DefaultUserRelation> {
	/**
	 * 根据外键获取子表明细列表
	 * @param userId
	 * @return
	 */
	public List<DefaultUserRelation> getByMainId(String userId);
	
	/**
	 * 根据外键删除子表记录
	 * @param userId
	 * @return
	 */
	public void delByMainId(String userId);
	
	
	List<DefaultUserRelation> queryByCriteria(UserRelationQuery query);
	
	List<DefaultUserRelation> queryByCriteria(UserRelationQuery query, Page page);

	public void updateByUpdateQuery(UserRelationUpdateQuery uQuery);

	public void deleteByDeleteQuery(UserRelationDeleteQuery dQuery);
	
}
