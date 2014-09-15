<%--
	time:2011-12-14 15:41:53
	desc:edit the 流程节点规则
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
<title><spr:message code="bpmNodeRule.title"/></title>
<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=bpmNodeRule"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/ScriptDialog.js" ></script>
<script type="text/javascript" src="${ctx}/js/javacode/codemirror.js"></script>
<script type="text/javascript" src="${ctx}/js/javacode/InitMirror.js"></script>
<style type="text/css">
a.ruledetail,a.delrule {
	cursor: pointer;
}
</style>
<script type="text/javascript">
	var deployId="${deployId}";
	var actDefId="${actDefId}";
	var nodeId="${nodeId}";
	function showRequest(formData, jqForm, options) {
		return true;
	}
	$(function() {
		valid(showRequest,function(){});
  		$("#layoutFlowRule").ligerLayout({ rightWidth:200, height: '95%' });
		 //加载列表
		 loadRuleList(); 		
		 $("#btnScript").click(selectScript);
		 $("a.save").click(save);
		 $("#btnAdd").click(addRule);
		 handFlowVars();
	});
	
	function handFlowVars(){		
		$("select[name='selFlowVar']").change(function(){
			var val=$(this).val();
			InitMirror.editor.insertCode(val);
		});
	}
	
	function addRule(){
		$("#ruleId").val("0");
		$("#memo").val("");
		$("#ruleName").val("");
		InitMirror.editor.setCode("return true;");
	}
	
	function save(){
		InitMirror.editor.save();
		 var rtn=$("#bpmNodeRuleForm").valid();
		 if(!rtn) return;
		 var url=__ctx+ "/platform/bpm/bpmNodeRule/save.ht";
		 var para=$('#bpmNodeRuleForm').serialize();
		 $.post(url,para,showResult);
	}
	
	
	function showResult(responseText)
	{
		var obj=new com.hotent.form.ResultMessage(responseText);
		var ruleId=$("#ruleId").val();
		if(!obj.isSuccess()){
			$.ligerDialog.err($lang.tip.msg,String.format($lang.add.fail,'<spr:message code="bpmNodeRule.rule"/>'),obj.getMessage());
			return;
		}
		//添加
		if(ruleId=="0"){
			$.ligerDialog.confirm(String.format($lang.add.success,'<spr:message code="bpmNodeRule.rule"/>'),$lang.tip.msg,function(rtn){
				if(!rtn){
					window.close();
				}
				else{
					__valid.resetForm();
					loadRuleList();
				}
			});
		}
		//更新
		else{
			$.ligerDialog.confirm(String.format($lang.add.success,'<spr:message code="bpmNodeRule.rule"/>'),$lang.tip.msg,function(rtn){
				if(!rtn){
					window.close();
				}
				else{
					loadRuleList();
				}
			});
		}
	}
	function selectScript(){
		ScriptDialog({callback:function(script){
			InitMirror.editor.insertCode(script);
		}});
	}
	
	
	//显示规则明细
	function showDetail(){
		var obj=$(this);
		var ruleId=obj.attr("rule");
		var url=__ctx + "/platform/bpm/bpmNodeRule/getById.ht?ruleId=" + ruleId;
		$.get(url,function(data) {
			var json=jQuery.parseJSON(data);
			jQuery.setFormByJson(json);
			var tmp=json.targetNode +"," + json.targetNodeName;
			$("select[name='targetNode']").val(tmp);			
			InitMirror.editor.setCode(document.getElementById("conditionCode").value);
		});
	}
	
	function getRow(ruleId,ruleName,idx){
		var className=(idx % 2==0)?"odd":"even";
		var aryRow=["<tr class='"+className+"'>",
		"<td>",
		"<a class='ruledetail' rule='"+ruleId+"'>"+ruleName+"</a>",
		"</td>",
		"<td>",
		"<a alt='"+$lang.operate.moveUp+"' href='#' class='link moveup' onclick='sortUp(this)'>&nbsp;&nbsp;&nbsp;</a>",
		"<a alt='"+$lang.operate.moveUp+"' href='#' class='link movedown' onclick='sortDown(this)'>&nbsp;&nbsp;&nbsp;</a>",
		"<a alt='"+$lang.operate.moveUp+"'  class='delrule link del' rule='"+ruleId+"'>&nbsp;&nbsp;&nbsp;</a>",
		"</td>",
		"</tr>"];
		return aryRow.join("");
	}
	//加载规则列表
	function loadRuleList(){
		var url=__ctx + "/platform/bpm/bpmNodeRule/getByDefIdNodeId.ht?actDefId=" + actDefId +"&nodeId=" + nodeId;
		url=url.getNewUrl();
		var tbodyList=$("#ruleList");
		tbodyList.empty();
		$.get(url,function(data) {
			var jsonAry=jQuery.parseJSON(data);
			for(var i=0;i<jsonAry.length;i++){
				var obj=jsonAry[i];
				var row=getRow(obj.ruleId,obj.ruleName,i);
				tbodyList.append($(row));
			}
			$("a.ruledetail").click(showDetail);
			$("a.delrule").click(delByRule);
		});
	}

	//删除规则	
	function delByRule(){
		var obj=$(this);
		var ruleId=obj.attr("rule");
		$.ligerDialog.confirm($lang.del.suredelete,$lang.tip.msg,function(rtn) {
			if(!rtn) return;
			var url=__ctx + "/platform/bpm/bpmNodeRule/del.ht?ruleId=" + ruleId;
			$.get(url,function(data) {
				var obj=new com.hotent.form.ResultMessage(data);
				if(obj.isSuccess()){
					$.ligerDialog.success($lang.del.success,$lang.tip.msg,function(){
						loadRuleList();
					});
				}
				else{
					$.ligerDialog.warn($lang.del.failure,$lang.tip.msg);
				}
			});
		});
	}
	
	//规则上移
	function sortUp(obj) {
		var thisTr = $(obj).parents("tr");
		var prevTr = $(thisTr).prev();
		if(prevTr){
			thisTr.insertBefore(prevTr);
			reSort();
		}
	};
	//重新排序
	function reSort(){
		var ruleids="";
		$("a.ruledetail").each(function(i){
			ruleids+=$(this).attr("rule") +",";
		});
		if(ruleids!="")
			ruleids=ruleids.substring(0, ruleids.length-1);
		
		var url=__ctx + "/platform/bpm/bpmNodeRule/sortRule.ht";
		var params="ruleids=" +ruleids;
		$.post(url,params,function(data){});
	}
	 
	// 规则下移
	function sortDown(obj) {
		var thisTr = $(obj).parents("tr");
		var nextTr = $(thisTr).next();
		if(nextTr){
			thisTr.insertAfter(nextTr);
			reSort();
		}
	}
	
	//更新那个bpm_node_set的IsJumpForDef字段
	function updateIsJumpForDef(ck){
		var url=__ctx+"/platform/bpm/bpmNodeRule/updateIsJumpForDef.ht";
		var params={
			nodeId:nodeId,
			actDefId:actDefId,
			isJumpForDef:ck.checked? 1:0
		};
		$.post(url,params,function(data){});
	}

