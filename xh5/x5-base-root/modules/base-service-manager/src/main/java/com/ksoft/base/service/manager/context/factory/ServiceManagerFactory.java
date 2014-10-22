package com.ksoft.base.service.manager.context.factory;

import java.util.Map;

import com.ksoft.base.service.manager.model.ServiceBean;

public interface ServiceManagerFactory {
	
	public String getServiceType();
	
	public String invokeService(ServiceBean serviceBaen,Map<String, Object> params);
	
}
