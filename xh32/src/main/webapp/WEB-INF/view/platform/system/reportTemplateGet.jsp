<%--
	time:2012-04-12 09:59:47
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title><spr:message code="reportTemplate.get.title" /></title>
	<%@include file="/commons/include/getById.jsp" %>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label"><spr:message code="reportTemplate.get.span" /></span>
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
						<th width="20%"><spr:message code="reportTemplate.title" />:</th>
						<td>${reportTemplate.title}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="reportTemplate.descp" />:</th>
						<td>${reportTemplate.descp}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="reportTemplate.reportLocation" />:</th>
						<td>${reportTemplate.reportLocation}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="reportTemplate.createTime" />:</th>
						<td><fmt:formatDate value="${reportTemplate.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="reportTemplate.updateTime" />:</th>
						<td><fmt:formatDate value="${reportTemplate.updateTime}" pattern="yyyy-MM-dd HH:mm"/></td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="reportTemplate.reportKey" />:</th>
						<td>${reportTemplate.reportKey}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="reportTemplate.isDefaultIn" />:</th>
						<td>${reportTemplate.isDefaultIn}</td>
					</tr>
				</table>
			</div>
	</div>
</div>

</body>
</html>
