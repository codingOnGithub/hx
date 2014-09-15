<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<title><spr:message code="title.edit"/><spr:message code="globalType"/></title>
	<f:js pre="js/lang/view/platform/system" ></f:js>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=globalType"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/Share.js"></script>
	<script type="text/javascript">
		var isAdd=${isAdd};
		var lanType = "${lanType}";
		function showRequest(formData, jqForm, options) { 
			return true;
		} 
		  
		$(function() {
			valid(showRequest,showResult);
			$("a.save").click(function(){
				var key=$("#nodeKey").val();
				var reg = new RegExp("^[0-9]*$");
				if(key!=''&&reg.test(key)){
					$.ligerDialog.error($lang_system.globalType.edit.validate_msg_NaN,$lang.tip.error);
				}else if(key.indexOf(',')!=-1){
					$.ligerDialog.error($lang_system.globalType.edit.validate_msg_nonValidChar,$lang.tip.error);
				}else{
					var typeNames = $("input[name=typeNameInput]");
					var typeNamesArr = [];
					typeNames.each(function(){
						var id = this.id;
					    var value = this.value;
					    var typeNamesObj = {};
					    if(lanType == id)
					    {
						    $("#typeName").val(value);
					    }
					    typeNamesObj.resValue=value;
					    typeNamesObj.lanType = id;
					    typeNamesArr.push(typeNamesObj);
					});
					var typeNamesStr = JSON.stringify(typeNamesArr);
					$("#typeNames").val(typeNamesStr);
					$("#globalTypeForm").submit();
				}
			});
			
			$("input[name=typeNameInput]:first").change(function(){   
				 getFullSpell($(this).val());  				   
			}); 
			
			initLocal();
		});
		
		function getFullSpell(typeName){
			if($("#nodeKey").val()!="") return;
// 			var url="${ctx}/platform/system/globalType/getPingyin.ht";
// 			var params={typeName:typeName};
// 			$.post(url,params,function(result){
// 				$("#nodeKey").val(result); 
// 			});
			
// 			var value=obj.val();
			Share.getPingyin({
				input:typeName,
				postCallback:function(data){
					$("#nodeKey").val(data.output);
				}
			});
		}
		
		function showResult(responseText){
			var obj=new com.hotent.form.ResultMessage(responseText);
			if(!obj.isSuccess()) {
				$.ligerDialog.warn(obj.getMessage(),$lang.tip.warn);
				return;
			};
			if(parent.refreshNode){//系统分类中增加分类
				parent.refreshNode(isAdd);
				if(isAdd){
					$("input[name='typeNameInput']").each(function(){
						var me = $(this);
						me.val('');
					});
					$("#nodeKey").val('');
				}
				return ;
			}
			//自定义表单中增加分类
			if(isAdd){
				$.ligerDialog.confirm($lang_system.globalType.dialog.success_msg,$lang.tip.warn,function(rtn){
					if(rtn){
						$("#typeName").val('');
						$("#nodeKey").val('');
					}
					else{
						$.ligerDialog.success($lang_system.globalType.dialog.success_msg_add,$lang.tip.success,function(){
							window.close();
						});
					}
				});
			}
			else{
				$.ligerDialog.success($lang_system.globalType.dialog.success_msg_update,$lang.tip.success,function(){
					window.close();
				});
			}
		}
		
		function initLocal(){
			if(!isAdd){
				var typeNameInput = $("#"+lanType);
				if(typeNameInput && typeNameInput.val()==''){
					typeNameInput.val('${globalType.typeName}');
				}
			}
		}
	</script>
</head>
<body>
	<div class="panel-top">
				<div class="tbar-title">
					<span class="tbar-label"><c:choose><c:when test="${isAdd}"><spr:message code="title.add"/><spr:message code="globalType"/></c:when><c:otherwise><spr:message code="title.edit"/><spr:message code="globalType"/></c:otherwise></c:choose></span>
				</div>
				<div class="panel-toolbar">  
					<div class="toolBar">
						<div class="group"><a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.save"/></a></div>
					</div>
				</div>
		</div>
		<form id="globalTypeForm" method="post" action="save.ht">
				<div class="panel-detail">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
							<c:if test="${isAdd}">
								<tr>
									<th width="20%"><spr:message code="globalType.parent"/>: </th>
									<td >${parentName}</td>
								</tr>
							</c:if>
							<tr>
								<th width="20%"><spr:message code="globalType.typeName"/>:  <span class="required">*</span></th>
								<td style="padding:2px 2px 2px 2px;">
									<div style="border:1px solid #dcdcdc;border-bottom:none;">
										<table style="width:100%;">
											<c:forEach var="global" items="${msgList}">
												<tr>
													<th>
														${global.lanMemo} :
													</th>
													<td><input type="text" id="${global.lanType}" name="typeNameInput" value="${global.lanMsg}"  class="inputText longInputText"/></td>
												</tr>
											</c:forEach>
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="globalType.nodeKey"/>:  <span class="required">*</span></th>
								<td ><input type="text" id="nodeKey" name="nodeKey" value="${globalType.nodeKey}"  class="inputText"/></td>
							</tr>
							<c:if test="${isDict}">
							<tr>
								<th width="20%"><spr:message code="globalType.type"/>:</th>
								<td>
									<input type="radio" name="type" value="0"  <c:if test="${globalType.type==0}">checked="checked"</c:if>  /><spr:message code="globalType.type.tile"/>
									<input type="radio" name="type" value="1" <c:if test="${globalType.type==1}">checked="checked"</c:if> /><spr:message code="globalType.type.tree"/>
								</td>
							</tr>
							</c:if>
					</table>
				</div>
				<input type="hidden" id="isRoot" name="isRoot" value="${isRoot}"/>
				<input type="hidden" id="parentId" name="parentId" value="${parentId}"/>
				<input type="hidden" id="typeId" name="typeId" value="${globalType.typeId}" />
				<input type="hidden" id="typeNames" name="typeNames" value=""/>
				<input type="hidden" id="typeName" name="typeName" value=""/>
		</form>
</body>
</html>