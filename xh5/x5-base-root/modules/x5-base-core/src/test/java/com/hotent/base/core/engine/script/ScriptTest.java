package com.hotent.base.core.engine.script;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.hotent.base.core.test.BaseTestCase;


@ContextConfiguration(locations={"classpath:conf/x5-base-core-test.xml"})
public class ScriptTest extends BaseTestCase {
	@Resource
	ScriptImplTest scriptImplTest;
	@Resource
	GroovyScriptEngine groovyScriptEngine;
	@Test
	public void test(){
		String script =  "return scriptImplTest.add(1, 2);";
		int  i = groovyScriptEngine.executeInt(script, null);
		
		System.out.println(i);
	}
	
}
