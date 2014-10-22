package com.hotent.org.persistence.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.base.api.Page;
import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.org.persistence.dao.DimensionRelationDao;
import com.hotent.org.persistence.model.DefaultDimensionRelation;
import com.hotent.org.persistence.query.DimensionRelationDeleteQuery;
import com.hotent.org.persistence.query.DimensionRelationQuery;
import com.hotent.org.persistence.query.DimensionRelationUpdateQuery;

@Repository
public class DimensionRelationDaoImpl extends MyBatisDaoImpl<String, DefaultDimensionRelation> implements DimensionRelationDao{

    @Override
    public String getNamespace() {
        return DefaultDimensionRelation.class.getName();
    }
	/**
	 * 根据外键获取子表明细列表
	 * @param dimId
	 * @return
	 */
	public List<DefaultDimensionRelation> getByMainId(String dimId) {
		Map<String,Object>params=new HashMap<String, Object>();
		params.put("dimId", dimId);
		return this.getByKey("getDimensionRelationRelationList", params);
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
	public List<DefaultDimensionRelation> queryByCriteria(DimensionRelationQuery query) {
		return this.getByKey("queryByCriteria", query);
	}
	
	
	@Override
	public List<DefaultDimensionRelation> queryByCriteria(DimensionRelationQuery query, Page page) {
		return this.getByKey("queryByCriteria", query, page);
	}

	@Override
	public long queryCountByCriteria(DimensionRelationQuery query) {
		return (Long) this.getOne("queryCountByCriteria", query);
	}
	
	@Override
	public void updateByUpdateQuery(DimensionRelationUpdateQuery uQuery){
		this.updateByKey("updateByUpdateQuery",uQuery);
	}

	
	@Override
	public void deleteByDeleteQuery(DimensionRelationDeleteQuery dQuery) {
		this.deleteByKey("deleteByDeleteQuery",dQuery);
	}
	
	
	
}
