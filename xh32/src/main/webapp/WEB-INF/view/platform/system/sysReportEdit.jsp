<%--
	time:2012-04-12 09:59:47
	desc:edit the 报表模板

--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<title><spr:message code="reportTemplate.edit.title" /></title>
	<f:link href="tree/zTreeStyle.css"></f:link>
	<f:js pre="js/lang/view/platform/system" ></f:js>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/Share.js"></script>
	<script type="text/javascript"src="${ctx}/js/hotent/platform/system/ScriptDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormUtil.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/form/CommonDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerComboBox.js"></script>
	<script type="text/javascript" src="${ctx}/js/lg/plugins/htCatCombo.js"></script>
	<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
	<script type="text/javascript">
	$(function() {
		$("a.save").click(function() {
			var rtn=$('#reportForm').form().valid();
			if(!rtn) return;
			if(!'${report.reportId}'){
				var path = $("#file").val();
				var index = path.lastIndexOf('.');
				var extName = path.substring(index+1,path.length);
				if(path==''){
					$.ligerDialog.warn($lang_system.reportTemplate.upload_validate_msg);
					return false ;
				}else if('zip'!=extName && 'jrxml'!=extName){
					$.ligerDialog.warn($lang_system.sysReport.file_validate_msg,$lang.tip.warn);
					return false ;
				}
			}
			
			//设置报表参数
			var paraInfo = $('#paraInfo tbody tr');
			var paramArr = [] ;
			for(var i=0;i<paraInfo.length;i++){
				var self = $(paraInfo[i]);
				var obj = {} ;
				obj.field = $('#field',self).text();
				obj.paraType = $('#paraType',self).text();
				obj.comment = $('#comment',self).val();
				var type = obj.defaultType = $('#defaultType',self).val();
				if(type=='0'){
					obj.defaultValue = $('#defaultValue',self).val();
				}else if(type=='1'){
					//表单输入，添加控件类型
					obj.paraCt = $('#paraCt',self).val();
					var dialogCt = $('#settingSpan',self);
					if(dialogCt.css('display')!='none'){
						//添加自定义对话框控件参数
						var dialogType = $('#dialog-type',dialogCt).val();
						var dialogParam = $('#dialog-param',dialogCt).val();
						if(!dialogType){
							$.ligerDialog.warn($lang_system.sysReport.selectDialog,$lang.tip.warn);
							return false ;
						}else if(!dialogParam){
							$.ligerDialog.warn($lang_system.sysReport.selectReturnValue,$lang.tip.warn);
							return false ;
						}
						obj.dialog = dialogType;
						obj.dialogParam = dialogParam;
					}
				}else{
					obj.defaultValue = $('#area',self).val();
				}
				paramArr.push(obj);
			}
			$('#params').val(JSON2.stringify(paramArr));
			$("#reportForm").submit();
			$.ligerDialog.waitting($lang.save.waiting,$lang.tip.msg);
		});
		$("#reportForm").ajaxForm({success:showResponse});

		function showResponse(responseText, statusText){
			$.ligerDialog.closeWaitting();
			var json=eval('('+responseText+')');
			if(json.result==1){
				if(json.message){
					$.ligerDialog.confirm($lang_system.sysReport.continueSet,$lang_system.sysReport.success_msg_add,function(result){
						if(result){
							window.location.href='edit.ht?reportId='+json.message;
						}else{
							window.location.href='list.ht';
						}
					});
				}else{
					$.ligerDialog.confirm($lang.operateTip.successContinue,$lang.tip.confirm,function(result){
						if(!result){
							window.location.href='list.ht';
						}
					});
				}
			}else{
				$.ligerDialog.error(json.message.trim());
			}
		}

		//获取自定义对话框
		getDialogs();
		$('#selectBtn').live('click',function(){
			var self = this ;
			var closestTd = $(self).closest('td');
			var type = $(this).siblings('#paraCt').val();
			switch (parseInt(type)) {
			//自定义对话框
			case 0 :
				var dialog = closestTd.find('#dialog-type');
				var dialogParam = closestTd.find('#dialog-param').val();
				var alias = dialog.val();
				if(!dialogParam){
					$.ligerDialog.warn($lang_system.sysReport.selectReturnValue,$lang.tip.warn);
					return false ;
				}
				var rtnValue = "" ;
				var temp = {};
				CommonDialog(alias,function(data){
					if(Object.prototype.toString.call((data)) == '[object Array]'){
						for(var j=0;j<data.length;j++){
							temp = data[j];
							if(temp[dialogParam])
								rtnValue+=temp[dialogParam]+',';
						}
						dialogCallBack(rtnValue.substring(0,rtnValue.length-1),"",self);
					}else{
						dialogCallBack(data[dialogParam],"",self);
					}
				});
				break;
				// 人员选择器(单选)
				case 4 :
					UserDialog({
						isSingle : true,
						callback : function(ids, names) {
							dialogCallBack(ids,names,self);
						}
					});
					break;
				// 角色选择器(单选)
				case 17 :
					RoleDialog({
						isSingle : true,
						callback : function(ids, names) {
							dialogCallBack(ids,names,self);
						}
					});
					break;
				// 组织选择器(单选)
				case 18 :
					OrgDialog({
						isSingle : true,
						callback : function(ids, names) {
							dialogCallBack(ids,names,self);
						}
					});
					break;
				// 岗位选择器(单选)
				case 19 :
					PosDialog({
						isSingle : true,
						callback : function(ids, names) {
							dialogCallBack(ids,names,self);
						}
					});
					break;
				}
			});
		if('${paramList}'){
			//构造参数表格
			var param = constructParamTable(${paramList});
			$("#paraInfo").empty().append(param);
		}
		//初始化报表分类
		initReportType()
	});
	
	function initReportType(){
		var typeId = 0;
		var glTypeList = $.parseJSON('${glTypeList}');
		if("${report.typeId}"){
			typeId = "${report.typeId}";
		}else if("${param.typeId}"){
			typeId = "${param.typeId}";
		}
		if(glTypeList && typeId!=0){
			for(var i=0;i<glTypeList.length;i++){
				if(glTypeList[i].typeId == typeId){
					$('.catComBo').val(glTypeList[i].typeName);
					$('#typeId').val(typeId);
					break;
				}
			}
		}
		//设置返回路径参数
		if(typeId!=0){
			var backUrl = $('a.link.back').attr('href');
			$('a.link.back').attr('href',backUrl+'?typeId='+typeId);
		}
	}
	
	function typeChange(obj){
		var self = $(obj);
		var selfValue = self.val();
		self.siblings().hide();
		var paraCt = self.siblings('#paraCt');
		if(selfValue!='1' && selfValue!='0'){
			// 脚本
			self.siblings('#script').show();
			return ;
		}
		paraCt.show();
		if(selfValue=='0'){
			//固定值
			self.siblings('#defaultValue').show();
			if(paraCt.val()=="1") return ;
			self.siblings('#selectBtn').show();
		}
		if(paraCt.val()=="0"){
			//自定义对话框控件
			self.siblings('#settingSpan').show();
		}
	}
	function paraChange(obj){
		var self = $(obj);
		var selfVal = self.val();
		var selectBtn = self.siblings('#selectBtn');
		self.siblings('#settingSpan').hide();
		if(selfVal=='1'){
			//单行文件
			selectBtn.hide();
		}else if(selfVal=='0'){
			//对话框
			self.siblings('#settingSpan').show();
		}
		
		if(self.siblings('#defaultType').val()!='1'){
			selectBtn.show();
		}else{
			selectBtn.hide();
		}
	}
	function dialogCallBack(ids,names,obj){
		var defaultValue=$(obj).siblings('#defaultValue');
		defaultValue.val(ids);
	}
	//选择不同的对话框
	function dialogChange(obj){
		var dia=$(obj).find("option:selected");
		var v = dia.attr("fields");
		if(v){
			var paramSelector = $(obj).siblings("#dialog-param");
			var opt = paramSelector.find("option:first-child");
			paramSelector.text('');
			//添加   请选择…… option
			paramSelector.append(opt);
			var fields = $.parseJSON(v);
			for(var i=0,f;f=fields[i++];){
				opt = $('<option value="'+f.field+'">'+f.comment+'</option>');
				paramSelector.append(opt);
			}
		}
	}
	//获取自定义对话框
	function getDialogs(){
		var url = __ctx + '/platform/form/bpmFormDialog/getAllDialogs.ht';
		$.ajax({
		    type:"get",
		    async:false,
		    url:url,
		    success:function(data){
				if (data) {
					for(var i=0,c;c=data[i++];){
						var opt = $('<option value="'+c.alias+'">'+c.name+'</option>');
						opt.attr("fields",c.resultfield);
						$("select#dialog-type").append(opt);
					}
				}
		    }
		});
	};
	function constructParamTable(params){
		var table = $("#para-txt table").clone();
		var tbody = $("tbody",table).empty();
		for(var i=0;i<params.length;i++){
			var tr = constructParamTr(params[i]);
			tbody.append(tr);
		}
		return table;
	};
	
	function constructParamTr(p){
		var tr = $("#para-txt table tbody tr").clone();
		$("[name='field']",tr).text(p.field);
		$("[name='paraType']",tr).text(p.paraType);
		$("[name='comment']",tr).val(p.comment);
		$("[name='paraCt']",tr).val(p.paraCt);
		var defaultType = $('[name=defaultType]',tr);
		defaultType.val(p.defaultType);
		typeChange(defaultType);
		$('[name=defaultValue]',tr).val(p.defaultValue);
		if(p.dialog){
			$('#settingSpan',tr).show();
			$('#dialog-type',tr).val(p.dialog)
			dialogChange($('#dialog-type',tr));
			$('#dialog-param',tr).val(p.dialogParam)
		}
		return tr;
	};
	function selectScript(obj) {
		var txtObj=$(obj).closest('div').siblings("textarea")[0];
		ScriptDialog({
			callback : function(script) {
				$.insertText(txtObj,script);
			}
		});
	};
	</script>
	<style type="text/css">
		html,body{height:100%;width:100%; overflow: hidden;}
		.para-info-table th, .para-info-table td{text-align: center;height: 32px;}
		select{width:90px;}
	</style>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
		        <c:when test="${not empty report.reportId}">
		            <span class="tbar-label"><spr:message code="reportTemplate.edit.span.edit" /></span>
		        </c:when>
		        <c:otherwise>
		            <span class="tbar-label"><spr:message code="reportTemplate.edit.span.add" /></span>
		        </c:otherwise>
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group">
				    <a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.save" /></a>
				</div>
				<div class="l-bar-separator"></div>
				<div class="group">
					<a class="link back" href="list.ht"><span></span><spr:message code="menu.button.back" /></a>
				</div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<form id="reportForm" method="post" action="save.ht" enctype="multipart/form-data">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="15%"><spr:message code="sysReport.title" />:  <span class="required">*</span></th>
					<td>
						<input type="text" id="title" name="title" validate="{required:true}" tipId="errorTitle"
							value="${report.title}" class="inputText"/><label id="errorTitle"></label>
					</td>
				</tr>
				<tr>
					<th><spr:message code="sysReport.dsName" />:  <span class="required">*</span></th>
					<td>
						<select name="dsName" style="width:115px;">
							<option value=""><spr:message code="sysReport.nullDS" /></option>
							<c:forEach var="item" items="${dsList}">
								<option value="${item.alias }" <c:if test="${report.dsName eq item.alias}">selected="selected"</c:if>>${item.alias }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th><spr:message code="reportTemplate.type" />: </th>
					<td>
						<input class="catComBo" catKey="REPORT_TYPE" valueField="typeId" catValue="" name="typeName" height="150" width="150"/>
					</td>
				</tr>
				<c:if test="${not empty paramList}">
				<tr>
					<th><spr:message code="sysReport.parameters" />： </th>
					<td id="paraInfo">
					</td>
				</tr>
				</c:if>
				<tr>
					<th><spr:message code="sysReport.descp" />: </th>
					<td>
						<textarea rows="5" cols="60" id="descp" name="descp"
							class="textarea">${report.descp}</textarea>
					</td>
				</tr>
				<tr>
					<th><spr:message code="reportTemplate.reportLocation" />:  <span class="required">*</span></th>
					<td>
					<c:choose>
						<c:when test="${empty report.reportId}">
							<input id="file" name="file" style="width: 200px;" class="inputText" type="file" 
								value="${report.filePath}" />
						</c:when>
						<c:otherwise>
							<label>${report.filePath}</label>
						</c:otherwise>
					</c:choose>
					</td>
				</tr>
			</table>
			<input type="hidden" name="params" value="[{}]" id="params">
			<input type="hidden" name="reportId" value="${report.reportId}" />
			<input type="hidden" name="filePath" value="${report.filePath}" />
		</form>
	</div>
	
	<div class="hidden">
		<div id="para-txt">
			<table class="table-detail para-info-table" cellpadding="0" cellspacing="0" border="0">
				<thead>
					<tr>
						<th width="15%"><spr:message code="sysReport.param.info" /></th>
						<th width="15%"><spr:message code="sysReport.param.type" /></th>
						<th width="20%"><spr:message code="sysReport.param.desc" /></th>
						<th width="50%"><spr:message code="sysReport.param.value" /></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><span name="field" id="field"></span></td>
						<td><span name="paraType" id="paraType"></span></td>
						<td>
							<input type="text" name="comment" id="comment" validate="{required:true}" tipId="errorComment"/>
							<label id="errorComment"></label>
						</td>
						<td style="text-align:left;">
							<select name="defaultType" id="defaultType" onchange="typeChange(this)">
								<option value="0"><spr:message code="script.fixedValue" /></option>
								<option value="1"><spr:message code="script.formInput" /></option>
								<option value="2"><spr:message code="script.script" /></option>
							</select>
							<div style="display:none;width:200px" id="script">
								<div name='btnScript'>
        							<a href='#' class='link var' title='<spr:message code="script.common" />' onclick='selectScript(this)'><spr:message code="script.common" /></a>
       							</div>
       							<textarea cols='20' rows='3' id="area" name="defaultValue"></textarea>
							</div>
							<input type="text" style="width:100px;" value="" id="defaultValue" name="defaultValue">
							<select name="paraCt" id="paraCt" onchange="paraChange(this)">
								<option value="1"><spr:message code="conditionScript.argument.control.singleInput" /></option>
								<option value="4"><spr:message code="conditionScript.argument.control.user" /></option> 
								<option value="17"><spr:message code="conditionScript.argument.control.role" /></option>
								<option value="18"><spr:message code="conditionScript.argument.control.org" /></option>
								<option value="19"><spr:message code="conditionScript.argument.control.pos" /></option> 
								<option value="0"><spr:message code="sysReport.customDialog" /></option>
							</select>
							<span style="display: none;" id="settingSpan">
								<select id="dialog-type" name="dialog-type" onchange="dialogChange(this)" style="width:100px;">
									<option value=""><spr:message code="sysReport.selectDialog" />……</option>
								</select>
								<select id="dialog-param" name="dialog-param" style="width:100px;">
									<option value=""><spr:message code="sysReport.selectReturnValue" />……</option>
								</select>
							</span>
							<input type="button" id="selectBtn" value="…" style="display:none;width:20px;" />
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
</body>
</html>
