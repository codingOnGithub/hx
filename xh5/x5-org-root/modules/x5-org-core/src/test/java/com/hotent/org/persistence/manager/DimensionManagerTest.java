package com.hotent.org.persistence.manager;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;

import com.hotent.base.api.Page;
import com.hotent.base.api.query.Direction;
import com.hotent.base.api.query.QueryFilter;
import com.hotent.base.db.model.DefaultQueryFilter;
import com.hotent.base.db.mybatis.domain.DefaultPage;
import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.org.api.model.Dimension;
import com.hotent.org.api.model.Dimension.Status;
import com.hotent.org.api.model.Group;
import com.hotent.org.persistence.OrgBaseTest;
import com.hotent.org.persistence.dao.DimensionRelationDao;
import com.hotent.org.persistence.model.DefaultDimension;
import com.hotent.org.persistence.model.DefaultDimensionRelation;
import com.hotent.org.persistence.model.DefaultGroup;
import com.hotent.org.persistence.query.DimensionQuery;
import com.hotent.org.persistence.query.DimensionRelationQuery;
import com.hotent.org.persistence.query.GroupQuery;

@Repository
public class DimensionManagerTest extends OrgBaseTest {

	@Resource
	DimensionManager manager;

	@Resource
	DimensionRelationDao dimensionRelationDao;

	@Resource
	GroupManagerTest groupManagerTest;

	@Resource(name = "groupManager")
	GroupManager groupManager;

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

	@Test
	@Rollback(true)
	public void testQueryByTypeOrderBySnAsc() {
		List<DefaultDimension> newEs = this.createEntities(5);
		List<String> ids = new ArrayList<String>();
		for (DefaultDimension d : newEs) {
			ids.add(d.getId());
		}
		DimensionQuery.FindQuery query = new DimensionQuery.FindQuery();
		query.createCriteria().andDimIdIn(ids);
		query.getOrderByClause().orderByName(Direction.ASC);
		List<DefaultDimension> ds = manager.queryByCriteria(query);
		for(int i=0;i<ds.size()-1;i++){
			assertTrue(ds.get(i+1).getName().compareTo(ds.get(i).getName())>=0);
		}
	}

	@Test
	@Rollback(true)
	public void testCreate() {
		// non-combination dimension
		DefaultDimension sigle1 = newEntity();
		sigle1.setIsCombination(Dimension.IS_COMBINATION_N);
		manager.create(sigle1);
		{
			DimensionQuery.FindQuery query = new DimensionQuery.FindQuery();
			query.createCriteria().andDimIdEqualTo(sigle1.getId()).andIsCombinationEqualTo(Dimension.IS_COMBINATION_N);
			List<DefaultDimension> d = manager.queryByCriteria(query);
			assertEquals(1, d.size());
		}

		// non-combination dimension
		DefaultDimension sigle2 = newEntity();
		sigle2.setIsCombination(Dimension.IS_COMBINATION_N);
		manager.create(sigle2);
		{
			DimensionQuery.FindQuery query = new DimensionQuery.FindQuery();
			query.createCriteria().andDimIdEqualTo(sigle2.getId()).andIsCombinationEqualTo(Dimension.IS_COMBINATION_N);
			List<DefaultDimension> d = manager.queryByCriteria(query);
			assertEquals(1, d.size());
		}

		DefaultDimension comp = newEntity();
		comp.setIsCombination(Dimension.IS_COMBINATION_Y);

		DefaultDimensionRelation relation1 = new DefaultDimensionRelation();
		relation1.setId("" + idGenerator.getUId());
		relation1.setDimId(comp.getDimId());
		relation1.setRelDimId(sigle1.getDimId());
		relation1.setRelType("1");

		DefaultDimensionRelation relation2 = new DefaultDimensionRelation();
		relation2.setId("" + idGenerator.getUId());
		relation2.setDimId(comp.getDimId());
		relation2.setRelDimId(sigle2.getDimId());
		relation2.setRelType("2");

		comp.getDimensionRelationList().add(relation1);
		comp.getDimensionRelationList().add(relation2);
		manager.create(comp);
		{
			{
				DimensionQuery.FindQuery query = new DimensionQuery.FindQuery();
				query.createCriteria().andDimIdEqualTo(comp.getId()).andIsCombinationEqualTo(Dimension.IS_COMBINATION_Y);
				List<DefaultDimension> d = manager.queryByCriteria(query);
				assertEquals(1, d.size());
			}
			{
				DimensionRelationQuery.FindQuery query = new DimensionRelationQuery.FindQuery();
				query.createCriteria().andDimIdEqualTo(comp.getId()).andRelDimIdEqualTo(sigle1.getDimId()).andRelTypeEqualTo("1");
				List<DefaultDimensionRelation> d = dimensionRelationDao.queryByCriteria(query);
				assertEquals(1, d.size());
			}
			{
				DimensionRelationQuery.FindQuery query = new DimensionRelationQuery.FindQuery();
				query.createCriteria().andDimIdEqualTo(comp.getId()).andRelDimIdEqualTo(sigle2.getDimId()).andRelTypeEqualTo("2");
				List<DefaultDimensionRelation> d = dimensionRelationDao.queryByCriteria(query);
				assertEquals(1, d.size());
			}
		}
	}

