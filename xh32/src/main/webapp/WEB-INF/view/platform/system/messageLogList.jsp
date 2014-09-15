<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="messageLog"/><spr:message code="title.manage"/></title>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="messageLog"/><spr:message code="title.manageList"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>
					<div class="l-bar-separator"></div>
					<!--
					<div class="group"><a class="link add" href="edit.ht">添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht">修改</a></div>
					-->
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span><spr:message code="menu.button.del"/></a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<ul class="row">
						<li><span class="label"><spr:message code="messageLog.subject"/>:</span><input type="text" name="Q_subject_SL"  class="inputText" value="${param['Q_subject_SL']}"/></li>
						<div class="row_date">
						<li><span class="label"><spr:message code="messageLog.sendTime"/> <spr:message code="search.from"/>:</span><input  name="Q_beginSendTime_DL"  class="inputText date" value="${param['Q_beginSendTime_DL']}"/></li>
						<li><span class="label"><spr:message code="search.to"/>: </span><input  name="Q_endSendTime_DG" class="inputText date" value="${param['Q_endSendTime_DG']}"/></li>
						</div>
						<li><span class="label"><spr:message code="messageLog.receiver"/>:</span><input type="text" name="Q_receiver_SL"  class="inputText" value="${param['Q_receiver_SL']}"/></li>
						<li><span class="label"><spr:message code="messageLog.messageType"/>:</span>			
						<select name="Q_messageType_SN" value="${param['Q_messageType_SN']}">
							<option value=""><spr:message code="search.select.pleaseSelect"/></option>
							<option value="1" <c:if test="${param['Q_messageType_SN'] == 1}">selected</c:if>><spr:message code="messageLog.messageType.mail"/></option>
							<option value="2" <c:if test="${param['Q_messageType_SN'] == 2}">selected</c:if>><spr:message code="messageLog.messageType.sms"/></option>
							<option value="3" <c:if test="${param['Q_messageType_SN'] == 3}">selected</c:if>><spr:message code="messageLog.messageType.inner"/></option>
						</select>
						</li>
						<li>
						<span class="label"><spr:message code="messageLog.state"/>:</span>
						<select name="Q_state_SN" value="${param['Q_state_SN']}">
							<option value=""><spr:message code="search.select.pleaseSelect"/></option>
							<option value="1" <c:if test="${param['Q_state_SN'] == 1}">selected</c:if>><spr:message code="messageLog.state.success"/></option>
							<option value="0" <c:if test="${param['Q_state_SN'] == 0}">selected</c:if>><spr:message code="messageLog.state.fail"/></option>
						</select></li>
					</ul>
				</form>
			</div>
			</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="messageLogList" id="messageLogItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"   class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${messageLogItem.id}">
				</display:column>
				<display:column property="subject" titleKey="messageLog.subject" sortable="true" sortName="subject"></display:column>
				<display:column  titleKey="messageLog.sendTime" sortable="true" sortName="sendTime">
					<fmt:formatDate value="${messageLogItem.sendTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</display:column>
				<display:column property="receiver" titleKey="messageLog.receiver" sortable="true" sortName="receiver" maxLength="80"></display:column>
				<display:column titleKey="messageLog.messageType" sortable="true" sortName="messageType">
					<c:choose>
						<c:when test="${messageLogItem.messageType==1 }"><spr:message code="messageLog.messageType.mail"/></c:when>
						<c:when test="${messageLogItem.messageType==2 }"><spr:message code="messageLog.messageType.sms"/></c:when>
						<c:when test="${messageLogItem.messageType==3 }"><spr:message code="messageLog.messageType.inner"/></c:when>
						<c:otherwise>
							<spr:message code="messageLog.messageType.unknow"/>
						</c:otherwise>
					</c:choose>
				</display:column>
				
				<display:column titleKey="messageLog.state" sortable="true" sortName="state">
						<c:choose>
							<c:when test="${messageLogItem.state==1 }"><font style="color: green;"><spr:message code="messageLog.state.success"/></font></c:when>
							<c:otherwise>
								<font style="color: red;"><spr:message code="messageLog.state.fail"/></font>
							</c:otherwise>
						</c:choose>
				</display:column>
				
				<display:column titleKey="list.manage" media="html" style="width:180px;text-align:center">
					<a href="del.ht?id=${messageLogItem.id}" class="link del"><spr:message code="operator.del"/></a>
					<!--
					<a href="edit.ht?id=${messageLogItem.id}" class="link edit">编辑</a>
					-->
					<a href="get.ht?id=${messageLogItem.id}" class="link detail"><spr:message code="operator.detail"/></a>
				</display:column>
			</display:table>
			<hotent:paging tableId="messageLogItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


