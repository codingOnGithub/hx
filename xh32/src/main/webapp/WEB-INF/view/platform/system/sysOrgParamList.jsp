<%--
	time:2012-02-23 17:43:35
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	
	<%@include file="/commons/include/get.jsp" %>
	<title><spr:message code="sysOrgParam.title"/></title>
</head>
<body>
<div class="panel">
	<c:choose>
		<c:when test="${scope=='global'}">
			<f:tab curTab="param" tabName="sysOrg"/>	
		</c:when>
		<c:otherwise>
			<f:tab curTab="param" tabName="sysOrgGrade"/>
		</c:otherwise>
	</c:choose>
		
	<div class="panel-detail">
		<c:choose>
			<c:when test="${empty sysOrg}">
				<div style="text-align: center;margin-top: 10%;"><spr:message code="sysUserOrg.emptyOrg"/></div>
			</c:when>
			<c:otherwise>
		    	<display:table name="userParamList" id="paramItem"  cellpadding="1" cellspacing="1" export="false"  class="table-grid">
					<display:column property="paramName" titleKey="sysOrgParam.paramName" style="text-align:left;" ></display:column>
					<display:column property="paramValue"  titleKey="sysOrgParam.paramValue" media="html"></display:column>
				</display:table>
			</c:otherwise>
		</c:choose>	
	</div>
 </div>
</body>
</html>