package com.hotent.bo.persistence.dao;
import java.util.Map;

import com.hotent.base.db.api.Dao;
import com.hotent.bo.persistence.model.BORuleRef;


public interface BORuleRefDao extends Dao<String, BORuleRef> {
	
	/**
	 * TODO方法名称描述
	 * <pre>
	 * 示例：
	 * Map map = new HashMap();
     * baMapRef.put("attrId", boAttribute.getId());
     * bORuleRefDao.removeRulesRefs(baMapRef); 
     * </pre>
	 * @param params 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	void removeRulesRefs(Map params);
	
}
