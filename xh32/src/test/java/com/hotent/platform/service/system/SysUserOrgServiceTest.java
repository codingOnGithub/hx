package com.hotent.platform.service.system;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.hotent.BaseTestCase;
import com.hotent.core.model.TaskExecutor;

public class SysUserOrgServiceTest extends BaseTestCase{
	
	@Resource
	private SysUserOrgService sysUserOrgService;
	
	
	
	@org.junit.Test
	public void getLeaderByUserId(){
		List<TaskExecutor> users= sysUserOrgService.getLeaderByUserId(1340765052532L);
		System.out.println(users.size());
	}

}
