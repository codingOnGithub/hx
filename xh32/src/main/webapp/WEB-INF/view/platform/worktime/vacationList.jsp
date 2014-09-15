
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@include file="/commons/include/get.jsp"%>
<html>
<head>
	<title><spr:message code="vacation.list.title" /></title>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="vacation.list.span" /></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search" /></a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link add" href="edit.ht"><span></span><spr:message code="menu.button.add" /></a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link update" id="btnUpd" action="edit.ht"><span></span><spr:message code="menu.button.alter" /></a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link del" action="del.ht"><span></span><spr:message code="menu.button.del" /></a>
					</div>
				</div>
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<ul class="row">
						<li><span class="label"><spr:message code="vacation.name" />:</span>
							<input type="text" name="Q_name_SL" class="inputText" value="${param['Q_name_SL']}"/> </li>
						<li><span class="label"><spr:message code="vacation.years" />:</span>
							<input type="text" name="Q_years_SN" class="inputText Wdate" value="${param['Q_years_SN']}" onfocus="WdatePicker({dateFmt:'yyyy'})"/>
							</li>

					</ul>
				</form>
			</div>
		</div>
		<div class="panel-body">
				<c:set var="checkAll">
					<input type="checkbox" id="chkall" />
				</c:set>
				<display:table name="vacationList" id="vacationItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
					<display:column title="${checkAll}" media="html"
						style="width:30px;">
						<input type="checkbox" class="pk" name="id"
							value="${vacationItem.id}">
					</display:column>
					<display:column property="name" titleKey="vacation.name" sortable="true"
						sortName="name"></display:column>
					<display:column property="years" titleKey="vacation.years" sortable="true"
						sortName="years"></display:column>
					<display:column titleKey="vacation.statTime" sortable="true" sortName="statTime">
						<fmt:formatDate value="${vacationItem.statTime}" pattern="yyyy-MM-dd" />
					</display:column>
					<display:column titleKey="vacation.endTime" sortable="true" sortName="endTime">
						<fmt:formatDate value="${vacationItem.endTime}" pattern="yyyy-MM-dd" />
					</display:column>
					<display:column titleKey="list.manage" media="html" style="width:180px;text-align:center" >
						<a href="del.ht?id=${vacationItem.id}" class="link del"><spr:message code="menu.button.del" /></a>
						<a href="edit.ht?id=${vacationItem.id}" class="link edit"><spr:message code="menu.button.edit" /></a>
						<a href="get.ht?id=${vacationItem.id}" class="link detail"><spr:message code="menu.button.detail" /></a>
					</display:column>
				</display:table>
				<hotent:paging tableId="vacationItem" />
			
		</div>
		<!-- end of panel-body -->
	</div>
	<!-- end of panel -->
</body>
</html>


