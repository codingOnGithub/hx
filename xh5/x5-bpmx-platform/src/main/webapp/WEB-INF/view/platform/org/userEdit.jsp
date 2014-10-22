<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户编辑</title>
		<link rel="stylesheet" type="text/css" href="/x5/styles/theme/default/css/x5.css">
		<script type="text/javascript" src="${ctx}/js/lib/jquery/jquery.form.js" ></script>
		<script type="text/javascript" src="${ctx}/js/hotent/form.js" ></script>
		<script type="text/javascript" src="${ctx}/js/hotent/lang/zh_CN.js" ></script>
		<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js" ></script>
		<script type="text/javascript">
		$(function() {
			var options = {};
			if (showResponse) {
				options.success = showResponse;
			}
			var frm = $('#userForm').form();
			$("a.link[iconCls='icon-save']").click(function() {
				frm.ajaxForm(options);
				if (frm.valid()) {
					$('#userForm').submit();
				}
			});
		});

		function showResponse(responseText) {
			
		}
		</script>
	</head>
	<body class="easyui-layout">
		<div class="sub_c data_c">
			<div id="tb" style="height:auto;">
			    <div style="margin-bottom:5px" class="datagrid-toolbar">
			        <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true">保存</a>
			        <a href="list.ht" class="easyui-linkbutton" iconCls="icon-return" plain="true">返回</a>
			    </div>
			</div>
			<form id="userForm" action="save.ht" method="post">
				<div class="grid grid2">
					<table cellspacing="0">
						<tr class="">								
							<td class="name"><span>姓名:</span><span class="required">*</span></td>
							<td><input type="text" name="fullname" class="inputText" validate="{required:true}" value="${user.fullname}"></td>								
						</tr>
						<tr>
							<td class="name"><span>账号:</span><span class="required">*</span></td>
							<td><input type="text" name="account" class="inputText" validate="{required:true}" value="${user.account}"></td>
						</tr>
						<tr>
							<td class="name"><span>密码:</span><span class="required">*</span></td>
							<td><input type="password" name="password" class="inputText" validate="{required:true}" value="${user.password}"></td>
						</tr>
						<tr>
							<td class="name"><span>状态:</span><span class="required">*</span></td>
							<td>
								<select name="status" validate="{required:true}" class="inputText">
									<option value="actived"  >激活</option>
									<option value="inactived"  >未激活</option>
									<option value="locked"  >禁用</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="name"><span>性别:</span><span class="required">*</span></td>
							<td>
								<input type="radio" name="sex" value="1" validate="{required:true}">男
								<input type="radio" name="sex" value="0"validate="{required:true}">女
							</td>
						</tr>
						<tr>
							<td class="name"><span>地址:</span></td>
							<td><input type="text" name="address" class="inputText" value="${user.address}"></td>
						</tr>
						<tr>
							<td class="name"><span>联系电话:</span></td>
							<td><input type="text" name="phone" class="inputText" value="${user.phone}"></td>
						</tr>
						<tr>
							<td class="name"><span>手机:</span></td>
							<td><input type="text" name="mobile" class="inputText"/></td>
						</tr>
						<input type="hidden" name="userId" value="" value="${user.mobile}"/>
					</table>
				</div>
			</form>
		</div>
	</body>
</html>