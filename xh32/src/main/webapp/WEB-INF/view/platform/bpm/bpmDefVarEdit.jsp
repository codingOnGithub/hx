<%--
	time:2011-12-01 16:50:08
	desc:edit the 流程变量定义
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
<title><spr:message code="bpmDefVar.title"/></title>
<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=bpmDefVar"></script>
	<script type="text/javascript">
	var defId="${defId}";
	var actDeployId="${actDeployId}";
	var actDefId="${actDefId}";
	function showRequest(formData, jqForm, options) { 
		return true;
	} 
	$(function() {
		valid(showRequest,function(){});
		change();
		$("a.save").click(save);
	});
	function change(){
		var s= $("#varScope").val();
		$("#n").each(function(i,o){
			if(s=='task'){
				$(".sub").eq(i).show();
			}else{
				$(".sub").hide();
			}
		});
	}
	function save(){
		 var rtn=$("#bpmDefVarForm").valid();
   		 if(!rtn) return;
		 var url=__ctx+ "/platform/bpm/bpmDefVar/save.ht";
   		 var para=$('#bpmDefVarForm').serialize();
   		 $.post(url,para,showResult);
	}
	function showResult(responseText)
   	{
   		var obj=new com.hotent.form.ResultMessage(responseText);
   		if(!obj.isSuccess()){
   			$.ligerDialog.err($lang.tip.msg,$lang.save.failure,obj.getMessage());
   			return;
   		}else{
   			$.ligerDialog.confirm(obj.getMessage()+','+$lang.operateTip.continueOp,$lang.tip.msg,function(rtn){
   				if(!rtn){
   					window.close();
   				}
   				else{
   					valid.resetForm();
   				}
   			});
   		}
   	}
</script>
<style type="text/css">
        .sub{display:none;}
</style>
</head>
<body>
    <div class="panel">
		<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label">
						<c:if test="${bpmDefVar.varId==null }"><spr:message code="menu.button.add"/><spr:message code="bpmDefVar.title"/></c:if>
						<c:if test="${bpmDefVar.varId!=null }"><spr:message code="menu.button.edit"/><spr:message code="bpmDefVar.title"/></c:if>
						</span>
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

				<form id="bpmDefVarForm" method="post" action="save.ht">
					<div class="panel-detail">
						<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th width="20%"><spr:message code="bpmDefVar.varName"/>:</th>
								<td><input type="text" id="varName" name="varName" value="${bpmDefVar.varName}"  class="inputText"/>
								</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="bpmDefVar.varKey"/>: </th>
								<td><input type="text" id="varKey" name="varKey" value="${bpmDefVar.varKey}"  class="inputText"/></td>
							</tr>		
							<tr>
								<th width="20%"><spr:message code="bpmDefVar.varDataType"/>: </th>
								<td>
									<select name="varDataType"  >
										<option value="varchar" <c:if test="${bpmDefVar.varDataType eq 'varchar'}">selected='selected'</c:if>><spr:message code="bpmDefVar.string"/>(varchar)</option>
										<option value="number" <c:if test="${bpmDefVar.varDataType eq 'number'}">selected='selected'</c:if>><spr:message code="bpmDefVar.number"/>(number)</option>
										<option value="date" <c:if test="${bpmDefVar.varDataType eq 'date'}">selected='selected'</c:if>><spr:message code="bpmDefVar.date"/>(date)</option>
									</select>
								</td>
							</tr>					
							<tr class="sub" id="n">
							<th width="20%" ><spr:message code="bpmDefVar.nodeName"/>: </th>
								<td>
								<select id="nodeId" name="nodeId" style="width:40%;">
								<c:forEach items="${nodeMap}" var="node">
								<option value="${node.key},${node.value}"  <c:if test="${node.value==bpmDefVar.nodeName}">selected='selected'</c:if>>${node.value}</option>
								</c:forEach>
								</select>
								</td>
							</tr>
						</table>
						<input type="hidden" name="varId"  id="varId" value="${bpmDefVar.varId}" />
						<input type="hidden" id="actDeployId" name="actDeployId" value="${actDeployId}"/>
						<input type="hidden" id="defId" name="defId" value="${defId}"  />
					</div>
				</form>
			</div>
		</div>
</body>
</html>
