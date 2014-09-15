<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="sys.pos.posManage"/></title>
<f:js pre="js/lang/view/platform/system" ></f:js>

<script type="text/javascript" src="${ctx }/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript">
	$(function() {
	});
	    //选择后的回调函数，保存岗位用户关系数据
		function dlgCallBack(userIds){
		var posId=$("input[type=checkbox]:checked").val();
		//alert(posId);
		//var path="${path}";
		var url="${ctx}/platform/system/userPosition/addPosUser.ht";   //添加人员，保存人员岗位关系
		para="userIds="+userIds+"&posId="+posId;
		$.post(url,para,function(data){
			var obj=new com.hotent.form.ResultMessage(data);
			if(obj.isSuccess()){
				 $.ligerDialog.success(obj.getMessage(),$lang.tip.msg,function(rtn){
					  //location.href="list.ht?posId="+posId+"&orgId="+${orgId};
					 location.href="list.ht?orgId="+${orgId};
				 });
			}else{
				$.ligerDialog.err($lang.tip.error,$lang_system.sysOrg.searchFail,obj.getMessage());
			}
		  
		});
	};
	function addClick(){
		 chb=$("input[type=checkbox]:checked");
		if(chb.length==0){$.ligerDialog.warn($lang_system.sysOrg.warn_select_leastOne,$lang.tip.msg)}
		if(chb.length>=2){$.ligerDialog.warn($lang_system.sysOrg.warn_select_onlyOne,$lang.tip.msg)}
		if(chb.length==1){
		UserDialog({callback:dlgCallBack,isSingle:false});
		}
	};

</script>
</head>
<body>
	<div class="panel">
		<c:choose>
		    <%--全局的 global,分级授权grade--%>
		  	<c:when test="${action=='global' }">
		  		<f:tab curTab="pos" tabName="sysOrg"/>
		  	</c:when>
		  	<c:otherwise>
		  		<f:tab curTab="pos" tabName="sysOrgGrade"/>
		  	</c:otherwise>
	   </c:choose>
	   
  <c:choose>
    <c:when test="${orgId==0}">
		<div style="text-align: center;margin-top: 10%;"><spr:message code="sysUserOrg.emptyOrg"/></div>
	</c:when>
    <c:otherwise>
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="search.user.pos"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>
					<div class="l-bar-separator"></div>

					<div class="group"><a class="link add"  href="#" onclick="addClick()"><span></span><spr:message code="position.addUser"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span><spr:message code="menu.button.alter"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span><spr:message code="menu.button.del"/></a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht?orgId=${orgId}">
					<div class="row">
						<span class="label"><spr:message code="position.posName"/>:</span><input type="text" name="Q_posname_SL"  class="inputText" />
						<span class="label"><spr:message code="position.posDesc"/>:</span><input type="text" name="Q_posdesc_SL"  class="inputText" />
						<%-- 
						<span class="label"><spr:message code=""/>组织ID:</span><input type="text" name="Q_orgid_SL"  class="inputText" />
						<span class="label"><spr:message code=""/>职务ID:</span><input type="text" name="Q_jobid_SL"  class="inputText" />
						<span class="label"><spr:message code=""/>是否删除:</span><input type="text" name="Q_isdelete_SL"  class="inputText" />
						--%>
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="positionList" id="positionItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="posId" value="${positionItem.posId}">
				</display:column>
				<display:column property="posName" titleKey="position.posName" sortable="true" sortName="POSNAME"></display:column>
				<display:column property="posDesc" titleKey="position.posDesc" sortable="true" sortName="POSDESC" maxLength="80"></display:column>
				<display:column property="orgName" titleKey="sysOrg.orgName" sortable="true" sortName="ORGID"></display:column>
				<display:column property="jobName" titleKey="position.job" sortable="true" sortName="JOBID"></display:column>
				<display:column property="userNames" titleKey="position.user" ></display:column>
				<%-- 
				<display:column property="isDelete" titleKey="position.isDeleted" sortable="true" sortName="ISDELETE"></display:column>
				--%>
				<display:column titleKey="list.manage" media="html" style="width:220px">
					<a href="del.ht?posId=${positionItem.posId}" class="link del"><spr:message code="menu.button.del"/></a>
					<a href="edit.ht?posId=${positionItem.posId}" class="link edit"><spr:message code="menu.button.edit"/></a>
					<a href="get.ht?posId=${positionItem.posId}" class="link detail"><spr:message code="menu.button.detail"/></a>
				</display:column>
			</display:table>
			<hotent:paging tableId="positionItem"/>
		</div><!-- end of panel-body -->
		</c:otherwise>
       </c:choose>				
	</div> <!-- end of panel -->
</body>
</html>


