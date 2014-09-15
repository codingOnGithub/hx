<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
	<head>
		<%@include file="/commons/include/form.jsp" %>
		<title><spr:message code="title.add"/><spr:message code="scheduler"/></title>
		<f:js pre="js/lang/view/platform/system" ></f:js>
		<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerDialog.js"  ></script>
		<script type="text/javascript" src="${ctx }/js/lg/plugins/ligerWindow.js" ></script>
		<script type="text/javascript" src="${ctx }/js/hotent/platform/scheduler/JobDialog.js"></script>
		
		<script type="text/javascript">
		function addjob(){
			addRow();
		}
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			valid(showRequest,showResponse,1);
			$("a.save").click(function() {
				var str=setParameterXml();
				$("#parameterJson").val(str);
				$('#jobForm').submit(); 
			});
		});
		function showResponse(responseText, statusText)  { 
			var obj=new com.hotent.form.ResultMessage(responseText);
			if(obj.isSuccess()){//成功
				$.ligerDialog.confirm(obj.getMessage()+','+$lang.operateTip.continueOp,$lang.tip.confirm,function(rtn){
					if(!rtn){
						var returnUrl=$("a.back").attr("href");
						location.href=returnUrl;
					}
					else{
						valid.resetForm();
					}
				});
				
		    }else{//失败
		    	$.ligerDialog.err($lang.tip.error,$lang_system.scheduler.jobAdd.error_msg_addJob,obj.getMessage());
		    }
		} 
		var valid;
		function valid(showRequest,showResponse){

		var options={};

		if(showRequest )
			options.beforeSubmit=showRequest;

		if(showResponse )
		   options.success=showResponse;
		valid=$("#jobForm").validate({
			rules: {
				name:{required:true,maxlength:100},
				className:{required:true,maxlength:100}
			},
			messages: {
				name:{required:$lang_system.scheduler.jobAdd.validate_msg_jobName_require,maxlength:$lang_system.scheduler.jobAdd.validate_msg_jobName_max},
				className:{required:$lang_system.scheduler.jobAdd.validate_msg_jobClass_require,maxlength:$lang_system.scheduler.jobAdd.validate_msg_jobClass_max
				}
			},
			submitHandler:function(form){
				$(form).ajaxSubmit(options);
		    },
		    success: function(label) {
				label.html("&nbsp;").addClass("checked");
			}
			
			});
		}
		</script>
</head>
<body>
		<div class="panel">
			<div class="panel-top">
				<div class="tbar-title">
					<span class="tbar-label"><spr:message code="title.add"/><spr:message code="scheduler"/></span>
				</div>
				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group"><a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.save"/></a></div>
						<div class="l-bar-separator"></div>
						<div class="group"><a class="link back" href="getJobList.ht"><span></span><spr:message code="menu.button.back"/></a></div>
					</div>
				</div>
			</div>
			<form id="jobForm" method="post" action="addJob2.ht">
				<div class="panel-detail">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%"><spr:message code="scheduler.job.name"/>: </th>
							<td><input type="text" id="name" name="name" value=""  class="inputText" style="width:60%"/></td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="scheduler.job.class"/>: </th>
							<td>
								<input type="text" id="className" name="className" value=""  class="inputText" style="width:60%"/>
								<input type="button" value="<spr:message code="scheduler.jobAdd.validate.class.value"/>"  id="btnCheckClass" onclick="validClass()" title="验证任务类名是否正确" />
							    <input id="parameterJson" name="parameterJson" type="hidden" />
							</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="scheduler.job.desc"/>:</th>
							<td>
							<textarea id="description" name="description" class="inputText" rows="5" style="width:80%"></textarea>
							</td>
						</tr>
					</table>
				</div>
				
			</form>
			<div class="panel-detail">
				<div class="tbar-title">
					<span class="tbar-label"><spr:message code="scheduler.job.arg"/></span>
				</div>
				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group"><a id="btnAddParameter" onclick="addRow()" class="link add"><span></span><spr:message code="operator.add"/></a></div>
					</div>	
				</div>
			</div>
			<div class="panel-body">
				
					<table cellpadding="1" cellspacing="1"  class="table-grid">
						<tr>
						  <th align="center"><spr:message code="scheduler.job.arg.name"/></th>
						  <th align="center"><spr:message code="scheduler.job.arg.type"/></th>
						  <th align="center"><spr:message code="scheduler.job.arg.value"/></th>
						  <th align="center"><spr:message code="operator.del"/></th>
						</tr>
						<tbody id="trContainer">
					    </tbody>
					</table>
				
			</div>
		</div>	
     </body>
</html>