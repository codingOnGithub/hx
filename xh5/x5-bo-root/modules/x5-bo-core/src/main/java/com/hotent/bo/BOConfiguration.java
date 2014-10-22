package com.hotent.bo;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import com.hotent.bo.context.BOContext;

/**
 * BO模块配置类
 * <pre> 
 * 构建组：x5-bo-core
 * 作者：heyifan
 * 邮箱:heyf@jee-soft.cn
 * 日期:2014-1-26-上午10:45:49
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class BOConfiguration implements ApplicationContextAware{
	private static ApplicationContext context;
	private static BOConfiguration boConfiguration = new BOConfiguration();
	public static BOConfiguration getInstance(){
		return boConfiguration;
	}
	
	/**
	 * 通过spring容器获取bean
	 * @param beanClass
	 * @return 
	 * T
	 * @exception 
	 * @since  1.0.0
	 */
	public static <T> T getBean(Class<T> beanClass){
		return context.getBean(beanClass);
	}
	
	/**
	 * 通过spring容器获取bean
	 * @param beanId
	 * @return 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
	public static Object getBean(String  beanId){
		if(context.containsBean(beanId)){
			return context.getBean(beanId);
		}
		else {
			return null;
		}
	}
	
	/**
	 * 获取BO模块上下文
	 * @return 
	 * BOContext
	 * @exception 
	 * @since  1.0.0
	 */
	public static BOContext getContext() {
		return getBean(BOContext.class);
	}
	
	/**
	 * 获取是否对BOAttribute的输入值进行验证
	 * @return 
	 * Boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public static Boolean getValidAttribute(){
		return true;
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		context = arg0;
	}
}
