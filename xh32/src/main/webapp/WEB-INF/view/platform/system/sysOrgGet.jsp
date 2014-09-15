<%--
	time:2011-11-09 11:20:13
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	
	<%@include file="/commons/include/get.jsp" %>
	<title><spr:message code="sysOrg.title"/><spr:message code="operator.detail"/></title>
	<style type="text/css"> 
		html{scroll:no;height:100%}
	    body {scroll:no;height:100%; padding:0px; margin:0;overflow:auto !important}
	   
    </style>
</head>
<body>
 <div class="panel" id="toppanel">	
 <c:if test="${isOtherLink==0}">
 		<c:choose>
		  	<c:when test="${action=='global' }">
		  		<f:tab curTab="info" tabName="sysOrg"/>
		  	</c:when>
		  	<c:otherwise>
		  		<f:tab curTab="info" tabName="sysOrgGrade"/>
		  	</c:otherwise>
		  </c:choose>
		<c:if test="${flag== 1}">
		    <div class="panel-toolbar" id="pToolbar">
				<div class="toolBar">
						<div class="group"><a class="link back"  href="slistById.ht?orgId=${sysOrg.orgSupId}&path=${path}" ><spr:message code="menu.button.back"/></a></div>	
				</div>	
		    </div>
	    </c:if>
	   </c:if>
		<div class="panel-body" id="pbody">	
			<c:choose>
				<c:when test="${empty sysOrg}">
					<div style="text-align: center;margin-top: 10%;"><spr:message code="sysUserOrg.emptyOrg"/></div>
				</c:when>
				<c:otherwise>
					
						<table id="tableid" class="table-detail" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th width="20%"><spr:message code="sysOrg.dimension"/>:</th>
								<td width="80%" colspan="3">${sysOrg.demName}</td>
								
							</tr>
							<tr>
								<th width="20%"><spr:message code="sysOrg.orgName"/>:</th>
								<td width="30%"><span class="green">${sysOrg.orgName}</span></td>
								<th width="20%"><spr:message code="sysOrg.creatorId"/>:</th>
								<td width="30%">${sysOrg.createName}</td>
							</tr>
							<tr>
								<th><spr:message code="sysOrg.orgSupId"/>:</th>
								<td>${sysOrg.orgSupName}</td>
								<th><spr:message code="sysOrg.updateId"/>:</th>
								<td>${sysOrg.updateName}</td>
							</tr>
							
							<tr>
								<th><spr:message code="sysOrg.orgType"/>:</th>
								<td>							
								 			<c:forEach items="${sysOrgTypelist}" var="org" >
								 					<c:if test="${sysOrg.orgType eq org.id}"><span class="green">${org.name}</span></c:if>													
											</c:forEach> 				
								</td>
								<th><spr:message code="sysOrg.createtime"/>:</th>
								<td>${f:shortDate(sysOrg.createtime)}</td>
							</tr>
							<tr>
								<th><spr:message code="sysUserOrg.header"/>:</th>
								<td>${userNameCharge}</td>
								<th><spr:message code="sysOrg.updatetime"/>:</th>
								<td>${f:shortDate(sysOrg.updatetime)}</td>
							</tr>
							<tr>
							    <th><spr:message code="sysOrg.allPath"/>:</th>
								<td><span class="green">${sysOrg.orgPathname}</span></td>
							    <th><spr:message code="sysOrg.orgDesc"/>:</th>
							    <td colspan="3">${sysOrg.orgDesc}</td>
							 </tr>
					    </table>
				</c:otherwise>
			</c:choose>	
		    
	    </div>   
 </div>
</body>
</html>
