package com.hotent.bpmx.persistence.manager;

import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hotent.base.core.constants.Bool;
import com.hotent.bpmx.api.model.process.nodedef.BpmUserNode;
import com.hotent.bpmx.persistence.BpmxBaseTest;
import com.hotent.bpmx.persistence.model.DefaultBpmDefinition;
import com.hotent.bpmx.persistence.model.DefaultBpmNodeConfig;
import com.hotent.bpmx.persistence.model.DefaultBpmUserNode;

public class BpmUserNodeManagerTest extends BpmxBaseTest{
	@Resource
	BpmUserNodeManager bpmUserNodeManager;
	
	@Resource
	BpmNodeConfigManager bpmNodeConfigManager;
	
	@Resource
	private BpmDefinitionManager bpmDefinitionManager;
	
	@Test
	@Rollback(false)	
	public void testCreate(){
		DefaultBpmNodeConfig nodeConfig = bpmNodeConfigManager.get("10000000090001");		
		DefaultBpmUserNode bpmUserNode = new DefaultBpmUserNode();
		bpmUserNode.setId(nodeConfig.getConfigId());
		bpmUserNode.setBizType(BpmUserNode.BIZ_TYPE.NORMAL);
		bpmUserNode.setIsAllowBack(Bool.TRUE.value());
		bpmUserNode.setIsAllowBackToStart(Bool.TRUE.value());
		bpmUserNode.setIsHideExecPath(Bool.FALSE.value());
		bpmUserNode.setIsHideOpinionField(Bool.FALSE.value());		
		//创建一实体
		bpmUserNodeManager.create(bpmUserNode);
	}
	
	@Test
	public void testClone(){
		String entityId = "10000000090001";
		DefaultBpmUserNode bpmUserNode =bpmUserNodeManager.get(entityId);		
		DefaultBpmUserNode cloneBpmUserNode = (DefaultBpmUserNode)bpmUserNode.clone();
		
		Assert.assertEquals(bpmUserNode.getIsAllowBack(), cloneBpmUserNode.getIsAllowBack());
		Assert.assertEquals(bpmUserNode.getNodeId(), cloneBpmUserNode.getNodeId());
	}	
	
	@Test
	public void getCascade() {
		String entityId = "10000000090001";
		DefaultBpmUserNode bpmUserNode = bpmUserNodeManager.get(entityId);
		Assert.assertNotNull(bpmUserNode);
		Assert.assertEquals(bpmUserNode.getId(), entityId);
	}
	
	protected String getProcDefId(){
		List<DefaultBpmDefinition> defs = bpmDefinitionManager.getAll();
		if(defs.size()>0){
			return defs.get(0).getDefId();
		}
		return "";
	}
}
