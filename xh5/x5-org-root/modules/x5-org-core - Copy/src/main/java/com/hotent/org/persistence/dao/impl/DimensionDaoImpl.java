package com.hotent.org.persistence.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hotent.base.api.Page;
import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.org.persistence.dao.DimensionDao;
import com.hotent.org.persistence.model.DefaultDimension;
import com.hotent.org.persistence.query.DimensionDeleteQuery;
import com.hotent.org.persistence.query.DimensionQuery;
import com.hotent.org.persistence.query.DimensionUpdateQuery;

@Repository
public class DimensionDaoImpl extends MyBatisDaoImpl<String, DefaultDimension> implements DimensionDao{

    @Override
    public String getNamespace() {
        return DefaultDimension.class.getName();
    }

	@Override
	public List<DefaultDimension> queryByTypeOrderBySnAsc(String type) {
		return getByKey("queryByTypeOrderBySnAsc", buildMap("groupType", type));
	}

	@Override
	public DefaultDimension getByDimKey(String dimKey) {
		return getUnique("getByDimKey", buildMap("dimKey", dimKey));
	}
	
	
	@Override
	public List<DefaultDimension> queryByCriteria(DimensionQuery query) {
		return this.getByKey("queryByCriteria", query);
	}
	
	
	@Override
	public List<DefaultDimension> queryByCriteria(DimensionQuery query, Page page) {
		return this.getByKey("queryByCriteria", query, page);
	}

	@Override
	public long queryCountByCriteria(DimensionQuery query) {
		return (Long) this.getOne("queryCountByCriteria", query);
	}
	
	@Override
	public void updateByUpdateQuery(DimensionUpdateQuery uQuery){
		this.updateByKey("updateByUpdateQuery",uQuery);
	}

	
	@Override
	public void deleteByDeleteQuery(DimensionDeleteQuery dQuery) {
		this.deleteByKey("deleteByDeleteQuery",dQuery);
	}
	
	
	
}
