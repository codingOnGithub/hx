<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<%@include file="/commons/include/get.jsp" %>
	<f:js pre="js/lang/view/platform/bpm" ></f:js>
	<title>${f:removeHTMLTag(processRun.subject)}--<spr:message code="taskOpinion.title"/></title>
	<link href="${ctx}/styles/default/css/jquery.qtip.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/js/util/easyTemplate.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.qtip.js" ></script>
	<script type="text/javascript" src="${ctx}/js/util/form.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/ProcessUrgeDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowUtil.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/ShowExeInfo.js" ></script>
	<script type="text/javascript">
		$(function(){
			$("a[opinionId]").each(showResult);	
		});
	
		
		function showResult(){
			var opinionId=$(this).attr("opinionId"),
				checkStatus= $(this).attr("checkStatus");
			var template=$("#txtReceiveTemplate").val();
			$(this).qtip({  
				content:{
					text:$lang.tip.loading,
					ajax:{
						url:__ctx +"/platform/bpm/commuReceiver/getByOpinionId.ht",
						type:"GET",
						data:{opinionId: opinionId },
						success:function(data,status){
							var html=easyTemplate(template,data).toString();
							this.set("content.text",html);
						}
					},
					title:{
						text: checkStatus==36?$lang_bpm.commuReceiver.reStart :(checkStatus==15?$lang_bpm.commuReceiver.title : $lang_bpm.transToReceiver.title)			
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
			        	event:'unfocus',
			        	fixed:true
			        },  
			        style: {
			       	  classes:'ui-tooltip-light ui-tooltip-shadow'
			        } 			    
		 	});	
		}
		
	</script>
	
</head>
<body>
	<div class="l-layout-header"><spr:message code="processRun.subject"/>-【<i>${processRun.subject}</i>】<spr:message code="taskOpinion.detail.title"/></div>
	<div class="panel">
		
		<c:if test="${param.tab eq 1 }">
			<c:choose>
					<c:when test="${processRun.status==2  || processRun.status==3}">
						<f:tab curTab="taskOpinion" tabName="process" />
					</c:when>
					<c:otherwise>
						<f:tab curTab="taskOpinion" tabName="process" hideTabs="copyUser"/>
					</c:otherwise>
			</c:choose>
		</c:if>
		<div class="panel-body" style="height:100%;overflow:auto;">
	
  		 <display:table name="taskOpinionList" id="taskOpinionItem" requestURI="list.ht" sort="external" cellpadding="0" cellspacing="0" class="table-grid">
			<display:column titleKey="list.orderNo" style="width:30px;">
			  ${taskOpinionItem_rowNum}
			</display:column>
			<display:column titleKey="taskOpinion.taskName" property="taskName"></display:column>
			<display:column titleKey="taskOpinion.startTime">
				<fmt:formatDate value="${taskOpinionItem.startTime}" pattern="yyyy-MM-dd HH:mm"/>
			</display:column>
			<display:column titleKey="taskOpinion.endTime">
				<fmt:formatDate value="${taskOpinionItem.endTime}" pattern="yyyy-MM-dd HH:mm"/>
			</display:column>
			<display:column titleKey="taskOpinion.durTime">
			  ${f:getTime(taskOpinionItem.durTime)}
			</display:column>
			<display:column  titleKey="taskOpinion.exeUser">
			
				<c:choose>
					<c:when test="${taskOpinionItem.exeUserId ne null and taskOpinionItem.exeUserId ne null}">
						<a href="${ctx}/platform/system/sysUser/get.ht?userId=${taskOpinionItem.exeUserId}&canReturn=2&hasClose=1" target="_blank">${taskOpinionItem.exeFullname}</a>
					</c:when>
					<c:otherwise>
						<c:forEach items="${taskOpinionItem.candidateUsers }" var="user">
							<a href="${ctx}/platform/system/sysUser/get.ht?userId=${user.userId}&canReturn=2&hasClose=1" target="_blank">${user.fullname}</a>
							<br/>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</display:column>
			<display:column  titleKey="taskOpinion.opinion" >
				 ${f:parseText(taskOpinionItem.opinion)}
				<c:if test="${taskOpinionItem.checkStatus==15}">
					<a  href="#" onclick="return false;" opinionId="${taskOpinionItem.opinionId }" checkStatus="15" ><spr:message code="commuReceiver.receivername"/></a>
				</c:if>
				<c:if test="${taskOpinionItem.checkStatus==38}">
					<a  href="#" onclick="return false;" opinionId="${taskOpinionItem.opinionId }" checkStatus="40" ><spr:message code="commuReceiver.receivername"/></a>
				</c:if>
				
			</display:column>
			<display:column titleKey="taskOpinion.checkStatus">
				<f:taskStatus status="${taskOpinionItem.checkStatus}" flag="0"></f:taskStatus>
			</display:column>
		</display:table>
		</div>
  </div>
  <textarea id="txtReceiveTemplate"  style="display: none;">
    <div  style="height:150px;width:150px;overflow:auto">
	  	
	  		<#list data as obj>
	  		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
	  		<tr>
	  			<th><spr:message code="commuReceiver.receivername"/></th>
	  			<td>\${obj.receivername }</td>
	  		</tr>
	  		<tr>
	  			<th><spr:message code="commuReceiver.status"/></th>
	  			<td><#if (obj.status==0) >
	  					<span class="red"><spr:message code="commuReceiver.status.noRead"/></span>
	  				<#elseif (obj.status==1)>
	  					<span class="green"><spr:message code="commuReceiver.status.read"/></span>
	  				<#else>
	  					<span class="green"><spr:message code="commuReceiver.status.feedback"/></span>	
	  				</#if>
	  			</td>
	  		</tr>
	  		<#if (obj.status==0) >
		  		<tr>
		  			<th><spr:message code="commuReceiver.createtime"/></th>
		  			<td>\${obj.createtimeStr}</td>
		  		</tr>
		  	<#elseif (obj.status==1)>
			  	<tr>
		  			<th><spr:message code="commuReceiver.receivetime"/></th>
		  			<td>\${obj.receivetimeStr}</td>
			  	</tr>
		  	<#elseif (obj.status==2)>
		  		<tr>
		  			<th><spr:message code="commuReceiver.receivetime"/></th>
		  			<td>\${obj.feedbacktimeStr}</td>
		  		</tr>
	  		</#if>
	  		</table>
	  		</#list>
	  	
  	</div>
  </textarea>
</body>
</html>
