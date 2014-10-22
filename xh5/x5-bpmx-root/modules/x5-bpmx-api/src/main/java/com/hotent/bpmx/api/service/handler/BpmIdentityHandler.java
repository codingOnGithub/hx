package com.hotent.bpmx.api.service.handler;

import java.util.List;

import com.hotent.org.api.model.IdentityType;

/**
 * 
 * 描述：节点人员运算处理器接口,所有需要加入至节点的人员运算的方式均需要实现该节口，
 * 如按角色查找人员（BpmIdentityHandler)
 * 构建组：x5-bpmx-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-11-11-下午5:46:00
 * 版权：广州宏天软件有限公司版权所有
 */
public interface BpmIdentityHandler {
	
	/**
	 * 返回节点的用户类型
	 * @param setValue 用户的设置值字符串
	 * @return 
	 * List<UserType>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<IdentityType> getNodeUsers(String setValue);
	
	/**
	 * 
	 * Handler描述
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getDescription();
	/**
	 * Handler的识别名称
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getName();
}
