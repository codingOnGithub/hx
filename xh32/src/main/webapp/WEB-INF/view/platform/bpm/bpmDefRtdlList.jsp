
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/include/get.jsp"%>
<title><spr:message code="bpmDefRights.title.manage"/></title>

<script type="text/javascript">
	function save(){
		document.getElementById('dataForm').submit();
	}
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">${bpmDefinition.subject}-<spr:message code="bpmDefRights.setting"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link add" onclick="save()"><span></span><spr:message code="menu.button.save"/></a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<div class="panel-data">
				<form action="save.ht" method="post" id="dataForm">
					<display:table name="bpmDefRtdlList" id="bpmDefRtdlItem"
						requestURI="list.ht" sort="external" cellpadding="1"
						cellspacing="1" export="true" class="table-grid">
						<display:column property="rightId" titleKey="bpmDefRights.list.rightDefId" sortable="true"
							sortName="rightId"></display:column>
						<display:column property="defId" titleKey="bpmDefRights.defId" sortable="true"
							sortName="defId"></display:column>
						<display:column property="typeId" titleKey="bpmDefRights.list.typeId" sortable="true"
							sortName="typeId"></display:column>
						<display:column property="roleId" titleKey="bpmDefRights.list.roleId" sortable="true"
							sortName="roleId"></display:column>
						<display:column property="userId" titleKey="bpmDefRights.list.userId" sortable="true"
							sortName="userId"></display:column>
						<display:column property="orgId" titleKey="bpmDefRights.list.orgId" sortable="true"
							sortName="orgId"></display:column>
						<display:column titleKey="list.manage" media="html" style="width:180px">
							<a href="del.ht?detailId=${bpmDefRtdlItem.detailId}"
								class="link del">...</a>
							<a href="edit.ht?detailId=${bpmDefRtdlItem.detailId}"
								class="link edit"><spr:message code="bpmNodePrivilege.clean"/></a>
						</display:column>
					</display:table>
				</form>
			</div>
		</div>
		<!-- end of panel-body -->
	</div>
	<!-- end of panel -->
</body>
</html>


