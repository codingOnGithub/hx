<%--
	time:2011-12-14 08:41:55
	desc:edit the 会签任务投票规则
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
<title><spr:message code="bpmNodeSign.title"/></title>
<f:js pre="js/lang/view/platform/bpm" ></f:js>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/ScriptDialog.js" ></script>
<script type="text/javascript"src="${ctx}/js/hotent/platform/system/PersonScriptAddDialog.js"></script>
<script type="text/javascript">
$(function(){
	var options={};
	if(showResponse){
		options.success=showResponse;
	}
	$("#bpmNodeSignForm").ajaxForm(options);
	$("a.save").click(function(){
		$("#bpmNodeSignForm").submit();
	});
	
	$("#tabContent").ligerTab({height:500});
	
	//wangwz[2012-12-20]添加
	$("input[name=voteType]").click(function(){
		var val=$(this).val();
		if(val=="2")
			$("#voteAmoutTips").html("输入整数票数。");
		else
			$("#voteAmoutTips").html("例:输入50则表示50%。");
	});
});

function addPersonScript(obj){
	var _this = $(obj);
	PersonScriptAddDialog({
		data:{
			defId:"${param.defId}"
		},
		callback:addScriptCallBack
	});
};

function addScriptCallBack(data){
	var script = data.script;
	var inst = script.classInsName;
	var method = script.methodName;
	var str = "return "+inst +"."+method+ "( ";
	var paramStr="";
	for(var i=0 ; i<script.argument.length; i++){
		var p=script.argument[i];
		switch(p.paraValType){
		case '1':
			paramStr += p.paraVal+" , " ;
			break;
		case '2':
			if(p.paraType.indexOf("String")>0){
				paramStr += "\"" + p.paraVal+ "\" , " ;
			}else{
				paramStr +=  p.paraVal+ " , " ;
			}
			break;
		}
	}
	if(paramStr){
		paramStr = paramStr.substring(0,paramStr.length-2);
	}
	str += paramStr+");" ;
	var target = $('#txtScriptData')[0];
	jQuery.insertText(target,str);
};

function showResponse(responseText) {
	var obj = new com.hotent.form.ResultMessage(responseText);
	if (obj.isSuccess()) {
		$.ligerDialog.success('你成功进行了会签属性设置!','操作成功', function(rtn) {
			if(rtn){
				window.close();
			}
		});
	} else {
		$.ligerDialog.error(obj.getMessage(),"提示信息");
	}
}

function selectCmp(obj){
	var cmpIds=$(obj).siblings("input[name='cmpIds']");
	var cmpNames=$(obj).siblings("textarea[name='cmpNames']");
	var assignType=$(obj).parent().prev('td').children('[name="userType"]').val();

	var nodeUserId=$(obj).parent().prev('td').prev('td').children('[name="nodeUserId"]').val();
	
	var nodeId=$(obj).parent().prev('td').prev('td').children('[name="nodeId"]').val();
	
	var callback=function(ids,names){				
		cmpIds.val(ids);
		cmpNames.val(names);
	};
	
	if(assignType==1){
		 UserDialog({callback:callback});
	}
	else if(assignType==2){
		RoleDialog({callback:callback});
	}
	else if(assignType==3 || assignType==4){
		OrgDialog({callback:callback});	
	}
	else if(assignType==5){
		PosDialog({callback:callback});
	}
	else if(assignType==6){
		UplowDialog({callback:callback});
	}
	else if(assignType==7){
		UserParamDialog({callback:callback,nodeUserId:nodeUserId,cmpIds:cmpIds.val(),cmpNames:cmpNames.val()});
	}
	else if(assignType==8){
		OrgParamDialog({callback:callback,nodeUserId:nodeUserId,cmpIds:cmpIds.val(),cmpNames:cmpNames.val()});
	}else if(assignType==10){
		showOtherNodeDlg({callback:callback,nodeId:nodeId});
	}else if(assignType==16){
		typeSetDialog({callback:callback,nodeUserId:nodeUserId,cmpIds:cmpIds.val(),cmpNames:cmpNames.val()});
	}
	
	else if(assignType==12){
		showScript(cmpNames);
	}
}

