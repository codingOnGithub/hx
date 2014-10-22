package com.ksoft.base.service.manager.context.factory.impl;

import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ksoft.base.service.manager.http.HttpService;
import com.ksoft.base.service.manager.model.HttpServiceBean;
import com.ksoft.base.service.manager.model.ServiceBean;
import com.ksoft.base.service.manager.model.ServiceGroupBean;


@Service
public class HttpServiceManagerFactory extends AbstractServiceManagerFactory {

	private static final Logger logger = Logger
			.getLogger(HttpServiceManagerFactory.class);

	@Resource
	private HttpService httpService;
	@Override
	public String getServiceType() {
		return "http";
	}

	@Override
	public String invokeService(ServiceBean serviceBaen,Map<String, Object> params) {
		

		HttpServiceBean service = (HttpServiceBean) serviceBaen;
		ServiceGroupBean serviceGroupBean = serviceBaen.getServiceGroupBean();
		String url = this.buildUrl(serviceGroupBean.getHostname(), serviceGroupBean.getPort(),service.getUrl());
		
		logger.debug("本次调用的服务信息 , 服务地址:" +url +", 服务名称 :"+ service.getServicename() );

		//String json = JsonPluginsUtil.mapToJson(params, null, true);
		Iterator<String> iterator = params.keySet().iterator();
		NameValuePair[] nameValuePair = new NameValuePair[params.size()];
		int i =0;
		while(iterator.hasNext())
		{
			
			String key = iterator.next();
			nameValuePair[i] = new NameValuePair(key, params.get(key).toString());
			i++;
		}
		
		//logger.debug("json param ;" + json);
		String result = httpService.postCallHttpRequest(url, nameValuePair);
		logger.debug("result :" + result);
		return result;
	}

}
