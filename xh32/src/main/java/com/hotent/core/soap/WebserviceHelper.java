package com.hotent.core.soap;

import java.io.StringWriter;
import java.util.Map;

import javax.xml.soap.SOAPMessage;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import com.hotent.core.util.AppUtil;
import com.hotent.platform.model.bpm.BpmCommonWsSet;
import com.hotent.platform.service.bpm.BpmCommonWsSetService;


public class WebserviceHelper {
	private static BpmCommonWsSetService bpmCommonWsSetService;
	private static Logger logger = LoggerFactory.getLogger(WebserviceHelper.class);
	
	private static BpmCommonWsSet getSet(String alias){
		if(bpmCommonWsSetService==null){
			bpmCommonWsSetService = (BpmCommonWsSetService)AppUtil.getBean(BpmCommonWsSetService.class);
		}
		return bpmCommonWsSetService.getByAlias(alias);
	}
	
	
	private static SOAPMessage execute(String alias,Map<String,Object> map) throws Exception{
		BpmCommonWsSet bpmCommonWsSet = getSet(alias);
		if(bpmCommonWsSet==null)return null;
		String document = bpmCommonWsSet.getDocument();
		JSONObject jObject = JSONObject.fromObject(document);
		return SoapUtil.execute(map, jObject);
	}
	
	/**
	 * 通过通用webservice设置的别名调用对应的服务
	 * @param alias webservice设置别名
	 * @param map 调用方法的入参
	 * @return SOAPMessage格式的返回值
	 * @throws Exception
	 */
	public static SOAPMessage executeObj(String alias,Map<String,Object> map) throws Exception{
		return execute(alias, map);
	}
	
	/**
	 * 通过通用webservice设置的别名调用对应的服务
	 * @param alias webservice设置别名
	 * @param map 调用方法的入参
	 * @return xml格式的返回值
	 * @throws Exception
	 */
	public static String executeXml(String alias,Map<String,Object> map) throws Exception{
		SOAPMessage responseMessage = execute(alias, map);
		Document doc = responseMessage.getSOAPPart().getEnvelope().getOwnerDocument();  
		StringWriter output = new StringWriter();  
		TransformerFactory.newInstance().newTransformer().transform( new DOMSource(doc), new StreamResult(output));  
		return output.toString();
	}
}
