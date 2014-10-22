package com.hotent.org.api.context;

import com.hotent.org.api.service.AttributeService;
import com.hotent.org.api.service.AttributeValueService;
import com.hotent.org.api.service.DimensionService;
import com.hotent.org.api.service.GroupService;
import com.hotent.org.api.service.UserService;

/**
 * <pre> 
 * 描述：组织架构服务引擎
 * 构建组：x5-org-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-11-18-下午12:12:45
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public interface IdentityContext {
	/**
	 * 获取UserService实例
	 * @return {@link UserService}
	 */
	UserService getUserService();
	
	/**
	 * 获取GroupService实例
	 * @return {@link GroupService}
	 */
	GroupService getGroupService();
	
	/**
	 * 获取AttributeService实例
	 * @return {@link AttributeService}
	 */
	AttributeService getAttributeService();
	
	/**
	 * 获取AttributeValueService实例
	 * @return {@link AttributeValueService}
	 */
	AttributeValueService getAttributeValueService();
	
	/**
	 * 获取DimensionService实例
	 * @return {@link DimensionService}
	 */
	DimensionService getDimensionService();
}
