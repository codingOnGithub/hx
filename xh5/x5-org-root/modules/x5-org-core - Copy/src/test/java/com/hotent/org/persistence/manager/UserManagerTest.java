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
import com.hotent.org.api.model.AttributeValue;
import com.hotent.org.api.model.User;
import com.hotent.org.api.model.UserGroupRelation;
import com.hotent.org.persistence.manager.UserManager;
import com.hotent.org.persistence.model.DefaultUser;
import com.hotent.org.persistence.query.UserGroupRelationQuery;
import com.hotent.org.persistence.query.UserQuery;
import com.hotent.org.persistence.OrgBaseTest;


@Repository
public class UserManagerTest extends OrgBaseTest{
	@Resource
	UserManager userManager;
	
	@Resource
	AttributeValueManagerTest attributeValueManagerTest;
	
	DefaultUser newDefaultUser(){
		DefaultUser user=new DefaultUser();
		String id = ""+idGenerator.getUId();
//		String account =""+ new Double(1000000*Math.random()).intValue();
		String account = "admin";
		String fullname = account;
		String password =  "1";
		String status = "" + new Double(10*Math.random()).intValue();
		
		user.setId(id);
		user.setAccount(account);
		user.setFullname(fullname);
		user.setStatus(status);
		user.setPassword(password);
		user.setCreateTime(new Date());
		return user;
	}
	
	
	List<User> createUsers(int cnt){
		List<User> users = new ArrayList<User>();
		for(int i=0;i<cnt;i++){
			DefaultUser user= newDefaultUser();
			users.add(user);
			userManager.create(user);
		}
		return users;
	}
	
	
	@Test
//	@Rollback(true)
	public void testCreate(){
		List<User> users = new ArrayList<User>();
		for(int i=0;i<1;i++){
			DefaultUser user= newDefaultUser();
			users.add(user);
			userManager.create(user);
		}
		
		for(User user:users){
			UserQuery query = new UserQuery();
			query.createCriteria().andUserIdEqualTo(user.getUserId());
			List<DefaultUser> us = userManager.queryByCriteria(query,new DefaultPage());
			assertEquals(1, us.size());
		}
		
	}
//	//@Test
	@Rollback(true)
	public void testGetByAccount() {
		//create users
		List<User> users = createUsers(30);
		
		//validate created user
		for(User user:users){
			DefaultUser us = userManager.getByAccount(user.getAccount());
			assertNotNull(us);
		}
		
		{
			DefaultUser us = userManager.getByAccount("admin");
			assertNotNull(us);
		}
	}

	//@Test
	public void testQueryByUserRelation() {
		fail("Not yet implemented"); // TODO
	}

	//@Test
	public void testQueryByGroupRelation() {
		fail("Not yet implemented"); // TODO
	}

//	@Test
	@Rollback(true)
	public void testQueryUserByAttributeValue() {
		AttributeValue attributeValue = attributeValueManagerTest.createEntities(1).get(0);
		User user = userManager.get(attributeValue.getUserId());
//		UserQuery query = new UserQuery();
		List<DefaultUser> users = userManager.queryUserByAttributeValue(attributeValue.getAttrKey(),attributeValue.getLongVal());
		assertEquals(1,users.size());
		assertEquals(user.getUserId(), users.get(0).getUserId());
	
	}


	//@Test
	public void testqueryByCriteria() {
		{
			UserQuery query = new UserQuery();
			query.createCriteria().andAccountEqualTo("1");
			
			userManager.queryByCriteria(query);
		}
		
		{
			UserQuery query = new UserQuery();
			query.createUserGroupCriteria().andGroupIdEqualTo("2");
			userManager.queryByCriteria(query);
		}
		
		{
			UserQuery query = new UserQuery();
			query.createCriteria().andAccountEqualTo("3");
			query.createUserGroupCriteria().andGroupIdEqualTo("3");
			userManager.queryByCriteria(query);
		}
		
		{
			UserQuery query = new UserQuery();
			query.createCriteria().andAccountEqualTo("1.1");
			query.or().andFullnameEqualTo("1.2");
			userManager.queryByCriteria(query);
		}
		
		{
			UserQuery query = new UserQuery();
			query.createUserGroupCriteria().andGroupIdEqualTo("2.1");
			query.orUserGroupCriteria().andGroupIdEqualTo("2.2");
			userManager.queryByCriteria(query);
		}
		
		{
			UserQuery query = new UserQuery();
			query.createCriteria().andAccountEqualTo("3.1");
			
			query.createUserGroupCriteria().andGroupIdEqualTo("3.1");
			
			query.orUserGroupCriteria().andGroupIdEqualTo("3.2");
			userManager.queryByCriteria(query);
		}
	}


//	@Test
	public void testUpdate() {
		DefaultUser newu = (DefaultUser) createUsers(1).get(0);
		DefaultUser newu2 = newDefaultUser();
		
		
		newu2.setUserId(newu.getUserId());
		userManager.update(newu2);
		
		DefaultUser u = userManager.get(newu.getUserId());
		
		String newu2_str = ToStringBuilder.reflectionToString(newu2);
		newu2_str = newu2_str.substring(newu2_str.indexOf("["));
		String u_str = ToStringBuilder.reflectionToString(u);
		u_str = u_str.substring(u_str.indexOf("["));
		assertTrue(newu2_str.equals(u_str));
	}

