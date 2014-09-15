<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<f:js pre="js/lang/view/platform/bpm" ></f:js>
	<title><spr:message code="processCenter.cancel.batTitle"/></title>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript">
		var ids="${param.ids}";
	
		$(function() {
			$("#dataFormSave").click(save);
		});
		
		function callBack(rtn) {
			if(!rtn) return;
			var opinion=$("#opinion").val();
			var informType=$.getChkValue("informtype");
			var params= {ids:ids,opinion:opinion,informType:informType};
			$.post(__ctx+"/platform/bpm/bpmTaskExe/cancelBat.ht",params,function(msg){
				var obj=new com.hotent.form.ResultMessage(msg);
				if(obj.isSuccess()){
					$.ligerDialog.successExt($lang.tip.msg,$lang_bpm.cancelAssign.batCancelSuccess,obj.getMessage(),function(){
						 window.returnValue="ok";
			    		 window.close(); 
			    	});
				}else{
					 $.ligerDialog.error(obj.getMessage(),$lang.tip.msg);
				}
			});
		}

		function save(){
			var rtn=$("#bpmTaskExeForm").form().valid();
			if(!rtn) return;
		 	$.ligerDialog.confirm($lang_bpm.cancelAssign.confirmBatCancel,$lang.tip.msg,callBack);
		 }
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">
				<spr:message code="processCenter.cancel.batTitle"/>
			</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.cancel"/></a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link close" href="#" onclick="window.close()" ><span></span><spr:message code="menu.button.close"/></a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="bpmTaskExeForm" method="post" >
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="20%"><spr:message code="processCenter.cancel.informtype"/>: </th>
					<td>
					   <%-- 
						<label><input type="checkbox" name="informtype" value="3"  checked="checked"/>站内消息</label>
						<label><input type="checkbox" name="informtype" value="1"  checked="checked"/>邮件</label>
						<label><input type="checkbox" name="informtype" value="2"  />手机短信</label>
						--%>
						<c:forEach items="${handlersMap}" var="item">
						   <input type="checkbox" name="informType" value="${item.key }"  <c:if test="${item.value.isDefaultChecked}">checked="checked"</c:if> />
				            ${item.value.title }
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th width="20%"><spr:message code="processCenter.cancel.opinion"/>: </th>
					<td>
						<textarea rows="5" cols="50" id="opinion"  validate="{required:true}" ></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
</body>
</html>