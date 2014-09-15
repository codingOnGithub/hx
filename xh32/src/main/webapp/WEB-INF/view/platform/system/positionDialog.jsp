<%@page pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp"%>
<f:js pre="js/lang/view/platform/selector" ></f:js>
<title><spr:message code="position.selector.title"/></title>
<f:link href="tree/zTreeStyle.css"></f:link>
<style type="text/css">
html {
	overflow: hidden;
}

.ztree {
	overflow: auto;
}

.label {
	color: #6F8DC6;
	text-align:left;
	padding-right: 6px;
	padding-left: 0px;
	font-weight: bold;
}
	.panel-search {
    min-width: 321px;
    padding-bottom: 3px;
    padding-left: 0px;
    padding-top: 3px;
}
.l-layout-right{left:578px;}
</style>
<script type="text/javascript"
	src="${ctx}/js/tree/jquery.ztree.js"></script>
<script type="text/javascript">
		var isSingle='${param.isSingle}';
		var orgTree = null;
		findStr = '';
		//树展开层数
		var expandDepth = 2; 
		forbidF5("Chrome");//禁止刷新页面
		$(function() {
			$("#defLayout").ligerLayout({
				leftWidth: 190,
				allowRightResize:false,
				allowLeftResize:false,
				allowTopResize:false,
				allowBottomResize:false,
				height: '90%',
				minLeftWidth:170,
				rightWidth:170
			});
			
			$('#demensionId').change(function(){
        		var demensionId=$(this).val();
        		loadTree(demensionId);
             });
			//首先加载行政维度的数据。
			loadTree(1);
			
			//快速查找
			handQuickFind();
			
			initData();
		});
		
		function handQuickFind(){
			$("input.quick-find").bind('keyup',function(){
				var str = $(this).val();
				if(!str)return;
				if(str==findStr)return;
				findStr = str;
				var  tbody = $("#orgList"), 	
					 firstTr = $('tr.hidden',tbody);
				$("tr",tbody).each(function(){
					var me = $(this),
						span = $('span',me),
						spanStr = span.html();
					if(!spanStr)return true;						
					if(spanStr.indexOf(findStr)>-1){
						$(this).insertAfter(firstTr);
					}
				});
			});
		}
		
		


		//展开收起
		function treeExpandAll(type) {
			orgTree = $.fn.zTree.getZTreeObj("orgTree");
			orgTree.expandAll(type);
		};
		
		function treeExpand() {
			orgTree = $.fn.zTree.getZTreeObj("orgTree");
			var treeNodes = orgTree.transformToArray(orgTree.getNodes());
			for(var i=1;i<treeNodes.length;i++){
				if(treeNodes[i].children){
					orgTree.expandNode(treeNodes[i], true, false, false);
				}
			}
		};
		
		function treeFlesh(){
			var demId=$("#demensionId").val();
			loadTree(demId);
		}
		
		function loadTree(value) {
			var setting = {
					data: {
						key : {
							name: "orgName"
						},
					
						simpleData: {
							enable: true,
							idKey: "orgId",
							pIdKey: "orgSupId",
							rootPId: 0
						}
					},
					async: {
						enable: true,
						url:"${ctx}/platform/system/sysOrg/getTreeData.ht?demId="+value,
						autoParam:["orgId","orgSupId"]
					},
					callback:{
						onClick : treeClick,
						onAsyncSuccess: orgTreeOnAsyncSuccess
					}
					
				};
				orgTree=$.fn.zTree.init($("#orgTree"), setting);
		};
		//判断是否为子结点,以改变图标	
		function orgTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
			if(treeNode){
		  	 	var children=treeNode.children;
			  	 if(children.length==0){
			  		treeNode.isParent=true;
			  		orgTree = $.fn.zTree.getZTreeObj("orgTree");
			  		orgTree.updateNode(treeNode);
			  	 }
			}
		};
		//选择分类
		function getSelectNode() {
			orgTree = $.fn.zTree.getZTreeObj("orgTree");
			var nodes = orgTree.getSelectedNodes();
			var node = nodes[0];
			if (node == null || node.orgId == 0)
				return '';
			return node.orgId;
		}
	
		function treeClick(event, treeId, treeNode) {
			//取得组织id
			var orgId = getSelectNode();
			var demId = $("#demensionId").val();
			var url = "${ctx}/platform/system/position/selector.ht?orgId=" + orgId + "&demId=" + demId+"&isSingle=${param.isSingle}";
			$("#posFrame").attr("src", url);
			//setOrgId(orgId,demId);
		}
		
		function setOrgId(orgId,demId){
			$("#orgId").val(orgId);
			$("#demId").val(demId);
		}
		
		function dellAll() {
			$("#posList").empty();
		};
		function del(obj) {
			var tr = $(obj).closest("tr");
			$(tr).remove();
		};
		
		//初始化父级窗口传进来的数据
		function initData(){
			var obj = window.dialogArguments;
			if(obj&&obj.length>0){
				for(var i=0,c;c=obj[i++];){
					var data = c.id+'#'+c.name;
					if(c.name!=undefined&&c.name!="undefined"&&c.name!=null&&c.name!=""){
						add(data);
					}
				}
			}
		};
		//list列表添加数据
		function add(data) {
			var aryTmp=data.split("#");
			var posId=aryTmp[0];
			var len= $("#pos_" + posId).length;
			if(len>0) return;
			var roleTemplate= $("#posTemplate").val();
			var html=roleTemplate.replace("#posId",posId)
					.replace("#data",data)
					.replace("#name",aryTmp[1]);
			$("#posList").append(html);
		};
	       //子窗口会调用
		function selectMulti(obj) {
			if ($(obj).attr("checked") == "checked"){
				var data = $(obj).val();
				add(data);
			}	
		};
		//选择按钮
		function  selectPosition(){
			var pleaseSelect= $lang_selector.position.pleaseSelect;
			var aryPos;
			if(isSingle=='true'){
				aryPos = $('#posFrame').contents().find(":input[name='posId'][checked]");
			}else{
				aryPos =$("input[name='pos']", $("#posList"));
			}
			if(aryPos.length==0){
				alert(pleaseSelect);
				return;
			}
			var aryId=[];
			var aryName=[];
			var posJson = [];
			aryPos.each(function(){
				var data=$(this).val();
				var aryTmp=data.split("#");
				aryId.push(aryTmp[0]);
				aryName.push(aryTmp[1]);
				posJson.push({id:aryTmp[0],name:aryTmp[1]});
			});
			var posIds=aryId.join(",");
			var posNames=aryName.join(",");
			
			var obj={};
			obj.posId=posIds;
			obj.posName=posNames;
			obj.posJson = posJson;
			window.returnValue=obj;
			window.close();
		}
		
		//清空岗位
		function clearPosition(){
			window.returnValue={posId:'',posName:''};
			window.close();
		}
		function treeFresh() {
			loadTree(1);
			setTimeout(function() {
				curStatus = true;
				treeExpandAll(false, true);
			}, 100);
		}

	</script>
	<style type="text/css">
		html { overflow-x: hidden; }
		.ztree {
			overflow: auto;
		}
		
		.label {
			color: #6F8DC6;
			text-align: right;
			padding-right: 6px;
			padding-left: 0px;
			font-weight: bold;
		}
				.panel-search {
    min-width: 321px;
    padding-bottom: 3px;
    padding-left: 0px;
    padding-top: 3px;
}
.l-layout-right{left:578px;}
	</style>
