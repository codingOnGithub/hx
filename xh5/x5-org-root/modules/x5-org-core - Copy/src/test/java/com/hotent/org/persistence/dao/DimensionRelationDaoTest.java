package com.hotent.org.persistence.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.junit.Test;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;

import com.hotent.base.api.Page;
import com.hotent.base.api.query.QueryFilter;
import com.hotent.base.db.model.DefaultQueryFilter;
import com.hotent.base.db.mybatis.domain.DefaultPage;
import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.org.persistence.OrgBaseTest;
import com.hotent.org.persistence.dao.DimensionRelationDao;
import com.hotent.org.persistence.model.DefaultDimension;
import com.hotent.org.persistence.model.DefaultDimensionRelation;
import com.hotent.org.persistence.model.DefaultDimensionRelation;
import com.hotent.org.persistence.model.DefaultGroup;
import com.hotent.org.persistence.query.DimensionRelationQuery;


@Repository
public class DimensionRelationDaoTest extends OrgBaseTest {

	@Resource
	DimensionRelationDao dao;
	
	@Resource
	DimensionDaoTest dimensionDaoTest;

	DefaultDimensionRelation newEntity() {
		List<DefaultDimension> dims = dimensionDaoTest.createEntities(2);

		DefaultDimensionRelation entity = new DefaultDimensionRelation();
		String id = "" + idGenerator.getUId();
		String dimId = dims.get(0).getDimId();
		String relDimId = dims.get(1).getDimId();
		String relType = "" + new Double(3 * Math.random()).intValue();

		entity.setId(id);
		entity.setRelDimId(relDimId);
		entity.setRelType(relType);
		entity.setDimId(dimId);
		
		return entity;
	}

	List<DefaultDimensionRelation> createEntities(int cnt) {
		List<DefaultDimensionRelation> groups = new ArrayList<DefaultDimensionRelation>();
		for (int i = 0; i < cnt; i++) {
			DefaultDimensionRelation group = newEntity();
			groups.add(group);
			dao.create(group);
		}
		return groups;
	}


	@Test
	@Rollback(true)
	public void testCreate() {
		List<DefaultDimensionRelation> entities = new ArrayList<DefaultDimensionRelation>();
		for (int i = 0; i < 100; i++) {
			DefaultDimensionRelation entity = newEntity();
			entities.add(entity);
			dao.create(entity);
		}

		for (DefaultDimensionRelation entity : entities) {
			DimensionRelationQuery query = new DimensionRelationQuery();
			query.createCriteria().andIdEqualTo(entity.getId());
			List<DefaultDimensionRelation> us = dao.queryByCriteria(query, new DefaultPage());
			assertEquals(1, us.size());
		}

	}

	@Test
	@Rollback(true)
	public void testQueryByCriteria() {
		{
			DimensionRelationQuery query = new DimensionRelationQuery();
			query.createCriteria().andIdEqualTo("1");
			dao.queryByCriteria(query);
		}

		{
			DimensionRelationQuery query = new DimensionRelationQuery();
			// query.createUserGroupCriteria().andIdEqualTo("2");
			dao.queryByCriteria(query);
		}

		{
			DimensionRelationQuery query = new DimensionRelationQuery();
			query.createCriteria().andIdEqualTo("3");
			// query.createUserGroupCriteria().andIdEqualTo("3");
			dao.queryByCriteria(query);
		}

		{
			DimensionRelationQuery query = new DimensionRelationQuery();
			query.createCriteria().andIdEqualTo("1.1");
			// query.or().andFullnameEqualTo("1.2");
			dao.queryByCriteria(query);
		}

		{
			DimensionRelationQuery query = new DimensionRelationQuery();
			// query.createUserGroupCriteria().andIdEqualTo("2.1");
			// query.orUserGroupCriteria().andIdEqualTo("2.2");
			dao.queryByCriteria(query);
		}

		{
			DimensionRelationQuery query = new DimensionRelationQuery();
			// query.createCriteria().andAccountEqualTo("3.1");

			// query.createUserGroupCriteria().andIdEqualTo("3.1");

			// query.orUserGroupCriteria().andIdEqualTo("3.2");
			dao.queryByCriteria(query);
		}
	}

