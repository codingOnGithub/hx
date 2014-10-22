package com.hotent.bpmx.persistence.dao;

import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hotent.bpmx.api.model.process.nodedef.BpmNodeConfig;
import com.hotent.bpmx.persistence.BpmxBaseTest;
import com.hotent.bpmx.persistence.model.BpmNodeScript;

public class BpmNodeScriptDaoTest extends BpmxBaseTest{
	@Resource
	private BpmNodeScriptDao bpmNodeScriptDao;
	
	@Resource
	private BpmNodeConfigDao bpmNodeConfigDao;
	
	@Test
	@Rollback(true)
	public void testCrud(){
		BpmNodeConfig bpmNodeConfig = getBpmNodeConfig();
		
		BpmNodeScript bpmNodeScript=new BpmNodeScript();
		Integer randId=new Double(100000*Math.random()).intValue();
		bpmNodeScript.setId(idGenerator.getSuid());
		bpmNodeScript.setConfigId(bpmNodeConfig.getConfigId());
		bpmNodeScript.setScript("bpmNodeScript" + randId);
		bpmNodeScript.setScriptType("bpmNodeScript" + randId);
		
		//创建一实体
		bpmNodeScriptDao.create(bpmNodeScript);
        Assert.assertNotNull(bpmNodeScript.getId());
        logger.debug("bpmNodeScript1:"+ bpmNodeScript.getId());
		//获取一实体
		BpmNodeScript bpmNodeScript2=bpmNodeScriptDao.get(bpmNodeScript.getId());
		Assert.assertNotNull(bpmNodeScript2);
		logger.debug("bpmNodeScript2:" + bpmNodeScript2.toString());
		Integer randId2=new Double(100000*Math.random()).intValue();
		bpmNodeScript2.setConfigId(bpmNodeConfig.getConfigId());
		bpmNodeScript2.setScript("bpmNodeScript" + randId2);
		bpmNodeScript2.setScriptType("bpmNodeScript" + randId2);
		//更新一实体
		bpmNodeScriptDao.update(bpmNodeScript2);
		
		BpmNodeScript bpmNodeScript3=bpmNodeScriptDao.get(bpmNodeScript.getId());
		System.out.println("bpmNodeScript3:"+bpmNodeScript3.toString());
		//删除一实体
		//bpmNodeScriptDao.remove(bpmNodeScript.getId());
	}
	
	@Test
	public void queryByConfigId(){
		BpmNodeConfig bpmNodeConfig = getBpmNodeConfig();
		
		List<BpmNodeScript> bpmNodeScripts = bpmNodeScriptDao.queryByConfigId(bpmNodeConfig.getConfigId());
		Assert.assertTrue(bpmNodeScripts.size()>0);
	}
	
	private BpmNodeConfig getBpmNodeConfig(){
		return bpmNodeConfigDao.getAll().get(0);
	}
}
