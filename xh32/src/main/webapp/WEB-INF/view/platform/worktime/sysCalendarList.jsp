
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<%@include file="/commons/include/get.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><spr:message code="sysCalendar.list.title" /></title>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="sysCalendar.list.span" /></span>
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
						<li><span class="label"><spr:message code="sysCalendar.name" />:</span><input type="text" name="Q_name_SL"  class="inputText" value="${param['Q_name_SL']}"/></li>
					</ul>
				</form>
			</div>
		</div>
		</div>
		<div class="panel-body">
			
		    	<c:set var="checkAll">
					<input type="checkbox" id="chkall"/>
				</c:set>
			    <display:table name="sysCalendarList" id="sysCalendarItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"  class="table-grid">
					<display:column title="${checkAll}" media="html" style="width:30px;">
						  	<input type="checkbox" class="pk" name="id" value="${sysCalendarItem.id}">
					</display:column>
					<display:column property="name" titleKey="sysCalendar.name" sortable="true" sortName="name"></display:column>
					<display:column titleKey="sysCalendar.default" sortable="true" sortName="name" style="text-align:center;">
						<c:choose>
							<c:when test="${sysCalendarItem.isDefault==0 }">
								<span class="red"><spr:message code="sysCalendar.notDefault" /></span>
							</c:when>
							<c:otherwise>
								<span class="green"><spr:message code="sysCalendar.default" /></span>
							</c:otherwise>
						</c:choose>
					</display:column>
					<display:column property="memo" titleKey="sysCalendar.memo" sortable="true" sortName="memo" maxLength="80"></display:column>
					<display:column titleKey="list.manage" media="html" style="width:180px;text-align:center" >
						<a href="edit.ht?id=${sysCalendarItem.id}" class="link edit"><spr:message code="menu.button.edit" /></a>
						<c:if test="${sysCalendarItem.isDefault==0}">
							<a href="setDefault.ht?id=${sysCalendarItem.id}" class="link detail"><spr:message code="sysCalendar.setDefault" /></a>
							<a href="del.ht?id=${sysCalendarItem.id}" class="link del"><spr:message code="menu.button.del" /></a>
						</c:if>
						<!--<a href="get.ht?id=${sysCalendarItem.id}" class="link detail"><spr:message code="menu.button.detail" /></a>-->
					</display:column>
				</display:table>
				<hotent:paging tableId="sysCalendarItem"/>
			
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


