( function($){
	$.fn.htTree = function (options){
		var $self=this;
		var $$=$(this);
		$(".tree-title").css({"overflow":"hidden","width":"100%"});
		$("html,body").css({ "padding":"0px", "margin":"0", "width":"100%","height":"100%","overflow": "hidden"} );
		var $leftTree=$('<div position="left" style="overflow: auto;float:left;width:100%"><div class="tree-toolbar"> <span class="toolBar"> <div class="group"><a class="link reload" id="treeFresh"></a></div><div class="l-bar-separator"></div><div class="group"><a class="link expand" id="treeExpandAll"></a></div><div class="l-bar-separator"></div><div class="group"><a class="link collapse" id="treeCollapseAll" ></a></div></span></div><ul id="glTypeTree" class="ztree"></ul></div>');
		var ztree=null;
		$leftTree.delegate(".link.reload","click",function(){
			ztree.reAsyncChildNodes(null,'refresh',true);
		});
		$leftTree.delegate(".link.expand","click",function(){
			var zTree = $.fn.zTree.getZTreeObj("glTypeTree");
			var treeNodes = zTree.transformToArray(zTree.getNodes());
			for(var i=1;i<treeNodes.length;i++){
				zTree.expandNode(treeNodes[i], true, true, true);
			}
			
		});
		$leftTree.delegate(".link.collapse","click",function(){
			ztree.expandAll(false);
		});
		var $layout=$$.parent("div[id*='layout']");
		$layout.append($leftTree);
		$layout.ligerLayout({leftWidth : 210,height : '98%',allowLeftResize : false});
		var params={"alias":$$.attr("alias")};
		var url=__ctx+"/demo/ztree/sysZtree/getTreeTypeByAlias.ht";
		var TreeType={};
		$.post(url,params,function(result){
			var dataOptions=$$.attr("data-options")?$$.attr("data-options").replaceAll("'","\""):{};
			TreeType=$.extend({},$.parseJSON(result)[0],$.parseJSON(dataOptions));
			setTree(TreeType);
		})
		
		function setTree(TreeType){
			TreeType=$.extend(true,{},TreeType,options?options.TreeType:{});
			var setting={
				callback:getFunctions(TreeType.callbacks),
				async:getFunctions(TreeType.asyncs,TreeType),
				check:getFunctions(TreeType.checks),
				data:getFunctions(TreeType.datas),
				edit:getFunctions(TreeType.edits),
				view:getFunctions(TreeType.views)
			}
			//判断是否为子结点,以改变图标	
			function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
				if(treeNode){
			  	 	var children=treeNode.children;
				  	 if(children.length==0){
				  		treeNode.isParent=true;
				  		var zTree = $.fn.zTree.getZTreeObj("glTypeTree");
				  		zTree.updateNode(treeNode);
				  	 }
				}
			};
			//过滤节点
			function filter(treeId, parentNode, childNodes) {
					return childNodes;
			};
			function getFunctions(params,TreeType){
				var jsonObj=eval("("+params+")");
				var returnObj={};
				for(var param in jsonObj){
					var o=jsonObj[param];
					if(o.type=='function'){
						if(o.defaults&&o.defaults.trim()!=""){
							var fn="function(){ "+o.defaults+" }";
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
			};
			setting=$.extend(true,{},setting,options?options.setting:{});
			if (setting.async.enable) {
				ztree = $.fn.zTree.init($("#glTypeTree"), setting); // 异步
			} else {
				$.post(setting.async.url, setting.async.otherParam, function(treeNodes) {
					var newResults=[];
			    	var rootNode={};
			    	var depth = TreeType.expandslevel-1;
					for(var n in treeNodes){
			    		if(treeNodes[n]["parentId"]==0){
			                rootNode=$.extend(true, {}, treeNodes[n]);
			                removeObjFromArr(treeNodes,n);
			                rootNode.icon=__ctx +"/styles/default/images/icon/root.png";
			                rootNode.isRoot=1;
			                rootNode.open=true;
			                if(TreeType.roottitle){
			                	rootNode.typeName=TreeType.roottitle;
			                }
			                newResults.push(rootNode);
			                break;
			            }
			    	 }
			        for(var i=0;i<treeNodes.length;i++){
			            var node=treeNodes[i];
			            node.open=false;
			            if(!setting.data.simpleData.rootPId){
			            	setting.data.simpleData.rootPId=rootNode.typeId;
			            }
			            if(isInArray(node.nodePath.split("."),TreeType.rootpid)){
			            	if(node.typeId==TreeType.rootpid){
			            		node.parentId=rootNode.typeId;
			            	}
			            	if(relateLevel(node,TreeType.rootpid)<depth){
			            		node.open=true;
			            	}
			            	newResults.push(node);
			            }
			        }
			        function relateLevel(node,rootId){
			        	var nodePaths=node.nodePath.split(".");
						for(var i=0;i<nodePaths.length; i++){
							if(nodePaths[i]==rootId){
								return nodePaths.length-i-1;
							}
						}
			        }
				    ztree =$.fn.zTree.init($("#glTypeTree"), setting,newResults); // 同步
		            if(depth!=null && depth>=0)
		            {
		                var nodes = ztree.getNodesByFilter(function(node){
		                    return (node.level==depth);
		                });
		                if(nodes.length>0){
		                    for(var idx=0;idx<nodes.length;idx++){
		                        ztree.expandNode(nodes[idx],false,false);
		                    }
		                }
		            }
		            else
		            {
		                ztree.expandAll(true);
		            }
				})
			}
			var toolbarHeight=$(".tree-toolbar").height();
	    	var allHeight=$("#defLayout,#layout").height();
	    	var treeHeight=(parseInt(allHeight)-parseInt(toolbarHeight)*3)+"px";
	    	$("#glTypeTree").css({"height":treeHeight});
		}
		
		return $self;
	}
})(jQuery)
$(function(){
	$.extend({
		initHtTree : function ($Tree,options) {
			return $Tree.htTree(options);
		}
	});
	//$.initHtTree($("#layout>div[class='ztree'],#defLayout>div[class='ztree']"),null);
})