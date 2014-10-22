package com.hotent.org.api.service;

import java.util.List;

import com.hotent.base.api.Page;
import com.hotent.org.api.model.Dimension;

/**
 * 
 * <pre> 
 * 描述：维度服务
 * 构建组：x5-org-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-11-20-上午11:52:54
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public interface DimensionService {
	/**
	 * 通过维度key获取维度
	 * @param dimKey 维度key
	 * @return {@link Dimension}
	 */
	Dimension getByDimKey(String dimKey);
	
	
	/**
	 * 通过维度id获取维度
	 * @param dimId 维度id
	 * @return {@link Dimension}
	 */
	Dimension getByDimId(String dimId);
	
	
	/**
	 * 获取到所有的用户组维度
	 * @return List&lt;{@link Dimension}>
	 */
	List<Dimension> getAll();
	
	/**
	 * 分页查询所有的用户组维度
	 * @return List&lt;{@link Dimension}>
	 */
	List<Dimension> getAll(Page page);
}
