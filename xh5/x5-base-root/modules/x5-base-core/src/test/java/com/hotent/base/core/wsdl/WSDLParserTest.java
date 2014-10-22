package com.hotent.base.core.wsdl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.wsdl.WSDLException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hotent.base.core.wsdl.model.OperationInfo;
import com.hotent.base.core.wsdl.model.ParameterInfo;
import com.hotent.base.core.wsdl.model.ServiceInfo;

public class WSDLParserTest {

	protected static Logger logger = LoggerFactory
			.getLogger(WSDLParserTest.class);
	String wsdlUrl = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl";



	/**
	 * 测试的方法
	 * 
	 * @param args
	 */
	@Test
	public void test() {

		// String wsdlURI =
		// "http://127.0.0.1:8080/moi/service/ProcessService?wsdl";
		try {
			WSDLParser parser = new WSDLParser(wsdlUrl);
			Collection<ServiceInfo> serviceInfos = parser.getServices()
					.values();
			Iterator<?> it = serviceInfos.iterator();
			while (it.hasNext()) {
				ServiceInfo serviceInfo = (ServiceInfo) it.next();
				Map<String, OperationInfo> operationList = serviceInfo
						.getOperations();
				Set<?> keys = operationList.keySet();
				for (Iterator<?> iterator = keys.iterator(); iterator.hasNext();) {
					Object key = iterator.next();
					OperationInfo info = operationList.get(key);
					List<ParameterInfo> inputParams = (List<ParameterInfo>) info
							.getInputParams();
					Iterator<ParameterInfo> it1 = inputParams.iterator();
					while (it1.hasNext()) {
						ParameterInfo tempinfo = it1.next();
						getParam(tempinfo);
					}
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
	
	
	/**
	 * 测试辅助
	 * 
	 * @param operationInfo
	 * @param output
	 */
	private static void getParam(ParameterInfo parameterInfo) {

		if (parameterInfo.getIsComplext() == ParameterInfo.COMPLEX_YES) {
			if (!"parameters".equals(parameterInfo.getName())) {
				logger.info("--" + parameterInfo.getType() + "复杂类型开始:--");
			}
			Map<String, ParameterInfo> tempMap = parameterInfo
					.getComplextParams();
			Set<?> keys = tempMap.keySet();
			for (Iterator<?> otheriterator = keys.iterator(); otheriterator
					.hasNext();) {
				Object key = otheriterator.next();
				ParameterInfo parameter = tempMap.get(key);// 上面key对应的value
				getParam(parameter);
			}
			if (!"parameters".equals(parameterInfo.getName())) {
				logger.info("--" + parameterInfo.getType() + "复杂类型end:--");
			}
		} else {
			logger.info("  --inputparamName:" + parameterInfo.getName()
					+ "  --inputparamType:" + parameterInfo.getType());
		}
	}

	/**
	 * 获取JSON树
	 * 
	 * @throws WSDLException
	 *             void
	 * @since 1.0.0
	 */
	@Test
	public void getTree() throws WSDLException {

		String wsdlUrl = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl";
		if (StringUtils.isEmpty(wsdlUrl))
			return;
		WSDLParser parser = new WSDLParser(wsdlUrl);
		Map<String, ServiceInfo> services = parser.getServices();

		JSONArray treeList = new JSONArray();
		for (String key : services.keySet()) {
			ServiceInfo serviceInfo = services.get(key);
			JSONObject oneService = new JSONObject();
			oneService.accumulate("name", serviceInfo.getName())
					.accumulate("wsdlUrl", serviceInfo.getWsdlUrl())
					.accumulate("namespace", serviceInfo.getTargetNamespace())
					.accumulate("open", true);
			JSONArray children = new JSONArray();
			for (String operationKey : serviceInfo.getOperations().keySet()) {
				OperationInfo operation = serviceInfo.getOperations().get(
						operationKey);
				JSONObject oneOperation = new JSONObject();
				oneOperation
						.accumulate("name", operation.getOperationName())
						.accumulate("wsdlUrl", serviceInfo.getWsdlUrl())
						.accumulate("namespace",
								serviceInfo.getTargetNamespace());
				children.add(oneOperation);
			}
			oneService.accumulate("children", children);
			treeList.add(oneService);
		}

		logger.info(" 解析的JSON树:" + treeList.toString());
	}
}
