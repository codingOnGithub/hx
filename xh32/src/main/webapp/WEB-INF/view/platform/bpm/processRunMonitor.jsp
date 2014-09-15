<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/get.jsp"%>
<title><spr:message code="processRun.monitor.title"/></title>
<script type="text/javascript" src="${ctx}/hotent/sf/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/hotent/sf/platform/bpm/SelectUtil.js" ></script>
<script type="text/javascript">
	function showDetail(obj){
		var url = $(obj).attr("action");
		jQuery.openFullWindow(url);
	};


	function executeTask(actInstId){
		 var url="${ctx}/platform/bpm/task/doNext.ht?instanceId="+actInstId;
		var rtn = jQuery.openFullWindow(url);
		
	}
</script>

</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="processRun.monitor.title"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a>
						<div class="l-bar-separator"></div>
						<div class="group"><a href="#" class="link reset" onclick="$.clearQueryForm();"><span></span><spr:message code="menu.button.reset"/></a></div>
					</div>
				</div>
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="monitor.ht">
						<div class="row">
							<span class="label"><spr:message code="processRun.subject"/>:</span>
							<input type="text" name="Q_subject_SUPL" size="18" class="inputText"  value="${param['Q_subject_SUPL']}"/>
							<span class="label"><spr:message code="processRun.processName"/>:</span>
							<input type="text" name="Q_processName_SUPL" size="18" class="inputText"  value="${param['Q_processName_SUPL']}" />
							<span class="label"><spr:message code="processRun.createtime"/><spr:message code="search.from"/>:</span>
							<input name="Q_begincreatetime_DL" id="Q_begincreatetime_DL" size="18" class="inputText datePicker" datetype="1"  value="${param['Q_begincreatetime_DL']}" />
							<span class="label"><spr:message code="search.to"/>: </span>
							<input name="Q_endcreatetime_DG" id="Q_endcreatetime_DG" size="18" class="inputText datePicker" datetype="2" value="${param['Q_endcreatetime_DG']}"  />
							<br/>
							<span class="label"><spr:message code="processRun.status"/>:</span>
							<select name="Q_status_SN">
								<option value=""><spr:message code="search.select.all"/></option>
								<option value="1" <c:if test="${param['Q_status_SN'] == 1}">selected</c:if>><spr:message code="processRun.status.approval"/></option>
								
								<option value="5" <c:if test="${param['Q_status_SN'] == 5}">selected</c:if>><spr:message code="processRun.status.reject"/></option>
								<option value="6" <c:if test="${param['Q_status_SN'] == 6}">selected</c:if>><spr:message code="processRun.status.recover"/></option>
								<option value="3" <c:if test="${param['Q_status_SN'] == 3}">selected</c:if>><spr:message code="processRun.status.manualFinish"/></option>
								<option value="2" <c:if test="${param['Q_status_SN'] == 2}">selected</c:if>><spr:message code="processRun.status.finish"/></option>
							</select>
							<span class="label"><spr:message code="processRun.creator"/>:</span>
								<input type="hidden" id="Q_creatorId_L" name="Q_creatorId_L" value="${param['Q_creatorId_SL']}"/>
								<input type="text" id="creator"  class="inputText"   />
								<input type="button" value="..."  onclick="selectUser();" />
						</div>
					</form>
			</div>
		</div>
		<div class="panel-body">
			<c:set var="checkAll">
				<input type="checkbox" id="chkall" />
			</c:set>
			<display:table name="processRunList" id="processRunItem" requestURI="monitor.ht" sort="external" cellpadding="1"
				cellspacing="1" class="table-grid">
				<display:column titleKey="processRun.sn" media="html" style="width:20px;">${processRunItem_rowNum}</display:column>
				<display:column  titleKey="processRun.subject" sortable="true" sortName="subject" style="text-align:left">
					<c:choose>
						<c:when test="${processRunItem.grade eq 2 }">
						   <a name="processDetail" onclick="showDetail(this)" href="#"  action="info.ht?prePage=myRequest&link=1&runId=${processRunItem.runId}" title="${processRunItem.subject}">${f:subString(processRunItem.subject)}</a>
						</c:when>
						<c:when test="${processRunItem.grade eq 3 and processRunItem.status eq 1}">
						   <a name="subject"  href="javascript:executeTask(${processRunItem.actInstId})" title="${processRunItem.subject}" >${f:subString(processRunItem.subject)}</a>
						</c:when>
						<c:when test="${processRunItem.grade eq 3 and processRunItem.status ne 1}">
						   <a name="processDetail" onclick="showDetail(this)" href="#"  action="info.ht?prePage=myRequest&link=1&runId=${processRunItem.runId}" title="${processRunItem.subject}">${f:subString(processRunItem.subject)}</a>
						</c:when>
						<c:otherwise>
							<div title="${processRunItem.subject}">${f:subString(processRunItem.subject)}</div>
						</c:otherwise>
					</c:choose>
					
				</display:column>
				<display:column property="processName" titleKey="processRun.processName" sortable="true" sortName="processName" style="text-align:left"></display:column>
				<display:column titleKey="processRun.createtime" sortable="true" sortName="createtime">
					<fmt:formatDate value="${processRunItem.createtime}"
						pattern="yyyy-MM-dd HH:mm:ss" />
				</display:column>
				<display:column titleKey="processRun.duration" sortable="true" sortName="duration">
								${f:getDurationTime(processRunItem.createtime)}
				</display:column>
				<display:column titleKey="processRun.type" >
						<c:out value="${processRunItem.typeName}"></c:out>
				</display:column>		
				<display:column titleKey="processRun.orgName" >
						<c:out value="${processRunItem.orgName}"></c:out>
				</display:column>
				<display:column titleKey="processRun.status" sortable="true" sortName="status" style="width:50px;" >
						<f:processStatus status="${processRunItem.status}"></f:processStatus>
				</display:column>
				
			</display:table>
			<hotent:paging tableId="processRunItem" />

		</div>
		<!-- end of panel-body -->
	</div>
	<!-- end of panel -->
</body>
</html>


