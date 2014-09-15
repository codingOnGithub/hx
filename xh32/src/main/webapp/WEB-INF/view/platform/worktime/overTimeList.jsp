
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="overTime.list.title" /></title>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="overTime.list.span" /></span>
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
						<li><span class="label"><spr:message code="overTime.subject" />:</span><input type="text" name="Q_subject_SL"  class="inputText" value="${param['Q_subject_SL']}"/></li>
					
						<li><span class="label"><spr:message code="overTime.userName" />:</span><input type="text" name="Q_userName_SL"  class="inputText" value="${param['Q_userName_SL']}"/></li>
						
						<div class="row_date">	
						<li><span class="label"><spr:message code="overTime.overTime" />:</span><input name="Q_startTime_DL"  class="inputText datetime" value="${param['Q_startTime_DL']}"/></li>
							
						<li><span class="label"><spr:message code="overTime.to" /></span><input name="Q_endTime_DG"  class="inputText datetime" value="${param['Q_endTime_DG']}"/></li>
						</div>
					</ul>
				</form>
			</div>
		</div>
		</div>
		<div class="panel-body">
			
		    	<c:set var="checkAll">
					<input type="checkbox" id="chkall"/>
				</c:set>
			    <display:table name="overTimeList" id="overTimeItem" requestURI="list.ht" 
			    	sort="external" cellpadding="1" cellspacing="1"   class="table-grid">
					<display:column title="${checkAll}" media="html" style="width:30px;">
						<input type="checkbox" class="pk" name="id" value="${overTimeItem.id}">
					</display:column>
					<display:column titleKey="overTime.subject" sortable="true" sortName="subject" property="subject" />
					<display:column titleKey="overTime.userName" sortable="true" sortName="userId">
						<c:out value="${overTimeItem.userName}"></c:out>
					</display:column>
					<display:column titleKey="overTime.workType" sortable="true" sortName="workType">
						<c:if test="${overTimeItem.workType==1}">
							<spr:message code="overTime.over" />
						</c:if>
						<c:if test="${overTimeItem.workType==2}">
							<spr:message code="overTime.leave" />
						</c:if>
					</display:column>
					<display:column  titleKey="overTime.startTime" sortable="true" sortName="startTime">
						<fmt:formatDate value="${overTimeItem.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</display:column>
					<display:column  titleKey="overTime.endTime" sortable="true" sortName="endTime">
						<fmt:formatDate value="${overTimeItem.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</display:column>
					<display:column titleKey="list.manage" media="html" style="width:180px;text-align:center" >
						<a href="del.ht?id=${overTimeItem.id}" class="link del"><spr:message code="menu.button.del" /></a>
						<a href="edit.ht?id=${overTimeItem.id}" class="link edit"><spr:message code="menu.button.edit" /></a>
						<a href="get.ht?id=${overTimeItem.id}" class="link detail"><spr:message code="menu.button.detail" /></a>
					</display:column>
				</display:table>
				<hotent:paging tableId="overTimeItem"/>
			
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


