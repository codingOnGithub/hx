
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="bpmNodeSign.title"/><spr:message code="title.manage"/></title>
</head>
<body>
			<div class="panel">
			<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label"><spr:message code=""/><spr:message code="title.manageList"/></span>
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
				</div>
				</div>
				<div class="panel-body">
					<div class="panel-search">
							<form id="searchForm" method="post" action="list.ht">
									<ul class="row">
											
											
												<li><span class="label"><spr:message code="bpmNodeMessage.nodeId"/>:</span><input type="text" name="Q_nodeId_S"  class="inputText" value="${param['Q_nodeId_S']}"/></li>
											
												<li><span class="label"><spr:message code="bpmNodeSign.voteAmount"/>:</span><input type="text" name="Q_voteAmount_S"  class="inputText" value="${param['Q_voteAmount_S']}"/></li>
											
												<li><span class="label"><spr:message code="bpmNodeSign.decideType"/>:</span><input type="text" name="Q_decideType_SN"  class="inputText" value="${param['Q_decideType_SN']}"/></li>
											
												<li><span class="label">1=<spr:message code="bpmNodeSign.voteType.percentage"/>:</span><input type="text" name="Q_voteType_SN"  class="inputText" value="${param['Q_voteType_SN']}"/></li>
											
												<li><span class="label"><spr:message code="bpmNodeSign.actDeployId"/>:</span><input type="text" name="Q_actDeployId_S"  class="inputText" value="${param['Q_actDeployId_S']}"/></li>
											
									</ul>
							</form>
					</div>
					<br/>
					<div class="panel-data">
				    	<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <display:table name="bpmNodeSignList" id="bpmNodeSignItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" export="true"  class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;">
								  	<input type="checkbox" class="pk" name="signId" value="${bpmNodeSignItem.signId}">
							</display:column>
							
							<display:column property="nodeId" titleKey="bpmNodeMessage.nodeId" sortable="true" sortName="nodeId"></display:column>
							<display:column property="voteAmount" titleKey="bpmNodeSign.voteAmount" sortable="true" sortName="voteAmount"></display:column>
							<display:column property="decideType" titleKey="bpmNodeSign.decideType" sortable="true" sortName="decideType"></display:column>
							<display:column property="voteType" titleKey="1=bpmNodeSign.voteType.percentage" sortable="true" sortName="voteType"></display:column>
							<display:column property="actDeployId" titleKey="bpmNodeSign.actDeployId" sortable="true" sortName="actDeployId"></display:column>
							<display:column titleKey="list.manage" media="html" style="width:180px">
								<a href="del.ht?signId=${bpmNodeSignItem.signId}" class="link del"><spr:message code="menu.button.del"/></a>
								<a href="edit.ht?signId=${bpmNodeSignItem.signId}" class="link edit"><spr:message code="menu.button.edit"/></a>
								<a href="get.ht?signId=${bpmNodeSignItem.signId}" class="link detail"><spr:message code="menu.button.detail"/></a>
							</display:column>
						</display:table>
						<hotent:paging tableId="bpmNodeSignItem"/>
					</div>
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
</html>


