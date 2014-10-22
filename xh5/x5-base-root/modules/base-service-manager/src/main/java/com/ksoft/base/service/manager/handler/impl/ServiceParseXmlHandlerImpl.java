package com.ksoft.base.service.manager.handler.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.xmlbeans.XmlException;
import org.springframework.stereotype.Service;


import com.ksoft.base.service.manager.util.AssertValue;
import com.ksoft.base.service.manager.exception.ServiceManagerException;
import com.ksoft.base.service.manager.handler.ServiceParseXmlHandler;
import com.ksoft.base.service.manager.model.HttpServiceBean;
import com.ksoft.base.service.manager.model.ServiceGroupBean;
import com.ksoft.base.service.manager.model.WebServiceBean;
import com.ksoft.base.service.manager.session.ServiceSession;
import com.ksoft.base.service.manager.session.impl.ServiceSessionImpl;
import com.ksoft.base.service.manager.util.IoUtil;
import com.ksoft.service.manager.config.Httpservice;
import com.ksoft.service.manager.config.Httpservices;
import com.ksoft.service.manager.config.Servicecfg;
import com.ksoft.service.manager.config.ServicecfgDocument;
import com.ksoft.service.manager.config.Servicegroup;
import com.ksoft.service.manager.config.Servicegroups;
import com.ksoft.service.manager.config.Webservice;
import com.ksoft.service.manager.config.Webservices;

@Service
public class ServiceParseXmlHandlerImpl implements ServiceParseXmlHandler {
	
	private List<ServiceGroupBean> groupList = new ArrayList<ServiceGroupBean>();
	
	public ServiceSession parse(String resource)
	{
		Servicecfg config = this.getServicecfg(resource);
		this.getServiceGroup(config);
		ServiceSession session = new  ServiceSessionImpl();
		session.setHttpServiceBeans(this.buildHttpService(config));
		session.setWebServiceBeans(this.buildWebservice(config));
		return session;
	}

	private List<ServiceGroupBean> getServiceGroup(Servicecfg config) {
		
		Servicegroups serviceGroupList = config.getServicegroups();
		if(AssertValue.isNotNull(serviceGroupList))
		{
			Servicegroup[] groups = serviceGroupList.getServicegroupArray() ;
			for(int i=0;i<groups.length;i++)
			{
				groupList.add(this.buildServiceGroup(groups[i]));
			}
		}
		return groupList;
		 
	}
	

	private List<HttpServiceBean> buildHttpService(Servicecfg config) {
		
		List<HttpServiceBean> serviceList = new ArrayList<HttpServiceBean>();

		Httpservices httpServiceList =  config.getHttpservices();
		if(AssertValue.isNotNull(httpServiceList))
		{
			Httpservice[] services = httpServiceList.getHttpserviceArray();
			for(int i=0;i<services.length;i++)
			{
				HttpServiceBean service = this.buildHttpServiceBean(services[i]);
				if(AssertValue.isNotNull(service))
				   serviceList.add(service);
			}
		}
		
		return serviceList;
	}

	private List<WebServiceBean> buildWebservice(Servicecfg config) {

		List<WebServiceBean> serviceList = new ArrayList<WebServiceBean>();

		Webservices webServices =  config.getWebservices();
		if(AssertValue.isNotNull(webServices))
		{
			Webservice[] services = webServices.getWebserviceArray();
			for(int i=0;i<services.length;i++)
			{
				WebServiceBean service =  this.buildWebServiceBean(services[i]);
				if(AssertValue.isNotNull(service))
					serviceList.add(service);
			}
		}
		return serviceList;
	}
	
	
	private Servicecfg getServicecfg(String resource)
	{
		byte[] serviceByte;
		try {
			serviceByte = IoUtil.readBytesForClassPath(resource);
			InputStream inStream = new ByteArrayInputStream(serviceByte);
			ServicecfgDocument document = ServicecfgDocument.Factory.parse(inStream);
			return document.getServicecfg();
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServiceManagerException(resource +",文件加载失败。");
		} catch (XmlException e) {
			e.printStackTrace();
			throw new ServiceManagerException(resource +",文件解析失败。");
		}
	}
	
	
	private ServiceGroupBean buildServiceGroup(Servicegroup group)
	{
		ServiceGroupBean bean = new ServiceGroupBean();
		bean.setCreateby(group.getCreateby());
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if(AssertValue.isNotNullAndNotEmpty(group.getCreatedate()))
		{
			try {
				bean.setCreatedate(sf.parse(group.getCreatedate()));
			} catch (ParseException e) {
				e.printStackTrace();
				bean.setCreatedate(new Date());
			}
		}
		bean.setDescription(group.getDescription());
		bean.setDisabled(group.getDisabled());
		bean.setGroupno(group.getGroupno());
		bean.setHostname(group.getHostname());
		bean.setPort(group.getPort());
		bean.setRemark(group.getRemark());
		bean.setSn(group.getSn());
		bean.setUpdateby(group.getUpdateby());
		
		if(AssertValue.isNotNullAndNotEmpty(group.getUpdatedate()))
		{
			try {
				bean.setUpdatedate(sf.parse(group.getUpdatedate()));
			} catch (ParseException e) {
				e.printStackTrace();
				bean.setUpdatedate(new Date());
			}
		}
		return bean;
	}

	
	private HttpServiceBean buildHttpServiceBean(Httpservice httpService)
	{
		HttpServiceBean bean = new HttpServiceBean();
		bean.setCategory(httpService.getCategory());
		bean.setCreateby(httpService.getCreateby());
		bean.setDescription(httpService.getDescription());
		bean.setDisabled(httpService.getDisabled());
		bean.setServicename(httpService.getServicename());
		bean.setServicetype(httpService.getServicetype());
		bean.setSn(httpService.getSn());
		bean.setUrl(httpService.getUrl());
		ServiceGroupBean group = this.getServiceGroup(httpService.getGroupno());
		if(!AssertValue.isNotNull(group))
			return null;
		bean.setServiceGroupBean(group);
		return bean;
	}
	
	private ServiceGroupBean getServiceGroup(String groupNo)
	{
		if(!AssertValue.isNotNullAndNotEmpty(this.groupList))
		{
			return null;
		}
		for(ServiceGroupBean group :this.groupList )
		{
			if(groupNo.equals(group.getGroupno()))
				return group;
		}
		return null;
	}
	
	private WebServiceBean buildWebServiceBean(Webservice webService)
	{
		WebServiceBean bean = new WebServiceBean();
		bean.setCategory(webService.getCategory());
		bean.setCreateby(webService.getCreateby());
		bean.setDescription(webService.getDescription());
		bean.setDisabled(webService.getDisabled());
		bean.setServicename(webService.getServicename());
		bean.setServicetype(webService.getServicetype());
		bean.setSn(webService.getSn());
		bean.setUrl(webService.getUrl());
		bean.setNamespace(webService.getNamespace());
		bean.setOutputtype(webService.getOutputparam());
		
		ServiceGroupBean group = this.getServiceGroup(webService.getGroupno());
		if(!AssertValue.isNotNull(group))
			return null;
		bean.setServiceGroupBean(group);
		
		return bean;
	}
}
