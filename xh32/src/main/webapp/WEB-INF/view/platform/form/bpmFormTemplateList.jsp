
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<%@include file="/commons/include/get.jsp" %>
	<f:js pre="js/lang/view/platform/form" ></f:js>
	<title><spr:message code="bpmFormTemplate.title"/><spr:message code="operator.list"/></title>
	<script type="text/javascript" src="${ctx }/js/lg/plugins/ligerWindow.js" ></script>
    <script type="text/javascript" src="${ctx }/js/hotent/platform/form/copyTemplateDialog.js"></script> 
    <script type="text/javascript">
    	$(function(){//设置不能编辑的行不能选中
    		$("tr").live("click",function(event){
    			var checkbox = $(this).find("input.pk");
    			if(checkbox.attr("disabled")=="disabled"){
   				   checkbox.attr("checked",false);
    			}
    		});
    		
    		handlerInit();
    	});
    	
    	//处理初始化模板
	    function handlerInit()
	    {
	    	$("a.link.init").click(function(){
	    		var action=$(this).attr("action");
	    		if($(this).hasClass('disabled')) return false;
	    		
	    		$.ligerDialog.confirm($lang_form.bpmFormTemplate.initConfirm,$lang.tip.confirm,function(rtn){
	    			if(rtn){
	    				var form=new com.hotent.form.Form();
	    				form.creatForm('form', action);
	    				form.submit();
	    			}
	    		});
	    		
	    	});
	    }

    	function copyTemplate(templateId,templateName,alias){
    		CopyTemplateDialog({templateId:templateId,templateName:templateName,alias:alias});
    	}
    </script>
