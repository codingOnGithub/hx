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
import com.hotent.org.api.model.RelationType;
import com.hotent.org.api.model.UserRelation;
import com.hotent.org.api.model.RelationType.ConstType;
import com.hotent.org.api.model.RelationType.Type;
import com.hotent.org.api.model.User;
import com.hotent.org.api.model.User.Status;
import com.hotent.org.api.model.UserGroupRelation;
import com.hotent.org.persistence.manager.UserManager;
import com.hotent.org.persistence.manager.impl.GroupManagerImpl;
import com.hotent.org.persistence.model.DefaultRelationType;
import com.hotent.org.persistence.model.DefaultUser;
import com.hotent.org.persistence.model.DefaultUserGroupRelation;
import com.hotent.org.persistence.model.DefaultUserRelation;
import com.hotent.org.persistence.query.UserGroupRelationQuery;
import com.hotent.org.persistence.query.UserQuery;
import com.hotent.org.persistence.OrgBaseTest;

@Repository
public class UserManagerTest extends OrgBaseTest {
	@Resource
	UserManager userManager;

	@Resource
	AttributeValueManagerTest attributeValueManagerTest;
	
	@Resource
	RelationTypeManagerTest relationTypeManagerTest;

	@Resource
	GroupManagerTest groupManagerTest;
	
	DefaultUser newDefaultUser() {
		DefaultUser user = new DefaultUser();
		String id = "" + idGenerator.getUId();
		String account = "" + new Double(1000000 * Math.random()).intValue();
		// String account = "admin";
		String fullname = account;
		String password = "1";
		Status status = Status.actived;

		user.setId(id);
		user.setAccount(account);
		user.setFullname(fullname);
		user.setStatus(status);
		user.setPassword(password);
		user.setCreateTime(new Date());
		return user;
	}
	
	DefaultUserRelation newDefaultUserRelation() {
		DefaultRelationType relationType = relationTypeManagerTest.createEntities(Type.user, ConstType.MANY2MANY, 1).get(0);

		DefaultUser user1 = createUsers(1).get(0);
		DefaultUser user2 = createUsers(1).get(0);
		
		DefaultUserRelation relation = new DefaultUserRelation();
		String id = "" + idGenerator.getUId();

		relation.setId(id);
		relation.setRelTypeId(relationType.getId());
		relation.setRelUserId(user2.getUserId());
		relation.setStatus(UserRelation.Status.actived);
		relation.setUserId(user1.getId());
		return relation;
	}

	List<DefaultUserRelation> createDefaultUserRelations(int cnt) {
		List<DefaultUserRelation> relations = new ArrayList<DefaultUserRelation>();
		for (int i = 0; i < cnt; i++) {
			DefaultUserRelation relation = newDefaultUserRelation();
			relations.add(relation);
			userManager.createUserRelation(relation);
		}
		return relations;
	}
	

	List<DefaultUser> createUsers(int cnt) {
		List<DefaultUser> users = new ArrayList<DefaultUser>();
		for (int i = 0; i < cnt; i++) {
			DefaultUser user = newDefaultUser();
			users.add(user);
			userManager.create(user);
		}
		return users;
	}

	@Test
	 @Rollback(true)
	public void testCreate() {
		List<User> users = new ArrayList<User>();
		for (int i = 0; i < 1; i++) {
			DefaultUser user = newDefaultUser();
			users.add(user);
			userManager.create(user);
		}

		for (User user : users) {
			UserQuery.FindQuery query = new UserQuery.FindQuery();
			query.createCriteria().andUserIdEqualTo(user.getUserId());
			List<DefaultUser> us = userManager.queryByCriteria(query, new DefaultPage());
			assertEquals(1, us.size());
		}

	}

	 @Test
	@Rollback(true)
	public void testGetByAccount() {
		// create users
		List<DefaultUser> users = createUsers(30);

		// validate created user
		for (User user : users) {
			DefaultUser us = userManager.getByAccount(user.getAccount());
			assertNotNull(us);
		}

		{
			DefaultUser us = userManager.getByAccount("admin");
			assertNotNull(us);
		}
	}

	@Test
	@Rollback(true)
	public void testQueryByUserRelation() {
		DefaultUserRelation relation = createDefaultUserRelations(1).get(0);
		List<DefaultUser> users = userManager.queryByUserRelation(relation.getUserId(), relation.getRelTypeId());
		assertEquals(1, users.size());
	}

	@Test
	@Rollback(true)
	public void testQueryByGroupRelation() {
		DefaultUserGroupRelation relation = groupManagerTest.createDefaultUserGroupRelations(1).get(0);
		List<DefaultUser> users = userManager.queryByUserGroupRelation(relation.getGroupId(), relation.getRelTypeId());
		assertEquals(1, users.size());
	}

	@Test
	@Rollback(true)
	public void testQueryUserByAttributeValue() {
		AttributeValue attributeValue = attributeValueManagerTest.createEntities(1).get(0);
		User user = userManager.get(attributeValue.getUserId());
		// UserQuery.FindQuery query = new UserQuery.FindQuery();
		List<DefaultUser> users = userManager.queryByAttributeValue(attributeValue.getAttrKey(), attributeValue.getLongVal());
		assertEquals(1, users.size());
		assertEquals(user.getUserId(), users.get(0).getUserId());

	}

