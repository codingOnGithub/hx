package com.ksoft.base.service.manager.ws.cxf.invok.impl;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;
import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.springframework.stereotype.Service;

import com.ksoft.base.service.manager.exception.SoapFireException;
import com.ksoft.base.service.manager.util.AssertValue;
import com.ksoft.base.service.manager.util.JsonPluginsUtil;
import com.ksoft.base.service.manager.ws.cxf.invok.InvokService;
import com.ksoft.base.service.manager.ws.cxf.parse.ParseService;
import com.ksoft.base.service.manager.ws.model.SoapBindingOperationInfo;
import com.ksoft.base.service.manager.ws.model.SoapInvokCmd;
import com.ksoft.base.service.manager.ws.model.SoapParamInfo;
import com.ksoft.base.service.manager.ws.model.SoapService;

@Service
public class InvokServiceImpl implements InvokService {

	@Resource
	private ParseService parseService;

	@Override
	public Object[] invok(SoapInvokCmd soapInvokCmd) {
		try {
			return this.fireOperation(soapInvokCmd);
		} catch (IllegalAccessException e) {
			throw new SoapFireException(e);
		} catch (InvocationTargetException e) {
			throw new SoapFireException(e);
		} catch (NoSuchMethodException e) {
			throw new SoapFireException(e);
		}

	}

	private Object[] fireOperation(SoapInvokCmd soapInvokCmd)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		Client client = parseService.createClient(soapInvokCmd.getWsdlPath());

		SoapService soapService = parseService.parse(client);

		SoapBindingOperationInfo sboi = soapService
				.getSoapBindingOperationInfo(soapInvokCmd.getOperatorName(),
						soapInvokCmd.getOperatorNamespace());

		if (!AssertValue.isNotNull(sboi)) {
			throw new SoapFireException("方法{"
					+ soapInvokCmd.getOperatorNamespace() + "}"
					+ soapInvokCmd.getOperatorName() + ",没有找到。");
		}

		// 构建参数
		SoapParamInfo inputParamInfo = sboi.getInputParams().get(0);

		Object inputparams = JsonPluginsUtil.jsonToBean(
				soapInvokCmd.getJsonParam(), inputParamInfo.getTypeClass());

		// Map<?, ?> paramsStat = BeanUtilsBean.getInstance().getPropertyUtils()
		// .describe(inputParamInfo.getTypeClass());
		//
		// Field[] fields = (Field[]) paramsStat.get("declaredFields");

		// Object inputparams = JsonPluginsUtil.jsonToBean(soapInvokCmd
		// .getParams().entrySet().iterator().next().getValue(),
		// fields[0].getType());

		// Object inputparams = soapInvokCmd.getParams().entrySet().iterator()
		// .next().getValue();

		try {
			Object[] returns = client
					.invokeWrapped(
							new QName(sboi.getNamespace(), sboi.getName()),
							inputparams);

			return returns;

		} catch (Exception e) {
			throw new SoapFireException(e);
		}
	}
}
