<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/get.jsp"%>
<title><spr:message code="processCenter.myCompleted"/></title>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/SelectUtil.js" ></script>
<script type="text/javascript">

	function showDetail(obj){
		var url = $(obj).attr("action");
		jQuery.openFullWindow(url);
	};
	
	//重新提交
	function executeTask(procInstId){
		 var url= "${ctx}/platform/bpm/task/toStart.ht?instanceId="+procInstId;
		 jQuery.openFullWindow(url);
	};
</script>

</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="processCenter.myCompleted"/></span>
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
				<form id="searchForm" method="post" action="myCompletedList.ht">
						<ul class="row">
						<input type="hidden" name="nodePath" value="${param['nodePath']}" title="流程分类节点路径"></input>
						<li>
							<span class="label"><spr:message code="processCenter.subject"/>:</span>
							<input type="text" name="Q_subject_SUPL" size="18" class="inputText"  value="${param['Q_subject_SUPL']}"/>
						</li>
						<li>
							<span class="label"><spr:message code="processCenter.processName"/>:</span>
							<input type="text" name="Q_processName_SUPL" size="18" class="inputText"  value="${param['Q_processName_SUPL']}" />
							<input type="hidden"   id="orgId" name="Q_orgId_L" value="${param['Q_orgId_L']}" />
						</li>
						<div class="row_date">
						<li>
							<span class="label"><spr:message code="processCenter.createtime"/>&nbsp;<spr:message code="search.from"/>:</span>
							<input name="Q_begincreatetime_DL" id="Q_begincreatetime_DL" size="18" class="inputText datePicker" datetype="1"  value="${param['Q_begincreatetime_DL']}" />
						</li>
						<li>						
							<span class="label"><spr:message code="search.to"/>: </span>
							<input name="Q_endcreatetime_DG" id="Q_endcreatetime_DG" size="18" class="inputText datePicker" datetype="2" value="${param['Q_endcreatetime_DG']}"  />
						</li>
						</div>
						<li>
							<span class="label"><spr:message code="processCenter.status"/>:</span>
							<select name="Q_status_SN">
								<option value=""><spr:message code="search.select.allSelect"/></option>
								<option value="2" <c:if test="${param['Q_status_SN'] == '2'}">selected</c:if>><spr:message code="processRun.status.finish"/></option>
								<option value="3" <c:if test="${param['Q_status_SN'] == '3'}">selected</c:if>><spr:message code="processRun.status.manualFinish"/></option>
								<option value="10" <c:if test="${param['Q_status_SN'] == '10'}">selected</c:if>><spr:message code="processRun.status.logicDelete"/></option>
							</select>
						</li>
						</ul>
					</form>
			</div>
		</div>
		</div>
		<div class="panel-body">
			<display:table name="processRunList" id="processRunItem" requestURI="myCompletedList.ht" sort="external" cellpadding="1"
				cellspacing="1" class="table-grid">
				<display:column titleKey="list.orderNo" media="html" style="width:20px;">${processRunItem_rowNum}</display:column>
		
				<display:column  titleKey="processCenter.subject" sortable="true" sortName="subject" style="text-align:left">
						<c:choose>
							<c:when test="${!processRunItem.allowBackToStart and (processRunItem.status==4 or processRunItem.status==5)}">
								<a href="#${processRunItem.actInstId}" onclick="javascript:executeTask('${processRunItem.actInstId}')" title='${processRunItem.subject}'>${f:subString(processRunItem.subject)}</a>
							</c:when>
							<c:otherwise>
								<a name="processDetail" onclick="showDetail(this)" href="#${processRunItem.runId}"  action="info.ht?prePage=myCompletedList&link=1&runId=${processRunItem.runId}" title='${processRunItem.subject}'>${f:subString(processRunItem.subject)}</a>								
							</c:otherwise>
						</c:choose>
				</display:column>
				<display:column property="processName" titleKey="processCenter.processName" sortable="true" sortName="processName" style="text-align:left"></display:column>
				<display:column titleKey="processCenter.createtime" sortable="true" sortName="createtime">
					<fmt:formatDate value="${processRunItem.createtime}"
						pattern="yyyy-MM-dd HH:mm:ss" />
				</display:column>
				<display:column titleKey="processCenter.duration" sortable="true" sortName="duration">
								${f:getDurationTime(processRunItem.createtime)}
				</display:column>
				<display:column titleKey="processCenter.type" >
						<c:out value="${processRunItem.typeName}"></c:out>
				</display:column>		
				<display:column titleKey="processCenter.status" sortable="true" sortName="status" style="width:50px;" >
						<f:processStatus status="${processRunItem.status}"></f:processStatus>
				</display:column>
			</display:table>
			<hotent:paging tableId="processRunItem"></hotent:paging>

		</div>
		<!-- end of panel-body -->
	</div>
	<!-- end of panel -->
</body>
</html>


