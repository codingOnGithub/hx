package com.hotent.bpmx.persistence.dao;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import com.hotent.bpmx.api.constant.DesignerType;
import com.hotent.bpmx.persistence.BpmxBaseTest;
import com.hotent.bpmx.persistence.model.BpmDefData;

public class BpmDefDataDaoTest extends BpmxBaseTest{
	@Resource
	private BpmDefDataDao bpmDefDataDao;
	
	@Test
	
	public void testCrud(){
		BpmDefData bpmDefData=new BpmDefData();
		Integer randId=new Double(100000*Math.random()).intValue();
		bpmDefData.setId(idGenerator.getSuid());
		bpmDefData.setDesigner(DesignerType.ECLIPSE);
		bpmDefData.setBpmnXml("DesignerType.ECLIPSE");
		bpmDefData.setDefXml("DesignerType.ECLIPSE");
		//创建一实体
		bpmDefDataDao.create(bpmDefData);
        Assert.assertNotNull(bpmDefData.getId());
        logger.debug("bpmDefData1:"+ bpmDefData.getId());
		
		//删除一实体
		//bpmDefDataDao.remove(bpmDefData.getId());
	}
	
}
