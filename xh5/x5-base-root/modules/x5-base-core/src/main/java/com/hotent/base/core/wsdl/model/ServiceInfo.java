package com.hotent.base.core.wsdl.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlSchemaType;

/**
 * wsdl请求的信息
 * 
 * <pre>
 * 构建组：x5-base-core
 * 作者：hugh zhuang
 * 邮箱:zhuangxh@jee-soft.cn
 * 日期:2014-2-14-上午11:56:51
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class ServiceInfo {
	// 名称
	private String name;
	// wsdl url地址
	private String wsdlUrl;
	// ws的http请求地址
	private String httpAddress;
	private Map<String, OperationInfo> operations = new TreeMap<String, OperationInfo>();
	/* 类型数组 */
	private List<XmlSchemaType> complexTypes = new ArrayList<XmlSchemaType>();
	// 命名空间
	private String targetNamespace;

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
	 * wsdlUrl
	 * 
	 * @return the wsdlUrl
	 * @since 1.0.0
	 */
	public String getWsdlUrl() {
		return wsdlUrl;
	}

	/**
	 * @param wsdlUrl
	 *            the wsdlUrl to set
	 */
	public void setWsdlUrl(String wsdlUrl) {
		this.wsdlUrl = wsdlUrl;
	}

	/**
	 * httpAddress
	 * 
	 * @return the httpAddress
	 * @since 1.0.0
	 */
	public String getHttpAddress() {
		return httpAddress;
	}

	/**
	 * @param httpAddress
	 *            the httpAddress to set
	 */
	public void setHttpAddress(String httpAddress) {
		this.httpAddress = httpAddress;
	}

	/**
	 * operations
	 * 
	 * @return the operations
	 * @since 1.0.0
	 */
	public Map<String, OperationInfo> getOperations() {
		return operations;
	}

	/**
	 * @param operations
	 *            the operations to set
	 */
	public void setOperations(Map<String, OperationInfo> operations) {
		this.operations = operations;
	}

	/**
	 * complexTypes
	 * 
	 * @return the complexTypes
	 * @since 1.0.0
	 */
	public List<XmlSchemaType> getComplexTypes() {
		return complexTypes;
	}

	/**
	 * @param complexTypes
	 *            the complexTypes to set
	 */
	public void setComplexTypes(List<XmlSchemaType> complexTypes) {
		this.complexTypes = complexTypes;
	}

	/**
	 * targetNamespace
	 * 
	 * @return the targetNamespace
	 * @since 1.0.0
	 */
	public String getTargetNamespace() {
		return targetNamespace;
	}

	/**
	 * @param targetNamespace
	 *            the targetNamespace to set
	 */
	public void setTargetNamespace(String targetNamespace) {
		this.targetNamespace = targetNamespace;
	}
}