	@Test
	@Rollback(true)
	public void testQueryByCriteria() {
		{
			DimensionQuery.FindQuery query = new DimensionQuery.FindQuery();
			query.createCriteria().andDimIdEqualTo("1");
			manager.queryByCriteria(query);
		}

		{
			DimensionQuery.FindQuery query = new DimensionQuery.FindQuery();
			// query.createUserGroupCriteria().andDimIdEqualTo("2");
			manager.queryByCriteria(query);
		}

		{
			DimensionQuery.FindQuery query = new DimensionQuery.FindQuery();
			query.createCriteria().andDimIdEqualTo("3");
			// query.createUserGroupCriteria().andDimIdEqualTo("3");
			manager.queryByCriteria(query);
		}

		{
			DimensionQuery.FindQuery query = new DimensionQuery.FindQuery();
			query.createCriteria().andDimIdEqualTo("1.1");
			// query.or().andFullnameEqualTo("1.2");
			manager.queryByCriteria(query);
		}

		{
			DimensionQuery.FindQuery query = new DimensionQuery.FindQuery();
			// query.createUserGroupCriteria().andDimIdEqualTo("2.1");
			// query.orUserGroupCriteria().andDimIdEqualTo("2.2");
			manager.queryByCriteria(query);
		}

		{
			DimensionQuery.FindQuery query = new DimensionQuery.FindQuery();
			// query.createCriteria().andAccountEqualTo("3.1");

			// query.createUserGroupCriteria().andDimIdEqualTo("3.1");

			// query.orUserGroupCriteria().andDimIdEqualTo("3.2");
			manager.queryByCriteria(query);
		}
	}

	@Test
	@Rollback(true)
	public void testUpdate() {
		DefaultDimension newu = (DefaultDimension) createEntities(1).get(0);
		DefaultDimension newu2 = newEntity();

		newu2.setDimId(newu.getDimId());
		manager.update(newu2);

		DefaultDimension u = (DefaultDimension) manager.get(newu.getDimId());
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
		List<DefaultDimension> news = createEntities(10);
		for (DefaultDimension n : news) {
			// ////validate created users;
			{
				DimensionQuery.FindQuery query = new DimensionQuery.FindQuery();
				query.createCriteria().andDimIdEqualTo(n.getDimId());
				List<DefaultDimension> users = manager.queryByCriteria(query);
				assertEquals(1, users.size());
			}
			// ////delete created users;
			manager.remove(n.getDimId());
			// ////validate deleted users;
			{
				DimensionQuery.FindQuery query = new DimensionQuery.FindQuery();
				query.createCriteria().andDimIdEqualTo(n.getDimId()).andStatusEqualTo(Dimension.Status.deleted);
				List<DefaultDimension> users = manager.queryByCriteria(query);
				assertEquals(1, users.size());
			}

		}

		// 删除组合类型维度
		{
			DefaultDimension sigle1 = newEntity();
			sigle1.setIsCombination(Dimension.IS_COMBINATION_N);
			manager.create(sigle1);

			DefaultDimension sigle2 = newEntity();
			sigle2.setIsCombination(Dimension.IS_COMBINATION_N);
			manager.create(sigle2);

			DefaultDimension comp = newEntity();

			DefaultDimensionRelation relation1 = new DefaultDimensionRelation();
			relation1.setId("" + idGenerator.getUId());
			relation1.setDimId(comp.getDimId());
			relation1.setRelDimId(sigle1.getDimId());
			relation1.setRelType("1");

			DefaultDimensionRelation relation2 = new DefaultDimensionRelation();
			relation2.setId("" + idGenerator.getUId());
			relation2.setDimId(comp.getDimId());
			relation2.setRelDimId(sigle2.getDimId());
			relation2.setRelType("2");

			comp.getDimensionRelationList().add(relation1);
			comp.getDimensionRelationList().add(relation2);
			manager.create(comp);

			// ////////////////////
			manager.remove(comp.getDimId());
		}
	}

