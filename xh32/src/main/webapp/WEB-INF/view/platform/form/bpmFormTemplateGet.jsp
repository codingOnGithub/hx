<%--
	time:2012-01-09 16:35:25
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/getById.jsp" %>
	<title><spr:message code="bpmFormTemplate.title"/><spr:message code="operator.detail"/></title>
	<script type="text/javascript" src="${ctx}/js/javacode/codemirror.js"></script>
	<script type="text/javascript" src="${ctx}/js/javacode/InitMirror.js"></script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="bpmFormTemplate.title"/><spr:message code="operator.detail"/></span>
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
					<th width="20%"><spr:message code="bpmFormTemplate.templateName"/>:</th>
					<td>${bpmFormTemplate.templateName}</td>
				</tr>
				<tr>
					<th width="20%"><spr:message code="bpmFormTemplate.templateType"/></th>
					<td>
						${bpmFormTemplate.templateType}
					</td>
				</tr>
				<tr>
					<th width="20%"><spr:message code="bpmFormTemplate.html"/>:</th>
					<td><textarea id="html" name="html" codemirror="true" mirrorheight="400px" cols=100 rows=20 readonly>${fn:escapeXml(bpmFormTemplate.html)}</textarea></td>
				</tr>
				<tr>
					<th width="20%"><spr:message code="bpmFormTemplate.templateDesc"/>:</th>
					<td><c:out escapeXml="true" value="${bpmFormTemplate.templateDesc}"></c:out></td>
				</tr>
			</table>
		</div>
</div>
</body>
</html>
