package com.hotent.core.bpm;

import javax.annotation.Resource;

import org.activiti.engine.impl.persistence.entity.ExecutionEntity;

import com.hotent.BaseTestCase;
import com.hotent.platform.dao.bpm.ExecutionExtDao;

public class ExecutionExtDaoTest extends BaseTestCase {
	
	@Resource
	private ExecutionExtDao executionExtDao;
	
	@org.junit.Test
	public void getById(){
		ExecutionEntity ent=executionExtDao.getById("10000011320163");
		System.out.println("ok");
	}

}
