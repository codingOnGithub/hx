package com.ksoft.base.service.manager.http;

import org.apache.commons.httpclient.NameValuePair;

public interface HttpService {
	
	public String getCallHttpRequest(String requestUrl);
	
	public String postCallHttpRequest(String requestUrl,NameValuePair[] paramData);
	
	public String postCallHttpRequestByJson(String requestUrl,String paramJson);

}
