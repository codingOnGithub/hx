<%--
	time:2012-02-23 17:43:35
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	
	<%@include file="/commons/include/getById.jsp" %>
	<title><spr:message code="sysParam.title"/><spr:message code="operator.detail"/></title>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="sysParam.title"/><spr:message code="title.detail"/></span>
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
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="20%"><spr:message code="sysParam.paramName"/>:</th>
					<td>${sysParam.paramName}</td>
				</tr>
				<tr>
					<th width="20%"><spr:message code="sysParam.paramKey"/>:</th>
					<td>${sysParam.paramKey}</td>
				</tr>
				<tr>
					<th width="20%"><spr:message code="sysParam.type"/>:</th>
					<td>
						<c:if test="${sysParam.effect==1}"><spr:message code="sysParam.userParam"/></c:if>
						<c:if test="${sysParam.effect==2}"><spr:message code="sysParam.orgParam"/></c:if>
											
					</td>
				</tr>
				<tr>
					<th width="20%"><spr:message code="sysParam.dataType"/>:</th>
					<td>
					
						<c:forEach items="${dataTypeMap}" var="t">
									<c:if test="${t.key==sysParam.dataType}">${t.value}</c:if>
						</c:forEach>
					
					</td>
				</tr>
			</table>
		</div>
</div>

</body>
</html>
