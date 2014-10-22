<%--
	time:2012-12-14 17:17:09
	desc:reset the 用户表
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	
	<%@include file="/commons/include/form.jsp" %>
	<title><spr:message code="sysUser.resetpwd.title"/></title>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=sysUser"></script>
	<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
				
			$("#password").blur(function(){
		        if ( $(this).val() != $("#newPassword").val() ){
		            alert("你输入的密码不一致!");
		            return false;
		    }});

			valid(showRequest,showResponse);
			$("a.save").click(function() {
				$('#sysUserForm').submit(); 
			});
		});
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="sysUser.resetpwd.title"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="dataFormSave"><span></span><spr:message code="menu.button.save"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="${returnUrl}"><span></span><spr:message code="menu.button.back"/></a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form id="sysUserForm" method="post" action="resetPwd.ht">
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th width="20%"><spr:message code="sysUser.resetpwd.title"/>:  <span class="required">*</span></th>
						<td ><input type="password" id="newPassword" name="newPassword" style="width:255px !important" class="inputText"/></td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="sysUser.resetpwd.sure"/>:  <span class="required">*</span></th>
						<td ><input type="password" id="password" name="password" style="width:255px !important" class="inputText"/></td>
					</tr>
				</table>
				<input type="hidden" name="userId" value="${sysUser.userId}" />
			</form>
		</div>
</div>
</body>
</html>
