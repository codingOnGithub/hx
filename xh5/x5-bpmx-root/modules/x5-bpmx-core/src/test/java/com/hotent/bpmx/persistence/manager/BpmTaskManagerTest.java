package com.hotent.bpmx.persistence.manager;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hotent.bpmx.persistence.model.DefaultBpmTask;
import com.hotent.bpmx.persistence.manager.BpmTaskManager;
import com.hotent.bpmx.persistence.BpmxBaseTest;

public class BpmTaskManagerTest extends BpmxBaseTest{
	@Resource
	BpmTaskManager bpmTaskManager;
	
	@Test
	@Rollback(true)
	public void testCreate(){
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
		bpmTaskManager.create(bpmTask);
	}
}
