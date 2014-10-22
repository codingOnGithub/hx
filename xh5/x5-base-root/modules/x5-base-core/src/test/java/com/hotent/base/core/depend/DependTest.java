package com.hotent.base.core.depend;

import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import com.hotent.base.core.test.BaseTestCase;


@ContextConfiguration({"classpath:conf/x5-base-core-test.xml"})
public class DependTest extends BaseTestCase implements ApplicationContextAware{
	
	


	private ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		
			this.context=context;
	}
	
	@Test
	public void testDepend(){
		Person p=(Person) this.context.getBean("person");
		p.sayHello();
	}

}
