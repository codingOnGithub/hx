<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
<title><spr:message code="taskReminder.title"/></title>
<base target="_self" />

<%-- <link href="${ctx}/styles/default/css/jquery.qtip.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/codemirror/lib/codemirror.css" > --%>
<f:link href="jquery.qtip.css"></f:link>
<f:link href="codemirror/lib/codemirror.css"></f:link>
<f:js pre="js/lang/view/platform/bpm" ></f:js>
<script type="text/javascript" src="${ctx}/js/hotent/displaytag.js" ></script>
<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=taskReminder"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/TemplateDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/ScriptDialog.js" ></script>
<script type="text/javascript" src="${ctx}/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${ctx}/js/ckeditor/ckeditor_remind.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.qtip.js" ></script>
<script type="text/javascript" src="${ctx}/js/codemirror/lib/codemirror.js"></script>
<script type="text/javascript" src="${ctx}/js/codemirror/lib/util/matchbrackets.js"></script>
<script type="text/javascript" src="${ctx}/js/codemirror/mode/groovy/groovy.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<style>
 .sub{ display: none;}
 .condExp-control{
 	width: 98%;
 	padding: 2px;
 	margin:2px;
 	border: solid 1px #A8CFEB;
 }
 .condExp-control [name='flowOperate']{
 	width:65px;
 }
 .condExp-editor{
 	width: 540px;
 	padding: 2px;
 	margin:2px;
 	height: 100px;
 	border: solid 1px #A8CFEB;
 	overflow: auto;
 }
 .condExp-editor-input{
 	width:100%;
 	height: 100px;
 }
 
 .script-control{
 	width: 98%;
 	padding: 2px;
 	margin:2px;
 	border: solid 1px #A8CFEB;
 }
 .script-editor{
	 width: 98%;
 	padding: 2px;
 	margin:2px;
 	height: 100px;
 	border: solid 1px #A8CFEB;
 	overflow: auto;
 }
  .script-editor-input{
 	width:100%;
 	height: 100px;
 }
 .send-msg-tr{
 	display:none;
 }
 .choose-assigner{
 	display:none;
 }
 .day-input{
 	width:40px;
 }
