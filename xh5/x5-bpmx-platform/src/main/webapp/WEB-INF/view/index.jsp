<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<head>
	<title>业务管理平台</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/theme/default/css/x5.css">
	<script type="text/javascript" src="${ctx}/js/hotent/jquery.easyuiExtend.js"></script>
	<script type="text/javascript">
	var $menuRoot=$('.easyui-layout');

	   $(function (){
			init();
			
			$(document).bind('mouseup.menu_ul_tree',function(){
				 var $parentArea=$($menuRoot.easyExtend.defaults.rootUlFeatureStr);
				var currentWidth=$parentArea.width();
				   var data=$parentArea.data('spread') ;
				   if(!data || data==0){
					   $menuRoot.easyExtend({expandWidth:currentWidth});
					   $menuRoot.easyExtend('updateTabWidth',$('#tabs'));
				   }						
			});
			
			$('.menu_up').bind('click',function(){
				var $container=$('ul.menu_ul_tree');
				var currentTopStr=$container.css('margin-top');
				currentTopStr=currentTopStr.replace('px','') ;
				$container.css({'margin-top':parseInt(currentTopStr)-55});
				/*
				var lis=$container.children('li:visible');
				if(lis.length>1){
					lis.eq(0).hide();
				}
				*/
			});
			$('.menu_down').bind('click',function(){
				var $container=$('ul.menu_ul_tree');
				var currentTopStr=$container.css('margin-top');
				currentTopStr=currentTopStr.replace('px','') ;
				var currentTop=parseInt(currentTopStr);
				if(currentTop<-30)
					$container.css({'margin-top':currentTop+55});
// 				var lis=$container.children('li:hidden');
// 				if(lis.length>0){
// 					lis.eq(lis.length-1).show();
// 				}
			});
	
	   });
	   function init(){
		   $.post("${ctx}/platform/console/getMenuData.ht",function(result){
			  var rootnodes=getNodeByParentId(result,0);
			  var $menuContainer=$('.menu_ul_tree');
			  var temp='';
			  for(var i=0;i<rootnodes.length;i++){
				  var currentNode=rootnodes[i];
				  var subContend=parseMenu(result,currentNode.resId,1);
				  var  appendHtml='<li class="list_li " >'
							  +'<span class="smartShow" ><img src="${ctx}/styles/theme/default/images/icons/home.png"  title="'+currentNode.resName+'" ></span>'
							  +'<ul class="list_btn">';
				 if(subContend)
					appendHtml+='<span class="down_span" foldBoxId="'+currentNode.resId+'"></span>';						  
				 
				appendHtml+='<a  title="'+currentNode.resName+'" source="${ctx}/platform/org/user/list.ht" onclick="addIframeTab(this)"><span class="bigShow"><img src="${ctx}/styles/theme/default/images/icons/home.png"></span>'+currentNode.resName+'</a>';
				appendHtml+=subContend;
				appendHtml+='</ul></li>';							
			    $menuContainer.append(appendHtml);		
			    //检查越界
				$menuRoot.easyExtend('checkOutOfWindow',$($menuRoot.easyExtend.defaults.rootUlFeatureStr));
			   }
			  
				$('li.list_li').bind('click',function(){
					var $parentArea=$(this).parents($menuRoot.easyExtend.defaults.rootUlFeatureStr);
					 var data=$parentArea.data('spread') ;
					   if(data && data==1){
						   $menuRoot.easyExtend('popupOrCollect',{container:$(this),flag:true});		
					 }
				});
				$('.list_btn').hover(function(){},function(event){	
					  var $parentArea=$(this).parents($menuRoot.easyExtend.defaults.rootUlFeatureStr);
					  var data=$parentArea.data('spread') ;
					   if(data && data==1){				
						  var $parentLi=$(this).parents('li.list_li');
						 // $('.easyui-layout').easyExtend('popupOrCollect',{container:$parentLi,flag:false});	
					 }
				});
				//折叠盒按钮控制
				$('span.down_span').bind('click',function(){
					var foldBoxId=$(this).attr('foldBoxId');
					var $foldbox=$('#foldBox'+foldBoxId);
					var $container=$('ul.menu_ul_tree').eq(0);
					if($foldbox.is(':hidden')){
						$(this).removeClass('down_span');
						$(this).addClass('up_span');
						$foldbox.show();						
					}else{
						$(this).removeClass('up_span');
						$(this).addClass('down_span');
						$foldbox.hide();
					}	
					$menuRoot.easyExtend('checkOutOfWindow',$container);	
				})
		   });
	   }
	   //解析子菜单
	   function parseMenu(map,parentId,level){
		   var resultStr='<ul id="foldBox'+parentId+'" class="foldBox">';
		  		 var nodes=getNodeByParentId(map,parentId);
		  		 if(nodes.length<1) return null;
		  		 var spaceStr='';
		  			for(var i=0;i<level;i++){
		  				spaceStr+='<span class="tree-indent"></span>'
		  			}
		  		  for(var i=0;i<nodes.length;i++){
		  			  var node=nodes[i];
		  			var appendHtml='';
 		  			  var subContent=parseMenu(map,node.resId,(level+1));
 		  			  if(subContent){   
 		  				appendHtml='<li class="sub_list_li" >'+spaceStr
 		  								+'<span><img src="${ctx}/styles/theme/default/images/icons/home.png" ></span><span class="down_span" foldBoxId="'+node.resId+'"></span>'
 		  								+node.resName+'</li>';
 		  				appendHtml+=subContent;
 		  			  }else{
 		  				appendHtml='<li class="sub_list_li">'+spaceStr+'<span></span>'
								+node.resName+'</li>';
 		  			  }
 		  			resultStr+=appendHtml;
		  		  }
		   return resultStr+'</ul>'
	   }
	   //获取指定父级的元素
	   function getNodeByParentId(map,parentId){
		   var result=new Array();
		   for(var i=0;i<map.length;i++){
			   var node=map[i];
			   if(node.parentId==parentId)
				   result.push(node);
		   }
		   return result;
	   }

	   function panelMenu(){
		   $menuRoot.easyExtend('panelMenu').easyExtend('updateTabWidth',$('#tabs'));
	   }

		function addIframeTab(obj){
			var url=$(obj).attr('source');
			var id=$(obj).attr('id');
			var title=$(obj).text();
			if($('#tabs').tabs('exists',{'which':title})){
				$('#tabs').tabs('select',{'which':title});
				return ;
			}
			$('#tabs').tabs('addIframeTab',{
				//tab参数为一对象，其属性同于原生add方法参数
				tab:{
	                title:title,
	                closable:true,
	                tools:[{	                	
	                    iconCls:'icon-mini-refresh',
	                    handler:function(e){
	                        var title2 = $(e.target).parent().parent().text();
	                        $('#tabs').tabs('updateIframeTab',{'which':title2});
	                    }
	                }],
	                onSelect: function(title,index){
	                	debugger;
	                	return alert('dddd');
	                  }
	            },
				iframe:{src:url,frameBorder:0,delay:0}
			});
	        $('#tabs').tabs('addEventParam');
		}

