package com.ksoft.base.service.manager.ws.model;

import java.util.ArrayList;
import java.util.List;

import com.ksoft.base.service.manager.util.AssertValue;


public class SoapService extends AbstractSoapModel implements
		java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<SoapServiceInfo> soapServiceInfos;

	private List<SoapBindingOperationInfo> soapBindingOperationInfos;

	public List<SoapBindingOperationInfo> getSoapBindingOperationInfos() {
		return soapBindingOperationInfos;
	}

	public void setSoapBindingOperationInfos(
			List<SoapBindingOperationInfo> soapBindingOperationInfos) {
		this.soapBindingOperationInfos = soapBindingOperationInfos;
	}

	public List<SoapServiceInfo> getSoapServiceInfos() {
		return soapServiceInfos;
	}

	public void setSoapServiceInfos(List<SoapServiceInfo> soapServiceInfos) {
		this.soapServiceInfos = soapServiceInfos;
	}

	public void putSoapServiceInfo(SoapServiceInfo soapServiceInfo) {
		if (!AssertValue.isNotNull(soapServiceInfos)) {
			this.soapServiceInfos = new ArrayList<SoapServiceInfo>();
		}

		this.soapServiceInfos.add(soapServiceInfo);
	}

	public void putSoapBindingOperationInfo(
			SoapBindingOperationInfo soapBindingOperationInfo) {

		if (!AssertValue.isNotNull(soapBindingOperationInfos)) {
			this.soapBindingOperationInfos = new ArrayList<SoapBindingOperationInfo>();
		}

		this.soapBindingOperationInfos.add(soapBindingOperationInfo);
	}

	public SoapBindingOperationInfo getSoapBindingOperationInfo(String name,
			String namespace) {

		if (!AssertValue.isNotNullAndNotEmpty(this.soapBindingOperationInfos)) {
			return null;
		}

		for (SoapBindingOperationInfo sboi : this.soapBindingOperationInfos) {
			if (sboi.getName().equals(name)
					&& sboi.getNamespace().equals(namespace)) {
				return sboi;
			}
		}

		return null;

	}

}
