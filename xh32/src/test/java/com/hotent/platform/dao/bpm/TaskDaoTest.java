package com.hotent.platform.dao.bpm;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.TaskService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.hotent.BaseTestCase;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.bpm.model.ProcessTask;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.service.bpm.TaskUserService;
import com.hotent.platform.webservice.api.ProcessService;

public class TaskDaoTest extends BaseTestCase {
	
	@Resource
	private TaskDao taskDao;
	
	@Resource
	private TaskService taskService;
	
	
	public void getReminderTask(){
		List<ProcessTask> list= taskDao.getReminderTask();
		System.out.println(list.size());
	}
	
	@org.junit.Test
	public void addUserGroup(){
		taskService.addGroupIdentityLink("10000006640072", "10000006640072", "org");
	}
	
	public static void main(String[] args){
		JaxWsProxyFactoryBean factoryBean=new JaxWsProxyFactoryBean();
		//http://192.168.1.202:8080/bpm32/service/ProcessService?wsdl
		factoryBean.setAddress("http://192.168.1.202:8080/bpm32/service/ProcessService");
		factoryBean.setServiceClass(ProcessService.class);
		
		ProcessService processService=(ProcessService)factoryBean.create();
		
//		ProcessCmd processCmd=new ProcessCmd();
//		processCmd.setFlowKey("qingjia");
//		processCmd.setUserAccount("ray");
	String	xmlString;
	StringBuffer sb=new StringBuffer();
	sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
	sb.append("<start actDefId=\"\" flowKey=\"qjlc123\" subject=\"webservice启动测试\"  startUser=\"admin\" mailInform=\"true\" smsInform=\"false\">");
	sb.append("<vars name=\"aa\" value=\"11\" type=\"int,long,double,date,String\"/>");
	sb.append("<vars name=\"bb\" value=\"22\" type=\"int,long,double,date,String\"/>");
	sb.append("</start>");
	xmlString=sb.toString();
		try{
//			ProcessRun processRun= processService.start(processCmd);
//			System.out.println("runId:" + processRun.getRunId());
			 String result= processService.start(xmlString);
			 System.out.println(result);
		}catch(Exception ex){
			
			ex.printStackTrace();
		}

	}

}
