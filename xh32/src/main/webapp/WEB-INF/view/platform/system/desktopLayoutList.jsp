<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<%@include file="/commons/include/get.jsp" %>
	<title><spr:message code="desktopLayout.list.title" /></title>
</head>
<body>
			<div class="panel">
			<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label"><spr:message code="desktopLayout.list.span" /></span>
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
						</div>	
						
					</div>
					<div class="panel-search">
							<form id="searchForm" method="post" action="list.ht">
									<ul class="row">
												<li><span class="label"><spr:message code="desktopLayout.name" />:</span><input type="text" name="Q_name_SL"  class="inputText" value="${param['Q_name_SL']}"/></li>
									</ul>
							</form>
					</div>
				</div>
				</div>
				<div class="panel-body">
						<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
						<display:table name="desktopLayoutList" id="desktopLayoutItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" export="false"  class="table-grid">
						<display:column title="${checkAll}" media="html" style="width:30px;">
							<input type="checkbox" class="pk" name="id" value="${desktopLayoutItem.id}">
						</display:column>
						<display:column property="name" titleKey="desktopLayout.name" sortable="true" sortName="name"></display:column>
						<display:column property="cols" titleKey="desktopLayout.cols" sortable="true" sortName="cols"></display:column>
						<display:column property="width" titleKey="desktopLayout.width" sortable="true" sortName="width"></display:column>
						<display:column property="memo" titleKey="desktopLayout.memo" sortable="true" sortName="memo"></display:column>
						<display:column  titleKey="desktopLayout.isDefault" sortable="true" sortName="isDefault">
							<c:if test="${desktopLayoutItem.isDefault==1 }"><span class="green"><spr:message code="yes" /></span></c:if>
							<c:if test="${desktopLayoutItem.isDefault==0 }"><span class="red"><spr:message code="no" /></span></c:if>
						</display:column>
						<display:column titleKey="list.manage" media="html" style="width:50px;text-align:center" class="rowOps">
							<c:choose>
								<c:when test="${desktopLayoutItem.isDefault==1}">
									<a class="link setting disabled"><spr:message code="menu.button.setDefault" /></a>
								</c:when>
								<c:otherwise>
								<a class="link setting" href="setDefault.ht?id=${desktopLayoutItem.id}"><spr:message code="menu.button.setDefault" /></a>
								</c:otherwise>
							</c:choose>
							<a href="del.ht?id=${desktopLayoutItem.id}" class="link del"><spr:message code="menu.button.del" /></a>
							<a href="edit.ht?id=${desktopLayoutItem.id}" class="link edit"><spr:message code="menu.button.edit" /></a>
							<a href="${ctx}/platform/system/desktopLayoutcol/show.ht?id=${desktopLayoutItem.id}" class="link undo"><spr:message code="menu.button.setColumn" /></a>							
						</display:column>
						</display:table>
						<hotent:paging tableId="desktopLayoutItem"/>
					
				</div>			
			</div>
</body>
</html>


