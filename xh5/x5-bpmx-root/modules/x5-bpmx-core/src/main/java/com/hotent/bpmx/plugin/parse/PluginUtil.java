package com.hotent.bpmx.plugin.parse;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.activiti.engine.impl.util.ReflectUtil;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.SAXException;

import com.hotent.base.core.util.FileUtil;
import com.hotent.bpmx.api.plugin.process.def.BpmTaskPluginDef;
import com.hotent.bpmx.core.defxml.entity.ExtensionElements;
import com.hotent.bpmx.core.defxml.entity.Task;
import com.hotent.bpmx.core.defxml.entity.hotent.ExecutionPlugin;
import com.hotent.bpmx.core.defxml.entity.hotent.PluginDef;
import com.hotent.bpmx.core.defxml.entity.hotent.TaskPlugin;

public class PluginUtil {
	static Logger logger = LoggerFactory.getLogger(PluginUtil.class);

	public static List<String> getExecutionPluginDefXml(com.hotent.bpmx.core.defxml.entity.Process process) {
		List<String> xmls = new ArrayList<String>();
		ExtensionElements extensions = process.getExtensionElements();
		List<Object> objs = extensions.getAny();
		for (Object obj : objs) {
			if (obj instanceof JAXBElement) {
				if (((JAXBElement<ExecutionPlugin>) obj).getValue() instanceof ExecutionPlugin) {
					JAXBElement<ExecutionPlugin> jaxb = ((JAXBElement<ExecutionPlugin>) obj);
					ExecutionPlugin plugin = (ExecutionPlugin) ((JAXBElement<ExecutionPlugin>) obj).getValue();
					PluginDef def = plugin.getPluginDef();
					System.out.println(def);

					List<Object> objs1 = def.getAny();

					Node node = (Node) objs1.get(0);

					Document document = node.getOwnerDocument();
					DOMImplementationLS domImplLS = (DOMImplementationLS) document.getImplementation();
					LSSerializer serializer = domImplLS.createLSSerializer();
					serializer.getDomConfig().setParameter("xml-declaration", false);
					String str = serializer.writeToString(node);
					xmls.add(str);

					logger.info("execution plugin definition: \n" + str);
				}
			}
		}
		return xmls;
	}

	public static List<String> getTaskPluginDefXml(Task task,Class<? extends BpmTaskPluginDef> clazz) {
		List<String> xmls = new ArrayList<String>();
		ExtensionElements extensions = task.getExtensionElements();
		if(extensions==null){
			return xmls;
		}
		List<Object> objs = extensions.getAny();
		for (Object obj : objs) {
			if (obj instanceof JAXBElement) {
				if (((JAXBElement<TaskPlugin>) obj).getValue() instanceof TaskPlugin) {
					JAXBElement<TaskPlugin> jaxb = ((JAXBElement<TaskPlugin>) obj);
					TaskPlugin plugin = (TaskPlugin) ((JAXBElement<TaskPlugin>) obj).getValue();
					try {
						if(!Class.forName(plugin.getClazz()).equals(clazz)){
							continue;
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
						throw new RuntimeException(e.getMessage());
					}
					plugin.getClazz();
					PluginDef def = plugin.getPluginDef();

					List<Object> objs1 = def.getAny();

					Node node = (Node) objs1.get(0);

					Document document = node.getOwnerDocument();
					DOMImplementationLS domImplLS = (DOMImplementationLS) document.getImplementation();
					LSSerializer serializer = domImplLS.createLSSerializer();
					serializer.getDomConfig().setParameter("xml-declaration", false);
					String str = serializer.writeToString(node);
					xmls.add(str);

					logger.info("task definition: \n" + str);
				}
			}
		}
		return xmls;
	}
	

	public static boolean validateXMLSchema(String xml) {
		File  xsddir = new File(ReflectUtil.getResource("com/hotent/bpmx/core/xsd").getFile());
		File[] files = xsddir.listFiles();
		List<File> lfile = new ArrayList<File>();
		for(File f:files){
			if(FilenameUtils.getExtension(f.getName()).equalsIgnoreCase("xsd")){
				lfile.add(f);
			}
		}
		return validateXMLSchema(new ByteArrayInputStream(xml.getBytes()),lfile.toArray(new File[]{}) );
	}

	public static boolean validateXMLSchema(String xml, File... xsdFiles) {
		return validateXMLSchema(new ByteArrayInputStream(xml.getBytes()), xsdFiles);
	}

	public static boolean validateXMLSchema(InputStream xmlIs, File... xsdFiles) {
		try {
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

			List<Source> sourceList = new ArrayList<Source>();

			for (File file : xsdFiles) {
				sourceList.add(new StreamSource(file));
			}

			Source[] sources = sourceList.toArray(new Source[] {});
			Schema schema = factory.newSchema(sources);

			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(xmlIs));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (SAXException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
