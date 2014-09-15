package com.hotent.platform.service.system;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.hotent.platform.service.util.ServiceUtil;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:conf/app-test.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
public class ServiceUtilTest {
	//@Test
	public void testGetInfoType() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDefaultSelectInfoType() {
		System.out.println(ServiceUtil.getDefaultSelectInfoType());
	}

}
