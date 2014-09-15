<%@ page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<title><spr:message code="task.addSign.title"/></title>
	
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
	<script type="text/javascript">
	var taskId="${param.taskId}";
	function selectSignUsers(){
		var uIds=$("#addSignUserIds");
		var uNames=$('#addSignFullnames');
		UserDialog({callback:function(userIds,fullnames){
						uIds.val(userIds);
						uNames.val(fullnames);
					}
		});
	}
	
	function save(){
		 var signUserIds=$("#addSignUserIds").val();
		 var params={taskId:taskId,signUserIds:signUserIds };
		 if(signUserIds=='') return;
   		 $.post(__ctx+'/platform/bpm/task/addSign.ht',params,
       		 function(){
				 $.ligerDialog.success($lang_bpm.task.addSign.success,$lang.tip.success,function(){
					 this.window.returnValue={};
					 this.window.close();
				 });
       		 }
   		 );
	}
	
	$(function(){
		$("#dataFormSave").click(save);
	});
	</script>
</head>
<body>


<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label"><spr:message code="task.addSign.taskSign"/></span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group">
					<a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="bpmNodeButton.button.addsign"/></a>
				</div>
				<div class="l-bar-separator"></div>
				<div class="group">
					<a class="link del" href="#" onclick="window.close()"><span></span><spr:message code="menu.button.close"/></a>
				</div>
			</div>
		</div>
	</div>
	<div class="panel-detail">
		<table class="table-detail">
			<tr>
				<th nowrap="nowrap"><spr:message code="task.addSign.title"/></th>
				<td>
					<input type="hidden" id="addSignUserIds"/>
					<input type="hidden" id="taskId" value="${param.taskId}"/>
					<input type="text" size="60" value="" readonly="readonly" id="addSignFullnames" class="inputText"/> <input type="button" onclick="selectSignUsers()" value="<spr:message code="menu.button.choose"/>.."/>
				</td>
			</tr>
		</table>
	</div>
</div>
</body>
</html>