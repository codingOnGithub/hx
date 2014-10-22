package com.hotent.org.persistence.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hotent.base.api.Page;
import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.org.persistence.dao.DimensionDao;
import com.hotent.org.persistence.model.DefaultDimension;
import com.hotent.org.persistence.query.DimensionQuery;

@Repository
public class DimensionDaoImpl extends MyBatisDaoImpl<String, DefaultDimension> implements DimensionDao{

    @Override
    public String getNamespace() {
        return DefaultDimension.class.getName();
    }


	@Override
	public DefaultDimension getByDimKey(String dimKey) {
		return getUnique("getByDimKey", buildMap("dimKey", dimKey));
	}
	
	
	@Override
	public List<DefaultDimension> queryByCriteria(DimensionQuery.FindQuery query) {
		return this.getByKey("queryByCriteria", query);
	}
	
	
	@Override
	public List<DefaultDimension> queryByCriteria(DimensionQuery.FindQuery query, Page page) {
		return this.getByKey("queryByCriteria", query, page);
	}

	@Override
	public long queryCountByCriteria(DimensionQuery.FindQuery query) {
		return (Long) this.getOne("queryCountByCriteria", query);
	}
	
	@Override
	public void updateByUpdateQuery(DimensionQuery.UpdateQuery uQuery){
		this.updateByKey("updateByUpdateQuery",uQuery);
	}

	
	@Override
	public void deleteByDeleteQuery(DimensionQuery.DeleteQuery dQuery) {
		this.deleteByKey("deleteByDeleteQuery",dQuery);
	}
	
	
	
}
