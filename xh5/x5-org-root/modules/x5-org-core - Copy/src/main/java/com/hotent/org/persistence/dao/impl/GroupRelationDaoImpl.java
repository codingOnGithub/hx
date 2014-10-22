package com.hotent.org.persistence.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hotent.base.api.Page;
import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.org.persistence.dao.GroupRelationDao;
import com.hotent.org.persistence.model.DefaultGroupRelation;
import com.hotent.org.persistence.query.GroupRelationDeleteQuery;
import com.hotent.org.persistence.query.GroupRelationQuery;
import com.hotent.org.persistence.query.GroupRelationUpdateQuery;

@Repository
public class GroupRelationDaoImpl extends MyBatisDaoImpl<String, DefaultGroupRelation> implements GroupRelationDao{

    @Override
    public String getNamespace() {
        return DefaultGroupRelation.class.getName();
    }
	
    

    @Override
	public List<DefaultGroupRelation> queryByCriteria(GroupRelationQuery query) {
		return this.getByKey("queryByCriteria", query);
	}
	
	
	@Override
	public List<DefaultGroupRelation> queryByCriteria(GroupRelationQuery query, Page page) {
		return this.getByKey("queryByCriteria", query, page);
	}

	@Override
	public long queryCountByCriteria(GroupRelationQuery query) {
		return (Long) this.getOne("queryCountByCriteria", query);
	}
	
	@Override
	public void updateByUpdateQuery(GroupRelationUpdateQuery uQuery){
		this.updateByKey("updateByUpdateQuery",uQuery);
	}

	
	@Override
	public void deleteByDeleteQuery(GroupRelationDeleteQuery dQuery) {
		this.deleteByKey("deleteByDeleteQuery",dQuery);
	}
	
	
	
}
