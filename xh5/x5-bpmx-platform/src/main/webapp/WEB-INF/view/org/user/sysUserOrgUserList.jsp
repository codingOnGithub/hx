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
	$(function() {/*
		$("#charge").click(function() {
			var ele = this;
			var url=ele.href;
			$.post(url,function(data)  { 
				var obj=new com.hotent.form.ResultMessage(data);
				if(obj.isSuccess()){
					 $.ligerDialog.success(obj.getMessage(),$lang.tip.msg,function(rtn){
						 var orgId="${orgId}";
						 var path="${path}";
						 location.href="userList.ht?orgId="+orgId+"&path="+path;
						 var node = parent.selectNode;
						 if(node){
							 parent.loadTree(node.demId);
						 }
					 });
				}else{
					$.ligerDialog.err($lang.tip.error,obj.getMessage());
				}
			});
			return false;
		});
		
		$("#noCharge").click(function() {
			var ele = this;
			var url=ele.href;
			$.post(url,function(data)  { 
				var obj=new com.hotent.form.ResultMessage(data);
				if(obj.isSuccess()){
					 $.ligerDialog.success(obj.getMessage(),$lang.tip.msg,function(rtn){
						 var orgId="${orgId}";
						 var path="${path}";
						 location.href="userList.ht?orgId="+orgId+"&path="+path;
						 var node = parent.selectNode;
						 if(node){
							 parent.loadTree(node.demId);
						 }
					 });
				}else{
					$.ligerDialog.err($lang.tip.error,obj.getMessage());
				}
			});
			return false;
		});*/
	});
	function dlgCallBack(userIds){
		var orgId="${orgId}";
		var path="${path}";
		var url="addOrgUser.ht";
		para="userIds="+userIds+"&orgId="+orgId;
		$.post(url,para,function(data){
			var obj=new com.hotent.form.ResultMessage(data);
			if(obj.isSuccess()){
				 $.ligerDialog.success(obj.getMessage(),$lang.tip.msg,function(rtn){
					  location.href="userList.ht?orgId="+orgId+"&path="+path;
				 });
			}else{
				$.ligerDialog.err($lang.tip.error,$lang_system.sysOrg.searchFail,obj.getMessage());
			}
		  
		});
	};
	function addClick(){
		UserDialog({callback:dlgCallBack,isSingle:false});
	};

