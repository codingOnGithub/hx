package com.ksoft.base.service.manager.http.factory;

import org.apache.commons.httpclient.NameValuePair;

public interface HttpFactory {
	
public String getCallHttpRequest(String requestUrl);
	
	public String postCallHttpRequest(String requestUrl,NameValuePair[] paramData);
	
	public String postCallHttpRequestByJson(String requestUrl,String paramJson);

}
