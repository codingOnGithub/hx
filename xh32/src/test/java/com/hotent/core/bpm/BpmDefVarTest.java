package com.hotent.core.bpm;

import java.util.List;

import javax.annotation.Resource;

import com.hotent.BaseTestCase;
import com.hotent.platform.dao.bpm.BpmDefVarDao;
import com.hotent.platform.model.bpm.BpmDefVar;

public class BpmDefVarTest extends BaseTestCase {

	@Resource
	private BpmDefVarDao bpmDefVarDao;
	
	@org.junit.Test
	public void getByDefId(){
		Long defId=1L;
		List<BpmDefVar> bpmDefVar=bpmDefVarDao.getByDefId(defId);
		System.out.println("getByDefId");
	}

}
