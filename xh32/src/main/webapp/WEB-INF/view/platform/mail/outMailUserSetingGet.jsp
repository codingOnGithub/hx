<%--
	time:2012-04-09 13:43:51
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	
	<%@include file="/commons/include/getById.jsp" %>
	<title><spr:message code="outMail.mailbox"/><spr:message code="operator.detail"/></title>
	<script type="text/javascript">
		//放置脚本
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="outMail.mailbox"/><spr:message code="title.detail"/></span>
			</div>
			<c:if test="${canReturn==0}">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back " href="list.ht"><span></span><spr:message code="menu.button.back"/></a></div>
				</div>
			</div>
			</c:if>
		</div>
		<div class="panel-body">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="20%"><spr:message code="outMail.accountName"/>:</th>
					<td>${outMailUserSeting.userName}</td>
				</tr>
				<tr>
					<th width="20%"><spr:message code="outMailUserSeting.mailAddress"/>:</th>
					<td>${outMailUserSeting.mailAddress}</td>
				</tr>
				<tr>
					<th width="20%"><spr:message code="outMailUserSeting.type"/>:</th>
					<td>${outMailUserSeting.mailType}</td>
				</tr>
				<tr >
					<th width="20%"><spr:message code="outMailUserSeting.smtpHost"/>:</th>
					<td>${outMailUserSeting.smtpHost}</td>
				</tr>
				<tr>
					<th width="20%"><spr:message code="outMailUserSeting.smtpPort"/>:</th>
					<td>${outMailUserSeting.smtpPort}</td>
				</tr>
				<tr <c:if test="${outMailUserSeting.mailType=='imap'}">style="display: none"</c:if>>
					<th width="20%"><spr:message code="outMailUserSeting.popHost"/>:</th>
					<td>${outMailUserSeting.popHost}</td>
				</tr>
				<tr <c:if test="${outMailUserSeting.mailType=='imap'}">style="display: none"</c:if>>
					<th width="20%"><spr:message code="outMailUserSeting.popPort"/>:</th>
					<td>${outMailUserSeting.popPort}</td>
				</tr>
				<tr <c:if test="${outMailUserSeting.mailType=='pop3'}">style="display: none"</c:if>>
					<th width="20%"><spr:message code="outMailUserSeting.imapHost"/>:</th>
					<td>${outMailUserSeting.imapHost}</td>
				</tr>
				<tr <c:if test="${outMailUserSeting.mailType=='pop3'}">style="display: none"</c:if>>
					<th width="20%"><spr:message code="outMailUserSeting.imapPort"/>:</th>
					<td>${outMailUserSeting.imapPort}</td>
				</tr>
				<tr>
					<th width="20%"><spr:message code="outMailUserSeting.isDefault"/>:</th>
					<td>
						<c:choose>
							<c:when test="${outMailUserSeting.isDefault==1}"><spr:message code="yes"/></c:when>
							<c:otherwise><spr:message code="no"/></c:otherwise>
						</c:choose>
					</td>
				</tr>
			</table>
		</div>
</div>

</body>
</html>
