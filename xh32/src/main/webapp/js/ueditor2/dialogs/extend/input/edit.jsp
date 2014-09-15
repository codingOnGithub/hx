<%@page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/commons/include/form.jsp" %>
<f:js pre="js/lang/view/platform/form" ></f:js>
<f:js pre="js/lang/view/platform/system" ></f:js>
<script type="text/javascript" src="${ctx}/js/ueditor2/dialogs/internal.js"></script>
<link rel="stylesheet" type="text/css" href="../edit.css">
<link rel="stylesheet" type="text/css" href="${ctx}/styles/default/css/form.css">
<link rel="stylesheet" type="text/css" href="${ctx}/js/tree/zTreeStyle.css"/>
<script type="text/javascript" src="${ctx}/js/util/loadjscss.js"></script>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerComboBox.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/Share.js"></script>
<script type="text/javascript"src="${ctx}/js/hotent/platform/system/ScriptDialog.js"></script>

<script type="text/javascript">	
	var oldElement,valid;
	$(function() {
		$(".button-td").bind("mouseenter mouseleave",function(){
			$(this).toggleClass("button-td-hover");
		});
		JsLoader.LoadCount=1;
		//获取所有数据字典列表
		JsLoader.Load(__ctx + "/js/lg/plugins/htCatCombo.js","javascript1");
		if (editor.curInputHtml) {
			var content=editor.curInputHtml.replace(/#validrule#/g,function(){
				getAllRules();
				return $lang_form.bpmFormDef.designEdit.valid_rule_getting;
				}).replace(/#serialnum#/g,function(){					
					return $lang_form.bpmFormDef.designEdit.serial_number_getting;
					});
			$("#inputPanel").html(content);
		}
		valid = $("#inputPanel").form();
		
		init();
		//将表名称转为小写
		$('.edit_table').delegate(':text[eid="tablename"]','change',function(){
			var value = $(this).val();
			$(this).val(value.toLowerCase());
		});
	});
	
	function initOptionArray(json) {
		var tbody = $('#option-table>tbody');
		if (typeof(json)!=undefined && json!=null && json != '' && json.length>0){  //有数据时
			for ( var i = 0, c; c = json[i++];) {
				if(typeof c.value ==='string'){//3.2版本
					var tr = addColumn1(c);
					tbody.append(tr);
				}else{
					var normalTr = addNormalColumn(c),
				    	newTr = addColumn(c);
					tbody.append(normalTr,newTr.hide());
				}
			}
		}else{
			var value = [];
			value.lantype=" ";
			value.lanmemo=" ";
			value.lanres=" ";
			var data ={key:" ",value:value};  //key 为一个空格
			var normalTr = addNormalColumn(data),
		        newTr = addColumn(data);
			tbody.append(normalTr,newTr.hide());
		}		
	};
	
	function getOptionData(){
		var optionAry = [];
		$("tr.editable-tr",$("#option-table")).each(function(){
			var	me = $(this),
				optionKey = $("input[name='optionKey']",me).val(),
				isDefault = $(":radio[name='defaultOption']",me).attr("checked");
			var optionValue = [];
			$("input.long",me).each(function(){
				var me = $(this),
			      name = me.attr("name"),
			      val = me.val(),
			      memo = me.attr("title");
				optionValue.push({lantype:name,lanres:val,lanmemo:memo});;
			});
			optionAry.push({key:optionKey,value:optionValue,isDefault:isDefault});
		});
		return optionAry;
	};
	
	//兼容3.2版本
	function getOptionData1(){
		var optionAry = [];
		$("tr.editable-tr",$("#option-table")).each(function(){
			var	me = $(this),
				optionKey = $("input[name='optionKey']",me).val(),
				isDefault = $(":radio[name='defaultOption']",me).attr("checked"),
				optionValue = $("input[name='optionValue']",me).val();
			optionAry.push({key:optionKey,value:optionValue,isDefault:isDefault});
		});
		return optionAry;
	};
	
	/**
	 * 终止事件冒泡
	 * @param  {[type]} e [description]
	 * @return {[type]}   [description]
	 */
	function stopBubble(e) {
	    if (e && e.stopPropagation) e.stopPropagation();
	    else window.event.cancelBubble = true;
	};

	/**
	 * 添加选项
	 * @param  {[json]} data [数据]
	 * @return {[type]}      [description]
	 */
	function addColumn(data){
	  var hiddenTable = $("#hiddenTable"),
	  	  tmpTr = $("tr.editable-tr",hiddenTable),
	  	  newTr = tmpTr.clone();
	  if(typeof(data.key)!= undefined && data.key!=null && data.key!=''){
		  $("input[name='optionKey']",newTr).val(data.key);
		  $("input.long",newTr).each(function(){
			  var opinionValue = data.value;
			  var me = $(this).val(''),
			      name = me.attr("name");
		      for(var i=0,c;c=opinionValue[i++];){
		          if(c.lantype==name){
		              me.val(c.lanres);
		          }
		      }
		  });
		  if(data.isDefault){
			  $(":radio[name='defaultOption']",newTr).attr('checked','checked');
		  }
	  }
	  return newTr;
	};
	
	/**
	 * 添加选项(兼容3.2版本)
	 * @param  {[json]} data [数据]
	 * @return {[type]}      [description]
	 */
	function addColumn1(data){
	  var hiddenTable = $("#hiddenTable32"),
	      tmpTr = $("tr.editable-tr",hiddenTable),
	      newTr = tmpTr.clone();
	  if(typeof(data.key)!= undefined && data.key!=null && data.key!=''){
		  $("input[name='optionKey']",newTr).val(data.key);
		  $("input[name='optionValue']",newTr).val(data.value);	
		  if(data.isDefault){
			  $(":radio[name='defaultOption']",newTr).attr('checked','checked');
		  }
	  }
	  return newTr;
	};
	
	function addNormalColumn(data){
		 var hiddenTable = $("#hiddenTable"),
	     tmpTr = $("tr.normalTr",hiddenTable),
	     newTr = tmpTr.clone();
	 if( typeof(data.key)!= undefined && data.key!=null && data.key!=''){
		  var lanDetail = $("span.lanDetail",newTr),
		  	  curLan = $("span.curLan",newTr),
		      detail = getSelectValue(data.value),
		      curLanMsg = getCurLan(data.value);
		  lanDetail.text(detail);
		  curLan.text(curLanMsg);
	 }
	 return newTr;
	}
	
	/**
	 * 获取选项值
	 */
	function getSelectValue(val){
		  if(!val||val.length==0)return '';
		  var str = [];
		  if(val.length==0)return str;
		  for(var i=0,c;c=val[i++];){
		    str.push('['+c.lanmemo + ']' + c.lanres);
		  }
		  return str.join('  ');
	};
	
	/**
	 * 获取当前语言对应的选项值
	 */
	function getCurLan(val){
		  if(!val||val.length==0)return '';
		  var str = "";
		  if(val.length==0)return str;
		  for(var i=0,c;c=val[i++];){
			  if(c.lantype==editor.locale){
				  str = c.lanres;
				  return str;
			  }
		  }
		  return str;
	};
	
	function init(){
		if($(editor.target).is('td')){
			if(!editor.curInputElement){                   //有保存相关的字段信息了，就不用再自动自成字段名称了
				var comment=$(editor.target).prev().text().replace(':','').trim();
				var $comment=$("#inputPanel").find('input[eid="comment"]');
				if(comment){
					$comment.val(comment);
					getKeyName($comment);
				}	
			}						
		}
		
		//判断是否是子表，是子表的就把显示到列表的字段隐藏起来，否则则显示
		var subDiv=$(editor.target).parents("div.subtable");
		if(subDiv.length!=0){
			$("label[name='Listlabel']").hide();
			$("label[name='QueryLabel']").hide();
			
		}
		
		$('#isDateString').bind('change',function(){
			if($('#isDateString').attr('checked')=="checked"){
				$('#dateStrFormat').parents('span:first').show();
			}else{
				$('#dateStrFormat').parents('span:first').hide();
			}
		});
		
		$("#option-table>tbody").delegate('tr.editable-tr','mouseover mouseleave', function(e) {
	 		 if (e.type == 'mouseover') {
	            $(this).addClass('hover');
	        } else {
	            $(this).removeClass('hover');
	        } 
	    });
		
		$("#option-table>tbody").delegate('tr.normalTr','mouseover mouseleave',function(e){
		    if(e.type=='mouseover'){
		      $(this).addClass('hover');  
		    }else{
		      $(this).removeClass('hover'); 
		    }
	  });
	  
	  $("#option-table>tbody").delegate('tr.normalTr','click',function(e){
	    var normalTr = $(this),
	      editTableTr = normalTr.next(),
	      editTable = editTableTr.attr("class");
	      
	    if(editTable=='editable-tr' && normalTr && editTableTr){
	        if(editTableTr.is(":hidden")){
	        	editTableTr.css("display","");
	        }
	        else {
	        	var str = [];
	    		$("input.long",editTableTr).each(function(){
	    			var me = $(this),
	    		      val = me.val(),
	    		      memo = me.attr("title");
	    			str.push('['+memo + ']' + val);
	    		});
	    		var lanDetail = $("span.lanDetail",normalTr),
	    		    curLan = $("span.curLan",normalTr),
	    		    selector = "input[name='"+editor.locale+"']";
	    		curLan.text($(selector,editTableTr).val());
	    		lanDetail.text(str.join('  '));
	    		editTableTr.hide();
	        }
	    }
	    stopBubble(e);
	  });
	    
	    $("#option-table>tbody").delegate('a.remove-bnt','click',function(e){
	    	stopBubble(e);
	    	var me = $(this),
	      		parentClass = me.closest('div').attr("class");
	      	if(parentClass=='editable-right-div'){//3.2版本
	      		var tr = me.parents('tr.editable-tr');
	      		tr.remove();
	      	}else{
	      		var normalTr = me.parents('tr.normalTr'),
	            	moreInfoTr = normalTr.next('tr.editable-tr');
	      		moreInfoTr.remove();
		        normalTr.remove();
	      	} 
	    });
	    
	    $("a.add").click(function() {
	    	var tbody = $('#option-table>tbody');
	    	var optionValue = $("input[name='optionValue']",tbody);
	    	if(optionValue.length>0){
	    		var data = {
	   	            key: "",
	   	            value: ""
	   	        }, newTr = addColumn1(data);
	    		tbody.append(newTr);
	    	}
	    	else{
	    		var value = [];
	    		value.lantype="";
	    		value.lanmemo="";
	    		value.lanres="";
	            var data = {
	                key: "",
	                value: value
	            }, newTr = addColumn(data),
	            normalTr = addNormalColumn(data);
	            tbody.append(normalTr,newTr.hide());
	    	}
	    });
	    
	    //名称后台判断 返回前台处理
	    $("input[eid='name']").blur(function(){
	    	checkMyField();
        }); 
	  
	}
	
	//检查字段名称
	function checkMyField(){
		var url= __ctx + "/platform/form/bpmFormTable/getFieldByTidFnaNh.ht";    	
    	
		var tableId = parent.document.getElementById('tableId').value;          //主表ID
    	if(typeof(tableId)== undefined || tableId==null || tableId.length<=0){
			return;
		}
    	
    	var start = editor.selection.getStart();          //当前光标位置的元素
    	var subtable = $(start).closest('.subtable');                //当前光标在所属的子表
    	var subTableName = "";
    	if(typeof(subtable)!= undefined && subtable!=null && subtable!=''){
    		var externalStr= subtable.attr("external");
    		if(typeof(externalStr)!= undefined && externalStr!=null && externalStr!=''){
    			externalStr = htmlDecode(externalStr);
    			var external=eval("("+externalStr+")");
    			if(typeof(external.tablename)!= undefined && external.tablename!=null && external.tablename!=''){
    				subTableName = external.tablename;          //所属的子表名称
    			}
			}
    		
		}
    	
    	var fieldName = $("input[eid='name']").val();  //字段名称
    	if(typeof(fieldName)== undefined || fieldName==null || fieldName.length<=0){
			return;
		} 	
    	$.post(url,{tableId:tableId,fieldName:fieldName,subTableName:subTableName},function(data){			
    		if(typeof(data)!= undefined && data!=null && data!=''){
    			var json = eval('(' + data + ')');	
    			var str = "";
    			if(json.isDeleted==1){
    				str =  $lang_form.bpmFormDef.designEdit.field_abandoned;
    			}else{
    				str = $lang_form.bpmFormDef.designEdit.field_used;
    			}
    			if(confirm(str)){
    				$("input[eid='comment']").val(json.fieldDesc);  //字段名称解析 comment
    				//$("input[eid='comment']").attr("disabled","disabled");
    				$("input[eid='name']").val(json.fieldName);        //字段名称
    				$("input[eid='name']").attr("disabled","disabled");
    				$("input[eid='length']").val(json.charLen);               //字段长度
    				$("input[eid='length']").attr("disabled","disabled");
    				var typeObj =$("select[eid='type']");
    				if(json.fieldType=='clob'){
    					typeObj.html('<option value="clob" selected="selected" >'+$lang_form.editTable.dataType.clob+'</option>');
    					$("input[eid='length']").parent().html("");  //大文本是没有长度的
    				}else{
    					typeObj.html('<option value="varchar">'+$lang_form.editTable.dataType.varchar+'</option><option value="number">'+$lang_form.editTable.dataType.number+'</option><option value="date">'+$lang_form.editTable.dataType.date+'</option>');
    					typeObj.find("option[value='"+json.fieldType+"']").attr("selected",true);
    				}
    				typeObj.attr("disabled","disabled");
    				
    				if(json.isRequired==1){
    					$("input[eid='isRequired']").attr("value",1);  
    					$("input[eid='isRequired']").attr("checked",true);
    				}
    				if(json.isList==1){
    					$("input[eid='isList']").attr("value",1);  
    					$("input[eid='isList']").attr("checked",true);
    				}
    				if(json.isQuery==1){
    					$("input[eid='isQuery']").attr("value",1);  
    					$("input[eid='isQuery']").attr("checked",true);
    				}
    				if(json.isFlowVar==1){
    					$("input[eid='isFlowVar']").attr("value",1);  
    					$("input[eid='isFlowVar']").attr("checked",true);
    				}
    				if(json.isReference==1){
    					$("input[eid='isReference']").attr("value",1);  
    					$("input[eid='isReference']").attr("checked",true);
    				}
    				if(json.isWebSign==1){
    					$("input[eid='isWebSign']").attr("value",1);  
    					$("input[eid='isWebSign']").attr("checked",true);
    				}
    				
    				$('label.error',$('#inputPanel')).remove();        //删除回填之前提示的错误
    			}else{
    				$("input[eid='name']").val("");        //清空或者重新给一个值
    			}
    			
    		}
    	});
	};
	
	//获取所有验证规则
	function getAllRules(){
		var url=__ctx+"/platform/form/bpmFormRule/getAllRules.ht";
		$.get(url,function(data){
			if(!data)return;
			var html=['<th>'+$lang_form.bpmFormDef.designEdit.validRule+':</th><td colspan="3"><select eid="content" prenode="valueFrom"><option value="">'+$lang_form.bpmFormDef.designEdit.none+'</option>'];
			for(var i=0,c;c=data[i++];){
				html.push('<option value="'+c.name+'">'+c.name+'</option>');
			}
			html.push('</select></td></td>');
			$(".valuefrom0").html(html.join(''));
			getAllSerialnum();
		});
	};
	
	//获取所有流水号
	function getAllSerialnum(){
		var url=__ctx+"/platform/system/identity/getAllIdentity.ht";
		$.get(url,function(data){
			if(!data)return;
			var html=['<th>'+$lang_form.editTable.varFrom.serialNumber+':</th><td colspan="3"><select eid="content" prenode="valueFrom">'];
			for(var i=0,c;c=data[i++];){
				html.push('<option value="'+c.alias+'">'+c.name+'</option>');
			}
			html.push('</select></td>');
			$(".valuefrom3").html(html.join(''));
			bindData();
		});
	};	
	//加载完inputPanel的内容以后触发该事件
	function initComplete(){
		$("[eid='type']").bind("change",typeChange);
		$("[eid='value']").bind("change",valueChange);
		$("#isQuery").bind("click",conditionChange);
		$("[eid='searchFrom']").bind("change",searchFromChange);
		typeChange.call($("[eid='type']")[0]);
	};	
	function conditionChange(){
		$(".condition_tr").toggleClass("hidden");
	};
	//值类型变化
	function typeChange(){
		$(".dbformat_td").html(getFormatHtml($(this).val()));
		if(editor.curInputType=="attachement"||editor.curInputType=="pictureshowcontrol"){//附件设置长度为2000
			$("[eid='length']").val('2000');
		}
	
	};
	//值来源变化
	function valueChange(){
		var val = $(this).val();
		$("tr[class^='valuefrom']").each(function(){
			if($(this).attr('class').indexOf(val)>-1){
				$(this).removeClass('hidden');
			}
			else{
				$(this).addClass('hidden');
			}
		});
	};
	//查询条件值来源变化
	function searchFromChange(){
		var val = $(this).val();
		if(val=="fromForm")
			$(".searchValue-td").addClass('hidden');
		else
			$(".searchValue-td").removeClass('hidden');
	};
	//修改状态下的初始化
	function bindData(){
		oldElement = null;
		/*
		if(!editor.curInputElement){
			if($("tr",$('#option-table>tbody')).length==0){
				var value = [];
	    		value.lantype="";
	    		value.lanmemo="";
	    		value.lanres="";
		    	var data = {
		            key: " ",
		            value: value
		        }, newTr = addColumn(data),
	            normalTr = addNormalColumn(data);
		    	var tbody = $('#option-table>tbody');
	            tbody.append(normalTr,newTr.hide());
		    }
			return;
		}	*/		
		var externalStr= $(editor.curInputElement).attr("external");
		if(typeof(externalStr)!= undefined && externalStr!=null && externalStr!=''){
			externalStr = htmlDecode(externalStr);
		}
		var external=eval("("+externalStr+")");
		
		/* //是否有 select 标签(下拉控件)
		if($("select",$(editor.curInputElement)).length ==1){
			if(typeof(external.options)==undefined || external.options==null || external.options == '' || external.options.length==0){
				external.options = new Array();       //当下拉空格为空时，设置一个空的默认值
				var data ={key:" ",value:"--请选择--"};  //key 为一个空格
				external.options.push(data)
			}
		}	 */	
		
		oldElement = editor.curInputElement;
		editor.curInputElement=null;
		bind(external);
		if($('#isDateString').attr('checked')=="checked"){
			$('#dateStrFormat').parents('span:first').show();
		}
		
		if($('#isCoin').attr('checked')=="checked"){
			//货币选择事件
			selectCoinChange($('#isCoin'),'isShowComdify','coinValue')
		}
	}
	//递归绑定值 
	function bind(d){	
		for (var k in d){
	    	 var val= d[k];
	    	 if(typeof val == 'object' && val != null){
	    		 if(k=='options'){
					 initOptionArray(val);
				 }
	    		 else{
	    		 	bind(val);
	    		 }
	    	 }
   	 		 else if(val===1){
				$("[eid='"+k+"']").attr("checked","checked");
				if(k=='isQuery'){
					conditionChange();
				}
				if(editor.canEditColumnNameAndType){
					 if(k=='displayDate')
						 $("[eid='"+k+"']").attr("disabled","disabled");
				 }
			 }
			 else{
				 if(k=='options'){
					 var options = [],
					 	 optAry = val.split('#newline#'),
					 	 opt;
					 while(opt=optAry.shift()){
						 options.push({key:opt,value:opt});
					 }
					 initOptionArray(options);
					 continue;
				 }
				 $("#"+k+":hidden").val(val);
				 $("[eid='"+k+"']:visible").val(val);
				 if(k==="dateStrFormat"){
				 	 $("[eid='"+k+"']").val(val);
				 }
				 if(k=='type'){
					 typeChange.call($("[eid='"+k+"']")[0]);
				 }
				 if(k=='value'){
					 valueChange.call($("[eid='"+k+"']")[0]);
				 }
				 if(k=='searchFrom'){
					 searchFromChange.call($("[eid='"+k+"']")[0]);
				 }
				 if(editor.canEditColumnNameAndType){
					 if(k=='name'||k=='type'||k=='length'||k=='intLen'||k=='decimalLen'||k=='format'){//编辑时不能修改字段的这些属性
						 $("[eid='"+k+"']").attr("disabled","disabled");
					 }
				 }
			 }
	     }
	}
	//选择货币事件
	function selectCoinChange(obj,cId,sId){
		if($(obj).is (":checked")){
			$('#'+cId).attr('checked','checked');
			$('#'+cId).attr('disabled','disabled');
			$('#'+sId).show();
		}else{
			$('#'+cId).attr('checked',false);
			$('#'+cId).attr('disabled',false);
			$('#'+sId).hide();
		}
	}
	//切换数据格式
	function getFormatHtml(type){
		var html=[''];
		switch(type){
			case 'varchar':
				html.push($lang_form.bpmFormDef.dataFormat.varchar.length+':<input eid="length" prenode="dbType" style="width:60px;" type="text" value="50"/>');
				$(".defaultValue_tr").removeClass('hidden');
				break;
			case 'number':
				html.push('<label for="isShowComdify">'+$lang_form.bpmFormDef.dataFormat.number.thousandth+':<input id="isShowComdify" prenode="dbType" type="checkbox" eid="isShowComdify" value="1"></label>'
						+'<label for="isCoin">'+$lang_form.bpmFormDef.dataFormat.number.coin+':<input id="isCoin" eid="isCoin" type="checkbox" value="1" onchange="selectCoinChange(this,\'isShowComdify\',\'coinValue\')"></label>'
						+'<select id="coinValue" prenode="dbType"  eid="coinValue" style="width:80px;display:none"><option value="￥">'+$lang_form.bpmFormDef.dataFormat.number.rmb+'</option><option value="$">'+$lang_form.bpmFormDef.dataFormat.number.dollar+'</option></select>'
					+'<label>'+$lang_form.bpmFormDef.dataFormat.number.integer+':<input eid="intLen" prenode="dbType" style="width:30px;" type="text" value="14"/>'+$lang_form.bpmFormDef.dataFormat.number.decimal_+':<input eid="decimalLen" prenode="dbType" style="width:25px;" type="text" value="0"/></label>');
				$(".defaultValue_tr").addClass('hidden');
				break;
			case 'date':
				html.push('<select eid="format" prenode="dbType"><option value="yyyy-MM-dd">yyyy-MM-dd</option><option value="yyyy-MM-dd HH:mm:ss">yyyy-MM-dd HH:mm:ss</option><option value="yyyy-MM-dd HH:mm:00">yyyy-MM-dd HH:mm:00</option><option value="HH:mm:ss">HH:mm:ss</option></select><br /><label for="displayDate"><input id="displayDate" eid="displayDate" prenode="dbType" type="checkbox" />'+$lang_form.bpmFormDef.dataFormat.date.curDate+'</label>');
				$(".defaultValue_tr").addClass('hidden');
				break;
		}
		return html.join('');
	};
	
	dialog.onok = function() {
		var rtn=valid.valid();
		if(!rtn)return false;
		//触发表名称的onchange事件，将表名转为小写
		$(':text[eid="tablename"]','.edit_table').change();
		var data=[];
		$("#inputPanel").find("*:visible").each(function(){
			var id = $(this).attr("eid"),
				val = $(this).val(),
				dictType=$("#dictType").val(),
				prenode = $(this).attr("prenode");
			if($(this).attr("type")){
				if($(this).attr("type")=="checkbox"||$(this).attr("type")=="radio"){
					val = (!!$(this).attr("checked")) ? 1 : 0;
				}
			}
			//数据字典取值
			if(dictType){
				data.push({id:'dictType',val:dictType});
			}
			if((id&&val)||(id&&val==0)){
				var item = {id:id,val:val};
				if(prenode) item.prenode = prenode;
				data.push(item);
			}
		});
		var inputValues = $("input[name='optionValue']",$("#option-table")),
			options;
		if(inputValues.length>0){//3.2版本
			options = getOptionData1();
		}else{
			options = getOptionData();
		}
		if(options && options.length > 0){
			data.push({id:'options',val:options});
		}
		//回填字段名
		if($(editor.target).is('td')){
			var comment = $("#inputPanel").find('input[eid="comment"]').val();
			if(comment){
				var target = $(editor.target).prev();
				if(target && target.hasClass("formTitle")){
					if($("span",target).length==0 && $("div", target).length==0){
						target.text(comment+":");
					}
				}
			}
		}
		
		editor.execCommand(editor.curInputType,data,oldElement);
	};	
	//解码单引号和双引号
	function htmlDecode(str){
		return str.replace(/\&quot\;/g, '\"').replace(/\&\#39\;/g, '\'');
	};
	
function getKeyName(obj){
    var value=$(obj).val();
	Share.getPingyin({
		input:value,
		postCallback:function(data){
			var inputObj=	$("input[eid='name']");
			if(inputObj.val().length<1){
				inputObj.val(data.output);
				checkMyField();       //赋值后触发检验字段名称
			}			
		}
	});
}
//调用常用脚本对话框
function addScript(obj){
	var _this = $(obj);
	var target = _this.closest('td').find('textarea')[0];
	ScriptDialog({
		callback:function(data){
			jQuery.insertText(target,data);
		}
	});
};
</script>
</head>
<body>
	<div id="inputPanel">		
	</div>
</body>
</html>