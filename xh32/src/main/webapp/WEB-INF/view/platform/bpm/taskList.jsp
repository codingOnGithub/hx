<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<f:js pre="js/lang/view/platform/bpm" ></f:js>
<title><spr:message code="task.list.title"/></title>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerLayout.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerMenu.js" ></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerMenuBar.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/TaskCommentWindow.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/TaskDetailWindow.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.qtip.js" ></script>
<link href="${ctx}/styles/default/css/jquery.qtip.css" rel="stylesheet" />
<script type="text/javascript" src="${ctx}/js/util/easyTemplate.js"></script>
<script type="text/javascript">
	//为某个任务分配人员
	function assignTask(){
		var taskIds=new Array();
		$("input[name='id']:checked").each(function(){
			taskIds.push($(this).val());
		});
		if(taskIds.length==0){
			$.ligerDialog.warn($lang_bpm.task.notSelectTask,$lang.tip.msg);
			return;
		}
		UserDialog({
			isSingle:true,
			callback:function(userId,fullname){
				if(!userId) return;
				var url="${ctx}/platform/bpm/task/assign.ht";
				var params= {taskIds:taskIds.join(','),userId:userId};
				$.post(url,params,function(data){
					document.location.reload();
				});
				
			}
		});
	}
	
	function setTaskAssignee(link,taskId){
		var winArgs="dialogWidth=680px;dialogHeight=340px;help=0;status=0;scroll=1;center=1";
		var url="${ctx}/platform/bpm/task/changeAssignee.ht?taskId="+taskId;
		url=url.getNewUrl();
		var rtn=window.showModalDialog(url,"",winArgs);
		if(rtn){
			document.location.reload();
		}
	}
	
	function setTaskOwner(link,taskId){
		var url="${ctx}/platform/bpm/task/setOwner.ht";
		var callback=function(userId,fullname){
			$(link).siblings("span").html('<img src="${ctx}/styles/default/images/bpm/user-16.png">' + fullname);	
		};
		setTaskExecutor(taskId,url,callback);
	}
	
	//设置任务的执行人
	function setTaskExecutor(taskId,url,callback){
		//显示用户选择器
		UserDialog({
			isSingle:true,
			callback:function(userId,fullname){
				if(userId=='' || userId==null || userId==undefined) return;
				var params= { taskId:taskId,userId:userId};
				$.post(url,params,function(responseText){
					var obj=new com.hotent.form.ResultMessage(responseText);
					if(obj.isSuccess()){
						$.ligerDialog.success(obj.getMessage(),$lang.tip.msg,function(){
							document.location.reload();
   						});
					}else{
						 $.ligerMsg.error($lang.tip.failure); 
					}
				});
			}
		});
	}
	//执行任务
	function executeTask(taskId){
		 var url="${ctx}/platform/bpm/task/doNext.ht?taskId="+taskId;
		 jQuery.openFullWindow(url);
	}
	
	function init(){
		var layout=$("#taskLayout").ligerLayout({rightWidth:300,height: '100%',isRightCollapse:true});
		$("tr.odd,tr.even").each(function(){
			$(this).bind("mousedown",function(event){
				if(event.target.tagName!="TD")  return;
				var strFilter='input[type="checkbox"][class="pk"]';
				var obj=$(this).find(strFilter);
				if(obj.length==1){
					var taskId=obj.val();
					layout.setRightCollapse(false);
					$("#taskDetailPanel").html("<div>"+$lang.tip.loading+"</div>");
					//在任务表单明细面版中加载任务详细
					$("#taskDetailPanel").load('${ctx}/platform/bpm/task/miniDetail.ht?manage=true&taskId='+taskId,null);
				}
			});    
		});
	}
	
	$(function(){
		init();
		endProcess();
		//handScroll();
	});
	
	
	
	function endProcess(){
		$.confirm("a.link.stop",$lang_bpm.task.endProcess);
	}
	
	$(function(){
		$("a[taskId]").each(showResult);
		$("a[targetUrl]").each(showResultByUrl);
	});
	function showResult(){
		var template=$("#txtReceiveTemplate").val();
		var jsonValue = $(this).next(":hidden") ;
		var html=easyTemplate(template,$.parseJSON(jsonValue.val())).toString();
		$(this).qtip({
			content:{
				text:html,
				title:{
					text: $lang_bpm.task.list.assigneelist
				}
			},
	        position: {
	        	at:'top left',
	        	target:'event',
				viewport:  $(window)
	        },
	        show:{
	        	event:"click",
  			    solo:true
	        },   			     	
	        hide: {
	        	event:'mouseleave',
	        	fixed:true
	        },  
	        style: {
	       	  classes:'ui-tooltip-light ui-tooltip-shadow'
	        } 			    
	 	});	
			
	}
	function showResultByUrl(){
		var template=$("#txtReceiveTemplate").val();
		var url = $(this).attr("targetUrl") ;
		$(this).qtip({
			content:{
				text:$lang.tip.loading,
				ajax:{
					url:url,
					type:"GET",
					success:function(data,status){
						var html=easyTemplate(template,data).toString();
						this.set("content.text",html);
					}
				},title:{
					text: $lang_bpm.task.list.assigneelist
				}
			},
	        position: {
	        	at:'top left',
	        	target:'event',
				viewport:  $(window)
	        },
	        show:{
	        	event:"click",
  			    solo:true
	        },   			     	
	        hide: {
	        	event:'mouseleave',
	        	fixed:true
	        },  
	        style: {
	       	  classes:'ui-tooltip-light ui-tooltip-shadow'
	        } 			    
	 	});	
	}
