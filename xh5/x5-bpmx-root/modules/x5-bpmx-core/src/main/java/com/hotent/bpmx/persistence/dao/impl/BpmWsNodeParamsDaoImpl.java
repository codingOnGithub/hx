package com.hotent.bpmx.persistence.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.bpmx.persistence.dao.BpmWsNodeParamsDao;
import com.hotent.bpmx.persistence.model.BpmWsNodeParams;

@Repository
public class BpmWsNodeParamsDaoImpl extends MyBatisDaoImpl<String, BpmWsNodeParams> implements BpmWsNodeParamsDao{

    @Override
    public String getNamespace() {
        return BpmWsNodeParams.class.getName();
    }
	/**
	 * 根据外键获取子表明细列表
	 * @param wsNodeId
	 * @return
	 */
	public List<BpmWsNodeParams> getByMainId(String wsNodeId) {
		Map<String,Object>params=new HashMap<String, Object>();
		params.put("wsNodeId", wsNodeId);
		return this.getByKey("getBpmWsNodeParamsList", params);
	}
	
	/**
	 * 根据外键删除子表记录
	 * @param wsNodeId
	 * @return
	 */
	public void delByMainId(String wsNodeId) {
		Map<String,Object>params=new HashMap<String, Object>();
		params.put("wsNodeId", wsNodeId);
		this.deleteByKey("delByMainId", params);
	}
	
	
}
