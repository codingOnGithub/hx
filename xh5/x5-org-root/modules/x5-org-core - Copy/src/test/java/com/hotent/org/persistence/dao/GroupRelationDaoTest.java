package com.hotent.org.persistence.dao;

import java.util.Date;
import javax.annotation.Resource;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import com.hotent.org.persistence.OrgBaseTest;
import com.hotent.org.persistence.model.DefaultGroupRelation;

public class GroupRelationDaoTest extends OrgBaseTest{
	@Resource
	private GroupRelationDao groupRelationDao;
	
	@Test
	@Rollback(true)
	public void testCrud(){
		DefaultGroupRelation groupRelation=new DefaultGroupRelation();
		Integer randId=new Double(100000*Math.random()).intValue();
		groupRelation.setId(idGenerator.getSuid());
		groupRelation.setGroupId("groupRelation" + randId);
		groupRelation.setRelGroupId("groupRelation" + randId);
		groupRelation.setRelType("groupRelation" + randId);
		groupRelation.setCreateTime(new Date());
		
		//创建一实体
		groupRelationDao.create(groupRelation);
        Assert.assertNotNull(groupRelation.getId());
        logger.debug("groupRelation1:"+ groupRelation.getId());
		//获取一实体
        DefaultGroupRelation groupRelation2=groupRelationDao.get(groupRelation.getId());
		Assert.assertNotNull(groupRelation2);
		logger.debug("groupRelation2:" + groupRelation2.toString());
		Integer randId2=new Double(100000*Math.random()).intValue();
		groupRelation2.setGroupId("groupRelation" + randId2);
		groupRelation2.setRelGroupId("groupRelation" + randId2);
		groupRelation2.setRelType("groupRelation" + randId2);
		groupRelation2.setCreateTime(new Date());
		//更新一实体
		groupRelationDao.update(groupRelation2);
		
		DefaultGroupRelation groupRelation3=groupRelationDao.get(groupRelation.getId());
		System.out.println("groupRelation3:"+groupRelation3.toString());
		//删除一实体
		//groupRelationDao.remove(groupRelation.getId());
	}
	
}
