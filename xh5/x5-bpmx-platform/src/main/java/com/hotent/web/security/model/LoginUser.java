package com.hotent.web.security.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hotent.org.api.model.User;
import com.hotent.org.persistence.model.DefaultUser;
/**
 * <pre> 
 * 描述：登录用户实体
 * 构建组：x5-bpmx-platform
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-1-4-下午5:30:48
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class LoginUser extends DefaultUser implements UserDetails{

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = 1L;

	public Collection<? extends GrantedAuthority> getAuthorities() {
		GrantedAuthority au=new SimpleGrantedAuthority("ROLE_LOGIN");
		ArrayList<GrantedAuthority> list=new ArrayList<GrantedAuthority>();
		list.add(au);
		return list;
	}
	
	public LoginUser() {
		
	}
	
	public LoginUser(DefaultUser user){
		this.account=user.getAccount();
		this.pwd=user.getPassword();
		this.fullname=user.getFullname();
		this.status=user.getStatus();
		this.sex=user.getSex();
	}

	@Override
	public String getPassword() {
		return this.pwd;
	}
	@Override
	public String getUsername() {
		return this.account;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return User.STATUS_ENABLED.equals(this.status)?true:false;
	}
	
	

}
