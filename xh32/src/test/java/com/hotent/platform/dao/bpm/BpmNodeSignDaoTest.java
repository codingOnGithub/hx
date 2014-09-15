package com.hotent.platform.dao.bpm;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.hotent.BaseTestCase;
import com.hotent.platform.model.bpm.BpmNodeSign;
import com.hotent.platform.service.bpm.BpmService;
 



public class BpmNodeSignDaoTest extends BaseTestCase{
	
	@Resource
	private BpmNodeSignDao dao;
	
	//@Test
	public void getByDeployAndNodeId()
	{
		BpmNodeSign model=dao.getByDefIdAndNodeId("113", "task1");
		
		System.out.println("ok");
		
	}

}
