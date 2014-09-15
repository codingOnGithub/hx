<%--
	time:2012-05-25 10:16:17
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/getById.jsp" %>
	<title><spr:message code="sysOfficeTemplate"/> <spr:message code="title.detail"/></title>
	<script type="text/javascript">
		//放置脚本
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="sysOfficeTemplate"/> <spr:message code="title.detail"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back" href="list.ht"><span></span><spr:message code="menu.button.back"/></a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%"><spr:message code="sysOfficeTemplate.subject"/>:</th>
							<td>${sysOfficeTemplate.subject}</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="sysOfficeTemplate.templateType"/>:</th>
							<td>
								<c:if test="${sysOfficeTemplate.templatetype==1}"><spr:message code="sysOfficeTemplate.templateType.common"/></c:if>
								<c:if test="${sysOfficeTemplate.templatetype==2}"><spr:message code="sysOfficeTemplate.templateType.nest"/></c:if>
							</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="sysOfficeTemplate.creator"/>:</th>
							<td>${sysOfficeTemplate.creator}</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="sysOfficeTemplate.createtime"/>:</th>
							<td>
								<fmt:formatDate value="${sysOfficeTemplate.createtime}"/>
							</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="sysOfficeTemplate.path"/>:</th>
							<td>
								<c:choose>
									<c:when test="${saveType eq 'database'}"><spr:message code="sysOfficeTemplate.inDatabase"/></c:when>
									<c:otherwise>${sysOfficeTemplate.path}</c:otherwise>
								</c:choose>	
							</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="sysOfficeTemplate.memo"/>:</th>
							<td>${sysOfficeTemplate.memo}</td>
						</tr>
					</table>
				</div>
		</div>

</body>
</html>
