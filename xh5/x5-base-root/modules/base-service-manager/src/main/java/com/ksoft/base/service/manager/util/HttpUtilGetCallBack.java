package com.ksoft.base.service.manager.util;

import java.io.IOException;

import org.apache.commons.httpclient.methods.GetMethod;

public interface HttpUtilGetCallBack {
	public void success(GetMethod get) throws IOException;
}
