<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
<title><spr:message code="bpmDefinition.export.title"/></title>

	<script type="text/javascript">
	window.name="win";
			
	$(function(){	
		$("#btnSave").click(function(){
			$("#exportForm").submit();
		});
	});
	
	
	function onChecked(me,obj){
/* 		if($(me).attr('checked')==undefined){
			$('#'+obj).attr('checked',false); 
		}*/
	}
	
	function onCheck(me,obj){
		if($(me).attr('checked')=='checked'){
			$('#'+obj).attr('checked',true);
		}
	}
	
	
	</script>
</head>
<base target="download"> 
<body>
	<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="bpmDefinition.export.title"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="btnSave"><span></span><spr:message code="menu.button.export"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del" onclick="javasrcipt:window.close()"><span></span><spr:message code="menu.button.close"/></a></div>
				
				</div>	
			</div>
	</div>
	<div class="panel-body">
		<form id="exportForm" name="exportForm" method="post" target="download" action="exportXml.ht">
			<div class="row">
			 <input type="hidden" name="bpmDefIds" value="${bpmDefIds}" >
			 <table id="tableid" class="table-detail" cellpadding="0" cellspacing="0" border="0">
			 	<tr>
			 		<th width="30%"><spr:message code="bpmDefinition.export.bpmDefinition"/>：</th>
			 		<td width="20%"><input type="checkbox"  name="bpmDefinition" checked="checked" disabled="disabled" value="true"></td>
			 		<th width="30%"><spr:message code="bpmDefinition.export.history"/>：</th>
			 		<td width="20%"><input type="checkbox"  name="bpmDefinitionHistory" checked="checked" value="true"></td>	
			 	</tr>	
			 	<tr>
			 		<th><spr:message code="bpmDefinition.export.bpmNodeSet"/>：</th>
			 		<td><input type="checkbox"  name="bpmNodeSet" checked="checked" disabled="disabled" value="true"></td>
					<th><spr:message code="bpmDefinition.export.bpmNodeUser"/>：</th>
			 		<td><input type="checkbox" id="bpmNodeUser"  name="bpmNodeUser" checked="checked"  value="true" ></td>		
			 	</tr>
			 	<tr>
			 		<th><spr:message code="bpmDefinition.export.bpmDefRights"/>：</th>
			 		<td><input type="checkbox"  name="bpmDefRights" checked="checked" value="true"></td>
			 		<th><spr:message code="bpmDefinition.export.taskApprovalItems"/>：</th>
			 		<td><input type="checkbox"  name="taskApprovalItems" checked="checked" value="true"></td> 		
			 	</tr>
			 	<tr>
			 		<th><spr:message code="bpmDefinition.export.bpmNodeRule"/>：</th>
			 		<td><input type="checkbox"  name="bpmNodeRule" checked="checked" value="true"></td>		
			 		<th><spr:message code="bpmDefinition.export.bpmNodeScript"/>：</th>
			 		<td><input type="checkbox"  name="bpmNodeScript" checked="checked" value="true"></td> 
			 	</tr>
			    <tr>
			 		<th><spr:message code="bpmDefinition.export.bpmNodeButton"/>：</th>
			 		<td><input type="checkbox"  name="bpmNodeButton" checked="checked" value="true"></td>
			 		<th><spr:message code="bpmDefinition.export.bpmDefVar"/>：</th>
			 		<td><input type="checkbox"  name="bpmDefVar" checked="checked" value="true"></td> 		
			 	</tr>
			 	<tr>
		 		 	<th><spr:message code="bpmDefinition.export.taskReminder"/>：</th>
			 		<td><input type="checkbox"  name="taskReminder" checked="checked" value="true"></td>
			 		<th><spr:message code="bpmDefinition.export.bpmNodeSign"/>：</th>
			 		<td><input type="checkbox"  name="bpmNodeSign" checked="checked" value="true"></td> 		
			 	</tr>
				<tr>
					<th><spr:message code="bpmDefinition.export.bpmNodeMessage"/>：</th>
					<td><input type="checkbox"  name="bpmNodeMessage" checked="checked" value="true"></td>
			 		<th><spr:message code="bpmDefinition.export.subBpmDefinition"/>：</th>
			 		<td><input type="checkbox"  name="subBpmDefinition" checked="checked" value="true"></td>		
			 	</tr>
			 	<tr>
					<th><spr:message code="bpmDefinition.export.bpmFormDef"/>：</th>
					<td><input type="checkbox" id="bpmFormDef" name="bpmFormDef" checked="checked" value="true" onclick="onChecked(this,'bpmFormTable');"></td>
					<th></th>
					<td></td>
			 	</tr>
			 	
			</table>				
			
			</div>
	    </form>
	</div><!-- end of panel-body -->
<iframe id="download" name="download" height="0px" width="0px"></iframe>				
</body>
</html>