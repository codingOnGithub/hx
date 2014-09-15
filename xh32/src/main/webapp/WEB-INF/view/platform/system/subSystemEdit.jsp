<%--
	time:2011-11-21 12:22:07
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp"%>
<title><spr:message code="title.edit"/><spr:message code="subSystem"/></title>
<script type="text/javascript"
	src="${ctx}/servlet/ValidJs?form=subSystem"></script>
<script type="text/javascript"
	src="${ctx }/js/lg/plugins/ligerWindow.js"></script>
<script type="text/javascript"
	src="${ctx }/js/hotent/platform/system/IconDialog.js"></script>
<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			if(${subSystem.systemId==0}){
				valid(showRequest,showResponse,1);
			}else{
				valid(showRequest,showResponse);
			}
			$("a.save").click(function() {
				$('#subSystemForm').submit(); 
				
			});
		});

		function selectIcon(){
			IconDialog({callback:function(src){
				$("#logo").val(src);
				$("#logoImg").attr("src",src);
				$("#logoImg").show();
			},params:"iconType=1"})
		};
	</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"> <c:choose>
						<c:when test="${subSystem.systemId==0}">
					<spr:message code="title.add"/><spr:message code="subSystem"/>
					</c:when>
						<c:otherwise>
					<spr:message code="title.edit"/><spr:message code="subSystem"/>
					</c:otherwise>
					</c:choose>
				</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.save"/></a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link back" href="../subSystem/list.ht"><span></span><spr:message code="menu.button.back"/></a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form id="subSystemForm" method="post" action="save.ht">

				<table class="table-detail" cellpadding="0" cellspacing="0"
					border="0">
					<tr>
						<th width="20%"><spr:message code="subSystem.sysName"/>: <span class="required">*</span></th>
						<td><input type="text" id="sysName" name="sysName"
							value="${subSystem.sysName}" class="inputText" /></td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="subSystem.alias"/>:</th>
						<td><input type="text" id="alias" name="alias"
							value="${subSystem.alias}" class="inputText" /></td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="subSystem.logo"/>:</th>
						<td><input type="hidden" id="logo" name="logo"
							value="${subSystem.logo}" class="inputText" /> <img id="logoImg"
							alt="" src="${ctx}${subSystem.logo}"> <a
							class="link detail" href="javascript:selectIcon();"><spr:message code="subSystem.edit.select"/></a></td>
					</tr>

					<tr>
						<th width="20%"><spr:message code="subSystem.defaultUrl"/>:</th>
						<td><input type="text" id="defaultUrl" name="defaultUrl"
							value="${subSystem.defaultUrl}" size="40" class="url_bpmForm inputText" /></td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="subSystem.homePage"/>:</th>
						<td><input type="text" id="homePage" name="homePage"
							value="${subSystem.homePage}" size="40" class="url_bpmForm inputText" /></td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="subSystem.allowDel"/>:</th>
						<td><input type="radio" value="1" name="allowDel"
							<c:if test="${subSystem.allowDel eq 1}">checked="checked"</c:if>><spr:message code="subSystem.allowDel.allow"/>
							<input type="radio" value="0" name="allowDel"
							<c:if test="${subSystem.allowDel eq 0}">checked="checked"</c:if>><spr:message code="subSystem.allowDel.forbid"/>

						</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="subSystem.isActive"/>:</th>
						<td><input type="radio" value="1" name="isActive"
							<c:if test="${subSystem.isActive eq 1}">checked="checked"</c:if>><spr:message code="yes"/>
							<input type="radio" value="0" name="isActive"
							<c:if test="${subSystem.isActive eq 0}">checked="checked"</c:if>><spr:message code="no"/>
						</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="subSystem.isLocal"/>:</th>
						<td><input type="radio" value="1" name="isLocal"
							<c:if test="${subSystem.isLocal eq 1}">checked="checked"</c:if>><spr:message code="yes"/>
							<input type="radio" value="0" name="isLocal"
							<c:if test="${subSystem.isLocal eq 0}">checked="checked"</c:if>><spr:message code="no"/>

						</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="subSystem.memo"/>:</th>
						<td><textarea id="memo" name="memo" rows="5" cols="40"
								class="textarea">${subSystem.memo}</textarea></td>
					</tr>
				</table>

				<input type="hidden" name="systemId" value="${subSystem.systemId}" />
			</form>
		</div>
	</div>
</body>
</html>

