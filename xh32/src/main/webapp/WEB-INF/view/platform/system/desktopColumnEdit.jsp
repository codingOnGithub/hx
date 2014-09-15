<%--
	time:2012-03-20 16:39:01
	desc:edit the 桌面栏目
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<title><spr:message code="desktopColumn.edit.title" /></title>
	<%-- <link  rel="stylesheet" type="text/css" href="${ctx}/js/codemirror/lib/codemirror.css" > --%>
	<f:link href="codemirror/lib/codemirror.css"></f:link>
	<script type="text/javascript" src="${ctx}/js/codemirror/lib/codemirror.js"></script>
	<script type="text/javascript" src="${ctx}/js/codemirror/mode/xml/xml.js"></script>
	<script type="text/javascript" src="${ctx}/js/codemirror/mode/javascript/javascript.js"></script>
    <script type="text/javascript" src="${ctx}/js/codemirror/mode/css/css.js"></script>
    <script type="text/javascript" src="${ctx}/js/codemirror/mode/htmlmixed/htmlmixed.js"></script>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=desktopColumn"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/BpmQueryDialog.js"></script>
	<script type="text/javascript">
		$(function() {
			var width = $("#html").width();
			var height = $("#html").height();
			editor = CodeMirror.fromTextArea(document.getElementById("html"), {
				mode: "text/html",
				tabMode: "indent",
				lineNumbers: true
			 });
			
			editor.setSize(width,height);
			
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			if(${desktopColumn.id ==null }){
				valid(showRequest,showResponse,1);
			}else{
				valid(showRequest,showResponse);
			}
			$("a.save").click(function() {
				$("#html").val(editor.getValue());
				$('#desktopColumnForm').submit(); 
			});
			
			$(":radio").click(function(){
				var  checked=$(this).attr('checked');
				var val=$(this).val();
				if(checked&&val==1){
					$("#queryAlias").show();
					$("#serviceMethod").hide();
				}else{
					$("#queryAlias").hide();
					$("#serviceMethod").show();
				}
			});
		});
		
		function selectQuery(){
			BpmQueryDialog({callback:function(alias){
				$("#queryAlias").find(':input').val(alias);
			}});
		}
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
			    <c:choose>
			        <c:when test="${desktopColumn.id !=null }">
			            <span class="tbar-label"><spr:message code="desktopColumn.edit.span.edit" /></span>
			        </c:when>
			        <c:otherwise>
			            <span class="tbar-label"><spr:message code="desktopColumn.edit.span.add" /></span>
			        </c:otherwise>
			    </c:choose>
				
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.save" /></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="list.ht"><span></span><spr:message code="menu.button.back" /></a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
				<form id="desktopColumnForm" method="post" action="save.ht">
				
						<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th width="20%"><spr:message code="desktopColumn.columnName" />: </th>
								<td>
								<input type="text" id="columnName" name="columnName" style="width: 20%;" value="${desktopColumn.columnName}"  class="inputText" style="width: 80%;"/>
								</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="desktopColumn.columnAlias" />: </th>
								<td>
									<input type="text" id="templateId" name="templateId" style="width: 20%;" value="${desktopColumn.templateId}"  class="inputText" style="width: 80%;"/>
									<a href="#" class="tipinfo"><span><spr:message code="desktopColumn.tipInfo1" /></span></a>
								</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="desktopColumn.columnUrl" />: </th>
								<td>
									<input type="text" id="columnUrl" name="columnUrl" style="width: 40%;" value="${desktopColumn.columnUrl}"  class="inputText" style="width: 80%;"/>
								</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="desktopColumn.dataLoadType" />: </th>
								<td>
									<input type="radio"  name="methodType"  value="0" <c:if test="${desktopColumn.methodType!=1}">checked="checked"</c:if> /><spr:message code="desktopColumn.dataLoad.service" />
									<input type="radio"  name="methodType" value="1" <c:if test="${desktopColumn.methodType==1}">checked="checked"</c:if> /><spr:message code="desktopColumn.dataLoad.customQuery" />
								</td>
							</tr>
							<tr id="serviceMethod" <c:if test="${desktopColumn.methodType!=0}">style="display:none"</c:if>>
								<th width="20%"><spr:message code="desktopColumn.methodUrl" />: </th>
								<td><input type="text"  name="serviceMethod" style="width: 40%;" value="${desktopColumn.serviceMethod}"  class="inputText" style="width: 80%;"/>
									<a href="#" class="tipinfo"><span><spr:message code="desktopColumn.tipInfo2" /></span></a>
								</td>
							</tr>
							<tr id="queryAlias" <c:if test="${desktopColumn.methodType!=1}">style="display:none"</c:if> >
								<th width="20%"><spr:message code="desktopColumn.customQuery" />: </th>
								<td>
									<input type="text"  name="queryAlias" style="width: 20%;" value="${desktopColumn.queryAlias}"  readonly="readonly" class="inputText" />&nbsp;<a  href="#" onclick="selectQuery()"  class="button"><span><spr:message code="operator.select" />...</span></a>
								</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="desktopColumn.html" />: </th>
								<td >
									<textarea id="html" name="html" style="width: 90%;height: 220px;">${fn:escapeXml(desktopColumn.html)}</textarea>
								</td>
							</tr>
						</table>
					<input type="hidden" name="id" value="${desktopColumn.id}" />
				</form>
		</div>
</div>
</body>
</html>
