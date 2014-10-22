package com.hotent.org.persistence.dao;
import java.util.List;

import com.hotent.base.api.Page;
import com.hotent.base.db.api.Dao;
import com.hotent.org.persistence.model.DefaultGroupRelation;
import com.hotent.org.persistence.query.GroupRelationQuery;
import com.hotent.org.persistence.query.GroupRelationQuery.FindQuery;


public interface GroupRelationDao extends Dao<String, DefaultGroupRelation> {
	
	
	List<DefaultGroupRelation> queryByCriteria(GroupRelationQuery.FindQuery query);
	
	List<DefaultGroupRelation> queryByCriteria(GroupRelationQuery.FindQuery query, Page page);
	
	long queryCountByCriteria(GroupRelationQuery.FindQuery query);

	void updateByUpdateQuery(GroupRelationQuery.UpdateQuery uQuery);

	void deleteByDeleteQuery(GroupRelationQuery.DeleteQuery dQuery);
	
}
