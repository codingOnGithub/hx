<%--
	time:2012-01-14 15:13:00
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/getById.jsp" %>
	<title><spr:message code="messageReceiver.title"/><spr:message code="title.detail"/></title>
	<script type="text/javascript">
		//放置脚本
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="messageReceiver.title"/><spr:message code="title.detail"/></span>
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
						<th width="20%"><spr:message code="messageReceiver.messageId"/>:</th>
						<td>${messageReceiver.messageId}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="messageReceiver.receiveType"/>:</th>
						<td>${messageReceiver.receiveType}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="messageReceiver.receiverId"/>:</th>
						<td>${messageReceiver.receiverId}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="messageReceiver.receiver"/>:</th>
						<td>${messageReceiver.receiver}</td>
					</tr>
				</table>
			</div>
		</div>
</div>

</body>
</html>
