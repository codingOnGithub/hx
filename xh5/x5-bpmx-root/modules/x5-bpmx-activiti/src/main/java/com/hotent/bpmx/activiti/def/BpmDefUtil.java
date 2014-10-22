package com.hotent.bpmx.activiti.def;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.transaction.TransactionException;

import com.hotent.base.core.xml.Dom4jUtil;

public class BpmDefUtil {
	
	/**
	 * 将通过设计器设计的流程定义xml添加监听器设置。
	 * @param id		流程定义ID
	 * @param name		流程定义名称
	 * @param xml		流程定义xml。
	 * @return			转化过的xml。
	 * @throws Exception
	 */
	public static String transBpmDef(String id,String name, String xml) {
		try{
			ClassLoader  loader  =  Thread.currentThread().getContextClassLoader();
			
			String xslUrl=loader.getResource("com/hotent/bpmx/activiti/xml/transdef.xsl").getPath();
			
			Map<String, String> map =new HashMap<String, String>();
			map.put("id", id);
			map.put("name", name);
			String result= Dom4jUtil.transXmlByXslt(xml, xslUrl, map);
			return result;
		}
		catch(Exception ex){
			throw new TransactionException("转换流程定义出错", ex);
		}
		
		
	}

}