	@Test
	@Rollback(true)
	public void testRemove_combinationType() {
		DefaultDimension sigle1 = newEntity();
		sigle1.setIsCombination(Dimension.IS_COMBINATION_N);
		manager.create(sigle1);

		DefaultDimension sigle2 = newEntity();
		sigle2.setIsCombination(Dimension.IS_COMBINATION_N);
		manager.create(sigle2);

		DefaultDimension comp = newEntity();

		DefaultDimensionRelation relation1 = new DefaultDimensionRelation();
		relation1.setId("" + idGenerator.getUId());
		relation1.setDimId(comp.getDimId());
		relation1.setRelDimId(sigle1.getDimId());
		relation1.setRelType("1");

		DefaultDimensionRelation relation2 = new DefaultDimensionRelation();
		relation2.setId("" + idGenerator.getUId());
		relation2.setDimId(comp.getDimId());
		relation2.setRelDimId(sigle2.getDimId());
		relation2.setRelType("2");

		comp.getDimensionRelationList().add(relation1);
		comp.getDimensionRelationList().add(relation2);
		manager.create(comp);

		// ////////////////////
		manager.remove(comp.getDimId());
	}

	@Test
	@Rollback(true)
	public void testRemove_relationGroup() {
		DefaultDimension sigle1 = newEntity();
		sigle1.setIsCombination(Dimension.IS_COMBINATION_N);
		manager.create(sigle1);
		// group
		DefaultGroup group1_1 = groupManagerTest.newDefaultGroup();
		group1_1.setDimId(sigle1.getDimKey());
		groupManager.create(group1_1);

		DefaultDimension sigle2 = newEntity();
		sigle2.setIsCombination(Dimension.IS_COMBINATION_N);
		manager.create(sigle2);
		// group
		DefaultGroup group2_1 = groupManagerTest.newDefaultGroup();
		group2_1.setDimId(sigle2.getDimKey());
		groupManager.create(group2_1);

		DefaultDimension comp = newEntity();

		DefaultDimensionRelation relation1 = new DefaultDimensionRelation();
		relation1.setId("" + idGenerator.getUId());
		relation1.setDimId(comp.getDimId());
		relation1.setRelDimId(sigle1.getDimId());
		relation1.setRelType("1");

		DefaultDimensionRelation relation2 = new DefaultDimensionRelation();
		relation2.setId("" + idGenerator.getUId());
		relation2.setDimId(comp.getDimId());
		relation2.setRelDimId(sigle2.getDimId());
		relation2.setRelType("2");

		comp.getDimensionRelationList().add(relation1);
		comp.getDimensionRelationList().add(relation2);
		comp.setIsCombination(Dimension.IS_COMBINATION_Y);
		manager.create(comp);
		// group
		DefaultGroup group3_1 = groupManagerTest.newDefaultGroup();

		group3_1.setDimId(comp.getDimKey());

		groupManager.create(group3_1);

		{
			GroupQuery.FindQuery groupQuery = new GroupQuery.FindQuery();
			groupQuery.createCriteria().andDimIdEqualTo(comp.getDimKey()).andStatusEqualTo(Group.Status.actived);
			assertEquals(1, groupManager.queryByCriteria(groupQuery).size());
		}

		{
			GroupQuery.FindQuery groupQuery = new GroupQuery.FindQuery();
			groupQuery.createCriteria().andDimIdEqualTo(comp.getDimKey()).andStatusEqualTo(Group.Status.deleted);
			assertEquals(0, groupManager.queryByCriteria(groupQuery).size());
		}
		// ////////////////////
		manager.remove(comp.getDimId());

		{
			GroupQuery.FindQuery groupQuery = new GroupQuery.FindQuery();
			groupQuery.createCriteria().andDimIdEqualTo(comp.getDimKey()).andStatusEqualTo(Group.Status.actived);
			assertEquals(0, groupManager.queryByCriteria(groupQuery).size());
		}

		{
			GroupQuery.FindQuery groupQuery = new GroupQuery.FindQuery();
			groupQuery.createCriteria().andDimIdEqualTo(comp.getDimKey()).andStatusEqualTo(Group.Status.deleted);
			assertEquals(1, groupManager.queryByCriteria(groupQuery).size());
		}
	}

