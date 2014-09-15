<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="processRun.myAttend.title"/></title>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowDetailWindow.js"></script>
<script type="text/javascript">
	//任务追回
	function recover(runId){
		//检查当前任务的上一步是否为当前任务的执行人员，若不是，不允许追回
		var url="${ctx}/platform/bpm/processRun/recover.ht?runId="+ runId;
		$.post(url,function(data){
			var jsonResult=eval("(" +data +")");
			if(jsonResult.result==1){
				$.ligerDialog.success($lang_bpm.processRun.redo_success,$lang.tip.msg,function(){
					window.location.reload();
				});
			}else{
				$.ligerDialog.error($lang_bpm.processRun.redo_fail,$lang.tip.failure);
			}
        });
	}
</script>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="processRun.myAttend.title"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>
				</div>	
			</div>
			<div class="panel-search">
					<form id="searchForm" method="post" action="myAttend.ht">
							<ul class="row">
								<li><span class="label"><spr:message code="myDraft.processName"/>:</span><input type="text" name="Q_processName_SL"  class="inputText" value="${param['Q_processName_SL']}"/></li>
								<li><span class="label"><spr:message code="processRun.subject"/>:</span><input type="text" name="Q_subject_SL"  class="inputText" value="${param['Q_subject_SL']}"/></li>
								<li><span class="label"><spr:message code="processRun.status"/>:</span>
								<select name="Q_status_SN" value="${param['Q_status_SN']}">
									<option value=""><spr:message code="search.select.allSelect"/></option>
									<option value="1" <c:if test="${param['Q_status_SN'] == 1}">selected</c:if>><spr:message code="processRun.status.running"/></option>
									<option value="2" <c:if test="${param['Q_status_SN'] == 2}">selected</c:if>><spr:message code="processRun.status.end"/></option>
								</select></li>
							</ul>
							<ul class="row">
								<li><span class="label"><spr:message code="processRun.createtime"/>  <spr:message code="search.from"/>:</span> <input  name="Q_begincreatetime_DL"  class="inputText date" value="${param['Q_begincreatetime_DL']}"/>
								<span class="label"><spr:message code="search.to"/>: </span><input  name="Q_endcreatetime_DG" class="inputText date" value="${param['Q_endcreatetime_DG']}"/></li>
								
								<li><span class="label"><spr:message code="processRun.endTime"/>  <spr:message code="search.from"/>:</span> <input  name="Q_beginendTime_DL"  class="inputText date" value="${param['Q_beginendTime_DL']}"/>
								<span class="label"><spr:message code="search.to"/>: </span><input  name="Q_endendTime_DG" class="inputText date" value="${param['Q_endendTime_DG']}"/></li>
							</ul>
					</form>
			</div>
		</div>
		</div>
		<div class="panel-body">
			
			
		    	<c:set var="checkAll">
					<input type="checkbox" id="chkall"/>
				</c:set>
			    <display:table name="processRunList" id="processRunItem" requestURI="myAttend.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
					<display:column title="${checkAll}" media="html" style="width:30px;">
						  	<input type="checkbox" class="pk" name="runId" value="${processRunItem.runId}">
					</display:column>
					<display:column property="processName" titleKey="myDraft.processName" sortable="true" sortName="processName" style="text-align:left"></display:column>
					<display:column property="subject" titleKey="processRun.subject" sortable="true" sortName="subject" style="text-align:left"></display:column>
					<display:column property="creator" titleKey="processRun.creator" sortable="true" sortName="creator"></display:column>
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
								<span class="green"><spr:message code="processRun.status.running"/></font>
							</c:when>
							<c:when test="${processRunItem.status==2}">
								<span class="red"><spr:message code="processRun.status.end"/> </span>
							</c:when>
							<c:when test="${processRunItem.status==3}">
								<span class="brown"><spr:message code="processRun.endByUser"/> </span>
							</c:when>
						</c:choose>
					</display:column>
					<display:column titleKey="list.manage" media="html" style="width:180px">
						<a  href="#" onclick="FlowDetailWindow({runId:${processRunItem.runId}});" class="link detail"><spr:message code="menu.button.detail"/></a>
						<c:if test="${processRunItem.recover==1}">
							<a href="#" onclick="recover(${processRunItem.runId})" class="link redo"><spr:message code="menu.button.redo"/></a>
						</c:if>
					</display:column>
				</display:table>
				<hotent:paging tableId="processRunItem"/>
			
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


