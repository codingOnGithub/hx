<%--
	time:2013-11-27 10:19:23
	desc:edit the 系统岗位表，实际是部门和职务的对应关系表
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	
	<%@include file="/commons/include/form.jsp" %>
	<title><spr:message code="menu.button.edit"/><spr:message code="position.title.relation"/> </title>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript">
		$(function() {
			var url="${ctx}/platform/system/position/list.ht?orgId="+"${orgId}";
			$(".link.back").attr("href",url);
			
			$("a.save").click(function() {
				$("#positionForm").attr("action","save.ht");
				submitForm();
			});
			
			 $("#jobId").change(function(){
               // sVal=$(this).val();
               //获取下拉框选中的文本
             var sText= $(this) .find("option:selected").text();
				 $("#posName").val("${sysOrg.orgName}"+"_"+sText);
				 });
			 
		});
		//提交表单
		function submitForm(){
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#positionForm').form();
			frm.ajaxForm(options);
			if(frm.valid()){
				frm.submit();
			}
		}
		
		function showResponse(responseText) {
			var url="${ctx}/platform/system/position/list.ht?orgId="+"${orgId}";
			var self=this;
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+","+$lang_system.sysUser.isContinue,$lang.tip.msg, function(rtn) {
				if(rtn){
					     window.location.reload(true);
				      }else{
				    	  window.location.href=url
				    	 // window.location.href="${returnUrl}";
				    	 //window.location.href="list.ht";//返回返回按钮的链接
				    	 // parent.$("#viewFrame").attr("src","${returnUrl}");
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
			    <c:when test="${position.posId !=null}">
			        <span class="tbar-label"><spr:message code="menu.button.edit"/><spr:message code="position.title.relation"/></span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><spr:message code="menu.button.add"/><spr:message code="position.title.relation"/></span>
			    </c:otherwise>			   
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.save"/></a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="${returnUrl}"><span></span><spr:message code="menu.button.back"/></a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="positionForm" method="post" action="save.ht">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
			
				<tr>
					<th width="20%"><spr:message code="sysOrg.orgName"/>: </th>
					<td><input type="hidden" id="orgId" name="orgId" value="${sysOrg.orgId}"/>
					${sysOrg.orgName} 
					</td>
				</tr>
				<tr>
					<th width="20%"><spr:message code="positionJob.name"/>: </th>
					<td><select id="jobId" name="jobId" class="select">  
									<c:forEach items="${jobList}" var="job" >							 				
						 					 <option value ="${job.jobid}" <c:if test="${position.jobId==job.jobid}">selected="selected"</c:if> >${job.jobname}</option>												
									</c:forEach> 
	                    </select>
	                 </td>
				</tr>
				<%--
				<tr>
					<th width="20%">JOBID: </th>
					<td><input type="text" id="jobid" name="jobid" value="${position.jobId}" validate="{required:false,number:true,maxIntLen:18 }" class="inputText"/></td>
				</tr>
				 --%>
				<tr>
					<th width="20%"><spr:message code="position.posName"/>:  <span class="required">*</span></th>
					<td><input type="text" id="posName" name="posName" value="${position.posName}" validate="{required:true,maxlength:128}" class="inputText" style="width:255px !important"/></td>
				</tr>
				<tr>
					<th width="20%"><spr:message code="position.posDesc"/>: </th>
					<td><textarea id="posDesc" name="posDesc" cols="30" rows="4"  style="width:258px !important">${position.posDesc}</textarea></td>
				</tr>
				<%--
				<tr>
					<th width="20%">ISDELETE: </th>
					<td><input type="text" id="isdelete" name="isdelete" value="${position.isDelete}" validate="{required:false,number:true }" class="inputText"/></td>
				</tr>
				 --%>
			</table>
			<input type="hidden" name="posId" value="${position.posId}" />					
		</form>
	</div>
</div>
</body>
</html>
