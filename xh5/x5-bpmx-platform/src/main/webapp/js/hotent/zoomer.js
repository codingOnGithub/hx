(function($){
  $.fn.Zoomer=function(b){
      var c=$.extend({
	  speedView:200,
	  speedRemove:400,
	  altAnim:false,
	  speedTitle:400,
	  debug:false,
	  marginTop:'-75px',
	  marginLeft:'-100px',
	  top:'50%',
	  left:'50%',
	  width:'200px',
	  height:'150px',
	  padding:'0px'
	  },b);
	  var d=$.extend(c,b);
	  function e(s){
	     if(typeof console!="undefined"&&typeof console.debug!="undefined"){
		     console.log(s)
		}else{
		    alert(s)
	    }
	  }
		if(d.speedView==undefined||d.speedRemove==undefined||d.altAnim==undefined||d.speedTitle==undefined){
		    e('speedView: '+d.speedView);
			e('speedRemove: '+d.speedRemove);
			e('altAnim: '+d.altAnim);
			e('speedTitle: '+d.speedTitle);
			return false
		}
		if(d.debug==undefined){
		     e('speedView: '+d.speedView);
			 e('speedRemove: '+d.speedRemove);
			 e('altAnim: '+d.altAnim);
			 e('speedTitle: '+d.speedTitle);
			 return false
		}
		if(typeof d.speedView!="undefined"||typeof d.speedRemove!="undefined"||typeof d.altAnim!="undefined"||typeof d.speedTitle!="undefined"){
		   if(d.debug==true){
		   e('speedView: '+d.speedView);
		   e('speedRemove: '+d.speedRemove);
		   e('altAnim: '+d.altAnim);
		   e('speedTitle: '+d.speedTitle)
		   }
		var oldCss={
		   width:120,
		   height:70,
		   padding:'0px'
		};   
		   $(this).hover(function(){
		 
				$(this).css({'z-index':'1000','position':'relative'}).find('img').addClass("hover")
				  .css({'z-index':'1000','position':'absolute'})
				 .stop().animate({marginTop:d.marginTop,
				                  marginLeft:d.marginLeft,
								  top:d.top,
								  left:d.left,
								  width:d.width,
								  height:d.height,
								  padding:d.padding
								  },d.speedView);
				if(d.altAnim==true){
				   var a=$(this).find("img").attr("title");
				    var b=$(this).find("img").attr("price");
				   if(a.length!=0){
				      $(this).prepend('<div class="title" >'+a+'</div><div class="jiage">价格:￥'+b+'</div>');
					  $('.title').animate({marginLeft:'-40px',marginTop:'-32px'},d.speedTitle)
					      .css({'z-index':'1001','position':'absolute','float':'left','width':d.width-10})
					   $('.jiage').animate({marginLeft:'-40px',marginTop:'140px'},d.speedTitle)
					      .css({'z-index':'1001','position':'absolute','float':'left','width':d.width-10})
					}
				}
			},function(){
			   $(this).css({'z-index':'10','position':'relative'}).find('img').removeClass("hover")
				 .css({'z-index':'1','position':'relative'})
				  .stop().animate({marginTop:'0',
				                  marginLeft:'0',
								  top:'0',
								  left:'0',
								  width:oldCss.width+'px',
								  height:oldCss.height+'px',
								  padding:oldCss.padding
								  },d.speedRemove);
				  $(this).find('.title,.jiage').remove()
			})
		}
	}
})(jQuery);