</style>
<script type="text/javascript">        
		function showRequest(formData, jqForm, options) { 
			return true;
		} 
		//CodeMirror Editor
		var condExpScriptEditor=null,
			actionScriptEditor=null,
			curTime = '${taskReminder.times}';
		
		$(function() {
			$("input.day-input").focus(function(){
				$(this).select();
			});
			//total page layout
			$("#reminder-layout").ligerLayout({
				rightWidth:210
			});
			//是否发送催办信息的checkbox
			$("#needSendMsg").change(function(){
				var me = $(this),
					sendMsg = me.attr("checked");
				
				if(sendMsg){
					$(".send-msg-tr").show();
				}
				else{
					$("select[name='times']").val(0);
					$(".send-msg-tr").hide();
				}
			});
			if(curTime>0){
				$("#needSendMsg").attr("checked","checked").trigger("change");
			}
			//TaskReminder form Edit Layout
			$("#reminder-div-tab").ligerTab({
			});
			//reminder action change handle
			change();
			//save reminder
			$("a.save").click(save);
			//new reminder
			$("a.add").click(add);
			//ckeditor Editor
			editorMail=ckeditor('mailContent');
			editorMsg=ckeditor('msgContent');	
			
			setTimeout(function(){
				var height=$("#condExp").height();
				condExpScriptEditor = CodeMirror.fromTextArea(document.getElementById("condExp"), {
					mode: "text/x-groovy",
			        lineNumbers: true,
			        matchBrackets: true
			    });
				condExpScriptEditor.setSize(null,height);
			},0);
			setTimeout(function(){
				var height=$("#script").height();
				actionScriptEditor = CodeMirror.fromTextArea(document.getElementById("script"), {
					mode: "text/x-groovy",
			        lineNumbers: true,
			        matchBrackets: true
			    });
				actionScriptEditor.setSize(null,height);
			},0);
			
			$("#reminders-list-table tbody tr").click(function(){
				var id=$(this).find("input.pk").val();
				url=__ctx + "/platform/bpm/taskReminder/edit.ht?actDefId=${actDefId}&nodeId=${nodeId}&id=" + id;
				document.getElementById('goLocation').href = url;
				document.getElementById('goLocation').click();
			});
		});
		/**
		* Action Change handler
		*/
		function change(){
			var s= $("#action").val();
			$(".sub").hide();
			$(".choose-assigner").hide();
			if(s==7){//选择执行脚本
				$(".sub").show();
				if(actionScriptEditor){
					actionScriptEditor.refresh();
				}
			}
			if(s==5){//选择交办
				$(".choose-assigner").show();
			}
		}
		
		/**
		* Select Template
		*/
		function slectTemplate(txtId,isText){
			var objcondExpCode=document.getElementById(txtId);
		    TemplateDialog({isText:isText,callback:function(content){
		    	if(isText)
					jQuery.insertText(objcondExpCode,content);
		    	else{		    		
		    		CKEDITOR.instances[txtId].setData(content);
		    	}				
			}});
		};		
		
		/**
		* 添加
		*/
		function add(){
			url=__ctx + "/platform/bpm/taskReminder/edit.ht?actDefId=${actDefId}&nodeId=${nodeId}&id=0";
			document.getElementById('goLocation').href = url;
			document.getElementById('goLocation').click();
		}
		/**
		* 保存
		*/
		function save(){
			condExpScriptEditor.save();
			actionScriptEditor.save();
			var name=$("#name").val();
			if(!name){
				$.ligerDialog.warn($lang_bpm.taskReminder.edit.nameMeg,$lang.tip.msg);
				return;
			}
			var ctime=getTotalMinute($("#completeTr"));
			var stime=getTotalMinute($("#startTr"));
			//每次时间间隔*催办次数。
			var etime=getTotalMinute($("#endTr")) * ( parseInt($("#times").val())-1);
			if(ctime<stime+etime){				
				$.ligerDialog.warn($lang_bpm.taskReminder.edit.timeMeg,$lang.tip.msg);
				return;
			}
			$(".ckeditor-editor").each(function(){
				$(this).val(CKEDITOR.instances[$(this).attr('name')].getData());
			});
			 var rtn=$("#taskReminderForm").valid();
	   		 if(!rtn) return;
			 var url=__ctx+ "/platform/bpm/taskReminder/save.ht";
	   		 var para=$('#taskReminderForm').serialize();
	   		 $.post(url,para,showResult);
		}
		function showResult(responseText){
			var obj=new com.hotent.form.ResultMessage(responseText);
			if(!obj.isSuccess()){
				$.ligerDialog.err($lang.tip.errorMsg,$lang_bpm.taskReminder.reminderFail,obj.getMessage());
				return;
			}else{
				$.ligerDialog.confirm(obj.getMessage()+','+$lang.operateTip.continueOp,$lang.tip.msg,function(rtn){
					if(!rtn){
						window.close();
					}else{
						url=__ctx + "/platform/bpm/taskReminder/edit.ht?actDefId=${actDefId}&nodeId=${nodeId}&id=0";
						document.getElementById('goLocation').href = url;
						document.getElementById('goLocation').click();
					}
				});
			}
		}
		
		/**
		* 
		*/
		function scriptSelectScript(obj){
			ScriptDialog({callback:function(script){
				var pos=scriptScriptEditor.getCursor();
				scriptScriptEditor.replaceRange(script,pos);
			}});
		}
		/**
		*
		*/
		function condExpSelectScript(obj){
			ScriptDialog({callback:function(script){
				var pos=condExpScriptEditor.getCursor();
				condExpScriptEditor.replaceRange(script,pos);
			}});
		}
		
		function getTotalMinute(e){
			var t=0;
			$(e).find(".dayInput").each(function(){
				t+= parseInt(3600* this.value);
			});
			$(e).find(".hourInput").each(function(){
				t+=parseInt(60* this.value);
			});			
			$(e).find(".minuteInput").each(function(){
				t+=parseInt(this.value);
			});
			return t;
		}
		
		function constructFlowOperate(type){
			var select = $("select[name='flowOperate']");
			select.html("");
			type=type.toLowerCase();
			switch(type){
			case 'int':
			case 'number':
			case 'date':
				var eq=$("<option value='eq'>"+$lang.operateType.eq+"</option>");
				var ne=$("<option value='ne'>"+$lang.operateType.notEq+"</option>");
				var gt=$("<option value='gt'>"+$lang.operateType.gt+"</option>");
				var lt=$("<option value='lt'>"+$lang.operateType.lt+"</option>");
				select.append(eq);
				select.append(ne);
				select.append(gt);
				select.append(lt);
				break;
			case 'varchar':
				var eq=$("<option value='eq'>"+$lang.operateType.eq+"</option>");
				var ne=$("<option value='ne'>"+$lang.operateType.notEq+"</option>");
				select.append(eq);
				select.append(ne);
				break;
			}
		}
		
		function dateTimePicker(){
				WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true});
				$(this).blur();
		}
		/**
		* Select a different flow var
		* @params obj,target dom object
		*/
		function selectFlowVar(obj,type){
			var obj=$(obj);
			obj.qtip("destroy");
			if(type==1){
				var fname=obj.val();
				if(!fname){
					return;
				}
				var ftype = obj.find("option:selected").attr("ftype");
				constructFlowOperate(ftype);
				
				var valueInput=$("<input name='flowValue'/>");
				var oldValueInput=$("input[name='flowValue']");
				
				if('date'==ftype.toLowerCase()){
					valueInput.addClass("date");
					valueInput.focus(dateTimePicker);
				}
				oldValueInput.replaceWith(valueInput);
				
			}else if(type==2){
				var fname = obj.val();
				var pos=actionScriptEditor.getCursor();
				actionScriptEditor.replaceRange(fname,pos);
			}
			var opt = obj.find("option:selected")
			var fname=opt.attr("fname");
			var fdesc=opt.attr("fdesc");
			var ftype=opt.attr("ftype");
			ftype=dbTypeToGroovyType(ftype);
			var content=""
				+"<table class='table-detail'>"
					+"<tr>"
						+"<th>"+$lang_bpm.taskReminder.content.name+"</th>"
						+"<td>"+fname+"</td>"
					+"</tr>"
					+"<tr>"
						+"<th>"+$lang_bpm.taskReminder.content.desc+"</th>"
						+"<td>"+fdesc+"</td>"
					+"</tr>"
					+"<tr>"
						+"<th>"+$lang_bpm.taskReminder.content.type+"</th>"
						+"<td>"+ftype+"</td>"
					+"</tr>"
				+"</table>";
			obj.qtip({
				content:content
			});
		}
		
		/**
		* Generate Express from Gui setting
		* @params obj,target dom object
		*/
		function generateExpress(obj){
			var div = $(obj).closest("div.condExp-control");
			var flowVar=div.find("select[name='flowVar']").find("option:selected");
			var flowVarName=flowVar.val();
			var flowVarType=flowVar.attr("ftype");
			var flowVarOperate=div.find("select[name='flowOperate']").find("option:selected").val();
			var flowVarValue=div.find("input[name='flowValue']").val();
			
			if(!flowVarName){
				$.ligerDialog.warn($lang_bpm.taskReminder.selFlowVar);
				return;
			}
			if(!flowVarOperate){
				$.ligerDialog.warn($lang_bpm.taskReminder.selVarType);
				return;
			}
			if(!flowVarValue){
				$.ligerDialog.warn($lang_bpm.taskReminder.inputFlowVarVal);
				return;
			}
			var exp=null;
			flowVarType=flowVarType.toLowerCase();
			switch(flowVarType){
			case 'int':
			case 'number':
				switch(flowVarOperate){
				case "eq":
					exp = flowVarName+" == "+flowVarValue;
					break;
				case "ne":
					exp = flowVarName+" != "+flowVarValue;
					break;
				case "gt":
					exp = flowVarName+" > "+flowVarValue;
					break;
				case "ge":
					exp = flowVarName+" >= "+flowVarValue;
					break;
				case "lt":
					exp = flowVarName+" < "+flowVarValue;
					break;
				case "le":
					exp = flowVarName+" <= "+flowVarValue;
					break;
				}
				break;
			case 'date':
				flowVarValue = "com.hotent.core.util.TimeUtil.convertString(\""+flowVarValue+'\","yyyy-MM-dd HH:mm:ss")';
				switch(flowVarOperate){
				case "eq":
					exp = flowVarName+".compareTo("+flowVarValue+") == 0";
					break;
				case "ne":
					exp = flowVarName+".compareTo("+flowVarValue+") !=0 ";
					break;
				case "gt":
					exp = flowVarName+".compareTo("+flowVarValue+") > 0";
					break;
				case "lt":
					exp = flowVarName+".compareTo("+flowVarValue+") < 0";
					break;
				}
				break;
			case 'varchar':
				switch(flowVarOperate){
				case "eq":
					exp = flowVarName+".equals(\""+flowVarValue+"\")";
					break;
				case "ne":
					exp =" !"+flowVarName+".equals(\""+flowVarValue+"\")";
					break;
				}
				break;
			}
			var pos=condExpScriptEditor.getCursor();
			condExpScriptEditor.replaceRange(exp,pos);
		}
		
		/**
		* 数据库类型到Groovy类型的转换
		* @params type relation data type
		* @return groovy type
		*/
		function dbTypeToGroovyType(type){
			type=type.toLowerCase();
			var t;
			switch(type){
			case 'int':
				t='int';
				break;
			case 'number':
				t='double';
				break;
			case 'date':
				t='java.lang.Date';
				break;
			case 'varchar':
			case 'clob':
				t='java.lang.String';
				break;
			default:
				t=type;		
			}
			return t;
		};
		//选择交办人
		function chooseAssigner(){
			UserDialog({
				isSingle:true,
				callback:function(userId,fullname){
					if(userId=='' || userId==null || userId==undefined) return;
					$("input[name='assignerId']").val(userId);
					$("input[name='assignerName']").val(fullname);
				}
			});
		};
	</script>
   </head>