</head>
<body>
			<div class="panel">
				<div class="hide-panel">
					<div class="panel-top">
						<div class="tbar-title">
							<span class="tbar-label"><spr:message code="bpmFormTemplate.title"/><spr:message code="operator.list"/></span>
						</div>
						<div class="panel-toolbar">
							<div class="toolBar">
								<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>
								<div class="l-bar-separator"></div>
								<div class="group"><a class="link add" href="edit.ht"><span></span><spr:message code="menu.button.add"/></a></div>
								<div class="l-bar-separator"></div>
								<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span><spr:message code="menu.button.alter"/></a></div>
								<div class="l-bar-separator"></div>
								<div class="group"><a class="link del"  action="del.ht"><span></span><spr:message code="menu.button.del"/></a></div>
								<div class="l-bar-separator"></div>
								<div class="group"><a class="link init" id="bntInit" action="init.ht"><span></span><spr:message code="menu.button.initTemplate"/></a></div>
							</div>	
						</div>
						
						<div class="panel-search">
							<form id="searchForm" method="post" action="list.ht">
								<ul class="row">
									<li><span class="label"><spr:message code="bpmFormTemplate.templateName"/>:</span><input type="text" name="Q_templateName_SL"  class="inputText" value="${param['Q_templateName_SL']}"/></li>
									<li><span class="label"><spr:message code="bpmFormTemplate.templateType"/>:</span>
									<select name="Q_templateType_S" value="${param['Q_templateType_S']}">
										<option value="">全部</option>
										<option value="main" <c:if test="${param['Q_templateType_S'] == 'main'}">selected</c:if>><spr:message code="bpmFormTemplate.templateType.main"/></option>
										<option value="subTable" <c:if test="${param['Q_templateType_S'] == 'subTable'}">selected</c:if>><spr:message code="bpmFormTemplate.templateType.subTable"/></option>
										<option value="macro" <c:if test="${param['Q_templateType_S'] == 'macro'}">selected</c:if>><spr:message code="bpmFormTemplate.templateType.macro"/></option>
										<option value="list" <c:if test="${param['Q_templateType_S'] == 'list'}">selected</c:if>><spr:message code="bpmFormTemplate.templateType.list"/></option>
										<option value="detail" <c:if test="${param['Q_templateType_S'] == 'detail'}">selected</c:if>><spr:message code="bpmFormTemplate.templateType.detail"/></option>
										<option value="tableManage" <c:if test="${param['Q_templateType_S'] == 'tableManage'}">selected</c:if>><spr:message code="bpmFormTemplate.templateType.tableManage"/></option>
										<option value="dataTemplate" <c:if test="${param['Q_templateType_S'] == 'dataTemplate'}">selected</c:if>><spr:message code="bpmFormTemplate.templateType.dataTemplate"/></option>
									</select></li>
								</ul>
							</form>
						</div>
					</div>
				</div>
				<div class="panel-body">
					
					
				    	<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <display:table name="bpmFormTemplateList" id="bpmFormTemplateItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"  class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;">
								  	<input type="checkbox" class="pk"  name="templateId" value="${bpmFormTemplateItem.templateId}"  <c:if test="${bpmFormTemplateItem.canEdit==0}">disabled="disabled"</c:if>  >
							</display:column>
							<display:column property="alias" titleKey="bpmFormTemplate.alias" sortable="true" sortName="alias" style="text-align:left"></display:column>
							<display:column property="templateName" titleKey="bpmFormTemplate.templateName" sortable="true" sortName="templateName" style="text-align:left"></display:column>
							<display:column titleKey="bpmFormTemplate.templateType" style="text-align:left">
								<c:if test="${bpmFormTemplateItem.templateType=='list'}"><spr:message code="bpmFormTemplate.templateType.list"/></c:if>
								<c:if test="${bpmFormTemplateItem.templateType=='detail'}"><spr:message code="bpmFormTemplate.templateType.detail"/></c:if>
								<c:if test="${bpmFormTemplateItem.templateType=='subTable'}"><spr:message code="bpmFormTemplate.templateType.subTable"/></c:if>
								<c:if test="${bpmFormTemplateItem.templateType=='main'}"><spr:message code="bpmFormTemplate.templateType.main"/></c:if>
								<c:if test="${bpmFormTemplateItem.templateType=='macro'}"><spr:message code="bpmFormTemplate.templateType.macro"/></c:if>
								<c:if test="${bpmFormTemplateItem.templateType=='tableManage'}"><spr:message code="bpmFormTemplate.templateType.tableManage"/></c:if>
								<c:if test="${bpmFormTemplateItem.templateType=='dataTemplate'}"><spr:message code="bpmFormTemplate.templateType.dataTemplate"/></c:if>
								
							</display:column>
							<display:column titleKey="bpmFormTemplate.from" style="text-align:left">
								<c:choose>
									<c:when test="${bpmFormTemplateItem.canEdit==0}"><span class="red"><spr:message code="bpmFormTemplate.canEdit.system"/></span></c:when>
									<c:when test="${bpmFormTemplateItem.canEdit==1}"><span class="green"><spr:message code="bpmFormTemplate.canEdit.custom"/></span></c:when>
								</c:choose>
							</display:column>
							<display:column titleKey="list.manage" media="html" style="width:50px;text-align:center" class="rowOps">
								<c:choose>
									<c:when test="${bpmFormTemplateItem.canEdit==0}">
										<a  class="link del disabled"><spr:message code="menu.button.del"/></a>
										<a  class="link edit disabled"><spr:message code="menu.button.edit"/></a>
										<a  class="link backUp disabled" ><spr:message code="menu.button.backUp"/></a>
										
									</c:when>
									<c:otherwise >
										<f:a alias="delTemplateForm" href="del.ht?templateId=${bpmFormTemplateItem.templateId}" css="link del"><spr:message code="menu.button.del"/></f:a>
										<a  href="edit.ht?templateId=${bpmFormTemplateItem.templateId}" class="link edit"><spr:message code="menu.button.edit"/></a>
										<a  href="backUp.ht?templateId=${bpmFormTemplateItem.templateId}" class="link backUp" ><spr:message code="menu.button.backUp"/></a>
										
									</c:otherwise>
								</c:choose>
								<a href="get.ht?templateId=${bpmFormTemplateItem.templateId}" class="link detail"><spr:message code="menu.button.detail"/></a>
								<a href="#" onclick="copyTemplate('${bpmFormTemplateItem.templateId}','${bpmFormTemplateItem.templateName}','${bpmFormTemplateItem.alias}')"  class="link copy"><spr:message code="menu.button.copy"/></a>
							</display:column>
						</display:table>
						<hotent:paging tableId="bpmFormTemplateItem"/>
				
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
</html>


