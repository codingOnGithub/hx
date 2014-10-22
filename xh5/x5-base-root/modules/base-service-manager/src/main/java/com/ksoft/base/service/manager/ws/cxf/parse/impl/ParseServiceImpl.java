package com.ksoft.base.service.manager.ws.cxf.parse.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.service.model.BindingInfo;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.apache.cxf.service.model.MessagePartInfo;
import org.apache.cxf.service.model.ServiceInfo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ksoft.base.service.manager.exception.WSDLParseException;
import com.ksoft.base.service.manager.util.AssertValue;
import com.ksoft.base.service.manager.util.JsonPluginsUtil;
import com.ksoft.base.service.manager.ws.cxf.parse.ParseService;
import com.ksoft.base.service.manager.ws.model.SoapBindingInfo;
import com.ksoft.base.service.manager.ws.model.SoapBindingOperationInfo;
import com.ksoft.base.service.manager.ws.model.SoapParamInfo;
import com.ksoft.base.service.manager.ws.model.SoapService;
import com.ksoft.base.service.manager.ws.model.SoapServiceInfo;

@Service
public class ParseServiceImpl implements ParseService {

	private static final Logger logger = Logger
			.getLogger(ParseServiceImpl.class);

	@Override
	public SoapService parse(Client client) {
		try {
			return this.parseWSDL(client);
		} catch (IllegalAccessException e) {
			throw new WSDLParseException(e);
		} catch (InvocationTargetException e) {
			throw new WSDLParseException(e);
		} catch (NoSuchMethodException e) {
			throw new WSDLParseException(e);
		}
	}

	@Override
	public Client createClient(String wsdlPath) {
		logger.debug("开始解析wsdl:" + wsdlPath);
		URL wsdlURL;
		try {
			wsdlURL = new URL(wsdlPath);
		} catch (MalformedURLException e) {
			throw new WSDLParseException(e);
		}

		JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory
				.newInstance();

		logger.debug(wsdlURL.toExternalForm());
		
		Client client = factory.createClient(wsdlURL.toExternalForm());
		return client;
	}

	@Override
	public SoapService parse(String wsdlPath) {

		try {
			return this.parseWSDL(this.createClient(wsdlPath));
		} catch (IllegalAccessException e) {
			throw new WSDLParseException(e);
		} catch (InvocationTargetException e) {
			throw new WSDLParseException(e);
		} catch (NoSuchMethodException e) {
			throw new WSDLParseException(e);
		}
	}

	private SoapService parseWSDL(Client client) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		org.apache.cxf.service.Service service = client.getEndpoint()
				.getService();

		logger.debug("服务名称：" + service.getName());

		SoapService soapService = new SoapService();

		soapService.setName(service.getName().getLocalPart());
		soapService.setNamespace(service.getName().getNamespaceURI());

		this.builderServiceInfo(service, soapService);

