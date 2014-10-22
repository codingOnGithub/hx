package com.hotent.bpmx.plugin.process.def;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


import com.hotent.bpmx.api.plugin.process.def.BpmTaskPluginDef;
import com.hotent.bpmx.core.defxml.entity.Task;
import com.hotent.bpmx.plugin.parse.PluginUtil;



@XmlRootElement(namespace=AssignUserPluginDef.PUGING_NAMESPACE,name="assign-user")
@XmlAccessorType(XmlAccessType.FIELD)
public class AssignUserPluginDef implements BpmTaskPluginDef {
	public final static String PUGING_NAMESPACE = "http://www.jee-soft.cn/bpmnx/plugin-assign-user";
	
	@XmlAttribute
	protected String id;
	@XmlAttribute
	protected String name;
	@XmlElement(namespace=AssignUserPluginDef.PUGING_NAMESPACE,name="users")
	protected String users;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsers() {
		return users;
	}
	public void setUsers(String users) {
		this.users = users;
	}
	
	
	public static List<AssignUserPluginDef> newInstances(Task task){
		List<AssignUserPluginDef> defs = new ArrayList<AssignUserPluginDef>();
		List<String> xmls = PluginUtil.getTaskPluginDefXml(task,AssignUserPluginDef.class);
		for(String xml:xmls){
			try {
			JAXBContext  ctx = JAXBContext.newInstance(AssignUserPluginDef.class);
				Unmarshaller unmarshaller = ctx.createUnmarshaller();
				AssignUserPluginDef def = (AssignUserPluginDef) unmarshaller.unmarshal(new ByteArrayInputStream(xml.getBytes()));
				defs.add(def);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		}
		return defs;
	}
	
}
