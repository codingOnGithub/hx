package com.ksoft.base.service.manager.session;

import java.util.List;

import com.ksoft.base.service.manager.model.HttpServiceBean;
import com.ksoft.base.service.manager.model.ServiceBean;
import com.ksoft.base.service.manager.model.WebServiceBean;

public interface ServiceSession {

	public List<WebServiceBean> getWebServiceBeans() ;

	public void setWebServiceBeans(List<WebServiceBean> webServiceBeans);

	public List<HttpServiceBean> getHttpServiceBeans() ;

	public void setHttpServiceBeans(List<HttpServiceBean> httpServiceBeans) ;
	
	public ServiceBean getServiceBean(String serviceName,String serviceGroup);
	
	public void putWebServiceBean(WebServiceBean webServiceBean);
	
	public void putHttpServiceBean(HttpServiceBean httpServiceBean);
	
}
