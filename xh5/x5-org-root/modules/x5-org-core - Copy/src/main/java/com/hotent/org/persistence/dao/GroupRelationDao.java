package com.hotent.org.persistence.dao;
import java.util.List;

import com.hotent.base.api.Page;
import com.hotent.base.db.api.Dao;
import com.hotent.org.persistence.model.DefaultGroupRelation;
import com.hotent.org.persistence.query.GroupRelationDeleteQuery;
import com.hotent.org.persistence.query.GroupRelationQuery;
import com.hotent.org.persistence.query.GroupRelationUpdateQuery;


public interface GroupRelationDao extends Dao<String, DefaultGroupRelation> {
	
	
	List<DefaultGroupRelation> queryByCriteria(GroupRelationQuery query);
	
	List<DefaultGroupRelation> queryByCriteria(GroupRelationQuery query, Page page);
	
	long queryCountByCriteria(GroupRelationQuery query);

	void updateByUpdateQuery(GroupRelationUpdateQuery uQuery);

	void deleteByDeleteQuery(GroupRelationDeleteQuery dQuery);
	
}
