<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/include/get.jsp" %>
<f:js pre="js/lang/view/platform/form" ></f:js>
<title><spr:message code="bpmFormDef.title"/><spr:message code="operator.list"/></title>
<f:link href="tree/zTreeStyle.css"></f:link>
<link href="${ctx}/styles/default/css/jquery.qtip.css" rel="stylesheet" />
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/CopyFormDefDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/ImportExportXmlUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerComboBox.js"></script>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/htCatCombo.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.qtip.js" ></script>
<script type="text/javascript" src="${ctx}/js/util/easyTemplate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.form.js"></script>
<script type="text/javascript">
	var win;
	function newFormDef(){
		var url=__ctx + '/platform/form/bpmFormDef/gatherInfo.ht?categoryId=${categoryId}';
		win= $.ligerDialog.open({title:$lang_form.bpmFormDef.list.newFormDef, url: url, height: 350,width:550 ,isResize: false });	
	}

	function closeWin(){
		if(win){
			win.hidden();
		}
	}
	function reload(){
		location.href=location.href.getNewUrl();
	}
	
	$(function(){
		$("a.link.del").unbind("click");
		$("a[formKey]").each(showResult);	
		//delFormDef();
		handlerDelSelect(showResponse);
		publish();
		handNewVersion();
	});	
	
	function showResult(){
		var formKey=$(this).attr("formKey");
		var template=$("#txtBpmDefinitionListTemplate").val();
		$(this).qtip({  
			content:{
				text:$lang.tip.loading,
				ajax:{
					url:__ctx +"/platform/bpm/bpmDefinition/getBpmDefinitionByFormKey.ht",
					type:"GET",
					data:{formKey: formKey },
					success:function(data,status){
						var html=easyTemplate(template,data).toString();
						this.set("content.text",html);
					}
				},
				title:{
					text:$lang_form.bpmFormDef.list.message	
				}
			},
	        position: {
	        	at:'top left',
	        	target:'event',
	        	
				viewport:  $(window)
	        },
	        show:{
	        	event:"click",
		     	solo:true
	        },   			     	
	        hide: {
	        	event:'unfocus mouseleave',
	        	fixed:true
	        },  
	        style: {
	       	  classes:'ui-tooltip-light ui-tooltip-shadow'
	        } 			    
	 	});	
	}
	
	function copyFormDef(formDefId){
		CopyFormDefDialog({formDefId:formDefId,center:0});
	};
	
	function publish(){
		$.confirm("a.link.deploy",$lang_form.bpmFormDef.list.publish);
	}
	
	function delFormDef(){
		$.confirm("table.table-grid td a.link.del",$lang_form.bpmFormDef.list.delFormDef);
	}
	
	function handlerDelSelect(showResponse)
	{
		//单击删除超链接的事件处理
		$("a.link.del").click(function(){	
			if($(this).hasClass('disabled')) return false;
			var self=this;
			var action=$(this).attr("action");
			var aryId = null;
			var singleAction=$(this).attr("href");
			//提交到后台服务器进行日志删除批处理的日志编号字符串
			var delId="";
			var keyName="formKey";
			if(!action){
				//action属性为空，则认为是操作删除一条记录
				action=$(self).attr("href");
				keyName="";
			}else{
				aryId = $("input[type='checkbox'][disabled!='disabled'][class='pk']:checked");
				if(aryId.length == 0){
					$.ligerDialog.warn($lang.operateTip.selectRecord);
					return false;
				}
				aryId.each(function(){
					delId+=$(this).attr(keyName) +",";
				});
				action +="?" +keyName +"=" +delId ;
			}
			
			$.ligerDialog.confirm($lang_form.bpmFormDef.list.delChoFormDef,$lang.tip.msg,function(rtn) {
				if(rtn) {
					var form = $('<form method="post"></form>');
					form.attr('action',action);
					form.ajaxForm({success:showResponse});
					form.submit();
				}
			});
			return false;
		});
	}
	
	function showResponse(responseText){
		var obj=new com.hotent.form.ResultMessage(responseText);
		if(obj.isSuccess()){//成功
			$.ligerDialog.closeWaitting();
			$.ligerDialog.success('<p><font color="green">'+obj.getMessage()+'</font></p>',$lang.tip.msg,function(){
				location.reload(true);
			});
	    }else{//失败
			$.ligerDialog.closeWaitting();
			var message = '<p><font color="red">'+obj.getMessage()+'</font></p>';
			$.ligerDialog.tipDialog($lang.tip.msg,$lang_form.bpmFormDef.list.delResult,message,null,function(){
				$.ligerDialog.hide();
			});
	    }
	}
	
	function handNewVersion(){
		$.confirm("a.link.newVersion",$lang_form.bpmFormDef.list.newVersion);
	}
	
	function authorizeDialog(formKey){
		var url=__ctx + '/platform/form/bpmFormDef/rightsDialog.ht?formKey=' + formKey;
		var winArgs="dialogWidth=800px;dialogHeight=600px;help=0;status=no;center=yes;resizable=no;";
		url=url.getNewUrl();
		window.showModalDialog(url,"",winArgs);
	}
	
	
	// 导出自定义表单
	function exportXml(){	
		var formDefIds = ImportExportXml.getChkValue('pk');
		if (formDefIds ==''){
			$.ligerDialog.warn($lang.operateTip.selectOneRecord,$lang.tip.msg);
			return ;
		}

		var url=__ctx + "/platform/form/bpmFormDef/export.ht?formDefIds="+formDefIds;
		ImportExportXml.showModalDialog({url:url});
	}

	
	//导入自定义表单
	function importXml(){
		var url=__ctx + "/platform/form/bpmFormDef/import.ht";
		ImportExportXml.showModalDialog({url:url});
	}
	
	function getFormKey(){
	var aryChk=$("input:checkbox[name='formDefId']:checked");
	if(aryChk.length==0) return "";
	var aryFormKey=[];
	aryChk.each(function(){
		aryFormKey.push($(this).attr("formKey"));
	});
	return aryFormKey.join(",");
}

	

