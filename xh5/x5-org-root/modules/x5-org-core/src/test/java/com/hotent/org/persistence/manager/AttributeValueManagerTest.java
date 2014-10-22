package com.hotent.org.persistence.manager;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.builder.EqualsBuilder;
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
import com.hotent.org.api.model.Attribute.DataType;
import com.hotent.org.persistence.OrgBaseTest;
import com.hotent.org.persistence.model.DefaultAttributeValue;
import com.hotent.org.persistence.model.DefaultGroup;
import com.hotent.org.persistence.model.DefaultUser;
import com.hotent.org.persistence.query.AttributeValueQuery;


@Repository
public class AttributeValueManagerTest extends OrgBaseTest {

	@Resource
	AttributeManagerTest attributeManagerTest;
	
	@Resource
	UserManagerTest userManagerTest;
	@Resource
	GroupManagerTest groupManagerTest;
	
	
	
	
	@Resource
	AttributeValueManager manager;
	
	@Resource
	UserManager userManager;
	
	@Resource
	GroupManager<String, DefaultGroup> groupManager;
	

	
	
	DefaultAttributeValue newEntity() {
		
		Attribute attribute = attributeManagerTest.createEntities(1).get(0);

		DefaultAttributeValue entity = new DefaultAttributeValue();
		String id = "" + idGenerator.getUId();
		String valId = id;
		String key = id;
		DataType dataType = DataType.DATE;
		Long longVal = 1000L;
		String textVal = "1000L";
		Date dateVal = new Date();
		Double decVal = 100D;
		
		DefaultUser user = (DefaultUser) userManagerTest.createUsers(1).get(0);
		
		DefaultGroup group = groupManagerTest.createGroups(1).get(0);

		entity.setId(id);
		entity.setAttrId(attribute.getAttrId());
		entity.setAttrKey(key);
		entity.setDateVal(dateVal);
		entity.setDecVal(decVal);
		entity.setGroupId(group.getGroupId());
		entity.setLongVal(longVal);
		entity.setTextVal(textVal);
		entity.setUserId(user.getUserId());
		entity.setValId(valId);
		entity.setDataType(dataType);
		return entity;
	}

	List<DefaultAttributeValue> createEntities(int cnt) {
		List<DefaultAttributeValue> groups = new ArrayList<DefaultAttributeValue>();
		for (int i = 0; i < cnt; i++) {
			DefaultAttributeValue group = newEntity();
			groups.add(group);
			manager.create(group);
		}
		return groups;
	}

	@Test
	@Rollback(true)
	public void testCreate() {
		List<DefaultAttributeValue> entities = new ArrayList<DefaultAttributeValue>();
		for (int i = 0; i < 5; i++) {
			DefaultAttributeValue entity = newEntity();
			entities.add(entity);
			manager.create(entity);
		}

		for (DefaultAttributeValue entity : entities) {
			AttributeValueQuery.FindQuery query = new AttributeValueQuery.FindQuery();
			query.createCriteria().andValIdEqualTo(entity.getValId());
			List<DefaultAttributeValue> us = manager.queryByCriteria(query, new DefaultPage());
			assertEquals(1, us.size());
		}

	}

	@Test
	@Rollback(true)
	public void testQueryByCriteria() {
		{
			AttributeValueQuery.FindQuery query = new AttributeValueQuery.FindQuery();
			query.createCriteria().andValIdEqualTo("1");
			manager.queryByCriteria(query);
		}

		{
			AttributeValueQuery.FindQuery query = new AttributeValueQuery.FindQuery();
			// query.createUserGroupCriteria().andValIdEqualTo("2");
			manager.queryByCriteria(query);
		}

		{
			AttributeValueQuery.FindQuery query = new AttributeValueQuery.FindQuery();
			query.createCriteria().andValIdEqualTo("3");
			// query.createUserGroupCriteria().andValIdEqualTo("3");
			manager.queryByCriteria(query);
		}

		{
			AttributeValueQuery.FindQuery query = new AttributeValueQuery.FindQuery();
			query.createCriteria().andValIdEqualTo("1.1");
			// query.or().andFullnameEqualTo("1.2");
			manager.queryByCriteria(query);
		}

		{
			AttributeValueQuery.FindQuery query = new AttributeValueQuery.FindQuery();
			// query.createUserGroupCriteria().andValIdEqualTo("2.1");
			// query.orUserGroupCriteria().andValIdEqualTo("2.2");
			manager.queryByCriteria(query);
		}

		{
			AttributeValueQuery.FindQuery query = new AttributeValueQuery.FindQuery();
			// query.createCriteria().andAccountEqualTo("3.1");

			// query.createUserGroupCriteria().andValIdEqualTo("3.1");

			// query.orUserGroupCriteria().andValIdEqualTo("3.2");
			manager.queryByCriteria(query);
		}
	}

