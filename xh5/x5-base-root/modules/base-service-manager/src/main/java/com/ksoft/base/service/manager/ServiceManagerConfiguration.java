package com.ksoft.base.service.manager;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ksoft.base.service.manager.util.AssertValue;


public class ServiceManagerConfiguration {

	private static ServiceManagerConfiguration instance;

	private ApplicationContext cxt;

	private static String[] springContextXml = { "service-app-context.xml" };

	private static Logger logger = Logger.getLogger(ServiceManagerConfiguration.class);

	public ServiceManagerConfiguration() {
		logger.info("Welcome to base service manager !");
	}

	public static ServiceManagerConfiguration getInstance() {
		if (!AssertValue.isNotNull(instance)) {

			instance = new ServiceManagerConfiguration();

			// 载入Spring
			ApplicationContext cxt = new ClassPathXmlApplicationContext(
					springContextXml);

			instance.setCxt(cxt);
		}
		return instance;
	}

	public ApplicationContext getCxt() {
		return cxt;
	}

	public void setCxt(ApplicationContext cxt) {
		this.cxt = cxt;
	}

	public <T> T getBean(Class<T> beanClass) {
		return this.cxt.getBean(beanClass);
	}
}
