package com.hotent.bpmx.core;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hotent.bpmx.api.engine.BpmxEngineFactory;
/**
 * <pre> 
 * 描述：BPMX
 * 构建组：x5-bpmx-core
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-11-27-下午6:06:07
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class BpmxCoreConfig {
	private final static Logger logger = Logger.getLogger(BpmxCoreConfig.class);

	private static BpmxCoreConfig instance;

	private ApplicationContext cxt;

	private static String[] springContextXml = { "classpath:conf/x5-bpmx-config.xml","classpath:conf/x5-bpmx-activiti.xml","classpath:conf/x5-base-db.xml" };

	private BpmxCoreConfig() {
		logger.info("Welcome to bpmx core !");
	}

	public static BpmxCoreConfig getInstance() {
		return BpmxCoreConfig.getInstance(springContextXml);
	}

	public static BpmxCoreConfig getInstance(String[] springContext) {

		if (instance == null) {
			logger.debug("BpmxCoreConfig对象还未创建，执行初始化操作。");

			instance = new BpmxCoreConfig();

			// 载入Spring
			ApplicationContext cxt = new ClassPathXmlApplicationContext(springContext);

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

	public BpmxEngineFactory getBpmxContext() {
		return this.getBean(BpmxEngineFactory.class);
	}

}
