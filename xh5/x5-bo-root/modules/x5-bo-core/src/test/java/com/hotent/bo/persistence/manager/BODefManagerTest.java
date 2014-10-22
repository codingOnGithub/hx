package com.hotent.bo.persistence.manager;

import java.util.Date;
import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import com.hotent.bo.BOBaseTest;
import com.hotent.bo.persistence.dao.BOAttributeDao;
import com.hotent.bo.persistence.model.BODef;

public class BODefManagerTest extends BOBaseTest{
	@Resource
	BODefManager bODefManager;
	@Resource
	BOAttributeDao boAttributeDao;
	
	@Test
	@Rollback(true)
	public void testCreate(){
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
		bODefManager.create(bODef);
	}
}
