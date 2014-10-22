package com.hotent.org.persistence.manager;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hotent.org.persistence.model.DefaultRelationType;
import com.hotent.org.persistence.manager.RelationTypeManager;
import com.hotent.org.persistence.OrgBaseTest;

public class RelationTypeManagerTest extends OrgBaseTest{
	@Resource
	RelationTypeManager relationTypeManager;
	
	@Test
	@Rollback(true)
	public void testCreate(){
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
		relationTypeManager.create(relationType);
	}
}
