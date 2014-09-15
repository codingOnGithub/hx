<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
 <%@include file="/commons/include/form.jsp" %>  
    <title><spr:message code="bpmDefinition.nodeSummary.title"/></title>
      
         <script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowEventWindow.js" ></script>
 		<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowRuleWindow.js" ></script>
        <script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowReminderWindow.js" ></script>
   		<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/BpmNodeButtonWindow.js" ></script>
   		<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/BpmNodeUserSetWindow.js" ></script>
   		 <script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowApprovalItemWindow.js" ></script>
<style>
.green-set{
 	font-weight: bold;color: green;font-size: 20px;
 }
.red-set{
	font-weight: bold;color: red;font-size: 20px;
}
.normal-set{
	font-weight: bold;font-size: 20px;
}

.header {
	height: 26px;
    margin-top: 2px;
    padding:8px 5px 5px;
    background:#ebebeb;
    border-bottom: solid 1px #cacaca;
    border-top: solid 1px #cacaca;
}

 
 </style>
</head>    
<body>
	<div class="panel">
	<c:if test="${empty nodeId}">
		<jsp:include page="incDefinitionHead.jsp">
			<jsp:param value="<spr:message code='bpmDefinition.nodeSummary.title'/>" name="title" />
		</jsp:include>
		
		<div class="panel-container">
		<f:tab curTab="nodeSummary" tabName="flow" />
		
	</c:if>	
	<c:if test="${!empty nodeId}">
	      <div class="panel-container">
	</c:if>
	
		<div class="panel-body">
			<div>
				<span class="green-set">√</span>：<spr:message code="bpmDefinition.nodeSummary.alreadySet"/> &nbsp;&nbsp;&nbsp;&nbsp;
				<span class="red-set" >×</span>：<spr:message code="bpmDefinition.nodeSummary.notSet"/>&nbsp;&nbsp;&nbsp;&nbsp;
				<span class="normal-set" >-</span>：<spr:message code="bpmDefinition.nodeSummary.notFunction"/>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#" class="link edit" title="<spr:message code="bpmDefinition.nodeSummary.setting"/>" ></a>：<spr:message code="bpmDefinition.nodeSummary.canSet"/>
			</div>
			<form method="post" >
				<input type="hidden" name="defId" value="${defId}" />
				 <input type="hidden" name="nodeId" value="${nodeId}" />
				<table class="table-grid" style="width: 100%">
						<tr>
							<td width="15px" nowrap="nowrap"  rowspan="2" style="text-align: center;font-weight: bold;" class="header"  ><spr:message code="list.orderNo"/></td>
							<td rowspan="2" style="text-align: center;font-weight: bold;" class="header "><spr:message code="bpmDefinition.nodeSummary.nodeName"/></td>
							<td rowspan="2" colspan="2" style="text-align: center;font-weight: bold;" class="header"><spr:message code="bpmDefinition.nodeSummary.nodeUser"/></td>
							<td rowspan="2" colspan="2" style="text-align: center;font-weight: bold;" class="header"><spr:message code="bpmDefinition.nodeSummary.taskApprovalItems"/></td>
							<td colspan="5" style="text-align: center;font-weight: bold;" class="header"><spr:message code="bpmDefinition.nodeSummary.flowScript"/></td>
							<td rowspan="2" colspan="2" style="text-align: center;font-weight: bold;" class="header"><spr:message code="bpmDefinition.nodeSummary.nodeRules"/></td>
							<td rowspan="2" colspan="2" style="text-align: center;font-weight: bold;" class="header"><spr:message code="bpmDefinition.nodeSummary.bpmForm"/></td>
							<td rowspan="2" colspan="2" style="text-align: center;font-weight: bold;" class="header"><spr:message code="bpmDefinition.nodeSummary.nodeButton"/></td>
							<td rowspan="2" colspan="2" style="text-align: center;font-weight: bold;" class="header"><spr:message code="bpmDefinition.nodeSummary.taskReminder"/></td>
						</tr>
						<tr>
							<td nowrap="nowrap" style="text-align: center;font-weight: bold;" class="header"><spr:message code="bpmDefinition.nodeSummary.preScript"/></td>
							<td nowrap="nowrap" style="text-align: center;font-weight: bold;" class="header"><spr:message code="bpmDefinition.nodeSummary.afterScript"/></td>
							<td nowrap="nowrap" style="text-align: center;font-weight: bold;"class="header"><spr:message code="bpmDefinition.nodeSummary.assignScript"/></td>
						    <td nowrap="nowrap" style="text-align: center;font-weight: bold;"class="header"><spr:message code="bpmDefinition.nodeSummary.startScript"/></br><spr:message code="bpmDefinition.nodeSummary.endScript"/></td>
							<td nowrap="nowrap" style="text-align: center;font-weight: bold;" class="header">&nbsp;</td>
						</tr>
						<tr>
							<td>-</td>
							<td><spr:message code="bpmDefinition.nodeSummary.globalSet"/></td>
							<td><span class="normal-set">-</span></td>
							<td><span class="normal-set">-</span></td>
							<td>
								<c:choose>
									<c:when test="${globalApprovalMap['global']}">
										<span class="green-set">√</span>
									</c:when>
									<c:otherwise>
										<span class="red-set" >×</span>
									</c:otherwise>
								</c:choose> 
							</td>
							<td>
								<a href="#" class="link edit" title="<spr:message code="bpmDefinition.nodeSummary.setting"/>" onclick="FlowApprovalItemWindow({actDefId:'${actDefId}',activitiId:'',defId:'${defId}',callback:function(){window.location.reload()}});"></a>
							</td>
							<td><span class="normal-set">-</span></td>
							<td><span class="normal-set">-</span></td>
							<td><span class="normal-set">-</span></td>
							<td><span class="normal-set">-</span></td>
							<td><span class="normal-set">-</span></td>
							<td><span class="normal-set">-</span></td>
							<td><span class="normal-set">-</span></td>
							<td>
								<c:choose>
									<c:when test="${formMap['global']}">
										<span class="green-set">√</span>
									</c:when>
									<c:otherwise>
										<span class="red-set">×</span>
									</c:otherwise>
								</c:choose> 
							</td>
							<td><a href="${ctx}/platform/bpm/bpmNodeSet/list.ht?defId=${defId}" class="link edit" title="<spr:message code="bpmDefinition.nodeSummary.setting"/>" ></a></td>
							<td><span class="normal-set">-</span></td>
							<td><span class="normal-set">-</span></td>
							<td><span class="normal-set">-</span></td>
							<td><span class="normal-set">-</span></td>
						</tr>
						<tr>
							<td>-</td>
							<td><spr:message code="bpmDefinition.nodeSummary.startNode"/></br>(${startFlowNode.nodeName})</td>
							<td><span class="normal-set">-</span></td>
							<td><span class="normal-set">-</span></td>
							<td><span class="normal-set">-</span></td>
							<td><span class="normal-set">-</span></td>
							<td><span class="normal-set">-</span></td>
							<td><span class="normal-set">-</span></td>
							<td><span class="normal-set">-</span></td>
							<td>
								<c:choose>
									<c:when test="${startScriptMap[startFlowNode.nodeId]}">
										<span class="green-set">√</span>
									</c:when>
									<c:otherwise>
										<span class="red-set">×</span>
									</c:otherwise>
								</c:choose> 
							</td>
							<td>
								<a href="#" class="link edit" title="<spr:message code="bpmDefinition.nodeSummary.setting"/>" 
								onclick="FlowEventWindow({type:'startEvent',actDefId:'${actDefId}',activitiId:'${startFlowNode.nodeId}',defId:'${defId}',callback:function(){window.location.reload()}});"></a>
							</td>
					
							<td><span class="normal-set">-</span></td>
							<td><span class="normal-set">-</span></td>
							<td>
								<c:choose>
									<c:when test="${formMap['start']}">
										<span class="green-set">√</span>
									</c:when>
									<c:otherwise>
										<span class="red-set">×</span>
									</c:otherwise>
								</c:choose> </td>
							<td><span class="normal-set">-</span></td>
							<td>
								<c:choose>
									<c:when test="${buttonMap[startFlowNode.nodeId]}">
										<span class="green-set">√</span>
									</c:when>
									<c:otherwise>
										<span class="red-set">×</span>
									</c:otherwise>
								</c:choose> 
							</td>
							<td><a href="#" class="link edit" title="<spr:message code="bpmDefinition.nodeSummary.setting"/>" onclick="BpmNodeButtonWindow({defId:'${defId}',nodeId:'',callback:function(){window.location.reload()}})"></a></td>
							<td><span class="normal-set">-</span></td>
							<td><span class="normal-set">-</span></td>
						</tr>
						<c:forEach items="${endFlowNodeList}" var="endFlowNode">
						<tr>
							<td>-</td>
							<td><spr:message code="bpmDefinition.nodeSummary.endNode"/></br>(${endFlowNode.nodeName})</td>
							<td><span class="normal-set">-</span></td>
							<td><span class="normal-set">-</span></td>
							<td><span class="normal-set">-</span></td>
							<td><span class="normal-set">-</span></td>
							<td><span class="normal-set">-</span></td>
							<td><span class="normal-set">-</span></td>
							<td><span class="normal-set">-</span></td>
							<td>
								<c:choose>
									<c:when test="${endScriptMap[endFlowNode.nodeId]}">
										<span class="green-set">√</span>
									</c:when>
									<c:otherwise>
										<span class="red-set">×</span>
									</c:otherwise>
								</c:choose> 
							</td>	
							<td>
								<a href="#" class="link edit" title="<spr:message code="bpmDefinition.nodeSummary.setting"/>" onclick="FlowEventWindow({type:'endEvent',actDefId:'${actDefId}',activitiId:'${endFlowNode.nodeId}',defId:'${defId}',callback:function(){window.location.reload()}});"></a>
							</td>	
							<td><span class="normal-set">-</span></td>
							<td><span class="normal-set">-</span></td>
							<td><span class="normal-set">-</span></td>
							<td><span class="normal-set">-</span></td>
							<td><span class="normal-set">-</span></td>
							<td><span class="normal-set">-</span></td>
							<td><span class="normal-set">-</span></td>
							<td><span class="normal-set">-</span></td>
						</tr>
						</c:forEach>
					<c:forEach items="${nodeSetList}" var="nodeSet" varStatus="i">
						<tr>
							<td>${i.count}</td>
							<td>${nodeSet.nodeName}</br>(${nodeSet.nodeId})</td>
							
							<!-- 人员设置 -->
							<td>
								<c:choose>
									<c:when test="${nodeUserMap[nodeSet.setId]}">
										<span class="green-set">√</span>
									</c:when>
									<c:otherwise>
										<span class="red-set">×</span>
									</c:otherwise>
								</c:choose> 
							</td>
							<td>
								<a href="#" class="link edit" title="<spr:message code="bpmDefinition.nodeSummary.setting"/>" onclick="UserSetWindow({defId:'${defId}',nodeId:'${nodeSet.nodeId}',callback:function(){window.location.reload()}});"></a>
							</td>
							<!-- 常用语 -->
							<td>
								<c:choose>
									<c:when test="${taskApprovalItemsMap[nodeSet.setId]}">
										<span class="green-set">√</span>
									</c:when>
									<c:otherwise>
										<span class="red-set">×</span>
									</c:otherwise>
								</c:choose> 
							</td>
							<td>
								<a href="#" class="link edit" title="<spr:message code="bpmDefinition.nodeSummary.setting"/>" onclick="FlowApprovalItemWindow({actDefId:'${actDefId}',activitiId:'${nodeSet.nodeId}',defId:'${defId}',callback:function(){window.location.reload()}});"></a>
							</td>
							
							<!-- 流程事件-前置脚本 -->
							<td>	
								<c:choose>
									<c:when test="${preScriptMap[nodeSet.setId]}">
										<span class="green-set">√</span>
									</c:when>
									<c:otherwise>
										<span class="red-set">×</span>
									</c:otherwise>
								</c:choose> 
							</td>
							<!-- 流程事件-后置脚本 -->
							<td>	
								<c:choose>
									<c:when test="${afterScriptMap[nodeSet.setId]}">
										<span class="green-set">√</span>
									</c:when>
									<c:otherwise>
										<span class="red-set">×</span>
									</c:otherwise>
								</c:choose> 
							</td>
							
							<!-- 流程事件-分配脚本 -->
							<td>	
								<c:choose>
									<c:when test="${assignScriptMap[nodeSet.setId]}">
										<span class="green-set">√</span>
									</c:when>
									<c:otherwise>
										<span class="red-set">×</span>
									</c:otherwise>
								</c:choose> 
							</td>
							<td>
								<span class="normal-set">-</span>
							</td>
							<td>
								<a href="#" class="link edit" title="<spr:message code="bpmDefinition.nodeSummary.setting"/>" onclick="FlowEventWindow({type:'userTask',actDefId:'${actDefId}',activitiId:'${nodeSet.nodeId}',defId:'${defId}'});"></a>
							</td>
							<!-- 流程规则 -->
							<td>	
								<c:choose>
									<c:when test="${nodeRulesMap[nodeSet.setId]}">
										<span class="green-set">√</span>
									</c:when>
									<c:otherwise>
										<span class="red-set">×</span>
									</c:otherwise>
								</c:choose> 
							</td>
							<td>
								<a href="#" class="link edit" title="<spr:message code="bpmDefinition.nodeSummary.setting"/>" onclick="FlowRuleWindow({deployId:'${deployId}',actDefId:'${nodeSet.actDefId}',nodeId:'${nodeSet.nodeId}',nodeName:'${nodeSet.nodeName}',callback:function(){window.location.reload()}});"></a>
							</td>
							<!-- 流程表单 -->
							<td>	
								<c:choose>
									<c:when test="${bpmFormMap[nodeSet.setId]}">
										<span class="green-set">√</span>
									</c:when>
									<c:otherwise>
										<span class="red-set">×</span>
									</c:otherwise>
								</c:choose> 
							</td>
							<td>
								<a href="${ctx}/platform/bpm/bpmNodeSet/list.ht?defId=${defId}" class="link edit" title="<spr:message code="bpmDefinition.nodeSummary.setting"/>" ></a>
							</td>
							
							<!-- 操作按钮-->
							<td>	
								<c:choose>
									<c:when test="${nodeButtonMap[nodeSet.setId]}">
										<span class="green-set">√</span>
									</c:when>
									<c:otherwise>
										<span class="red-set">×</span>
									</c:otherwise>
								</c:choose> 
							</td>
							<td>
								<a href="#" class="link edit" title="<spr:message code="bpmDefinition.nodeSummary.setting"/>" onclick="BpmNodeButtonWindow({defId:'${defId}',nodeId:'${nodeSet.nodeId}',callback:function(){window.location.reload()}})"></a>
							</td>
							
							<!-- 催办设置-->
							<td>	
								<c:choose>
									<c:when test="${taskReminderMap[nodeSet.setId]}">
										<span class="green-set">√</span>
									</c:when>
									<c:otherwise>
										<span class="red-set">×</span>
									</c:otherwise>
								</c:choose> 
							</td>
							<td>
								<a href="#" class="link edit" title="<spr:message code="bpmDefinition.nodeSummary.setting"/>" onclick="FlowReminderWindow({actDefId:'${actDefId}',nodeId:'${nodeSet.nodeId}',callback:function(){window.location.reload()}})"></a>
							</td>
						</tr>
					</c:forEach>
				</table>
				<div style="height: 40px"></div>
			</form>
			
		</div>
	</div>
	</div>
	</div>
</body>
</html>