<%--
	time:2011-11-28 11:31:14
	desc:edit the 系统角色表
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<f:js pre="js/lang/view/platform/system" ></f:js>
	<title><spr:message code="sys.role.copyRole"/></title>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/Share.js"></script>
	<script type="text/javascript">
	var roleId=${sysRole.roleId};
	
	
	function validInput(){
		var __valid__=$("#frmCopy").validate({
			rules: {
				newRoleName:{
					required:true,
					maxlength:20
				},
				newAlias:{
					required:true,
					maxlength:20
				}
			},
			messages: {
				newRoleName:{
					required:$lang_system.sysRole.copy.nullName,
					maxlength:$lang_system.sysRole.copy.tooLongName
				},
				newAlias:{
					required:$lang_system.sysRole.copy.nullAlias,
					maxlength:$lang_system.sysRole.copy.tooLongAlias
				}
			}
		});
		return __valid__;
	}

	
	$(function(){
		validInput();
		
	});
	function save(){
		var rtn=$("#frmCopy").valid();
		if(!rtn){
			return;
		}
		var newRoleName=$("#newRoleName").val();
		var newAlias=$("#newAlias").val();
		var alias=$("#alias").val();
		var oldRoleName=$("#roleName").val();
		
		if(newRoleName==oldRoleName){
			$.ligerDialog.error($lang_system.sysRole.copy.nameNotSame,$lang.tip.msg);
			return ;
	    }
		
		if(newAlias==alias){
			$.ligerDialog.error($lang_system.sysRole.copy.aliasNotSame,$lang.tip.msg);
			return ;
	    }
		var url="copyRole.ht";
		var para="roleId="+roleId+"&newRoleName="+newRoleName +"&newAlias=" +newAlias;
		
	    $.post(url,para,function(responseText){
	    	var obj=new com.hotent.form.ResultMessage(responseText);
	    	if(obj.isSuccess()){//成功
	    		$.ligerDialog.success(obj.getMessage(),$lang.tip.msg,function(){
	    			window.close();
	    		});	
	        }else{//失败
	        	$.ligerDialog.err($lang.tip.error,$lang_system.sysRole.copy.saveFail,obj.getMessage());
	        }
	    });
		
		
	}
	</script>
	<style type="text/css">
		html { overflow-x: hidden; }
	</style>
</head>
<body>
<div class="panel">
  <div class="panel-top">
    <div class="tbar-title">
	    <span class="tbar-label"><spr:message code="sys.role.copyRole"/></span>
	</div>
	<div class="panel-toolbar">
			<div class="toolBar">
			<div class="group"><a class="link save" id="btnSearch" onclick="save()"><span></span><spr:message code="menu.button.save"/></a></div>
			<div class="l-bar-separator"></div>
			<div class="group"><a class="link del" onclick="javasrcipt:window.close()"><span></span><spr:message code="menu.button.close"/></a></div>
	</div>	
	</div>
</div>
	<div class="panel-body">
		<form id="frmCopy" name="frmCopy">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="15%"><spr:message code="sys.role.oldName"/>: </th>
					<td><input type="text" id="roleName" name="roleName" class="inputText" disabled="disabled" value="${sysRole.roleName }"/></td>
					<th width="15%"><spr:message code="sys.role.newName"/>: </th>
					<td><input type="text" id="newRoleName" name="newRoleName"class="inputText" onblur="Share.getKeyName(this,newAlias)"/></td>
				</tr>
				<tr>
					<th width="15%"><spr:message code="sys.role.oldAlias"/>: </th>
					<td><input type="text" id="alias" name="alias" class="inputText" disabled="disabled" value="${sysRole.alias }"/></td>
					<th width="15%"><spr:message code="sys.role.newAlias"/>: </th>
					<td><input type="text" id="newAlias" name="newAlias"class="inputText"/></td>
				</tr>
			</table>
		</form>
					
	</div>
</body>
</html>
