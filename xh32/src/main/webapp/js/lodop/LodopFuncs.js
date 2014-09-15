//初始化打印控件
document.write('<object id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>'); 
document.write('	<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0 pluginspage="${ctx}/js/lodop/install_lodop32.exe"></embed>');
document.write('</object>');



/*
 * 
 * 例子：
 * 
    var LODOP; //声明为全局变量 
	function CreateTwoFormPage(){
		LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));  
		var root = $('div.printForm');
		var modules = getShowModules(root,"div.divClass&测试DIV","taskOpinionItem&意见;testId&测试ID","myImg&图片","formTable&表单");
		var conf = {
				root:root,
				modules:modules
		}
		selectModulesDialog(LODOP,conf);
	}; 
 * 
 * 
 */



//获取打印控件对象
function getLodop(oOBJECT, oEMBED) {
	/***************************************************************************
	 * 本函数根据浏览器类型决定采用哪个对象作为控件实例： IE系列、IE内核系列的浏览器采用oOBJECT，
	 * 其它浏览器(Firefox系列、Chrome系列、Opera系列、Safari系列等)采用oEMBED,
	 * 对于64位浏览器指向64位的安装程序install_lodop64.exe。
	 **************************************************************************/
	var exePath = __ctx +"/js/lodop";
	var strHtmInstall = "<br><font color='#FF00FF'>打印控件未安装!点击这里<a href='"+exePath+"/install_lodop32.exe' target='_self'>执行安装</a>,安装后请刷新页面或重新进入。</font>";
	var strHtmUpdate = "<br><font color='#FF00FF'>打印控件需要升级!点击这里<a href='"+exePath+"/install_lodop32.exe' target='_self'>执行升级</a>,升级后请重新进入。</font>";
	var strHtm64_Install = "<br><font color='#FF00FF'>打印控件未安装!点击这里<a href='"+exePath+"/install_lodop64.exe' target='_self'>执行安装</a>,安装后请刷新页面或重新进入。</font>";
	var strHtm64_Update = "<br><font color='#FF00FF'>打印控件需要升级!点击这里<a href='"+exePath+"/install_lodop64.exe' target='_self'>执行升级</a>,升级后请重新进入。</font>";
	var strHtmFireFox = "<br><br><font color='#FF00FF'>注意：<br>1：如曾安装过Lodop旧版附件npActiveXPLugin,请在【工具】->【附加组件】->【扩展】中先卸它。</font>";
	var LODOP = oEMBED;
	try {
		var isIE = (navigator.userAgent.indexOf('MSIE') >= 0) || (navigator.userAgent.indexOf('Trident') >= 0);
		var is64IE = isIE && (navigator.userAgent.indexOf('x64') >= 0);
		if (isIE)
			LODOP = oOBJECT;
		if ((LODOP == null) || (typeof (LODOP.VERSION) == "undefined")) {
			if (navigator.userAgent.indexOf('Firefox') >= 0) {
				document.documentElement.innerHTML = strHtmFireFox + document.documentElement.innerHTML;
			};
			if (is64IE) {
				document.write(strHtm64_Install);
			} else if (isIE) {
				document.write(strHtmInstall);
			} else {
				document.documentElement.innerHTML = strHtmInstall + document.documentElement.innerHTML;
			};
			return LODOP;
		} else if (LODOP.VERSION < "6.1.5.8") {
			if (is64IE) {
				document.write(strHtm64_Update);
			} else if (isIE) {
				document.write(strHtmUpdate);
			} else {
				document.documentElement.innerHTML = strHtmUpdate + document.documentElement.innerHTML;
			};
			return LODOP;
		}
		// =====如下空白位置适合调用统一功能:=====

		// =======================================
		return LODOP;
	} catch (err) {
		if (is64IE)
			document.documentElement.innerHTML = "Error:" + strHtm64_Install + document.documentElement.innerHTML;
		else
			document.documentElement.innerHTML = "Error:" + strHtmInstall + document.documentElement.innerHTML;
		return LODOP;
	}
}



//选择模拟并预览打印模块
/*
 * var myConf ={
				root:root,
				myTop:myTop,
				myLeft:myLeft,
				myWidth:myWidth,
				myHeight:myHeight,
				modules:newModules
		};
*/
function selectModulesDialog(LODOP,conf) {
	if(!conf) conf={};
	var url=__ctx + "/platform/form/bpmFormHandler/modulesDialog.ht";
	var winArgs="dialogWidth:600px;dialogHeight:550px;help:0;status:0;scroll:1;center:1";
	url=url.getNewUrl();
	if(conf.root==undefined||conf.root==null||conf.root==""){
		conf.root = $("html");   //默认整个页面
	}
	var myConf = window.showModalDialog(url,conf,winArgs);
	if(myConf.modules.length>0 ){
		CreatePrintForm(LODOP,myConf);
	}
}


//模块选取
function getShowModules(root,myStr,ids,names,classes) {
	
	var modules = new Array();
	var mark = true;  
	if(myStr!=null&&typeof(myStr)!=undefined&&myStr!=""){           //自己定义表达式获取
		modules = selectMyModules(root,myStr,modules,"other");
		mark = false;
	}
	if(ids!=null&&typeof(ids)!=undefined&&ids!=""){           //用ID获取
		modules = selectMyModules(root,ids,modules,"id");
		mark = false;
	}
	if(names!=null&&typeof(names)!=undefined&&names!=""){  //用name获取
		modules = selectMyModules(root,names,modules,"name");
		mark = false;
	}
	if(classes!=null&&typeof(classes)!=undefined&&classes!=""){  //用class获取
		modules = selectMyModules(root,classes,modules,"class");
		mark = false;
	}		
	if(mark){        //没有自己要自定义的打印模块时，就获取默认的
		modules = selectInitModules(root,modules)
	}
	return modules;
}

