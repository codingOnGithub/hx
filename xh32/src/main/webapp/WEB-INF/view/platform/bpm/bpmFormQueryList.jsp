<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="bpmFormQuery.title"/><spr:message code="operator.list"/></title>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/CommonDialog.js"></script>
<script type="text/javascript">
	function preview(alias){
		CommonQuery(alias);
	}
</script>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="bpmFormQuery.title"/><spr:message code="operator.list"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span><spr:message code="menu.button.add"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span><spr:message code="menu.button.edit"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span><spr:message code="menu.button.del"/></a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<ul class="row">
						<li><span class="label"><spr:message code="bpmFormQuery.name"/>:</span><input type="text" name="Q_name_SL"  class="inputText" value="${param['Q_name_SL']}"/></li>
						<li><span class="label"><spr:message code="bpmFormQuery.alias"/>:</span><input type="text" name="Q_alias_SL"  class="inputText" value="${param['Q_alias_SL']}"/></li>
					</ul>
				</form>
			</div>
		</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="bpmFormQueryList" id="bpmFormQueryItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${bpmFormQueryItem.id}">
				</display:column>
				<display:column property="name" titleKey="bpmFormQuery.name" sortable="true" sortName="name"></display:column>
				<display:column property="alias" titleKey="bpmFormQuery.alias" sortable="true" sortName="alias"></display:column>
				<display:column property="objName" titleKey="bpmFormQuery.objName" sortable="true" sortName="objName"></display:column>
				<display:column property="dsalias" titleKey="bpmFormQuery.dataSource" sortable="true" sortName="dsalias"></display:column>
				<display:column titleKey="list.manage" media="html" style="width:50px;text-align:center" class="rowOps">
					<a href="del.ht?id=${bpmFormQueryItem.id}" class="link del"><spr:message code="menu.button.del"/></a>
					<a href="edit.ht?id=${bpmFormQueryItem.id}" class="link edit"><spr:message code="menu.button.edit"/></a>
					<a href="javascript:preview('${bpmFormQueryItem.alias}')" class="link detail"><spr:message code="menu.button.preview"/></a>
				</display:column>
			</display:table>
			<hotent:paging tableId="bpmFormQueryItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
	
	<div id="divJsonData" style="display: none;">
		<div><spr:message code="bpmFormQuery.tip1"/></div>
		<ul>
			<li><spr:message code="bpmFormQuery.tip2"/></li>
		</ul>
		<textarea id="txtJsonData" rows="10" cols="80" style="height: 180px;width:480px"></textarea>
	</div>
</body>
</html>


