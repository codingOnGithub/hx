<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>

<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<title><spr:message code="title.edit"/><spr:message code="bpmDataTemplate.dataTemplate"/></title>
	<f:js pre="js/lang/view/platform/form" ></f:js>
	<f:link href="codemirror/lib/codemirror.css"></f:link>
	<script type="text/javascript" src="${ctx}/js/codemirror/lib/codemirror.js"></script>
	<script type="text/javascript" src="${ctx}/js/codemirror/mode/xml/xml.js"></script>
	<script type="text/javascript" src="${ctx}/js/codemirror/mode/javascript/javascript.js"></script>
    <script type="text/javascript" src="${ctx}/js/codemirror/mode/css/css.js"></script>
    <script type="text/javascript" src="${ctx}/js/codemirror/mode/htmlmixed/htmlmixed.js"></script>
	<script type="text/javascript">
		var editor=null;
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			$('#bpmDataTemplateForm').ajaxForm(options);
			$("a.save").click(function() {
				editor.save();
				$('#bpmDataTemplateForm').submit();
			});
			var width = $("#templateHtml").width();
			var height = $("#templateHtml").height();
			editor = CodeMirror.fromTextArea(document.getElementById("templateHtml"), {
				mode: "text/html",
				tabMode: "indent",
				lineNumbers: true
			 });
			editor.setSize(width,height);
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm( obj.getMessage()+","+$lang.operateTip.continueOp,$lang.tip.confirm, function(rtn) {
					if(rtn){
						this.close();
					}else{
						this.close();
					}
				});
			} else {
				$.ligerDialog.err($lang.tip.error,$lang_form.bpmDataTemplate.editTemplate.msg_edit_fail,obj.getMessage());
			}
		}
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="title.edit"/><spr:message code="bpmDataTemplate.dataTemplate"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" href="#"><span></span><spr:message code="menu.button.save"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link close" href="#" onclick="window.close();"><span></span><spr:message code="menu.button.close"/></a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
				<form id="bpmDataTemplateForm" method="post" action="saveTemplate.ht" >
					<table class="table-detail">
						<tr>
							<th width="5%" nowrap="nowrap"><spr:message code="bpmDataTemplate.template"/></th>
							<td colspan="3">
								<textarea id="templateHtml" name="templateHtml" style="width: 99%;height: 700px;">${bpmDataTemplate.templateHtml }</textarea>
							</td>
						</tr>
					</table>
					<input name="id" type="hidden" value="${bpmDataTemplate.id }"/>
				</form>
		</div>
</div>
</body>
</html>
