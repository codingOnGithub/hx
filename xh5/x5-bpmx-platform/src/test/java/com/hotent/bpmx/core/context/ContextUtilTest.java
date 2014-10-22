package com.hotent.bpmx.core.context;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.hotent.org.api.model.User;
import com.hotent.org.persistence.model.DefaultUser;


@ContextConfiguration({"classpath:conf/app-context-test.xml"})
public class ContextUtilTest extends com.hotent.base.test.BaseTestCase {

	@Test
	public void testSetUser(){
		DefaultUser user=new DefaultUser();
		user.setAccount("zyg");
		user.setId("111");
		user.setFullname("zhangyg");
		ContextUtil.setCurrentUser(user);
		
		System.out.println("testSetUser");
	
	}
	
	@Test
	public void testGetUser(){
		User user1= ContextUtil.getCurrentUser();
		System.out.println(user1.getAccount() +"," + user1.getFullname());
	}

}
