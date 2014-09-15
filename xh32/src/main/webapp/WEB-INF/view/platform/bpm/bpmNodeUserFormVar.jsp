<%@page pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
	<head>
		<%@include file="/commons/include/form.jsp" %>
		<title><spr:message code="bpmNodeUser.userType.formVar"/></title>
		<f:js pre="js/lang/view/platform/bpm" ></f:js>
		<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
		<script type="text/javascript" src="${ctx}/js/hotent/platform/form/CommonDialog.js"></script>
		<script type="text/javascript" src="${ctx}/js/util/easyTemplate.js"></script>
		<script type="text/javascript">
			$(function(){
				$("input[name='varType']").click(function(){
					checkType(this);
				});
			});
			
			//点击人员参照单选项时
			function checkType(ck){
				var objTr=$(ck).closest("tr");
				$("select", objTr).attr('disabled',false);
				$('select', objTr.siblings('tr')).attr('disabled',true);
			}
			
			function save(){
				var objType=$("[name='varType']:checked");
				if(objType.length==0){
					$.ligerDialog.warn($lang_bpm.formVar.warn_select_varType,$lang.tip.msg);
					return ;
				}
				var varType=objType.val();
				var varTypeName=objType.attr("memo");
				var objTr=objType.closest("tr");
				var objSelect=$("select[name='varName'] option",objTr);
				var len=objSelect.length;
				if(len==0){
					$.ligerDialog.warn($lang_bpm.formVar.warn_no_formVar,$lang.tip.msg);
					return ;
				}
				var objSelect=$("select[name='varName'] option:selected",objTr);
				len=objSelect.length;
				if(len==0){
					$.ligerDialog.warn($lang_bpm.formVar.warn_no_select_formVar,$lang.tip.msg);
					return ;
				}
				
				var varName=objSelect.get(0).value;
				var varText=objSelect.get(0).text;
				var obj={};
				var userType,userText;
				if(varType=='6') {
					var userSelect = $("select[name='userType'] option:selected",objTr);
					userType = userSelect.get(0).value;
					userText = userSelect.get(0).text;
					obj.json="{type:"+ varType +",userType:" + userType + ",varName:\""+varName+"\"}";
					obj.description=varTypeName +","+String.format($lang_bpm.formVar.varType,userText)+","+String.format($lang_bpm.formVar.varName,varText );
				}
				else {
					obj.json="{type:"+ varType +",varName:\""+varName+"\"}";
					obj.description=varTypeName +","+String.format($lang_bpm.formVar.varName,varText );
				}
				window.returnValue= obj;
				window.close();
			}
			
		</script>
	</head>
	
