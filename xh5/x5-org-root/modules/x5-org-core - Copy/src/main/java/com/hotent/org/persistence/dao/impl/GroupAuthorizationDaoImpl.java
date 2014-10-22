package com.hotent.org.persistence.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.org.persistence.dao.GroupAuthorizationDao;
import com.hotent.org.persistence.model.DefaultGroupAuthorization;

@Repository
public class GroupAuthorizationDaoImpl extends MyBatisDaoImpl<String, DefaultGroupAuthorization> implements GroupAuthorizationDao{

    @Override
    public String getNamespace() {
        return DefaultGroupAuthorization.class.getName();
    }
	/**
	 * 根据外键获取子表明细列表
	 * @param relTypeId
	 * @return
	 */
	public List<DefaultGroupAuthorization> getByMainId(String relTypeId) {
		Map<String,Object>params=new HashMap<String, Object>();
		params.put("relTypeId", relTypeId);
		return this.getByKey("getGroupAuthorizationList", params);
	}
	
	/**
	 * 根据外键删除子表记录
	 * @param relTypeId
	 * @return
	 */
	public void delByMainId(String relTypeId) {
		Map<String,Object>params=new HashMap<String, Object>();
		params.put("relTypeId", relTypeId);
		this.deleteByKey("delByMainId", params);
	}
	
	
}
