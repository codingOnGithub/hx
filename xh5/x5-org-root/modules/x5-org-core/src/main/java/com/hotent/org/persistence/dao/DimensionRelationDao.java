package com.hotent.org.persistence.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hotent.base.api.Page;
import com.hotent.base.db.api.Dao;
import com.hotent.org.persistence.model.DefaultAttributeValue;
import com.hotent.org.persistence.model.DefaultDimensionRelation;
import com.hotent.org.persistence.query.DimensionRelationQuery;
import com.hotent.org.persistence.query.DimensionRelationQuery.FindQuery;


public interface DimensionRelationDao extends Dao<String, DefaultDimensionRelation> {
	/**
	 * 根据外键获取子表明细列表
	 * @param dimId
	 * @return
	 */
	public List<DefaultDimensionRelation> getByMainId(String dimId);
	
	/**
	 * 根据外键删除子表记录
	 * @param dimId
	 * @return
	 */
	public void delByMainId(String dimId);
	
	
	List<DefaultDimensionRelation> queryByCriteria(DimensionRelationQuery.FindQuery query);
	
	List<DefaultDimensionRelation> queryByCriteria(DimensionRelationQuery.FindQuery query, Page page);
	
	long queryCountByCriteria(DimensionRelationQuery.FindQuery query);

	void updateByUpdateQuery(DimensionRelationQuery.UpdateQuery uQuery);

	void deleteByDeleteQuery(DimensionRelationQuery.DeleteQuery dQuery);
	
}
