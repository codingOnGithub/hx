<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/customForm.jsp" %>
<title><spr:message code="title.edit"/><spr:message code="bpmDefinition.form.form"/></title>
<script type="text/javascript">
$(function() {
	function showRequest(formData, jqForm, options) { 
		return true;
	} 
	initSubForm();
	$("a.save").click(saveHandler);
	
});

window.returnValue = "close";

function saveHandler(){
	var rtn=CustomForm.validate();
	if(rtn){
		//Office控件提交。
		OfficePlugin.submit();
		//WebSign控件提交。
		WebSignPlugin.submit();
		var data=CustomForm.getData();
		//设置表单数据
		$("#formData").val(data);
		$('#frmData').submit();
	}else{
		$.ligerDialog.warn($lang_js.customValid.form_validate_msg,$lang.tip.warn);
	}
}

function initSubForm(opitons){
	opitons=$.extend({},{success:showResponse },opitons);
	$('#frmData').ajaxForm(opitons); 
}

function showResponse(responseText){
	var obj=new com.hotent.form.ResultMessage(responseText);
	if(obj.isSuccess()){
		$.ligerDialog.confirm( obj.getMessage()+","+$lang.operateTip.continueOp,$lang.tip.confirm, function(rtn) {
			if(!rtn){
				window.close();
			}
		});
	}else{
		alert(obj.getMessage());
	}
}

</script>


</head>
<body>
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">
				<c:choose>
					<c:when test="${hasPk}">
						<spr:message code="title.edit"/>${tableName}
					</c:when>
					<c:otherwise>
						<spr:message code="title.add"/>${tableName}
					</c:otherwise>
				</c:choose>
			</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group">
					<a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.save"/></a>
				</div>
				<div class="l-bar-separator"></div>
				<div class="group">
					<a class="link close" href="javascript:window.close();"><span></span><spr:message code="menu.button.close"/></a>
				</div>
			</div>
		</div>
	</div>
	<form id="frmData" name="frmData" method="post" action="${ctx}/platform/form/bpmFormHandler/save.ht">
	
		<div type="custform">
			${bpmFormDef.html}
		</div>
		<input type="hidden" name="formData" id="formData" />
		<input type="hidden" name="${pkField}"  id="${pkField}"  value="${id}" />
		<input id='tableId' name='tableId' type='hidden' value='${tableId}' />
		<input id='tableName' name='tableName' type='hidden' value='${tableName}'/>
	</form>
	
</body>
</html>