	@Test
	@Rollback(true)
	public void testGet() {
		List<DefaultDimension> news = createEntities(1);
		{
			DefaultDimension u = (DefaultDimension) manager.get(news.get(0).getDimId());
			assertNotNull(u);
		}

		{
			manager.remove(news.get(0).getDimId());
		}

		{
			DefaultDimension u = (DefaultDimension) manager.get(news.get(0).getDimId());
			assertEquals(Dimension.Status.deleted, u.getStatus());
		}
	}

	@Test
	@Rollback(true)
	public void testRemoveByIds() {
		List<DefaultDimension> news = createEntities(10);

		// ////validate created users;
		for (DefaultDimension n : news) {
			{
				DimensionQuery.FindQuery query = new DimensionQuery.FindQuery();
				query.createCriteria().andDimIdEqualTo(n.getDimId());
				List<DefaultDimension> users = manager.queryByCriteria(query);
				assertEquals(1, users.size());
			}

		}

		// ////delete created users;
		List<String> userIds = new ArrayList<String>();
		for (DefaultDimension entity : news) {
			userIds.add(entity.getDimId());
		}
		manager.removeByIds(userIds.toArray(new String[0]));

		// ////validate deleted users;
		for (DefaultDimension n : news) {
			{
				DimensionQuery.FindQuery query = new DimensionQuery.FindQuery();
				query.createCriteria().andDimIdEqualTo(n.getDimId()).andStatusEqualTo(Dimension.Status.actived);
				List<DefaultDimension> users = manager.queryByCriteria(query);
				assertEquals(0, users.size());
			}

			{
				DimensionQuery.FindQuery query = new DimensionQuery.FindQuery();
				query.createCriteria().andDimIdEqualTo(n.getDimId()).andStatusEqualTo(Dimension.Status.deleted);
				List<DefaultDimension> users = manager.queryByCriteria(query);
				assertEquals(1, users.size());
			}

		}

	}

	@Test
	@Rollback(true)
	public void testQuery() {
		{
			DimensionQuery.FindQuery query = new DimensionQuery.FindQuery();
			List<DefaultDimension> users1 = manager.queryByCriteria(query);

			List<DefaultDimension> news2 = createEntities(10);
			DimensionQuery.FindQuery query2 = new DimensionQuery.FindQuery();
			List<DefaultDimension> users2 = manager.queryByCriteria(query2);
			assertEquals(users1.size() + 10, users2.size());
		}
		// no setting
		{
			QueryFilter queryFilter = new DefaultQueryFilter();
			PageList<DefaultDimension> users1 = (PageList<DefaultDimension>) manager.query(queryFilter);

			List<DefaultDimension> news2 = createEntities(10);
			QueryFilter queryFilter2 = new DefaultQueryFilter();
			PageList<DefaultDimension> users2 = (PageList<DefaultDimension>) manager.query(queryFilter2);
			System.out.println(users1.getPageResult().getTotalCount());
			assertEquals(users1.getPageResult().getTotalCount() + 10, users2.getPageResult().getTotalCount());

		}
	}

	@Test
	@Rollback(true)
	public void testGetAll() {
		long cnt1 = manager.getAll().size();
		createEntities(5);
		long cnt2 = manager.getAll().size();
		assertEquals(cnt2, cnt1 + 5);
	}

	@Test
	@Rollback(true)
	public void testGetAllByPage() {
		createEntities(20);
		Page page = new DefaultPage(1, 10);
		List<DefaultDimension> users = manager.getAllByPage(page);
		assertEquals(10, users.size());
	}

}
