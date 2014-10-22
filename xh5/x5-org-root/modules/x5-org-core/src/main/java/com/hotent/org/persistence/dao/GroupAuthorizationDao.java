package com.hotent.org.persistence.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hotent.base.db.api.Dao;
import com.hotent.org.persistence.model.DefaultGroupAuthorization;


public interface GroupAuthorizationDao extends Dao<String, DefaultGroupAuthorization> {
	/**
	 * 根据外键获取子表明细列表
	 * @param relTypeId
	 * @return
	 */
	public List<DefaultGroupAuthorization> getByMainId(String relTypeId);
	
	/**
	 * 根据外键删除子表记录
	 * @param relTypeId
	 * @return
	 */
	public void delByMainId(String relTypeId);
	
}
