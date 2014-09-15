
<%--
	time:2012-12-19 15:38:01
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/getById.jsp"%>
<title><spr:message code="sysCodeTemplate"/><spr:message code="title.detail"/></title>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="sysCodeTemplate"/><spr:message code="title.detail"/></span>
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
				<th width="20%"><spr:message code="sysCodeTemplate.name"/>:</th>
				<td>${sysCodeTemplate.templateName}</td>
			</tr>
 
			<tr>
				<th width="20%"><spr:message code="sysCodeTemplate.memo"/>:</th>
				<td>${sysCodeTemplate.memo}</td>
			</tr>
 
			<tr>
				<th width="20%"><spr:message code="sysCodeTemplate.alias"/>:</th>
				<td>${sysCodeTemplate.templateAlias}</td>
			</tr>
			
			<tr>
				<th width="20%"><spr:message code="sysCodeTemplate.fileName"/>:</th>
				<td>${sysCodeTemplate.fileName}</td>
			</tr>
			<tr>
				<th width="20%"><spr:message code="sysCodeTemplate.fileDir"/>:</th>
				<td>${sysCodeTemplate.fileDir}</td>
			</tr>
			
			<tr>
				<th width="20%"><spr:message code="sysCodeTemplate.html"/>:</th>
				<td>
					<textarea name="html" cols="120" rows="15" readonly="readonly" >${fn:escapeXml(sysCodeTemplate.html)}</textarea>
				</td>
			</tr>
		</table>
		</div>
		
	</div>
</body>
</html>

