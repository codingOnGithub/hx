package com.hotent.platform.dao.bpm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


import com.hotent.platform.model.bpm.BpmNodeRule;
import com.hotent.platform.model.bpm.BpmNodeSign;
import com.hotent.platform.service.bpm.BpmNodeRuleService;


public class BpmNodeRuleDaoTest {
	
	@Resource
	private BpmNodeRuleDao dao;
	@Resource
	BpmNodeRuleService bpmNodeRuleService;
	
	@Test
	public void test(){
		
		Map<String,String> rule1=new HashMap<String, String>();
		rule1.put("name", "zyg");
		rule1.put("address", "gz");
		
	
		
		String str=JSONObject.fromObject(rule1).toString();
		System.out.println(str);
	}
	
	
	public void getJson()
	{
		BpmNodeRule model=dao.getById(1324344234801L);
	
		String str=JSONObject.fromObject(model).toString();
		
		System.out.println(str);
		
	}
	
	public void getJsonList(){
		List<BpmNodeRule> ruleList= bpmNodeRuleService.getByDefIdNodeId("ArchIssueFlow:1:8904", "task1");
		
		String str=JSONObject.fromObject(ruleList).toString();
		System.out.println(str);
	}
	
	
}
