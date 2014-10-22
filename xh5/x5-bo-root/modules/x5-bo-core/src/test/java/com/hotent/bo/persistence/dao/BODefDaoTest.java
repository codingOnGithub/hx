package com.hotent.bo.persistence.dao;

import java.util.Date;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hotent.bo.BOBaseTest;
import com.hotent.bo.persistence.model.BODef;

public class BODefDaoTest extends BOBaseTest{
	@Resource
	private BODefDao bODefDao;
	
	@Test
	@Rollback(true)
	public void testCrud(){
		BODef bODef=new BODef();
		Integer randId=new Double(100000*Math.random()).intValue();
		bODef.setId(idGenerator.getSuid());
		bODef.setPackageId("10000000001001");
		bODef.setCode("bODef" + randId);
		bODef.setName("bODef" + randId);
		bODef.setVersion(1L);
		bODef.setStatus("bODef" + randId);
		bODef.setCreateTime(new Date());
		
		//创建一实体
		bODefDao.create(bODef);
        Assert.assertNotNull(bODef.getId());
        logger.debug("bODef1:"+ bODef.getId());
		//获取一实体
		BODef bODef2=bODefDao.get(bODef.getId());
		Assert.assertNotNull(bODef2);
		logger.debug("bODef2:" + bODef2.toString());
		Integer randId2=new Double(100000*Math.random()).intValue();
		bODef2.setPackageId("10000000001001");
		bODef2.setCode("bODef" + randId2);
		bODef2.setName("bODef" + randId2);
		bODef2.setVersion(2L);
		bODef2.setStatus("bODef" + randId2);
		bODef2.setCreateTime(new Date());
		//更新一实体
		bODefDao.update(bODef2);
		
		BODef bODef3=bODefDao.get(bODef.getId());
		System.out.println("bODef3:"+bODef3.toString());
		//删除一实体
		//bODefDao.remove(bODef.getId());
	}
	
}