function assignTypeChange(obj){
	var cmpIds=$(obj).parent().next('td').children('input[name="cmpIds"]');
	var cmpdNames=$(obj).parent().next('td').children('textarea[name="cmpNames"]');
	var selButtons=$(obj).parent().next('td').children('.button');
	if(obj.value==0||obj.value==11||obj.value==9 || obj.value==13 ||obj.value==14 || obj.value==15 ){
		selButtons.hide();
		cmpdNames.hide();
	}else{
		selButtons.show();
		cmpdNames.show();
	}
	cmpIds.val('');
	cmpdNames.val('');
}

function selectScript(){
	var objConditionCode=$("#txtScriptData")[0];
	ScriptDialog({callback:function(script){
		jQuery.insertText(objConditionCode,script);
	}});
}



var win;
function showScript(obj){
	$("#txtScriptData").val(obj.val());
	
	var divObj=$("#divScriptData");
	win= $.ligerDialog.open({ target:divObj , height: 350,width:500, modal :true,
		buttons: [ { text: '确定', onclick: function (item, dialog) { 
				obj.val($("#txtScriptData").val());
				dialog.hide();
			} 
		}, 
		{ text: '取消', onclick: function (item, dialog) { dialog.hide(); } } ] }); 
	
}

function changeVar(obj){
	var val=$(obj).val();
	var objScript=$("#txtScriptData")[0];
	jQuery.insertText(objScript,val);
}

//显示其他节点的对话框
function showOtherNodeDlg(conf){
	var winArgs="dialogWidth=650px;dialogHeight=500px;help=0;status=0;scroll=1;center=1";
	url=__ctx + "/platform/bpm/bpmDefinition/taskNodes.ht?actDefId=${bpmDefinition.actDefId}&nodeId="+conf.nodeId;
	url=url.getNewUrl();
	var rtn=window.showModalDialog(url,"",winArgs);
	if(conf.callback){
		if(rtn!=undefined){
			conf.callback.call(this,rtn.nodeId,rtn.nodeName);
		}	
	}
}

