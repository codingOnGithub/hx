package com.hotent.org.persistence.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hotent.base.api.Page;
import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.org.persistence.dao.AttributeDao;
import com.hotent.org.persistence.model.DefaultAttribute;
import com.hotent.org.persistence.model.DefaultUser;
import com.hotent.org.persistence.query.AttributeDeleteQuery;
import com.hotent.org.persistence.query.AttributeQuery;
import com.hotent.org.persistence.query.AttributeUpdateQuery;
import com.hotent.org.persistence.query.UserQuery;
import com.hotent.org.persistence.query.UserUpdateQuery;

@Repository
public class AttributeDaoImpl extends MyBatisDaoImpl<String, DefaultAttribute> implements AttributeDao{
    @Override
    public String getNamespace() {
        return DefaultAttribute.class.getName();
    }

	@Override
	public DefaultAttribute getByAttrKey(String attrKey) {
		return getUnique("getByAttrKey", buildMap("attrKey", attrKey));
	}

	@Override
	public List<DefaultAttribute> queryByBelongType(String belongType) {
		return getByKey("queryByBelongType", buildMap("belongType", belongType));
	}
	
	@Override
	public List<DefaultAttribute> queryByCriteria(AttributeQuery query) {
		return this.getByKey("queryByCriteria", query);
	}
	
	
	@Override
	public List<DefaultAttribute> queryByCriteria(AttributeQuery query, Page page) {
		return this.getByKey("queryByCriteria", query, page);
	}
	
	
	
	@Override
	public void updateByUpdateQuery(AttributeUpdateQuery uQuery){
		this.updateByKey("updateByUpdateQuery",uQuery);
	}
	
	
	@Override
	public void deleteByDeleteQuery(AttributeDeleteQuery dQuery){
		this.deleteByKey("deleteByDeleteQuery",dQuery);
	}

}
