
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="bpmFormDef.versions.title"/></title>
<f:js pre="js/lang/view/platform/form" ></f:js>

<style type="text/css">
	.panel-data {
		margin-top: 5px ;
		margin-bottom: 5px ;
	}
	
	.floatBtn {
		font-weight: bold;
		color: red !important;
	}
	
</style>
<script type="text/javascript">
	$(function(){
		handPublish();
		handNewVersion();
	});
	
	function handPublish(){
		$.confirm("a.link.deploy",$lang_form.bpmFormDef.list.publish);
	}
	
	function handNewVersion(){
		$.confirm("a.link.newVersion",$lang_form.bpmFormDef.list.newVersion);
	}
	
	
	
</script>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">${formName}--<spr:message code="bpmFormDef.versions.versionmane"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back " href="list.ht"><span></span><spr:message code="menu.button.back"/></a></div>
				</div>
			</div>
		</div>
		</div>
		<div class="panel-body">
			<display:table name="versions" id="bpmFormDefItem" requestURI="versions.ht" sort="external" cellpadding="1" cellspacing="1"  class="table-grid">
				<display:column property="versionNo" titleKey="bpmFormDef.versions.versionNo" sortable="true" sortName="versionNo"></display:column>
				<display:column property="publishedBy" titleKey="bpmFormDef.versions.publishedBy" sortable="true" sortName="publishedBy"></display:column>
				<display:column titleKey="bpmFormDef.versions.publishTime" sortable="true" sortName="publishTime">
					<fmt:formatDate value="${bpmFormDefItem.publishTime}"/>
				</display:column>
				<display:column titleKey="bpmFormDef.versions.isDefault" sortable="true" sortName="isDefault">
					<c:choose>
					 	<c:when test="${bpmFormDefItem.isDefault==1}"><span class="green"><spr:message code="yes"/></span></c:when>
					 	<c:otherwise>
					 		<span class="red"><spr:message code="no"/></span>
					 	</c:otherwise>
					</c:choose>
				</display:column>
				<display:column titleKey="bpmFormDef.versions.isPublesh" sortable="true" sortName="isDefault">
					<c:choose>
					 	<c:when test="${bpmFormDefItem.isPublished==1}"><span class="green"><spr:message code="bpmFormDef.versions.published"/></span></c:when>
					 	<c:otherwise>
					 		<span class="red"><spr:message code="bpmFormDef.versions.unpublished"/></span>
					 	</c:otherwise>
					</c:choose>
				</display:column>
			
				<display:column titleKey="list.manage" media="html" >
					<c:choose>
						<c:when test="${bpmFormDefItem.isDefault==1}">
							<a class="link setting " style="color:gray;"><spr:message code="menu.button.setDefault"/></a>
						</c:when>
						<c:otherwise>
							<a  class="link setting" href="setDefaultVersion.ht?formDefId=${bpmFormDefItem.formDefId }&formKey=${bpmFormDefItem.formKey}"><span ><spr:message code="menu.button.setDefault"/></span></a>
						</c:otherwise>
					</c:choose>
					<c:if test="${bpmFormDefItem.isPublished==0&&bpmFormDefItem.designType==0}">
							<a  class="link deploy" href="publish.ht?formDefId=${bpmFormDefItem.formDefId }"><spr:message code="menu.button.publish"/></a>
					</c:if>
					<c:if test="${bpmFormDefItem.isPublished== 1}">
						<a href="newVersion.ht?formDefId=${bpmFormDefItem.formDefId}"  class="link newVersion"><spr:message code="menu.button.newVersion"/></a>
					</c:if>	
					
					<c:choose>
						<c:when test="${bpmFormDefItem.designType==0 }">
							<a href="#" onclick="javascript:jQuery.openFullWindow('edit.ht?formDefId=${bpmFormDefItem.formDefId}');" class="link edit"><spr:message code="menu.button.edit"/></a>
						</c:when>
						<c:otherwise >
							<a href="#" onclick="javascript:jQuery.openFullWindow('designEdit.ht?formDefId=${bpmFormDefItem.formDefId}');" class="link edit"><spr:message code="menu.button.edit"/></a>
						</c:otherwise>
					</c:choose>
					
					<a href="get.ht?formDefId=${bpmFormDefItem.formDefId}"  class="link detail"><spr:message code="menu.button.check"/></a>
					
					<c:choose>
							<c:when test="${bpmFormDefItem.designType==0 }">
								<a target="_blank" href="${ctx}/platform/form/bpmFormHandler/edit.ht?formDefId=${bpmFormDefItem.formDefId}" class="link preview"><spr:message code="menu.button.preview"/></a>
							</c:when>
							<c:when test="${bpmFormDefItem.designType==1 }">
								<a href="#" onclick="javascript:jQuery.openFullWindow('preview.ht?formDefId=${bpmFormDefItem.formDefId}');" class="link edit"><spr:message code="menu.button.preview"/></a>
							</c:when>
					</c:choose>
					
					<input type="hidden" id="bpmFormDefId"value="${bpmFormDefItem.formDefId}">
				</display:column>
			</display:table>
			
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


