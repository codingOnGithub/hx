package com.hotent.org.persistence.manager;

import java.util.List;

import com.hotent.base.api.Page;
import com.hotent.base.manager.api.Manager;
import com.hotent.org.persistence.model.DefaultAttribute;
import com.hotent.org.persistence.model.DefaultAttributeValue;
import com.hotent.org.persistence.query.AttributeQuery;

public interface AttributeManager extends Manager<String, DefaultAttribute>{
	/**
	 * 通过属性key获取扩展属性
	 * @param attrKey
	 * @return
	 */
	DefaultAttribute getByAttrKey(String attrKey);
	
	public void remove(String entityId, boolean logical);
	
	List<DefaultAttribute> queryByCriteria(AttributeQuery.FindQuery query);
	
	List<DefaultAttribute> queryByCriteria(AttributeQuery.FindQuery query, Page page);
	
	long queryCountByCriteria(AttributeQuery.FindQuery query);

	void updateByUpdateQuery(AttributeQuery.UpdateQuery uQuery);

	void deleteByDeleteQuery(AttributeQuery.DeleteQuery dQuery);

	void createAttributeValue(DefaultAttributeValue attributeValue);
	
}
