package com.hotent.org.persistence.dao;
import java.util.List;

import com.hotent.base.api.Page;
import com.hotent.base.db.api.Dao;
import com.hotent.org.api.model.User;
import com.hotent.org.persistence.model.DefaultUser;
import com.hotent.org.persistence.query.UserDeleteQuery;
import com.hotent.org.persistence.query.UserQuery;
import com.hotent.org.persistence.query.UserUpdateQuery;


public interface UserDao extends Dao<String, DefaultUser> {
	
	public DefaultUser getByAccount(String account);

	public List<DefaultUser> queryByUserRelation(String userKey, String relType);

	public List<DefaultUser> queryByGroupRelation(String groupKey, String relType);

	public List<DefaultUser> queryUserByAttributeValue(String key, Object value);

	List<DefaultUser> queryByCriteria(UserQuery query);
	
	List<DefaultUser> queryByCriteria(UserQuery query, Page page);
	
	long queryCountByCriteria(UserQuery query);

	void updateByUpdateQuery(UserUpdateQuery uQuery);

	void deleteByDeleteQuery(UserDeleteQuery dQuery);
}
