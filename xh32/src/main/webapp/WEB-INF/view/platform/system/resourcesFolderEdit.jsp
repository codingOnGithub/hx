<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<f:js pre="js/lang/view/platform/system" ></f:js>
	<title><spr:message code="resources.folderEdit.title"/></title>
	<script type="text/javascript">
		$(function(){
			$("#folderForm").ajaxForm({success:showResponse});
			$('a.add').click(function(){
				var name=$("#folderName").val();
				if(name==''){
					$.ligerDialog.warn($lang_system.resources.edit.completedNode,$lang.tip.warn);
				}else{
					$("#folderForm").submit();
				}
			});
		});
		
		function showResponse(responseText){
			var obj=new com.hotent.form.ResultMessage(responseText);
			if(obj.isSuccess()){//成功
				$.ligerDialog.success(obj.getMessage(),$lang.tip.success,function(){
					window.parent.location.reload();		
				});
		    }else{//失败
		    	$.ligerDialog.err($lang.tip.error,$lang_system.resources.dialog.error_msg_setPicture,obj.getMessage());
		    }
		};
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">
			<spr:message code="res.folder.new"/>
			</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link add" id="dataFormSave" href="#"><span></span><spr:message code="res.save"/></a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="icons.ht"><span></span><spr:message code="res.canel"/></a></div>
			</div>
		</div>
	</div>
	<div class="panel-body" id="folderEdit">
		<form id="folderForm" action="createFolder.ht" method="post" >
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<th width="20%"><spr:message code="res.pid"/>: </th>
				<td id="parentFoler" ></td>
			</tr>
			<tr>
				<th width="20%"><spr:message code="res.name"/>: </th>
				<td><input type="text" id="folderName" name="folderName" class="inputText"/></td>
			</tr>
		</table>
		<input type="hidden" id="path" name="path" value=""/>
		</form>
	</div>
</div>
</body>
</html>
