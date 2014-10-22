package com.ksoft.base.service.manager.session.impl;

import java.util.ArrayList;
import java.util.List;

import com.ksoft.base.service.manager.model.HttpServiceBean;
import com.ksoft.base.service.manager.model.ServiceBean;
import com.ksoft.base.service.manager.model.WebServiceBean;
import com.ksoft.base.service.manager.session.ServiceSession;
import com.ksoft.base.service.manager.util.AssertValue;

public class ServiceSessionImpl implements ServiceSession{
	
	private List<WebServiceBean> webServiceBeans;
	private List<HttpServiceBean> httpServiceBeans;

	public List<WebServiceBean> getWebServiceBeans() {
		return webServiceBeans;
	}

	public void setWebServiceBeans(List<WebServiceBean> webServiceBeans) {
		this.webServiceBeans = webServiceBeans;
	}

	public List<HttpServiceBean> getHttpServiceBeans() {
		return httpServiceBeans;
	}

	public void setHttpServiceBeans(List<HttpServiceBean> httpServiceBeans) {
		this.httpServiceBeans = httpServiceBeans;
	}

	public ServiceBean getServiceBean(String serviceName, String serviceGroup) {
		
		if(!AssertValue.isNotNullAndNotEmpty(httpServiceBeans))
			return null;
		for(HttpServiceBean bean : httpServiceBeans)
		{
			if(bean.getServicename().equals(serviceName) && bean.getServiceGroupBean().getGroupno().equals(serviceGroup))
				return bean;
		}
		
		if(!AssertValue.isNotNullAndNotEmpty(webServiceBeans))
			return null;
		
		for(WebServiceBean bean : webServiceBeans)
		{
			if(bean.getServicename().equals(serviceName) && bean.getServiceGroupBean().getGroupno().equals(serviceGroup))
				return bean;
		}
		
		return null;
	}
	
	
	public void putWebServiceBean(WebServiceBean webServiceBean) {
		
		if(!AssertValue.isNotNullAndNotEmpty(this.webServiceBeans))
			this.webServiceBeans = new ArrayList<WebServiceBean>();
		this.webServiceBeans.add(webServiceBean);
	}

	public void putHttpServiceBean(HttpServiceBean httpServiceBean) {
		if(!AssertValue.isNotNullAndNotEmpty(this.httpServiceBeans))
			this.httpServiceBeans = new ArrayList<HttpServiceBean>();
		this.httpServiceBeans.add(httpServiceBean);
	}

}