</script>
</head>
<body>
<div class="panel">
		<c:choose>
		  	<c:when test="${action=='global' }">
		  		<f:tab curTab="user" tabName="sysOrg"/>
		  	</c:when>
		  	<c:otherwise>
		  		<f:tab curTab="user" tabName="sysOrgGrade"/>
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
						<div class="toolBar">
						<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>
						<div class="l-bar-separator"></div>
						<c:if test="${action=='global'}">
							<div class="group"><a class="link add"  href="${ctx}/platform/system/sysUser/edit.ht?orgId=${orgId}&returnUrl=${ctx}/platform/system/sysUserOrg/userList.ht?orgId=${orgId}"><span></span><spr:message code="sysUserOrg.addNewUser"/></a></div>
							<div class="l-bar-separator"></div>
						</c:if>
						<%-- 
						<div class="group"><a class="link add"  href="#" onclick="addClick()"><span></span><spr:message code="sysUserOrg.addUser"/></a></div>
						--%>
						<div class="l-bar-separator"></div>
						<div class="group"><a class="link del" action="${ctx}/platform/system/sysUserOrg/del.ht"><span></span><spr:message code="sys.remove"/></a></div>
					</div>	
					</div>	
				</div>
				<div class="panel-search">
					<form id="searchForm" method="post" action="userList.ht?orgId=${orgId}">
						<ul class="row">
							<li><span class="label"><spr:message code="sysUser.fullname"/></span><input type="text" name="Q_fullname_SL"  class="inputText" style="width:110px !important" value="${param['Q_fullname_SL']}"/></li>
							<li><span class="label"><spr:message code="sysUser.account"/></span><input type="text" name="Q_account_SL"  class="inputText" style="width:110px !important" value="${param['Q_account_SL']}"/></li>
						</ul>
					</form>
		 		</div>
		 	</div>
		 	</div>
			<div class="panel-body">
			        <c:set var="checkAll">
					<input type="checkbox" id="chkall"/>
					</c:set>
				    <display:table name="userPositionList" id="userPositionItem"  requestURI="userList.ht" sort="external" cellpadding="1" cellspacing="1" export="false"  class="table-grid">
						<display:column title="${checkAll}" media="html" style="width:30px;text-align:center;">
							  	<input type="checkbox" class="pk" name="userPosId" value="${userPositionItem.userPosId}">
						</display:column>
						<display:column property="orgName" titleKey="sysUser.dialog.orgName" sortable="true" sortName="orgName"></display:column>
						<display:column property="posName" titleKey="sysUser.pos" sortable="true" sortName="posId"></display:column>
							<%-- 
						<display:column property="userName" titleKey="sysUser.fullname" sortable="true" sortName="userId">
					      --%>
						<display:column  titleKey="sysUser.fullname" sortable="true" sortName="userId">
							<f:userName userId="${userPositionItem.userId}" />
						</display:column>
						<display:column property="account" titleKey="sysUser.account" sortable="true" sortName="userId"></display:column>
						<display:column   titleKey="sysUserOrg.mainPos" >
							<c:choose>
								<c:when test="${userPositionItem.isPrimary==1}"><span class="green"><spr:message code="yes"/></span></c:when>
								<c:otherwise><span class="red"><spr:message code="no"/></span></c:otherwise>
							</c:choose>
						</display:column>
						<display:column   titleKey="sysUserOrg.header" >
							<c:choose>
								<c:when test="${userPositionItem.isCharge==1}"><span class="green"><spr:message code="yes"/></span></c:when>
								<c:otherwise><span class="red"><spr:message code="no"/></span></c:otherwise>
							</c:choose>
						</display:column>
						<%-- 
						<display:column   titleKey="sysUserOrg.admin" >
							<c:choose>
								<c:when test="${sysOrgItem.isGradeManage==1}"><span class="green"><spr:message code="yes"/></span></c:when>
								<c:otherwise><span class="red"><spr:message code="no"/></span></c:otherwise>
							</c:choose>
						</display:column>
						 --%>
						
 						<display:column titleKey="list.manage" media="html" style="width:50px;text-align:center" class="rowOps">
							<a href="${ctx}/platform/system/sysUserOrg/del.ht?userOrgId=${userPositionItem.userPosId}" class="link del"><spr:message code="menu.button.del"/></a>
							
							<a href="${ctx}/platform/system/sysUser/get.ht?userId=${userPositionItem.userId}&canReturn=1" class="link detail"><spr:message code="menu.button.detail"/></a>
							<c:choose>
								<c:when test="${userPositionItem.isPrimary==0}">
									<a class="link primary" href="setIsPrimary.ht?userPosId=${userPositionItem.userPosId}"><spr:message code="sysUserOrg.primaryPos"/></a>
								</c:when>
								<c:otherwise>
									<a class="link notPrimary" href="setIsPrimary.ht?userPosId=${userPositionItem.userPosId}"><spr:message code="sysUserOrg.notPrimaryPos"/></a>
								</c:otherwise>
							</c:choose>
							<c:choose>
									<c:when test="${userPositionItem.isCharge==0}">
										<a class="link charge" id="charge" title="<spr:message code="sysUserOrg.setHeader"/>" href="setIsCharge.ht?userPosId=${userPositionItem.userPosId}"><spr:message code="sysUserOrg.setHeader"/></a>
									</c:when>
									<c:otherwise>
										<a class="link noCharge" id="noCharge" title="<spr:message code="sysUserOrg.setNotHeader"/>" href="setIsCharge.ht?userPosId=${userPositionItem.userPosId}"><spr:message code="sysUserOrg.setNotHeader"/></a>
									</c:otherwise>
							</c:choose>
							<%-- 
							<c:choose>
									<c:when test="${sysOrgItem.isGradeManage==0}">
										<a class="link charge" title="设为组织管理者，可取得分级授权" href="setIsManage.ht?userPosId=${sysOrgItem.userOrgId}"><spr:message code="sysUserOrg.setOrgAdmin"/></a>
									</c:when>
									<c:otherwise>
										<a class="link noCharge" title="设为非组织管理者，取消分级授权" href="setIsManage.ht?userPosId=${sysOrgItem.userOrgId}"><spr:message code="sysUserOrg.setNotOrgAdmin"/></a>
									</c:otherwise>
							</c:choose>
							 --%>
						</display:column>
					</display:table>
					<hotent:paging tableId="userPositionItem"/>
	  		
	   		</div>
       		</c:otherwise>
       </c:choose>
       
	  </div> 					
</body>
</html>