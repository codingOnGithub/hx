<%@page pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
<f:js pre="js/lang/view/platform/bpm" ></f:js>
<title><spr:message code="tab.process.userImage"/></title>
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
			var nodeId=$(this).attr('id');
			if($(this).attr('type')=='userTask' || $(this).attr('type')=='multiUserTask'){
				$(this).css('cursor','pointer');
				var url="${ctx}/platform/bpm/processRun/taskUser.ht?processInstanceId="+processInstanceId+"&nodeId=" + nodeId;
				$(this).powerFloat({ eventType: "click", target:url, targetMode: "ajax"});
			}
			if($(this).attr('type')=='callActivity'){
				$(this).css('cursor','pointer');
				$(this).click(function(){
					var nodeId=$(this).attr('id');
					var conf = {nodeId:nodeId,processInstanceId:processInstanceId};
					viewSubFlow(conf);
				});
			}
			checkStatusInfo(nodeId);
		});		
		if(self!=top){
			setIframeHeight();
		}
	});
	
	function viewSubFlow(conf){
		if(!conf) conf={};
		var url="${ctx}/platform/bpm/processRun/subFlowImage.ht?processInstanceId="+conf.processInstanceId+"&nodeId=" + conf.nodeId+"&processDefinitionId="+conf.processDefinitionId;
		var winArgs="dialogWidth=800px;dialogHeight=600px;help=0;status=0;scroll=1;center=1;";
		url=url.getNewUrl();
		window.showModalDialog(url,"",winArgs);
	}
	


	//初始化qtip
	function checkStatusInfo(nodeId){
		var obj = $("#"+nodeId);
		$("#"+nodeId).qtip({
			content:{
				text:function(){
					var html =  getTableHtml(nodeId);
					if(!html){
						html = obj.attr("title");;
					}
					return html;
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
				}
	        },   			     	
	        hide: {
	        	event:'unfocus mouseleave',
	        	leave: false,
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
	function getTableHtml(taskId){	
		var node=loadStatus(taskId);
		if(!node) return false;
		var aryOptions=node.taskOpinionList;
		if(aryOptions.length==0)return false;
	
		var html=['<div style="height:240px;width:260px;overflow:auto;">'];
		var tableHtml=$("#txtTaskStatus").val();
 		var str= easyTemplate(tableHtml,node);
		html.push(str);
		html.push('</div>');

		return html.join('');
	}
	

	

	
</script>
</head>
<body>
<div class="panel">
	<c:if test="${notShowTopBar==null}">
	<div class="panel-top" >
		<div class="tbar-title">
			<span class="tbar-label"><spr:message code="processRun.subject"/>:<i>${processRun.subject}</i></span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link close" href="#" onclick="window.close();"><span></span><spr:message code="menu.button.close"/>/a></div>
			</div>
		</div>
	</div>
	<c:if test="${link ne 1 }">
		<c:choose>
			<c:when test="${action=='task'}">
			<f:tab curTab="detail" tabName="task"/>
			</c:when>
			<c:otherwise>
			<f:tab curTab="processImage" tabName="process"/>
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
		<div style="padding-top:20px;background-color: white;float:left;">
			<div style="margin-bottom: 5px; "><spr:message code="processRunUserImage.help"/>
				<c:if test="${superInstanceId != null}">
					<a class="link setting" onclick="ViewSuperExecutionFlowWindow({'processInstanceId':'${superInstanceId}'})"><spr:message code="processRunUserImage.queryMainFlow"/></a>
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
						<td>\${obj.exeFullname==null?"":obj.exeFullname}</td>
					</tr>
					<tr>
						<th><spr:message code="processRunUserImage.task.candidateUser"/>: </th>
						<td>\${obj.candidateUser==null?"":obj.candidateUser}</td>
					</tr>
				<#else>
					<tr>
						<th><spr:message code="processRunUserImage.task.exeFullname"/>: </th>
						<td>\${obj.exeFullname}</td>
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
	</div>
	</c:if>
</div>
</body>
</html>