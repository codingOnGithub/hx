package com.hotent.bo.persistence.model.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import com.hotent.base.core.util.BeanUtils;
import com.hotent.bo.exception.BOBaseException;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

public class JSONDataObject extends AbstractDataObject {
	private String json = "{}";
	
	public void setInstData(String instData)
	{
		if(BeanUtils.isEmpty(instData))return;
		this.json = instData;
	}
	/**
	 * 返回 实例数据
	 * @return
	 */
	public String getInstData() 
	{
		return this.json;
	}
	
	public Object get(String key){
		return JsonPath.read(this.json,handlerPath(key));
	}
	
	/**
	 * 将路径中首节字符串替换为$
	 * @param path
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	private String handlerPath(String path){
		Pattern regex = Pattern.compile("^(\\w+)\\..*");
		Matcher regexMatcher = regex.matcher(path);
		if (regexMatcher.find()) {
			String pathFirstNode = regexMatcher.group(1);
			if(BeanUtils.isEmpty(this.boDef) || pathFirstNode.equals(this.boDef.getName())){
				return path.replaceFirst(pathFirstNode, "\\$");
			}
		}
		return path;
	}
	
	private Object toJSON(String str){
		try{
			return new JSONParser(-1).parse(str);
		}
		catch (Exception e){
			throw new BOBaseException("传入的字符串数据不是标准的JSON格式");
		}
	}
	
	private void setJSONKeyValue(JSONObject jobject,String key,Object val){
		Object valObj = val;
		if(val instanceof String){
			valObj = toJSON(val.toString());
		}
		if(val instanceof List){
			List<Object> list = (List<Object>)val;
			JSONArray jarry = new JSONArray();
			for(Object obj:list){
				Object jo = obj;
				if(obj instanceof String){
					jo = toJSON(obj.toString());
				}
				jarry.add(jo);
			}
			valObj = jarry;
		}
		Pattern regex = Pattern.compile("^(\\w+)\\[(.*)\\]$");
		Matcher regexMatcher = regex.matcher(key);
		if (regexMatcher.find()) {
			String newKey = regexMatcher.group(1);
			String indexStr =  regexMatcher.group(2);
			Object obj = JsonPath.read(jobject,"$." + key);
			if(obj instanceof JSONArray){
				jobject.put(newKey, valObj);
			}
			else{
				JSONArray jarray = (JSONArray)JsonPath.read(jobject,"$." + newKey + "[*]");
				int index = Integer.parseInt(indexStr);
				jarray.set(index, valObj);
			}
		}
		else{
			jobject.put(key, valObj);
		}
	}

	public void set(String key,Object val){
		ReadContext readContext = JsonPath.parse(this.json);
		JSONObject jsonObject = (JSONObject)readContext.json();
		String path = handlerPath(key);
		String code = "";
		Pattern regex = Pattern.compile("^(\\$.*)\\.(.*)$");
		Matcher regexMatcher = regex.matcher(path);
		while (regexMatcher.find()) {
			path = regexMatcher.group(1);
			code =  regexMatcher.group(2);
		}
		Object obj = JsonPath.read(jsonObject,path);
		if(obj instanceof JSONArray){
			JSONArray jarray = (JSONArray)obj;
			for(Object o : jarray){
				JSONObject jobject = (JSONObject)o;
				setJSONKeyValue(jobject, code, val);
			}
		}
		else if(obj instanceof JSONObject){
			JSONObject jobject = (JSONObject)obj;
			setJSONKeyValue(jobject, code, val);
		}
		this.json = jsonObject.toJSONString();
	}

	@Override
	public String getDataType() {
		return "json";
	}
}
