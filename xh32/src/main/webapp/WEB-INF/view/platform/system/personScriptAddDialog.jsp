
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/include/form.jsp"%>
<title><spr:message code="personScript"/><spr:message code="personScript.addDialog.title"/></title>
<f:link href="form.css" ></f:link>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
<script type="text/javascript"src="${ctx}/js/hotent/platform/system/PersonScriptDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/CommonDialog.js"></script>
<script type="text/javascript">
	var winArgs = window.dialogArguments;
	var defId = winArgs.defId;
	var personScripts={};

	$(function(){
		//初始化按钮选择器
		FormUtil.handSelector();
		$(':input[id="dialog"]').live('click',function(){
			var target = $(this).attr('param');
			var dialog = $(this).attr('dialog');
			var me = $(this);
			CommonDialog(dialog,function(data){
				if(Object.prototype.toString.call((data)) == '[object Array]'){
					for(var i=0,d;d=data[i++];){
						me.closest('div').find('[name="paraValID"]').val(d[target]);
						me.closest('div').find('[name="paraVal"]').val(d[target]);
					}
				}else{
					me.closest('div').find('[name="paraValID"]').val(data[target]);
					me.closest('div').find('[name="paraVal"]').val(data[target]);
				}
			});
		});
	});
	
	function formSave(obj){
		var _this=$(obj);
		var form=$('#rulePersonScriptForm').form();
		form.setData();
		var json =$('textarea[name="json"]',form).val();
		var _this = $(obj);

		var script = getScriptJson();

		var status = script?1:0;
		var winRtn = {
			status:status,
			data:{
				script:script
			}
		};
		window.returnValue = winRtn;
		window.close();
	};

	function getScriptJson(){
		var personScriptObj = $("[name='condScriptId']").data("personScriptObj");
		if(!personScriptObj){
			return null;
		}
		if(!$.isPlainObject(personScriptObj.argument)){
			if($.type(personScriptObj.argument)=='string'){
				personScriptObj.argument=$.parseJSON(personScriptObj.argument);
			}
		}

		var json={};
		$(".para-info-table .param-tr").each(function(i,e){
			var _this = $(this);
			var paraValType = $("[name='paraValType']",_this).val();
			
			if(!personScriptObj.argument[i]){
				personScriptObj.argument[i]={};
			}
			personScriptObj.argument[i].paraValType = paraValType;
			
			switch(paraValType){
			case '1':
				personScriptObj.argument[i].paraVal = $("[name='flowVar']",_this).val();
				personScriptObj.argument[i].paraValName = $("[name='flowVar'] option:selected",_this).text();
				break;
			case '2':
				personScriptObj.argument[i].paraVal = $("[name='paraValID']",_this).val();
				personScriptObj.argument[i].paraValName = $("[name='paraVal']",_this).val();				
				break;
			}
		});
		json = $.extend(true,json,personScriptObj);
		return json;
	};

	/**
	 * 人员脚本变更处理
	 * @param obj
	 * @returns
	 */
	function personScriptChange(obj){
		var me = $(obj);
		var json = me.data("personScriptObj");
		if(json){
			var params = json.argument;
			if($.type(params)=='string'){
				params = $.parseJSON(json.argument);
			}
			constructParamTable(params);
		}
	};

	/**
	 * 选择人员脚本
	 * @param obj
	 */
	function selectPersonScript(obj){
		var me = $(obj);
		PersonScriptDialog({
			callback:function(data){
				var id = data.id;
				var json = getPersonScript(id);
				var idObj = me.parent().find("input[type='hidden']");
				var nameObj = me.parent().find("input[type='text']");
				if(json){
					idObj.val(json.id);
					nameObj.val(json.methodDesc);
					idObj.removeData("personScriptObj");
					idObj.data("personScriptObj",json);
					idObj.trigger("change");
				}
			}
		});
	};

	/**
	 * 根据人员脚本ID，取得脚本脚本
	 * @param id
	 * @returns
	 */
	function getPersonScript(id){
		if(typeof personScripts=='undefined'){
			personScripts={};
		}
		if(personScripts[id]){
			json = personScripts[id];
			return json;
		}
		var url =__ctx+ "/platform/system/personScript/getJson.ht";
		var params={
				id:id
		};
		
		var json;
		$.ajax({
			url:url,
			async:false,
			data:params
		}).done(function(data){
			if(!data.status){
				json = data.personScript;
			}else{
				//TODO error handle
			}
		});
		personScripts[id]=json;
		return json;
	}

	/**
	 * 构造参数信息表
	 * @param params
	 * @returns
	 */
	function constructParamTable(params){
		if(!params){
			params=[];
		}
		var paramTableBody = $(".para-info-table tbody").empty();
		for(var i=0;i<params.length;i++){
			var p = params[i];
			var tr = constructParamTr(p);
			paramTableBody.append(tr);
			tr.data("param",p);
			$('[name="paraValType"]',tr).trigger("change");
		}
	};

	/**
	* 构造 参数 行
	*/
	function constructParamTr(p){
		var tr = $(".param-tr-template").clone().removeClass("param-tr-template");
		$("[name='paraName']",tr).text(p.paraName);
		$("[name='paraType']",tr).text(p.paraType);
		$("[name='paraDesc']",tr).text(p.paraDesc);
		return tr;
	};


	/**
	* 参数值来源 变更
	*/
	function paraValTypeChange(obj){
		var _this=$(obj);
		var paraValType = _this.val();
		var tr = _this.closest("tr");
		var p = tr.data("param");
		var paraValTd = _this.closest("td").next("td");
		var div=null;
		switch(paraValType){
		case '1':
			div = $("#template-container [name='flowVars-div']").clone();
			break;
		case '2':
			var input = getInput(p);
			div = $("#template-container [name='custom-div']").clone();
			div.append(input);
			if(p.dialog){
				var dialog = '<input type="button" value="…" id="dialog" dialog="'+p.dialog+'" param="'+p.target+'"/>';
				div.append(dialog);
			}
			break;
		}
		paraValTd.empty().append(div);
	};


	/**
	 * 操作类型变更处理
	 * @param obj 事件源对象
	 */
	function getInput(p){
		var ct = p.paraCt;
		ct = ""+ct;
		
		var valueName = "paraValID";
	//	var datefmt = "YYYY-MM-DD"
		var getNormalInput = function(){
			input = $("#normal-input").clone(true,true).removeAttr("id").attr("name",valueName);
			return input;
		};
		
		var getSelector = function(str){
			var value="paraVal";
			var control = $("#"+str).clone(true,true).removeAttr("id");
		 	$("input[type='text']",control).attr("name","paraVal");
		 	$("input[type='hidden']",control).attr("name",valueName);
		 	$("a.link",control).attr("name",value);
			return control;
		};
		
	//	var dateInput = function(){
	//		dateInput = $("#date-input").clone(true,true).removeAttr("id").attr("datefmt",datefmt).attr("name",valueName);
	//	};
		
		var dicInput = function(){
			var control = getDicControl(flowVarOptioin,container).attr("name",valueName);
			return control;
		};


		var input;
		switch(ct){
			case "4"://用户单选
			case "8"://用户多选
				str = "user-div";
				input = getSelector(str);
				break;
			case "5"://角色
			case "17"://角色
				str = "role-div";
				input = getSelector(str);
				break;
			case "6"://组织
			case "18"://组织
				str = "org-div";
				input = getSelector(str);
				break;
			case "7"://岗位
			case "19"://岗位
				str = "position-div";
				input = getSelector(str);
				break;
			default:
				input = getNormalInput();
		}
		return input;
	};

