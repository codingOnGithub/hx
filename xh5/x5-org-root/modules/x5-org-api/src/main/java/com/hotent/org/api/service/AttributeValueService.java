/**
 * @版权所有 2013-2015 广州宏天软件有限公司
 */
package com.hotent.org.api.service;

import java.util.List;

import com.hotent.org.api.model.AttributeValue;

/**
 * @功能描述：扩展属性值服务
 * @开发公司：广州宏天软件有限公司
 * @作者：Winston Yan
 * @邮箱：yancm@jee-soft.cn
 * @创建时间：2013-11-29 下午3:03:09
 */
public interface AttributeValueService {
	/**
	 * 通过userId获取扩展属性
	 * @param userId 用户key
	 * @return List&lt;{@link AttributeValue}>
	 */
	List<AttributeValue> queryByUserId(String userId);
	
	/**
	 * 通过groupId获取扩展属性
	 * @param groupId 用户组key
	 * @return List&lt;{@link AttributeValue}>
	 */
	List<AttributeValue> queryByGroupId(String groupId);
}
