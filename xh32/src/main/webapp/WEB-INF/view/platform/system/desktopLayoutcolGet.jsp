<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title><spr:message code="desktopLayoutcol.get.title" /></title>
	<%@include file="/commons/include/getById.jsp" %>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="desktopLayoutcol.get.span" /></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back" href="list.ht"><span></span><spr:message code="menu.button.back" /></a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<div class="panel-detail">
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th width="20%"><spr:message code="desktopMycolumn.layoutId" />:</th>
						<td>${desktopLayoutcol.layoutId}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="desktopMycolumn.columnId" />:</th>
						<td>${desktopLayoutcol.columnId}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="desktopMycolumn.col" />:</th>
						<td>${desktopLayoutcol.col}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="desktopMycolumn.sn" />:</th>
						<td>${desktopLayoutcol.sn}</td>
					</tr>
				</table>
			</div>
		</div>
</div>
</body>
</html>
