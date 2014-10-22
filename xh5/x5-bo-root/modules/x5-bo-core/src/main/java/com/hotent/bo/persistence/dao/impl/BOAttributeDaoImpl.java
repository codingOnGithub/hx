package com.hotent.bo.persistence.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.bo.persistence.dao.BOAttributeDao;
import com.hotent.bo.persistence.model.BOAttribute;

@Repository
public class BOAttributeDaoImpl extends MyBatisDaoImpl<String, BOAttribute> implements BOAttributeDao{

    @Override
    public String getNamespace() {
        return BOAttribute.class.getName();
    }
	/**
	 * 根据外键获取子表明细列表
	 * @param defId
	 * @return
	 */
	public List<BOAttribute> getByMainId(String defId) {
		Map<String,Object>params=new HashMap<String, Object>();
		params.put("defId", defId);
		return this.getByKey("getBOAttributeList", params);
	}
	
	/**
	 * 根据外键删除子表记录
	 * @param defId
	 * @return
	 */
	public void delByMainId(String defId) {
		Map<String,Object>params=new HashMap<String, Object>();
		params.put("defId", defId);
		this.deleteByKey("delByMainId", params);
	}
	
	
}
