<%--
	time:2012-08-29 11:26:00
	desc:edit the 电子印章
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<%@include file="/js/msg.jsp"%>
	<title><spr:message code="title.add"/> <spr:message code="seal"/></title>
	<f:js pre="js/lang/view/platform/system" ></f:js>
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/ntkosign/NtkoSignManage.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
	
	<style type="text/css">
		.displaynone{
			display:none;
		}
	</style>
	<script type="text/javascript">
		$(function() {
			eventBind();
		});
		
		function showResponse(responseText) {
			var fileObj = document.getElementById('filename');
			var realpath = getSelectText(fileObj);
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				var filename=realpath;
				var signname=$("#signname").val();
				var username=$("#username").val();
				var password=$("#password").val();
				var rtn={
						status:true,
						filename:filename,
						signname:signname,
						username:username,
						password:password,
						showImageId:obj.getMessage()
					};
					window.returnValue=rtn;
					window.close();
			} else {
				window.returnValue=null;
				window.close();
			}
		}
		
		//事件绑定
		function eventBind(){
			//Ok Button
			$("#btnSelect").click(function(){
				try{
					var frm=$('#signForm').form();					
					var options={};
					
					options.success=showResponse;
					frm.ajaxForm(options);
					
					if(frm.valid()){
						frm.submit();
					}
				}catch(ex){
					$.ligerDialog.warn($lang_system.seal.addSign.addTrustedSites,$lang.tip.warn);
				}
			});
			
			$("#filename").bind("change",function(){
				var sUrl = $(this).val();
				if(!sUrl){
					return false;
				}
				var Extlist = ".jpg.jpeg.bmp.png.";	
				if(Extlist.indexOf('.'+getExt(sUrl)+'.')==-1){
					$.ligerDialog.warn($lang_system.seal.addSign.selectValidPicture,$lang.tip.warn);
					$("#filename").replaceWith( $(this).clone( true ));
				}
			});
		}
		
		function getExt(sUrl)
		{
	        var arrList = sUrl.split(".");
	        return arrList[arrList.length-1];
		}
		
		function getSelectText(obj)    //获取用户选择文本 
		{
			if (obj) { 
				if(window.navigator.userAgent.indexOf("MSIE") >= 1){ 
					obj.select(); 
					return document.selection.createRange().text;
				}else if(window.navigator.userAgent.indexOf("Firefox") >= 1){
					if(obj.files){
						return window.URL.createObjectURL(obj.files[0]);	  
					}		  
					return obj.value;
				}		  
				return obj.value;
			}
		}
		
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top" >
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="title.add"/><spr:message code="seal"/></span>
			</div>
		</div>
		<div class="panel-body">
			<form id="signForm" method="post" action="${ctx}/platform/system/sysFile/saveFile.ht?ajaxType=obj&uploadName=filename">  <!-- ajaxType=obj  表示返回的是一个对象 -->
				<table class="table-detail">
					<tr>
						<th><spr:message code="seal.picture"/>：</th>
						<td>
							<input id="filename" name="filename" type="file" validate="{required:true}"  class="inputText">
							<span class="green"><spr:message code="seal.picture.tip"/></span>
						</td>
					</tr>
					<tr>
						<th><spr:message code="seal.sealName"/>：</th>
						<td><input id="signname" name="signname" type="text" validate="{required:true,maxlength:100}" class="inputText" /></td>
					</tr>
					<tr>
						<th><spr:message code="seal.belongName"/>：</th>
						<td>
							<input id="username" name="username" type="text" validate="{required:true,maxlength:50}" class="inputText" />
						</td>
					</tr>
					<tr>
						<th><spr:message code="seal.password"/>：</th>
						<td>
							<input id="password" name="password" type="password" validate="{required:true,minlength:6,maxlength:32}"  class="inputText"/>
							<span class="green"><spr:message code="seal.password.tip"/></span>
						</td>
					</tr>
					<tr>
						<th><spr:message code="seal.password.confirm"/>：</th>
						<td><input id="passwordConfirm" name="passwordConfirm" type="password" validate="{required:true,equalTo:'password'}"   class="inputText"/></td>
					</tr>
				</table>
			</form>
		</div>
		<div position="bottom"  class="bottom" style='margin-top:10px'>
			<a class='button' id="btnSelect"><span class="icon ok"></span><span ><spr:message code="operator.ok"/></span></a>
			<a class="button" id="btnClose" style='margin-left:10px;'  onclick="window.close()"><span class="icon cancel"></span><span ><spr:message code="operator.cancel"/></span></a>
		</div>
	</div>
</body>
</html>
