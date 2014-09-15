<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<f:js pre="js/lang/view/platform/bpm" ></f:js>
	<title><spr:message code="task.taskDetail"/></title>
	
    <script type="text/javascript" src="${ctx}/js/lg/plugins/ligerMenu.js" ></script>
     <script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/TaskChangePathWindow.js" ></script>
    <script type="text/javascript">
            var taskId="${taskEntity.id}";
        	$(function(){
        		var menuItem={ width: 120, items:[
          					            { id:'changePath',taskId:taskId,text: $lang_bpm.task.detail.menuItem.changePath, click: itemclick},
          					            { id:'completeTask', taskId:taskId,text: $lang_bpm.task.detail.menuItem.completeTask, click: itemclick},
          					            { id:'approvalDetail',taskId:taskId, text: $lang_bpm.task.detail.menuItem.approvalDetail, click: itemclick},
          					            { id:'businessForm',taskId:taskId, text: $lang_bpm.task.detail.menuItem.businessForm, click: itemclick}
          					           ]
          					        };
        		//加上菜单
        		$("div[name='taskMenu']").ligerMenuBar({ items: [
                				{ text: '<a href="#">'+$lang_bpm.task.detail.taskOperation+'</a>', menu: menuItem}]
        		});
        		
        		$("div[name='taskMenu']").removeClass('l-menubar');
        	});
        	function itemclick(item){
        	    if(item.id=='changePath'){//更改执行路径
        	    	TaskChangePathWindow({taskId:taskId,callback:function(rtn){
        	    		if(rtn==1){
        	    			if(window.opener){
        						try{
        							window.opener.location.href=__ctx + "/platform/bpm/task/list.ht";
        						}
        						catch(e){}
        					}	
        					window.close();
        	    		}
        	    	}});
        	    }else if(item.id=='completeTask'){//结束任务
        	    	$.ligerDialog.confirm($lang_bpm.task.detail.endTask,$lang.tip.msg,function(rtn){
        	    		if(!rtn) return;
        	    		url=__ctx + "/platform/bpm/task/end.ht?taskId="+ item.taskId;
           				$.post(url,function(responseText){
           					var obj=new com.hotent.form.ResultMessage(responseText);
           					if(obj.isSuccess()){//成功
           						if(window.opener){
            						try{
            							window.opener.location.href=__ctx + "/platform/bpm/task/list.ht";
            						}
            						catch(e){}
            					}	
           						$.ligerDialog.success(obj.getMessage(),$lang.tip.msg,function(){
           							window.close();
           						});
           						
           					}
           					else{
           						$.ligerDialog.err($lang.tip.msg,"更改执行路径失败",obj.getMessage());
           					}
           				});
        	    	});	
        	    }else if(item.id=='approvalDetail'){
        	    	var winArgs="dialogWidth=780px;dialogHeight=600px;help=0;status=0;scroll=1;center=1";
        	    	var url=__ctx + '/platform/bpm/taskOpinion/list.ht?taskId='+item.taskId;
        	    	url=url.getNewUrl();
        			window.showModalDialog(url,"",winArgs);
        	    }else if(item.id=='taskComment'){// 任务评论
        	    	TaskCommentWindow({taskId:item.taskId});
        	    }
        	    else if(item.id=="businessForm"){
        	    	var url=__ctx+ '/platform/bpm/task/getForm.ht?taskId='+item.taskId;
        	    	jQuery.openFullWindow(url);
        	    }
        	}
           
            
     </script> 
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="task.taskDetail"/>--${processRun.subject}--${taskEntity.name}</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link close" href="javascript:window.close();"><span></span><spr:message code="menu.button.close"/></a></div>
				</div>
			</div>
		</div>
		<f:tab curTab="detail" tabName="task"/>
		<div class="panel-body">
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><div name="taskMenu"></div></div>
			</div>
		</div>
		<table class="table-detail" style="width:100%">
			<tr>
				<th nowrap="nowrap" width="100"><spr:message code="task.subject"/></th>
				<td width="*">
					${processRun.subject}
				</td>
			</tr>
			<tr>
				<th><spr:message code="task.taskId"/></th>
				<td>${taskEntity.id}</td>
			</tr>
			<tr>
				<th nowrap="nowrap">
					<spr:message code="task.runId"/>
				</th>
				<td>${processRun.runId}</td>
			</tr>
			<tr>
				<th nowrap="nowrap"><spr:message code="task.name"/></th>
				<td>${taskEntity.name}</td>
			</tr>
			<tr>
				<th><spr:message code="task.description"/></th>
				<td>
					<c:choose>
						<c:when test="${not empty taskEntity.description}">
							${taskEntity.description}
						</c:when>
						<c:otherwise>${taskEntity.name}</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<th nowrap="nowrap"><spr:message code="task.owner"/></th>
				<td>
				 <span><f:userName userId="${taskEntity.owner}"/></span>&nbsp; <c:if test="${not empty param['manage']}"><a href='#' class='button' onclick="setTaskOwner(this,${taskEntity.id})"><span><spr:message code="menu.button.change"/>..</span></c:if> </a>
				</td>
			</tr>
			<tr>
				<th nowrap="nowrap"><spr:message code="task.assignee"/></th>
				<td>
					<span><f:userName userId="${taskEntity.assignee}"/></span>&nbsp;<c:if test="${not empty param['manage']}"><a href='#' class='button' onclick="setTaskAssignee(this,${taskEntity.id})"><span><spr:message code="menu.button.change"/>..</span></c:if></a>
				</td>
			</tr>
			<tr>
				<th nowrap="nowrap"><spr:message code="task.executor"/></th>
				<td>
				
					<c:choose>
						<c:when test="${fn:length(candidateUsers)==0}">
							<font color='red'><spr:message code="tas.tempNull"/></font>
						</c:when>
						<c:otherwise>
							<c:forEach items="${candidateUsers}" var="executor">
								
								<c:if test="${not empty executor }">
								<c:set var="type" >
									<c:choose>
										<c:when test="${executor.type == 'org' }">(<spr:message code="task.org"/>)</c:when>
										<c:when test="${executor.type == 'role' }">(<spr:message code="task.role"/>)</c:when>
										<c:when test="${executor.type == 'pos' }">(<spr:message code="task.pos"/>)</c:when>
										<c:otherwise>(<spr:message code="task.user"/>)</c:otherwise>
									</c:choose>
								</c:set>
								<img src='${ctx}/styles/default/images/bpm/user-16.png'>${executor.executor}${type }
								</c:if>
							</c:forEach>		
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<th><spr:message code="task.curTask"/></th>
				<td>
					<c:forEach items="${curTaskList}" var="curTask" varStatus="i"><c:if test="${i.count>1}">,</c:if>${curTask.name}(<f:userName userId="${curTask.assignee}"/>)</c:forEach>
				</td>
			</tr>
			<tr>
				<th nowrap="nowrap"><spr:message code="task.createDate"/></th>
				<td>
					<fmt:formatDate value="${taskEntity.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
			<tr>
				<th nowrap="nowrap"><spr:message code="task.dueDate"/></th>
				<td>
					<fmt:formatDate value="${taskEntity.dueDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		</table>
		<br/>
		<table class="table-detail" style="width:100%">
			<tr>
				<th nowrap="nowrap" width="100"><spr:message code="task.flowName"/></th>
				<td>${processDefinition.subject}</td>
			</tr>
			<tr>
				<th><spr:message code="task.versionNo"/></th>
				<td>
					${processDefinition.versionNo}
				</td>
			</tr>
			<tr>
				<th nowrap="nowrap"><spr:message code="task.processDescp"/></th>
				<td>
					${processDefinition.descp}
				</td>
			</tr>
			
		</table>
	</div>
</div>
</body>
</html>
