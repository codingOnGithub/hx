package com.hotent.org.persistence.dao;

import java.io.Serializable;
import java.util.List;

import com.hotent.base.api.Page;
import com.hotent.base.db.api.Dao;
import com.hotent.org.persistence.model.DefaultGroup;
import com.hotent.org.persistence.query.GroupQuery;


public interface GroupDao<PK extends Serializable,T extends DefaultGroup> extends Dao<PK,T>{
	
	public void deleteByGroupKey(String groupKey);
	
	List<DefaultGroup> queryByCriteria(GroupQuery.FindQuery query);
	
	List<DefaultGroup> queryByCriteria(GroupQuery.FindQuery query, Page page);
	
	long queryCountByCriteria(GroupQuery.FindQuery query);

	void updateByUpdateQuery(GroupQuery.UpdateQuery uQuery);

	void deleteByDeleteQuery(GroupQuery.DeleteQuery dQuery);
}
