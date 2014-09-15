
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/get.jsp"%>
<title><spr:message code="bpmFormIndex.title"/></title>

<script type="text/javascript">
	function editIndex(tableId, tableName, indexName) {
		var url = __ctx + "/platform/form/bpmFormTable/indexDialog.ht?isAdd=0&" + "tableId=" + tableId + "&tableName=" + encodeURIComponent(tableName) + "&indexName=" + encodeURIComponent(indexName);
		var winArgs = "dialogWidth=800px;dialogHeight=600px;help=0;status=0;scroll=1;center=0;resizable=1;";
		url = url.getNewUrl();
		var rtn = window.showModalDialog(url, "", winArgs);
		window.location.reload(true);
	}
	function addIndex() {
		var tableId = '${tableId}';
		var tableName = '${table.tableName}';
		var url = __ctx + "/platform/form/bpmFormTable/indexDialog.ht?isAdd=1&" + "tableId=" + tableId + "&tableName=" + encodeURIComponent(tableName);
		var winArgs = "dialogWidth=800px;dialogHeight=600px;help=0;status=0;scroll=1;center=0;resizable=1;";
		url = url.getNewUrl();
		var rtn = window.showModalDialog(url, "", winArgs);
		window.location.reload(true);
	}

</script>
</head>
<body>
	<div class="panel">

		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="bpmFormIndex.title"/><spr:message code="operator.manageList"/></span>
			</div>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group">
					<a onclick="addIndex()" class="link add"><span></span><spr:message code="menu.button.add"/></a>
				</div>
				<div class="l-bar-separator"></div>
				<div class="group">
					<a class="link back" href="list.ht"><span></span><spr:message code="menu.button.back"/></a>
				</div>
			</div>
		</div>
		<div class="panel-data">
			<form id="frmIndexes">
				<table cellpadding="0" cellspacing="0" border="0" style="" class="table-grid">
					<tr class="even">
						<td><spr:message code="bpmFormTable.tableName"/>：${table.tableName }&nbsp;&nbsp;&nbsp;<spr:message code="bpmFormTable.tableDesc"/>：${table.tableDesc }</td>
					</tr>
				</table>
				<display:table name="tableIndexes" id="index" requestURI="editIndex.ht" sort="external" cellpadding="1" cellspacing="1" export="false" class="table-grid">
					<display:column property="indexName" titleKey="bpmFormIndex.indexName" style="text-align:left"></display:column>
					<display:column property="indexType" titleKey="bpmFormIndex.indexType" style="text-align:left"></display:column>
					<display:column titleKey="bpmFormIndex.indexField" style="text-align:left">
						<c:forEach items="${index.indexFields}" var="field">
							${field }<br />
						</c:forEach>
					</display:column>
					<display:column property="indexStatus" titleKey="bpmFormIndex.indexStatus" style="text-align:left"></display:column>
					<display:column titleKey="list.manage" media="html" style="text-align:left;">
						<c:if test="${index.pkIndex}">
						
						</c:if>
						<c:choose>
							<c:when test="${index.pkIndex}">
								<font color="blue"><spr:message code="bpmFormIndex.pkInde"/></font> 
							</c:when>
							<c:otherwise>
								<a href="#" class="link edit" onclick="editIndex('${tableId }','${table.tableName}','${index.indexName}')"><spr:message code="menu.button.edit"/></a>
								<a href="delIndex.ht?tableName=${table.tableName}&indexName=${index.indexName}" class="link del"><spr:message code="menu.button.del"/></a>
							</c:otherwise>
						</c:choose>
					</display:column>
				</display:table>
			</form>
		</div>
	</div>
</body>
</html>


