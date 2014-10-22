package com.hotent.org.persistence.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.base.api.Page;
import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.org.persistence.dao.RankTypeDao;
import com.hotent.org.persistence.model.DefaultRankType;
import com.hotent.org.persistence.query.RankTypeQuery;

@Repository
public class RankTypeDaoImpl extends MyBatisDaoImpl<String, DefaultRankType> implements RankTypeDao{

    @Override
    public String getNamespace() {
        return DefaultRankType.class.getName();
    }
	/**
	 * 根据外键获取子表明细列表
	 * @param dimId
	 * @return
	 */
	public List<DefaultRankType> getByMainId(String dimId) {
		Map<String,Object>params=new HashMap<String, Object>();
		params.put("dimId", dimId);
		return this.getByKey("getRankTypeList", params);
	}
	
	/**
	 * 根据外键删除子表记录
	 * @param dimId
	 * @return
	 */
	public void delByMainId(String dimId) {
		Map<String,Object>params=new HashMap<String, Object>();
		params.put("dimId", dimId);
		this.deleteByKey("delByMainId", params);
	}
	
	
	
	@Override
	public List<DefaultRankType> queryByCriteria(RankTypeQuery.FindQuery query) {
		return this.getByKey("queryByCriteria", query);
	}
	
	
	@Override
	public List<DefaultRankType> queryByCriteria(RankTypeQuery.FindQuery query, Page page) {
		return this.getByKey("queryByCriteria", query, page);
	}

	@Override
	public long queryCountByCriteria(RankTypeQuery.FindQuery query) {
		return (Long) this.getOne("queryCountByCriteria", query);
	}
	
	@Override
	public void updateByUpdateQuery(RankTypeQuery.UpdateQuery uQuery){
		this.updateByKey("updateByUpdateQuery",uQuery);
	}

	
	@Override
	public void deleteByDeleteQuery(RankTypeQuery.DeleteQuery dQuery) {
		this.deleteByKey("deleteByDeleteQuery",dQuery);
	}
	
}
