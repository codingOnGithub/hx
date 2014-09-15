<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<f:js pre="js/lang/view/platform/system" ></f:js>
	<title><spr:message code="resources.icons.title"/></title>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/FlexUploadDialog.js"></script>
	<script type="text/javascript">
		var selectedImg=null;
		$(function(){
			$("#imgList img").click(function(){
				if(selectedImg){
					$(selectedImg).removeClass('selected');
				}
				$(this).addClass('selected');
				selectedImg=this;
			});
			
			//上传图标
			$("#iconForm").ajaxForm({success:showResponse});
			$("a.upload").click(function(){
				if($("#iconFile").val()==''){
					$.ligerDialog.warn($lang_system.resources.dialog.selectPic,$lang.tip.warn);
				}else{
					$("#iconForm").submit();
				}
			});
			
			$("a.del").click(function(){
			   if(selectedImg){
					var iconPath=$(selectedImg).attr('path');
				   $.post('delFile.ht',{path:iconPath},function(data){
					   showResponse(data);
				   });
			   }
			});
		});
		
		function showResponse(responseText){
			var obj=new com.hotent.form.ResultMessage(responseText);
			if(obj.isSuccess()){//成功
				$.ligerDialog.success(obj.getMessage(),$lang.tip.msg,function(){
					window.location.reload();		
				});
		    }else{//失败
		    	$.ligerDialog.err($lang.tip.error,$lang_system.resources.dialog.error_msg_setPicture,obj.getMessage());
		    }
		};
		
	</script>
	<style type="text/css">
		html { overflow-x: hidden; overflow-y: auto; }
		#imgList{
			overflow: auto;
		}
		#imgList img {
			cursor: pointer;
			border-width: 1px; 
			border-style: solid;
			border-color: transparent; 
			padding: 1px;
		}
		
		#imgList .selected {
			border-color:  #86a9d1; 
			background-color:#c3dcfc; 
		}
	</style>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label"><spr:message code="res.res.pic"/></span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link del" href="#"><span></span><spr:message code="res.del"/></a></div>
			</div>
			
		</div>
		<div class="panel-toolbar">
			<form id="iconForm" method="post" action="uploadIcon.ht">
				<div class="group"><input type="file"  id="iconFile"  name="iconFile"  /></div>
				<div class="l-bar-separator"></div>
					<a class="link upload" href="#"><spr:message code="res.upload"/></a></div>
				<input type="hidden" name="path" id="iconPath" value="${iconPath}" />
			</form>
		</div>
	</div>
	<div class="panel-body" id="imgList">
		<c:if test="${empty iconList}">
			<span ><spr:message code="res.no.res.pic"/></span>
		</c:if>
		<c:forEach items="${iconList}" var="d" varStatus="status">
		<img src="${ctx}/${iconPath}/${d}" title="${d}" path="${iconPath}/${d}"/>
		</c:forEach>
	</div>
</div>
</body>
</html>
