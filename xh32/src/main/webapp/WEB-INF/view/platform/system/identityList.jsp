<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<%@include file="/commons/include/get.jsp" %>
	<title><spr:message code="identity.title"/><spr:message code="operator.manageList"/></title>
</head>
<body>		
	<div class="panel">
	<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label"><spr:message code="identity.title"/><spr:message code="operator.manageList"/></span>
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
										<li><span class="label"><spr:message code="identity.name"/>:</span><input type="text" name="Q_name_SL"  class="inputText" value="${param['Q_name_SL']}"/></li>
										<li><span class="label"><spr:message code="identity.alias"/>:</span><input type="text" name="Q_alias_SL"  class="inputText" value="${param['Q_alias_SL']}"/></li>
								</ul>
						</form>
					</div>
				</div>
				</div>
				<div class="panel-body">
						<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <display:table name="identityList" id="identityItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"  class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;">
								  	<input type="checkbox" class="pk" name="id" value="${identityItem.id}">
							</display:column>
							<display:column property="name" titleKey="identity.name" sortable="true" sortName="name"></display:column>
							<display:column property="alias" titleKey="identity.alias" sortable="true" sortName="alias"></display:column>
							<display:column property="rule" titleKey="identity.rule" sortName="REGULATION"></display:column>
							<display:column  titleKey="identity.genType" style="text-align:center" >
								<c:choose>
									<c:when test="${identityItem.genType==1}">
										<span class="green"><spr:message code="identity.genType.date"/></span>
									</c:when>
									<c:when test="${identityItem.genType==2}">
										<span class="green"><spr:message code="identity.genType.month"/></span>
									</c:when>
									<c:when test="${identityItem.genType==3}">
										<span class="green"><spr:message code="identity.genType.year"/></span>
									</c:when>
									<c:otherwise>
										<span class="red"><spr:message code="identity.genType.increase"/></span>
									</c:otherwise>
								</c:choose>
							</display:column>
							<display:column property="noLength" titleKey="identity.noLength" sortable="true" sortName="noLength"></display:column>
							<display:column property="initValue" titleKey="identity.initValue" sortable="true" sortName="initValue"></display:column>
						
							<display:column titleKey="list.manage" media="html"  style="width:180px;text-align:center">
								<f:a alias="delSerialNo" href="del.ht?id=${identityItem.id}" css="link del"><spr:message code="menu.button.del"/></f:a>
								<a href="edit.ht?id=${identityItem.id}" class="link edit"><spr:message code="operator.edit"/></a>
								<a href="get.ht?id=${identityItem.id}" class="link detail"><spr:message code="operator.detail"/></a>
							</display:column>
						</display:table>
						<hotent:paging tableId="identityItem"/>
					
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
</html>


