<%@page pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
<f:js pre="js/lang/view/platform/bpm" ></f:js>
<title><spr:message code="processRunUserImage.mainTitle" arguments="${f:removeHTMLTag(processRun.subject)}"/></title>
<link href="${ctx}/styles/default/css/jquery.qtip.css" rel="stylesheet" />
<link href="${ctx}/js/jquery/plugins/powerFloat.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery-powerFloat.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.qtip.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/ViewSubFlowWindow.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/ViewSuperExecutionFlowWindow.js" ></script>
<script type="text/javascript" src="${ctx}/js/util/easyTemplate.js" ></script>
<script type="text/javascript" src="${ctx}/js/util/form.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/ProcessUrgeDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/ShowExeInfo.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/UserInfo.js"></script>
<style type="text/css">
div.header{
	background-image: url(${ctx}/styles/default/images/tool_bg.jpg);
	height:24px;
	line-height:24px;
	font-weight: bold;
	font-size: 14px;
	padding-left: 5px;
	margin: 0;
	width: 394px;
}
div .legend {
	width:14px;
	height:14px;
	border: 1px solid black;
	float: left;
}

.table-task {
	margin: 0 auto;
	width:210px;
	border-collapse: collapse;
}

.table-task th {
	text-align: right;
	padding-right: 6px;
	color: #000;
	height: 32px;
	border: solid 1px #A8CFEB;
	font-weight: bold;
	text-align: right;
	font-size: 13px;
	font-weight: bold;
	padding-right: 5px;
	background-color: #D7D7D7;
	padding-right: 5px;
	border: 1px solid #656565;
}
.table-task td {
	border: solid 1px #656565;
	padding-left: 6px;
	text-align: left;
	line-height: 20px;
}

