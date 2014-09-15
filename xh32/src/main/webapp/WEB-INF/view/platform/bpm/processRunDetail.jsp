<%--
	time:2011-12-04 18:56:52
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.jee-soft.cn/functions" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	
	<%@include file="/commons/include/getById.jsp"%>
	<title><spr:message code="processRun.title"/><spr:message code="operator.detail"/></title>
    <script type="text/javascript">
    </script> 
</head>
<body>
<div class="panel">
	
	<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<th width="20%">
				<spr:message code="processRun.runId"/>(runId)
			</th>
			<td>${processRun.runId}</td>
		</tr>
		<tr>
			<th width="20%"><spr:message code="processRun.defId"/>:</th>
			<td>${processRun.defId}</td>
		</tr>
		<tr>
			<th width="20%"><spr:message code="processRun.subject"/>:</th>
			<td>${processRun.subject}</td>
		</tr>
		<tr>
			<th width="20%"><spr:message code="processRun.creator"/>ID:</th>
			<td>${processRun.creatorId}</td>
		</tr>
		<tr>
			<th width="20%"><spr:message code="processRun.creator"/>:</th>
			<td>${processRun.creator}</td>
		</tr>
		<tr>
			<th width="20%"><spr:message code="processRun.createtime"/>:</th>
			<td>${f:shortDate(processRun.createtime)}</td>
		</tr>
		<tr>
			<th width="20%"><spr:message code="processRun.busDescp"/>:</th>
			<td>${processRun.busDescp}</td>
		</tr>
		<tr>
			<th width="20%"><spr:message code="processRun.status"/>:</th>
			<td>
				<c:choose>
				<c:when test="${processRun.status==1}">
					<font color='green'><spr:message code="processRun.status.running"/></font>
				</c:when>
				<c:when test="${processRun.status==2}">
					<spr:message code="processRun.status.end"/>
				</c:when>
			</c:choose>
			</td>
		</tr>
		<tr>
			<th width="20%"><spr:message code="processRun.actInstId"/>:</th>
			<td>${processRun.actInstId}</td>
		</tr>
		<tr>
			<th width="20%"><spr:message code="processRun.actDefId"/>:</th>
			<td>${processRun.actDefId}</td>
		</tr>
		<tr>
			<th width="20%">businessKey:</th>
			<td>${processRun.businessKey}</td>
		</tr>
	</table>
</div>
</body>
</html>
