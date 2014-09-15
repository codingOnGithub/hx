<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
<title><spr:message code="bpmFormTable.title"/><spr:message code="operator.export"/></title>


	<script type="text/javascript">
	window.name="win";
			
	$(function(){	
		$("#btnSave").click(function(){
			$("#exportForm").submit();
		});
	});
	
	
	</script>
</head>
<base target="download"> 
<body>
	<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="bpmFormTable.title"/><spr:message code="operator.export"/></span>
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
			 <input type="hidden" name="tableIds" value="${tableIds}" >
			 <table id="tableid" class="table-detail" cellpadding="0" cellspacing="0" border="0">
			 	<tr>
			 		<th width="30%"><spr:message code="bpmFormTable.export.bpmFormTable"/>：</th>
			 		<td width="70%"><input type="checkbox"  name="bpmFormTable" checked="checked" disabled="disabled"></td>	
			 	</tr>
			 	<tr>
			 		<th><spr:message code="bpmFormTable.export.bpmFormField"/>：</th>
			 		<td><input type="checkbox"  name="bpmFormField" checked="checked" disabled="disabled"></td>	
			 	</tr>
			 	<tr>
			 		<th><spr:message code="bpmFormTable.export.subTable"/>：</th>
			 		<td><input type="checkbox"  name="subTable" checked="checked" value="true"></td>	
			 	</tr>
			 	<tr>
			 		<th><spr:message code="bpmFormTable.export.identity"/>：</th>
			 		<td><input type="checkbox"  name="identity" checked="checked" value="true" ></td>	
			 	</tr>		
			</table>				
			
			</div>
	    </form>
	</div><!-- end of panel-body -->
	<iframe id="download" name="download" height="0px" width="0px"></iframe>				
</body>
</html>