
<%--
	time:2012-11-27 10:37:13
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/getById.jsp"%>
<title></title>
<f:js pre="js/lang/view/platform/form" ></f:js>
<script type="text/javascript" src="${ctx}/js/util/util.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/CommonDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/util/json2.js"></script>
<script type="text/javascript">
	var condition = ${bpmFormQuery.conditionfield};
	var alias = "${bpmFormQuery.alias}";
	
	$(function(){
		initUI();
		//查询
		$("#btnSearch").click(search);
		//帮助
		$("a.detail").click(help);
	});
	
	function initUI(){
		var table = $("#queryCondition"),
		columnNum = 2,
		html = [];
		for(var i=0,c;c=condition[i++];){
			if(c.defaultType == 5 && !c.field) continue;
			var columnRes = i % columnNum;
			if(columnRes == 1)
				html = ['<tr>'];
			html.push('<th>');
			html.push(c.comment);
			html.push('</th><td>');
			html.push('<input type="input" class="inputText" name="');
			html.push(c.field);
			html.push('"');
			if(c.defaultType == 2 || c.defaultType == 3){
				html.push(' disabled="disabled"');
				html.push(' value="');
				html.push(c.defaultValue);
				html.push('"');
			}
			html.push(' /></td>');
			if(columnRes == 0){
				html.push('</tr>');
				table.append(html.join(''));
				html = [];
			}
		}
		html.push('</tr>');
		table.append(html.join(''));
	}
	
	
	//查询
	function search (){	
		var div = $("#resultDiv");			
		div.html('<span class="brown">'+$lang.tip.waiting+'</span>');
		var data = getQueryData();
		var condition = {alias:alias,page:1,pagesize:10,querydata:data};
		DoQuery(condition,function(data){
			div.html('');
			if(data.errors)
				div.html('<span class="red">'+data.errors+'</span>');
			else{
				var html = JSON2.stringify(data.list);
				div.html(html);
			}					
		});
	}

	function getQueryData(){
		var data = [];
		$("#queryCondition tr").each(function(){
			var tr = $(this);				
			$("input",tr).each(function(){
				var input = $(this);
				if(!input.attr("disabled")){
					var item ='"' + input.attr("name") + '":"' + input.val() + '"';
					data.push(item);
				}
			});
		});
		data = '{' + data.join(',') + '}';		
		return data;
	};
	
	//帮助
	function help(){
			var html = ['<br /><table class="table-detail" cellpadding="0" cellspacing="0" border="0">'];			
			html.push('<tr><th width="90">'+$lang_form.bpmFormQuery.get.postParam+':</th><td>');
			html.push('var <span class="brown">querydata</span> = \'');
			html.push(getQueryData());			
			html.push('\',<br /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="brown">condition</span> = {alias:"');
			html.push(alias);
			html.push('",page:1,pagesize:10,querydata:<span class="brown">querydata</span>};</td></tr>');			
			html.push('<tr><th>'+$lang_form.bpmFormQuery.get.callMethod+':</th><td>');
			html.push('DoQuery(<span class="brown">condition</span>');
			html.push(',function(<span class="brown">data</span>){});<br />'+String.format($lang_form.bpmFormQuery.get.callMethodTip,'<span class="green">${ctx}/js/sf/platform/form/CommonDialog.js</span>'));
			html.push('</td></tr>');
			html.push('<tr><th>'+$lang_form.bpmFormQuery.get.callbackMethod+':</th><td>');
			html.push(String.format($lang_form.bpmFormQuery.get.callbackMethodTip,'<span class="brown">data</span>','<span class="green">QueryResult</span>'));
			html.push('</td></tr>');
			html.push('<tr><th>'+$lang_form.bpmFormQuery.get.otherNotes+':</th><td>1、'+
					String.format($lang_form.bpmFormQuery.get.otherNotes1,'<span class="brown">page</span>','<span class="brown">pagesize</span>')+'<br />');
			html.push('2、'+
					String.format($lang_form.bpmFormQuery.get.otherNotes2,'<span class="brown">alias</span>','<span class="brown">querydata</span>')+'<br />');
			html.push('3、'+
					String.format($lang_form.bpmFormQuery.get.otherNotes3,'<span class="brown">data.errors</span>','<span class="brown">data.list</span>','<span class="brown">data.isPage</span>','<span class="brown">data.totalCount</span>','<span class="brown">data.totalPage</span>')+'<br />');
			html.push('</td></tr>');
			$("#resultDiv").html(html.join(''));
	}
	
	
</script>
</head>
<body>
	<div class="panel">	
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group">
					<a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a>
					<div class="l-bar-separator"></div>
					<a class="link detail"><span></span><spr:message code="menu.button.help"/></a>
				</div>
			</div>	
		</div>	
		<div class="panel-body">
			<table class="table-detail" id="queryCondition" cellpadding="0" cellspacing="0" border="0"></table>
		</div>
		<div id="resultDiv">			
		</div>
	</div>
</body>
</html>

