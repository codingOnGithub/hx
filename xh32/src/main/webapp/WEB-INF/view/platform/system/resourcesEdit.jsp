<%--
	time:2011-12-05 17:00:54
	desc:edit the 子系统资源
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<title><spr:message code="res.sub.edit"/></title>
	<f:js pre="js/lang/view/platform/system" ></f:js>
	<script type="text/javascript" src="${ctx }/js/lg/plugins/ligerComboBox.js"></script>
	<script type="text/javascript" src="${ctx }/js/lg/plugins/ligerWindow.js" ></script>
	<script type="text/javascript" src="${ctx }/js/hotent/platform/system/IconDialog.js"></script>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=resources"></script>
	<script type="text/javascript">
		var i=100;
		var ctx="${ctx}";
		var lanType = "${lanType}";
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			}
			valid(showRequest,showResponse);
			$("a.save").click(function() {
				var resNames = $("input[name=resNamesInput]");
				var resNamesArr = [];
				resNames.each
				(
				   function (index)
				   {
					   var id = this.id;
					   var value = this.value;
					   var resNamesObj = {};
					   if(lanType == id)
					   {
						   $("#resName").val(value);
					   }
					   resNamesObj.lanMsg=value;
					   resNamesObj.lanType = id;
					   resNamesObj.resId = $("#resId").val();
					   resNamesArr.push(resNamesObj);
				   }
			    );
				var resNamesStr = JSON.stringify(resNamesArr);
				$("#resNames").val(resNamesStr);
				$('#resourcesForm').submit(); 
			});
		});
		
		function showResponse(responseText){
			var json=eval("("+responseText+")");
			if(json.result==1){
				var resName=$("#resName").val();
				var isFolder=$("#isFolder").val();
				var icon=$("#icon").val();
				if(json.operate=='add'){
					parent.addResource(json.resId,resName,icon,isFolder);
					$.ligerDialog.confirm($lang_system.resources.edit.success_msg_add,$lang.tip.confirm,function(rtn){
						if(rtn){
							$("input[name='resNamesInput']").each(function(){
								$(this).val("");
							});
							$("#alias,#defaultUrl").val("");
						}
						else{
							location.href=ctx +"/platform/system/resources/get.ht?resId="+ json.resId;
						}
					});
				}
				else{
					parent.editResource(resName,icon,isFolder);
					$.ligerDialog.success($lang_system.resources.edit.success_msg_edit,$lang.tip.msg);
				}
			}
			else{
				$.ligerDialog.err($lang.tip.error,$lang_system.resources.edit.error_msg_edit,json.message);
			}
		}
		
		
		
		function selectIcon(){
			 IconDialog({callback:function(src){
				$("#icon").val(src);
				$("#iconImg").attr("src",src);
				$("#iconImg").show();
			}});
		};
		function add(){
			var aryTr=[
			'<tr>',
			'<td style="text-align: center;">',
			'<input   name="chDefaultUrl" type="radio"   onclick="setDefaultUrl(this);">', 
			'</td>',
			'<td style="text-align: center;">',
			'<input class="inputText" type="text" name="name"	style="width: 95%;" >',
			'</td>',
			'<td style="text-align: center;">',
			'<input class="inputText" type="text" name="url"	style="width: 95%;" >',
			'</td>',
			'<td style="text-align:center;">',
			'<a href="#" class="link del" onclick="singleDell(this);">'+$lang.button.del+'</a>',
			'</td>',
			'</tr>'];
			
			$("#resourcesUrlItem").append(aryTr.join(""));
			$("#notSetURL").remove();
		};
		function checkDell(){
			var trCheckeds=$("#resourcesUrlItem").find(":checkbox[name='resUrlId'][checked]");
			$.each(trCheckeds,function(i,c){
				var tr=$(c).parents('tr');
				$(tr).remove();
			});
		};
		function singleDell(obj){
			var tr=$(obj).parents('tr');
			$(tr).remove();
		};
		function setDefaultUrl(obj){
			var tr=$(obj).parents('tr');
			$("#defaultUrl").val( tr.find(":input[name='url']").val());
		};
	</script>
