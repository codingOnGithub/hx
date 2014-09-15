<%--
	time:2012-11-27 10:37:13
	desc:edit the 通用表单查询
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 ztree</title>
	<%@include file="/commons/include/form.jsp" %>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/Share.js"></script>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#sysZtreeForm').form();
			$("a.save").click(function() {
				var alias=$("#alias").val();
				if(alias==""){
					$.ligerDialog.warn("别名不能为空");
				}else if(/.*[\u4e00-\u9fa5]+.*$/.test(alias)){
					$.ligerDialog.warn("别名不能为中文");
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
		
			$.ligerDialog.waitting('正在查询，请等待...');
			$.post(url, { dsName:dsName, objectName: objectname,istable:istable },function(data) {
				$.ligerDialog.closeWaitting();
				selList.empty();
				var success=data.success;
				if(success=='false'){
					$.ligerDialog.error("出错了!");
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
				$.ligerDialog.confirm( obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						this.close();
					}else{
						window.parent.location.href = "${ctx}/demo/ztree/sysZtree/manage.ht";
					}
				});
			} else {
				$.ligerDialog.err("出错信息","编辑通用表单失败",obj.getMessage());
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
					$("#selInfo").text("请先选择数据库表");
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
			fields.alias=$("#alias").val();
			fields.tablename=objectname;
			var url="setting.ht?dsName=" +dsName +"&objectName=" + objectname + "&istable=" + istable +"&id=" + id;
			var winArgs="dialogWidth=800px;dialogHeight=540px;help=0;status=0;scroll=1;center=1;resizable=1;";
			url=url.getNewUrl();
			var backParams=window.showModalDialog(url,fields,winArgs);
			if(backParams==undefined) return;
           	if(backParams){
           		for(var i in backParams){
           			console.info(backParams[i]+"::"+i);
           			$("input[name='"+i+"']").val(backParams[i]);
           		}
           		$("#tablename").val(objectname);
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
			    <c:when test="${sysZtree.typeid !=null}">
			        <span class="tbar-label">编辑通用表单查询</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label">添加通用表单查询</span>
			    </c:otherwise>			   
		    </c:choose>
		</div>
		<c:if test="${canReturn==0}">
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="#"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
		</c:if>
	</div>
	<div class="panel-body">
		<form id="sysZtreeForm" method="post" action="save.ht">
		
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%">树名称: </th>
							<td><input type="text" id="typename" name="typename" value="${sysZtree.typename}"  class="inputText" onblur="getKeyName(this)"/></td>
							<th width="20%">树别名: </th>
							<td><input type="text" id="alias" name="alias" value="${sysZtree.alias}"  class="inputText"/><span id="aliasInfo" style="color:red"></span></td>
						</tr>
						<c:if test="${sysZtree.typeid==0}">
							<tr>
								<th width="20%">数据源: </th>
								<td>
									<select id="dataSource" name="dsname">
										<option value="LOCAL">本地数据源 </option>
										<c:forEach items="${dsList}" var="ds">
											<option value="${ds.alias}">${ ds.name}</option>
										</c:forEach>
									</select>
								</td>
								<th width="20%">查询表(视图): </th>
								<td>
									<select name="istable" id="istable">
										<option value="1">表</option>
										<option value="0">视图</option>
									</select>
									<input type="text" name="objectname" id="objectname">
									<a href="javascript:;" id="btnSearch" class="link search">查询</a>
								</td>
							</tr>
						</c:if>
						<c:if test="${sysZtree.typeid!=0}">
							<input type="hidden" name="dsname" id="dsname" value="${sysZtree.dsname}">
							<input type="hidden" name="istable" id="istable" value="${sysZtree.istable}">
						</c:if>
						<tr>
							<th width="20%">查询字段设置: </th>
							<td colspan="3" valign="top">
								<a href="javascript:;" id="btnSetting" class="link setting" onclick="dialog()">设置列</a>
								<c:choose>
									<c:when test="${sysZtree.typeid==0}">
										<br>
										<select id="objname" name="objectname" size="10" style="width:350px;">
										</select>
										<span id="selInfo" name="selInfo" style="color:red"></span>
									</c:when>
									<c:otherwise >
										<input type="text"  id="objname" name="objectname" value="${sysZtree.tablename}" />
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</table>
			
			<input type="hidden" id="tablename" name="tablename" value="${sysZtree.tablename}" />
			<input type="hidden" id="idkey" name="idkey" value="${sysZtree.idkey}" />
			<input type="hidden" id="namekey" name="namekey" value="${sysZtree.namekey}" />
			<input type="hidden" id="pidkey" name="pidkey" value="${sysZtree.pidkey}" />
			<input type="hidden" id="roottitle" name="roottitle" value="${sysZtree.roottitle}" />
			<input type="hidden" id="expandslevel" name="expandslevel" value="${sysZtree.expandslevel}" />
			<input type="hidden" id="rootpid" name="rootpid" value="${sysZtree.rootpid}" />
			<input type="hidden" id="dataparams" name="dataparams" value='${sysZtree.dataparams}' />
			<input type="hidden" id="id" name="typeid" value="${sysZtree.typeid}" />
			<input type="hidden" id="callbacks" name="callbacks" value='${sysZtree.callbacks}' />
			<input type="hidden" id="asyncs" name="asyncs" value='${sysZtree.asyncs}' />
			<input type="hidden" id="checks" name="checks" value='${sysZtree.checks}' />
			<input type="hidden" id="datas" name="datas" value='${sysZtree.datas}' />
			<input type="hidden" id="edits" name="edits" value='${sysZtree.edits}' />
			<input type="hidden" id="views" name="views" value='${sysZtree.views}' />
		</form>
		
	</div>
</div>
</body>
</html>
