package com.hotent.org.persistence.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hotent.org.persistence.dao.AttributeDao;
import com.hotent.org.persistence.model.DefaultAttribute;
import com.hotent.org.persistence.OrgBaseTest;

public class AttributeDaoTest extends OrgBaseTest{
	@Resource
	private AttributeDao attributeDao;
	
	@Test
	@Rollback(true)
	public void testCrud(){
		DefaultAttribute attribute=new DefaultAttribute();
		Integer randId=new Double(100000*Math.random()).intValue();
		attribute.setId(idGenerator.getSuid());
		attribute.setName("attribute" + randId);
		attribute.setAttrKey("attribute" + randId);
		attribute.setBelongType("attribute" + randId);
		attribute.setDataType("attribute" + randId);
		
		//创建一实体
		attributeDao.create(attribute);
        Assert.assertNotNull(attribute.getId());
        logger.debug("attribute1:"+ attribute.getId());
		//获取一实体
		DefaultAttribute attribute2=attributeDao.get(attribute.getId());
		Assert.assertNotNull(attribute2);
		logger.debug("attribute2:" + attribute2.toString());
		Integer randId2=new Double(100000*Math.random()).intValue();
		attribute2.setName("attribute" + randId2);
		attribute2.setAttrKey("attribute" + randId2);
		attribute2.setBelongType("attribute" + randId2);
		attribute2.setDataType("attribute" + randId2);
		//更新一实体
		attributeDao.update(attribute2);
		
		DefaultAttribute attribute3=attributeDao.get(attribute.getId());
		System.out.println("attribute3:"+attribute3.toString());
		//删除一实体
		//attributeDao.remove(attribute.getId());
	}
	
}
