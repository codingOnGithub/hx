package com.hotent.org.persistence.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.base.api.Page;
import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.org.persistence.dao.UserGroupRelationDao;
import com.hotent.org.persistence.model.DefaultUser;
import com.hotent.org.persistence.model.DefaultUserGroupRelation;
import com.hotent.org.persistence.query.UserGroupRelationQuery;
import com.hotent.org.persistence.query.UserQuery;

@Repository
public class UserGroupRelationDaoImpl extends MyBatisDaoImpl<String, DefaultUserGroupRelation> implements UserGroupRelationDao{

    @Override
    public String getNamespace() {
        return DefaultUserGroupRelation.class.getName();
    }
	/**
	 * 根据外键获取子表明细列表
	 * @param relTypeId
	 * @return
	 */
	public List<DefaultUserGroupRelation> getByMainId(String relTypeId) {
		Map<String,Object>params=new HashMap<String, Object>();
		params.put("relTypeId", relTypeId);
		return this.getByKey("getUserGroupRelationList", params);
	}
	
	/**
	 * 根据外键删除子表记录
	 * @param relTypeId
	 * @return
	 */
	public void delByMainId(String relTypeId) {
		Map<String,Object>params=new HashMap<String, Object>();
		params.put("relTypeId", relTypeId);
		this.deleteByKey("delByMainId", params);
	}
	
	
	@Override
	public List<DefaultUserGroupRelation> queryByCriteria(UserGroupRelationQuery.FindQuery query) {
		return this.getByKey("queryByCriteria", query);
	}
	
	
	@Override
	public List<DefaultUserGroupRelation> queryByCriteria(UserGroupRelationQuery.FindQuery query, Page page) {
		return this.getByKey("queryByCriteria", query, page);
	}

	@Override
	public long queryCountByCriteria(UserGroupRelationQuery.FindQuery query) {
		return (Long) this.getOne("queryCountByCriteria", query);
	}
	
	
	@Override
	public void updateByUpdateQuery(UserGroupRelationQuery.UpdateQuery uQuery){
		this.updateByKey("updateByUpdateQuery",uQuery);
		
	}
	
	
	@Override
	public void deleteByDeleteQuery(UserGroupRelationQuery.DeleteQuery dQuery){
		this.deleteByKey("deleteByDeleteQuery",dQuery);
		
	}
}
