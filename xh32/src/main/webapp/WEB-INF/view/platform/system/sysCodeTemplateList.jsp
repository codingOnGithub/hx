<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/include/get.jsp" %>
<f:js pre="js/lang/view/platform/system" ></f:js>
<title><spr:message code="sysCodeTemplate"/><spr:message code="title.manage"/></title>
<script type="text/javascript">
$(function(){
	handlerInit();
});
//处理初始化模板
function handlerInit()
{
	$("a.link.init").click(function(){
		var action=$(this).attr("action");
		if($(this).hasClass('disabled')) return false;
		
		$.ligerDialog.confirm($lang_system.sysCodeTemplate.confirm_msg_init,$lang.tip.confirm,function(rtn){
			if(rtn){
				var form=new com.hotent.form.Form();
				form.creatForm('form', action);
				form.submit();
			}
		});
		
	});
}
</script>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="sysCodeTemplate"/><spr:message code="title.manageList"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span><spr:message code="menu.button.add"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span><spr:message code="menu.button.alter"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link init" id="btnInit" action="init.ht"><span></span><spr:message code="menu.button.initTemplate"/></a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<ul class="row">
						<li><span class="label"><spr:message code="sysCodeTemplate.name"/>:</span><input type="text" name="${param['Q_templateName_SL']}"  class="inputText" /></li>
						<li><span class="label"><spr:message code="sysCodeTemplate.alias"/>:</span><input type="text" name="${param['Q_templateAlias_SL']}"  class="inputText" /></li>
					</ul>
				</form>
			</div>
		</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="sysCodeTemplateList" id="sysCodeTemplateItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${sysCodeTemplateItem.id}">
				</display:column>
				<display:column property="templateName" titleKey="sysCodeTemplate.name" sortable="true" sortName="TEMPLATE_NAME"></display:column>
				<display:column property="templateAlias" titleKey="sysCodeTemplate.alias" sortable="true" sortName="TEMPLATE_ALIAS"></display:column>
				<display:column property="memo" titleKey="sysCodeTemplate.memo" sortable="true" sortName="memo"></display:column>
				<display:column  titleKey="sysCodeTemplate.templateType" sortable="true" sortName="TEMPLATE_TYPE">
				<c:choose >
					<c:when test="${sysCodeTemplateItem.templateType==1}"><span class="red"><spr:message code="sysCodeTemplate.templateType.system"/></span></c:when>
					<c:otherwise><spr:message code="sysCodeTemplate.templateType.custom"/></c:otherwise>
				</c:choose>
				</display:column>
				<display:column titleKey="list.manage" media="html" style="width:180px;text-align:center">
					<c:if test="${sysCodeTemplateItem.templateType==0}">
					<a href="del.ht?id=${sysCodeTemplateItem.id}" class="link del"><spr:message code="operator.del"/></a>
					</c:if>
					<a href="edit.ht?id=${sysCodeTemplateItem.id}" class="link edit"><spr:message code="operator.edit"/></a>
					<a href="get.ht?id=${sysCodeTemplateItem.id}" class="link detail"><spr:message code="operator.detail"/></a>
				</display:column>
			</display:table>
			<hotent:paging tableId="sysCodeTemplateItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


