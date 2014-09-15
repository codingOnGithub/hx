<%--
	time:2011-11-23 11:07:27
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<f:js pre="js/lang/view/platform/system" ></f:js>
	<title><spr:message code="title.edit"/><spr:message code="dictionary"/></title>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=dictionary"></script>
	<script type="text/javascript">
		var isAdd=${isAdd};
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			valid(showRequest,showResponse);
			$("a.save").click(function() {
				$('#dictionaryForm').submit(); 
			});
			
			function showResponse(responseText){
				var obj=new com.hotent.form.ResultMessage(responseText);
				if(obj.isSuccess()){//成功
					if(isAdd==1){
						$("#itemName,#itemKey,#itemValue").val("");
						$.ligerDialog.success($lang_system.dictionary.edit.success_msg_add,$lang.tip.success);
					}
					else{
						$.ligerDialog.success($lang_system.dictionary.edit.success_msg_update,$lang.tip.success);
					}
					var conf=window.dialogArguments;
					if(conf.callBack){
						conf.callBack();
					}
				}
				else{
					$.ligerDialog.err($lang.tip.error,$lang_system.dictionary.edit.error_msg_save,obj.getMessage());
				}
			}
		});
		
		function autoGetDictionaryKey(inputObj){
			var url=__ctx + '/platform/system/dictionary/getDictionaryKey.ht';
			var subject=$(inputObj).val();
			if($.trim(subject).length<1) return;
			$.post(url,{'subject':subject},function(response){
				var json=eval('('+response+')');
					
						$('#itemValue').val(json.message);
				
			 });
		}
		
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">
					<c:choose><c:when test="${isAdd==1}"><spr:message code="title.add"/><spr:message code="dictionary"/></c:when><c:otherwise><spr:message code="title.edit"/><spr:message code="dictionary"/></c:otherwise></c:choose>  
				</span>  
			</div>
			<c:if test="${canReturn==0}">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.save"/></a></div>
				</div>
			</div>
			</c:if>
		</div>
		<div class="panel-body">
			<div class="panel-detail">
				<form id="dictionaryForm" method="post" action="save.ht">
					<table border="0" cellspacing="0" cellpadding="0" class="table-detail">
						<tr>
							<th width="20%"><spr:message code="dictionary.itemName"/>:<span class="required">*</span></th>
							<td ><input type="text" id="itemName" name="itemName" value="${dictionary.itemName}" onblur="autoGetDictionaryKey(this)" class="inputText"/></td>
						</tr>	
						<tr>
							<th width="20%"><spr:message code="dictionary.itemKey"/>:</th>
							<td ><input type="text" id="itemKey" name="itemKey" value="${dictionary.itemKey}" class="inputText"/>
							<br>
							<spr:message code="dictionary.edit.itemKey.comment"/></td>
						</tr>				
						<tr>
							<th width="20%"><spr:message code="dictionary.itemValue"/>:<span class="required">*</span></th>
							<td ><input type="text" id="itemValue" name="itemValue" value="${dictionary.itemValue}"   class="inputText"/></td>
						</tr>
					
					</table>
					<input type="hidden" name="dicId" value="${dictionary.dicId}">
					<input type="hidden" name="typeId" value="${dictionary.typeId}">
					<input type="hidden" name="parentId" value="${dictionary.parentId}">
					<input type="hidden" name="type" value="${dictionary.type}">
					
				</form>
			</div>
		</div>
</div>
</body>
</html>
