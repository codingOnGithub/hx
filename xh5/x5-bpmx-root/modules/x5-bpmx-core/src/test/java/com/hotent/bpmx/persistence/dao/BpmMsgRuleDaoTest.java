package com.hotent.bpmx.persistence.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hotent.bpmx.persistence.dao.BpmMsgRuleDao;
import com.hotent.bpmx.persistence.model.BpmMsgRule;
import com.hotent.bpmx.persistence.BpmxBaseTest;

public class BpmMsgRuleDaoTest extends BpmxBaseTest{
	@Resource
	private BpmMsgRuleDao bpmMsgRuleDao;
	
	@Test
	@Rollback(true)
	public void testCrud(){
		BpmMsgRule bpmMsgRule=new BpmMsgRule();
		Integer randId=new Double(100000*Math.random()).intValue();
		bpmMsgRule.setId(idGenerator.getSuid());
		bpmMsgRule.setBizType("bpmMsgRule" + randId);
		bpmMsgRule.setMsgType("bpmMsgRule" + randId);
		bpmMsgRule.setTemplate("bpmMsgRule" + randId);
		
		//创建一实体
		bpmMsgRuleDao.create(bpmMsgRule);
        Assert.assertNotNull(bpmMsgRule.getId());
        logger.debug("bpmMsgRule1:"+ bpmMsgRule.getId());
		//获取一实体
		BpmMsgRule bpmMsgRule2=bpmMsgRuleDao.get(bpmMsgRule.getId());
		Assert.assertNotNull(bpmMsgRule2);
		logger.debug("bpmMsgRule2:" + bpmMsgRule2.toString());
		Integer randId2=new Double(100000*Math.random()).intValue();
		bpmMsgRule2.setBizType("bpmMsgRule" + randId2);
		bpmMsgRule2.setMsgType("bpmMsgRule" + randId2);
		bpmMsgRule2.setTemplate("bpmMsgRule" + randId2);
		//更新一实体
		bpmMsgRuleDao.update(bpmMsgRule2);
		
		BpmMsgRule bpmMsgRule3=bpmMsgRuleDao.get(bpmMsgRule.getId());
		System.out.println("bpmMsgRule3:"+bpmMsgRule3.toString());
		//删除一实体
		//bpmMsgRuleDao.remove(bpmMsgRule.getId());
	}
	
}
