package com.hotent.org.persistence.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hotent.base.api.Page;
import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.org.persistence.dao.RelationTypeDao;
import com.hotent.org.persistence.model.DefaultRelationType;
import com.hotent.org.persistence.query.RelationTypeQuery;

@Repository
public class RelationTypeDaoImpl extends MyBatisDaoImpl<String, DefaultRelationType> implements RelationTypeDao{

    @Override
    public String getNamespace() {
        return DefaultRelationType.class.getName();
    }
	
    
    @Override
	public List<DefaultRelationType> queryByCriteria(RelationTypeQuery.FindQuery query) {
		return this.getByKey("queryByCriteria", query);
	}
	
	
	@Override
	public List<DefaultRelationType> queryByCriteria(RelationTypeQuery.FindQuery query, Page page) {
		return this.getByKey("queryByCriteria", query, page);
	}

	@Override
	public long queryCountByCriteria(RelationTypeQuery.FindQuery query) {
		return (Long) this.getOne("queryCountByCriteria", query);
	}
	
	@Override
	public void updateByUpdateQuery(RelationTypeQuery.UpdateQuery uQuery){
		this.updateByKey("updateByUpdateQuery",uQuery);
	}

	
	@Override
	public void deleteByDeleteQuery(RelationTypeQuery.DeleteQuery dQuery) {
		this.deleteByKey("deleteByDeleteQuery",dQuery);
	}
}
