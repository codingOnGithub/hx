package com.hotent.org.persistence.dao;

import java.io.Serializable;
import java.util.List;

import com.hotent.base.api.Page;
import com.hotent.base.db.api.Dao;
import com.hotent.org.persistence.model.DefaultGroup;
import com.hotent.org.persistence.model.DefaultUser;
import com.hotent.org.persistence.query.GroupDeleteQuery;
import com.hotent.org.persistence.query.GroupQuery;
import com.hotent.org.persistence.query.GroupUpdateQuery;
import com.hotent.org.persistence.query.UserQuery;


public interface GroupDao<PK extends Serializable,T extends DefaultGroup> extends Dao<PK,T>{
	
	public void deleteByGroupKey(String groupKey);
	
	List<DefaultGroup> queryByCriteria(GroupQuery query);
	
	List<DefaultGroup> queryByCriteria(GroupQuery query, Page page);
	
	long queryCountByCriteria(GroupQuery query);

	void updateByUpdateQuery(GroupUpdateQuery uQuery);

	void deleteByDeleteQuery(GroupDeleteQuery dQuery);
}
