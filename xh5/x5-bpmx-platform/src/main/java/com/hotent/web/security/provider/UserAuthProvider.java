package com.hotent.web.security.provider;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.hotent.org.api.service.UserService;
import com.hotent.org.persistence.model.DefaultUser;
import com.hotent.web.security.model.LoginUser;
/**
 * 
 * <pre> 
 * 描述：TODO
 * 构建组：x5-bpmx-platform
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-1-7-下午3:00:51
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class UserAuthProvider implements UserDetailsService{
	private Log logger=LogFactory.getLog(UserAuthProvider.class);
	@Resource
	UserService userService;
	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LoginUser loginUser=null;
		try{
			DefaultUser user=(DefaultUser)userService.getByKey(username);
			if(user==null) return null;
			 loginUser=new LoginUser(user);
		}catch(Exception ex){
			logger.error(ex.getMessage());
		}
		return loginUser;
	}

}
