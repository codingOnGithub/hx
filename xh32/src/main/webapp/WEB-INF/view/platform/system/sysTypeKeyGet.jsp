<%--
	time:2012-03-10 10:18:37
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/getById.jsp" %>
	<title><spr:message code="sysTypeKey"/><spr:message code="title.detail"/></title>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="sysTypeKey"/><spr:message code="title.detail"/></span>
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
							<th width="20%"><spr:message code="sysTypeKey.typeKey"/>:</th>
							<td>${sysTypeKey.typeKey}</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="sysTypeKey.typeName"/>:</th>
							<td>${sysTypeKey.typeName}</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="sysTypeKey.sn"/>:</th>
							<td>${sysTypeKey.sn}</td>
						</tr>
					</table>
				</div>
		</div>
</div>

</body>
</html>
