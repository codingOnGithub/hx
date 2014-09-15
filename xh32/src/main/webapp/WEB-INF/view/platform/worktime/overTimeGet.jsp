<%--
	time:2012-02-20 09:25:52
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<html>
<head>
	<title><spr:message code="overTime.get.title" /></title>
	<%@include file="/commons/include/getById.jsp" %>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="overTime.get.span" /></span>
			</div>
			<c:if test="${canReturn==0}">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back" href="list.ht"><span></span><spr:message code="menu.button.back" /></a></div>
				</div>
			</div>
			</c:if>
		</div>
		<div class="panel-body">
			<div class="panel-detail">
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th width="20%"><spr:message code="overTime.subject" />:</th>
						<td>${overTime.subject}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="overTime.userName" />:</th>
						<td>${overTime.userName}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="overTime.workType" />:</th>
						<td><c:if test="${overTime.workType==1}"><spr:message code="overTime.over" /></c:if>
						<c:if test="${overTime.workType==2}"><spr:message code="overTime.leave" /></c:if></td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="overTime.startTime" />:</th>
						<td><fmt:formatDate value="${overTime.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="overTime.endTime" />:</th>
						<td><fmt:formatDate value="${overTime.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					</tr>
				</table>
			</div>
		</div>
</div>

</body>
</html>