</script>
<style type="text/css"> 
    html,body{ padding:0px; margin:0; width:100%;height:100%;overflow: hidden;}  
 </style>
</head>
<body style="overflow: hidden;">
	<div class="panel">
		<div id="taskLayout" type="layout" >
           	<div position="center" style="overflow: auto;" handScroll="true">
           		<div class="hide-panel">
	           		<div class="panel-top">
						<div class="tbar-title">
							<span class="tbar-label"><spr:message code="task.list.title"/></span>
						</div>
						<div class="panel-toolbar">
							<div class="toolBar">
								<div class="group"><a class="link search" ><span></span><spr:message code="menu.button.search"/></a></div>
	<!-- 							<div class="l-bar-separator"></div> -->
								<!-- 删除功能只删除了任务，act_ru_execution表里还有 -->
	<!-- 							<div class="group"><a class="link del" action="delete.ht">删除任务</a></div>  -->
							</div>	
						</div>
						<div class="panel-search">
							<form id="searchForm" method="post" action="list.ht">
								<ul class="row">
									<li><span class="label"><spr:message code="processRun.processName"/>:</span><input type="text" name="Q_processName_SL"  class="inputText" value="${param['Q_processName_SL']}" /></li>
									<li><span class="label"><spr:message code="task.subject"/>:</span><input type="text" name="Q_subject_SL"  class="inputText"  value="${param['Q_subject_SL']}"/></li>
									<li><span class="label"><spr:message code="task.name"/>:</span><input type="text" name="Q_name_SL"  class="inputText"  value="${param['Q_name_SL']}"/></li>
									<li><span class="label"><spr:message code="task.taskId"/>:</span><input type="text" name="Q_id_SL"  class="inputText"  value="${param['Q_id_SL']}"/></li>
								</ul>
							</form>
						</div>		
					</div>
				</div>
				<div class="panel-body">
					
				    	<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <display:table name="taskList" id="taskItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"   class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;">
								  	<input type="checkbox" class="pk" name="id" value="${taskItem.id}">
							</display:column>
							<display:column media="html" titleKey="task.subject" sortable="true" sortName="subject"  >
								<a href="#${taskItem.id}" onclick="javascript:executeTask(${taskItem.id},'${taskItem.name}')" >${taskItem.subject}</a>
							</display:column>
							<display:column property="name" titleKey="task.name" sortable="true"  sortName="name_" style="text-align:left;"></display:column>
							
							<display:column titleKey="task.assignee" sortable="true" sortName="assignee_">
								<f:userName userId="${taskItem.assignee}" taskId="${taskItem.id}"/>
							</display:column>
							<display:column titleKey="task.delegation" sortable="true" sortName="taskStatus">
								<c:choose>
									<c:when test="${taskItem.taskStatus==1}"><spr:message code="processRun.status.approval"/></c:when>
									<c:when test="${taskItem.taskStatus==5}"><spr:message code="processRun.status.recover"/></c:when>
									<c:when test="${taskItem.taskStatus==6}"><spr:message code="processRun.status.reject"/></c:when>
									<c:when test="${taskItem.taskStatus==7}"><spr:message code="processRun.status.retrieved"/></c:when>
								</c:choose>
							</display:column>
							<display:column titleKey="task.createDate" sortable="true" sortName="create_time_">
								<fmt:formatDate value="${taskItem.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
							</display:column>
						
						</display:table>
						<hotent:paging tableId="taskItem"/>
				</div><!-- end of panel-body -->				
			</div> 
			<div id="taskDetailPanel" position="right" title="<spr:message code='task.taskDetail'/>"></div>
		</div>
	</div><!--  end of panel -->
	<textarea id="txtReceiveTemplate"  style="display: none;">
    <div style="height:150px;width:150px;overflow:auto">
    <table class="table-detail" cellpadding="0" cellspacing="0" border="0">
  		<#list data as obj>
  		<tr>
  			<th>\${obj_index+1}</th>
  			<td>
  			<#if ("org"==obj.type)>
				<a href="${ctx}/platform/system/sysUserOrg/userList.ht?orgId=\${obj.executeId}&hasClose=true" target="_blank">\${obj.executor}</a>
			<#elseif ("pos"==obj.type)>
				<a href="${ctx}/platform/system/userPosition/edit.ht?posId=\${obj.executeId}&hasClose=true" target="_blank">\${obj.executor}</a> 
			<#elseif ("role"==obj.type)>
				<a href="${ctx}/platform/system/userRole/edit.ht?roleId=\${obj.executeId}&hasClose=true" target="_blank">\${obj.executor}</a>
			<#elseif (obj.executeId!=null)>
				<a href="${ctx}/platform/system/sysUser/get.ht?userId=\${obj.executeId}&hasClose=true" target="_blank">\${obj.executor}</a>
			<#elseif (obj.userId!=null)>
				<#if (obj.userName!=null)>
				<a href="${ctx}/platform/system/sysUser/get.ht?userId=\${obj.userId}&hasClose=true" target="_blank">\${obj.userName}</a>
				<#elseif (obj.fullname!=null)>
				<a href="${ctx}/platform/system/sysUser/get.ht?userId=\${obj.userId}&hasClose=true" target="_blank">\${obj.fullname}</a>
				</#if>
			</#if>
  			
  			</td>
  		</tr>
  		</#list>
	</table>
  	</div>
  </textarea>
</body>
</html>


