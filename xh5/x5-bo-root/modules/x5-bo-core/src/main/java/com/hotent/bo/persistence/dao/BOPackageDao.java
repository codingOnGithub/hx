package com.hotent.bo.persistence.dao;
import java.util.Map;

import com.hotent.base.db.api.Dao;
import com.hotent.bo.persistence.model.BOPackage;


public interface BOPackageDao extends Dao<String, BOPackage> {

	/**
	 * 根据参数获取唯一的包
     * <pre>
	 * 示例：
	 * Map map = new HashMap();
	 * map.put("name", name);
	 * map.put("depth", 1);      
	 * BOPackage oldBp = bOPackageDao.getBOPackageByMap(map);	
	 * 返回:
	 * BOPackage 对象
	 * </pre>
	 * @param map
	 * @return 
	 * BOPackage
	 * @exception 
	 * @since  1.0.0
	 */
	BOPackage getBOPackageByMap(Map map);
}
