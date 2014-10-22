package com.ksoft.base.service.manager.ws.model;


public class SoapInvokCmd implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String wsdlPath;

	private String operatorName;

	private String operatorNamespace;

	private String jsonParam;

	public SoapInvokCmd(String wsdlPath, String name, String namespace) {
		this.wsdlPath = wsdlPath;
		this.operatorName = name;
		this.operatorNamespace = namespace;
	}

	public String getWsdlPath() {
		return wsdlPath;
	}

	public void setWsdlPath(String wsdlPath) {
		this.wsdlPath = wsdlPath;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOperatorNamespace() {
		return operatorNamespace;
	}

	public void setOperatorNamespace(String operatorNamespace) {
		this.operatorNamespace = operatorNamespace;
	}

	public String getJsonParam() {
		return jsonParam;
	}

	public void setJsonParam(String jsonParam) {
		this.jsonParam = jsonParam;
	}

	// public Map<String, String> getParams() {
	// return params;
	// }
	//
	// public void setParams(Map<String, String> params) {
	// this.params = params;
	// }
	//
	// public void putParam(String name, String value) {
	// if (!AssertValue.isNotNull(params)) {
	// this.params = new HashMap<String, String>();
	// }
	//
	// this.params.put(name, value);
	// }
}
