package com.hotent.org.persistence.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hotent.org.persistence.dao.RelationTypeDao;
import com.hotent.org.persistence.model.DefaultRelationType;
import com.hotent.org.persistence.OrgBaseTest;

public class RelationTypeDaoTest extends OrgBaseTest{
	@Resource
	private RelationTypeDao relationTypeDao;
	
	@Test
	@Rollback(true)
	public void testCrud(){
		DefaultRelationType relationType=new DefaultRelationType();
		Integer randId=new Double(100000*Math.random()).intValue();
		relationType.setId(idGenerator.getSuid());
		relationType.setName("relationType" + randId);
		relationType.setKey("relationType" + randId);
		relationType.setType("relationType" + randId);
		relationType.setConstType("relationType" + randId);
		relationType.setCurrentName("relationType" + randId);
		relationType.setRelName("relationType" + randId);
		relationType.setStatus("relationType" + randId);
		relationType.setCreateTime(new Date());
		
		//创建一实体
		relationTypeDao.create(relationType);
        Assert.assertNotNull(relationType.getId());
        logger.debug("relationType1:"+ relationType.getId());
		//获取一实体
		DefaultRelationType relationType2=relationTypeDao.get(relationType.getId());
		Assert.assertNotNull(relationType2);
		logger.debug("relationType2:" + relationType2.toString());
		Integer randId2=new Double(100000*Math.random()).intValue();
		relationType2.setName("relationType" + randId2);
		relationType2.setKey("relationType" + randId2);
		relationType2.setType("relationType" + randId2);
		relationType2.setConstType("relationType" + randId2);
		relationType2.setCurrentName("relationType" + randId2);
		relationType2.setRelName("relationType" + randId2);
		relationType2.setStatus("relationType" + randId2);
		relationType2.setCreateTime(new Date());
		//更新一实体
		relationTypeDao.update(relationType2);
		
		DefaultRelationType relationType3=relationTypeDao.get(relationType.getId());
		System.out.println("relationType3:"+relationType3.toString());
		//删除一实体
		//relationTypeDao.remove(relationType.getId());
	}
	
}
