<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
<title><spr:message code="bpmNodeScript.title"/></title>
<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=bpmNodeScript"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerTab.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/ScriptDialog.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowVarWindow.js" ></script>
<script type="text/javascript" src="${ctx}/js/javacode/codemirror.js"></script>

<script type="text/javascript">
	var defId="${defId}";
	var currentTextarea=null;
	function showRequest(formData, jqForm, options) {
		return true;
	}
	$(function() {
        //Tab
		valid(showRequest,function(){});
           $("#tabScript").ligerTab({height:300});
           $("#btnSearch").click(save);
        $("textarea").click(function(){
        	currentTextarea=this;
        });
	});
	
	function handFlowVars(obj,txtId){
		var val=$(obj).val();
		$(currentTextarea).append(val);
	}
	
	function slectVars(txtId){
		FlowVarWindow({defId:defId,callback:function(varKey,varName){
			$(currentTextarea).append(varKey);
		}});
		
	}
	
	function slectScript(txtId){
		ScriptDialog({callback:function(script){
			$(currentTextarea).append(script);
		}});
	}
	
	function save() {		
		var rtn = $("#bpmNodeScriptForm").valid();
		if (!rtn)
			return;

		var url = __ctx + "/platform/bpm/bpmNodeScript/save.ht";
		var para = $('#bpmNodeScriptForm').serialize();

		$.post(url, para, showResult);
	}

	function showResult(responseText) {
		var obj = new com.hotent.form.ResultMessage(responseText);
		if (!obj.isSuccess()) {
			$.ligerDialog.err($lang.tip.errormsg,$lang_bpm.bpmNodeScript.edit.fail, obj.getMessage());
			return;
		} else {
			$.ligerDialog.success($lang_bpm.bpmNodeScript.edit.success,$lang.tip.msg,function() {
				window.close();
			});
		}
	}
</script>
	<style type="text/css">
	html { overflow-y: hidden; }
	</style>
