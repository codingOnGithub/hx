<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<title>
	<sitemesh:write property='title'></sitemesh:write></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/lib/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/lib/easyui/themes/icon.css">
	<link rel="stylesheet" href="${ctx}/js/lib/z-tree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctx}/js/lib/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/lib/easyui/jquery.easyui.all.js"></script>
	<script type="text/javascript" src="${ctx}/js/lib/z-tree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/topCall.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/linkButtons.js"></script>
		<script type="text/javascript" src="${ctx}/js/hotent/foldBox.js"></script>
	<sitemesh:write property='head' />
</head>
<body class="easyui-layout">
	<sitemesh:write property='body' />
</body>
</html>
