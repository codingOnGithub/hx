package com.hotent.platform.ws;

import java.util.Map;

import org.junit.Test;

import com.hotent.platform.webservice.model.Org;
import com.hotent.platform.webservice.model.User;
import com.hotent.platform.ws.impl.OrgService;
import com.hotent.platform.ws.impl.UserOrgService;
import com.hotent.platform.ws.impl.UserService;

public class WebServiceTest {
	// 请求的URL地址
	String url = "http://127.0.0.1:8080/bpm/service/userOrgService?wsdl";

	// 测试 的方法
	@Test
	public void testUser() throws Exception {

		User user = new User();
		user.setAccount("test");
		UserService userService = new UserService(url);

		Map<String, Object> map = userService.add(user);
		System.out.println("请求返回的soap包：" + map.get("response"));
		System.out.println("请求返回的状态码：" + map.get("code"));
		System.out.println("请求返回的结果：" + map.get("result"));
	}

	// 测试的方法
	@Test
	public void testOrg() throws Exception {

		Org org = new Org();
		org.setOrgName("测试组织");

		OrgService orgService = new OrgService(url);

		Map<String, Object> map = orgService.add(org);
		System.out.println("请求返回的soap包：" + map.get("response"));
		System.out.println("请求返回的状态码：" + map.get("code"));
		System.out.println("请求返回的结果：" + map.get("result"));

	}

	// 测试的方法
	@Test
	public void testUserOrg() throws Exception {
		String userId = "";
		String orgId = "";

		UserOrgService userOrgService = new UserOrgService(url);

		Map<String, Object> map = userOrgService.add(userId, orgId);
		System.out.println("请求返回的soap包：" + map.get("response"));
		System.out.println("请求返回的状态码：" + map.get("code"));
		System.out.println("请求返回的结果：" + map.get("result"));

	}

}
