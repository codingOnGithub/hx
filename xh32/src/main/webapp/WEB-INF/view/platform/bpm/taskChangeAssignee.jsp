<%
	//任务设置执行人
%>
<%@page pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
	<head>
		<%@include file="/commons/include/get.jsp" %>
		<f:js pre="js/lang/view/platform/bpm" ></f:js>
		<title><spr:message code="task.changePath.title"/></title>
		<f:link href="tree/zTreeStyle.css"></f:link>
		<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
		<script type="text/javascript">
			//更改任务执行路径
			function saveTaskAssignee(){
				var userId=$("#userId:checked").val();
				if(!userId){
					$.ligerDialog.confirm($lang_bpm.task.selAssignee);
					return;
				}
				var voteContent=$("#voteContent").val();
				if(voteContent==''){
					$.ligerDialog.confirm($lang_bpm.task.chanAssigneeReason,$lang.tip.msg);
					return;
				}
				var params=$('#taskForm').serialize();
				$.post('${ctx}/platform/bpm/task/setAssignee.ht',params,function(data){
					var obj=new com.hotent.form.ResultMessage(data);
					if(obj.isSuccess()){
						$.ligerDialog.success(obj.getMessage(),$lang.tip.success);
						window.returnValue=1;	
						window.close();
					}else{
						$.ligerDialog.err($lang.tip.msg,$lang.tip.failure,obj.getMessage());
					}
				});
			}
			//为目标节点选择执行的人员列表
			function selectExeUsers(nodeId){
				UserDialog({isSingle:true,callback:function(uIds,uNames){
					if(uIds.isEmpty()) return;
					
					var html="<input type='checkbox' id='userId' name='userId' checked='checked' value='"+uIds+"'/>&nbsp;"+uNames;
					$("#jumpUserDiv").html(html);
				}});
			}
		</script>
	</head>
	<body>
	<div class="panel">
		<div class="panel-top">
		   <div class="tbar-title">
		    	<span class="tbar-label"><spr:message code="task.changeAssignee.title"/></span>
		   </div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="btnSearch" onclick="saveTaskAssignee()"><span></span><spr:message code="menu.button.save"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link close" onclick="javasrcipt:window.close()"><span></span><spr:message code="menu.button.close"/></a></div>
			    </div>
			</div>
		</div>
		<div class="panel-detail">
			<form id="taskForm">
					<table class="table-detail">
						<tr>
							<th nowrap="nowrap"><spr:message code="task.changePath.curTask"/></th>
							<td>
								<input type="hidden" id="taskId"  name="taskId" value="${taskEntity.id}"/>
								${taskEntity.name}
							</td>
						</tr>
						<tr>
							<th nowrap="nowrap">
								<spr:message code="task.assignee"/>
							</th>
							<td>
								<div id="jumpUserDiv"></div>
								<a href="#" id="jumpUserLink" class="link get" onclick="selectExeUsers('${nodeUserMap.nodeId}')">&nbsp;&nbsp;</a>
							</td>
						</tr>
						<tr>
							<th><spr:message code="task.changeAssignee.changeReason"/></th>
							<td>
								<textarea rows="5" cols="60" id="voteContent" name="voteContent" maxlength="512"></textarea>
							</td>
						</tr>
						<tr>
							<th nowrap="nowrap"><spr:message code="task.changePath.inform"/></th>
							<td>
							<%-- 
			                <input name="informType" type="checkbox" value="3" checked="checked"><spr:message code="message.inner"/> &nbsp;
			                <input name="informType" type="checkbox" value="1" checked="checked"><spr:message code="message.sms"/> &nbsp;
			                <input name="informType" type="checkbox" value="2" checked="checked"> <spr:message code="message.mail"/>
							--%>
								 <c:forEach items="${handlersMap}" var="item">
								   <input type="checkbox" name="informType" value="${item.key }"  checked="checked" />
						            ${item.value.title }
							     </c:forEach>	
							</td>
						</tr>
					</table>
					<input type="hidden" id="voteAgree" name="voteAgree" value="8"/> 
				</form>
			</div>
		</div>
	</body>
</html>