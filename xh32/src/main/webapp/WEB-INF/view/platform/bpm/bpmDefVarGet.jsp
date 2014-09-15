<%--
	time:2011-12-01 16:50:08
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	
	<%@include file="/commons/include/getById.jsp" %>
	<title><spr:message code="bpmDefVar.title"/><spr:message code="operator.detail"/></title>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="bpmDefVar.title"/><spr:message code="title.detail"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back"  href="../bpmDefVar/list.ht?defId=${bpmDefVar.defId}&actDeployId=${bpmDefVar.actDeployId}&actDefId=${actDefId}"><span></span><spr:message code="menu.button.back"/></a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<div class="panel-detail">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%"><spr:message code="bpmDefVar.defId"/>:</th>
							<td>${bpmDefVar.defId}</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="bpmDefVar.varName"/>:</th>
							<td>${bpmDefVar.varName}</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="bpmDefVar.varKey"/>:</th>
							<td>${bpmDefVar.varKey}</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="bpmDefVar.varDataType"/>:</th>
							<td>
							<c:if test="${bpmDefVar.varDataType eq 'string'}"><spr:message code="bpmDefVar.string"/></c:if>
							<c:if test="${bpmDefVar.varDataType eq 'number'}"><spr:message code="bpmDefVar.number"/></c:if>
							<c:if test="${bpmDefVar.varDataType eq 'date'}"><spr:message code="bpmDefVar.date"/></c:if>
							</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="bpmDefVar.defValue"/>:</th>
							<td>${bpmDefVar.defValue}</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="bpmDefVar.actDeployId"/>:</th>
							<td>${bpmDefVar.actDeployId}</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="bpmDefVar.nodeName"/>:</th>
							<td>${bpmDefVar.nodeName}</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="bpmDefVar.nodeId"/>:</th>
							<td>${bpmDefVar.nodeId}</td>
						</tr>
					</table>
			</div>
		</div>
</div>

</body>
</html>
