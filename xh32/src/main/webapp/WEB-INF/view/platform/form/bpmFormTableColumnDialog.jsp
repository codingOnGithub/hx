<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
<f:js pre="js/lang/view/platform/form" ></f:js>
<title><c:choose> <c:when test="${isAdd==1}"><spr:message code="operator.add"/><spr:message code="bpmFormField.title"/></c:when>
<c:otherwise><spr:message code="operator.edit"/><spr:message code="bpmFormField.title"/></c:otherwise> </c:choose> </title>

<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<f:link href="tree/zTreeStyle.css"></f:link>
<f:link href="reminder.css" ></f:link>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerComboBox.js"></script>
<script type="text/javascript" src="${ctx}/js/util/loadjscss.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/ScriptDialog.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/EditTable.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/FieldsManage.js"></script>
<script type="text/javascript" src="${ctx}/js/javacode/codemirror.js"></script>
<script type="text/javascript" src="${ctx}/js/javacode/InitMirror.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/IdentityList.js"></script>
<script type="text/javascript">
$(function(){
	//值来源
	handValueFrom();
	//字段类型修改
	handFieldType();
	//处理控件类型
	handControlType();
	//
	init();
	//处理条件字段isQuery
	handConditionClick();
	
	//处理货币coin
	handCoinClick();
	
});

var locale = "${locale}";
var __isFieldAdd__=true;

function init() {
    var conf = window.dialogArguments;
    //设置字段管理,主要是为了验证字段是否存在。
    TableRow.setFieldManage(conf.fieldManage);
    //在EditTable.js 的validateField 方法中有用到。
    __isFieldAdd__ = conf.isAdd;
    var valid = validateField();
    
    //添加字段
    if (__isFieldAdd__) {

        $("#dataFormSave").html("<span></span>"+$lang.button.add);
        initAdd(conf.isExternal);
    }
    //更新字段
    else {
        var allowEditColName = conf.allowEditColName;
        $("#dataFormSave").html("<span></span>"+$lang.button.save);
        initControlByField(conf.field, allowEditColName,conf.isExternal);
        var identityName=$("#identityName").val()
        if(!identityName)
        initIdentity();
    }
    $("#dataFormSave").click(function() {
        InitMirror.save();
        //获取字段.
        var field = getField();
        if (!valid.form()) return false;
        if (__isFieldAdd__) {
            var rtn = conf.callBack.call(this, field);
            if (!rtn) return;
            //重置字段
            resetField();
        } else {
            conf.callBack.call(this, field);
            $.ligerDialog.success($lang.save.success,$lang.tip.msg, function() {
                window.close();
            });
        }
    });

    $("#option-table>tbody").delegate('tr.editable-tr','mouseover mouseleave', function(e) {
 		 if (e.type == 'mouseover') {
            $(this).addClass('hover');
        } else {
            $(this).removeClass('hover');
        } 
    });
    
    //删除选项
    $("#option-table>tbody").delegate('a.del','click',function(e){
        stopBubble(e);
       if(confirm($lang.del.suredelete)){
	        var me = $(this),
	      		parentClass = me.parent().attr("class");
	      	if(parentClass=='editable-right-div'){//3.2版本
	      		var tr = me.parents('tr.editable-tr');
	      		tr.remove();
	      	}else{
	      		var normalTr = me.parents('tr.normalTr'),
	            	moreInfoTr = normalTr.next('tr.editable-tr');
	      		moreInfoTr.remove();
		        normalTr.remove();
	      	}           
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
    		    selector = "input[name='"+locale+"']";
    		curLan.text($(selector,editTableTr).val());
    		lanDetail.text(str.join('  '));
    		editTableTr.hide();
        }
    }
    stopBubble(e);
  });
    
    //上移选项
    $("#option-table>tbody").delegate('a.moveup','click',function(e){
      	stopBubble(e);
      	var me = $(this),
      		parentClass = me.parent().attr("class");
      	if(parentClass=='editable-right-div'){
      		move1(me,true);
      	}else{
      		move(me,true);
      	}
    });
    //下移选项
    $("#option-table>tbody").delegate('a.movedown','click',function(e){
      	 stopBubble(e);
      	var me = $(this),
	 		parentClass = me.parent().attr("class");
	 	if(parentClass=='editable-right-div'){
	 		move1(me,false);
	 	}else{
	 		move(me,false);
	 	}
    });
    
    //新增选项
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
};

