<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
<f:js pre="js/lang/view/platform/bpm" ></f:js>
<title><spr:message code="task.transToOpinion.title"/></title>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript">
var taskId=${param.taskId};
var formData= window.dialogArguments.data;

function save(isAgree){
	var rtn=$("#frmComm").form().valid();
	if(!rtn) return;
	var content=$("#opinion").val();
	var informType=$.getChkValue("informType");
	
	var params= {isAgree:isAgree,opinion:content,informType:informType,taskId:taskId,formData:formData};
	var url=__ctx +"/platform/bpm/task/saveOpinion.ht";

	$.post(url,params,function(msg){
		var obj=new com.hotent.form.ResultMessage(msg);
		if(obj.isSuccess()){
			 $.ligerDialog.success($lang.tip.success,$lang.tip.msg,function(){
				 window.returnValue="ok";
	    		 window.close(); 
	    	 });
		}else{
			 $.ligerDialog.err($lang.tip.msg,$lang.tip.failure,obj.getMessage());
		}
	});
 }
 
</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label"><spr:message code="task.transToOpinion.opinion"/></span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link agree" id="agree" href="#" onclick="save(true)"><span></span><spr:message code="bpmNodeButton.button.complete"/></a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link notAgree" id="notAgree" href="#" onclick="save(false)"><span></span><spr:message code="bpmNodeButton.button.oppose"/></a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link close" href="#" onclick="window.close()"><span></span><spr:message code="menu.button.close"/></a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="frmComm">
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<th><spr:message code="task.transToOpinion.remind"/>:</th>
				<td>
				<%-- 
					<input type="checkbox" name="informType" value="3" checked="checked"><spr:message code="message.inner"/>
					<input type="checkbox" name="informType" value="1"><spr:message code="message.mail"/>
					<input type="checkbox" name="informType" value="2"><spr:message code="message.sms"/>
				--%>
					<c:forEach items="${handlersMap}" var="item">
							   <input type="checkbox" name="informType" value="${item.key }"  <c:if test="${item.value.isDefaultChecked}">checked="checked"</c:if> />
					            ${item.value.title }
					</c:forEach>
				</td>
			</tr>
			<tr>
				<th><spr:message code="task.transToOpinion.opinion"/>:</th>
				<td>
					<textarea rows="4" cols="50" style="width:300px" id="opinion" name="opinion" validate="{required:true,maxLength:500}" maxLength="500"></textarea>
				</td>
			</tr>
		</table>
		</form>
	</div>
</div>
</body>
</html>