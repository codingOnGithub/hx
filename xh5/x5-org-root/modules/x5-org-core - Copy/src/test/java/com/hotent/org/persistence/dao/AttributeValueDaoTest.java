package com.hotent.org.persistence.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hotent.org.persistence.dao.AttributeValueDao;
import com.hotent.org.persistence.model.DefaultAttributeValue;
import com.hotent.org.persistence.OrgBaseTest;

public class AttributeValueDaoTest extends OrgBaseTest{
	@Resource
	private AttributeValueDao attributeValueDao;
	
	@Test
	@Rollback(true)
	public void testCrud(){
		DefaultAttributeValue attributeValue=new DefaultAttributeValue();
		Integer randId=new Double(100000*Math.random()).intValue();
		attributeValue.setId(idGenerator.getSuid());
		attributeValue.setAttrId("attributeValue" + randId);
		attributeValue.setAttrKey("attributeValue" + randId);
		attributeValue.setUserId("attributeValue" + randId);
		attributeValue.setGroupId("attributeValue" + randId);
		attributeValue.setDataType("attributeValue" + randId);
		
		//创建一实体
		attributeValueDao.create(attributeValue);
        Assert.assertNotNull(attributeValue.getId());
        logger.debug("attributeValue1:"+ attributeValue.getId());
		//获取一实体
		DefaultAttributeValue attributeValue2=attributeValueDao.get(attributeValue.getId());
		Assert.assertNotNull(attributeValue2);
		logger.debug("attributeValue2:" + attributeValue2.toString());
		Integer randId2=new Double(100000*Math.random()).intValue();
		attributeValue2.setAttrId("attributeValue" + randId2);
		attributeValue2.setAttrKey("attributeValue" + randId2);
		attributeValue2.setUserId("attributeValue" + randId2);
		attributeValue2.setGroupId("attributeValue" + randId2);
		attributeValue2.setDataType("attributeValue" + randId2);
		//更新一实体
		attributeValueDao.update(attributeValue2);
		
		DefaultAttributeValue attributeValue3=attributeValueDao.get(attributeValue.getId());
		System.out.println("attributeValue3:"+attributeValue3.toString());
		//删除一实体
		//attributeValueDao.remove(attributeValue.getId());
	}
	
}
