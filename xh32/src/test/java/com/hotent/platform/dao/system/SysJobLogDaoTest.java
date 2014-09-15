package com.hotent.platform.dao.system;

import java.util.Date;

import javax.annotation.Resource;

import com.hotent.BaseTestCase;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.model.system.SysJobLog;

public class SysJobLogDaoTest extends BaseTestCase {

	@Resource
	private SysJobLogDao sysJobLogDao;
	
	@org.junit.Test
	public void add() throws Exception{
		SysJobLog sysJobLog=new SysJobLog();
		sysJobLog.setLogId(UniqueIdUtil.genId());
		sysJobLog.setContent("aaaa");
		sysJobLog.setStartTime(new Date());
		sysJobLog.setRunTime(1L);
		sysJobLog.setEndTime(new Date());
		sysJobLog.setJobName("jobname444444444");
		sysJobLog.setState(1);
		
		sysJobLogDao.add(sysJobLog);
		
		System.out.println("ok");
		
	}
}
