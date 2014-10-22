package com.hotent.bpmx.persistence.dao;

import java.util.Date;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hotent.bpmx.api.model.process.task.BpmTask;
import com.hotent.bpmx.persistence.BpmxBaseTest;
import com.hotent.bpmx.persistence.model.DefaultBpmTask;

public class BpmTaskDaoTest extends BpmxBaseTest{
	@Resource
	private BpmTaskDao bpmTaskDao;
	
	@Test
	@Rollback(true)
	public void testCrud(){
		DefaultBpmTask bpmTask=new DefaultBpmTask();
		Integer randId=new Double(100000*Math.random()).intValue();
		bpmTask.setId(idGenerator.getSuid());
		bpmTask.setName("bpmTask" + randId);
		bpmTask.setSubject("bpmTask" + randId);
		bpmTask.setProcInstId("bpmTask" + randId);
		bpmTask.setProcDefId("bpmTask" + randId);
		bpmTask.setStatus("bpmTask" + randId);
		bpmTask.setCreateTime(new Date());
		
		//创建一实体
		bpmTaskDao.create(bpmTask);
        Assert.assertNotNull(bpmTask.getId());
        logger.debug("bpmTask1:"+ bpmTask.getId());
		//获取一实体
		DefaultBpmTask bpmTask2=(DefaultBpmTask)bpmTaskDao.get(bpmTask.getId());
		Assert.assertNotNull(bpmTask2);
		logger.debug("bpmTask2:" + bpmTask2.toString());
		Integer randId2=new Double(100000*Math.random()).intValue();
		bpmTask2.setName("bpmTask" + randId2);
		bpmTask2.setSubject("bpmTask" + randId2);
		bpmTask2.setProcInstId("bpmTask" + randId2);
		bpmTask2.setProcDefId("bpmTask" + randId2);
		bpmTask2.setStatus("bpmTask" + randId2);
		bpmTask2.setCreateTime(new Date());
		//更新一实体
		bpmTaskDao.update(bpmTask2);
		
		BpmTask bpmTask3=bpmTaskDao.get(bpmTask.getId());
		System.out.println("bpmTask3:"+bpmTask3.toString());
		//删除一实体
		//bpmTaskDao.remove(bpmTask.getId());
	}
	
}
