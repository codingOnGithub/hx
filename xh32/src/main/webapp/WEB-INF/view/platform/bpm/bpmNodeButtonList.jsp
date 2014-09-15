<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="bpmNodeButton.title"/></title>

<f:js pre="js/lang/view/platform/bpm" ></f:js>
<script type="text/javascript">

$(function(){
	$(".table-detail").width($(".table-grid").width());
	$(".tbar-title").width($(".table-grid").width());
	$("a.del").unbind("click");
});
function initAll(defId,actDefId){
	var url =  __ctx+'/platform/bpm/bpmNodeButton/initAll.ht?defId='+defId+'&actDefId='+actDefId;
	$.ligerDialog.confirm($lang_bpm.nodeButton.initAllTip,$lang.tip.msg,function(rtn) {
		if(rtn){
			gotoUrl(url);
		}
	});	
}
function init(defId,nodeId){
	if($.isEmpty(nodeId)) nodeId ='';
	var url =  __ctx+'/platform/bpm/bpmNodeButton/init.ht?defId='+defId+'&nodeId='+nodeId;
	$.ligerDialog.confirm($lang_bpm.nodeButton.initTip,$lang.tip.msg,function(rtn) {
		if(rtn){
			gotoUrl(url);
		}
	});	
}
function clearAll(defId,actDefId){
	var url =  __ctx+'/platform/bpm/bpmNodeButton/delByDefId.ht?defId='+defId+'&actDefId='+actDefId;
	$.ligerDialog.confirm($lang_bpm.nodeButton.clearAllTip,$lang.tip.msg,function(rtn) {
		if(rtn){
			gotoUrl(url);
		}
	});	
}

function del(defId,nodeId){
	if($.isEmpty(nodeId)) nodeId ='';
	var url =  __ctx+'/platform/bpm/bpmNodeButton/deByDefNodeId.ht?defId='+defId+'&nodeId='+nodeId;
	$.ligerDialog.confirm($lang_bpm.nodeButton.delTip,$lang.tip.msg,function(rtn) {
		if(rtn){
			gotoUrl(url);
		}
	});	
}
function gotoUrl(url){
	if($.browser.msie){
		$.gotoDialogPage(url);
	}
	else{
		location.href=url;
	}
}
</script>

</head>
<body>
    
    <jsp:include page="incDefinitionHead.jsp">
   		<jsp:param value="<spr:message code='bpmNodeButton.title'/>" name="title"/>
	</jsp:include>
