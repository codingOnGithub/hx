
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="bpmFormRule.title"/><spr:message code="operator.list"/></title>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerComboBox.js"></script>
	<script type="text/javascript" src="${ctx}/js/lg/plugins/htDicCombo.js"></script>
	<script type="text/javascript">
	$(function() {
		$("div.group > a.link.export").click(function()
		{	
			if($(this).hasClass('disabled')) return false;
			
			var action=$(this).attr("action");
			var $aryId = $("input[type='checkbox'][disabled!='disabled'][class='pk']:checked");
			
			if($aryId.length == 0){
				$.ligerDialog.warn($lang.operateTip.selectRecord);
				return false;
			}
			
			//提交到后台服务器进行日志删除批处理的日志编号字符串
			var delId="";
			var keyName="";
			var len=$aryId.length;
			$aryId.each(function(i){
				var obj=$(this);
				if(i<len-1){
					delId+=obj.val() +",";
				}
				else{
					keyName=obj.attr("name");
					delId+=obj.val();
				}
			});
			var url=action +"?" +keyName +"=" +delId ;
			
			$.ligerDialog.confirm($lang_js.bpm.formRule.confirmExport,$lang.tip.confirm,function(rtn) {
				if(rtn) {
					var form=new com.hotent.form.Form();
					form.creatForm("form", action);
					form.addFormEl(keyName, delId);
					form.submit();
				}
			});
			return false;
		
		});
	});
	function ImportDefTableWindow(conf)
	{
		if(!conf) conf={};
		var url=__ctx + "/platform/form/bpmFormRule/import.ht";
		conf.url=url;
		var dialogWidth=550;
		var dialogHeight=250;
		conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:0,center:1},conf);
		
		var winArgs="dialogWidth="+conf.dialogWidth+"px;dialogHeight="+conf.dialogHeight
			+"px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center;
		
		var rtn=window.showModalDialog(url,"",winArgs);
		if(rtn!=null)
			window.location.reload(true);
	}
</script>
</head>
<body>
			<div class="panel">
			<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label"><spr:message code="bpmFormRule.title"/><spr:message code="operator.list"/></span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link add" href="edit.ht"><span></span><spr:message code="menu.button.add"/></a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span><spr:message code="menu.button.alter"/></a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link del"  action="del.ht"><span></span><spr:message code="menu.button.del"/></a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link export"  action="export.ht"><span></span><spr:message code="menu.button.export"/></a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link import"  href="#"  onclick="ImportDefTableWindow()"><span></span><spr:message code="menu.button.import"/></a></div>
						</div>	
					</div>
					<div class="panel-search">
							<form id="searchForm" method="post" action="list.ht">
									<ul class="row">
												<li><span class="label"><spr:message code="bpmFormRule.name"/>:</span><input type="text" name="Q_name_SL"  class="inputText" value="${param['Q_name_SL']}"/></li>
									</ul>
							</form>
					</div>
				</div>
				</div>
				<div class="panel-body">
					<c:set var="checkAll">
						<input type="checkbox" id="chkall"/>
					</c:set>
				    <display:table name="bpmFormRuleList" id="bpmFormRuleItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"  class="table-grid">
						<display:column title="${checkAll}" media="html" style="width:30px;">
							  	<input type="checkbox" class="pk" name="id" value="${bpmFormRuleItem.id}">
						</display:column>
						<display:column property="name" titleKey="bpmFormRule.name" style="text-align:left" ></display:column>
						<display:column property="rule" titleKey="bpmFormRule.rule" style="text-align:left"></display:column>
						<display:column property="tipInfo" titleKey="bpmFormRule.tipInfo" style="text-align:left"></display:column>
						<display:column property="memo" titleKey="bpmFormRule.memo" style="text-align:left"></display:column>
						<display:column title="管理" media="html" style="width:50px;text-align:center" class="rowOps">
							<a href="del.ht?id=${bpmFormRuleItem.id}" class="link del"><spr:message code="menu.button.del"/></a>
							<a href="edit.ht?id=${bpmFormRuleItem.id}" class="link edit"><spr:message code="menu.button.edit"/></a>
							<a href="get.ht?id=${bpmFormRuleItem.id}" class="link detail"><spr:message code="menu.button.detail"/></a>
						</display:column>
					</display:table>
					<hotent:paging tableId="bpmFormRuleItem"/>
					
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
</html>


