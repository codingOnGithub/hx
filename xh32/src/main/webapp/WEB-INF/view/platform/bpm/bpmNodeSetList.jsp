<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code='bpmDefinition.form.title'/></title>
<f:js pre="js/lang/view/platform/bpm" ></f:js>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerWindow.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FormDialog.js"></script>

<script type="text/javascript">
	//流程定义ID
	var actDefId="${bpmDefinition.actDefId}";
	
	function save(){
		var type=$("#bpmFormType").val();
		var isEmpty=isEmptyForm();
		if(!isEmpty){
			if(type==-1){
				$.ligerDialog.error($lang_bpm.nodeSet.actForm,$lang.tip.msg);
				return;
			}else if(type==0){
				var bpmFormKey=$("#bpmFormKey").val();
				if(!bpmFormKey||bpmFormKey==0){
					$.ligerDialog.error($lang_bpm.nodeSet.actForm,$lang.tip.msg);
					return;
				}
			}else if(type==1){
				var bpmFormUrl=$("#bpmFormUrl").val();
				if(!bpmFormUrl||bpmFormUrl==''){
					$.ligerDialog.error($lang_bpm.nodeSet.actForm,$lang.tip.msg);
					return;
				}
			}
		}
		$('#dataForm')[0].submit();
	}
	
	function isEmptyForm(){
		var isEmpty=true;
		var globalFormObj=$("div[name='globalFormUrl']");
		var globalUrlObj=globalFormObj.find('input[name="formUrlGlobal"]');
		var globalDetailObj=globalFormObj.find('input[name="detailUrlGlobal"]');
		var globalUrl=(globalUrlObj!=undefined)?globalUrlObj.val():"";
		var globalDetailUrl=(globalDetailObj!=undefined)?globalDetailObj.val():"";
		var globalFormKey=($("#defaultFormKey")!=undefined)?$("#defaultFormKey").val():0;
		if(globalUrl!=""||globalFormKey!=0||globalDetailUrl!=""){
			isEmpty=false;
		}
		$("div[name='nodeForm']").each(function(){
			var formKeyObj=$(this).find('input[name="formKey"]');
			var formKey=(formKeyObj!=undefined)?formKeyObj.val():0;
			var formUrlObj=$(this).find('input[name="formUrl"]');
			var formUrl=(formUrlObj!=undefined)?formUrlObj.val():"";
			var formDetailObj=$(this).find('input[name="detailUrl"]');
			var formDetail=(formDetailObj!=undefined)?formDetailObj.val():"";
			if(formKey!=0||formUrl!=""||formDetail!=""){
				isEmpty=false;
			}
		});
		return isEmpty;
	}
	
	//选择全局表单
	function selectGlobalForm() {
		FormDialog({
			callback : function(ids, names, tableId) {
				var globalFormObj=$("div[name='globalForm']");
				var bpmFormObj=$("div[name='bpmForm']");
				
				$("select[name='bpmFormType']").val("0");
				bpmFormObj.show();
				$("div.url_bpmForm").hide();
				
				$("input.formKey",globalFormObj).val(ids);
				$("input.formKey",bpmFormObj).val(ids);
				$("input.formDefName",globalFormObj).val(names);
				$("input.formDefName",bpmFormObj).val(names);
				//给表单添加 超链接，使之能看到表单明细
				var namesUrl="<a target='_blank' href="+__ctx+"/platform/form/bpmFormHandler/edit.ht?formDefId="+ids+" >"+names+"</a>";
				$("span[name='spanForm']",globalFormObj).html(namesUrl);
				$("span[name='spanForm']",bpmFormObj).html(namesUrl);
			}
		})
	}
	
	//选择业务表单
	function selectBpmForm(obj) {
		FormDialog({
			callback : function(ids, names, tableId) {
				var tdObj=$(obj).parent();
				$("input.formKey",tdObj).val(ids);
				$("input.formDefName",tdObj).val(names);
				//给表单添加 超链接，使之能看到表单明细
				var namesUrl="<a target='_blank' href="+__ctx+"/platform/form/bpmFormHandler/edit.ht?formDefId="+ids+" >"+names+"</a>";
				$("span[name='spanForm']",tdObj).html(namesUrl);
			}
		})
	}
	
	//选择节点表单
	function selectNodeForm(obj) {
		FormDialog({
			callback : function(ids, names, tableId) {
				var tdObj=$(obj).parent();
				$("input.formKey",tdObj).val(ids);
				$("input.formDefName",tdObj).val(names);
				$("input.tableId",tdObj).val(tableId);
				//给表单添加 超链接，使之能看到表单明细
				var namesUrl="<a target='_blank' href="+__ctx+"/platform/form/bpmFormHandler/edit.ht?formDefId="+ids+" >"+names+"</a>";
				$("span[name='spanForm']",tdObj).html(namesUrl);
				// 是否显示子表授权功能
				$.ajax({
					type : "POST",
					url : __ctx + "/platform/form/bpmFormDef/isSubTable.ht",
					data : {formKey:ids},
					dataType : "json",
					success : function(res) {
						var result= eval('(' + res + ')');
						if(result.success && obj.id == 'subNodeSel'){
							$(obj).siblings("a.grant").show();
						}else{
							$(obj).siblings("a.grant").hide();
						}
					},
					error : function(res) {
						
					}
				});
			}
		})
	}
	//全局表单授权
	function authorizeDialog(aurl){
		var url=aurl;
		var winArgs="dialogWidth=850px;dialogHeight=600px;help=0;status=no;center=yes;resizable=no;";
		url=url.getNewUrl();
		window.showModalDialog(url,"",winArgs);
	}
	
	//清除表单
	function clearForm(obj){
		var btn=$(obj);
		var tdObj=btn.parent();
		$("input.formKey",tdObj).val('');
		$("input.formDefName",tdObj).val('');
		$("span[name='spanForm']",tdObj).text('');
		$(obj).siblings("a.grant").hide();
	}
	
	//表单授权
	function authorize(obj,nodeId){
		var btn=$(obj);
		var tdObj=btn.parent();
		var objDefId=$("input.formKey",tdObj);
		if(objDefId.val()==""||objDefId.val()==0){
			$.ligerDialog.warn($lang_bpm.nodeSet.selectForm,$lang.tip.msg);
			return;
		}
		var formKey=objDefId.val();
	
		var url= __ctx + '/platform/form/bpmFormDef/rightsDialog.ht?actDefId=' +actDefId+'&nodeId=' + nodeId +'&formKey=' + formKey;
	   if(nodeId.length>0){
			var oldformKey=$("input.oldFormKey",tdObj).val();
			if(oldformKey!=formKey)
				url+='&isAlert=true'
		}
		authorizeDialog(url,nodeId,formKey);
	}
	
	// 弹出子表授权脚本填写页面
	function subDataGrant(obj,nodeId){
		var btn=$(obj);
		var tdObj=btn.parent();
		var objDefId=$("input.formKey",tdObj);
		var formKey=objDefId.val();
		var tableId = $("input.tableId",tdObj).val();
		var url= __ctx + '/platform/form/bpmFormDef/subRightsDialog.ht?actDefId=' 
			+actDefId+'&nodeId=' + nodeId +'&formKey=' + formKey+'&tableId='+tableId;
		authorizeDialog(url,nodeId,formKey);
	}

	
	
	$(function(){
		
		$('#ckJumpType').on('click',function(){
			$('.jumpType').attr('checked',this.checked);
		});
		
		$('#ckHidenOption').on('click',function(){
			var checked=this.checked;
			$('.hideOption').attr('checked',checked);
		});
		
		$('#ckHidenPath').on('click',function(){
			var checked=this.checked;
			$('.hidePath').attr('checked',checked);
		});
		
		$('#existSubTable').each(function(){
			var obj = $(this);
			if(obj.val()==0){
				obj.siblings("a.grant").hide();
			}
		});
			
		
		//处理表单类型
		handFormType();
		//验证handler
		validHandler();
		
		showHelper();
		
		//是否默认选中  隐藏执行路径
		var isNew = '${isNew}';
		if(isNew=='yes'){             //没有绑定表单时都要默认选中  隐藏执行路径
			$('#ckHidenPath').attr('checked',true);
			$('#ckHidenPath').click();
		}
	});
	
	function handFormType(){
		//业务表单
		$("select[name='bpmFormType']").change(function(){
			var value=$(this).val();
			var nodeId=$(this).parents(".formBox").find("#nodeId").val();
			if(value==-1){
				$("#formBox_"+nodeId).hide();
				$(".url_"+nodeId).hide();
			}
			else{
				if(value==0){
					$(".form_"+nodeId).show();
					$(".url_"+nodeId).hide();
				}
				else if(value==1){
					$(".form_"+nodeId).hide();
					$(".url_"+nodeId).show();
				}else{
					$(".form_"+nodeId).hide();
					$(".url_"+nodeId).hide();
				}
			}
		});
		//节点表单
		$("select[name='formType']").change(function(){
			var value=$(this).val();
			var nodeId=$(this).parents(".formBox").find("#nodeId").val();
			if(value==-1){
				$("#formBox_"+nodeId).hide();
			}
			else{
				$("#formBox_"+nodeId).show();
				if(value==0){
					$(".form_"+nodeId).show();
					$(".url_"+nodeId).hide();
				}
				else if(value==1){
					$(".form_"+nodeId).hide();
					$(".url_"+nodeId).show();
				}else{
					$(".form_"+nodeId).hide();
					$(".url_"+nodeId).hide();
				}
			}
		});
		
		$("select[name='globalFormType']").change(function(){
			var obj=$("select[name='bpmFormType']");
			var value=$(this).val();
			var nodeId=$(this).parents(".formBox").find("#nodeId").val();
			var objNodeId=obj.parents(".formBox").find("#nodeId").val();
			obj.val(value);
			if(value==-1){
				$("#formBox_"+nodeId).hide();
				$(".form_"+objNodeId).hide();
				$(".url_"+objNodeId).hide();
			}
			else{
				$("#formBox_"+nodeId).show();
				if(value==0){
					$(".form_"+nodeId).show();
					$(".url_"+nodeId).hide();
					$(".form_"+objNodeId).show();
					$(".url_"+objNodeId).hide();
				}
				else if(value==1){
					$(".form_"+nodeId).hide();
					$(".url_"+nodeId).show();
					$(".form_"+objNodeId).hide();
					$(".url_"+objNodeId).show();
				}else{
					$(".form_"+nodeId).hide();
					$(".url_"+nodeId).hide();
					$(".form_"+objNodeId).hide();
					$(".url_"+objNodeId).hide();
				}
			}
		});
	}
	
	function validHandler(){
		$("input.handler").blur(function(){
			var obj=$(this);
			var val=obj.val();
			if(val.trim()==""){
				return;
			}
			var params={handler:val};
			$.post("validHandler.ht",params,function(data){
				var json=eval("(" +data +")");
				if(json.result!='0'){
					$.ligerDialog.warn(json.msg,$lang.tip.msg,function(){
						obj.focus();
					});
				}
			});
		});
	}
	
	function showHelper(){
		var cookie=$.getCookie("help");
		if(cookie=="hidden") {
			$("ul.help").hide();
			return ;
		}
		$("ul.help").show();
	}
	
	function toggleHelp(){
		var display=$("ul.help").css("display");
		if(display=="none"){
			$("ul.help").show();
			$.setCookie("help","show");
		}
		else{
			$("ul.help").hide();
			$.setCookie("help","hidden");
		}
	}
	
