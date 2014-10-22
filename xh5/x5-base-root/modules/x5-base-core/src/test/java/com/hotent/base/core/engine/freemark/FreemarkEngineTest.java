package com.hotent.base.core.engine.freemark;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.hotent.base.core.test.BaseTestCase;

/**
 * Freemark 引擎 测试类
 * @author hugh zhuang
 *
 */
@ContextConfiguration(locations = { "classpath:conf/x5-base-core-test.xml" })
public class FreemarkEngineTest extends BaseTestCase {
	@Resource
	FreemarkEngine freemarkEngine;

	@Test
	public void mergeTemplateIntoString() throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		

		model.put("tableIdCode", "xxxxx");
		model.put("showExplain", true);
		model.put("showPageSize", true);
		model.put("baseHref", "XXX");

		
		String s = freemarkEngine.mergeTemplateIntoString("test.ftl", model);
		
		
		System.out.println("输出字符串:"+s);
	}
	
	
	
	@Test
	public void parseByStringTemplate() throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();

		model.put("test", "xxxxx");
		String templateSource ="hello ${test}";
		String s = freemarkEngine.parseByStringTemplate(templateSource, model);
		
		
		System.out.println("输出字符串:"+s);
	}
}
