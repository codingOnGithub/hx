<%@page language="java" pageEncoding="UTF-8" import="com.hotent.platform.model.system.SysUser"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>

<%@include file="/commons/include/form.jsp" %>
<title><spr:message code="sysUser.editStatus.title"/></title>
<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=sysUser"></script>
<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 							
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
				<span class="tbar-label"><spr:message code="sysUser.editStatus.name" arguments="【${sysUser.fullname}】"/></span>
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
				<form id="sysUserForm" method="post" action="editStatus.ht">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%"><spr:message code="sysUser.editStatus.isAvailable"/>:</th>
							<td >
                            <select name="isLock" class="select" style="width:245px !important">
									<option value="<%=SysUser.UN_LOCKED %>" <c:if test="${sysUser.isLock==0}">selected</c:if> ><spr:message code="sys.user.unlock"/></option>
									<option value="<%=SysUser.LOCKED %>" <c:if test="${sysUser.isLock==1}">selected</c:if> ><spr:message code="sys.user.locked"/></option>
							</select>
                            </td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="sysUser.editStatus.curStatus"/>:</th>
							<td >
                             <select name="status"  class="select" style="width:245px !important">
									<option value="<%=SysUser.STATUS_OK %>" <c:if test="${sysUser.status==1}">selected</c:if> ><spr:message code="sys.user.active"/></option>
									<option value="<%=SysUser.STATUS_NO %>" <c:if test="${sysUser.status==0}">selected</c:if> ><spr:message code="sys.user.unactive"/></option>
									<option value="<%=SysUser.STATUS_Del %>" <c:if test="${sysUser.status==-1}">selected</c:if>><spr:message code="menu.button.del"/></option>
							 </select>
                            </td>
						</tr>
					</table>
					<input type="hidden" name="userId" value="${sysUser.userId}" />
				</form>
		</div>
</div>
</body>
</html>