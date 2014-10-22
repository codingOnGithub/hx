package com.hotent.bo.persistence.manager;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hotent.bo.BOBaseTest;
import com.hotent.bo.persistence.model.BOPackage;

public class BOPackageManagerTest extends BOBaseTest{
	@Resource
	BOPackageManager bOPackageManager;
	
	@Test
	@Rollback(true)
	public void testCreate(){
		BOPackage bOPackage=new BOPackage();
		Integer randId=new Double(100000*Math.random()).intValue();
		bOPackage.setPackageId(idGenerator.getSuid());
		bOPackage.setName("bOPackage" + randId);
		bOPackage.setDepth(1L);
		bOPackage.setStatus("bOPackage" + randId);
		bOPackage.setCreateTime(new Date());
		//创建一实体
		bOPackageManager.create(bOPackage);
	}
}
