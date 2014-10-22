package com.hotent.bo.persistence.dao;
import java.util.Map;

import com.hotent.base.db.api.Dao;
import com.hotent.bo.persistence.model.BODef;


public interface BODefDao extends Dao<String, BODef> {
	
	/**
	 * 更新对象的是否主版本问题
	 * @param map 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void updateBoDefsIsMain(Map map);
	
	/**
	 * 获取最在当前对象的最大版本对象
	 * <pre>
	 * 示例：
	 * Map map = new HashMap();
	 * map.put("code", oldBODef.getCode());
	 * BODef maxVersionBODef = bODefDao.getMaxVersionBODef(map);
	 * 返回:
	 * BODef对象
	 * </pre>
	 * @param map
	 * @return 
	 * BODef
	 * @exception 
	 * @since  1.0.0
	 */
	public BODef getMaxVersionBODef(Map map);
}
