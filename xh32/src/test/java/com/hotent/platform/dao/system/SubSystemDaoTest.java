package com.hotent.platform.dao.system;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.hotent.platform.model.system.SubSystem;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:conf/app-test.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
public class SubSystemDaoTest {
	
	@Resource
	private SubSystemDao dao;
	
	@Test
	public void getSubSystemList()
	{
		Object params=null;
		List<SubSystem> list= dao.getBySqlKey("getSubSystemList", params);
		System.out.println("size:" + list.size());
	}
}
