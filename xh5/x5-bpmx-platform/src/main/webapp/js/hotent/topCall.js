(function($){
	function messageCall(myopt,opt){
		var def={
			type:'show' 	
		}
		$.extend(def, opt);
		if('show'==def.type){
			myopt.window.$.messager.show(def);
		}else if('alert'==def.type){
			myopt.window.$.messager.alert(def.title,def.msg,def.icon,def.fn);
		}else if('confirm'==def.type){
			myopt.window.$.messager.confirm(def.title,def.msg,def.fn);
		}else if('prompt'==def.type){
			myopt.window.$.messager.prompt(def.title,def.msg,def.fn);
		}else if('progress'==def.type){
			myopt.window.$.messager.progress(def);
		}
	};
	function dialogCall(myopt,opt){
		if(myopt.src){
			opt.href='';
			opt.content='<iframe id="myiframe" style="width:100%;height:98%" src="'+myopt.src+'"></iframe>'
		}
		if(myopt.id ){
			var $win=myopt.currentWin.$('#'+myopt.id);
			$win.appendTo(myopt.window.$('body'));
			myopt.window.$('#'+myopt.id).dialog(opt);
			if(myopt.src){
				var result =myopt.window.$('#'+myopt.id);
				result.iframe=myopt.window.$("#myiframe").contents() ;
				return result;
			}
			return myopt.window.$('#'+myopt.id);
		}
	}
	function windowCall(myopt,opt){
//		debugger;
//		var closeFn=null;
//		if(opt.onClose){	
//			closeFn=opt.onClose;
//		}
//		var closeWin=function(){
//			debugger;
//			if(myopt.id){
//				myopt.window.$('#'+myopt.id).hide();
//			}
//			if(closeFn)
//				closeFn();
//		};
//		opt.onClose=closeWin;
		if(myopt.id ){
			var $win=myopt.currentWin.$('#'+myopt.id);
			$win.appendTo(myopt.window.$('body'));
			myopt.window.$('#'+myopt.id).window(opt);
			return myopt.window.$('#'+myopt.id);
		}
	
	};
	function handle(opt){
		var def={
				fn:'message' //window/message/dialog
				,base:{}
				,appendhtml:''
				,'currentWin':window
				,'window':window
		};
		$.extend(def, opt);
		 if(def.window.parent!=def.window){
			 var newOpt=def;
			 	newOpt.window=def.window.parent;
				return handle(newOpt);
		 }
		else{
			if('message'==def.fn)
				messageCall(def,def.base);	
			else if('window'==def.fn)
					return windowCall(def,def.base);		
				else if('dialog'==def.fn)
					return dialogCall(def,def.base);	
		}
	};
	$.topCall={
			window:function(opt){
				opt.fn='window';
				return handle(opt);
			},
			message:function(opt){
				//alert
				opt.fn='message';
				 handle(opt);
			},
			dialog:function(opt){
				opt.fn='dialog';
				return handle(opt);
			}
	}
})(jQuery);