.target{
	height:20px;
	float: left;
	margin:10px;
}
div.icon{
	border:1px solid #000;
	line-height: 10px;
	width: 15px;
	height:15px;
	float: left;
	overflow: hidden;
}
.target span{
	margin: 0 0 0 5px;
	font-size: 14px;
	font-weight: bold;
	float:left;
	vertical-align: middle;
	white-space: nowrap;
}
</style>
<script type="text/javascript">
	var processInstanceId="${processInstanceId}";
	var isStatusLoaded=false;
	var _height=${shapeMeta.height};
	//状态数据
	//var aryResult=null;
	//hjx add ifram自适应高度
	$(window.parent.document).find("#flowchart").load(function(){
		var main = $(window.parent.document).find("#flowchart");
		var thisheight = $(document).height()+30;
		main.height(thisheight);
		});
		
	function setIframeHeight(){
		var mainIFrame = window.parent.document.getElementById("flowchart");
		if(!mainIFrame)return;
		mainIFrame.style.height=_height+200;
	};
	
	$(function(){
		$.each($("div.flowNode"),function(){
			var obj=$(this);
			var nodeId=$(this).attr('id');
			if(obj.attr('type')=='userTask' || obj.attr('type')=='multiUserTask'){
				obj.css('cursor','pointer');
				//var url="${ctx}/platform/bpm/processRun/taskUser.ht?processInstanceId="+processInstanceId+"&nodeId=" + nodeId;
				//obj.powerFloat({ eventType: "click", target:url, targetMode: "ajax"});
				//只有用户任务和会签任务显示节点。
				checkStatusInfo(nodeId);
			}
			if(obj.attr('type')=='callActivity'){
				obj.css('cursor','pointer');
				obj.click(function(){
					var nodeId=obj.attr('id');
					var conf = {nodeId:nodeId,processInstanceId:processInstanceId};
					viewSubFlow(conf);
				});
			}
		});		
		if(self!=top){
			setIframeHeight();
		}
	});
	
	function showResult(){
		var targetUrl = $(this).attr("candidateUserUrl") ;
		
		var template=$("#txtReceiveTemplate").val();
		$(this).qtip({  
			content:{
				text:$lang.tip.loading,
				ajax:{
					url:targetUrl,
					type:"GET",
					success:function(data,status){
						var html=easyTemplate(template,data).toString();
						this.set("content.text",html);
					}
				},
				title:{
					text:$lang_bpm.task.list.assigneelist
				}
			},
		        position: {
		        	at:'top left',
		        	target:'event',
					viewport:  $(window)
		        },
		        show:{
		        	event:"focus mouseenter",
		        },   			     	
		        hide: {
		        	event:'unfocus mouseleave',
		        	fixed:true
		        },  
		        style: {
		       	  classes:'ui-tooltip-light ui-tooltip-shadow'
		        } 			    
	 	});	
	}
	
	function viewSubFlow(conf){
		if(!conf) conf={};
		var url="${ctx}/platform/bpm/processRun/subFlowImage.ht?processInstanceId="+conf.processInstanceId+"&nodeId=" + conf.nodeId+"&processDefinitionId="+conf.processDefinitionId;
		
		var winArgs="dialogWidth=800px;dialogHeight=600px;help=0;status=0;scroll=1;center=1;";
		
		var winArgs="height=600,width=800,status=no,toolbar=no,menubar=no,location=no,scrollbars=yes,resizable=yes";
		url=url.getNewUrl();
		var win=window.open(url,"subFlow",winArgs);
		win.focus();
	}
	


	//初始化qtip
	function checkStatusInfo(nodeId){
		var html =  getTableHtml(nodeId);
		if(html)
		var obj = $("#"+nodeId);
		$("#"+nodeId).qtip({
			content:{
				text:function(){
					var html =  getTableHtml(nodeId);
					if(html){
						return html;
					}
					else{
						return $lang_bpm.processImage.init;
					}
					
				},
				title:{
					text: $lang_bpm.processImage.implementation	
				}
			},
	        position: {
	        	at:'center',
	        	target:'event',
	        	adjust: {
	        		x:-15,
	        		y:-15
	  				},  
	  				viewport:  ($.isIE6() ? "" : $(window))
	        },
	        show:{   			        	
		     	effect: function(offset) {
					$(this).slideDown(200);
					$("a[candidateUserUrl]").each(showResult);
				}
	        }, 
	        hide: {
	        	event:'mouseleave',
	        	fixed:true,
	        	delay:300
        	},  
	        style: {
	       	  classes:'ui-tooltip-light ui-tooltip-shadow',
	          width : ($.isIE6()? 279 : "" )   
	        } 			    
		});
	};
	


	var taskNodeStatus={};
	

	//加载流程状态数据。
	function loadStatus(nodeId){
		var status = taskNodeStatus[nodeId];
		if(!status && status!=""){
			var url="${ctx}/platform/bpm/processRun/getFlowStatusByInstanceIdAndNodeId.ht";
			var params={
					instanceId:processInstanceId,
					nodeId:nodeId
				};
			$.ajax({
				url:url,
				async:false,
				data:params
			}).done(function(result){
				status=result;
			});
			taskNodeStatus[nodeId]=status;
		}
		return status;
	};
	
	//构建显示的html
	function getTableHtml(nodeId){	
		var node = loadStatus(nodeId);
		if (!node)
			return false;
		var taskOpinionList = node.taskOpinionList;
		var taskExecutorList = node.taskExecutorList;
		var lastCheckStatus = node.lastCheckStatus;
		var html = [ '<div style="max-height:340px;width:260px;overflow:auto;">' ];
		if (lastCheckStatus != "-2") {  //正在执行的节点
			if (taskOpinionList.length == 0) {
				return false;
			} else {
				var tableHtml = $("#txtTaskStatus").val();
				var str = easyTemplate(tableHtml, node);
				html.push(str);
				html.push('</div>');
			}
		}else if(lastCheckStatus == "-2"){ //未执行的节点
			if (taskExecutorList.length == 0) {
				return false;
			} else {
				var tableHtml = $("#txtTaskStatusExecutors").val();
				var str = easyTemplate(tableHtml, node);
				html.push(str);
				html.push('</div>');
			}
		}

		return html.join('');
	}
	

	

	
