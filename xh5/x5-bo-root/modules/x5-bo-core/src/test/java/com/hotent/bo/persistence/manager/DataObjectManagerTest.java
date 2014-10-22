package com.hotent.bo.persistence.manager;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hotent.bo.BOBaseTest;
import com.hotent.bo.persistence.model.DataObject;
import com.hotent.bo.persistence.model.impl.JSONDataObject;

public class DataObjectManagerTest extends BOBaseTest{
	@Resource
	DataObjectManager dataObjectManager;
	
	@Test
	@Rollback(true)
	public void testCreate(){
		DataObject dataObject=new JSONDataObject();
		dataObject.setId(idGenerator.getSuid());
		dataObject.setCreateTime(new Date());
		//创建一实体
		dataObjectManager.create(dataObject);
	}
}
