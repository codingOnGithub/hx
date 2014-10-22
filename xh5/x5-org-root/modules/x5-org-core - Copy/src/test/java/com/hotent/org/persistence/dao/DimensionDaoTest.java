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
import com.hotent.org.persistence.model.DefaultDimension;
import com.hotent.org.persistence.model.DefaultDimension;
import com.hotent.org.persistence.model.DefaultGroup;
import com.hotent.org.persistence.query.DimensionQuery;


@Repository
public class DimensionDaoTest extends OrgBaseTest {

	@Resource
	DimensionDao dao;

	DefaultDimension newEntity() {

		DefaultDimension entity = new DefaultDimension();
		String id = "" + idGenerator.getUId();
		String dimId = id;
		String dimKey = id;
		String name = "" + new Double(1000000 * Math.random()).intValue();
		String desc = name;
		String status = Dimension.STATUS_ENABLED;
		String groupType = "" + new Double(3 * Math.random()).intValue();
		char gradeAuth = 'Y';
		
		entity.setId(id);
		entity.setDesc(desc);
		entity.setDimId(dimId);
		entity.setDimKey(dimKey);
		entity.setStatus(status);
		entity.setName(name);
		entity.setGradeAuth(gradeAuth);
		entity.setIsCombination(Dimension.IS_COMBINATION_Y);
		entity.setIsSystem('Y');;
		entity.setShowType("plat");
		entity.setSn(new Double(1000000 * Math.random()).intValue());
		
		return entity;
	}

	List<DefaultDimension> createEntities(int cnt) {
		List<DefaultDimension> groups = new ArrayList<DefaultDimension>();
		for (int i = 0; i < cnt; i++) {
			DefaultDimension group = newEntity();
			groups.add(group);
			dao.create(group);
		}
		return groups;
	}

//	//@Test
//	@Rollback(true)
//	public void testQueryByTypeOrderBySnAsc() {
//		List<DefaultDimension>  newEs = this.createEntities(20);
//		final DefaultDimension newE = newEs.get(0);
//		List<DefaultDimension>  sameTypeEs = (List<DefaultDimension>) CollectionUtils.select(newEs, new Predicate() {
//			@Override
//			public boolean evaluate(Object object) {
//				return ((DefaultDimension)object).getGroupType().equals(newE.getGroupType());
//			}
//		});
//		List<DefaultDimension> entities = dao.queryByTypeOrderBySnAsc(newE.getGroupType());
//		assertEquals(sameTypeEs.size(), entities.size());
//		
//		for(int i=0;i<sameTypeEs.size()-1;i++){
//			assertTrue(entities.get(i+1).getSn()>=entities.get(i).getSn());
//		}
//	}



	//@Test
	@Rollback(true)
	public void testCreate() {
		List<DefaultDimension> entities = new ArrayList<DefaultDimension>();
		for (int i = 0; i < 5; i++) {
			DefaultDimension entity = newEntity();
			entities.add(entity);
			dao.create(entity);
		}

		for (DefaultDimension entity : entities) {
			DimensionQuery query = new DimensionQuery();
			query.createCriteria().andDimIdEqualTo(entity.getDimId());
			List<DefaultDimension> us = dao.queryByCriteria(query, new DefaultPage());
			assertEquals(1, us.size());
		}

	}

	//@Test
	@Rollback(true)
	public void testQueryByCriteria() {
		{
			DimensionQuery query = new DimensionQuery();
			query.createCriteria().andDimIdEqualTo("1");
			dao.queryByCriteria(query);
		}

		{
			DimensionQuery query = new DimensionQuery();
			// query.createUserGroupCriteria().andDimIdEqualTo("2");
			dao.queryByCriteria(query);
		}

		{
			DimensionQuery query = new DimensionQuery();
			query.createCriteria().andDimIdEqualTo("3");
			// query.createUserGroupCriteria().andDimIdEqualTo("3");
			dao.queryByCriteria(query);
		}

		{
			DimensionQuery query = new DimensionQuery();
			query.createCriteria().andDimIdEqualTo("1.1");
			// query.or().andFullnameEqualTo("1.2");
			dao.queryByCriteria(query);
		}

		{
			DimensionQuery query = new DimensionQuery();
			// query.createUserGroupCriteria().andDimIdEqualTo("2.1");
			// query.orUserGroupCriteria().andDimIdEqualTo("2.2");
			dao.queryByCriteria(query);
		}

		{
			DimensionQuery query = new DimensionQuery();
			// query.createCriteria().andAccountEqualTo("3.1");

			// query.createUserGroupCriteria().andDimIdEqualTo("3.1");

			// query.orUserGroupCriteria().andDimIdEqualTo("3.2");
			dao.queryByCriteria(query);
		}
	}

