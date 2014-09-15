package com.hotent.platform.ws.impl;

import java.util.Map;

import com.hotent.platform.webservice.model.Org;
import com.hotent.platform.ws.BaseWebService;
import com.hotent.platform.ws.util.ResponseUtil;
import com.hotent.platform.ws.util.SoapUtil;

public class OrgService extends BaseWebService {

	public OrgService(String url) {
		super();
		this.url = url;
	}
	
	
	/**
	 * 新增组织
	 * @param org
	 * @return
	 */
	public Map<String, Object> add(Org org) {
		String soapXml = SoapUtil.genSaveOrg(org);
		return ResponseUtil.getResponse(url, soapXml);
	}
	
	
	/**
	 * 修改组织
	 * @param org
	 * @return
	 */
	public Map<String, Object> edit(Org org) {
		String soapXml = SoapUtil.genSaveOrg(org);
		return ResponseUtil.getResponse(url, soapXml);
	}

	
	/**
	 * 删除组织
	 * @param orgId
	 * @return
	 */
	public Map<String, Object> del(String orgId) {
		String soapXml = "";
		return ResponseUtil.getResponse(url, soapXml);
	}
}
