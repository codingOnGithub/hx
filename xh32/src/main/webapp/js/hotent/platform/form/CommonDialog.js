/**
 * 通用对话框。
 * @param alias		对话框别名。
 * @param callBack	回调函数。
 * 调用示例：
 * CommonDialog("role",function(data){
 * 
 * });
 * data:为json数组或者为json对象。
 * @param paramValueString	向对话框传递的"参数=值"字符串
 * 传入多个则需要使用"&"符号进行连接（user=admin&orgId=1）
 */
function CommonDialog(alias,callBack,paramValueString){
	window.__resultData__=0;
	if(alias==null || alias==undefined){
		$.ligerDialog.warn($lang.tip.msg,$lang_js.form.commonDialog.aliasNull);
		return;
	}
	var url=__ctx + "/platform/form/bpmFormDialog/dialogObj.ht?alias=" +alias;
	url=url.getNewUrl();
	$.post(url,{"alias":alias},function(data){
		if(data.success==0){
			$.ligerDialog.warn($lang_js.form.commonDialog.aliasError,$lang.tip.msg);
			return;
		}
		var obj=data.bpmFormDialog;
		var width=obj.width;
		var name=obj.name;
		var height=obj.height;
		var displayList=obj.displayfield.trim();
		var resultfield=obj.resultfield.trim();
		if( displayList==""){
			$.ligerDialog.warn($lang_js.form.commonDialog.displayfield,$lang.tip.msg);
			return;
		}
		if( resultfield==""){
			$.ligerDialog.warn($lang_js.form.commonDialog.resultfield,$lang.tip.msg);
			return;
		}
		
		var urlShow=__ctx + "/platform/form/bpmFormDialog/show.ht?dialog_alias_=" +alias;
		if(!paramValueString==false){
			urlShow = urlShow + "&" + encodeURI(paramValueString) ;
		}
		urlShow=urlShow.getNewUrl();
		$.ligerDialog.open({ url:urlShow, height: height,width: width, title :name,name:"frameDialog_",
			buttons: [ { text: $lang.button.ok, onclick: function (item, dialog) { 
					if(__resultData__==-1 || __resultData__==0){
						alert($lang_js.form.commonDialog.resultData);
						return;
					}
					if(callBack){
						callBack(__resultData__);
					}
					dialog.close();
			} }, 
			{ text: $lang.button.reset, onclick: function (item, dialog) { 
				$('[name="frameDialog_"]')[0].contentWindow.resetMethod();
			} }, 
			{ text: $lang.button.cancel, onclick: function (item, dialog) { dialog.close(); } } ] });
	});
};
/**
 * 调用通用表单查询
 * @param alias 查询别名
 * @param callback 回调函数
 */
function CommonQuery(alias){
	window.__queryData__ = [];
	if(alias==null || alias==undefined){
		$.ligerDialog.warn($lang_js.form.commonDialog.aliasNull,$lang.tip.msg);
		return;
	}
	var url=__ctx + "/platform/bpm/bpmFormQuery/queryObj.ht?alias=" +alias;
	url=url.getNewUrl();
	$.post(url,{"alias":alias},function(data){
		if(data.success==0){
			$.ligerDialog.warn($lang_js.form.commonDialog.aliasError,$lang.tip.msg);
			return;
		}
		var obj=data.bpmFormQuery;
		var name=obj.name;
		var conditionfield=obj.conditionfield.trim();
		var resultfield=obj.resultfield.trim();
		
		if( conditionfield==""){
			$.ligerDialog.warn($lang_js.form.commonDialog.displayfield,$lang.tip.msg);
			return;
		}
		if( resultfield==""){
			$.ligerDialog.warn($lang_js.form.commonDialog.resultfield,$lang.tip.msg);
			return;
		}
		
		var urlShow=__ctx + "/platform/bpm/bpmFormQuery/get.ht?query_alias_=" +alias;
		urlShow=urlShow.getNewUrl();
		$.ligerDialog.open({ url:urlShow, height: 380,width: 600, title :name,name:"frameQuery_",
			buttons: [{ text: $lang.button.close, onclick: function (item, dialog) { dialog.close(); } } ] });
	});
};

/**
 * 执行查询
 * @param alias 查询别名
 * @param condition 查询条件
 * @param callback 查询完成后的回调
 */
function DoQuery(condition,callback){
	var url = __ctx + "/platform/bpm/bpmFormQuery/doQuery.ht";
	$.post(url,condition,function(data){
		if(callback)
			callback(data);
	});	
};
