<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp"%>
<title><spr:message code="bpmNodeUser.taskNode.title" /></title>
<script type="text/javascript"src="${ctx}/js/hotent/platform/system/PersonScriptAddDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/javacode/codemirror.js"></script>
<script type="text/javascript" src="${ctx}/js/javacode/InitMirror.js"></script>
<script type="text/javascript">
var defId = ${defId};
$(function(){
	handFlowVars();
});

	function selectScript() {
		var valueTemp = InitMirror.editor.getCode();
		window.returnValue={returnVal:valueTemp};
		window.close();
	}
	
	function addPersonScript(obj){
		var _this = $(obj);
		PersonScriptAddDialog({
			data:{
				defId:defId
			},
			callback:addScriptCallBack
		});
	};

	function addScriptCallBack(data){
		var script = data.script;
		var inst = script.classInsName;
		var method = script.methodName;
		var str = "return "+inst +"."+method+ "( ";
		var paramStr="";
		for(var i=0 ; i<script.argument.length; i++){
			var p=script.argument[i];
			switch(p.paraValType){
			case '1':
				paramStr += p.paraVal+" , " ;
				break;
			case '2':
				if(p.paraType.indexOf("String")>0){
					paramStr += "\"" + p.paraVal+ "\" , " ;
				}else{
					paramStr +=  p.paraVal+ " , " ;
				}
				break;
			}
		}
		if(paramStr){
			paramStr = paramStr.substring(0,paramStr.length-2);
		}
		str += paramStr+");" ;
		InitMirror.editor.insertCode(str);
	};
	
	function handFlowVars(){
		$("select[name='selFlowVar']").change(function(){
			var val=$(this).val();
			InitMirror.editor.insertCode(val);
		});
	}
</script>
</head>
<body>
	<div class="panel">
		<div class="hide-panel">
			<div class="panel-top">

				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group">
							<a class="link save" onclick="selectScript()"><span></span>
							<spr:message code="menu.button.choose" /></a>
						</div>
						<div class="l-bar-separator"></div>
						<div class="group">
							<a class="link del" onclick="javasrcipt:window.close()"><span></span>
							<spr:message code="menu.button.close" /></a>
						</div>
						<div class="l-bar-separator"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<div id="divScriptData" >
				<a href="#" id="btnScript" class="link var"
					title="<spr:message code='script.common'/>"
					onclick="addPersonScript()"><spr:message code="script.common" /></a>
					&nbsp;&nbsp;<spr:message code="script.flowVar"/>:<f:flowVar defId="${defId}" controlName="selFlowVar"></f:flowVar>
				<ul>
					<li><spr:message code="bpmNodeUser.script.help" /></li>
				</ul>
				<textarea id="txtScriptData" codemirror="true" mirrorheight="200px"
					name="txtScriptData" rows="20" cols="80" style="height: 95%;width:98%" class="inputText">${cmpNames}</textarea>
			</div>
		</div>
		<input type="hidden" id="defId" value="${defId}" />
	</div>
</body>
</html>