/**
 * 描述：TODO
 * 包名：com.hotent.bpmx.api.plugin.org.def
 * 文件名：OrgPlugin.java
 * 作者：win-mailto:chensx@jee-soft.cn
 * 日期2014-2-23-下午10:07:12
 *  2014广州宏天软件有限公司版权所有
 * 
 */
package com.hotent.bpmx.api.plugin.org.def;

import java.util.Map;

/**
 * <pre> 
 * 描述：TODO
 * 构建组：x5-bpmx-api
 * 作者：Winston Yan
 * 邮箱：yancm@jee-soft.cn
 * 日期：2014-2-23-下午10:07:12
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public interface OrgPluginDef {
	public Map<String,Object> getAttrs();
	
	/**
	 * 人员运算类型-与运算=and
	 */
	public static final String CAL_TYPE_AND="and";
	/**
	 * 人员运算类型-或运算=or
	 */
	public static final String CAL_TYPE_OR="or";
	/**
	 * 人员运算类型-非运算=not
	 */
	public static final String CAL_TYPE_NOT="not";
	/**
	 * 用户抽取类型
	 * @return  String
	 */
	public String getExtractType();
	
	/**
	 * 获取显示的名称。 
	 * @return String
	 */
	public String getDisplayName();
	/**
	 * 任务节点的人员运算类型，如And或Or或Not
	 * @return String
	 */
	public String getCalType();
	/**
	 * 取得用户的运算序号
	 * @return String
	 */
	public String getSn();
}
