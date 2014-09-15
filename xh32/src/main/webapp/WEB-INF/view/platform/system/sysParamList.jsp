
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.hotent.platform.model.system.SysParam"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	
	<%@include file="/commons/include/get.jsp" %>
	<title><spr:message code="sysParam.title"/><spr:message code="title.manage"/></title>
</head>
<body>
			<div class="panel">
			<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label"><spr:message code="sysParam.title"/><spr:message code="title.manageList"/></span>
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
									<ul class="row">
												
												<li><span class="label"><spr:message code="sysParam.paramName"/>:</span><input type="text" name="Q_paramName_SL"  class="inputText" value="${param['Q_paramName_SL']}"/></li>
												<li><span class="label"><spr:message code="sysParam.type"/>:</span>
												<select name="Q_effect_SN">
													<option value="">--<spr:message code="search.select.pleaseSelect"/>--</option>
													<option value="1"><spr:message code="sysParam.userParam"/></option>
													<option value="2"><spr:message code="sysParam.orgParam"/></option>
												</select></li>
												
												<li><span class="label"><spr:message code="sysParam.dataType"/>:</span>
												<select name="Q_dataType_SL">
													<option value="">--<spr:message code="search.select.pleaseSelect"/>--</option>
													<c:forEach items="${dataTypeMap}" var="t">
														<option value="${t.key}">${t.value }</option>
													</c:forEach>
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
					    <display:table name="sysParamList" id="sysParamItem" requestURI="list.ht" 
					    	sort="external" cellpadding="1" cellspacing="1" export="false"  class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;">
								  	<input type="checkbox" class="pk" name="paramId" value="${sysParamItem.paramId}">
							</display:column>
							<display:column property="paramName" titleKey="sysParam.paramName" sortable="true" sortName="paramName"></display:column>
							<display:column property="paramKey" titleKey="sysParam.paramKey" sortable="true" sortName="paramKey"></display:column>
							
							
							<display:column media="html" titleKey="sysParam.type" sortable="true" sortName="paramName">
									<c:if test="${sysParamItem.effect==1}"><spr:message code="sysParam.userParam"/></c:if>
									<c:if test="${sysParamItem.effect==2}"><spr:message code="sysParam.orgParam"/></c:if>
							</display:column>
							
							<display:column media="html" titleKey="sysParam.dataType" sortable="true" sortName="dataType">
								<c:forEach items="${dataTypeMap}" var="t">
									<c:if test="${t.key==sysParamItem.dataType}">${t.value}</c:if>
								</c:forEach>
							</display:column>
							<display:column titleKey="list.manage" media="html"  style="width:50px;text-align:center" class="rowOps">
								<f:a alias="delShuxing" href="del.ht?paramId=${sysParamItem.paramId}" css="link del"><spr:message code="menu.button.del"/></f:a>
								<a href="edit.ht?paramId=${sysParamItem.paramId}" class="link edit"><spr:message code="menu.button.edit"/></a>
								<a href="get.ht?paramId=${sysParamItem.paramId}" class="link detail"><spr:message code="menu.button.detail"/></a>
							</display:column>
						</display:table>
						<hotent:paging tableId="sysParamItem"/>
					
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
</html>