</script>
</head>

<body class="easyui-layout">
	<div data-options="region:'north',border:false"  class="top">
			<div class="logo"></div>
			<div class="info">
				<span class="email"><span class="e_msg">5</span></span>				
				<a class="more">超级管理员<span></span></a>
				<a class="sys more">系统切换<span></span></a>
			</div>
	</div>
	<div data-options="region:'west',split:true,border:false" class="main_tree"  >
		<a href="javascript:void(0)" onclick="javascript:panelMenu()" class="list_show"><span></span></a>
		 
		<div class="treeContain">
		   <span class="menu_up"></span>
			<span class="menu_down"></span>
			<ul class="menu_ul_tree"></ul>
		</div>
	</div>
	<!-- div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div-->
	
	<div data-options="region:'center',border:false,tabHeight:20" style="text-align:center;padding:10px 10px "  class="easyui-tabs main-tabs"   id="tabs">
		<div title="我的主页" data-options="iconCls:'icon-help'">
          	<div class="i_box">
					<span class="box_1 box_icon"></span>
					<div class="box_text">
						<span class="box_msg">1349</span><br>
						<span class="box_title">未读消息</span>
					</div>
					<span class="box_more">查看更多</span>
					<div class="box_more_bg"></div>
				</div>
				<div class="i_box odd">
					<span class="box_2 box_icon"></span>
					<div class="box_text">
						<span class="box_msg">249</span><br>
						<span class="box_title">新建流程</span>
					</div>
					<span class="box_more">查看更多</span>
					<div class="box_more_bg"></div>
				</div>
				<div class="i_box">
					<span class="box_3 box_icon"></span>
					<div class="box_text">
						<span class="box_msg">89%</span><br>
						<span class="box_title">我的办结</span>
					</div>
					<span class="box_more">查看更多</span>
					<div class="box_more_bg"></div>
				</div>
				<div class="i_box odd">
					<span class="box_4 box_icon"></span>
					<div class="box_text">
						<span class="box_msg">126</span><br>
						<span class="box_title">待办事宜</span>
					</div>
					<span class="box_more">查看更多</span>
					<div class="box_more_bg"></div>
				</div>
				<div>
					<img src="${ctx}/styles/theme/default/images/analysis.jpg" style="margin: 5px 7px;">
				</div>
				<div class="s_box">
					<span class="s_box_title">常规统计</span>
					<a class="reload">刷新</a>
					<img src="images/s1.jpg" style=" margin-top: 5px; margin-left: 10px;">
				</div>
				<div class="s_box">
					<span class="s_box_title">服务器统计</span>
					<a class="reload">刷新</a>
					<img src="images/s2.jpg" style=" margin-top: 5px; margin-left: 10px;">
				</div>
        </div>
	</div>
	<div data-options="region:'south',border:false" style="height:20px;background:#A9FACD;padding:5px;">版本@Copyright 2008-2014</div>

</body>