//选取自己定义要打印的模块
function selectMyModules(root,str,modules,type) {              //根目录、字符串 、 容器 和类型 (id、name、class、other)  字符串 : id&name;id&name;...
	var module;
	if(root==null||typeof(root)==undefined||root==""){	  
		var arrys = str.split(';');
		for ( var i = 0; i < arrys.length; i++) {
			var astr = arrys[i];
			var aa = astr.split('&');
			var id = aa[0];
			var idName = id+"模块";
			if(aa.length>1){
				idName = aa[1];
			}			
			var idObj
			if(type=='id'){  //用ID获取
				idObj = $("#"+id);
			}else if(type=='name'){ //用NAME获取
				idObj = $("[name='"+id+"']") ;
			}else if(type=='class'){     //用CLASS获取  
				idObj = $("."+id);
			}else{      //自己定义的表达式 
				idObj = $(id);
			}
			module = {
					  name:idName, 
					  obj:idObj
					  };
			modules.push(module);
		}
	}else{
		var arrys = str.split(';');
		for ( var i = 0; i < arrys.length; i++) {
			var astr = arrys[i];
			var aa = astr.split('&');
			var id = aa[0];
			var idName = id+"模块";
			if(aa.length>1){
				idName = aa[1];
			}			
			var idObj
			if(type=='id'){  //用ID获取
				idObj = $("#"+id,root);
			}else if(type=='name'){ //用NAME获取
				idObj = $("[name='"+id+"']",root) ;
			}else if(type=='class'){     //用CLASS获取  
				idObj = $("."+id,root);
			}else{      //自己定义的表达式 
				idObj = $(id,root);
			}
			module = {
					  name:idName, 
					  obj:idObj
					  };
			modules.push(module);
		}
	}
	return modules;
	
}

//选取默认的打印模块
function selectInitModules(root,modules) {              //根目录 和 容器             没有指定的模块时,默认模块
	var module;
	if(root==null||typeof(root)==undefined||root==""){	  //没有根目录时
		var tableObj = $('table.formTable');
		module = {
				name:"表单内容",
				obj:tableObj
		}
		modules.push(module);
		var divObj = $("[name ='editable-input']");    //$('div.taskopinion');
		module = {
					name:"意见内容",
					obj:divObj
				 }
		modules.push(module);
	}else{
		var tableObj = $('table.formTable',root);
		module = {
				name:"表单内容",
				obj:tableObj
		}
		modules.push(module);
		var divObj = $("[name ='editable-input']",root); //$('div.taskopinion');
		module = {
					name:"意见内容",
					obj:divObj
				 }
		modules.push(module);
	}
	return modules;
	
}


//预览要打印的模块
/*
 * var conf ={
				root:root,
				myTop:myTop,
				myLeft:myLeft,
				myWidth:myWidth,
				myHeight:myHeight,
				modules:newModules
		};
*/
function CreatePrintForm(LODOP,conf){
	var modules = conf.modules;
	var root = conf.root;	
	if(modules.length>0){
		if(LODOP==null||typeof(LODOP)==undefined||LODOP==""){
			LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));  
		}
		
		var myTop = "20px";
		if(typeof(conf.myTop)!=undefined&&conf.myTop!=null&&conf.myTop!=""){
			myTop = conf.myTop;
		}
		var myLeft = "20px";
		if(typeof(conf.myLeft)!=undefined&&conf.myLeft!=null&&conf.myLeft!=""){
			myLeft = conf.myLeft;
		}
		var myWidth = "100%";
		if(typeof(conf.myWidth)!=undefined&&conf.myWidth!=null&&conf.myWidth!=""){
			myWidth = conf.myWidth;
		}
		var myHeight = "100%";
		if(typeof(conf.myHeight)!=undefined&&conf.myHeight!=null&&conf.myHeight!=""){
			myHeight = conf.myHeight;
		}
	  
		var html = "";
		if(root==null||typeof(root)==undefined||root==""){	  
		    for ( var i = 0; i < modules.length; i++) {
				var module = modules[i];
				var obj = module.obj;
				if(module.isShow){
					alert(obj.html())
					html += obj.html()+"<br/>";    //叠加选中的模块内容
				}
			}  
		}else{  //有根目录的情况
		    $('div.noprint',root).remove();
		    for ( var i = 0; i < modules.length; i++) {
				var module = modules[i];
				var obj = module.obj;
				if(!module.isShow){
					obj.remove();                   //删除没有选中显示的模块
				}
			} 
			html = root.html();
			window.location.reload();			
		}
		
		html = changePrintHtml(html);
	       
		LODOP.ADD_PRINT_HTM(myTop,myLeft,myWidth,myHeight,html);
		LODOP.PREVIEW();
	}else{
		//没有要打印的内容
	}

}; 

//HTML组装
function changePrintHtml(html){
	if(html==null||typeof(html)==undefined||html==""){
		return "";
	}
	
	if(html.indexOf("<body>")>-1||html.indexOf("<DODY>")>-1){
		//
	}else{
		html ='<body>'+html+'</body>';
	}
	
	if(html.indexOf("<html>")>-1||html.indexOf("<HTML>")>-1){
		//
	}else{
		html ='<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><html>'+html+'</html>';
	}
	return html;
}


