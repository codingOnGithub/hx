
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="processRun.title"/></title>
<script type="text/javascript"  ></script>
</head>
<body>
    
    <jsp:include page="incDefinitionHead.jsp">
    	<jsp:param value="<spr:message code='processRun.title'/>" name="title"/>
    </jsp:include>
   <div class="panel-container">
	<f:tab curTab="instance" tabName="flow"/>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="processRun.title"/><spr:message code="menu.button.search"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search " id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>
				</div>	
			</div>
			<div class="panel-search">
					<form id="searchForm" method="post" action="${ctx}/platform/bpm/bpmDefinition/instance.ht">
							<ul class="row">
										<input type="hidden" name="defId" value="${bpmDefinition.defId}"/>
										<li><span class="label"><spr:message code="processRun.subject"/>:</span><input type="text" name="Q_subject_SL"  class="inputText" value="${param['Q_subject_SL']}"/></li>
										<div class="row_date">
										<li><span class="label"><spr:message code="processRun.createtime"/><spr:message code="search.from"/>:</span><input size="12"  name="Q_begincreatetime_DL"  class="inputText date" value="${param['Q_begincreatetime_DL']}"/></li>
										<li><span class="label"><spr:message code="search.to"/>: </span><input size="12" name="Q_endcreatetime_DG" class="inputText date" value="${param['Q_endcreatetime_DG']}"/></li>
										</div>
										<li><span class="label"><spr:message code="processRun.status"/>:</span>
											<select name="Q_status_SN" value="${param['Q_status_SN']}">
												<option value=""><spr:message code="search.select.allSelect"/>..</option>
												<option value="1" <c:if test="${param['Q_status_SN'] == 1}">selected</c:if>><spr:message code="processRun.status.running"/></option>
												<option value="2" <c:if test="${param['Q_status_SN'] == 2}">selected</c:if>><spr:message code="processRun.status.end"/></option>
											</select>
											</li>
							</ul>
					</form>
			</div>
		</div>
		</div>
		<div class="panel-body">
			
			
		    
			    <display:table name="processRunList" id="processRunItem" requestURI="instance.ht" sort="external" cellpadding="1" cellspacing="1"  class="table-grid">
					
					<display:column titleKey="processRun.subject" sortable="true" sortName="subject" style="text-align:left">
						<a href="#" onclick="window.open('${ctx }/platform/bpm/processRun/get.ht?tab=1&runId=${processRunItem.runId}');" >${processRunItem.subject}</a>
					</display:column>
					<display:column property="creator" titleKey="processRun.creator" sortable="true" sortName="creator"></display:column>
					<display:column  titleKey="processRun.createtime" sortable="true" sortName="createtime">
						<fmt:formatDate value="${processRunItem.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</display:column>
					<display:column  titleKey="processRun.endTime" sortable="true" sortName="endTime">
						<fmt:formatDate value="${processRunItem.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</display:column>
					<display:column property="duration" titleKey="processRun.duration" sortable="true" sortName="duration"></display:column>
					
					<display:column titleKey="processRun.status" sortable="true" sortName="status">
						<c:choose>
							<c:when test="${processRunItem.status==1}">
								<font color='green'><spr:message code="processRun.status.running"/></font>
							</c:when>
							<c:when test="${processRunItem.status==2}">
								<spr:message code="processRun.status.end"/>
							</c:when>
						</c:choose>
					</display:column>
					
				</display:table>
				<hotent:paging tableId="processRunItem"/>
		
		</div><!-- end of panel-body -->				
	</div>
	</div> <!-- end of panel -->
</body>
</html>


