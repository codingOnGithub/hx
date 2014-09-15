<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>

<%@include file="/commons/include/get.jsp"%>
<title><spr:message code="sys.userrole.Title"/></title>
<script type="text/javascript" src="${ctx }/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript">
	function dlgCallBack(userIds, fullnames) {
		if (userIds.length > 0) {
			var form = new com.hotent.form.Form();
			form.creatForm("form", "${ctx}/platform/system/userRole/add.ht");
			form.addFormEl("roleId", "${roleId}");
			form.addFormEl("userIds", userIds);
 	 		form.submit();
		}
	};

	function add() {
		UserDialog({
			callback : dlgCallBack,
			isSingle : false
		});
	}
</script>
</head>
<body>
	<div class="panel">
		<div class="hide-panel">
			<div class="panel-top">
				<div class="tbar-title">
					<span class="tbar-label"><spr:message code="sys.userrole.Title"/></span>
				</div>
				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>
						<div class="l-bar-separator"></div>
						<div class="group">
							<a class="link add" href="javascript:add();"><span></span><spr:message code="sys.userrole.addUser"/></a>
						</div>
						<div class="l-bar-separator"></div>
						<div class="group">
							<a class="link del" action="del.ht"><span></span><spr:message code="menu.button.del"/></a>
						</div>
						<div class="l-bar-separator"></div>
						<div class="group">
							<a class="link back"
								href="${ctx }/platform/system/sysRole/list.ht"><span></span><spr:message code="menu.button.back"/></a>
						</div>
					</div>
				</div>
				<div class="panel-search">
					<form id="searchForm" method="post" action="edit.ht?roleId=${roleId}&roleName=${roleName}">
							<ul class="row">
								<li><span class="label"><spr:message code="sysUser.fullname"/>:</span><input type="text" name="Q_fullname_SL"  class="inputText" value="${param['Q_fullname_SL']}"/></li>
								<li><span class="label"><spr:message code="sysUser.account"/>:</span><input type="text" name="Q_account_SL"  class="inputText" value="${param['Q_account_SL']}"/>			</li>		
							</ul>
							
					</form>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<c:set var="checkAll">
				<input type="checkbox" id="chkall" />
			</c:set>
			<display:table name="userRoleList" id="userRoleItem"
				requestURI="edit.ht" sort="external" cellpadding="1"
				cellspacing="1" export="false" class="table-grid">
				<display:caption style="text-align:center;height:30px;background-color:#DDDDDD;color:#444444" ><font size="3" ><spr:message code="sys.userrole.titleName" arguments="${roleName }"/></font></display:caption>
				<display:column title="${checkAll}" media="html" style="width:30px;">
					<input type="checkbox" class="pk" name="userRoleId" value="${userRoleItem.userRoleId}">
				</display:column>

				<display:column property="fullname" titleKey="bpmRunLog.username" sortable="true" sortName="fullname"></display:column>
				<display:column property="account" titleKey="sysUser.account" sortable="true" sortName="account"></display:column>

				<display:column titleKey="list.manage" media="html" style="width:180px">
					<a href="del.ht?userRoleId=${userRoleItem.userRoleId}" class="link del"><spr:message code="menu.button.del"/></a>
				</display:column>
			</display:table>
			<hotent:paging tableId="userRoleItem"/>
		</div>
		<!-- end of panel-body -->
	</div>
	<!-- end of panel -->
</body>
</html>


