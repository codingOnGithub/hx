
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<%@include file="/commons/include/get.jsp" %>
	<title><spr:message code="globalType"/><spr:message code="title.list"/></title>
	<f:js pre="js/lang/view/platform/system" ></f:js>
	<script type="text/javascript">
		function addNew(parentId,catKey,returnUrl,type)
		{
			if(parentId==0)
			{
				 $.ligerDialog.warn($lang_system.globalType.list.validate_msg_category);
				 return;
			}
			else
			{
				location.href="add1.ht?parentId="+parentId+"&catKey="+catKey+"&type="+type+"&returnUrl="+returnUrl;
			}
		}
	</script>
</head>
<body>
			<div class="panel">
			<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label">${parent.typeName }</span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group"><a class="link search" id="btnSearch"><spr:message code="menu.button.search"/></a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link add" onclick="addNew('${parentId}','${catKey}','${returnUrl}','${type}')"  href="#"><span></span><spr:message code="menu.button.add"/></a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link del"  action="del.ht"><span></span><spr:message code="menu.button.del"/></a></div>
						</div>	
					</div>
				</div>
				</div>
				<div class="panel-body">
					<div class="panel-search">
							<form id="searchForm" method="post" action="list.ht">
									<ul class="row">
												<li><span class="label"><spr:message code="globalType.typeName"/>:</span><input type="text" name="Q_typeName_S"  class="inputText" value="${param['Q_typeName_S']}"/></li>
											
												<li><span class="label" style="display: none;"><spr:message code="globalType.nodePath"/></span><input type="hidden" name="Q_nodePath_S"  class="inputText" value="${Q_nodePath_S }" readonly="readonly"/></li>
									</ul>
							</form>
					</div>
					<br/>
					<div class="panel-data">
				    	<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <display:table name="globalTypeList" id="globalTypeItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" export="false"  class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;">
								  	<input type="checkbox" class="pk" name="typeId" value="${globalTypeItem.typeId}">
							</display:column>
							<display:column property="typeName" titleKey="globalType.typeName" sortable="true" sortName="typeName" style="text-align:left"></display:column>
							<display:column property="nodeKey" titleKey="globalType.nodeKey" sortable="true" sortName="nodeKey" style="text-align:left"></display:column>
							<display:column property="catKey" titleKey="globalType.catKey" sortable="true" sortName="catKey" style="text-align:left"></display:column>
							<display:column titleKey="globalType.type" sortable="true" sortName="type" >
								<c:choose>
									<c:when test="${globalTypeItem.type==0}">
										<div title="<spr:message code="globalType.type.tile"/>" class="icon-list">&nbsp;</div>
								   	</c:when>
							       	<c:otherwise>
								        <div title="<spr:message code="globalType.type.tree"/>" class="icon-tree">&nbsp;</div>
								   	</c:otherwise>
								</c:choose>
							</display:column>
							<display:column titleKey="list.manage" media="html" style="width:180px;text-align:center">
								<a href="del.ht?typeId=${globalTypeItem.typeId}" class="link del"><spr:message code="operator.del"/></a>
								<a href="upd1.ht?typeId=${globalTypeItem.typeId}&returnUrl=${returnUrl}" class="link edit"><spr:message code="operator.edit"/></a>
								<a href="get.ht?typeId=${globalTypeItem.typeId}" class="link detail"><spr:message code="operator.detail"/></a>
							</display:column>
						</display:table>
						<hotent:paging tableId="globalTypeItem"/>
					</div>
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
</html>


