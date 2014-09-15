<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
	<head>
		<%@include file="/commons/include/form.jsp" %>
		<title><spr:message code="title.add"/><spr:message code="scheduler.job"/></title>
		<f:js pre="js/lang/view/platform/system" ></f:js>
		<script type="text/javascript" src="${ctx }/js/lg/plugins/ligerWindow.js" ></script>
		<script type="text/javascript" src="${ctx }/js/hotent/platform/scheduler/JobDialog.js"></script>
		<script type="text/javascript" src="${ctx }/js/hotent/platform/scheduler/Trigger.js"></script>
		<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerDialog.js"></script>
 		<script  type="text/javascript">
 		$(function() {
 			
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			valid(showRequest,showResponse);
			$("a.save").click(function() {
				var str=getPlan();
				$("#planJson").val(str);
				$('#dataForm').submit(); 
			});
			$('[name="chkMon"]').click(function(){
				var id = $(this).attr('id');
				var checkedCount = $(this).closest('td').find('[name="chkMon"]:checked').length;
				if(checkedCount<1){
					//没有选中，则全部恢复可选状态
					$(this).siblings('[name="chkMon"]').removeAttr('disabled');
				}else if(id=='lastDay'){
					//选中了最后一天，则其他天数不可选
					$(this).siblings('[name="chkMon"]').attr('disabled','disabled');
				}else{
					//选中了其他天数，则最后一天不可选
					$(this).siblings('#lastDay').attr('disabled','disabled');
				}
			})
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
		    	$.ligerDialog.err($lang.tip.error,$lang_system.scheduler.triggerAdd.error_msg_add,obj.getMessage());
		    }
		} 
		var valid;
		function valid(showRequest,showResponse){
		var options={};
		if(showRequest )
			options.beforeSubmit=showRequest;
		if(showResponse )
		   options.success=showResponse;
		valid=$("#dataForm").validate({
			rules: {
				name:{required:true,maxlength:128}
			},
			messages: {
				name:{required:$lang_system.scheduler.triggerAdd.validate_msg_triggerName_require,maxlength:$lang_system.scheduler.triggerAdd.validate_msg_triggerName_max
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
				<span class="tbar-label"><spr:message code="title.add"/><spr:message code="scheduler.job"/>:${jobName}</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.save"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="getTriggersByJob.ht?jobName=${jobName}"><span></span><spr:message code="menu.button.back"/></a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
				<form id="dataForm" method="post" action="addTrigger2.ht">
					
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
						    
							<th width="20%"><spr:message code="scheduler.triggerAdd.name"/>: </th>
							<td>
							<input type="text" id="name" name="name" class="inputText" size="40"/>
							<input id="planJson" name="planJson" type="hidden" />	
						    <input id="jobName" name="jobName" type="hidden" value="${jobName}" />
							</td>
						</tr>
						<tr>
							<th colspan="2"  style="text-align: left;padding-left: 5px;">
								<spr:message code="scheduler.triggerAdd.runWay"/>           
							</th>
						</tr>
						<tr>
							<th width="20%"><input type="radio" value="1"  name="rdoTimeType" /><spr:message code="scheduler.triggerAdd.runWay.single"/>: </th>
							<td>
							<spr:message code="scheduler.triggerAdd.runWay.single.start"/>:<input type="text" id="txtOnceDate" class="Wdate" size="10" onclick="WdatePicker({minDate:'%y-%M-{%d}'})" />
							<select id="txtOnceHour">
								<c:forEach begin="0" end="23" step="1" var="tmp">
											<option value="${tmp }">${tmp }<spr:message code="scheduler.triggerAdd.runWay.single.hour"/></option>
								</c:forEach>
							</select>
							<select id="txtOnceMinute">
								<c:forEach begin="0" end="55" step="5" var="tmp">
										<option value="${tmp }">${tmp }<spr:message code="scheduler.triggerAdd.runWay.single.minute"/></option>
									</c:forEach>
								<option value="59">59<spr:message code="scheduler.triggerAdd.runWay.single.minute"/></option>
							</select>
							<select id="txtOnceSecond">
								<c:forEach begin="0" end="55" step="5" var="tmp">
										<option value="${tmp }">${tmp }<spr:message code="scheduler.triggerAdd.runWay.single.minute"/></option>
								</c:forEach>
								
								<option value="59">59<spr:message code="scheduler.triggerAdd.runWay.single.second"/></option>
							</select>
						</td>
						</tr>
						<tr>
							<th width="20%"><input type="radio" checked="checked" value="2" name="rdoTimeType" /><spr:message code="scheduler.triggerAdd.runWay.everyday"/>  </th>
							<td>
							<select id="selEveryDay">
							<option value="1">1<spr:message code="scheduler.triggerAdd.runWay.everyday.minute"/></option>
	               			<option value="5">5<spr:message code="scheduler.triggerAdd.runWay.everyday.minute"/></option>
	               			<option value="10">10<spr:message code="scheduler.triggerAdd.runWay.everyday.minute"/></option>
	               			<option value="15">15<spr:message code="scheduler.triggerAdd.runWay.everyday.minute"/></option>
	               			<option value="30">30<spr:message code="scheduler.triggerAdd.runWay.everyday.minute"/></option>
	               			<option value="60">1<spr:message code="scheduler.triggerAdd.runWay.everyday.hour"/></option>
	               		</select>
							</td>
						</tr>
						<tr>
							<th width="20%"><input type="radio"  value="3" name="rdoTimeType" /><spr:message code="scheduler.triggerAdd.runWay.everyday"/>  </th>
							<td>
								
								<select id="txtDayHour">
									<c:forEach begin="0" end="23" step="1" var="tmp">
										<option value="${tmp }">${tmp }<spr:message code="scheduler.triggerAdd.runWay.hour"/></option>
									</c:forEach>
								</select>
						
								<select id="txtDayMinute">
									<c:forEach begin="0" end="55" step="5" var="tmp">
												<option value="${tmp }">${tmp }<spr:message code="scheduler.triggerAdd.runWay.minute"/></option>
									</c:forEach>
									<option value="59">59<spr:message code="scheduler.triggerAdd.runWay.minute"/></option>
								</select>
							</td>
						</tr>
						<tr>
							<th width="20%"><input type="radio" value="4" name="rdoTimeType" /><spr:message code="scheduler.triggerAdd.runWay.weekly"/> </th>
							<td>
							     <input type="checkbox" name="chkWeek" value="MON"/><spr:message code="scheduler.triggerAdd.runWay.weekly.mon"/>
			               		 <input type="checkbox" name="chkWeek" value="TUE"/><spr:message code="scheduler.triggerAdd.runWay.weekly.tue"/>
			               		 <input type="checkbox" name="chkWeek" value="WED"/><spr:message code="scheduler.triggerAdd.runWay.weekly.wed"/>
			               		 <input type="checkbox" name="chkWeek" value="THU"/><spr:message code="scheduler.triggerAdd.runWay.weekly.thu"/>
			               		 <input type="checkbox" name="chkWeek" value="FRI"/><spr:message code="scheduler.triggerAdd.runWay.weekly.fri"/>
			               		 <input type="checkbox" name="chkWeek" value="SAT"/><spr:message code="scheduler.triggerAdd.runWay.weekly.sta"/>
			               		 <input type="checkbox" name="chkWeek" value="SUN"/><spr:message code="scheduler.triggerAdd.runWay.weekly.sun"/>	<br/>
	               				 <select id="txtWeekHour">
			               		 	<c:forEach begin="0" end="23" step="1" var="tmp">
										<option value="${tmp }">${tmp }<spr:message code="scheduler.triggerAdd.runWay.hour"/></option>
									</c:forEach>
								</select>
						
						
								<select id="txtWeekMinute">
									<c:forEach begin="0" end="55" step="5" var="tmp">
										<option value="${tmp }">${tmp }<spr:message code="scheduler.triggerAdd.runWay.minute"/></option>
									</c:forEach>
									<option value="59">59<spr:message code="scheduler.triggerAdd.runWay.minute"/></option>
								</select>
	               		 
							</td>
						</tr>
						<tr>
							<th width="20%"><input type="radio" value="5" name="rdoTimeType" /><spr:message code="scheduler.triggerAdd.runWay.monthly"/></th>
							<td>
								<c:forEach begin="1" end="31" var="mon">
									 <input type="checkbox" name="chkMon" value="${mon}"/>${mon}
								</c:forEach>
								     
		               			 <input type="checkbox" name="chkMon" value="L" id="lastDay"/><spr:message code="scheduler.triggerAdd.runWay.monthly.lastday"/><br/>
	               		 
			               		 <select id="txtMonHour">
			               		 	<c:forEach begin="0" end="23" step="1" var="tmp">
										<option value="${tmp }">${tmp }<spr:message code="scheduler.triggerAdd.runWay.hour"/></option>
									</c:forEach>
								</select>
								<select id="txtMonMinute">
									<c:forEach begin="0" end="55" step="5" var="tmp">
										<option value="${tmp }">${tmp }<spr:message code="scheduler.triggerAdd.runWay.minute"/></option>
									</c:forEach>
									<option value="59">59<spr:message code="scheduler.triggerAdd.runWay.minute"/></option>
								</select>
							</td>
						</tr>
						<tr>
						<th width="20%"><input type="radio" value="6" name="rdoTimeType" /><spr:message code="scheduler.triggerAdd.runWay.monthly.cron"/>:</th>
						<td><input type="text" id="txtCronExpression" name="txtCronExpression" /></td>
						</tr>
					</table>
					
				</form>
		</div>
</div>
	</body>
</html>