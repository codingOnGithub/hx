package com.hotent.org.core;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hotent.org.api.context.IdentityContext;

/**
 * 
 * <pre> 
 * 描述：组织架构配置类
 * 构建组：x5-org-core
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-1-9-上午9:10:34
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class OrgConfiguration{
	
	private final static Logger logger = Logger.getLogger(OrgConfiguration.class);

	private static String[] springContextXml = { "classpath:conf/x5-org-core.xml"};
	
		
	public IdentityContext identityContext=null;
	
	public static OrgConfiguration configuration=null;
	
	private static ApplicationContext appContext;

	private OrgConfiguration() {
		logger.info("Welcome to OrgConfiguration Core Engine!");
	}

	public static OrgConfiguration getInstance() {
		return getInstance(springContextXml);
	}

	public static OrgConfiguration getInstance(String[] springContext) {
		if(configuration==null){
			configuration=new OrgConfiguration();
			appContext = new ClassPathXmlApplicationContext(springContext);
		}
		return configuration;
	}

	public <T> T getBean(Class<T> beanClass) {
		return appContext.getBean(beanClass);
	}

	public IdentityContext getIdentityContext() {
		return this.getBean(IdentityContext.class);
	}
	
}

