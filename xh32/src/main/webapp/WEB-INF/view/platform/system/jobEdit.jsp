<%--
	time:2013-11-28 16:17:48
	desc:edit the 职务表
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	
	<%@include file="/commons/include/form.jsp" %>
	<title><spr:message code="menu.button.edit"/><spr:message code="positionJob.selector.list"/></title>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript">
		$(function() {
			$("a.save").click(function() {
				$("#jobForm").attr("action","save.ht");
				submitForm();
			});
		});
		//提交表单
		function submitForm(){
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#jobForm').form();
			frm.ajaxForm(options);
			if(frm.valid()){
				frm.submit();
			}
		}
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.success(obj.getMessage(),$lang.tip.msg, function(rtn) {
					if(rtn){
						if(window.opener){
							window.opener.location.reload();
							window.close();
						}else{
							this.close();
							window.location.href="list.ht";
						}
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),$lang.tip.msg);
			}
		}
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${job.jobid !=null}">
			        <span class="tbar-label"><spr:message code="menu.button.edit"/><spr:message code="positionJob.selector.list"/></span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><spr:message code="menu.button.add"/><spr:message code="positionJob.selector.list"/></span>
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
		<form id="jobForm" method="post" action="save.ht">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
				<tr>
					<th width="20%"><spr:message code="positionJob.name"/>: <span class="required">*</span></th>
					<td><input type="text" id="jobname" name="jobname" value="${job.jobname}" validate="{required:true,maxlength:100}" class="inputText"/></td>
				</tr>
				<tr>
					<th width="20%"><spr:message code="positionJob.jobcode"/>: </th>
					<td><input type="text" id="jobcode" name="jobcode" value="${job.jobcode}" validate="{required:false,maxlength:100}" class="inputText"/></td>
				</tr>
				<tr>
					<th width="20%"><spr:message code="positionJob.desc"/>: </th>
					<td><input type="text" id="jobdesc" name="jobdesc" value="${job.jobdesc}" validate="{required:false}" class="inputText"/></td>
				</tr>
				<%-- 
				<tr>
					<th width="20%"><spr:message code="positionJob.setid"/>: </th>
					<td><input type="text" id="setid" name="setid" value="${job.setid}" validate="{required:false,number:true,maxIntLen:18 }" class="inputText"/></td>
				</tr>
				<tr>
					<th width="20%"><spr:message code="positionJob.isDeleted"/>: </th>
					<td><input type="text" id="isdelete" name="isdelete" value="${job.isdelete}" validate="{required:false,number:true }" class="inputText"/></td>
				</tr>
				--%>
			</table>
			<input type="hidden" name="jobid" value="${job.jobid}" />					
		</form>
	</div>
</div>
</body>
</html>
