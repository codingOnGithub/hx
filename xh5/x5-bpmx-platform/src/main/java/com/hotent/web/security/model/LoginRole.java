package com.hotent.web.security.model;

import org.springframework.security.core.GrantedAuthority;

import com.hotent.org.persistence.model.DefaultGroup;
/**
 * <pre> 
 * 描述：获取登录角色
 * 构建组：x5-bpmx-platform
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-1-4-下午5:41:54
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class LoginRole extends DefaultGroup implements GrantedAuthority{

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = 1L;

	public String getAuthority() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
