package com.hotent.org.persistence.dao;

import static org.junit.Assert.*;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hotent.base.api.Page;
import com.hotent.base.core.util.UUIDUtil;
import com.hotent.base.db.mybatis.domain.DefaultPage;
import com.hotent.org.api.model.User;
import com.hotent.org.persistence.OrgBaseTest;
import com.hotent.org.persistence.model.DefaultUser;
import com.hotent.org.persistence.query.UserQuery;


//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {
//		"classpath*:conf/x5-*.xml"
//		})
public class UserDaoTest extends OrgBaseTest{
	@Resource
	UserDao userDao;
	
	private void creatUser(String account,String status,String password){
		DefaultUser defaultUser = new DefaultUser();
		defaultUser.setAccount(account);
		defaultUser.setFullname(account);
		defaultUser.setId(UUID.randomUUID().toString());
		defaultUser.setStatus(status);
		defaultUser.setPassword(password);
		userDao.create(defaultUser);
	}
	
	
	@Test
	@Rollback(true)
	public void testGetByAccount() {
		creatUser("exists", "0", "pwd");
	
		User user = userDao.getByAccount("exists");
		assertNotNull(user);
		user = userDao.getByAccount("not exists");
		assertNull(user);
	}

	@Test
	public void testQueryByUserRelation() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testQueryByGroupRelation() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testQueryUserByAttributeValue() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	@Rollback(true)
	public void testqueryByCriteria() {
		creatUser("exists", "0", "pwd");
		
		//fullname
		{
			UserQuery userQuery = new UserQuery();
			userQuery.createCriteria().andFullnameEqualTo("exists");
			List<DefaultUser>  user1  = userDao.queryByCriteria(userQuery, new DefaultPage());
			assertEquals(user1.size(), 1);
		}
		{
			UserQuery userQuery = new UserQuery();
			userQuery.createCriteria().andFullnameEqualTo("not exists");
			List<DefaultUser>  user1  = userDao.queryByCriteria(userQuery, new DefaultPage());
			assertEquals(user1.size(), 0);
		}
		
		///fullnamelike
		creatUser("exists1", "0", "pwd");
		{
			UserQuery userQuery = new UserQuery();
			userQuery.createCriteria().andFullnameLike("%exists%");
			List<DefaultUser>  user1  = userDao.queryByCriteria(userQuery, new DefaultPage());
			assertEquals(user1.size(),2);
		}
		{
			UserQuery userQuery = new UserQuery();
			userQuery.createCriteria().andFullnameLike("%exists1%");
			List<DefaultUser>  user1  = userDao.queryByCriteria(userQuery, new DefaultPage());
			assertEquals(user1.size(),1);
		}
	}

	@Test
	public void testQueryUserCountByQueryCriteria() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testCreate() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGet() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testRemoveByIds() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testQuery() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetAll() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetAllByPage() {
		fail("Not yet implemented"); // TODO
	}

}
