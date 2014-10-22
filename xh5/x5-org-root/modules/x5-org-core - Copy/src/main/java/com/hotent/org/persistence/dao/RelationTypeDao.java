package com.hotent.org.persistence.dao;

import java.util.List;

import com.hotent.base.api.Page;
import com.hotent.base.db.api.Dao;
import com.hotent.org.persistence.model.DefaultRelationType;
import com.hotent.org.persistence.query.RelationTypeDeleteQuery;
import com.hotent.org.persistence.query.RelationTypeQuery;
import com.hotent.org.persistence.query.RelationTypeUpdateQuery;

public interface RelationTypeDao extends Dao<String, DefaultRelationType> {
	List<DefaultRelationType> queryByCriteria(RelationTypeQuery query);

	List<DefaultRelationType> queryByCriteria(RelationTypeQuery query, Page page);

	long queryCountByCriteria(RelationTypeQuery query);

	void updateByUpdateQuery(RelationTypeUpdateQuery uQuery);

	void deleteByDeleteQuery(RelationTypeDeleteQuery dQuery);
}
