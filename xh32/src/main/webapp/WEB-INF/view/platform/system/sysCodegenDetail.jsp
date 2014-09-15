<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
<title><spr:message code="sysCodegen"/></title>
<f:js pre="js/lang/view/platform/system" ></f:js>
<script type="text/javascript" src="${ctx}/js/hotent/foldBox.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/absoulteInTop.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/displaytag.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerLayout.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/BpmDefinitionDialog.js"></script>
<script type="text/javascript">
	var form;
	$(function() {
		var options={};
		if(showResponse){
			options.success=showResponse;
		}
	
		form=$("#codeForm").form();
		form.ajaxForm(options);
		$("#codeDetail").ligerLayout({ 
			topHeight:70,
			leftWidth: '25%',
			height: '90%',
			InWindow: false,
			allowLeftCollapse:true,
			rightWidth:'73%',
			centerWidth:'2%',
			allowRightResize:false,
		 	allowRightCollapse:false
		});
		initTable();
		$("#dataFormSave").click(codegen);
	});
	
	
	function codegen(){
		var formDefIds=$("#formDefIds").val();
		var hasTemp=$('[name="templateId"]:checked').length;
		var hasTable=$("#codeForm").find('[name="tableId"]').length;
		var isZip=$("#isZip").attr("checked");
		if(formDefIds==''){
			$.ligerDialog.error($lang_system.sysCodegen.error_msg_customForm,$lang.tip.error);
			return ;
		}
		if(!hasTable){
			$.ligerDialog.error($lang_system.sysCodegen.error_msg_customTable,$lang.tip.error);
			return;
		}
		if(hasTemp==0){
			$.ligerDialog.error($lang_system.sysCodegen.error_msg_template,$lang.tip.error);
			return;
		}
		if(isZip){
			var path=$("#folderPath").val();
			if(path==''){
				$.ligerDialog.error($lang_system.sysCodegen.folderPath,$lang.tip.error);
				return;
			}
		}
		if(form.valid()){
			$("tr[type='append']").remove();
			$("#codeForm").submit();
		}
		
	}
	function showResponse(responseText) {
		var obj = new com.hotent.form.ResultMessage(responseText);
		if (obj.isSuccess()) {
			$.ligerDialog.success(obj.getMessage(),$lang.tip.success, function() {
				window.close();
				$("#codeForm").resetForm();
			});
		} else {
			$.ligerDialog.error(obj.getMessage(),$lang.tip.error);
		}
	}
	
	function initTable(){
		var tree=window.parent.tableTree;
		var formDefIds=[];
		try{
			var nodes=tree.getCheckedNodes(true);
			for(var i=0;i<nodes.length;i++){
				var node=nodes[i];
				var formDefId=node.formDefId;
				formDefIds[i]=formDefId;
				$.post("${ctx}/platform/form/bpmFormDef/getTableInfo.ht?formDefId="+formDefId,function(data){
					for(var i= 0,c;c=data[i++];){
						var className=$.getFirstUpper(c.tableName);
						var classVar=$.getFirstLower(c.tableName);
						var row=$("tr[type='append']").clone();
						row.removeAttr("type").removeAttr("style");
						row.find("td[name='tableDesc']").text(c.tableDesc);
						row.find("input[name='tableId']").val(c.tableId);
						row.find('input[name="className"]').val(className);
						row.find('input[name="classVar"]').val(classVar);
						$("#tableVarSet").append(row);
					}
				})
			}
			$("#formDefIds").val(formDefIds.toString());
		}catch(e){}
		
	}
	
	
	
	function selectFlow(){
		BpmDefinitionDialog({isSingle:true,showAll:1,returnDefKey:true,callback:function(defIds,subjects,defKeys){
			$("#flowName").val(subjects);
			$("#defKey").val(defKeys);
		}});
	}
