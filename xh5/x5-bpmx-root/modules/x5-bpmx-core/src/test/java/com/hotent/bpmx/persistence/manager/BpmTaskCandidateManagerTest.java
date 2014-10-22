package com.hotent.bpmx.persistence.manager;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hotent.bpmx.persistence.model.DefaultBpmTaskCandidate;
import com.hotent.bpmx.persistence.manager.BpmTaskCandidateManager;
import com.hotent.bpmx.persistence.BpmxBaseTest;

public class BpmTaskCandidateManagerTest extends BpmxBaseTest{
	@Resource
	BpmTaskCandidateManager bpmTaskCandidateManager;
	
	@Test
	@Rollback(true)
	public void testCreate(){
		DefaultBpmTaskCandidate bpmTaskCandidate=new DefaultBpmTaskCandidate();
		Integer randId=new Double(100000*Math.random()).intValue();
		bpmTaskCandidate.setId(idGenerator.getSuid());
		//创建一实体
		bpmTaskCandidateManager.create(bpmTaskCandidate);
	}
}
