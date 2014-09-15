package com.hotent.platform.dao.bpm;

import java.util.List;

import javax.annotation.Resource;

import com.hotent.BaseTestCase;
import com.hotent.platform.model.bpm.BpmNodeSet;

public class BpmNodeSetDaoTest  extends BaseTestCase{
	
	@Resource
	private BpmNodeSetDao bpmNodeSetDao;

	
	public void getByActDefId(){
		String actDefId="testkey:3:5808";
	    List<BpmNodeSet> list=	bpmNodeSetDao.getByActDefId(actDefId);
	    System.out.println("getByActDefId:" + list.size());
	}
	
	@org.junit.Test
	public void getByDefId(){
		
	    List<BpmNodeSet> list=	bpmNodeSetDao.getByDefId(1337423308606L);
	    System.out.println("getByDefId:" + list.size());
	}
}
