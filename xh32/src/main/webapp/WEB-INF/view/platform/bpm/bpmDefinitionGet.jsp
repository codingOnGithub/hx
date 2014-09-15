<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/getById.jsp" %>
	<title><spr:message code="bpmDefinition.detail.title"/></title>
	
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="bpmDefinition.detail.title"/>--${bpmDefinition.subject}</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<c:choose>
							<c:when test="${not empty param['returnFromDetail']}">
								<a class="link back" href="myList.ht"><span></span><spr:message code="menu.button.back"/></a>
							</c:when>
							<c:otherwise>
								<a class="link back" href="list.ht"><span></span><spr:message code="menu.button.back"/></a>		
							</c:otherwise>
						
						</c:choose>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="20%" nowrap="nowrap"><spr:message code="bpmDefinition.type"/></th>
					<td>${globalType.typeName}</td>
				</tr>
				<tr>
					<th width="20%" nowrap="nowrap"><spr:message code="bpmDefinition.subject"/>:</th>
					<td>${bpmDefinition.subject}</td>
				</tr>
				<tr>
					<th width="20%" nowrap="nowrap"><spr:message code="bpmDefinition.defKey"/>:</th>
					<td>${bpmDefinition.defKey}</td>
				</tr>
				<tr>
					<th width="20%" nowrap="nowrap"><spr:message code="bpmDefinition.taskNameRule"/>:</th>
					<td>${bpmDefinition.taskNameRule}</td>
				</tr>
				<tr>
					<th width="20%" nowrap="nowrap"><spr:message code="bpmDefinition.descp"/>:</th>
					<td>${bpmDefinition.descp}</td>
				</tr>
				<tr>
					<th width="20%" nowrap="nowrap"><spr:message code="bpmDefinition.createtime"/>:</th>
					<td><fmt:formatDate value="${bpmDefinition.createtime}" pattern="yyyy-MM-dd HH:mm"/> </td>
				</tr>
				<tr>
					<th width="20%" nowrap="nowrap"><spr:message code="bpmDefinition.status"/>:</th>
					<td>
						<c:choose>
							<c:when test="${bpmDefinition.status==1}">
								<font color='green'><spr:message code="bpmDefinition.status.start"/></font>
							</c:when>
							<c:otherwise><spr:message code="bpmDefinition.status.disabled"/></c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th width="20%"><spr:message code="bpmDefinition.versionNo"/>:</th>
					<td>${bpmDefinition.versionNo}</td>
				</tr>
				<tr>
					<th><spr:message code="bpmDefinition.bpmImage"/></th>
					<td>
						<img src="${ctx}/bpmImage?deployId=${bpmDefinition.actDeployId}"/>
					</td>
				</tr>
			</table>
		</div>
</div>

</body>
</html>
