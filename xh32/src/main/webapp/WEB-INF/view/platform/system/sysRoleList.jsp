<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	
	<%@include file="/commons/include/get.jsp" %>
	<title><spr:message code="sysRole.title"/><spr:message code="list.manage"/></title>
	<%@ taglib prefix="hotent" uri="http://www.jee-soft.cn/paging" %>

	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/CopyRoleDialog.js"></script>
    <script type="text/javascript">
	    function copyRole(roleId,roleName){
	    	CopyRoleDialog({roleId:roleId});
	    }
	    
		function editRoleRes(roleId){
	        var url=__ctx+"/platform/system/roleResources/edit.ht?roleId="+roleId;
	    	var winArgs="dialogWidth=350px;dialogHeight=460px;status=0;help=0;";
	    	url=url.getNewUrl();
	    	window.showModalDialog(url,"",winArgs);
	    }
    </script>
</head>
<body>
			<div class="panel">
			<div class="hide-panel">
				<div class="hide-panel">
					<div class="panel-top">
						<div class="tbar-title">
							<span class="tbar-label"><spr:message code="sysRole.title"/><spr:message code="title.manageList"/></span>
						</div>
						<div class="panel-toolbar">
							<div class="toolBar">
								<div class="group"><f:a alias="searchRole" css="link search" id="btnSearch" ><span></span><spr:message code="menu.button.search"/></f:a></div>
								<div class="l-bar-separator"></div>
								<div class="group">
									<f:a alias="addRole" css="link add" href="edit.ht"><span></span><spr:message code="menu.button.add"/></f:a>
								</div>
								<div class="l-bar-separator"></div>
								<div class="group">
									<f:a alias="delRole" css="link del" action="del.ht"><span></span><spr:message code="menu.button.del"/></f:a>
								</div>
							</div>	
						</div>
						<div class="panel-search">
								<form id="searchForm" method="post" action="list.ht">
										<ul class="row">
											<li><span class="label"><spr:message code="sysRole.roleName"/>:</span><input type="text" name="Q_roleName_SL"  class="inputText" value="${param['Q_roleName_SL']}"/></li>
											<li><span class="label"><spr:message code="sys.user.systemName"/>:</span><input type="text" name="Q_sysName_SL"  class="inputText" value="${param['Q_sysName_SL']}"/></li>
											<li><span class="label"><spr:message code="sysRole.allowDel"/>:</span>
											<select name="Q_allowDel_SN" class="select" >
												<option value="">--<spr:message code="search.select.all"/>--</option>
												<option value="1"><spr:message code="subSystem.allowDel.allow"/></option>
												<option value="0"><spr:message code="subSystem.allowDel.forbid"/></option>
											</select></li>
											<li><span class="label"><spr:message code="sysRole.allowEdit"/>:</span>
											<select name="Q_allowEdit_SN" class="select" >
												<option value="">--<spr:message code="search.select.all"/>--</option>
												<option value="1"><spr:message code="subSystem.allowDel.allow"/></option>
												<option value="0"><spr:message code="subSystem.allowDel.forbid"/></option>
											</select></li>
											<li><span class="label"><spr:message code="sysRole.enabled"/>:</span>
											<select name="Q_enabled_SN" class="select" >
												<option value="">--<spr:message code="search.select.all"/>--</option>
												<option value="1"><spr:message code="yes"/></option>
												<option value="0"><spr:message code="no"/></option>
											</select></li>
										</ul>
								</form>
						</div>
					</div>
				</div>
				</div>
				<div class="panel-body">
					
				
				    	<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <display:table name="sysRoleList" id="sysRoleItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" export="false"  class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;">
							<c:if test="${sysRoleItem.allowDel==0}"><input type="checkbox" class="disabled" name="roleId" id="roleId" value="${sysRoleItem.roleId}" disabled="disabled"></c:if>
							<c:if test="${sysRoleItem.allowDel==1}"><input type="checkbox" class="pk" name="roleId" id="roleId" value="${sysRoleItem.roleId}"></c:if>
							</display:column>
							<display:column property="roleName" titleKey="sysRole.roleName" style="text-align:left" sortable="true" sortName="roleName"></display:column>
							<display:column property="subSystem.sysName" titleKey="sys.user.systemName" style="text-align:left">
							</display:column>
							<display:column property="memo" titleKey="sysRole.memo" style="text-align:left"></display:column>
 			                <display:column titleKey="sysRole.allowDel">
							<c:choose>
								<c:when test="${sysRoleItem.allowDel eq 0}"><span class="red"><spr:message code="subSystem.allowDel.forbid"/></span></c:when>
								<c:when test="${sysRoleItem.allowDel eq 1}"><span class="green"><spr:message code="subSystem.allowDel.allow"/></font></c:when>
								<c:otherwise><spr:message code="sys.role.noState"/></c:otherwise>
							</c:choose>
							</display:column>
							<display:column  titleKey="sysRole.allowEdit">
							<c:choose>
								<c:when test="${sysRoleItem.allowEdit eq 0}"><span class="red"><spr:message code="subSystem.allowDel.forbid"/></span></c:when>
								<c:when test="${sysRoleItem.allowEdit eq 1}"><span class="green"><spr:message code="subSystem.allowDel.allow"/></font></c:when>
								<c:otherwise><spr:message code="sys.role.noState"/></c:otherwise>
							</c:choose>
							</display:column>
							<display:column titleKey="sysRole.status" >
							<c:choose>
							    <c:when test="${sysRoleItem.enabled eq 0}"><span class="red"><spr:message code="menu.button.disabled"/></span></c:when>
								<c:when test="${sysRoleItem.enabled eq 1}"><span class="green"><spr:message code="menu.button.enable"/></span></c:when>
								<c:otherwise><span class="red"><spr:message code="sys.role.noState"/></span></c:otherwise>
							</c:choose>
							</display:column>
							<display:column titleKey="list.manage" media="html"  style="width:50px;text-align:center" class="rowOps">
								<c:choose>
									<c:when test="${sysRoleItem.allowDel==0}">
										<a href="#" class="link del disabled" ><spr:message code="menu.button.del"/></a>
									</c:when>
									<c:otherwise>
										<f:a alias="delRole" css="link del" href="del.ht?roleId=${sysRoleItem.roleId}"><spr:message code="menu.button.del"/></f:a>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${sysRoleItem.allowEdit==0}">
										<a href="#" class="link edit disabled" ><spr:message code="menu.button.edit"/></a>
									</c:when>
									<c:otherwise>
										<f:a alias="updRole" css="link edit" href="edit.ht?roleId=${sysRoleItem.roleId}"><spr:message code="menu.button.edit"/></f:a>
									</c:otherwise>
								</c:choose>
								<f:a alias="roleDetail" css="link detail" href="get.ht?roleId=${sysRoleItem.roleId}"><spr:message code="menu.button.detail"/></f:a>
								<f:a alias="copyRole" css="link copy" onclick="copyRole('${sysRoleItem.roleId}','${sysRoleItem.roleName}')" ><spr:message code="sys.role.copyRole"/></f:a>
								<f:a alias="sourceRole" css="link flowDesign" href="javascript:editRoleRes(${sysRoleItem.roleId });"><spr:message code="sys.role.assignResource"/></f:a>
								<f:a alias="userRole" css="link auth" href="${ctx}/platform/system/userRole/edit.ht?roleId=${sysRoleItem.roleId}&roleName=${sysRoleItem.roleName }" ><spr:message code="sys.role.assigeUser"/></f:a>
								
								<f:a alias="stopRole" css="link lock" href="runEnable.ht?roleId=${sysRoleItem.roleId }" ><c:choose><c:when test="${sysRoleItem.enabled eq 1}"><spr:message code="menu.button.disabled"/></c:when><c:when test="${sysRoleItem.enabled eq 0}"><spr:message code="menu.button.enable"/></c:when></c:choose></f:a>
								
							</display:column>
						</display:table>
						<hotent:paging tableId="sysRoleItem"/>
					
				</div> 			
			</div> 
</body>
</html>


