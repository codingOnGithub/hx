<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="sysTestResult"/></title>
<style type="text/css">
	.owner-span{
		font-size: 13px;
		background-color: #EFF2F7;
	    border: 1px solid #CCD5E4;
	    border-radius: 5px 5px 5px 5px;
	    cursor: default;
	    float: left;
	    height: auto !important;
	    margin: 3px;
	    overflow: hidden;
	    padding: 2px 4px;
	    white-space: nowrap;
	}
</style>
<f:js pre="js/lang/view/platform/system" ></f:js>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript">
	$(function(){
		$("a.moreinfo").live('click',function(){
			var me = $(this),
				hrefStr = me.attr('hrefstr');
			if(!hrefStr)return;
			openDetailWin({url:hrefStr,hasClose:true});
		});
	});
	//显示用户详情
	function openDetailWin(conf){
		var dialogWidth=650;
		var dialogHeight=500;
		
		conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:0,center:1},conf);

		var winArgs="dialogWidth="+conf.dialogWidth+"px;dialogHeight="+conf.dialogHeight
			+"px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center;
		var url = conf.url + '&hasClose=' +conf.hasClose;
		var rtn=window.showModalDialog(url,"",winArgs);		
	};
	//选择器回填
	function setOwnerSpan(tr,json,href){
		var div = $("div.owner-div",tr);
		if(!div||div.length==0)return;
		div.empty();
		if(!json||json.length==0)return;
		for(var i=0,c;c=json[i++];){
			var a = $('<a class="moreinfo"></a>').html(c.fullname).attr("ownerId",c.id);
			if(href){
				a.attr("hrefstr",href+c.id);
				a.attr("href","#");
			}
			var	span = $('<span class="owner-span"></span>').html(a);
			div.append(span);
		}
	};	
	//选择用户
	function chooseUser(obj) {
		var tr=$(obj).parents("tr"),
			href = "${ctx}/platform/system/sysUser/get.ht?userId=";
		UserDialog({isSingle:false,selectUsers:'',callback:function(userIds, fullnames,email,mobile,json){
			setOwnerSpan(tr,json,href);
		}});
	};
	function clearAll(){
		$("div.owner-div").empty();
	};
	//启动
	function startProcess(){
		var flowKey = $("#flowKey").val(),
			count = $("#count").val(),
			formData = $("#formData").val(),
			userAry = [];

		if(!flowKey||!count||!formData){
			$.ligerDialog.warn($lang_system.sysTestResult.list.validate_msg_args,$lang.tip.warn);
			return;
		}
		$("a.moreinfo").each(function(){
			var me = $(this),
				ownerId = me.attr("ownerid");

			userAry.push(ownerId);
		});
		if(userAry.length==0){
			$.ligerDialog.warn($lang_system.sysTestResult.list.validate_msg_user,$lang.tip.warn);
			return;
		}
		userAry = userAry.join(',');
		
		$.ligerDialog.confirm($lang_system.sysTestResult.list.tip_msg_start,$lang.tip.msg,function(r){
			if(r){
				$.ligerDialog.waitting($lang_system.sysTestResult.list.waitting);
				var url = __ctx + '/ecp/bpm/sysTestResult/testStart.ht';
				$.post(url,{flowKey:flowKey,formData:formData,userIds:userAry,count:count},function(r){
					$.ligerDialog.closeWaitting();
					var data = eval("("+r+")");
					if(data.result){
						$.ligerDialog.success(data.message,$lang.tip.success,function(){
							this.location.reload();
						});
					}
					else{
						$.ligerDialog.error(data.message,$lang.tip.errorMsg);
					}
				});
			}
		});
	};
	function doNext(){
		var flowKey = $("#flowKey2").val(),
			numToComplete = $("#numToComplete").val();
		if(!flowKey){
			$.ligerDialog.warn($lang_system.sysTestResult.list.validate_msg_require_actDefKey,$lang.tip.warn);
			return;
		}
		
		$.ligerDialog.confirm($lang_system.sysTestResult.list.tip_msg_start,$lang.tip.msg,function(r){
			if(r){
				$.ligerDialog.waitting($lang_system.sysTestResult.list.waitting);
				var url = __ctx + '/ecp/bpm/sysTestResult/testNext.ht';
				$.post(url,{flowKey:flowKey,num:numToComplete},function(r){
					$.ligerDialog.closeWaitting();
					var data = eval("("+r+")");
					if(data.result){
						$.ligerDialog.success(data.message,$lang.tip.success);
					}
					else{
						$.ligerDialog.error(data.message,$lang.tip.errorMsg);
					}
				});
			}
		});
	};
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="sysTestResult"/></span>
			</div>
		</div>
		<div class="panel-body">
			<table class="table-detail">
				<tbody>
					<tr>
						<th colspan="2" style="text-align: center;font-weight:bold;font-size:14px;"><spr:message code="sysTestResult.title.start"/></th>
					</tr>
					<tr>
						<th><spr:message code="sysTestResult.list.actDefKey"/>:</th>
						<td>
							<input id="flowKey" type="text" />
						</td>
					</tr>	
					<tr>
						<th><spr:message code="sysTestResult.list.startUser"/>:</th>
						<td>
							<input type="button" onclick="chooseUser(this)" value="<spr:message code="sysTestResult.list.startUser.select"/>"/>
							<input type="button" onclick="clearAll(this)" value="<spr:message code="sysTestResult.list.startUser.reset"/>"/>
							<div class="owner-div"></div>
						</td>
					</tr>
					<tr>
						<th><spr:message code="sysTestResult.list.startCount"/>:</th>
						<td>
							<input id="count" type="text" />
						</td>
					</tr>
					<tr>
						<th><spr:message code="sysTestResult.list.formData"/>:</th>
						<td>
							<textarea id="formData" cols="100" rows="8"></textarea>
							<input type="button" value="<spr:message code="sysTestResult.list.start"/>" onclick="startProcess()"/>
						</td>
					</tr>
					<tr>
						<th colspan="2" style="text-align: center;font-weight:bold;font-size:14px;"><spr:message code="sysTestResult.list.check"/></th>
					</tr>
					<tr>
						<th><spr:message code="sysTestResult.list.actDefKey"/>:</th>
						<td>
							<input id="flowKey2" type="text" />
						</td>
					</tr>
					<tr>
						<th><spr:message code="sysTestResult.list.completeCount"/>:</th>
						<td>
							<input id="numToComplete" type="text" />
							<input type="button" value="<spr:message code="sysTestResult.list.start"/>" onclick="doNext()"/>
						</td>
					</tr>
				</tbody>
			</table> 
		</div>			
	</div>
</body>
</html>


