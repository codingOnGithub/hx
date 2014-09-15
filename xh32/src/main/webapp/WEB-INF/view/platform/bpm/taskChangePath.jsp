<%
	//任务设置执行路径
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
			function saveTaskChangePath(){
				var deskTask=$('#destTask').val();
				if(deskTask==''){
					$.ligerDialog.confirm($lang_bpm.task.selExePath,$lang.tip.msg);
					return;
				}
				var params=$('#taskForm').serialize();
				$.post('${ctx}/platform/bpm/task/saveChangePath.ht',params,function(data){
					window.returnValue=1;	
					window.close();
				});
			}
			
			//更改
			function changeDestTask(sel){
				var nodeId=sel.value;
				if(typeof nodeId == 'undefined'){    //对象不是用原始JS的，而是通过Jquery获取的对象
					nodeId = sel.val();
				}
				if(typeof nodeId == 'undefined' || nodeId==null || nodeId==""){
					$('#jumpUserDiv').html("");
					$('#lastDestTaskId').val("");
					return;
				}				
				$('#lastDestTaskId').val(nodeId);
				var url="${ctx}/platform/bpm/task/getTaskUsers.ht?taskId=${taskEntity.id}&nodeId="+nodeId;
				$.getJSON(url, function(dataJson){
					var data=eval(dataJson);
					var aryHtml=[];
					for(var i=0;i<data.length;i++){
						var span="<input type='checkbox' name='" + nodeId + "_userId' checked='checked' value='"+data[i].executeId+"'/>&nbsp;"+data[i].executor;
						aryHtml.push(span);
					}
					$("#jumpUserDiv").html(aryHtml.join(''));
				});
			}
			
			//为目标节点选择执行的人员列表
			function selectExeUsers(nodeId){
				UserDialog({callback:function(uIds,uNames){
					if(uIds.isEmpty()) return;
					var aryHtml=[];
					var ids=uIds.split(',');
					var names=uNames.split(',');
					for(var i=0;i<ids.length;i++){
						aryHtml.push("<input type='checkbox' name='" + nodeId + "_userId' checked='checked' value='"+ids[i]+"'/>&nbsp;"+names[i]);
					}
					$("#jumpUserDiv").html(aryHtml.join(''));
				}});
			}
		</script>
	</head>
	<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
		   <div class="tbar-title">
		    	<span class="tbar-label"><spr:message code="task.changePath.title"/></span>
		   </div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="btnSearch" onclick="saveTaskChangePath()"><span></span><spr:message code="menu.button.save"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del" onclick="javasrcipt:window.close()"><span></span><spr:message code="menu.button.close"/></a></div>
			    </div>
			</div>
		</div>
		</div>
	 <div class="panel-body">
		<form id="taskForm">
			<div class="panel-detail">
				<table class="table-detail">
					<tr>
						<th nowrap="nowrap"><spr:message code="task.changePath.curTask"/>当前任务</th>
						<td>
							<input type="hidden" id="taskId"  name="taskId" value="${taskEntity.id}"/>
							${taskEntity.name}
						</td>
					</tr>
					<tr>
						<th nowrap="nowrap"><spr:message code="task.changePath.goalNode"/>目标节点</th>
						<td>
							<select name="destTask" id="destTask" onchange="changeDestTask(this)">
								<option value=""><spr:message code="task.changePath.selGoalNode"/></option>
								<c:forEach items="${taskNodeMap}" var="map" varStatus="i">
									<option value="${map.key}">${map.value}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th nowrap="nowrap">
							<spr:message code="task.changePath.nodeExeUsers"/>
						</th>
						<td>
							<input type="hidden" id="lastDestTaskId" name="lastDestTaskId" value="">
							<div id="jumpUserDiv"></div>
							<a href="#" id="jumpUserLink" class="link get" onclick="selectExeUsers('${nodeUserMap.nodeId}')">&nbsp;&nbsp;</a>
						</td>
					</tr>
					<tr>
						<th><spr:message code="task.changePath.changeMemo"/></th>
						<td>
							<textarea rows="5" cols="60" id="voteContent" name="voteContent" maxlength="512">${curUser.fullname}<spr:message code="task.changePath.changeExePath"/></textarea>
						</td>
					</tr>
					<tr>
						<th nowrap="nowrap"><spr:message code="task.changePath.inform"/></th>
						<td>
							 <c:forEach items="${handlersMap}" var="item">
								   <input type="checkbox" name="informType" value="${item.key }"  checked="checked" />
						            ${item.value.title }
							  </c:forEach>
						</td>
					</tr>
				</table>
				<input type="hidden" id="voteAgree" name="voteAgree" value="8"/> 
			</div>
		</form>
	 </div>
	</div>
	</body>
</html>