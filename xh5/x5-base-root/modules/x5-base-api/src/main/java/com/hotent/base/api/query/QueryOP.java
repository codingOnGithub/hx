/**
 * 描述：TODO
 * 包名：com.hotent.base.api.query
 * 文件名：QueryOP.java
 * 作者：win-mailto:chensx@jee-soft.cn
 * 日期2014-1-3-上午11:17:00
 *  2014广州宏天软件有限公司版权所有
 * 
 */
package com.hotent.base.api.query;

/**
 * <pre> 
 * 描述：查询字段和值的操作类型枚举
 * 构建组：x5-base-api
 * 作者：Winston Yan
 * 邮箱：yancm@jee-soft.cn
 * 日期：2014-1-3-上午11:17:00
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public enum QueryOP {
	EQUAL("EQ"),LESS("LT"),GREAT("GT"),LESS_EQUAL("LE"),GREAT_EQUAL("GE"),LIKE("LK"),LEFT_LIKE("LFK"),RIGHT_LIKE("RHK"),IS_NULL("ISNULL"),NOTNULL("NOTNULL"),IN("IN"),BETWEEN("BETWEEN");
	private String val;
	QueryOP(String _val){
		val = _val;
	}
	public String value(){
		return val;
	}
}
