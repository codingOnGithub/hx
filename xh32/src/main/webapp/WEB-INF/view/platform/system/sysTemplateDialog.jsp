<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="sysTemplate"/><spr:message code="menu.button.choose"/></title>
<script type="text/javascript">
	window.name="win";
	$(function(){
		jQuery.highlightTableRows();
	});
	function selectTemplate(t){
		
		var me = $(this),
			td = me.parents("td"),
			span = td.find("span[name='"+t+"']");

		var conf=window.dialogArguments;			
		if(conf.isText){
			window.returnValue=span.text();
		}
		else{
			alert(span.text());
			window.returnValue=span.html();
		}
		window.close();
	}
</script>
<style type="text/css">
.panel{
	height:100%;width:100%;overflow:auto;
}
</style>
</head>
<body>
<div class="panel">
			<div class="panel-top">
				<div class="tbar-title">
					<span class="tbar-label"><spr:message code="sysTemplate"/><spr:message code="menu.button.choose"/></span>
				</div>
			</div>
		<div class="panel-body">
			   <display:table name="sysTemplateList" id="sysTemplateItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"  class="table-grid">											
				<display:column property="name" titleKey="sysTemplate.name" ></display:column>
				<display:column titleKey="sysTemplate.useType">
					<c:choose>
						<%--
						<c:when test="${sysTemplateItem.useType eq 1}">终止</c:when>
						<c:when test="${sysTemplateItem.useType eq 2}">催办</c:when>
						<c:when test="${sysTemplateItem.useType eq 3}">任务通知</c:when>
						<c:when test="${sysTemplateItem.useType eq 4}">撤销</c:when>
						<c:when test="${sysTemplateItem.useType eq 5}">取消转办</c:when>
						<c:when test="${sysTemplateItem.useType eq 6}">沟通</c:when>
						<c:when test="${sysTemplateItem.useType eq 7}">通知发起人</c:when>
						<c:when test="${sysTemplateItem.useType eq 8}">转办</c:when>
						<c:when test="${sysTemplateItem.useType eq 9}">驳回</c:when>
						<c:when test="${sysTemplateItem.useType eq 10}">驳回到发起人</c:when>
						<c:when test="${sysTemplateItem.useType eq 11}">追回</c:when>
						<c:when test="${sysTemplateItem.useType eq 12}">抄送</c:when>
						<c:when test="${sysTemplateItem.useType eq 13}">流程节点无人员</c:when>
						<c:when test="${sysTemplateItem.useType eq 14}">任务预警</c:when>
						<c:otherwise>其他</c:otherwise>
						 --%>
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
						<c:otherwise><spr:message code="sysTemplate.useType.other"/></c:otherwise>
					</c:choose>
				</display:column>
				<display:column titleKey="operator.select">
					<a href="#" onclick="selectTemplate.call(this,'html')" class="link edit"><spr:message code="sysTemplate.htmlContent"/></a><br>
					<span name="html" class="text">${sysTemplateItem.htmlContent}</span><br>
					<a href="#" onclick="selectTemplate.call(this,'plain')" class="link edit"><spr:message code="sysTemplate.plainContent"/></a><br>
					<span name="plain" class="text">${sysTemplateItem.plainContent}</span>
				</display:column>
			</display:table>
		</div>			
	</div>
</body>
</html>



<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title><spr:message code="sysTemplate.tempCon.choose"/></title>
	<%@include file="/commons/include/get.jsp" %>
	<script type="text/javascript">
		window.name="win";
		$(function(){
			jQuery.highlightTableRows();
		});
		function selectTemplate(obj){		
			var conf=window.dialogArguments;			
			if(conf.isText){
				window.returnValue=$(obj).prev().text();
			}
			else{
				window.returnValue=$(obj).prev().html();
			}
			window.close();
		}
	</script>
</head>
<body>
<div class="panel">
<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label"><spr:message code="sysTemplate.tempCon.choose"/></span>
					</div>
				
				</div>
				</div>
			<div class="panel-body">
				<div class="panel-data">
					   <display:table name="sysTemplateList" id="sysTemplateItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"  class="table-grid">											
						<display:column property="name" titleKey="sysTemplate.name" sortable="true" sortName="name"></display:column>
						<display:column property="content"  titleKey="sysTemplate.tempCon" sortable="true" sortName="content" maxLength="80"></display:column>
						<display:column titleKey="menu.button.choose">
							<span style="display: none;" >${sysTemplateItem.content}</span>
							<a href="javascript:;" onclick="selectTemplate(this)" class="link edit" target="win"><spr:message code="menu.button.choose"/></a>
						</display:column>
					</display:table>
				</div>
			</div>			
		</div>
</body>
</html>
 --%>