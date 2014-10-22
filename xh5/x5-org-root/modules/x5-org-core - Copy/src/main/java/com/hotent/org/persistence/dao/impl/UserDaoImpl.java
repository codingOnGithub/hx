package com.hotent.org.persistence.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.base.api.Page;
import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.org.persistence.dao.UserDao;
import com.hotent.org.persistence.model.DefaultUser;
import com.hotent.org.persistence.query.UserDeleteQuery;
import com.hotent.org.persistence.query.UserQuery;
import com.hotent.org.persistence.query.UserRelationDeleteQuery;
import com.hotent.org.persistence.query.UserUpdateQuery;

@Repository
public class UserDaoImpl extends MyBatisDaoImpl<String, DefaultUser> implements UserDao {
	@Override
	public String getNamespace() {
		return DefaultUser.class.getName();
	}

	@Override
	public DefaultUser getByAccount(String account) {
		return this.getUnique("getByAccount", this.buildMap("account", account));
	}

	@Override
	public List<DefaultUser> queryByUserRelation(String userKey, String relType) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userKey);
		params.put("relType", relType);
		return this.getByKey("queryByUserRelation", params);
	}

	@Override
	public List<DefaultUser> queryByGroupRelation(String groupKey, String relType) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupId", groupKey);
		params.put("relType", relType);
		return this.getByKey("queryByGroupRelation", params);
	}

	@Override
	public List<DefaultUser> queryUserByAttributeValue(String key, Object value) {
		return null;
	}


	@Override
	public List<DefaultUser> queryByCriteria(UserQuery query) {
		return this.getByKey("queryByCriteria", query);
	}
	
	
	@Override
	public List<DefaultUser> queryByCriteria(UserQuery query, Page page) {
		return this.getByKey("queryByCriteria", query, page);
	}

	@Override
	public long queryCountByCriteria(UserQuery query) {
		return (Long) this.getOne("queryCountByCriteria", query);
	}
	
	@Override
	public void updateByUpdateQuery(UserUpdateQuery uQuery){
		this.updateByKey("updateByUpdateQuery",uQuery);
	}

	
	@Override
	public void deleteByDeleteQuery(UserDeleteQuery dQuery) {
		this.deleteByKey("deleteByDeleteQuery",dQuery);
	}
}
