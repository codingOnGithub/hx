package com.hotent.bpmx.persistence.manager;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hotent.bpmx.persistence.model.BpmMsgRule;
import com.hotent.bpmx.persistence.manager.BpmMsgRuleManager;
import com.hotent.bpmx.persistence.BpmxBaseTest;

public class BpmMsgRuleManagerTest extends BpmxBaseTest{
	@Resource
	BpmMsgRuleManager bpmMsgRuleManager;
	
	@Test
	@Rollback(true)
	public void testCreate(){
		BpmMsgRule bpmMsgRule=new BpmMsgRule();
		Integer randId=new Double(100000*Math.random()).intValue();
		bpmMsgRule.setId(idGenerator.getSuid());
		bpmMsgRule.setBizType("bpmMsgRule" + randId);
		bpmMsgRule.setMsgType("bpmMsgRule" + randId);
		bpmMsgRule.setTemplate("bpmMsgRule" + randId);
		//创建一实体
		bpmMsgRuleManager.create(bpmMsgRule);
	}
}
