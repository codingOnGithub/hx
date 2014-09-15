
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@include file="/commons/include/get.jsp"%>
<title><spr:message code="sysOrg.title"/><spr:message code="title.list"/></title>
<script type="text/javascript">
	var orgId=0;
	function setLocation(id){
		window.returnValue={orgId:id};
		window.close();
	}
	
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="sysOrg.title"/><spr:message code="title.list"/></span>
			</div>
		</div>
		<div style="height:500px;">
			<iframe id="sysOrgListFrame" name="sysOrgListFrame" height="90%"
				width="100%" frameborder="0"
				src="${ctx}/platform/system/sysOrg/search.ht"></iframe>
		</div>
	</div>
	<!-- end of panel -->
</body>
</html>


