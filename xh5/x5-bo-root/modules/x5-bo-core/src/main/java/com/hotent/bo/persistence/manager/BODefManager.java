package com.hotent.bo.persistence.manager;

import java.util.List;

import com.hotent.base.manager.api.Manager;
import com.hotent.bo.persistence.model.BOAttribute;
import com.hotent.bo.persistence.model.BODef;


public interface BODefManager extends Manager<String, BODef>{
	List<BOAttribute> getByMainId(String defId);
	
	/**
	 * 发布（保存或者修改）业务对象
	 * @param bODef
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	void deploy(BODef bODef);
	
	/**
	 * 发布新版本的业务对象
	 * @param bODef
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	void modify(BODef bODef);
	
	/**
	 * 加载业务对象（包括加载对象规则和对象属性以及对象属性的规则和依赖对象）
	 * @param entityId 
	 * BODef
	 * @exception 
	 * @since  1.0.0
	 */
	BODef loadBODef(String entityId);
	
	/**
	 * 删除业务对象（包括加载对象规则和对象属性以及对象属性的规则和依赖对象）
	 * @param entityId 
	 * BODef
	 * @exception 
	 * @since  1.0.0
	 */
	void removeBODef(String	entityId);
}
