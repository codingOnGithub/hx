package com.hotent.bpmx.plugin;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBException;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.impl.bpmn.parser.BpmnParse;
import org.activiti.engine.impl.util.ReflectUtil;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.ActivitiTestCase;
import org.activiti.engine.test.Deployment;
import org.junit.Test;

import com.hotent.bpmx.core.defxml.DefXmlUtil;
import com.hotent.bpmx.core.defxml.entity.Definitions;

public class PluginTest extends ActivitiTestCase {

	@Override
	public String getConfigurationResource() {
		return "conf/activiti-raise-test.cfg.xml";
	}

//	@Test
	@Deployment(resources = { "com/hotent/definitions/plugins.bpmn" })
	public void testManulTask() {
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess");
		processInstance.getProcessDefinitionId();
	}
	
	
	@Test
	private void parseBpmn() throws JAXBException, IOException {
		String resource = "com/hotent/definitions/plugins.bpmn";
		InputStream is = ReflectUtil.getResourceAsStream(resource);
		Definitions definitions = DefXmlUtil.getDefinitions(is);
		System.out.println(definitions);
	}
}
