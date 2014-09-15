<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title></title>
<%@include file="/commons/include/get.jsp" %>
<f:js pre="js/lang/view/platform/system" ></f:js>
<script type="text/javascript" src="${ctx }/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx }/js/hotent/platform/system/GradeRoleSelectDialog.js"></script>
<script type="text/javascript">
	var orgId="${orgId}";
	var scope="${scope}";
	
	var reloadUrl=__ctx + "/platform/system/sysOrgRole/roleList.ht?orgId=${orgId}";
	var url=__ctx + "/platform/system/sysOrgRole/addOrgRole.ht";
	if(scope=="grade"){
		reloadUrl=__ctx + "/platform/system/sysOrgRole/roleGradeList.ht?orgId=${orgId}";
		url=__ctx + "/platform/system/sysOrgRole/addGradeOrgRole.ht";
	}
	
		
	function dlgCallBack(roles){
		para="roleIds="+roles+"&orgId="+orgId;
		$.post(url,para,function(data){
			var obj=new com.hotent.form.ResultMessage(data);
			if(obj.isSuccess()){
				 $.ligerDialog.success(obj.getMessage(),$lang.tip.msg,function(rtn){
					  location.href=reloadUrl;
				 });
			}else{
				$.ligerDialog.err($lang.tip.error,$lang_system.sysOrg.assignRoles_fail,obj.getMessage());
			}
		});
	};
	
	

	$(function(){
		$("a.add").click(function(){
			if(scope=="grade"){
				GradeRoleSelectDialog({orgId: orgId,callback:dlgCallBack});
			}
			else{
				RoleDialog({callback:dlgCallBack});
			}		
		});		
	});
</script>
</head>
<body>
<div class="panel">
       
       <c:choose>
	   		<c:when test="${scope eq 'global' }">
	   			<f:tab curTab="authorize" tabName="sysOrg"/>
	   		</c:when>
	   		<c:otherwise>
	   			<f:tab curTab="authorize" tabName="sysOrgGrade"/>
	   		</c:otherwise>
	   </c:choose>
       <c:choose>
       		<c:when test="${empty sysOrg}">
					<div style="text-align: center;margin-top: 10%;"><spr:message code="sysUserOrg.emptyOrg"/></div>
				</c:when>
       		<c:otherwise>
       		<div class="hide-panel">
       		<div class="panel-top">
	       		<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group"><a class="link add" href="javascript:;"><span></span><spr:message code="menu.button.assignRoles"/></a></div>
						<div class="l-bar-separator"></div>
						<c:choose>
							<c:when test="${scope=='global' }">
								<div class="group"><a class="link del" action="${ctx}/platform/system/sysOrgRole/del.ht"><span></span><spr:message code="sys.remove"/></a></div>
							</c:when>
							<c:otherwise>
								<div class="group"><a class="link del" action="${ctx}/platform/system/sysOrgRole/delGrade.ht"><span></span><spr:message code="sys.remove"/></a></div>
							</c:otherwise>
						</c:choose>
					</div>	
				</div>
		 	</div>
		 	</div>
			<div class="panel-body">
			        <c:set var="checkAll">
					<input type="checkbox" id="chkall"/>
					</c:set>
				    <display:table name="orgRoles" id="orgRole" cellpadding="1" cellspacing="1" export="false" class="table-grid">
						<display:column title="${checkAll}" media="html" style="width:30px;text-align:center;">							
							<c:choose>
							<c:when test="${scope=='global' }">
								<c:choose>
									<c:when test="${orgRole.orgid==orgId}">
										<input type="checkbox" class="pk" name="id" value="${orgRole.id}"/>
									</c:when>
									<c:otherwise>
										<input type="checkbox" class="pk" disabled="disabled" name="id" value="${orgRole.id}"/>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${orgRole.orgid==orgId && orgRole.canDel==1 }">
										<input type="checkbox" class="pk" name="id" value="${orgRole.id}"/>
									</c:when>
									<c:otherwise>
										<input type="checkbox" class="pk" disabled="disabled" name="id" value="${orgRole.id}"/>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
						</display:column>
										
						<display:column property="role.roleName" titleKey="sysRole.roleName"></display:column>
						<display:column property="role.memo" titleKey="sysRole.memo"></display:column>
						<display:column property="orgName" titleKey="sysUser.dialog.orgName"></display:column>
						<display:column property="role.systemName" titleKey="sysRole.system"></display:column>
						<display:column titleKey="list.manage" media="html" >
							<c:choose>
								<c:when test="${scope=='global' }">
									<c:choose>
										<c:when test="${orgRole.orgid==orgId}">
											<a href="${ctx}/platform/system/sysOrgRole/del.ht?id=${orgRole.id}" class="link del"><spr:message code="sys.remove"/></a>
										</c:when>
										<c:otherwise>
											<a  class="link del disabled"><spr:message code="sys.remove"/></a>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${orgRole.orgid==orgId && orgRole.canDel==1 }">
											<a href="${ctx}/platform/system/sysOrgRole/delGrade.ht?id=${orgRole.id}" class="link del"><spr:message code="sys.remove"/></a>
										</c:when>
										<c:otherwise>
											<a  class="link del disabled"><spr:message code="sys.remove"/></a>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</display:column>
					</display:table>
	   		</div>
       		</c:otherwise>
       </c:choose>       
	  </div> 					
</body>
</html>