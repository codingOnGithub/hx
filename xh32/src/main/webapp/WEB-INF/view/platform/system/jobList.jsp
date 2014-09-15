<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>

<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="sys.job.jobTitle"/></title>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="sys.job.jobManage"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span><spr:message code="menu.button.add"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span><spr:message code="menu.button.alter"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span><spr:message code="menu.button.del"/></a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label"><spr:message code="positionJob.name"/>:</span><input type="text" name="Q_jobname_SL"  class="inputText" />
						<span class="label"><spr:message code="positionJob.jobcode"/>:</span><input type="text" name="Q_jobcode_SL"  class="inputText" />
						<span class="label"><spr:message code="positionJob.desc"/>:</span><input type="text" name="Q_jobdesc_SL"  class="inputText" />
						<span class="label"><spr:message code="positionJob.setid"/>:</span><input type="text" name="Q_setid_SL"  class="inputText" />
						<span class="label"><spr:message code="positionJob.isDeleted"/>:</span><input type="text" name="Q_isdelete_SL"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="jobList" id="jobItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="jobid" value="${jobItem.jobid}">
				</display:column>
				<display:column property="jobname" titleKey="positionJob.name" sortable="true" sortName="JOBNAME"></display:column>
				<display:column property="jobcode" titleKey="positionJob.jobcode" sortable="true" sortName="JOBCODE"></display:column>
				<display:column property="jobdesc" titleKey="positionJob.desc" sortable="true" sortName="JOBDESC" maxLength="80"></display:column>
				<%--
				<display:column property="setid" titleKey="positionJob.setid" sortable="true" sortName="SETID"></display:column>
				<display:column property="isdelete" titleKey="positionJob.isDeleted" sortable="true" sortName="ISDELETE"></display:column>
				 --%>
				<display:column titleKey="list.manage" media="html" style="width:220px">
					<a href="del.ht?jobid=${jobItem.jobid}" class="link del"><spr:message code="menu.button.del"/></a>
					<a href="edit.ht?jobid=${jobItem.jobid}" class="link edit"><spr:message code="menu.button.edit"/></a>
					<a href="get.ht?jobid=${jobItem.jobid}" class="link detail"><spr:message code="menu.button.detail"/></a>
				</display:column>
			</display:table>
			<hotent:paging tableId="jobItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


