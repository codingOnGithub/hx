<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>自定义别名脚本表管理</title>
<%@include file="/commons/include/get.jsp" %>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/ScriptEdit.js"></script>
<script type="text/javascript">

    //预览事件
	function runScript(classInsName,methodName,jsonId){
		var jsonStr = $("#"+jsonId).val();
		var json; 
		if(typeof(jsonStr)==undefined||jsonStr==null||jsonStr==''){
			json==[];
		}else{
			json = eval('(' + jsonStr + ')');
		}
    	exeScript(classInsName,methodName,json);
	}

	 //帮助事件
	function goToHelp(){
		var url = __ctx+'/platform/system/aliasScript/goToHelp.ht'
		var winArgs='height=550,width=700,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no';
		url=url.getNewUrl();
		window.open (url,"别名脚本开发帮助",winArgs);
	}

    
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">自定义别名脚本表管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label">脚本别名:</span><input type="text" name="Q_aliasName_SL"  class="inputText" value="${param['Q_aliasName_SL']}"/>
						<span class="label">脚本描叙:</span><input type="text" name="Q_aliasDesc_SL"  class="inputText" value="${param['Q_aliasDesc_SL']}"/>
						<span class="label">类的路径:</span><input type="text" name="Q_className_SL"  class="inputText" value="${param['Q_className_SL']}"/>
						<span class="label">调用方法:</span><input type="text" name="Q_methodName_SL"  class="inputText" value="${param['Q_methodName_SL']}"/>
						<!-- <span class="label">返回类型:</span><input type="text" name="Q_returnType_SL"  class="inputText" value="${param['Q_returnType_SL']}" /> -->
						<!-- <span class="label">是否禁用:</span><input type="text" name="Q_enable_SL"  class="inputText" /> -->
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="aliasScriptList" id="aliasScriptItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${aliasScriptItem.id}">
				</display:column>
				<display:column titleKey="aliasScriptItem.aliasName" title="脚本别名" sortable="true" sortName="ALIAS_NAME">
						<a class="link" href="edit.ht?id=${aliasScriptItem.id}" style="color:blue;">${aliasScriptItem.aliasName}</a> 
				</display:column>
				<display:column property="aliasDesc" title="脚本描叙" sortable="true" sortName="ALIAS_DESC"></display:column>
				<display:column property="className" title="类的路径" sortable="true" sortName="CLASS_NAME" maxLength="80"></display:column>
		<%-- 	<display:column property="classInsName" title="类的对象名" sortable="true" sortName="CLASS_INS_NAME"></display:column> --%>
				<display:column property="methodName" title="调用方法" sortable="true" sortName="METHOD_NAME"></display:column>
		<%--    <display:column property="methodDesc" title="方法的描叙" sortable="true" sortName="METHOD_DESC" maxLength="80"></display:column> --%>
		<%-- 		<display:column property="returnType" title="返回类型" sortable="true" sortName="RETURN_TYPE"></display:column> --%>
		<%--    <display:column property="argument" title="相关设置" sortable="true" sortName="ARGUMENT" maxLength="80"></display:column> --%>
				<display:column titleKey="aliasScriptItem.enable"  title="是否禁用" sortable="true" sortName="ENABLE">
					<c:choose>
						<c:when test="${aliasScriptItem.enable eq 0}"><span class="green"><spr:message code="yes"/></span></c:when>
						<c:when test="${aliasScriptItem.enable eq 1}"><span class="red"><spr:message code="no"/></span></c:when>
					</c:choose>
				</display:column>
				
				<display:column title="管理" media="html" style="width:50px;text-align:center" class="rowOps">
					<a href="del.ht?id=${aliasScriptItem.id}" class="link del">删除</a>
					<a href="edit.ht?id=${aliasScriptItem.id}" class="link edit">编辑</a>
					<a href="get.ht?id=${aliasScriptItem.id}" class="link detail">明细</a>
					<a href="#" onclick="javascript:runScript('${aliasScriptItem.classInsName}','${aliasScriptItem.methodName}','${aliasScriptItem.id}_Argument')" class="link run">预览</a>
				    <textarea class="hidden" id="${aliasScriptItem.id}_Argument">${aliasScriptItem.argument}</textarea>
				    <a href="#" onclick="javascript:goToHelp()" class="link help">帮助</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="aliasScriptItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


