
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
<f:js pre="js/lang/view/platform/bpm" ></f:js>
<title><spr:message code="bpmDefinition.import.title"/></title>

<%-- <link href="${ctx}/styles/ligerUI/ligerui-all.css" rel="stylesheet" type="text/css" /> --%>
<f:link href="Aqua/css/ligerui-all.css"></f:link>
	<script type="text/javascript">
	window.name="win";
			
	$(function(){	
		valid(showResponse);
		$("#btnSave").click(function(){
			if($("#btnSave").hasClass("disabled"))return;
			$("#btnSave").addClass("disabled");
			
			var path = $('#xmlFile').val();
			var extNaem = path.substring(path.length-3, path.length);
			if(extNaem!='zip'){
				$.ligerDialog.warn($lang_bpm.bpmDefinition.imp.selectZip,$lang.tip.msg);
			}else{
				$("#importForm").submit();
				$.ligerDialog.waitting($lang_bpm.bpmDefinition.imp.importing);
			}
		});		
	});
	function showResponse(responseText){
		$.ligerDialog.closeWaitting();
		var obj=new com.hotent.form.ResultMessage(responseText);
		if(obj.isSuccess()){//成功
			$.ligerDialog.closeWaitting();
			var message = obj.getMessage();
			if(message){
				message =message.replaceAll("###","\'").replaceAll("!!!","<font ").replaceAll("%%%","</font>").replaceAll("&gt;",">").replaceAll("&lt;","<");   
			}
		  	 $.ligerDialog.tipDialog($lang.tip.msg,$lang_bpm.bpmDefinition.imp.result,message,null,function(){
					window.returnValue="/";
					window.close();
				});
	    }else{//失败
			$.ligerDialog.closeWaitting();
	    	$.ligerDialog.err($lang.tip.msg,$lang_bpm.bpmDefinition.imp.fail,obj.getMessage());
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
<div class="panel">
	<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="bpmDefinition.import.title"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="btnSave"><span></span><spr:message code="menu.button.import"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del" onclick="javasrcipt:window.close()"><span></span><spr:message code="menu.button.close"/></a></div>
				
				</div>	
			</div>
	</div>
	<div class="panel-body">
		<form id="importForm" name="importForm" method="post" target="win" action="importXml.ht" enctype="multipart/form-data">
				<div class="row">
					 <div class="panel-detail">
						 <table id="tableid" class="table-detail" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th width="22%"><spr:message code="menu.button.selectFile"/>：</th>
								<td width="78%"><input type="file" size="40" name="xmlFile" id="xmlFile"/></td>						
							</tr>
						</table>				
					</div>
				</div>
		</form> 		
	</div><!-- end of panel-body -->				
</div> 
</body>
</html>