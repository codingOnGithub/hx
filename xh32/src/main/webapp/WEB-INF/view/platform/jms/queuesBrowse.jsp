<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="queues.browse"/></title>
<%@ taglib prefix="jms" tagdir="/WEB-INF/tags/jms" %>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="queues.browse"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.refresh"/></a></div>
					<div class="l-bar-separator"></div>
			        <div class="group"><a class="link back" href="list.ht"><span></span><spr:message code="menu.button.back"/></a></div>
				</div>	
			</div>
			<form id="searchForm" method="post" action="browse.ht">
				<input type="hidden" name="JMSDestination" value="${JMSDestination}">	
			</form>
		</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="browseList" id="item" requestURI="browse.ht" sort="external" cellpadding="1" cellspacing="1" export="true"  class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="name" value="${item.JMSMessageID}">
				</display:column>
				<display:column property="JMSMessageID" titleKey="queues.browse.JMSMessageID"  href="message.ht?JMSDestination=${JMSDestination}&"  paramId="id" paramProperty="JMSMessageID"  style="width:280px;"  maxLength="50">
				</display:column>
				<display:column property="JMSCorrelationID" titleKey="queues.browse.JMSCorrelationID"></display:column>
				<display:column  titleKey="queues.browse.isPersistent">
				<jms:persistent message="${item}" />
				</display:column>
				<display:column property="JMSPriority" titleKey="queues.browse.JMSPriority"></display:column>
				<display:column property="JMSRedelivered" titleKey="queues.browse.JMSRedelivered"></display:column>
				<display:column property="JMSReplyTo" titleKey="queues.browse.JMSReplyTo" ></display:column>
				<display:column titleKey="queues.browse.JMSTimestamp"  >
				<jms:formatTimestamp timestamp="${item.JMSTimestamp}" />
				</display:column>
			</display:table>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


