<%--
	time:2012-06-25 11:05:09
	desc:edit the 通用表单对话框
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<title><spr:message code="operator.edit"/><spr:message code="bpmFormDialog.title"/></title>
	<f:js pre="js/lang/view/platform/form" ></f:js>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/Share.js"></script>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=bpmFormDialog"></script>
	<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			if(${bpmFormDialog.id==0}){
				valid(showRequest,showResponse,1);
			}else{
				valid(showRequest,showResponse);
			}
			$("a.save").click(function() {
				var alias=$("#alias").val(),
					objname=$("#objname").val(),
					settingobj=$("#settingobj").val();
				if(alias==""){
					$.ligerDialog.warn($lang_js.form.commonDialog.aliasNull);
				}else if(/.*[\u4e00-\u9fa5]+.*$/.test(alias)){
					$.ligerDialog.warn($lang_form.bpmFormQuery.edit.alias_validate_zh);
				}else if(objname!=settingobj){
					$.ligerDialog.warn($lang_form.bpmFormDialog.notSetColumn);
				}
				else{
					$('#bpmFormDialogForm').submit();
				}
			});
			
			$("#btnSearch").click(searchObjectList);
			
			var needpage = ${bpmFormDialog.style==0} ;
			if(!needpage) {
				//树，隐藏分页TD以及合并TD
				$('#needpage').next('td').hide() ;
				$('#needpage').hide() ;
				$('#needpage').prev('td').attr('colspan',3) ;
			}
			$('#treeRadio').click(function(){
				$('#needpage').next('td').hide() ;
				$('#needpage').hide() ;
				$('#needpage').prev('td').attr('colspan',3) ;
			});
			$('#listRadio').click(function(){
				$('#needpage').show() ;
				$('#needpage').next('td').show() ;
				$('#needpage').prev('td').removeAttr('colspan') ;
			});
		});
		
		function searchObjectList(){
			
			var selList=$("#objname");
			var dsName=$("#dataSource").val();
			
			var objectname=$("#objectname").val();
			var istable=$("#istable").val();
			var url=__ctx +"/platform/form/bpmFormDialog/getByDsObjectName.ht";
			if(dsName==null || dsName==""){
				$.ligerDialog.error($lang_form.bpmFormDialog.selectDS);
				return ;
			}
			
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
		
		
		function dialog(){
			$("#selInfo").text("");
			var id=$("#id").val();
			var istable=$("#istable").val();
			var objname=$("#objname").val();
			var dataSource=$("#dataSource").val();
			var style=$("input[name='style']:checked").val();
			
			if(id==0){
				if(objname==null){
					$("#selInfo").text($lang_form.bpmFormDialog.selectTableView);
					return ;
				}
			}
			showSettingDialog(dataSource,objname,istable,style,id);
		}
		
		function showSettingDialog(dsName,objectname,istable,style,id){
			var settingobj=$("#settingobj").val(),
				fields={};
			
			if(settingobj==objectname){
				var displayField=$("#displayfield").val(),
					conditionField=$("#conditionfield").val(),
					sortField=$("#sortfield").val(),
					resultField=$("#resultfield").val();
				
				if(displayField)
					fields.displayField=displayField;
				if(conditionField)
					fields.conditionField=conditionField;
				if(resultField)
					fields.resultField=resultField;
				if(sortField)
					fields.sortField=sortField;
			}
			
			var url="setting.ht?dsName=" +dsName +"&objectName=" + objectname +"&istable=" + istable +"&style=" +style +"&id=" + id;
			var winArgs="dialogWidth=800px;dialogHeight=540px;help=0;status=0;scroll=1;center=1;resizable=1;";
			url=url.getNewUrl();
			var rtn=window.showModalDialog(url,fields,winArgs);
			if(rtn==undefined) return;
           	if(rtn.length>0){
           		 $("#settingobj").val(objectname);
           		 $("#displayfield").val(rtn[0]);
           		 $("#conditionfield").val(rtn[1]);
           		 $("#resultfield").val(rtn[2]);
           		 $("#sortfield").val(rtn[3]);
          	}
		}
	</script>
</head>
<body>
<div class="panel">
<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="operator.edit"/><spr:message code="bpmFormDialog.title"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.save"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="list.ht"><span></span><spr:message code="menu.button.back"/></a></div>
				</div>
			</div>
		</div>
		</div>
		<div class="panel-body">
				<form id="bpmFormDialogForm" method="post" action="save.ht">
					
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%"><spr:message code="bpmFormDialog.name"/>: </th>
							<td><input type="text" id="name" name="name" value="${bpmFormDialog.name}"  class="inputText" onblur="Share.getKeyName(this,alias)"/></td>
							<th width="20%"><spr:message code="bpmFormDialog.alias"/>: </th>
							<td><input type="text" id="alias" name="alias" value="${bpmFormDialog.alias}"  class="inputText" /><span id="aliasInfo" style="color:red"></span></td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="bpmFormDialog.style"/>： </th>
							<td colspan="3">
								<c:choose>
									<c:when test="${bpmFormDialog.id==0}">
										<input type="radio" name="style" value="0" checked="checked"/><spr:message code="bpmFormDialog.style.list"/>
										<input type="radio" name="style" value="1"  /><spr:message code="bpmFormDialog.style.tree"/>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${bpmFormDialog.style==0}"><spr:message code="bpmFormDialog.style.list"/></c:when>
											<c:otherwise ><spr:message code="bpmFormDialog.style.tree"/></c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="bpmFormDialog.width"/>: </th>
							<td><input type="text" id="width" name="width" value="${bpmFormDialog.width}"  class="inputText"/></td>
							<th width="20%"><spr:message code="bpmFormDialog.height"/>: </th>
							<td><input type="text" id="height" name="height" value="${bpmFormDialog.height}"  class="inputText"/></td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="bpmFormDialog.issingle"/>:
							 </th>
							<td>
								<label><input type="radio" name="issingle" value="1" <c:if test="${bpmFormDialog.issingle==1}">checked="checked"</c:if>><spr:message code="bpmFormDialog.issingle.single"/></label>
								<label><input type="radio" name="issingle" value="0" <c:if test="${bpmFormDialog.issingle==0}">checked="checked"</c:if>><spr:message code="bpmFormDialog.issingle.mult"/></label>
								
							</td>
							<th width="20%" id="needpage"><spr:message code="bpmFormDialog.needpage"/>: </th>
							<td>
								<input type="radio" name="needpage" value="0"  onclick="selsize();" <c:if test="${bpmFormDialog.needpage==0}">checked="checked"</c:if> ><spr:message code="bpmFormDialog.needpage.notPage"/>
								<input type="radio" name="needpage" value="1" onclick="selsize();" <c:if test="${bpmFormDialog.needpage==1}">checked="checked"</c:if>><spr:message code="bpmFormDialog.needpage.page"/>
								<span style="color:red;<c:if test="${bpmFormDialog.needpage==0}">display:none;</c:if>" id="spanPageSize" name="spanPageSize">
									<spr:message code="bpmFormDialog.needpage.size"/>：
									  <select id="pagesize" name="pagesize" >
									  		<option value="5" <c:if test="${bpmFormDialog.pagesize==5}">selected="selected"</c:if> >5</option>
											<option value="10" <c:if test="${bpmFormDialog.pagesize==10}">selected="selected"</c:if>>10</option>
											<option value="15" <c:if test="${bpmFormDialog.pagesize==15}">selected="selected"</c:if> >15</option>
											<option value="20" <c:if test="${bpmFormDialog.pagesize==20}">selected="selected"</c:if>>20</option>
											<option value="50" <c:if test="${bpmFormDialog.pagesize==50}">selected="selected"</c:if>>50</option>						  
									  </select>
								 </span>
								
							</td>
						</tr>
						<c:if test="${bpmFormDialog.id==0}">
							<tr>
								<th width="20%"><spr:message code="bpmFormDialog.ds"/>: </th>
								<td>
									<select id="dataSource" name="dsalias">
										<option value="LOCAL"><spr:message code="bpmFormDialog.ds.local"/> </option>
										<c:forEach items="${dsList}" var="ds">
											<option value="${ds.alias}">${ ds.name} </option>
										</c:forEach>
									</select>
								</td>
								<th width="20%"><spr:message code="bpmFormDialog.queryTable"/>: </th>
								<td>
									<select name="istable" id="istable">
										<option value="1"><spr:message code="bpmFormDialog.istable.table"/></option>
										<option value="0"><spr:message code="bpmFormDialog.istable.view"/></option>
									</select>
									<input type="text" name="objectname" id="objectname">
									<a href="#" id="btnSearch" class="link search"><spr:message code="menu.button.search"/></a>
									
								</td>
							</tr>
						</c:if>
						<tr>
							<th width="20%"><spr:message code="bpmFormDialog.fieldSet"/>: </th>
							<td colspan="3" valign="top">
								<a href="#" id="btnSetting" class="link setting" onclick="dialog()"><spr:message code="bpmFormDialog.setColumn"/></a>
								<c:choose>
									<c:when test="${bpmFormDialog.id==0}">
										<br>
										<select id="objname" name="objname" size="10" style="width:350px;">
										</select>
										<span id="selInfo" name="selInfo" style="color:red"></span>
									</c:when>
									<c:otherwise >
										<input type="hidden"  id="objname" name="objname" value="${bpmFormDialog.objname}" />
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</table>
					
					<input type="hidden" id="id" name="id" value="${bpmFormDialog.id}" />
					<input type="hidden" id="settingobj" value="${bpmFormDialog.objname}" />
					<textarea id="displayfield" name="displayfield"  style="display: none;">
						${bpmFormDialog.displayfield}
					</textarea>
					<textarea  id="conditionfield"  name="conditionfield" style="display: none;">
						${bpmFormDialog.conditionfield}
					</textarea>
					<textarea  id="resultfield"  name="resultfield" style="display: none;">
						${bpmFormDialog.resultfield}
					</textarea>
					<textarea  id="sortfield"  name="sortfield" style="display: none;">
						${bpmFormDialog.sortfield}
					</textarea>
					
				</form>
		</div>
</div>
</body>
</html>
