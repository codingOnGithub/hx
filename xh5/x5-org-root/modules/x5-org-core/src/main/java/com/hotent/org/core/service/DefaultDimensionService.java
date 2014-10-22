package com.hotent.org.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.base.api.Page;
import com.hotent.org.api.model.Dimension;
import com.hotent.org.api.service.DimensionService;
import com.hotent.org.persistence.manager.AttributeValueManager;
import com.hotent.org.persistence.manager.DimensionManager;
import com.hotent.org.persistence.model.DefaultDimension;
import com.hotent.org.persistence.query.DimensionQuery;
import com.hotent.org.persistence.query.DimensionQuery.FindQuery;

@Service
public class DefaultDimensionService implements DimensionService{
	@Resource
	DimensionManager dimensionManager;
	
	@Override
	public Dimension getByDimKey(String dimKey) {
		DimensionQuery.FindQuery query = new FindQuery();
		query.createCriteria().andDimKeyEqualTo(dimKey).andStatusNotEqualTo(Dimension.Status.deleted);
		List<DefaultDimension> dims = dimensionManager.queryByCriteria(query);
		if(dims.isEmpty()){
			return null;
		}else{
			return dims.get(0);
		}
	}

	@Override
	public List<Dimension> getAll() {
		DimensionQuery.FindQuery query = new FindQuery();
		query.createCriteria().andStatusNotEqualTo(Dimension.Status.deleted);
		List<Dimension> dims = (List)dimensionManager.queryByCriteria(query);
		return dims;
	}

	@Override
	public Dimension getByDimId(String dimId) {
		DimensionQuery.FindQuery query = new FindQuery();
		query.createCriteria().andDimIdEqualTo(dimId).andStatusNotEqualTo(Dimension.Status.deleted);
		List<DefaultDimension> dims = dimensionManager.queryByCriteria(query);
		if(dims.isEmpty()){
			return null;
		}else{
			return dims.get(0);
		}
	}

	@Override
	public List<Dimension> getAll(Page page) {
		DimensionQuery.FindQuery query = new FindQuery();
		query.createCriteria().andStatusNotEqualTo(Dimension.Status.deleted);
		return (List)dimensionManager.queryByCriteria(query, page);
	}

}
