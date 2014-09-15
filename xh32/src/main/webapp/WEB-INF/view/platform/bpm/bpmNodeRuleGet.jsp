<%--
	time:2011-12-14 15:41:53
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	
	<%@include file="/commons/include/getById.jsp" %>
	<title><spr:message code="controller.bpmNodeRule"/><spr:message code="operator.detail"/></title>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="controller.bpmNodeRule"/><spr:message code="title.detail"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back" href="../bpmNodeRule/list.ht"><span></span><spr:message code="menu.button.back"/></a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
				<form id="bpmNodeRuleForm" method="post" action="add2.ht">
					<div class="panel-detail">
						<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th width="20%"><spr:message code="bpmDefinition.nodeSet.title"/>ID:</th>
								<td>${bpmNodeRule.setId}</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="bpmNodeRule.ruleName"/>:</th>
								<td>${bpmNodeRule.ruleName}</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="bpmNodeRule.conditionCode"/>:</th>
								<td>${bpmNodeRule.conditionCode}</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="bpmNodeRule.actDefId"/>:</th>
								<td>${bpmNodeRule.actDefId}</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="bpmNodeRule.priority"/>:</th>
								<td>${bpmNodeRule.priority}</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="bpmNodeRule.targetNode"/>:</th>
								<td>${bpmNodeRule.targetNode}</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="bpmNodeRule.targetNodeName"/>:</th>
								<td>${bpmNodeRule.targetNodeName}</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="bpmNodeRule.memo"/>:</th>
								<td>${bpmNodeRule.memo}</td>
							</tr>
						</table>
					</div>
				</form>
		</div>
</div>

</body>
</html>
