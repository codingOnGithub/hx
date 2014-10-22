package com.ksoft.base.service.manager.ws.cxf;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ksoft.base.service.manager.ws.WebServiceClient;
import com.ksoft.base.service.manager.ws.cxf.invok.InvokService;
import com.ksoft.base.service.manager.ws.cxf.parse.ParseService;
import com.ksoft.base.service.manager.ws.model.SoapInvokCmd;
import com.ksoft.base.service.manager.ws.model.SoapService;

@Service
public class CxfDynamicWebServiceClient implements WebServiceClient {

	@Resource
	private ParseService parseService;

	@Resource
	private InvokService invokService;

	@Override
	public SoapService parse(String wsdlPath) {
		return parseService.parse(wsdlPath);
	}

	@Override
	public Object[] invok(SoapInvokCmd soapInvokCmd) {
		return invokService.invok(soapInvokCmd);
	}

}
