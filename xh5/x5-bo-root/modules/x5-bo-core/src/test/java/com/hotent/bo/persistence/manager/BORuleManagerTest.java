package com.hotent.bo.persistence.manager;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hotent.bo.BOBaseTest;
import com.hotent.bo.persistence.model.BORule;

public class BORuleManagerTest extends BOBaseTest{
	@Resource
	BORuleManager bORuleManager;
	
	@Test
	@Rollback(true)
	public void testCreate(){
		BORule bORule=new BORule();
		Integer randId=new Double(100000*Math.random()).intValue();
		bORule.setId(idGenerator.getSuid());
		bORule.setDefId("bORule" + randId);
		bORule.setBelongType("bORule" + randId);
		//创建一实体
		bORuleManager.create(bORule);
	}
}
