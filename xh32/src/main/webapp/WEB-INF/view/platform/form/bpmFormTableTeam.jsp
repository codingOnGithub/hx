<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
<f:js pre="js/lang/view/platform/form" ></f:js>
<title><spr:message code="bpmFormTable.team.title"/></title>
<f:link href="tree/zTreeStyle.css"></f:link>
<script type="text/javascript" src="${ctx}/js/tree/jquery.ztree.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript" src="${ctx}/js/util/SelectOption.js"></script>
<script type="text/javascript">
var tableId = '${bpmFormTable.tableId}',valid, team = '${bpmFormTable.team}';

$(function() {
	$("#teamLayout").ligerLayout({
		leftWidth : 150,
		height : '100%',
		allowLeftResize : false
	});
	loadTree();
	valid =  $("#teamItem").form();
	var height = $("body").height();
	$("#fieldtree").height(height-95);
	
	parseTeam(team);	
	
	var teamItem = 	$("#teamItem");
	$("a.add").click(function(){
		add(true);
	});
	$("a.save").click(function(){
		saveData();
	});
	
	teamItem.delegate("select[name='teamField']", "focus click", function() {		
		selectTeam (this);
	});
	
	$(".moveFieldTop").click(function(){
		var td = $(this).parent().parent().parent();
		var select  = $("select[name='teamField']",td).get(0);
		__SelectOption__.moveTop(select);
	});
	$(".moveFieldUp").click(function(){
		var td = 	$(this).parent().parent().parent();
		var select  = $("select[name='teamField']",td).get(0);
		__SelectOption__.moveUp(select,'1');
	});
	$(".moveFieldDown").click( function(){
		var td = $(this).parent().parent().parent();
		var select  = $("select[name='teamField']",td).get(0);;
		__SelectOption__.moveDown(select,'1');
	});
	$(".moveFieldBottom").click( function(){
		var td = $(this).parent().parent().parent();
		var select  = $("select[name='teamField']",td).get(0);
		__SelectOption__.moveBottom(select);
	});
	
	$('#isShow').click(function(){
		if($(this).is(":checked"))
			$('#showPosition').show();
		else
			$('#showPosition').hide();	
	});
	
	$("a.moveup,a.movedown").click(move);

});

//绑定上下移动	
function move(){
 	var obj=$(this);
 	var direct=obj.hasClass("moveup");
 	var objFieldset=obj.closest('[zone="team"]');
	if(direct){
		var prevObj=objFieldset.prev();
		if(prevObj.length>0){
			objFieldset.insertBefore(prevObj);	
		}
	}
	else{
		var nextObj=objFieldset.next();
		if(nextObj.length>0){
			objFieldset.insertAfter(nextObj);
		}
	}
};

//加载树
function loadTree(){
	BpmFormTableTeam.getFieldsByTableId(tableId);
}


</script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/form/BpmFormTableTeam.js"></script>

<body>	
	<div class="panel">
			<div id="teamLayout">
				<div position="left" title="<spr:message code="bpmFormTable.team.tableField"/>" style="overflow: auto; float: left; width: 100%; height: 100%;">
					<div class="panel-toolbar tree-title">
						<span class="toolBar">
							<div class="group">
								<a class="link reload" onclick="loadTree()"><span></span><spr:message code="menu.button.refresh"/></a>
							</div>
						</span>
					</div>
					<ul id="colstree" class="ztree"></ul>
				</div>
				<div position="center" title="<spr:message code="bpmFormTable.team.setting"/>" style="overflow: auto;">
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group">
								<a class="link save"  href="#"><span></span><spr:message code="menu.button.save"/></a>
							</div>
							<div class="l-bar-separator"></div>
							<div class="group">
								<a class="link add"  href="#"><span></span><spr:message code="menu.button.add"/></a>
							</div>
							<div class="l-bar-separator"></div>
							<div class="group">
								<a class="link back" href="list.ht"><span></span><spr:message code="menu.button.back"/></a>
							</div>
						</div>
					</div>
					<div>
						<table cellpadding="1" cellspacing="1"  class="table-grid">
							<tr>
								<th style="text-align: left;" width="120px"><spr:message code="bpmFormTable.team.isShow"/>：</th>
								<th style="text-align: left;" width="70%" >
									<input type="checkbox" id="isShow"  checked="checked">
									&nbsp;&nbsp;&nbsp;&nbsp;
									<select id="showPosition"  >
										<option value="1"><spr:message code="bpmFormTable.team.showPosition.after"/></option>
										<option value="2"><spr:message code="bpmFormTable.team.showPosition.before"/></option>
									</select>
								<th>
							</tr>
						</table>
					</div>
					<div>
						<form  id ="teamItem" >
						
						</form>
					</div>
					<div  id="cloneTemplate"  style="display: none;">
							<fieldset style="margin: 5px 0px 5px 0px;" zone="team" >
								<legend>
									<span><spr:message code="bpmFormTable.team.setting"/></span>
									<div class="group" style="float: right;">
										<a class="link del" var="del" title="<spr:message code="menu.button.del"/>"></a>
										&nbsp;&nbsp;&nbsp;&nbsp;
											<a class='link moveup' title="<spr:message code="operator.moveup"/>"></a>
											<a class='link movedown' title="<spr:message code="operator.movedown"/>"></a>
									</div>
								</legend>
								<table cellpadding="1" cellspacing="1"  class="table-detail">
									<tr>
										<th width="10%"><spr:message code="bpmFormTable.team.name"/></th>
										<td><input type="text" width="70%" name="teamName" class='inputText valid'  validate="{required:true}" /></td>
									</tr>
									<tr>
										<th><spr:message code="bpmFormTable.team.field"/></th>
										<td>
												<div style="float: left;">
													<select size="10" style="width:200px;height:200px;display:inline-block;" id="teamField1" name="teamField" ondblclick="removeOpt(this)"  ></select>						
												</div>
												<div style="float: left;">
													</br>
													<p>
														<input type="button" value="<spr:message code="operator.movetop"/>" class="moveFieldTop">
													</p>
													</br>
													<p>
														<input type="button" value="<spr:message code="operator.moveup"/>" class="moveFieldUp" >
													</p>
													</br>
													<p>
														<input type="button" value="<spr:message code="operator.movedown"/>" class="moveFieldDown" >
													</p>
													</br>
													<p>
														<input type="button" value="<spr:message code="operator.movebottom"/>" class="moveFieldBottom">
													</p>
												</div>
			
											</table>
										</td>
									</tr>
								</table>
							</fieldset>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>		