/**
 * @版权所有 2013-2015 广州宏天软件有限公司
 */
package com.hotent.org.api.service;

import java.util.List;

import com.hotent.base.api.Page;
import com.hotent.org.api.model.Attribute;

/**
 * @功能描述：扩展属性服务
 * @开发公司：广州宏天软件有限公司
 * @作者：Winston Yan
 * @邮箱：yancm@jee-soft.cn
 * @创建时间：2013-11-29 上午11:44:49
 */
public interface AttributeService {
	public final static class AttributeBelongType{
		/**
		 * 扩展属性属于用户
		 */
		public final static String USER = "user";
		/**
		 * 扩展属性属于用户组
		 */
		public final static String GROUP = "group";
	} 
	/**
	 * 通过扩展属性key获取扩展属性
	 * @param attrKey 扩展属性key
	 * @return {@link Attribute}
	 */
	Attribute getByAttrKey(String attrKey);
	
	/**
	 * 通过扩展属性Id获取扩展属性
	 * @param attrKey 扩展属性Id
	 * @return {@link Attribute}
	 */
	Attribute getByAttrId(String attrId);
	
	/**
	 * 查询所有的扩展属性
	 * @return List&lt;{@link Attibute}>
	 */
	List<Attribute> queryAll();
	
	/**
	 * 查询所有的扩展属性
	 * @return List&lt;{@link Attibute}>
	 */
	List<Attribute> queryAll(Page page);
	
	/**
	 * 通过归属类型查询扩展属性
	 * @param belongType 扩展属性归属类型
	 * @return List&lt;{@link Attibute}>
	 */
	List<Attribute> queryByBelongType(String belongType);
}