	@Test
	@Rollback(true)
	public void testqueryByCriteria() {
		{
			UserQuery.FindQuery query = new UserQuery.FindQuery();
			query.createCriteria().andAccountEqualTo("1");

			userManager.queryByCriteria(query);
		}

		{
			UserQuery.FindQuery query = new UserQuery.FindQuery();
			query.createUserGroupCriteria().andGroupIdEqualTo("2");
			userManager.queryByCriteria(query);
		}

		{
			UserQuery.FindQuery query = new UserQuery.FindQuery();
			query.createCriteria().andAccountEqualTo("3");
			query.createUserGroupCriteria().andGroupIdEqualTo("3");
			userManager.queryByCriteria(query);
		}

		{
			UserQuery.FindQuery query = new UserQuery.FindQuery();
			query.createCriteria().andAccountEqualTo("1.1");
			query.or().andFullnameEqualTo("1.2");
			userManager.queryByCriteria(query);
		}

		{
			UserQuery.FindQuery query = new UserQuery.FindQuery();
			query.createUserGroupCriteria().andGroupIdEqualTo("2.1");
			query.orUserGroupCriteria().andGroupIdEqualTo("2.2");
			userManager.queryByCriteria(query);
		}

		{
			UserQuery.FindQuery query = new UserQuery.FindQuery();
			query.createCriteria().andAccountEqualTo("3.1");

			query.createUserGroupCriteria().andGroupIdEqualTo("3.1");

			query.orUserGroupCriteria().andGroupIdEqualTo("3.2");
			userManager.queryByCriteria(query);
		}
	}

	@Test
	@Rollback(true)
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
		List<DefaultUser> news = createUsers(10);
		for (User n : news) {
			// ////validate created users;
			{
				UserQuery.FindQuery query = new UserQuery.FindQuery();
				query.createCriteria().andUserIdEqualTo(n.getUserId());
				List<DefaultUser> users = userManager.queryByCriteria(query);
				assertEquals(1, users.size());
			}
			// ////delete created users;
			userManager.remove(n.getUserId());
			// ////validate deleted users;
			{
				{
					UserQuery.FindQuery query = new UserQuery.FindQuery();
					query.createCriteria().andUserIdEqualTo(n.getUserId()).andStatusEqualTo(User.Status.deleted);
					List<DefaultUser> users = userManager.queryByCriteria(query);
					assertEquals(1, users.size());
				}
				{
					UserQuery.FindQuery query = new UserQuery.FindQuery();
					query.createCriteria().andUserIdEqualTo(n.getUserId()).andStatusEqualTo(User.Status.actived);
					List<DefaultUser> users = userManager.queryByCriteria(query);
					assertEquals(0, users.size());
				}
				// {
				// UserGroupRelationQuery userGroupRelationQuery = new
				// UserGroupRelationQuery();
				// userGroupRelationQuery.createCriteria().andUserIdEqualTo(n.getUserId()).andStatusEqualTo(UserGroupRelation.STATUS_DELETED);
				// List<DefaultUser> users =
				// userGroupManager.queryByCriteria(query);
				// assertEquals(1, users.size());
				// }
			}

		}
	}

	@Test
	@Rollback(true)
	public void testGet() {
		List<DefaultUser> news = createUsers(1);
		{
			User u = userManager.get(news.get(0).getUserId());
			assertNotNull(u);
		}

		{
			userManager.remove(news.get(0).getUserId());
		}

		{
			User u = userManager.get(news.get(0).getUserId());
			assertEquals(User.Status.deleted, u.getStatus());
		}
	}

	@Test
	@Rollback(true)
	public void testRemoveByIds() {
		List<DefaultUser> news = createUsers(10);

		// ////validate created users;
		for (User n : news) {
			{
				UserQuery.FindQuery query = new UserQuery.FindQuery();
				query.createCriteria().andUserIdEqualTo(n.getUserId());
				List<DefaultUser> users = userManager.queryByCriteria(query);
				assertEquals(1, users.size());
			}

		}

		// ////delete created users;
		List<String> userIds = new ArrayList<String>();
		for (User user : news) {
			userIds.add(user.getUserId());
		}
		userManager.removeByIds(userIds.toArray(new String[0]));

		// ////validate deleted users;
		for (User n : news) {
			{
				UserQuery.FindQuery query = new UserQuery.FindQuery();
				query.createCriteria().andUserIdEqualTo(n.getUserId()).andStatusNotEqualTo(User.Status.deleted);
				List<DefaultUser> users = userManager.queryByCriteria(query);
				assertEquals(0, users.size());
			}

		}

	}

	@Test
	@Rollback(true)
	public void testQuery() {
		{
			UserQuery.FindQuery query = new UserQuery.FindQuery();
			List<DefaultUser> users1 = userManager.queryByCriteria(query);

			List<DefaultUser> news2 = createUsers(10);
			UserQuery.FindQuery query2 = new UserQuery.FindQuery();
			List<DefaultUser> users2 = userManager.queryByCriteria(query2);
			assertEquals(users1.size() + 10, users2.size());
		}
		// no setting
		{
			QueryFilter queryFilter = new DefaultQueryFilter();
			PageList<DefaultUser> users1 = (PageList<DefaultUser>) userManager.query(queryFilter);

			List<DefaultUser> news2 = createUsers(10);
			QueryFilter queryFilter2 = new DefaultQueryFilter();
			PageList<DefaultUser> users2 = (PageList<DefaultUser>) userManager.query(queryFilter2);
			System.out.println(users1.getPageResult().getTotalCount());
			assertEquals(users1.getPageResult().getTotalCount() + 10, users2.getPageResult().getTotalCount());

		}
	}

	@Test
	@Rollback(true)
	public void testGetAll() {
		long cnt1 = userManager.getAll().size();
		createUsers(2);
		long cnt2 = userManager.getAll().size();
		assertEquals(cnt2, cnt1 + 2);
	}

	@Test
	@Rollback(true)
	public void testGetAllByPage() {
		createUsers(20);
		Page page = new DefaultPage(1, 10);
		List<DefaultUser> users = userManager.getAllByPage(page);
		assertEquals(10, users.size());
	}

}
