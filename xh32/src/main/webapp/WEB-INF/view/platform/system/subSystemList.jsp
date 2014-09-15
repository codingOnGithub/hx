
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="subSystem"/><spr:message code="title.manageList"/></title>
<script type="text/javascript">
	function ImportXml(systemId){
		var url=__ctx +"/platform/system/subSystem/import.ht";
		
		var conf=$.extend({},{dialogWidth:550 ,dialogHeight:200,help:0,status:0,scroll:0,center:1});
		var winArgs="dialogWidth="+conf.dialogWidth+"px;dialogHeight="+conf.dialogHeight
			+"px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center;
		
		url=url.getNewUrl();
		var rtn=window.showModalDialog(url,{systemId:systemId},winArgs);
	}
</script>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="subSystem"/><spr:message code="title.manageList"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span><spr:message code="menu.button.add"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span><spr:message code="menu.button.alter"/></a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<ul class="row">
						<li><span class="label"><spr:message code="subSystem.sysName"/>:</span><input type="text" name="Q_sysName_SL"  class="inputText"  value="${param['Q_sysName_SL']}"/></li>
						<li><span class="label"><spr:message code="subSystem.alias"/>:</span><input type="text" name="Q_alias_SL"  class="inputText"  value="${param['Q_alias_SL']}"/></li>
						<div class="row_date">
						<li><span class="label"><spr:message code="subSystem.createtime"/> <spr:message code="search.from"/>:</span><input  name="Q_begincreatetime_DL"  class="inputText date"  value="${param['Q_begincreatetime_DL']}"/></li>
						<li><span class="label"><spr:message code="search.to"/></span><input  name="Q_endcreatetime_DG" class="inputText date"  value="${param['Q_endcreatetime_DG']}"/></li>
						</div>
						<li><span class="label"><spr:message code="subSystem.allowDel"/>:</span>
						<select name="Q_allowDel_SN" class="select" >
							<option value=""><spr:message code="search.select.all"/></option>
							<option value="1" <c:if test="${param['Q_allowDel_SN'] == 1}">selected</c:if>><spr:message code="yes"/></option>
							<option value="0" <c:if test="${param['Q_allowDel_SN'] == 0}">selected</c:if>><spr:message code="no"/></option>
						</select>
						</li>
						<li>
						<span class="label"><spr:message code="subSystem.isActive"/>:</span><select name="Q_isActive_SN" class="select" >
							<option value=""><spr:message code="search.select.all"/></option>
							<option value="1" <c:if test="${param['Q_isActive_SN'] == 1}">selected</c:if>><spr:message code="yes"/></option>
							<option value="0" <c:if test="${param['Q_isActive_SN'] == 0}">selected</c:if>><spr:message code="no"/></option>
						</select></li>
					</ul>
				</form>
			</div>
		</div>
		</div>
		<div class="panel-body">
			
		    	<c:set var="checkAll">
					<input type="checkbox" id="chkall"/>
				</c:set>
			    <display:table name="subSystemList" id="subSystemItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
					<display:column title="${checkAll}" media="html" style="width:30px;">
						  	<input type="checkbox" class="pk" name="id" value="${subSystemItem.systemId}">
					</display:column>
					<display:column property="sysName" titleKey="subSystem.sysName" sortable="true" sortName="sysName" ></display:column>
					<display:column property="alias" titleKey="subSystem.alias" sortable="true" sortName="alias" ></display:column>
					
					<display:column titleKey="subSystem.logo" sortName="logo">
						<img alt="" src="${ctx }/${subSystemItem.logo}" >
					</display:column>
					
					
					<display:column property="defaultUrl" titleKey="subSystem.defaultUrl" sortable="true" sortName="defaultUrl" ></display:column>
					<display:column  titleKey="subSystem.createtime" sortable="true" sortName="createtime">
						<fmt:formatDate value="${subSystemItem.createtime}" pattern="yyyy-MM-dd"/>
					</display:column>
					<display:column titleKey="subSystem.allowDel" sortable="true" sortName="allowDel" style="text-align:center">
						<c:choose>
							<c:when test="${subSystemItem.allowDel eq 1}"><span class="green"><spr:message code="yes"/></span></c:when>
							<c:when test="${subSystemItem.allowDel eq 0}"><span class="red"><spr:message code="no"/></span></c:when>
							<c:otherwise><span class="red"><spr:message code="subSystem.allowDel.unspecified"/></span></c:otherwise>
						</c:choose>
					</display:column>
					<display:column titleKey="subSystem.isActive" sortable="true" sortName="isActive" style="text-align:center">
						<c:choose>
							<c:when test="${subSystemItem.isActive eq 1}"><span class="green"><spr:message code="yes"/></span></c:when>
							<c:when test="${subSystemItem.isActive eq 0}"><span class="red"><spr:message code="no"/></span></c:when>
							<c:otherwise><span class="red"><spr:message code="subSystem.isActive.unspecified"/></span></c:otherwise>
						</c:choose>
					</display:column>
					<display:column titleKey="subSystem.isLocal" sortable="true" sortName="isLocal" style="text-align:center">
						<c:choose>
							<c:when test="${subSystemItem.isLocal eq 0}"><span class="red"><spr:message code="no"/></span></c:when>
							<c:otherwise><span class="green"><spr:message code="yes"/></span></c:otherwise>
						</c:choose>
					</display:column>
					<display:column titleKey="list.manage" media="html" style="width:50px;text-align:center" class="rowOps">
						<c:choose>
							<c:when test="${subSystemItem.allowDel==1 }">
								<f:a alias="delSubsystem" href="del.ht?id=${subSystemItem.systemId}" css="link del"><spr:message code="operator.del"/></f:a>
							</c:when>
							<c:otherwise>
								<a href="#" class="link del disabled"><spr:message code="operator.del"/></a>
							</c:otherwise>
						</c:choose>
						
						<a href="edit.ht?id=${subSystemItem.systemId}" class="link edit"><spr:message code="operator.edit"/></a>
						<a href="exportXml.ht?systemId=${subSystemItem.systemId}" class="link download"><spr:message code="operator.export"/></a>
						<a href="#" onclick="ImportXml(${subSystemItem.systemId})" class="link upload"><spr:message code="operator.import"/></a>
					</display:column>
				</display:table>
				<hotent:paging tableId="subSystemItem"/>
			
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


