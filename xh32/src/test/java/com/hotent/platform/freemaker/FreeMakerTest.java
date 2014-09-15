package com.hotent.platform.freemaker;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.hotent.BaseTestCase;
import com.hotent.core.engine.FreemarkEngine;

import freemarker.template.TemplateException;

public class FreeMakerTest  extends BaseTestCase {
	
	@Resource
	FreemarkEngine freeMaker;
	
	@org.junit.Test
	public void merge() throws TemplateException, IOException{
		Map map=new HashMap();
		map.put("to", 5);
		String str="<#list 1..${to} as x>${x}</#list>";
		String out= freeMaker.parseByStringTemplate(map, str);
		System.out.println(out);
	}

}