<div class="panel-container">
    <f:tab curTab="button" tabName="flow"/>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code='bpmNodeButton.title'/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link init"  href="#" onclick="initAll('${bpmDefinition.defId}','${bpmDefinition.actDefId}');"><span></span><spr:message code="bpmNodeButton.button.initAll"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del" href="#" onclick="clearAll('${bpmDefinition.defId}','${bpmDefinition.actDefId}')"><span></span><spr:message code="bpmNodeButton.button.clearAll"/></a></div>
				</div>
			</div>
		</div>
		</div>
		<div class="panel-body" >
			
				<form action="save.ht" method="post" id="dataForm">
				    <input type="hidden" name="defId" value="${bpmDefinition.defId}"/>
							<!-- 节点对应表 -->
					    <table cellpadding="1" cellspacing="1" class="table-grid table-list">
					    <thead>
							<tr>
								<th><spr:message code="list.orderNo"/></th>
								<th><spr:message code="bpmNodeButton.nodeName"/></th>
								<th><spr:message code="bpmNodeButton.operatortype"/></th>
								<th><spr:message code="bpmNodeButton.operatorButton"/></th>
								<th><spr:message code="list.manage"/></th>
							</tr>
							</thead>
							<tr>
									<td>0</td>
									<td><spr:message code="bpmNodeButton.startProcess"/></td>
									<td style="color:red"><spr:message code="bpmNodeButton.startNode"/></td>
									<td>
										<c:set var="btnList" value="${btnMap.start}"></c:set>
										<c:set var="flag" value="0"></c:set>
										<!-- 启动流程 -->
										<c:choose>
											<c:when test="${empty btnList }">
												<c:forEach items="${startBtnList}" var="btn" varStatus="status" >
													${btn.text}<c:if test="${!status.last}">,</c:if>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<c:forEach items="${btnList}" var="btn" varStatus="status" >
															${btn.btnname}<c:if test="${!status.last}">,</c:if>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</td>
                                    <td >
										<a class="link edit" href="getByNode.ht?defId=${bpmDefinition.defId}"><spr:message code="menu.button.edit"/></a>
										
										<a class="link init" href="init.ht?defId=${bpmDefinition.defId}"><spr:message code="menu.button.init"/></a>
										<a class="link comment" href="${ctx}/platform/bpm/bpmFormLanguage/buttonList.ht?defId=${bpmDefinition.defId}" ><spr:message code="menu.button.international" /> </a>
										<a  class="link del"  href="deByDefNodeId.ht?defId=${bpmDefinition.defId}"><spr:message code="menu.button.del"/></a>
									</td>
							</tr>
							
							
							<c:forEach items="${bpmNodeSetList}" var="item" varStatus="status">
							<tr>
								<td>
									${status.index +1}
								</td>
								<td>
									<input type="hidden" name="nodeId" value="${item.nodeId}"/>
									<input type="hidden" name="nodeName" value="${item.nodeName}"/>${item.nodeName}
								</td>
								<td>
									<c:choose>
										<c:when test="${taskMap[item.nodeId].isSignNode }"><span class="red"><spr:message code="bpmNodeButton.nodetype.sign"/></span></c:when>
										<c:when test="${taskMap[item.nodeId].isSubProcess or taskMap[item.nodeId].isCallActivity}"><span class="red"><spr:message code="bpmNodeButton.nodetype.subProcess"/></span></c:when>
										<c:otherwise><span class="green"><spr:message code="bpmNodeButton.nodetype.common"/></span></c:otherwise>
									</c:choose>
								</td>
								<td nowrap="nowrap">
									<c:set var="btnList" value="${btnMap[item.nodeId] }"></c:set>
									<c:choose>
										<c:when test="${empty btnList }">
											<c:choose>
												<c:when test="${taskMap[item.nodeId].isSignNode}"><!-- 表示会签节点 -->
													<c:forEach items="${signBtnList}" var="btn" varStatus="status" >
														${btn.text}<c:if test="${!status.last}">,</c:if>
													</c:forEach>
												</c:when>
												<c:when test="${taskMap[item.nodeId].isFirstNode }"><!-- 表示第一个节点 -->
													<c:forEach items="${firstNodeBtnList}" var="btn" varStatus="status" >
														${btn.text}<c:if test="${!status.last}">,</c:if>
													</c:forEach>
												</c:when>
												<c:when test="${taskMap[item.nodeId].isSubProcess or taskMap[item.nodeId].isCallActivity}" ><!-- 子流程-->
													&nbsp;
												</c:when>
												<c:otherwise>
													<c:forEach items="${commonBtnList}" var="btn" varStatus="status" >
														${btn.text}<c:if test="${!status.last}">,</c:if>
													</c:forEach>
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>
											<c:forEach items="${btnList}" var="btn" varStatus="status" >
												${btn.btnname}<c:if test="${!status.last}">,</c:if>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</td>
								<td>
									<c:choose>
										<c:when test="${taskMap[item.nodeId].isSubProcess or taskMap[item.nodeId].isCallActivity}" >
												&nbsp;	
										</c:when>
										<c:otherwise>
											<a class="link edit" href="getByNode.ht?defId=${bpmDefinition.defId}&nodeId=${item.nodeId}"><spr:message code="menu.button.edit"/></a>
											<a class="link init" href="#" onclick="init('${bpmDefinition.defId}','${item.nodeId}')"><spr:message code="menu.button.init"/></a>
											<a class="link comment" href="${ctx}/platform/bpm/bpmFormLanguage/buttonList.ht?defId=${bpmDefinition.defId}&nodeId=${item.nodeId}" ><spr:message code="menu.button.international" /> </a>
											<a  class="link del" href="#" onclick="del('${bpmDefinition.defId}','${item.nodeId}')"><spr:message code="menu.button.del"/></a>
										</c:otherwise>
									</c:choose>
								</td>
								
							</tr>
							</c:forEach>
						</table>
						
				</form>
			
		</div>				
</div>	
</div>
</body>
</html>





