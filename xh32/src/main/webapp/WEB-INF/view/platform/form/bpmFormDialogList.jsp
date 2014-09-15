
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="bpmFormDialog.title"/><spr:message code="operator.list"/></title>
<f:js pre="js/lang/view/platform/form" ></f:js>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/CommonDialog.js"></script>

<script type="text/javascript">
	var win;
	function preview(alias){
		//向对话框传入参数
		var paramValueString = "" ;
		if(alias==null || alias==undefined){
			$.ligerDialog.warn($lang_js.form.commonDialog.aliasNull,$lang.tip.warn);
			return;
		}
		var isPreviewCallCode = false ;
		if(alias.indexOf("callCode-")==0){
			isPreviewCallCode = true;
			alias = alias.replace("callCode-","");
		}
		
		var url=__ctx + "/platform/form/bpmFormDialog/dialogObj.ht?alias=" +alias;
		url=url.getNewUrl();
		var isParamsNeeded = false ;
		$.ajax({  
			type : "post",  
			url  : url,  
			data : {"alias":alias},   
			async : false,  
			success : function(data){
			if(data.success==0){
				$.ligerDialog.warn($lang_js.form.commonDialog.aliasError,$lang.tip.warn);
				isParamsNeeded = true ;
				return;
			}
			var obj=data.bpmFormDialog;
			var fieldObj=eval("("+obj.conditionfield.trim()+")") ;
			var paramArr = [] ;
			var urlForParams = __ctx + "/platform/form/bpmFormDialog/params.ht?alias="+alias;
			for(var i=0,c;c=fieldObj[i++];){
				//4：动态传入参数，5：java脚本参数
				if(c.defaultType=="4"){
					paramArr.push(c.field) ;
					isParamsNeeded = true ;
				}else if(c.defaultType=="5" && c.dbType=='isAfferent'){
					paramArr.push(c.field) ;
					isParamsNeeded = true ;
				}
			}
			//需要传入参数
			urlForParams += "&params="+JSON2.stringify(paramArr)+"&isPreviewCallCode="+isPreviewCallCode;
			if(isParamsNeeded){
				if(isPreviewCallCode){
					urlForParams += "&resultfield="+obj.resultfield.trim() ;
					$.ligerDialog.open({ url:urlForParams, height: obj.height,width: obj.width,title:$lang_form.bpmFormDialog.callDialog,name:'paramDialog_'});
				}else{
				//若取消，返回null
				$.ligerDialog.open({ url:urlForParams, height: obj.height,width: obj.width,title:$lang_form.bpmFormDialog.paramDialog,name:'paramDialog_',
					buttons: [ { text: $lang.button.ok, onclick: function (item, dialog) { 
						paramValueString = $('#paramDialog_')[0].contentWindow.selectForm() ;
						CommonDialog(alias,function(data){
							var json=JSON2.stringify(data);
							$("#txtJsonData").val(json);
							if(!win){
								var obj=$("#divJsonData");
								win= $.ligerDialog.open({ target:obj , height: 300,width:500, modal :true}); 
							}
							win.show();
						},paramValueString);
						dialog.close();
					} }, 
					{ text: $lang.button.cancel, onclick: function (item, dialog) { dialog.close(); } } ] });
				
				}
			}else if(isPreviewCallCode){
				urlForParams += "&resultfield="+obj.resultfield.trim() ;
				$.ligerDialog.open({ url:urlForParams, height: obj.height,width: obj.width,title:$lang_form.bpmFormDialog.previewDialog,name:'paramDialog_'});
			}
			}
		});
		if(isParamsNeeded || isPreviewCallCode) return ;
		CommonDialog(alias,function(data){
			var json=JSON2.stringify(data);
			$("#txtJsonData").val(json);
			if(!win){
				var obj=$("#divJsonData");
				win= $.ligerDialog.open({ target:obj , height: 300,width:500, modal :true}); 
			}
			win.show();
		},paramValueString);
	}
