<%--
	time:2011-11-23 11:07:27
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/getById.jsp" %>
	<title><spr:message code="globalType"/><spr:message code="title.detail"/></title>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="globalType"/><spr:message code="title.detail"/></span>
			</div>
			<c:if test="${canReturn==0}">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class=" link back" href="../globalType/list.ht"><span></span><spr:message code="menu.button.back"/></a></div>
				</div>
			</div>
			</c:if>
		</div>
		<div class="panel-body">
				<form id="globalTypeForm" method="post" action="add2.ht">
				<div class="panel-detail">
						<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th width="20%"><spr:message code="globalType.typeName"/>:</th>
								<td>${globalType.typeName}</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="globalType.nodeKey"/>:</th>
								<td>${globalType.nodeKey}</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="globalType.catKey"/>:</th>
								<td>${globalType.catKey}</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="globalType.type"/>:</th>
								<td>
									<c:if test="${globalType.type==0}"><spr:message code="globalType.type.tile"/></c:if> 
									<c:if test="${globalType.type==1}"><spr:message code="globalType.type.tree"/></c:if> 
								
								</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="globalType.sn"/>:</th>
								<td>${globalType.sn}</td>
							</tr>						
							<tr>
								<th width="20%"><spr:message code="globalType.parent"/>:</th>
								<td>${globalType.parentId}</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="globalType.depth"/>:</th>
								<td>${globalType.depth}</td>
							</tr>
							<tr>
								<th width="20%">nodePath:</th>
								<td>${globalType.nodePath}</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="globalType.userId"/>:</th>
								<td>${globalType.userId}</td>
							</tr>
							<tr>
								<th width="20%">depId:</th>
								<td>${globalType.depId}</td>
							</tr>
							
						</table>
					</div>
				</form>
		</div>
</div>

</body>
</html>
