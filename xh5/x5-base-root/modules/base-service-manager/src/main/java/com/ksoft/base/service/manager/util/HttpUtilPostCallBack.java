package com.ksoft.base.service.manager.util;

import java.io.IOException;

import org.apache.commons.httpclient.methods.PostMethod;

public interface HttpUtilPostCallBack {
	public void success(PostMethod post) throws IOException;
}
