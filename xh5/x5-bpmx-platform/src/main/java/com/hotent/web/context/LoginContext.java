package com.hotent.web.context;

import java.util.Locale;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hotent.org.api.context.CurrentContext;
import com.hotent.org.api.model.User;

/**
 * 
 * <pre> 
 * 描述：获取登录信息
 * 构建组：x5-bpmx-platform
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-1-4-下午4:07:20
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class LoginContext implements CurrentContext {
	
	
	
	
	/**
	 * 存放当前用户登录的语言环境
	 */
	private static ThreadLocal<Locale> currentLocale = new ThreadLocal<Locale>();
	
	/**
	 * 当前上下文用户。
	 */
	private static ThreadLocal<User> currentUser = new ThreadLocal<User>();
	
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
		if(currentUser.get()!=null){
			User user=currentUser.get();
			return user;
		}
		SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext != null) {
            Authentication auth = securityContext.getAuthentication();
            if (auth != null) {
                Object principal = auth.getPrincipal();
                if (principal instanceof User) {
                	return (User)principal;
                }
            } 
        }
        return null;
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
