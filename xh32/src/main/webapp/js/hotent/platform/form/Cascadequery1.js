(function($) {
	$.queryUI = {
		init : function() {
			$("select[selectquery]").each(function() {
				
						var me = $(this);
						var parentNaem = me.attr("cascadename");
						if (!parentNaem)
							return true;
						var query = me.attr("selectquery");
						if (!query)
							return true;
						query=query.replaceAll("'","\"");
						var queryJson = JSON2.parse(query);
						if (!queryJson.trigger || queryJson.trigger == "isparent") {
							getvalue(me);
						}
					});
			change();
		}
	};

	var change = function() {
		$("select[selectquery]").live("change", function(event) {
			var parentSel = $(this);
			var parentNaem = parentSel.attr("cascadename");
			if (!parentNaem)
				return;
			$("select[selectquery]").each(function() {
				var son = $(this);
				var query = son.attr("selectquery");
				if (!query)
					return true;
				query=query.replaceAll("'","\"");
				var queryJson = JSON2.parse(query);
				if (!queryJson.trigger || queryJson.trigger != parentNaem
						|| parentNaem == son.attr("cascadename"))
					return true;
				getvalue(son, parentSel);
			});
		});
	};

	var getvalue = function(me, pSelect) {
		var queryObj=me.attr("selectquery").replaceAll("'","\"");
		var queryJson = JSON2.parse(queryObj);
		var name = queryJson.query; // 自定义查询的别名
		var key = queryJson.binding.key; // 返回值作为select的value
		var value = queryJson.binding.value; // 返回值作为select的显示值
		var condition = queryJson.condition;
		var conname = condition.toUpperCase();
		var cond = '';
		if (typeof pSelect == 'undefined') {
			var intValue=queryJson.intvalue;
			cond = intValue;
			querydataStr = "{" + conname + ":\"" + cond + "\"}";
		} else {
			var selectOption = pSelect.find("option:selected");
			var selvalue = selectOption.val();
			if(selvalue!=null){//当前下拉框不为null
			cond = selvalue;
			querydataStr = "{" + conname + ":\"" + cond + "\"}";
			}else{
				me.empty();
			}
		}
		queryCond = {
			alias : name,
			querydata : querydataStr,
			page : 0,
			pagesize : 0
		};
		me.empty();
		DoQuery(queryCond, function(data) {
					if (data.errors || data.list.length < 1) {
						me.trigger("change");
						return;
					}
					for (var i = 0; i < data.list.length; i++) {
						var dataobj = data.list[i];
						var datavalue = dataobj[value.toLowerCase()];
						var datakey = dataobj[key.toLowerCase()];
						var opt = $("<option>").val(datakey).text(datavalue);
						me.append(opt);
					}
					var tempValue = me.attr("val");
					me.val(tempValue);
					me.trigger("change");
				});
	};
	
	
	
	
	// 此处需要进行自调用
	$(function() {
				$.queryUI.init();
			});
})(jQuery);