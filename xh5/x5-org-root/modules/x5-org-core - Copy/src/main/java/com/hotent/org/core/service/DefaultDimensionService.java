package com.hotent.org.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.org.api.model.Dimension;
import com.hotent.org.api.service.DimensionService;
import com.hotent.org.persistence.manager.AttributeValueManager;
import com.hotent.org.persistence.manager.DimensionManager;

@Service
public class DefaultDimensionService implements DimensionService{
	@Resource
	DimensionManager dimensionManager;
	
	@Override
	public Dimension getByDimKey(String dimKey) {
		return dimensionManager.getByDimKey(dimKey);
	}

	@Override
	public List<Dimension> queryAll() {
		return (List)dimensionManager.getAll();
	}

}
