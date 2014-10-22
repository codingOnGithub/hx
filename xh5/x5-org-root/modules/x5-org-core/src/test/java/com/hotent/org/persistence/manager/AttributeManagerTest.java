package com.hotent.org.persistence.manager;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

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
import com.hotent.org.api.model.Attribute;
import com.hotent.org.api.model.Attribute.BelongType;
import com.hotent.org.api.model.Attribute.DataType;
import com.hotent.org.api.model.Attribute.Status;
import com.hotent.org.persistence.OrgBaseTest;
import com.hotent.org.persistence.model.DefaultAttribute;
import com.hotent.org.persistence.model.DefaultAttribute;
import com.hotent.org.persistence.model.DefaultGroup;
import com.hotent.org.persistence.query.AttributeQuery;
import com.hotent.org.persistence.query.AttributeQuery.FindQuery;


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
		Status status = Attribute.Status.actived;
		BelongType belongType = BelongType.user;
		DataType dataType = DataType.TEXT;

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

	
	DefaultAttribute newEntity(BelongType belongType,DataType dataType) {

		DefaultAttribute entity = new DefaultAttribute();
		String id = "" + idGenerator.getUId();
		String key = id;
		String name = "" + new Double(1000000 * Math.random()).intValue();
		String desc = name;
		Status status = Attribute.Status.actived;

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
	
	List<DefaultAttribute> createEntities(BelongType belongType,DataType dataType,int cnt) {
		List<DefaultAttribute> groups = new ArrayList<DefaultAttribute>();
		for (int i = 0; i < cnt; i++) {
			DefaultAttribute group = newEntity(belongType,dataType);
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
	public void testCreate() {
		List<DefaultAttribute> entities = new ArrayList<DefaultAttribute>();
		for (int i = 0; i < 100; i++) {
			DefaultAttribute entity = newEntity();
			entities.add(entity);
			manager.create(entity);
		}

		for (DefaultAttribute entity : entities) {
			AttributeQuery.FindQuery query = new AttributeQuery.FindQuery();
			query.createCriteria().andAttrIdEqualTo(entity.getAttrId());
			List<DefaultAttribute> us = manager.queryByCriteria(query, new DefaultPage());
			assertEquals(1, us.size());
		}

	}

	@Test
	@Rollback(true)
	public void testQueryByCriteria() {
		{
			AttributeQuery.FindQuery query = new AttributeQuery.FindQuery();
			query.createCriteria().andAttrIdEqualTo("1");
			manager.queryByCriteria(query);
		}

		{
			AttributeQuery.FindQuery query = new AttributeQuery.FindQuery();
			// query.createUserGroupCriteria().andAttrIdEqualTo("2");
			manager.queryByCriteria(query);
		}

		{
			AttributeQuery.FindQuery query = new AttributeQuery.FindQuery();
			query.createCriteria().andAttrIdEqualTo("3");
			// query.createUserGroupCriteria().andAttrIdEqualTo("3");
			manager.queryByCriteria(query);
		}

		{
			AttributeQuery.FindQuery query = new AttributeQuery.FindQuery();
			query.createCriteria().andAttrIdEqualTo("1.1");
			// query.or().andFullnameEqualTo("1.2");
			manager.queryByCriteria(query);
		}

		{
			AttributeQuery.FindQuery query = new AttributeQuery.FindQuery();
			// query.createUserGroupCriteria().andAttrIdEqualTo("2.1");
			// query.orUserGroupCriteria().andAttrIdEqualTo("2.2");
			manager.queryByCriteria(query);
		}

		{
			AttributeQuery.FindQuery query = new AttributeQuery.FindQuery();
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

		String newu2_str = ToStringBuilder.reflectionToString(newu2,ToStringStyle.SHORT_PREFIX_STYLE);
		String u_str = ToStringBuilder.reflectionToString(u,ToStringStyle.SHORT_PREFIX_STYLE);
		
		
		
		AttributeQuery.FindQuery q = new FindQuery();
		q.createCriteria().andAttrIdEqualTo(u.getAttrId());
		DefaultAttribute uuuu = manager.queryByCriteria(q).get(0);
		String uuuu_str = ToStringBuilder.reflectionToString(uuuu,ToStringStyle.SHORT_PREFIX_STYLE);
		System.out.println(newu2_str);
		System.out.println(u_str);
		System.out.println(uuuu_str);
		assertTrue(newu2_str.equals(u_str));
	}

	@Test
	@Rollback(true)
	public void testRemove() {
		List<DefaultAttribute> news = createEntities(10);
		for (DefaultAttribute n : news) {
			// ////validate created users;
			{
				AttributeQuery.FindQuery query = new AttributeQuery.FindQuery();
				query.createCriteria().andAttrIdEqualTo(n.getAttrId());
				List<DefaultAttribute> users = manager.queryByCriteria(query);
				assertEquals(1, users.size());
			}
			// ////delete created users;
			manager.remove(n.getAttrId());
			// ////validate deleted users;
			{
				AttributeQuery.FindQuery query = new AttributeQuery.FindQuery();
				query.createCriteria().andAttrIdEqualTo(n.getAttrId()).andStatusEqualTo(Attribute.Status.actived);
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
			assertEquals(Attribute.Status.deleted, u.getStatus());
		}
	}

	@Test
	@Rollback(true)
	public void testRemoveByIds() {
		List<DefaultAttribute> news = createEntities(10);

		// ////validate created users;
		for (DefaultAttribute n : news) {
			{
				AttributeQuery.FindQuery query = new AttributeQuery.FindQuery();
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
				AttributeQuery.FindQuery query = new AttributeQuery.FindQuery();
				query.createCriteria().andAttrIdEqualTo(n.getAttrId()).andStatusNotEqualTo(Status.deleted);
				List<DefaultAttribute> users = manager.queryByCriteria(query);
				assertEquals(0, users.size());
			}

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
