<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.hotent.platform.model.bpm.BpmDefRights"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="bpmDefinition.selector.title"/></title>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowUtil.js" ></script>
<style type="text/css">
	body{overflow-x:hidden;}
</style>
<script type="text/javascript">
	var isSingle='${isSingle}';
	$(function(){
		$("#bpmDefinitionItem").find("tr").bind('click', function() {
			if(isSingle=='true'){
				var rad=$(this).find('input[name=defId]:radio');
				rad.attr("checked","checked");
			}else{
				var ch=$(this).find(":checkbox[name='defId']");
				window.parent.selectMulti(ch);
			}
		});
	});
</script>
</head>
<body>      
	<div class="panel">
		<div class="panel-top">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="selector.ht">
					<ul class="row">
						<li><span class="label"><spr:message code="bpmDefinition.subject"/>:</span><input type="text" name="Q_subject_S"  class="inputText"  value="${param['Q_subject_S']}"/></li>
						<li><span class="label"><spr:message code="bpmDefinition.descp"/>:</span><input type="text" name="Q_descp_S" class="inputText" maxlength="125"  value="${param['Q_descp_S']}"/></li>
					</ul>
				</form>
			</div>	
		</div>
		<div class="panel-body">
						
			<div class="panel-data">
		    	<c:set var="checkAll">
		    	<c:if test="${isSingle==false}">
					<input onclick="window.parent.selectAll(this);" type="checkbox" id="chkall"/>
		    	</c:if>
				</c:set>
			    <display:table name="bpmDefinitionList" id="bpmDefinitionItem" requestURI="selector.ht" sort="external" cellpadding="1" cellspacing="1" export="false"  class="table-grid">
					<display:column title="${checkAll}" media="html" style="width:30px;">
							<c:choose>
								<c:when test="${isSingle==true}">
								<input type="radio" class="pk" name="defId" value="${bpmDefinitionItem.defId}#${bpmDefinitionItem.subject}#${bpmDefinitionItem.defKey}">	
								</c:when>
								<c:otherwise>
						  		<input type="checkbox" class="pk" name="defId" value="${bpmDefinitionItem.defId}#${bpmDefinitionItem.subject}#${bpmDefinitionItem.defKey}">
								</c:otherwise>
							</c:choose>
						  	
					</display:column>
					<display:column property="subject" titleKey="bpmDefinition.subject" sortable="true" sortName="subject" ></display:column>
					
					<display:column titleKey="bpmDefinition.type" sortable="true" sortName="typeName">
						<c:out value="${bpmDefinitionItem.typeName}"></c:out>
					</display:column>
					<display:column property="versionNo" titleKey="bpmDefinition.version.info" sortable="true" sortName="versionNo" style="width:60px"></display:column>
					
					<display:column titleKey="bpmDefinition.status" sortable="true" sortName="status" style="width:60px">
						<c:choose>
							<c:when test="${bpmDefinitionItem.status eq 0}"><spr:message code="bpmDefinition.status.unpublished"/></span></c:when>
							<c:when test="${bpmDefinitionItem.status eq 1}"><span class="green"><spr:message code="bpmDefinition.status.published"/></span></c:when>
							<c:when test="${bpmDefinitionItem.status eq 2}"><span class="red"><spr:message code="bpmDefinition.status.disabled"/></span></c:when>
							<c:when test="${bpmDefinitionItem.status eq 3}"><span class="red"><spr:message code="bpmDefinition.status.disabledcase"/></span></c:when>
							<c:when test="${bpmDefinitionItem.status eq 4}"><span class="green"><spr:message code="bpmDefinition.status.test"/></span></c:when>
							<c:otherwise><span class="red"><spr:message code="bpmDefinition.status.unknow"/></span></c:otherwise>
						</c:choose>
					</display:column>
				</display:table>
				<hotent:paging tableId="bpmDefinitionItem"/>
			</div>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


