<%--
	time:2011-12-14 08:41:55
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	
	<%@include file="/commons/include/getById.jsp" %>
	<title><spr:message code="bpmNodeSign.title"/><spr:message code="operator.detail"/></title>
	<script type="text/javascript">
		//放置脚本
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="bpmNodeSign.title"/><spr:message code="title.detail"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back" href="../bpmNodeSign/list.ht"><span></span><spr:message code="menu.button.back"/></a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<div class="panel-detail">
				<form id="bpmNodeSignForm" method="post" action="add2.ht">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%"><spr:message code="bpmNodeMessage.nodeId"/>:</th>
							<td>${bpmNodeSign.nodeId}</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="bpmNodeSign.voteAmount"/>:</th>
							<td>${bpmNodeSign.voteAmount}</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="bpmNodeSign.decideType"/>:</th>
							<td>${bpmNodeSign.decideType}</td>
						</tr>
						<tr>
							<th width="20%">1=<spr:message code="bpmNodeSign.voteType.percentage"/>:</th>
							<td>${bpmNodeSign.voteType}</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="bpmNodeSign.actDeployId"/>:</th>
							<td>${bpmNodeSign.actDeployId}</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
</div>

</body>
</html>
