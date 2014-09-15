
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
<title><spr:message code="subSystem"/><spr:message code="title.import"/></title>
<%-- <link href="${ctx}/styles/ligerUI/ligerui-all.css" rel="stylesheet" type="text/css" /> --%>
<f:js pre="js/lang/view/platform/system" ></f:js>
<f:link href="Aqua/css/ligerui-all.css"></f:link>
	<script type="text/javascript">
	var obj=window.dialogArguments;	
	$(function(){	
		var option={success:showResponse};
		$("#importForm").ajaxForm(option);
		if(obj!=null){
			$("#systemId").val(obj.systemId);
		}
		$("#btnSave").click(function(){
			var path = $('#xmlFile').val();
			var extName = path.substring(path.length-3, path.length);
			if(extName!='xml'){
				$.ligerDialog.warn($lang_system.subSystem.import_.warn_msg_select_file,$lang.tip.warn);
			}else{
				$("#importForm").submit();
			}
		});		
	});
	
	function showResponse(responseText){
		var obj=new com.hotent.form.ResultMessage(responseText);
		if(obj.isSuccess()){//成功
			$.ligerDialog.success(obj.getMessage(),$lang.tip.success,function(){
				window.close();
			});
	    }else{//失败
	    	$.ligerDialog.err($lang.tip.error,$lang_system.subSystem.import_.error_msg,obj.getMessage());
	    }
	}
	
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="subSystem"/><spr:message code="title.import"/></span>
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
			 <table id="tableid" class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="22%"><spr:message code="menu.button.selectFile"/>:</th>
					<td width="78%"><input type="file" size="40" name="xmlFile" id="xmlFile"/></td>						
				</tr>
			</table>				
			</div>
			<input type="hidden" name="systemId" id="systemId" value=""/>
	    </form>
	</div><!-- end of panel-body -->				
</div> 
</body>
</html>