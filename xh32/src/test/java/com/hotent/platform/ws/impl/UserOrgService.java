package com.hotent.platform.ws.impl;

import java.util.Map;

import com.hotent.platform.ws.BaseWebService;
import com.hotent.platform.ws.util.ResponseUtil;
import com.hotent.platform.ws.util.SoapUtil;

public class UserOrgService extends BaseWebService {

	public UserOrgService(String url) {
		super();
		this.url = url;
	}

	/**
	 * 新增用户组织
	 * 
	 * @param user
	 * @return
	 */
	public Map<String, Object> add(String userId, String orgId) {
		String soapXml = SoapUtil.genSaveUserOrg(userId, orgId);
		return ResponseUtil.getResponse(url, soapXml);
	}

	/**
	 * 修改用户组织
	 * 
	 * @param userId
	 * @param orgId
	 * @return
	 */
	public Map<String, Object> edit(String userId, String orgId) {
		String soapXml = SoapUtil.genSaveUserOrg(userId, orgId);
		return ResponseUtil.getResponse(url, soapXml);
	}

	/**
	 * 删除用户组织
	 * 
	 * @param userOrgId
	 * @return
	 */
	public Map<String, Object> del(String userOrgId) {
		String soapXml = "";
		return ResponseUtil.getResponse(url, soapXml);
	}

}
