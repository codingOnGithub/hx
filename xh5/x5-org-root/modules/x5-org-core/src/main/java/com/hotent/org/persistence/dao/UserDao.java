package com.hotent.org.persistence.dao;
import java.util.List;

import com.hotent.base.api.Page;
import com.hotent.base.db.api.Dao;
import com.hotent.org.persistence.model.DefaultUser;
import com.hotent.org.persistence.query.UserQuery;


public interface UserDao extends Dao<String, DefaultUser> {
	
	public DefaultUser getByAccount(String account);

	public List<DefaultUser> queryByUserRelation(String userKey, String relType);

	public List<DefaultUser> queryByGroupRelation(String groupKey, String relType);

	public List<DefaultUser> queryUserByAttributeValue(String key, Object value);

	List<DefaultUser> queryByCriteria(UserQuery.FindQuery query);
	
	List<DefaultUser> queryByCriteria(UserQuery.FindQuery query, Page page);
	
	long queryCountByCriteria(UserQuery.FindQuery query);

	void updateByUpdateQuery(UserQuery.UpdateQuery uQuery);

	void deleteByDeleteQuery(UserQuery.DeleteQuery dQuery);
}
