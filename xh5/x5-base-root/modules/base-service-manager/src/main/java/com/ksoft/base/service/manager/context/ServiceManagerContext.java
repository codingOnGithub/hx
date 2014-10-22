package com.ksoft.base.service.manager.context;

import java.util.Map;

import com.ksoft.base.service.manager.model.ServiceBean;
import com.ksoft.base.service.manager.session.ServiceSession;
import com.ksoft.base.service.manager.ws.model.SoapService;

public interface ServiceManagerContext {
	
	public String invokeService(ServiceBean serviceBean,Map<String, Object> params);
	
	public ServiceSession parse(String resource);
	
	public SoapService wsdlParse(String wsdlPath);
	
}
