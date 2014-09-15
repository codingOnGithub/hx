<%
	//作用:查看某一流程所有版本历史
	//作者:csx
%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="bpmDefinition.history.title"/></title>
</head>
<body>
            <jsp:include page="incDefinitionHead.jsp">
		   		<jsp:param value="<spr:message code='bpmDefinition.history.title'/>" name="title"/>
			</jsp:include>
		 <div class="panel-container">
            <f:tab curTab="history" tabName="flow"/>
			<div class="panel">
				
				<div class="panel-body">
					
					    <display:table name="bpmDefinitionList" id="bpmDefinitionItem" requestURI="versions.ht"  sort="external" cellpadding="1" cellspacing="1"   class="table-grid">
							<display:column titleKey="<spr:message code='list.orderNo'/>" media="html" style="width:30px;">
								  	<input type="checkbox" class="pk" name="defId" value="${bpmDefinitionItem.defId}">
							</display:column>
							<display:column property="subject" titleKey="bpmDefinition.subject" sortable="false" sortName="subject"></display:column>
							<display:column property="defKey" titleKey="bpmDefinition.defKey" sortable="false" sortName="defKey"></display:column>
							<display:column titleKey="bpmDefinition.type" sortable="false" sortName="typeId">
								<c:out value="${bpmDefinitionItem.typeName}"></c:out>
							</display:column>
							<display:column property="versionNo" titleKey="bpmDefinition.versionNo" sortable="false" sortName="versionNo"></display:column>
							<display:column property="reason" titleKey="bpmDefinition.reason" sortable="false" sortName="reason"></display:column>
							<display:column titleKey="bpmDefinition.createtime" sortable="false" sortName="createtime">
								<fmt:formatDate value="${bpmDefinitionItem.createtime}" pattern="yyyy-MM-dd HH:mm"/>
							</display:column>
							<display:column titleKey="bpmDefinition.updatetime" sortable="false">
								<fmt:formatDate value="${bpmDefinitionItem.updatetime}" pattern="yyyy-MM-dd HH:mm"/>
							</display:column>
							<display:column property="descp" titleKey="bpmDefinition.descp" sortable="false" sortName="descp"></display:column>
							<display:column titleKey="list.manage" media="html" style="width:220px;">
								<a href="del.ht?defId=${bpmDefinitionItem.defId}&isOnlyVersion=true" class="link del" title="<spr:message code="menu.button.del"/>"><spr:message code="menu.button.del"/></a>
								<a href="design.ht?defId=${bpmDefinitionItem.defId}" target="_blank" class="link flowDesign" title="<spr:message code="bpmDefinition.button.flowDesign"/>"><spr:message code="bpmDefinition.button.flowDesign"/></a>
								<a href="detail.ht?defId=${bpmDefinitionItem.defId}&defIdForReturn=${bpmDefinition.defId}" class="link detail" title="<spr:message code="menu.button.detail"/>"><spr:message code="menu.button.detail"/></a>
							</display:column>
						</display:table>
					
				</div><!-- end of panel-body -->				
		</div>	
		</div> <!-- end of panel -->
</body>
</html>


