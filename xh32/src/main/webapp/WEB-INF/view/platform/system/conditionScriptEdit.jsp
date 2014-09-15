<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp"%>
<title><spr:message code="conditionScript"/><spr:message code="operator.edit"/></title>
<f:js pre="js/lang/view/platform/system" ></f:js>
<style type="text/css">
	html,body{
		overflow:auto;
	}
	.para-info-table thead tr th{
		text-align:center;
	}
	.para-info-table tbody tr td{
		padding:5px;
	}
	input{
		width:180px;
		height:21px;
	}
</style>
<script type="text/javascript" src="${ctx}/js/util/easyTemplate.js" ></script>
<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=conditionScript"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/ScriptEdit.js"></script>
<script type="text/javascript">

	$(function(){
		$("select[name='className']").change(classNameChange).trigger("change");
		$('#conditionScriptEdit').ajaxForm({success:showResponse });	
	});
	
	//类名选择事件
	function classNameChange(){
		var className = $(this).val();
		if(!className)return;
		
		var match = /^.*\.(\w*)$/.exec(className),
			name = '';
		if(match!=null){
			name = match[1];
		}
		if(!name)return;
		name = name.toLowerCase().split("",1) + name.slice(1);
		$("input[name='classInsName']").val(name);
		var url = __ctx + '/platform/system/conditionScript/getMethodsByName.ht?name='+className;
		getMethods(url);
	};
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="conditionScript"/><spr:message code="operator.edit"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link save" onclick="save('conditionScriptEdit')"><span></span><spr:message code="menu.button.save"/></a> 
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link back" href="list.ht"><span></span><spr:message code="menu.button.back"/></a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
		<form id="conditionScriptEdit" method="post" action="save.ht">
			<div class="panel-detail">
			<input type="hidden" name="id" value="${conditionScript.id}"/>
			<input type="hidden" id="methodName" value="${conditionScript.methodName}"/>
			<textarea class="hidden" name="argument">${conditionScript.argument}</textarea>
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">				 
				<tr>
					<th width="20%"><spr:message code="conditionScript.className"/>:</th>
					<td>
						<select name="className">
							<c:forEach items="${implClasses}" var="implClass">
								<option value="${implClass.name}"<c:if test="${conditionScript.className eq implClass.name}"> selected="selected"</c:if>>${implClass.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th width="20%"><spr:message code="conditionScript.classInsName"/>:</th>
					<td>
						<input type="text" readonly="readonly" value="${conditionScript.classInsName}" name="classInsName" />
					</td>
				</tr>
				<tr>
					<th width="20%"><spr:message code="conditionScript.methodName"/>:</th>
					<td>
						<select name="methodName">
						</select>
					</td>
				</tr>
				<tr>
					<th width="20%"><spr:message code="conditionScript.methodDesc"/>:</th>
					<td>
						<input type="text" name="methodDesc" value="${conditionScript.methodDesc}"/>
					</td>
				</tr>
				<tr>
					<th width="20%"><spr:message code="conditionScript.returnType"/>:</th>
					<td>
						<input type="text" readonly="readonly" name="returnType" value="${conditionScript.returnType}"/>
					</td>
				</tr>
				<tr>
					<th width="20%"><spr:message code="conditionScript.argument"/>:</th>
					<td id="paraInfo">
					
					</td>
				</tr> 
				<tr>
					<th width="20%"><spr:message code="conditionScript.enable"/>:</th>
					<td>
						<select name="enable">
							<option value="0"<c:if test="${conditionScript.enable eq 0}"> selected="selected"</c:if>>是</option>
							<option value="1"<c:if test="${conditionScript.enable eq 1}"> selected="selected"</c:if>>否</option>
						</select>
					</td>
				</tr>
			</table>
			</div>
		</form>
		</div>
	</div>
	
	<div class="hidden">
		<div id="para-txt">
			<table class="table-detail para-info-table" cellpadding="0" cellspacing="0" border="0">
				<thead>
					<tr>
						<th width="10%"><spr:message code="conditionScript.argument"/></th>
						<th width="25%"><spr:message code="conditionScript.argument.type"/></th>
						<th width="25%"><spr:message code="conditionScript.argument.desc"/></th>
						<th width="35%"><spr:message code="conditionScript.argument.control"/></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><span name="paraName"></span></td>
						<td><span name="paraType"></span></td>
						<td><input type="text" name="paraDesc"/></td>
						<td>
							<div class="controlType-div">
								<select name="paraCt">
									<option value="1"><spr:message code="conditionScript.argument.control.singleInput"/></option>
									<option value="4"><spr:message code="conditionScript.argument.control.user"/></option> 
									<option value="8"><spr:message code="conditionScript.argument.control.users"/></option> 
									<option value="17"><spr:message code="conditionScript.argument.control.role"/></option>
									<option value="5"><spr:message code="conditionScript.argument.control.roles"/></option> 
									<option value="18"><spr:message code="conditionScript.argument.control.org"/></option>
									<option value="6"><spr:message code="conditionScript.argument.control.orgs"/></option> 
									<option value="19"><spr:message code="conditionScript.argument.control.pos"/></option> 
									<option value="7"><spr:message code="conditionScript.argument.control.poss"/></option>
									<option value="0"><spr:message code="sysReport.customDialog" /></option>
<!--									<option value="11">下拉选项</option> -->
								</select>
								<span style="display: none;" id="settingSpan">
									<select id="dialog-type" name="dialog-type" onchange="dialogChange(this)" style="width:100px;">
										<option value=""><spr:message code="sysReport.selectDialog" />……</option>
									</select>
									<select id="dialog-param" name="dialog-param" style="width:100px;">
										<option value=""><spr:message code="sysReport.selectReturnValue" />……</option>
									</select>
								</span>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	
	<div style="display: none;" id="template">
		<textarea name="script"></textarea>
		<select id="paraCt-temp" name="paraCt"></select>
	</div>
</body>
</html>

