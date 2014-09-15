
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="sysJobLog"/><spr:message code="title.manageList"/></title>
</head>
<body>
			<div class="panel">
			<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label"><spr:message code="sysJobLog"/><spr:message code="title.manageList"/></span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link del"  action="delJobLog.ht"><span></span><spr:message code="menu.button.del"/></a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link back" href="getJobList.ht"><span></span><spr:message code="menu.button.back"/></a></div>
						</div>	
					</div>
					<div class="panel-search">
							<form id="searchForm" method="post" action="getLogList.ht?jobName=${jobName }&trigName=${trigName}">
									<ul class="row">
									           <li> <span class="label"><spr:message code="sysJobLog.jobName"/>:</span><input type="text" name="Q_jobName_SL"  class="inputText" value="${param['Q_jobName_SL']}"/></li>
									            <li><span class="label"><spr:message code="sysJobLog.trigName"/>:</span><input type="text" name="Q_trigName_SL"  class="inputText" value="${param['Q_trigName_SL']}"/></li>
												<li><span class="label"><spr:message code="search.from"/>:</span> <input type="text" name="Q_startTime_DL"  class="inputText date" value="${param['Q_startTime_DL']}"/>
												<span class="label"><spr:message code="search.to"/>: </span><input type="text" name="Q_endTime_DG" class="inputText date" value="${param['Q_endTime_DG']}"/></li>
									</ul>
							</form>
					</div>
				</div>
				</div>
				<div class="panel-body">
						<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <display:table name="sysJobLogList" id="sysJobLogItem" requestURI="getLogList.ht" sort="external" cellpadding="1" cellspacing="1"  class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;">
								  	<input type="checkbox" class="pk" name="logId"  value="${sysJobLogItem.logId}">
							</display:column>
							<display:column property="jobName" titleKey="sysJobLog.jobName" style="text-align:left" ></display:column>
							<display:column property="trigName" titleKey="sysJobLog.trigName" style="text-align:left" ></display:column>
							<display:column  titleKey="sysJobLog.startTime" sortable="true" sortName="startTime">
							<fmt:formatDate value="${sysJobLogItem.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
							</display:column>							
							<display:column  titleKey="sysJobLog.endTime" sortable="true" sortName="endTime">
							<fmt:formatDate value="${sysJobLogItem.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
							</display:column>
							<display:column property="content" titleKey="sysJobLog.content"  maxLength="80"></display:column>
							<display:column  titleKey="sysJobLog.state" style="text-align:center">
							<c:if test="${sysJobLogItem.state eq 1 }"><span class="green"><spr:message code="sysJobLog.content.success"/></span></c:if>
							<c:if test="${sysJobLogItem.state eq 0 }"><span class="red"><spr:message code="sysJobLog.content.fail"/></span></c:if>
							</display:column>
							<display:column property="runTime" titleKey="sysJobLog.runTime" sortable="true" sortName="runTime" style="width:100px"></display:column>
							<display:column titleKey="list.manage" media="html" style="width:80px;text-align:center">
							<a href="delJobLog.ht?logId=${sysJobLogItem.logId }" class="link del"><spr:message code="menu.button.del"/></a>
							</display:column>
						</display:table>
						<hotent:paging tableId="sysJobLogItem"/>
					
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
</html>


