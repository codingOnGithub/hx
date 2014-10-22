package com.hotent.org.api.service;

import java.util.List;

import com.hotent.org.api.model.Group;

/** 
 * 描述：组服务接口
 * 构建组：x5-bpmx-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-11-15-下午1:47:54
 * 版权：广州宏天软件有限公司版权所有
 */
public interface GroupService{
	/**
	 * 通过用户组key获取用户组
	 * @param groupKey 用户组key
	 * @return {@link Group}
	 */
	Group getByGroupKey(String groupKey);
	
	/**
	 * 获取用户组的直接父节点
	 * @param groupId 用户组Id
	 * @return {@link Group}
	 */
	Group getSuperior(String groupId);
	
	/**
	 * 获取用户组的父节点	
	 * @param groupId 用户组Id
	 * @return List&lt;{@link Group}>
	 */
	List<Group> queryParents(String groupId);
	
	/**
	 * 获取用户组的同级节点
	 * @param groupId 用户组Id
	 * @return List&lt;{@link Group}>
	 */
	List<Group> queryBrothers(String groupId);
	
	/**
	 * 获取用户组的子节点
	 * @param groupId 用户组Id
	 * @param descendants 是否包含更低层次的后代节点
	 * @return List&lt;{@link Group}>
	 */
	List<Group> queryChildren(String groupId,Boolean descendants);
	
	/**
	 * 通过用户组关系获取用户组
	 * @param groupId 用户组Id
	 * @param relType 用户组关系类型
	 * @return List&lt;{@link Group}>
	 */
	List<Group> queryByGroupRelation(String groupId,String relType);
	
	/**
	 * 通过与用户的关系获取用户组
	 * @param userId 用户组Id
	 * @param relType 用户组与用户的关系类型
	 * @return List&lt;{@link Group}>
	 */
	List<Group> queryByUserRelation(String userId,String relType);
	
	/**
	 * 通过扩展属性获取用户组
	 * @param key 用户组扩展属性key
	 * @param value 用户组扩展属性值
	 * @return List&lt;{@link Group}>
	 */
	List<Group> queryGroupByAttributeValue(String key,Object value);

	/**
	 * 通过用户组Id获取用户组
	 * @param groupId 用户组Id
	 * @return {@link Group}
	 */
	Group getByGroupId(String groupId);
}
