package com.hotent.platform.service.bpm.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.hotent.BaseTestCase;
import com.hotent.platform.model.system.SysRole;
import com.hotent.platform.service.bpm.impl.ScriptImpl;

public class ScriptImplTest extends BaseTestCase {

	@Resource
	private ScriptImpl scriptImpl;

	@org.junit.Test
	public void getLeaderByUserId() {
		String returnStr ="";
		Object returnObj=new Object();
		boolean returnBl=false;
		
		// 获取发起用户所属角色。
		List<SysRole> roleList = scriptImpl.getUserRoles("1");
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<获取发起用户所属角色");
		for (SysRole role : roleList) {
			System.out.println(role);
		}

		// 判断用户是否属于某角色。
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<判断用户是否属于某角色。");
		 returnBl = scriptImpl.isUserInRole("1", "超级用户");
		System.out.println(returnBl);
		
		returnBl = scriptImpl.isUserInRole("1", "bpm_manager");
		System.out.println(returnBl);

		// 获取流程发起用户的主岗位名称。
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<获取流程发起用户的主岗位名称。");
	    returnStr = scriptImpl.getUserPos(1L);
		System.out.println(returnStr);
		
		// 根据用户ID获取参数值。
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<根据用户ID获取参数值。");
	    returnObj = scriptImpl.getParaValueByUser(1L,"参数");
		System.out.println(returnObj);

		// 获取用户的组织的直属领导岗位。
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<获取用户的组织的直属领导岗位");
	    returnStr = scriptImpl.getDirectLeaderPosByUserId("1");
	    System.out.println(returnStr);
	    
	 // 判断用户是否该部门的负责人
	 		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<判断用户是否该部门的负责人");
	 		 returnBl = scriptImpl.isDepartmentLeader("1","1");
	 	    System.out.println( returnBl);
	 	    
	 	    
	 		
//			// 获取我的领导id集合。
//			Set<String> rtSet = scriptImpl.getMyLeader(1L);
//			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<获取我的领导id集合。");
//			System.out.println(rtSet);
//			
//			
//			// 获取我的下属Id用户ID集合。
//			Set<String> rtSet2 = scriptImpl.getMyUnderUserId(1L);
//			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<获取我的下属Id用户ID集合。");
//			System.out.println(rtSet2);
			
	}

}
