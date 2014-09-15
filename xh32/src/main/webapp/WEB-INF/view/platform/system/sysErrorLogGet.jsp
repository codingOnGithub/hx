
<%--
	time:2013-07-12 16:42:11
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/getById.jsp"%>
<title><spr:message code="sysErrorLog"/><spr:message code="title.detail"/></title>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="sysErrorLog"/><spr:message code="title.detail"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link back" href="list.ht"><spr:message code="menu.button.back"/></a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<th width="20%"><spr:message code="sysErrorLog.id"/>:</th>
				<td>${sysErrorLog.id}</td>
			</tr> 
			<tr>
				<th width="20%"><spr:message code="sysErrorLog.hashCode"/>:</th>
				<td>${sysErrorLog.hashcode}</td>
			</tr>
 
			<tr>
				<th width="20%"><spr:message code="sysErrorLog.account"/>:</th>
				<td>${sysErrorLog.account}</td>
			</tr>
 
			<tr>
				<th width="20%"><spr:message code="sysErrorLog.ip"/>:</th>
				<td>${sysErrorLog.ip}</td>
			</tr>
 
			<tr>
				<th width="20%"><spr:message code="sysErrorLog.errorurl"/>:</th>
				<td>${sysErrorLog.errorurl}</td>
			</tr>
 			<tr>
				<th width="20%"><spr:message code="sysErrorLog.errordate"/>:</th>
				<td>
				<fmt:formatDate value="${sysErrorLog.errordate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
			<tr>
				<th width="20%"><spr:message code="sysErrorLog.error"/>:</th>
				<td>${sysErrorLog.error}</td>
			</tr>
 
	
		</table>
		</div>
		
	</div>
</body>
</html>

