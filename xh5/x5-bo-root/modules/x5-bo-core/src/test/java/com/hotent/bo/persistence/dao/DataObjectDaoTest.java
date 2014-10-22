package com.hotent.bo.persistence.dao;

import java.util.Date;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hotent.bo.BOBaseTest;
import com.hotent.bo.persistence.model.DataObject;
import com.hotent.bo.persistence.model.impl.JSONDataObject;

public class DataObjectDaoTest extends BOBaseTest{
	@Resource
	private DataObjectDao dataObjectDao;
	
	@Test
	@Rollback(true)
	public void testCrud(){
		DataObject dataObject=new JSONDataObject();
		Integer randId=new Double(100000*Math.random()).intValue();
		dataObject.setId(idGenerator.getSuid());
		dataObject.setCreateTime(new Date());
		
		//创建一实体
		dataObjectDao.create(dataObject);
        Assert.assertNotNull(dataObject.getId());
        logger.debug("dataObject1:"+ dataObject.getId());
		//获取一实体
		DataObject dataObject2=dataObjectDao.get(dataObject.getId());
		Assert.assertNotNull(dataObject2);
		logger.debug("dataObject2:" + dataObject2.toString());
		dataObject2.setCreateTime(new Date());
		//更新一实体
		dataObjectDao.update(dataObject2);
		
		DataObject dataObject3=dataObjectDao.get(dataObject.getId());
		System.out.println("dataObject3:"+dataObject3.toString());
		//删除一实体
		//dataObjectDao.remove(dataObject.getId());
	}
	
}