function cleanCmp(obj){
	var cmpIds=$("input[name='cmpIds']",$(obj).parent());
	var cmpNames=$("textarea[name='cmpNames']",$(obj).parent());
	cmpIds.val('');
	cmpNames.val('');
}
</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label"><spr:message code="bpmNodeSign.title"/></span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group"><a class="link save" id="btnSearch"><span></span><spr:message code="menu.button.save"/></a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link del" onclick="javasrcipt:window.close()"><span></span><spr:message code="menu.button.close"/></a></div>
						
						</div>	
					</div>
				</div>
		<div class="panel-body">
				<form id="bpmNodeSignForm" method="post" action="save.ht">
					<div id="tabContent" >
						<div tabid="voteSet" title="<spr:message code='bpmNodeSign.voteSet'/>">
						
							<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
								<tr>
									<th width="20%"><spr:message code="bpmNodeSign.decideType"/>:  <span class="required">*</span></th>
									<td>
										<c:choose>
											<c:when test="${bpmNodeSign.signId==0}">
												<input type="radio" value="1" name="decideType"  /><spr:message code="bpmNodeSign.decideType.pass"/>
												<input type="radio" value="2" name="decideType"  checked="checked"/><spr:message code="bpmNodeSign.decideType.refuse"/>
											</c:when>
											<c:otherwise>
												<input type="radio" value="1" name="decideType" <c:if test="${bpmNodeSign.decideType==1}">checked="checked"</c:if>  /><spr:message code="bpmNodeSign.decideType.pass"/>
												<input type="radio" value="2" name="decideType" <c:if test="${bpmNodeSign.decideType==2}">checked="checked"</c:if> /><spr:message code="bpmNodeSign.decideType.refuse"/>
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
								<tr>
									<th width="20%"><spr:message code="bpmNodeSign.flowMode"/>:  </th>
									<td>
										<c:choose>
											<c:when test="${bpmNodeSign.signId==0}">
												<input type="radio" value="1" name="flowMode" checked="checked" /><spr:message code="bpmNodeSign.flowMode.direct"/>
												<input type="radio" value="2" name="flowMode"  /><spr:message code="bpmNodeSign.flowMode.waitVote"/>
											</c:when>
											<c:otherwise>
												<input type="radio" value="1" name="flowMode" <c:if test="${bpmNodeSign.flowMode==1}">checked="checked"</c:if>  /><spr:message code="bpmNodeSign.flowMode.direct"/>
												<input type="radio" value="2" name="flowMode" <c:if test="${bpmNodeSign.flowMode==2}">checked="checked"</c:if> /><spr:message code="bpmNodeSign.flowMode.waitVote"/>
											</c:otherwise>
										</c:choose>
									</td>
								</tr>						
								<tr>
									<th width="20%"><spr:message code="bpmNodeSign.voteType"/>: </th>
									<td>
										<c:choose>
											<c:when test="${bpmNodeSign.signId==0}">
												<input type="radio" value="2" name="voteType" checked="checked" /><spr:message code="bpmNodeSign.voteType.absolute"/>
												<input type="radio" value="1" name="voteType"  /><spr:message code="bpmNodeSign.voteType.percentage"/>
												
											</c:when>
											<c:otherwise>
												<input type="radio" value="2" name="voteType" <c:if test="${bpmNodeSign.voteType==2}">checked="checked"</c:if> /><spr:message code="bpmNodeSign.voteType.absolute"/>
												<input type="radio" value="1" name="voteType" <c:if test="${bpmNodeSign.voteType==1}">checked="checked"</c:if>  /><spr:message code="bpmNodeSign.voteType.percentage"/>
				
											</c:otherwise>
										</c:choose>
										
									</td>
								</tr>
								<tr>
								<tr>
								<th width="20%"><spr:message code="bpmNodeSign.voteAmount"/>: </th>
								<td>
								<c:choose>
	 	 	 						<c:when test="${bpmNodeSign.signId==0}">
										<input type="text" id="voteAmount" name="voteAmount" value="1"  class="inputText"/>
									</c:when>
									<c:otherwise>
										<input type="text" id="voteAmount" name="voteAmount" value="${bpmNodeSign.voteAmount}"  class="inputText"/>
									</c:otherwise>
								</c:choose>
								<span style="color:red"
										<%--wangwz[2012-12-20]添加--%>
										<span id="voteAmoutTips"
										<spr:message code="operator.tip"/>:
											<c:if test="${bpmNodeSign.voteType==0||bpmNodeSign.voteType==2}"><spr:message code="bpmNodeSign.voteAmount.tip1"/></c:if>
											<c:if test="${bpmNodeSign.voteType==1}"><spr:message code="bpmNodeSign.voteAmount.tip2"/></c:if>
										</span>
								</span>
									</td>
								</tr>
							</table>
						</div>
						
						<div tabid="privilegeSet" title="<spr:message code='bpmNodePrivilege.privilegeSet'/>">
							<table style="width:100%" id="table_privilege" class="table-grid">
								<thead>
								<tr>
									<th width="80" nowrap="nowrap"><spr:message code="bpmNodePrivilege.privilegemode"/></th>
									<th width="98" nowrap="nowrap"><spr:message code="bpmNodePrivilege.usertype"/></th>
									<th width="*" nowrap="nowrap"><spr:message code="bpmNodePrivilege.userform"/></th>
								</tr>
								</thead>
								<tbody class="data">
										<c:forEach items="${modeList}" var="text" varStatus="i">
											<c:set var="bpmNodePrivilege" value="${bpmNodePrivilegeList[i.index]}"/>
											<tr>
												<td nowrap="nowrap" height="28">
													${text}
												</td>
												<td>
													<select name="userType" class="select" onchange="assignTypeChange(this);">
														<option value="1" ${bpmNodePrivilege.usertype==1 ? 'selected="selected"' : ''}><spr:message code="bpmNodePrivilege.usertype.user"/></option>
														<option value="3" ${bpmNodePrivilege.usertype==3 ? 'selected="selected"' : ''}><spr:message code="bpmNodePrivilege.usertype.org"/></option>
														<option value="4" ${bpmNodePrivilege.usertype==4 ? 'selected="selected"' : ''}><spr:message code="bpmNodePrivilege.usertype.orgHead"/></option>
														<option value="7" ${bpmNodePrivilege.usertype==7 ? 'selected="selected"' : ''}><spr:message code="bpmNodePrivilege.usertype.userProperties"/></option>
														<option value="12" ${bpmNodePrivilege.usertype==12 ? 'selected="selected"' : ''}><spr:message code="bpmNodePrivilege.usertype.script"/></option>										
													</select>
												</td>
												<td>
													<input type="hidden" name="cmpIds" value='${bpmNodePrivilege.cmpids}'/>
													<c:choose>
														<c:when test="${bpmNodePrivilege.usertype==0}">
															<spr:message code="bpmNodePrivilege.flowPromoter"/><textarea name="cmpNames" style="width:75%;display:none;" rows="3" class="textarea">${bpmNodePrivilege.cmpnames}</textarea>
															<a class="button" onclick="selectCmp(this);" style="display:none;"><span><spr:message code="bpmNodePrivilege.choose"/></span></a>
														</c:when>
														<c:when test="${bpmNodePrivilege.usertype==9}">
															<spr:message code="bpmNodePrivilege.sameDepPromoter"/><textarea name="cmpNames" style="width:75%;display:none;" rows="3" class="textarea">${bpmNodePrivilege.cmpnames}</textarea>
															<a class="button" onclick="selectCmp(this);" style="display:none;"><span><spr:message code="bpmNodePrivilege.choose"/></span></a>
														</c:when>
														<c:when test="${bpmNodePrivilege.usertype==11}">
															<spr:message code="bpmNodePrivilege.laderPromoterOrg"/><textarea name="cmpNames" style="width:75%;display:none;" rows="3" class="textarea">${bpmNodePrivilege.cmpnames}</textarea>
															<a class="button" onclick="selectCmp(this);" style="display:none;"><span><spr:message code="bpmNodePrivilege.choose"/></span></a>
														</c:when>
														<c:when test="${bpmNodePrivilege.usertype==13}">
															<spr:message code="bpmNodePrivilege.lastTaskLaderOrg"/><textarea name="cmpNames" style="width:75%;display:none;" rows="3" class="textarea">${bpmNodePrivilege.cmpnames}</textarea>
															<a class="button" onclick="selectCmp(this);" style="display:none;"><span><spr:message code="bpmNodePrivilege.choose"/></span></a>
														</c:when>
														<c:when test="${bpmNodePrivilege.usertype==14}">
															<spr:message code="bpmNodePrivilege.laderPromoter"/><textarea name="cmpNames" style="width:75%;display:none;" rows="3" class="textarea">${bpmNodePrivilege.cmpnames}</textarea>
															<a class="button" onclick="selectCmp(this);" style="display:none;"><span><spr:message code="bpmNodePrivilege.choose"/></span></a>
														</c:when>
														<c:when test="${bpmNodePrivilege.usertype==15}">
															<spr:message code="bpmNodePrivilege.lastTaskLader"/><textarea name="cmpNames" style="width:75%;display:none;" rows="3" class="textarea">${bpmNodePrivilege.cmpnames}</textarea>
															<a class="button" onclick="selectCmp(this);" style="display:none;"><span><spr:message code="bpmNodePrivilege.choose"/></span></a>
														</c:when>
														
														<c:otherwise>
															<textarea name="cmpNames" readonly="readonly" style="width:75%;visibility:visible" rows="2" class="textarea">${bpmNodePrivilege.cmpnames}</textarea>
															<a class="button" onclick="selectCmp(this);" style="visibility:visible"><span><spr:message code="bpmNodePrivilege.choose"/></span></a>
														</c:otherwise>
													</c:choose>
													<a class="button" onclick="cleanCmp(this);" style="visibility:visible"><span><spr:message code="bpmNodePrivilege.clean"/></span></a>
												</td>
											</tr>
										</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<input type="hidden" name="signId" value="${bpmNodeSign.signId}" />
					<input type="hidden" id="nodeId" name="nodeId" value="${bpmNodeSign.nodeId}"/>
					<input type="hidden" id="actDefId" name="actDefId" value="${bpmNodeSign.actDefId}" />
				</form>
		</div>
</div>
	<div id="divScriptData" style="display: none;">
		
		<a href="#" id="btnScript" class="link var" title=""<spr:message code='script.common'/>" onclick="addPersonScript()">"<spr:message code="script.common"/></a>
		<ul>
			<li><spr:message code="bpmNodeUser.script.help" arguments="Set&lt;String&gt;"/></li>
		</ul>
		<textarea id="txtScriptData" rows="10" cols="80" style="height: 200px;width:480px"></textarea>
	</div>
</body>
</html>
