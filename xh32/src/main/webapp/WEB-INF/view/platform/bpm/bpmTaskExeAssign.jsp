<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<f:js pre="js/lang/view/platform/bpm" ></f:js>
	<title><spr:message code="bpmTaskExe.assign.title"/></title>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			$("#bpmTaskExeForm").ajaxForm(options);
			
			$("a.save").click(delegate);
		});
		
		var objSave=null;
		
		function delegate(){
			objSave=$(this);	
			if(objSave.hasClass('disabled')){
				return;
			}
			objSave.addClass("disabled");
			var frm=$('#bpmTaskExeForm').form();
			if(!frm.valid()){
				objSave.removeClass("disabled");
				return;
			} 
			$.ligerDialog.confirm($lang_bpm.assign.confirm,$lang.tip.msg,function(rtn){
				if(!rtn){
					objSave.removeClass("disabled");
					return;
				}
				$("#bpmTaskExeForm").submit();
			});
		}
			
		function showResponse(responseText) {
			objSave.removeClass("disabled");
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {//成功
				 $.ligerDialog.success(obj.getMessage(),$lang.tip.msg,function(){
					 window.returnValue=1;
					 window.close();
					 window.parent.close();
				 });
			} else {
				$.ligerDialog.error(obj.getMessage(),$lang.tip.msg,function(rtn){
					window.parent.close();
				});
			}
		}
	
		function chooseUser() {
			UserDialog({isSingle:true,
				callback:function(userIds, fullnames){
					$("#assigneeId").val(userIds);
					$("#assigneeName").val(fullnames);
					$('#bpmTaskExeForm').form().valid();
				}
			});
		};

	</script>
</head>
<body style="overflow-x:hidden;">
<div class="panel">
<div class="hide-panel">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">
				<spr:message code="bpmTaskExe.assign.title"/>
			</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.assignTask"/></a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link close" href="#" onclick="window.close()" ><span></span><spr:message code="menu.button.close"/></a></div>
			</div>
		</div>
	</div>
	</div>
	<div class="panel-body">
		<form id="bpmTaskExeForm" method="post" action="assignSave.ht">
			<input type="hidden" name="taskId" value="${taskId}">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="120px"><spr:message code="bpmTaskExe.assign.assignee"/>:</th>
					<td>
						<input type="hidden" id="assigneeId" name="assigneeId" >
						<input type="text"  id="assigneeName" name="assigneeName" readonly="readonly" style="width:200px;" validate="{required:true}"  >
						<a href="#" class="button"  onclick="chooseUser()"><span class="icon ok" ></span><span class="chosen" ><spr:message code="menu.button.choose"/></span></a>
					</td>
				</tr>
				<tr>
					<th nowrap="nowrap"><spr:message code="message.reminder"/>: </th>
					<td>
					<%--这里消息发送方式固定死了，改成：消息方式从app-jms.xml中的handlersMap中获取，名称从具体的hander中获取，如mailMessageHandler.getTitle()
						<label><input type="checkbox" name="informtype" value="3"  checked="checked"/><spr:message code="message.inner"/></label>
						<label><input type="checkbox" name="informtype" value="1"  checked="checked"/><spr:message code="message.mail"/></label>
						<label><input type="checkbox" name="informtype" value="2" /><spr:message code="message.sms"/></label>
					 --%>	
							<c:forEach items="${handlersMap}" var="item">
							   <input type="checkbox" name="informType" value="${item.key }"  <c:if test="${item.value.isDefaultChecked}">checked="checked"</c:if> />
					            ${item.value.title }
							</c:forEach>
					</td>
				</tr>
				<tr>
					<th><spr:message code="bpmTaskExe.assign.memo"/>: </th>
					<td>
						<textarea rows="5" cols="50" name="memo"  validate="{required:true}" id="memo" ></textarea>
					</td>
				</tr>
				
				
			</table>
		</form>
	</div>
</div>
</body>
</html>