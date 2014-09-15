<%--
	time:2012-11-27 10:37:13
	desc:edit the 通用表单查询
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<f:js pre="js/lang/view/platform/form" ></f:js>
	<title><spr:message code="operator.edit"/><spr:message code="bpmFormQuery.title"/></title>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/Share.js"></script>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#bpmFormQueryForm').form();
			$("a.save").click(function() {
				var alias=$("#alias").val();
				if(alias==""){
					$.ligerDialog.warn($lang_form.bpmFormQuery.edit.alias_validate_null,$lang.tip.warn);
				}else if(/.*[\u4e00-\u9fa5]+.*$/.test(alias)){
					$.ligerDialog.warn($lang_form.bpmFormQuery.edit.alias_validate_zh,$lang.tip.warn);
				}else{
					frm.setData();
					frm.ajaxForm(options);
					if(frm.valid()){
						form.submit();
					}
				}
			});
			$("#btnSearch").click(searchObjectList);
		});
		
		function searchObjectList(){
			var selList=$("#objname");
			var dsName=$("#dataSource").val();
			var objectname=$("#objectname").val();
			var istable=$("#istable").val();
			var url=__ctx +"/platform/form/bpmFormDialog/getByDsObjectName.ht";
		
			$.ligerDialog.waitting($lang_form.bpmFormQuery.edit.waiting_msg);
			$.post(url, { dsName:dsName, objectName: objectname,istable:istable },function(data) {
				$.ligerDialog.closeWaitting();
				selList.empty();
				var success=data.success;
				if(success=='false'){
					$.ligerDialog.error($lang.tip.errorMsg);
					return;
				}
				//表的处理
				if(istable=="1"){
					var tables=data.tables;
					for(key in tables ){
						selList.append("<option title='"+tables[key]+"' value='"+ key+"'>"+ key +"("+tables[key] +")" +"</option>" );
					}
				}
				//视图的处理
				else{
					var aryView=data.views;
					for(var i=0;i<aryView.length;i++){
						var v=aryView[i];
						selList.append("<option value='"+ v+"'>"+v+"</option>" );
					}
				}
		    });
		}
		
		function selsize(){
			var isneedPage=$("input:radio[name='needpage']:checked").val();
			if(isneedPage>0){
				$("#spanPageSize").show();
			}
			else{
				$("#spanPageSize").hide();
			}
		}
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm( obj.getMessage()+","+$lang.operateTip.continueOp,$lang.tip.confirm, function(rtn) {
					if(rtn){
						this.close();
					}else{
						window.location.href = "${ctx}/platform/bpm/bpmFormQuery/list.ht";
					}
				});
			} else {
				$.ligerDialog.err($lang.tip.error,$lang_form.bpmFormQuery.edit.form_edit_fail,obj.getMessage());
			}
		}
		
		function dialog(){
			$("#selInfo").text("");
			var id=$("#id").val();
			var istable=$("#istable").val();
			var objname=$("#objname").val();
			var dataSource=$("#dataSource").val();
			
			if(id==0){
				if(objname==null){
					$("#selInfo").text($lang_form.bpmFormQuery.edit.selectTableView);
					return ;
				}
			}
			showSettingDialog(dataSource,objname,istable,id);
		}
		
		function showSettingDialog(dsName,objectname,istable,id){
			var settingobj=$("#settingobj").val(),
				fields={};
			
			if(settingobj==objectname){
				var conditionField=$("#conditionfield").val(),
					resultField=$("#resultfield").val(),
					sortField=$("#sortfield").val();
				
				if(conditionField)
					fields.conditionField=conditionField;
				if(resultField)
					fields.resultField=resultField;
				if(sortField)
					fields.sortField=sortField;
			}
			
			var url="setting.ht?dsName=" +dsName +"&objectName=" + objectname + "&istable=" + istable +"&id=" + id;
			var winArgs="dialogWidth=800px;dialogHeight=540px;help=0;status=0;scroll=1;center=1;resizable=1;";
			url=url.getNewUrl();
			var rtn=window.showModalDialog(url,fields,winArgs);
			if(rtn==undefined) return;
           	if(rtn.length>0){
           		 $("#settingobj").val(objectname);
           		 $("#conditionfield").val(rtn[1]);
           		 $("#resultfield").val(rtn[2]); 
           		 $("#sortfield").val(rtn[3]); 
          	}
		}
		function getKeyName(obj){
		    var value=$(obj).val();
		    if(!value)return false;
			Share.getPingyin({
				input:value,
				postCallback:function(data){
					var inputObj=	$("input[name='alias']");
					//当alias为空或者为中文时
					if(inputObj.val().length<1 || /.*[\u4e00-\u9fa5]+.*$/.test(inputObj.val())){
						inputObj.val(data.output);
					}
				}
			});
		}
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${bpmFormQuery.id !=null}">
			        <span class="tbar-label"><spr:message code="operator.edit"/><spr:message code="bpmFormQuery.title"/></span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><spr:message code="operator.add"/><spr:message code="bpmFormQuery.title"/></span>
			    </c:otherwise>			   
		    </c:choose>
		</div>
		<c:if test="${canReturn==0}">
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.save"/></a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span><spr:message code="menu.button.back"/></a></div>
			</div>
		</div>
		</c:if>
	</div>
	<div class="panel-body">
		<form id="bpmFormQueryForm" method="post" action="save.ht">
		
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%"><spr:message code="bpmFormQuery.name"/>: </th>
							<td><input type="text" id="name" name="name" value="${bpmFormQuery.name}"  class="inputText" onblur="getKeyName(this)"/></td>
							<th width="20%"><spr:message code="bpmFormQuery.alias"/>: </th>
							<td><input type="text" id="alias" name="alias" value="${bpmFormQuery.alias}"  class="inputText"/><span id="aliasInfo" style="color:red"></span></td>
						</tr>
						<c:if test="${bpmFormQuery.id==0}">
							<tr>
								<th width="20%"><spr:message code="bpmFormQuery.dataSource"/>: </th>
								<td>
									<select id="dataSource" name="dsalias">
										<option value="LOCAL"><spr:message code="bpmFormQuery.currentDataSource"/> </option>
										<c:forEach items="${dsList}" var="ds">
											<option value="${ds.alias}">${ ds.name}</option>
										</c:forEach>
									</select>
								</td>
								<th width="20%"><spr:message code="bpmFormQuery.queryTableView"/>: </th>
								<td>
									<select name="isTable" id="istable">
										<option value="1"><spr:message code="bpmFormQuery.queryTableView.table"/></option>
										<option value="0"><spr:message code="bpmFormQuery.queryTableView.view"/></option>
									</select>
									<input type="text" name="objectname" id="objectname">
									<a href="javascript:;" id="btnSearch" class="link search"><spr:message code="menu.button.search"/></a>
								</td>
							</tr>
						</c:if>
						<tr>
							<th width="20%"><spr:message code="bpmFormQuery.queryFieldSet"/>: </th>
							<td colspan="3" valign="top">
								<a href="javascript:;" id="btnSetting" class="link setting" onclick="dialog()"><spr:message code="bpmFormQuery.setColumn"/></a>
								<c:choose>
									<c:when test="${bpmFormQuery.id==0}">
										<br>
										<select id="objname" name="objName" size="10" style="width:350px;">
										</select>
										<span id="selInfo" name="selInfo" style="color:red"></span>
									</c:when>
									<c:otherwise >
										<input type="hidden"  id="objname" name="objName" value="${bpmFormQuery.objName}" />
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</table>
			
			<input type="hidden" id="id" name="id" value="${bpmFormQuery.id}" />
			<input type="hidden" id="settingobj" value="${bpmFormQuery.objName}" />
			<textarea  id="conditionfield"  name="conditionfield" style="display: none;">
				${bpmFormQuery.conditionfield}
			</textarea>
			<textarea  id="resultfield"  name="resultfield" style="display: none;">
				${bpmFormQuery.resultfield}
			</textarea>				
			<textarea  id="sortfield"  name="sortfield" style="display: none;">
				${bpmFormQuery.sortfield}
			</textarea>
		</form>
		
	</div>
</div>
</body>
</html>
