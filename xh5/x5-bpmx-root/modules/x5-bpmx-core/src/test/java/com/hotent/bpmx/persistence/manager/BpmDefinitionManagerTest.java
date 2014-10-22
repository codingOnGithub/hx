package com.hotent.bpmx.persistence.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hotent.base.api.query.FieldRelation;
import com.hotent.base.api.query.FieldSort;
import com.hotent.base.api.query.QueryOP;
import com.hotent.base.core.constants.Bool;
import com.hotent.base.core.util.time.DateUtil;
import com.hotent.base.db.model.DefaultFieldLogic;
import com.hotent.base.db.model.DefaultQueryField;
import com.hotent.base.db.model.DefaultQueryFilter;
import com.hotent.base.db.mybatis.domain.DefaultFieldSort;
import com.hotent.base.db.mybatis.domain.DefaultPage;
import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.bpmx.persistence.BpmxBaseTest;
import com.hotent.bpmx.persistence.constants.ProcDefStatus;
import com.hotent.bpmx.persistence.constants.ProcDefTestStatus;
import com.hotent.bpmx.persistence.model.DefaultBpmDefinition;
import com.hotent.bpmx.persistence.model.DefaultBpmNodeConfig;
import com.hotent.bpmx.persistence.model.query.BpmDefFieldSorts;
import com.hotent.bpmx.persistence.model.query.BpmDefQueryFields;

public class BpmDefinitionManagerTest extends BpmxBaseTest{
	@Resource
	BpmDefinitionManager bpmDefinitionManager;
	
	@Resource
	BpmNodeConfigManager bpmNodeConfigManager;
	
	@Test
	@Rollback(false)	
	public void testCreate(){
		DefaultBpmDefinition defaultBpmDefinition=new DefaultBpmDefinition();
		Integer randId=new Double(100000*Math.random()).intValue();
		defaultBpmDefinition.setDefId(idGenerator.getSuid());
		defaultBpmDefinition.setName("defaultBpmDefinition" + randId);
		defaultBpmDefinition.setInstSubjectRule("defaultBpmDefinition" + randId);
		defaultBpmDefinition.setDefKey("defaultBpmDefinition" + randId);
		defaultBpmDefinition.setStatus(ProcDefStatus.DRAFT.getKey());
		defaultBpmDefinition.setTestStatus("defaultBpmDefinition" + randId);
		defaultBpmDefinition.setIsMain(Bool.TRUE.value());
		//创建一实体
		bpmDefinitionManager.create(defaultBpmDefinition);
	}

	@Test
	@Rollback(true)
	public void testClone(){
		DefaultBpmDefinition bpmDefinition = bpmDefinitionManager.getWithCascade("10000000088019");
		DefaultBpmDefinition newBpmDefinition = bpmDefinitionManager.cloneToMain(bpmDefinition);
		
		List<DefaultBpmNodeConfig> nodeConfigs = bpmNodeConfigManager.queryBpmNodeConfigs(bpmDefinition.getDefId());
		List<DefaultBpmNodeConfig> newNodeConfigs = bpmNodeConfigManager.queryBpmNodeConfigs(newBpmDefinition.getDefId());
		
		Assert.assertEquals(bpmDefinition.getDefKey(), newBpmDefinition.getDefKey());
		Assert.assertEquals(nodeConfigs.size(), newNodeConfigs.size());
	}
	
	@Test
	public void getMaxVersion(){
		Integer maxVersion = bpmDefinitionManager.getMaxVersion("defkey1387271133622");
		Assert.assertEquals(maxVersion, Integer.valueOf(0));		
	}
	
	@Test
	public void query(){
		//流程定义查询字段集合
		BpmDefQueryFields bpmDefQueryFields = new BpmDefQueryFields();
		bpmDefQueryFields.addName("default").addDefKey("def");
		List<ProcDefStatus> statusList = new ArrayList<ProcDefStatus>();
		statusList.add(ProcDefStatus.DRAFT);
		statusList.add(ProcDefStatus.PUBLISHED);
		bpmDefQueryFields.addStatus(statusList);
		bpmDefQueryFields.addTestStatus(ProcDefTestStatus.TEST);
		bpmDefQueryFields.addIsMain(Bool.TRUE);
		Date endDate = new Date();
		Date startDate = DateUtil.setAsBegin(endDate);
		bpmDefQueryFields.addCreateTimeRange(startDate, endDate);		
		
		
		//流程定义排序字段集合
		BpmDefFieldSorts bpmDefFieldSorts = new BpmDefFieldSorts();
		bpmDefFieldSorts.addDefId();
		//分页
		DefaultPage page = new DefaultPage(1,2,bpmDefFieldSorts.getFieldSorts());
		
		PageList<DefaultBpmDefinition> defPageList = (PageList<DefaultBpmDefinition>)bpmDefinitionManager.query(bpmDefQueryFields, FieldRelation.AND, page);
		Assert.assertTrue(defPageList.size()==1);
		
		List<DefaultBpmDefinition> defList = bpmDefinitionManager.query(bpmDefQueryFields, FieldRelation.AND, bpmDefFieldSorts);
		Assert.assertTrue(defList.size()>0);
	}
	@Test
	public void query_nopage(){
		DefaultQueryFilter queryFilter = new DefaultQueryFilter();
		//构造排序
		FieldSort sort=new DefaultFieldSort("DEF_ID_");
		//构造查询条件
		DefaultFieldLogic fieldLogic = new DefaultFieldLogic(FieldRelation.AND);
		fieldLogic.getWhereClauses().add(new DefaultQueryField("NAME_",QueryOP.LIKE,"test"));
		//设置分页和查询条件
		queryFilter.setFieldLogic(fieldLogic);
		queryFilter.setPage(null);
		List<DefaultBpmDefinition> defList = bpmDefinitionManager.query(queryFilter);
		Assert.assertTrue(defList.size()>0);
	}
}
