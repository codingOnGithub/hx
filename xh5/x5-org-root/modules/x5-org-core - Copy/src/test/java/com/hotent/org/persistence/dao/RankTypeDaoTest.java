package com.hotent.org.persistence.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;

import com.hotent.base.api.Page;
import com.hotent.base.api.query.QueryFilter;
import com.hotent.base.db.model.DefaultQueryFilter;
import com.hotent.base.db.mybatis.domain.DefaultPage;
import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.org.api.model.Dimension;
import com.hotent.org.persistence.OrgBaseTest;
import com.hotent.org.persistence.model.DefaultRankType;
import com.hotent.org.persistence.model.DefaultRankType;
import com.hotent.org.persistence.model.DefaultGroup;
import com.hotent.org.persistence.query.RankTypeQuery;


@Repository
public class RankTypeDaoTest extends OrgBaseTest {

	@Resource
	RankTypeDao dao;
	
	@Resource
	DimensionDaoTest dimensionDaoTest;

	DefaultRankType newEntity() {
		
		Dimension dimension = dimensionDaoTest.createEntities(1).get(0);
		
		DefaultRankType entity = new DefaultRankType();
		String id = "" + idGenerator.getUId();
		String dimId = dimension.getDimId();
		String key = id;
		String name = "" + new Double(1000000 * Math.random()).intValue();
		String desc = name;
		Long level =new Double(3 * Math.random()).longValue();

		entity.setId(id);
		entity.setDimId(dimId);
		entity.setKey(key);
		entity.setLevel(level);
		entity.setName(name);
		return entity;
	}

	List<DefaultRankType> createEntities(int cnt) {
		List<DefaultRankType> groups = new ArrayList<DefaultRankType>();
		for (int i = 0; i < cnt; i++) {
			DefaultRankType group = newEntity();
			groups.add(group);
			dao.create(group);
		}
		return groups;
	}


	@Test
	@Rollback(true)
	public void testCreate() {
		List<DefaultRankType> entities = new ArrayList<DefaultRankType>();
		for (int i = 0; i < 100; i++) {
			DefaultRankType entity = newEntity();
			entities.add(entity);
			dao.create(entity);
		}

		for (DefaultRankType entity : entities) {
			RankTypeQuery query = new RankTypeQuery();
			query.createCriteria().andIdEqualTo(entity.getId());
			List<DefaultRankType> us = dao.queryByCriteria(query, new DefaultPage());
			assertEquals(1, us.size());
		}

	}

	@Test
	@Rollback(true)
	public void testQueryByCriteria() {
		{
			RankTypeQuery query = new RankTypeQuery();
			query.createCriteria().andDimIdEqualTo("1");
			dao.queryByCriteria(query);
		}

		{
			RankTypeQuery query = new RankTypeQuery();
			// query.createUserGroupCriteria().andDimIdEqualTo("2");
			dao.queryByCriteria(query);
		}

		{
			RankTypeQuery query = new RankTypeQuery();
			query.createCriteria().andDimIdEqualTo("3");
			// query.createUserGroupCriteria().andDimIdEqualTo("3");
			dao.queryByCriteria(query);
		}

		{
			RankTypeQuery query = new RankTypeQuery();
			query.createCriteria().andDimIdEqualTo("1.1");
			// query.or().andFullnameEqualTo("1.2");
			dao.queryByCriteria(query);
		}

		{
			RankTypeQuery query = new RankTypeQuery();
			// query.createUserGroupCriteria().andDimIdEqualTo("2.1");
			// query.orUserGroupCriteria().andDimIdEqualTo("2.2");
			dao.queryByCriteria(query);
		}

		{
			RankTypeQuery query = new RankTypeQuery();
			// query.createCriteria().andAccountEqualTo("3.1");

			// query.createUserGroupCriteria().andDimIdEqualTo("3.1");

			// query.orUserGroupCriteria().andDimIdEqualTo("3.2");
			dao.queryByCriteria(query);
		}
	}

