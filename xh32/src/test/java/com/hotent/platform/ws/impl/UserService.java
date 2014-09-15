package com.hotent.platform.ws.impl;

import java.util.Map;

import com.hotent.platform.webservice.model.User;
import com.hotent.platform.ws.BaseWebService;
import com.hotent.platform.ws.util.ResponseUtil;
import com.hotent.platform.ws.util.SoapUtil;

public class UserService extends BaseWebService {

	public UserService(String url) {
		super();
		this.url = url;
	}

	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	public Map<String, Object> add(User user) {
		String soapXml = SoapUtil.genSaveUser(user);
		return ResponseUtil.getResponse(url, soapXml);
	}
	
	
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	public Map<String, Object> edit(User user) {
		String soapXml = SoapUtil.genSaveUser(user);
		return ResponseUtil.getResponse(url, soapXml);
	}

	
	/**
	 * 删除用户
	 * @param user
	 * @return
	 */
	public Map<String, Object> del(String userId) {
		String soapXml = "";
		return ResponseUtil.getResponse(url, soapXml);
	}

}
