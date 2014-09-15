var Share=function(){}
/**
 * 传入字符串，取得相应的拼音
 * @param conf Object {input:'用于计算拼音的字符串',preCallback:Function,postCallback:Function}),只有参数input是必填
 * @returns String 传入字符串对应的拼音
 */
Share.getPingyin= function(conf)
{	
	var v=true;
	if(!conf){
		v=false;
	}else if(typeof(conf)=='object'){
		if(!conf.input){
			v=false;
		}
	} else {
		conf = {
			input : conf
		};
	}
	
	if(!v){
		throw new Error($lang_js.share.errorMsg);
	}

	
	var py;
	var url=__ctx + "/platform/system/share/getPingyin.ht";
	url=url.getNewUrl();
	$.ajax(url, {
		async : false,
		data : {
			input : conf.input
		},
		beforeSend : function() {
			conf.preCallback && conf.preCallback();
		}
	}).done(function(data) {
		conf.postCallback && conf.postCallback(data);
		py=data.output;
	});
	return py;
}

/**
 * 传入要获得拼音的源对象sourceObj，取得拼音后将存放到目标对象targetObj
 * @param sourceObj	
 * @param targetObj
 */
Share.getKeyName = function(sourceObj,targetObj){
	Share.getPingyin({
		input:sourceObj.value,
		postCallback:function(data){
			if(targetObj.value.length<1 || /.*[\u4e00-\u9fa5]+.*$/.test(targetObj.value)){
				targetObj.value = data.output;
			}
		}
	});
}