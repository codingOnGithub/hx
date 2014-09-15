<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	
	<%@include file="/commons/include/getById.jsp" %>
	<title><spr:message code="outMail.mail"/></title>
	<script type="text/javascript">
		window.onbeforeunload = function() {
			window.opener.location.reload();
		}
	</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="outMail.mail"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<c:if test="${outMail.types==1}">
						<div class="group"><a class="link update" href="reply.ht?mailId=${outMail.mailId}&isReply=1&outMailSetId=${outMailSetId}"><span></span><spr:message code="menu.button.reply"/></a></div>
						<div class="l-bar-separator"></div>
					</c:if>
					<div class="group">
						<a class="link back" href="list.ht?id=${outMailSetId}&types=${outMail.types}"><span></span><spr:message code="menu.button.back"/></a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="15%"><spr:message code="outMail.title"/>:</th>
					<td>${outMail.title}</td>
				</tr>
				<tr>
					<th width="15%"><spr:message code="outMail.sender"/>:</th>
					<td>(${outMail.senderName})${outMail.senderAddresses}</td>
				</tr>
				<tr>
					<th width="15%"><spr:message code="outMail.receiver"/>:</th>
					<td>${outMail.receiverAddresses}</td>
				</tr>
				<tr>
					<th width="15%"><spr:message code="outMail.mailDate"/>:</th>
					<td><fmt:formatDate value='${outMail.mailDate}' type="both"/></td>
				</tr>	
			</table>
			<div style="border:solid 1px #7babcf;margin:2px;padding:4px;">
				${outMail.content}
			</div>
		</div>
	</div>
</body>
</html>
