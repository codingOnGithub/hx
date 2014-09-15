<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@include file="/commons/include/get.jsp" %>
	<title><spr:message code="bpmDefinition.userSet.title"/></title>
	<%@include file="/commons/include/nodeUserConditionJS.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/ScriptDialog.js" ></script>
	
	<style type="text/css">
		.inputChange{
			background-color: #BBAAAA;
		}
	</style>
	<script type="text/javascript">
		$(function(){
			$("a.del").unbind("click");
			$("div.group > a.link.update").unbind('click');
			$('#btnReflesh').attr('href',window.location.href)

			$("body").delegate("input[name=groupNo]","change",changeGroupNo);
		});
		

		function refresh(){
			location.href=location.href.getNewUrl();
		};

		/**
		* 保存分组号
		*/
		function saveGroupNo(nodeId){
			var conditionIds=[],
				groupNos=[];
			var url = __ctx+"/platform/bpm/bpmUserCondition/updateGroup.ht";
			var id = "table_" + nodeId;
			var table = $('#' + id);
			table.find("tbody.data").find("input[name=groupNo]").each(function(){
				var _this = $(this);
				if(_this.val()!=_this.attr("ivalue")){
					var groupNo = _this.val();
					var tr = _this.closest("tr");
					var conditionId = $("input[name=conditionId]", tr).val();
					conditionIds.push(conditionId);
					groupNos.push(groupNo);
				}
			});
			if(conditionIds.length==0){
				return;
			}
			var params = {
					conditionIds:conditionIds.join(","),
					groupNos:groupNos.join(",")
				};
			$.post(url,params,function(data){
				var obj = new com.hotent.form.ResultMessage(data);
				if (obj.isSuccess()) {
					$.ligerDialog.success( obj.getMessage(),function(){
						window.location.reload();
					});
					
				} else {
					$.ligerDialog.err($lang.tip.errorMsg,obj.getMessage());
				}			
			});
		};

		/**
		* 分组号值变更
		*/
		function changeGroupNo(){
			var _this=$(this);
			//_this.addClass("inputChange");

			var td = _this.closest("td");
			var tr = _this.closest("tr");
		
			var groupNo = _this.val();
			
			groupNo = groupNo.replace(/(^\s*0*)|(\s*$)/g,"");
			if(!/^\d+$/.test(groupNo)){
				groupNo=1;
			}
			_this.val(groupNo);
			
			$("div[name=groupNo]",td).text(groupNo).show();

			var url = __ctx+"/platform/bpm/bpmUserCondition/updateGroup.ht";

			var conditionId = $("input[name=conditionId]",tr).val();
			var params = {
				conditionId:conditionId,
				groupNo:groupNo
			};

			var oldGroup = _this.attr("ivalue");
			//_this.hide();

			if(oldGroup==groupNo){
				tr.removeClass("inputChange");
				return;
			}else{
				if(!tr.hasClass("inputChange"))
					tr.addClass("inputChange");
			}

		};
	</script>
	
	<base target="_self" />
	<style type="text/css">
		.additionalParam,textarea{
			margin-top:5px;
			display:block;
			width:80%;
		}
	</style>
</head>
<body>	
	<div class="panel">
	<div class="hide-panel">
	<c:if test="${empty nodeTag}">
	    <jsp:include page="incDefinitionHead.jsp">
		    	<jsp:param value="<spr:message code='bpmDefinition.userSet.title'/>" name="title"/>
		</jsp:include>

	<div class="panel-container">
		<f:tab curTab="userSet" tabName="flow"/>

	</c:if>

	<c:if test="${!empty nodeTag}">
	      <div class="panel-container">
	</c:if>
	<div class="panel">
		<div class="panel-top">
				 <h2 class="setting"><spr:message code='bpmDefinition.userSet.title'/></h2>
		</div>

		<div class="panel-body">

			<a type="hidden" id="btnReflesh" onclick="refresh()"></a>

			<form action="saveUser.ht" method="post" id="defUserForm">
				<input type="hidden" name="defId" value="${defId}"/>
				<input type="hidden" name="nodeId" value="${nodeId}"/>

				
				<c:forEach items="${nodeUserMapList}" var="nodeUserMap" varStatus="i">
						  <div class="table-top">
								<div class="table-top-left">${nodeUserMap.nodeName}(${nodeUserMap.nodeId})</div>
								<div class="table-top-right">
											
									<div class="toolBar" style="margin:0;">
										<div class="group"><a class="link add" id="btnSearch" onclick="conditionDialog('table_${nodeUserMap.nodeId}')"><span></span><spr:message code="menu.button.add"/></a></div>
										<div class="l-bar-separator"></div>
										<div class="group"><a class=" update link " onclick="conditionDialog('table_${nodeUserMap.nodeId}',true)"><span></span><spr:message code="menu.button.alter"/></a></div>
										<div class="l-bar-separator"></div>
										<div class="group"><a class="link del " id="btnSearch" onclick="delRows('table_${nodeUserMap.nodeId}');"><span></span><spr:message code="menu.button.del"/></a></div>
										<div class="l-bar-separator"></div>
										<div class="group"><a class="link save" onclick="saveGroupNo('${nodeUserMap.nodeId}')" id="btnSaveGroupNo" ><spr:message code="menu.button.saveGroupNo"/></a></div>
									</div>								
							    </div>
							</div>						
								<%@include file="/commons/include/nodeUserCondition.jsp" %>
				</c:forEach>

				<div style="height:40px"></div>
			</form>
			
		</div>				
	</div>

	<div id="divScriptData" style="display: none;">
		
		<a href="#" id="btnScript" class="link var" title="<spr:message code='bpmDefinition.userSet.script'/>" onclick="selectScript()"><spr:message code='bpmDefinition.userSet.script'/></a>
		<ul>
			<li><spr:message code='bpmDefinition.userSet.script.message1'/><li><spr:message code='bpmDefinition.userSet.script.message2'/></li>
			<li><spr:message code='bpmDefinition.userSet.script.message3'/></li>
		</ul>
		<textarea id="txtScriptData" rows="10" cols="80" style="height: 200px;width:480px"></textarea>
	</div>
	</div>
	<textarea class="hidden" id="conditionNodeTemplate">
		<input type="hidden" name="setId" value="\${data.setId}"/>
		<input type="hidden" name="defId" value="\${data.defId}"/>
		<input type="hidden" name="nodeId" value="\${data.nodeId}"/>
		<#list data.list as obj>
			<tr <#if (obj_index%2==0)>class="odd"</#if>>
				<td>
					<input type='checkbox' name='nodeUserCk' onchange="changeCheck(this)"/>&nbsp;\${obj_index+1}						
					<input type="hidden" name="conditionId" value="\${obj.id}"/>		
					<input type="hidden" name="sn" value="\${obj.sn}"/>				
					<#if (data.nodeId!=null)>
						<input type="hidden" name="nodeId" value="\${data.nodeId}"/>
					</#if>
				</td>
				<td>
					<div name="conditionShow">
						<!-- 因有两个textarea嵌套，因此需要将此处的textarea的结束符替换为&#47 -->
						<textarea class="hidden">\${obj.condition}<&#47textarea>
					</div>
					<div style="margin:8px 0 0 0;">
						\${obj.conditionShow}
					</div>
				</td>
				<td name="groupNoTd">
					<div style="width: 80px">
						<input name="groupNo" style="width:70px;" class="inputText" ivalue ="\${obj.groupNo}"  value="\${obj.groupNo}" />
					</div>
				</td>
			</tr>
		</#list>
	</textarea>
</body>
</html>
