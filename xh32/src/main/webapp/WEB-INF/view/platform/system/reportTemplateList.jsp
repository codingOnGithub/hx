
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.hotent.platform.model.system.GlobalType"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="reportTemplate.list.title" /></title>
</head>
<body>

	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				 <span class="tbar-label"><spr:message code="reportTemplate.list.span" /></span>		
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search" /></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span><spr:message code="menu.button.add" /></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span></span><spr:message code="menu.button.alter" /></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span><spr:message code="menu.button.del" /></a></div>
				</div>	
			</div>
			
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<ul class="row">
						<li><span class="label"><spr:message code="reportTemplate.title" />:</span><input type="text" name="Q_title_SL"  class="inputText" value="${param['Q_title_SL']}"/></li>
					</ul>
				</form>
			</div>
		</div>
		</div>
		<div class="panel-body">
			
			
		    	<c:set var="checkAll">
					<input type="checkbox" id="chkall"/>
				</c:set>
			    <display:table name="reportTemplateList" id="reportTemplateItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"   class="table-grid">
					<display:column title="${checkAll}" media="html" style="width:30px;">
						  	<input type="checkbox" class="pk" name="reportId" value="${reportTemplateItem.reportId}">
					</display:column>
					<display:column property="title" titleKey="reportTemplate.title" sortable="true" sortName="title"></display:column>
					<display:column property="reportLocation" titleKey="reportTemplate.reportLocation" sortable="true" sortName="reportLocation"></display:column>
					<display:column  titleKey="reportTemplate.createTime" sortable="true" sortName="createTime">
						<fmt:formatDate value="${reportTemplateItem.createTime}" pattern="yyyy-MM-dd"/>
					</display:column>
					<display:column  titleKey="reportTemplate.updateTime" sortable="true" sortName="updateTime">
						<fmt:formatDate value="${reportTemplateItem.updateTime}" pattern="yyyy-MM-dd"/>
					</display:column>
					<display:column titleKey="reportTemplate.isDefaultIn" sortable="true" sortName="isDefaultIn">
						<c:if test="${reportTemplateItem.isDefaultIn==1}"><spr:message code="yes" /></c:if>
						<c:if test="${reportTemplateItem.isDefaultIn==0}"><spr:message code="no" /></c:if>
					</display:column>
					<display:column titleKey="list.manage" media="html" style="width:50px;text-align:center" class="rowOps">
						<a href="<%=request.getContextPath()%>${reportTemplateItem.reportSeverlet}${reportTemplateItem.fileName}" 
							class="link detail"><spr:message code="menu.button.preview" /></a>
						<!-- <a href="get.ht?reportId=${reportTemplateItem.reportId}" class="link-detail"><span class="link-btn"><spr:message code="reportTemplate.setParam" /></span></a> -->
						<a href="edit.ht?reportId=${reportTemplateItem.reportId}" class="link edit"><spr:message code="menu.button.edit" /></a>
						<a href="del.ht?reportId=${reportTemplateItem.reportId}" class="link del"><spr:message code="menu.button.del" /></a>
					</display:column>
				</display:table>
				<hotent:paging tableId="reportTemplateItem"/>
			
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->

</body>
</html>


