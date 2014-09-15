<%--
	time:2012-01-05 12:01:21
	desc:edit the 其它参数
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/get.jsp" %>
	<title><spr:message code="bpmDefinition.otherParam.title"/></title>
	<script type="text/javascript" src="${ctx}/js/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="${ctx}/js/ckeditor/ckeditor_rule.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowVarWindow.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/FlexUploadDialog.js"></script>
	<script type="text/javascript">
	var defId=${bpmDefinition.defId };
	
	$(function(){
		editor=ckeditor('taskNameRule');
		handFlowVars();
		//状态改变
		handleStatusChange();
	});
	
	function handleStatusChange(){
		$("#status").change(function(){
			var v=$(this).val();
			if(v=="2" || v=="3"){
				$("#spanMessage").hide();
			}
			else{
				$("#spanMessage").show();
			}
			if(v=="4"){
				$("#testTag").show();
			}else{
				$("#testTag").hide();
			}
		});
	}
	
	function handFlowVars(){
		var objConditionCode=$("#taskNameRule");
		$("select[name='selFlowVar']").change(function(){		
			var val=$(this).val();
			var text=$(this).find("option:selected").text();
			if(val.length==0) return;
			if(text=="发起人(长整型)")
				text=text.replace("(长整型)","");			
			var inStr="{"+text+":"+val+"}";
			InsertText(inStr);
		});
	}
	
	function InsertText(val){
		// Get the editor instance that we want to interact with.
		var oEditor = CKEDITOR.instances.taskNameRule;
		// Check the active editing mode.
		if ( oEditor.mode == 'wysiwyg' ){
			oEditor.insertText( val );
		}else
			alert( $lang_bpm.bpmDefinition.otherParam.insertTextTip );
	}
	
	function getCheckedValue(id){
		
		var checked=$(id).attr("checked");
		if(checked==undefined){
			return 0;
		}else if(checked){
			return 1;
		}else{
			return 0;
		}
	}
	function getMsgTypeList(id){
		var msgTypeList=[];
		$("input[name='"+id+"']").each(function(){
			var me = $(this),
				val = me.val(),
				state = me.attr("checked");
			if(state)
				msgTypeList.push(val);
		});
		return msgTypeList;
	}
	function saveParam(){
		var taskNameRule=CKEDITOR.instances["taskNameRule"].getData();
		var toFirstNode=getCheckedValue("#toFirstNode");
		var showFirstAssignee=getCheckedValue("#showFirstAssignee");
		var submitConfirm=getCheckedValue("#submitConfirm");
		var allowDivert=getCheckedValue("#allowDivert");
		var allowFinishedDivert=getCheckedValue("#allowFinishedDivert");
		var informStart = getMsgTypeList("informStart");
		var informType = getMsgTypeList("informType");
		var allowFinishedCc=getCheckedValue("#allowFinishedCc");
		var isPrintForm=getCheckedValue("#isPrintForm");
		var formDetailUrl=$('#formDetailUrl').val();
		var attachment=$('#attachment').val();
		var status=$('#status').val();
		var sameExecutorJump=getCheckedValue("#sameExecutorJump");
		var isUseOutForm=getCheckedValue("#isUseOutForm");
		var isUseOutForm=getCheckedValue("#isUseOutForm");
		var allowRefer=getCheckedValue("#allowRefer");
		var instanceAmount=$('#instanceAmount').val();
		var testStatusTag=$('#testStatusTag').val();
		
		var directstart=getCheckedValue("#directstart");
		var ccMessageType = $("input[name='ccMessageType']").val();
		 
		
		var params={defId:defId,taskNameRule:taskNameRule,toFirstNode:toFirstNode,
				showFirstAssignee:showFirstAssignee,
				submitConfirm:submitConfirm,allowDivert:allowDivert,
				allowFinishedDivert:allowFinishedDivert,informStart:informStart.join(','),
				informType:informType.join(','),allowFinishedCc:allowFinishedCc,
				isPrintForm:isPrintForm,attachment:attachment,
				status:status,sameExecutorJump:sameExecutorJump,
				isUseOutForm:isUseOutForm,allowRefer:allowRefer,instanceAmount:instanceAmount,
				directstart:directstart,ccMessageType:ccMessageType,testStatusTag:testStatusTag};
		
		$.post("saveParam.ht",params,function(msg){
			var obj=new com.hotent.form.ResultMessage(msg);
			if(obj.isSuccess()){
				$.ligerDialog.success(obj.getMessage(),$lang.tip.msg);
			}else{
				$.ligerDialog.err($lang.tip.msg,$lang_bpm.bpmDefinition.otherParam.failure,obj.getMessage());
			}
		});
	}
	
	function openCcUserList(){
		var url=__ctx +'/platform/bpm/bpmDefinition/copyUserList.ht?defId=${bpmDefinition.defId}';
	 	var winArgs="height=450,width=750,status=no,toolbar=no,menubar=no,location=no,resizable=1,scrollbars=1";
		url=url.getNewUrl();
	 	window.open(url,"",winArgs);
	 	$("#allowFinishedCc").attr("checked","checked");
	};
	
	//添加附件
	function addFile(){
		FlexUploadDialog({isSingle:true,callback:fileCallback});
	};
	
	function fileCallback(fileIds,fileNames,filePaths){
		if(fileIds==undefined || fileIds=="") return ;
		var url=__ctx+"/platform/system/sysFile/file_"+fileIds +".ht";
		$("#attachment").val(fileIds);
		if($("#file").length>0){
			$("#file").attr("href",url).text(fileNames);
		}else{
			var node='<a href="'+url+'" target="_blank" id="file">'+fileNames+' </a><a  class="link del" onclick="del(this)" style="cursor:pointer;"> <spr:message code="menu.button.del"/></a>';
			$("#attachment").after(node);
		}
	}
	function del(obj){
		$("#attachment").val("");
		$("#file").remove();
		$(obj).remove();
	}
	var referDef;
	function referDefinition(){
		var url=__ctx +'/platform/bpm/bpmDefinition/defReferSelector.ht?defId=${bpmDefinition.defId}';
		referDef = $.ligerDialog.open({title:'<spr:message code="bpmDefinition.defRefer.title"/>',mask:true,isResize:true,height: 500,url:url,width:700});
	};
	
	function clickAllowRefer(obj){
		var $obj=$(obj);
		if($obj.attr("checked")){
			$("#spanInstanceAmount").show();	
		}
		else{
			$("#spanInstanceAmount").hide();
		}
		
	}
	
	function delRefer(refId){
		$.post("delReferDef.ht",{refId:refId},function(msg){
			var obj=new com.hotent.form.ResultMessage(msg);
			if(obj.isSuccess()){
				$.ligerDialog.success(obj.getMessage(),$lang.tip.msg);
				$('#ref_'+refId).remove();
			}else{
				$.ligerDialog.error(obj.getMessage(),$lang.tip.msg);
			}
		});
	}
	
	</script>