<body style="overflow: hidden;">
	<div class="panel">
		<div class="hide-panel">
			<div class="panel-top">
				<div class="tbar-title">
				    <span class="tbar-label"><spr:message code="bpmNodeUser.userType.formVar"/></span>
				</div>
				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group"><a class="link save" id="dataFormSave" onclick="save();" href="#"><span></span><spr:message code="menu.button.save"/></a></div>
						<div class="l-bar-separator"></div>
						<div class="group"><a class="link close" onclick="window.close()" href="#"><span></span><spr:message code="menu.button.close"/></a></div>
					</div>
				</div>
			</div>
		</div>
		<div style="text-align: center;padding-top: 10px;">
			<fieldset>
				<legend><b><spr:message code="bpmNodeUser.formVar.varType"/></b></legend>
				<table class="table-grid" width="90%">
					<tr>
						<th style="width: 20px"></th>
						<th><spr:message code="bpmNodeUser.formVar.executorFormType"/></th>
						<th><spr:message code="bpmNodeMessage.formVar"/></th>
						
					</tr>
					<tr>
						<td><input type="radio" name="varType" value="1" memo="<spr:message code='bpmNodeUser.formVar.executorFormType.user'/>" <c:if test="${varType==1}">checked="checked"</c:if> /></td> 
						<td><spr:message code="bpmNodeUser.formVar.executorFormType.user"/></td>
						<td>
							<select name="varName" <c:if test="${varType!=1}">disabled="disabled"</c:if> >
								<c:forEach items="${userVarList}" var="item">
									<option value="${item.fieldName}" <c:if test="${varName==item.fieldName}">selected="selected"</c:if> >${item.fieldDesc}</option>	
								</c:forEach>
							</select>
						</td>
						
					</tr>
					<tr>
						<td><input type="radio" name="varType" value="2"  memo="<spr:message code='bpmNodeUser.formVar.executorFormType.org'/>" <c:if test="${varType==2}">checked="checked"</c:if> /></td> 
						<td><spr:message code="bpmNodeUser.formVar.executorFormType.org"/></td>
						<td>
							<select name="varName" <c:if test="${varType!=2}">disabled="disabled"</c:if>>
								<c:forEach items="${orgVarList}" var="item">
									<option value="${item.fieldName}" <c:if test="${varName==item.fieldName}">selected="selected"</c:if> >${item.fieldDesc}</option>	
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td><input type="radio" name="varType" value="3"  memo="<spr:message code='bpmNodeUser.formVar.executorFormType.orgCharge'/>" <c:if test="${varType==3}">checked="checked"</c:if>/></td> 
						<td><spr:message code="bpmNodeUser.formVar.executorFormType.orgCharge"/></td>
						<td>
							<select name="varName" <c:if test="${varType!=3}">disabled="disabled"</c:if> >
								<c:forEach items="${orgVarList}" var="item">
									<option value="${item.fieldName}" <c:if test="${varName==item.fieldName}">selected="selected"</c:if> >${item.fieldDesc}</option>	
								</c:forEach>
							</select>
						</td>
						
					</tr>
					<tr>
						<td><input type="radio" name="varType" value="4"  memo="<spr:message code='bpmNodeUser.formVar.executorFormType.role'/>"  <c:if test="${varType==4}">checked="checked"</c:if>/></td> 
						<td><spr:message code="bpmNodeUser.formVar.executorFormType.role"/></td>
						<td>
							<select name="varName" <c:if test="${varType!=4}">disabled="disabled"</c:if>>
								<c:forEach items="${roleVarList}" var="item">
									<option value="${item.fieldName}" <c:if test="${varName==item.fieldName}">selected="selected"</c:if> >${item.fieldDesc}</option>
								</c:forEach>
							</select>
						</td>
						
					</tr>
					<tr>
						<td><input type="radio" name="varType" value="5" memo="<spr:message code='bpmNodeUser.formVar.executorFormType.pos'/>"  <c:if test="${varType==5}">checked="checked"</c:if>/></td> 
						<td><spr:message code="bpmNodeUser.formVar.executorFormType.pos"/></td>
						<td>
							<select name="varName" <c:if test="${varType!=5}">disabled="disabled"</c:if>>
								<c:forEach items="${posVarList}" var="item">
									<option value="${item.fieldName}" <c:if test="${varName==item.fieldName}">selected="selected"</c:if> >${item.fieldDesc}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td><input type="radio" name="varType" value="6" memo="<spr:message code='bpmNodeUser.formVar.Comvar'/>"  <c:if test="${varType==6}">checked="checked"</c:if>/></td> 
						<td><spr:message code="bpmNodeUser.formVar.Comvar"/></td>
						<td>
							<select name="userType" <c:if test="${varType!=6}">disabled="disabled"</c:if>>
								<option value="1" <c:if test="${userType eq '1'}">selected='selected'</c:if> ><spr:message code="bpmNodeUser.formVar.executorFormType.user"/></option>
								<option value="2" <c:if test="${userType eq '2'}">selected='selected'</c:if> ><spr:message code="bpmNodeUser.formVar.executorFormType.org"/></option>
								<option value="3" <c:if test="${userType eq '3'}">selected='selected'</c:if> ><spr:message code="bpmNodeUser.formVar.executorFormType.orgCharge"/></option>
								<option value="4" <c:if test="${userType eq '4'}">selected='selected'</c:if> ><spr:message code="bpmNodeUser.formVar.executorFormType.role"/></option>
								<option value="5" <c:if test="${userType eq '5'}">selected='selected'</c:if> ><spr:message code="bpmNodeUser.formVar.executorFormType.pos"/></option>
							</select>
							<select name="varName" <c:if test="${varType!=6}">disabled="disabled"</c:if>>
								<c:forEach items="${otherList}" var="item">
									<option value="${item.fieldName}" <c:if test="${varName==item.fieldName}">selected="selected"</c:if> >${item.fieldDesc}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
			</fieldset>
		</div>
		
	</div>
</body>
</html>