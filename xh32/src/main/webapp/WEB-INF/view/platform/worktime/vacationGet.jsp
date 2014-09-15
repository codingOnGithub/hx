<%--
	time:2012-02-20 09:25:49
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<html>
<head>
	<title><spr:message code="vacation.get.title" /></title>
	<%@include file="/commons/include/getById.jsp" %>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="vacation.get.span" /></span>
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
			
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th width="20%"><spr:message code="vacation.name" />:</th>
						<td>${vacation.name}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="vacation.years" />:</th>
						<td>${vacation.years}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="vacation.statTime" />:</th>
						<td>${vacation.sTime}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="vacation.endTime" />:</th>
						<td>${vacation.eTime}</td>
					</tr>
				</table>
			
		</div>
</div>

</body>
</html>
