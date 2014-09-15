<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="agentSetting"/><spr:message code="title.manageList"/></title>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/UserInfo.js"></script>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="agentSetting"/><spr:message code="title.manageList"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span><spr:message code="menu.button.add"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span><spr:message code="menu.button.edit"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span><spr:message code="menu.button.del"/></a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<ul class="row">
					<div class="row_date">
					<li>
						<span class="label"><spr:message code="agentSetting.startdate"/>&nbsp;<spr:message code="search.from"/>:</span><input  name="Q_beginstartdate_DL"  class="inputText datePicker"  datetype="1" />
					</li>
					<li>
						<span class="label"><spr:message code="search.to"/>:</span><input  name="Q_endstartdate_DG"  class="inputText datePicker"  datetype="2" />
					</li>
					</div>
					<li>
						<span class="label"><spr:message code="agentSetting.enabled"/>:</span>
							<select name="Q_enabled_N">
								<option value=""><spr:message code="search.select.all"/></option>
								<option value="0"><spr:message code="agentSetting.enabled.false"/></option>
								<option value="1"><spr:message code="agentSetting.enabled.true"/></option>
							</select>
					</li>
					<div class="row_date">
					<li>
						<span class="label"><spr:message code="agentSetting.enddate"/>&nbsp;<spr:message code="search.from"/>:</span><input  name="Q_beginenddate_DL"  class="inputText datePicker"  datetype="1" />
					</li>
					<li>
						<span class="label"><spr:message code="search.to"/>:</span> <input  name="Q_endenddate_DG"  class="inputText datePicker"  datetype="2" />
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
		    <display:table name="agentSettingList" id="agentSettingItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"  class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${agentSettingItem.id}">
				</display:column>
				<display:column titleKey="agentSetting.authtype" sortable="true" sortName="authtype">
					<c:choose>
						<c:when test="${agentSettingItem.authtype==0}"><spr:message code="agentSetting.authtype.general"/></c:when>
						<c:when test="${agentSettingItem.authtype==1}"><spr:message code="agentSetting.authtype.partial"/></c:when>
						<c:when test="${agentSettingItem.authtype==2}"><spr:message code="agentSetting.authtype.condition"/></c:when>
					</c:choose>
				</display:column>
				
				<display:column  titleKey="agentSetting.startdate" sortable="true" sortName="startdate">
					<fmt:formatDate value="${agentSettingItem.startdate}" pattern="yyyy-MM-dd"/>
				</display:column>
				<display:column  titleKey="agentSetting.enddate" sortable="true" sortName="enddate">
					<fmt:formatDate value="${agentSettingItem.enddate}" pattern="yyyy-MM-dd"/>
				</display:column>
				<display:column titleKey="agentSetting.flowname" sortable="true"  property="flowname" sortName="flowname"></display:column>
				<display:column  titleKey="agentSetting.enabled" sortable="true" sortName="enabled">
					<c:choose>
						<c:when test="${agentSettingItem.enabled==0}"><spr:message code="agentSetting.enabled.false"/></c:when>
						<c:otherwise ><spr:message code="agentSetting.enabled.true"/></c:otherwise>
					</c:choose>
				</display:column>
				
				<display:column  titleKey="agentSetting.agent" sortable="true" sortName="agent">
					<a href="javascript:userDetail('${agentSettingItem.agentid}');">${agentSettingItem.agent}</a>
				</display:column>
				<display:column titleKey="agentSetting.createtime" sortable="true" sortName="createtime">
					<fmt:formatDate value="${agentSettingItem.createtime}" pattern="yyyy-MM-dd"/>
				</display:column>
				<display:column titleKey="list.manage" media="html" style="width:180px;line-height:21px;">
					<a href="del.ht?id=${agentSettingItem.id}" class="link del"><spr:message code="operator.del"/></a>
					<a href="edit.ht?id=${agentSettingItem.id}" class="link edit"><spr:message code="operator.edit"/></a>
				<%--
					<a href="get.ht?id=${agentSettingItem.id}" class="link detail">明细</a>
					 --%>
				</display:column>
			</display:table>
			<hotent:paging tableId="agentSettingItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


