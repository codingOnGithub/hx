<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@include file="/commons/include/get.jsp"%>
<title><spr:message code="bpmNodeRule.title"/></title>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowRuleWindow.js"></script>
<script type="text/javascript">
	var actDefId = "${bpmDefinition.actDefId}";
	var deployId = "${bpmDefinition.actDeployId}";
	function setNodeRule(nodeId, nodeName) {
		var url = __ctx + "/platform/bpm/bpmNodeRule/edit.ht?deployId=" + deployId + "&actDefId=" + actDefId + "&nodeId=" + nodeId + "&nodeName=" + encodeURIComponent(nodeName);
		var winArgs = "dialogWidth=800px;dialogHeight=650px;help=0;status=0;scroll=1;center=0;resizable=1;";
		url = url.getNewUrl();
		var rtn = window.showModalDialog(url, "", winArgs);
		//window.location.reload(true);
	}
</script>
</head>
<body>
	<c:if test="${empty nodeId}">
		<jsp:include page="incDefinitionHead.jsp">
			<jsp:param value="<spr:message code="bpmNodeRule.nodeUser"/>" name="title" />
		</jsp:include>
		<f:tab curTab="<spr:message code="bpmNodeRule.jumpRule"/>" tabName="flow" />
	</c:if>
	<div class="panel">
		
		<div class="panel-body">
			
				<form action="" method="post" id="">
					<input type="hidden" name="defId" value="${defId}" /> <input type="hidden" name="nodeId" value="${nodeId}" />
					<table class="table-grid" style="width: 100%">
						<thead>
							<tr>
								<th width="30" nowrap="nowrap"><spr:message code="list.orderNo"/></th>
								<th ><spr:message code="bpmNodeRule.nodeName"/></th>
								<th ><spr:message code="bpmNodeRule.isDefRule"/></th>
								<th ><spr:message code="list.manage"/></th>
							</tr>
						</thead>
						<c:forEach items="${nodeSets}" var="nodeSet" varStatus="i">
							<tr>
								<td>${i.count}</td>
								<td>${nodeSet.nodeName}(${nodeSet.nodeId})</td>
								<td>
								<c:choose>
										<c:when test="${fn:length(nodeSetNodeRulesMap[nodeSet.setId])==0}">
											<span class="red"><spr:message code="search.select.none"/></span>
										</c:when>
										<c:otherwise>
											<span class="green"><spr:message code="bpmNodeRule.defined"/></span>
	
										</c:otherwise>
									</c:choose> </td>
								<td>
									<a href="#" class="link edit" onclick="setNodeRule('${nodeSet.nodeId}','${nodeSet.nodeName}')"><spr:message code="menu.button.edit"/></a>
								</td>
							</tr>
						</c:forEach>
					</table>
					<div style="height: 40px"></div>
				</form>
			
		</div>
	</div>
</body>
</html>
