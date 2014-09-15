<%@page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/commons/include/form.jsp" %>
<link rel="stylesheet" type="text/css" href="../input.css">
<style type="text/css">
	.resVal{
		width:240px;
	}
</style>
<script type="text/javascript" src="${ctx}/js/ueditor2/dialogs/internal.js"></script>
<script type="text/javascript">
	var curNode = null,
		curNodeParent = null;
	$(function(){
		curNode =  editor.$curTextNode;
		curNodeParent = domUtils.findParents(curNode,false,null,true)[0];
		getLanguages();
	});

	function getLanguages(){
		var url = __ctx + '/platform/system/sysLanguage/getAllLanguages.ht';
		$.get(url,function(data){
			data = $.parseJSON(data);
			var table = $("#table");
			for(var i=0,c;c=data[i++];){
				var input = $('<input type="text">').attr("id",c.language).addClass("resVal"),
					td = $('<td>').append(input),
					th = $('<th>').html(c.memo),
					tr = $('<tr>').append(th).append(td);
				table.append(tr);
			}
			init();
		});
	};

	function init(){
		if(!curNode)return;
		if(curNodeParent&&curNodeParent.getAttribute("i18n")){
			var i18n = curNodeParent.getAttribute("i18n").replaceAll("'", "\"");
			i18n = $.parseJSON(i18n);
			if(!i18n)return;
			for(var i=0,c;c=i18n[i++];){
				$("#"+c.lantype).val(c.value);
			}
		}
		else{
			var zh_cn_value = curNode.nodeValue.trim();
			$("#zh_CN").val(zh_cn_value);
		}
	};

	dialog.onok = function() {
		if(!curNode)return;
		
		var i18n = [];
		$("input.resVal").each(function(){
			var me = $(this),
				lantype = me.attr("id"),
				val = me.val();
			i18n.push({lantype:lantype,value:val});
		});
		if(curNodeParent&&curNodeParent.getAttribute("i18n")){
			var i18nStr = JSON2.stringify(i18n);
			curNodeParent.setAttribute("i18n",i18nStr.replaceAll("\"","'"));
		}
		else{
			var p = $("<p>"),
			next = domUtils.getNextDomNode(curNode);
			var i18nStr = JSON2.stringify(i18n);
			p.attr("i18n",i18nStr.replaceAll("\"","'"));
			$(curNode).before(p);
			p.append(curNode);
			if($(next).is('br'))
				p.append($(next));
		}
	};
</script>
</head>
<body>
	<div id="inputPanel">
		<fieldset class="base">
			<legend><var id="lang_dialog_setting"></var></legend>
			<table id="table">
				<tbody>
				</tbody>
			</table>
		</fieldset>
	</div>
</body>
</html>