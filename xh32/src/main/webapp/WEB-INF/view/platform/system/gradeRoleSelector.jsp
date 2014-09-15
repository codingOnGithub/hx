<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title></title>
<%@include file="/commons/include/get.jsp" %>
<f:js pre="js/lang/view/platform/system" ></f:js>
<script type="text/javascript" src="${ctx }/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript">
	$(function(){
		$("#defLayout").ligerLayout({topHeight :80,height : '100%'});
	});

	function selectRole(){
		var aryRoleId=[];
		var aryRoleName=[];
		$("input[name='role']:checked").each(function(){
			var tmp=$(this).val();
			var aryTmp=tmp.split(",");
			aryRoleId.push(aryTmp[0]);
			aryRoleName.push(aryTmp[1]);
		});
		
		var orgIds=aryRoleId.join(",");
		var orgNames=aryRoleName.join(",");
		if(orgIds==""){
			$.ligerDialog.warn($lang_system.sysOrg.select_orgId,$lang.tip.msg);
			return "";
		}
		window.returnValue={roleId:orgIds,roleName:orgNames};
		window.close();
	}	

	
</script>
</head>
<body>
	<div id="defLayout">
	<div class="hide-panel">
       		<div class="panel-top" position="top">
	       		<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group"><a class="link add" href="javascript:;"><span></span><spr:message code="menu.button.search"/></a></div>
					
					</div>	
				</div>
				<div class="panel-search">
							<form id="searchForm" method="post" action="list.ht">
									<ul class="row">
										<li><span class="label"><spr:message code="sys.user.roleName"/>:</span><input type="text" name="Q_name_S"  class="inputText" value="${param['Q_name_S']}"/></li>
									</ul>
							</form>
					</div>
		 	</div>
		 	</div>
			<div class="panel-body" position="center" style="overflow:auto;">
			        <c:set var="checkAll">
					<input type="checkbox" id="chkall"/>
					</c:set>
				    <display:table name="orgRoles" id="orgRole" cellpadding="1" cellspacing="1" export="false" class="table-grid">
						<display:column title="${checkAll}" media="html" style="width:30px;text-align:center;">
							<input type="checkbox" class="pk" name="role" value="${orgRole.roleid},${orgRole.role.roleName}"/>
						</display:column>
						<display:column property="role.roleName" titleKey="sys.user.roleName"></display:column>
						<display:column property="role.memo" titleKey="sysRole.memo"></display:column>
						<display:column property="orgName" titleKey="sysUser.dialog.orgName"></display:column>
						<display:column property="role.systemName" titleKey="sysRole.system"></display:column>
					</display:table>
	   		</div>
	 		
			<div position="bottom"  class="bottom" style='margin-top:10px;'>
				<a href='#' class='button' onclick="selectRole()" ><span class="icon ok"></span><span ><spr:message code="menu.button.choose"/></span></a>
				<a href='#' class='button' style='margin-left:10px;' onclick="window.close()"><span class="icon cancel"></span><span ><spr:message code="menu.button.cancel"/></span></a>
			</div>		
	</div>	
</body>
</html>