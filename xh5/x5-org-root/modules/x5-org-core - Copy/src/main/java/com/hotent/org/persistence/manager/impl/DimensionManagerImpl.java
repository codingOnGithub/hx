package com.hotent.org.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.base.api.Page;
import com.hotent.base.core.util.BeanUtils;
import com.hotent.base.db.api.Dao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.org.api.OrgException;
import com.hotent.org.api.model.Attribute;
import com.hotent.org.api.model.Dimension;
import com.hotent.org.api.model.DimensionRelation;
import com.hotent.org.api.model.Group;
import com.hotent.org.api.model.GroupRelation;
import com.hotent.org.api.model.UserGroupRelation;
import com.hotent.org.persistence.dao.DimensionDao;
import com.hotent.org.persistence.dao.DimensionRelationDao;
import com.hotent.org.persistence.dao.GroupDao;
import com.hotent.org.persistence.dao.RankTypeDao;
import com.hotent.org.persistence.manager.DimensionManager;
import com.hotent.org.persistence.manager.GroupManager;
import com.hotent.org.persistence.model.DefaultAttributeValue;
import com.hotent.org.persistence.model.DefaultDimension;
import com.hotent.org.persistence.model.DefaultDimension;
import com.hotent.org.persistence.model.DefaultDimensionRelation;
import com.hotent.org.persistence.model.DefaultGroup;
import com.hotent.org.persistence.model.DefaultRankType;
import com.hotent.org.persistence.query.AttributeUpdateQuery;
import com.hotent.org.persistence.query.AttributeValueQuery;
import com.hotent.org.persistence.query.DimensionQuery;
import com.hotent.org.persistence.query.DimensionQuery;
import com.hotent.org.persistence.query.DimensionRelationDeleteQuery;
import com.hotent.org.persistence.query.DimensionRelationUpdateQuery;
import com.hotent.org.persistence.query.DimensionUpdateQuery;
import com.hotent.org.persistence.query.GroupDeleteQuery;
import com.hotent.org.persistence.query.GroupRelationDeleteQuery;
import com.hotent.org.persistence.query.GroupRelationUpdateQuery;
import com.hotent.org.persistence.query.GroupUpdateQuery;
import com.hotent.org.persistence.query.RankTypeDeleteQuery;
import com.hotent.org.persistence.query.RankTypeQuery;
import com.hotent.org.persistence.query.RankTypeUpdateQuery;
import com.hotent.org.persistence.query.UserGroupRelationDeleteQuery;
import com.hotent.org.persistence.query.UserGroupRelationUpdateQuery;

@Service("dimensionManager")
public class DimensionManagerImpl extends AbstractManagerImpl<String, DefaultDimension> implements DimensionManager {
	@Resource
	DimensionDao dimensionDao;

	@Resource(name = "groupManager")
	GroupManager<String, DefaultGroup> groupManager;

	@Resource
	DimensionRelationDao dimensionRelationDao;

	@Resource
	RankTypeDao rankTypeDao;

	@Override
	protected Dao<String, DefaultDimension> getDao() {
		return dimensionDao;
	}

	@Override
	public List<DefaultDimension> queryByTypeOrderBySnAsc(String type) {
		return dimensionDao.queryByTypeOrderBySnAsc(type);
	}

	@Override
	public DefaultDimension getByDimKey(String dimKey) {
		return dimensionDao.getByDimKey(dimKey);
	}

	@Override
	public List<DefaultDimension> queryByCriteria(DimensionQuery query) {
		return dimensionDao.queryByCriteria(query);
	}

	@Override
	public List<DefaultDimension> queryByCriteria(DimensionQuery query, Page page) {
		return dimensionDao.queryByCriteria(query, page);
	}

	/**
	 * 删除维度对象。<br/>
	 * 此删除操作是逻辑删除操作。<br/>
	 * 删除维度对象时，级联维度关联对象，用户组等级对象<br/>
	 * (non-Javadoc)
	 * 
	 * @see com.hotent.base.manager.impl.AbstractManagerImpl#remove(java.io.Serializable
	 *      )
	 */
	public void remove(String entityId) {
		this.remove(entityId, true);
	}