<body>
<div class="panel">
		<div class="panel-top">
				<div class="tbar-title">
					<span class="tbar-label"><spr:message code="taskReminder.name"/></span>
				</div>
				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group"><a class="link add" id="btnAdd"><span></span><spr:message code="menu.button.add"/></a></div>
						<div class="l-bar-separator"></div>
						<div class="group"><a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.save"/></a></div>
						<div class="l-bar-separator"></div>
						<div class="group"><a class="link del" onclick="javascript:window.close();"><span></span><spr:message code="menu.button.close"/></a></div>
					</div>
				</div>
		</div>
		<div class="panel-body">
			<div class="reminder-layout" id="reminder-layout">
				<div class="reminder-edit" position="center">
					<div style="height:570px;overflow: auto;">
					<form id="taskReminderForm" method="post" action="save.ht">
						<div class="reminder-div" >
							<div class="reminder-div-tab" id="reminder-div-tab">
							<div class="reminder-div-base" title="<spr:message code='taskReminder.basicInfor'/>">
								<div class="panel-detail">
								<fieldset class="fieldset-detail">
									<legend>
										<span><spr:message code='taskReminder.bueToCondition'/></span>
									</legend>
									<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
											<tr>
												<th width="120px"><spr:message code="taskReminder.name"/>:</th>
												<td >
													<input id="name" name="name" value="${taskReminder.name}" class="inputText" />
												</td>
