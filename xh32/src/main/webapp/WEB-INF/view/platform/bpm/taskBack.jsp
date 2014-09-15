<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>

<%@include file="/commons/include/form.jsp" %>
<title><spr:message code="task.back.title"/></title>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js" ></script>
<script type="text/javascript">
 function save(){
	 $("#backTaskForm").submit();
	 window.close();
 }

 function selBackUser(obj)
 {
 	var assigneeIds=$(obj).siblings("input[name='assigneeIds']");
 	var assigneeNames=$(obj).siblings("input[name='assigneeNames']");
 	
    UserDialog({
 		callback:function(uIds,unames){
 			assigneeIds.val(uIds);
 			assigneeNames.val(unames);
 		}
 	});
 }
</script>
</head>
<body>
   <div>
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label"><spr:message code="task.back.title"/></span>
		</div>
	</div>
	<form action="${ctx}/platform/bpm/task/jumpBack.ht" method="post" id="backTaskForm">
		<table class="table-grid" style="width:100%">
			<tr>
				<th><spr:message code="task.back.backNode"/></th>
				<th><spr:message code="task.back.execution"/></th>
			</tr>
			<tr>
				<td>${parentStack.nodeName}</td>
				<td>
					<input type="hidden" name="taskId" value="${taskId}"/> <!-- required -->
					<input type="hidden" name="assigneeIds" value="${parentStack.assignees}"/> <!-- allow null -->
					<input type="hidden" name="back" value="true"/>  <!-- required -->
					<input type="hidden" name="stackId" value="${parentStack.stackId}"/><!-- allow null -->
					<input type="text" class="inputText" name="assigneeNames" value="${assigneeNames}">
					<input type="button" value="..." onclick="selBackUser(this);"/>
				</td>
			</tr>
		</table>
		<div position="bottom"  class="bottom" style='margin-top:10px;'>
			<a href='#' class='button'  onclick="save()" ><span ></span><spr:message code="menu.button.ok"/></a>
			<a href='#' class='button' style='margin-left:10px;' onclick="window.close()"><span ></span><spr:message code="menu.button.cancel"/></a>
		</div>
	</form>
</div>
</body>
</html>