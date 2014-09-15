<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<script type="text/javascript">
	$(function(){//隐藏意见控件
		var opinion = $("textarea[opinionname]");
		opinion.removeAttr("validate");
		opinion.hide();
	});
	
	function beforeClick(operatorType){<c:if test="${not empty mapButton.button}">
		switch(operatorType){<c:forEach items="${mapButton.button }" var="btn"  ><c:if test="${not empty btn.prevscript}">
				case ${btn.operatortype}:
				${btn.prevscript}
				break;</c:if></c:forEach>
			}</c:if>
	}
	
	function afterClick(operatorType){<c:if test="${not empty mapButton.button}">
		switch(operatorType){<c:forEach items="${mapButton.button }" var="btn" ><c:if test="${not empty btn.afterscript}">
			case ${btn.operatortype}:
				${btn.afterscript}
				break;</c:if></c:forEach>
			}</c:if>
	}
	
	function openOpinionDialog(){
		var rtn=CustomForm.validate();
		if(!rtn){
			$.ligerDialog.warn($lang_js.customValid.form_validate_msg,$lang.tip.warn);
			return;
		}
		var obj = {data:CustomForm.getData()};
		//增加抄送人员
		var url=__ctx + "/platform/bpm/task/transToOpinionDialog.ht?taskId=${task.id}";
		var winArgs="dialogWidth=500px;dialogHeight=300px;help=0;status=0;scroll=0;center=1";
		url=url.getNewUrl();
		var rtn=window.showModalDialog(url,obj,winArgs);
		if(rtn=="ok"){
			if(window.opener && window.opener.location){
				window.opener.location.href=window.opener.location.href.getNewUrl();
			}
			window.close();
		}
	}
</script>
<div class="panel-top noprint">
	<div class="panel-toolbar">
		<div class="toolBar">
			<div class="group"><a id="btnNotify" class="link agree" onclick="openOpinionDialog()"><span></span><spr:message code="bpmNodeButton.button.feedback"/></a></div>
			<div class="l-bar-separator"></div>
			<div class="group"><a id="btnSave" class="link save"><span></span><spr:message code="bpmNodeButton.button.saveData"/></a></div>
			<div class="l-bar-separator"></div>
			<div class="group"><a class="link setting" onclick="showTaskUserDlg()"><span></span><spr:message code="menu.button.flowImage"/></a></div>
			<div class="l-bar-separator"></div>
			<div class="group"><a class="link search" onclick="showTaskOpinions()"><span></span><spr:message code="bpmNodeButton.button.history"/></a></div>
			<div class="l-bar-separator"></div>
			<div class="group"><a class="link sendMessage" onclick="showTaskCommunication()"><span></span><spr:message code="bpmNodeButton.button.commu"/></a></div>
		</div>	
	</div>
</div>