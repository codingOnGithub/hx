package com.hotent.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.Date;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import com.hotent.bo.context.BOContext;
import com.hotent.bo.persistence.model.BODef;
import com.hotent.bo.persistence.model.DataObject;

public class BOContextTest extends BOBaseTest{
	BOContext context;
	
	@Before
	public void init(){
		context = BOConfiguration.getContext();
	}
	
	private String readXml(String url,String charsetName) {
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
	
	@Test
	@Rollback(true)
	public void deployWithXmlTest(){
		String xml = readXml("D:\\dev\\x5-bo-root\\modules\\x5-bo-core\\src\\main\\resources\\xml\\person.xml","UTF-8");	
        BODef boDef = context.parse(xml);

        DataObject dataObject = context.createDataObject(boDef);
        assertNotNull(dataObject);
        dataObject.set("person.born", "1990-06-29 09:11:12");
        Date d = dataObject.getDate("person.born");
        assertNotNull(d);
        
        List<DataObject> familyDetails = dataObject.getDataObjects("person.familyDetail");
        assertEquals(familyDetails.size(), 1);
		
		String str2 = "<person><familyDetail><marydetailaddress>1</marydetailaddress><marydetailaddress>2</marydetailaddress><marydetaility>中国</marydetaility><marydetailname>4</marydetailname></familyDetail><familyDetail><marydetailaddress>5</marydetailaddress><marydetailaddress>6</marydetailaddress><marydetaility>美国</marydetaility><marydetailname>8</marydetailname></familyDetail></person>";
		DataObject dataObject4 = context.createDataObject(boDef,str2);
		String marydetailname = dataObject4.getString("person.familyDetail[0].marydetailname[0]");
		assertEquals(marydetailname, "4");
		
		String str = "<person><name>a123</name><born>1987-06-29 13:00:00</born><personDetail><nationality>中国</nationality></personDetail></person>";
		DataObject dataObject2 = context.createDataObject(boDef,str);
		assertEquals(dataObject2.getString("person.name"), "a123");
		
		context.deploy(boDef);
		dataObject = context.save(dataObject);
		DataObject dataObject3 = context.getDataObject(dataObject.getId());
		assertNotNull(dataObject3);
		
		DataObject personDetail = dataObject3.getDataObject("person.personDetail");
        assertNotNull(personDetail);
	}
	
	@Test
	@Rollback(true)
	public void deployTest() throws Exception{
		String xml = readXml("D:\\dev\\x5-bo-root\\modules\\x5-bo-core\\src\\main\\resources\\xml\\person.xml","UTF-8");	
        BODef boDef = context.parse(xml);

        DataObject dataObject = context.createDataObject(boDef);
        assertNotNull(dataObject);
        dataObject.set("person.born", "1990-06-29 09:11:12");
        Date d = dataObject.getDate("person.born");
        assertNotNull(d);
        
        List<DataObject> familyDetails = dataObject.getDataObjects("person.familyDetail");
        assertEquals(familyDetails.size(), 1);
		
		String str2 = "{\"familyDetail\": [{\"marydetailaddress\": [\"广东\"],\"marydetaility\": \"中国\",\"marydetailname\": [\"张三\"]}]}";
		DataObject dataObject4 = context.createDataObject(boDef,str2);
		assertEquals(dataObject4.getString("person.familyDetail[0].marydetailname[0]"), "张三");
		
		String str = "{\"born\":\"1987-06-29 13:00:00\",\"name\":\"a123\",\"personDetail\":{\"nationality\":\"中国\"}}";
		DataObject dataObject2 = context.createDataObject(boDef,str);
		assertEquals(dataObject2.getString("person.name"), "a123");
		
		context.deploy(boDef);
		dataObject = context.save(dataObject);
		DataObject dataObject3 = context.getDataObject(dataObject.getId());
		assertNotNull(dataObject3);
		
		DataObject personDetail = dataObject3.getDataObject("person.personDetail");
        assertNotNull(personDetail);
        
        BODef boDef2 = dataObject3.getBoDef();
        String xmlString = context.getBODefXML(boDef2);
        System.out.println(xmlString);
	}
}
