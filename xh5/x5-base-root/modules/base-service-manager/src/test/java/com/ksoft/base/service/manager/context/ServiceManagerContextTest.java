package com.ksoft.base.service.manager.context;

import java.util.HashMap;
import java.util.Map;

import com.ksoft.base.service.manager.ServiceManagerConfiguration;
import com.ksoft.base.service.manager.model.ServiceBean;
import com.ksoft.base.service.manager.session.ServiceSession;
import com.ksoft.base.service.manager.util.JsonPluginsUtil;

import junit.framework.TestCase;

public class ServiceManagerContextTest extends TestCase{
	private ServiceManagerContext context ;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		context = ServiceManagerConfiguration.getInstance().getBean(
				ServiceManagerContext.class);
	}
	

	public void testParse()
	{
		ServiceSession session = context.parse("service-manager.xml");
		
		System.out.println(JsonPluginsUtil.beanToJson(session));
		
		ServiceBean serviceBean = session.getServiceBean("getTasksByUserId", "188-bpm");
		System.out.println(JsonPluginsUtil.beanToJson(serviceBean));
	}
	
	public void testInvok()
	{
		ServiceSession session = context.parse("service-manager.xml");
		
		ServiceBean serviceBean = session.getServiceBean("getTasksByUserId", "188-bpm");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", 1);
		String result = context.invokeService(serviceBean, params);
		System.out.println(result);
		
		/*ServiceBean serviceBean = session.getServiceBean("findAliasByAliasno", "yun9-user");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("aliasno", "opiJ6jsip_ymJTSh8X99m0un3tnU");
		String result = context.invokeService(serviceBean, params);
		System.out.println(result);*/
		
		/*ServiceBean serviceBean = session.getServiceBean("getGroupInfo", "weixinopen");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("access_token", "GIAF4ovpDGwXr1bOFS7J4rkjY7jaBVg4ppIipW_Pr3eG_tuJjfCpEwmXoBt_Ul6PvZErP42S6tJzumAJvuhoPVbfKwKwjsZkZvhF2IOnO2_d5QbXFGepAw2O_usc6r9lStPgT24pn2OGx--d5NxIdw");
		String result = context.invokeService(serviceBean, params);
		System.out.println(result);*/
		
	}
}