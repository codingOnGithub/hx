package com.hotent.bpmx.api.service;

import java.util.List;

import com.hotent.bpmx.api.model.identity.BpmIdentity;
import com.hotent.bpmx.api.service.handler.BpmIdentityHandler;
import com.hotent.org.api.model.User;

/**
 * 
 * 描述：流程组织架构服务
 * 构建组：x5-bpmx-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-11-8-下午3:51:04
 * 版权：广州宏天软件有限公司版权所有
 */
public interface BpmIdentityService {

	/**
	 * 通过用户组类型及用户组ID获取用户组下的具体用户
	 * @param groupType
	 * @param groupId
	 * @return 
	 * List<User>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<User> getUsersByBpmIdentitity(BpmIdentity bpmIdentity);
	
	/**
	 * 
	 * TODO方法名称描述
	 * @param bpmIdentitys
	 * @return 
	 * List<User>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<User> getUsersByBpmIdentities(List<BpmIdentity> bpmIdentitys);
	
	/**
	 * 返回识别处理器集合
	 * @return 
	 * List<BpmIdentityHandler>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<BpmIdentityHandler> getBpmIdentityHandlers();
	/**
	 * 按名称取得Handler
	 * @param handlerName
	 * @return 
	 * BpmIdentityHandler
	 * @exception 
	 * @since  1.0.0
	 */
	public BpmIdentityHandler getBpmIdentityHandlerByName(String handlerName);
	
}
