package com.hotent.bo.persistence.manager;

import java.util.List;

import com.hotent.base.manager.api.Manager;
import com.hotent.bo.persistence.model.BORule;

public interface BORuleManager extends Manager<String, BORule>{
	/**
	 * 根据属性ID获取属性的规则
	 * @param attributeId
	 * @return 
	 * List<BORule>
	 * @exception 
	 * @since  1.0.0
	 */
	List<BORule> getByAttributeId(String attributeId);
}
