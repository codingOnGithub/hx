<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<%@include file="/commons/include/get.jsp" %>
	<title><spr:message code="bpmFormDialog.param.title"/></title>
	<script type="text/javascript" src="${ctx}/js/util/easyTemplate.js"></script>
	<script type="text/javascript">
		$(function(){
			var paramArr = '${param.params}';
			var isPreviewCallCode = '${param.isPreviewCallCode}';
			var resultfield = {} ;
			if(isPreviewCallCode == 'true'){
				$("#callCode").show();
				resultfield = eval("("+'${param.resultfield}'+")") ;
				paramArr = eval("("+paramArr+")") ;
				var paramString = "" ;
				var paramReturnString = "" ;
				for(var i=0;i<resultfield.length;i++){
					if(i>0){
						paramReturnString+=", ";
					}
					paramReturnString+=resultfield[i].field+" = \"<spr:message code='bpmFormDialog.param.value'/>\""
				}
				for(var i=0;i<paramArr.length;i++){
					if(i>0){
						paramString+="&";
					}
					paramString+=paramArr[i]+"=<spr:message code='bpmFormDialog.param.value'/>"
				}
				$("[id='paramString']").text(paramString);
				$("#paramReturnString").text(paramReturnString);
			}else{
				$("#bpmFormDialogTable").show();
				paramArr = $.parseJSON(paramArr) ;
				if(paramArr.length<=0){
					$('#listManager').show() ;
				}
				var template = $('#templateArea').val();
				var htmlContent = easyTemplate(template,paramArr).toString();
				
				$('#contentBody').html(htmlContent) ;
				$('.add').live('click',function(){
					var tr = $(this).parent().parent() ;
					tr.clone(true).appendTo(tr.parent()) ;
				});
				$('.del').live('click',function(){
					var tr = $(this).parent().parent() ;
					tr.remove() ;
				});
			}
		})
		function selectForm(){
			var result= ""; 
			var paramNames = $('[name="paramName"]') ;
	    	for(var i=0;i<paramNames.length;i++){
				if($.trim($(paramNames[i]).val())!=''){
					result += $(paramNames[i]).val() + "=" + $(paramNames[i]).closest('td').next('td').find('[name="paramValue"]').val() + "&" ;
				}
			}
			return result.substring(0,result.length-1);
	    };
	    
	</script>
	<style type="text/css">
	html,body{height:100%;width:100%; overflow: auto;}
	#bpmFormDialogTable th, #bpmFormDialogTable td{text-align:center;}
	</style>
</head>
<body>

	<div class="panel">
		<div class="panel-top">
		</div>
		<div class="panel-body">
				<div class='panel-table'>
				<span id="callCode" style="display:none;">
					<table id="callCodeTable" class="table-grid table-list">
					<tr>
						<td><spr:message code="bpmFormDialog.param.step1"/>：</td><td style="text-align: left;">引入CommonDialog.js：<br>
						&lt;script type="text/javascript" src="\${ctx}/js/hotent/platform/form/CommonDialog.js"&gt;&lt;/script&gt;；</td>
					</tr>
					<tr>
						<td><spr:message code="bpmFormDialog.param.step2"/>：</td><td style="text-align: left;"><spr:message code="bpmFormDialog.param.tip1"/>：<br>
						var paramValueString = "<spr:message code='bpmFormDialog.param.name'/>=<spr:message code='bpmFormDialog.param.value'/>";</td>
					</tr>
					<tr>
						<td><spr:message code="bpmFormDialog.param.step3"/>：</td><td style="text-align: left;"><spr:message code="bpmFormDialog.param.tip2"/>;</td>
					</tr>
					<tr>
						<td style="width:60px;"><spr:message code="bpmFormDialog.param.callCode"/>：</td><td style="text-align: left;">
						&lt;script type="text/javascript" src="\${ctx}/js/hotent/platform/form/CommonDialog.js"&gt;&lt;/script&gt;<br>
						&lt;script type="text/javascript"&gt;<br>
						var paramValueString = "<span id='paramString'></span>";<br>
						CommonDialog("${param.alias}",function(data){<br>
							&nbsp;&nbsp;//data<spr:message code="bpmFormDialog.param.back"/> Object { <span id="paramReturnString"></span>}，<spr:message code="bpmFormDialog.param.tip3"/><br>
						},paramValueString);<br>
						&lt;/script&gt;
						</td>
					</tr>
					</table>
				</span>
				<table id="bpmFormDialogTable" cellpadding="1" class="table-grid table-list" cellspacing="1" style="display:none;">
					<tr>
						<th><spr:message code="bpmFormDialog.param.name"/></th>
						<th><spr:message code="bpmFormDialog.param.value"/></th>
		 	            <th style="display:none" id="listManager"><spr:message code="list.operate"/></th>
					</tr>
					<tbody id="contentBody">
					</tbody>
				</table>
				
				</div>
			
		</div><!-- end of panel-body -->
	</div> <!-- end of panel -->
       	 
	<textarea id="templateArea" style="display:none">
		<#if (data.length>0)>
	        <#list data as obj>
	          <tr>
		          <td><input style="background:transparent;border:0px;" name="paramName" value="\${obj}" readOnly="true" /></td>
		          <td><input type="text" name="paramValue" /></td>
	          </tr>
	        </#list>
			<#else>
	          <tr>
		          <td><input type="text" name="paramName" /></td>
		          <td><input type="text" name="paramValue" /></td>
				  <td>
			          <a class="link add" href="#"><spr:message code="menu.button.add"/></a>
			          <a class="link del" href="#"><spr:message code="menu.button.del"/></a>
		          </td>
	          </tr>
    	</#if>
	</textarea> 
                        
</body>
</html>
