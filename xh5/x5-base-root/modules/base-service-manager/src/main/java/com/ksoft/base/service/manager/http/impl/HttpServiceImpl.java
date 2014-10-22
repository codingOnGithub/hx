package com.ksoft.base.service.manager.http.impl;

import javax.annotation.Resource;

import org.apache.commons.httpclient.NameValuePair;
import org.springframework.stereotype.Service;

import com.ksoft.base.service.manager.http.HttpService;
import com.ksoft.base.service.manager.http.factory.HttpFactory;

@Service
public class HttpServiceImpl implements HttpService{

	@Resource
	private HttpFactory httpFactory;
	
	@Override
	public String getCallHttpRequest(String requestUrl) {
		return httpFactory.getCallHttpRequest(requestUrl);
	}

	@Override
	public String postCallHttpRequest(String requestUrl,
			NameValuePair[] paramData) {
		return httpFactory.postCallHttpRequest(requestUrl, paramData);
	}

	@Override
	public String postCallHttpRequestByJson(String requestUrl, String paramJson) {
		return httpFactory.postCallHttpRequestByJson(requestUrl, paramJson);
	}

}
