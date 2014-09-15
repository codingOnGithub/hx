package demo;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import org.dom4j.Document;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.util.Dom4jUtil;
import com.hotent.core.util.XmlBeanUtil;
/**
 * xml与javabean的相互转换类示例
 * @author Administrator
 *
 */
public class XmlJavaConvter {
	
	public static void main(String[]args) throws JAXBException {
		testUnmarshall();
		//testMarshall();
	}
	
	public static void testMarshall()throws JAXBException{
		//JAXBContext jc = JAXBContext.newInstance(ProcessCmd.class); 
		 
		ProcessCmd processCmd=new ProcessCmd();
		
		processCmd.setFlowKey("orderCheckFlow");
		 
		//设置流程变量，流程变量由流程执行过程中使用
		Map<String,Object> variables=new HashMap<String,Object>();
		variables.put("orderId", 123l);
		variables.put("total", new BigDecimal("12303.3"));
		variables.put("orderNo", "SHIP_NO");
		//设置业务主键
		processCmd.setBusinessKey("123");
		processCmd.setVariables(variables);
		//设置启动流程的用户账号
		processCmd.setUserAccount("limin");
		 
		 String xml=XmlBeanUtil.marshall(processCmd,ProcessCmd.class);
		 System.out.println("xml:" + xml);
		
	}
	
	public static void testUnmarshall()throws JAXBException{
		String filePath="D:/dev/bpmx3/bpm/src/main/java/com/hotent/platform/servlet/send.xml";
		Document doc=Dom4jUtil.load(filePath);
		String xml=Dom4jUtil.docToString(doc);
		JAXBContext jc = JAXBContext.newInstance(ProcessCmd.class);
		ProcessCmd cmd=unmarshall(jc, xml);
		
		System.out.println("flow key:" + cmd.getFlowKey());
		
		System.out.println("vars:");
		
		Map<String,Object> vars=cmd.getVariables();
		System.out.println("size:" + vars.size());
	}
	
	
	private static ProcessCmd unmarshall(JAXBContext jc, String xml)
			throws JAXBException {
		Unmarshaller u = jc.createUnmarshaller();
		return (ProcessCmd) u.unmarshal(new StringReader(xml));
	}

	private static String marshall(JAXBContext jc, ProcessCmd processCmd)
			throws JAXBException, PropertyException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.marshal(processCmd, out);
		return out.toString();
	}
	
}
