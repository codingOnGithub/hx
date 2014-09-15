
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="sysOfficeTemplate"/><spr:message code="title.manage"/></title>
<style type="text/css">
	html,body{ padding:0px; margin:0; width:100%;height:100%;overflow: hidden;}
</style>	
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="sysOfficeTemplate"/><spr:message code="title.manageList"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span><spr:message code="menu.button.add"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span><spr:message code="menu.button.alter"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span><spr:message code="menu.button.del"/></a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<ul class="row">
						<li><span class="label"><spr:message code="sysOfficeTemplate.subject"/>:</span><input type="text" name="Q_subject_SL"  class="inputText" value="${param['Q_subject_SL']}"/></li>
						<li><span class="label"><spr:message code="sysOfficeTemplate.templateType"/>:</span>
						<select name="Q_templatetype_S">
							<option value=""><spr:message code="select.pleaseChoose"/></option>
							<option value="1" <c:if test="${param['Q_templatetype_S'] == 1}">selected</c:if>><spr:message code="sysOfficeTemplate.templateType.common"/></option>
							<option value="2" <c:if test="${param['Q_templatetype_S'] == 2}">selected</c:if>><spr:message code="sysOfficeTemplate.templateType.nest"/></option>
						</select></li>
					</ul>
				</form>
			</div>
			</div>
		</div>
		<div class="panel-body">
			
		    	<c:set var="checkAll">
					<input type="checkbox" id="chkall"/>
				</c:set>
			    <display:table name="sysOfficeTemplateList" id="sysOfficeTemplateItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"   class="table-grid">
					<display:column title="${checkAll}" media="html" style="width:30px;">
						  	<input type="checkbox" class="pk" name="id" value="${sysOfficeTemplateItem.id}">
						  	<input type="hidden" class="rtn" name="rtn" value="${sysOfficeTemplateItem.id},${ sysOfficeTemplateItem.subject},${sysOfficeTemplateItem.path}">
					</display:column>
					<display:column property="subject" titleKey="sysOfficeTemplate.subject" sortable="true" sortName="subject"></display:column>
					<display:column titleKey="sysOfficeTemplate.templateType" sortable="true" sortName="templatetype">
						<c:choose>
							<c:when test="${sysOfficeTemplateItem.templatetype==1 }"><spr:message code="sysOfficeTemplate.templateType.common"/></c:when>
							<c:otherwise>
								<spr:message code="sysOfficeTemplate.templateType.nest"/>
							</c:otherwise>
						</c:choose>
					</display:column>
					<display:column property="creator" titleKey="sysOfficeTemplate.creator" sortable="true" sortName="creator"></display:column>
					<display:column  titleKey="sysOfficeTemplate.createtime" sortable="true" sortName="createtime">
						<fmt:formatDate value="${sysOfficeTemplateItem.createtime}" pattern="yyyy-MM-dd"/>
					</display:column>
					<display:column titleKey="list.manage" media="html"  style="width:180px;text-align:center">
						<a href="del.ht?id=${sysOfficeTemplateItem.id}" class="link del"><spr:message code="operator.del"/></a>
						<a href="edit.ht?id=${sysOfficeTemplateItem.id}" class="link edit"><spr:message code="operator.edit"/></a>
						<a href="get.ht?id=${sysOfficeTemplateItem.id}" class="link detail"><spr:message code="operator.detail"/></a>
					</display:column>
				</display:table>
				<hotent:paging tableId="sysOfficeTemplateItem"/>
			
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