	@Test
	@Rollback(true)
	public void testUpdate() {
		DefaultRankType newu = (DefaultRankType) createEntities(1).get(0);
		DefaultRankType newu2 = newEntity();

		newu2.setId(newu.getId());
		dao.update(newu2);

		DefaultRankType u = (DefaultRankType) dao.get(newu.getId());
		{
			newu2.setUpdateBy(null);
			newu2.setUpdateTime(null);
			
			u.setUpdateBy(null);
			u.setUpdateTime(null);
		}

		String newu2_str = ToStringBuilder.reflectionToString(newu2);
		newu2_str = newu2_str.substring(newu2_str.indexOf("["));
		String u_str = ToStringBuilder.reflectionToString(u);
		u_str = u_str.substring(u_str.indexOf("["));
		assertTrue(newu2_str.equals(u_str));
	}

	@Test
	@Rollback(true)
	public void testRemove() {
		List<DefaultRankType> news = createEntities(10);
		for (DefaultRankType n : news) {
			// ////validate created users;
			{
				RankTypeQuery query = new RankTypeQuery();
				query.createCriteria().andIdEqualTo(n.getId());
				List<DefaultRankType> users = dao.queryByCriteria(query);
				assertEquals(1, users.size());
			}
			// ////delete created users;
			dao.remove(n.getId());
			// ////validate deleted users;
			{
				RankTypeQuery query = new RankTypeQuery();
				query.createCriteria().andIdEqualTo(n.getId());
				List<DefaultRankType> users = dao.queryByCriteria(query);
				assertEquals(0, users.size());
			}

		}
	}

	@Test
	@Rollback(true)
	public void testGet() {
		List<DefaultRankType> news = createEntities(1);
		{
			DefaultRankType u = (DefaultRankType) dao.get(news.get(0).getId());
			assertNotNull(u);
		}

		{
			dao.remove(news.get(0).getId());
		}

		{
			DefaultRankType u = (DefaultRankType) dao.get(news.get(0).getId());
			assertNull(u);
		}
	}

	@Test
	@Rollback(true)
	public void testRemoveByIds() {
		List<DefaultRankType> news = createEntities(10);

		// ////validate created users;
		for (DefaultRankType n : news) {
			{
				RankTypeQuery query = new RankTypeQuery();
				query.createCriteria().andIdEqualTo(n.getId());
				List<DefaultRankType> users = dao.queryByCriteria(query);
				assertEquals(1, users.size());
			}

		}

		// ////delete created users;
		List<String> userIds = new ArrayList<String>();
		for (DefaultRankType entity : news) {
			userIds.add(entity.getId());
		}
		dao.removeByIds(userIds.toArray(new String[0]));

		// ////validate deleted users;
		for (DefaultRankType n : news) {
			{
				RankTypeQuery query = new RankTypeQuery();
				query.createCriteria().andIdEqualTo(n.getId());
				List<DefaultRankType> users = dao.queryByCriteria(query);
				assertEquals(0, users.size());
			}

		}

	}

	@Test
	@Rollback(true)
	public void testQuery() {
		{
			RankTypeQuery query = new RankTypeQuery();
			List<DefaultRankType> users1 = dao.queryByCriteria(query);

			List<DefaultRankType> news2 = createEntities(10);
			RankTypeQuery query2 = new RankTypeQuery();
			List<DefaultRankType> users2 = dao.queryByCriteria(query2);
			assertEquals(users1.size() + 10, users2.size());
		}
		// no setting
		{
			QueryFilter queryFilter = new DefaultQueryFilter();
			PageList<DefaultRankType> users1 = (PageList<DefaultRankType>) dao.query(queryFilter);

			List<DefaultRankType> news2 = createEntities(10);
			QueryFilter queryFilter2 = new DefaultQueryFilter();
			PageList<DefaultRankType> users2 = (PageList<DefaultRankType>) dao.query(queryFilter2);
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
		List<DefaultRankType> users = dao.getAllByPage(page);
		assertEquals(10, users.size());
	}

}
