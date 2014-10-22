package com.hotent.bo.persistence.dao;
import java.util.List;
import java.util.Map;

import com.hotent.base.db.api.Dao;
import com.hotent.bo.persistence.model.BORule;


public interface BORuleDao extends Dao<String, BORule> {
	
	/**
	 * 根据属性ID获取属性的规则
	 * @param attributeId
	 * @return 
	 * List<BORule>
	 * @exception 
	 * @since  1.0.0
	 */
	List<BORule> getByAttributeId(String attributeId);
	
	/**
	 * 根据规则各参数获取规则
	 * <pre>
	 * 示例：
	 * Map map = new HashMap();
	 * map.put("defId", entityId);
	 * map.put("belongType", BORULE_BELONG_TYPE.DEF);	
	 * List<BORule> bORuleList= bORuleDao.getRules(map);
	 * 返回:
	 * List<BORule> LIST数组
	 * </pre>
	 * @param params
	 * @return 
	 * List<BORule>
	 * @exception 
	 * @since  1.0.0
	 */
	List<BORule> getRules(Map params);
	
	/**
	 * 根据规则各参数获取规则
	 * <pre>
	 * 示例：
	 * Map map = new HashMap();
	 * baMap.put("attrId", boAttribute.getId());
	 * List<BORule> attRuleList= bORuleDao.getRulesByAttrId(baMap);
	 * 返回:
	 * List<BORule> LIST数组
	 * </pre>
	 * @param params
	 * @return 
	 * List<BORule>
	 * @exception 
	 * @since  1.0.0
	 */
	List<BORule> getRulesByAttrId(Map params);

	/**
	 * 根据规则各参数删除规则
	 * <pre>
	 * 示例：
	 * Map map = new HashMap();
	 * baMap.put("attrId", boAttribute.getId());
	 * bORuleRefDao.removeRulesRefs(baMapRef);
	 * </pre>
	 * @param params 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	void removeRules(Map params);
}
