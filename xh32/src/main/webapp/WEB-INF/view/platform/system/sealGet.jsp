<%--
	time:2012-08-29 11:26:00
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/getById.jsp" %>
	<%@include file="/commons/include/get.jsp" %>
	<title><spr:message code="seal"/> <spr:message code="title.detail"/></title>
	<f:js pre="js/lang/view/platform/system" ></f:js>
	<script type="text/javascript" src="${ctx}/js/ntkosign/NtkoSignManage.js"></script>
	<style type="text/css">
		.displaynone{
			display:none;
		}
	</style>
	<script type="text/javascript">
		$(function(){
			$('body').addClass("displaynone");
			var nktoSignManage=new NtkoSignManage();
			var fileId = '${seal.attachmentId}';
			nktoSignManage.load('divSeal',fileId);
			$('body').removeClass("displaynone");
		});
	</script>
</head>
<body>
<div class="panel">
<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="seal"/> <spr:message code="title.detail"/></span>
			</div>
			<c:if test="${canReturn==0}">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back" href="list.ht"><span></span><spr:message code="menu.button.back"/></a></div>
				</div>
			</div>
			</c:if>
		</div>
		</div>
		<div class="panel-body">
			<div class="panel-detail">
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th width="20%"><spr:message code="seal.sealName"/>:</th>
						<td>${seal.sealName}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="seal.belongId"/>:</th>
						<td>${seal.belongId}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="seal.belongName"/>:</th>
						<td>${seal.belongName}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="seal.seal"/>：</th>
						<td><div id="divSeal"></div></td>
					<tr>
				</table>
			</div>
		</div>
</div>

</body>
</html>
