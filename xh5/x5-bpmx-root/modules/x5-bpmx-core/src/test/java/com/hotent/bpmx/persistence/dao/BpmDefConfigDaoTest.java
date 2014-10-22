package com.hotent.bpmx.persistence.dao;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hotent.bpmx.persistence.BpmxBaseTest;
import com.hotent.bpmx.persistence.model.BpmDefConfig;

public class BpmDefConfigDaoTest extends BpmxBaseTest{
	@Resource
	private BpmDefConfigDao bpmDefConfigDao;
	
	@Test
	@Rollback(true)
	public void testCrud(){
		BpmDefConfig bpmDefConfig=new BpmDefConfig();
		Integer randId=new Double(100000*Math.random()).intValue();
		bpmDefConfig.setId(idGenerator.getSuid());
		bpmDefConfig.setInformType("1");
		
		//创建一实体
		bpmDefConfigDao.create(bpmDefConfig);
        Assert.assertNotNull(bpmDefConfig.getId());
        logger.debug("bpmDefConfig1:"+ bpmDefConfig.getId());
		//获取一实体
		BpmDefConfig bpmDefConfig2=bpmDefConfigDao.get(bpmDefConfig.getId());
		Assert.assertNotNull(bpmDefConfig2);
		logger.debug("bpmDefConfig2:" + bpmDefConfig2.toString());
		Integer randId2=new Double(100000*Math.random()).intValue();
		//更新一实体
		bpmDefConfigDao.update(bpmDefConfig2);
		
		BpmDefConfig bpmDefConfig3=bpmDefConfigDao.get(bpmDefConfig.getId());
		System.out.println("bpmDefConfig3:"+bpmDefConfig3.toString());
		//删除一实体
		//bpmDefConfigDao.remove(bpmDefConfig.getId());
	}
	
}