		return soapService;
	}

	private void builderServiceInfo(org.apache.cxf.service.Service service,
			SoapService soapService) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		logger.debug("开始构建服务信息。");

		if (!AssertValue.isNotNullAndNotEmpty(service.getServiceInfos())) {
			logger.debug("服务信息内容为空。");
			return;
		}

		for (ServiceInfo serviceInfo : service.getServiceInfos()) {
			logger.debug("正在处理服务信息：" + serviceInfo.getName());

			SoapServiceInfo soapServiceInfo = new SoapServiceInfo();

			soapServiceInfo.setName(serviceInfo.getName().getLocalPart());
			soapServiceInfo.setNamespace(serviceInfo.getName()
					.getNamespaceURI());

			// 构建服务绑定
			this.builderBindingInfo(soapService, serviceInfo, soapServiceInfo);

			soapService.putSoapServiceInfo(soapServiceInfo);
		}

	}

	private void builderBindingInfo(SoapService soapService,
			ServiceInfo serviceInfo, SoapServiceInfo soapServiceInfo)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		if (!AssertValue.isNotNullAndNotEmpty(serviceInfo.getBindings())) {
			logger.debug("服务的绑定信息为空。");
			return;
		}

		for (BindingInfo bindingInfo : serviceInfo.getBindings()) {
			logger.debug("正在处理服务绑定信息：" + bindingInfo.getName());
			SoapBindingInfo soapBindingInfo = new SoapBindingInfo();

			soapBindingInfo.setName(bindingInfo.getName().getLocalPart());
			soapBindingInfo.setNamespace(bindingInfo.getName()
					.getNamespaceURI());

			this.builderBindingOperationInfo(soapService, bindingInfo,
					soapBindingInfo);

			soapServiceInfo.putSoapBindingInfo(soapBindingInfo);
		}

	}

	private void builderBindingOperationInfo(SoapService soapService,
			BindingInfo bindingInfo, SoapBindingInfo soapBindingInfo)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		if (!AssertValue.isNotNullAndNotEmpty(bindingInfo.getOperations())) {
			logger.debug("服务的绑定的方法信息为空。");
			return;
		}

		for (BindingOperationInfo bindingOperationInfo : bindingInfo
				.getOperations()) {
			logger.debug("正在处理服务绑定方法信息：" + bindingOperationInfo.getName());
			SoapBindingOperationInfo soapBindingOperationInfo = new SoapBindingOperationInfo();

			soapBindingOperationInfo.setName(bindingOperationInfo.getName()
					.getLocalPart());
			soapBindingOperationInfo.setNamespace(bindingOperationInfo
					.getName().getNamespaceURI());

			this.builderParams(bindingOperationInfo, soapBindingOperationInfo);

			soapBindingInfo
					.putSoapBindingOperationInfo(soapBindingOperationInfo);

			// 同时在服务基本put方法
			soapService.putSoapBindingOperationInfo(soapBindingOperationInfo);
		}
	}

	private void builderParams(BindingOperationInfo bindingOperationInfo,
			SoapBindingOperationInfo soapBindingOperationInfo)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		if (AssertValue.isNotNullAndNotEmpty(bindingOperationInfo.getInput()
				.getMessageParts())) {

			for (MessagePartInfo messagePartInfo : bindingOperationInfo
					.getInput().getMessageParts()) {

				logger.debug("正在处理输入参数:" + messagePartInfo.getName()
						+ ",Index:" + messagePartInfo.getIndex() + ",QName:"
						+ messagePartInfo.getTypeQName());

				Map<String, Object> structureInfos = parseFieldType(messagePartInfo
						.getTypeClass());

				logger.debug("JSON:"
						+ JsonPluginsUtil.beanToJson(structureInfos));

				SoapParamInfo soapParamInfo = new SoapParamInfo();

				soapParamInfo.setName(messagePartInfo.getName().getLocalPart());
				soapParamInfo.setNamespace(messagePartInfo.getName()
						.getNamespaceURI());

				soapParamInfo.setIndex(messagePartInfo.getIndex());
				soapParamInfo.setStructureInfos(structureInfos);
				soapParamInfo.setTypeClass(messagePartInfo.getTypeClass());
				soapBindingOperationInfo.putInputParam(soapParamInfo);
			}
		}

		if (AssertValue.isNotNullAndNotEmpty(bindingOperationInfo.getOutput()
				.getMessageParts())) {
			for (MessagePartInfo messagePartInfo : bindingOperationInfo
					.getOutput().getMessageParts()) {
				logger.debug("正在处理输出参数:" + messagePartInfo.getName()
						+ ",Index:" + messagePartInfo.getIndex() + ",QName:"
						+ messagePartInfo.getTypeQName());

				Map<String, Object> structureInfos = parseFieldType(messagePartInfo
						.getTypeClass());

				logger.debug("JSON:"
						+ JsonPluginsUtil.beanToJson(structureInfos));

				SoapParamInfo soapParamInfo = new SoapParamInfo();

				soapParamInfo.setName(messagePartInfo.getName().getLocalPart());
				soapParamInfo.setNamespace(messagePartInfo.getName()
						.getNamespaceURI());

				soapParamInfo.setIndex(messagePartInfo.getIndex());
				soapParamInfo.setStructureInfos(structureInfos);
				soapParamInfo.setTypeClass(messagePartInfo.getTypeClass());
				soapBindingOperationInfo.putOutputParam(soapParamInfo);
			}

		}

	}

	private Map<String, Object> parseFieldType(Object bean)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Map<?, ?> paramsStat = BeanUtilsBean.getInstance().getPropertyUtils()
				.describe(bean);

		Field[] fields = (Field[]) paramsStat.get("declaredFields");

		List<String> javaDataType = new ArrayList<String>();

		javaDataType.add("java.lang.String");
		javaDataType.add("java.lang.Integer");
		javaDataType.add("java.lang.Boolean");
		javaDataType.add("java.lang.Byte");
		javaDataType.add("java.lang.Double");
		javaDataType.add("java.lang.Short");
		javaDataType.add("java.lang.Long");
		javaDataType.add("java.lang.Float");
		javaDataType.add("java.lang.Number");
		javaDataType.add("java.util.Date");
		javaDataType.add("java.math.BigInteger");
		javaDataType.add("java.math.BigDecimal");
		javaDataType.add("short");
		javaDataType.add("int");

		Map<String, Object> beanFields = new HashMap<String, Object>();

		if (AssertValue.isNotNullAndNotEmpty(fields)) {
			for (int i = 0; i < fields.length; i++) {
				logger.debug("类属性,Name:" + fields[i].getName() + ",Type:"
						+ fields[i].getType().getCanonicalName()
						+ ",GenericType:" + fields[i].getGenericType());

				// 检查是否为java一般类型
				if (javaDataType.contains(fields[i].getType()
						.getCanonicalName())) {
					logger.debug(fields[i].getType().getCanonicalName()
							+ "，为一般类型");
					beanFields.put(fields[i].getName(), fields[i].getType()
							.getCanonicalName());
				} else {
					beanFields.put(fields[i].getName(),
							this.parseFieldType(fields[i].getType()));
				}

			}
		}

		return beanFields;
	}

}
