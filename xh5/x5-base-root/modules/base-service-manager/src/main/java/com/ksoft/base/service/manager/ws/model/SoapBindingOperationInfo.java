package com.ksoft.base.service.manager.ws.model;

import java.util.ArrayList;
import java.util.List;

import com.ksoft.base.service.manager.util.AssertValue;


public class SoapBindingOperationInfo extends AbstractSoapModel implements
		java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<SoapParamInfo> inputParams;

	private List<SoapParamInfo> outputParams;

	public List<SoapParamInfo> getInputParams() {
		return inputParams;
	}

	public void setInputParams(List<SoapParamInfo> inputParams) {
		this.inputParams = inputParams;
	}

	public List<SoapParamInfo> getOutputParams() {
		return outputParams;
	}

	public void setOutputParams(List<SoapParamInfo> outputParams) {
		this.outputParams = outputParams;
	}

	public void putInputParam(SoapParamInfo soapParamInfo) {
		if (!AssertValue.isNotNull(inputParams)) {
			this.inputParams = new ArrayList<SoapParamInfo>();
		}

		this.inputParams.add(soapParamInfo);
	}

	public void putOutputParam(SoapParamInfo soapParamInfo) {
		if (!AssertValue.isNotNull(this.outputParams)) {
			this.outputParams = new ArrayList<SoapParamInfo>();
		}

		this.outputParams.add(soapParamInfo);
	}
}
