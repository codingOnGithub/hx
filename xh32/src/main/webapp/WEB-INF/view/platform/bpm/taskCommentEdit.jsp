<%--
	time:2012-04-05 17:02:32
	desc:edit the 流程任务评论
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<title><spr:message code="menu.button.edit"/><spr:message code="taskComment.title"/></title>
	<%@ taglib prefix="hotent" uri="http://www.jee-soft.cn/paging" %>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=taskComment"></script>
	<script type="text/javascript">
		
		function showRequest(formData, jqForm, options) { 
			return true;
		} 
	
		$(function() {
			
			valid(showRequest,function(){});

		});
		
		function save(){

			var rtn=$("#taskCommentForm").valid();
   		 	if(!rtn) return;
   		 	
			var url=__ctx+ "/platform/bpm/taskComment/save.ht";
   		 	var para=$('#taskCommentForm').serialize();
   		 	
   		 	$.post(url,para,function(result){
	   		 	var obj = eval('(' + result + ')');
	   			if(obj.result==1){
	  	    			$.ligerDialog.success(obj.message,$lang.tip.success,function(){
	  	  					$('#content').val('');
							location.reload();
	  	    			});
	   			}else{
	  	    			$.ligerDialog.error(obj.message,$lang.tip.failure,function(){});
	   			}
   		 	});
		}
		
		function loadTaskCommentPanel(actDefId,runId,taskId){
			$("#tcPanel").empty();
			
			$("#tcPanel").load('${ctx}/platform/bpm/taskComment/list.ht?taskId='+taskId
				+'&actDefId='+actDefId+'&runId='+runId);
		}
		
	</script>
	<style type="text/css">
		html,body{
			height:100%;width:100%;
		}
		body{margin: -2px -1px 0px 0px; }
	</style>
	
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label">
			   <c:choose>
					<c:when test="${taskComment.content==null}">
						<spr:message code="menu.button.add"/><spr:message code="taskComment.title"/>
					</c:when>
					<c:otherwise>
						<spr:message code="menu.button.edit"/><spr:message code="taskComment.title"/>
					</c:otherwise>
				</c:choose> 		
			</span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="javascript:this.save()"><span></span><spr:message code="menu.button.save"/></a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link close" href="javascript:this.close()"><span></span><spr:message code="menu.button.close"/></a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="taskCommentForm" method="post" action="save.ht">

			<div class="panel-detail">
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th width="20%"><spr:message code="taskComment.flowname"/>:</th>
						<td>${taskComment.subject}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="taskComment.nodeName"/>:</th>
						<td>${taskComment.nodeName}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="taskComment.content"/>: </th>
						<td>
							<textarea rows="5" cols="60" id="content" name="content"
								style="margin-top: 5px;margin-bottom: 5px;">${taskComment.content}</textarea>
							<input type="hidden" id="actDefId" name="actDefId" value="${taskComment.actDefId}" />
							<input type="hidden" id="runId" name="runId" value="${taskComment.runId}" />
							<input type="hidden" id="taskId" name="taskId" value="${taskComment.taskId}" />
						</td>
					</tr>
				</table>
			</div>
		</form>
		
		<br>
		
		<display:table name="taskCommentList" id="taskCommentItem" requestURI="edit.ht" 
			sort="external" cellpadding="1" cellspacing="1" export="false"  class="table-grid">
			<display:column property="author" titleKey="taskComment.author" sortable="true" sortName="author"></display:column>
			<display:column  titleKey="taskComment.commentTime" sortable="true" sortName="commentTime">
				<fmt:formatDate value="${taskCommentItem.commentTime}" pattern="yyyy-MM-dd"/>
			</display:column>
			<display:column property="content" titleKey="taskComment.content" sortable="false" 
				sortName="content" maxLength="80"></display:column>
			</display:table>
		<hotent:paging tableId="taskCommentItem"/>

	</div>
</div>
</body>
</html>
