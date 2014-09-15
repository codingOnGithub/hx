<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" import="com.hotent.platform.model.system.Resources"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
<title><spr:message code="resources"/><spr:message code="title.dialog"/></title>
<f:js pre="js/lang/view/platform/system" ></f:js>
<f:link href="tree/zTreeStyle.css"></f:link>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerComboBox.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerWindow.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/IconDialog.js"></script>
<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=resources"></script>
<script type="text/javascript">

	var rootId=0;
	//当前访问系统
	var systemId=null;
	var winArgs = window.dialogArguments;
	$(function(){
		$("#defaultUrl").val(winArgs.addUrl);
		$("#resName").val(winArgs.name);
		
		//布局
		loadLayout();
		//加载树
		systemId=$("#subSystem").val();
		loadTree();
		//改变子系统
		$("#subSystem").change(function(){
			systemId=$("#subSystem").val();
			loadTree();
		});
		
		//-----Center Area
		function showRequest(formData, jqForm, options) { 
			return true;
		}
		valid(showRequest,showResponse);
		$("a.save").click(function() {
			var parentId = $("#parentId").val();
			if(!parentId){
				$.ligerDialog.warn($lang_system.resources.addResource.warn_msg_no_parent,$lang.tip.warn);
				return;
			}
			$('#resourcesForm').submit(); 
		});
		
		
	});

	//布局
	function loadLayout(){
		$("#layout").ligerLayout( {
			leftWidth : 300,
			height: 550,
			onHeightChanged: heightChanged,
			allowLeftResize:false
		});
		//取得layout的高度
        var height = $(".l-layout-center").height();
        $("#resourcesTree").height(height-90);
	};
	//布局大小改变的时候通知tab，面板改变大小
    function heightChanged(options){
     	$("#resourcesTree").height(options.middleHeight -90);
    };
    //树
	var resourcesTree;
	//加载树
	function loadTree(){
		var setting = {
			async: {enable: false},
			data: {
				key:{name:"resName"},
				simpleData: {
					enable: true,
					idKey: "resId",
					pIdKey: "parentId",
					rootPId: <%=Resources.ROOT_PID%>
				}
			},
			view: {
				selectedMulti: false
			},
			callback:{
				onClick: zTreeOnLeftClick
			}
		};
		var url="${ctx}/platform/system/resources/getSystemTreeData.ht";
		var params={systemId:systemId};
		$.post(url,params,function(result){
			resourcesTree=$.fn.zTree.init($("#resourcesTree"), setting,result);
			resourcesTree.expandAll(true);
		});
	
	};


	//左击
	function zTreeOnLeftClick(event, treeId, treeNode){
		var isfolder=treeNode.isFolder;
		var msg=$("#msg");
		if(isfolder==1){
			msg.text('');
		}else if(isfolder==0){
			msg.text($lang_system.resources.addResource.warn_msg_column_parent);
			return;
		}
		var resId=treeNode.resId;
		$("#parentId").val(resId);
	};
	
	//展开收起
	function treeExpandAll(type){
		resourcesTree = $.fn.zTree.getZTreeObj("resourcesTree");
		resourcesTree.expandAll(type);
	};

	//选择资源节点。
	function getSelectNode(){
		resourcesTree = $.fn.zTree.getZTreeObj("resourcesTree");
		var nodes  = resourcesTree.getSelectedNodes();
		var node   = nodes[0];
		return node;
	}
	//刷新
	function reFresh(){
		loadTree();
	};
	
	//添加完成后调用该函数
	function addResource(id,text,icon,isFolder){
		var parentNode=getSelectNode();
		resourcesTree = $.fn.zTree.getZTreeObj("resourcesTree");
		var treeNode= {resId:id,parentId:parentNode.resId,resName:text,icon:icon,isFolder:isFolder};
		resourcesTree.addNodes(parentNode,treeNode);
	}
	//编辑完成后调用该函数。
	function editResource(text,icon,isFolder){
		var selectNode=getSelectNode();
		selectNode.resName=text;
		selectNode.icon=icon;
		selectNode.isFolder=isFolder;
		resourcesTree = $.fn.zTree.getZTreeObj("resourcesTree");
		resourcesTree.updateNode(selectNode);
	}

	function showResponse(responseText){
		var json=eval("("+responseText+")");
		if(json.result==1){
			var resName=$("#resName").val();
			var isFolder=$("#isFolder").val();
			var icon=$("#icon").val();
			parent.addResource(json.resId,resName,icon,isFolder);
			$.ligerDialog.alert($lang_system.resources.addResource.success_msg,$lang.tip.msg,"success",function(rtn){
				window.close();
			});
		}
		else{
			$.ligerDialog.err($lang.tip.error,$lang_system.resources.addResource.error_msg,json.message);
		}
	}
	
	
	function selectIcon(){
		 IconDialog({callback:function(src){
			$("#icon").val(src);
			$("#iconImg").attr("src",src);
			$("#iconImg").show();
		}});
	};
	
</script>
<style type="text/css">
	.tree-title{overflow:hidden;width:8000px;}
	.ztree{overflow: auto;}
