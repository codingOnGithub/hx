package com.hotent.org.persistence.manager;

import java.util.Date;
import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import com.hotent.org.persistence.OrgBaseTest;
import com.hotent.org.persistence.model.DefaultGroupRelation;

public class GroupRelationManagerTest extends OrgBaseTest{
	@Resource
	GroupRelationManager groupRelationManager;
	
	@Test
	@Rollback(true)
	public void testCreate(){
		DefaultGroupRelation groupRelation=new DefaultGroupRelation();
		Integer randId=new Double(100000*Math.random()).intValue();
		groupRelation.setRelId(idGenerator.getSuid());
		groupRelation.setGroupId("groupRelation" + randId);
		groupRelation.setRelGroupId("groupRelation" + randId);
		groupRelation.setRelType("groupRelation" + randId);
		groupRelation.setCreateTime(new Date());
		//创建一实体
		groupRelationManager.create(groupRelation);
	}
}
