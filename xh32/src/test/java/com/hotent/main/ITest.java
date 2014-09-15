package com.hotent.main;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.hotent.core.cache.ICache;
import com.hotent.core.util.AppUtil;
import com.hotent.platform.dao.system.ResourcesDao;
import com.hotent.platform.service.system.ResourcesService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:conf/app-test.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
public class ITest {

	
	public static void main(String[] args) {}
	
	
	
	
	@Resource
	private ResourcesDao ResourcesDao;
	
	@Test
	public void  test() {
		Map<String, Object> p= new HashMap<String, Object>();
		p.put("alias", "BPM");
		List lit=ResourcesDao.getBySqlKey("getAll",p);
		System.out.println(">>>>>>>>>>."+lit.size());

	}
	

}
