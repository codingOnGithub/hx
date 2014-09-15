<%--
	time:2012-12-19 15:38:01
	desc:edit the 自定义表代码模版
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp"%>
<title><spr:message code="title.edit"/><spr:message code="sysCodeTemplate"/></title>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript">
	$(function() {
		var options = {};
		if (showResponse) {
			options.success = showResponse;
		}
		var frm = $('#sysCodeTemplateForm').form();
		$("a.save").click(function() {
			frm.ajaxForm(options);
			if (frm.valid()) {
				$('#sysCodeTemplateForm').submit();
			}
		});
	});

	function showResponse(responseText) {
		var obj = new com.hotent.form.ResultMessage(responseText);
		if (obj.isSuccess()) {
			$.ligerDialog.confirm(obj.getMessage() + $lang.operateTip.continueOperate,$lang.tip.confirm,
							function(rtn) {
								if (rtn) {
									this.close();
									$("#sysCodeTemplateForm").resetForm();
								} else {
									window.location.href = "${ctx}/platform/system/sysCodeTemplate/list.ht";

								}
							});
		} else {
			$.ligerDialog.error(obj.getMessage(),$lang.tip.error);
		}
	}

	
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<c:choose>
					<c:when test="${sysCodeTemplate.id !=null}">
						<span class="tbar-label"><spr:message code="title.edit"/><spr:message code="sysCodeTemplate"/></span>
					</c:when>
					<c:otherwise>
						<span class="tbar-label"><spr:message code="title.add"/><spr:message code="sysCodeTemplate"/></span>
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
			<form id="sysCodeTemplateForm" method="post" action="save.ht">
				<table class="table-detail" cellpadding="0" cellspacing="0"
					border="0" type="main">
					<tr>
						<th width="20%"><spr:message code="sysCodeTemplate.name"/>:</th>
						<td><input type="text" id="templateName" name="templateName" value="${sysCodeTemplate.templateName}" class="inputText" validate="{required:true,maxlength:200}" /></td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="sysCodeTemplate.alias"/>:</th>
						<td><input type="text" id="templateAlias" name="templateAlias" value="${sysCodeTemplate.templateAlias}" class="inputText" validate="{required:true,maxlength:200}" /></td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="sysCodeTemplate.memo"/>:</th>
						<td><input type="text" id="memo" name="memo" value="${sysCodeTemplate.memo}" class="inputText" validate="{required:false,maxlength:200}" /></td>
					</tr>
					
					<tr>
						<th width="20%"><spr:message code="sysCodeTemplate.fileDir"/>:</th>
						<td><input type="text" id="fileDir" name="fileDir" value="${sysCodeTemplate.fileDir}" class="inputText" validate="{required:true,maxlength:200}" style="width: 350px;" /></td>
					</tr>
					
					<tr>
						<th width="20%"><spr:message code="sysCodeTemplate.fileName"/>:</th>
						<td><input type="text" id="fileName" name="fileName" value="${sysCodeTemplate.fileName}" class="inputText" validate="{required:true,maxlength:200}" style="width: 250px;"  /></td>
					</tr>

					<tr>
						<th width="20%"><spr:message code="sysCodeTemplate.html"/>:</th>
						<td><textarea name="html" cols="120" rows="20">${fn:escapeXml(sysCodeTemplate.html)}</textarea>
						</td>
					</tr>
				</table>
				<input type="hidden" name="id" value="${sysCodeTemplate.id}" />
			</form>

		</div>
	</div>
</body>
</html>
