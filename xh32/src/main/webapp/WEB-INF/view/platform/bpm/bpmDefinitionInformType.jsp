<%
	//某个任务节点的手机访问的设置
%>
<%@page pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
  <title><spr:message code="bpmDefinition.informType.title"/></title>
    <f:js pre="js/lang/view/platform/bpm" ></f:js>
	<script type="text/javascript">
		$(function(){
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			$('#informTypeForm').ajaxForm(options);
			
			$(".save").click(function(){
				$('#informTypes').val(getMsgTypeList('informType'));
				$('#informTypeForm').submit();
			});
		});
		
		function getMsgTypeList(id){
			var msgTypeList=[];
			$("input[name='"+id+"']").each(function(){
				var me = $(this),
					val = me.val(),
					state = me.attr("checked");
				if(state)
					msgTypeList.push(val);
			});
			return msgTypeList.join(',');
		}
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.success(obj.getMessage(),$lang.tip.msg, function(rtn) {
					if(rtn){
						window.close();
					}
				});
			} else {
				$.ligerDialog.err($lang.tip.errorMsg,$lang_bpm.bpmDefinition.nodeSet.informType.fail,obj.getMessage());
			}
		}
	</script>
</head>
<body>
<div class="panel-top">
	<div class="tbar-title">
		<span class="tbar-label">
			<spr:message code="bpmDefinition.informType.titleName"/>
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
<form id="informTypeForm" method="post" action="saveInformType.ht">
	<div style="padding:8px 8px 8px 8px">
		<b><spr:message code="tip.explanation"/>：</b>
		<ul>
			<li><spr:message code="bpmDefinition.informType.message"/></li>
		</ul>
	</div>
	<div style="padding:8px 8px 8px 8px" id="nodeTypeDiv">
		<label><b><spr:message code="bpmDefinition.informType.notice"/> ${bpmNodeSet.nodeName} : </b></label> 
		<%--新增一致消息方式，这里不能动态变化
		<input type="checkbox" name="informType" value="1" <c:if test="${fn:contains(bpmNodeSet.informType,1)}">checked="checked"</c:if> />
		<spr:message code="message.mail"/>
		<input type="checkbox" name="informType" value="2" <c:if test="${fn:contains(bpmNodeSet.informType,2)}">checked="checked"</c:if> />
		<spr:message code="message.sms"/>
		<input type="checkbox" name="informType" value="3" <c:if test="${fn:contains(bpmNodeSet.informType,3)}">checked="checked"</c:if> />
		<spr:message code="message.inner"/>
		 --%>
		<c:forEach items="${handlersMap}" var="item">
		<input type="checkbox" name="informType" value="${item.key }"  <c:if test="${fn:contains(bpmNodeSet.informType,item.key)}">checked="checked"</c:if> />
          ${item.value.title }
		</c:forEach>
		
		<input type="hidden" name="actDefId" value="${bpmNodeSet.actDefId}" />
		<input type="hidden" name="nodeId" value="${bpmNodeSet.nodeId}" />
		<input type="hidden" id="informTypes" name="informTypes" value=""/>
	</div>
</form>
</body>
</html>