	@Test
	@Rollback(true)
	public void testUpdate() {
		DefaultAttributeValue newu = (DefaultAttributeValue) createEntities(1).get(0);
		DefaultAttributeValue newu2 = newEntity();

		newu2.setValId(newu.getValId());
		manager.update(newu2);

		DefaultAttributeValue u = (DefaultAttributeValue) manager.get(newu.getValId());
		{
			newu2.setUpdateBy(null);
			newu2.setUpdateTime(null);
			
			u.setUpdateBy(null);
			u.setUpdateTime(null);
			
			newu2.setDecVal(u.getDecVal());
		}

		String newu2_str = ToStringBuilder.reflectionToString(newu2, ToStringStyle.SHORT_PREFIX_STYLE);
//		newu2_str = newu2_str.substring(newu2_str.indexOf("["));
		String u_str = ToStringBuilder.reflectionToString(u, ToStringStyle.SHORT_PREFIX_STYLE);
//		u_str = u_str.substring(u_str.indexOf("["));
		
		/*
		 	[valId=10000000435002,attrId=10000000435005,attrKey=10000000435006,userId=10000000435007,groupId=10000000435008,dataType=9,textVal=1000L,dateVal=Thu Feb 13 16:03:53 CST 2014,longVal=1000,decVal=100,createBy=<null>,updateBy=<null>,createTime=<null>,updateTime=<null>,createOrgId=<null>]
			[valId=10000000435002,attrId=10000000435005,attrKey=10000000435006,userId=10000000435007,groupId=10000000435008,dataType=9,textVal=1000L,dateVal=Thu Feb 13 00:00:00 CST 2014,longVal=1000,decVal=100.0000,createBy=<null>,updateBy=<null>,createTime=<null>,updateTime=<null>,createOrgId=<null>]
		 */
		System.out.println(newu2_str);
		System.out.println(u_str);
		assertTrue(newu2_str.equals(u_str));
	}

	@Test
	@Rollback(true)
	public void testRemove() {
		List<DefaultAttributeValue> news = createEntities(10);
		for (DefaultAttributeValue n : news) {
			// ////validate created users;
			{
				AttributeValueQuery.FindQuery query = new AttributeValueQuery.FindQuery();
				query.createCriteria().andValIdEqualTo(n.getValId());
				List<DefaultAttributeValue> users = manager.queryByCriteria(query);
				assertEquals(1, users.size());
			}
			// ////delete created users;
			manager.remove(n.getValId());
			// ////validate deleted users;
			{
				AttributeValueQuery.FindQuery query = new AttributeValueQuery.FindQuery();
				query.createCriteria().andValIdEqualTo(n.getValId());
				List<DefaultAttributeValue> users = manager.queryByCriteria(query);
				assertEquals(0, users.size());
			}

		}
	}

	@Test
	@Rollback(true)
	public void testGet() {
		List<DefaultAttributeValue> news = createEntities(1);
		{
			DefaultAttributeValue u = (DefaultAttributeValue) manager.get(news.get(0).getValId());
			assertNotNull(u);
		}

		{
			manager.remove(news.get(0).getValId());
		}

		{
			DefaultAttributeValue u = (DefaultAttributeValue) manager.get(news.get(0).getValId());
			assertNull(u);
		}
	}

	@Test
	@Rollback(true)
	public void testRemoveByIds() {
		List<DefaultAttributeValue> news = createEntities(3);

		// ////validate created users;
		for (DefaultAttributeValue n : news) {
			{
				AttributeValueQuery.FindQuery query = new AttributeValueQuery.FindQuery();
				query.createCriteria().andValIdEqualTo(n.getValId());
				List<DefaultAttributeValue> users = manager.queryByCriteria(query);
				assertEquals(1, users.size());
			}

		}

		// ////delete created users;
		List<String> userIds = new ArrayList<String>();
		for (DefaultAttributeValue entity : news) {
			userIds.add(entity.getValId());
		}
		manager.removeByIds(userIds.toArray(new String[0]));

		// ////validate deleted users;
		for (DefaultAttributeValue n : news) {
			{
				AttributeValueQuery.FindQuery query = new AttributeValueQuery.FindQuery();
				query.createCriteria().andValIdEqualTo(n.getValId());
				List<DefaultAttributeValue> users = manager.queryByCriteria(query);
				assertEquals(0, users.size());
			}

		}

	}

	@Test
	@Rollback(true)
	public void testQuery() {
		{
			AttributeValueQuery.FindQuery query = new AttributeValueQuery.FindQuery();
			List<DefaultAttributeValue> users1 = manager.queryByCriteria(query);

			List<DefaultAttributeValue> news2 = createEntities(10);
			AttributeValueQuery.FindQuery query2 = new AttributeValueQuery.FindQuery();
			List<DefaultAttributeValue> users2 = manager.queryByCriteria(query2);
			assertEquals(users1.size() + 10, users2.size());
		}
		// no setting
		{
			QueryFilter queryFilter = new DefaultQueryFilter();
			PageList<DefaultAttributeValue> users1 = (PageList<DefaultAttributeValue>) manager.query(queryFilter);

			List<DefaultAttributeValue> news2 = createEntities(10);
			QueryFilter queryFilter2 = new DefaultQueryFilter();
			PageList<DefaultAttributeValue> users2 = (PageList<DefaultAttributeValue>) manager.query(queryFilter2);
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
		List<DefaultAttributeValue> users = manager.getAllByPage(page);
		assertEquals(10, users.size());
	}

}
