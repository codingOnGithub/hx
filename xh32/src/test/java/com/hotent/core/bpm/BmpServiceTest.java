package com.hotent.core.bpm;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.activity.ActivityRequiredException;
import javax.annotation.Resource;
import javax.script.ScriptException;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;

import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.pvm.runtime.ExecutionImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hotent.BaseTestCase;
import com.hotent.core.engine.GroovyScriptEngine;
import org.activiti.engine.TaskService;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.FileUtil;
import com.hotent.platform.service.bpm.BpmService;
import com.hotent.platform.service.bpm.IBpmActService;
//import com.hotent.platform.service.bpm.ISignUser;

public class BmpServiceTest extends BaseTestCase {
	@Autowired
	private RuntimeService runtimeService; 
	   
	@Autowired 
	private TaskService taskService; 
	
	@Autowired 
	private RepositoryService repositoryService;
	
	@Resource
	private BpmService bpmService;
	
	@Resource 
	GroovyScriptEngine scriptEngine;
	
	@Resource
	IBpmActService bpmActService;
	

	
	
	
	//inclusiveGateway 
	//这种类型的网关，可以接多个顺序工作流，每个流可以设置
	
	
	
	public BmpServiceTest() {
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unused")
	
	@Test
	public void deploy() throws IOException{
//		repositoryService.deleteDeployment("1", true);

//		String xml=FileUtil.readFile("E:\\temp\\bpm\\hello.bpm20.xml");
//		//System.out.println(xml);
		String xml=FileUtil.readFile("E:\\temp\\bpm\\hello.bpm20.xml");
		Deployment d= repositoryService.createDeployment().name("helloword").addString("bpmn20.xml", xml).deploy();
		
		System.out.println(d.getId());
//		InputStream is= repositoryService.getResourceAsStream(d.getId(), "helloword.png");
//		FileUtil.writeFile("E:\\temp\\bpm\\hello.png", is);
		
//		BpmUtil.genImageByDepolyId("2801", "E:\\temp\\bpm\\out.png", repositoryService);
//		
//		System.out.println("run complete");
		
	}
	
//	@Test
	public void startHello(){
		HashMap<String, Object> variableMap = new HashMap<String, Object>();
		List<String> list=new ArrayList<String>();
		list.add("hello");
	    variableMap.put("ZHANGYG", list);
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("person1",variableMap);
	}
	
	
	
	public void inclusiveGatewayDeoploy()
	{
		InputStream is= Thread.currentThread().getContextClassLoader().getResourceAsStream("com/hotent/core/bpm/graph/shiporder.bpm20.xml");
		Deployment d= repositoryService.createDeployment().name("inclusiveGateway").addInputStream("bpmn20.xml", is).deploy();

	}
	
	
	

	public void start()
	{
		HashMap<String, Object> variableMap = new HashMap<String, Object>();
      variableMap.put("receivedPayment", true);
      variableMap.put("shipOrder", true);
      ProcessInstance pi = runtimeService.startProcessInstanceByKey("inclusiveGateway",variableMap);
      
      TaskQuery query = taskService.createTaskQuery()
              .processInstanceId(pi.getId())
              .orderByTaskName()
              .asc();

		List<Task> tasks =query.list();
		Assert. assertEquals(1, tasks.size());
		Task task = tasks.get(0);
		taskService.complete(task.getId());
	}
	
	
	
	
	public void start1()
	{
		HashMap<String, Object> variableMap = new HashMap<String, Object>();
      variableMap.put("receivedPayment", false);
      variableMap.put("shipOrder", true);
      ProcessInstance pi = runtimeService.startProcessInstanceByKey("inclusiveGateway",variableMap);
     

	}
	
	
	public void start2()
	{
		HashMap<String, Object> variableMap = new HashMap<String, Object>();
      variableMap.put("receivedPayment", true);
      variableMap.put("shipOrder", false);
      ProcessInstance pi = runtimeService.startProcessInstanceByKey("inclusiveGateway",variableMap);
	}
	
	
	public void start3()
	{
		HashMap<String, Object> variableMap = new HashMap<String, Object>();
      variableMap.put("receivedPayment", true);
      variableMap.put("shipOrder", true);
      ProcessInstance pi = runtimeService.startProcessInstanceByKey("inclusiveGateway",variableMap);
      

	}

	
	public void start4()
	{
	
      ProcessInstance pi = runtimeService.startProcessInstanceByKey("inclusiveGateway");
      

	}
	
	
	
	public void seqDeoploy()
	{
		InputStream is= Thread.currentThread().getContextClassLoader().getResourceAsStream("com/hotent/core/bpm/graph/seq.bpmn20.xml");
		Deployment d= repositoryService.createDeployment().name("seq").addInputStream("bpmn20.xml", is).deploy();

	}
	
	
	public void startSeq(){
		Map<String,Object> vars=new HashMap<String, Object>();
		vars.put("days", 3);
		vars.put("applyuser","老张");
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("seq",vars);
	}
	
	
	public void setTaskId(){
		
		
////		taskService.setVariable("10805", "amount", 9);
////		//taskService.setVariablesLocal(taskId, variables) ("10805", "sign", "pass");
		Map<String,Object> vars=taskService.getVariables("10805");
		Map<String,Object> localvars=taskService.getVariablesLocal("10805");
		System.out.println("ok");
//		String[] aryBeans= AppUtil.getContext().getBeanDefinitionNames();
//		for(String bean :aryBeans)
//		{
//			System.out.println(bean);
//		}
	}
	

	
	
	
	public void multiDeoploy()
	{
		InputStream is= Thread.currentThread().getContextClassLoader().getResourceAsStream("com/hotent/core/bpm/graph/multi.xml");
		Deployment d= repositoryService.createDeployment().name("multi").addInputStream("bpmn20.xml", is).deploy();
	}
	
	
	
	public void startMulti(){
		Map<String, Object> vars=new HashMap<String, Object>();
		List<String> list=new ArrayList<String>();
		list.add("csx");
		list.add("chenlin");
		list.add("fangyj");
		
		vars.put("signList", list);
		
		
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("multi",vars);
		
		System.out.println("ok");
	}

	//@Test
	public void completeSeqTask() throws ActivityRequiredException{

//		22314
//		22318
//		22322
		taskService.complete("22718");
//		taskService.complete("22018");
//		taskService.complete("22022");
		
	}
	
	
	public void multiOutDeoploy()
	{
		InputStream is= Thread.currentThread().getContextClassLoader().getResourceAsStream("com/hotent/core/bpm/graph/multiOut.bpmn20.xml");
		Deployment d= repositoryService.createDeployment().name("MultiOut").addInputStream("bpmn20.xml", is).deploy();
	}
	
	
	public void startMultiOut(){
		
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("Exclusivegateway");
	}
	
	
	public void gatewayDeoploy()
	{
		InputStream is= Thread.currentThread().getContextClassLoader().getResourceAsStream("com/hotent/core/bpm/graph/exclusivegateway.bpmn20.xml");
		Deployment d= repositoryService.createDeployment().name("Exclusivegateway").addInputStream("bpmn20.xml", is).deploy();
	}
	
	
	public void scriptDeoploy()
	{
		InputStream is= Thread.currentThread().getContextClassLoader().getResourceAsStream("com/hotent/core/bpm/graph/script.bpmn20.xml");
		Deployment d= repositoryService.createDeployment().name("Script").addInputStream("bpmn20.xml", is).deploy();
		
	}
	
	
	public void multiConditionDeoploy()
	{
		InputStream is= Thread.currentThread().getContextClassLoader().getResourceAsStream("com/hotent/core/bpm/graph/multiCondition.bpmn20.xml");
		Deployment d= repositoryService.createDeployment().name("MultiCondition").addInputStream("bpmn20.xml", is).deploy();
		
	}
	
	
	public void startMultiCondition()
	{
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("MultiCondition","1990");
	}
	

	public void startScript() throws ScriptException, NoSuchMethodException{
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("name", "zhangyg");
		map.put("address", "天河");
		boolean rtn=scriptEngine.executeBoolean("def i=2 ;return i==2;", map);
//		if(scriptEngine.hasErrors())
//		{
//			Map<String, String> errors= scriptEngine.getErrors();
//			Set<Map.Entry<String, String>> set = errors.entrySet();
//			for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
//				Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
//				System.out.println(entry.getKey() +"," + entry.getValue());
//			}
//		}
	
	}
	
	
	public void multiUserDeoploy()
	{
		InputStream is= Thread.currentThread().getContextClassLoader().getResourceAsStream("com/hotent/core/bpm/graph/multiUser.xml");
		Deployment d= repositoryService.createDeployment().name("multiUser").addInputStream("bpmn20.xml", is).deploy();
	}
	
	
	public void startMultiUser()
	{
		List<String> list=new ArrayList<String>();
		
	
		list.add("1");
		list.add("1322449844081");
		list.add("1322454269266");
		//signUser.setUser(list);
		
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("multiUser");
	}

	public void getExecution()
	{
		ExecutionEntity ent= bpmActService.getExecution("11811");
		
		Map<String, Object> vars=ent.getVariables();
		Set<Map.Entry<String, Object>> set = vars.entrySet();
		  for (Iterator<Map.Entry<String, Object>> it = set.iterator(); it.hasNext();) {
		   Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
		  System.out.println(entry.getKey() +"," + entry.getValue());
		  }

	}

	public void subprocessDeoploy()
	{
		InputStream is= Thread.currentThread().getContextClassLoader().getResourceAsStream("com/hotent/core/bpm/graph/subprocess.xml");
		Deployment d= repositoryService.createDeployment().name("subprocess").addInputStream("bpmn20.xml", is).deploy();
	}
	
	
	public void conForkDeoploy()
	{
		InputStream is= Thread.currentThread().getContextClassLoader().getResourceAsStream("com/hotent/core/bpm/graph/conFork.xml");
		Deployment d= repositoryService.createDeployment().name("formCondition").addInputStream("bpmn20.xml", is).deploy();
	}
	
	
	public void startConFork()
	{
		
		Map<String, Object> vars=new HashMap<String, Object>();
		
		vars.put("task", 5);
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("formCondition",vars);
	}
	
	
	public void flowRuleDeoploy()
	{
		InputStream is= Thread.currentThread().getContextClassLoader().getResourceAsStream("com/hotent/core/bpm/graph/flowRule.xml");
		Deployment d= repositoryService.createDeployment().name("formEvent").addInputStream("bpmn20.xml", is).deploy();
	}
	
	
	public void startFlowRule()
	{
		List<String> list=new ArrayList<String>();
		list.add("111");
		list.add("222");
		list.add("333");
		Map<String, Object> vars=new HashMap<String, Object>();
		vars.put("signUserList", list);
		Authentication.setAuthenticatedUserId("666");
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("flowRule",vars);
	}
	
	
	
	
	public void assignSeqTask() throws ActivityRequiredException{
		//taskService.complete("18723");
		//taskService.setAssignee("18802", "222");
		//taskService.delegateTask("18802", "333");
		taskService.claim("19502", "444");
	}
	

	public void getProcessDefByKey(){
		List<ProcessDefinition> list=bpmService.getProcessDefinitionByKey("abdemotesst");
		for(ProcessDefinition def:list){
			System.out.println(def.getId() +"," + def.getName() +"," +def.getKey() +"," + def.getDeploymentId());
		}
		
	}
	
	public void getProcessDefByDeployId()
	{
		
	}
	
	
	
}
