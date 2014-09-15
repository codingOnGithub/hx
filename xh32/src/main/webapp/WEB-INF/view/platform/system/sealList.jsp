
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="seal"/><spr:message code="title.manage"/></title>

</head>
<body>
			<div class="panel">
			<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label"><spr:message code="seal"/><spr:message code="title.manageList"/></span>
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
										<li><span class="label"><spr:message code="seal.sealName"/>:</span><input type="text" name="Q_sealName_SL"  class="inputText" value="${param['Q_sealName_SL']}"/></li>								
										<li><span class="label"><spr:message code="seal.belongName"/>:</span><input type="text" name="Q_belongName_SL"  class="inputText" value="${param['Q_belongName_SL']}"/></li>
									</ul>
							</form>
					</div>
				</div>
				</div>
				<div class="panel-body">
					
				
				    	<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <display:table name="sealList" id="sealItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"   class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;">
								  	<input type="checkbox" class="pk" name="sealId" value="${sealItem.sealId}">
							</display:column>
							<display:column property="sealName" titleKey="seal.sealName" sortable="true" sortName="sealName"></display:column>
							
							<display:column property="belongName" titleKey="seal.belongName" sortable="true" sortName="belongName"></display:column>
							<display:column titleKey="list.manage" media="html" style="width:180px;text-align:center" >
								<a href="del.ht?sealId=${sealItem.sealId}" class="link del"><spr:message code="operator.del"/></a>
								<a href="edit.ht?sealId=${sealItem.sealId}" class="link edit"><spr:message code="operator.edit"/></a>
								<a href="get.ht?sealId=${sealItem.sealId}" class="link detail"><spr:message code="operator.detail"/></a>
							</display:column>
						</display:table>
						<hotent:paging tableId="sealItem"/>
					
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
</html>


