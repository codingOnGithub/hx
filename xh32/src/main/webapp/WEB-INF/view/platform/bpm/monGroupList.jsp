<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="monGroup.title"/><spr:message code="title.manageList"/></title>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/MonGroupAuthDialog.js" ></script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="monGroup.title"/><spr:message code="title.manageList"/></span>
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
					<div class="row">
						<span class="label"><spr:message code="monGroup.name"/>:</span><input type="text" name="Q_name_SL"  class="inputText" value="${param['Q_name_SL']}" />
						<span class="label"><spr:message code="monGroup.grade"/>:</span>
						<select name="Q_grade_N">
							<option value="" ><spr:message code="search.select.allSelect"/></option>
							<option value="1" <c:if test="${param['Q_grade_N'] == 1}">selected</c:if>><spr:message code="monGroup.grade.viewTitle"/></option>
							<option value="2" <c:if test="${param['Q_grade_N'] == 2}">selected</c:if>><spr:message code="monGroup.grade.processInfo"/></option>
							<option value="3" <c:if test="${param['Q_grade_N'] == 3}">selected</c:if>><spr:message code="monGroup.grade.mayInterfere"/></option>
						</select>
						<span class="label"><spr:message code="monGroup.enabled"/>:</span>
						<select name="Q_enabled_N">
							<option value="" ><spr:message code="search.select.allSelect"/></option>
							<option value="1" <c:if test="${param['Q_enabled_N'] == 1}">selected</c:if>><spr:message code="monGroup.enabled.enabled"/></option>
							<option value="0" <c:if test="${param['Q_enabled_N'] == 0}">selected</c:if>><spr:message code="monGroup.enabled.disabled"/></option>
						</select>
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="monGroupList" id="monGroupItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"  class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${monGroupItem.id}">
				</display:column>
				<display:column property="name" titleKey="monGroup.name" sortable="true" sortName="name"></display:column>
				<display:column  titleKey="monGroup.grade" sortable="true" sortName="grade">
					<c:choose>
						<c:when test="${monGroupItem.grade==1 }"><spr:message code="monGroup.grade.viewTitle"/></c:when>
						<c:when test="${monGroupItem.grade==2 }"><spr:message code="monGroup.grade.processInfo"/></c:when>
						<c:when test="${monGroupItem.grade==3 }"><spr:message code="monGroup.grade.mayInterfere"/></c:when>
					</c:choose>
				</display:column>
				<display:column  titleKey="monGroup.enabled" sortable="true" sortName="enabled">
					<c:choose>
						<c:when test="${monGroupItem.enabled==1 }"><spr:message code="monGroup.enabled.enabled"/></c:when>
						<c:when test="${monGroupItem.enabled==0 }"><spr:message code="monGroup.enabled.disabled"/></c:when>
					</c:choose>
				</display:column>
				<display:column property="creator" titleKey="monGroup.creator" sortable="true" sortName="creator">
					<a href="${ctx }/platform/system/sysUser/get.ht?id=${ monGroupItem.creatorid}">${monGroupItem.creator }</a>
				</display:column>
				<display:column  titleKey="monGroup.createtime" sortable="true" sortName="createtime">
					<fmt:formatDate value="${monGroupItem.createtime}" pattern="yyyy-MM-dd"/>
				</display:column>
				<display:column titleKey="list.manage" media="html" style="line-height:21px;width:100px;">
					<a href="del.ht?id=${monGroupItem.id}" class="link del" ><spr:message code="menu.button.del"/></a>
					<a href="edit.ht?id=${monGroupItem.id}" class="link edit" title="<spr:message code="menu.button.edit"/>"><spr:message code="menu.button.edit"/></a>
					<a href="javascript:MonGroupAuthDialog({groupId:${monGroupItem.id}});" class="link auth" ><spr:message code="menu.button.authorize"/></a>
				</display:column>
			</display:table>
			<hotent:paging tableId="monGroupItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


