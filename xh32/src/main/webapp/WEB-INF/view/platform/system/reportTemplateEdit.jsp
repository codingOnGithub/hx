<%--
	time:2012-04-12 09:59:47
	desc:edit the 报表模板
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<title><spr:message code="reportTemplate.edit.title" /></title>
	<f:js pre="js/lang/view/platform/system" ></f:js>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=reportTemplate"></script>
	<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			}
			if(${reportTemplate.reportId ==null }){
				valid(showRequest,showResponse,1);
			}else{
				valid(showRequest,showResponse);
			}
			
			$("a.save").click(function() {
				var path = $("#file").val();
				var extName = path.substring(path.length-3,path.length);
				if(path==''){
					$.ligerDialog.warn($lang_system.reportTemplate.upload_validate_msg);
				}else if(extName!='cpt'){
					$.ligerDialog.warn($lang_system.reportTemplate.file_validate_msg);
				}else{
					$("#reportTemplateForm").submit();
				}
			});
			
			function showResponse(responseText){
				var obj=new com.hotent.form.ResultMessage(responseText);
				if(obj.isSuccess()){
					$.ligerDialog.success(obj.getMessage(),$lang.tip.msg,function(){
						window.location.href="list.ht";
					});
				}else{
					$.ligerDialog.err($lang.tip.error,$lang_system.reportTemplate.fail_msg_save,obj.getMessage());
				}
			}
			
		});

	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
		        <c:when test="${reportTemplate.reportId !=null }">
		            <span class="tbar-label"><spr:message code="reportTemplate.edit.span.edit" /></span>
		        </c:when>
		        <c:otherwise>
		            <span class="tbar-label"><spr:message code="reportTemplate.edit.span.add" /></span>
		        </c:otherwise>
		    </c:choose>				
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.save" /></a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span><spr:message code="menu.button.back" /></a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="reportTemplateForm" method="post" action="save.ht" enctype="multipart/form-data">
		
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th width="20%"><spr:message code="reportTemplate.title" />:  <span class="required">*</span></th>
						<td>
							<input type="text" id="title" name="title" 
								value="${reportTemplate.title}" class="inputText"/>
						</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="reportTemplate.descp" />:  <span class="required">*</span></th>
						<td>
							<textarea rows="5" cols="60" id="descp" name="descp"
								class="textarea">${reportTemplate.descp}</textarea>
						</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="reportTemplate.reportLocation" />:  <span class="required">*</span></th>
						<td>
							<input id="file" name="file" style="width: 200px;" class="inputText" type="file" 
								value="${reportTemplate.reportLocation}" />
						</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="reportTemplate.reportKey" />: </th>
						<td>
							<input type="text" id="reportKey" name="reportKey" 
								value="${reportTemplate.reportKey}" class="inputText"/>
						</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="reportTemplate.isDefaultIn" />: </th>
						<td>
							<select id="isDefaultIn" name="isDefaultIn">
								<c:if test="${reportTemplate.isDefaultIn==1||reportTemplate.isDefaultIn==null}">
									<option value="1" selected="selected"><spr:message code="reportTemplate.isDefault" /></option>
									<option value="0"><spr:message code="reportTemplate.isNotDefault" /></option>
								</c:if>
								<c:if test="${reportTemplate.isDefaultIn==0}">
									<option value="1"><spr:message code="reportTemplate.isDefault" /></option>
									<option value="0" selected="selected"><spr:message code="reportTemplate.isNotDefault" /></option>
								</c:if>
							</select>
						</td>
					</tr>
					
					<tr>
						<th width="20%"><spr:message code="reportTemplate.type" />: </th>
						<td>
							<select id="typeId" name="typeId">
								<c:forEach var="type" items="${typelist}">
									<c:if test="${type.parentId!=0}">
										<c:if test="${reportTemplate.typeId==type.typeId}">
											<option selected="selected" value="${type.typeId}">${type.typeName}</option>
										</c:if>
										<c:if test="${reportTemplate.typeId!=type.typeId}">
											<option value="${type.typeId}">${type.typeName}</option>
										</c:if>
									</c:if>
								</c:forEach>
							</select>
						</td>
					</tr>
					
				</table>
		
			<input type="hidden" name="reportId" value="${reportTemplate.reportId}" />
			<input type="hidden" name="tmpCreateTime" value="${reportTemplate.createTime}">
			
		</form>
	</div>
</div>
</body>
</html>