<!-- 												<th ><spr:message code="taskreminder.default"/>:</th> -->
<!-- 												<td > -->
<%-- 													<input id="isDefaultTrue" name="isDefault" type="radio" value="1"  <c:if test="${taskReminder.isDefault==1 }">checked="checked"</c:if> /> --%>
<!-- 													<label for="isDefaultTrue"><spr:message code="taskreminder.default.yes"/></label>				 -->
<%-- 													<input id="isDefaultFalse" name="isDefault" type="radio" value="0" <c:if test="${taskReminder.isDefault!=1 }">checked="checked"</c:if> /> --%>
<!-- 													<label for="isDefaultFalse"><spr:message code="taskreminder.default.no"/></label> -->
<!-- 												</td> -->
												<th width="100px"><spr:message code="taskReminder.curNode"/>:</th>
												<td><input type="text" value="${nodeId}" disabled="disabled"/></td>
											</tr>
											<tr>
												<th><spr:message code="taskReminder.relativeNode"/>:</th>
												<td>
													<select name="relativeNodeId">
														<c:forEach items="${nodes}" var="node">
																<option value="${node.nodeId}" <c:if test="${node.nodeId==taskReminder.relativeNodeId}">selected="selected"</c:if>>${node.nodeName}</option>
														</c:forEach>
													</select>
												</td>
												<th><spr:message code="taskReminder.relativeAction"/>:</th>
												<td>
													<select name="relativeNodeType">
														<option value="0" ><spr:message code="taskReminder.relativeAction.create"/></option>
														<option value="1" <c:if test="${taskReminder.relativeNodeType==1}">selected="selected"</c:if>><spr:message code="taskReminder.relativeAction.finish"/></option>
													</select>
												</td>
											</tr>
											<tr>
												<th ><spr:message code="taskReminder.relativeTime"/>: </th>
												<td id="completeTr">
												    <input class="day-input" type="text" name="completeTimeDay" value="${completeTimeDay}"/>
													<span><spr:message code="taskReminder.completeTimeDay"/></span>
													<select id="completeTimeHour" class="hourInput" name="completeTimeHour">
														<c:forEach var="i" begin="0" end="23" step="1">
															<option value="${i}" <c:if test="${completeTimeHour==i}">selected="selected"</c:if>>${i}<spr:message code="taskReminder.completeTimeHour"/></option>
														</c:forEach>
													</select>
													<select id="completeTimeMinute" class="minuteInput" name="completeTimeMinute">
														<c:forEach var="i" begin="0" end="4" step="1">
															<option value="${i}" <c:if test="${completeTimeMinute==i}">selected="selected"</c:if>>${i}<spr:message code="taskReminder.completeTimeMinute"/></option>
														</c:forEach>
														<c:forEach var="i" begin="5" end="59" step="5">
															<option value="${i}" <c:if test="${completeTimeMinute==i}">selected="selected"</c:if>>${i}<spr:message code="taskReminder.completeTimeMinute"/></option>
														</c:forEach>
													</select>					
										    	</td>
										    	<th><spr:message code="taskReminder.relativeTimeType"/>:</th>
												<td>
													<select name="relativeTimeType">
														<option value="0"><spr:message code="taskReminder.relativeTimeType.workTime"/></option>
														<option value="1" <c:if test="${taskReminder.relativeTimeType==1}">selected="selected"</c:if>><spr:message code="taskReminder.relativeTimeType.generalDay"/></option>
													</select>
												</td>
											</tr>
											<tr>
												<th >
													<a href="#" class="link tipinfo"><span style="z-index: 100;text-align: left;"><spr:message code="taskReminder.conditional.Tip"/></span></a>
													<spr:message code="taskReminder.conditional"/>:
												</th>
												<td colspan="3">
													<div class="condExp-control">
														<a href="#"  class="link var" title="<spr:message code='taskReminder.script.common'/>" onclick="condExpSelectScript(this)"><spr:message code='taskReminder.script.common'/></a>
