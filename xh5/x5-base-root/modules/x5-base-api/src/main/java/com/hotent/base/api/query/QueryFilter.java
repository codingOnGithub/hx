package com.hotent.base.api.query;

import java.util.List;
import java.util.Map;
import com.hotent.base.api.Page;

/**
 * 
 * <pre> 
 * 描述：组合条件查询过滤
 * 构建组：x5-base-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-1-3-下午4:01:18
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public interface QueryFilter {
	/**
	 * 返回分页信息
	 * @return
	 */
	public Page getPage();
	/**
	 * 返回字段组合查询逻辑
	 * @return
	 */
	public FieldLogic getFieldLogic();

	/**
	 * 返回组合的参数映射
	 * @return
	 */
	public Map<String,Object> getParams();
	
	/**
	 * 返回字段排序列表
	 * @return
	 */
	public List<FieldSort> getFieldSortList();

}
