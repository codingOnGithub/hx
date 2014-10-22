package com.hotent.bo.persistence.dao;

import java.util.List;
import com.hotent.base.db.api.Dao;
import com.hotent.bo.persistence.model.BOAttribute;


public interface BOAttributeDao extends Dao<String, BOAttribute> {

	/**
	 * 根据外键获取子表明细列表
	 * @param defId
	 * @return 
	 * List<BOAttribute>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<BOAttribute> getByMainId(String defId);
	

	/**
	 * 根据外键删除子表记录
	 * @param defId 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void delByMainId(String defId);
	
}
