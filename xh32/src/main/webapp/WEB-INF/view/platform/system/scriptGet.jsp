<%--
	time:2012-01-05 12:01:21
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/getById.jsp" %>
	<title><spr:message code="script"/><spr:message code="operator.detail"/></title>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="script"/><spr:message code="operator.detail"/></span>
			</div>
			<c:if test="${canReturn==0}">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back" href="../script/list.ht"><span></span><spr:message code="menu.button.back"/></a></div>
				</div>
			</div>
			</c:if>
		</div>
		<div class="panel-body">
				<form id="scriptForm" method="post" action="add2.ht">
					<div class="panel-detail">
						<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th width="20%"><spr:message code="script.name"/>:</th>
								<td>${script.name}</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="script.script"/>:</th>
								<td>${script.script}</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="script.category"/>:</th>
								<td>${script.category}</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="script.memo"/>:</th>
								<td>${script.memo}</td>
							</tr>
						</table>
					</div>
				</form>
		</div>
	</div>
</body>
</html>
