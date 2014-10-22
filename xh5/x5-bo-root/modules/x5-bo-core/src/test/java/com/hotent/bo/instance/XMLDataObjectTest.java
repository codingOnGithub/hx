package com.hotent.bo.instance;

import static org.junit.Assert.assertEquals;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import com.hotent.base.core.xml.Dom4jUtil;
import com.hotent.bo.BOBaseTest;
import com.hotent.bo.persistence.model.DataObject;
import com.hotent.bo.persistence.model.impl.XMLDataObject;

public class XMLDataObjectTest extends BOBaseTest{
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
	public void getByPath(){
		String xml = readXml("D:\\dev2\\bo\\data\\person.xml","UTF-8");
		DataObject dataObject = new XMLDataObject();
		dataObject.setInstData(xml);
		assertEquals(dataObject.getString("person.name"), "张三");
		
//		Date bornDate = dataObject.getDate("person.born");
//		assertNotNull(bornDate);
		String string = "<personDetail><nationality>美国</nationality></personDetail>";
		dataObject.set("person.personDetail", string);
		
		assertEquals(dataObject.getString("person.personDetail.nationality"), "美国");
		
		String familyDetail = "<familyDetail><marydetailaddress>湖南</marydetailaddress><marydetailaddress>湖北</marydetailaddress>"
							 +"<marydetaility>美国</marydetaility><marydetailname>赵六</marydetailname></familyDetail>"
							 +"<familyDetail><marydetailaddress>山西</marydetailaddress><marydetailaddress>山东</marydetailaddress>"
							 +"<marydetaility>法国</marydetaility><marydetailname>钱七</marydetailname></familyDetail>";
		dataObject.set("person.familyDetail[*]", familyDetail);
		
		dataObject.set("person.familyDetail[*].marydetailaddress[*]","测试");
		assertEquals(dataObject.getString("person.familyDetail[0].marydetailaddress[0]"), "测试");
	}
}
