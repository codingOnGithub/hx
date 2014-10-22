package com.hotent.org.persistence.manager;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;

import com.hotent.base.api.Page;
import com.hotent.base.api.query.QueryFilter;
import com.hotent.base.db.model.DefaultQueryFilter;
import com.hotent.base.db.mybatis.domain.DefaultPage;
import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.org.api.model.Attribute;
import com.hotent.org.persistence.OrgBaseTest;
import com.hotent.org.persistence.model.DefaultAttribute;
import com.hotent.org.persistence.model.DefaultAttribute;
import com.hotent.org.persistence.model.DefaultGroup;
import com.hotent.org.persistence.query.AttributeQuery;


@Repository
public class AttributeManagerTest extends OrgBaseTest {

	@Resource
	AttributeManager manager;

	DefaultAttribute newEntity() {

		DefaultAttribute entity = new DefaultAttribute();
		String id = "" + idGenerator.getUId();
		String key = id;
		String name = "" + new Double(1000000 * Math.random()).intValue();
		String desc = name;
		String status = Attribute.STATUS_ENABLED;
		String belongType = "" + new Double(10 * Math.random()).intValue();
		String dataType = "" + new Double(10 * Math.random()).intValue();

		entity.setId(id);
		entity.setAttrId(id);
		entity.setAttrKey(key);
		entity.setBelongType(belongType);
		entity.setDataType(dataType);
		entity.setStatus(status);
		entity.setDesc(desc);
		entity.setName(name);
		return entity;
	}

	List<DefaultAttribute> createEntities(int cnt) {
		List<DefaultAttribute> groups = new ArrayList<DefaultAttribute>();
		for (int i = 0; i < cnt; i++) {
			DefaultAttribute group = newEntity();
			groups.add(group);
			manager.create(group);
		}
		return groups;
	}

	@Test
	@Rollback(true)
	public void testGetByAttrKey() {
		List<DefaultAttribute> newEntities = this.createEntities(5);
		for(DefaultAttribute entity:newEntities){
			DefaultAttribute ent = manager.getByAttrKey(entity.getAttrKey());
			assertNotNull(ent);
		}
	}

	@Test
	@Rollback(true)
	public void testQueryByBelongType() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Rollback(true)
	public void testQueryByCriteriaAttributeQuery() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Rollback(true)
	public void testQueryByCriteriaAttributeQueryPage() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Rollback(true)
	public void testCreate() {
		List<DefaultAttribute> entities = new ArrayList<DefaultAttribute>();
		for (int i = 0; i < 100; i++) {
			DefaultAttribute entity = newEntity();
			entities.add(entity);
			manager.create(entity);
		}

		for (DefaultAttribute entity : entities) {
			AttributeQuery query = new AttributeQuery();
			query.createCriteria().andAttrIdEqualTo(entity.getAttrId());
			List<DefaultAttribute> us = manager.queryByCriteria(query, new DefaultPage());
			assertEquals(1, us.size());
		}

	}

	@Test
	@Rollback(true)
	public void testQueryByCriteria() {
		{
			AttributeQuery query = new AttributeQuery();
			query.createCriteria().andAttrIdEqualTo("1");
			manager.queryByCriteria(query);
		}

		{
			AttributeQuery query = new AttributeQuery();
			// query.createUserGroupCriteria().andAttrIdEqualTo("2");
			manager.queryByCriteria(query);
		}

		{
			AttributeQuery query = new AttributeQuery();
			query.createCriteria().andAttrIdEqualTo("3");
			// query.createUserGroupCriteria().andAttrIdEqualTo("3");
			manager.queryByCriteria(query);
		}

		{
			AttributeQuery query = new AttributeQuery();
			query.createCriteria().andAttrIdEqualTo("1.1");
			// query.or().andFullnameEqualTo("1.2");
			manager.queryByCriteria(query);
		}

		{
			AttributeQuery query = new AttributeQuery();
			// query.createUserGroupCriteria().andAttrIdEqualTo("2.1");
			// query.orUserGroupCriteria().andAttrIdEqualTo("2.2");
			manager.queryByCriteria(query);
		}

		{
			AttributeQuery query = new AttributeQuery();
			// query.createCriteria().andAccountEqualTo("3.1");

			// query.createUserGroupCriteria().andAttrIdEqualTo("3.1");

			// query.orUserGroupCriteria().andAttrIdEqualTo("3.2");
			manager.queryByCriteria(query);
		}
	}

