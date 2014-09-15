package com.hotent.platform.ws.util;

import com.hotent.core.util.BeanUtils;
import com.hotent.platform.webservice.model.Org;
import com.hotent.platform.webservice.model.User;

public class SoapUtil {


	/**
	 * 构建SaveUserOrg
	 * 
	 * @param userId
	 * @param orgId
	 * @return
	 */
	public static String genSaveUserOrg(String userId, String orgId) {
		StringBuffer sb = new StringBuffer();
		sb.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:impl=\"http://impl.webservice.platform.hotent.com/\"><soapenv:Header/><soapenv:Body><impl:saveUserOrg>");
		sb.append("<userId>").append(userId).append("</userId>");
		sb.append("<orgId>").append(orgId).append("</orgId>");
		sb.append("</impl:saveUserOrg></soapenv:Body></soapenv:Envelope>");
		return sb.toString();
	}

	/**
	 * 构建SaveOrg
	 * 
	 * @param org
	 * @return
	 */
	public static String genSaveOrg(Org org) {
		StringBuffer sb = new StringBuffer();
		sb.append(
				"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:impl=\"http://impl.webservice.platform.hotent.com/\"><soapenv:Header/><soapenv:Body><impl:saveOrg>")
				.append(" <org>");
		if (BeanUtils.isNotEmpty(org.getCreateName()))
			sb.append("<createName>").append(org.getCreateName())
					.append("</createName>");
		if (BeanUtils.isNotEmpty(org.getCreatetime()))
			sb.append("<createtime>").append(org.getCreatetime())
					.append("</createtime>");
		if (BeanUtils.isNotEmpty(org.getCreatorId()))
			sb.append("<creatorId>").append(org.getCreatorId())
					.append("</creatorId>");
		if (BeanUtils.isNotEmpty(org.getDepth()))
			sb.append("<depth>").append(org.getDepth()).append("</depth>");
		if (BeanUtils.isNotEmpty(org.getFromType()))
			sb.append("<fromType>").append(org.getFromType())
					.append("</fromType>");
		if (BeanUtils.isNotEmpty(org.getOrgDesc()))
			sb.append("<orgDesc>").append(org.getOrgDesc())
					.append("</orgDesc>");
		if (BeanUtils.isNotEmpty(org.getOrgId()))
			sb.append("<orgId>").append(org.getOrgId()).append("</orgId>");
		if (BeanUtils.isNotEmpty(org.getOrgName()))
			sb.append("<orgName>").append(org.getOrgName())
					.append("</orgName>");
		if (BeanUtils.isNotEmpty(org.getOrgPathname()))
			sb.append("<orgPathname>").append(org.getOrgPathname())
					.append("</orgPathname>");
		if (BeanUtils.isNotEmpty(org.getOrgSupId()))
			sb.append("<orgSupId>").append(org.getOrgSupId())
					.append("</orgSupId>");
		if (BeanUtils.isNotEmpty(org.getOrgSupName()))
			sb.append("<orgSupName>").append(org.getOrgSupName())
					.append("</orgSupName>");
		if (BeanUtils.isNotEmpty(org.getOrgType()))
			sb.append("<orgType>").append(org.getOrgType())
					.append("</orgType>");
		if (BeanUtils.isNotEmpty(org.getOwnUser()))
			sb.append("<ownUser>").append(org.getOwnUser())
					.append("</ownUser>");
		if (BeanUtils.isNotEmpty(org.getOwnUserName()))
			sb.append("<ownUserName>").append(org.getOwnUserName())
					.append("</ownUserName>");
		if (BeanUtils.isNotEmpty(org.getPath()))
			sb.append("<path>").append(org.getPath()).append("</path>");
		if (BeanUtils.isNotEmpty(org.getSn()))
			sb.append("<sn>").append(org.getSn()).append("</sn>");
		if (BeanUtils.isNotEmpty(org.getUpdateId()))
			sb.append("<updateId>").append(org.getUpdateId())
					.append("</updateId>");
		if (BeanUtils.isNotEmpty(org.getUpdateName()))
			sb.append("<updateName>").append(org.getUpdateName())
					.append("</updateName>");
		if (BeanUtils.isNotEmpty(org.getUpdatetime()))
			sb.append("<updatetime>").append(org.getUpdatetime())
					.append("</updatetime>");

		sb.append(" </org></impl:saveOrg></soapenv:Body></soapenv:Envelope>");
		return sb.toString();
	}

	/**
	 * 构建SaveUer
	 * 
	 * @param user
	 * @return
	 */
	public static String genSaveUser(User user) {
		StringBuffer sb = new StringBuffer();
		sb.append(
				"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:impl=\"http://impl.webservice.platform.hotent.com/\"><soapenv:Header/>"
						+ "<soapenv:Body>" + "<impl:saveUser>").append(
				" <user>");
		if (BeanUtils.isNotEmpty(user.getAccount()))
			sb.append("<account>").append(user.getAccount())
					.append("</account>");
		if (BeanUtils.isNotEmpty(user.getCreatetime()))
			sb.append("<createtime>").append(user.getCreatetime())
					.append("</createtime>");
		if (BeanUtils.isNotEmpty(user.getEmail()))
			sb.append("<email>").append(user.getEmail()).append("</email>");
		if (BeanUtils.isNotEmpty(user.getFullname()))
			sb.append("<fullname>").append(user.getFullname())
					.append("</fullname>");
		if (BeanUtils.isNotEmpty(user.getIsExpired()))
			sb.append("<isExpired>").append(user.getIsExpired())
					.append("</isExpired>");
		if (BeanUtils.isNotEmpty(user.getIsLock()))
			sb.append("<isLock>").append(user.getIsLock()).append("</isLock>");
		if (BeanUtils.isNotEmpty(user.getMobile()))
			sb.append("<mobile>").append(user.getMobile()).append("</mobile>");
		if (BeanUtils.isNotEmpty(user.getPassword()))
			sb.append("<password>").append(user.getPassword())
					.append("</password>");
		if (BeanUtils.isNotEmpty(user.getPhone()))
			sb.append("<phone>").append(user.getPhone()).append("</phone>");
		if (BeanUtils.isNotEmpty(user.getSex()))
			sb.append("<sex>").append(user.getSex()).append("</sex>");
		if (BeanUtils.isNotEmpty(user.getStatus()))
			sb.append("<status>").append(user.getStatus()).append("</status>");
		if (BeanUtils.isNotEmpty(user.getUserId()))
			sb.append("<userId>").append(user.getUserId()).append("</userId>");

		sb.append(" </user></impl:saveUser></soapenv:Body></soapenv:Envelope>");
		return sb.toString();
	}
}
