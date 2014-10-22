package com.ksoft.base.service.manager.ws.cxf.parse;

import org.apache.cxf.endpoint.Client;

import com.ksoft.base.service.manager.ws.model.SoapService;

public interface ParseService {
	public SoapService parse(String wsdlPath);

	public SoapService parse(Client client);

	public Client createClient(String wsdlPath);

}
