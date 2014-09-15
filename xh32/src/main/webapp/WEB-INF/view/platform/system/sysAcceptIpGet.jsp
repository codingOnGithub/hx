<%--
	time:2012-02-20 17:35:46
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/getById.jsp" %>
	<title><spr:message code="sysAcceptIp"/><spr:message code="title.detail"/></title>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="sysAcceptIp"/><spr:message code="title.detail"/></span>
			</div>
			<c:if test="${canReturn==0}">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back" href="list.ht"><span></span><spr:message code="menu.button.back"/></a></div>
				</div>
			</div>
			</c:if>
		</div>
		<div class="panel-body">
				<div class="panel-detail">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%"><spr:message code="sysAcceptIp.title"/>:</th>
							<td>${sysAcceptIp.title}</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="sysAcceptIp.startIp"/>:</th>
							<td>${sysAcceptIp.startIp}</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="sysAcceptIp.endIp"/>:</th>
							<td>${sysAcceptIp.endIp}</td>
						</tr>				
						<tr>
							<th width="20%"><spr:message code="sysAcceptIp.remark"/>:</th>
							<td>${sysAcceptIp.remark}</td>
						</tr>
					</table>
				</div>
		</div>
</div>

</body>
</html>
