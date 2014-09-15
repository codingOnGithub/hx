<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="net.sf.json.JSONArray" %>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
<title><c:if test="${ not empty nodeName}">[${nodeName}]</c:if><spr:message code="bpmUserCondition.title"/></title>
<f:js pre="js/lang/view/platform/bpm" ></f:js>
<f:link href="form.css" ></f:link>
<link href="${ctx}/js/jquery/plugins/link-div-default.css" rel="stylesheet" type="text/css" />
<link  rel="stylesheet" type="text/css" href="${ctx}/js/codemirror/lib/codemirror.css" >
<script type="text/javascript" src="${ctx}/commons/include/nodeUserSelectorJS.jsp" ></script>
<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=bpmNodeRule"></script>
<script type="text/javascript" src="${ctx}/js/javacode/codemirror.js"></script>
<script type="text/javascript" src="${ctx}/js/javacode/InitMirror.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/ScriptDialog.js" ></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.qtip.js" ></script>
<script type="text/javascript" src="${ctx}/js/util/easyTemplate.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/BpmDefinitionConditionEdit.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/BpmNodeRule.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/ConditionScriptEditDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/PersonScriptAddDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.linkdiv.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysCustomDisplayEdit.js"></script>
<style type="text/css">
a.ruledetail,a.delrule {
	cursor: pointer;
}
html{ 
	overflow-x: hidden;
}
#condition-table td{
	text-align:left;
}
.table-grid textarea {
	float: left;
	height: 30px;
}
</style>
<body>
	<div class="panel">
		<div class="hide-panel">
			<div class="panel-top">
				<div class="tbar-title">
					<span class="tbar-label"><spr:message code="bpmUserCondition.title"/></span>
				</div>
				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group"><a class="link save" onclick="save()"><span></span><spr:message code="menu.button.save"/></a></div>
						<div class="l-bar-separator"></div>
						<div class="group"><a class="link del" onclick="javasrcipt:window.close()"><span></span><spr:message code="menu.button.close"/></a></div>
						<div class="l-bar-separator"></div>
					</div>	
				</div>
			</div>
		</div>
		<div class="panel-body">
			<fieldset style="margin: 5px 0px 5px 0px;" zone="team" >
					<legend><span><spr:message code="bpmUserCondition.ruleSetting"/></span></legend>					
					<div class="table-top">
						<div class="table-top-right">	
							<div class="toolBar" style="margin:0;">
								<div class="group"><a class="link add" onclick="addDiv(1)"><spr:message code="bpmUserCondition.ruleSetting.addRule"/></a></div>
								<div class="l-bar-separator"></div>
								<div class="group"><a class="link add" onclick="addDiv(2)"><spr:message code="bpmUserCondition.ruleSetting.addScript"/></a></div>
								<div class="l-bar-separator"></div>
								<div class="group"><a class="link switchuser" onclick="assembleDiv()"><spr:message code="bpmUserCondition.ruleSetting.groupRule"/></a></div>
								<div class="l-bar-separator"></div>
								<div class="group"><a class="link switchuser" onclick="splitDiv()"><spr:message code="bpmUserCondition.ruleSetting.splitRule"/></a></div>
								<div class="l-bar-separator"></div>
								<div class="group"><a class="link del" onclick="removeDiv()"><spr:message code="menu.button.del"/></a></div>
							</div>
					    </div>
					</div>
					<div id="ruleDiv" style="margin:5px 0 0 0;"></div>
			</fieldset>
			<fieldset style="margin: 5px 0px 5px 0px;" zone="team" >
					<legend><span><spr:message code="bpmUserCondition.userSetting"/></span></legend>
					<div style="margin-top:5px;">
						<input type="hidden" name="defId" value="${bpmDefinition.defId}" />											
						<input type="hidden" id="conditionId" name="conditionId" value="${bpmUserCondition.id}" />
						
						<%@include file="/commons/include/nodeUserSelector.jsp" %>
					</div>
			</fieldset>
		<!-- 规则模板 -->
		<%@include file="/commons/include/nodeRuleTemplate.jsp" %>
		<!-- 人员设置模板 -->
		<%@include file="/commons/include/nodeUserTemplate.jsp" %>
		
	
		<div class="hidden">
				<div id="userSetTypesSelectOpt">
					<c:forEach items="${userSetTypes}" var="item">
						<option value="${item.key}" <c:if test="${item.key eq 'users'}">selected="selected"</c:if> >${item.value["title"]}</option>
					</c:forEach>
				</div>
				<textarea id="conditionEntity">
						{
						setId:"${setId}",
						defId:${defId},
						actDefId:"${bpmDefinition.actDefId}",
						nodeId:"${nodeId}",
						conditionId:"${bpmUserCondition.id}",
						conditionType:"${bpmUserCondition.conditionType}",
						sn:"${bpmUserCondition.sn}",
						groupNo:"${bpmUserCondition.groupNo}",
						formIdentity:"${bpmUserCondition.formIdentity}"
						}
				</textarea>
				<textarea id="conditionTxt">
					${bpmUserCondition.condition}
				</textarea>
		</div>
		<div style="display: none">
			<input type="hidden" id="defId" value="${bpmDefinition.defId}" />
			<input type="hidden" id="actDefId" value="${bpmDefinition.actDefId}" />
			<input type="hidden" id="nodeId" value="${nodeId}" />
		</div>
</body>
</html>


