package com.hotent.bpmx.persistence.dao;

import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hotent.base.core.constants.Bool;
import com.hotent.bpmx.api.model.process.nodedef.BpmNodeConfig;
import com.hotent.bpmx.persistence.BpmxBaseTest;
import com.hotent.bpmx.persistence.model.DefaultBpmDefinition;
import com.hotent.bpmx.persistence.model.DefaultBpmUserNode;

public class BpmUserNodeDaoTest extends BpmxBaseTest{
	@Resource
	private BpmUserNodeDao bpmUserNodeDao;
	
	@Resource
	private BpmNodeConfigDao defaultBpmNodeConfigDao;
	
	@Resource
	private BpmDefinitionDao defaultBpmDefinitionDao;
	
	@Test
	@Rollback(true)
	public void testCrud(){		
		String procDefId = getProcDefId();
		
		DefaultBpmUserNode bpmUserNode=new DefaultBpmUserNode();
		Integer randId=new Double(100000*Math.random()).intValue();
		bpmUserNode.setId(idGenerator.getSuid());
		bpmUserNode.setBizType("bpmUserNode" + randId);
		bpmUserNode.setNodeId("nodeId");
		bpmUserNode.setProcDefId(procDefId);
		bpmUserNode.setNodeType(BpmNodeConfig.NODE_TYPE.MSG);
		
		//创建一实体
		bpmUserNodeDao.create(bpmUserNode);
        Assert.assertNotNull(bpmUserNode.getId());
        logger.debug("bpmUserNode1:"+ bpmUserNode.getId());
		//获取一实体
		DefaultBpmUserNode bpmUserNode2=bpmUserNodeDao.get(bpmUserNode.getId());
		Assert.assertNotNull(bpmUserNode2);
		System.out.println("bpmUserNode2:" + bpmUserNode2.toString());		
		Integer randId2=new Double(100000*Math.random()).intValue();
		bpmUserNode2.setBizType("bpmUserNode" + randId2);
		//更新一实体
		bpmUserNode2.setNodeName("temp");
		bpmUserNode2.setIsAllowBack(Bool.TRUE.value());
		bpmUserNodeDao.update(bpmUserNode2);
		
		DefaultBpmUserNode bpmUserNode3=bpmUserNodeDao.get(bpmUserNode.getId());
		System.out.println("bpmUserNode3:"+bpmUserNode3.toString());
		//删除一实体
		bpmUserNodeDao.remove(bpmUserNode.getId());
	}
	
	private String getProcDefId(){
		List<DefaultBpmDefinition> defs = defaultBpmDefinitionDao.getAll();
		if(defs.size()>0){
			return defs.get(0).getDefId();
		}
		return "";
	}
}
