<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<f:js pre="js/lang/view/platform/bpm" ></f:js>
	<title><spr:message code="title.edit"/><spr:message code="agentCondition"/></title>
	<f:link href="form.css" ></f:link>
	<link href="${ctx}/js/jquery/plugins/link-div-default.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/util/easyTemplate.js" ></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/BpmNodeRule.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormUtil.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/ConditionScriptEditDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.linkdiv.js"></script>
	<script type="text/javascript">
		var winArgs = window.dialogArguments;
		$(function() {
			$("a.save").click(saveCondForm);
			//初始化条件
			var conditions = winArgs.conditions;
			initConditions(conditions);	
			//初始化按钮选择器
			FormUtil.handSelector();

			if($("#agentConditionForm [name='conditionItem']").length==0){
				addAgent();
			}
			
		});
		/**
		* 初始化条件设置
		*/
		function initConditions(conditions){
			for(var i=0;i<conditions.length;i++){
				cond = conditions[i];
				constructConditionItem(cond);
			}
		};
		
		/**
		* 构造代理条件
		*/
		function constructConditionItem(cond){
			var agentid = cond.agentid;
			var agent= cond.agent;
			var condition = cond.condition.condition;
			var memo = cond.memo;
			var temp = $("#divAgentTemplate [name='conditionItem']").clone();
			
			$('[name="agentid"]',temp).val(agentid);
			$('[name="agent"]',temp).val(agent);
			$("#agentConditionForm").append(temp);
			
			//初始化规则代码
			$("div[name='ruleDiv']",temp).linkdiv({data:condition,updateContent:updateContent,rule2json:rule2json});
		};
		
		/**
		* 处理【确定】事件，保存条件信息
		*/
		function saveCondForm(){
			var conditions = getConditions();
			
			var status=0;
			var msg="";
			
			if(!conditions || conditions.length<1){
				status=-1;
				msg= $lang_bpm.agent.conditionEdit.mustHaveOne;
			}
			
			for(var i=0;i<conditions.length;i++){
				var cond = conditions[i];
				if(!cond.agentid){
					status=-1;
					msg+=String.format($lang_bpm.agent.conditionEdit.noAgent,i+1);
				}
			}
			
			if(status){
				$.ligerDialog.warn(msg,$lang.tip.msg);
				return;
			}
			
			var winRtn = {
				status:1,
				conditions:conditions
			};
			window.returnValue = winRtn;
			window.close();
		};
		
		/**
		* 获取代理条件设置
		*/
		function getConditions(){
			var conditions = [];
			var tableId = $("#tableId").val();
			var conditionItems = $("div[name='conditionItem']",$("#agentConditionForm"));
			conditionItems.each(function(){
				var item = $(this);
				var agentid = $("[name='agentid']",item).val();
				var agent = $("[name='agent']",item).val();
				var condition = getData(item);
				var memo = '';
				var cond={
					agentid:agentid,
					agent:agent,
					condition:{
						tableId:tableId,
						condition:condition
					},
					memo:memo
				};
				conditions.push(cond);
			});
			return conditions;
		};
		
		/**
		* 添加一个代理条件
		*/
		function addAgent(){
			var temp = $("#divAgentTemplate [name='conditionItem']").clone();
			$('[name="agentid"]',temp).val("");
			$('[name="agent"]',temp).val("");
			$("#agentConditionForm").append(temp);
			
			$("div[name='ruleDiv']",temp).linkdiv({updateContent:updateContent,rule2json:rule2json});
		}
		
		/**
		* 选择代理人
		*/
		function selectAgent(linkObj){
			var obj=$(linkObj).closest("td");
			UserDialog({isSingle:true,
				callback:function(userIds, fullnames){
					$("[name='agent']",obj).val(fullnames);
					$("[name='agentid']",obj).val(userIds);
				}
			});
		};
		
		/**
		* 删除一个代理条件
		*/
		function delAgent(obj){
			var objFieldSet=$(obj).closest("[name='conditionItem']");
			objFieldSet.remove();
		}
		/*********************************************/
		
		function getRuleDiv(t){
			var parent = $(t).parents("div.link-div");
			return $("div[name='ruleDiv']",parent);
		};
		
		function addDiv(ruleType){
			getRuleDiv(this).linkdiv("addDiv",{ruleType:ruleType});
		};
	
		function removeDiv(){
			getRuleDiv(this).linkdiv("removeDiv");	
		};
	
		function assembleDiv(){
			getRuleDiv(this).linkdiv("assembleDiv");
		};
	
		function splitDiv(){
			getRuleDiv(this).linkdiv("splitDiv");
		};
		
		function getData(t){
			var ruleDiv = $("div[name='ruleDiv']",t);
			var json = ruleDiv.linkdiv("getData");
			return json;
		};
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${agentCondition.id !=null}">
			        <span class="tbar-label"><spr:message code="title.update"/><spr:message code="agentCondition"/></span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><spr:message code="title.add"/><spr:message code="agentCondition"/></span>
			    </c:otherwise>			   
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="#"><span></span></span><spr:message code="menu.button.ok"/></a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link add" onclick="addAgent()" href="#"><span></span><spr:message code="menu.button.add"/></a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link close" href="#" onclick="window.close()"><span></span><spr:message code="menu.button.close"/></a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="agentConditionForm" method="post" action="save.ht">
			<input id="tableId" value="${tableId}" type="hidden"/>
			<input id="defId" value="${defId}" type="hidden"/>
		</form>
	</div>
</div>

<div style="display: none;" id="divAgentTemplate">
	<div name="conditionItem">
		<fieldset style="margin: 5px 0px 5px 0px;" zone="team"  >
			<legend>
				<span><spr:message code="agentCondition.edit.agentSetting"/></span>
					<a style="margin-left: 5px" class="link del" var="del" onclick="delAgent(this)" title='<spr:message code="agentCondition.edit.agentSetting"/>'><spr:message code="operator.del"/></a>
			</legend>
			<div>
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
					<tr>
						<th width="20%"><spr:message code="agentCondition.agent"/>: </th>
						<td>
							<input type="hidden"  name="agentid" />
							<input type="text"  name="agent"  class="inputText" readonly="readonly" />
							<a href="#" class="button" onclick="selectAgent(this);"><span class="chosen"><spr:message code="agentCondition.edit.agentSelect"/></span></a>
						</td>
					</tr>
				</table>
				
				<div class="link-div">
					<div class="table-top">
						<div class="table-top-right">
							<div class="toolBar" style="margin:0;">
								<div class="group"><a class="link add" onclick="addDiv.apply(this,[1])"><spr:message code="menu.button.addCondition"/></a></div>
								<div class="l-bar-separator"></div>
								<div class="group"><a class="link add" onclick="addDiv.apply(this,[2])"><spr:message code="menu.button.addScript"/></a></div>
								<div class="l-bar-separator"></div>
								<div class="group"><a class="link switchuser" onclick="assembleDiv.apply(this)"><spr:message code="menu.button.assembleRule"/></a></div>
								<div class="l-bar-separator"></div>
								<div class="group"><a class="link switchuser" onclick="splitDiv.apply(this)"><spr:message code="menu.button.splitRule"/></a></div>
								<div class="l-bar-separator"></div>
								<div class="group"><a class="link del" onclick="removeDiv.apply(this)"><spr:message code="menu.button.del"/></a></div>
							</div>
					    </div>
					</div>
					<div name="ruleDiv" style="border:2px solid #ccc;margin:5px 0 0 0;"></div>
				</div>
			</div>
		</fieldset>
	</div>
</div>
<%@include file="/commons/include/nodeRuleTemplate.jsp" %>
</body>
</html>
