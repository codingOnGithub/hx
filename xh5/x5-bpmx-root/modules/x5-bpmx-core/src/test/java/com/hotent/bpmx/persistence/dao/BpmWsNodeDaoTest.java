package com.hotent.bpmx.persistence.dao;

import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hotent.base.api.query.FieldRelation;
import com.hotent.base.api.query.FieldSort;
import com.hotent.base.db.model.DefaultFieldLogic;
import com.hotent.base.db.model.DefaultQueryField;
import com.hotent.base.db.model.DefaultQueryFilter;
import com.hotent.base.db.mybatis.domain.DefaultFieldSort;
import com.hotent.base.db.mybatis.domain.DefaultPage;
import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.bpmx.api.model.process.nodedef.BpmNodeConfig;
import com.hotent.bpmx.persistence.BpmxBaseTest;
import com.hotent.bpmx.persistence.model.BpmWsNode;
import com.hotent.bpmx.persistence.model.DefaultBpmDefinition;

public class BpmWsNodeDaoTest extends BpmxBaseTest{
	
	@Resource
	private BpmNodeConfigDao defaultBpmNodeConfigDao;
	
	@Resource
	private BpmDefinitionDao defaultBpmDefinitionDao;	
	
	@Resource
	private BpmWsNodeDao bpmWsNodeDao;
	
	@Test
	@Rollback(true)
	public void testCrud(){
		String procDefId = getProcDefId();
		
		BpmWsNode bpmWsNode=new BpmWsNode();
		
		Integer randId=new Double(100000*Math.random()).intValue();
		bpmWsNode.setId(idGenerator.getSuid());
		bpmWsNode.setNodeId("nodeId");
		bpmWsNode.setProcDefId(procDefId);
		bpmWsNode.setNodeType(BpmNodeConfig.NODE_TYPE.WEB_SERVICE);						
		
		//创建一实体
		bpmWsNodeDao.create(bpmWsNode);
        Assert.assertNotNull(bpmWsNode.getId());
        logger.debug("bpmWsNode1:"+ bpmWsNode.getId());
		//获取一实体
		BpmWsNode bpmWsNode2=bpmWsNodeDao.get(bpmWsNode.getId());
		Assert.assertNotNull(bpmWsNode2);
		logger.debug("bpmWsNode2:" + bpmWsNode2.toString());
		Integer randId2=new Double(100000*Math.random()).intValue();
		bpmWsNode2.setWsConfigs("bpmWsNode" + randId2);
		//更新一实体
		bpmWsNodeDao.update(bpmWsNode2);
		
		BpmWsNode bpmWsNode3=bpmWsNodeDao.get(bpmWsNode.getId());
		System.out.println("bpmWsNode3:"+bpmWsNode3.toString());
		//删除一实体
		//bpmWsNodeDao.remove(bpmWsNode.getId());
	}
	
	@Test
	public void query(){
		DefaultQueryFilter queryFilter = new DefaultQueryFilter();
		//构造排序
		FieldSort sort=new DefaultFieldSort("ID_");
		//构造分页
		DefaultPage page=new DefaultPage(1, 2,sort);
		//构造查询条件
		DefaultFieldLogic fieldLogic = new DefaultFieldLogic(FieldRelation.AND);
		fieldLogic.getWhereClauses().add(new DefaultQueryField("ID_","20000000010001"));
		//设置分页和查询条件
		queryFilter.setPage(page);
		queryFilter.setFieldLogic(fieldLogic);
		//查询
		PageList<BpmWsNode> nodeList = (PageList<BpmWsNode>)bpmWsNodeDao.query(queryFilter);		
		Assert.assertTrue(nodeList.size()>0);
	}
	
	private String getProcDefId(){
		List<DefaultBpmDefinition> defs = defaultBpmDefinitionDao.getAll();
		if(defs.size()>0){
			return defs.get(0).getDefId();
		}
		return "";
	}
}