</script>
</head>
<body>
			<div class="panel">
			<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label"><spr:message code="bpmFormDialog.title"/><spr:message code="operator.list"/></span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link add" href="edit.ht"><span></span><spr:message code="menu.button.add"/></a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span><spr:message code="menu.button.alter"/></a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link del"  action="del.ht"><span></span><spr:message code="menu.button.del"/></a></div>
						</div>	
					</div>
					<div class="panel-search">
							<form id="searchForm" method="post" action="list.ht">
									<ul class="row">
											<li><span class="label"><spr:message code="bpmFormDialog.name"/>:</span><input type="text" name="Q_name_SL"  class="inputText" value="${param['Q_name_SL']}"/></li>
											<li><span class="label"><spr:message code="bpmFormDialog.alias"/>:</span><input type="text" name="Q_alias_SL"  class="inputText" value="${param['Q_alias_SL']}"/></li>
									</ul>
							</form>
					</div>
				</div>
				</div>
				<div class="panel-body">
					
					
				    	<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <display:table name="bpmFormDialogList" id="bpmFormDialogItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"   class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;">
								  	<input type="checkbox" class="pk" name="id" value="${bpmFormDialogItem.id}">
							</display:column>
							<display:column property="name" titleKey="bpmFormDialog.name" sortable="true" sortName="name"></display:column>
							<display:column property="alias" titleKey="bpmFormDialog.alias" sortable="true" sortName="alias"></display:column>
							<display:column  titleKey="bpmFormDialog.style" sortable="true" sortName="style">
							
								<c:choose>
									<c:when test="${bpmFormDialogItem.style==0}">
										<span class="green"><spr:message code="bpmFormDialog.style.list"/></span>
									</c:when>
									<c:otherwise>
										<span class="red"><spr:message code="bpmFormDialog.style.tree"/></span>
									</c:otherwise>
								</c:choose>
							</display:column>
							<display:column  titleKey="bpmFormDialog.issingle" sortable="true" sortName="issingle">
								<c:choose>
									<c:when test="${bpmFormDialogItem.issingle==0}">
										<span class="red"><spr:message code="bpmFormDialog.issingle.mult"/></span>
									</c:when>
									<c:otherwise>
										<span class="green"><spr:message code="bpmFormDialog.issingle.single"/></span>
									</c:otherwise>
								</c:choose>
							</display:column>
							<display:column property="width" titleKey="bpmFormDialog.width" sortable="true" sortName="width"></display:column>
							<display:column property="height" titleKey="bpmFormDialog.height" sortable="true" sortName="height"></display:column>
							<display:column  titleKey="bpmFormDialog.istable" sortable="true" sortName="istable">
								<c:choose>
									<c:when test="${bpmFormDialogItem.istable==0}">
										<span class="red"><spr:message code="bpmFormDialog.istable.view"/></span>
									</c:when>
									<c:otherwise>
										<span class="green"><spr:message code="bpmFormDialog.istable.table"/></span>
									</c:otherwise>
								</c:choose>
							</display:column>
							<display:column property="objname" titleKey="bpmFormDialog.objname" sortable="true" sortName="objname"></display:column>
							<display:column property="dsalias" titleKey="bpmFormDialog.dsalias" sortable="true" sortName="dsalias"></display:column>
							<display:column titleKey="list.manage" media="html" style="width:50px;text-align:center" class="rowOps">
								<f:a alias="delFormDialog" href="del.ht?id=${bpmFormDialogItem.id}" css="link del"><spr:message code="menu.button.del"/></f:a>
								<a href="edit.ht?id=${bpmFormDialogItem.id}" class="link edit"><spr:message code="menu.button.edit"/></a>
								<a href="javascript:preview('${bpmFormDialogItem.alias}')"  class="link detail"><spr:message code="menu.button.preview"/></a>
								<a href="javascript:preview('callCode-${bpmFormDialogItem.alias}')"  class="link copyTo"><spr:message code="bpmFormDialog.devHelp" /></a>
							</display:column>
						</display:table>
						<hotent:paging tableId="bpmFormDialogItem"/>
					
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
			
			<div id="divJsonData" style="display: none;">
				<div><spr:message code="bpmFormDialog.help0"/></div>
				<ul>
					<li ><spr:message code="bpmFormDialog.help1"/></li>
					<li><spr:message code="bpmFormDialog.help2"/></li>
				</ul>
				<textarea id="txtJsonData" rows="10" cols="80" style="height: 180px;width:480px"></textarea>
			</div>
</body>
</html>


