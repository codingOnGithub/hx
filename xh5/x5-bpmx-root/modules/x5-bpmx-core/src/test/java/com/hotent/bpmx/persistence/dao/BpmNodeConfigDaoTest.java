package com.hotent.bpmx.persistence.dao;

import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hotent.bpmx.persistence.BpmxBaseTest;
import com.hotent.bpmx.persistence.model.DefaultBpmDefinition;
import com.hotent.bpmx.persistence.model.DefaultBpmNodeConfig;

public class BpmNodeConfigDaoTest extends BpmxBaseTest{
	@Resource
	private BpmNodeConfigDao defaultBpmNodeConfigDao;
	
	@Resource
	private BpmDefinitionDao defaultBpmDefinitionDao;
	
	@Test
	@Rollback(true)
	public void testCrud(){
		DefaultBpmNodeConfig defaultBpmNodeConfig=new DefaultBpmNodeConfig();
		Integer randId=new Double(100000*Math.random()).intValue();
		defaultBpmNodeConfig.setId(idGenerator.getSuid());
		defaultBpmNodeConfig.setNodeId("defaultBpmNodeConfig" + randId);
		defaultBpmNodeConfig.setProcDefId(getProcDefId());
		defaultBpmNodeConfig.setNodeType("defaultBpmNodeConfig" + randId);
		
		//创建一实体
		defaultBpmNodeConfigDao.create(defaultBpmNodeConfig);
        Assert.assertNotNull(defaultBpmNodeConfig.getId());
        logger.debug("defaultBpmNodeConfig1:"+ defaultBpmNodeConfig.getId());
		//获取一实体
		DefaultBpmNodeConfig defaultBpmNodeConfig2=defaultBpmNodeConfigDao.get(defaultBpmNodeConfig.getId());
		Assert.assertNotNull(defaultBpmNodeConfig2);
		logger.debug("defaultBpmNodeConfig2:" + defaultBpmNodeConfig2.toString());
		Integer randId2=new Double(100000*Math.random()).intValue();
		defaultBpmNodeConfig2.setNodeId("defaultBpmNodeConfig" + randId2);
		defaultBpmNodeConfig2.setProcDefId(getProcDefId());
		defaultBpmNodeConfig2.setNodeType("defaultBpmNodeConfig" + randId2);
		//更新一实体
		defaultBpmNodeConfigDao.update(defaultBpmNodeConfig2);
		
		DefaultBpmNodeConfig defaultBpmNodeConfig3=defaultBpmNodeConfigDao.get(defaultBpmNodeConfig.getId());
		System.out.println("defaultBpmNodeConfig3:"+defaultBpmNodeConfig3.toString());
		//删除一实体
		//defaultBpmNodeConfigDao.remove(defaultBpmNodeConfig.getId());
	}

	@Test
	public void queryBpmNodeConfigs(){
		List<DefaultBpmNodeConfig> configs = this.defaultBpmNodeConfigDao.getAll();
		if(configs.size()>0){
			DefaultBpmNodeConfig nodeConfig =configs.get(0); 
			configs = defaultBpmNodeConfigDao.queryBpmNodeConfigs(nodeConfig.getProcDefId());
			Assert.assertTrue(configs.size()>0);
		}		
	}
	
	private String getProcDefId(){
		List<DefaultBpmDefinition> defs = defaultBpmDefinitionDao.getAll();
		if(defs.size()>0){
			return defs.get(0).getDefId();
		}
		return "";
	}
}
