package com.hotent.org.api.context;

import java.util.Locale;

import com.hotent.org.api.model.User;

public class BaseCurrentContext implements CurrentContext {

	/**
	 * 存放当前用户登录的语言环境
	 */
	protected static ThreadLocal<Locale> currentLocale = new ThreadLocal<Locale>();
	
	/**
	 * 当前上下文用户。
	 */
	protected static ThreadLocal<User> currentUser = new ThreadLocal<User>();
	
	/**
	 * 获取当前语言环境
	 * @return
	 */
	public  Locale getLocale(){
		if(currentLocale.get()!=null){
			return currentLocale.get();
		}
		setLocale(new Locale("zh","CN"));
		return currentLocale.get();
	}
	
	
	/**
	 * 返回当前用户ID
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public  String getCurrentUserId(){
		User user=getCurrentUser();
		if(user==null) return null;
		return user.getUserId();
	}
	

	@Override
	public User getCurrentUser() {
		return currentUser.get();
	}

	@Override
	public void clearCurrentUser() {
		currentUser.remove();
	}

	@Override
	public void setCurrentUser(User user) {
		currentUser.set(user);
	}

	

	@Override
	public void clearLocale() {
		currentLocale.remove();
	}


	@Override
	public void setLocale(Locale local) {
		currentLocale.set(local);
		
	}


}
