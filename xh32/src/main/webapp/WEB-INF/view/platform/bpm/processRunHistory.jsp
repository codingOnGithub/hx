<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="processRun.history.title"/></title>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowDetailWindow.js"></script>
</head>
<body>
			<div class="panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label"><spr:message code="processRun.history.title"/></span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link del"  action="del.ht"><span></span><spr:message code="menu.button.del"/></a></div>
						</div>	
					</div>
					<div class="panel-search">
							<form id="searchForm" method="post" action="history.ht">
								<ul class="row">
								<li>
									<span class="label"><spr:message code="processRun.processName"/>:</span><input type="text" name="Q_processName_SL"  class="inputText" value="${param['Q_processName_SL']}"/>
								</li>
								<li>
									<span class="label"><spr:message code="processRun.subject"/>:</span><input type="text" name="Q_subject_SL"  class="inputText" value="${param['Q_subject_SL']}"/>
								</li>
								<li>	
									<span class="label"><spr:message code="processRun.creator"/>:</span><input type="text" name="Q_creator_SL"  class="inputText" value="${param['Q_creator_SL']}"/>
								</li>
								<div class="row_date">
								<li>
									<span class="label"><spr:message code="processRun.createtime"/> <spr:message code="search.from"/>:</span><input  name="Q_begincreatetime_DL"  class="inputText date" value="${param['Q_begincreatetime_DL']}"/>
								</li>
								<li>	
									<span class="label"><spr:message code="search.to"/>: </span><input  name="Q_endcreatetime_DG" class="inputText date" value="${param['Q_endcreatetime_DG']}"/>
								</li>
								</div>
								</ul>
							</form>
					</div>		
				</div>
				<div class="panel-body">
						<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <display:table name="processRunList" id="processRunItem" requestURI="history.ht" sort="external" cellpadding="1" cellspacing="1" export="true"  class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;">
								  	<input type="checkbox" class="pk" name="runId" value="${processRunItem.runId}">
							</display:column>
							<display:column property="processName" titleKey="processRun.processName" sortable="true" sortName="processName"></display:column>
							<display:column property="subject" titleKey="processRun.subject" sortable="true" sortName="subject"></display:column>
							<display:column property="creator" titleKey="processRun.creator" sortable="true" sortName="creator"></display:column>
							<display:column  titleKey="processRun.createtime" sortable="true" sortName="createtime">
								<fmt:formatDate value="${processRunItem.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
							</display:column>
							<display:column  titleKey="processRun.endTime" sortable="true" sortName="endtime">
								<fmt:formatDate value="${processRunItem.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
							</display:column>
							<display:column titleKey="processRun.duration" sortable="true" sortName="duration">
								${f:getTime(processRunItem.duration)}
							</display:column>
							<display:column titleKey="processRun.status" sortable="true" sortName="status">
								<c:choose>
									<c:when test="${processRunItem.status==1}">
										<span class='green'><spr:message code="processRun.status.running"/></span>
									</c:when>
									<c:when test="${processRunItem.status==2}">
										<span class="red"><spr:message code="processRun.status.end"/></span>
									</c:when>
									<c:when test="${processRunItem.status==3}">
										<span class="red"><spr:message code="processRun.endByUser"/></span>
									</c:when>
								</c:choose>
							</display:column>
							<display:column titleKey="list.manage" media="html" style="width:50px;text-align:center" class="rowOps">
								<f:a alias="delHistory" href="del.ht?runId=${processRunItem.runId}" css="link del"><spr:message code="menu.button.del"/></f:a>
								<a  href="#" onclick="FlowDetailWindow({runId:${processRunItem.runId}});" class="link detail"><spr:message code="menu.button.detail"/></a>
								<a href="${ctx}/platform/bpm/bpmRunLog/list.ht?runId=${processRunItem.runId}" class="link log"><spr:message code="menu.button.operationLog"/></a>
								<a href="${ctx}/platform/bpm/bpmProCopyto/list.ht?runId=${processRunItem.runId}" class="link copyTo"><spr:message code="menu.button.copyTo"/></a>
							</display:column>
						</display:table>
						<hotent:paging tableId="processRunItem"/>
					
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
</html>


