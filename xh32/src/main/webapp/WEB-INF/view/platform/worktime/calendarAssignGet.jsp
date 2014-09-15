<%--
	time:2012-02-20 09:25:51
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<html>
<head>
	<title><spr:message code="calendarAssign.get.title" /></title>
	<%@include file="/commons/include/getById.jsp" %>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label"><spr:message code="calendarAssign.get.span" /></span>
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
					<th width="20%"><spr:message code="sysCalendar.name" />:</th>
					<td>${calendarAssign.calendarName}</td>
				</tr>
				<tr>
					<th width="20%"><spr:message code="calendarAssign.assignType" />:</th>
					<td>
						<c:if test="${calendarAssign.assignType==1}"><spr:message code="calendarAssign.user" /></c:if>
						<c:if test="${calendarAssign.assignType==2}"><spr:message code="calendarAssign.organization" /></c:if>
					</td>
				</tr>
				<tr>
					<th width="20%"><spr:message code="calendarAssign.userOrOrg" />:</th>
					<td>${calendarAssign.assignUserName}</td>
				</tr>
			</table>
		</div>
	</div>
</div>

</body>
</html>
