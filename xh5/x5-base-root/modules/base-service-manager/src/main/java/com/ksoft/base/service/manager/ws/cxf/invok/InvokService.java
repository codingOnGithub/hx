package com.ksoft.base.service.manager.ws.cxf.invok;

import com.ksoft.base.service.manager.ws.model.SoapInvokCmd;

public interface InvokService {
	public Object[] invok(SoapInvokCmd soapInvokCmd);
}
