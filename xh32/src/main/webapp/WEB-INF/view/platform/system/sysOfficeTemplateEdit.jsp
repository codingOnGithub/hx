<%--
	time:2012-05-25 10:16:17
	desc:edit the office模版
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<f:js pre="js/lang/view/platform/system" ></f:js>
	<title><spr:message code="title.edit"/> <spr:message code="sysOfficeTemplate"/></title>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=sysOfficeTemplate"></script>
	<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			if(${sysOfficeTemplate.id ==null }){
				valid(showRequest,showResponse,1);
			}else{
				valid(showRequest,showResponse);
			}
			$("a.save").click(function() {
				var v=$("#file").val();
				if(v==''){
					$.ligerDialog.warn($lang_system.sysOfficeTemplate.upload_validate_msg);
				}else{
					$("#sysOfficeTemplateForm").submit();
				}
			});
		});
		
		function showResponse(responseText){
			var obj=new com.hotent.form.ResultMessage(responseText);
			if(obj.isSuccess()){
				$.ligerDialog.success(obj.getMessage(),$lang.tip.success,function(){
						window.location.href="list.ht";
				});
			}else{
				$.ligerDialog.err($lang.tip.error,$lang_system.sysOfficeTemplate.error_msg_save,obj.getMessage());
			}
		}
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
			    <c:choose>
			        <c:when test="${sysOfficeTemplate.id !=null }">
			            <span class="tbar-label"><spr:message code="title.edit"/> <spr:message code="sysOfficeTemplate"/></span>
			        </c:when>
			        <c:otherwise>
			            <span class="tbar-label"><spr:message code="title.add"/> <spr:message code="sysOfficeTemplate"/></span>
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
				
				<form id="sysOfficeTemplateForm" method="post" action="saveTemplate.ht">
				
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%"><spr:message code="sysOfficeTemplate.subject"/>: </th>
							<td><input type="text" id="subject" name="subject" value="${sysOfficeTemplate.subject}"  class="inputText"/></td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="sysOfficeTemplate.templateType"/>: </th>
							<td>
								<input type="radio"  name="templatetype" value="1" <c:if test="${sysOfficeTemplate.templatetype!=2}">checked="checked"</c:if> /><spr:message code="sysOfficeTemplate.templateType.common"/>
								<input type="radio"  name="templatetype" value="2" <c:if test="${sysOfficeTemplate.templatetype==2}">checked="checked"</c:if> /><spr:message code="sysOfficeTemplate.templateType.nest"/>
							</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="sysOfficeTemplate.path"/>: </th>
							<td><input type="file" id="file" name="file" style="width: 200px;" class="inputText" value="${sysOfficeTemplate.path}"/></td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="sysOfficeTemplate.memo"/>: </th>
							<td>
								<textarea rows="4" cols="40"id="memo" name="memo" value="${sysOfficeTemplate.memo}">${sysOfficeTemplate.memo}</textarea>
							</td>
						</tr>
					</table>
					<input type="hidden" id="officeTemplateId" name="id" value="${sysOfficeTemplate.id}" />
				</form>
				
		</div>
</div>
</body>
</html>
