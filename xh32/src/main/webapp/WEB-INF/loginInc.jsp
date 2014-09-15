<%@page import="com.hotent.core.util.AppUtil"%>
<%@page import="com.hotent.platform.model.system.SysLanguage"%>
<%@page import="java.util.List"%>
<%@page import="com.hotent.platform.service.system.SysLanguageService"%>
<%@page import="java.util.Properties"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Properties configProperties=(Properties)AppUtil.getBean("configproperties");
	String validCodeEnabled=configProperties.getProperty("validCodeEnabled");
	SysLanguageService sysLanguageService=(SysLanguageService)AppUtil.getBean("systemLanguageService");
	List<SysLanguage> list = sysLanguageService.getUserableLangauge();
	request.setAttribute("validCodeEnabled", validCodeEnabled);
	request.setAttribute("sysLanguageList", list);
	request.setAttribute("lang", SysLanguage.CURRENT_LANGUAGE);
%>
