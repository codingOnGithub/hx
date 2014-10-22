package com.hotent.org.api.context;

import java.util.Locale;

import com.hotent.org.api.context.CurrentContext;
import com.hotent.org.api.model.User;

/**
 * 获取上下文数据对象的工具类。
 * <pre> 
 * 
 * 构建组：x5-org-core
 * 作者：ray
 * 邮箱:zhangyg@jee-soft.cn
 * 日期:2013-12-20-上午9:38:46
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class ContextUtil  {

	private static CurrentContext currentContext;
	
	
	public void setCurrentContext(CurrentContext _currentContext){
		ContextUtil.currentContext=_currentContext;
	}
	
	/**
	 * 获取当前执行人
	 * @return 
	 * User
	 * @exception 
	 * @since  1.0.0
	 */
	public static User getCurrentUser(){
		return currentContext.getCurrentUser();
	}
	
	/**
	 * 获取当前Locale。
	 * @return 
	 * Locale
	 * @exception 
	 * @since  1.0.0
	 */
	public static Locale getLocale(){
		return currentContext.getLocale();
	}

	/**
	 * 清除当期执行人。
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public static void clearCurrentUser(){
		currentContext.clearCurrentUser();
		
	}
	
	/**
	 * 设置当前执行人。
	 * @param user 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public static void setCurrentUser(User user){
		currentContext.setCurrentUser(user);
	}
	
	/**
	 * 设置Locale。
	 * @param locale 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public static void setLocale(Locale locale){
		currentContext.setLocale(locale);
	}
	
	/**
	 * 清除Local。
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public static void cleanLocale(){
		currentContext.clearLocale();
	}
	
	
}
