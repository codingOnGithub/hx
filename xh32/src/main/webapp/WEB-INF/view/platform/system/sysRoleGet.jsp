<%--
	time:2011-11-28 11:31:14
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/getById.jsp" %>
	<title><spr:message code="sys.role.getTitle"/></title>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="sys.role.getTitle"/></span>
			</div>
			<c:if test="${isOtherLink==0}">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back" href="../sysRole/list.ht"><span></span><spr:message code="menu.button.back"/></a></div>
				</div>
			</div>
			</c:if>
		</div>
		<div class="panel-body">
			<div class="panel-detail">
				<form id="sysRoleForm" method="post" action="add2.ht">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
					    <tr>
							<th width="20%"><spr:message code="sys.user.roleName"/>:</th>
							<td>${sysRole.roleName}</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="sys.user.systemName"/>:</th>
							<td>
								<c:if test="${not empty subsystem}">
									${subsystem.sysName}
								</c:if>
							</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="sys.role.alias"/>:</th>
							<td>${sysRole.alias}</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="sysRole.memo"/>:</th>
							<td>${sysRole.memo}</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="sysRole.allowDel"/>:</th>
							<td>
							<c:choose>
							<c:when test="${sysRole.allowDel eq 1}"><spr:message code="subSystem.allowDel.allow"/></c:when>
							<c:when test="${sysRole.allowDel eq 0}"><spr:message code="subSystem.allowDel.forbid"/></c:when>
							<c:otherwise><spr:message code="sys.role.noState"/></c:otherwise>
							</c:choose>
							</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="sysRole.allowEdit"/>:</th>
							<td>
							<c:choose>
							<c:when test="${sysRole.allowEdit eq 1}"><spr:message code="subSystem.allowDel.allow"/></c:when>
							<c:when test="${sysRole.allowEdit eq 0}"><spr:message code="subSystem.allowDel.forbid"/></c:when>
							<c:otherwise><spr:message code="sys.role.noState"/></c:otherwise>
							</c:choose>
							</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="sysRole.enabled"/>:</th>
							<td>
							<c:choose>
							<c:when test="${sysRole.enabled eq 1}"><spr:message code="subSystem.allowDel.forbid"/></c:when>
							<c:when test="${sysRole.enabled eq 0}"><spr:message code="menu.button.disabled"/></c:when>
							<c:otherwise><spr:message code="sys.role.noState"/></c:otherwise>
							</c:choose>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
</div>

</body>
</html>
