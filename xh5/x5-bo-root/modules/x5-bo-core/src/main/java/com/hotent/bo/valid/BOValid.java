package com.hotent.bo.valid;

import com.hotent.bo.persistence.model.BOAttribute;
import com.hotent.bo.persistence.model.BORule;

public interface BOValid {
	/**
	 * 通过BORule对输入值进行验证
	 * @param rule
	 * @param data
	 * @return 
	 * Boolean
	 * @exception 
	 * @since  1.0.0
	 */
	Boolean valid(BORule rule,Object data);
	
	/**
	 * 通过BOAttribute对输入值进行验证
	 * @param attribute
	 * @param data
	 * @return 
	 * Boolean
	 * @exception 
	 * @since  1.0.0
	 */
	Boolean valid(BOAttribute attribute,Object data);
}
