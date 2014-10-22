package com.hotent.bpmx.persistence.dao;
import java.util.List;

import com.hotent.base.db.api.Dao;
import com.hotent.bpmx.persistence.model.BpmWsNodeParams;


public interface BpmWsNodeParamsDao extends Dao<String, BpmWsNodeParams> {
	/**
	 * 根据外键获取子表明细列表
	 * @param wsNodeId
	 * @return
	 */
	public List<BpmWsNodeParams> getByMainId(String wsNodeId);
	
	/**
	 * 根据外键删除子表记录
	 * @param wsNodeId
	 * @return
	 */
	public void delByMainId(String wsNodeId);
	
}
