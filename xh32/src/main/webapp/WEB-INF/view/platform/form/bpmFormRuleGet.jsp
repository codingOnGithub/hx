<%--
	time:2012-01-11 10:53:15
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/getById.jsp" %>
	<title><spr:message code="bpmFormRule.title"/><spr:message code="operator.detail"/></title>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="bpmFormRule.title"/><spr:message code="title.detail"/></span>
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
					<th width="20%"><spr:message code="bpmFormRule.name"/>:</th>
					<td>${bpmFormRule.name}</td>
				</tr>
				<tr>
					<th width="20%"><spr:message code="bpmFormRule.rule"/>:</th>
					<td>${bpmFormRule.rule}</td>
				</tr>
					<tr>
					<th width="20%"><spr:message code="bpmFormRule.tipInfo"/>:</th>
					<td>${bpmFormRule.tipInfo}</td>
				</tr>
				<tr>
					<th width="20%"><spr:message code="bpmFormRule.memo"/>:</th>
					<td>${bpmFormRule.memo}</td>
				</tr>
			</table>
		</div>
</div>

</body>
</html>
