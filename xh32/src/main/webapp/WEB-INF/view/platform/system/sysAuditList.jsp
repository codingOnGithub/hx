
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="sysAudit"/><spr:message code="title.manageList"/></title>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label"><spr:message code="sysAudit"/><spr:message code="title.manageList"/></span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link del"  action="del.ht"><span></span><spr:message code="menu.button.del"/></a></div>
						</div>	
					</div>
					<div class="panel-search">
							<form id="searchForm" method="post" action="list.ht">
									<ul class="row">
												<li><span class="label"><spr:message code="sysAudit.opName"/>:</span><input type="text" name="Q_opName_SL"  class="inputText" value="${param['Q_opName_SL']}"/></li>
												<div class="row_date">
												<li><span class="label"><spr:message code="sysAudit.exeTime"/> <spr:message code="search.from"/>:</span><input  name="Q_beginexeTime_DL"  class="inputText date" value="${param['Q_beginexeTime_DL']}"/></li>
												<li><span class="label"><spr:message code="search.to"/>: </span><input  name="Q_endexeTime_DG" class="inputText date" value="${param['Q_endexeTime_DG']}"/></li>
												</div>
												<li><span class="label"><spr:message code="sysAudit.executor"/>:</span><input type="text" name="Q_executor_SL"  class="inputText" value="${param['Q_executor_SL']}"/></li>
									</ul>
							</form>
					</div>
				</div>
				</div>
				<div class="panel-body">
					
					
				    	<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <display:table name="sysAuditList" id="sysAuditItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" export="true"  class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;">
								  	<input type="checkbox" class="pk" name="auditId" value="${sysAuditItem.auditId}">
							</display:column>
							<display:column property="opName" titleKey="sysAudit.opName" sortable="true" sortName="opName"></display:column>
							<display:column titleKey="sysAudit.exeTime" sortable="true" sortName="exeTime">
								<fmt:formatDate value="${sysAuditItem.exeTime}" pattern="yyyy-MM-dd HH:mm"/>
							</display:column>
						
							<display:column property="executor" titleKey="sysAudit.executor" sortable="true" sortName="executor"></display:column>
							<display:column property="fromIp" title="IP" sortable="true" sortName="fromIp"></display:column>
							<%-- <display:column property="exeMethod" title="执行方法" sortable="true" sortName="exeMethod"></display:column>
							<display:column property="requestURI" title="请求URL" sortable="true" sortName="requestURI"></display:column> --%>
							<display:column property="ownermodel" titleKey="sysAudit.ownermodel" sortable="true" sortName="ownermodel"></display:column>
							<display:column property="exectype" titleKey="sysAudit.exectype" sortable="true" sortName="exectype"></display:column>
							<display:column titleKey="list.manage" media="html" style="width:100px;text-align:center">
								<a href="del.ht?auditId=${sysAuditItem.auditId}" class="link del"><spr:message code="menu.button.del"/></a>
								<a href="get.ht?auditId=${sysAuditItem.auditId}" class="link detail"><spr:message code="menu.button.detail"/></a>
							</display:column>
						</display:table>
						<hotent:paging tableId="sysAuditItem"/>
					
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
</html>


