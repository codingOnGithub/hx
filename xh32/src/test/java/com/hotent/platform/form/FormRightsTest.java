package com.hotent.platform.form;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;



public class FormRightsTest  {
	
	public void testJson(){
		String jsonStr="{'field':[" +
	"{'title':'orderId','memo':'订单ID','read':{'type':'everyone','id':'', 'fullname':''},'write':{'type':'everyone','id':'', 'fullname':''}}," +
	"{'title':'orderName','memo':'订单名','read':{'type':'everyone','id':'', 'fullname':''},'write':{'type':'everyone','id':'', 'fullname':''}}," +
	"{'title':'customer','memo':'客户','read':{'type':'everyone','id':'', 'fullname':''},'write':{'type':'everyone','id':'', 'fullname':''}}" +
	
	"]}";
		JSONObject jsonObj=JSONObject.fromObject(jsonStr);
		JSONArray aryJson=jsonObj.getJSONArray("field");
		for(Object obj:aryJson){
			JSONObject json=(JSONObject)obj;
			
			System.out.println(json.get("title"));
		}
		System.out.println(aryJson.size());
	}

	public void testStringContain(){
		String all="zyg,zhangyg";
		System.out.println(all.contains("zyg1"));
	}

}
