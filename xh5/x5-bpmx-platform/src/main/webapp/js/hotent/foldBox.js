( function( $ ) {
	$.foldBox = function ( box, options ){ 
		   var $content=$('.content',$(box));
		   var $button=$('.drop',$(box));
			if($(box).hasClass(options.searchBox)){ //搜索框
				var  insertHtml='<div class="check_c_title"><span>查询条件</span><span class="sep2"></span><span class="drop check_more"></span></div>'
				$(insertHtml).insertBefore( $content ) ;
				var afterShowfn=options.afterShow;
				options.afterShow=function(){afterShowfn();
		//		$('.drop',$(box)).find('a').text('收起');
				$('.drop',$(box)).addClass('activi')}
				var afterHidefn=options.afterHide;
				options.afterHide=function(){afterHidefn();
		//		$('.drop',$(box)).find('a').text('展开');
				$('.drop',$(box)).removeClass('activi')}
				$button=	$('.drop',$(box));
			}
			
			$button.bind('click',function(){
					 if( $content.is(':hidden')){
						 options.beforeShow($(box));
						 $content.show();
						 options.afterShow($(box));						
					 }else{
						options.beforeHide($(box));
						$content.hide();
						options.afterHide($(box));				
					 }
				});
	
			
	};
	
	$.fn.foldBox = function ( options ){
		options = options || {};
		options.searchBox = options.searchBox || 'searchBox';
		options.beforeShow = options.beforeShow || function($box){};
		options.afterShow = options.afterShow || function($box){};
		options.beforeHide = options.beforeHide || function($box){};
		options.afterHide = options.afterHide || function($box){};
		this.each( function() 
			{
				new $.foldBox( this, options );
			}
		);
		
		return this;
	};
})( jQuery );

$(function(){
	$.extend({initFoldBox:function(){
		/**
		 * 初始化更新页面
		 */
		try{
			$('.foldBox').foldBox();
		}catch(e){}
	}});
	$.initFoldBox();
});
