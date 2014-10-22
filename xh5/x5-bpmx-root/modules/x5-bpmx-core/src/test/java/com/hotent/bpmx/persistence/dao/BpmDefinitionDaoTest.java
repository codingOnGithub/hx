package com.hotent.bpmx.persistence.dao;

import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hotent.bpmx.persistence.BpmxBaseTest;
import com.hotent.bpmx.persistence.model.DefaultBpmDefinition;

public class BpmDefinitionDaoTest extends BpmxBaseTest{
	@Resource
	private BpmDefinitionDao defaultBpmDefinitionDao;
	
	@Test
	@Rollback(true)
	public void testCrud(){
		DefaultBpmDefinition defaultBpmDefinition=new DefaultBpmDefinition();
		Integer randId=new Double(100000*Math.random()).intValue();
		defaultBpmDefinition.setId(idGenerator.getSuid());
		defaultBpmDefinition.setName("defaultBpmDefinition" + randId);
		defaultBpmDefinition.setInstSubjectRule("defaultBpmDefinition" + randId);
		defaultBpmDefinition.setDefKey("defaultBpmDefinition" + randId);
		defaultBpmDefinition.setStatus("defaultBpmDefinition" + randId);
		defaultBpmDefinition.setTestStatus("defaultBpmDefinition" + randId);
		
		//创建一实体
		defaultBpmDefinitionDao.create(defaultBpmDefinition);
        Assert.assertNotNull(defaultBpmDefinition.getId());
        logger.debug("defaultBpmDefinition1:"+ defaultBpmDefinition.getId());
		//获取一实体
		DefaultBpmDefinition defaultBpmDefinition2=defaultBpmDefinitionDao.get(defaultBpmDefinition.getId());
		Assert.assertNotNull(defaultBpmDefinition2);
		logger.debug("defaultBpmDefinition2:" + defaultBpmDefinition2.toString());
		Integer randId2=new Double(100000*Math.random()).intValue();
		defaultBpmDefinition2.setName("defaultBpmDefinition" + randId2);
		defaultBpmDefinition2.setInstSubjectRule("defaultBpmDefinition" + randId2);
		defaultBpmDefinition2.setDefKey("defaultBpmDefinition" + randId2);
		defaultBpmDefinition2.setStatus("defaultBpmDefinition" + randId2);
		defaultBpmDefinition2.setTestStatus("defaultBpmDefinition" + randId2);
		//更新一实体
		defaultBpmDefinitionDao.update(defaultBpmDefinition2);
		
		DefaultBpmDefinition defaultBpmDefinition3=defaultBpmDefinitionDao.get(defaultBpmDefinition.getId());
		System.out.println("defaultBpmDefinition3:"+defaultBpmDefinition3.toString());
		//删除一实体
		//defaultBpmDefinitionDao.remove(defaultBpmDefinition.getId());
	}
	
	
	@Test
	public void getByDefKey(){
		DefaultBpmDefinition defaultBpmDefinition = getDefaultBpmDefinition();
		defaultBpmDefinition = defaultBpmDefinitionDao.getMainByDefKey(defaultBpmDefinition.getDefKey());
		Assert.assertNotNull(defaultBpmDefinition);
	}
	
	@Test
	public void queryByDefKey(){
		DefaultBpmDefinition defaultBpmDefinition = getDefaultBpmDefinition();
		List<DefaultBpmDefinition> allDefs = defaultBpmDefinitionDao.queryByDefKey(defaultBpmDefinition.getDefKey());
		Assert.assertTrue(allDefs.size()>=1);		
		for(DefaultBpmDefinition def :allDefs){
			System.out.println(def);
		}
	}
	
	@Test
	public void queryHistorys(){
		DefaultBpmDefinition defaultBpmDefinition = getDefaultBpmDefinition();
		List<DefaultBpmDefinition> allDefs = defaultBpmDefinitionDao.queryHistorys(defaultBpmDefinition.getDefKey());
		if(defaultBpmDefinition.isMain()){
			Assert.assertTrue(allDefs.size()>=0);	
		}else{
			Assert.assertTrue(allDefs.size()>=1);
		}			
		for(DefaultBpmDefinition def :allDefs){
			System.out.println(def);
		}
	}
	
	public DefaultBpmDefinition getDefaultBpmDefinition(){
		List<DefaultBpmDefinition> defs = defaultBpmDefinitionDao.getAll();
		return defs.get(0);
	}
}
