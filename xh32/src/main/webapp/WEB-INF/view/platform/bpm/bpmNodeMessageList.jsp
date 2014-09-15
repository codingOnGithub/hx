
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="bpmNodeMessage.get.title"/><spr:message code="title.manage"/></title>
</head>
<body>
			<div class="panel">
			<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label"><spr:message code="bpmNodeMessage.get.title"/><spr:message code="title.manageList"/></span>
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
												<li><span class="label"><spr:message code="bpmDefinition.nodeSet.title"/>ID:</span><input type="text" name="Q_setId_S"  class="inputText"  value="${param['Q_setId_S']}"/></li>
											
												<li><span class="label"><spr:message code="bpmNodeMessage.subject.mail"/>:</span><input type="text" name="Q_subject_S"  class="inputText"  value="${param['Q_subject_S']}"/></li>
											
												<li><span class="label"><spr:message code="bpmNodeMessage.receiver.mail"/>:</span><input type="text" name="Q_receiver_S"  class="inputText"  value="${param['Q_receiver_S']}"/></li>
											
												<li><span class="label"><spr:message code="bpmNodeMessage.copyTo.mail"/>:</span><input type="text" name="Q_copyTo_S"  class="inputText"  value="${param['Q_copyTo_S']}"/></li>
											
												<li><span class="label"><spr:message code="bpmNodeMessage.actDefId"/>:</span><input type="text" name="Q_actDefId_S"  class="inputText"  value="${param['Q_actDefId_S']}"/></li>
											
												<li><span class="label"><spr:message code="bpmNodeMessage.nodeId"/>:</span><input type="text" name="Q_nodeId_S"  class="inputText"  value="${param['Q_nodeId_S']}"/></li>
											
												<li><span class="label"><spr:message code="bpmNodeMessage.conTemp"/>:</span><input type="text" name="Q_templateId_S"  class="inputText"  value="${param['Q_templateId_S']}"/></li>
											
												<li><span class="label"><spr:message code="bpmNodeMessage.bcc.mail"/>:</span><input type="text" name="Q_bcc_S"  class="inputText"  value="${param['Q_bcc_S']}"/></li>
											
												<li><span class="label"><spr:message code="bpmNodeMessage.sender"/>:</span><input type="text" name="Q_fromUser_S"  class="inputText"  value="${param['Q_fromUser_S']}"/></li>
											
												<li><span class="label">messageType:</span><input type="text" name="Q_messageType_S"  class="inputText"  value="${param['Q_messageType_S']}"/></li>
											
									</ul>
							</form>
					</div>
					<br/>
					<div class="panel-data">
				    	<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <display:table name="bpmNodeMessageList" id="bpmNodeMessageItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" export="true"  class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;">
								  	<input type="checkbox" class="pk" name="id" value="${bpmNodeMessageItem.id}">
							</display:column>
							<display:column property="subject" titleKey="bpmNodeMessage.subject.mail" sortable="true" sortName="subject"></display:column>
								<display:column property="receiver" titleKey="bpmNodeMessage.receiver.mail" sortable="true" sortName="receiver" maxLength="80"></display:column>
								<display:column property="copyTo" titleKey="bpmNodeMessage.copyTo.mail" sortable="true" sortName="copyTo" maxLength="80"></display:column>
							<display:column property="actDefId" titleKey="bpmNodeMessage.actDefId" sortable="true" sortName="actDefId"></display:column>
							<display:column property="nodeId" titleKey="bpmNodeMessage.nodeId" sortable="true" sortName="nodeId"></display:column>
							<display:column property="templateId" titleKey="bpmNodeMessage.conTemp" sortable="true" sortName="templateId"></display:column>
								<display:column property="bcc" titleKey="bpmNodeMessage.bcc.mail" sortable="true" sortName="bcc" maxLength="80"></display:column>
							<display:column property="fromUser" titleKey="bpmNodeMessage.sender" sortable="true" sortName="fromUser"></display:column>
							<display:column property="messageType" title="messageType" sortable="true" sortName="messageType"></display:column>
							<display:column titleKey="list.manage" media="html" style="width:180px">
								<a href="del.ht?id=${bpmNodeMessageItem.id}" class="link del"><spr:message code="menu.button.del"/></a>
								<a href="edit.ht?id=${bpmNodeMessageItem.id}" class="link edit"><spr:message code="menu.button.edit"/></a>
								<a href="get.ht?id=${bpmNodeMessageItem.id}" class="link detail"><spr:message code="menu.button.detail"/></a>
							</display:column>
						</display:table>
						<hotent:paging tableId="bpmNodeMessageItem"/>
					</div>
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
</html>


