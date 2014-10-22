package com.hotent.org.persistence.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.base.api.Page;
import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.org.persistence.dao.UserRelationDao;
import com.hotent.org.persistence.model.DefaultUserRelation;
import com.hotent.org.persistence.query.UserRelationQuery;
import com.hotent.org.persistence.query.UserRelationQuery.FindQuery;

@Repository
public class UserRelationDaoImpl extends MyBatisDaoImpl<String, DefaultUserRelation> implements UserRelationDao{

    @Override
    public String getNamespace() {
        return DefaultUserRelation.class.getName();
    }
	/**
	 * 根据外键获取子表明细列表
	 * @param userId
	 * @return
	 */
	@Override
	public List<DefaultUserRelation> getByMainId(String userId) {
		Map<String,Object>params=new HashMap<String, Object>();
		params.put("userId", userId);
		return this.getByKey("getUserRelationList", params);
	}
	
	/**
	 * 根据外键删除子表记录
	 * @param userId
	 * @return
	 */
	public void delByMainId(String userId) {
		Map<String,Object>params=new HashMap<String, Object>();
		params.put("userId", userId);
		this.deleteByKey("delByMainId", params);
	}
	
	@Override
	public List<DefaultUserRelation> queryByCriteria(UserRelationQuery.FindQuery query) {
		return this.getByKey("queryByCriteria", query);
	}
	@Override
	public List<DefaultUserRelation> queryByCriteria(UserRelationQuery.FindQuery query, Page page) {
		return this.getByKey("queryByCriteria", query, page);
	}
	@Override
	public void updateByUpdateQuery(UserRelationQuery.UpdateQuery uQuery) {
		this.updateByKey("updateByUpdateQuery",uQuery);
	}
	@Override
	public void deleteByDeleteQuery(UserRelationQuery.DeleteQuery dQuery) {
		this.deleteByKey("deleteByDeleteQuery",dQuery);
		
	}
	@Override
	public long queryCountByCriteria(FindQuery query) {
		return (Long) this.getOne("queryCountByCriteria", query);
	}

	
}
