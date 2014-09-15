<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="processRun.manage.title"/></title>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowDetailWindow.js"></script>
</head>
<body>
			<div class="panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label"><spr:message code="processRun.manage.title"/><spr:message code="operator.list"/></span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>
							<div class="l-bar-separator"></div>
							<%-- <a class="link del"  action="del.ht">删除</a>--%>
							<%-- 能够控制按钮权限的写法，注意别名不要写错--%>
							<div class="group"><f:a alias="delRun" css="link del" action="del.ht"><span></span><spr:message code="menu.button.del"/></f:a></div>
						</div>	
					</div>
					<div class="panel-search">
							<form id="searchForm" method="post" action="list.ht">
									<ul class="row">
										<li><span class="label"><spr:message code="processRun.processName"/>:</span><input type="text" name="Q_processName_SL"  class="inputText" value="${param['Q_processName_SL']}"/></li>
										<li><span class="label"><spr:message code="processRun.subject"/>:</span><input type="text" name="Q_subject_SL"  class="inputText" value="${param['Q_subject_SL']}"/></li>
										<li><span class="label"><spr:message code="processRun.runId"/>:</span><input type="text" name="Q_runId_S"  class="inputText" value="${param['Q_runId_S']}"/></li>
										<li><span class="label"><spr:message code="processRun.InstId"/>:</span><input type="text" name="Q_actInstId_SL"  class="inputText" value="${param['Q_actInstId_SL']}"/></li>
										<li><span class="label"><spr:message code="processRun.flowBusId"/>:</span><input type="text" name="Q_businessKey_SL"  class="inputText" value="${param['Q_businessKey_SL']}"/></li>
										<div class="row_date">
											<li><span class="label"><spr:message code="processRun.createtime"/>:</span><input  name="Q_begincreatetime_DL"  class="inputText date" value="${param['Q_begincreatetime_DL']}"/></li>
											<li><span class="label"><spr:message code="search.to"/>: </span><input  name="Q_endcreatetime_DG" class="inputText date" value="${param['Q_endcreatetime_DG']}"></li>
										</div>
										<li><span class="label"><spr:message code="processRun.status"/>:</span>
										<select name="Q_status_SN" value="${param['Q_status_SN']}">
											<option value=""><spr:message code="search.select.allSelect"/></option>
											<option value="1" <c:if test="${param['Q_status_SN'] == '1'}">selected</c:if>><spr:message code="processRun.status.running"/></option>
											<option value="2" <c:if test="${param['Q_status_SN'] == '2'}">selected</c:if>><spr:message code="processRun.status.end"/></option>
											<option value="3" <c:if test="${param['Q_status_SN'] == '3'}">selected</c:if>><spr:message code="processRun.endByUser"/></option>
											<option value="4" <c:if test="${param['Q_status_SN'] == '4'}">selected</c:if>><spr:message code="processRun.draft"/></option>
											<option value="5" <c:if test="${param['Q_status_SN'] == '5'}">selected</c:if>><spr:message code="processRun.status.recover"/></option>
											<option value="6" <c:if test="${param['Q_status_SN'] == '6'}">selected</c:if>><spr:message code="processRun.status.reject"/></option>
											<option value="10" <c:if test="${param['Q_status_SN'] == '10'}">selected</c:if>><spr:message code="processRun.logicalDel"/></option>
										</select></li>
									</ul>
							</form>
					</div>		
				</div>
				</div>
				<div class="panel-body">
				    	<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <display:table name="processRunList" id="processRunItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"   class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;">
								  	<input type="checkbox" class="pk" name="runId" value="${processRunItem.runId}">
							</display:column>
							<display:column  titleKey="processRun.subject" media="html" maxLength="18" sortable="true" sortName="subject" style="text-align:left">
								<a href="#${processRunItem.runId}" onclick="FlowDetailWindow({runId:${processRunItem.runId}})" style="text-align:left;" >${processRunItem.subject}</a>
							</display:column>
							<display:column property="processName" titleKey="processRun.processName" sortable="true" sortName="processName" style="text-align:left"></display:column>
							<display:column property="creator" titleKey="processRun.creator" sortable="true" sortName="creator" style="text-align:left"></display:column>
							<display:column  titleKey="processRun.createtime" sortable="true" sortName="createtime">
								<fmt:formatDate value="${processRunItem.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
							</display:column>
							<display:column  titleKey="processRun.endTime" sortable="true" sortName="endTime">
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
										<span class="brown"><spr:message code="processRun.endByUser"/></span>
									</c:when>
									<c:when test="${processRunItem.status==4}">
										<span class="green"><spr:message code="processRun.testRun"/></span>
									</c:when>
								</c:choose>
							</display:column>
							<display:column title="list.manage" media="html" style="width:140px">
								<f:a alias="delRun" href="del.ht?runId=${processRunItem.runId}" css="link del"><spr:message code="menu.button.del"/></f:a>
								<a href="${ctx}/platform/bpm/bpmRunLog/list.ht?runId=${processRunItem.runId}" class="link log"><spr:message code="menu.button.operationLog"/></a>
							</display:column>
						</display:table>
						<hotent:paging tableId="processRunItem"/>
					
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
</html>