var dialog=null;

function setCategory(){
	
	var formKeys=getFormKey();
	if(formKeys==""){
		$.ligerDialog.warn($lang.operateTip.selectOneRecord,$lang.tip.msg);
		return;
	}
	$.initCatComboBox();
	if(dialog==null){
		dialog=$.ligerDialog.open({title:$lang_form.bpmFormDef.list.setCategory,target:$("#dialogCategory"),width:400,height:250,buttons:
			[ {text : $lang.button.ok,onclick: setCategoryOk},
			{text : $lang.button.cancel,onclick: function (item, dialog) {
					dialog.hide();
				}
			}]});	
	}
	dialog.show();
}


function setCategoryOk(item, dialog){
	var categoryId=$("#categoryId").val();
	if(categoryId==""){
		$.ligerDialog.warn($lang_form.bpmFormDef.list.selectCategory,$lang.tip.msg);
		return;
	}
	var formKeys=getFormKey();
	var params={formKeys:formKeys,categoryId:categoryId};
	var url="${ctx}/platform/form/bpmFormDef/setCategory.ht";
	$.post(url,params,function(responseText){
		var obj=new com.hotent.form.ResultMessage(responseText);
		if(obj.isSuccess()){
			$.ligerDialog.success($lang.save.success,$lang.tip.msg,function(){
				dialog.hide();
				var url=location.href.getNewUrl();
				location.href=url;
			});
		}
		else{
			$.ligerDialog.err($lang.tip.msg,$lang.save.failure,obj.getMessage());
		}
	});
}

