package com.hotent.bo.valid.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minidev.json.parser.JSONParser;
import org.springframework.stereotype.Repository;
import com.hotent.bo.exception.BOBaseException;
import com.hotent.bo.exception.RuleValidFailedException;
import com.hotent.bo.persistence.model.BOAttribute;
import com.hotent.bo.persistence.model.BORule;
import com.hotent.bo.persistence.model.BORule.BORULE_TYPE;
import com.hotent.bo.valid.BOValid;

@Repository
public class DefaultBOValid implements BOValid{
	@Override
	public Boolean valid(BORule rule, Object data) {
		if(data instanceof String){
			if(valid(rule, data.toString())){
				return true;
			}
			else{
				throw new RuleValidFailedException("传入的数据:"+data.toString()+",未能通过规则验证:" + rule.getTipInfo());
			}
		}
		if(data instanceof List){
			List<String> list = (List<String>)data;
			for(String str : list){
				if(!valid(rule, str)){
					throw new RuleValidFailedException("传入的数据:" + str + ",未能通过规则验证:" + rule.getTipInfo());
				}
			}
			return true;
		}
		return true;
	}
	
	/**
	 * 将JSON格式的字符串转换为列表
	 * @param str
	 * @return 
	 * List<String>
	 * @exception 
	 * @since  1.0.0
	 */
	private List<String> string2List(String str){
		try{
			Object obj = new JSONParser(-1).parse(str); 
			return (List<String>)obj;
		}
		catch (Exception e){
			throw new BOBaseException("传入的字符串数据不是标准的JSON格式");
		}
	}
	
	private Boolean valid(BORule rule,String data){
		//正则表达式
		if(BORULE_TYPE.FORMAT.equals(rule.getRuleType())){
			Pattern regex = Pattern.compile(rule.getContent());
			Matcher regexMatcher = regex.matcher(data);
			return regexMatcher.find();
		}
		//排除
		else if(BORULE_TYPE.EXCLUSION.equals(rule.getRuleType())){
			List<String> list = string2List(rule.getContent());
			return !list.contains(data);
		}
		//包含
		else if(BORULE_TYPE.INCLUSION.equals(rule.getRuleType())){
			List<String> list = string2List(rule.getContent());
			return list.contains(data);
		}
		return true;
	}

	@Override
	public Boolean valid(BOAttribute attribute, Object data) {
		List<BORule> rules = attribute.getAttRuleList();
		for(BORule rule : rules){
			if(!valid(rule,data))return false;
		}
		return true;
	}
}
