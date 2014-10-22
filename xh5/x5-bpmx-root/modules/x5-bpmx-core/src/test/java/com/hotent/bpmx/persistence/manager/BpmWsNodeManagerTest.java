package com.hotent.bpmx.persistence.manager;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hotent.bpmx.persistence.model.BpmWsNode;
import com.hotent.bpmx.persistence.manager.BpmWsNodeManager;
import com.hotent.bpmx.persistence.BpmxBaseTest;

public class BpmWsNodeManagerTest extends BpmxBaseTest{
	@Resource
	BpmWsNodeManager bpmWsNodeManager;
	
	@Test
	@Rollback(true)
	public void testCreate(){
		BpmWsNode bpmWsNode=new BpmWsNode();
		Integer randId=new Double(100000*Math.random()).intValue();
		bpmWsNode.setId(idGenerator.getSuid());
		bpmWsNode.setWsConfigs("bpmWsNode" + randId);
		//创建一实体
		bpmWsNodeManager.create(bpmWsNode);
	}
}
