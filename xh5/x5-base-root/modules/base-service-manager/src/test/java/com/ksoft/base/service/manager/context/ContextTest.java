package com.ksoft.base.service.manager.context;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import com.ksoft.base.service.manager.ServiceManagerConfiguration;
import com.ksoft.base.service.manager.util.IoUtil;
import com.ksoft.base.service.manager.util.JsonPluginsUtil;
import com.ksoft.base.service.manager.ws.WebServiceClient;
import com.ksoft.base.service.manager.ws.model.SoapInvokCmd;
import com.ksoft.base.service.manager.ws.model.SoapService;

public class ContextTest extends TestCase {
	private ServiceManagerContext soapContext;
	private WebServiceClient webServiceClient;
	

	// private String wsdlPath = "http://localhost:10088/ws/cmd/user/reg?wsdl";
	private String wsdlPath = "http://192.168.1.188:8080/bpmx3/service/ProcessService?wsdl";
	// private String wsdlPath =
	// "http://www.yun9.com:10088/ws/cmd/user/reg?wsdl";
	// private String wsdlPath = "http://localhost:9000/Complex?wsdl";

	//

	// private String operationName = "RegUser";
	// private String operationNamespace =
	// "http://www.ksoft.com/service/masterdata/user";

	private String operationName = "getTasksByUserId";
	private String operationNamespace = "http://api.webservice.platform.hotent.com/";

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		soapContext = ServiceManagerConfiguration.getInstance().getBean(
				ServiceManagerContext.class);
		webServiceClient = ServiceManagerConfiguration.getInstance().getBean(WebServiceClient.class);
	}

	public void testParseWsdl() {
		SoapService sopaService = webServiceClient.parse(
				wsdlPath);

		String json = JsonPluginsUtil.beanToJson(sopaService);

		System.out.println(json);

		SoapService tempSoapService = JsonPluginsUtil.jsonToBean(json,
				SoapService.class);

		System.out.println(JsonPluginsUtil.beanToJson(tempSoapService));
	}

	public void testInvok() throws IOException {
		SoapInvokCmd sic = new SoapInvokCmd(wsdlPath, operationName,
				operationNamespace);

		// String json = IoUtil.readAsciiFileForClassPath("invok-reguser.json");
		String json = IoUtil
				.readAsciiFileForClassPath("invok-gettaskbyuserid.json");

		System.out.println("请求参数：" + json);

		sic.setJsonParam(json);

		Object[] returns = webServiceClient.invok(sic);

		
		System.out.println(returns[0].getClass().getCanonicalName());

		List returnList = (List) returns[0];

		for (Object obj : returnList) {
			System.out.println( obj.getClass().getCanonicalName());
		}
		
		String returnJSON = JsonPluginsUtil.listToJson(returnList);
		System.out.println(returnJSON);
		
	}
	
	/*public void testInvokeService()
	{
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("code", "123");
		String result = soapContext.invokeService("188-weixin", "getWxOAuth", params);
		System.out.println(result);
		
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("userId", "1");
		String str = soapContext.invokeService("188-bpm", "getTasksByUserId", param);
		System.out.println(str);
	}
	
	public void testDeployGroup()
	{
		soapContext.deployServiceGroeup("service-group-manager.xml");
	}
	
	public void testDeployService()
	{
		soapContext.deployService("service-manager.xml");
	}*/
}
