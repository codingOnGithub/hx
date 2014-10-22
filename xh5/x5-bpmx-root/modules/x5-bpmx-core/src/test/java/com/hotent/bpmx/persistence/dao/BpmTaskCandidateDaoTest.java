package com.hotent.bpmx.persistence.dao;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hotent.bpmx.api.model.process.task.BpmTaskCandidate;
import com.hotent.bpmx.persistence.BpmxBaseTest;
import com.hotent.bpmx.persistence.model.DefaultBpmTaskCandidate;

public class BpmTaskCandidateDaoTest extends BpmxBaseTest{
	@Resource
	private BpmTaskCandidateDao bpmTaskCandidateDao;
	
	@Test
	@Rollback(true)
	public void testCrud(){
		DefaultBpmTaskCandidate bpmTaskCandidate=new DefaultBpmTaskCandidate();
		Integer randId=new Double(100000*Math.random()).intValue();
		bpmTaskCandidate.setId(idGenerator.getSuid());
		
		//创建一实体
		bpmTaskCandidateDao.create(bpmTaskCandidate);
        Assert.assertNotNull(bpmTaskCandidate.getId());
        logger.debug("bpmTaskCandidate1:"+ bpmTaskCandidate.getId());
		//获取一实体
		DefaultBpmTaskCandidate bpmTaskCandidate2=(DefaultBpmTaskCandidate)bpmTaskCandidateDao.get(bpmTaskCandidate.getId());
		Assert.assertNotNull(bpmTaskCandidate2);
		logger.debug("bpmTaskCandidate2:" + bpmTaskCandidate2.toString());
		Integer randId2=new Double(100000*Math.random()).intValue();
		//更新一实体
		bpmTaskCandidateDao.update(bpmTaskCandidate2);
		
		BpmTaskCandidate bpmTaskCandidate3=bpmTaskCandidateDao.get(bpmTaskCandidate.getId());
		System.out.println("bpmTaskCandidate3:"+bpmTaskCandidate3.toString());
		//删除一实体
		//bpmTaskCandidateDao.remove(bpmTaskCandidate.getId());
	}
	
}
