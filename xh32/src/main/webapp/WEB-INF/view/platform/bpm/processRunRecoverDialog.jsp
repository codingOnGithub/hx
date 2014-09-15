<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<f:js pre="js/lang/view/platform/bpm" ></f:js>
<title><spr:message code="processRun.recover.title"/></title>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerMsg.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerDialog.js" ></script>
<script type="text/javascript">
var callBack=function(rtn){
	 if(!rtn) return;
	 var json=$("#frmRecover").serialize() ;
	
	 var url=__ctx +"/platform/bpm/processRun/recover.ht";
	 $.post(url,json,function(responseText){
		 var obj=new com.hotent.form.ResultMessage(responseText);
		 if(obj.isSuccess()){
			 $.ligerDialog.success(obj.getMessage(),$lang.tip.msg,function(){
				 window.returnValue=1;
				 window.close();
			 });
		 }
		 else{
			 $.ligerDialog.err($lang.tip.msg,$lang.save.failure,obj.getMessage());
		 }
	 });
};

$(function(){
	 $("a.save").click(function(){	
		 if($.isEmpty( $('#opinion').val())){
			 $.ligerDialog.warn($lang_bpm.recover.opinion,$lang.tip.msg);
			 $('#opinion').focus();
			 return;
		 }
		 $.ligerDialog.confirm($lang_bpm.recover.confirm,$lang.tip.msg,callBack);
	 });
})
</script>
</head>
<body>
	<div class="panel">
		
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="processRun.recover.title"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.recover"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link close" href="#" onclick="window.close();"><span></span><spr:message code="menu.button.close"/></a></div>
				</div>
			</div>
		</div>
		
		<div class="panel-body">
			<form id="frmRecover">
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th nowrap="nowrap"><spr:message code="message.reminder"/>:</th>
						<td>
						<%-- 
							<input type="checkbox" name="informType" value="3" checked="checked"><spr:message code="message.inner"/></label>
							<input type="checkbox" name="informType" checked="checked" value="1"><spr:message code="message.mail"/>
							<input type="checkbox" name="informType" value="2"><spr:message code="message.sms"/>
						--%>
							<c:forEach items="${handlersMap}" var="item">
							    <input type="checkbox" name="informType" value="${item.key }"  <c:if test="${item.value.isDefaultChecked}">checked="checked"</c:if> />
					            ${item.value.title }
						    </c:forEach>
						</td>
					</tr>
					<tr>
						<th><spr:message code="processRun.recover.opinion"/>:</th>
						<td>
							<textarea rows="7" cols="61" style="width:350px;" id="opinion" name="opinion"></textarea>
							<input type="hidden" name="runId" value="${runId }"/>
							<input type="hidden" name="backToStart" value="${backToStart }"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>