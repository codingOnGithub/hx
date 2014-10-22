package com.hotent.bo.instance;

import java.io.FileOutputStream;
import java.text.ParseException;
import javax.annotation.Resource;
import org.apache.xmlbeans.XmlException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;
import com.hotent.bo.BOBaseTest;
import com.hotent.bo.def.parse.BODefParseFactory;
import com.hotent.bo.persistence.manager.BODefManager;
import com.hotent.bo.persistence.model.BODef;


public class BODefParseFactoryTest extends BOBaseTest{
	@Resource
	private BODefParseFactory bODefParseFactory;
	@Resource
	protected BODefManager boDefManager;
	
	//通过XML发布对象
//	@Test
	public void testDeployBODef() throws ParseException, XmlException{
		String xml = readXml("E:\\workspace\\x5-bo-root\\modules\\x5-bo-core\\src\\main\\resources\\xml\\person.xml","UTF-8");		
		BODef bodef = bODefParseFactory.parse(xml);
		boDefManager.deploy(bodef);
		System.out.println("===发布业务对象成功！====");
	}
	
	
	//对象转XML
//	@Test  
	public void testBODefToXML() throws ParseException, XmlException{
		String entityId = "10000000021001";               //业务对象ID
		//获取对象
		BODef bODef = boDefManager.loadBODef(entityId);
		System.out.println("===bODef==="+bODef);
		//对象转成XML字符串
		String xml = bODefParseFactory.getBODefXML(bODef);	
		System.out.println("===XML==="+xml);
		//写出XML文件
		writerXml(xml,"UTF-8","D:/MyPerson.xml");
	}
	
	//发布新版本的对象
//	@Test
	public void testDeployNewBODef(){
        String entityId = "10000000018001";        
        //获取对象
        BODef bODef = boDefManager.loadBODef(entityId);
        //发布新版本对象
		boDefManager.deploy(bODef);
		System.out.println("===发布新业务对象版本成功！====");
	}
	
	
	//修改对象
//	@Test
	public void testModifyBODef(){
        String entityId = "10000000018001";
		String xml = readXml("E:\\workspace\\x5-bo-root\\modules\\x5-bo-core\\src\\main\\resources\\xml\\person.xml","UTF-8");		
		BODef bodef = bODefParseFactory.parse(xml);
		bodef.setId(entityId);
		boDefManager.modify(bodef);
		System.out.println("===修改业务对象成功！====");
	}
	
	
	//测试删除对象
	@Test
	public void testRemoveBODef(){
        String entityId = "10000000021001";
		boDefManager.removeBODef(entityId);
		System.out.println("===删除业务对象成功！===");
	}
	
	
	//读XML文件
	public String readXml(String url,String charsetName) {
        String xml = "";
		SAXReader reader = new SAXReader();
        reader.setEncoding(charsetName);
        Document document = null;
        
        try {
            // 获取Document对象
            document = reader.read(url);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        
        xml =  document.asXML();
        
        return xml;
    }
	
	//写XML文件
	public void writerXml(String xml,String charsetName,String path) {
	      try {
			   OutputFormat format = OutputFormat.createPrettyPrint();
			   format.setEncoding(charsetName);
			   XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(path), format);
			   Document doc = DocumentHelper.parseText(xml);
			   xmlWriter.write(doc);
			   xmlWriter.close();
		  } catch (Exception e) {
		       e.printStackTrace();
		  }
    }
	
	
}