/**
 * 上下移动
 * @param {} obj 移动的对象
 * @param {} flag 上移 true,下移 false
 */
 function move(obj,flag){         
    var trNormalObj = obj.parents('tr.normalTr'),
    	trObj = trNormalObj.next('tr.editable-tr');
	if(flag){
		var prevObj=trNormalObj.prev().prev();
		if(prevObj.length>0){
			trObj.insertBefore(prevObj);
			trNormalObj.insertBefore(trObj);	
		}
	}else{
		var nextObj=trObj.next().next();
		if(nextObj.length>0){
			trNormalObj.insertAfter(nextObj);
			trObj.insertAfter(trNormalObj);
		}
	}
}
 
 /**
  * 上下移动(兼容3.2版本)
  * @param {} obj 移动的对象
  * @param {} flag 上移 true,下移 false
  */
  function move1(obj,flag){         
 	var  trObj = obj.parents('tr.editable-tr');
 	if(flag){
 		var prevObj=trObj.prev();
 		if(prevObj.length>0){
 			trObj.insertBefore(prevObj);	
 		}
 	}else{
 		var nextObj=trObj.next();
 		if(nextObj.length>0){
 			trObj.insertAfter(nextObj);
 		}
 	}
 }

function selectScript() {
    ScriptDialog({
        callback: function(script) {
            InitMirror.editor.insertCode(script);
        }
    });
}
//根据字段描述生成字段名
function autoGetKey(inputObj){
	var url=__ctx + '/platform/form/bpmFormTable/getFieldKey.ht' ;
	var subject=$(inputObj).val();
	if($.trim(subject).length<1) return;
	$.ajax({
		  url: url,
		  async:false,
		  type:'POST',
		  data: ({subject : subject}),
		  success: function(data){
			  var json=eval('('+data+')');
				if(json.result==1 && $.trim($('#fieldName').val()).length<1    ){			
					$('#fieldName').val(json.message);
					var valid=validateField();
					valid.form();
				
				}
		  }
	});
}

//弹出页面选择流水号
function ChooseIdentity(){
	var callBack=function(identityAlias,identityName){
		$("#identityName").val(identityName);
		$("#identityAlias").val(identityAlias);
	}
	IdentityList(callBack);
}

//初始化流水号，根据别名取名称
function initIdentity(){
	var identityAlias=$("#identityAlias").val();
	if(!identityAlias)return;
	//debugger
	var url=__ctx+'/platform/system/identity/getById.ht'
	$.ajax({
		url:url,
		async:false,
		type:'POST',
		data: ({alias: identityAlias}),
		success: function(data){
		var json=eval('('+data+')');
			if(json.result==1 ){	
		 		$("#identityName").val(json.message);
		 	}
		}
	});
}

