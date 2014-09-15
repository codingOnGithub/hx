<%--
	time:2011-12-28 14:04:30
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/getById.jsp" %>
	<title><spr:message code="sysTemplate"/><spr:message code="operator.detail"/></title>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="sysTemplate"/><spr:message code="operator.detail"/></span>
			</div>
			<c:if test="${canReturn==0}">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back" href="../sysTemplate/list.ht"><span></span><spr:message code="menu.button.back"/></a></div>
				</div>
			</div>
			</c:if>
		</div>
		<div class="panel-body">
			<div class="panel-detail">
				<form id="sysTemplateForm" method="post" action="add2.ht">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%"><spr:message code="sysTemplate.name"/>:</th>
							<td>${sysTemplate.name}</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="sysTemplate.useType"/>:</th>
							<td>
								<c:choose>
									<c:when test="${sysTemplate.useType eq 1}"><spr:message code="sysTemplate.useType.processComplete"/></c:when>
									<c:when test="${sysTemplate.useType eq 2}"><spr:message code="sysTemplate.useType.processReminder"/></c:when>
									<c:when test="${sysTemplate.useType eq 3}"><spr:message code="sysTemplate.useType.processCheck"/></c:when>
									<c:when test="${sysTemplate.useType eq 4}"><spr:message code="sysTemplate.useType.processRevoked"/></c:when>
									<c:when test="${sysTemplate.useType eq 5}"><spr:message code="sysTemplate.useType.processCancelDelegate"/></c:when>
									<c:when test="${sysTemplate.useType eq 6}"><spr:message code="sysTemplate.useType.processCommunication"/></c:when>
									<c:when test="${sysTemplate.useType eq 7}"><spr:message code="sysTemplate.useType.processStartuser"/></c:when>
									<c:when test="${sysTemplate.useType eq 8}"><spr:message code="sysTemplate.useType.processDelegate"/></c:when>
									<c:when test="${sysTemplate.useType eq 9}"><spr:message code="sysTemplate.useType.processReject"/></c:when>
									<c:when test="${sysTemplate.useType eq 10}"><spr:message code="sysTemplate.useType.processFeedback"/></c:when>
									<c:when test="${sysTemplate.useType eq 11}"><spr:message code="sysTemplate.useType.processCancelAgent"/></c:when>
									<c:when test="${sysTemplate.useType eq 12}"><spr:message code="sysTemplate.useType.processCopyTo"/></c:when>
									<c:when test="${sysTemplate.useType eq 13}"><spr:message code="sysTemplate.useType.processNobody"/></c:when>
									<c:when test="${sysTemplate.useType eq 19}"><spr:message code="sysTemplate.useType.processOverDue"/></c:when>
									<c:when test="${sysTemplate.useType eq 22}"><spr:message code="sysTemplate.useType.processAgent"/></c:when>
									<c:when test="${sysTemplate.useType eq 23}"><spr:message code="sysTemplate.useType.processForward"/></c:when>
									<c:when test="${sysTemplate.useType eq 24}"><spr:message code="sysTemplate.useType.restartTask"/></c:when>
									<c:when test="${sysTemplate.useType eq 25}"><spr:message code="sysTemplate.useType.processNotifyTaskOwner" /></c:when>
									<c:when test="${sysTemplate.useType eq 26}"><spr:message code="sysTemplate.useType.processTransToRemind" /></c:when>
									<c:when test="${sysTemplate.useType eq 27}"><spr:message code="sysTemplate.useType.processTransToFeedback" /></c:when>
									<c:when test="${sysTemplate.useType eq 28}"><spr:message code="sysTemplate.useType.processCancelTransTo" /></c:when>
									<c:otherwise><spr:message code="sysTemplate.useType.other"/></c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="sysTemplate.isDefault"/>:</th>
							<td>
								<c:choose>
									<c:when test="${sysTemplate.isDefault==1}"><spr:message code="yes"/></c:when>
									<c:otherwise><spr:message code="no"/></c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<th><spr:message code="sysTemplate.title" />: </th>
							<td>
								${sysTemplate.title}				
							</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="sysTemplate.htmlContent"/>:</th>
							<td>${sysTemplate.htmlContent}</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="sysTemplate.plainContent"/>:</th>
							<td>${sysTemplate.plainContent}</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
</div>

</body>
</html>