</script>
<style>
html { overflow-x: hidden; }
</style>
<body>
	<div class="panel">
		<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label"><spr:message code="bpmNodeRule.title"/></span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group"><a class="link add" id="btnAdd"><span></span><spr:message code="menu.button.add"/></a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link save" id="btnSearch"><span></span><spr:message code="menu.button.save"/></a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link del" onclick="javasrcipt:window.close()"><span></span><spr:message code="menu.button.close"/></a></div>
						</div>	
					</div>
				</div>
		<div class="panel-body">
			<div id="layoutFlowRule" style="width: 100%">
				<div  style="width:100%;" position="right" title="<spr:message code='bpmNodeRule.list'/>">
					<table  cellpadding="1" class="table-grid table-list" cellspacing="1">
						<tr>
							<th><spr:message code="bpmNodeRule.ruleName"/></th><th><spr:message code="list.manage"/></th>
						</tr>
						<tbody id="ruleList">
						</tbody>
					</table>
				</div>
				<div  id="framecenter" position="center">
					<form id="bpmNodeRuleForm" method="post" action="save.ht">
						<div class="panel-detail">
							<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
								<tr>
									<th width="20%"><spr:message code="bpmNodeRule.curNode"/>:</th>
									<td>${nodeName}</td>
								</tr>
								<tr>
									<th width="20%"><spr:message code="bpmNodeRule.followNode"/>:</th>
									<td>
										<c:forEach items="${nextNodes}" var="node" varStatus="status">
											
											${node.nodeName } (${node.nodeId })
											<c:if test="${! status.last }">,</c:if>
										</c:forEach>
									</td>
								</tr>
								<tr>
									<th><spr:message code="bpmNodeRule.accordTargetNode"/></th>
									<td>
										<input type="checkbox" name="isJumpForDef" value="1" onclick="updateIsJumpForDef(this);" <c:if test="${bpmNodeSet.isJumpForDef==1}">checked="checked"</c:if> >
									</td>
								</tr>
								<tr>
									<th width="20%"><spr:message code="bpmNodeRule.ruleName"/>: <span class="required">*</span></th>
									<td><input type="text" id="ruleName" name="ruleName" size="40" value="${bpmNodeRule.ruleName}" class="inputText" /></td>
								</tr>
								<tr>
									<th width="20%"><spr:message code="bpmNodeRule.conditionCode"/>:<span class="required">*</span></th>
									<td>
										<div style="margin:8px 0;">
											
											<a href="#" id="btnScript" class="link var" title="<spr:message code='script.common'/>"><spr:message code="script.common"/></a>
											&nbsp;&nbsp;<spr:message code="script.flowVar"/>:<f:flowVar defId="${defId}" controlName="selFlowVar"></f:flowVar>
										</div>
										<textarea id="conditionCode" codemirror="true" rows="12" cols="55" name="conditionCode" >return true;</textarea>
										<br/> 
										<div style="margin:8px 0;"><spr:message code="bpmNodeRule.tip" arguments="return,true"/></div>
									</td>
								</tr>
								<tr>
									<th width="20%"><spr:message code="bpmNodeRule.targetNodeName"/>:</th>
									<td>
										<select name="targetNode">
											<c:forEach items="${activityList}" var="item">
												<optgroup label="${item.key}">
													<c:forEach items="${item.value}" var="node">
														<option value="${node.key},${node.value}">${node.value}</option>
													</c:forEach>
												</optgroup>
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<th width="20%"><spr:message code="bpmNodeRule.memo"/>:</th>
									<td>
										<textarea id="memo" rows="4" cols="40" name="memo" >${bpmNodeRule.memo}</textarea>
									</td>
								</tr>
							</table>
							<input type="hidden" id="ruleId" name="ruleId" value="${bpmNodeRule.ruleId}" />						
							<input type="hidden" name="priority" value="${bpmNodeRule.priority}" />
							<input type="hidden" name="actDefId" value="${bpmNodeRule.actDefId}" />
							<input type="hidden" name="nodeId" value="${bpmNodeRule.nodeId}" />
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

