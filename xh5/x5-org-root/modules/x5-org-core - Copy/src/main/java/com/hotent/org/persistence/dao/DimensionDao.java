package com.hotent.org.persistence.dao;
import java.util.List;

import com.hotent.base.api.Page;
import com.hotent.base.db.api.Dao;
import com.hotent.org.persistence.model.DefaultDimension;
import com.hotent.org.persistence.model.DefaultDimension;
import com.hotent.org.persistence.query.DimensionDeleteQuery;
import com.hotent.org.persistence.query.DimensionQuery;
import com.hotent.org.persistence.query.DimensionUpdateQuery;


public interface DimensionDao extends Dao<String, DefaultDimension> {
	List<DefaultDimension> queryByTypeOrderBySnAsc(String type);
	
	DefaultDimension getByDimKey(String dimKey);
	
	List<DefaultDimension> queryByCriteria(DimensionQuery query);
	
	List<DefaultDimension> queryByCriteria(DimensionQuery query, Page page);
	
	long queryCountByCriteria(DimensionQuery query);

	void updateByUpdateQuery(DimensionUpdateQuery uQuery);

	void deleteByDeleteQuery(DimensionDeleteQuery dQuery);
	
}