</script>
</head>
<body>
<div class="l-layout-header"><spr:message code="processRunUserImage.name" arguments="${processRun.subject}"/></div>
<div class="panel">

	<c:if test="${param.tab eq 1 }">
			<c:choose>
				<c:when test="${processRun.status==2  || processRun.status==3}">
					<f:tab curTab="processImage" tabName="process" />
				</c:when>
				<c:otherwise>
					<f:tab curTab="processImage" tabName="process" hideTabs="copyUser"/>
				</c:otherwise>
			</c:choose>
	</c:if>
	
	<div class="panel-body">
		<div>
			<div class="target">
				<div class="icon" style="background:gray;"></div>
				<span><spr:message code="processRunUserImage.init"/></span>
			</div>
			<div class="target">
				<div class="icon" style="background:#F89800;"></div>
				<span><spr:message code="processRunUserImage.submit"/></span>
			</div>
			<div class="target">
				<div class="icon" style="background:#FFE76E;"></div>
				<span><spr:message code="processRunUserImage.reSubmit"/></span>
			</div>
			<div class="target">
				<div class="icon" style="background:#00FF00;"></div>
				<span><spr:message code="processRunUserImage.agree"/></span>
			</div>
			<div class="target">
				<div class="icon" style="background:orange;"></div>
				<span><spr:message code="processRunUserImage.abandon"/></span>
			</div>
			<div class="target">
				<div class="icon" style="background:red;"></div>
				<span><spr:message code="processRunUserImage.curNode"/></span>
			</div>
			<div class="target">
				<div class="icon" style="background:blue;"></div>
				<span><spr:message code="processRunUserImage.notAgree"/></span>
			</div>
			<div class="target">
				<div class="icon" style="background:#8A0902;"></div>
				<span><spr:message code="processRunUserImage.reject"/></span>
			</div>
			<div class="target">
				<div class="icon" style="background:#023B62;"></div>
				<span><spr:message code="processRunUserImage.recover"/></span>
			</div>
			<div class="target">
				<div class="icon" style="background:#338848;"></div>
				<span><spr:message code="processRunUserImage.signPassed"/></span>
			</div>
			<div class="target">
				<div class="icon" style="background:#82B7D7;"></div>
				<span><spr:message code="processRunUserImage.signNotPassed"/></span>
			</div>
			<div class="target">
				<div class="icon" style="background:#EEAF97;"></div>
				<span><spr:message code="processRunUserImage.endProcess"/></span>
			</div>
			<div class="target">
				<div class="icon" style="background:#C33A1F;"></div>
				<span><spr:message code="processRunUserImage.complete"/></span>
			</div>
		</div>
		<div style="padding-top:20px;background-color: white;float:left;clear:both;">
			<div style="margin-bottom: 5px; "><spr:message code="processRunUserImage.help"/>
				<c:if test="${superInstanceId != null}">
					<a class="link setting" onclick="ViewSuperExecutionFlowWindow({'actInstanceId':'${superInstanceId}'})"><spr:message code="processRunUserImage.queryMainFlow"/></a>
				</c:if>
			</div>
			<div id="divTaskContainer" style="margin:0 auto;  position: relative;background:url('${ctx}/bpmImage?processInstanceId=${processInstanceId}&randId=<%=Math.random()%>') no-repeat;width:${shapeMeta.width}px;height:${shapeMeta.height}px;">
				${shapeMeta.xml}
			</div>
		</div>
		<textarea id="txtTaskStatus" style="display:none">
			<#list data.taskOpinionList as obj>
				<table class="table-task" cellpadding="0" cellspacing="0" border="0">
				<tr><th><spr:message code="processRunUserImage.task.taskName"/>: </th>
				<td>\${obj.taskName}</td></tr>
				<#if (obj.checkStatus == -1)> <!-- 正在审批 -->
					<tr>
						<th><spr:message code="processRunUserImage.task.exeFullname"/>: </th>
						<#if (obj.taskExeStatus==null)>
							<td></td>
						<#else>
							<td> <a href="javascript:userDetail('\${obj.taskExeStatus.executorId}');">\${obj.taskExeStatus.executor}</a>--\${obj.taskExeStatus.mainOrgName}\${obj.taskExeStatus.isRead==false?"(<spr:message code='processCenter.readStatus.notRead'/>)":"(<spr:message code='processCenter.readStatus.read'/>)"}</td>
						</#if>
					</tr>
					<tr>
						<th><spr:message code="processRunUserImage.task.candidateUser"/>: </th>
						<#if (obj.candidateUserStatusList==null)>
							<td></td>
						<#else>
							<td>
								<#list obj.candidateUserStatusList as candidateUserStatus>
									<#if (candidateUserStatus.type=="user")>
									<a href="javascript:userDetail('\${candidateUserStatus.executorId}');">\${candidateUserStatus.candidateUser}</a>
										<span>--\${candidateUserStatus.mainOrgName}\${candidateUserStatus.isRead==false?"(<font color='red'><spr:message code='processCenter.readStatus.notRead'/></font>)":"(<font color='green'><spr:message code='processCenter.readStatus.read'/></font>)"} </span><br/>
									<#else>
										<#if (candidateUserStatus.type=="org")>
											<span><a href="#" candidateUserUrl="${ctx}/platform/system/sysUserOrg/getUserListByOrgId.ht?orgId=\${candidateUserStatus.executorId}">\${candidateUserStatus.candidateUser}</a>(<spr:message code="processRunUserImage.org"/>)</span><br/>
										<#elseif (candidateUserStatus.type=="pos")>
											<span><a href="#" candidateUserUrl="${ctx}/platform/system/userPosition/getUserListByPosId.ht?posId=\${candidateUserStatus.executorId}">\${candidateUserStatus.candidateUser}</a>(<spr:message code="processRunUserImage.pos"/>)</span><br/>
										<#elseif (candidateUserStatus.type=="role")>
											<span><a href="#" candidateUserUrl="${ctx}/platform/system/userRole/getUserListByRoleId.ht?roleId=\${candidateUserStatus.executorId}">\${candidateUserStatus.candidateUser}</a>(<spr:message code="processRunUserImage.role"/>)</span><br/>
										<#elseif (candidateUserStatus.type=="group")>
											<span>\${candidateUserStatus.candidateUser}(<spr:message code="processRunUserImage.userGroup"/>)</span><br/>
										</#if>
									</#if>
								</#list>
							</td>
						</#if>
					</tr>
				<#else>
					<tr>
						<th><spr:message code="processRunUserImage.task.exeFullname"/>: </th>
						<td><a href="javascript:userDetail('\${obj.exeUserId}');">\${obj.exeFullname}</a></td>
					</tr>
				</#if>
				<tr><th  nowrap="nowrap"><spr:message code="processRunUserImage.task.startTime"/>: </th>
				<td>\${obj.startTimeStr}</td></tr>
				
				<tr><th><spr:message code="processRunUserImage.task.endTime"/>: </th>
				<td>\${obj.endTimeStr}</td></tr>
				
				<tr><th ><spr:message code="processRunUserImage.task.durTime"/>: </th>
				<td>\${obj.durTimeStr}</td></tr>
				
				<tr><th><spr:message code="processRunUserImage.task.status"/>: </th>
				<td>\${obj.status}</td></tr>
				
				<tr><th><spr:message code="processRunUserImage.task.opinion"/>: </th>
				<td>\${obj.opinion==null?"":obj.opinion}</td></tr>
				</table><br>
			</#list>
		</textarea>
		<textarea id="txtTaskStatusExecutors" style="display:none">
			<div><spr:message code="processRunUserImage.initStart"/>： <span style='color:red;line-height: 24px;'><spr:message code="processRunUserImage.init"/></span> </div>
				<table class="table-task" cellpadding="0" cellspacing="0" border="0">
					<tr >
					<th width="50"><spr:message code="processRunUserImage.task.exeFullname"/>: </th>
						<td>
						<#list data.taskExecutorList as obj>
						<a href="javascript:userDetail('\${obj.executeId}');">\${obj.executor}</a>
							-- \${obj.mainOrgName}</br>
						</#list>
						</td>
					</tr>
				</table>
		</textarea>
		<textarea id="txtReceiveTemplate"  style="display: none;">
		    <div  style="height:150px;width:150px;overflow:auto">
		  		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			  		<#list data as obj>
				  		<tr>
				  			<th>\${obj_index+1}</th>
				  			<#if (obj.fullname==null)>
				  				<td>\${obj.userName}</td>
			  				<#else>
			  					<td>\${obj.fullname}</td>
		  					</#if>
				  		</tr>
			  		</#list>
		  		</table>
		  	</div>
	   </textarea>
	</div>
</div>
</body>
</html>