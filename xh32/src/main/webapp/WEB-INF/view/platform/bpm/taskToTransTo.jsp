<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
<f:js pre="js/lang/view/platform/bpm" ></f:js>	
<title><spr:message code="task.transTo.title"/></title>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<style type="text/css">
th{width:15%;}
</style>
<script type="text/javascript">
var taskId=${param.taskId};
var formData = window.dialogArguments.data;

function callBack(rtn) {
	if(!rtn) return;
	var cmpIds=$("#cmpIds").val();
	var transType=$("input[name='transType']:checked").val();
	var arrCmpId = new Array();
	arrCmpId = cmpIds.split(",");
	if(transType=='2' && arrCmpId.length<=1){
		$.ligerDialog.error($lang_bpm.task.toTransTo.error_message_num);
		return;
	}
		
	var taskOpinion=$("#opinion").val();
	var action=$("input[name='action']:checked").val();
	var informType=$.getChkValue("informType");
	var params= {cmpIds:cmpIds,
				 opinion:taskOpinion,
				 transType:transType,
				 action:action,
				 informType:informType,
				 taskId:taskId,
				 formData:formData};
	var url="${ctx}/platform/bpm/task/toStartTransTo.ht";
	$.post(url,params,function(msg){
		var obj=new com.hotent.form.ResultMessage(msg);
		if(obj.isSuccess()){
			 $.ligerDialog.success($lang_bpm.task.toTransTo.success,function(){
				 window.returnValue="ok";
	    		 window.close();
	    	 });
		}else{
			 $.ligerDialog.error($lang_bpm.task.toTransTo.failure+obj.getMessage());
		}
	});
}


function save(){
	var rtn=$("#frmComm").form().valid();
	if(!rtn) return;
 	$.ligerDialog.confirm($lang_bpm.task.toTransTo.sureSend,callBack);
 }
 
function dlgCallBack(userIds, fullnames) {
 	if (userIds.length > 0) {
		var cmpIds=$("#cmpIds");
		var cmpNames=$("#cmpNames");
		cmpIds.val(userIds);
		cmpNames.val(fullnames);
	}
};

function add() {
	UserDialog({
		selectUserIds:$("#cmpIds").val(),
	    selectUserNames:$("#cmpNames").val(),
		callback : dlgCallBack,
		isSingle : false
	});
}

</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label"><spr:message code="task.transTo.title"/></span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="#" onclick="save()"><span></span><spr:message code="menu.button.submit"/></a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link close" href="#" onclick="window.close();"><span></span><spr:message code="menu.button.close"/></a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="frmComm">
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">

			<tr>
				<th nowrap="nowrap"><spr:message code="task.transTo.addUser"/></th>
				<td>
					<input type="hidden" id="cmpIds" /> 
					<textarea id="cmpNames"  cols="50" style="width:300px"  rows="2" class="textarea" readonly="readonly" validate="{required:true}"></textarea>
					<a class="link grant" onclick="add(this);"><span></span><span><spr:message code="task.transTo.selectUser"/></span></a>
				</td>
			</tr>
			<tr>
				<th><spr:message code="task.transTo.sign"/></th>
				<td>
					<input type="radio" name="transType"  class="isNotSign" value="1" checked="checked"><spr:message code="task.transTo.notSign"/>
					<input type="radio" name="transType" class="isSign" value="2"><spr:message code="task.transTo.sign"/>
				</td>
			</tr>
			<tr>
				<th><spr:message code="task.transTo.transToEnd"/></th>
				<td>
					<input type="radio" name="action" class="back" value="1" checked="checked"><spr:message code="menu.button.back"/>
					<input type="radio" name="action" class="submit" value="2"><spr:message code="menu.button.submit"/>
				</td>
			</tr>
			<tr>
				<th><spr:message code="task.transTo.memo"/></th>
				<td>
					<textarea rows="5" cols="50" style="width:300px" id="opinion" name="opinion" validate="{required:true,maxLength:1000}" maxLength="1000"></textarea>
				</td>
			</tr>
			<tr>
				<th><spr:message code="message.reminder"/></th>
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
				<th><spr:message code="task.transTo.instructions"/></th>
				<td>
					<div class="content"  style=" background: none repeat scroll 0 0 #EFEFEF;">
						<ul class="help">
						<spr:message code="task.transTo.instructions.tip" arguments="<li>,</li>"/>
							
						</ul>
					</div>
				</td>
			</tr>
		</table>
		</form>
	</div>
</div>
</body>
</html>