	//@Test
	@Rollback(true)
	public void testUpdate() {
		DefaultDimension newu = (DefaultDimension) createEntities(1).get(0);
		DefaultDimension newu2 = newEntity();

		newu2.setDimId(newu.getDimId());
		dao.update(newu2);

		DefaultDimension u = (DefaultDimension) dao.get(newu.getDimId());
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

	//@Test
	@Rollback(true)
	public void testRemove() {
		List<DefaultDimension> news = createEntities(10);
		for (DefaultDimension n : news) {
			// ////validate created users;
			{
				DimensionQuery query = new DimensionQuery();
				query.createCriteria().andDimIdEqualTo(n.getDimId());
				List<DefaultDimension> users = dao.queryByCriteria(query);
				assertEquals(1, users.size());
			}
			// ////delete created users;
			dao.remove(n.getDimId());
			// ////validate deleted users;
			{
				DimensionQuery query = new DimensionQuery();
				query.createCriteria().andDimIdEqualTo(n.getDimId());
				List<DefaultDimension> users = dao.queryByCriteria(query);
				assertEquals(0, users.size());
			}

		}
	}

	//@Test
	@Rollback(true)
	public void testGet() {
		List<DefaultDimension> news = createEntities(1);
		{
			DefaultDimension u = (DefaultDimension) dao.get(news.get(0).getDimId());
			assertNotNull(u);
		}

		{
			dao.remove(news.get(0).getDimId());
		}

		{
			DefaultDimension u = (DefaultDimension) dao.get(news.get(0).getDimId());
			assertNull(u);
		}
	}

	//@Test
	@Rollback(true)
	public void testRemoveByIds() {
		List<DefaultDimension> news = createEntities(10);

		// ////validate created users;
		for (DefaultDimension n : news) {
			{
				DimensionQuery query = new DimensionQuery();
				query.createCriteria().andDimIdEqualTo(n.getDimId());
				List<DefaultDimension> users = dao.queryByCriteria(query);
				assertEquals(1, users.size());
			}

		}

		// ////delete created users;
		List<String> userIds = new ArrayList<String>();
		for (DefaultDimension entity : news) {
			userIds.add(entity.getDimId());
		}
		dao.removeByIds(userIds.toArray(new String[0]));

		// ////validate deleted users;
		for (DefaultDimension n : news) {
			{
				DimensionQuery query = new DimensionQuery();
				query.createCriteria().andDimIdEqualTo(n.getDimId());
				List<DefaultDimension> users = dao.queryByCriteria(query);
				assertEquals(0, users.size());
			}

		}

	}

	//@Test
	@Rollback(true)
	public void testQuery() {
		{
			DimensionQuery query = new DimensionQuery();
			List<DefaultDimension> users1 = dao.queryByCriteria(query);

			List<DefaultDimension> news2 = createEntities(10);
			DimensionQuery query2 = new DimensionQuery();
			List<DefaultDimension> users2 = dao.queryByCriteria(query2);
			assertEquals(users1.size() + 10, users2.size());
		}
		// no setting
		{
			QueryFilter queryFilter = new DefaultQueryFilter();
			PageList<DefaultDimension> users1 = (PageList<DefaultDimension>) dao.query(queryFilter);

			List<DefaultDimension> news2 = createEntities(10);
			QueryFilter queryFilter2 = new DefaultQueryFilter();
			PageList<DefaultDimension> users2 = (PageList<DefaultDimension>) dao.query(queryFilter2);
			System.out.println(users1.getPageResult().getTotalCount());
			assertEquals(users1.getPageResult().getTotalCount() + 10, users2.getPageResult().getTotalCount());

		}
	}

	//@Test
	@Rollback(true)
	public void testGetAll() {
		long cnt1 = dao.getAll().size();
		createEntities(5);
		long cnt2 = dao.getAll().size();
		assertEquals(cnt2, cnt1 + 5);
	}

	//@Test
	@Rollback(true)
	public void testGetAllByPage() {
		createEntities(20);
		Page page = new DefaultPage(1, 10);
		List<DefaultDimension> users = dao.getAllByPage(page);
		assertEquals(10, users.size());
	}

}
