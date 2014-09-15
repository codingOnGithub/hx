<%--
	time:2013-05-25 18:29:05
	desc:edit the 系统支持的语言
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<title><spr:message code="title.edit"/><spr:message code="sysLanguage"/></title>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#sysLanguageForm').form();
			$("a.save").click(function() {
				frm.setData();
				frm.ajaxForm(options);
				if(frm.valid()){
					form.submit();
				}
			});
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm( obj.getMessage()+$lang.operateTip.continueOp,$lang.tip.confirm, function(rtn) {
					if(!rtn){
						window.location.href = "${ctx}/platform/system/sysLanguage/list.ht";
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
			    <c:when test="${sysLanguage.id !=null}">
			        <span class="tbar-label"><spr:message code="title.edit"/><spr:message code="sysLanguage"/></span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><spr:message code="title.add"/><spr:message code="sysLanguage"/></span>
			    </c:otherwise>			   
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.save"/></a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span><spr:message code="menu.button.back"/></a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="sysLanguageForm" method="post" action="save.ht">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
				<tr>
					<th width="20%"><spr:message code="sysLanguage.language"/>: </th>
					<td><input type="text" id="language" name="language" value="${sysLanguage.language}"  class="inputText" validate="{required:false,maxlength:10}"  /></td>
				</tr>
				<tr>
					<th width="20%"><spr:message code="sysLanguage.isdefault"/>: </th>
					<td>
						<input type="radio" value="1" name="isdefault" <c:if test="${sysLanguage.isdefault==1}"> checked="checked"</c:if> ><spr:message code="yes"/>
						<input type="radio" value="0" name="isdefault" <c:if test="${sysLanguage.isdefault==0}"> checked="checked"</c:if>> <spr:message code="no"/>
						<c:if test="${sysLanguage.isdefault==-1}">
							<input type="radio" value="-1" name="isdefault" checked="checked"> <spr:message code="menu.button.disabled"/>
						</c:if>
					</td>
				</tr>
				<tr>
					<th width="20%"><spr:message code="sysLanguage.memo"/>: </th>
					<td><input type="text" id="memo" name="memo" value="${sysLanguage.memo}"  class="inputText longInputText" validate="{required:false,maxlength:100}"  /></td>
				</tr>
			</table>
			<input type="hidden" name="id" value="${sysLanguage.id}" />
		</form>
		
	</div>
</div>
</body>
</html>
