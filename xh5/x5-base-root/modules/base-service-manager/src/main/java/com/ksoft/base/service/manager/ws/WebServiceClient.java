package com.ksoft.base.service.manager.ws;

import com.ksoft.base.service.manager.ws.model.SoapInvokCmd;
import com.ksoft.base.service.manager.ws.model.SoapService;

public interface WebServiceClient {
	public SoapService parse(String wsdlPath);

	public Object[] invok(SoapInvokCmd soapInvokCmd);
}
