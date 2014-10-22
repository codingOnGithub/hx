package com.ksoft.base.service.manager.context.factory.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ksoft.base.service.manager.model.ServiceBean;
import com.ksoft.base.service.manager.model.ServiceGroupBean;
import com.ksoft.base.service.manager.model.WebServiceBean;
import com.ksoft.base.service.manager.util.JsonPluginsUtil;
import com.ksoft.base.service.manager.ws.WebServiceClient;
import com.ksoft.base.service.manager.ws.model.SoapInvokCmd;

@Service
public class WebServiceManagerFactory extends AbstractServiceManagerFactory{

	
	@Resource
	private WebServiceClient webServiceClient;
	
	@Override
	public String getServiceType() {
		return "ws";
	}
	
	private static final Logger logger = Logger.getLogger(WebServiceManagerFactory.class);

	@Override
	public String invokeService(ServiceBean serviceBaen,Map<String, Object> params) {
		
		WebServiceBean service = (WebServiceBean)serviceBaen;
		ServiceGroupBean serviceGroupBean = serviceBaen.getServiceGroupBean();
		String url = this.buildUrl(serviceGroupBean.getHostname(), serviceGroupBean.getPort(), service.getUrl());
		
		logger.debug("本次调用的服务信息 , 服务地址:" +url +", 服务名称 :"+ service.getServicename() );

		SoapInvokCmd soapInvok = new SoapInvokCmd(url,service.getServicename(),service.getNamespace());
	    logger.debug("operatorName : "+ service.getServicename() +", operatorNamespace : "+service.getNamespace());
	    
	    String json = JsonPluginsUtil.beanToJson(params);
	    
	    logger.debug("json param ;"+ json);
	    soapInvok.setJsonParam(json);
	    
	    Object[] reuqstResul = webServiceClient.invok(soapInvok);
	     
	    return this.buildWSInvokeResult(service.getOutputtype(), reuqstResul[0]);
	    
	}


	 
	 private String buildWSInvokeResult(String outputparams , Object result)
	 {
		 if(outputparams.equals("List"))
		 {
			 List list = (List)result;
			 return JsonPluginsUtil.listToJson(list);
		 }else if(outputparams.equals("Map"))
		 {
			 Map map = (Map)result;
			 return JsonPluginsUtil.mapToJson(map,null,true);
			 
		 }else if(outputparams.equals("Bean"))
		 {
			 return JsonPluginsUtil.beanToJson(result);
		 }else
		 {
			return result.toString();
		 }
	 }
	 
	
}
