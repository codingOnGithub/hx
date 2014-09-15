<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<f:js pre="js/lang/view/platform/bpm" ></f:js>
	<title><spr:message code="processCenter.cancel.title"/></title>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
	<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerDialog.js"></script>
	
	<script type="text/javascript">
		var id=${param.id};
	
		$(function() {
			$("#dataFormSave").click(save);
		});
		
		function callBack(rtn) {
			if(!rtn) return;
			var taskOpinion=$("#opinion").val();
			var informType=$.getChkValue("informType");
			var params= {id:id,opinion:taskOpinion,informType:informType};
			$.post("${ctx}/platform/bpm/bpmTaskExe/cancel.ht",params,function(msg){
				var obj=new com.hotent.form.ResultMessage(msg);
				if(obj.isSuccess()){
					 $.ligerDialog.success($lang_bpm.cancelAssign.cancelSuccess,$lang.tip.msg,function(){
						 window.returnValue="ok";
			    		 window.close(); 
			    	 });
				}else{
					 $.ligerDialog.err($lang.tip.msg,$lang_bpm.cancelAssign.cancelFailure,obj.getMessage());
				}
			});
		}


		function save(){
			var rtn=$("#bpmTaskExeForm").form().valid();
			if(!rtn) return;
		 	$.ligerDialog.confirm($lang_bpm.cancelAssign.confirmCancel,$lang.tip.msg,callBack);
		 }
	</script>
</head>
<body>
<div class="panel">
<div class="hide-panel">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">
				<spr:message code="processCenter.cancel.title"/>
			</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.cancel"/></a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link close" href="#" onclick="window.close()" ><span></span><spr:message code="menu.button.close"/></a></div>
			</div>
		</div>
	</div>
	</div>
	<div class="panel-body">
		<form id="bpmTaskExeForm" method="post" >
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="20%" nowrap="nowrap"><spr:message code="processCenter.cancel.informtype"/>： </th>
					<td>
					   <%-- 
						<input type="hidden" name="informType" />
						<label><input type="checkbox" name="informType" value="3"  checked="checked"/><spr:message code="message.inner"/></label>
						<label><input type="checkbox" name="informType" value="1" checked="checked"/><spr:message code="message.mail"/></label>
						<label><input type="checkbox" name="informType" value="2" /><spr:message code="message.sms"/></label>
						--%>
						<c:forEach items="${handlersMap}" var="item">
						   <input type="checkbox" name="informType" value="${item.key }"  <c:if test="${item.value.isDefaultChecked}">checked="checked"</c:if> />
				            ${item.value.title }
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th width="20%"><spr:message code="processCenter.cancel.opinion"/>： </th>
					<td>
						<textarea rows="5" cols="50" id="opinion"  validate="{required:true}" ></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
</body>
</html>