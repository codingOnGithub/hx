package com.hotent.bpmx.persistence.manager;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hotent.bpmx.api.model.process.nodedef.BpmNodeConfig;
import com.hotent.bpmx.persistence.BpmxBaseTest;
import com.hotent.bpmx.persistence.model.BpmNodeScript;

public class BpmNodeScriptManagerTest extends BpmxBaseTest{
	@Resource
	BpmNodeScriptManager bpmNodeScriptManager;
	
	@Resource
	BpmNodeConfigManager bpmNodeConfigManager;
	
	@Test
	@Rollback(true)
	public void testCreate(){
		BpmNodeConfig bpmNodeConfig = getBpmNodeConfig();
		
		BpmNodeScript bpmNodeScript=new BpmNodeScript();
		Integer randId=new Double(100000*Math.random()).intValue();
		bpmNodeScript.setId(idGenerator.getSuid());
		bpmNodeScript.setConfigId(bpmNodeConfig.getConfigId());
		bpmNodeScript.setScript("bpmNodeScript" + randId);
		bpmNodeScript.setScriptType("bpmNodeScript" + randId);
		//创建一实体
		bpmNodeScriptManager.create(bpmNodeScript);
	}
	
	public BpmNodeConfig getBpmNodeConfig(){
		return bpmNodeConfigManager.getAll().get(0);
	}
}
