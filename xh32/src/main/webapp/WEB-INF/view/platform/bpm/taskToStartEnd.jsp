<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="task.toStartEnd.title"/></title>
<f:js pre="js/lang/view/platform/bpm" ></f:js>
<script type="text/javascript" src="${ctx}/js/sf/platform/system/SysDialog.js"></script>
<script type="text/javascript">
 function save(){
	 var opinion=$("#opinion").val();
	 if(opinion==""){
		 $.ligerDialog.error($lang_bpm.task.toStartEnd.warn_msg_opinion_require,$lang.tip.error);
		 return;
	 }
 	 $.ligerDialog.confirm($lang_bpm.task.toStartEnd.confirm_msg,$lang.tip.confirm,function(rtn) {
		if(rtn) {
		 	window.returnValue=opinion;
	   		window.close();
		}
	 });
 }
</script>
</head>

<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="task.toStartEnd.title"/></span>
			</div>
			<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group"><a class="link save" id="dataFormSave" href="#" onclick="save()"><span></span><spr:message code="menu.button.ok"/></a></div>
						<div class="l-bar-separator"></div>
						<div class="group"><a class="link close" href="#" onclick="window.close();"><span></span><spr:message code="menu.button.close"/></a></div>
					</div>
				</div>
		</div>
		<div class="panel-body">
			<spr:message code="task.toStartEnd.title"/>：<textarea rows="6" cols="45" id="opinion" name="opinion"></textarea>
		</div>
	</div>
</body>
</html>