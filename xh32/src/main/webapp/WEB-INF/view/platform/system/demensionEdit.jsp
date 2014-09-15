<%--
	time:2011-12-01 14:23:26
	desc:edit the SYS_DEMENSION
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>

	<%@include file="/commons/include/form.jsp" %>
		<title><spr:message code="menu.button.edit"/><spr:message code="sysUser.dialog.dimension"/></title>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=demension"></script>
	<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
						
			if(${demension.demId ==null}){
				valid(showRequest,showResponse,1);
			}else{
				valid(showRequest,showResponse);
			}
			$("a.save").click(function() {
				$('#demensionForm').submit(); 
			});
		});
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
			    <c:choose>
			        <c:when test="${demension.demId !=null}">
			            <span class="tbar-label"><spr:message code="menu.button.edit"/><spr:message code="sysUser.dialog.dimension"/></span>
			        </c:when>
			        <c:otherwise>
			            <span class="tbar-label"><spr:message code="menu.button.add"/><spr:message code="sysUser.dialog.dimension"/></span>
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
				<form id="demensionForm" method="post" action="save.ht">
					
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="25%"><spr:message code="demension.demName"/>:</th>
							<td><input type="text" id="demName" name="demName" value="${demension.demName}"  class="inputText" style="width:220px !important" /></td>
						</tr>
						<tr height="40px">
							<th width="20%"><spr:message code="demension.demDesc"/>:</th>
							<td>
								<textarea id="demDesc" name="demDesc" rows="3" cols="50">${demension.demDesc}</textarea>
							</td>
						</tr>
					</table>
					
					<input type="hidden" name="demId" value="${demension.demId}" />
				</form>
		</div>
</div>
</body>
</html>