</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
			 <span class="tbar-label"><spr:message code="personScript"/><spr:message code="personScript.addDialog.title"/></span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" onclick="formSave(this)" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.ok"/></a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link close" onclick="window.close()"><span></span><spr:message code="menu.button.close"/></a></div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="rulePersonScriptForm">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0" type="main">
				<tr>
					<th><spr:message code="personScript"/>:</th>
					<td>
						<input type="hidden" name="condScriptId" value="" onchange="personScriptChange(this)">
						<input type="text" name="condScriptName" readonly="readonly" />
						<a class="link detail" onclick="selectPersonScript(this)"><spr:message code="operator.select"/></a>
					</td>
				</tr>
			</table>
			
			<table class="table-detail para-info-table" cellpadding="0" cellspacing="0" border="0">
				<thead>
					<tr>
						<th width="10%"><spr:message code="personScript.argument.name"/></th>
						<th width="25%"><spr:message code="personScript.argument.type"/></th>
						<th width="25%"><spr:message code="personScript.argument.desc"/></th>
						<th  colspan="3"><spr:message code="personScript.argument.value"/></th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</form>
	</div>
	
	<div id="template-container" style="display: none">
		<table>
			<tr class="param-tr-template param-tr">
				<td><span name="paraName"></span></td>
				<td><span name="paraType"></span></td>
				<td><span name="paraDesc"></span></td>
				<td>
					<select name="paraValType" onchange="paraValTypeChange(this)">
						<option value="1"><spr:message code="personScript.argument.value.flowVar"/></option>
						<option value="2"><spr:message code="personScript.argument.value.fix"/></option>
					</select>
				</td>
				<td>
				</td>
			</tr>
		</table>
		<div  name="flowVars-div">
			<f:flowVar defId="${param.defId}" controlName="flowVar"></f:flowVar>
		</div>
		<div name="custom-div">
			<div name="paraValueDiv"></div>
		</div>
		<!-- 规则模板 -->
		<%@include file="/commons/include/nodeRuleTemplate.jsp" %>
	</div>
</body>
</html>

