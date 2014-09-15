package com.hotent.core.bpm;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.bpmn.diagram.ProcessDiagramGenerator;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

import com.hotent.BaseTestCase;
import com.hotent.core.bpm.util.BpmUtil;
import com.hotent.core.util.FileUtil;
import com.hotent.platform.dao.bpm.TaskDao;
import com.hotent.platform.service.bpm.BpmService;
import com.hotent.platform.service.bpm.IBpmActService;

public class BpmTestCase extends BaseTestCase
{
	@Resource
	RepositoryService repositoryService;
	@Resource
	TaskService taskService;
	
	@Resource
	BpmService bpmService;
	@Resource	
	RuntimeService runtimeService;
	
	@Resource
	IBpmActService bpmActService;
	
	@Resource	
	TaskDao taskDao;
	
	//@Test
	public void genBpmImage() 
	{
		String deployId = "1";
		Deployment deploy = repositoryService.createDeploymentQuery().deploymentId(deployId).singleResult();
		System.out.println("out:" + deploy.getDeploymentTime());
		try{
			BpmUtil.genImageByDepolyId(deployId, "d:/abc.png", repositoryService);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	//@Test
	public void testGetXml()
	{
		String deployId="1";
		String defXml=bpmService.getDefXmlByDeployId(deployId);
		System.out.println("xml:" + defXml);
	}
	
	//取得某个流程定义的所有节点及属性
	@Test
	public void getActivity()
	{
		String defId="cityAdminFlow:1:1504";
		List<ActivityImpl> list=bpmService.getActivityNodes(defId);
		for(ActivityImpl act:list)
		{
			Iterator<String> it=act.getProperties().keySet().iterator();
			while(it.hasNext())
			{
				String key=it.next();
				System.out.println("key:" + key + " val:" + act.getProperties().get(key));
			}
		}
	}
	//取得某个流程所有任务节点的名称
	//@Test
	public void getTaskNames()
	{
		String defId="commonOrder:1:606";
		List<String> names=bpmService.getExecuteNodes(defId);
		for(String str:names)
		{
			System.out.println("name:" + str);
		}
	}
	
	//@Test
	public void getTasksName()
	{
		String taskId="314";
		List<String> actNames=bpmService.getActiveTasks(taskId);
		for(String actName:actNames)
		{
			System.out.println("name:" + actName);
		}
	}
	//@Test
	public void genActivesTaskImage() throws IOException
	{
		String taskId="314";
		
		ProcessDefinitionEntity ent=bpmService.getProcessDefinitionByTaskId(taskId);
		
		List<String> acts=bpmService.getActiveActIdsByTaskId(taskId);
		
		//InputStream is=ProcessDiagramGenerator.generateDiagram(ent, "png", acts);
		
	//	FileUtil.writeFile("d:/abc.png", is);

		
	}
	
	
//	
	//@Test
	public void testNewTask()
	{
		
		String orgTaskId="9934";
		String assignee="1324017565389";
		
		bpmService.newTask(orgTaskId,assignee);
	}
	
	//@Test
	public void endProcess(){
//		String taskId="35124";
//		bpmService.endProcessByTaskId(taskId);
//		System.out.println("endProcess");
//		String executeId="35117";
//		ExecutionEntity execution=(ExecutionEntity) runtimeService.createExecutionQuery().executionId(executeId).singleResult();
//		execution.end();
//		System.out.println("endProcess");
		String taskId="35124";
		bpmActService.endProcessByTaskId(taskId);
		System.out.println("endProcess");
//		Task task= taskService.createTaskQuery().taskId(taskId).singleResult();
//		String executeId=task.getExecutionId();
//		ExecutionEntity exe=(ExecutionEntity) runtimeService.createExecutionQuery().executionId(executeId).singleResult();
//		System.out.println(exe.getProcessInstanceId()+"," + exe.getProcessDefinitionId());
		
	}

	
}
