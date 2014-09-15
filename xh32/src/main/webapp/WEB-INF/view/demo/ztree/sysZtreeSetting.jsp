<%--
	time:2012-06-25 11:05:09
	desc:edit the 通用表单对话框
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/get.jsp" %>
	<%@include file="/js/tree/util/htTree.jsp" %>
	<f:js pre="js/lang/view/platform/form" ></f:js>			
	<script type="text/javascript"src="${ctx}/js/hotent/platform/system/ScriptDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/javacode/codemirror.js"></script>
	<script type="text/javascript" src="${ctx}/js/javacode/InitMirror.js"></script>
	<script type="text/javascript">
		$(function() {
				$("#defLayout").ligerLayout({ leftWidth: 270,height: '100%',
				bottomHeight:50,allowLeftCollapse:false,rightWidth:465,allowRightResize:false,centerWidth:20,
			 	allowBottomResize:false,allowRightCollapse:false});
				init();	
				//$.initHtTree($(".ztree"),null);
		});
		function preView(){
			var fields = window.dialogArguments;	
			var roottitle=$("input[name='roottitle']");
			var expandslevel=$("input[name='expandslevel']");
			var rootpid=$("input[name='rootpid']").val();
			var typeId=$("input[name='idkey']").val();
			var typeName=$("input[name='namekey']").val();
			var parentId=$("input[name='pidkey']").val();
			
			var callbacks=$("textarea[name='callbacks']").val();
			var asyncs=$("textarea[name='asyncs']").val();
			var checks=$("textarea[name='checks']").val();
			var datas=$("textarea[name='datas']").val();
			var edits=$("textarea[name='edits']").val();
			var views=$("textarea[name='views']").val();
			var options={
			
			}
			var TreeType={
				 "alias":fields.alias,
				  "tablename":fields.tablename,
				  "idkey":typeId,
				  "namekey":typeName,
				  "pidkey":parentId,
				  "rootpid":rootpid,
				  "roottitle":roottitle.val(),
				  "expandslevel":expandslevel.val()
				  ,"dataparams":"{'typeId': '"+typeId+"','typeName':'"+typeName+"','parentId':'"+parentId+"'}"
			}
			 
			options.TreeType=TreeType;
			var setting={
				callback:getFunctions(callbacks),
				async:getFunctions(asyncs,TreeType),
				 check:getFunctions(checks),
				data:getFunctions(datas),
				 edit:getFunctions(edits),
				view:{
					addHoverDom:addHoverDom,
					removeHoverDom:removeHoverDom
				}
			}
			options.setting=setting;
//   			$("#layout").html('<div class="ztree"></div>');
//  			$("#layout").attr("class","panel-top");
			var preViewDialog=$("#preViewDialog");
  			var preViewDialogHtml="<div id='preViewDialogHtml'></div>";
 			preViewDialog.html(preViewDialogHtml );
			var ztreeHtml='<div id="layout'+GetRandomNum(1,1000)+'" class="panel-top"> <div class="ztree" alias=""></div> </div>';
 			$("#preViewDialogHtml").html(ztreeHtml);
			var htTree=$.initHtTree($(".ztree"),options);
			$.ligerDialog.open({
				target : $("#preViewDialogHtml"),
				height : 400,
				width : 250,
				allowClose : false,
				modal : true,
				buttons : [{
							text : '确定',
							onclick : function(item, dialog) {
								$.fn.zTree.destroy("glTypeTree");
								dialog.close();
							}
				}]
			});	
		}
		function getFunctions(params,TreeType){
			var jsonObj=eval("("+params+")");
			var returnObj={};
			for(var param in jsonObj){
				var o=jsonObj[param];
				if(o.type=='function'){
					if(o.defaults&&o.defaults.trim()!=""){
						var fn="function("+o.arguments+"){ "+o.defaults+" }";
						returnObj[param]=eval("("+fn+")");
					}else{
						returnObj[param]=null;
					}
				}else if(o.type=='json'||o.type=='array'||o.type=='arrayOrJson'||o.type=='select'||o.type=='boolean'||o.type=='text'){
					var obj=o.defaults;
					for(var ob in obj){
						if(ob=='treeType'){
							obj[ob]=eval("("+obj[ob]+")");
						}
					}
					if(param=='url')
						o.defaults=o.defaults.replaceAll("{ctx}",__ctx);
					if(o.type=='boolean'){
						o.defaults=eval("("+o.defaults+")");
					}
					returnObj[param]= o.defaults ;
				}else if(o.type=='jsonChildren'){
					returnObj[param]=getFunctions(JSON.stringify(o.value));
				}else{
					returnObj[param]= o.defaults ;
				}
			}
			return returnObj;
		}
		function getParamObj(param,option){
			var callbacksParams=["beforeAsync", "beforeClick", "beforeDblClick", "beforeRightClick", "beforeMouseDown", "beforeMouseUp", "beforeExpand", "beforeCollapse", "beforeRemove", "onAsyncError", "onAsyncSuccess", "onNodeCreated", "onClick", "onDblClick", "onRightClick", "onMouseDown", "onMouseUp", "onExpand", "onCollapse", "onRemove"];
			if($.inArray(option,callbacksParams)!=-1){
				var fn="function(){"+param+"}";
				return eval("("+fn+")");
			}else{
				return param;
			}
			
		}
		function addParam(){
			var option=$("#addSelect").val();
			var preHtml=$("#preHtml").val();
			var param=$("#param").val();
			var obj=getFunctions(preHtml);
			obj[option]=getParamObj(param,option);
			$("#preHtml").val(JsonUtil.__writeObj(obj,0));
		}
		function getAddHtml(addHtml,params,v){
			//继续调试。。
			if(v.type=="function"){
				addHtml+="<div><span  class='params'>"+params+":</span>function(<span class='arguments'>"+v.arguments+"</span>){<span class='codeoc'></span><br><textarea class='function' name='"+params+"'>"+(v.defaults==null?"":v.defaults)+"</textarea>}</div>";
			}else if(v.type=="boolean"||v.type=="select"){
				addHtml+="<div><span  class='params'>"+params+":</span><select class='select' name='"+params+"'>";
				var options="";
				for(var o in v.values){
					var isSelected=v.values[o]==v.defaults;
					 if(v.type=="boolean") isSelected=v.values[o]==eval("("+v.defaults+")");
					options+="<option value='"+v.values[o]+"' "+(isSelected?"selected":"")+">"+v.values[o]+"</option>"
				}
				addHtml+=options+"</select></div>";
			}else if(v.type=="text"){
				addHtml+="<div><span  class='params'>"+params+":</span><input class='text' name='"+params+"' value='"+v.defaults+"'/></div>";
			}else if(v.type=="array"){
				addHtml+="<div><span  class='params'>"+params+":</span>[<input class='array' name='"+params+"' value='"+v.defaults+"'/>]</div>";
			}else if(v.type=="arrayOrJson"){
				//var defaults=v.defaults;
				addHtml+="<div><span  class='params'>"+params+":</span><input class='arrayOrJson' name='"+params+"' value='"+JSON.stringify(v.defaults)+"'/></div>";
			}else if(v.type=="number"){
				addHtml+="<div><span  class='params'>"+params+":</span><input  class='number' name='"+params+"' value='"+v.defaults+"'/></div>";
			}else if(v.type=="jsonChildren"){
				var vc=v.value;
				addHtml+="<div name='"+params+"'  class='jsonChildren'><div><span class='params'>"+params+":</span></div>"
				for(var c in vc){
					var cp=vc[c];
					addHtml+="";//添加空格
					addHtml=getAddHtml(addHtml,c,cp);
				}
				addHtml+="<div></div></div>";
			}
			return addHtml;
		}
		function addAttr(name){
			var selectDialog=$("#selectDialog");
			var selectDialogHtml="<div id='selectDialogHtml'></div>";
			selectDialog.html(selectDialogHtml);
			var jsonHtml=$("#"+name).val();
			var jsonObj=eval("("+jsonHtml+")");
			var addHtml="<div class='addHtml'><input id='jsonHtml' type='hidden' value='"+jsonHtml+"'>";
			for(var params in jsonObj){
				var v=jsonObj[params];
				addHtml=getAddHtml(addHtml,params,v);
			}
			addHtml+="</div>";
			$("#selectDialogHtml").append("<br/><div id='preHtml'></div>");
			$("#preHtml").html(addHtml);
			var imageURL="url("+__ctx+"/styles/default/images/codec.png)";
			$(".codeoc").css({"background-image":""+imageURL+"","display": "inline-block", "width":"14px", "height":"14px", "margin-left":"2px", "margin-bottom":"-2px"});
			$(".jsonChildren").children("div:gt(0)").css({"margin-left":"40px"});
			$("#selectDialogHtml").delegate(".function,.select,.text,.array,.arrayOrJson,.number","blur",function(){
				var me=$(this);
				var type=me.attr("class");
				var param=me.attr("name");
				var defaults=me.val();
				var meParentDiv=me.parent().parent(".jsonChildren");
				if(meParentDiv.length>0){
					var parentName=meParentDiv.attr("name");
					jsonObj[parentName][param]['defaults']=getDefaults(defaults);
				}else{
					jsonObj[param]['defaults']=getDefaults(defaults);
				}
				function getDefaults(defaults){
					if(type=="function"){
						if(defaults.trim()==""){
							return "";
						}
					}else if(type=="array"){
						return defaults.split(",");
					}else if(type=='arrayOrJson'){
						if(!(defaults instanceof Object)){
							return eval("("+defaults+")");
						}
					}else if(type=='boolean'){
						return eval("("+defaults+")");
					}
					return defaults;
				}
				
				$("#jsonHtml").val(JSON.stringify(jsonObj));
			})
// 			selectDialog.html(selectDialogHtml );
//         	var mySelect = document.createElement("select");
//         	mySelect.id="addSelect";
//         	$("#selectDialogHtml").append($(mySelect));
//         	$("#selectDialogHtml").append("属性值<textarea id='param' rows='5'></textarea><input type='button' value='add' onclick='javascript:addParam();'>");
//         	$("#selectDialogHtml").append("<br/>预览效果<textarea id='preHtml' rows='10' cols='70' readonly='readonly'></textarea>");
//         	var obj=document.getElementById('addSelect'); 
//         	$("#preHtml").val(JsonUtil.__writeObj(eval("("+$("#"+name).val()+")"),1));
//         	var params=getParams(name);
//         	for(var i in params){
//         		obj.options.add(new Option(params[i],params[i])); 
//         	}
        	$.ligerDialog.open({
				target : $("#selectDialogHtml"),
				height : 300,
				width : 500,
				allowClose : false,
				modal : true,
				buttons : [{
							text : '确定',
							onclick : function(item, dialog) {
								$("#"+name).val($("#jsonHtml").val());
								dialog.close();
							}
				}]
			});	
		}
		function getParams(name){
			var callbacksParams=["beforeAsync", "beforeClick", "beforeDblClick", "beforeRightClick", "beforeMouseDown", "beforeMouseUp", "beforeExpand", "beforeCollapse", "beforeRemove", "onAsyncError", "onAsyncSuccess", "onNodeCreated", "onClick", "onDblClick", "onRightClick", "onMouseDown", "onMouseUp", "onExpand", "onCollapse", "onRemove"];
			var asyncsParams=["enable", " contentType", " type", " dataType", " url", " autoParam", " otherParam", " dataFilter"];
			var datasParams=["key","simpleData","keep"];
			var viewsParams=["addDiyDom", " autoCancelSelected ", " dblClickExpand ", " expandSpeed ", " fontCss ", " nameIsHTML ", " selectedMulti ", " showIcon ", " showLine ", " showTitle"];
			var checksParams=["enable", "autoCheckTrigger ", "chkStyle ", "nocheckInherit ", "chkDisabledInherit ", "radioType ", "chkboxType "];
			var editsParams=["enable ", "editNameSelectAll ", "showRemoveBtn ", "showRenameBtn ", "removeTitle ", "renameTitle ", "drag"];
			return eval(name+"Params");
		}
		function setRootPid(event, treeId, treeNode){
			if((treeNode.typeId+"").length<=14){
				$("input[name='rootpid']").val(treeNode.typeId);
			}else{
				$("input[name='rootpid']").val("");
			}	
		}
		function addHoverDom(treeId, treeNode) {
			var sObj = $("#" + treeNode.tId + "_span");
			if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
			var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
				+ "' title='设置为根节点' onfocus='this.blur();'></span>";
			sObj.after(addStr);
			var btn = $("#addBtn_"+treeNode.tId);
			if (btn) btn.bind("click", function(){
				setRootPid(null,treeId,treeNode);
				//var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				//zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
				return false;
			});
		};
		function removeHoverDom(treeId, treeNode) {
			console.log("remove   " + "#addBtn_"+treeNode.id);
			$("#addBtn_"+treeNode.tId).unbind().remove();
		};
		function init(){
			$("input.treeField").focus(function(){
				curField=$(this);
			});
			var fields = window.dialogArguments;	
		}

		function setField(){
			var checkedKey=$("input[name='checkedKey']:checked");
			var selectInput=$("input[name='"+checkedKey.attr('filed')+"']");
			$("input[name='fieldName']:checked").each(function(){
				var fieldName=$(this).val();  
				selectInput.val(fieldName);
			});
			var dataparams=$.parseJSON($("#dataparams").val());
			dataparams.typeId=$("input[name='idkey']").val();
			dataparams.typeName=$("input[name='namekey']").val();
			dataparams.parentId=$("input[name='pidkey']").val();
			$("#dataparams").val(JSON.stringify(dataparams));
		}
		function selectForm(){
			var dataparams=$("#dataparams").val();
			var roottitle=$("input[name='roottitle']").val();
			var expandslevel=$("input[name='expandslevel']").val();
			var rootpid=$("input[name='rootpid']").val();
			
			var backParams={};
			$(".backParam").each(function(){
				var name=$(this).attr('name');
				var val=$(this).val();
				backParams[name]=val;
			})
			window.returnValue=backParams;
	    	window.close();
			
	    }
	</script>
	<style type="text/css">
		body{ padding:2px; margin:0 0 0 0;overflow: hidden; }
		div.content{height:110px;overflow: auto;}
		ul.btnContainer{text-align: center;}
		.l-layout-content{width:100% !important;}
		.l-layout-right{left:319px;height:480px;}
		.l-layout-right .l-layout-header-inner {padding-left : 0px;}
		.l-layout-center{height:480px;}
		/* tab样式 */
		.tab-box {width:99%; height:99%; margin:1px;}
		.tagMenu {height:20px; line-height:20px; background:#efefef; position:relative; border-bottom:1px solid #999}
		.tab-content .table-grid{margin-top: 0px;}
		.tagMenu ul {position:absolute; left:5px; bottom:-1px; height:26px;}
		ul.tab-menu li {float:left; margin-bottom:1px; line-height:16px; height:14px; margin:5px 0 0 -1px; text-align:center; padding:0 12px; cursor:pointer}
		ul.tab-menu li.current {border:1px solid #999; border-bottom:none; background:#fff; height:25px; line-height:26px; margin:0}
		.javaField, .helpField {border: 3px solid #DDDDDD;height:200px;float:left;width:98%;}
		.helpField {padding:0 5px 0 10px;width:95%;display:none;}
		.fieldBtnDiv {float:left;display:none;}
		.fieldBtnDiv .button{margin:0 2px 2px 2px;}
		
		.divX {line-height:7px;width:7px;font-weight:bold;cursor:pointer;color:red;display: none;position:relative;float:left;}
		.table-detail .inputText {width:140px;}
		
		.tlabel { min-width:120px; display:inline-block; line-height:30px;}
		.nr { margin-left:20px;}
		.params { min-width: 120px; display: inline-block;}
		.function {margin-left: 120px; width: 71%;}
		.select { min-width: 150px;}
		.text, .arrayOrJson { width: 70%; margin-left: 2px; padding-left:5px;}
		.array { width: 69%; padding-left:5px;}
		.addHtml div { margin-bottom: 5px;}
		.number { width: 145px; margin-left: 2px;}
	</style>
	
</head>
<body>
		<div id="defLayout" >
	            <div position="left" title="<spr:message code='bpmFormQuery.setting.fieldList'/>" style="overflow: scroll;width: 270px;height:450px;">
					<table cellpadding="1" class="table-grid table-list" cellspacing="1">
						<tr>
							<th></th>
							<th><spr:message code="bpmFormQuery.setting.field"/></th>
							<th><spr:message code="bpmFormQuery.setting.desc"/></th>
						</tr>
						<c:forEach var="col" items="${tableModel.columnList }" varStatus="status">
						<c:set var="clsName"  ><c:choose><c:when test="${status.index%2==0}">odd</c:when><c:otherwise>even</c:otherwise></c:choose> </c:set>
						<tr class="${clsName}">
							<td>
								<c:choose>
	            					<c:when test="${style==0 }">
										<input type="Radio" name="fieldName"  class="pk"  value="${col.name }"  dbType="${col.columnType }">
									</c:when>
									<c:otherwise>
										<input type="Radio" name="fieldName" class="pk"  value="${col.name }" id="${col.name }" dbType="${col.columnType }">
									</c:otherwise>
								</c:choose>
							</td>
							<td nowrap="nowrap">
								${col.name }
							</td>
							<td>
								<input type="text" name="comment" class="inputText" value="${col.comment }">
							</td>
						</tr>
						</c:forEach>
					</table>
	            </div>
	            <div position="center" >
           			<ul class="btnContainer">
	          			<li class="btn">
	          				 <a href='#' class='button'  onclick="setField()" ><span >=></span></a>
	          			</li>
	          		</ul>
	            </div>  
	            <div id="fieldSetting" position="right"  title="<spr:message code='bpmFormQuery.setting.fieldSet'/>">
	                <input type='hidden' title="istable" name="istable" value="${sysZtree.istable}" readonly="readonly" class='backParam'/>
	            	<div><input type='Radio' name="checkedKey" filed="idkey"/><span class="tlabel">idKey:</span><input type='text' title="设置IDKey" name="idkey" value="${sysZtree.idkey}" readonly="readonly" class='backParam'/></div>
	            	<div><input type='Radio' name="checkedKey" filed="namekey"/><span class="tlabel">nameKey:</span><input type='text' title="设置nameKey" name="namekey" value="${sysZtree.namekey}" readonly="readonly" class='backParam'/></div>
	            	<div><input type='Radio' name="checkedKey" filed="pidkey"/><span class="tlabel">pidKey:</span><input type='text' title="设置pidKey" name="pidkey" value="${sysZtree.pidkey}" readonly="readonly" class='backParam'/></div>
	            	<div><span class="tlabel nr">自定义根节点名称: </span><input type="text" id="roottitle" name="roottitle" value="${sysZtree.roottitle}"  class='backParam'/></div>
		            <div><span class="tlabel nr">展开的层数:</span><input type="text" id="expandslevel" name="expandslevel" value="${sysZtree.expandslevel}"  class='backParam' /></div>
		            <div><span class="tlabel nr">根节点ID:</span><input type="text" id="rootpid" name="rootpid" value="${sysZtree.rootpid}"   readonly="readonly"   class='backParam'/></div>
		            <div><span class="tlabel nr">callbacks:</span><input  type="button" class="button" value="add" title="添加属性" onclick="javascript:addAttr('callbacks')"/> <textarea id="callbacks" name="callbacks"  rows="2" cols="50" style="overflow:auto;display:none;"  class='backParam'>${f:getDefaultDatas("callbacks",sysZtree.callbacks)} </textarea></div>
		            <div><span class="tlabel nr">asyncs:</span><input  type="button" class="button" value="add" title="添加属性" onclick="javascript:addAttr('asyncs')"/><textarea id="asyncs" name="asyncs"  rows="2" cols="50" style="overflow:auto;display:none;"  class='backParam'>${f:getDefaultDatas("asyncs",sysZtree.asyncs)} </textarea></div>
		            <div><span class="tlabel nr">checks:</span><input  type="button" class="button" value="add" title="添加属性" onclick="javascript:addAttr('checks')"/><textarea id="checks" name="checks"    rows="2" cols="50" style="overflow:auto;display:none;"  class='backParam'>${f:getDefaultDatas("checks",sysZtree.checks)}  </textarea></div>
		            <div><span class="tlabel nr">datas:</span><input  type="button" class="button" value="add" title="添加属性" onclick="javascript:addAttr('datas')"/><textarea id="datas" name="datas"   rows="2" cols="50" style="overflow:auto;display:none;"  class='backParam'> ${f:getDefaultDatas("datas",sysZtree.datas)}</textarea></div>
		            <div><span class="tlabel nr">edits:</span><input  type="button" class="button" value="add" title="添加属性" onclick="javascript:addAttr('edits')"/><textarea id="edits" name="edits"      rows="2" cols="50" style="overflow:auto;display:none;"  class='backParam'> ${f:getDefaultDatas("edits",sysZtree.edits)}</textarea></div>
		            <div><span class="tlabel nr">views:</span><input  type="button" class="button" value="add" title="添加属性" onclick="javascript:addAttr('views')"/><textarea id="views" name="views"  rows="2" cols="50" style="overflow:auto;display:none;"  class='backParam'>${f:getDefaultDatas("views",sysZtree.views)} </textarea></div>
		            <c:choose>
						 <c:when test="${not empty sysZtree.dataparams}">
						 <input type="hidden" width="200px" name="dataparams" id="dataparams" value='${sysZtree.dataparams}'  class='backParam'/>
						 </c:when>
						 <c:otherwise>
						 <input type="hidden" width="200px" name="dataparams" id="dataparams" value='{"typeId": "","typeName":"","parentId":""}'  class='backParam'/>
						 </c:otherwise>
					</c:choose>
					 <div id="preViewDialog" style='display:none'></div>
					 <div id="selectDialog" style='display:none'></div>
	       	  </div>
       	      <div position="bottom" class="bottom" style="padding-top: 15px;">
       	      		 <a href='#' class='button'  onclick="preView()" ><span class="icon ok"></span><span >预览</span></a>
            	     <a href='#' class='button'  onclick="selectForm()" style='margin-left:10px;' ><span class="icon ok"></span><span ><spr:message code="menu.button.ok"/></span></a>
			  		 <a href='#' class='button' style='margin-left:10px;' onclick="window.close()"><span class="icon cancel"></span><span ><spr:message code="menu.button.cancel"/></span></a>
			  </div>
       	 
</body>
</html>
