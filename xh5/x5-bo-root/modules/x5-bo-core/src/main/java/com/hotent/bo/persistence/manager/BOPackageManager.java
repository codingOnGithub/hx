package com.hotent.bo.persistence.manager;

import java.util.Map;

import com.hotent.base.manager.api.Manager;
import com.hotent.bo.persistence.model.BOPackage;

public interface BOPackageManager extends Manager<String, BOPackage>{
	
	/**
	 * 根据包类的参数获取包
	 * @param map
	 * @return 
	 * BOPackage
	 * @exception 
	 * @since  1.0.0
	 */
	BOPackage getBOPackageByMap(Map map);
	
}
