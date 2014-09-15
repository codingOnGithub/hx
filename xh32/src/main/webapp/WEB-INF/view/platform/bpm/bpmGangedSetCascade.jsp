<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/commons/include/form.jsp" %>
<title><spr:message code="bpmGangedSet.setCascade.title"/></title>
<f:js pre="js/lang/view/platform/bpm" ></f:js>
<link href="${ctx}/js/jquery/plugins/token-input-normal.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.tokeninput.js"></script>
<script type="text/javascript">
	$(function(){
		$(".token-input").tokenInput('',{theme:"normal"});
		init();
	});
	//初始化
	function init(){
		var cascadeSet = window.dialogArguments ;
		$("#fieldKey").val(cascadeSet.text);
		$("#guid-input").val(cascadeSet.guid);
		if(!cascadeSet.ops)return;

		var ops = eval("("+cascadeSet.ops+")"),
			options = [];
		for(var i=0,c;c=ops[i++];){
			options.push('<option value="');
			options.push(c.key);
			options.push('">');
			options.push(c.value);
			options.push('</option>');
		}
		$("#options-select").html(options.join(''));
		if(!cascadeSet.cascade)return;
		var set = cascadeSet.cascade;
		var reduceSet = set.reduce;
		
		for(var j=0,n;n=reduceSet[j++];){
			$("#reduce-input").tokenInput("add",n);
		}
	};
	//添加
	function addOption(t){
		$("#options-select").find("option:selected").each(function(){
			var obj = {},
				val = $(this).val(),
				txt = $(this).text();

			obj.id = val;
			obj.name = txt;
			$("#"+t).tokenInput("add",obj);
		});
	};
	//清空
	function cleanOption(t){
		$("#"+t).tokenInput("clear");
	};
	//获取级联设置
	function saveCascadeSet(){
		var obj = {},
			reduceSet = $("#reduce-input").tokenInput("get"),
			guid = $("#guid-input").val();

		if(reduceSet.length==0){
			$.ligerDialog.warn($lang_bpm.bpmGangedSet.cascadeSetTip,$lang.tip.msg);
			return;
		}		
		obj.guid = guid;
		obj.reduce = reduceSet;
		window.returnValue = obj; 
        window.close();
	};
</script>
</head>
<body>
   	<div id="defLayout" >
 		<table class="table-grid" cellpadding="1" cellspacing="1">
			<tr>
				<th><spr:message code="bpmGangedSet.setCascade.fieldKey"/>:</th>
				<td>
					<input disabled="disabled" style="height:21px;width:400px;" id="fieldKey"/>
					<input type="hidden" id="guid-input"/>
				</td>
			</tr>
			<tr>
				<th><spr:message code="bpmGangedSet.setCascade.origOption"/>:</th>
				<td>
					<select id="options-select" multiple="multiple" size="10" style="width:404px;">							
					</select>
				</td>
			</tr>
			<tr>
				<th><spr:message code="bpmGangedSet.setCascade.reduceOption"/>:</th>
				<td>
					<textarea class="token-input" id="reduce-input"></textarea>
					<a class="link add" onclick="addOption('reduce-input')"><spr:message code="menu.button.add"/></a>
					<a class="link del" onclick="cleanOption('reduce-input')"><spr:message code="menu.button.empty"/></a>
				</td>
			</tr>
 		</table>
     </div>
	<div position="bottom"  class="bottom" style="height:50px;" >
		<a href="#" class="button"  onclick="saveCascadeSet()" style="margin-right:30px;"><span class="icon ok"></span><span class="chosen"><spr:message code="menu.button.ok"/></span></a>
		<a href="#" class="button" style="margin-left:10px;"  onclick="window.close()"><span class="icon cancel" ></span><span class="cance" ><spr:message code="menu.button.cancel"/></span></a>
	</div>
</body>
</html>
