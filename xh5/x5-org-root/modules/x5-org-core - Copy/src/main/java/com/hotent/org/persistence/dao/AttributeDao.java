package com.hotent.org.persistence.dao;

import java.util.List;

import com.hotent.base.api.Page;
import com.hotent.base.db.api.Dao;
import com.hotent.org.persistence.model.DefaultAttribute;
import com.hotent.org.persistence.model.DefaultUser;
import com.hotent.org.persistence.query.AttributeDeleteQuery;
import com.hotent.org.persistence.query.AttributeQuery;
import com.hotent.org.persistence.query.AttributeUpdateQuery;
import com.hotent.org.persistence.query.UserQuery;
import com.hotent.org.persistence.query.UserUpdateQuery;

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
	
	

	List<DefaultAttribute> queryByCriteria(AttributeQuery query);
	
	
	List<DefaultAttribute> queryByCriteria(AttributeQuery query, Page page);
	
	void updateByUpdateQuery(AttributeUpdateQuery uQuery);
	
	void deleteByDeleteQuery(AttributeDeleteQuery dQuery);
	
}
