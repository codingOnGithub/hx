<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<%@include file="/commons/include/get.jsp" %>
	<title><spr:message code="desktopLayoutcol.list.title" /></title>
</head>
<body>
			<div class="panel">
			<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label"><spr:message code="desktopLayoutcol.list.span" /></span>
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
							<div class="group"><a class="link show"  action="show.ht"><span></span><spr:message code="desktopLayoutcol.showByPicture" /></a></div>							
						</div>	
					</div>
				</div>
				</div>
				<div class="panel-body">
					<div class="panel-search">
							<form id="searchForm" method="post" action="list.ht">
									<ul class="row">
										<li><span class="label"><spr:message code="desktopLayoutcol.layoutName" />:</span><input type="text" name="Q_layoutId_S"  class="inputText" value="${param['Q_layoutId_S']}"/></li>
									</ul>
							</form>
					</div>
					<br/>
					<div class="panel-data">
				    	<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <display:table name="desktopLayoutcolList" id="desktopLayoutcolItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" export="false"  class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;">
							<input type="checkbox" class="pk" name="id" value="${desktopLayoutcolItem.id}">
							</display:column>
							<display:column property="layoutName" titleKey="desktopLayoutcol.layoutName" sortable="true" sortName="layoutName"></display:column>
							<display:column property="columnName" titleKey="desktopLayoutcol.columnName" sortable="true" sortName="columnName"></display:column>
							<display:column property="col" titleKey="desktopLayoutcol.col" sortable="true" sortName="col"></display:column>
							<display:column property="sn" titleKey="desktopLayoutcol.sn" sortable="true" sortName="sn"></display:column>
							<display:column titleKey="list.manage" media="html" style="width:180px;text-align:center">
							<a href="del.ht?id=${desktopLayoutcolItem.id}" class="link del"><spr:message code="menu.button.del" /></a>
							<a href="edit.ht?id=${desktopLayoutcolItem.id}" class="link edit"><spr:message code="menu.button.edit" /></a>
							<a href="get.ht?id=${desktopLayoutcolItem.id}" class="link detail"><spr:message code="menu.button.detail" /></a>
							</display:column>
						</display:table>
						<hotent:paging tableId="desktopLayoutcolItem"/>
					</div>
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
</html>