</head>
<body>
	<div id="defLayout">
		<div  position="left" title="<spr:message code='inf.js.orgtree'/>" style="float: left;width: 100% !important;height:95%;;">
			 <div style="width:100%;">
		        <select id="demensionId"  style="width:99.8% !important;">  
		              <c:forEach var="dem" items="${demensionList}">  
		              	<option  value="${dem.demId}" <c:if test="${dem.demId==1 }">selected='selected'</c:if> >${dem.demName}</option>  
		              </c:forEach>  
		        </select>
	         </div>

			<div class="tree-toolbar">
				<span class="toolBar">
					<div class="group">
						<a class="link reload" id="treeFresh" href="javascript:treeFlesh();"></a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link expand" id="treeExpandAll"
							href="javascript:treeExpand()"></a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link collapse" id="treeCollapseAll" href="javascript:treeExpandAll(false)"></a>
					</div>
				</span>
			</div>
			<ul id="orgTree" class="ztree" style="overflow:auto;height:89%;"></ul>
		</div>
		<div position="center">
			<div class="l-layout-header"><spr:message code="position.selector.list"/></div>
			<iframe id="posFrame" name="posFrame" height="95%" width="100%"  
			frameborder="0" src="${ctx}/platform/system/position/selector.ht?isSingle=${param.isSingle}"></iframe>
		</div>
		<c:if test="${param.isSingle==false}">
			   <div position="right" title="<span><a onclick='javascript:dellAll();' class='link del'><spr:message code='menu.button.empty'/> </a><input type='text' class='quick-find' title='<spr:message code='menu.button.search'/>'/></span>" style="overflow: auto;height:95%;width:170px;">
          			<table width="145"   class="table-grid table-list"  cellpadding="1" cellspacing="1">
          				<tbody id="posList">
          					<tr class="hidden"></tr>
          				</tbody>
					</table>
			  </div>
		</c:if>
	</div>
	 <div position="bottom"  class="bottom" style='margin-top:10px;'>
		<a href='#' class='button'  onclick="selectPosition()" style="margin-right:10px;"><span class="icon ok"></span><span><spr:message code="menu.button.choose"/></span></a>
		<a href="#" class="button"  onclick="clearPosition()"><span class="icon cancel" ></span><span class="cance" ><spr:message code="menu.button.empty"/></span></a>
		<a href='#' class='button' style='margin-left:10px;'  onclick="window.close()"><span class="icon cancel"></span><span><spr:message code="menu.button.cancel"/></span></a>
	</div>
	<textarea style="display: none;" id="posTemplate">
		<tr id="pos_#posId">
	  			<td>
	  				<input type="hidden" name="pos" value="#data"><span>#name</span>
	  			</td>
	  			<td style="width: 30px;" nowrap="nowrap"><a onclick="javascript:del(this);" class="link del" title="<spr:message code='menu.button.del'/>">&nbsp;</a> </td>
	  	</tr>
	</textarea>
</body>
</html>