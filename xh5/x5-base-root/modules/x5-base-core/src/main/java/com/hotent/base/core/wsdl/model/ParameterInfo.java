package com.hotent.base.core.wsdl.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 参数类型
 * 
 * <pre>
 * 构建组：x5-base-core
 * 作者：hugh zhuang
 * 邮箱:zhuangxh@jee-soft.cn
 * 日期:2014-2-14-上午11:51:51
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class ParameterInfo {
	/** 复杂类型 */
	public static final Short COMPLEX_YES = 1;
	/** 不是复杂类型 */
	public static final Short COMPLEX_NO = 0;
	/** 显示类型 */
	public static final Short SHOW_YES = 0;
	/** 不显示类型 */
	public static final Short SHOW_NO = 1;

	// 参数名称
	private String name;
	// 参数类型
	private String type;
	// 是否是复杂类型，默认为不是0
	private Short isComplext = COMPLEX_NO;
	// 是否显示，默认为是0，不是1
	private Short isShow = SHOW_NO;
	// 复杂深度
	private String deep;
	// 是否为列表
	private Boolean isList = false;
	// 复杂类型数组
	private Map<String, ParameterInfo> complextParams = new HashMap<String, ParameterInfo>();
	// 已经处理过的复杂类型
	private Map<String, String> parentComplext = new HashMap<String, String>();

	/**
	 * name
	 * 
	 * @return the name
	 * @since 1.0.0
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * type
	 * 
	 * @return the type
	 * @since 1.0.0
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * isComplext
	 * 
	 * @return the isComplext
	 * @since 1.0.0
	 */
	public Short getIsComplext() {
		return isComplext;
	}

	/**
	 * @param isComplext
	 *            the isComplext to set
	 */
	public void setIsComplext(Short isComplext) {
		this.isComplext = isComplext;
	}

	/**
	 * isShow
	 * 
	 * @return the isShow
	 * @since 1.0.0
	 */

	public Short getIsShow() {
		return isShow;
	}

	/**
	 * @param isShow
	 *            the isShow to set
	 */
	public void setIsShow(Short isShow) {
		this.isShow = isShow;
	}

	/**
	 * deep
	 * 
	 * @return the deep
	 * @since 1.0.0
	 */

	public String getDeep() {
		return deep;
	}

	/**
	 * @param deep
	 *            the deep to set
	 */
	public void setDeep(String deep) {
		this.deep = deep;
	}

	/**
	 * isList
	 * 
	 * @return the isList
	 * @since 1.0.0
	 */
	public Boolean getIsList() {
		return isList;
	}

	/**
	 * @param isList
	 *            the isList to set
	 */
	public void setIsList(Boolean isList) {
		this.isList = isList;
	}

	/**
	 * complextParams
	 * 
	 * @return the complextParams
	 * @since 1.0.0
	 */
	public Map<String, ParameterInfo> getComplextParams() {
		return complextParams;
	}

	/**
	 * @param complextParams
	 *            the complextParams to set
	 */
	public void setComplextParams(Map<String, ParameterInfo> complextParams) {
		this.complextParams = complextParams;
	}

	/**
	 * parentComplext
	 * 
	 * @return the parentComplext
	 * @since 1.0.0
	 */
	public Map<String, String> getParentComplext() {
		return parentComplext;
	}

	/**
	 * @param parentComplext
	 *            the parentComplext to set
	 */
	public void setParentComplext(Map<String, String> parentComplext) {
		this.parentComplext = parentComplext;
	}
}
