<%--
	time:2012-08-06 13:56:42
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/getById.jsp" %>
	<title><spr:message code="bpmRunLog.title"/><spr:message code="operator.detail"/></title>
	<script type="text/javascript">
		//放置脚本
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="bpmRunLog.title"/><spr:message code="operator.detail"/></span>
			</div>
			<c:if test="${canReturn==0}">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back" onclick="history.back()"><span></span><spr:message code="menu.button.back"/></a></div>
				</div>
			</div>
			</c:if>
		</div>
		<div class="panel-body">
			
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th width="20%"><spr:message code="bpmRunLog.flowRunId"/>:</th>
						<td>${bpmRunLog.runid}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="bpmRunLog.processSubject"/>:</th>
						<td>${bpmRunLog.processSubject}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="bpmRunLog.username"/>:</th>
						<td>${bpmRunLog.username}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="bpmRunLog.createtime"/>:</th>
						<td><fmt:formatDate value="${bpmRunLog.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="bpmRunLog.operatortype"/>:</th>
						<td>
							<c:choose>
								<c:when test="${bpmRunLog.operatortype eq 0}"><span class="green"><spr:message code="bpmRunLog.operatortype.start"/></span></c:when>
									<c:when test="${bpmRunLog.operatortype eq 1}"><span class="green"><spr:message code="bpmRunLog.operatortype.delegate"/></span></c:when>
									<c:when test="${bpmRunLog.operatortype eq 2}"><span class="red"><spr:message code="bpmRunLog.operatortype.retrieve"/></span></c:when>
									<c:when test="${bpmRunLog.operatortype eq 3}"><span class="red"><spr:message code="bpmRunLog.operatortype.deleteInstance"/></span></c:when>
									<c:when test="${bpmRunLog.operatortype eq 4}"><span class="green"><spr:message code="bpmRunLog.operatortype.agree"/></span></c:when>
									<c:when test="${bpmRunLog.operatortype eq 5}"><span class="red"><spr:message code="bpmRunLog.operatortype.objection"/></span></c:when>
									<c:when test="${bpmRunLog.operatortype eq 6}"><span class="green"><spr:message code="bpmRunLog.operatortype.abstention"/></span></c:when>
									<c:when test="${bpmRunLog.operatortype eq 7}"><span class="green"><spr:message code="bpmRunLog.operatortype.sign"/></span></c:when>
									<c:when test="${bpmRunLog.operatortype eq 8}"><span class="red"><spr:message code="bpmRunLog.operatortype.reject"/></span></c:when>
									<c:when test="${bpmRunLog.operatortype eq 9}"><span class="red"><spr:message code="bpmRunLog.operatortype.reject2sponsor"/></span></c:when>
									<c:when test="${bpmRunLog.operatortype eq 10}"><span class="red"><spr:message code="bpmRunLog.operatortype.deleteTask"/></span></c:when>
									<c:when test="${bpmRunLog.operatortype eq 11}"><span class="green"><spr:message code="bpmRunLog.operatortype.agent"/></span></c:when>
									<c:when test="${bpmRunLog.operatortype eq 13}"><span class="green"><spr:message code="bpmRunLog.operatortype.lock"/></span></c:when>
									<c:when test="${bpmRunLog.operatortype eq 14}"><span class="green"><spr:message code="bpmRunLog.operatortype.unLock"/></span></c:when>
									<c:when test="${bpmRunLog.operatortype eq 15}"><span class="green"><spr:message code="bpmRunLog.operatortype.addOpinion"/></span></c:when>
									<c:when test="${bpmRunLog.operatortype eq 16}"><span class="green"><spr:message code="bpmRunLog.operatortype.assign"/></span></c:when>
									<c:when test="${bpmRunLog.operatortype eq 17}"><span class="green"><spr:message code="bpmRunLog.operatortype.setOwner"/></span></c:when>
									<c:when test="${bpmRunLog.operatortype eq 18}"><span class="green"><spr:message code="bpmRunLog.operatortype.endTask"/></span></c:when>
									<c:when test="${bpmRunLog.operatortype eq 22}"><span class="green"><spr:message code="bpmRunLog.operatortype.saveDraft"/></span></c:when>
									<c:when test="${bpmRunLog.operatortype eq 23}"><span class="green"><spr:message code="bpmRunLog.operatortype.finishDivert"/></span></c:when>
									<c:when test="${bpmRunLog.operatortype eq 24}"><span class="green"><spr:message code="bpmRunLog.operatortype.deleteDraft"/></span></c:when>
									<c:otherwise><span class="red" > <spr:message code="bpmRunLog.operatortype.other"/></span></c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="bpmRunLog.memo"/>:</th>
						<td>${bpmRunLog.memo}</td>
					</tr>
				</table>
				
		</div>
</div>

</body>
</html>
