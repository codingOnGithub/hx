<%--
	time:2011-11-26 18:19:16
--%>
<%@page language="java" pageEncoding="UTF-8"%>

<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/getById.jsp" %>	
	<title><spr:message code="sysFile"/><spr:message code="title.detail"/></title>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="sysFile"/><spr:message code="title.detail"/></span>
			</div>
			<c:if test="${canReturn==0}">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back" href="${returnUrl}"><span></span><spr:message code="menu.button.back"/></a></div>
				</div>
			</div>
			</c:if>
		</div>
		<div class="panel-body">
			<form id="sysFileForm" method="post" action="add2.ht">
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">						
					<tr>
						<th width="20%"><spr:message code="sysFile.fileName"/>:</th>
						<td>${sysFile.fileName}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="sysFile.filePath"/>:</th>
						<td>
							<c:choose>
								<c:when test="${saveType eq 'database'}"><spr:message code="sysFile.inDataBase"/></c:when>
								<c:otherwise>${sysFile.filePath}</c:otherwise>
							</c:choose>					
						</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="sysFile.createtime"/>:</th>
						<td>
							<fmt:formatDate value="${sysFile.createtime }" pattern="yyyy-MM-dd HH:ss"/>
						</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="sysFile.ext"/>:</th>
						<td>${sysFile.ext}</td>
					</tr>						
					<tr>
						<th width="20%"><spr:message code="sysFile.note"/>:</th>
						<td>${sysFile.note}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="sysFile.creator"/>:</th>
						<td>${sysFile.creator}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="sysFile.totalBytes"/>:</th>
						<td>${sysFile.totalBytes}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="sysFile.get.operate"/>:</th>
						<td>
							<c:choose>
								<c:when test="${sysFile.delFlag eq 1}"><font color="red"><spr:message code="sysFile.get.operate.msg"/></font></c:when>
								<c:otherwise><a href="download.ht?fileId=${sysFile.fileId }" target="_blank" class="link download"><spr:message code="operator.download"/></a></c:otherwise>
							</c:choose>
						</td>
					</tr>
				</table>
			</form>
		</div>
</div>

</body>
</html>
