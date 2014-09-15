<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="queues"/></title>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="queues"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.refresh"/></a></div>
				</div>	
			</div>
			<form id="searchForm" method="post" action="list.ht">
			</form>
		</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="queuesList" id="queuesItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"  class="table-grid">
				<display:column property="name" titleKey="queues.name"  href="browse.ht"  paramId="JMSDestination" paramProperty="name"  style="width:155px;"  maxLength="15"></display:column>
				<display:column property="queueSize" titleKey="queues.queueSize"></display:column>
				<display:column property="consumerCount" titleKey="queues.consumerCount"></display:column>
				<display:column property="enqueueCount" titleKey="queues.enqueueCount"></display:column>
				<display:column property="dequeueCount" titleKey="queues.dequeueCount"></display:column>
				<display:column titleKey="list.manage" media="html" style="width:220px">
					<a href="purge.ht?JMSDestination=${queuesItem.name}" class="link edit"><spr:message code="menu.button.empty"/></a>
				</display:column>
			</display:table>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


