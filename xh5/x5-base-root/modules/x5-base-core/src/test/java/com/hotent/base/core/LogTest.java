package com.hotent.base.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Test;

public class LogTest {
	
	Log log=LogFactory.getLog(LogTest.class);
	
	@Test
	public void logTest(){
		DOMConfigurator.configure("E:/work/x5/x5-base-root/modules/x5-base-core/src/test/resources/log4j.xml");//加载.xml文件
		log.error("ddddddddddddddddddddddddddddddddddd");
	}

}
