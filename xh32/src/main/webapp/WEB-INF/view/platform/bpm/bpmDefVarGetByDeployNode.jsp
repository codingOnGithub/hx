<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="f" uri="http://www.jee-soft.cn/functions" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="bpmDefVar.title"/><spr:message code="bpmDefVar.choose"/></title>
<script type="text/javascript">
	window.name="win";
	$(function(){
		jQuery.highlightTableRows();
	});
	
	function selectVars(key,name){
		var obj={key:key,name:name};
		window.returnValue=obj;
		window.close();
	}

</script>
</head>
<body>
		<div class="panel" >
		<div class="hide-panel">
			<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label"><spr:message code="bpmDefVar.title"/><spr:message code="bpmDefVar.choose"/></span>
					</div>
				
				</div>
				</div>
			<div class="panel-body">
				<div class="panel-data">
				    <display:table name="bpmDefVarList" id="bpmDefVarItem"  cellpadding="1" cellspacing="1"   class="table-grid">
				    	
						<display:column property="varName" titleKey="bpmDefVar.varName"  ></display:column>
						<display:column  titleKey="bpmDefVar.varDataType" >
						<c:choose>
							<c:when test="${bpmDefVarItem.varDataType eq 'string'}"><spr:message code="bpmDefVar.string"/></c:when>
							<c:when test="${bpmDefVarItem.varDataType eq 'number'}"><spr:message code="bpmDefVar.number"/></c:when>
							<c:when test="${bpmDefVarItem.varDataType eq 'date'}"><spr:message code="bpmDefVar.date"/></c:when>
						</c:choose>
						</display:column>
					
						<display:column titleKey="bpmDefVar.nodeName" >
						<c:choose>
							<c:when test="${bpmDefVarItem.varScope eq 'global'}"><spr:message code="bpmDefVar.globalVar"/></c:when>
							<c:when test="${bpmDefVarItem.varScope eq 'task'}">${bpmDefVarItem.nodeName}</c:when>
						</c:choose>
						</display:column>
						
						<display:column titleKey="bpmDefVar.choose">
							<a href="#" onclick="selectVars('${bpmDefVarItem.varKey}','${bpmDefVarItem.varName}')" class="link edit" target="win"><spr:message code="bpmDefVar.choose"/></a>
						</display:column>
					
					</display:table>
				</div>
			</div><!-- end of panel-body -->	
		</div>
</body>
</html>


