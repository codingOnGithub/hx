<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.hotent.platform.model.system.SysUser"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>

<%@include file="/commons/include/get.jsp"%>
<title><spr:message code="sysUser.title"/><spr:message code="title.manage"/></title>
<script type="text/javascript"
	src="${ctx }/js/lg/plugins/ligerWindow.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerTab.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$("#ligerTab-div").ligerTab();
	});
</script>
</head>
<body>

	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="bpmNodeUser.preview"/></span>
			</div>
		</div>
		<div class="panel-body">
			<div id="ligerTab-div">
				<div title="<spr:message code='bpmNodeUser.preview.flowUser'/>">
					<display:table name="attendTaskExecutors"
						id="attendTaskExecutorItem" cellpadding="1" cellspacing="1"
						sort="page" class="table-grid">
						<display:column titleKey="bpmNodeUser.preview.executorType" style="text-align:left">
							<c:choose>
								<c:when test="${attendTaskExecutorItem.type=='user'}">
									<span class="black"><spr:message code="bpmNodeUser.userType.user"/></span>
								</c:when>
								<c:when test="${attendTaskExecutorItem.type=='org'}">
									<span class="black"><spr:message code="bpmNodeUser.userType.org"/></span>
								</c:when>
								<c:when test="${attendTaskExecutorItem.type=='role'}">
									<span class="black"><spr:message code="bpmNodeUser.userType.role"/></span>
								</c:when>
								<c:when test="${attendTaskExecutorItem.type=='pos'}">
									<span class="black"><spr:message code="bpmNodeUser.userType.position"/></span>
								</c:when>
							</c:choose>
						</display:column>
						<display:column property="executeId" titleKey="bpmNodeUser.preview.executorID"
							style="text-align:left"></display:column>
						<display:column property="executor" titleKey="bpmNodeUser.preview.executor">
						</display:column>
					</display:table>
				</div>
				<div title="<spr:message code='bpmNodeUser.preview.receiver'/>">
					<display:table name="informTaskExecutors"
						id="informTaskExecutorItem" cellpadding="1" cellspacing="1"
						sort="page" class="table-grid">
						<display:column titleKey="bpmNodeUser.preview.executorType" style="text-align:left">
							<c:choose>
								<c:when test="${informTaskExecutorItem.type=='user'}">
									<span class="black"><spr:message code="bpmNodeUser.userType.user"/></span>
								</c:when>
								<c:when test="${informTaskExecutorItem.type=='org'}">
									<span class="black"><spr:message code="bpmNodeUser.userType.org"/></span>
								</c:when>
								<c:when test="${informTaskExecutorItem.type=='role'}">
									<span class="black"><spr:message code="bpmNodeUser.userType.role"/></span>
								</c:when>
								<c:when test="${informTaskExecutorItem.type=='pos'}">
									<span class="black"><spr:message code="bpmNodeUser.userType.position"/></span>
								</c:when>
							</c:choose>
						</display:column>
						<display:column property="executeId" titleKey="bpmNodeUser.preview.executorID"
							style="text-align:left"></display:column>
						<display:column property="executor" titleKey="bpmNodeUser.preview.executor">
						</display:column>
					</display:table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>


