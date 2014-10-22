package com.hotent.org.persistence.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hotent.base.api.Page;
import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.org.persistence.dao.AttributeValueDao;
import com.hotent.org.persistence.model.DefaultAttributeValue;
import com.hotent.org.persistence.query.AttributeValueQuery;

@Repository
public class AttributeValueDaoImpl extends MyBatisDaoImpl<String, DefaultAttributeValue> implements AttributeValueDao{

    @Override
    public String getNamespace() {
        return DefaultAttributeValue.class.getName();
    }
	
	@Override
	public List<DefaultAttributeValue> queryByCriteria(AttributeValueQuery.FindQuery query) {
		return this.getByKey("queryByCriteria", query);
	}
	
	
	@Override
	public List<DefaultAttributeValue> queryByCriteria(AttributeValueQuery.FindQuery query, Page page) {
		return this.getByKey("queryByCriteria", query, page);
	}
	

	@Override
	public void updateByUpdateQuery(AttributeValueQuery.UpdateQuery uQuery) {
		this.updateByKey("updateByUpdateQuery", uQuery);
		
	}

	@Override
	public void deleteByDeleteQuery(AttributeValueQuery.DeleteQuery dQuery) {
		this.deleteByKey("deleteByDeleteQuery", dQuery);
		
	}

	@Override
	public long queryCountByCriteria(AttributeValueQuery.FindQuery query) {
		return (Long) this.getOne("queryCountByCriteria", query);
	}
	
}
