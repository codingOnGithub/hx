<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/get.jsp"%>
<f:js pre="js/lang/view/platform/bpm" ></f:js>
<title><spr:message code="menu.button.edit"/><spr:message code="WebService.edit"/></title>
<link rel="stylesheet" href="${ctx}/js/tree/zTreeStyle.css" type="text/css" />
<style type="text/css">
body{
	overflow:hidden;
}
.wsTable {font-size：14px;
	border: 2px #8dc2e3 solid;
	width: 100%;
	height: 100%;
	padding-top: 4px;
	background: #ffffff;
}

.fontBold {
	font-weight: bold;
}

.inputDiv {
	float: left;
	width: 50%;
}

.outDiv {
	float: right;
	width: 50%;
}

.clear {
	clear: both;
}

.drag-span{
	font-style: italic;
}

td {
	margin: 5px;
}

ul.radio {
	
}

ul.radio li {
	margin-left: 10px;
	float: left;
}
</style>
<script type="text/javascript">
	//定义常量
	var defId = "${defId}";
	var nodeId = "${nodeId}";
	var actDefId = "${actDefId}";
	var bpmNodeWebServiceSetId = "${setId}";
	var bpmNodeWebServiceDocument = '${document}';
</script>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/BpmNodeWebServiceEdit.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.dragspan.js"></script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="menu.button.edit"/><spr:message code="WebService.edit"/></span>
			</div>
		</div>
		<div id="webLayout" class="panel-body">
			<div position="left" title="<spr:message code='WebService.argum'/>"
				style="overflow: hidden; float: left; width: 100%; height: 100%;">
				<div id="wsLayout" style="height: 48%; border: 1px solid #ddd;">
					<div>
						<input type="text" id="wsdlTxt" value="<spr:message code="WebService.addSearch"/>"
							style="width: 75%; height: 23px" /> <a class="link search"
							id="treeSearch" onclick="javascript:getByWsdlUrl();"><spr:message code="menu.button.search"/></a>
					</div>
					<div class="tree-toolbar" id="pToolbar">
						<div class="toolBar"
							style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap">
							<div class="group">
								<a class="link reload" id="treeReFresh"><spr:message code="menu.button.refresh"/></a>
							</div>
							<div class="l-bar-separator"></div>
							<div class="group">
								<a class="link expand" id="treeExpand"><spr:message code="menu.button.expand"/></a>
							</div>
							<div class="l-bar-separator"></div>
							<div class="group">
								<a class="link collapse" id="treeCollapse"><spr:message code="menu.button.collapse"/></a>
							</div>
						</div>
					</div>
					<ul id="wsTree" class="ztree" style="overflow: auto;"></ul>
				</div>
				<div id="varLayout" style="height:40%;">
					<div class="panel-toolbar" style="border: 1px #8dc2e3 solid;">
						<div class="group"><spr:message code="WebService.flowVar"/></div>
					</div>
					<ul id="varTree" class="ztree" style="height:100%;overflow: auto;"></ul>
				</div>
			</div>
			<div position="center" title="<spr:message code='WebService.setting'/>" style="overflow: auto;">
				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group">
							<a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.save"/></a>
						</div>
						<div class="group">
							<a class="link close" onclick="javasrcipt:closeWin()"><span></span><spr:message code="menu.button.close"/></a>
						</div>
					</div>
				</div>
				<div>
					<form id="bpmNodeWebServiceForm" method="post" action="save.ht">
						<div id="webservice"></div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div style="display: none;" id="editField">
		<!-- 方法表格 -->
		<fieldset style="margin: 5px 0px 5px 0px;" zone="method">
			<legend>
				<span><spr:message code="WebService.webSer.binding"/></span>
				<div class="group" style="float: right;">
					<a class="link del" var="del" title="<spr:message code='WebService.delCurBinding'/>"></a>
				</div>
			</legend>
			<table class="table-detail">
				<tbody>
					<tr>
						<th style="width: 15%;"><spr:message code="WebService.wsdlUrl"/></th>
						<td style="width: 35%" var="wsdl"></td>
						<th style="width: 15%;"><spr:message code="WebService.callAdress"/></th>
						<td>
							<input type="text" class="inputText" var="invokeUrl" style="width: 300px;" />
							<input type="hidden" var="serviceName" />
							<input type="hidden" var="soapaction"/>
						</td>
					</tr>
					<tr>
						<th><spr:message code="WebService.namespace"/></th>
						<td var="namespace"></td>
						<th><spr:message code="WebService.callMethod"/></th>
						<td var="method"></td>
					</tr>
					<tr>
						<th><spr:message code="WebService.inParaBind"/></th>
						<td colspan="3">
							<div style="float: left; width: 30%;">
								<ul var="inputTree" class="ztree" style="overflow: auto;"></ul>
							</div>
							<div style="float: right; width: 65%; padding: 5px;"
								var="inputTreeEdit"></div>
						</td>
					</tr>
					<tr>
						<th><spr:message code="WebService.outParaBind"/></th>
						<td colspan="3">
							<div style="float: left; width: 30%;">
								<ul var="outputTree" class="ztree" style="overflow: auto;"></ul>
							</div>
							<div style="float: right; width: 65%; padding: 5px;"
								var="outputTreeEdit"></div>
						</td>
					</tr>
				</tbody>
			</table>
		</fieldset>

		<!-- 出入参编辑表格 -->
		<table class="table-detail" zone="binding">
			<tbody>
				<tr>
					<th width="20%"><spr:message code="WebService.argument.name"/></th>
					<td width="30%">
						<input type="hidden" var="fullpath"/>
						<span var="name"></span>
					</td>
					<th width="20%"><spr:message code="WebService.soapType"/></th>
					<td var="type"></td>
				</tr>
				<tr>
					<th><spr:message code="WebService.bindingType"/></th>
					<td colspan="3">
						<select name="bindingType">
							<option value="1"><spr:message code="WebService.fixedValue"/></option>
							<option value="2" selected="selected"><spr:message code="WebService.flowVar"/></option>
							<option value="3"><spr:message code="WebService.script"/></option>
						</select>
					</td>
				</tr>
				<tr bingdingType="1" style="display: none;">
					<th><spr:message code="WebService.defValue"/></th>
					<td colspan="3"><input type="text" class="inputText" name="defValue1" /></td>
				</tr>
				<tr bingdingType="2">
					<th><spr:message code="WebService.bindVariable"/></th>
					<td colspan="3"><span class="drag-span" name="defValue2"><spr:message code="WebService.tip"/></span></td>
				</tr>
				<tr bingdingType="2">
					<th><spr:message code="WebService.javaType"/></th>
					<td colspan="3" name="javaType"></td>
				</tr>
				<tr bingdingType="3" style="display: none;">
					<th>
						<a href="javascript:;" class="link tipinfo hidden">
							<span style="z-index: 100;text-align: left;">
							<spr:message code="WebService.tip1"/>
							<spr:message code="WebService.tip2"/>
							</span>
						</a>
						<spr:message code="WebService.script"/>
					</th>
					<td colspan="3">
						<textarea cols="50" rows="5" name="defValue3"></textarea>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>