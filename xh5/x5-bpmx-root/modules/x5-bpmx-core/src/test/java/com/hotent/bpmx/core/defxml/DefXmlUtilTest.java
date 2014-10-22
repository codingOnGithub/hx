package com.hotent.bpmx.core.defxml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBException;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.hotent.base.test.BaseTestCase;
import com.hotent.bpmx.core.defxml.entity.Definitions;
import com.hotent.bpmx.core.defxml.entity.Process;
import com.hotent.bpmx.core.defxml.entity.Task;
@ContextConfiguration({"classpath:conf/x5-bpmx-core-test.xml"})
public class DefXmlUtilTest extends BaseTestCase{	
	@Test
	public void getTasks() throws JAXBException, IOException{		
		outputTasks("/test/demo2.bpmn");		
		outputTasks("/test/example1.bpmn");
		outputTasks("/test/example2.bpmn");		
	}
	
	private void outputTasks(String fileRelatePath) throws JAXBException, IOException{
		String defXml = Class.class.getClass().getResource("/").getPath() + fileRelatePath;		
		File file = new File(defXml);
		InputStream is = new FileInputStream(file);
		Process process = DefXmlUtil.getProcess(is).get(0);		
		List<Task> tasks =DefXmlUtil.getTasks(process);
		System.out.println();
		System.out.println("====== "  + fileRelatePath + " begin ============");
		for(Task task:tasks){
			System.out.println("id="+task.getId() + ";name=" + task.getName());
		}
		System.out.println("====== "  + fileRelatePath + " end ============");
	}
	
	@Test
	public void testProcessCorrect() throws IOException, JAXBException{
		isProcessNotCorrect("/test/errorProcess.bpmn");
	}
	
	public void isProcessNotCorrect(String fileRelatePath) throws IOException, JAXBException{
		String defXml = Class.class.getClass().getResource("/").getPath() + fileRelatePath;		
		String bpmnXml = FileUtils.readFileToString(new File(defXml));
		boolean isCorrect = DefXmlUtil.isProcessCorrect(bpmnXml);
		Assert.assertFalse(isCorrect);
	}
}
