<%
	//某个任务节点的手机访问的设置
%>
<%@page pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
   
    <%@include file="/commons/include/form.jsp" %>
     <title><spr:message code="bpmDefinition.mobile.title"/</title>
	<script type="text/javascript">
		$(function(){
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			$('#setMobileForm').ajaxForm(options);
			$(".save").click(function(){
				$('#setMobileForm').submit();
			});
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.success(obj.getMessage(),"提示信息", function(rtn) {
					if(rtn){
						window.close();
					}
				});
			} else {
				$.ligerDialog.err("出错信息","编辑手机访问设置失败",obj.getMessage());
			}
		}
	</script>
</head>
<body>
<div class="panel-top">
	<div class="tbar-title">
		<span class="tbar-label">
			<spr:message code="bpmDefinition.mobile.visit"/>
	    </span>
	</div>
	<div class="panel-toolbar">
		<div class="toolBar">
			<div class="group"><a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.save"/></a></div>
			<div class="l-bar-separator"></div>
			<div class="group"><a class="link del" href="#" onclick="window.close()"><span></span><spr:message code="menu.button.close"/></a></div>
		</div>
	</div>
</div>
<form id="setMobileForm" method="post" action="setMobile.ht">
	<div style="padding:8px 8px 8px 8px">
		<b><spr:message code="bpmDefinition.mobile.note"/>：</b>
		<ul>
			<li><spr:message code="bpmDefinition.mobile.tip"/></li>
		</ul> 
	</div>
	<div style="padding:8px 8px 8px 8px" id="nodeTypeDiv">
		<label><b>${bpmNodeSet.nodeName} : </b></label><input type="checkbox" name="isAllowMobile" value="1" <c:if test="${bpmNodeSet.isAllowMobile==1}">checked="checked"</c:if> /><spr:message code="bpmDefinition.mobile.allow"/>
		<input type="hidden" name="actDefId" value="${bpmNodeSet.actDefId}" />
		<input type="hidden" name="nodeId" value="${bpmNodeSet.nodeId}" />
	</div>
</form>
</body>
</html>