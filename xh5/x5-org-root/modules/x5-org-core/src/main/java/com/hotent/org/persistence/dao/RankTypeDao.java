package com.hotent.org.persistence.dao;
import java.util.List;

import com.hotent.base.api.Page;
import com.hotent.base.db.api.Dao;
import com.hotent.org.persistence.model.DefaultRankType;
import com.hotent.org.persistence.query.RankTypeQuery;


public interface RankTypeDao extends Dao<String, DefaultRankType> {
	/**
	 * 根据外键获取子表明细列表
	 * @param dimId
	 * @return
	 */
	public List<DefaultRankType> getByMainId(String dimId);
	
	/**
	 * 根据外键删除子表记录
	 * @param dimId
	 * @return
	 */
	public void delByMainId(String dimId);
	
	

	List<DefaultRankType> queryByCriteria(RankTypeQuery.FindQuery query);
	
	List<DefaultRankType> queryByCriteria(RankTypeQuery.FindQuery query, Page page);
	
	long queryCountByCriteria(RankTypeQuery.FindQuery query);

	void updateByUpdateQuery(RankTypeQuery.UpdateQuery uQuery);

	void deleteByDeleteQuery(RankTypeQuery.DeleteQuery dQuery);
	
}
