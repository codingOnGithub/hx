package com.hotent.bpmx.persistence.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hotent.bpmx.persistence.dao.BpmProcessInstanceDao;
import com.hotent.bpmx.persistence.model.DefaultBpmProcessInstance;
import com.hotent.bpmx.persistence.BpmxBaseTest;

public class BpmProcessInstanceDaoTest extends BpmxBaseTest{
	@Resource
	private BpmProcessInstanceDao bpmProcessInstanceDao;
	
	@Test
	@Rollback(true)
	public void testCrud(){
		DefaultBpmProcessInstance bpmProcessInstance=new DefaultBpmProcessInstance();
		Integer randId=new Double(100000*Math.random()).intValue();
		bpmProcessInstance.setId(idGenerator.getSuid());
		bpmProcessInstance.setSubject("bpmProcessInstance" + randId);
		bpmProcessInstance.setProcDefId("bpmProcessInstance" + randId);
		bpmProcessInstance.setProcDefName("bpmProcessInstance" + randId);
		
		//创建一实体
		bpmProcessInstanceDao.create(bpmProcessInstance);
        Assert.assertNotNull(bpmProcessInstance.getId());
        logger.debug("bpmProcessInstance1:"+ bpmProcessInstance.getId());
		//获取一实体
		DefaultBpmProcessInstance bpmProcessInstance2=bpmProcessInstanceDao.get(bpmProcessInstance.getId());
		Assert.assertNotNull(bpmProcessInstance2);
		logger.debug("bpmProcessInstance2:" + bpmProcessInstance2.toString());
		Integer randId2=new Double(100000*Math.random()).intValue();
		bpmProcessInstance2.setSubject("bpmProcessInstance" + randId2);
		bpmProcessInstance2.setProcDefId("bpmProcessInstance" + randId2);
		bpmProcessInstance2.setProcDefName("bpmProcessInstance" + randId2);
		//更新一实体
		bpmProcessInstanceDao.update(bpmProcessInstance2);
		
		DefaultBpmProcessInstance bpmProcessInstance3=bpmProcessInstanceDao.get(bpmProcessInstance.getId());
		System.out.println("bpmProcessInstance3:"+bpmProcessInstance3.toString());
		//删除一实体
		//bpmProcessInstanceDao.remove(bpmProcessInstance.getId());
	}
	
}
