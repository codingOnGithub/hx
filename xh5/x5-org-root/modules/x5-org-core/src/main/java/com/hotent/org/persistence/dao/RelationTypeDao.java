package com.hotent.org.persistence.dao;

import java.util.List;

import com.hotent.base.api.Page;
import com.hotent.base.db.api.Dao;
import com.hotent.org.persistence.model.DefaultRelationType;
import com.hotent.org.persistence.query.RelationTypeQuery;

public interface RelationTypeDao extends Dao<String, DefaultRelationType> {
	List<DefaultRelationType> queryByCriteria(RelationTypeQuery.FindQuery query);

	List<DefaultRelationType> queryByCriteria(RelationTypeQuery.FindQuery query, Page page);

	long queryCountByCriteria(RelationTypeQuery.FindQuery query);

	void updateByUpdateQuery(RelationTypeQuery.UpdateQuery uQuery);

	void deleteByDeleteQuery(RelationTypeQuery.DeleteQuery dQuery);
}
