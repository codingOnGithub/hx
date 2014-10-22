package com.ksoft.base.service.manager.http.factory.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ksoft.base.service.manager.exception.HttpRequestException;
import com.ksoft.base.service.manager.http.factory.HttpFactory;
import com.ksoft.base.service.manager.util.AssertValue;
import com.ksoft.base.service.manager.util.HttpUtil;
import com.ksoft.base.service.manager.util.HttpUtilGetCallBack;
import com.ksoft.base.service.manager.util.HttpUtilPostCallBack;


@Service
public class HttpFactoryImpl implements HttpFactory {
	
	private static final Logger logger = Logger.getLogger(HttpFactoryImpl.class);
	
	private String getCallResponse;
	
	private String postCallResponse;
	
	
	@Override
	public String getCallHttpRequest(String requestUrl) {
		try {
			HttpUtil.getCallHttpRequest(requestUrl, new HttpUtilGetCallBack() {
				
				@Override
				public void success(GetMethod getMethod) throws IOException {
					
					if(getMethod.getStatusCode() != 200)
					{
						throw new HttpRequestException("调用服务出现错误，StatusCode:"
								+ getMethod.getStatusCode()
								+ ",Text:"
								+ getMethod.getStatusText());
					}
					getCallResponse = new String(getMethod.getResponseBody(), "UTF-8");
					
					logger.debug("本次Get请求的结果 :"+getCallResponse);
				}
			});
		} catch (HttpException e) {
			e.printStackTrace();
			throw new HttpRequestException("调用服务失败:"+e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new HttpRequestException("调用服务失败:"+e.getMessage());
		}
		return getCallResponse;
	}

	@Override
	public String postCallHttpRequest(String requestUrl,
			NameValuePair[] paramData) {
		try {
			HttpUtil.postCallHttpRequest(requestUrl, paramData, new HttpUtilPostCallBack() {
				
				@Override
				public void success(PostMethod post) throws IOException {
					if(post.getStatusCode() != 200)
					{
						throw new HttpRequestException("调用服务出现错误，StatusCode:"
								+ post.getStatusCode()
								+ ",Text:"
								+ post.getStatusText());
					}
					postCallResponse = post.getResponseBodyAsString();
					
					logger.debug("本次Post请求的结果 :"+getCallResponse);
				}
			});
		} catch (HttpException e) {
			e.printStackTrace();
			throw new HttpRequestException("调用服务失败:"+e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new HttpRequestException("调用服务失败:"+e.getMessage());
		}
		return postCallResponse;
	}

	@Override
	public String postCallHttpRequestByJson(String requestUrl, String paramJson) {
		try {
			return new String(this.PostRequestByJson(requestUrl,paramJson), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new HttpRequestException("解析Post请求的结果失败。" +e.getMessage());
		}
		
	}

	private byte[] PostRequestByJson(String requestUrl,String json) {
		HttpURLConnection http = null;
		try {
			
			URL url = new URL(requestUrl);
			http = (HttpURLConnection) url.openConnection();

			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒

			http.connect();
			
			OutputStream os = http.getOutputStream();
			os.write(json.getBytes("UTF-8"));
			os.flush();
			os.close();
			
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			is.close();
			return jsonBytes;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new HttpRequestException("通过post方式传递json参数，调用服务失败."+e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new HttpRequestException("通过post方式传递json参数，调用服务失败."+e.getMessage());
		}finally
		{
			if(AssertValue.isNotNull(http))
				http.disconnect();
		}
		 
	}
}
