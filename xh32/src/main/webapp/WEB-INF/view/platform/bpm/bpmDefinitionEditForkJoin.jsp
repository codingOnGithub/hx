<%
	//某个任务节点的分发及汇总属性的设置
%>
<%@page pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
 <%@include file="/commons/include/form.jsp" %>
    <title><spr:message code="bpmDefinition.editForkJoin.title"/></title>
    <f:js pre="js/lang/view/platform/bpm" ></f:js>
	<script type="text/javascript">
		$(function(){
			initControl();
			$("#dataFormSave").click(function(){
				saveConfig();
			});
		});
		
		function saveConfig(){
			var url=__ctx+ "/platform/bpm/bpmDefinition/saveForkJoin.ht";
			var checked=$("#nodeType1").attr("checked");
			if(checked){
				$("#joinTaskKey").val('');
				$("#joinTaskName").val('');
			}
			 var param=$('#forkJoinForm').serialize();		        		
			 $.post(url,param,showResult);
		}
		
		function showResult(responseText){
			var obj=new com.hotent.form.ResultMessage(responseText);
			if(!obj.isSuccess()){
				$.ligerDialog.err($lang.tip.msg,$lang_bpm.bpmDefinition.nodeSet.flowForkJoin.fail,obj.getMessage());
				return;
			}else{
				$.ligerDialog.success(obj.getMessage(),$lang.tip.msg,function(){
					window.close();
				});
			}
		}
		
		function initControl(){
			$("#nodeTypeDiv input[name='nodeType']").on('click',function(){
				   var value=$(this).val();
				   if(value==1){
					   $("#joinTaskDiv").css('display','block');
				   }else{
					   $("#joinTaskDiv").css('display','none');
				   }
			});
		
			$("#joinTaskKey").on('change',function(){
				   var value=this.value;
				   var text=this.options[this.selectedIndex].text;
				  
				   if(value!=''){
					   $("#joinTaskName").val(text);
				   }else{
					   $("#joinTaskName").val('');
				   }
			});
			
		}
	</script>
</head>
<body>
<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">
					<c:choose>
						<c:when test="${bpmNodeSet.joinTaskName==null}">
							<spr:message code="menu.button.add"/><spr:message code="bpmDefinition.editForkJoin.titleName"/>
						</c:when>
						<c:otherwise>
							<spr:message code="menu.button.edit"/><spr:message code="bpmDefinition.editForkJoin.titleName"/>
						</c:otherwise>
				   </c:choose> 
			    </span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.save"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del" href="#" onclick="window.close()"><span></span><spr:message code="menu.button.close"/></a></div>
				</div>
			</div>
</div>
<form id="forkJoinForm">
	<div style="padding:8px 8px 8px 8px">
		<b><spr:message code="tip.explanation"/>：</b>
		<ul>
			<li><spr:message code="bpmDefinition.editForkJoin.message1"/></li>
			<li><spr:message code="bpmDefinition.editForkJoin.message2"/></li>
			<li><spr:message code="bpmDefinition.editForkJoin.message3"/></li>
		</ul>
	</div>
	<div style="padding:8px 8px 8px 8px" id="nodeTypeDiv">
		<input type="radio" name="nodeType" <c:if test="${bpmNodeSet.nodeType==0}">checked="checked"</c:if> value="0" id="nodeType1"><label for="nodeType1"><spr:message code="bpmDefinition.editForkJoin.commonNode"/></label>
		<input type="radio" name="nodeType" <c:if test="${bpmNodeSet.nodeType==1}">checked="checked"</c:if> value="1" id="nodeType2"><label for="nodeType2"><spr:message code="bpmDefinition.editForkJoin.issueNode"/></label>
		<input type="hidden" name="actDefId" value="${actDefId}" />
		<input type="hidden" name="nodeId" value="${nodeId}" />
	</div>
	<div style="padding:8px 8px 8px 8px; <c:if test="${bpmNodeSet.nodeType!=1}">display:none</c:if>" id="joinTaskDiv">
		<label><spr:message code="bpmDefinition.editForkJoin.sumNodes"/></label>
		<select name="joinTaskKey" id="joinTaskKey">
			<option value=""><spr:message code="bpmDefinition.editForkJoin.selSumNodes"/></option>
			<c:forEach items="${nodeMap}" var="node">
				<option value="${node.key}" <c:if test="${bpmNodeSet.joinTaskKey==node.key}">selected</c:if> >${node.value}</option>
			</c:forEach>
		</select>
		<input type="hidden" name="joinTaskName" id="joinTaskName" value="${bpmNodeSet.joinTaskName}"/>
	</div>
</form>
</body>
</html>