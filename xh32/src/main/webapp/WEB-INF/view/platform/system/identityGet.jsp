<%--
	time:2012-02-03 14:40:59
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/getById.jsp" %>
	<title><spr:message code="identity.title"/><spr:message code="operator.detail"/></title>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="identity.title"/><spr:message code="operator.detail"/></span>
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
						<th width="20%"><spr:message code="identity.name"/>:</th>
						<td>${identity.name}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="identity.alias"/>:</th>
						<td>${identity.alias}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="identity.rule"/>:</th>
						<td>${identity.rule}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="identity.genType"/>:</th>
						<td> 
							<c:choose>
								<c:when test="${identity.genType==1}">
									<spr:message code="identity.genType.date"/>
								</c:when>
								<c:when test="${identity.genType==1}">
									<spr:message code="identity.genType.month"/>
								</c:when>
								<c:when test="${identity.genType==1}">
									<spr:message code="identity.genType.year"/>
								</c:when>
								<c:otherwise>
									<spr:message code="identity.genType.increase"/>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="identity.noLength"/>:</th>
						<td>${identity.noLength}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="identity.initValue"/>:</th>
						<td>${identity.initValue}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="identity.curValue"/>:</th>
						<td>${identity.curValue}</td>
					</tr>
				</table>
			</div>
		</div>
</div>

</body>
</html>
