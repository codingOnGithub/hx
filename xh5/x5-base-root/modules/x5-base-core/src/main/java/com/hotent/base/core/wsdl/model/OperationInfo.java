package com.hotent.base.core.wsdl.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 操作类型 信息
 * 
 * <pre>
 * 构建组：x5-base-core
 * 作者：hugh zhuang
 * 邮箱:zhuangxh@jee-soft.cn
 * 日期:2014-2-14-上午11:48:43
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class OperationInfo {
	// 操作名称
	private String operationName = null;
	// 输入参数
	private List<ParameterInfo> inputParams = new ArrayList<ParameterInfo>();
	// 返回
	private ParameterInfo returnType = null;
	// 输出参数
	private List<ParameterInfo> outputParams = new ArrayList<ParameterInfo>();
	// 输入参数的soapAction
	private String inputAction = "api";

	/**
	 * operationName
	 * 
	 * @return the operationName
	 * @since 1.0.0
	 */

	public String getOperationName() {
		return operationName;
	}

	/**
	 * @param operationName
	 *            the operationName to set
	 */
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	/**
	 * inputParams
	 * 
	 * @return the inputParams
	 * @since 1.0.0
	 */

	public List<ParameterInfo> getInputParams() {
		return inputParams;
	}

	/**
	 * @param inputParams
	 *            the inputParams to set
	 */
	public void setInputParams(List<ParameterInfo> inputParams) {
		this.inputParams = inputParams;
	}

	/**
	 * returnType
	 * 
	 * @return the returnType
	 * @since 1.0.0
	 */

	public ParameterInfo getReturnType() {
		return returnType;
	}

	/**
	 * @param returnType
	 *            the returnType to set
	 */
	public void setReturnType(ParameterInfo returnType) {
		this.returnType = returnType;
	}

	/**
	 * outputParams
	 * 
	 * @return the outputParams
	 * @since 1.0.0
	 */

	public List<ParameterInfo> getOutputParams() {
		return outputParams;
	}

	/**
	 * @param outputParams
	 *            the outputParams to set
	 */
	public void setOutputParams(List<ParameterInfo> outputParams) {
		this.outputParams = outputParams;
	}

	/**
	 * inputAction
	 * 
	 * @return the inputAction
	 * @since 1.0.0
	 */

	public String getInputAction() {
		return inputAction;
	}

	/**
	 * @param inputAction
	 *            the inputAction to set
	 */
	public void setInputAction(String inputAction) {
		this.inputAction = inputAction;
	}

}