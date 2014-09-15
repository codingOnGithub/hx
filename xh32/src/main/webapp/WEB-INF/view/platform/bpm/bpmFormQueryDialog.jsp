<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="bpmFormQuery.title"/><spr:message code="title.manageList"/></title>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/CommonDialog.js"></script>
<script type="text/javascript">
	function selectQuery(){
		var alias="";
		$(".pk").each(function(){
			var checked=$(this).attr("checked");
			if(checked){
				alias=$(this).val();
			}
		});
		
		if(alias==""){
			$.ligerDialog.confirm($lang.operateTip.selectOneRecord,$lang.tipconfirm);
			return;
		}else{
			window.returnValue=alias;
			window.close();
		}
		
	}
</script>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="bpmFormQuery.title"/><spr:message code="title.manageList"/></span>
			</div>
		</div>
		</div>
		<div class="panel-body" >
		    <display:table name="bpmFormQueryList" id="bpmFormQueryItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="radio" class="pk" name="id" value="${bpmFormQueryItem.alias}">
				</display:column>
				<display:column property="name" titleKey="bpmFormQuery.name" sortable="true" sortName="name"></display:column>
				<display:column property="alias" titleKey="bpmFormQuery.alias" sortable="true" sortName="alias"></display:column>
				<display:column property="objName" titleKey="bpmFormQuery.objName" sortable="true" sortName="objName"></display:column>
			</display:table>
			<hotent:paging tableId="bpmFormQueryItem" showExplain="false"/>
		</div><!-- end of panel-body -->	
		<div position="bottom"  class="bottom" >
			<a href="#" class="button"  onclick="selectQuery()" ><span class="icon ok"></span><span><spr:message code="operator.select"/></span></a>
			<a href="#" class="button" style="margin-left:10px;"  onclick="window.close()"><span class="icon cancel"></span><span ><spr:message code="operator.cancel"/></span></a>
		</div>			
	</div> <!-- end of panel -->
</body>
</html>


