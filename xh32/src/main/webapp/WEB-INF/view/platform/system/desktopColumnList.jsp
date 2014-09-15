<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="desktopColumn.list.title" /></title>
<f:js pre="js/lang/view/platform/system" ></f:js>
<script type="text/javascript">
function previewTemplate(url){
	var winArgs="dialogWidth=400px;dialogHeight=400px;help=0;status=0;scroll=1;center=1" ;
	url=url.getNewUrl();
	var rtn=window.showModalDialog(url,null,winArgs);
	
}


//处理初始化模板
function handlerInit()
{
	$("a.link.init").click(function(){
		var action=$(this).attr("action");
		if($(this).hasClass('disabled')) return false;
		
		$.ligerDialog.confirm($lang_system.desktopColumn.confirm,$lang.tip.confirm,function(rtn){
			if(rtn){
				var form=new com.hotent.form.Form();
				form.creatForm('form', action);
				form.submit();
			}
		});
		
	});
}

$(function(){
	handlerInit();
})
</script>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="desktopColumn.list.span" /></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search" /></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span><spr:message code="menu.button.add" /></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span><spr:message code="menu.button.alter" /></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span><spr:message code="menu.button.del" /></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a  class="link init" id="bntInit" action="init.ht"><span></span><spr:message code="menu.button.initTemplate" /></a></div>
				</div>	
			</div>
			<div class="panel-search">
					<form id="searchForm" method="post" action="list.ht">
							<ul class="row">
								<li><span class="label"><spr:message code="desktopColumn.columnName" />:</span><input type="text" name="Q_columnName_SL"  class="inputText" value="${param['Q_columnName_SL']}"/></li>
							    <li><span class="label"><spr:message code="desktopColumn.templateName" />:</span><input type="text" name="Q_templateName_SL"  class="inputText" value="${param['Q_templateName_SL']}"/></li>
							</ul>
					</form>
			</div>
		</div>
		</div>
		<div class="panel-body">
				<c:set var="checkAll">
					<input type="checkbox" id="chkall"/>
				</c:set>
			    <display:table name="desktopColumnList" id="desktopColumnItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" export="false"  class="table-grid">
					<display:column title="${checkAll}" media="html" style="width:30px;">
					<input type="checkbox" class="pk" name="id" value="${desktopColumnItem.id}">
					</display:column>
					<display:column property="columnName" titleKey="desktopColumn.columnName" sortable="true" sortName="name"></display:column>
					<display:column titleKey="desktopColumn.dataLoadType" sortable="true" sortName="methodType">
						<c:choose>
							<c:when test="${desktopColumnItem.methodType==0}">
								<span class="red"><spr:message code="desktopColumn.dataLoad.service" /></span>
							</c:when>
							<c:otherwise>
								<span class="green"><spr:message code="desktopColumn.dataLoad.customQuery" /></span>
							</c:otherwise>
						</c:choose>
					</display:column>
					<display:column property="columnUrl" titleKey="desktopColumn.columnUrl" sortable="true" sortName="columnUrl"></display:column>
					<display:column titleKey="list.manage" media="html" style="width:50px;text-align:center" class="rowOps">
						<a href="del.ht?id=${desktopColumnItem.id}" class="link del"><spr:message code="menu.button.del" /></a>
						<a href="edit.ht?id=${desktopColumnItem.id}" class="link edit"><spr:message code="menu.button.edit" /></a>
						<a href="get.ht?id=${desktopColumnItem.id}" class="link detail"><spr:message code="menu.button.detail" /></a>
						<a href="#" onclick="previewTemplate('getTemp.ht?id=${desktopColumnItem.id}');" class="link preview"><spr:message code="menu.button.preview" /></a>
					</display:column>
				</display:table>
				<hotent:paging tableId="desktopColumnItem"/>
			
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


