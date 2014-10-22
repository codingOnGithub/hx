package com.ksoft.base.service.manager.ws.first;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.ClientImpl;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.service.Service;
import org.apache.cxf.service.model.BindingInfo;
import org.apache.cxf.service.model.BindingMessageInfo;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.apache.cxf.service.model.ServiceInfo;

public class DemoDynamicClient {
	private String wsdlPath = "http://localhost:10088/ws/cmd/user/reg?wsdl";
	// private String wsdlPath = "http://localhost:9000/Complex?wsdl";

	// private String wsdlPath = "http://localhost:10088/http/demo/hello?wsdl";
	// private String wsdlPath =
	// "http://www.yun9.com:10088/ws/cmd/user/reg?wsdl";

	private static final QName SERVICE_NAME = new QName(
			"http://www.ksoft.com/service/masterdata/user", "RegUserService");

	private static final QName OPERATION_NAME = new QName(
			"http://www.ksoft.com/service/masterdata/user", "RegUser");

	public static void main(String[] arg) throws MalformedURLException,
			IllegalArgumentException, InstantiationException,
			IllegalAccessException, IntrospectionException,
			InvocationTargetException {
		DemoDynamicClient ddc = new DemoDynamicClient();
		ddc.invok();
	}

	public void invok() throws MalformedURLException, IllegalArgumentException,
			InstantiationException, IllegalAccessException,
			IntrospectionException, InvocationTargetException {
		URL wsdlURL = new URL(wsdlPath);

		JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory
				.newInstance();

		// Client client = factory.createClient(wsdlURL.toExternalForm(),
		// SERVICE_NAME);

		Client client = factory.createClient(wsdlURL.toExternalForm());

		ClientImpl clientImpl = (ClientImpl) client;

		// this.printInfo(clientImpl);

		this.action(clientImpl);

	}

	private void action(Client client) throws InstantiationException,
			IllegalAccessException, IntrospectionException,
			IllegalArgumentException, InvocationTargetException {
		Service service = client.getEndpoint().getService();
		ServiceInfo serviceInfo = service.getServiceInfos().get(0);

		BindingInfo bindInfo = serviceInfo.getBinding(new QName(
				"http://www.ksoft.com/service/masterdata/user",
				"RegUserServiceSoapBinding"));

		BindingOperationInfo operation = bindInfo.getOperation(OPERATION_NAME);

		BindingMessageInfo inputParams = operation.getInput();

		Class<?> inputClass = inputParams.getMessageParts().get(0)
				.getTypeClass();

		Object inputObject = inputClass.newInstance();

		PropertyDescriptor partPropertyDescriptor = new PropertyDescriptor(
				"regUserInfo", inputClass);

		Object inputPropObject = this.writePropObject(
				partPropertyDescriptor.getPropertyType(), this.builderParams());

		partPropertyDescriptor.getWriteMethod().invoke(inputObject,
				inputPropObject);

		try {
			Object[] result = client.invoke(OPERATION_NAME, inputPropObject);
			Object resultObject = result[0];

			System.out.println("结果："
					+ resultObject.getClass().getCanonicalName());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private Map<String, Object> builderParams() {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("userNo", "111111" + System.currentTimeMillis());
		params.put("userName", "测试");
		params.put("idCard", "422801198108012414");
		params.put("sex", "male");
		params.put("password", "111111");
		params.put("inviteCode", "xxxfffdadfadf");
		params.put("institutionNo", "djkj");
		params.put("roles", "ROLE_ADMIN");
		params.put("weixinId", "123123123" + System.currentTimeMillis());

		return params;
	}

	private void printInfo(Client client) {
		Service service = client.getEndpoint().getService();

		System.out.println("Service name:" + service.getName());

		System.out
				.println("***********************Service Info***************************");
		for (ServiceInfo si : service.getServiceInfos()) {
			System.out.println("ServiceInfo TargetNamespace:"
					+ si.getTargetNamespace());
			System.out.println("ServiceInfo Documentation:"
					+ si.getDocumentation());
			System.out.println("ServiceInfo Description:"
					+ si.getDescription().getDocumentation());

			for (BindingInfo bi : si.getBindings()) {
				this.printBindingInfo(bi);
			}

		}
		System.out
				.println("***********************Service Info***************************");

		System.out
				.println("***********************Service Endpoints***************************");
		for (Map.Entry<QName, Endpoint> entity : service.getEndpoints()
				.entrySet()) {
			System.out.println("QName prefix:" + entity.getKey().getPrefix());
			System.out.println("QName NamespaceURI:"
					+ entity.getKey().getNamespaceURI());
			System.out.println("QName LocalPart:"
					+ entity.getKey().getLocalPart());

		}
		System.out
				.println("***********************Service Endpoints***************************");

	}

	private void printBindingInfo(BindingInfo bindingInfo) {

		System.out.println("bindInfoName:" + bindingInfo.getName());

		for (BindingOperationInfo boi : bindingInfo.getOperations()) {
			this.printOperation(boi);

		}
	}

	private void printOperation(BindingOperationInfo boi) {
		System.out
				.println("************************begin operation:"
						+ boi.getName().getLocalPart()
						+ "****************************");

		System.out.println(boi.getInput().getMessageInfo().getMessageParts());
		System.out.println(boi.getOutput().getMessageInfo().getMessageParts());

		System.out
				.println("****************************************************");
	}

	private Object writePropObject(Class<?> clazz, Map<String, Object> values)
			throws IntrospectionException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {

		Object propObject = clazz.newInstance();

		if (values != null && !values.isEmpty()) {
			for (Map.Entry<String, Object> entity : values.entrySet()) {
				PropertyDescriptor userNoPropertyDescriptor = new PropertyDescriptor(
						entity.getKey(), clazz);
				userNoPropertyDescriptor.getWriteMethod().invoke(propObject,
						entity.getValue());
			}

		}

		return propObject;
	}

}
