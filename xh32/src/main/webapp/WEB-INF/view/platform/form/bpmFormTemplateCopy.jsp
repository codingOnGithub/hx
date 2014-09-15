<%--
	time:2011-11-28 11:31:14
	desc:edit the 系统表单模板
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<f:js pre="js/lang/view/platform/form" ></f:js>
	<title><spr:message code="operator.copy"/><spr:message code="bpmFormTemplate.title"/></title>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript">
		var obj=window.dialogArguments;
		var valid;
		$(function(){
			$("#templateName").val(obj.templateName);
			$("#alias").val(obj.alias);
			valid=$("#formCopy").form();
		});
		function save(){
			var rtn = valid.valid();
			if (!rtn){
				return;			
			}
			
			var templateId=obj.templateId;
			var newTemplateName=$("#newTemplateName").val();
			var oldTemplateName=$("#templateName").val();
			var alias=$("#alias").val();
			var newAlias=$("#newAlias").val();
			
			var url="copyTemplate.ht";
			var para="templateId="+templateId+"&newTemplateName="+newTemplateName+"&newAlias="+newAlias;
			
			if(newTemplateName==""||newAlias==""){
				//window.close();
			}else{
				if(newTemplateName==oldTemplateName){
					$.ligerDialog.error($lang_form.bpmFormTemplate.notSameName,$lang.tip.error);
			    }else{
				    $.post(url,para,function(data){
				    	var obj=new com.hotent.form.ResultMessage(data);
			    		if(obj.isSuccess()){
						    $.ligerDialog.success(obj.getMessage(),$lang.tip.msg,function(){
							    window.close();
				    		});
			    		}else{
			    			$.ligerDialog.err($lang.tip.msg,$lang.save.failure,obj.getMessage());
			    		}
				    });
			}
		  }
		}
	</script>
	<style>
		html { overflow-x: hidden; }
	</style>
</head>
<body>
<div class="panel">
<div class="hide-panel">
  <div class="panel-top">
    <div class="tbar-title">
	    <span class="tbar-label"><spr:message code="operator.copy"/><spr:message code="bpmFormTemplate.title"/></span>
	</div>
	<div class="panel-toolbar">
			<div class="toolBar">
			<div class="group"><a class="link save" id="btnSearch" onclick="save()"><span></span><spr:message code="menu.button.save"/></a></div>
			<div class="l-bar-separator"></div>
			<div class="group"><a class="link del" onclick="javasrcipt:window.close()"><span></span><spr:message code="menu.button.close"/></a></div>
	</div>	
	</div>
</div>
</div>
	<div class="panel-body">
		<form id="formCopy" name="formCopy">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="30%"><spr:message code="copy.old"/><spr:message code="bpmFormTemplate.templateName"/>: </th>
					<td><input type="text" id="templateName" name="templateName" class="inputText" disabled="disabled"/></td>
				</tr>
				<tr>
					<th width="30%"><spr:message code="copy.new"/><spr:message code="bpmFormTemplate.templateName"/>: </th>
					<td><input type="text" id="newTemplateName" name="newTemplateName" class="inputText"  validate="{required:true}"/></td>
				</tr>
				<tr>
					<th width="30%"><spr:message code="copy.old"/><spr:message code="bpmFormTemplate.alias"/>: </th>
					<td><input type="text" id="alias" name="alias" class="inputText" disabled="disabled"/></td>
				</tr>
				<tr>
					<th width="30%"><spr:message code="copy.new"/><spr:message code="bpmFormTemplate.alias"/>: </th>
					<td><input type="text" id="newAlias" name="newAlias"class="inputText"  validate="{required:true}"/></td>
				</tr>
		</table>
		</form>
	</div>
</div>
</body>
</html>
