/**
 * 描述：TODO
 * 包名：com.hotent.base.api.query
 * 文件名：QueryRelation.java
 * 作者：win-mailto:chensx@jee-soft.cn
 * 日期2014-1-3-上午11:37:18
 *  2014广州宏天软件有限公司版权所有
 * 
 */
package com.hotent.base.api.query;

/**
 * <pre> 
 * 描述：查询字段之间的关系枚举
 * 构建组：x5-base-api
 * 作者：Winston Yan
 * 邮箱：yancm@jee-soft.cn
 * 日期：2014-1-3-上午11:37:18
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public enum FieldRelation {
	AND("AND"),OR("OR"),NOT("NOT");
	private String val;
	FieldRelation(String _val) {
			val = _val;
	}
	public String value(){
		return val;
	}
}
