
<%--
	time:2013-11-28 16:17:48
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>

<%@include file="/commons/include/get.jsp"%>
<title><spr:message code="positionJob.selector.list"/><spr:message code="operator.detail"/></title>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="positionJob.selector.list"/><spr:message code="title.detail"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link back" href="list.ht"><span></span><spr:message code="menu.button.back"/></a>
					</div>
				</div>
			</div>
		</div>
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<th width="20%"><spr:message code="positionJob.name"/>:</th>
				<td>${job.jobname}</td>
			</tr>
			<tr>
				<th width="20%"><spr:message code="positionJob.jobcode"/>:</th>
				<td>${job.jobcode}</td>
			</tr>
			<tr>
				<th width="20%"><spr:message code="positionJob.desc"/>:</th>
				<td>${job.jobdesc}</td>
			</tr>
			<%-- 
			<tr>
				<th width="20%"><spr:message code="positionJob.setid"/>:</th>
				<td>${job.setid}</td>
			</tr>
		      --%>
			<tr>
				<th width="20%"><spr:message code="positionJob.isDeleted"/>:</th>
				<td>
					<c:choose>
					  	<c:when test="${job.isdelete==1 }">
					  		<spr:message code="menu.button.del"/>
					  	</c:when>
					  	<c:otherwise>
					  		<spr:message code="menu.button.undelete"/>
					  	</c:otherwise>
		            </c:choose>
				</td>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>