	/**
	 * 删除维度对象。<br/>
	 * 删除维度对象时，级联维度关联对象，用户组等级对象<br/>
	 * 
	 * @param entityId
	 *            要删除的维度对象ID
	 * @param logical
	 *            是事逻辑删除
	 */
	public void remove(String entityId, boolean logical) {
		ensureDataIntegrity(entityId, logical);
		if (logical) {
			DimensionUpdateQuery uQuery = new DimensionUpdateQuery();
			uQuery.createCriteria().andDimIdEqualTo(entityId);
			uQuery.getUpdateClause().setStatus(Dimension.STATUS_DELETED);
			dimensionDao.updateByUpdateQuery(uQuery);
		} else {
			super.remove(entityId);
		}
	}

	private void ensureDataIntegrity(String dimensionId, boolean logical) {
		DefaultDimension dimension = this.get(dimensionId);
		if (dimension == null) {
			return;
		}
		if (logical) {
			// Group
			{

				GroupUpdateQuery groupUpdateQuery = new GroupUpdateQuery();
				groupUpdateQuery.createCriteria().andDimKeyEqualTo(dimension.getDimKey());
				groupUpdateQuery.getUpdateClause().setStatus(Group.STATUS_DELETED);
				groupManager.updateByUpdateQuery(groupUpdateQuery);
			}

		} else {

			// Dimension Relation
			{
				DimensionRelationDeleteQuery dimensionRelationUQuery = new DimensionRelationDeleteQuery();
				dimensionRelationUQuery.createCriteria().andDimIdEqualTo(dimensionId);
				dimensionRelationUQuery.or().andRelDimIdEqualTo(dimensionId);
				dimensionRelationDao.deleteByDeleteQuery(dimensionRelationUQuery);
			}

			// Group Relation
			{
				GroupDeleteQuery groupDeleteQuery = new GroupDeleteQuery();
				groupDeleteQuery.createCriteria().andGroupIdEqualTo(dimensionId);
				groupManager.deleteByDeleteQuery(groupDeleteQuery);
			}

			// Rank Type
			{
				RankTypeDeleteQuery rankTypeDeleteQuery = new RankTypeDeleteQuery();
				rankTypeDeleteQuery.createCriteria().andDimIdEqualTo(dimensionId);
				rankTypeDao.deleteByDeleteQuery(rankTypeDeleteQuery);
			}
		}

	}

	/**
	 * 创建维度对象。 <br/>
	 * 如果是组合维度，需要指定维度关联对象列表<br/>
	 * 如果指定的用户组等级对象，用户组等级对象信息同时保存<br/>
	 * (non-Javadoc)
	 * 
	 * @see com.hotent.base.manager.impl.AbstractManagerImpl#create(java.io.Serializable)
	 */
	@Override
	public void create(DefaultDimension entity) {
		
		// 如果是组织类型的维度，需要同时添加维度关联
		if (Dimension.IS_COMBINATION_Y == entity.getIsCombination()) {
			if (BeanUtils.isEmpty(entity.getDimensionRelationList())) {
				throw new OrgException("组合类型维度，必须指定维度关联对象");
			}
			if (entity.getDimensionRelationList().size()<2) {
				throw new OrgException("组合类型维度，必须由两个或两个以上的维度组成");
			}
			for (DefaultDimensionRelation dimensionRelation : entity.getDimensionRelationList()) {
				DefaultDimension dimension = dimensionDao.get(dimensionRelation.getRelDimId());
				if(dimension.getIsCombination()==Dimension.IS_COMBINATION_Y){
					throw new OrgException("组成复合维度的维度的类型不能是复合类型");
				}
			}			
			
			super.create(entity);
			for (DefaultDimensionRelation dimensionRelation : entity.getDimensionRelationList()) {
				dimensionRelationDao.create(dimensionRelation);
			}
		}else{
			super.create(entity);
		}
		// 如果指定了用户组等级分类，同时保存到数据库
		if (entity.getRankType() != null) {
			rankTypeDao.create(entity.getRankType());
		}
	}
}
