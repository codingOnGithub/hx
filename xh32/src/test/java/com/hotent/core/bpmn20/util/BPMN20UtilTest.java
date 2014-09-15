package com.hotent.core.bpmn20.util;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import org.activiti.engine.impl.bpmn.diagram.Bpmn20NamespaceContext;
import org.junit.Test;

import com.hotent.core.bpmn20.entity.FlowElement;
import com.hotent.core.bpmn20.entity.Process;
import com.hotent.core.bpmn20.entity.StartEvent;
import com.hotent.core.bpmn20.entity.UserTask;
import com.hotent.core.bpmn20.entity.ht.BPMN20HtExtConst;

public class BPMN20UtilTest {

	@Test
	public void testGetFlowElementByType() throws JAXBException, IOException {
		InputStream is = new FileInputStream("E:/workspace/activiti001/MyProcess.bpmn");
		List<Process> processes = BPMN20Util.getProcess(is);
		if(processes.size()==0){
			return;
		}
		Process process = processes.get(0);
		List<FlowElement> flowElements = BPMN20Util.getFlowElementByType(process,true,UserTask.class );
		System.out.println(flowElements.size());
	}

//	@Test
	public void testMarshall() {
		fail("Not yet implemented");
	}

//	@Test
	public void testUnmarshallInputStreamClassOfQextendsObject() {
		fail("Not yet implemented");
	}

//	@Test
	public void testUnmarshallStringClassOfQextendsObject() {
		fail("Not yet implemented");
	}

//	@Test
	public void testCreateDefinitionsInputStream() {
		fail("Not yet implemented");
	}

//	@Test
	public void testCreateDefinitionsString() {
		fail("Not yet implemented");
	}

//	@Test
	public void testGetProcessInputStream() {
		fail("Not yet implemented");
	}

//	@Test
	public void testGetProcessString() {
		fail("Not yet implemented");
	}

//	@Test
	public void testGetFlowElementExtension() throws JAXBException, IOException {
		InputStream is = new FileInputStream("src/test/resources/bpmn20/bpmnXml.ht.xml");
//		is=checkForUtf8BOM(is);
		List<Process> processes = BPMN20Util.getProcess(is);
		if(processes.size()==0){
			return;
		}
		Process process = processes.get(0);
		List<FlowElement> flowElements = BPMN20Util.getFlowElementByType(process,true,UserTask.class );
		for(FlowElement flowElement:flowElements){
			QName qname =BPMN20HtExtConst._Order_QNAME;
			List<Object>  objs= BPMN20Util.getFlowElementExtension(flowElement,qname);
			System.out.println(objs.size());
		}
	}
	
	private static InputStream checkForUtf8BOM(InputStream inputStream) throws IOException {
	    PushbackInputStream pushbackInputStream = new PushbackInputStream(new BufferedInputStream(inputStream), 3);
	    byte[] bom = new byte[3];
	    if (pushbackInputStream.read(bom) != -1) {
	        if (!(bom[0] == (byte) 0xEF && bom[1] == (byte) 0xBB && bom[2] == (byte) 0xBF)) {
	            pushbackInputStream.unread(bom);
	        }
	    }
	    System.out.println(byte2HexString(bom));
	    return pushbackInputStream;
	}
	 public static String byte2HexString(byte[] b) {
	        char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7',
	                      '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
	        char[] newChar = new char[b.length * 2];
	        for(int i = 0; i < b.length; i++) {
	            newChar[2 * i] = hex[(b[i] & 0xf0) >> 4];
	            newChar[2 * i + 1] = hex[b[i] & 0xf];
	        }
	        return new String(newChar);
	  }
	 
//	 @Test
	 public void test(){
		 System.out.println(byte2HexString("0<0".getBytes()));
	 }
}