</script>
</head>
<body>
<div class="panel">
		<div class="hide-panel">
			<div class="panel-top">
					<div class="tbar-title">
						<c:choose> <c:when test="${isAdd==1}"><spr:message code="operator.add"/><spr:message code="bpmFormField.title"/></c:when><c:otherwise><spr:message code="operator.edit"/><spr:message code="bpmFormField.title"/></c:otherwise> </c:choose>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group">
								<a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.add"/></a>
							</div>
							<div class="group">
								<a class="link del" href="#" onclick="window.close();"><span></span><spr:message code="menu.button.close"/></a>
							</div>
						</div>
					</div>
			</div>
		</div>
	<form id="frmFields" action="">
		<div class="panel-detail">
			<input type="hidden" id="fieldId" name="fieldId"/>
			<input type="hidden" id="curIsMain" value="${isMain}" />
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width=100><spr:message code="bpmFormField.fieldDesc"/>:</td>
					<td><input type="text" id="fieldDesc" name="fieldDesc" value="" class="inputText"   onblur="autoGetKey(this)"/></td>
					
					<th width=100><spr:message code="bpmFormField.fieldName"/>:</td>
					<td><input type="text" id="fieldName" maxlength="20" name="fieldName" value="" class="inputText"/></td>
					
				</tr>
				<tr>
					<th width=100><spr:message code="bpmFormField.fieldType"/>:</td>
					<td>
						<select id="fieldType" name="fieldType">
							<option value="varchar"><spr:message code="bpmFormField.fieldType.varchar"/></option>
							<option value="number"><spr:message code="bpmFormField.fieldType.number"/></option>
							<option value="date"><spr:message code="bpmFormField.fieldType.date"/></option>
							<option value="clob"><spr:message code="bpmFormField.fieldType.clob"/></option>
						</select>
						<span id="spanDateFormat" style="display:none">
							<br>
							<spr:message code="bpmFormField.currentDate"/>：<input type="checkbox" id="isCurrentDate" name="isCurrentDate" value="1">
							<br>
							<select id="selDateFormat" >
								<option value="yyyy-MM-dd">yyyy-MM-dd</option>
								<option value="yyyy-MM-dd HH:mm:ss">yyyy-MM-dd HH:mm:ss</option>
								<option value="yyyy-MM-dd HH:mm:00">yyyy-MM-dd HH:mm:00</option>
								<option value="yyyy-MM-dd HH:mm:ss">HH:mm:ss</option>
							</select>
						</span>
					</td>
					
					<th width=100><spr:message code="bpmFormField.len"/>:</td>
					<td>
						<span id="spanCharLen">
							<input type="text" id="charLen" name="charLen" value="50" class="inputText" style="width:40px" />
						</span>
						
						<span id="showComdify" style="display: none">
							<label><spr:message code="bpmFormField.isShowComdify"/>:</label>
							<input id="isShowComdify" name="isShowComdify" type="checkbox"  value="1"/>
						</span>
						<span id="spanCoin" style="display: none">
							<label><spr:message code="bpmFormField.coin"/>:</label>
							<input id="coin" name="coin" type="checkbox" value="1"/>
						</span>
						<span id="spanCoinType" style="display: none">
							<select id="CoinType" name="CoinType">
								<option value="￥"><spr:message code="bpmFormField.RMB"/></option> 
								<option value="$"><spr:message code="bpmFormField.Dollar"/></option>
							</select>
						</span>
				
						<span id="spanIntLen" style="display: none">
							<label><spr:message code="bpmFormField.intLen"/>:</label>
							<input type="text" id="intLen" name="intLen" value="13" class="inputText" size="4" style="width:40px"/>
						</span>
						
					
						<span id="spanDecimalLen" style="display: none">
							<label><spr:message code="bpmFormField.decimalLen"/>:</label>
							<input type="text" id="decimalLen" name="decimalLen" value="0" class="inputText" style="width:20px" />
						</span>
					</td>
				</tr>
			
				<tr>
					<th width=100><spr:message code="bpmFormField.option"/>:</td>
					<td colspan="3">
						<span><input id="isRequired" name="isRequired" type="checkbox" value="1"/>&nbsp;<spr:message code="bpmFormField.isRequired"/>&nbsp;</span>
						<c:if test="${isMain==1}">
						<span><input id="isList" name="isList" type="checkbox" checked="checked" value="1"/>&nbsp;<spr:message code="bpmFormField.isList"/>&nbsp;</span>
						<span><input id="isQuery" name="isQuery" type="checkbox" value="1"/>&nbsp;<spr:message code="bpmFormField.isQuery"/>&nbsp;</span>
						</c:if>
						<span><input id="isFlowVar" name="isFlowVar" type="checkbox" value="1"/>&nbsp;<spr:message code="bpmFormField.isFlowVar"/>&nbsp;</span>
						<span id="showIsReference" style="display:none;"><input id="isReference" name="isReference" type="checkbox" checked="checked" value="1"/>&nbsp;<spr:message code="bpmFormField.isReference"/>&nbsp;</span>
						<span><input id="isWebSign" name="isWebSign" type="checkbox" value="1"/>&nbsp;<spr:message code="bpmFormField.isWebSign"/>&nbsp;</span>
					</td>
				</tr>
				<tr>
					<th width=100 ><spr:message code="bpmFormField.valFrom"/>:</td>
					<td colspan="3">
						<select id="valueFrom" name="valueFrom">
							<option value="0"><spr:message code="script.formInput"/></option>
							<option value="1"><spr:message code="bpmFormField.scriptDisplay"/></option>
							<option value="2"><spr:message code="bpmFormField.scriptHide"/></option>
							<option value="3"><spr:message code="controller.identity"/></option>
						</select>
						<span name="showidentity" id="showidentity">
							<input type="hidden" value="" name="identityAlias" id="identityAlias">
							<input type="text" nema="identityName" id="identityName" value="" readonly="readonly"> 
							<input type="button" name="identitybut" id="identitybut" onclick="ChooseIdentity()" value="<spr:message code="menu.button.choose"/>">
						</span>
					</td>
				</tr>
				<tr id="trControlType" >
					<th width=100><spr:message code="bpmFormField.controlType"/>:</td>
					<td colspan="3"><select id="controlType" name="controlType"></select></td>
				</tr>
				<tr id="trDict" >
					<th width=100><spr:message code="bpmFormField.dictType"/>:</td>
					<td colspan="3">
						<input id="dictTypeName" class="catComBo" catKey="DIC" valueField="dictType" isNodeKey="true" name="dictTypeName" height="150" width="200"/>
					</td>
				</tr>
				<tr id="trRule" >
					<th width=100><spr:message code="bpmFormField.validRule"/>:</td>
					<td colspan="3">
						<select id="validRule" name="validRule">
							<option value=""><spr:message code="search.select.none"/></option>
							<c:forEach items="${validRuleList}" var="rule">
								<option value="${rule.name}">${rule.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				
				<tr id="trScript" >
					<th width=100><spr:message code="bpmFormField.script"/>:</td>
					<td colspan="3">
						<a href="#" onclick="selectScript()" id="btnScript" class="link var" title="<spr:message code="script.common"/>"><spr:message code="script.common"/></a>
						<br/><spr:message code="bpmFormField.scriptTip"/><br />
						<textarea id="script" codemirror="true" name="script" rows="6" cols="70"></textarea>
					</td>
				</tr>
				<tr id="trScriptID" >
					<th width=100>ID<spr:message code="bpmFormField.script"/>:</th>
					<td colspan="3">
						<a href="#" onclick="selectScript()" id="btnScriptID" class="link var" title="<spr:message code="script.common"/>"><spr:message code="script.common"/></a>
						<br/><spr:message code="bpmFormField.scriptTip"/><br />
						<textarea id="scriptID" codemirror="true" mirrorheight="80px" name="scriptID" rows="4" cols="70"></textarea>
					</td>
				</tr>
				<tr id="trOption" >
					<th width=100><spr:message code="bpmFormField.options"/>:</td>
					<td colspan="3">
					<div id="panel" class="s">			
							<table id="option-table">
								<thead>
									<tr>
										<td colspan="2">
											<a href="###" class="link add"><spr:message code="menu.button.add"/></a>
										</td>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
							<div class="hidden">
								<table id="hiddenTable">
									<tbody>
									<tr class="normalTr">
										<td>
											<div class="detail">
												<span class="lanDetail"></span>
											</div>
										</td>
										<td>
											<div>
												<div style="float:left;">
													<span class="curLan"></span>
												</div>
												<div style="float:right;" class="move">
													<a href="#" class="moveup" title="<spr:message code="menu.button.moveUp"/>" tabindex="-1"><spr:message code="menu.button.moveUp"/></a>
					                                <a href="#" class="movedown" title="<spr:message code="menu.button.moveDown"/>"  tabindex="-1"><spr:message code="menu.button.moveDown"/></a>
					                                <a href="#" class="del"  title="<spr:message code="sys.remove"/>" tabindex="-1"><spr:message code="sys.remove"/></a>
												</div>
											</div>
										</td>
									</tr>
									<tr class="editable-tr">
					                        <td colspan="2">
					                            <div class="editable-left-div">
					                                 <label><spr:message code="bpmFormField.val"/>: <input name="optionKey" type="text" style="height:21px;"/></label>
             	 									 <fieldset style="margin:0 30px 0 0">
														<legend><spr:message code="bpmFormField.option"/></legend>
														<c:forEach items="${languages}" var="language">
															<div>
																<span>${language.memo}:</span>
																<label><input type="text" class="long" title="${language.memo}" name="${language.language}" /></label>
															</div>
														</c:forEach>
													</fieldset>
					                            </div>
					                        </td>
					                    </tr>
									</tbody>
								</table>
							</div>
							<div class="hidden">
								<table id="hiddenTable32">
									<tbody>
									<tr class="editable-tr">
					                        <td>
					                            <div class="editable-left-div32">
					                                 <label><spr:message code="bpmFormField.val"/>: <input name="optionKey" type="text" style="height:21px;"/></label>
               	 									 <label style="margin:0 0 0 10px;"><spr:message code="bpmFormField.option"/>: <input name="optionValue" class="long" type="text"/></label>
					                            </div>
					                        </td>
					                        <td>
					                            <div class="editable-right-div">
					                                <a href="#" class="moveup" title="<spr:message code="menu.button.moveUp"/>" tabindex="-1"><spr:message code="menu.button.moveUp"/></a>
					                                <a href="#" class="movedown" title="<spr:message code="menu.button.moveDown"/>"  tabindex="-1"><spr:message code="menu.button.moveDown"/></a>
					                                <a href="#" class="del"  title="<spr:message code="sys.remove"/>" tabindex="-1"><spr:message code="sys.remove"/></a>
					                            </div>
					                        </td>
					                    </tr>
									</tbody>
								</table>
							</div>
						</div>
					</td>
				</tr>
				<!-- <tr id="formUserTr" style="display:none;">
					<th width=100>
						是否表单用户:
					</td>
					<td colspan="3">
						<label><input id="ifFormUser" name="ifFormUser" type="checkbox" /><span id="formUserSpan"></span></label>
					</td>
				</tr> -->
				<tr id="trUpLoad" style="display:none;">
					<th width=120>
						<spr:message code="bpmFormField.upLoad"/>:
					</th>
					<td colspan="3">
						<label><input id="isDirectUpLoad" name="isDirectUpLoad" type="checkbox" /></label>
					</th>
				</tr>
				<tr id="showCurUserTr" style="display:none;">
					<th width=120>
						<spr:message code="bpmFormField.curUser"/>:
					</th>
					<td colspan="3">
						<label><input id="showCurUser" name="showCurUser" type="checkbox" /></label>
					</th>
				</tr>
				<tr id="showCurOrgTr" style="display:none;">
					<th width=120>
						<spr:message code="bpmFormField.curOrg"/>:
					</th>
					<td colspan="3">
						<label><input id="showCurOrg" name="showCurOrg" type="checkbox" /></label>
					</td>
				</tr>
				<tr id="showCurPosTr" style="display:none;">
					<th width=120>
						<spr:message code="bpmFormField.curPos"/>:
					</th>
					<td colspan="3">
						<label><input id="showCurPos" name="showCurPos" type="checkbox" /></label>
					</td>
				</tr>
			</table>
		</div>		
	</form>
</div>
</body>
</html>