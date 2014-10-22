package com.hotent.bpmx.activiti.test.def;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.ManualTaskActivityBehavior;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.ProcessDefinitionBuilder;
import org.activiti.engine.impl.pvm.PvmExecution;
import org.activiti.engine.impl.pvm.PvmProcessDefinition;
import org.activiti.engine.impl.pvm.PvmProcessInstance;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

import com.hotent.base.core.util.FileUtil;
import com.hotent.base.core.xml.Dom4jUtil;
import com.hotent.bpmx.activiti.test.ActivitiBaseTest;
import com.hotent.bpmx.natapi.def.NatProDefinitionService;


public class ProcDefinitionServiceImplTest  extends ActivitiBaseTest {
	@Resource
	NatProDefinitionService natProDefinitionService;
	
	@Resource
	RepositoryService repositoryService;
	
	@Resource
	RuntimeService runtimeService;
	
	@Resource
	TaskService taskService;
	
//	
	@Test
	public void testDeploy() throws IOException{
		ClassLoader  loader  =  Thread.currentThread().getContextClassLoader();
		String url=loader.getResource("com/hotent/bpmx/activiti/xml/chongfumsg.bpmn").getPath();
	
		//System.out.println("url:" + url);
		String defXml=getBpmnFileString(url);
		System.out.println("xml:" + defXml);
		
		String deployId=natProDefinitionService.deploy("mymessage", defXml);
		
		logger.debug("deployId============>"+ deployId);
		
		System.out.println("deployId:" + deployId);
	}
	
	
	//@Test
	public void testSuspendProcessDefinitionByKey(){
		//repositoryService.suspendProcessDefinitionById(arg0)
		//repositoryService.suspendProcessDefinitionByKey("messagetest",true,null);
		repositoryService.activateProcessDefinitionByKey("messagetest");
		//runtimeService.startProcessInstanceByKey("messagetest");
		//repositoryService.activateProcessDefinitionById(arg0, arg1, arg2)
	}
	
	//@Test
	public void testDeployCatch() throws IOException{
		ClassLoader  loader  =  Thread.currentThread().getContextClassLoader();
		String url=loader.getResource("com/hotent/bpmx/activiti/xml/catch.bpmn").getPath();
	
		//System.out.println("url:" + url);
		String defXml=getBpmnFileString(url);
		System.out.println("xml:" + defXml);
		
		String deployId=natProDefinitionService.deploy("catch", defXml);
		
		logger.debug("deployId============>"+ deployId);
		
		System.out.println("deployId:" + deployId);
	}
	
	//@Test
	public void testDeploySign() throws IOException{
		ClassLoader  loader  =  Thread.currentThread().getContextClassLoader();
		String url=loader.getResource("com/hotent/bpmx/activiti/xml/sign.bpmn").getPath();
	
		//System.out.println("url:" + url);
		String defXml=getBpmnFileString(url);
		System.out.println("xml:" + defXml);
		
		String deployId=natProDefinitionService.deploy("sign", defXml);
		
		logger.debug("deployId============>"+ deployId);
		
		System.out.println("deployId:" + deployId);
	}
	
	//ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("financialReport");
	
	//@Test
	public void testStart() throws IOException, InterruptedException{
		//ProcessInstance p= runtimeService.startProcessInstanceById("boundarymessageflow:1:10000000152004");
		
		ProcessInstance p= runtimeService.startProcessInstanceByKey("boundarymessageflow");
		
		//ProcessInstance p= runtimeService.startProcessInstanceById("sign:1:10000000059008");
		//10000000071004
		System.out.println("testStart:" +p.getProcessInstanceId());
		
		System.out.println("wait here");
		
		//Thread.sleep(60000);
	}
	
	//@Test
	public void testMessage1() throws IOException{
		ProcessInstance p= runtimeService.startProcessInstanceByMessage("message2");
		
		//ProcessInstance p= runtimeService.startProcessInstanceById("sign:1:10000000059008");
		//10000000071004
		System.out.println(p.getProcessInstanceId());
	}
	
	//@Test
	public void testMessage2() throws IOException{
		ProcessInstance p= runtimeService.startProcessInstanceByMessage("mymessage");
		
		//ProcessInstance p= runtimeService.startProcessInstanceById("sign:1:10000000059008");
		//10000000071004
		System.out.println(p.getProcessInstanceId());
	}
	
	
	
	
	//@Test
	public void testComplete() throws IOException{
		 taskService.complete("10000000178006");
		System.out.println("complete");
	}
	
	//@Test
	public void testEvent() throws IOException{
		 taskService.complete("messageEventReceived");
		
		 
		System.out.println("complete");
	}
	
	@Test
	public void testMessageEvent() throws IOException{
		 runtimeService.messageEventReceived("msgBound", "10000000180003");
		
		 
		System.out.println("complete");
	}
	
	
	
	
	
