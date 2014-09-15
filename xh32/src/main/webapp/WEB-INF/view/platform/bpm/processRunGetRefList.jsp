<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="processRun.title"/></title>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerWindow.js" ></script>
<script type="text/javascript">
function showDetail(runId){
	var url ="get.ht?tab=1&runId=" +runId;
	var params="height=700,width=1000,status=no,toolbar=no,menubar=no,location=no,resizable=1,scrollbars=1";
	window.open(url,"reference_" +runId,params);
};
</script>
</head>
<body>
	<c:choose>
		<c:when test="${type==0 }">
			<f:tab curTab="mySubmitProcess" tabName="referenceTab"/>	
		</c:when>
		<c:otherwise>
			<f:tab curTab="myApprovalProcess" tabName="referenceTab"/>
		</c:otherwise>
	</c:choose>
	
	<div class="panel">
		<div class="panel-body">
			    <display:table name="processRunList" id="processRunItem" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
					<display:column  titleKey="processRun.subject" sortable="true" sortName="subject" style="text-align:left">
							<a name="processDetail" onclick="showDetail(${processRunItem.runId})" href="#"  title="${processRunItem.subject}" >${f:subString(processRunItem.subject)}</a>
					</display:column>
					<display:column property="processName" titleKey="processRun.processName" sortable="true" sortName="processName" style="text-align:left"></display:column>
					<c:if test="${type>0 }">
						<display:column  titleKey="processRun.creator" property="creator" >
						</display:column>
					</c:if>
					<display:column  titleKey="processRun.createtime" sortable="true" sortName="createtime">
						<fmt:formatDate value="${processRunItem.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</display:column>
					<display:column  titleKey="processRun.endTime" sortable="true" sortName="endTime">
						<fmt:formatDate value="${processRunItem.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</display:column>
					
				</display:table>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


