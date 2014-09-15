<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/commons/include/form.jsp"%>
<f:js pre="js/lang/view/platform/form" ></f:js>
<title><c:choose>
		<c:when test="${isAdd==1}"><spr:message code="operator.add"/><spr:message code="bpmFormIndex.title"/></c:when>
		<c:otherwise><spr:message code="operator.edit"/><spr:message code="bpmFormIndex.title"/></c:otherwise>
	</c:choose></title>


<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var isAdd = parseInt('${isAdd}');
		//判断是新建索引还是修改索引
		if (isAdd != 1) {//不是新建索引
			$("#dataFormSave").html("<span></span>"+$lang.button.add);
			//索引类型
			var idxType = '${index.indexType}';
			//索引引用的字段
			var idxFields=$.parseJSON('${selectedFields}');
	
			//设置索引类型列表的状态			
			$("#indexType option[value='" + idxType + "']").attr({
				'selected' : 'selected'
			});
			//设置索引字段选择列表的状态
			$("input[type='checkbox'][class='pk']").each(function() {
				var val = this.value.toUpperCase();
				val = $.trim(val);
				for (x in idxFields) {
					var field = idxFields[x].toUpperCase();
					field = $.trim(field);
					if ($.trim(val) == field) {
						$(this).attr({
							'checked' : 'checked'
						});
					}
				}
			});
			$("#dataFormSave").html("<span></span>"+$lang.button.save);
		}else{
			$("#dataFormSave").html("<span></span>"+$lang.button.add);
		}

		$("#dataFormSave").click(function() {
			var form=$('#frmIndex').form();
			var retValite=form.valid();
			if(!retValite){
				return false;
			}
			var $indexesAry = $("input[type='checkbox'][disabled!='disabled'][class='pk']:checked");

			if ($indexesAry.length == 0) {
				$.ligerDialog.warn($lang_form.index.checkedField,$lang.tip.msg);
				return false;
			}
					
			var idxName=$('#indexName').val();
			idxName=$.trim(idxName);
			if (idxName.indexOf(' ') >= 0) {
				$.ligerDialog.warn($lang_form.index.NotSpaces);
				return false;
			}

			var url = __ctx + "/platform/form/bpmFormTable/saveIndex.ht?isAdd=" + isAdd;
			$.post(url, $('#frmIndex').serialize(), function(data) {
				var obj = new com.hotent.form.ResultMessage(data);
				if (obj.isSuccess()) {
					$.ligerDialog.success(obj.getMessage(),$lang.tip.msg);
					window.close();
				} else {
					$.ligerDialog.err($lang.tip.msg,$lang.save.failure,obj.getMessage());
				}
			});
		});
	});
</script>
</head>
<body>
	<div class="panel-top">
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group">
					<a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.add"/></a>
				</div>
				<div class="l-bar-separator"></div>
				<div class="group">
					<a class="link del" href="#" onclick="window.close();"><span></span><spr:message code="menu.button.close"/></a>
				</div>
			</div>

		</div>
	</div>
	<form id="frmIndex" action="">
		<div class="panel-detail">
		<input type="hidden" id="indexId" name="indexId" />
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<th width=100><spr:message code="bpmFormIndex.indexName"/>:</th>
				<td>
					<input type="text" id="indexName" maxlength="20" name="indexName" class="inputText" value="${index.indexName }"  validate="{required:true,maxlength:25}" tipId="indexNameErr" />
					<span id="indexNameErr"></span>
					<input type="hidden" name="oldIndexName" class="inputText" value="${index.indexName }"/>
				</td>
			</tr>
			<tr>
				<th width=100><spr:message code="bpmFormIndex.indexFieldSelect"/></th>
				<td colspan="3">
					<table id="list" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
						<tr>
							<th width="5%"><spr:message code="list.check"/></th>
							<th width="10%"><spr:message code="bpmFormField.fieldName"/></th>
							<th width="10%"><spr:message code="bpmFormField.fieldDesc"/></th>
							<th width="15%"><spr:message code="bpmFormField.fieldType"/></th>
							<th width="10%"><spr:message code="bpmFormField.isQuery"/></th>
							<th width="10%"><spr:message code="bpmFormField.isFlowVar"/></th>
						</tr>
						<c:if test="${fn:length(tableFileds) == 0}">
							<tr>
								<td colspan="7"><spr:message code="search.select.none"/></td>
							</tr>
						</c:if>
						<c:forEach items="${tableFileds}" var="field" varStatus="status">
							<c:if test="${field.isHidden == 0 }">
								<tr>
									<td><input name="indexFields" class="pk" type="checkbox" value="${field.fieldName }" /></td>
									<td>${field.fieldName }</td>
									<td>${field.fieldDesc }</td>
									<td><c:if test="${field.fieldType == 'varchar'}"><spr:message code="bpmFormField.fieldType.varchar"/>(${field.charLen })</c:if> 
										<c:if test="${field.fieldType == 'number'}"><spr:message code="bpmFormField.fieldType.number"/>(${field.intLen }, ${field.decimalLen })</c:if> 
										<c:if test="${field.fieldType == 'date'}"><spr:message code="bpmFormField.fieldType.date"/></c:if> 
										<c:if test="${field.fieldType == 'clob'}"><spr:message code="bpmFormField.fieldType.clob"/></c:if></td>
									<td><c:if test="${field.isQuery == 1 }">√</c:if></td>
									<td><c:if test="${field.isFlowVar == 1 }">√</c:if></td>
								</tr>
							</c:if>
						</c:forEach>
					</table> 
					<input type="hidden" name="tableName" value="${tableName}" />
				</td>
			</tr>

		</table>
		</div>
	</form>
</body>
</html>