</script>
<style type="text/css">
	html {height: 100%}
	body {padding: 0px;margin: 0;overflow-y: auto;overflow-x: hidden;}
	#codeDetail {width: 100%;margin: 0;padding: 0;}
	.l-layout-top {
	border-style: none none solid none;
	}
	.shortLi { min-width:120px!important;}
	.row li { min-width:200px;}
	.inputText { width:120px;}
	.label { width:93px;text-align: right}
</style>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link run" id="dataFormSave" href="#"><span></span><spr:message code="sysCodegen.gen"/></a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
		<form method="post" id="codeForm" action="codegen.ht">
		<div id="codeDetail" style="bottom: 1; top: 1">
			<div position="top">
				<ul class="row">
					<li class="shortLi"><span class="label"><spr:message code="sysCodegen.override"/>:</span><input type="checkbox" name="override" value="1" /></li>
					<li><span class="label"><spr:message code="sysCodegen.flowName"/>:</span><input type="text" id="flowName" name="flowName" readonly="readonly"  class="inputText" value="" /><input type="button" value="..." onclick="selectFlow()"/>
						<input type="hidden" name="defKey" id="defKey" value=""/>
					</li>
					<li><span class="label"><spr:message code="sysCodegen.baseDir"/>:</span><input type="text" class="inputText" name="baseDir" value="" validate="{required:true}" /></li>
					<br>
					<li class="shortLi"><span class="label"><spr:message code="sysCodegen.isZip"/>:</span><input type="checkbox" id="isZip" name="isZip" value="1" /></li>
					<li><span class="label"><spr:message code="sysCodegen.folderPath"/>:</span><input type="text" class="inputText"  id="folderPath" name="folderPath" value="" style="width:138px!important"/></li>
					<li><span class="label"><spr:message code="sysCodegen.modelName"/>:</span><input type="text" class="inputText" name="system" value="" validate="{required:true}" /></li>
				</ul>
			</div>
			<div position="left" title="<spr:message code="sysCodegen.template"/>" id="tempalteManage" style="top: 45">
				<table cellpadding="1" class="table-grid table-list" cellspacing="1" id="templates">
						<tr >
							<th><input type="checkbox" id="chkall"/></th>
							<th><spr:message code="sysCodegen.template.name"/></th>
							<th><spr:message code="sysCodegen.template.alias"/></th>
						</tr>
						<c:forEach var="template" items="${templateList}" varStatus="status">
						<tr>
							<td>
								<input type="checkbox" name="templateId" class="pk" value="${template.id}" id="templateId" >
							</td>
							<td>
								${template.templateName}
							</td>
							<td>
								${template.templateAlias}
							</td>
							<input type="hidden" name="templateName" value="${template.templateName}"/>
						</tr>
						</c:forEach>
					</table>
			</div>
			<div position="center"></div>
			<div position="right" title="<spr:message code="sysCodegen.code"/>" id="tableManage" style="top: 45">
				<table id="tableVarSet" cellpadding="1" class="table-grid table-list" cellspacing="1">
      				<tr>
      					<th>
      						<spr:message code="sysCodegen.code.customTable"/>
      					</th>
      					<th>
      						<spr:message code="sysCodegen.code.package"/>(package)
      					</th>
      					<th>
      						<spr:message code="sysCodegen.code.class"/>(class)
      					</th>
      					<th>
      						<spr:message code="sysCodegen.code.classVar"/>(classVar)
      					</th>
      				</tr>
      				<tr type="append" style="display:none">
      					<td name="tableDesc">
      					</td>
      					
      					<td>
      						<input type="text" class="inputText" name="packageName" value="" validate="{required:true}"/>
      					</td>
      					<td>
      						<input type="text" class="inputText" name="className" value="" validate="{required:true}"/>
      					</td>
      					<td>
      						<input type="text" class="inputText" name="classVar" value="" validate="{required:true}"/>
      					</td>
      					<input type="hidden" name="tableId" value=""/>
      				</tr>
      			</table>
			</div>
		</div>
		<input type="hidden" id="formDefIds" name="formDefIds" value=""/>
		</form>
		</div>
	</div>
</body>
</html>