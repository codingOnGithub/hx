package com.hotent.bo.persistence.dao;

import javax.annotation.Resource;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import com.hotent.bo.BOBaseTest;
import com.hotent.bo.persistence.model.BORule;

public class BORuleDaoTest extends BOBaseTest{
	@Resource
	private BORuleDao bORuleDao;
	
	@Test
	@Rollback(true)
	public void testCrud(){
		BORule bORule=new BORule();
		Integer randId=new Double(100000*Math.random()).intValue();
		bORule.setId(idGenerator.getSuid());
		bORule.setDefId("bORule" + randId);
		bORule.setBelongType("bORule" + randId);
		
		//创建一实体
		bORuleDao.create(bORule);
        Assert.assertNotNull(bORule.getId());
        logger.debug("bORule1:"+ bORule.getId());
		//获取一实体
		BORule bORule2=bORuleDao.get(bORule.getId());
		Assert.assertNotNull(bORule2);
		logger.debug("bORule2:" + bORule2.toString());
		Integer randId2=new Double(100000*Math.random()).intValue();
		bORule2.setDefId("bORule" + randId2);
		bORule2.setBelongType("bORule" + randId2);
		//更新一实体
		bORuleDao.update(bORule2);
		
		BORule bORule3=bORuleDao.get(bORule.getId());
		System.out.println("bORule3:"+bORule3.toString());
		//删除一实体
		//bORuleDao.remove(bORule.getId());
	}
	
}
