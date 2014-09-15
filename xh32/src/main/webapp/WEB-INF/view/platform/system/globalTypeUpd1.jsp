<%--
	time:2011-11-23 11:07:27
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<title><spr:message code="title.edit"/><spr:message code="globalType"/></title>
	<f:link href="tree/zTreeStyle.css"></f:link>
	<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerComboBox.js"></script>
	<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=globalType"></script>
	<script src="${ctx }/js/jquery/plugins/jquery.selectInput.js" type="text/javascript"></script>
	<script src="${ctx }/js/jquery/plugins/jquery.bgiframe.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) {
				var ref=false; 
				var nodeKey=$("#nodeKey").val();
				var typeId=$("#typeId").val();
				$.ajax({
					async:false,
					type: 'POST',
					url:"${ctx}/platform/system/globalType/validNodeKey.ht?nodeKey="+nodeKey+"&typeId="+typeId,
					success: function(result){
						var msg=new com.hotent.form.ResultMessage(result);
						if(msg.isSuccess()){
							ref=true; 
						}else{
							$.ligerDialog.success(String.format($lang_system.globalType.upd1.error_msg_exists,nodeKey),$lang.tip.success); 
							ref=false; 
						}
						
					}
				});
				return ref;
			}; 
			
			valid(showRequest,showResponse);
			$("a.save").click(function() {
				$('#globalTypeForm').submit(); 
				window.parent.loadTree(0);
			});

			//$("#catKey").selectInput();
		});
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="title.edit"/><spr:message code="globalType"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.save"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="${returnUrl}"><span></span><spr:message code="menu.button.back"/></a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<div class="panel-detail">
				<form id="globalTypeForm" method="post" action="upd2.ht">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%"><spr:message code="globalType.typeName"/>:  <span class="required">*</span></th>
							<td ><input type="text" id="typeName" name="typeName" value="${globalType.typeName}"  class="inputText"/></td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="globalType.nodeKey"/>:  <span class="required">*</span></th>
							<td ><input type="text" id="nodeKey" name="nodeKey" value="${globalType.nodeKey}"  class="inputText"/></td>
						</tr>
						<!--  
						<tr>
							<th width="20%">分类Key  <span class="required">*</span></th>
							<td >
								<select id="catKey" name="catKey">
									<c:forEach items="${brotherList}" var="brother">
										<option value="${brother.catKey }" <c:if test="${brother.catKey==globalType.catKey }"> selected="selected"</c:if>>${brother.catKey }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
				        -->
			
						<tr>
							<th width="20%"><spr:message code="globalType.type"/>:<span class="required">*</span> </th>
							<td >
								<select name="type">
									<option value="0" <c:if test="${globalType.type==0}">selected="selected"</c:if>><spr:message code="globalType.type.tile"/></option>
									<option value="1" <c:if test="${globalType.type==1}">selected="selected"</c:if>><spr:message code="globalType.type.tree"/></option>
								</select>
								
							</td>
						</tr>
						
						<tr style="display: none;">
							<th width="20%"><spr:message code="globalType.sn"/>:  <span class="required">*</span></th>
							<td ><input type="text" id="sn" name="sn" value="${globalType.sn}"  class="inputText"/></td>
						</tr>
						
						<tr style="display: none;">
							<th width="20%"><spr:message code="globalType.depth"/>:  <span class="required">*</span></th>
							<td ><input type="text" id="depth" name="depth" value="${globalType.depth}"  class="inputText"/></td>
						</tr>
						<tr style="display: none;">
							<th width="20%"><spr:message code="globalType.parent"/>: </th>
							<td ><input type="text" id="parentId" name="parentId" value="${globalType.parentId}"  class="inputText"/></td>
						</tr>
						
						<tr style="display: none;">
							<th width="20%">nodePath: </th>
							<td ><input type="text" id="nodePath" name="nodePath" value="${globalType.nodePath}"  class="inputText"/></td>
						</tr>
						<tr style="display: none;">
							<th width="20%"><spr:message code="globalType.userId"/>: </th>
							<td ><input type="text" id="userId" name="userId" value="${globalType.userId}"  class="inputText"/></td>
						</tr>
						<tr style="display: none;">
							<th width="20%">depId: </th>
							<td ><input type="text" id="depId" name="depId" value="${globalType.depId}"  class="inputText"/></td>
						</tr>
					</table>
					
					<input type="hidden" id="typeId" name="typeId" value="${globalType.typeId}" />
					<input type="hidden" id="catKey" name="catKey" value="${globalType.catKey}" />
					<input type="hidden" id="returnUrl" value="${returnUrl}" />
				</form>
				</div>
		</div>
</div>
</body>
</html>

