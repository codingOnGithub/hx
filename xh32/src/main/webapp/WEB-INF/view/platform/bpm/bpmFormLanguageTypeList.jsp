<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<f:js pre="js/lang/view/platform/bpm" ></f:js>
<title><spr:message code="sysTypeKeyLanguage.title"/><spr:message code="title.manage"/></title>
<script type="text/javascript">
	var typeId = ${typeId};
	$(function(){
		//处理点击列的国际化选项。
		$("#tableColumnItem").delegate("tbody>tr>td.lan", "click", function() {
			var tdObj=$(this),
				trObj=tdObj.parent();
			var hasInput=tdObj.has("input").length==1;
			if(!hasInput){
				var tdText = tdObj.text().trim();
				var txtObj=$("<input type='text' class='inputText' maxlength='20' size='20' value='"+tdText+"' />");
				txtObj.blur(function(){
					var tmpObj=$(this);
					var val=tmpObj.val();
					tmpObj.parent().text(val);
					tmpObj.remove();
					if(tdText!=val){
						if(!trObj.hasClass("hover")){
							trObj.addClass("hover");
						}
						if(!tdObj.hasClass("modified")){
							tdObj.addClass("modified");
						}
						tdObj.attr('style','background-color:#FFE2D7;');
					}
				});
				tdObj.empty();
				tdObj.append(txtObj);
				txtObj.focus();
			}
		});
		
		$("a.save").click(function(){
			handSave();
		})
	});
	
	/**
	 * 处理保存事件。
	 */
	function handSave(){
		var trObj = $("#tableColumnItem>tbody>tr.hover");
		if(trObj.length==0){
			$.ligerDialog.warn($lang_bpm.language.no_any_operate,$lang.tip.warn);
			return;
		}
		var lanJson = [];
		trObj.each(function(){
			var lanDetail = [];
			var input = $("input[name='resKey']", $(this)),
				resKey = input.val(),
				formId = input.attr("typeId");
			$("td.modified", $(this)).each(function(){
				var me = $(this),
					lanType = me.attr("name"),
					resValue = me.text().trim();
				lanDetail.push({lanType:lanType, resValue:resValue});
			});
			lanJson.push({formId:formId, resKey:resKey, lanDetail:lanDetail});
		});
			
		var lanJsonStr=JSON2.stringify(lanJson);
		$.post("save.ht", {lanJsonStr:lanJsonStr, typeId:typeId}, showResponse);
	}
	
	/**
	 * 显示返回结果。
	 */
	function showResponse(data){
		var obj=new com.hotent.form.ResultMessage(data);
		if(obj.isSuccess()){//成功
			$.ligerDialog.confirm($lang.operateTip.successContinue,$lang.tip.msg,function(rtn){
				if(!rtn){
					location.href=__ctx+'/platform/system/sysTypeKey/list.ht';
				}
				else{
					location.href=location.href.getNewUrl();
				}
			});
	    }else{//失败
	    	$.ligerDialog.err($lang.tip.msg,$lang_bpm.language.save_fail,obj.getMessage());
	    };
	};
	
	
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="sysTypeKeyLanguage.title"/><spr:message code="title.manageList"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link save" href="#"><span></span><spr:message code="menu.button.save"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del" action="typeDel.ht"><span></span><spr:message code="menu.button.del"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="${ctx}/platform/system/sysTypeKey/list.ht"><span></span><spr:message code="menu.button.back"/></a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="typeList.ht">
					<div class="row">
						<span class="label"><spr:message code="sysTypeKeyLanguage.typeName"/>:</span><input type="text" name="Q_typeKey_SL" value="${param['Q_typeKey_SL']}" class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
			<table id="tableColumnItem" cellpadding="1" cellspacing="1" border="0" class="table-grid">
				<thead>
					<th width="3%">
						<input type="checkbox" id="chkall"/>
					</th>
					<th width="15%"><spr:message code="sysTypeKeyLanguage.typeName"/></th>
					<c:forEach items="${languages}" var="language">
						<th width="20%">${language.memo}</th>
					</c:forEach>
					<th><spr:message code="list.manage"/></th>
				</thead>
				<tbody>
					<c:forEach items="${sysTypeKeyList}" var="sysTypeKey" varStatus="obj">
						<c:if test="${obj.count%2=='0'}">
							<tr class="even">
						</c:if>
						<c:if test="${obj.count%2!='0'}">
							<tr class="odd">
						</c:if>
							<td>
								<input type="checkbox" class="pk" name="resKey" typeId="${sysTypeKey.typeId}" value="${sysTypeKey.typeKey}">
							</td>
							<td>${sysTypeKey.typeName}</td>
							<c:forEach items="${languages}" var="language">
								<td class="lan" name="${language.language}">
									${sysTypeKeyLanguageMap[sysTypeKey.typeKey][language.language]}
								</td>
							</c:forEach>
							<td style="line-height:21px;">
								<a href="typeDel.ht?resKey=${sysTypeKey.typeKey}" class="link del"><spr:message code="menu.button.del"/></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>			
	</div>
</body>
</html>