</script>
<style type="text/css">
	ul.help li { list-style:inside circle;list-style-type:decimal ;padding-left: 10px;font-weight: normal; } 
</style>

</head>
<body>

    
     <jsp:include page="incDefinitionHead.jsp">
   		<jsp:param value="<spr:message code='bpmDefinition.form.title'/>" name="title"/>
	</jsp:include>
	<div class="panel-container">
    <f:tab curTab="form" tabName="flow"/>

	<div class="panel">

		<div class="panel-top" usesclflw="false"  style="margin-left:5px;margin-right:5px">
				 <h2 class="setting" style="margin-top:12px"><spr:message code='bpmDefinition.form.title'/></h2>
				  <div class="panel-toolbar inline" style="margin-left: 5px;background:white;border-top: solid  0px white;border-bottom: solid 0px white;">
				     <a class="link save" onclick="save()"> <span></span><spr:message code="menu.button.save"/></a>
				 </div>	
				 <div class="foldBox">
					<a class="drop" value="1">
						<span class="dropicon"></span>
						<spr:message code="bpmDefinition.form.help"/>
					</a>
					<div class="content"  style=" background: none repeat scroll 0 0 #EFEFEF;">

								<ul class="help" style="display:none;">
									<li></span><spr:message code="bpmDefinition.form.form"/>
										<ul>
											<li><spr:message code="bpmDefinition.form.form.formTip1"/></li>
											<li><spr:message code="bpmDefinition.form.form.formTip2"/></li>
										</ul>
									</li>
									<li><spr:message code="bpmDefinition.form.handler"/>
										<ul>
											<li><spr:message code="bpmDefinition.form.handler.Tip1"/></li>
											<li>
											<spr:message code="bpmDefinition.form.handler.Tip2"/>
											</li>
										</ul>
										
									</li>
								</ul>

					</div>
				</div>
		</div>
		<div class="panel-body">			
				<form action="save.ht" method="post" id="dataForm">				
				    <input type="hidden" name="defId" value="${bpmDefinition.defId}"/>
				     <div class="foldBox formBox">
				     	 <input id="nodeId" type="hidden" value="globalFormType"/>
				               <div class="title" >
									<spr:message code="bpmDefinition.form.globalForm"/>
									<select name="globalFormType" class="selectForm" >
										<c:choose >
											<c:when test="${globalForm==null }">
												<option value="-1" selected="selected" ><spr:message code="bpmDefinition.form.selectForm.notSelect"/></option>
												<option value="0" ><spr:message code="bpmDefinition.form.selectForm.onlineForm"/></option>
												<option value="1" ><spr:message code="bpmDefinition.form.selectForm.urlForm"/></option>
											</c:when>
											<c:otherwise>
												<option value="-1" <c:if test="${globalForm.formType==-1 }">selected="selected"</c:if>><spr:message code="bpmDefinition.form.selectForm.notSelect"/></option>
												<option value="0"  <c:if test="${globalForm.formType==0 }">selected="selected"</c:if> ><spr:message code="bpmDefinition.form.selectForm.onlineForm"/></option>
												<option value="1"  <c:if test="${globalForm.formType==1 }">selected="selected"</c:if>><spr:message code="bpmDefinition.form.selectForm.urlForm"/></option>
											</c:otherwise>
										</c:choose>
									</select>
								</div>
				     			<div  class="content">
				     						<table id="formBox_globalFormType" class="table-noborder" <c:if test="${globalForm==null}">style="display: none" </c:if>>
													<tr>
														<th nowrap="nowrap"><spr:message code="bpmDefinition.form.form"/>:</th>
														<td>
															<div name="globalForm" class="form_globalFormType" <c:if test="${globalForm!=null && globalForm.formType!=0 }">style="display: none" </c:if>>
																<input id="defaultFormKey" class="formKey"  type="hidden" name="defaultFormKey" value="${globalForm.formKey }" >
																<input id="defaultFormName" class="formDefName"  type="hidden" name="defaultFormName" value="${globalForm.formDefName }">
												<span name="spanForm">				<a target="_blank" href="${ctx}/platform/form/bpmFormHandler/edit.ht?formDefId=${globalForm.formKey}" >${globalForm.formDefName }</a></span>
																<a href="javascript:;" class="link get" onclick="selectGlobalForm()"><spr:message code="menu.button.choose"/></a>
																<a href="javascript:;" class="link clean" onclick="clearForm(this)"><spr:message code="menu.button.reChoose"/></a>
																<a href="javascript:;" class="link auth" onclick="authorize(this,'')"><spr:message code="bpmDefinition.form.authorize"/></a>
																<a href="#" class="tipinfo"><span><spr:message code="bpmDefinition.form.globalTip"/></span></a>
															</div>
															<div name="globalFormUrl" <c:if test="${globalForm!=null && globalForm.formType!=1 }">style="display: none" </c:if> class="url_globalFormType" >
																<spr:message code="bpmDefinition.form.formUrl"/>:<input type="text" name="formUrlGlobal" value="${globalForm.formUrl }" class="inputText" style="width: 250px;"/>
																<br/>
																<spr:message code="bpmDefinition.form.detailUrl"/>:<input type="text" name="detailUrlGlobal" value="${globalForm.detailUrl }" class="inputText" style="width: 250px;"/>
															</div>
														</td>
													</tr>
													<tr>
														<th nowrap="nowrap" ><spr:message code="bpmDefinition.form.handler.before"/>-<spr:message code="bpmDefinition.form.handler"/>:</th>
														<td>
															<input type="text" name="beforeHandlerGlobal" value="${globalForm.beforeHandler }" class="inputText handler" size="40"/>
															
														</td>
													</tr>
													<tr>
														<th nowrap="nowrap" ><spr:message code="bpmDefinition.form.handler.after"/>-<spr:message code="bpmDefinition.form.handler"/>:</th>
														<td>
															<input type="text" name="afterHandlerGlobal" value="${globalForm.afterHandler }" class="inputText handler" size="40"/>
															
														</td>
													</tr>
												</table>
				     			</div>
				     </div>
					<div class="foldBox formBox">
						<input type="hidden" id="nodeId" value="bpmForm"/>
							 <div class="title" ><spr:message code="bpmDefinition.form.startForm1"/>:
							 	 <select name="bpmFormType" id="bpmFormType" class="selectForm" >
									<c:choose >
										<c:when test="${bpmForm==null }">
											<option value="-1" selected="selected" ><spr:message code="bpmDefinition.form.selectForm.notSelect"/></option>
											<option value="0" ><spr:message code="bpmDefinition.form.selectForm.onlineForm"/></option>
											<option value="1" ><spr:message code="bpmDefinition.form.selectForm.urlForm"/></option>
										</c:when>
										<c:otherwise>
											<option value="-1" <c:if test="${bpmForm.formType==-1 }">selected="selected"</c:if>><spr:message code="bpmDefinition.form.selectForm.notSelect"/></option>
											<option value="0"  <c:if test="${bpmForm.formType==0 }">selected="selected"</c:if>><spr:message code="bpmDefinition.form.selectForm.onlineForm"/></option>
											<option value="1"  <c:if test="${bpmForm.formType==1 }">selected="selected"</c:if>><spr:message code="bpmDefinition.form.selectForm.urlForm"/></option>
										</c:otherwise>
									</c:choose>
								</select>
							 </div>
							 <div class="content">
							 	<div  id="formBox_bpmForm"  name="bpmForm" class="form_bpmForm" <c:if test="${empty bpmForm || bpmForm.formType!=0}">style="display: none"</c:if> >
									<input id="bpmFormKey" class="formKey"  type="hidden" name="bpmFormKey" value="${bpmForm.formKey}">
									<input id="bpmFormName" class="formDefName"  type="hidden" name="bpmFormName" value="${bpmForm.formDefName}">
									<span name="spanForm"><a target="_blank" href="${ctx}/platform/form/bpmFormHandler/edit.ht?formDefId=${bpmForm.formKey}" >${bpmForm.formDefName}</a></span>
									<a href="javascript:;" class="link get" onclick="selectBpmForm(this)"><spr:message code="menu.button.choose"/></a>
									<a href="javascript:;" class="link clean" onclick="clearForm(this)"><spr:message code="menu.button.reChoose"/></a>
								</div>
								<div  class="url_bpmForm" <c:if test="${empty bpmForm || bpmForm.formType!=1}">style="display: none"</c:if>>
									<spr:message code="bpmDefinition.form.formUrl"/>:<input type="text" id="bpmFormUrl" name="bpmFormUrl" value="${bpmForm.formUrl}" class="inputText" size="40"/>
								</div>
							 </div>
					</div>
					<div class="foldBox">
					      <div class="title" ><spr:message code="bpmDefinition.form.nodeForm"/></div>
					       <div class="content">
					           <table cellpadding="1" cellspacing="1" class="table-grid table-list">
													<thead>
													<tr>
														<th width="20%"><spr:message code="bpmDefinition.form.nodeName"/></th>
														<th width="13%">
															<label><input type="checkbox" id="ckJumpType"><spr:message code="bpmDefinition.form.jumpType"/></label>
														</th>
														<th width="7%">
															<label title="<spr:message code="bpmDefinition.form.hidenOption"/>"><input type="checkbox" id="ckHidenOption"><spr:message code="bpmDefinition.form.hidenOption"/></label>
														</th>
														<th width="7%">
															<label title="<spr:message code="bpmDefinition.form.hidenPath"/>"><input type="checkbox" id="ckHidenPath"><spr:message code="bpmDefinition.form.hidenPath"/></label>
														</th>
														<th width="10%"><spr:message code="bpmDefinition.form.handler"/></th>
														<th width="32%"><spr:message code="bpmDefinition.form.form"/></th>
													</tr>													
													</thead>
													<c:forEach items="${bpmNodeSetList}" var="item" varStatus="status">
													<tr  <c:if test="${status.index%2=='0' }">class="odd"</c:if>>
														<td>
															<input type="hidden" name="nodeId" value="${item.nodeId}"/>
															<input type="hidden" name="nodeName" value="${item.nodeName}"/>${item.nodeName}
														</td>
														<td nowrap="nowrap">
														
															<ul>
																<li><input type="checkbox" class="jumpType" name="jumpType_${item.nodeId}" value="1"  <c:if test="${fn:indexOf(item.jumpType,'1')!=-1}">checked="checked"</c:if> /><spr:message code="bpmDefinition.form.normalJump"/></li> 
																<li><input type="checkbox" class="jumpType" name="jumpType_${item.nodeId}" value="2" <c:if test="${fn:indexOf(item.jumpType,'2')!=-1}">checked="checked"</c:if> /><spr:message code="bpmDefinition.form.selectPathJump"/></li> 
																<li><input type="checkbox" class="jumpType" name="jumpType_${item.nodeId}" value="3" <c:if test="${fn:indexOf(item.jumpType,'3')!=-1}">checked="checked"</c:if> /><spr:message code="bpmDefinition.form.freeJump"/></li>
															</ul>
														</td>
														<td>
															<input type="checkbox" class="hideOption" name="isHideOption_${item.nodeId}" value="1" <c:if test="${item.isHideOption==1}">checked="checked"</c:if> />
														</td>
														<td>
															<input type="checkbox" class="hidePath" name="isHidePath_${item.nodeId}" value="1" <c:if test="${item.isHidePath==1}">checked="checked"</c:if> />
														</td>
														
														<td>
															<table class="table-detail">
																<tr>
																		<td nowrap="nowrap" class="head"><spr:message code="bpmDefinition.form.handler.before"/>:</td>
																		<td><input type="text" name="beforeHandler" value="${item.beforeHandler}" class="inputTexts handler" size="20"/>
																			
																		</td>
																	</tr>
																	<tr>
																		<td nowrap="nowrap" class="head"><spr:message code="bpmDefinition.form.handler.after"/>:</td>
																		<td><input type="text" name="afterHandler" value="${item.afterHandler}" class="inputTexts handler" size="20"/>
																			
																		</td>
																	</tr>
															</table>
														</td>
														<td class='formBox'>
															<input id="nodeId" type ="hidden" value="${item.nodeId}"/>
															<table class="table-detail" >
																<tr>
																	<td nowrap="nowrap" class="head"><spr:message code="bpmDefinition.form.formType"/>:</td>
																	<td>
																		<select name="formType" class="selectForm" >
																			<option value="-1" <c:if test="${item.formType==-1}">selected</c:if>><spr:message code="bpmDefinition.form.selectForm.notSelect"/></option>
																			<option value="0" <c:if test="${item.formType==0}">selected</c:if>><spr:message code="bpmDefinition.form.selectForm.onlineForm"/></option>
																			<option value="1" <c:if test="${item.formType==1}">selected</c:if>><spr:message code="bpmDefinition.form.selectForm.urlForm"/></option>
																		</select>
																	</td>
																</tr>
															</table>
															<div id="formBox_${item.nodeId}" name="nodeForm" <c:if test="${item.formType==-1}">style="display:none"</c:if>>
															<table class="table-detail table-noborder" >
																<tr class="form_${item.nodeId}" <c:if test="${item!=null && item.formType!=0 }">style="display: none" </c:if>>
																	<td nowrap="nowrap"><spr:message code="bpmDefinition.form.form"/>:</td>
																	<td>
																		<div>
																			<input type="hidden" class="formKey" name="formKey" value="${item.formKey}">
																			<input type="hidden" class="oldFormKey" name="oldFormKey" value="${item.formKey}">
																			<input type="hidden" class="formDefName" name="formDefName" value="${item.formDefName}">
																			<input type="hidden" class="tableId" name="tableId" value="${item.mainTableId }">
																			<span name="spanForm"><a target="_blank" href="${ctx}/platform/form/bpmFormHandler/edit.ht?formDefId=${item.formKey}" >${item.formDefName}</a></span>
																			<a href="javascript:;" class="link get" onclick="selectNodeForm(this)" id="subNodeSel"><spr:message code="menu.button.choose"/></a>
																			<a href="javascript:;" class="link clean" onclick="clearForm(this)"><spr:message code="menu.button.reChoose"/></a>
																			<a href="javascript:;" class="link auth" onclick="authorize(this,'${item.nodeId}')"><spr:message code="bpmDefinition.form.authorize"/></a>
																			<input type="hidden" id="existSubTable" value="${item.existSubTable }">
																			<a href="javascript:;" class="link grant" onclick="subDataGrant(this,'${item.nodeId}')"><spr:message code="bpmDefinition.form.subDataGrant"/></a>
																		</div>
																	</td>
																</tr>
															</table>
															<table class="table-detail table-noborder" >
																<tr <c:if test="${item!=null && item.formType!=1 }">style="display: none" </c:if> class="url_${item.nodeId}">
																<td><spr:message code="bpmDefinition.form.formUrl"/></td>
																	<td><input type="text" name="formUrl" value="${item.formUrl}" class="inputTexts" size="30"/></td>
																</tr>
																<tr <c:if test="${item!=null && item.formType!=1 }">style="display: none" </c:if> class="url_${item.nodeId}">
																<td><spr:message code="bpmDefinition.form.detailUrl"/></td>
																	<td><input type="text" name="detailUrl" value="${item.detailUrl}" class="inputTexts" size="30"/></td>
																</tr>
															</table>
															</div>
														</td>
													</tr>
													</c:forEach>
												</table>			       
					       </div>
					</div>    
				</form>
			
		</div>				
	</div>
	</div>
</body>
</html>


