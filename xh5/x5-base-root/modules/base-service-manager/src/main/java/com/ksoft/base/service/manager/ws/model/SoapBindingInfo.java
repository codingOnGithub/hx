package com.ksoft.base.service.manager.ws.model;

import java.util.ArrayList;
import java.util.List;

import com.ksoft.base.service.manager.util.AssertValue;


public class SoapBindingInfo extends AbstractSoapModel implements
		java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<SoapBindingOperationInfo> soapBindingOperationInfos;

	public List<SoapBindingOperationInfo> getSoapBindingOperationInfos() {
		return soapBindingOperationInfos;
	}

	public void setSoapBindingOperationInfos(
			List<SoapBindingOperationInfo> soapBindingOperationInfos) {
		this.soapBindingOperationInfos = soapBindingOperationInfos;
	}

	public void putSoapBindingOperationInfo(
			SoapBindingOperationInfo soapBindingOperationInfo) {

		if (!AssertValue.isNotNull(soapBindingOperationInfos)) {
			this.soapBindingOperationInfos = new ArrayList<SoapBindingOperationInfo>();
		}

		this.soapBindingOperationInfos.add(soapBindingOperationInfo);
	}

}
