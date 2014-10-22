package com.hotent.bpmx.persistence.manager;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hotent.bpmx.persistence.model.DefaultBpmProcessInstance;
import com.hotent.bpmx.persistence.manager.BpmProcessInstanceManager;
import com.hotent.bpmx.persistence.BpmxBaseTest;

public class BpmProcessInstanceManagerTest extends BpmxBaseTest{
	@Resource
	BpmProcessInstanceManager bpmProcessInstanceManager;
	
	@Test
	@Rollback(true)
	public void testCreate(){
		String procDefId="10000000051005";
		String procDefName="RULE-xx";
		DefaultBpmProcessInstance bpmProcessInstance=new DefaultBpmProcessInstance();
		Integer randId=new Double(100000*Math.random()).intValue();
		bpmProcessInstance.setId(idGenerator.getSuid());
		bpmProcessInstance.setSubject("bpmProcessInstance" + randId);
		bpmProcessInstance.setProcDefId("bpmProcessInstance" + procDefId);
		bpmProcessInstance.setProcDefName(procDefName);
		//创建一实体
		bpmProcessInstanceManager.create(bpmProcessInstance);
	}
}