	//@Test
	public void testSign() throws IOException{
		runtimeService.signalEventReceived("signBound");
		
		System.out.println("testSign");
	}
	
	
	
	
	//@Test
	public void getDefFromFile() throws IOException{
		//ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition("process:4:10000000191004");
		ProcessDefinitionEntity processDefinition=(ProcessDefinitionEntity) FileUtil.deserializeFromFile("e:\\a.dat");
		ActivityImpl act= processDefinition.findActivity("UserTask_3");
		System.err.println(act.getProperty("name"));
		System.out.println("ok");
	}
	
	//@Test
	public void testClone() throws Exception{
		//ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition("process:4:10000000191004");
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition("process:4:10000000191004");
		ProcessDefinitionEntity  p= (ProcessDefinitionEntity) FileUtil.cloneObject(processDefinition);
		
		Assert.assertTrue(processDefinition!=p);
	}

	
	
	
//	@Test
	public void testGetXml() throws IOException{
		
		String deployId="10000000016001";
		
		String defXml=natProDefinitionService.getDefXmlByDeployId(deployId);
		
		System.out.println("defXml:" + defXml);
		
	}
		
	

	/**
	 * 测试流程发布、获取定义文件、更改流程定义文件
	 * @throws IOException 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
//	@Test
	public void testDeployAndGetXmlModify() throws IOException{
		ClassLoader  loader  =  Thread.currentThread().getContextClassLoader();
		String url=loader.getResource("com/hotent/bpmx/activiti/test/def/demo2.bpmn").getPath();
		if(url!=null){
			url=url.substring(1);
		}
		//从文件中读取流程定义文件XML
		String fileXml1=getBpmnFileString(url);
		
		System.out.println("xml:" + fileXml1);
		//发布流程
//		String deployId=natProDefinitionService.deploy("demo3", fileXml1);
//		
//		System.out.println(deployId);
//		
//		Assert.assertTrue(deployId!=null);
//		//获取已经发布的流程定义XML
//		String defXml1=natProDefinitionService.getDefXmlByDeployId(deployId);
//		System.out.println("getXmlFromDb:"+ defXml1);
		
		//读取另一流程定义文件
//		String url2=loader.getResource("com/hotent/bpmx/activiti/test/def/example2.bpmn").getPath();
//		if(url2!=null){
//			url2=url2.substring(1);
//		}
//		//读取其流程定义文件XML
//		String fileXml2=getBpmnFileString(url2);
//		
//		natProDefinitionService.writeDefXml(deployId,  fileXml2);
//
//		String defXml2=natProDefinitionService.getDefXmlByDeployId(deployId);
//		
//		System.out.println("deployXML:"+ defXml2);
	}
	
	
	public void loadXml() throws Exception{
		ClassLoader  loader  =  Thread.currentThread().getContextClassLoader();
		String url=loader.getResource("com/hotent/bpmx/activiti/test/def/example2.bpmn").getPath();
//		if(url!=null){
//			url=url.substring(1);
//		}
		String xslUrl=loader.getResource("com/hotent/bpmx/activiti/xml/transdef.xsl").getPath();
//		if(xslUrl!=null){
//			xslUrl=xslUrl.substring(1);
//		}
		System.out.println(xslUrl);
		Map<String, String> map =new HashMap<String, String>();
		map.put("id", "process");
		map.put("name", "myprocess");
		String result= Dom4jUtil.transFileXmlByXslt(url, xslUrl, map);
				
		System.out.println(result);
		//Dom4jUtil.load(arg0)
	}

	//@Test
	public void testExecutions() throws InterruptedException{
//		List<Execution> executions = runtimeService.createExecutionQuery()
//			      .signalEventSubscriptionName("abc")
//			      .list();
//		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
//			     .messageEventSubscriptionName("mymessage")
//			      .singleResult();
		
		
		List<Execution> execution = runtimeService.createExecutionQuery()
			      .messageEventSubscriptionName("paymentMessage")
			      
			      .list();
		System.out.println("testExecutions:" +execution.size());
		
		Thread.sleep(60000);
	}
	
	//@Test
	public void testPvm(){
		
		PvmProcessDefinition processDefinition = new ProcessDefinitionBuilder()  
        .createActivity("a").initial().behavior(new ManualTaskActivityBehavior())  
        .transition("b").endActivity().createActivity("b")  
        .behavior(new ManualTaskActivityBehavior()).transition("c").endActivity()  
        .createActivity("c").behavior(new ManualTaskActivityBehavior()).endActivity()  
        .buildProcessDefinition();  
		PvmProcessInstance processInstance = processDefinition  
		        .createProcessInstance();  
		processInstance.start();  
		PvmExecution activityInstance = processInstance.findExecution("a"); 
		
		Assert.assertNotNull(activityInstance);  
		activityInstance.signal(null, null);  
		activityInstance = processInstance.findExecution("b");  
		Assert.assertNotNull(activityInstance);  
		activityInstance.signal(null, null);  
		activityInstance = processInstance.findExecution("c");  
		Assert.assertNotNull(activityInstance);  
	}

	
	
	
}
