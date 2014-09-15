<%--
	time:2012-06-25 11:05:09
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/getById.jsp" %>
	<title><spr:message code="bpmFormDialog.title"/><spr:message code="title.detail"/></title>
	<script type="text/javascript">
		//放置脚本
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="bpmFormDialog.title"/><spr:message code="title.detail"/></span>
			</div>
			<c:if test="${canReturn==0}">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="back bar-button" href="list.ht"><span></span><spr:message code="menu.button.back"/></a></div>
				</div>
			</div>
			</c:if>
		</div>
		<div class="panel-body">
				<div class="panel-detail">
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th width="20%"><spr:message code="bpmFormDialog.name"/>:</th>
						<td>${bpmFormDialog.name}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="bpmFormDialog.alias"/>:</th>
						<td>${bpmFormDialog.alias}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="bpmFormDialog.style"/> 0,<spr:message code="bpmFormDialog.style.list"/>  1,<spr:message code="bpmFormDialog.style.tree"/>:</th>
						<td>${bpmFormDialog.style}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="bpmFormDialog.width"/>:</th>
						<td>${bpmFormDialog.width}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="bpmFormDialog.height"/>:</th>
						<td>${bpmFormDialog.height}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="bpmFormDialog.issingle"/> 0,<spr:message code="bpmFormDialog.issingle.mult"/>1.<spr:message code="bpmFormDialog.issingle.single"/>:</th>
						<td>${bpmFormDialog.issingle}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="bpmFormDialog.needpage"/>:</th>
						<td>${bpmFormDialog.needpage}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="bpmFormDialog.istable"/> 0,<spr:message code="bpmFormDialog.istable.view"/>1,<spr:message code="bpmFormDialog.istable.table"/>:</th>
						<td>${bpmFormDialog.istable}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="bpmFormDialog.objname"/>:</th>
						<td>${bpmFormDialog.objname}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="bpmFormDialog.displayfield"/>:</th>
						<td>${bpmFormDialog.displayfield}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="bpmFormDialog.conditionfield"/>:</th>
						<td>${bpmFormDialog.conditionfield}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="bpmFormDialog.resultfield"/>:</th>
						<td>${bpmFormDialog.resultfield}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="bpmFormDialog.dsname"/>:</th>
						<td>${bpmFormDialog.dsname}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="bpmFormDialog.dsalias"/>:</th>
						<td>${bpmFormDialog.dsalias}</td>
					</tr>
				</table>
			</div>
		</div>
</div>

</body>
</html>
