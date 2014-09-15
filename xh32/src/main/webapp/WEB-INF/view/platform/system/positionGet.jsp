
<%--
	time:2013-11-27 10:19:23
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>

<%@include file="/commons/include/get.jsp"%>
<title><spr:message code="position.title.relation"/><spr:message code="menu.button.detail"/>明细</title>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="position.title.relation"/><spr:message code="title.detail"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link back" href="${returnUrl}"><span></span><spr:message code="menu.button.back"/></a>
					</div>
				</div>
			</div>
		</div>
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<th width="20%"><spr:message code="sysOrg.orgName"/>:</th>
				<td>${sysOrg.orgName} </td>
			</tr>
			<tr>
				<th width="20%"><spr:message code="positionJob.name"/>:</th>
				<td>${position.jobName}</td>
				<%-- 
				<c:forEach items="${Jobist}" var="job" >
					 <c:if test="${position.jobId eq org.jobid}">${job.jobname}</c:if>													
				</c:forEach> 
				--%>
			</tr>
			<tr>
				<th width="20%"><spr:message code="position.posName"/>:</th>
				<td>${position.posName}</td>
			</tr>
			<tr>
				<th width="20%"><spr:message code="position.posDesc"/>:</th>
			<td><textarea  cols="30" rows="4"  style="width:258px !important">${position.posDesc}</textarea></td>
			</tr>
			<tr>
				<th width="20%"><spr:message code="position.isDeleted"/>:</th>
				<td>
			<c:choose>
			    <c:when test="${position.isDelete ==1}">
			          <span class="red"><spr:message code="menu.button.del"/></span>
			    </c:when>
			    <c:otherwise>
			           <span class="green"><spr:message code="no"/></span>
			    </c:otherwise>			   
		    </c:choose>
				</td>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>

