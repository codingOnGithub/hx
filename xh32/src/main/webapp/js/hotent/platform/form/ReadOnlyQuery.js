if (typeof ReadOnlyQuery == 'undefined') {
	ReadOnlyQuery = {};
}

ReadOnlyQuery.init = function() {

	$("span[selectquery]").each(function() {
		var me = $(this);
		var cond = me.attr("selectvalue");
		var queryJson = JSON2.parse(me.attr("selectquery")
				.replaceAll("'", "\""));
		var key = queryJson.binding.key; // 返回值作为select的value
		var value = queryJson.binding.value; // 返回值作为select的显示值
		var query = queryJson.query;
		var filedValue, subfiled, isNotMain;
		var querydataStr = "{";
	for (var i = 0; i < query.length; i++) {
		var field='';
		if (query[i].isMain=="true") {
			//输入框，下拉框，数据字典
			subfield = ".formTable [name$=':" + query[i].trigger + "']";
			// 选择器
			field = ".formTable [name$=':" + query[i].trigger + "ID']";
			// 单选、复选框
			checkfield = ":checked[name$=':" + query[i].trigger + "']"
		} else {
			subfield = "[name='" + query[i].trigger + "']";
			field = "[name='" + query[i].trigger + "ID']";
			checkfield = ":checked[name='" + query[i].trigger + "']"
		}
		switch (query[i].controlType) {
			//没有绑定任何表字段controlType=-1而有预设值时
			case '-1':break;
			// 输入框，下拉框，数据字典是一类直接获取值
			case '1' :
			case '2' :
			case '3' :
			case '11' :
			case '15' :
				fieldValue = $(subfield).val();
				break;
			// 选择器
			case '4' :
			case '5' :
			case '6' :
			case '7' :
			case '8' :
			case '17' :
			case '18' :
			case '19' :
				fieldValue = $(field).val();
				fieldValue = QueryUI.replaceValue(fieldValue);
				break;
			// 单选、复选框
			case '13' :
			case '14' :
				var tempvalue;
				$(checkfield).each(function() {
							var obj = $(this);
							if (typeof(tempvalue) != "undefined"
									&& tempvalue != "") {
								tempvalue += "#," + obj.val();
							} else {
								tempvalue = obj.val();
							}
						});
				fieldValue = tempvalue;
				tempvalue = "";
				break;
		}
		//如果对应的绑定字段有值则使用该值，没有值则判断设计时有没有输入预设值，有预设值则使用，没有则不做输入条件
		if (typeof(fieldValue) != "undefined" && fieldValue != "") {
			querydataStr += query[i].condition + ":\"" + fieldValue + "\",";
		}else{
			if(query[i].initValue!='')
			querydataStr += query[i].condition + ":\"" + query[i].initValue + "\",";
		}
	}
	if (querydataStr.length > 1){
		querydataStr = querydataStr.substring(0, querydataStr.length - 1);
		querydataStr += "}";
		queryCond = {
			alias : queryJson.name,
			querydata : querydataStr,
			page : 0,
			pagesize : 0
		};

			DoQuery(queryCond, function(data) {
						if (data.errors || data.list.length < 1) {
							return;
						}
						for (var i = 0; i < data.list.length; i++) {
							var dataobj = data.list[i];
							if (dataobj[key.toLowerCase()] == cond) {
								var datavalue = dataobj[value.toLowerCase()];
								me.children().text(datavalue);
							}

						}

					});

		}

	});
}
