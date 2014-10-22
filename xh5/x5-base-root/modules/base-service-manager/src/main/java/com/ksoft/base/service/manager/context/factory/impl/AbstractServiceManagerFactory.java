package com.ksoft.base.service.manager.context.factory.impl;



import org.springframework.stereotype.Service;

import com.ksoft.base.service.manager.context.factory.ServiceManagerFactory;

@Service
public abstract class AbstractServiceManagerFactory implements ServiceManagerFactory{
	
	
	public String buildUrl(String hostname, long port,String url) {
			String path = hostname;
	    	if(port>0)
	    		path += ":"+port;
    		path += url;
	    	return path;
	 }

}
