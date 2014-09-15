<%--
	time:2012-02-20 14:57:32
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title><spr:message code="sysCalendar.get.title" /></title>
	<%@include file="/commons/include/getById.jsp" %>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="sysCalendar.get.span" /></span>
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
						<td>${sysCalendar.name}</td>
					</tr>
					<tr>
						<th width="20%">memo:</th>
						<td>${sysCalendar.memo}</td>
					</tr>
				</table>
			</div>
		</div>
</div>

</body>
</html>
