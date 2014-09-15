package com.hotent.core.bpm;

import static org.junit.Assert.*;

import groovy.lang.GroovyShell;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.dom4j.Document;
import org.dom4j.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.hotent.core.bpm.graph.ShapeMeta;
import com.hotent.core.bpm.util.BpmUtil;
import com.hotent.core.util.Dom4jUtil;
import com.hotent.core.util.FileUtil;
import com.ibm.db2.jcc.t4.ac;

public class BpmUtilTest {

	@Before
	public void setUp() throws Exception {
	}
	
	private String getBpmn20Xml(String file) throws IOException{
		InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
		String xml = FileUtil.inputStream2String(is, "utf-8");
		return xml;
	}
	
	@Test
	public void tetGetTaskActivitys(){
		String file="";
		try {
			String xml = getBpmn20Xml(file);
			Map<String, Map<String, String>> activities = BpmUtil.getTaskActivitys(xml);
			Map<String,String> tasks = activities.get("任务节点");
			System.out.println(tasks);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void transform() throws TransformerFactoryConfigurationError, Exception {
		InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream("com/hotent/core/bpm/graph/process.idfl");
		String xml= FileUtil.inputStream2String(is, "utf-8");
		String str= BpmUtil.transform("helloword", "流程转换", xml);
		System.out.println(str);
	}
	
	//
	public void transfGraph() throws TransformerFactoryConfigurationError, Exception {
		InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream("com/hotent/core/bpm/graph/multiUser.xml");
		String xml= FileUtil.inputStream2String(is, "utf-8");
		
		ShapeMeta shapeMeta= BpmUtil.transGraph(xml);
		
		
		System.out.println(shapeMeta.getXml());
	}
	
//	@Test 
	public void Test(){
		
	}
	
	//@Test
	public void startScript() throws ScriptException, NoSuchMethodException{
		
		GroovyShell shell=new GroovyShell();
		//shell.evaluate("class WorldGreeter {    public static void main( args) {       System.out.println ('hello world') ;    } } ");
		shell.evaluate("def closure={ param -> println 'hello ${param}' }  closure.call('world!') ");
		
	}
	
	public void isTaskListener()
	{
		boolean rtn=BpmUtil.isTaskListener("com.hotent.platform.service.bpm.HrTaskListener");
		Assert.assertTrue(rtn);
	}
	
//	@Test
	public void getFirstTaskNode() throws TransformerFactoryConfigurationError, Exception {
		InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream("com/hotent/core/bpm/graph/process.xml");
		String xml= FileUtil.inputStream2String(is, "utf-8");
		String taskId=BpmUtil.getFirstTaskNode(xml);
		System.out.println(taskId);
	}
}
