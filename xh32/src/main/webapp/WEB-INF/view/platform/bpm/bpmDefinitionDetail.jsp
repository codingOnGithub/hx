<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
    <title><spr:message code="bpmDefinition.detail.title"/></title>
	<style type="text/css"> 
	    div.flowNode{cursor:pointer;}
    </style>
</head>
<body>
		<div class="panel">
			<div class="hide-panel">
				<div class="panel-top">
				
				
		        <jsp:include page="incDefinitionHead.jsp">
			    	<jsp:param value="<spr:message code='bpmDefinition.detail.title'/>" name="title"/>
			    </jsp:include>

			 <div class="panel-container">
				<f:tab curTab="detail" tabName="flow"/>
				<div class="panel-detail">
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th width="20%"><spr:message code="bpmDefinition.type"/></th>
						<td>${globalType.typeName}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="bpmDefinition.subject"/>:</th>
						<td>${bpmDefinition.subject}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="bpmDefinition.defKey"/>:</th>
						<td>${bpmDefinition.defKey}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="bpmDefinition.taskNameRule"/>:</th>
						<td>${bpmDefinition.taskNameRule}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="bpmDefinition.descp"/>:</th>
						<td>${bpmDefinition.descp}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="bpmDefinition.createBy"/>:</th>
						<td><f:userName userId="${bpmDefinition.createBy}"/></td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="bpmDefinition.createtime"/>:</th>
						<td><fmt:formatDate value="${bpmDefinition.createtime}" pattern="yyyy-MM-dd HH:mm"/> </td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="bpmDefinition.updateBy"/>:</th>
						<td><f:userName userId="${bpmDefinition.updateBy}"/></td>
					</tr>
					<tr>
						<th><spr:message code="bpmDefinition.reason"/>:</th>
						<td>${bpmDefinition.reason}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="bpmDefinition.updatetime"/>:</th>
						<td><fmt:formatDate value="${bpmDefinition.updatetime}" pattern="yyyy-MM-dd HH:mm"/> </td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="bpmDefinition.status"/>:</th>
						<td>
							<c:choose>
								<c:when test="${bpmDefinition.status==1}">
									<font color='green'><spr:message code="bpmDefinition.status.start"/></font>
								</c:when>
								<c:when test="${bpmDefinition.status==2}">
									<font color='red'><spr:message code="bpmDefinition.status.disabled"/></font>
								</c:when>								
								<c:when test="${bpmDefinition.status==3}">
									<font color='red'><spr:message code="bpmDefinition.status.disabledcase"/></font>
								</c:when>
								<c:when test="${bpmDefinition.status==4}">
									<font color='green'><spr:message code="bpmDefinition.status.test"/></font>
								</c:when>
								<c:otherwise><font color='red'><spr:message code="bpmDefinition.status.unknow"/>
								</font></c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="bpmDefinition.versionNo"/>:</th>
						<td>${bpmDefinition.versionNo}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="bpmDefinition.actDef"/>ID:</th>
						<td>${bpmDefinition.actDefId}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="bpmDefinition.actDef"/>Key:</th>
						<td>${bpmDefinition.actDefKey}</td>
					</tr>
				</table>
				</div>
			</div>
		</div>

</body>
</html>
