<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
<title><spr:message code="bpmDataTemplate.dataTemplate"/><spr:message code="operator.import"/></title>
<f:js pre="js/lang/view/platform/form" ></f:js>
	<script type="text/javascript">
	window.name="win";
			
	$(function(){
		valid(showResponse);
		
		$("#btnSave").click(function(){
			var path = $('#file').val();
			var extName = path.substring(path.length-3, path.length);
			if(extName=='xls' || extName == 'xlsx'){
				$.ligerDialog.waitting($lang_form.bpmDataTemplate.importData.waitting_msg);
				$("#importForm").submit();	
			}else{
				$.ligerDialog.warn($lang_form.bpmDataTemplate.importData.file_validate_msg);
			}
		});
	});
	
	function showResponse(responseText){
		var obj=new com.hotent.form.ResultMessage(responseText);
		if(obj.isSuccess()){//成功
			$.ligerDialog.closeWaitting();
			//
			$.ligerDialog.success(obj.getMessage(),$lang.tip.msg);
	    }else{//失败
			$.ligerDialog.closeWaitting();
	    	$.ligerDialog.error(obj.getMessage(),$lang.tip.error);
	    }
	}
	
	function valid(showResponse){
		var options={success:showResponse};
		__valid=$("#importForm").validate({
			rules: {},
			messages: {},
			submitHandler:function(form){
				$(form).ajaxSubmit(options);
		    },
		    success: function(label) {}
		});
	}
	
	</script>
</head>
<body>
	<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="bpmFormTable.title"/><spr:message code="operator.import"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link import" id="btnSave"><span></span><spr:message code="menu.button.import"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del" onclick="javasrcipt:window.close()"><span></span><spr:message code="menu.button.close"/></a></div>
				
				</div>	
			</div>
	</div>
	<div class="panel-body">
		<form id="importForm" name="importForm" method="post" target="win" action="importSave.ht" enctype="multipart/form-data">
			<div class="row">
			 	<input type="hidden" id="__displayId__" name="__displayId__" value="${__displayId__}">
			 <table id="tableid" class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="22%"><spr:message code="menu.button.selectFile"/>：</th>
					<td width="78%"><input type="file" size="40" name="file" id="file"/></td>						
				</tr>
			</table>				
			
			</div>
	    </form>
	</div><!-- end of panel-body -->				
</body>
</html>