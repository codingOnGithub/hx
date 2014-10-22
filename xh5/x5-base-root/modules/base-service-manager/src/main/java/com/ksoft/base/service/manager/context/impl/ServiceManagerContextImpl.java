package com.ksoft.base.service.manager.context.impl;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ksoft.base.service.manager.util.AssertValue;
import com.ksoft.base.service.manager.context.ServiceManagerContext;
import com.ksoft.base.service.manager.context.factory.ServiceManagerFactory;
import com.ksoft.base.service.manager.exception.ServiceManagerException;
import com.ksoft.base.service.manager.handler.ServiceParseXmlHandler;
import com.ksoft.base.service.manager.model.ServiceBean;
import com.ksoft.base.service.manager.session.ServiceSession;
import com.ksoft.base.service.manager.ws.WebServiceClient;
import com.ksoft.base.service.manager.ws.model.SoapService;

@Service
public class ServiceManagerContextImpl implements ServiceManagerContext {
 
	
	@Resource
	private List<ServiceManagerFactory> serviceManagerFactorys;
	
	@Resource
	private ServiceParseXmlHandler serviceParseXmlHandler;
	
	@Resource
	private WebServiceClient webServiceClient;
	
	
	
	private static final Logger logger = Logger.getLogger(ServiceManagerContextImpl.class);

	
	public String invokeService(ServiceBean serviceBean,Map<String, Object> params)
	{
		
		if(!AssertValue.isNotNull(serviceBean))
		{
			throw new ServiceManagerException("没有对应的服务部署。");
		}
		ServiceManagerFactory factory = this.findServiceManagerFactory(serviceBean.getServicetype());
		
		return factory.invokeService(serviceBean, params);
		
	}
	
	private ServiceManagerFactory findServiceManagerFactory(String serviceType)
	{
		if(AssertValue.isNotNullAndNotEmpty(this.serviceManagerFactorys))
		{
			for(ServiceManagerFactory serviceFactory : this.serviceManagerFactorys)
			{
				if(serviceFactory.getServiceType().equals(serviceType))
				{
					logger.debug("正在比对服务工厂：" + serviceFactory.getServiceType()
							+ ",是否符合当前查找的服务工厂:" + serviceType);
					return serviceFactory;
				}
			}
		}
		return null;
	}
	
	
	public ServiceSession parse(String resource)
	{
		return serviceParseXmlHandler.parse(resource);
	}
	
	
	public SoapService wsdlParse(String wsdlPath)
	{
		return webServiceClient.parse(wsdlPath);
	}

}
