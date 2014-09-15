<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="tab.process.copyUser"/></title>
<script type="text/javascript">
</script>
</head>
<body>
	<div class="l-layout-header"><spr:message code="processRun.title"/>--【<i>${processRun.subject}</i>】<spr:message code="tab.process.copyUser"/>。</div>
	<div class="panel">
		<c:if test="${param.tab eq 1 }">
			<f:tab curTab="copyUser" tabName="process"/>
		</c:if>
		
		<div class="panel-body">
	    	<display:table name="bpmProCopytoList" id="bpmProCopytoItem" requestURI="getCopyUserByInstId.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column property="ccUname" titleKey="bpmProCopyto.ccUname">
					<c:out value="${bpmProCopytoItem.ccUname}"></c:out>
				</display:column>
				<display:column property="posName" titleKey="bpmProCopyto.posName">
					<c:out value="${bpmProCopytoItem.posName}"></c:out>
				</display:column>
				<display:column property="orgName" titleKey="bpmProCopyto.orgName">
					<c:out value="${bpmProCopytoItem.orgName}"></c:out>
				</display:column>
			
				<display:column titleKey="bpmProCopyto.isReaded" style="width:60px;" sortable="true" sortName="IS_READED">
					<c:choose>
						<c:when test="${bpmProCopytoItem.isReaded eq 0}"><span class="red close-message"><spr:message code="bpmProCopyto.isReaded.noRead"/></span></c:when>
						<c:when test="${bpmProCopytoItem.isReaded eq 1}"><span class="green open-message"><spr:message code="bpmProCopyto.isReaded.read"/></c:when>
					</c:choose>
				</display:column>
				<display:column  titleKey="bpmProCopyto.ccTime">
					<fmt:formatDate value="${bpmProCopytoItem.ccTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</display:column>
				<display:column titleKey="bpmProCopyto.cpType" style="width:45px;" sortable="true" sortName="CP_TYPE">
					<c:choose>
						<c:when test="${bpmProCopytoItem.cpType eq 1}"><span class="green"><spr:message code="bpmProCopyto.cpType.cc"/></span></c:when>
						<c:when test="${bpmProCopytoItem.cpType eq 2}"><span class="brown"><spr:message code="bpmProCopyto.cpType.forward"/></span></c:when>
					</c:choose>
				</display:column>
			</display:table>
			<hotent:paging tableId="bpmProCopytoItem"></hotent:paging>
		</div>
	</div>
</body>
</html>


