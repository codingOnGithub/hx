package com.ksoft.base.service.manager.context;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;

import com.ksoft.base.service.manager.ServiceManagerConfiguration;
import com.ksoft.base.service.manager.http.HttpService;
import com.ksoft.base.service.manager.util.JsonPluginsUtil;

import junit.framework.TestCase;

public class HttpServiceTest extends TestCase {

	private HttpService httpService;
	
	protected void setUp() throws Exception {
		super.setUp();
		httpService = ServiceManagerConfiguration.getInstance().getBean(HttpService.class);
	}
	
	public void testHttp()
	{
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("code", "123");
		String result = httpService.postCallHttpRequestByJson("http://192.168.1.188:10090/http/weixin/getoauthtoken", 
				JsonPluginsUtil.mapToJson(params, null, false));
		System.out.println(result);
		NameValuePair[] param = {new NameValuePair("code", "123s")};
		String str = httpService.postCallHttpRequest("http://192.168.1.188:10090/http/weixin/getoauthtoken", param);
		System.out.println(str);
	}
}
