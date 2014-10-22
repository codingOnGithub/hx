package com.hotent.bpmx.persistence.manager;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hotent.bpmx.persistence.BpmxBaseTest;
import com.hotent.bpmx.persistence.model.DefaultBpmDefinition;
import com.hotent.bpmx.persistence.model.DefaultBpmNodeConfig;

public class BpmNodeConfigManagerTest extends BpmxBaseTest{
	@Resource
	BpmNodeConfigManager defaultBpmNodeConfigManager;
	
	@Resource
	private BpmDefinitionManager bpmDefinitionManager;
	
	@Test
	@Rollback(true)	
	public void testCreate(){
		DefaultBpmNodeConfig defaultBpmNodeConfig=new DefaultBpmNodeConfig();
		Integer randId=new Double(100000*Math.random()).intValue();
		defaultBpmNodeConfig.setConfigId(idGenerator.getSuid());
		defaultBpmNodeConfig.setNodeId("defaultBpmNodeConfig" + randId);
		defaultBpmNodeConfig.setProcDefId(getProcDefId());
		defaultBpmNodeConfig.setNodeType("defaultBpmNodeConfig" + randId);
		//创建一实体
		defaultBpmNodeConfigManager.create(defaultBpmNodeConfig);
	}
	
	protected String getProcDefId(){
		List<DefaultBpmDefinition> defs = bpmDefinitionManager.getAll();
		if(defs.size()>0){
			return defs.get(0).getDefId();
		}
		return "";
	}
}