	@Test
	@Rollback(true)
	public void testUpdate() {
		DefaultAttribute newu = (DefaultAttribute) createEntities(1).get(0);
		DefaultAttribute newu2 = newEntity();

		newu2.setAttrId(newu.getAttrId());
		manager.update(newu2);

		DefaultAttribute u = (DefaultAttribute) manager.get(newu.getAttrId());
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
		List<DefaultAttribute> news = createEntities(10);
		for (DefaultAttribute n : news) {
			// ////validate created users;
			{
				AttributeQuery query = new AttributeQuery();
				query.createCriteria().andAttrIdEqualTo(n.getAttrId());
				List<DefaultAttribute> users = manager.queryByCriteria(query);
				assertEquals(1, users.size());
			}
			// ////delete created users;
			manager.remove(n.getAttrId());
			// ////validate deleted users;
			{
				AttributeQuery query = new AttributeQuery();
				query.createCriteria().andAttrIdEqualTo(n.getAttrId());
				List<DefaultAttribute> users = manager.queryByCriteria(query);
				assertEquals(0, users.size());
			}

		}
	}

	@Test
	@Rollback(true)
	public void testGet() {
		List<DefaultAttribute> news = createEntities(1);
		{
			DefaultAttribute u = (DefaultAttribute) manager.get(news.get(0).getAttrId());
			assertNotNull(u);
		}

		{
			manager.remove(news.get(0).getAttrId());
		}

		{
			DefaultAttribute u = (DefaultAttribute) manager.get(news.get(0).getAttrId());
			assertNull(u);
		}
	}

	@Test
	@Rollback(true)
	public void testRemoveByIds() {
		List<DefaultAttribute> news = createEntities(10);

		// ////validate created users;
		for (DefaultAttribute n : news) {
			{
				AttributeQuery query = new AttributeQuery();
				query.createCriteria().andAttrIdEqualTo(n.getAttrId());
				List<DefaultAttribute> users = manager.queryByCriteria(query);
				assertEquals(1, users.size());
			}

		}

		// ////delete created users;
		List<String> userIds = new ArrayList<String>();
		for (DefaultAttribute entity : news) {
			userIds.add(entity.getAttrId());
		}
		manager.removeByIds(userIds.toArray(new String[0]));

		// ////validate deleted users;
		for (DefaultAttribute n : news) {
			{
				AttributeQuery query = new AttributeQuery();
				query.createCriteria().andAttrIdEqualTo(n.getAttrId());
				List<DefaultAttribute> users = manager.queryByCriteria(query);
				assertEquals(0, users.size());
			}

		}

	}

	@Test
	@Rollback(true)
	public void testQuery() {
		{
			AttributeQuery query = new AttributeQuery();
			List<DefaultAttribute> users1 = manager.queryByCriteria(query);

			List<DefaultAttribute> news2 = createEntities(10);
			AttributeQuery query2 = new AttributeQuery();
			List<DefaultAttribute> users2 = manager.queryByCriteria(query2);
			assertEquals(users1.size() + 10, users2.size());
		}
		// no setting
		{
			QueryFilter queryFilter = new DefaultQueryFilter();
			PageList<DefaultAttribute> users1 = (PageList<DefaultAttribute>) manager.query(queryFilter);

			List<DefaultAttribute> news2 = createEntities(10);
			QueryFilter queryFilter2 = new DefaultQueryFilter();
			PageList<DefaultAttribute> users2 = (PageList<DefaultAttribute>) manager.query(queryFilter2);
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
		List<DefaultAttribute> users = manager.getAllByPage(page);
		assertEquals(10, users.size());
	}

}
