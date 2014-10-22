(function($){
		/**
		 * 刷新tab的宽度
		 */
	  function  updateTabWidth($tab){
		   	var $parentContainer=$tab.parent().eq(0);
		   	var newwidth=$parentContainer.width()-30;	
		   	var state = 	$tab.data('tabs');
		    var opts =state.options;
		    if(opts){
		    	state.options.width=newwidth;
		    }
		   	$('.tabs-header,.tabs-wrap,.tabs-panels,.panel,.panel-body',$tab).width(newwidth);
	   }
	  
	  function popupOrCollect($li,flag){
		   var $menuDiv=$($.fn.easyExtend.defaults.divFeatureStr,$li);
		   var hideWidth= $.fn.easyExtend.defaults.hideWidth;
		   var expandWidth= $.fn.easyExtend.defaults.expandWidth;
		   if(flag){		  	
			     popupOrCollect($('.'+$.fn.easyExtend.defaults.unexpendClass),false);
				 $li.addClass($.fn.easyExtend.defaults.unexpendClass);
				 $menuDiv.css({'position':'absolute','z-index':'999','margin-left':hideWidth,'width':expandWidth});
				 $($.fn.easyExtend.defaults.expandImgFeatureStr,$menuDiv).hide();
				 $menuDiv.show();
		   }else{		 
				 $menuDiv.css({'position':'relative','z-index':'1','margin-left':'0px','width':'100%'});
				 $($.fn.easyExtend.defaults.expandImgFeatureStr,$menuDiv).show();
				 $li.removeClass($.fn.easyExtend.defaults.unexpendClass);
				 $menuDiv.hide(true);
		   }
	  }
	  //越界处理
	  function checkOutOfWindow($container){
			var container_x=$container.offset().top;
			var containerMaxHeight=$(window).innerHeight()-container_x-25;
			var containerActHeight=$container.height();
			if(containerActHeight>containerMaxHeight){
				$container.parent().find('.menu_up,.menu_down').show();	
			}else if($container.children('li:hidden').length<1){
				$container.parent().find('.menu_up,.menu_down').hide();
			}
	  }
	  
	  function panelMenu(){
			   var hideWidth=$.fn.easyExtend.defaults.hideWidth;
			   var expandWidth= $.fn.easyExtend.defaults.expandWidth;
			   var $container=$('.easyui-layout');
			   var $parentArea=$($.fn.easyExtend.defaults.rootUlFeatureStr,$container);
			   var $divParentArea= $parentArea.parents('.main_tree');
			   var $parentDiv= $parentArea.parent();
			   var panels =$container.data('layout').panels;		   
			   var data=$parentArea.data('spread') ;
			   if(data && data==1){	
				   //放小
				   popupOrCollect($('.'+$.fn.easyExtend.defaults.unexpendClass),false);		   
				   panels.center.panel('resize',{left:expandWidth,width:( $container.innerWidth()-expandWidth)});
				   panels.west.panel('resize',{width:expandWidth});
				   $parentArea.data('spread',0);
				   $($.fn.easyExtend.defaults.liFeatureStr).css({height:'auto'});
				   $($.fn.easyExtend.defaults.divFeatureStr,$parentArea).show();
				   $parentDiv.css({width:expandWidth})
				   $($.fn.easyExtend.defaults.hideImgFeatureStr,$parentArea).hide();
				   $divParentArea.css({width:expandWidth,overflow:'hidden'});	
			   }else{
				   //缩小
				   panels.center.panel('resize',{left:hideWidth,width:( $container.innerWidth()-hideWidth)}); 
				   panels.west.panel('resize',{width:hideWidth});
				   $($.fn.easyExtend.defaults.liFeatureStr).css({height:'42px'});
				   $($.fn.easyExtend.defaults.divFeatureStr,$parentArea).hide();
				   $($.fn.easyExtend.defaults.hideImgFeatureStr,$parentArea).show();
				   $parentDiv.css({width:expandWidth+hideWidth})
				   $parentArea.data('spread',1);
				   $divParentArea.css({width:hideWidth,overflow:'visible'});
			   }
			   //
			   checkOutOfWindow($parentArea);
			   
		
	};
	$.fn.easyExtend=function(options,param){
		
		if(typeof options == 'string'){
			return $.fn.easyExtend.functions[options](this, param);
		}
		$.extend($.fn.easyExtend.defaults,options );
		
	};
	
	$.fn.easyExtend.functions={
			panelMenu:function(jq){
				panelMenu.call(jq);
				return jq;
			},
			updateTabWidth:function(jq,param){
				updateTabWidth.call(jq,param);
				return jq;
			},
			popupOrCollect:function(jq,param){
				popupOrCollect.call(jq,param.container,param.flag);
				return jq;
			},
			checkOutOfWindow:function(jq,param){
				checkOutOfWindow.call(jq,param);
				return jq
			}
	
	}
	$.fn.easyExtend.defaults={
			rootUlFeatureStr:'ul.menu_ul_tree',
			liFeatureStr:'li.list_li',			
	  		divFeatureStr:'.list_btn',
	  		hideImgFeatureStr:'.smartShow',
	  		expandImgFeatureStr:'.bigShow',
	  		hideWidth:42,
	  		expandWidth:215,
	  		unexpendClass:'list_unexpend'
	}
})(jQuery);
$(function (){

 });