//业务数据模板
function dataTemplate(formKey,tableId){
	var url=__ctx+"/platform/form/bpmDataTemplate/edit.ht?formKey="+formKey+"&tableId="+tableId;
	$.openFullWindow(url);
}
</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label"><spr:message code="bpmFormDef.title"/><spr:message code="operator.list"/></span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link add" onclick="newFormDef()"  href="#"><span></span><spr:message code="menu.button.add"/></a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><f:a alias="delForm" css="link del" action="delByFormKey.ht"><span></span><spr:message code="menu.button.del"/></f:a></div>
				<div class="l-bar-separator"></div>
				<div class="group">
					<a onclick="exportXml()" href="#" class="link export"><span></span><spr:message code="menu.button.export"/></a>
				</div>
			   <div class="l-bar-separator"></div>
				<div class="group">
					<a onclick="importXml()" href="#" class="link import"><span></span><spr:message code="menu.button.import"/></a>
				</div>
				<div class="l-bar-separator"></div>
				<div class="group">
					<a onclick="setCategory()" href="#" class="link category"><span></span><spr:message code="menu.button.setCategory"/></a>
				</div>
			</div>	
		</div>
		<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
				<ul class="row">
						<input type="hidden" name="categoryId" value="${param['categoryId']}" title="表单分类ID"></input>
					<li>
						<span class="label"><spr:message code="bpmFormDef.subject"/>:</span><input type="text" name="Q_subject_SL"  class="inputText" value="${param['Q_subject_SL']}"/>	
					</li>
					<li>
						<span class="label"><spr:message code="bpmFormDef.correspondTable"/>:</span><input type="text" name="Q_tableName_SL"  class="inputText" value="${param['Q_tableName_SL']}"/>
					</li>
					<li>
						<span class="label"><spr:message code="bpmFormDef.designType"/>:</span>
						<select name="Q_designType_SN" value="${param['Q_designType_SN']}">
							<option value=""><spr:message code="search.select.allSelect"/></option>
							<option value="1" <c:if test="${param['Q_designType_SN'] == '1'}">selected</c:if>><spr:message code="bpmFormDef.designType.editor"/></option>	
							<option value="0" <c:if test="${param['Q_designType_SN'] == '0'}">selected</c:if>><spr:message code="bpmFormDef.designType.table"/></option>
						</select>
					</li>
					<li>
						<span class="label"><spr:message code="bpmFormDef.isPublished"/>:</span>
						<select name="Q_isPublished_SN" value="${param['Q_isPublished_SN']}">
							<option value=""><spr:message code="search.select.allSelect"/></option>
							<option value="1" <c:if test="${param['Q_isPublished_SN'] == '1'}">selected</c:if>><spr:message code="bpmFormDef.isPublished.publish"/></option>	
							<option value="0" <c:if test="${param['Q_isPublished_SN'] == '0'}">selected</c:if>><spr:message code="bpmFormDef.isPublished.notPublish"/></option>
						</select>
					</li>
					</ul>
				</form>
		</div>
	</div>
	</div>
	<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall" />
			</c:set>
		    <display:table name="bpmFormDefList" id="bpmFormDefItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" export="false"  class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
					<input type="checkbox" class="pk" name="formDefId"
						value="${bpmFormDefItem.formDefId}" formKey="${bpmFormDefItem.formKey}">
				</display:column>
				<display:column property="subject" titleKey="bpmFormDef.subject" sortable="true" sortName="subject">
					<a href="#${bpmFormDefItem.formKey}" formKey="${bpmFormDefItem.formKey}" style="text-align:left;" >${bpmFormDefItem.subject}</a>
				</display:column>
				<display:column property="tableName" titleKey="bpmFormDef.correspondTable" sortable="true" sortName="tableName" style="width:120px;white-space:nowrap;"></display:column>
				<display:column property="categoryName" titleKey="bpmFormDef.category" sortable="true" sortName="categoryId" style="width:80px;"></display:column>
				<display:column  titleKey="bpmFormDef.isPublished" sortable="true" sortName="isPublished" style="text-align:left;width:60px;">
					<c:choose>
						<c:when test="${bpmFormDefItem.isPublished==1 }">
							<span class="green"><spr:message code="bpmFormDef.isPublished.publish"/></span>
						</c:when>
						<c:otherwise>
							<span class="red"><spr:message code="bpmFormDef.isPublished.notPublish"/></span>
						</c:otherwise>
					</c:choose>
				</display:column>
				<display:column  titleKey="bpmFormDef.dataTemplate"   >
					<c:choose>
						<c:when test="${dataTemplateCounts[bpmFormDefItem.formDefId] > 0}">
							<span class="green"><spr:message code="bpmFormDef.dataTemplate.add"/></span>
						</c:when>
						<c:otherwise>
							<span class="red"><spr:message code="bpmFormDef.dataTemplate.notAdd"/></span>
						</c:otherwise>
					</c:choose>
				</display:column>
				<display:column titleKey="bpmFormDef.designType" sortable="true" sortName="designType" style="text-align:left;width:60px;">
					<c:choose>
						<c:when test="${bpmFormDefItem.designType==0 }">
							<span class="green"><spr:message code="bpmFormDef.designType.table"/></span>
						</c:when>
						<c:when test="${bpmFormDefItem.designType==1 }">
							<span class="brown"><spr:message code="bpmFormDef.designType.editor"/></span>
						</c:when>
					</c:choose>
				</display:column>
				<display:column titleKey="bpmFormDef.versionInfo" style="text-align:left;width:95px;white-space: nowrap;">
					<c:if test="${publishedCounts[bpmFormDefItem.formDefId] > 0}">
						<a href="get.ht?formDefId=${bpmFormDefItem.formDefId}" ><spr:message arguments="${defaultVersions[bpmFormDefItem.formDefId].versionNo}" code="bpmFormDef.version.defaultversion"/></a>
					</c:if>
					<c:choose>
						<c:when test="${publishedCounts[bpmFormDefItem.formDefId] > 0}">
							&nbsp;<a href="versions.ht?formKey=${bpmFormDefItem.formKey}" ><spr:message arguments="${publishedCounts[bpmFormDefItem.formDefId]}" code="bpmFormDef.version.publishversion"/>
							</a>
						</c:when>
						<c:otherwise>
							<spr:message arguments="${publishedCounts[bpmFormDefItem.formDefId]}" code="bpmFormDef.version.publishversion"/>,
						</c:otherwise>
					</c:choose>
				</display:column>				
				<display:column titleKey="list.manage" media="html"  style="width:50px;text-align:center" class="rowOps">
					<c:choose>
						<c:when test="${bpmFormDefItem.isPublished== 1}">
							<a href="newVersion.ht?formDefId=${bpmFormDefItem.formDefId}"  class="link newVersion"><spr:message code="menu.button.newVersion"/></a>
						</c:when>
						<c:otherwise>
						<c:choose>
							<c:when test="${bpmFormDefItem.designType==0 }">
								<a href="#" onclick="javascript:jQuery.openFullWindow('edit.ht?formDefId=${bpmFormDefItem.formDefId}');" class="link edit"><spr:message code="menu.button.edit"/></a>
								<a href="publish.ht?formDefId=${bpmFormDefItem.formDefId }" class="link deploy" ><spr:message code="menu.button.publish"/></a>
							</c:when>
							<c:when test="${bpmFormDefItem.designType==1 }">
								<c:choose>
									<c:when test="${empty bpmFormDefItem.tableName}">
										<a href="#" onclick="javascript:jQuery.openFullWindow('designEdit.ht?formDefId=${bpmFormDefItem.formDefId}');" class="link edit"><spr:message code="menu.button.edit"/></a>
									</c:when>
									<c:otherwise>
									<a href="#" onclick="javascript:jQuery.openFullWindow('edit.ht?formDefId=${bpmFormDefItem.formDefId}');" class="link edit"><spr:message code="menu.button.edit"/></a>
									<a href="publish.ht?formDefId=${bpmFormDefItem.formDefId }" class="link deploy" ><spr:message code="menu.button.publish"/></a>
									</c:otherwise>
								</c:choose>
							</c:when>
						</c:choose>
						</c:otherwise>
					</c:choose>
					<f:a alias="delForm" css="link del" href="delByFormKey.ht?formKey=${bpmFormDefItem.formKey}"><spr:message code="menu.button.del"/></f:a>
					<a href="get.ht?formDefId=${bpmFormDefItem.formDefId}" class="link detail"><spr:message code="menu.button.detail"/></a>
					
					<c:choose>
							<c:when test="${bpmFormDefItem.designType==0 }">
								<a target="_blank" href="${ctx}/platform/form/bpmFormHandler/edit.ht?formDefId=${bpmFormDefItem.formDefId}" class="link preview"><spr:message code="menu.button.preview"/></a>
								<a  class="link auth" href="javascript:authorizeDialog(${bpmFormDefItem.formKey})"><spr:message code="menu.button.formAuthorize"/></a>
							</c:when>
							<c:when test="${bpmFormDefItem.designType==1 }">
								<a href="#" onclick="javascript:jQuery.openFullWindow('preview.ht?formDefId=${bpmFormDefItem.formDefId}');" class="link preview"><spr:message code="menu.button.preview"/></a>
								<c:if test="${bpmFormDefItem.isPublished==1}">
									<a class="link auth"  href="javascript:authorizeDialog(${bpmFormDefItem.formKey})"><spr:message code="menu.button.formAuthorize"/></a>
								</c:if>
							</c:when>
					</c:choose>
					<c:if test="${bpmFormDefItem.isPublished==1}">
						<a  href="javascript:;" onclick="copyFormDef(${bpmFormDefItem.formDefId})" class="link copy"><spr:message code="menu.button.copy"/></a>
						<a  href="${ctx}/platform/form/bpmPrintTemplate/list.ht?formKey=${bpmFormDefItem.formKey}" class="link dataList"><spr:message code="menu.button.printTemplate"/></a>
						<a  href="javascript:;" onclick="dataTemplate(${bpmFormDefItem.formKey},${bpmFormDefItem.tableId})" class="link preview"><spr:message code="menu.button.workDataTemplate"/></a>
						
					</c:if>
				</display:column>
			</display:table>
			<hotent:paging tableId="bpmFormDefItem"/>
		
	</div><!-- end of panel-body -->				
 <!-- end of panel -->
 	<textarea id="txtBpmDefinitionListTemplate"  style="display: none;">
	    <div  style="height:150px;width:150px;overflow:auto">
	    	<ui>
	    		<#list data as obj>
	    		<li style="margin-top:10px;"><a href="${ctx}/platform/bpm/bpmDefinition/detail.ht?defId=\${obj.defId}">\${obj.subject}</a></li>
	    		</#list>
	    	</ui>
	  	</div>
	  </textarea>
</body>
<div id="dialogCategory" style="width: 380px;display: none;">
	<div class="panel">
			<div class="panel-body">
				<form id="frmDel">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th style="width:113px;text-align:center;"><spr:message code="bpmFormDef.category"/>:</th>
							<td>
								<input class="catComBo" catKey="FORM_TYPE" valueField="categoryId" catValue="" name="typeName" height="150" width="235"/>
							</td>
						</tr>
					</table>
				
				</form>
			</div>
		</div>
</div>
</html>