<%-- 													<f:flowVar defId="${defId}"></f:flowVar> --%>
														<span class="green"><spr:message code="taskReminder.flowVar"/></span>		
														<select name="flowVar" onchange="selectFlowVar(this,1)">
															<option value=""><spr:message code="taskReminder.notSelect"/></option>
															<optgroup label="<spr:message code='taskReminder.flowVar'/>"></optgroup>
															<c:forEach items="${flowVars}" var="flowVar">
																<option class="flowvar-item" value="${flowVar.fieldName}"  fname="${flowVar.fieldName}" fdesc="${flowVar.fieldDesc}" ftype="${flowVar.fieldType}">${flowVar.fieldDesc}</option>
															</c:forEach>
															<c:if test="${not empty defVars}">
																<optgroup label="<spr:message code='taskReminder.customVar'/>"></optgroup>
																<c:forEach items="${defVars}" var="defVars">
																	<option class="flowvar-item" value="${defVars.varKey}"  fname="${defVars.varKey}" fdesc="${defVars.varName}" ftype="${defVars.varDataType}">${defVars.varName}</option>
																</c:forEach>
															</c:if>
														</select>
														<span class="green"><spr:message code="taskReminder.comparison"/></span>
														<select name="flowOperate" >
														</select>
														<span class="green"><spr:message code="taskReminder.val"/></span>
														<input name="flowValue"/>
														<a onclick="generateExpress(this)" href="#" class="button">
															<span><spr:message code="taskReminder.create"/></span>
														</a>
													</div>
													<div class="condExp-editor">
														<textarea id="condExp" name="condExp" class="condExp-editor-input">${taskReminder.condExp}</textarea>
													</div> 
												</td>
											</tr>
											</table>
										</fieldset>
										<fieldset class="fieldset-detail">
											<legend>
												<span><spr:message code="taskReminder.bueToAction"/></span>
											</legend>
											<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
											<tr>
												<th width="100"><spr:message code="taskReminder.executeAction"/>: </th>
												<td colspan="3">
													<select id="action" onchange="change()" name="action">
														<option value="0" <c:if test="${taskReminder.action==0}">selected="selected"</c:if>> <spr:message code="taskReminder.executeAction.NoAction"/></option>
														<option value="1" <c:if test="${taskReminder.action==1}">selected="selected"</c:if>> <spr:message code="taskReminder.executeAction.complete"/></option>
														<option value="2" <c:if test="${taskReminder.action==2}">selected="selected"</c:if>> <spr:message code="taskReminder.executeAction.oppose"/></option>
														<option value="3" <c:if test="${taskReminder.action==3}">selected="selected"</c:if>> <spr:message code="taskReminder.executeAction.back"/></option>
														<option value="4" <c:if test="${taskReminder.action==4}">selected="selected"</c:if>> <spr:message code="taskReminder.executeAction.backToStart"/></option>
														<option value="5" <c:if test="${taskReminder.action==5}">selected="selected"</c:if>> <spr:message code="taskReminder.executeAction.toOther"/></option>
														<option value="6" <c:if test="${taskReminder.action==6}">selected="selected"</c:if>> <spr:message code="taskReminder.executeAction.end"/></option>
														<option value="7" <c:if test="${taskReminder.action==7}">selected="selected"</c:if>> <spr:message code="taskReminder.executeAction.callMethod"/></option>
													</select>
												</td>
											</tr>	
											<tr class="sub" width="100">
												<th > <spr:message code="taskReminder.executeScript"/>: </th>
												<td colspan="3">
													<div class="condExp-control">
														<a href="#"  class="link var" title="<spr:message code='taskReminder.script.common'/>" onclick="scriptSelectScript(this)"> <spr:message code="taskReminder.script.common"/></a>
														<span class="green"> <spr:message code="taskReminder.flowVar"/>  :</span>
 														<f:flowVar defId="${defId}" controlName="selFlowVar"></f:flowVar>  
														<select name="flowVar" onchange="selectFlowVar(this,2)">
															<option value=""> <spr:message code="taskReminder.notSelect"/></option>
															<c:forEach items="${flowVars}" var="flowVar">
																<option class="flowvar-item" value="${flowVar.fieldName}" fname="${flowVar.fieldName}" fdesc="${flowVar.fieldDesc}" ftype="${flowVar.fieldType}">${flowVar.fieldDesc}</option>
															</c:forEach>
														</select>			
													</div>
													<div class="script-editor">
														<textarea rows="6" cols="60" id="script" name="script" class="script-editor-input">${taskReminder.script}</textarea>
													</div> 
												</td>
											</tr>
											<tr class="choose-assigner" width="100">
												<th> <spr:message code="taskReminder.specifyAssignper"/>:</th>
												<td colspan="3">
													<input type="hidden" name="assignerId" value="${taskReminder.assignerId}"/>
													<input type="text" name="assignerName" readonly="readonly" value="${taskReminder.assignerName}"/>
													<a href="javascript:;" onclick="chooseAssigner()" class="button">
														<span> <spr:message code="taskReminder.specifyAssignper.choose"/></span>
													</a>
												</td>
											</tr>										
											</table>
										</fieldset>	
										<fieldset class="fieldset-detail">
											<legend>
												<span> <spr:message code="taskReminder.sentTaskReminder"/><spr:message code="menu.button.setting"/></span>
											</legend>
											<table class="table-detail" cellpadding="0" cellspacing="0" border="0">										
											<tr>
												<th width="100">
												  <spr:message code="taskReminder.sentTaskReminder"/>:
												</th>
												<td colspan="3">
													<label><input type="checkbox" id="needSendMsg"/> <spr:message code="taskReminder.sentTaskReminder.sent"/></label>
												</td>
											</tr>
											<tr class="send-msg-tr">
												<th> <spr:message code="taskReminder.sentTaskReminder.startTime"/>:</th>
												<td id="startTr" colspan="3">
													<input class="day-input" type="text" name="reminderStartDay" value="${reminderStartDay}"/>
													<span> <spr:message code="taskReminder.completeTimeDay"/></span>
													<select id="reminderStartHour" class="hourInput" name="reminderStartHour">
													<c:forEach var="i" begin="0" end="23" step="1">
														<option value="${i}" <c:if test="${reminderStartHour==i}">selected="selected"</c:if>>${i} <spr:message code="taskReminder.completeTimeHour"/></option>
													</c:forEach>
													</select>
													<select id="reminderStartMinute" class="minuteInput" name="reminderStartMinute">
														<c:forEach var="i" begin="0" end="4" step="1">
															<option value="${i}" <c:if test="${reminderStartMinute==i}">selected="selected"</c:if>>${i} <spr:message code="taskReminder.completeTimeMinute"/></option>
														</c:forEach>
														<c:forEach var="i" begin="5" end="59" step="5">
															<option value="${i}" <c:if test="${reminderStartMinute==i}">selected="selected"</c:if>>${i} <spr:message code="taskReminder.completeTimeMinute"/></option>
														</c:forEach>
													</select>							
												</td>
											</tr>
											<tr class="send-msg-tr">
												<th><a href="#" class="tipinfo"><span> <spr:message code="taskReminder.sendInterval"/> </span></a> <spr:message code="taskReminder.interval"/>:</th>
												<td id="endTr">
													<input class="day-input" type="text" name="reminderEndDay" value="${reminderEndDay}"/>
													<span> <spr:message code="taskReminder.completeTimeDay"/></span>
													<select id="reminderEndHour" class="hourInput" name="reminderEndHour">
														<c:forEach var="i" begin="0" end="23" step="1">
															<option value="${i}" <c:if test="${reminderEndHour==i}">selected="selected"</c:if>>${i} <spr:message code="taskReminder.completeTimeHour"/></option>
														</c:forEach>
													</select>
													<select id="reminderEndMinute" class="minuteInput" name="reminderEndMinute">
														<c:forEach var="i" begin="1" end="4" step="1">
															<option value="${i}" <c:if test="${reminderEndMinute==i}">selected="selected"</c:if>>${i} <spr:message code="taskReminder.completeTimeMinute"/></option>
														</c:forEach>
														<c:forEach var="i" begin="5" end="59" step="5">
															<option value="${i}" <c:if test="${reminderEndMinute==i}">selected="selected"</c:if>>${i} <spr:message code="taskReminder.completeTimeMinute"/></option>
														</c:forEach>
													</select>
												</td>
												<th> <spr:message code="taskReminder.sendImforTime"/>: </th>
												<td>
													<select name="times" >
														<c:forEach var="i" begin="0" end="10" step="1">
															<option value="${i}" <c:if test="${taskReminder.times==i}">selected="selected"</c:if>>${i}</option>
														</c:forEach>
													</select>
												</td>
											</tr>
										</table>
										</fieldset>
								</div>
							</div>
							    <div class="reminder-div-msg-mail" title="<spr:message code='taskReminder.mailContent'/>">
							    	<div class="panel-detail">
									    <table class="table-detail" cellpadding="0" cellspacing="0" border="0">
									    	<tr>
									     		<th width="60" > <spr:message code="taskReminder.mailContent"/>: </th>
												<td>
													<div>
														<a href="#"  class="link var" title=" <spr:message code='taskReminder.chooseTemplate'/>" onclick="slectTemplate('mailContent',false)"> <spr:message code="taskReminder.chooseTemplate"/></a>
													</div>
													<textarea id="mailContent"  name="mailContent" class="ckeditor-editor" rows="20" cols="50">${taskReminder.mailContent}</textarea>
												</td>
											</tr>
										</table>
									</div>
							    </div>
							    <div class="reminder-div-msg-inter" title="<spr:message code='taskReminder.innerContent'/>">
						     		<div class="panel-detail">
									    <table class="table-detail" cellpadding="0" cellspacing="0" border="0">
										    <tr>
										     	<th width="60" > <spr:message code="taskReminder.innerContent"/>: </th>
												<td>
													<div>
														<a href="#"  class="link var" title="<spr:message code='taskReminder.chooseTemplate'/>" onclick="slectTemplate('msgContent',false)"> <spr:message code="taskReminder.chooseTemplate"/></a>
													</div>
													<textarea id="msgContent"  name="msgContent" class="ckeditor-editor" rows="12" cols="50">${taskReminder.msgContent}</textarea>
												</td>
												</tr>
										</table>
									</div>
							    </div>
							    <div class="reminder-div-msg-sms" title="<spr:message code='taskReminder.smsContent'/>">
							    	<div class="panel-detail">
									    <table class="table-detail" cellpadding="0" cellspacing="0" border="0">
									    	<tr >
										     	<th width="60" > <spr:message code="taskReminder.smsContent"/>: </th>
												<td>
												<div>
													<a href="#"  class="link var" title="<spr:message code='taskReminder.chooseTemplate'/>" onclick="slectTemplate('smsContent',true)"> <spr:message code="taskReminder.chooseTemplate"/></a>
												</div>
												<textarea id="smsContent" name="smsContent" rows="12" cols="50">${taskReminder.smsContent}</textarea>
												</td>
											</tr>
										</table>
									</div>
						     	</div>
							</div>
						</div>
						<div>
							<input type="hidden" name="taskDueId" value="${taskReminder.taskDueId}" />
							<input type="hidden" name="actDefId" value="${actDefId}" />
							<input type="hidden" name="nodeId" value="${nodeId}" />
							<input type="hidden" id="defId" name="defId" value="${defId}" />
						</div>
					</form>
					</div>
				</div>
				<div class="reminders-list" position="right">
					<div class="reminders-div">
						<table class="table-grid" id="reminders-list-table">
							<thead>
								<tr>
									<th> <spr:message code="taskReminder.name"/></th>
									<th> <spr:message code="taskReminder.manege"/></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${taskReminders }" var="reminder" varStatus="status">
									<tr <c:if test="${status.index%2==0 }">class="odd"</c:if><c:if test="${status.index%2==1 }">class="even"</c:if>>
										<td>
											<span>${reminder.name }</span>
											<input class="pk" type="hidden" value="${reminder.taskDueId}"/>
										</td>
										<td>
											<a class="link del" href="del.ht?taskDueId=${reminder.taskDueId}"> <spr:message code="menu.button.del"/></a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		 <a href="" id="goLocation" style="display:none;"></a>
	</div>
</body>
</html>