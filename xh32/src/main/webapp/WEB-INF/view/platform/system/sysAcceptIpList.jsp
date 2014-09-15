
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<%@include file="/commons/include/get.jsp" %>
	<title><spr:message code="sysAcceptIp"/><spr:message code="title.manageList"/></title>
</head>
<body>
			<div class="panel">
			<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label"><spr:message code="sysAcceptIp"/><spr:message code="title.manageList"/></span>
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
												<li><span class="label"><spr:message code="sysAcceptIp.title"/>:</span><input type="text" name="Q_title_SL"  class="inputText" value="${param['Q_title_SL']}"/>	</li>											
									</ul>
							</form>
					</div>
				</div>
				</div>
				<div class="panel-body">
					
				    	<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <display:table name="sysAcceptIpList" id="sysAcceptIpItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"  class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;">
								  	<input type="checkbox" class="pk" name="acceptId" value="${sysAcceptIpItem.acceptId}">
							</display:column>
							<display:column property="title" titleKey="sysAcceptIp.title" sortable="true" sortName="title"></display:column>
							<display:column property="startIp" titleKey="sysAcceptIp.startIp" sortable="true" sortName="startIp"></display:column>
							<display:column property="endIp" titleKey="sysAcceptIp.endIp" sortable="true" sortName="endIp"></display:column>						
							<display:column titleKey="list.manage" media="html"  style="width:180px;text-align:center" >
								<f:a alias="delAddress" href="del.ht?acceptId=${sysAcceptIpItem.acceptId}" css="link del"><spr:message code="operator.del"/></f:a>
								<a href="edit.ht?acceptId=${sysAcceptIpItem.acceptId}" class="link edit"><spr:message code="operator.edit"/></a>
								<a href="get.ht?acceptId=${sysAcceptIpItem.acceptId}" class="link detail"><spr:message code="operator.detail"/></a>
							</display:column>
						</display:table>
						<hotent:paging tableId="sysAcceptIpItem"/>
					
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
</html>


