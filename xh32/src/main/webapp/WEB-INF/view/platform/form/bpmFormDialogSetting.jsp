<%--
	time:2012-06-25 11:05:09
	desc:edit the 通用表单对话框
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/get.jsp" %>
	<f:js pre="js/lang/view/platform/form" ></f:js>			
	<title><spr:message code="bpmFormQuery.setting.fieldSet"/></title>
	<script type="text/javascript"src="${ctx}/js/hotent/platform/system/ScriptDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/javacode/codemirror.js"></script>
	<script type="text/javascript" src="${ctx}/js/javacode/InitMirror.js"></script>
	<link href="${ctx}/styles/default/css/jquery.qtip.css" rel="stylesheet" />
	<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.qtip.js"></script>
	<script type="text/javascript">
		var isTree=${style==1 };
		$(function() {
				$("#defLayout").ligerLayout({ leftWidth: 270,height: '100%',
				bottomHeight:50,allowLeftCollapse:false,rightWidth:465,allowRightResize:false,centerWidth:20,
			 	allowBottomResize:false,allowRightCollapse:false});
				//默认显示第一个Tab标签
				javaFieldSelected(false) ;

				//获取自定义对话框
				getDialogs();
				init();	
		});
		
		function init(){
			$("input.treeField").focus(function(){
				curField=$(this);
			});
			var fields = window.dialogArguments;	
			if(fields.displayField)initDisplayField(fields.displayField);
			if(fields.conditionField)initConditionField(fields.conditionField);
			if(fields.resultField)initResultField(fields.resultField);
			if(fields.sortField)initSortField(fields.sortField);
		}
		//未保存的自定义对话框，在已经设置列以后再次打开设置列的窗口时 初始化已设置的列
		function initDisplayField(displayField){
			var fieldObj=eval("("+displayField+")"),
				objContainer=$("#trDisplayField");
			objContainer.empty();
			if(!isTree){
				for(var i=0,c;c=fieldObj[i++];){
					objContainer.append(getDispalyField(c.field,c.comment));
					
				}
			}
			else{
				if(fieldObj.id)$("#treeId").val(fieldObj.id);
				if(fieldObj.pid)$("#parentId").val(fieldObj.pid);
				if(fieldObj.displayName)$("#displayName").val(fieldObj.displayName);
				if(fieldObj.pvalue)$("#parentValue").val(fieldObj.pvalue);
 				if(fieldObj.isScript=="true")$("#isScript").attr("checked","checked");
			}
		};
		
		//初始化条件字段
		function initConditionField(conditionField){
			if(conditionField==null) return;
			conditionField=conditionField.trim();
			if(conditionField=="") return;
			var fieldObj=eval("("+conditionField+")"),
				objContainer=$("#trConditionField");
			objContainer.empty();
			if(fieldObj.length>0 && fieldObj[0].defaultType == 5){
				for(var i=0;i<fieldObj.length;i++){
					var c = fieldObj[i] ;
					if(c.field=='') continue ;
					var div = $('#templateDiv').clone(true).css('display','');
					div.attr('id',c.field);
					var text=div.children(':text');
					text.val(c.comment);
					text.attr('name',c.field);
					text.attr('dbType',c.dbType);
					if('isAfferent'==c.dbType){
						div.children(':checkbox').attr('checked',"checked");
					}
					$('div.fieldBtnDiv').append(div) ;
					
				}
				$("#script").text(fieldObj[0].defaultValue) ;
				javaFieldSelected(true) ;
				return ;
			}
			for(var i=0,c;c=fieldObj[i++];){
				objContainer.append(getConditionField(c.field,c.comment,c.dbType,!isTree));
				var curTr = $("#condition"+c.field),
					curValTr = $("#conditionVal"+c.field);
				$("select.condition",curTr).val(c.condition);
				$("select[name='changeDefalut']",curValTr).val(c.defaultType);
				changeDefault($("select[name='changeDefalut']",curValTr));
				if(c.defaultType=='1' && c.dbType!='date'){
					$("select[name='ct']",curValTr).find("option[value='"+c.paraCt+"']").attr('selected','selected');
					if(c.paraCt=='0'){
						$('select#dialog-type',curValTr).find("option[value='"+c.dialog+"']").attr('selected','selected');
						dialogChange($("#dialog-type",curValTr));
						$('select#dialog-param',curValTr).find("option[value='"+c.param+"']").attr('selected','selected');
						$('#settingDiv',curValTr).show();
					}
				}else{
					$("textarea[name='defaultValue']",curValTr).val(c.defaultValue);
				}
			}
		};
		
		//初始化返回字段
		function initResultField(resultField){
			var fieldObj=eval("("+resultField+")"),
				objContainer=$("#trRtnField");
			objContainer.empty();
			if(!fieldObj)return;
			for(var i=0,c;c=fieldObj[i++];){
				objContainer.append(getRtnField(c.field,c.comment));
			}
		};
		
		//初始化排序字段
		function initSortField(sortField){
			if(!sortField.trim()) return ;
			var fieldObj=eval("("+sortField+")"),
				objContainer=$("#trSortField");
			objContainer.empty();
			if(!fieldObj)return;
			for(var i=0,c;c=fieldObj[i++];){
				var tr=$('tr#sortTrTemplate').clone(true);
				tr.attr('id','sort'+c.field);
				tr.attr('name',c.field);
				tr.css('display','');
				if(c.comment=='ASC'){
					$('td#sort',tr).find(':checkbox').attr('checked',true);
				}else{
					$('td#sort',tr).find(':checkbox').attr('checked',false);
				}
				$('td#fieldName',tr).text(c.field);
				objContainer.append(tr);
			}
		};
		
		var curField;
		/*
		 *左边字段选择
		 */
		function selectTreeField(){   
			var obj=$("input:checkbox[name='fieldName']:checked");
			if(curField==null || curField.length==0){
				$.ligerDialog.error(lang_form.bpmFormDialog.selectTreeField.selectRightInput,$lang.tip.error);
				return;
			}
			if(obj.length==0){
				$.ligerDialog.error($lang_form.bpmFormDialog.selectTreeField.selectLeftField,$lang.tip.error);
				return;
			}
			if(obj.length>1){
				$.ligerDialog.error($lang_form.bpmFormDialog.selectTreeField.selectLeftOneField,$lang.tip.error);
				return;
			}
			curField.val(obj.val());
		}
		
		function setDisplayField(){
			var objContainer=$("#trDisplayField");
			$("input:checkbox[name='fieldName']:checked").each(function(){
				var trObj=$(this).closest("tr");	
				var fieldName=$(this).val();  //id
				var comment=$("input[name='comment']",trObj).val();
				var obj=$("#display" + fieldName);
				if(obj.length==0){
					var tr=getDispalyField(fieldName,comment);
					objContainer.append(tr);
				}
			});
		}
		
		function delRow(obj){
			$(obj).closest("tr").remove();
		}
		
		function delConditionTr(obj){
			var tr=$(obj).closest("tr");
			tr.next().remove().end().remove();
		}
		
		function getDispalyField(fieldName,comment){
			var sb=new StringBuffer();
			sb.append("<tr id='display"+ fieldName +"' name='"+fieldName+"' comment='"+comment+"'>");
			sb.append("<td >"+fieldName+"</td>");
			sb.append("<td >"+comment+"</td>");
			sb.append("<td><a alt='"+$lang.operate.moveUp+"' href='#' class='link moveup' onclick='sortTr(this,true)'>&nbsp;&nbsp;</a>");
			sb.append("<a alt='"+$lang.operate.moveDown+"' href='#' class='link movedown' onclick='sortTr(this,false)'>&nbsp;&nbsp;</a>");
			sb.append("<a href='#' class='link del'  onclick='delRow(this)' >"+$lang.operate.del+"</a></td>");
			sb.append("</tr>");
			return sb.toString();
		}
		
		function getConditionField(fieldName,comment,dbType,isList){
			var db=getConditionSelect(dbType,fieldName,comment);
			var templateSelector = $('#templateSelector').html();
			var sb=new StringBuffer();
			sb.append("<tr class='trCondition' id='condition"+ fieldName +"'>");
			sb.append("<td >"+fieldName+"</td>");
			sb.append("<td >"+db+"</td>");
			sb.append("<td >"+comment+"</td>");
			sb.append("<td><a alt='"+$lang.operate.moveUp+"' href='#' class='link moveup' onclick='sortConditionTr(this,true)'></a>");
			sb.append("<a alt='"+$lang.operate.moveDown+"' href='#' class='link movedown' onclick='sortConditionTr(this,false)'></a>");
			sb.append("<a href='#' class='link del'  onclick='delConditionTr(this)' >"+$lang.operate.del+"</a></td>");
			sb.append("</tr>");
			sb.append("<tr id='conditionVal"+ fieldName +"'>");
			sb.append("<td>"+$lang.operate.defValue+"</td>");
			sb.append("<td><select name='changeDefalut' onchange='changeDefault(this)'>");
			if(isList){
				sb.append("<option value='1'>"+$lang.operate.formInput+"</option>");
			}
			sb.append("<option value='2'>"+$lang.operate.fixedValue+"</option><option value='3'>"+$lang.operate.script+"</option><option value='4'>"+$lang_form.bpmFormDialog.dynamic_incoming+"</option></select></td>");
			sb.append("<td colspan='2'><div name='btnScript' style='display:none;'><a href='#' class='link var' title='"+$lang.operate.commonScript+"' onclick='selectScript(this)'>"+$lang.operate.commonScript+"</a></div>");
			sb.append("<textarea name='defaultValue' cols='40' rows='3' ");
			if(isList){
				sb.append("		style='display:none;' ");	
			}
			//增加表单输入控件选择
			sb.append("></textarea>");
			if(dbType!='date'&&isList){
				sb.append(templateSelector);
			}
			sb.append("</td></tr>");
			return sb.toString();
		}
		
		function selectScript(obj) {
			var txtObj=$(obj).closest('div').siblings("textarea")[0];
			ScriptDialog({
				callback : function(script) {
					$.insertText(txtObj,script);
				}
			});
		};
		
		function changeDefault(obj){
			var val=$(obj).val();
			var objTr=$(obj).parents("tr");
			var txtObj=$("textarea[name='defaultValue']",objTr);
			var linkObj=$("div[name='btnScript']",objTr);
			var selectObj=$("select[name='ct']",objTr);
			if(typeof selectObj!='undefined'){
				if(val=='1'){
					selectObj.show();
				}else{
					selectObj.hide();
				}
			}
			switch(val){
				case "1":
					txtObj.hide();
					linkObj.hide();
					break;
				case "2":
					txtObj.show();
					linkObj.hide();
					break;
				case "3":
					txtObj.show();
					linkObj.show();
					break;
				case "4":
					txtObj.hide();
					linkObj.hide();
					break;
			}
		}
			
		function getRtnField(fieldName,comment){
			var sb=new StringBuffer();
			sb.append("<tr id='rtn"+ fieldName +"' name='"+fieldName+"' comment='"+comment+"' >");
			sb.append("<td >"+fieldName+"</td>");
			sb.append("<td >"+comment+"</td>");
			sb.append("<td><a alt='"+$lang.operate.moveUp+"' href='#' class='link moveup' onclick='sortTr(this,true)'>&nbsp;&nbsp;</a>");
			sb.append("<a alt='"+$lang.operate.moveDown+"' href='#' class='link movedown' onclick='sortTr(this,false)'>&nbsp;&nbsp;</a>");
			sb.append("<a href='#' class='link del' onclick='delRow(this)' >"+$lang.operate.del+"</a></td>");
			sb.append("</tr>");
			return sb.toString();
		}
		
		function getConditionSelect(dbType,name,comment){
			var sb=new StringBuffer();
			sb.append("<select class='condition' name='"+name+"' dbType='"+dbType+"' comment='"+comment+"'>");
			switch(dbType){
				case "varchar":
					sb.append("<option value='='>"+$lang.operateType.eq+"</option>");
					sb.append("<option value='like'>LIKE</option>");
					sb.append("<option value='likeEnd'>LIKEEND</option>");
					break;
				case "number":
					sb.append("<option value='='>"+$lang.operateType.eq+"</option>");
					sb.append("<option value='>='>"+$lang.operateType.gtEq+"</option>");
					sb.append("<option value='>'>"+$lang.operateType.gt+"</option>");
					sb.append("<option value='<'>"+$lang.operateType.lt+"</option>");
					sb.append("<option value='<='>"+$lang.operateType.ltEq+"</option>");
					break;
				case "date":
					sb.append("<option value='='>"+$lang.operateType.eq+"</option>");
					sb.append("<option value='between'>Between</option>");
					sb.append("<option value='>='>"+$lang.operateType.gtEq+"</option>");
					sb.append("<option value='<='>"+$lang.operateType.ltEq+"</option>");
					
					break;
				
			}
			
			sb.append("</select>");
			return sb;
		}
		
		function setField(){
			var divContents = $('div.content') ;
			var visibleDivContent = undefined;
			if(divContents==undefined) return ;
			for(var i=0;i<divContents.length;i++){
				if($(divContents[i]).css('display')!='none'){
					visibleDivContent = divContents[i] ;
					break ;
				}
			}
			if(visibleDivContent == undefined){
				$.ligerDialog.error($lang_form.bpmFormDialog.expandRightArea,$lang.tip.error);
				return ;
			}
			var divParent = $(visibleDivContent).closest('.fieldContainer') ;
			if(divParent.length==0){
				divParent = $(visibleDivContent).closest('.panel-detail') ;
			}

			if(divParent.attr('class').indexOf('display')>=0){
				setDisplayField() ;
				return ;
			}
			if(divParent.attr('class').indexOf('tree')>=0){
				selectTreeField() ;
				return ;
			}
			
			if(divParent.attr('class').indexOf('condition')>=0){
				if($('.javaField').css('display')!='none'){
				//java脚本区域
					$("input[name='fieldName']:checked").each(function(){
						var trObj=$(this).closest("tr");	
						var fieldName=$(this).val();  //id
						var comment=$("input[name='comment']",trObj).val();
						var dbType=$(this).attr("dbType");
						var fnBtns = $('div.fieldBtnDiv').children('div[id="'+fieldName+'"]') ;
						if(fnBtns==undefined || fnBtns.length<1){
							var div = $("#templateDiv").clone(true).css('display','') ;
							div.attr('id',fieldName);
							var text = div.children(':text');
							text.val(comment);
							text.attr('name',fieldName);
							text.attr('dbType',dbType);
							$('div .fieldBtnDiv').append(div);
							$('div.fieldBtnDiv').append(div);
							javaFieldSelected(true);
						}
					});
				}else if($('.trConditionField').css('display')!='none'){
					if($('.btnTree')!=undefined && $('.btnTree').length>0){
						setConditionField(false) ;
					}else
						setConditionField(true) ;
				}
				return ;
			}
			if(divParent.attr('class').indexOf('return')>=0){
				setReturnField() ;
				return ;
			}
			if(divParent.attr('class').indexOf('order')>=0){
				setOrderField() ;
				return ;
			}
			if(divParent.attr('class').indexOf('sort')>=0){
				setSortField() ;
				return ;
			}
		}

		function setSortField(){
			var objContainer=$("#trSortField");
			$("input:checkbox[name='fieldName']:checked").each(function(){
				var trObj=$(this).closest("tr");	
				var fieldName=$(this).val();  //id
				var obj=$("#sort" + fieldName);
				if(obj.length==0){
					var tr=$('tr#sortTrTemplate').clone(true);
					tr.attr('id','sort'+fieldName);
					tr.attr('name',fieldName);
					tr.css('display','');
					$('td#fieldName',tr).text(fieldName);
					objContainer.append(tr);
				}
			});
		}
		
		function setConditionField(isList){
			var objContainer=$("#trConditionField");
			$("input[name='fieldName']:checked").each(function(){
				var trObj=$(this).closest("tr");	
				var fieldName=$(this).val();  //id
				var comment=$("input[name='comment']",trObj).val();
				var dbType=$(this).attr("dbType");
				var tr=getConditionField(fieldName,comment,dbType,isList);
				objContainer.append(tr);
				
			});
		}
		
		function setReturnField(){
			var objContainer=$("#trRtnField");
			$("input:checkbox[name='fieldName']:checked").each(function(){
				var trObj=$(this).closest("tr");	
				var fieldName=$(this).val();  //id
				var comment=$("input[name='comment']",trObj).val();
				var obj=$("#rtn" + fieldName);
				if(obj.length==0){
					var tr=getRtnField(fieldName,comment);
					objContainer.append(tr);
				}
			});
		}
		
		var displayStr="";
		var conditionStr="";
		var rtnStr="";		
		var sortStr="";
		
		function buildTreeJson(){
			var aryRtnField=[];
			var rtn="";
			var treeId=$("#treeId").val();
			var parentId=$("#parentId").val();
			var parentValue=$("#parentValue").val();
			var isScript=$("#isScript").is(":checked");
			var displayName=$("#displayName").val();
			if(treeId=="" || parentId=="" || displayName==""){
				rtn+=$lang_form.bpmFormDialog.displayField+"\r\n";
			}
			
			displayStr="{id:'"+treeId+"',pid:'"+parentId+"',displayName:'"+displayName+"',pvalue:'"+parentValue+"',isScript:'"+isScript+"'}" ;

// 			$("input:checkbox[name='treeReturn']:checked").each(function(){
// 				var prevObj=$(this).prev();   
// 				rtnStr+=prevObj.val()+",";
// 			});
// 			rtnStr=rtnStr.substring(0, rtnStr.length-1);
// 			if(rtnStr==""){
// 				rtn+="请选择要返回的字段\r\n";
// 			}
			var result=getCondition();
			if(!result){
				rtn+=$lang_form.bpmFormDialog.conditionVal+"\r\n";
			}
			//排序字段
			getSort();
			//返回字段
			$("#trRtnField").children().each(function(){
				var fieldName=$(this).attr("name");
				var comment=$(this).attr("comment");
				var obj={};
				obj.field=fieldName;
				obj.comment=comment;
				aryRtnField.push(obj);
			});
			
			if(aryRtnField.length==0){
				rtn+=$lang_form.bpmFormDialog.rtnField;
			}
			rtnStr=JSON2.stringify(aryRtnField);
			
			return rtn;
		}
		
		//获取排序字段
		function getSort(){
			var arySortField=[];
			$("#trSortField").children().each(function(){
				var fieldName=$(this).attr("name");
				var comment='ASC' ;
				if($('#sort :checked',this).length==0){
					comment='DESC' ;
				}
				var obj={};
				obj.field=fieldName;
				obj.comment=comment;
				arySortField.push(obj);
			});
			sortStr=JSON2.stringify(arySortField);
		}
		
		//获取选择的条件。
		function getCondition(){
			var aryContion=[];
			var rtn=true;
			if(curConditionField=="javaField"){
				$('.fieldBtnDiv div').each(function(){
					var obj={};
					obj.field=$(this).attr('id');
					obj.comment=$(this).children(':text:first').val();
					obj.condition="";
					obj.dbType="";
					obj.defaultType="5";
					obj.defaultValue="";
					if($(this).children(':checkbox').is(':checked')){
						obj.dbType = "isAfferent";
					}
					if(InitMirror.editor!=null){
						obj.defaultValue=InitMirror.editor.getCode();
					}
					aryContion.push(obj);
				});
				if(aryContion.length == 0){
					obj.field="";
					obj.comment="";
					obj.condition="";
					obj.dbType="";
					obj.defaultType="5";
					obj.defaultValue=InitMirror.editor.getCode();
					aryContion.push(obj);
				}
				conditionStr=JSON2.stringify(aryContion);
				return rtn ;
			}

			$("#trConditionField").children("tr.trCondition").each(function(){
				var trObj=$(this);
				var trDefault=trObj.next();
				var selObj=$('select.condition',trObj);
				var fieldName=selObj.attr("name");
				var comment=selObj.attr("comment");
				var condition=selObj.attr("value");
				var dbType=selObj.attr("dbType");
				var obj={};
				obj.field=fieldName;
				obj.comment=comment;
				obj.condition=condition;
				obj.dbType=dbType;
				
				var selDefault=$("select[name='changeDefalut']",trDefault).val();
				var txtDefault=$("textarea[name='defaultValue']",trDefault).val();
				if(selDefault!="1" && selDefault!="4" && txtDefault.trim()==""){
					rtn=false;
				}
				obj.defaultType=selDefault;
				if(selDefault=="1" || selDefault=="4"){
					obj.defaultValue="";
				}else{
					obj.defaultValue=txtDefault;
				}
				if(selDefault=="1" && obj.dbType!='date'){
					var paraCtSelector = $('select[name="ct"]',trDefault);
					obj.paraCt=paraCtSelector.val();
					if(paraCtSelector.val()=="0"){
						obj.dialog=paraCtSelector.closest('td').find('select#dialog-type').val();
						obj.param=paraCtSelector.closest('td').find('select#dialog-param').val();
					}
				}
				aryContion.push(obj);
			});
			conditionStr=JSON2.stringify(aryContion);
			return rtn;
		}
		
		function buildListJson(){
			var rtn="";
			var aryDisplay=[];
			var aryRtnField=[];
			$("#trDisplayField").children().each(function(){
				var fieldName=$(this).attr("name");
				var comment=$(this).attr("comment");
				var obj={};
				obj.field=fieldName;
				obj.comment=comment;
				aryDisplay.push(obj);
				
			});
			if(aryDisplay.length==0){
				rtn= $lang_form.bpmFormDialog.displayField+"\r\n";
			}
			
			var result=getCondition();
			if(!result){
				rtn+=$lang_form.bpmFormDialog.conditionVal+"\r\n";
			}
			
			$("#trRtnField").children().each(function(){
				var fieldName=$(this).attr("name");
				var comment=$(this).attr("comment");
				var obj={};
				obj.field=fieldName;
				obj.comment=comment;
				aryRtnField.push(obj);
			});
			
			if(aryRtnField.length==0){
				rtn+=$lang_form.bpmFormDialog.rtnField;
			}
			
			displayStr=JSON2.stringify(aryDisplay);
			rtnStr=JSON2.stringify(aryRtnField);
			//排序字段
			getSort();
			return rtn;
			
		}
		
	    function selectForm(){
	    	var rtn="";
			$('[id="settingDiv"]').each(function(){
				if($(this).css('display')=='none') return ;
				var dialog = $(this).find('#dialog-type');
				var param = $(this).find('#dialog-param');
				var paraName = $(this).closest('tr').prev('tr').find('td:first').text();
				if(!dialog.val()){
					rtn = String.format($lang_form.bpmFormDialog.selectDialog,paraName);
					return false;
				}else if(!param.val()){
					rtn = String.format($lang_form.bpmFormDialog.selectReturnValue,paraName);
					return false;
				}
			});
			if(rtn) {
				$.ligerDialog.warn(rtn,$lang.tip.warn);
				return false;
			}
			//如果是树状的只取不大于3个的返回值				
			if($("input.treeField").length>0) {
				rtn=buildTreeJson();
			}
			else{
				rtn=buildListJson();
			}
			if(rtn!=""){
				$.ligerDialog.warn(rtn,$lang.tip.warn);
				return;
			}
			var rerurnlist= new Array(displayStr,conditionStr,rtnStr,sortStr); 
			window.returnValue=rerurnlist;
	    	window.close();
			
	    }
	    
		function sortTr(obj,isUp) {
			var thisTr = $(obj).parents("tr");
			if(isUp){
				var prevTr = $(thisTr).prev();
				if(prevTr){
					thisTr.insertBefore(prevTr);
				}
			}
			else{
				var nextTr = $(thisTr).next();
				if(nextTr){
					thisTr.insertAfter(nextTr);
				}
			}
		};
	    
		function sortConditionTr(obj,isUp) {
			var thisTr = $(obj).closest("tr");
			var nextTr=thisTr.next();
			
			//向上
			if(isUp){
				var prevTr = thisTr.prev();
				if(prevTr.length==0) return;
				var targeTr=prevTr.prev();
				thisTr.insertBefore(targeTr)
				nextTr.insertBefore(targeTr);
			}
			else{
				var tmpTr =nextTr.next();
				if(tmpTr.length==0) return;
				var targeTr=tmpTr.next();
				nextTr.insertAfter(targeTr);
				thisTr.insertAfter(targeTr);
			}
		};
		
		$(function(){
			var headHei = $('.header').height() ;
			var siblingNum = $('.header').siblings().length ;
			var conditionTable = $('.conditionContainer').find('table') ;
			var settingContainerHei = $('.l-layout-right').height()-headHei ;
			
			$('.header').next('.content').hide() ;
			$('.header').closest('.fieldContainer').css("height",headHei);
			
			$(".header").hover(
				  function () 
				  {
					 $(this).css("color","#ffffff");
				     $(this).css("cursor","pointer");
				  },
				  function () 
				  {
				     $(this).css("color","#222222");
					 $(this).css("cursor","pointer");
				  }
		   );
		   //加载或关闭二级菜单
		   $(".header").bind('click',
				function () 
				{
					settingContainerHei = $('.l-layout-right').height()-headHei ;
			        var divContentObj = $(this).next('.content');
			        var isHidden = $(divContentObj).css('display')=='none';
		           	$("div.content").hide();
		           
		           	$("div.content").closest('.fieldContainer').css("height",headHei);
			        if(isHidden){
			           	divContentObj.show() ;
			           	divContentObj.closest('.fieldContainer').css("height",settingContainerHei-siblingNum*headHei);
			           	divContentObj.css("height",settingContainerHei-(siblingNum+1)*headHei);
		            }
			        
				}
		   );
		   $("td[nowrap='nowrap']").dblclick(function(){
			  var field = $(this).text().trim() ;
			  if($('.javaField').css('display')!='none'){
				  InitMirror.editor.insertCode(field) ;
			  }
		   });
	      
	      $("div.tab-content div.tab-layout").attr("id", function(){return "No"+ $("div.tab-content div.tab-layout").index(this)});
	      $("ul.tab-menu li").click(function(){
	          var c = $("ul.tab-menu li");
	          var index = c.index(this);
	          $('#No'+index).siblings().hide();
	          $('#No'+index).show() ;
	          c.eq(index).addClass("current").siblings('li').removeClass("current");
			   if($('#No'+index).attr('class').indexOf('javaField')>=0){
					$('div.fieldBtnDiv').show() ;
				   curConditionField = "javaField" ;
			   }else{
				   $('.fieldBtnDiv').hide() ;
				   if($('#No'+index).attr('class').indexOf('helpField')<0)
				   		curConditionField = "" ;
			   }
	   	  });
	   
			$('.l-layout-center .btnContainer').css('margin-top',$('.l-layout-content').height()/2+"px") ;
			callQtip();
			$('select[name="ct"]').live('change',function(){
				var settingDiv = $(this).siblings('#settingDiv');
				if($(this).val()==0){
					settingDiv.show();
				}
				else{
					settingDiv.hide();
				}
			});
		})
		var curConditionField ;
		function javaFieldSelected(bool){
			$("ul.tab-menu li").removeClass("current");
			$("div.tab-content .tab-layout").hide();
			if(bool==true){
				$("ul.tab-menu li:nth-child(2)").addClass("current");
				$(".tab-content .javaField").show() ;
				$('div.fieldBtnDiv').show() ;
				curConditionField = "javaField" ;
				callQtip();
			}else{
				$("ul.tab-menu li:first-child").addClass("current");
				$("div.tab-content .tab-layout:first-child").show() ;
				curConditionField = "" ;
			}
		}
		function delDiv(target){
			$(target).closest('div').remove();
		}
		function callQtip(){
			$('.fieldBtnDiv :checkbox').qtip({
				content:$lang_form.bpmFormDialog.dynamic_incoming_qtip,
				style:"cream"
			})
		}

		//获取自定义对话框
		function getDialogs(){
			var url = __ctx + '/platform/form/bpmFormDialog/getAllDialogs.ht';
			$.ajax({
			    type:"get",
			    async:false,
			    url:url,
			    success:function(data){
					if (data) {
						for(var i=0,c;c=data[i++];){
							var opt = $('<option value="'+c.alias+'">'+c.name+'</option>');
							opt.attr("fields",c.resultfield);
							$("select[name='dialog-type']",$("#settingDiv")).append(opt);
						}
					}
			    }
			});
		};		
		//选择不同的对话框
		function dialogChange(obj){
			var dia=$(obj).find("option:selected");
			var v = dia.attr("fields");
			if(v){
				var paramSelector = $(obj).siblings("#dialog-param");
				var opt = paramSelector.find("option:first-child");
				paramSelector.text('');
				//添加   请选择…… option
				paramSelector.append(opt);
				var fields = $.parseJSON(v);
				for(var i=0,f;f=fields[i++];){
					opt = $('<option value="'+f.field+'">'+f.comment+'</option>');
					paramSelector.append(opt);
				}
			}
		}
	</script>
	<style type="text/css">
		body{ padding:2px; margin:0 0 0 0;overflow: hidden; }
		div.fieldContainer{border:1px solid #BED5F3;margin-top: 3px;height:143px;}
		div.content{height:110px;overflow: auto;}
		ul.btnContainer{text-align: center;}
		.l-layout-content{width:100% !important;}
		.l-layout-right{left:319px;height:480px;}
		.l-layout-right .l-layout-header-inner {padding-left : 0px;}
		.l-layout-center{height:480px;}
		/* tab样式 */
		.tab-box {width:99%; height:99%; margin:1px;}
		.tagMenu {height:20px; line-height:20px; background:#efefef; position:relative; border-bottom:1px solid #999}
		.tab-content .table-grid{margin-top: 0px;}
		.tagMenu ul {position:absolute; left:5px; bottom:-1px; height:26px;}
		ul.tab-menu li {float:left; margin-bottom:1px; line-height:16px; height:14px; margin:5px 0 0 -1px; text-align:center; padding:0 12px; cursor:pointer}
		ul.tab-menu li.current {border:1px solid #999; border-bottom:none; background:#fff; height:25px; line-height:26px; margin:0}
		.javaField, .helpField {border: 3px solid #DDDDDD;height:200px;float:left;width:98%;}
		.helpField {padding:0 5px 0 10px;width:95%;display:none;}
		.fieldBtnDiv {float:left;display:none;width:100%;}
		
		.table-detail .inputText {width:140px;}
		.inputText {vertical-align: middle;}
	</style>
	
</head>
<body>
		<div id="defLayout" >
	            <div position="left" title="获取字段列表" style="overflow: auto;width: 300px;height:450px;">
					<table cellpadding="1" class="table-grid table-list" cellspacing="1">
						<tr>
							<th></th>
							<th><spr:message code="bpmFormDialog.setting.field"/></th>
							<th><spr:message code="bpmFormDialog.setting.desc"/></th>
						</tr>
						<c:forEach var="col" items="${tableModel.columnList }" varStatus="status">
						<c:set var="clsName"  ><c:choose><c:when test="${status.index%2==0}">odd</c:when><c:otherwise>even</c:otherwise></c:choose> </c:set>
						<tr class="${clsName}">
							<td>
								<c:choose>
	            					<c:when test="${style==0 }">
										<input type="checkbox" name="fieldName"  class="pk"  value="${col.name }"  dbType="${col.columnType }">
									</c:when>
									<c:otherwise>
										<input type="checkbox" name="fieldName" class="pk"  value="${col.name }" id="${col.name }" dbType="${col.columnType }">
									</c:otherwise>
								</c:choose>
							</td>
							<td nowrap="nowrap">
								${col.name }
							</td>
							<td>
								<input type="text" name="comment" class="inputText" value="${col.comment }">
							</td>
						</tr>
						</c:forEach>
					</table>
	            </div>
	            <div position="center" >
	            	<c:choose>
	            		<c:when test="${style==0 }">
	            			<ul class="btnContainer">
			          			<li class="btn">
			          				 <a href='#' class='button'  onclick="setField()" ><span >=></span></a>
			          			</li>
			          		</ul>
	            		</c:when>
	            		<c:otherwise>
	            			<ul class="btnContainer">
			          			<li class="btnTree">
			          				<a href='#' class='button'  onclick="setField()" ><span >=></span></a>
			          			</li>
			          		</ul>
	            		</c:otherwise>
	            	</c:choose>
	            </div>  
	            <div id="fieldSetting" position="right"  title="<spr:message code='bpmFormDialog.setting.fieldSet'/>">
	            	<c:choose>
	            		<c:when test="${style==0 }">
			          		<div class="fieldContainer displayContainer">
			          			<div class="header">
			          				<spr:message code="bpmFormDialog.setting.showField"/>
			          			</div>
			          			<div class="content">
			          				<table cellpadding="1" class="table-grid table-list" cellspacing="1">
				          				<tr>
				          					<th>
				          						<spr:message code="bpmFormDialog.setting.fieldName"/>
				          					</th>
				          					<th>
				          						<spr:message code="bpmFormDialog.setting.showName"/>
				          					</th>
				          					<th>
				          						<spr:message code="list.manage"/>
				          					</th>
				          				</tr>
				          				<tbody id="trDisplayField">
				          					<c:forEach items="${ bpmFormDialog.displayList}" var="field">
				          						<tr id='display${field.fieldName}' name='${field.fieldName}' comment='${field.comment}'>
				          							<td>${field.fieldName}</td>
				          							<td>${field.comment}</td>
				          							<td>
				          							<a alt='<spr:message code="operator.moveup"/>' href='#' class='link moveup' onclick='sortTr(this,true)'>&nbsp;</a>
				          							<a alt='<spr:message code="operator.movedown"/>' href='#' class='link movedown' onclick='sortTr(this,false)'>&nbsp;</a>
				          							<a href='#' class='link del'  onclick='delRow(this)' ><spr:message code="menu.button.del"/></a>
				          							</td>
				          						</tr>
				          					</c:forEach>
				          				</tbody>
				          			</table>
			          			</div>
			          			
			          		</div>
			          	</c:when>
			          	<c:otherwise>
				          	<div class="panel-detail treeContainer">
				          		<div class="header" style="margin-top:5px;">
			          				<spr:message code="bpmFormDialog.setting.showField"/>
			          			</div>
			          			<div class="content">
				          		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
									<tr>
										<th width="20%">ID: </th>
										<td>
											<input type="text" id="treeId"  name="treeId"  class="inputText treeField" value="${ bpmFormDialog.treeField.id}"/>								
<!-- 											<input type="checkbox" name="treeReturn" checked="checked" />返回 -->
										</td>
									</tr>
									<tr>
										<th width="20%"><spr:message code="bpmFormDialog.setting.parentId"/>: </th>
										<td>
										<input type="text" id="parentId" name="parentId"  class="inputText treeField" value="${ bpmFormDialog.treeField.pid}" />
										</td>
									</tr>
									<tr>
										<th width="20%"><spr:message code="bpmFormDialog.setting.parentValue"/>: </th>
										<td>
											<textarea id="parentValue" name="parentValue" cols="40" rows="5"></textarea>
 											<input type="checkbox" name="isScript" id="isScript"/><spr:message code="script.script"/>
										</td>
									</tr>
									<tr>
										<th width="20%"><spr:message code="bpmFormDialog.setting.displayName"/>: </th>
										<td>
											<input type="text" id="displayName" name="displayName"  class="inputText treeField" value="${bpmFormDialog.treeField.displayName}"/>
<!-- 											<input type="checkbox" name="treeReturn" checked="checked" />返回 -->
											
										</td>
									</tr>
									
								</table>
								</div>
							</div>
			          	</c:otherwise>
			          </c:choose>
			          <div class="panel-detail conditionContainer">
	          			<div class="header" style="margin-top:5px;">
	          				<spr:message code="bpmFormDialog.setting.conditionField"/>
	          			</div>
	          			<div class="content">
	          			<div class="tab-box">
						    <div class="tagMenu">
						        <ul class="tab-menu">
						            <li><spr:message code="bpmFormDialog.setting.conditionSelect"/></li>
						            <li><spr:message code="bpmFormDialog.setting.javaScript"/></li>
						 			<li><spr:message code="bpmFormDialog.setting.help"/></li>
						        </ul>
						    </div>
						    <div class="tab-content">
						    <div class="tab-layout trConditionField">
	          			<table cellpadding="1" class="table-grid table-list" cellspacing="1">
	          				<tr>
	          					<th>
	          						<spr:message code="bpmFormDialog.setting.fieldName"/>
	          					</th>
	          					<th>
	          						<spr:message code="bpmFormDialog.setting.condition"/>
	          					</th>
	          					
	          					<th>
	          						<spr:message code="bpmFormDialog.setting.showName"/>
	          					</th>
	          					<th>
	          						<spr:message code="list.manage"/>
	          					</th>
	          				</tr>
	          				<tbody id="trConditionField">
	          					<c:forEach items="${ bpmFormDialog.conditionList}" var="field">
	          						<tr class='trCondition' id='condition${field.fieldName}' name='${field.fieldName}' comment='${field.comment}'>
	          							<td>${field.fieldName}</td>
	          							<td>
	          								<select class='condition' name='${field.fieldName}' dbType='${field.fieldType}' comment='${field.comment}' >
	          								<c:choose>
	          									<c:when test="${field.fieldType=='varchar'}">
		          									<option value='=' <c:if test="${field.condition=='='}">selected</c:if> ><spr:message code="condition.eq"/></option>
													<option value='like' <c:if test="${field.condition=='like'}">selected</c:if>>LIKE</option>
													<option value='likeEnd' <c:if test="${field.condition=='likeEnd'}">selected</c:if>>LIKEEND</option>
	          									</c:when>
	          									<c:when test="${field.fieldType=='number'}">
	          										<option value='=' <c:if test="${field.condition=='='}">selected</c:if>><spr:message code="condition.eq"/></option>
													<option value='>=' <c:if test="${field.condition=='>='}">selected</c:if> ><spr:message code="condition.gtEq"/></option>
													<option value='>' <c:if test="${field.condition=='>'}">selected</c:if> ><spr:message code="condition.gt"/></option>
													<option value='<' <c:if test="${field.condition=='<'}">selected</c:if> ><spr:message code="condition.lt"/></option>
													<option value='<=' <c:if test="${field.condition=='<='}">selected</c:if>><spr:message code="condition.ltEq"/></option>
	          									</c:when>
	          									<c:otherwise>
	          										<option value='=' <c:if test="${field.condition=='='}">selected</c:if>><spr:message code="condition.eq"/></option>
	          										<option value='between' <c:if test="${field.condition=='between'}">selected</c:if> >between</option>
													<option value='>=' <c:if test="${field.condition=='>='}">selected</c:if> ><spr:message code="condition.gtEq"/></option>
													<option value='<=' <c:if test="${field.condition=='<='}">selected</c:if> ><spr:message code="condition.ltEq"/></option>
	          									</c:otherwise>
	          								</c:choose>
	          								</select>
										</td>
										<td>${field.comment}</td>
	          							
	          							<td>
	          							<a alt='<spr:message code="operator.moveup"/>' href='#' class='link moveup' onclick='sortConditionTr(this,true)'></a>
	          							<a alt='<spr:message code="operator.movedown"/>' href='#' class='link movedown' onclick='sortConditionTr(this,false)'></a>
	          							<a href='#' class='link del'  onclick='delConditionTr(this)' ><spr:message code="menu.button.del"/></a>
	          							</td>
	          						</tr>
	          					

	          						<tr id='conditionVal${field.fieldName}'>
	          							<td><spr:message code="script.defValue"/></td>
	          							<td>
	          								<select name='changeDefalut' onchange='changeDefault(this)'>
	          									<option value="2" <c:if test="${field.defaultType=='2'}">selected</c:if>><spr:message code="script.fixedValue"/></option>
	          									<option value="3" <c:if test="${field.defaultType=='3'}">selected</c:if>><spr:message code="script.script"/></option>
	          								</select>
	          							</td>
	          							<td colspan="2">
	          							<div name='btnScript' <c:if test="${ field.defaultType=='2'}">style='display:none;'</c:if> >
	          							<a href='#' class='link var' title='<spr:message code="script.common"/>' onclick='selectScript(this)'><spr:message code="script.common"/></a>
	          							</div>
	          							<textarea name='defaultValue' cols='40' rows='3' >${field.defaultValue}</textarea></td>
	          						</tr>
	          					</c:forEach>
	          				</tbody>
	          			</table>
          				</div>
						 		
						        <div class="fieldBtnDiv" name="fieldBtnDiv">
								 </div>
						        <div class="tab-layout javaField">
						 			<textarea id="script" name="defaultValue" codemirror="true" mirrorheight="200px" name="script" rows="26" cols="70"></textarea>
						 		</div>
						 		<div class="tab-layout helpField">
						 			<span>
										例：if(map.get("ACTDEFID")!=null)<br>&nbsp;&nbsp;return " and ACTDEFID like '%"+map.get("ACTDEFID")+"%'" ;<br>&nbsp;<br>
										<spr:message code="bpmFormDialog.setting.tip1"/><br>&nbsp;<br>
										<spr:message code="bpmFormDialog.setting.tip2"/>
									</span>
						 		</div>
						    </div>
						</div>
          			</div>
	          		</div>
	          		<div class="panel-detail returnContainer">
	          			<div class="header">
	          				<spr:message code="bpmFormDialog.setting.returnField"/>
	          			</div>
	          			<div class="content" style="overflow: auto;">
		          			<table cellpadding="1" class="table-grid table-list" cellspacing="1">
		          				<tr>
		          					<th>
		          						<spr:message code="bpmFormDialog.setting.fieldName"/>
		          					</th>
		          					<th>
		          						<spr:message code="bpmFormDialog.setting.showName"/>
		          					</th>
		          					<th>
		          						<spr:message code="list.manage"/>
		          					</th>
		          				</tr>
		          				<tbody id="trRtnField">
		          					<c:forEach items="${ bpmFormDialog.returnList}" var="field">
		          						<tr id='rtn${field.fieldName}' name='${field.fieldName}' comment='${field.comment}'>
		          							<td>${field.fieldName}</td>
		          							<td>${field.comment}</td>
		          							<td>
		          							<a alt='<spr:message code="operator.moveup"/>' href='#' class='link moveup' onclick='sortTr(this,true)'>&nbsp;</a>
		          							<a alt='<spr:message code="operator.movedown"/>' href='#' class='link movedown' onclick='sortTr(this,true)'>&nbsp;</a>
		          							<a href='#' class='link del'  onclick='delRow(this)' ><spr:message code="menu.button.del"/></a>
		          							</td>
		          						</tr>
		          					</c:forEach>
		          				</tbody>
		          			</table>
		          		</div>
	          		</div>
		          <div class="panel-detail sortContainer">
	          			<div class="header">
	          				<spr:message code="bpmFormDialog.setting.sortField"/>
	          			</div>
	          			<div class="content">
	          			<div style="overflow: auto;">
		          			<table cellpadding="1" class="table-grid table-list" cellspacing="1">
		          				<tr>
		          					<th>
		          						<spr:message code="bpmFormDialog.setting.fieldName"/>
		          					</th>
		          					<th>
		          						<spr:message code="bpmFormDialog.setting.sort"/>
		          					</th>
		          					<th>
		          						<spr:message code="list.manage"/>
		          					</th>
		          				</tr>
		          				<tbody id="trSortField">
		          					<c:forEach items="${ bpmFormDialog.sortList}" var="field">
		          						<tr id="sort${field.fieldName}" name="${field.fieldName}">
		          							<td id="fieldName">${field.fieldName}</td>
		          							<td id="sort">
		          								<input type="checkbox" name="sort" id="ASC" <c:if test="${field.comment == 'ASC'}">checked="checked"</c:if> />
			          							&nbsp;<spr:message code="bpmFormDialog.setting.tip3"/>
		          							</td>
		          							<td>
		          							<a alt='<spr:message code="operator.moveup"/>' href='#' class='link moveup' onclick='sortTr(this,true)'>&nbsp;</a>
		          							<a alt='<spr:message code="operator.movedown"/>' href='#' class='link movedown' onclick='sortTr(this,false)'>&nbsp;</a>
		          							<a href='#' class='link del'  onclick='delRow(this)' ><spr:message code="menu.button.del"/></a>
		          							</td>
		          						</tr>
		          					</c:forEach>
		          				</tbody>
		          				<tr id="sortTrTemplate" style="display:none;">
		   							<td id="fieldName"></td>
		   							<td id="sort">
			   							<input type="checkbox" name="sort" id="ASC" checked="checked" />&nbsp;<spr:message code="bpmFormDialog.setting.tip3"/>
		   							</td>
		   							<td>
		   							<a alt='<spr:message code="operator.moveup"/>' href='#' class='link moveup' onclick='sortTr(this,true)'>&nbsp;</a>
          							<a alt='<spr:message code="operator.movedown"/>' href='#' class='link movedown' onclick='sortTr(this,false)'>&nbsp;</a>
          							<a href='#' class='link del'  onclick='delRow(this)' ><spr:message code="menu.button.del"/></a>
		   							</td>
		   						</tr>
		          			</table>
		          		</div>
	          			</div>
	          		</div>
	            </div>
	            <div position="bottom" class="bottom" style="padding-top: 15px;">
					<a href='#' class='button'  onclick="selectForm()" ><span class="icon ok"></span><span ><spr:message code="menu.button.ok"/></span></a>
			  		<a href='#' class='button' style='margin-left:10px;' onclick="window.close()"><span class="icon cancel"></span><span ><spr:message code="menu.button.cancel"/></span></a>
				</div>
				
				<input type="hidden" name="resultfield" id="resultfield" value="${bpmFormDialog.resultfield}"/>
				<input type="hidden" name="id" id="id" value="${bpmFormDialog.id}"/>
       	  </div>
       	  <div id="templateJavaDiv" style="display:none;">
       	  	<input type="checkbox"/>
       	  	<input type="text" />
       	  	<span></span>
       	  </div>
       	  
       	  <div id="templateDiv" style="display:none;padding:5px;width:45%;float:left;text-align:center;">
       	  	<input type="checkbox" />
       	  	<input type="text" value="" name="comment" />
       	  	<span><a href="#" class="link del" onclick="delDiv(this)"></a></span>
       	  </div>
       	  
       	  <div id="templateSelector" style="display:none;">
       	  	<select name="ct">
	       	  	<option value="1"><spr:message code="conditionScript.argument.control.singleInput"/></option>
				<option value="4"><spr:message code="conditionScript.argument.control.user"/></option>
				<option value="17"><spr:message code="conditionScript.argument.control.role"/></option>
				<option value="18"><spr:message code="conditionScript.argument.control.org"/></option>
				<option value="19"><spr:message code="conditionScript.argument.control.pos"/></option>
				<option value="0"><spr:message code="bpmFormDialog.setting.customDialog"/></option>
			</select>
			<div style="display: none;margin-top:5px;" id="settingDiv">
				<select id="dialog-type" name="dialog-type" onchange="dialogChange(this)" style="width:120px;">
					<option value=""><spr:message code="bpmFormDialog.setting.selectDialog"/>……</option>
				</select>
				<select id="dialog-param" name="dialog-param" style="width:120px;">
					<option value=""><spr:message code="bpmFormDialog.setting.selectReturnValue"/>……</option>
				</select>
			</div>
       	  </div>
</body>
</html>
