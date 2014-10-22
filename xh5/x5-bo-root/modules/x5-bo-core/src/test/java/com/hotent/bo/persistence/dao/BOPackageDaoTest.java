package com.hotent.bo.persistence.dao;

import java.util.Date;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hotent.bo.BOBaseTest;
import com.hotent.bo.persistence.model.BOPackage;

public class BOPackageDaoTest extends BOBaseTest{
	@Resource
	private BOPackageDao bOPackageDao;
	
	@Test
	@Rollback(true)
	public void testCrud(){
		BOPackage bOPackage=new BOPackage();
		Integer randId=new Double(100000*Math.random()).intValue();
		bOPackage.setId(idGenerator.getSuid());
		bOPackage.setName("bOPackage" + randId);
		bOPackage.setDepth(1L);
		bOPackage.setStatus("bOPackage" + randId);
		bOPackage.setCreateTime(new Date());
		
		//创建一实体
		bOPackageDao.create(bOPackage);
        Assert.assertNotNull(bOPackage.getId());
        logger.debug("bOPackage1:"+ bOPackage.getId());
		//获取一实体
		BOPackage bOPackage2=bOPackageDao.get(bOPackage.getId());
		Assert.assertNotNull(bOPackage2);
		logger.debug("bOPackage2:" + bOPackage2.toString());
		Integer randId2=new Double(100000*Math.random()).intValue();
		bOPackage2.setName("bOPackage" + randId2);
		bOPackage2.setDepth(1L);
		bOPackage2.setStatus("bOPackage" + randId2);
		bOPackage2.setCreateTime(new Date());
		//更新一实体
		bOPackageDao.update(bOPackage2);
		
		BOPackage bOPackage3=bOPackageDao.get(bOPackage.getId());
		System.out.println("bOPackage3:"+bOPackage3.toString());
		//删除一实体
		//bOPackageDao.remove(bOPackage.getId());
	}
	
}