</head>
<body> 

            <jsp:include page="incDefinitionHead.jsp">
		   		<jsp:param value="<spr:message code='bpmDefinition.otherParam.title'/>" name="title"/>
			</jsp:include>
			 <div class="panel-container">
            <f:tab curTab="otherParam" tabName="flow"/>
            <div class="panel-top">
	            <div class="tbar-title">
					<span class="tbar-label"><spr:message code="bpmDefinition.otherParam.title"/></span>
				</div>
				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group"><a class="link save" onclick="saveParam()"><span></span><spr:message code="menu.button.save"/></a></div>
					</div>	
				</div>

			</div>
            <div class="panel-detail">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="15%"><spr:message code="bpmDefinition.taskNameRule"/></th>
					<td>
						<spr:message code="script.flowVar"/>:<f:flowVar defId="${defId}" controlName="selFlowVar"></f:flowVar>
						<textarea id="taskNameRule" row="6" name="taskNameRule" >${bpmDefinition.taskNameRule }</textarea>
					</td>	
				</tr>
				
				<tr>
					<th width="15%"><spr:message code="bpmDefinition.toFirstNode"/>:</th>
					<td>
						<input id="toFirstNode" type="checkbox"  name="toFirstNode" value="0"  <c:if test="${bpmDefinition.toFirstNode==1 }">checked="checked"</c:if> />
						<div class="tipbox"><a href="#" class="tipinfo"><span><spr:message code="bpmDefinition.toFirstNode.help"/></a></div>
					</td>	
				</tr>
				<tr>
					<th width="15%"><spr:message code="bpmDefinition.directStartProcess"/>:</th>
					<td>
						<input id="directstart" type="checkbox" name="directstart" value="0"  <c:if test="${bpmDefinition.directstart==1 }">checked="checked"</c:if> />
						<div class="tipbox"><a href="#" class="tipinfo"><span><spr:message code="bpmDefinition.directStartProcess.help"/></span></a></div>
					</td>	
				</tr>
				<tr>
					<th width="15%"><spr:message code="bpmDefinition.showFirstAssignee"/>:</th>
					<td>
						<input id="showFirstAssignee" type="checkbox" name="showFirstAssignee" value="1"  <c:if test="${bpmDefinition.showFirstAssignee==1 }">checked="checked"</c:if> />
						<div class="tipbox"><a href="#" class="tipinfo"><span><spr:message code="bpmDefinition.showFirstAssignee.help"/></span></a></div>
					</td>	
				</tr>
				<tr>
					<th width="15%"><spr:message code="bpmDefinition.isUseOutForm"/>:</th>
					<td>
						<input id="isUseOutForm" type="checkbox" name="isUseOutForm" value="1"  <c:if test="${bpmDefinition.isUseOutForm==1 }">checked="checked"</c:if> />
						
						<div class="tipbox"><a href="#" class="tipinfo"><span><spr:message code="bpmDefinition.isUseOutForm.help"/></span></a></div>
					</td>	
				</tr>
				
				<tr>
					<th width="20%"><spr:message code="bpmDefinition.submitConfirm"/>:</th>
					<td>
						<input id="submitConfirm" type="checkbox" name="submitConfirm" value="${bpmDefinition.submitConfirm}" <c:if test="${bpmDefinition.submitConfirm==1 }">checked="checked"</c:if>  />
						<div class="tipbox"><a href="#" class="tipinfo"><span><spr:message code="bpmDefinition.submitConfirm.help"/></span></a></div>
					</td>	
				</tr>
				
				<tr>
					<th width="20%"><spr:message code="bpmDefinition.allowDivert"/>:</th>
					<td>
						<input id="allowDivert" type="checkbox" name="allowDivert" value="${bpmDefinition.allowDivert}" <c:if test="${bpmDefinition.allowDivert==1 }">checked="checked"</c:if> />
						<div class="tipbox"><a href="#" class="tipinfo"><span><spr:message code="bpmDefinition.allowDivert.help"/></span></a></div>
					</td>	
				</tr>
				<tr>
					<th width="20%"><spr:message code="bpmDefinition.allowFinishedDivert"/>:</th>
					<td>
						<input id="allowFinishedDivert" type="checkbox" name="allowFinishedDivert"   <c:if test="${bpmDefinition.allowFinishedDivert==1 }">checked="checked"</c:if>  />
						<div class="tipbox"><a href="#" class="tipinfo"><span><spr:message code="bpmDefinition.allowFinishedDivert.help"/></span></a></div>
					</td>	
				</tr>
				<tr>
					<th width="20%"><spr:message code="bpmDefinition.startUserMsgSet"/>:</th>
					<td>
							<label><input type="checkbox" name="informStart" value="3" <c:if test="${fn:contains(bpmDefinition.informStart,3)}">checked="checked"</c:if>/><spr:message code="message.inner"/></label>
							<label><input type="checkbox" name="informStart" value="1" <c:if test="${fn:contains(bpmDefinition.informStart,1)}">checked="checked"</c:if>/><spr:message code="message.mail"/></label>
							<label><input type="checkbox" name="informStart" value="2" <c:if test="${fn:contains(bpmDefinition.informStart,2)}">checked="checked"</c:if>/><spr:message code="message.sms"/></label>
					</td>	
				</tr>
			
				<tr>
					<th width="20%"><spr:message code="bpmDefinition.allowFinishedCc"/>:</th>
					<td>
						<input id="allowFinishedCc" type="checkbox" name="allowFinishedCc" value="${bpmDefinition.allowFinishedCc}"  <c:if test="${bpmDefinition.allowFinishedCc==1 }">checked="checked"</c:if>  />
						<!-- 抄送消息提醒方式 -->
						<input class="send_type" name="ccMessageType" type="hidden" value='${bpmDefinition.ccMessageType}' />
						<a href="###" onclick="openCcUserList()"><spr:message code="bpmDefinition.allowFinishedCc.setting"/></a>
						<div class="tipbox"><a class="tipinfo"><span><spr:message code="bpmDefinition.allowFinishedCc.help"/></span></a></div>
					</td>
				</tr>
				<tr>
					<th width="20%"><spr:message code="bpmDefinition.isPrintForm"/>:</th>
					<td>
						<input id="isPrintForm" type="checkbox" name="isPrintForm" value="${bpmDefinition.isPrintForm}"   <c:if test="${bpmDefinition.isPrintForm==1 }">checked="checked"</c:if> />
						<div class="tipbox"><a href="#" class="tipinfo"><span><spr:message code="bpmDefinition.isPrintForm.help"/></span></a></div>
					</td>	
				</tr>
				
				<tr>
					<th width="20%"><spr:message code="bpmDefinition.attachment"/>:</th>
					<td>
						<input id="attachment" type="hidden" name="attachment" value="${bpmDefinition.attachment}"  />
						
						<c:if test="${sysFile !=null }">
							<a href="${ctx}/platform/system/sysFile/file_${sysFile.fileId}.ht" target="_blank" id="file">${sysFile.fileName }</a><a  class="link del" onclick="del(this)" style="cursor:pointer;">删除</span>
						</c:if>
						<a href="javascript:void(0);" class="link selectFile"  onclick="addFile()"><spr:message code="menu.button.addFile"/></a>
						<div class="tipbox"><a href="#" class="tipinfo"><span><spr:message code="bpmDefinition.attachment.help"/></span></a></div>
					</td>	
				</tr>
				<tr>
					<th width="20%"><spr:message code="bpmDefinition.refDefs"/>:</th>
					<td>
						<span id="refDefArray">
							<c:forEach items="${referList}" var="refer">
								<span id="ref_${refer.id}"><c:out value="${refer.subject}"></c:out><a href="javascript:void(0);" onclick="delRefer(${refer.id})"><spr:message code="menu.button.del"/></a>&nbsp;&nbsp;&nbsp;&nbsp;</span>
							</c:forEach>
						</span><span id="refDefs"></span>
						<a class="link search" href="javascript:void(0);" onclick="referDefinition()"><spr:message code="menu.button.refer"/></a>
						<div class="tipbox"><a href="#" class="tipinfo"><span><spr:message code="bpmDefinition.refDefs.help"/></span></a></div>
					</td>	
				</tr>
				<tr>
					<th width="20%"><spr:message code="bpmDefinition.allowRefer"/>:</th>
					<td>
						<input type="checkbox" value="${bpmDefinition.allowRefer}" name="allowRefer" id="allowRefer" onclick="clickAllowRefer(this)"  <c:if test="${bpmDefinition.allowRefer==1 }">checked="checked"</c:if> ><spr:message code="bpmDefinition.otherParam.allowRefer"/>
						<span id="spanInstanceAmount" <c:if test="${bpmDefinition.allowRefer==0 }">style="display:none;"</c:if> >
						<spr:message code="bpmDefinition.instanceAmount"/>:<input type="text" value="${bpmDefinition.instanceAmount }" name="instanceAmount" id="instanceAmount" style="width:30px;" >
						</span>
					</td>	
				</tr>
				
				<tr>
					<th width="20%"><spr:message code="bpmDefinition.status"/>:</th>
					<td>
						<select id="status" name="status">
							<option value="1" <c:if test="${bpmDefinition.status==1}">selected='selected'</c:if>><spr:message code="bpmDefinition.status.start"/></option>
							<option value="2" <c:if test="${bpmDefinition.status==2}">selected='selected' </c:if>><spr:message code="bpmDefinition.status.disabled"/></option>
							<option value="3" <c:if test="${bpmDefinition.status==3}">selected='selected' </c:if>><spr:message code="bpmDefinition.status.disabledcase"/></option>
							<option value="4" <c:if test="${bpmDefinition.status==4}">selected='selected' </c:if>><spr:message code="bpmDefinition.status.test"/></option>
						</select>
						<span id="spanMessage" <c:if test="${bpmDefinition.status==2||bpmDefinition.status==3}">style="display:none;"</c:if>>
							<%--
								<label><input type="checkbox" name="informType" value="3" <c:if test="${fn:contains(bpmDefinition.informType,3)}">checked="checked"</c:if>/><spr:message code="message.inner"/></label>
								<label><input type="checkbox" name="informType" value="1" <c:if test="${fn:contains(bpmDefinition.informType,1)}">checked="checked"</c:if>/><spr:message code="message.mail"/></label>
								<label><input type="checkbox" name="informType" value="2" <c:if test="${fn:contains(bpmDefinition.informType,2)}">checked="checked"</c:if>/><spr:message code="message.sms"/></label>
							--%>
							<c:forEach items="${handlersMap}" var="item">
							   <input type="checkbox" name="informType" value="${item.key }"  <c:if test="${fn:contains(bpmDefinition.informType,item.key)}">checked="checked"</c:if> />
					            ${item.value.title }
							</c:forEach>
						</span>
						<span id="testTag"<c:if test="${bpmDefinition.status!=4}">style="display:none;"</c:if>>
							&nbsp;&nbsp;<spr:message code="bpmDefinition.testStatusTag"/>：<input type="text" id="testStatusTag" name="testStatusTag" value="${bpmDefinition.testStatusTag}" >
						</span>
					</td>	
				</tr>
				<tr>
					<th width="20%"><spr:message code="bpmDefinition.sameExecutorJump"/>:</th>
					<td>
						<input type="checkbox" name="sameExecutorJump" id="sameExecutorJump" value="${bpmDefinition.sameExecutorJump}"  <c:if test="${bpmDefinition.sameExecutorJump==1 }">checked="checked"</c:if> />
						<div class="tipbox"><a href="#" class="tipinfo"><span><spr:message code="bpmDefinition.sameExecutorJump.help"/></span></a></div>
					</td>	
				</tr>
			</table>
			</div>
			</div>
			<input type="hidden" id="defId" name="defId" value="${bpmDefinition.defId }">
</body>
</html>
