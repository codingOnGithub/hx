package com.hotent.org.persistence.dao;
import java.util.List;

import com.hotent.base.api.Page;
import com.hotent.base.db.api.Dao;
import com.hotent.org.persistence.model.DefaultAttribute;
import com.hotent.org.persistence.model.DefaultAttributeValue;
import com.hotent.org.persistence.query.AttributeQuery;
import com.hotent.org.persistence.query.AttributeValueDeleteQuery;
import com.hotent.org.persistence.query.AttributeValueQuery;
import com.hotent.org.persistence.query.AttributeValueUpdateQuery;
import com.hotent.org.persistence.query.UserDeleteQuery;


public interface AttributeValueDao extends Dao<String, DefaultAttributeValue> {
	
	List<DefaultAttributeValue> queryByCriteria(AttributeValueQuery query);
	
	
	List<DefaultAttributeValue> queryByCriteria(AttributeValueQuery query, Page page);
	

	void updateByUpdateQuery(AttributeValueUpdateQuery uQuery);
	
	void deleteByDeleteQuery(AttributeValueDeleteQuery dQuery);
}
