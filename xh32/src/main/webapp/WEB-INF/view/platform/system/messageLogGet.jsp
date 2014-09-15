
<%--
	time:2012-11-29 16:24:35
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/getById.jsp"%>
<title><spr:message code="messageLog"/><spr:message code="title.detail"/></title>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="messageLog"/><spr:message code="title.detail"/></span>
			</div>
			<c:if test="${canReturn==0}">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link back" href="list.ht"><span></span><spr:message code="menu.button.back"/></a>
					</div>
				</div>
			</div>
			</c:if>
		</div>
		<div class="panel-body">
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			 
			<tr>
				<th width="20%"><spr:message code="messageLog.subject"/>:</th>
				<td>${messageLog.subject}</td>
			</tr>
 
			<tr>
				<th width="20%"><spr:message code="messageLog.sendTime"/>:</th>
				<td>
				<fmt:formatDate value="${messageLog.sendTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
 
			<tr>
				<th width="20%"><spr:message code="messageLog.receiver"/>:</th>
				<td>${messageLog.receiver}</td>
			</tr>
 
			<tr>
				<th width="20%"><spr:message code="messageLog.messageType"/>:</th>
				<td><c:choose>
						<c:when test="${messageLog.messageType==1 }"><spr:message code="messageLog.messageType.mail"/></c:when>
						<c:when test="${messageLog.messageType==2 }"><spr:message code="messageLog.messageType.sms"/></c:when>
						<c:when test="${messageLog.messageType==3 }"><spr:message code="messageLog.messageType.inner"/></c:when>
						<c:otherwise>
							<spr:message code="messageLog.messageType.unknow"/>
						</c:otherwise>
					</c:choose>
					
					
				</td>
			</tr>
 
			<tr>
				<th width="20%"><spr:message code="messageLog.state"/>:</th>
				<td>
					<c:choose>
							<c:when test="${messageLog.state eq 1 }"><spr:message code="messageLog.state.success"/></c:when>
							<c:otherwise><spr:message code="messageLog.state.fail"/></c:otherwise>
					</c:choose></td>
			</tr>
		</table>
		</div>
		
	</div>
</body>
</html>

