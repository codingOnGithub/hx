
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<%@include file="/commons/include/get.jsp" %>
	<title><spr:message code="calendarAssign.list.title" /></title>
	<f:js pre="js/lang/view/platform/system" ></f:js>
</head>
<body>

<c:if test="${flag==1}">
	<script type="text/javascript">
		$.ligerDialog.warn($lang_system.calendarAssign.setCalendarFirst,$lang.tip.warn,function(rtn){});
	</script>
</c:if>

	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="calendarAssign.list.span" /></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search" /></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span><spr:message code="menu.button.add" /></a></div>
					<div class="l-bar-separator"></div>
					<!--<div class="group"><a class="link update" id="btnUpd" action="edit.ht"></span><spr:message code="menu.button.alter" /></a></div>
					<div class="l-bar-separator"></div>-->
					<div class="group"><a class="link del"  action="del.ht"><span></span><spr:message code="menu.button.del" /></a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<ul class="row">
						<li><span class="label"><spr:message code="calendarAssign.calendar" />:</span><input type="text" name="Q_calendarName_SL" class="inputText" value="${param['Q_calendarName_SL']}"/></li>
						<li><span class="label"><spr:message code="calendarAssign.assignUserName" />:</span><input type="text" name="Q_assignUserName_SL" class="inputText" value="${param['Q_assignUserName_SL']}"/></li>
							
					</ul>
				</form>
			</div>
		</div>
		</div>
		<div class="panel-body">
			
		    	<c:set var="checkAll">
					<input type="checkbox" id="chkall"/>
				</c:set>
			    <display:table name="calendarAssignList" id="calendarAssignItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"    class="table-grid">
					<display:column title="${checkAll}" media="html" style="width:30px;">
						<input type="checkbox" class="pk" name="id" value="${calendarAssignItem.id}">
					</display:column>
					<display:column titleKey="calendarAssign.calendar" sortable="true" sortName="canlendarId">
					<c:out value="${calendarAssignItem.calendarName}"></c:out>
					</display:column>
					<display:column titleKey="calendarAssign.assignType" sortable="true" sortName="assignType">
						<c:if test="${calendarAssignItem.assignType==1}">
							<spr:message code="calendarAssign.user" />	
						</c:if>
						<c:if test="${calendarAssignItem.assignType==2}">
							<spr:message code="calendarAssign.organization" />
						</c:if>
					</display:column>
					<display:column titleKey="calendarAssign.assignUserName" sortable="true" sortName="assignId">
					<c:out value="${calendarAssignItem.assignUserName}"></c:out>
					</display:column>
					<display:column titleKey="list.manage" media="html" style="width:180px;text-align:center">
						<a href="del.ht?id=${calendarAssignItem.id}" class="link del"><spr:message code="menu.button.del" /></a>
						<a href="get.ht?id=${calendarAssignItem.id}" class="link detail"><spr:message code="menu.button.detail" /></a>
					</display:column>
				</display:table>
				<hotent:paging tableId="calendarAssignItem"/>
			
		</div><!-- end of panel-body -->
	</div> <!-- end of panel -->
</body>
</html>