</head>
<body>
<c:set var="preModel" value="${map.type1}"></c:set>
<c:set var="afterModel" value="${map.type2}"></c:set>
<c:set var="assignModel" value="${map.type4}"></c:set>
<c:set var="scrptModel" value="${map.type3}"></c:set>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">
				  <c:choose>
						<c:when test="${nodeId==0}">
							<spr:message code="menu.button.add"/><spr:message code="bpmNodeScript.title"/>
						</c:when>
						<c:otherwise>
							<spr:message code="menu.button.edit"/><spr:message code="bpmNodeScript.title"/>
						</c:otherwise>
				   </c:choose> 
				</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="btnSearch"><span></span><spr:message code="menu.button.save"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  onclick="window.close()"><span></span><spr:message code="menu.button.close"/></a></div>
				
				</div>	
			</div>
		</div>
		<div class="panel-body">
				<form id="bpmNodeScriptForm" method="post" action="save.ht">
						<c:if test="${type eq 'startEvent' || type eq 'subProcess' }">
							<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
								<tr>
									<th width="20%"><spr:message code="tip.explanation"/>:<span class="required">*</span> </th>
									<td>
										<spr:message code="bpmNodeScript.startTip" arguments="<span class='red'>,</span>,<span class='red'>execution</span>" />
										<spr:message code="bpmNodeScript.example" />:execution.setVariable("total", 100);
										<input type="hidden"  name="scriptType" value="1"  class="inputText"/>
										
									</td>
								</tr>
								<tr>
									<th width="20%" ><spr:message code="bpmNodeScript.script"/>:<span class="required">*</span> </th>
									<td>
										<div>
											<a href="#"  class="link var" title="<spr:message code="script.common"/>" onclick="slectScript('startScript')"><spr:message code="script.common"/></a>
											<spr:message code="script.flowVar"/>:<f:flowVar defId="${defId}" change="handFlowVars(this,'startScript')" controlName="selFlowVar"></f:flowVar>
										</div>
										<textarea id="startScript" name="script"  rows="10" cols="80" >${preModel.script}</textarea>
									</td>
								</tr>
							</table>
						</c:if>
						<c:if test="${type eq 'endEvent' || type eq 'subProcess'}">
							<table class="table-detail" cellpadding="0" cellspacing="0" border="0"  >
								<tr>
									<th width="20%"><spr:message code="tip.explanation"/>:<span class="required">*</span> </th>
									<td>
										<spr:message code="bpmNodeScript.endTip" arguments="<span class='red'>,</span>,<span class='red'>execution</span>" />
										<spr:message code="bpmNodeScript.example" />:execution.setVariable("total", 100);
										<input type="hidden"   name="scriptType" value="2"  />
									</td>
								</tr>
								<tr>
									<th width="20%" ><spr:message code="bpmNodeScript.script"/>:<span class="required">*</span> </th>
									<td>
										<div>
											<a href="#"  class="link var" title="<spr:message code="script.common"/>" onclick="slectScript('endScript')"><spr:message code="script.common"/></a>
											<spr:message code="script.flowVar"/>:<f:flowVar defId="${defId}" change="handFlowVars(this,'endScript')" controlName="selFlowVar"></f:flowVar>
										</div>
										<textarea id="endScript" name="script"  rows="10" cols="80"  >${afterModel.script}</textarea>
									</td>
								</tr>
							</table>
						</c:if>
						<c:if test="${type eq 'script' }">
							<table class="table-detail" cellpadding="0" cellspacing="0" border="0" style='${type eq "script"?'display:':'display:none'}'>
									<tr>
										<th width="20%"><spr:message code="tip.explanation"/>:<span class="required">*</span> </th>
										<td>
											<spr:message code="bpmNodeScript.scriptTip" arguments="<span class='red'>,</span>,<span class='red'>execution</span>" />
											<spr:message code="bpmNodeScript.example" />:execution.setVariable("total", 100);
											<input type="hidden"  name="scriptType" value="3"  />
										</td>
									</tr>
									<tr>
										<th width="20%" ><spr:message code="bpmNodeScript.script"/>:<span class="required">*</span> </th>
										<td>
											<div>
												<a href="#"  class="link var" title="<spr:message code="script.common"/>" onclick="slectScript('scriptScript')"><spr:message code="script.common"/></a>
												<spr:message code="script.flowVar"/>:<f:flowVar defId="${defId}" change="handFlowVars(this,'scriptScript')" controlName="selFlowVar"></f:flowVar>
											</div>
											<textarea id="scriptScript" name="script"  rows="10" cols="80" >${scrptModel.script}</textarea>
										</td>
									</tr>
							</table>
						</c:if>
						<c:if test="${type=='multiUserTask'||type=='userTask' }">
						   <div id="tabScript" >
							  <div tabid="startScript" title="<spr:message code="bpmNodeScript.preScript"/>">
							  
							     <table class="table-detail" cellpadding="0" cellspacing="0" border="0">
											<tr>
												<th width="20%"><spr:message code="tip.explanation"/>:<span class="required">*</span> </th>
												<td>
													<spr:message code="bpmNodeScript.preTip" arguments="<span class='red'>,</span>,<span class='red'>task</span>" />
													<spr:message code="bpmNodeScript.example" />:task.setVariable("total", 100);
													<input type="hidden"  name="scriptType" value="1"  />
												
												</td>
											</tr>
											<tr>
												<th width="20%" ><spr:message code="bpmNodeScript.script"/>:<span class="required">*</span> </th>
												<td>
													<div>
														<a href="#"  class="link var" title="<spr:message code="script.common"/>" onclick="slectScript('preScript')"><spr:message code="script.common"/></a>
														<spr:message code="script.flowVar"/>:<f:flowVar defId="${defId}" change="handFlowVars(this,'preScript')" controlName="selFlowVar"></f:flowVar>
													</div>
													<textarea id="preScript" name="script"  rows="10" cols="80"  >${preModel.script}</textarea>
												</td>
											</tr>
							    </table>
				              </div>
				               <div tabid="endScript" title="<spr:message code="bpmNodeScript.afterScript"/>" >
							     <table class="table-detail" cellpadding="0" cellspacing="0" border="0">
											<tr>
												<th width="20%"><spr:message code="tip.explanation"/>:<span class="required">*</span> </th>
												<td>
													<spr:message code="bpmNodeScript.afterTip" arguments="<span class='red'>,</span>,<span class='red'>task</span>" />
													<spr:message code="bpmNodeScript.example" />:task.setVariable("total", 100);
													<input type="hidden"   name="scriptType" value="2" />
												
												</td>
											</tr>
											<tr>
												<th width="20%"><spr:message code="bpmNodeScript.script"/>:<span class="required">*</span>  </th>
												<td>
													<div>
														<a href="#"  class="link var" title="<spr:message code="script.common"/>" onclick="slectScript('afterScript')"><spr:message code="script.common"/></a>
														<spr:message code="script.flowVar"/>:<f:flowVar defId="${defId}" change="handFlowVars(this,'afterScript')" controlName="selFlowVar"></f:flowVar>
													</div>
													<textarea id="afterScript" name="script"  rows="10" cols="80" >${afterModel.script}</textarea>
												</td>
											</tr>
							    </table>
				              </div>
				               <div tabid="assignScript" title="<spr:message code="bpmNodeScript.assignScript"/>" >
							     <table class="table-detail" cellpadding="0" cellspacing="0" border="0">
											<tr>
												<th width="20%"><spr:message code="tip.explanation"/>:<span class="required">*</span> </th>
												<td>
													<spr:message code="bpmNodeScript.assignTip" arguments="<span class='red'>,</span>,<span class='red'>task</span>" />
													<spr:message code="bpmNodeScript.example" />:task.setVariable("total", 100);
													<input type="hidden"  name="scriptType" value="4"  class="inputText"/>
													
												</td>
											</tr>
											<tr>
												<th width="20%"><spr:message code="bpmNodeScript.script"/>:<span class="required">*</span> </th>
												<td>
													<div>
														<a href="#"  class="link var" title="<spr:message code="script.common"/>" onclick="slectScript('assignScript')"><spr:message code="script.common"/></a>
														<spr:message code="script.flowVar"/>:<f:flowVar defId="${defId}" change="handFlowVars(this,'assignScript')" controlName="selFlowVar"></f:flowVar>
													</div>
													<textarea id="assignScript" name="script" rows="10" cols="80" >${assignModel.script}</textarea>
												</td>
											</tr>
							    </table>
				              </div>
							 </div>
						 </c:if>
						<input type="hidden" id="nodeId" name="nodeId" value="${nodeId}"  class="inputText"/>
						<input type="hidden" id="actDefId" name="actDefId" value="${actDefId}"  class="inputText"/>
				</form>
				</div>
</div>
</body>
</html>