</style>
</head>
<body>
<div id="layout">
	<div id="left" position="left" title='<spr:message code="res.res.man"/>' >
		<div style="width:100%;">
		        <select id="subSystem" style="width:99.8% !important;">  
		              <c:forEach var="subSystemItem" items="${subSystemList}">  
		         			<option value="${subSystemItem.systemId}" 
		         			<c:choose>
		         				<c:when test="${subSystemItem.systemId==currentSystemId}">
		         					selected="selected"
		         				</c:when>
		         			</c:choose>>${subSystemItem.sysName}</option>  
		        	  </c:forEach>  
		        </select>
		  </div>
		<div class="tree-toolbar tree-title" id="pToolbar">
			<span class="toolBar">
				<div class="group"><a class="link reload" id="treeFresh" href="javascript:reFresh();"><spr:message code="tree.refresh"/></a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link expand" id="treeExpandAll" href="javascript:treeExpandAll(true)"><spr:message code="tree.expand"/></a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link collapse" id="treeCollapseAll" href="javascript:treeExpandAll(false)"><spr:message code="tree.collapse"/></a></div>
			</span>
		</div>
		<ul id="resourcesTree" class="ztree"></ul>
	</div>
    
	<div position="center">
		<div id="addResourceDiv">
		<form id="resourcesForm" method="post" action="save.ht">
			<div class="panel">
					<div class="panel-top">
						<div class="tbar-title">
							<span class="tbar-label"><spr:message code="res.sub.add"/></span>
						</div>
						<div class="panel-toolbar">
							<div class="toolBar">
								<div class="group"><a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.save"/></a></div>
								<div class="l-bar-separator"></div>
								<div><span style="margin-left: 50px" class="red" id="msg"></span></div>
							</div>
						</div>
					</div>
					<div class="panel-body">
							<table id="resourcesTable" class="table-detail" cellpadding="0" cellspacing="0" border="0">
									<tr>
										<th width="20%"><spr:message code="resources.resName"/>:  <span class="required">*</span></th>
										<td><input type="text" id="resName" name="resName"   class="inputText"/></td>
									</tr>
									<tr>
										<th width="20%"><spr:message code="resources.alias"/>: </th>
										<td><input type="text" id="alias" name="alias" class="inputText"/></td>
									</tr>
									
									<tr>
										<th width="20%"><spr:message code="resources.icon"/>: </th>
										<td>
											<input type="hidden" id="icon" name="icon"  class="inputText"/>
											<img id="iconImg" alt="" style="display:none;">
											<a class="link detail" href="javascript:selectIcon();"><spr:message code="resources.addResource.icon.select"/></a>
										</td>
									</tr>
									<tr>
										<th width="20%"><spr:message code="resources.defaultUrl"/>: </th>
										<td><input type="text" id="defaultUrl" name="defaultUrl" size="60"  class="inputText" readonly="readonly"/></td>
									</tr>
									<tr>
										<th width="20%"><spr:message code="res.is.bar"/>: </th>
										<td>
											<select id="isFolder" name="isFolder">
												<option value="0" ><spr:message code="no"/></option>
												<option value="1" ><spr:message code="yes"/></option>
											</select>
										</td>
									</tr>
									<tr>
										<th width="20%"><spr:message code="resources.isDisplayInMenu"/>: </th>
										<td>
											<select id="isDisplayInMenu" name="isDisplayInMenu">
												<option value="0" ><spr:message code="no"/></option>
												<option value="1" selected="selected" ><spr:message code="yes"/></option>
											</select>
										</td>
									</tr>
									<tr>
										<th width="20%"><spr:message code="resources.isOpen"/>: </th>
										<td>
											<select id="isOpen" name="isOpen">
												<option value="0" ><spr:message code="no"/></option>
												<option value="1" selected="selected" ><spr:message code="yes"/></option>
											</select>
										</td>
									</tr>
									
									<tr style="display: none;">
										<th width="20%"><spr:message code="resources.sn"/>: </th>
										<td><input type="text" id="sn" name="sn" value="1" class="inputText"/></td>
									</tr>
									<tr style="display: none;">
										<th width="20%"><spr:message code="resources.parentId"/>: </th>
										<td><input type="text" id="parentId" name="parentId"  class="inputText"/></td>
									</tr>
									<tr style="display: none;">
										<th width="20%">systemId: </th>
										<td><input type="text" id="systemId" name="systemId" class="inputText" value="${currentSystemId}"/></td>
									</tr>
									<input type="hidden" id="resId" name="resId" />
							</table>
					</div>
			</div>
			</form>
		</div>
	</div>
</div>
<div position="bottom" class="bottom" style="padding-top: 15px;">
<!-- <a href='#' class='button'  onclick="saveForm()" ><span class="icon ok"></span><span >确定</span></a> -->
 	<a href='#' class='button' style='margin-left:10px;' onclick="window.close()"><span class="icon cancel"></span><span ><spr:message code="menu.button.close"/></span></a>
</div>
</body>
</html>

