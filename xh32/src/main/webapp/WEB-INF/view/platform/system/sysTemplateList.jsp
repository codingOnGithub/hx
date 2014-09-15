<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/get.jsp"%>
<title><spr:message code="sysTemplate"/><spr:message code="operator.manageList"/></title>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/ImportExportXmlUtil.js"  charset="UTF-8"></script>
<script type="text/javascript">
//导出自定义表
function ExportXml(){
	var ids = "";
	$("input[type='checkbox'][class='pk']:checked").each(function(){ 
			var obj=$(this);
			ids+= obj.val()+",";
	
	});
	if(ids==''){
		$.ligerDialog.warn($lang_system.sysTemplate.list.export_validate_msg);
		return;
	}else{
		ids=ids.substring(0,ids.length-1);
	}
	var url = __ctx + "/platform/system/sysTemplate/exportXml.ht";
	$("#exportIds").val(ids);
	$("#exportXmlForm").submit();
};
//导入自定义表
function ImportXml(){
	var url= __ctx + "/platform/system/sysTemplate/import.ht";
	url=url.getNewUrl();
	var winArgs="dialogWidth:600px;dialogHeight:400px;help:0;status:1;scroll:1;center:1";
	var rtn=window.showModalDialog(url,"",winArgs);
	if(rtn){
		window.location.reload(true);
	}
}
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="sysTemplate"/><spr:message code="operator.manageList"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link add" href="edit.ht"><span></span><spr:message code="menu.button.add"/></a>
					</div>
					<div class="group">
						<a class="link download"  href="#" onclick="ExportXml()"><span></span><spr:message code="menu.button.export"/></a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link upload"  href="#" onclick="ImportXml()"><span></span><spr:message code="menu.button.import"/></a>
					</div>
				</div>
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<ul class="row">
					<li>
						<span class="label"><spr:message code="sysTemplate.name"/>:</span>
						<input type="text" name="Q_name_SL" class="inputText" value="${param['Q_name_SL']}"/>
					</li>
					<li>
						<span class="label"><spr:message code="sysTemplate.useType"/>:</span>
						<select name="Q_useType_L">
							<option value="">------<spr:message code="search.select.allSelect"/>------</option>
							<option value="1"<c:if test="${param['Q_useType_L'] eq 1}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processComplete"/></option>
							<option value="2"<c:if test="${param['Q_useType_L'] eq 2}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processReminder"/></option>
							<option value="3"<c:if test="${param['Q_useType_L'] eq 3}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processCheck"/></option>
							<option value="4"<c:if test="${param['Q_useType_L'] eq 4}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processRevoked"/></option>
							<option value="5"<c:if test="${param['Q_useType_L'] eq 5}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processCancelDelegate"/></option>
							<option value="6"<c:if test="${param['Q_useType_L'] eq 6}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processCommunication"/></option>
							<option value="7"<c:if test="${param['Q_useType_L'] eq 7}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processStartuser"/></option>
							<option value="8"<c:if test="${param['Q_useType_L'] eq 8}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processDelegate"/></option>
							<option value="9"<c:if test="${param['Q_useType_L'] eq 9}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processReject"/></option>
							<option value="10"<c:if test="${param['Q_useType_L'] eq 10}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processFeedback"/></option>
							<option value="11"<c:if test="${param['Q_useType_L'] eq 11}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processCancelAgent"/></option>
							<option value="12"<c:if test="${param['Q_useType_L'] eq 12}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processCopyTo"/></option>
							<option value="19"<c:if test="${param['Q_useType_L'] eq 19}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processOverDue"/></option>
							<option value="22"<c:if test="${param['Q_useType_L'] eq 22}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processAgent"/></option>
							<option value="23"<c:if test="${param['Q_useType_L'] eq 23}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processForward"/></option>
							<option value="24"<c:if test="${param['Q_useType_L'] eq 24}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.restartTask"/></option>
							<option value="25"<c:if test="${param['Q_useType_L'] eq 25}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processNotifyTaskOwner"/></option>
							<option value="26"<c:if test="${param['Q_useType_L'] eq 26}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processTransToRemind"/></option>
							<option value="27"<c:if test="${param['Q_useType_L'] eq 27}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processTransToFeedback"/></option>
							<option value="28"<c:if test="${param['Q_useType_L'] eq 28}"> selected="selected"</c:if>><spr:message code="sysTemplate.useType.processCancelTransTo"/></option>
						</select>
					</li>
					</ul>
				</form>
			</div>
		</div>
		<div class="panel-body">
			<c:set var="checkAll">
				<input type="checkbox" id="chkall" />
			</c:set>
			<display:table name="sysTemplateList" id="sysTemplateItem"
				requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"
				class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
					<input type="checkbox" class="pk" name="templateId" value="${sysTemplateItem.templateId}">
				</display:column>

				<display:column property="name" titleKey="sysTemplate.name" sortable="true" sortName="name"></display:column>
					
				<display:column titleKey="sysTemplate.useType">
					<c:choose>
						<c:when test="${sysTemplateItem.useType eq 1}"><spr:message code="sysTemplate.useType.processComplete"/></c:when>
						<c:when test="${sysTemplateItem.useType eq 2}"><spr:message code="sysTemplate.useType.processReminder"/></c:when>
						<c:when test="${sysTemplateItem.useType eq 3}"><spr:message code="sysTemplate.useType.processCheck"/></c:when>
						<c:when test="${sysTemplateItem.useType eq 4}"><spr:message code="sysTemplate.useType.processRevoked"/></c:when>
						<c:when test="${sysTemplateItem.useType eq 5}"><spr:message code="sysTemplate.useType.processCancelDelegate"/></c:when>
						<c:when test="${sysTemplateItem.useType eq 6}"><spr:message code="sysTemplate.useType.processCommunication"/></c:when>
						<c:when test="${sysTemplateItem.useType eq 7}"><spr:message code="sysTemplate.useType.processStartuser"/></c:when>
						<c:when test="${sysTemplateItem.useType eq 8}"><spr:message code="sysTemplate.useType.processDelegate"/></c:when>
						<c:when test="${sysTemplateItem.useType eq 9}"><spr:message code="sysTemplate.useType.processReject"/></c:when>
						<c:when test="${sysTemplateItem.useType eq 10}"><spr:message code="sysTemplate.useType.processFeedback"/></c:when>
						<c:when test="${sysTemplateItem.useType eq 11}"><spr:message code="sysTemplate.useType.processCancelAgent"/></c:when>
						<c:when test="${sysTemplateItem.useType eq 12}"><spr:message code="sysTemplate.useType.processCopyTo"/></c:when>
						<c:when test="${sysTemplateItem.useType eq 13}"><spr:message code="sysTemplate.useType.processNobody"/></c:when>
						<c:when test="${sysTemplateItem.useType eq 14}"><spr:message code="sysTemplate.useType.processTaskWarn"/></c:when>
						<c:when test="${sysTemplateItem.useType eq 15}"><spr:message code="sysTemplate.useType.processTaskEnd"/></c:when>
						<c:when test="${sysTemplateItem.useType eq 16}"><spr:message code="sysTemplate.useType.processTaskValuate"/></c:when>
						<c:when test="${sysTemplateItem.useType eq 17}"><spr:message code="sysTemplate.useType.processTaskNotity"/></c:when>
						<c:when test="${sysTemplateItem.useType eq 18}"><spr:message code="sysTemplate.useType.processTaskValuateTell"/></c:when>
						<c:when test="${sysTemplateItem.useType eq 19}"><spr:message code="sysTemplate.useType.processOverDue"/></c:when>
						<c:when test="${sysTemplateItem.useType eq 22}"><spr:message code="sysTemplate.useType.processAgent"/></c:when>
						<c:when test="${sysTemplateItem.useType eq 23}"><spr:message code="sysTemplate.useType.processForward"/></c:when>
						<c:when test="${sysTemplateItem.useType eq 24}"><spr:message code="sysTemplate.useType.restartTask"/></c:when>
						<c:when test="${sysTemplateItem.useType eq 25}"><spr:message code="sysTemplate.useType.processNotifyTaskOwner" /></c:when>
						<c:when test="${sysTemplateItem.useType eq 26}"><spr:message code="sysTemplate.useType.processTransToRemind" /></c:when>
						<c:when test="${sysTemplateItem.useType eq 27}"><spr:message code="sysTemplate.useType.processTransToFeedback" /></c:when>
						<c:when test="${sysTemplateItem.useType eq 28}"><spr:message code="sysTemplate.useType.processCancelTransTo" /></c:when>
						<c:otherwise><spr:message code="sysTemplate.useType.other"/></c:otherwise>
					</c:choose>
				</display:column>
				<display:column titleKey="sysTemplate.isDefault">
					<c:choose>
						<c:when test="${sysTemplateItem.isDefault==1}"><span class="green"><spr:message code="yes"/></span></c:when>
						<c:otherwise><spr:message code="no"/></c:otherwise>
					</c:choose>
				</display:column>
				<display:column title="操作" media="html" style="width:50px;text-align:center" class="rowOps">	
					<c:choose>
						<c:when test="${sysTemplateItem.isDefault==1}">
							<a class="link setting disabled"><spr:message code="sysTemplate.mange.setDefault"/></a>
						</c:when>
						<c:otherwise>
							<a class="link setting" href="setDefault.ht?templateId=${sysTemplateItem.templateId}"><spr:message code="sysTemplate.mange.setDefault"/></a>
						</c:otherwise>
					</c:choose>
					<a href="del.ht?templateId=${sysTemplateItem.templateId}"  class="link del"><spr:message code="operator.del"/></a>
					<a href="edit.ht?templateId=${sysTemplateItem.templateId}" class="link edit"><spr:message code="operator.edit"/></a>
					<a href="get.ht?templateId=${sysTemplateItem.templateId}" class="link detail"><spr:message code="operator.detail"/></a>
				</display:column>
			</display:table>
			<hotent:paging tableId="sysTemplateItem" />
		</div>
		<div style="display: none">
			<form  id="exportXmlForm" target="download" action="exportXml.ht">
				<input id="exportIds" name="ids">
			</form>
			<iframe id="download" name="download" height="0px" width="0px"></iframe>
		</div>
	</div>
</body>
</html>


