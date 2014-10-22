package com.hotent.org.persistence.dao;

import java.util.List;

import com.hotent.base.api.Page;
import com.hotent.base.db.api.Dao;
import com.hotent.org.persistence.model.DefaultAttribute;
import com.hotent.org.persistence.query.AttributeQuery;

public interface AttributeDao extends Dao<String, DefaultAttribute> {
	/**
	 * 通过属性key获取扩展属性
	 * @param attrKey
	 * @return
	 */
	DefaultAttribute getByAttrKey(String attrKey);
	/**
	 * 通过属性所属类型获取扩展属性
	 * @param belongType
	 * @return
	 */
	List<DefaultAttribute> queryByBelongType(String belongType);
	
	

	List<DefaultAttribute> queryByCriteria(AttributeQuery.FindQuery query);
	
	long queryCountByCriteria(AttributeQuery.FindQuery query);

	
	List<DefaultAttribute> queryByCriteria(AttributeQuery.FindQuery query, Page page);
	
	void updateByUpdateQuery(AttributeQuery.UpdateQuery uQuery);
	
	void deleteByDeleteQuery(AttributeQuery.DeleteQuery dQuery);
	
}
