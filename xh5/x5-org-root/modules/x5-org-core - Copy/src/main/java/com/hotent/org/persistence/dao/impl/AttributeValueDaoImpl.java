package com.hotent.org.persistence.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hotent.base.api.Page;
import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.org.persistence.dao.AttributeValueDao;
import com.hotent.org.persistence.model.DefaultAttribute;
import com.hotent.org.persistence.model.DefaultAttributeValue;
import com.hotent.org.persistence.query.AttributeValueDeleteQuery;
import com.hotent.org.persistence.query.AttributeValueQuery;
import com.hotent.org.persistence.query.AttributeValueUpdateQuery;
import com.hotent.org.persistence.query.UserQuery;
import com.hotent.org.persistence.query.UserRelationDeleteQuery;

@Repository
public class AttributeValueDaoImpl extends MyBatisDaoImpl<String, DefaultAttributeValue> implements AttributeValueDao{

    @Override
    public String getNamespace() {
        return DefaultAttributeValue.class.getName();
    }
	
	@Override
	public List<DefaultAttributeValue> queryByCriteria(AttributeValueQuery query) {
		return this.getByKey("queryByCriteria", query);
	}
	
	
	@Override
	public List<DefaultAttributeValue> queryByCriteria(AttributeValueQuery query, Page page) {
		return this.getByKey("queryByCriteria", query, page);
	}
	

	@Override
	public void updateByUpdateQuery(AttributeValueUpdateQuery uQuery) {
		this.updateByKey("updateByUpdateQuery", uQuery);
		
	}

	@Override
	public void deleteByDeleteQuery(AttributeValueDeleteQuery dQuery) {
		this.deleteByKey("deleteByDeleteQuery", dQuery);
		
	}
	
}
