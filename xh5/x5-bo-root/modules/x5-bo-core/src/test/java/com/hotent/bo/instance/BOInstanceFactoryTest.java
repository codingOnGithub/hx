package com.hotent.bo.instance;

import static org.junit.Assert.*;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import com.hotent.bo.BOBaseTest;
import com.hotent.bo.persistence.manager.BODefManager;
import com.hotent.bo.persistence.model.BODef;
import com.hotent.bo.persistence.model.DataObject;

public class BOInstanceFactoryTest extends BOBaseTest{
	@Resource
	private BOInstanceFactory boFactory;
	@Resource
	protected BODefManager boDefManager;
	
	@Test
	@Rollback(true)
	public void getByPath() throws ParseException{
		BODef boDef = boDefManager.get("10000000020001");
		DataObject dataObject = boFactory.createDataObject(boDef);
		assertEquals(dataObject.getString("person.name"),"");
		assertEquals(dataObject.getString("person.sex"),"男");
		dataObject.set("person.sex", "女");
		assertEquals(dataObject.getString("person.sex"),"女");
		
		dataObject.set("person.personDetail[0].nationality", "美国");
		assertEquals(dataObject.getString("person.personDetail[0].nationality"),"美国");
		
		dataObject.set("person.born", "1985-07-15 13:09:51");
		Date born = dataObject.getDate("person.born");
		assertNotNull(born);
		
		DataObject personDetail = dataObject.getDataObject("person.personDetail[0]");
		assertEquals(personDetail.getString("detail.nationality"),"美国");
		
		personDetail.set("detail.nationality", "中国");
		dataObject.setDataObject("person.personDetail[0]", personDetail);
		assertEquals(dataObject.getString("person.personDetail[0].nationality"),"中国");
		
		dataObject.put("person.personDetail", personDetail);
		assertEquals(dataObject.getString("person.personDetail[1].nationality"),"中国");
		
		List<DataObject> list = dataObject.getDataObjects("person.personDetail");
		assertEquals(list.size(), 2);
		
		list.add(personDetail);
		dataObject.setDataObjects("person.personDetail", list);
		assertEquals(list.size(), 3);
	}
	
	@Test
	@Rollback(true)
	public void testLoadData(){
		//测试 load DataObject数据
		BODef boDef = boDefManager.get("10000000020001");
		String str = "{\"born\":\"1987-06-29 13:00:00\",\"address\":[\"长沙\",\"湘潭\",\"广州\"],\"personDetail\":[{\"nationality\":\"中国\"},{\"nationality\":\"美国\"}],\"name\":\"何一帆\"}";
		DataObject dataObject2 = boFactory.createDataObject(boDef, str);
		assertEquals(dataObject2.getString("person.personDetail[1].nationality"), "美国");
	}
}
