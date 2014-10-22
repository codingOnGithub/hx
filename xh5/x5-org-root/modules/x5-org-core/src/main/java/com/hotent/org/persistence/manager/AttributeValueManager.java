package com.hotent.org.persistence.manager;

import java.util.List;

import com.hotent.base.api.Page;
import com.hotent.base.manager.api.Manager;
import com.hotent.org.persistence.model.DefaultAttributeValue;
import com.hotent.org.persistence.model.DefaultDimension;
import com.hotent.org.persistence.query.AttributeValueQuery;

public interface AttributeValueManager extends Manager<String, DefaultAttributeValue>{
	
	
	List<DefaultAttributeValue> queryByCriteria(AttributeValueQuery.FindQuery query);
	
	List<DefaultAttributeValue> queryByCriteria(AttributeValueQuery.FindQuery query, Page page);
	
	long queryCountByCriteria(AttributeValueQuery.FindQuery query);

	void updateByUpdateQuery(AttributeValueQuery.UpdateQuery uQuery);

	void deleteByDeleteQuery(AttributeValueQuery.DeleteQuery dQuery);
}
