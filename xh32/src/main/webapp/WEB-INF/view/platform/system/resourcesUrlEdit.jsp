
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<f:js pre="js/lang/view/platform/system" ></f:js>
	<title><spr:message code="resources.urlEdit.title"/></title>
	<script type="text/javascript"	src="${ctx }/js/hotent/displaytag.js"></script>
	<script type="text/javascript"	src="${ctx }/js/lg/plugins/ligerComboBox.js"></script>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=resourcesUrl"></script>
	<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			valid(showRequest,showResponse);
			$("a.save").click(function() {
				$('#resourcesUrlForm').submit(); 
			});
			function showResponse(responseText, statusText)  { 
				var obj=new com.hotent.form.ResultMessage(responseText);
				if(obj.isSuccess()){//成功
					$.ligerDialog.success(obj.getMessage(),$lang.tip.success,function(rtn){
					});
					
			    }else{//失败
			    	$.ligerDialog.err($lang.tip.error,$lang_system.resources.urlEdit.fail_msg,obj.getMessage());
			    }
			} 
		});
		
		function add(){
			var tr='';
			tr+='<tr>';
			tr+='<td style="text-align: center;">';
			tr+='<input type="radio" name="defaultUrl" >'; 
			tr+='</td>';
			tr+='<td >';
			tr+='<input class="inputText" type="text" name="name"	style="width: 95%;" >';
			tr+='</td>';
			tr+='<td>';
			tr+='<input class="inputText" type="text" name="url"	style="width: 95%;" onchange="setDefaultUrl(this);">';
			tr+='</td>';
			tr+='<td style="text-align:center">';
			tr+='<a href="#" class="link del" onclick="handlerDelOne(this);">'+$lang.button.del+'</a>';
			tr+='</td>';
			tr+='</tr>';
			$("#resourcesUrlItem").append(tr);
		};
		
		function checkDell(){
			var trCheckeds=$("#resourcesUrlItem").find(":checkbox[name='resUrlId'][checked]");
			$.each(trCheckeds,function(i,c){
				var tr=$(c).parents('tr');
				$(tr).remove();
			});
			
		};
		function handlerDelOne(obj){
			var tr=$(obj).parents('tr');
			$(tr).remove();
		};
		function setDefaultUrl(obj){
			var tr=$(obj).parents('tr');
			tr.find(":radio[name='defaultUrl']").val($(obj).val());;
		};
	</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="res.edit"/>【${resources.resName }】URL</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save"><span></span><spr:message code="res.save"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a onclick="add();" class="link add"><span></span><spr:message code="res.add"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a onclick="checkDell();" class="link del"><span></span><spr:message code="res.del"/></a></div>
				</div>	
			</div>
		</div>
		<div class="panel-body">
			<form id="resourcesUrlForm" method="post" action="upd.ht">
				<input type="hidden" name="resId" value="${resources.resId }">
				<input type="hidden" name="returnUrl" id="returnUrl" value="${returnUrl }">
				
				<table id="resourcesUrlItem" class="table-grid table-list" id="0" cellpadding="1" cellspacing="1">
			   		<thead>
			   			<th width="10%"><spr:message code="res.default.url"/></th>
			   			<th width="25%"><spr:message code="res.name"/></th>
			    		<th width="34%">URL</th>
			    		<th width="10%"><spr:message code="res.manager"/></th>
			    	</thead>
			    	<tbody>
			    	<c:forEach items="${resourcesUrlList}" var="resourcesUrlItem">
			    		<tr>
			    			<td style="text-align: center;">
			    				<input  type="radio" name="defaultUrl" value="${resourcesUrlItem.url }" style="width: 95%;" <c:if test="${resourcesUrlItem.url==resources.defaultUrl}">checked="checked"</c:if>> 
			    			</td>
				    		<td >
			    				<input class="inputText" type="text" name="name"	style="width:95%;" value="${resourcesUrlItem.name}">
			    			</td>
			    			<td >
			    				<input class="inputText" type="text" name="url"	style="width: 95%;" value="${resourcesUrlItem.url}" onchange="setDefaultUrl(this);">
			    			</td>
			    			
			    			<td style="text-align: center;">
			    				<a href="#" class="link del" onclick="handlerDelOne(this);"><spr:message code="res.del"/></a>
							</td>
			    		</tr>
			    	</c:forEach>
			    	</tbody>
			    </table>
			</form>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


