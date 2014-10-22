package com.hotent.bpmx.plugin;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBException;

import static org.junit.Assert.*;

import org.activiti.engine.impl.util.ReflectUtil;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.junit.Test;

import com.hotent.bpmx.core.defxml.DefXmlUtil;
import com.hotent.bpmx.core.defxml.entity.Process;
import com.hotent.bpmx.core.defxml.entity.Task;
import com.hotent.bpmx.core.engine.task.plugins.def.AssignUserPluginDef;

public class PlugiTest_Ex {

	@Test
	public void parseBpmn() throws JAXBException, IOException {
		String resource = "com/hotent/definitions/plugins.bpmn";
		InputStream is = ReflectUtil.getResourceAsStream(resource);
		Process process = DefXmlUtil.getProcess(is).get(0);
		
		List<Task> tasks = DefXmlUtil.getTasks(process);
		for(Task task:tasks){
			List<AssignUserPluginDef> defs = AssignUserPluginDef.newInstances(task);
			for(AssignUserPluginDef def:defs){
				System.out.println(ToStringBuilder.reflectionToString(def,ToStringStyle.SHORT_PREFIX_STYLE));
			}
		}
		
	}
}