	@Test
	@Rollback(true)
	public void testUpdate() {
		DefaultDimensionRelation newu = (DefaultDimensionRelation) createEntities(1).get(0);
		DefaultDimensionRelation newu2 = newEntity();

		newu2.setId(newu.getId());
		dao.update(newu2);

		DefaultDimensionRelation u = (DefaultDimensionRelation) dao.get(newu.getId());
		{
			newu2.setUpdateBy(null);
			newu2.setUpdateTime(null);
			
			u.setUpdateBy(null);
			u.setUpdateTime(null);
		}

		String newu2_str = ToStringBuilder.reflectionToString(newu2,ToStringStyle.SHORT_PREFIX_STYLE);
		String u_str = ToStringBuilder.reflectionToString(u,ToStringStyle.SHORT_PREFIX_STYLE);
		System.out.println(newu2_str);
		System.out.println(u_str);
		assertTrue(newu2_str.equals(u_str));
	}

	@Test
	@Rollback(true)
	public void testRemove() {
		List<DefaultDimensionRelation> news = createEntities(10);
		for (DefaultDimensionRelation n : news) {
			// ////validate created users;
			{
				DimensionRelationQuery query = new DimensionRelationQuery();
				query.createCriteria().andIdEqualTo(n.getId());
				List<DefaultDimensionRelation> users = dao.queryByCriteria(query);
				assertEquals(1, users.size());
			}
			// ////delete created users;
			dao.remove(n.getId());
			// ////validate deleted users;
			{
				DimensionRelationQuery query = new DimensionRelationQuery();
				query.createCriteria().andIdEqualTo(n.getId());
				List<DefaultDimensionRelation> users = dao.queryByCriteria(query);
				assertEquals(0, users.size());
			}

		}
	}

	@Test
	@Rollback(true)
	public void testGet() {
		List<DefaultDimensionRelation> news = createEntities(1);
		{
			DefaultDimensionRelation u = (DefaultDimensionRelation) dao.get(news.get(0).getId());
			assertNotNull(u);
		}

		{
			dao.remove(news.get(0).getId());
		}

		{
			DefaultDimensionRelation u = (DefaultDimensionRelation) dao.get(news.get(0).getId());
			assertNull(u);
		}
	}

	@Test
	@Rollback(true)
	public void testRemoveByIds() {
		List<DefaultDimensionRelation> news = createEntities(10);

		// ////validate created users;
		for (DefaultDimensionRelation n : news) {
			{
				DimensionRelationQuery query = new DimensionRelationQuery();
				query.createCriteria().andIdEqualTo(n.getId());
				List<DefaultDimensionRelation> users = dao.queryByCriteria(query);
				assertEquals(1, users.size());
			}

		}

		// ////delete created users;
		List<String> userIds = new ArrayList<String>();
		for (DefaultDimensionRelation entity : news) {
			userIds.add(entity.getId());
		}
		dao.removeByIds(userIds.toArray(new String[0]));

		// ////validate deleted users;
		for (DefaultDimensionRelation n : news) {
			{
				DimensionRelationQuery query = new DimensionRelationQuery();
				query.createCriteria().andIdEqualTo(n.getId());
				List<DefaultDimensionRelation> users = dao.queryByCriteria(query);
				assertEquals(0, users.size());
			}

		}

	}

	@Test
	@Rollback(true)
	public void testQuery() {
		{
			DimensionRelationQuery query = new DimensionRelationQuery();
			List<DefaultDimensionRelation> users1 = dao.queryByCriteria(query);

			List<DefaultDimensionRelation> news2 = createEntities(10);
			DimensionRelationQuery query2 = new DimensionRelationQuery();
			List<DefaultDimensionRelation> users2 = dao.queryByCriteria(query2);
			assertEquals(users1.size() + 10, users2.size());
		}
		// no setting
		{
			QueryFilter queryFilter = new DefaultQueryFilter();
			PageList<DefaultDimensionRelation> users1 = (PageList<DefaultDimensionRelation>) dao.query(queryFilter);

			List<DefaultDimensionRelation> news2 = createEntities(10);
			QueryFilter queryFilter2 = new DefaultQueryFilter();
			PageList<DefaultDimensionRelation> users2 = (PageList<DefaultDimensionRelation>) dao.query(queryFilter2);
			System.out.println(users1.getPageResult().getTotalCount());
			assertEquals(users1.getPageResult().getTotalCount() + 10, users2.getPageResult().getTotalCount());

		}
	}

	@Test
	@Rollback(true)
	public void testGetAll() {
		long cnt1 = dao.getAll().size();
		createEntities(5);
		long cnt2 = dao.getAll().size();
		assertEquals(cnt2, cnt1 + 5);
	}

	@Test
	@Rollback(true)
	public void testGetAllByPage() {
		createEntities(20);
		Page page = new DefaultPage(1, 10);
		List<DefaultDimensionRelation> users = dao.getAllByPage(page);
		assertEquals(10, users.size());
	}

}
