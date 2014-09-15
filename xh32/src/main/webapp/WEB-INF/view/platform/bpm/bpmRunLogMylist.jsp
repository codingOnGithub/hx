
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="bpmRunLog.title"/><spr:message code="title.manage"/></title>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="bpmRunLog.title"/><spr:message code="title.manageList"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">							
					<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>							
				</div>	
			</div>
			<div class="panel-search">
					<form id="searchForm" method="post" action="mylist.ht">
							<ul class="row">
							<li>
								<span class="label"><spr:message code="bpmRunLog.processSubject"/>:</span><input type="text" name="Q_processSubject_SL"  class="inputText"  value="${param['Q_processSubject_SL']}"/>
							</li>
							<li>
								<span class="label"><spr:message code="bpmRunLog.operatortype"/>:</span>
								<select name="Q_operatortype_S" class="select" value="${param['Q_operatortype_S']}">
									<option value="">--<spr:message code="search.select.all"/>--</option>
										<option value="0" <c:if test="${param['Q_operatortype_S'] == 0}">selected</c:if>><spr:message code="bpmRunLog.operatortype.start"/></option>
										<option value="1" <c:if test="${param['Q_operatortype_S'] == 1}">selected</c:if>><spr:message code="bpmRunLog.operatortype.delegate"/></option>
										<option value="2" <c:if test="${param['Q_operatortype_S'] == 2}">selected</c:if>><spr:message code="bpmRunLog.operatortype.retrieve"/></option>
										<option value="3" <c:if test="${param['Q_operatortype_S'] == 3}">selected</c:if>><spr:message code="bpmRunLog.operatortype.deleteInstance"/></option>
										<option value="10" <c:if test="${param['Q_operatortype_S'] == 10}">selected</c:if>><spr:message code="bpmRunLog.operatortype.deleteTask"/></option>
										<option value="4" <c:if test="${param['Q_operatortype_S'] == 4}">selected</c:if>><spr:message code="bpmRunLog.operatortype.agree"/></option>
										<option value="5" <c:if test="${param['Q_operatortype_S'] == 5}">selected</c:if>><spr:message code="bpmRunLog.operatortype.objection"/></option>
										<option value="6" <c:if test="${param['Q_operatortype_S'] == 6}">selected</c:if>><spr:message code="bpmRunLog.operatortype.abstention"/></option>
										<option value="7" <c:if test="${param['Q_operatortype_S'] == 7}">selected</c:if>><spr:message code="bpmRunLog.operatortype.sign"/></option>
										<option value="8" <c:if test="${param['Q_operatortype_S'] == 8}">selected</c:if>><spr:message code="bpmRunLog.operatortype.reject"/></option>
										<option value="9" <c:if test="${param['Q_operatortype_S'] ==9}">selected</c:if>><spr:message code="bpmRunLog.operatortype.reject2sponsor"/></option>
										<option value="11" <c:if test="${param['Q_operatortype_S'] == 11}">selected</c:if>><spr:message code="bpmRunLog.operatortype.agent"/></option>
										<option value="13" <c:if test="${param['Q_operatortype_S'] == 13}">selected</c:if>><spr:message code="bpmRunLog.operatortype.lock"/></option>
										<option value="14" <c:if test="${param['Q_operatortype_S'] == 14}">selected</c:if>><spr:message code="bpmRunLog.operatortype.unLock"/></option>
										<option value="15" <c:if test="${param['Q_operatortype_S'] == 15}">selected</c:if>><spr:message code="bpmRunLog.operatortype.addOpinion"/></option>
										<option value="16" <c:if test="${param['Q_operatortype_S'] == 16}">selected</c:if>><spr:message code="bpmRunLog.operatortype.assign"/></option>
										<option value="17" <c:if test="${param['Q_operatortype_S'] == 17}">selected</c:if>><spr:message code="bpmRunLog.operatortype.setOwner"/></option>
										<option value="18" <c:if test="${param['Q_operatortype_S'] == 18}">selected</c:if>><spr:message code="bpmRunLog.operatortype.endTask"/></option>
								</select>							
							</li>
							<div class="row_date">
							<li>
								<span class="label"><spr:message code="bpmRunLog.createtime"/> <spr:message code="search.from"/>:</span> <input  name="Q_begincreatetime_DL"  class="inputText date" value="${param['Q_begincreatetime_DL']}"/>
							</li>
							<li>
								<span class="label"><spr:message code="search.to"/>: </span><input  name="Q_endcreatetime_DG" class="inputText date"  value="${param['Q_endcreatetime_DG']}"/>										
							</li>
							</div>
							</ul>
					</form>
			</div>		
		</div>
		</div>
		<div class="panel-body">
				<c:set var="checkAll">
					<input type="checkbox" id="chkall"/>
				</c:set>
			    <display:table name="bpmRunLogList" id="bpmRunLogItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
					<display:column title="${checkAll}" media="html" style="width:30px;">
						  	<input type="checkbox" class="pk" name="id" value="${bpmRunLogItem.id}">
					</display:column>
					<display:column property="processSubject" titleKey="bpmRunLog.processSubject" style="width:150px;" sortable="true" sortName="processSubject" maxLength="15"></display:column>
					<display:column property="username" titleKey="bpmRunLog.username" style="width:150px;" sortable="true" sortName="username"></display:column>
					<display:column  titleKey="bpmRunLog.createtime" sortable="true" style="width:170px;" sortName="createtime">
						<fmt:formatDate value="${bpmRunLogItem.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</display:column>
					<display:column titleKey="bpmRunLog.operatortype" sortable="true" style="width:150px;" sortName="operatortype">
						<c:choose>
							<c:when test="${bpmRunLogItem.operatortype eq 0}"><span class="green"><spr:message code="bpmRunLog.operatortype.start"/></span></c:when>
							<c:when test="${bpmRunLogItem.operatortype eq 1}"><span class="green"><spr:message code="bpmRunLog.operatortype.delegate"/></span></c:when>
							<c:when test="${bpmRunLogItem.operatortype eq 2}"><span class="red"><spr:message code="bpmRunLog.operatortype.retrieve"/></span></c:when>
							<c:when test="${bpmRunLogItem.operatortype eq 3}"><span class="red"><spr:message code="bpmRunLog.operatortype.deleteInstance"/></span></c:when>
							<c:when test="${bpmRunLogItem.operatortype eq 4}"><span class="green"><spr:message code="bpmRunLog.operatortype.agree"/></span></c:when>
							<c:when test="${bpmRunLogItem.operatortype eq 5}"><span class="red"><spr:message code="bpmRunLog.operatortype.objection"/></span></c:when>
							<c:when test="${bpmRunLogItem.operatortype eq 6}"><span class="green"><spr:message code="bpmRunLog.operatortype.abstention"/></span></c:when>
							<c:when test="${bpmRunLogItem.operatortype eq 7}"><span class="green"><spr:message code="bpmRunLog.operatortype.sign"/></span></c:when>
							<c:when test="${bpmRunLogItem.operatortype eq 8}"><span class="red"><spr:message code="bpmRunLog.operatortype.reject"/></span></c:when>
							<c:when test="${bpmRunLogItem.operatortype eq 9}"><span class="red"><spr:message code="bpmRunLog.operatortype.reject2sponsor"/></span></c:when>
							<c:when test="${bpmRunLogItem.operatortype eq 10}"><span class="red"><spr:message code="bpmRunLog.operatortype.deleteTask"/></span></c:when>
							<c:when test="${bpmRunLogItem.operatortype eq 11}"><span class="green"><spr:message code="bpmRunLog.operatortype.agent"/></span></c:when>
							<c:when test="${bpmRunLogItem.operatortype eq 13}"><span class="green"><spr:message code="bpmRunLog.operatortype.lock"/></span></c:when>
							<c:when test="${bpmRunLogItem.operatortype eq 14}"><span class="green"><spr:message code="bpmRunLog.operatortype.unLock"/></span></c:when>
							<c:when test="${bpmRunLogItem.operatortype eq 15}"><span class="green"><spr:message code="bpmRunLog.operatortype.addOpinion"/></span></c:when>
							<c:when test="${bpmRunLogItem.operatortype eq 16}"><span class="green"><spr:message code="bpmRunLog.operatortype.assign"/></span></c:when>
							<c:when test="${bpmRunLogItem.operatortype eq 17}"><span class="green"><spr:message code="bpmRunLog.operatortype.setOwner"/></span></c:when>
							<c:when test="${bpmRunLogItem.operatortype eq 18}"><span class="green"><spr:message code="bpmRunLog.operatortype.endTask"/></span></c:when>
							<c:otherwise><span class="red"><spr:message code="bpmRunLog.operatortype.other"/></span></c:otherwise>
						</c:choose>
					</display:column>
					<display:column property="memo" titleKey="bpmRunLog.memo" style="width:270px;" sortable="true" sortName="memo" maxLength="30"></display:column>
					<display:column titleKey="list.manage" media="html" style="width:80px">																
						<a href="get.ht?id=${bpmRunLogItem.id}" class="link detail"><spr:message code="operator.detail"/></a>
					</display:column>
				</display:table>
				<hotent:paging tableId="bpmRunLogItem"/>
			
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


