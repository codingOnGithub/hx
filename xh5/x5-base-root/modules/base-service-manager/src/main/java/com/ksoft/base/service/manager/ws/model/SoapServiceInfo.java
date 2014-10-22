package com.ksoft.base.service.manager.ws.model;

import java.util.ArrayList;
import java.util.List;

import com.ksoft.base.service.manager.util.AssertValue;


public class SoapServiceInfo extends AbstractSoapModel implements
		java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<SoapBindingInfo> soapBindingInfos;

	public List<SoapBindingInfo> getSoapBindingInfos() {
		return soapBindingInfos;
	}

	public void setSoapBindingInfos(List<SoapBindingInfo> soapBindingInfos) {
		this.soapBindingInfos = soapBindingInfos;
	}

	public void putSoapBindingInfo(SoapBindingInfo soapBindingInfo) {

		if (!AssertValue.isNotNull(soapBindingInfos)) {
			soapBindingInfos = new ArrayList<SoapBindingInfo>();
		}

		this.soapBindingInfos.add(soapBindingInfo);
	}

}
