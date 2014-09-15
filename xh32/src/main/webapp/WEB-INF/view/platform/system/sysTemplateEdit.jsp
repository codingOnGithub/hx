<%--
	time:2011-12-28 14:04:30
	desc:edit the 模版管理
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp"%>
<title><spr:message code="operator.edit"/><spr:message code="sysTemplate"/></title>
<script type="text/javascript"
	src="${ctx}/servlet/ValidJs?form=sysTemplate"></script>
<script type="text/javascript"
	src="${ctx }/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript"
	src="${ctx}/js/ckeditor/ckeditor_sysTemp.js"></script>
<script type="text/javascript">
	$(function() {
		function showRequest(formData, jqForm, options) { 
			return true;
		} 
		mailEditor = ckeditor('htmlContent');
		valid(showRequest,showResponse);
		$("a.save").click(function() {
			$('#htmlContent').val(mailEditor.getData());
			$('#sysTemplateForm').submit(); 
		});					
	});
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<c:choose>
					<c:when test="${sysTemplate.templateId !=null }">
						<span class="tbar-label"><spr:message code="operator.edit"/><spr:message code="sysTemplate"/></span>
					</c:when>
					<c:otherwise>
						<span class="tbar-label"><spr:message code="operator.add"/><spr:message code="sysTemplate"/></span>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.save"/></a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link back" href="list.ht"><span></span><spr:message code="menu.button.back"/></a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">

			<form id="sysTemplateForm" method="post" action="save.ht">
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th><spr:message code="sysTemplate.name"/>: </th>
							<td><input type="text" id="name" name="name" value="${sysTemplate.name}" class="inputText" style="width:500px !important"/></td>
						</tr>
						<tr>
							<th><spr:message code="sysTemplate.useType"/>: </th>
							<td>
								<select name="useType">
									<option value="1"<c:if test="${sysTemplate.useType eq 1}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processComplete"/></option>
									<option value="2"<c:if test="${sysTemplate.useType eq 2}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processReminder"/></option>
									<option value="3"<c:if test="${sysTemplate.useType eq 3}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processCheck"/></option>
									<option value="4"<c:if test="${sysTemplate.useType eq 4}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processRevoked"/></option>
									<option value="5"<c:if test="${sysTemplate.useType eq 5}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processCancelDelegate"/></option>
									<option value="6"<c:if test="${sysTemplate.useType eq 6}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processCommunication"/></option>
									<option value="7"<c:if test="${sysTemplate.useType eq 7}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processStartuser"/></option>
									<option value="8"<c:if test="${sysTemplate.useType eq 8}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processDelegate"/></option>
									<option value="9"<c:if test="${sysTemplate.useType eq 9}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processReject"/></option>
									<option value="10"<c:if test="${sysTemplate.useType eq 10}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processFeedback"/></option>
									<option value="11"<c:if test="${sysTemplate.useType eq 11}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processCancelAgent"/></option>
									<option value="12"<c:if test="${sysTemplate.useType eq 12}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processCopyTo"/></option>
									<option value="13"<c:if test="${sysTemplate.useType eq 13}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processNobody"/></option>
									<option value="14"<c:if test="${sysTemplate.useType eq 14}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processTaskWarn"/></option>
									<option value="15"<c:if test="${sysTemplate.useType eq 15}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processTaskEnd"/></option>
									<option value="16"<c:if test="${sysTemplate.useType eq 16}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processTaskValuate"/></option>
									<option value="17"<c:if test="${sysTemplate.useType eq 17}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processTaskNotity"/></option>
									<option value="18"<c:if test="${sysTemplate.useType eq 18}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processTaskValuateTell"/></option>
									<option value="19"<c:if test="${sysTemplate.useType eq 19}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processOverDue"/></option>
									<option value="22"<c:if test="${sysTemplate.useType eq 22}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processAgent"/></option>
									<option value="23"<c:if test="${sysTemplate.useType eq 23}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processForward"/></option>
									<option value="24"<c:if test="${sysTemplate.useType eq 24}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.restartTask" /></option>
									<option value="25"<c:if test="${sysTemplate.useType eq 25}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processNotifyTaskOwner" /></option>
									<option value="26"<c:if test="${sysTemplate.useType eq 26}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processTransToRemind" /></option>
									<option value="27"<c:if test="${sysTemplate.useType eq 27}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processTransToFeedback" /></option>
									<option value="28"<c:if test="${sysTemplate.useType eq 28}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processCancelTransTo" /></option>
								</select>
							</td>
						</tr>
						<tr>
							<th><spr:message code="sysTemplate.title"/>: </th>
							<td>					
								<textarea rows="5" cols="30" id="title" name="title">${fn:escapeXml(sysTemplate.title)}</textarea>																											
							</td>
						</tr>
						<%--
						<tr>
							<th><spr:message code="sysTemplate.innerContent"/>: </th>
							<td>					
								<textarea id="innerContent" name="innerContent">${fn:escapeXml(sysTemplate.innerContent)}</textarea>																											
							</td>
						</tr>
						 --%>
						<tr>
							<th><spr:message code="sysTemplate.htmlContent"/>: </th>
							<td>					
								<textarea id="htmlContent" name="htmlContent">${fn:escapeXml(sysTemplate.htmlContent)}</textarea>																											
							</td>
						</tr>
						<tr>
							<th><spr:message code="sysTemplate.plainContent"/>: </th>
							<td>					
								<textarea rows="5" cols="30" id="plainContent" name="plainContent">${fn:escapeXml(sysTemplate.plainContent)}</textarea>																											
							</td>
						</tr>
					</table>
				<input type="hidden" name="templateId" value="${sysTemplate.templateId}" />
			</form>

		</div>
	</div>
</body>
</html>
