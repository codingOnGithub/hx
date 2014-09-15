<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/get.jsp"%>
<title><spr:message code="operator.edit"/><spr:message code="WebService.edit"/></title>
<f:js pre="js/lang/view/platform/bpm" ></f:js>
<link rel="stylesheet" href="${ctx}/js/tree/zTreeStyle.css" type="text/css" />
<style type="text/css">
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
	var bpmNodeWebServiceSetId = "${bpmCommonWsSet.id}";
	var bpmNodeWebServiceDocument = '${bpmCommonWsSet.document}';
</script>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/BpmCommonWsSet.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.dragspan.js"></script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="operator.edit"/><spr:message code="WebService.edit"/></span>
			</div>
		</div>
		<div id="webLayout" class="panel-body">
			<div position="left" title="<spr:message code='WebService.argum'/>"
				style="overflow: hidden; float: left; width: 100%; height: 100%;">
				<div id="wsLayout" style="height: 48%; border: 1px solid #ddd;">
					<div>
						<input type="text" id="wsdlTxt" value="${bpmCommonWsSet.wsdlUrl}" 
							   style="width: 75%; height: 23px" />
						<a class="link search" id="treeSearch" onclick="javascript:getByWsdlUrl();"><spr:message code="menu.button.search"/></a>
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
					<div style="border: 1px #8dc2e3 solid;height:35px;line-height:35px;padding:0 8px;background:#ebebeb;">
						<div class="group"><spr:message code="WebService.customArgum"/></div>
						<a class="link add2" id="add_custom" style="margin:0 0 0 10px;" title="<spr:message code='operator.add'/><spr:message code='WebService.customArgum'/>" href="#"><spr:message code="menu.button.add"/></a>
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
							<a class="link back" href="list.ht"><span></span><spr:message code="menu.button.back"/></a>
						</div>
					</div>
				</div>
				<div>
					<form id="bpmNodeWebServiceForm" method="post" action="save.ht">
						<div id="webservice" style="padding:10px 0 0 0;">
							<table class="table-detail" zone="method">
								<tbody>
									<tr>
										<th style="width: 15%;"><spr:message code="WebService.namespace"/></th>
										<td style="width: 35%;" var="namespace"></td>
										<th style="width: 15%;"><spr:message code="WebService.callMethod"/></th>
										<td style="width: 35%;" var="method"></td>
									</tr>
									<tr>
										<th><spr:message code="WebService.callAlias"/></th>
										<td colspan="3">
											<input type="text" name="alias" value="${bpmCommonWsSet.alias}"/>
										</td>
									</tr>
									<tr>
										<th ><spr:message code="WebService.wsdlUrl"/></th>
										<td var="wsdl"  colspan="3"></td>
									</tr>
									<tr>
										<th style="width: 15%;"><spr:message code="WebService.callAdress"/></th>
										<td colspan="3">
											<div style="padding:0 10px 0 0;">
												<input type="text" class="inputText" var="invokeUrl" style="width:100%;" />
												<input type="hidden" var="serviceName" />
												<input type="hidden" var="soapaction"/>
											</div>
										</td>
									</tr>
									<tr>
										<th><spr:message code="WebService.bindArgument"/></th>
										<td colspan="3">
											<div style="float: left; width: 30%;">
												<ul var="inputTree" class="ztree" style="overflow: auto;"></ul>
											</div>
											<div style="float: right; width: 65%; padding: 5px;"
												var="inputTreeEdit"></div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div style="display: none;" id="editField">
		<div id="custom_param_div">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th style="width:113px;text-align:center;"><spr:message code="WebService.argument.name"/>:</th>
					<td>
						<input name="name" type="text"/>
						<input name="id" type="hidden" />
					</td>
				</tr>
				<tr>
					<th style="width:113px;text-align:center;"><spr:message code="WebService.argument.type"/>:</th>
					<td>
						<select name="paramType">
							<option value="1"><spr:message code="WebService.argument.type.string"/></option>
							<option value="2"><spr:message code="WebService.argument.type.number"/></option>
							<option value="3"><spr:message code="WebService.argument.type.list"/></option>
							<option value="4"><spr:message code="WebService.argument.type.date"/></option>
						</select>
					</td>
				</tr>
				<tr>
					<th style="width:113px;text-align:center;"><spr:message code="WebService.argument.desc"/>:</th>
					<td>
						<input type="text" name="description"/>
					</td>
				</tr>
			</table>
		</div>
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
							<option value="1"><spr:message code="script.fixedValue"/></option>
							<option value="2" selected="selected"><spr:message code="personScript.argument.value.flowVar"/></option>
							<option value="3"><spr:message code="script"/></option>
						</select>
					</td>
				</tr>
				<tr bingdingType="1" style="display: none;">
					<th><spr:message code="script.defValue"/></th>
					<td colspan="3"><input type="text" class="inputText" name="defValue1" /></td>
				</tr>
				<tr bingdingType="2">
					<th><spr:message code="WebService.bindVariable"/></th>
					<td colspan="3"><span class="drag-span" name="defValue2"><spr:message code="WebService.tip"/></span></td>
				</tr>
				<tr bingdingType="2">
					<th><spr:message code="WebService.variable.type"/></th>
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
						<spr:message code="script"/>
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