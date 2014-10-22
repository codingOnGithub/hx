package com.hotent.org.api.service;

import java.util.List;
import com.hotent.org.api.model.User;
/**
 * <pre> 
 * 描述：用户服务接口类
 * 构建组：x5-org-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-11-18-下午12:18:54
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public interface UserService {
	/**
	 * 通过用户id获取用户
	 * @param id 用户id
	 * @return {@link User}
	 */
	User getById(String id);
	
	/**
	 * 通过用户account获取用户
	 * @param account 用户account
	 * @return {@link User}
	 */
	User getByAccount(String account);
	
	/**
	 * 通过用户关系获取用户
	 * @param userKey 用户key
	 * @param relType 用户关系类型
	 * @return List&lt;{@link User}>
	 */
	List<User> queryByUserRelation(String userKey,String relType);
	
	/**
	 * 通过用户组关系获取用户
	 * @param groupId 用户组key
	 * @param relType	用户组关系类型
	 * @return List&lt;{@link User}>
	 */
	List<User> queryByGroupRelation(String groupId,String relType);
	
	/**
	 * 通过用户扩展属性获取用户
	 * @param key 扩展属性key
	 * @param value	扩展属性值
	 * @return List&lt;{@link User}>
	 */
	List<User> queryUserByAttributeValue(String key,Object value);
}