	@Test
	@Rollback(true)
	public void testRemove() {
		List<User> news = createUsers(10);
		for(User n:news){
			//////validate created users;
			{
				UserQuery query = new UserQuery();
				query.createCriteria().andUserIdEqualTo(n.getUserId());
				List<DefaultUser> users = userManager.queryByCriteria(query);
				assertEquals(1, users.size());
			}
			//////delete created users;
			userManager.remove(n.getUserId());
			//////validate deleted users;
			{
				{
					UserQuery query = new UserQuery();
					query.createCriteria().andUserIdEqualTo(n.getUserId()).andStatusEqualTo(User.STATUS_DELETED);
					List<DefaultUser> users = userManager.queryByCriteria(query);
					assertEquals(1, users.size());
				}
				{
					UserQuery query = new UserQuery();
					query.createCriteria().andUserIdEqualTo(n.getUserId()).andStatusEqualTo(User.STATUS_ENABLED);
					List<DefaultUser> users = userManager.queryByCriteria(query);
					assertEquals(0, users.size());
				}
//				{
//					UserGroupRelationQuery userGroupRelationQuery = new UserGroupRelationQuery();
//					userGroupRelationQuery.createCriteria().andUserIdEqualTo(n.getUserId()).andStatusEqualTo(UserGroupRelation.STATUS_DELETED);
//					List<DefaultUser> users = userGroupManager.queryByCriteria(query);
//					assertEquals(1, users.size());
//				}
			}
			
		}
	}

	//@Test
	@Rollback(true)
	public void testGet() {
		List<User> news = createUsers(1);
		{
			User u = userManager.get(news.get(0).getUserId());
			assertNotNull(u);
		}
		
		{
			userManager.remove(news.get(0).getUserId());
		}
		
		{
			User u = userManager.get(news.get(0).getUserId());
			assertNull(u);
		}
	}

	//@Test
	@Rollback(true)
	public void testRemoveByIds() {
		List<User> news = createUsers(10);
		
		//////validate created users;
		for(User n:news){
			{
				UserQuery query = new UserQuery();
				query.createCriteria().andUserIdEqualTo(n.getUserId());
				List<DefaultUser> users = userManager.queryByCriteria(query);
				assertEquals(1, users.size());
			}
	
			
		}
		
		//////delete created users;
		List<String> userIds = new ArrayList<String>();
		for(User user:news){
			userIds.add(user.getUserId());
		}
		userManager.removeByIds(userIds.toArray(new String[0]));
		
		//////validate deleted users;
		for(User n:news){
			{
				UserQuery query = new UserQuery();
				query.createCriteria().andUserIdEqualTo(n.getUserId());
				List<DefaultUser> users = userManager.queryByCriteria(query);
				assertEquals(0, users.size());
			}
			
		}
		
	}

	//@Test
	@Rollback(true)
	public void testQuery() {
		{
			UserQuery query = new UserQuery();
			List<DefaultUser> users1 = userManager.queryByCriteria(query);
			
			
			List<User> news2 = createUsers(10);
			UserQuery query2 = new UserQuery();
			List<DefaultUser> users2 = userManager.queryByCriteria(query2);
			assertEquals(users1.size()+10, users2.size());
		}
		//no setting
		{
			QueryFilter queryFilter = new DefaultQueryFilter();
			PageList<DefaultUser> users1 = (PageList<DefaultUser>) userManager.query(queryFilter);
			
			List<User> news2 = createUsers(10);
			QueryFilter queryFilter2 = new DefaultQueryFilter();
			PageList<DefaultUser> users2 = (PageList<DefaultUser>) userManager.query(queryFilter2);
			System.out.println(users1.getPageResult().getTotalCount());
			assertEquals(users1.getPageResult().getTotalCount()+10, users2.getPageResult().getTotalCount());
			
		}
	}

//	@Test
	@Rollback(true)
	public void testGetAll() {
		long cnt1 = userManager.getAll().size();
		createUsers(2);
		long cnt2 = userManager.getAll().size();
		assertEquals(cnt2,cnt1+2);
	}

	//@Test
	@Rollback(true)
	public void testGetAllByPage() {
		createUsers(20);
		Page page = new DefaultPage(1, 10);
		List<DefaultUser> users = userManager.getAllByPage(page);
		assertEquals(10,users.size());
	}

	
}
