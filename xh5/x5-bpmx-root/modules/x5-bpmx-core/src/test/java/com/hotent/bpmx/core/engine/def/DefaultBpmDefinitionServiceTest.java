package com.hotent.bpmx.core.engine.def;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import com.hotent.bpmx.api.constant.DesignerType;
import com.hotent.bpmx.api.model.process.def.BpmDefinition;
import com.hotent.bpmx.persistence.BpmxBaseTest;
import com.hotent.bpmx.persistence.manager.BpmDefinitionManager;
import com.hotent.bpmx.persistence.model.DefaultBpmDefinition;

public class DefaultBpmDefinitionServiceTest extends BpmxBaseTest{
	@Resource
	DefaultBpmDefinitionService defaultBpmDefinitionService;
	
	@Resource
	BpmDefinitionManager bpmDefinitionManager;
	
	private static String defKey ="defkey" +Calendar.getInstance().getTimeInMillis();
	
	@Test
	//@Rollback(true)		
	public void deploy() throws IOException{
		BpmDefinition bpmDefinition = new DefaultBpmDefinition();		
		bpmDefinition.setName("zhangyg");
		bpmDefinition.setDefKey("demo3");
		String defXml = readXml("/test/example1.bpmn");
		bpmDefinition.setDesigner(DesignerType.ECLIPSE.name());
		bpmDefinition.setDefXml(defXml);
		bpmDefinition.setInstSubjectRule("RULE");
		
		//第一次发布
		boolean result = defaultBpmDefinitionService.deploy(bpmDefinition);	
		Assert.assertTrue(result);
		
//		//再次发布
//		result = defaultBpmDefinitionService.deploy(bpmDefinition);
//		Assert.assertTrue(result);
	}
	
	//@Test
	public void updateBpmDefinition() throws IOException{
		DefaultBpmDefinition defaultBpmDefinition = bpmDefinitionManager.getMainByDefKey("defkey1387271133622");
		String defXml = readXml("/test/example1.bpmn");
		defaultBpmDefinition.setDefXml(defXml);
		defaultBpmDefinitionService.updateBpmDefinition(defaultBpmDefinition);
	}
	
	private DefaultBpmDefinition getMain(String defKey){
		List<DefaultBpmDefinition> defs = bpmDefinitionManager.getAll();
		for(DefaultBpmDefinition def:defs){
			if(def.getDefKey().equals(defKey) && def.isMain()){
				DefaultBpmDefinition defWithSub = bpmDefinitionManager.getWithCascade(def.getDefId());
				return defWithSub;
			}
		}
		return null;
	}
	
	private String readXml(String fileRelatePath) throws IOException{
		String defXmlPath = Class.class.getClass().getResource("/").getPath() + fileRelatePath;
		File file = new File(defXmlPath);
		String defXml = FileUtils.readFileToString(file, "UTF-8");
		return defXml;
	}
	
	
}
