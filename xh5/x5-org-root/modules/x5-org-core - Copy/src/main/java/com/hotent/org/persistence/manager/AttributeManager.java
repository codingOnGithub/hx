package com.hotent.org.persistence.manager;

import java.util.List;

import com.hotent.base.api.Page;
import com.hotent.base.manager.api.Manager;
import com.hotent.org.persistence.model.DefaultAttribute;
import com.hotent.org.persistence.query.AttributeQuery;

public interface AttributeManager extends Manager<String, DefaultAttribute>{
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
	List<DefaultAttribute>  queryByBelongType(String belongType);
	
	
	List<DefaultAttribute> queryByCriteria(AttributeQuery query);
	
	
	List<DefaultAttribute> queryByCriteria(AttributeQuery query, Page page);
	
	public void remove(String entityId, boolean logical);
}
