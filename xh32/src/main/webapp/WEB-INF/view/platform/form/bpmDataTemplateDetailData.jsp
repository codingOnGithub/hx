<%@page pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
	<head>
		<%@include file="/commons/include/customForm.jsp" %>
		<title><spr:message code="bpmDataTemplate.dataTemplate"/><spr:message code="title.detail"/></title>
	</head>
	<body >
		<div class="panel">
			<div class="l-layout-header"><spr:message code="bpmDataTemplate.dataTemplate"/><spr:message code="title.detail"/></div>
		</div>
		<div class="panel-body">
				${form}
		</div>
	</div>
	</body>
</html>