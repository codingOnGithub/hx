package com.hotent.org.core.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hotent.base.db.mybatis.domain.DefaultPage;
import com.hotent.org.api.model.Dimension;
import com.hotent.org.api.model.Dimension.Status;
import com.hotent.org.api.service.DimensionService;
import com.hotent.org.persistence.OrgBaseTest;
import com.hotent.org.persistence.manager.DimensionManager;
import com.hotent.org.persistence.model.DefaultDimension;
import com.hotent.org.persistence.query.DimensionQuery;
import com.hotent.org.persistence.query.DimensionQuery.FindQuery;

public class DefaultDimensionServiceTest extends OrgBaseTest {
	@Resource 
	DimensionService dimensionService;
	@Resource
	DimensionManager manager;
	
	
	@Test
	@Rollback
	public void testGetByDimKey() {
		DefaultDimension dimension = createEntities(1).get(0);
		Dimension rDimension = dimensionService.getByDimKey(dimension.getDimKey());
		assertNotNull(rDimension);
	}
	@Test
	@Rollback
	public void testGetByDimId() {
		DefaultDimension dimension = createEntities(1).get(0);
		Dimension rDimension = dimensionService.getByDimId(dimension.getDimId());
		assertNotNull(rDimension);
	}

	@Test
	@Rollback
	public void testGetAll() {
		DimensionQuery.DeleteQuery dQuery = new DimensionQuery.DeleteQuery();
		manager.deleteByDeleteQuery(dQuery);
		List<DefaultDimension> dimensions = createEntities(10);
		List<Dimension> rDimensions = dimensionService.getAll();
		assertEquals(dimensions.size(), rDimensions.size());
	}
	
	@Test
	@Rollback
	public void testGetAllPage() {
		DimensionQuery.DeleteQuery dQuery = new DimensionQuery.DeleteQuery();
		manager.deleteByDeleteQuery(dQuery);
		List<DefaultDimension> dimensions = createEntities(10);
		List<Dimension> rDimensions = dimensionService.getAll(new DefaultPage(3));
		assertEquals(3, rDimensions.size());
	}
	
	DefaultDimension newEntity() {

		DefaultDimension entity = new DefaultDimension();
		String id = "" + idGenerator.getUId();
		String dimId = id;
		String dimKey = id;
		String name = "" + new Double(1000000 * Math.random()).intValue();
		String desc = name;
		Status status = Dimension.Status.actived;
		String groupType = "" + new Double(3 * Math.random()).intValue();
		char gradeAuth = 'Y';

		entity.setId(id);
		entity.setDesc(desc);
		entity.setDimId(dimId);
		entity.setDimKey(dimKey);
		entity.setStatus(status);
		entity.setName(name);
		entity.setGradeAuth(gradeAuth);
		entity.setIsCombination('N');
		entity.setIsSystem('Y');
		;
		entity.setShowType("plat");
		entity.setSn(new Double(1000000 * Math.random()).intValue());

		return entity;
	}

	List<DefaultDimension> createEntities(int cnt) {
		List<DefaultDimension> groups = new ArrayList<DefaultDimension>();
		for (int i = 0; i < cnt; i++) {
			DefaultDimension group = newEntity();
			groups.add(group);
			manager.create(group);
		}
		return groups;
	}

}