</head>
<body>
<form id="resourcesForm" method="post" action="save.ht">
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">
				<c:if test="${resources.resId==null }"><spr:message code="res.sub.add"/></c:if>
				<c:if test="${resources.resId!=null }"><spr:message code="res.sub.edit"/></c:if> 
				</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="res.save"/></a></div>
					<div class="l-bar-separator"></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
				<table id="resourcesTable" class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%"><spr:message code="res.res.name"/>:  <span class="required">*</span></th>
							<td style="padding:2px 2px 2px 2px;">
							<div style="border:1px solid #dcdcdc;border-bottom:none;">
								<table style="width:100%;">
									<c:forEach var="res" items="${msgList}">
										<tr>
											<th>${res.lanMemo} :</th>
											<td><input type="text" id="${res.lanType}" name="resNamesInput" value="${res.lanMsg}"  class="inputText longInputText"/></td>
										</tr>
									</c:forEach>
								</table>
							</div>
							</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="res.res.other"/>: </th>
							<td><input type="text" id="alias" name="alias" value="${resources.alias}"  class="inputText longInputText"/></td>
						</tr>
						
						<tr>
							<th width="20%"><spr:message code="res.res.pic"/>: </th>
							<td>
								<input type="hidden" id="icon" name="icon" value="${resources.icon}"  class="inputText"/>
								<img id="iconImg" alt="" src="${resources.icon}" <c:if test="${resources.icon==null}">style="display:none;"</c:if>>
								<a class="link detail" href="javascript:selectIcon();"><spr:message code="res.choose"/></a>
							</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="res.default.addr"/>: </th>
							<td><input type="text" id="defaultUrl" name="defaultUrl" style="width:400px" value="${resources.defaultUrl}"  class="inputText"/></td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="res.is.bar"/>: </th>
							<td>
								<select id="isFolder" name="isFolder">
									<option value="0" <c:if test="${resources.isFolder==0}">selected="selected"</c:if>><spr:message code="no"/></option>
									<option value="1" <c:if test="${resources.isFolder==1}">selected="selected"</c:if>><spr:message code="yes"/></option>
								</select>
							</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="res.view.menu"/>: </th>
							<td>
								<select id="isDisplayInMenu" name="isDisplayInMenu">
									<option value="0" <c:if test="${resources.isDisplayInMenu==0}">selected="selected"</c:if>><spr:message code="no"/></option>
									<option value="1" <c:if test="${resources.isDisplayInMenu==1}">selected="selected"</c:if>><spr:message code="yes"/></option>
								</select>
							</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="res.open.default"/>: </th>
							<td>
								<select id="isOpen" name="isOpen">
									<option value="0" <c:if test="${resources.isOpen==0}">selected="selected"</c:if>><spr:message code="no"/></option>
									<option value="1" <c:if test="${resources.isOpen==1}">selected="selected"</c:if>><spr:message code="yes"/></option>
								</select>
							</td>
						</tr>
						
						<tr>
							<th width="20%"><spr:message code="res.bro.sort"/>: </th>
							<td><input type="text" id="sn" name="sn" value="${resources.sn}"  class="inputText"/></td>
						</tr>
						<tr style="display: none;">
							<th width="20%"><spr:message code="res.pid"/>: </th>
							<td><input type="text" id="parentId" name="parentId" value="${resources.parentId}"  class="inputText"/></td>
						</tr>
						<tr style="display: none;">
							<th width="20%">systemId: </th>
							<td><input type="text" id="systemId" name="systemId" value="${resources.systemId}"  class="inputText"/></td>
						</tr>
							
				</table>
					<input type="hidden" id="resId" name="resId" value="${resources.resId}" />
					<input type="hidden" id="returnUrl" value="${returnUrl}" />
					<input type="hidden" id="resNames" name="resNames" value="" />
					<input type="hidden" id="resName" name="resName" value="null" />
					
		</div>
					
		<c:if test="${resources.isFolder==0 && resources.resId!=null}">
			<div class="panel-top">
				<div class="tbar-title">
					<span class="tbar-label"><spr:message code="res.res.url"/></span>
				</div>
				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group"><a onclick="add();" class="link add"><span></span><spr:message code="res.add"/></a></div>
					</div>	
				</div>
			</div>
							
			<div class="panel-body">
			
					<table id="resourcesUrlItem" class="table-grid table-list" id="0" cellpadding="1" cellspacing="1">
				   		<tr>
				   			<th width="10%"><spr:message code="res.default.url"/></th>
				   			<th width="30%"><spr:message code="res.name"/></th>
				    		<th width="50%">URL</th>
				    	
				    		<th width="10%" style="text-align: center;"><spr:message code="res.manager"/></th>
				    	</tr>
				    	<tbody>
					    	<c:forEach items="${resourcesUrlList}" var="resourcesUrlItem">
					    		<tr>
					    			<td style="text-align: center;">
					    				<input name="chDefaultUrl" type="radio"  <c:if test="${resourcesUrlItem.url==resources.defaultUrl}">checked="checked"</c:if> onclick="setDefaultUrl(this);"> 
					    			</td>
						    		<td style="text-align: center;">
					    				<input class="inputText" type="text" name="name"	style="width: 95%;" value="${resourcesUrlItem.name}">
					    			</td>
					    			<td style="text-align: center;">
					    				<input class="inputText" type="text" name="url"	style="width: 95%;" value="${resourcesUrlItem.url}" >
					    			</td>
					    			<td style="text-align: center;">
					    				<a href="#" class="link del" onclick="singleDell(this);"><spr:message code="res.del"/></a>
									</td>
					    		</tr>
					    	</c:forEach>
				    	</tbody>
				   	 </table>
				   	 <c:if test="${resourcesUrlList=='[]'}">
				   	 	<div id="notSetURL"  width="90%">
					    	<spr:message code="res.no.url"/>
					    </div>
					 </c:if>
					
			</div>
		</c:if>
					
		
</div>
</form>
</body>
</html>
