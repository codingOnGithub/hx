package com.hotent.bo.instance;

import org.junit.Assert;
import org.junit.Test;
import com.hotent.base.core.util.FileUtil;
import com.hotent.bo.BOBaseTest;
import com.hotent.bo.persistence.model.impl.JSONDataObject;

public class JSONDataObjectTest extends BOBaseTest{
	@Test
	public void getByPath(){
		JSONDataObject dataObject = new JSONDataObject();
		String json = FileUtil.readFile("D:\\dev2\\bo\\data\\person.json");
		dataObject.setInstData(json);
		dataObject.set("person.name", "李四");
		Assert.assertEquals(dataObject.get("person.name"), "李四");
		
		dataObject.set("person.familyDetail[0].marydetaility", "湖北");
		Assert.assertEquals(dataObject.get("person.familyDetail[0].marydetaility"), "湖北");
		
		String marydetailaddress = dataObject.getString("person.familyDetail[0].marydetailaddress");
		Assert.assertNotNull(marydetailaddress);
	}
}
