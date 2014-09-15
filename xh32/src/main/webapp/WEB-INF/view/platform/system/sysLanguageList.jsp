<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="sysLanguage"/><spr:message code="title.manageList"/></title>

</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="sysLanguage"/><spr:message code="title.manageList"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					
					<div class="group"><a class="link add" href="edit.ht"><span></span><spr:message code="menu.button.add"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span><spr:message code="menu.button.alter"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span><spr:message code="menu.button.del"/></a></div>
				</div>	
			</div>
			
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="sysLanguageList" id="sysLanguageItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"   class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${sysLanguageItem.id}">
				</display:column>
				<display:column property="language" titleKey="sysLanguage.language" sortable="true" sortName="language"></display:column>
				<display:column titleKey="sysLanguage.isdefault" sortable="true" sortName="isdefault">
					<c:choose>
						<c:when test="${sysLanguageItem.isdefault==1}">
							<span class="green"><spr:message code="yes"/></span>
						</c:when>
						<c:when test="${sysLanguageItem.isdefault==0}">
							<span class="red"><spr:message code="no"/></span>
						</c:when>
						<c:otherwise>
							<span class="red"><spr:message code="menu.button.disabled"/></span>
						</c:otherwise>
						
					</c:choose>
				 
				</display:column>
				<display:column property="memo" titleKey="sysLanguage.memo" sortable="true" sortName="memo"></display:column>
				<display:column titleKey="list.manage" media="html" style="width:240px;text-align:center">
					<a href="edit.ht?id=${sysLanguageItem.id}" class="link edit"><spr:message code="menu.button.edit" /></a>
					<c:if test="${sysLanguageItem.isdefault==-1}">
						<a href="enable.ht?id=${sysLanguageItem.id}" class="link switchuser"><spr:message code="menu.button.enable" /></a>
						<a href="del.ht?id=${sysLanguageItem.id}" class="link del"><spr:message code="menu.button.del" /></a>
					</c:if>
					<c:if test="${sysLanguageItem.isdefault==0}">
						<a href="disable.ht?id=${sysLanguageItem.id}" class="link switchuser"><spr:message code="menu.button.disabled" /></a>
						<a href="setDefault.ht?id=${sysLanguageItem.id}" class="link setting"><spr:message code="menu.button.setDefault" /></a>
						<a href="del.ht?id=${sysLanguageItem.id}" class="link del"><spr:message code="menu.button.del" /></a>
					</c:if>
				</display:column>
			</display:table>
			
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


